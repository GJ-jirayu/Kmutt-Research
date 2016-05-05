package th.ac.kmutt.research.portlet;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

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
import th.ac.kmutt.research.form.UtilizationForm;
import th.ac.kmutt.research.mapper.CustomObjectMapper;
import th.ac.kmutt.research.model.UtilizationM;
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

@Controller("utilizationController")
@RequestMapping("VIEW")
@SessionAttributes({"utilizationForm"})
public class UtilizationController {
    private static final Logger logger = Logger
            .getLogger(UtilizationController.class);
    @Autowired
    @Qualifier("researchServiceWSImpl")
    private ResearchService researchService;

    @Autowired
    private CustomObjectMapper customObjectMapper;

    @InitBinder
    public void initBinder(PortletRequestDataBinder binder, PortletPreferences preferences) {
        logger.debug("initBinder");
        binder.registerCustomEditor(byte[].class, new ByteArrayMultipartFileEditor());
        //String a[] = new String[]{"ntcfaq.nfaqName"};
        final String[] ALLOWED_FIELDS = {"utilizationM.utilizationId", "utilizationM.createdBy", "utilizationM.createdDate",
                "utilizationM.groupCode", "utilizationM.permissions", "utilizationM.updatedBy",
                "utilizationM.docType",
                "utilizationM.budgetYear", "utilizationM.outCome", "utilizationM.utilizationType.utilizationTypeId", "utilizationM.researchProject.researchProjectId"
                , "utilizationM.updatedDate", "utilizationM.groupTh", "utilizationM.groupEng", "mode", "command", "keySearch", "pageNo", "paging.pageSize", "ids", "tab", "filter"};


        binder.setAllowedFields(ALLOWED_FIELDS);
    }

    @RequestMapping
    // default (action=list)
    public String listUtilization(Model model) {
        UtilizationForm utilizationForm = null;
        if (!model.containsAttribute("utilizationForm")) {
            utilizationForm = new UtilizationForm();
            model.addAttribute("utilizationForm",
                    utilizationForm);
        } else {
            utilizationForm = (UtilizationForm) model.asMap().get("utilizationForm");
        }
        String keySearch = utilizationForm.getKeySearch();
        UtilizationM utilizationM = new UtilizationM();
        utilizationM.setKeySearch(keySearch);

        utilizationM.setPaging(utilizationForm.getPaging());
        utilizationM.getPaging().setPageNo(utilizationForm.getPageNo());

        ImakeResultMessage imakeResultMessage = researchService
                .searchUtilizationM(utilizationM);
        model.addAttribute("utilizations", imakeResultMessage.getResultListObj());
        //utilizationForm.getPaging().setPageSize(ImakeUtils.PAGE_SIZE);
        utilizationForm.setPageCount(ImakeUtils.calculatePage(utilizationForm.getPaging().getPageSize(), Integer.parseInt(imakeResultMessage.getMaxRow())));
        int[] start_end = ImakeUtils.calculatePageStartEnd(utilizationForm.getPageNo(), utilizationForm.getPageCount());
        utilizationForm.setPageStart(start_end[0]);
        utilizationForm.setPageEnd(start_end[1]);

        return "docs/utilizationList";
    }

    @RequestMapping(params = "action=add")
    // render phase
    public String showUtilizationForm(Model model) {
        // Used for the initial form as well as for redisplaying with errors.
        /*
		 * if (!model.containsAttribute("site")) { model.addAttribute("site",
		 * new PetSite()); }
		 */
        UtilizationForm rtilizationForm = new UtilizationForm();
        rtilizationForm.setMode("add");
        model.addAttribute("utilizationForm",
                rtilizationForm);
        return "docs/utilizationAddEditView";
    }

    @RequestMapping(params = "action=doSubmit") // action phase
    public void populateSite(javax.portlet.ActionRequest request, javax.portlet.ActionResponse response,
                             @ModelAttribute("utilizationForm") UtilizationForm utilizationForm,
                             BindingResult result, Model model) {
        // new PetSiteValidator().validate(petSite, result);
		/*
		 * if (!result.hasErrors()) { this.petSites.put(petSite.getName(),
		 * petSite.getUrl()); status.setComplete();
		 * response.setRenderParameter("action", "list"); }
		 */
        String command = utilizationForm.getCommand();
        String mode = utilizationForm.getMode();
        User user = (User) request.getAttribute(WebKeys.USER);
        utilizationForm.getUtilizationM().setUpdatedBy(user.getUserId() + "");
        command = "addEditView";
        Integer refId = null;
        if (mode != null) {
            if (mode.equals("add") || mode.equals("copy")) {
                utilizationForm.getUtilizationM().setCreatedBy(user.getUserId() + "");
                //utilizationForm.getUtilizationM().setResearchProject(researchProject);esearchProjectId(null);
                refId = researchService.saveUtilizationM(utilizationForm.getUtilizationM());
            } else if (mode.equals("edit")) {
                refId = researchService.updateUtilizationM(utilizationForm.getUtilizationM());
            } else if (mode.equals("deleteItems")) {
                if (utilizationForm.getIds() != null && utilizationForm.getIds().length > 0) {
                    //Integer researchProjectId,
                    researchService.deleteItemsUtilizationM(utilizationForm.getIds());
                }
                command = "list";
            } else {
                command = "list";
            }

        }
        //response.setRenderParameter("nfaqSiteId",faqform.getNfaqSiteId());
        //command = "list";
        //}
        response.setRenderParameter("action", command);
        if (mode.equals("add") || mode.equals("edit") || mode.equals("copy")) {
            response.setRenderParameter("researchProjectId", refId + "");
            response.setRenderParameter("mode", "edit");
        }
    }

    @RequestMapping(params = "action=delete")
    public void removeSite(@RequestParam("researchProjectId") Integer researchProjectId,
                           @RequestParam("utilizationItemList") Integer utilizationItemList,
                           ActionResponse response) {

        UtilizationM utilizationM = new UtilizationM();
        //utilizationM.setResearchProjectId(utilizationId);
        utilizationM.getResearchProject().setResearchProjectId(researchProjectId);
        utilizationM.setUtilizationItemList(utilizationItemList);
        //researchService.deleteUtilizationM(utilizationM);
        researchService.deleteUtilizationItem(utilizationM);
        response.setRenderParameter("action", "list");
    }

    @RequestMapping(params = "action=undoItem")
    public void undoItem(@RequestParam("researchProjectId") Integer researchProjectId,
                         @RequestParam("utilizationItemList") Integer utilizationItemList,
                         ActionResponse response) {
        UtilizationM utilizationM = new UtilizationM();
        utilizationM.getResearchProject().setResearchProjectId(researchProjectId);
        utilizationM.setUtilizationItemList(utilizationItemList);

        utilizationM.setUpdateType(ServiceConstant.UPDATE_TYEP_FLAG);
        utilizationM.setFlag(ServiceConstant.FLAG_ACTIVE);
        researchService.updateFlagUtilization(utilizationM);
        //researchService.deleteResearchProjectM(researchProjectM);
        response.setRenderParameter("action", "list");
    }

    @RequestMapping(params = "action=addEditView")  // render phase
    public String addEditViewUser(@RequestParam(value = "researchProjectId", required = false) Integer researchProjectId, @RequestParam("mode") String mode, Model model) {
        model.addAttribute("mode", mode);
        UtilizationForm utilizationForm = null;
        if (!model.containsAttribute("utilizationForm")) {
            utilizationForm = new UtilizationForm();
            model.addAttribute("utilizationForm",
                    utilizationForm);
        } else {
            utilizationForm = (UtilizationForm) model.asMap().get("utilizationForm");
        }
        UtilizationM utilizationM = null;
        if (mode.equals("add")) {
            utilizationM = new UtilizationM();
        } else if (mode.equals("edit")) {
            utilizationM = researchService.findUtilizationById(researchProjectId, null);
        }
        utilizationForm.setMode(mode);
        utilizationForm.setUtilizationM(utilizationM);
        model.addAttribute("utilizationForm", utilizationForm);
        return "docs/utilizationAddEditView";

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
    public void getById(@RequestParam("utilizationId") Integer utilizationId, ResourceRequest request,
                        ResourceResponse response) throws PortletException, IOException {
        //ObjectMapper mapper = new ObjectMapper();
        String id = request.getParameter("utilizationId");
        UtilizationM utilization = null;//researchService.findUtilizationById(Integer.valueOf(utilizationId));
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        ResourceURL csvResource = response.createResourceURL();
        csvResource.setResourceID("research_group_resource_export");
        csvResource.setParameter("utilizationId", utilizationId + "");
        csvResource.setParameter("type", "csv");

        ResourceURL xmlResource = response.createResourceURL();
        xmlResource.setResourceID("research_group_resource_export");
        xmlResource.setParameter("utilizationId", utilizationId + "");
        xmlResource.setParameter("type", "xml");

        utilization.setCsvResource(csvResource.toString());
        utilization.setXmlResource(xmlResource.toString());

        try {
            //mapper.writeValue(response.getWriter(), utilization);
            customObjectMapper.writeValue(response.getWriter(), utilization);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @ResourceMapping(value = "research_group_resource_export")
    public void loadXls(@RequestParam("utilizationId") Integer utilizationId, @RequestParam("type") String type, ResourceRequest request,
                        ResourceResponse response) throws PortletException, IOException {
        //ObjectMapper mapper = new ObjectMapper();
        String filename = "utilization";
        response.setCharacterEncoding("UTF-8");
        String contentType = "text/xml";
        UtilizationM utilization = null;//researchService.findUtilizationById(Integer.valueOf(utilizationId));
        String content = "";
        if (type.equals("xml")) {
            filename = filename + ".xml";
            XStream xs = new XStream(new DomDriver());
            content = xs.toXML(utilization);

        } else {
            filename = filename + ".csv";
            contentType = "text/csv";
            ColumnPositionMappingStrategy strat = new ColumnPositionMappingStrategy();
            strat.setType(UtilizationM.class);
            String[] columns = new String[]{"utilizationId", "groupCode", "groupTh"}; // the fields to bind do in your JavaBean
            strat.setColumnMapping(columns);
            StringWriter sw = new StringWriter();
            try {
                List<UtilizationM> list = new ArrayList<UtilizationM>(1);
                list.add(utilization);
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
		 
		 
		 /*CSVReader reader = new CSVReader(
				    new InputStreamReader(
				        new ByteArrayInputStream( xml.getBytes("UTF-8"))));
		 
		 CsvToBean csv = new CsvToBean();
		 List list = csv.parse(strat, reader); // list of Order bean
      */


        //sendFile(response, filename, in, "application/vnd.ms-excel");
		 
		  
						
					/*	IOUtils.copy(in, out);
						out.flush();*/


    }
}