package th.ac.kmutt.research.portlet;

import java.io.IOException;
import java.io.StringWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.PortletPreferences;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.portlet.ResourceURL;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;
import org.springframework.web.portlet.bind.PortletRequestDataBinder;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import th.ac.kmutt.research.constant.ServiceConstant;
import th.ac.kmutt.research.form.CopyrightForm;
import th.ac.kmutt.research.mapper.CustomObjectMapper;
import th.ac.kmutt.research.model.CopyrightM;
import th.ac.kmutt.research.service.ResearchService;
import th.ac.kmutt.research.utils.ImakeUtils;
import th.ac.kmutt.research.xstream.common.ImakeResultMessage;

import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.portlet.PortletResponseUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.User;
import com.liferay.portal.theme.ThemeDisplay;
import com.opencsv.bean.BeanToCsv;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

@Controller("copyRightController")
@RequestMapping("VIEW")
@SessionAttributes({"copyrightForm"})
public class CopyRightController {
    private static final Logger logger = Logger
            .getLogger(CopyRightController.class);
    @Autowired
    @Qualifier("researchServiceWSImpl")
    private ResearchService researchService;
    private static SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
    @Autowired
    private CustomObjectMapper customObjectMapper;

    @InitBinder
    public void initBinder(PortletRequestDataBinder binder, PortletPreferences preferences) {
        logger.debug("initBinder");
        binder.registerCustomEditor(byte[].class, new ByteArrayMultipartFileEditor());
        final String[] ALLOWED_FIELDS = {"copyrightM.copyrightId", "copyrightM.createdBy", "copyrightM.createdDate",
                "copyrightM.budgetYear", "copyrightM.copyrightCode", "copyrightM.updatedBy",
                "copyrightM.updatedDate", "copyrightM.docType", "copyrightM.engName",
                "copyrightM.proposeDate", "copyrightM.proposeNo", "copyrightM.receiveDate",
                "copyrightM.remark", "copyrightM.requestNo", "copyrightM.researchType",
                "copyrightM.thaiName", "copyrightM.researchProject.researchProjectId",
                "copyrightM.innovativeWorksType.workTypeId",
                "proposeDate", "receiveDate",
                "mode", "command", "keySearch", "docsAssign", "pageNo", "paging.pageSize", "ids", "tab", "filter"};


        binder.setAllowedFields(ALLOWED_FIELDS);
    }

    @RequestMapping
    // default (action=list)
    public String listResearchCopyRight(javax.portlet.RenderRequest request, Model model) {
        User user = (User) request.getAttribute(WebKeys.USER);
        CopyrightForm copyrightForm = null;
        if (!model.containsAttribute("copyrightForm")) {
            copyrightForm = new CopyrightForm();
            model.addAttribute("copyrightForm",
                    copyrightForm);
        } else {
            copyrightForm = (CopyrightForm) model.asMap().get("copyrightForm");
        }
        String keySearch = copyrightForm.getKeySearch();
        CopyrightM copyrightM = new CopyrightM();
        copyrightM.setKeySearch(keySearch);
        copyrightM.setPaging(copyrightForm.getPaging());
        copyrightM.getPaging().setPageNo(copyrightForm.getPageNo());
        copyrightM.setUserid(user.getUserId() + "");
        if (copyrightForm.getTab() == null)
            copyrightForm.setTab(ServiceConstant.TAB_ALL);
        if (copyrightForm.getFilter() == null)
            copyrightForm.setFilter(ServiceConstant.FILTERS[0]);
        copyrightM.setTab(copyrightForm.getTab());
        copyrightM.setFilter(copyrightForm.getFilter());

        ImakeResultMessage imakeResultMessage = researchService
                .searchCopyrightM(copyrightM);
        model.addAttribute("researchCopyRights", imakeResultMessage.getResultListObj());
        //copyrightForm.getPaging().setPageSize(ImakeUtils.PAGE_SIZE);
        copyrightForm.setPageCount(ImakeUtils.calculatePage(copyrightForm.getPaging().getPageSize(), Integer.parseInt(imakeResultMessage.getMaxRow())));
        int[] start_end = ImakeUtils.calculatePageStartEnd(copyrightForm.getPageNo(), copyrightForm.getPageCount());
        copyrightForm.setPageStart(start_end[0]);
        copyrightForm.setPageEnd(start_end[1]);
        return "docs/researchCopyRightList";
    }

    @RequestMapping(params = "action=add")
    // render phase
    public String showResearchCopyright(Model model) {
        // Used for the initial form as well as for redisplaying with errors.
        /*
		 * if (!model.containsAttribute("site")) { model.addAttribute("site",
		 * new PetSite()); }
		 */
        //	copyrightForm
        CopyrightForm copyrightForm = new CopyrightForm();
        copyrightForm.setMode("add");
        model.addAttribute("copyrightForm",
                copyrightForm);
        return "docs/researchCopyRightAddEditView";
    }

    @RequestMapping(params = "action=doSubmit") // action phase
    public void populateSite(javax.portlet.ActionRequest request, javax.portlet.ActionResponse response,
                             @ModelAttribute("copyrightForm") CopyrightForm copyrightForm,
                             BindingResult result, Model model) {
        // new PetSiteValidator().validate(petSite, result);
		/*
		 * if (!result.hasErrors()) { this.petSites.put(petSite.getName(),
		 * petSite.getUrl()); status.setComplete();
		 * response.setRenderParameter("action", "list"); }
		 */
        String command = copyrightForm.getCommand();
        String mode = copyrightForm.getMode();
        User user = (User) request.getAttribute(WebKeys.USER);
        copyrightForm.getCopyrightM().setUpdatedBy(user.getUserId() + "");
        String proposeDate = copyrightForm.getProposeDate();
        String receiveDate = copyrightForm.getReceiveDate();

        if (proposeDate != null && proposeDate.trim().length() > 0)
            try {
                copyrightForm.getCopyrightM().setProposeDate(format1.parse(proposeDate));
            } catch (ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        if (receiveDate != null && receiveDate.trim().length() > 0)
            try {
                copyrightForm.getCopyrightM().setReceiveDate(format1.parse(receiveDate));
            } catch (ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        //String docsAssign=copyrightForm.getDocsAssign();
        command = "addEditView";
        Integer refId = null;
        if (mode != null) {
            if (mode.equals("add") || mode.equals("copy")) {
                copyrightForm.getCopyrightM().setCreatedBy(user.getUserId() + "");
                copyrightForm.getCopyrightM().setCopyrightId(null);
                refId = researchService.saveCopyrightM(copyrightForm.getCopyrightM());
            } else if (mode.equals("edit")) {
                refId = researchService.updateCopyrightM(copyrightForm.getCopyrightM());
            } else if (mode.equals("deleteItems")) {
                if (copyrightForm.getIds() != null && copyrightForm.getIds().length > 0) {
                    researchService.deleteItemsCopyrightM(copyrightForm.getIds());
                }
                command = "list";
            } else {
                command = "list";
            }
				/*if(refId!=null)
					if(docsAssign!=null && docsAssign.trim().length()>0){
						DocAssignMappingM mapping =new DocAssignMappingM();
						mapping.setRefId(refId);
						mapping.setRefType("COPYRIGHT");
						mapping.setUserId(docsAssign);
						researchService.saveDocAssignMapping(mapping);
					}*/
        }

        response.setRenderParameter("action", command);
        if (mode.equals("add") || mode.equals("edit") || mode.equals("copy")) {
            response.setRenderParameter("copyrightId", refId + "");
            response.setRenderParameter("mode", "edit");
        }
    }

    @RequestMapping(params = "action=delete")
    public void removeSite(@RequestParam("copyrightId") Integer copyrightId,
                           ActionResponse response) {
        CopyrightM copyrightM = new CopyrightM();
        copyrightM.setCopyrightId(copyrightId);
		/*copyrightM.setFlag("0");
		copyrightM.setUpdateType("flag");*/
        //researchService.updateFlagCopyright(copyrightM);
        researchService.deleteCopyrightM(copyrightM);
        response.setRenderParameter("action", "list");
    }

    @RequestMapping(params = "action=undoItem")
    public void undoItem(@RequestParam("copyrightId") Integer copyrightId,
                         ActionResponse response) {
        CopyrightM copyrightM = new CopyrightM();
        copyrightM.setCopyrightId(copyrightId);
        copyrightM.setUpdateType(ServiceConstant.UPDATE_TYEP_FLAG);
        copyrightM.setFlag(ServiceConstant.FLAG_ACTIVE);
        researchService.updateFlagCopyright(copyrightM);
        //researchService.deleteResearchProjectM(researchProjectM);
        response.setRenderParameter("action", "list");
    }

    @RequestMapping(params = "action=addEditView")  // render phase
    public String addEditViewUser(javax.portlet.RenderRequest request, @RequestParam(value = "copyrightId", required = false) Integer copyrightId, @RequestParam("mode") String mode, Model model) {
        model.addAttribute("mode", mode);
        CopyrightForm copyrightForm = null;
        if (!model.containsAttribute("copyrightForm")) {
            copyrightForm = new CopyrightForm();
            model.addAttribute("copyrightForm",
                    copyrightForm);
        } else {
            copyrightForm = (CopyrightForm) model.asMap().get("copyrightForm");
        }


        CopyrightM copyrightM = null;
        if (mode.equals("add")) {
            copyrightM = new CopyrightM();
        }
        User user = (User) request.getAttribute(WebKeys.USER);
        String userid = null;
        if (user != null)
            userid = user.getUserId() + "";
        else if (mode.equals("edit")) {
            copyrightM = researchService.findResearchCopyRightById(copyrightId, userid);
            Date proposeDate = copyrightM.getProposeDate();
            Date receiveDate = copyrightM.getReceiveDate();

            if (proposeDate != null)
                copyrightForm.setProposeDate(format1.format(proposeDate));

            if (receiveDate != null)
                copyrightForm.setReceiveDate(format1.format(receiveDate));

        } else if (mode.equals("copy")) {
            copyrightM = researchService.findResearchCopyRightById(copyrightId, userid);
            Date proposeDate = copyrightM.getProposeDate();
            Date receiveDate = copyrightM.getReceiveDate();

            if (proposeDate != null)
                copyrightForm.setProposeDate(format1.format(proposeDate));

            if (receiveDate != null)
                copyrightForm.setReceiveDate(format1.format(receiveDate));

            // reset for copy
            copyrightM.setCopyrightCreators(null);
            copyrightM.setDocAssignMappings(null);
            copyrightM.setCopyrightDocuments(null);
        }
        copyrightForm.setMode(mode);
        copyrightForm.setCopyrightM(copyrightM);
        model.addAttribute("copyrightForm",
                copyrightForm);
        return "docs/researchCopyRightAddEditView";

    }

    @ResourceMapping(value = "research_group_resource_id")
    public void myResourceMethod(ResourceRequest request,
                                 ResourceResponse response) throws PortletException, IOException {
        JSONObject json = JSONFactoryUtil.createJSONObject();
        try {
            ThemeDisplay themeDisplay = (ThemeDisplay) request
                    .getAttribute(WebKeys.THEME_DISPLAY);
            User user = themeDisplay.getUser();

            response.setCharacterEncoding("UTF-8");
            json.put("firstName", user != null ? user.getFirstName() : "");
            json.put("lastName", user != null ? user.getLastName() : "");
            response.getWriter().write(json.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
		
	/*	String id= request.getParameter("id");
	 * List<SomeClass> aClassList =aService.findById(Integer.parseInt(id));

		String test= JSONFactoryUtil.serialize(aClassList);

		response.setContentType("text/json");

		try {
		response.getWriter().write(test);
		} catch (IOException e) {
		
		}*/
    }

    @ResourceMapping(value = "research_group_resource_get_byid")
    public void getById(@RequestParam("copyrightId") Integer copyrightId, ResourceRequest request,
                        ResourceResponse response) throws PortletException, IOException {
        //ObjectMapper mapper = new ObjectMapper();
        String id = request.getParameter("copyrightId");
        CopyrightM researchCopyRight = researchService.findResearchCopyRightById(Integer.valueOf(copyrightId), null);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        ResourceURL csvResource = response.createResourceURL();
        csvResource.setResourceID("research_group_resource_export");
        csvResource.setParameter("copyrightId", copyrightId + "");
        csvResource.setParameter("type", "csv");

        ResourceURL xmlResource = response.createResourceURL();
        xmlResource.setResourceID("research_group_resource_export");
        xmlResource.setParameter("copyrightId", copyrightId + "");
        xmlResource.setParameter("type", "xml");

        researchCopyRight.setCsvResource(csvResource.toString());
        researchCopyRight.setXmlResource(xmlResource.toString());

        try {
            //mapper.writeValue(response.getWriter(), researchCopyRight);
            customObjectMapper.writeValue(response.getWriter(), researchCopyRight);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @ResourceMapping(value = "research_group_resource_export")
    public void loadXls(@RequestParam("copyrightId") Integer copyrightId, @RequestParam("type") String type, ResourceRequest request,
                        ResourceResponse response) throws PortletException, IOException {
        //ObjectMapper mapper = new ObjectMapper();
        String filename = "researchCopyRight";
        response.setCharacterEncoding("UTF-8");
        String contentType = "text/xml";
        CopyrightM researchCopyRight = researchService.findResearchCopyRightById(Integer.valueOf(copyrightId), null);
        String content = "";
        if (type.equals("xml")) {
            filename = filename + ".xml";
            XStream xs = new XStream(new DomDriver());
            content = xs.toXML(researchCopyRight);

        } else {
            filename = filename + ".csv";
            contentType = "text/csv";
            ColumnPositionMappingStrategy strat = new ColumnPositionMappingStrategy();
            strat.setType(CopyrightM.class);
            String[] columns = new String[]{"copyrightId", "groupCode", "groupTh"}; // the fields to bind do in your JavaBean
            strat.setColumnMapping(columns);
            StringWriter sw = new StringWriter();
            try {
                List<CopyrightM> list = new ArrayList<CopyrightM>(1);
                list.add(researchCopyRight);
                BeanToCsv bean = new BeanToCsv();
                bean.write(strat, sw, list); // list from above read
                content = sw.toString();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                sw.close();
            }

        }
        String contentDispositionType = "attachment; filename=\"" + filename + "\"";

        PortletResponseUtil.sendFile(request, response, filename, content.getBytes("UTF-8"), contentType, contentDispositionType);

        //


    }
}
