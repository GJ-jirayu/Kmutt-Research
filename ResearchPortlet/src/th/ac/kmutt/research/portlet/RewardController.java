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
import th.ac.kmutt.research.form.RewardForm;
import th.ac.kmutt.research.mapper.CustomObjectMapper;
import th.ac.kmutt.research.model.CountryM;
import th.ac.kmutt.research.model.RewardM;
import th.ac.kmutt.research.service.ResearchService;
import th.ac.kmutt.research.utils.ImakeUtils;
import th.ac.kmutt.research.xstream.common.ImakeResultMessage;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.portlet.PortletResponseUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.User;
import com.liferay.portal.service.RoleLocalServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.opencsv.bean.BeanToCsv;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

@Controller("rewardController")
@RequestMapping("VIEW")
@SessionAttributes({"rewardForm"})
public class RewardController {
    private static final Logger logger = Logger
            .getLogger(RewardController.class);
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
        //String a[] = new String[]{"ntcfaq.nfaqName"};
        final String[] ALLOWED_FIELDS = {"rewardM.rewardId", "rewardM.createdBy", "rewardM.createdDate",
                "rewardM.budgetYear", "rewardM.docType", "rewardM.updatedBy",
                "rewardM.updatedDate", "rewardM.remark", "rewardM.researchProject.researchProjectId",
                "rewardM.rewardCode", "rewardM.rewardCountry", "rewardM.rewardFrom",
                "rewardM.rewardName", "rewardDate", "rewardM.rewardLocation",
                "mode", "command", "keySearch"
                , "docsAssign", "pageNo", "paging.pageSize", "ids", "tab", "filter"};


        binder.setAllowedFields(ALLOWED_FIELDS);
    }

    @RequestMapping
    // default (action=list)
    public String listReward(javax.portlet.RenderRequest request, Model model) {

        User user = (User) request.getAttribute(WebKeys.USER);
        long roleId = 0l;
        try {
            roleId = RoleLocalServiceUtil.getRole(user.getCompanyId(), "Research Admin").getRoleId();
        } catch (PortalException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SystemException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {
            boolean hasRole = UserLocalServiceUtil.hasRoleUser(roleId, user.getUserId());
        } catch (SystemException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        RewardForm rewardForm = null;
        if (!model.containsAttribute("rewardForm")) {
            rewardForm = new RewardForm();
            model.addAttribute("rewardForm",
                    rewardForm);
        } else {
            rewardForm = (RewardForm) model.asMap().get("rewardForm");
        }
        String keySearch = rewardForm.getKeySearch();
        RewardM rewardM = new RewardM();
        rewardM.setKeySearch(keySearch);

        rewardM.setPaging(rewardForm.getPaging());
        rewardM.getPaging().setPageNo(rewardForm.getPageNo());
        rewardM.setUserid(user.getUserId() + "");
        if (rewardForm.getTab() == null)
            rewardForm.setTab(ServiceConstant.TAB_ALL);
        if (rewardForm.getFilter() == null)
            rewardForm.setFilter(ServiceConstant.FILTERS[0]);
        rewardM.setTab(rewardForm.getTab());
        rewardM.setFilter(rewardForm.getFilter());

        ImakeResultMessage imakeResultMessage = researchService
                .searchRewardM(rewardM);
        model.addAttribute("rewards", imakeResultMessage.getResultListObj());
        //rewardForm.getPaging().setPageSize(ImakeUtils.PAGE_SIZE);
        rewardForm.setPageCount(ImakeUtils.calculatePage(rewardForm.getPaging().getPageSize(), Integer.parseInt(imakeResultMessage.getMaxRow())));
        int[] start_end = ImakeUtils.calculatePageStartEnd(rewardForm.getPageNo(), rewardForm.getPageCount());
        rewardForm.setPageStart(start_end[0]);
        rewardForm.setPageEnd(start_end[1]);
        return "docs/researchRewardList";
    }

    @RequestMapping(params = "action=add")
    // render phase
    public String showRewardForm(Model model) {
        // Used for the initial form as well as for redisplaying with errors.
        /*
		 * if (!model.containsAttribute("site")) { model.addAttribute("site",
		 * new PetSite()); }
		 */
        RewardForm rewardForm = new RewardForm();
        rewardForm.setMode("add");
        model.addAttribute("rewardForm",
                rewardForm);
        return "docs/researchRewardAddEditView";
    }

    @RequestMapping(params = "action=doSubmit") // action phase
    public void populateSite(javax.portlet.ActionRequest request, javax.portlet.ActionResponse response,
                             @ModelAttribute("rewardForm") RewardForm rewardForm,
                             BindingResult result, Model model) {
        // new PetSiteValidator().validate(petSite, result);
		/*
		 * if (!result.hasErrors()) { this.petSites.put(petSite.getName(),
		 * petSite.getUrl()); status.setComplete();
		 * response.setRenderParameter("action", "list"); }
		 */
        String command = rewardForm.getCommand();
        String mode = rewardForm.getMode();
        User user = (User) request.getAttribute(WebKeys.USER);
        rewardForm.getRewardM().setUpdatedBy(user.getUserId() + "");
        //String docsAssign=rewardForm.getDocsAssign();
        String rewardDate = rewardForm.getRewardDate();
        if (rewardDate != null && rewardDate.trim().length() > 0)
            try {
                rewardForm.getRewardM().setRewardDate(format1.parse(rewardDate));
            } catch (ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        command = "addEditView";
        Integer refId = null;
        if (mode != null) {
            if (mode.equals("add") || mode.equals("copy")) {
                rewardForm.getRewardM().setCreatedBy(user.getUserId() + "");
                rewardForm.getRewardM().setRewardId(null);
                refId = researchService.saveRewardM(rewardForm.getRewardM());
            } else if (mode.equals("edit")) {
                refId = researchService.updateRewardM(rewardForm.getRewardM());
            } else if (mode.equals("deleteItems")) {
                if (rewardForm.getIds() != null && rewardForm.getIds().length > 0) {
                    researchService.deleteItemsRewardM(rewardForm.getIds());
                }
                command = "list";
            } else {
                command = "list";
            }
				/*if(refId!=null)
					if(docsAssign!=null && docsAssign.trim().length()>0){
						DocAssignMappingM mapping =new DocAssignMappingM();
						mapping.setRefId(refId);
						mapping.setRefType("REWARD");
						mapping.setUserId(docsAssign);
						researchService.saveDocAssignMapping(mapping);
					}*/
        }

        response.setRenderParameter("action", command);
        if (mode.equals("add") || mode.equals("edit") || mode.equals("copy")) {
            response.setRenderParameter("rewardId", refId + "");
            response.setRenderParameter("mode", "edit");
        }
    }

    @RequestMapping(params = "action=delete")
    public void removeSite(@RequestParam("rewardId") Integer rewardId,
                           ActionResponse response) {
        RewardM rewardM = new RewardM();
        rewardM.setRewardId(rewardId);
		/*rewardM.setFlag("0");
		rewardM.setUpdateType("flag");*/

        //researchService.updateFlagReward(rewardM);
        researchService.deleteRewardM(rewardM);
        response.setRenderParameter("action", "list");
    }

    @RequestMapping(params = "action=undoItem")
    public void undoItem(@RequestParam("rewardId") Integer rewardId,
                         ActionResponse response) {
        RewardM rewardM = new RewardM();
        rewardM.setRewardId(rewardId);
        rewardM.setUpdateType(ServiceConstant.UPDATE_TYEP_FLAG);
        rewardM.setFlag(ServiceConstant.FLAG_ACTIVE);
        researchService.updateFlagReward(rewardM);
        //researchService.deleteResearchProjectM(researchProjectM);
        response.setRenderParameter("action", "list");
    }

    @RequestMapping(params = "action=addEditView")  // render phase
    public String addEditViewUser(javax.portlet.RenderRequest request, @RequestParam(value = "rewardId", required = false) Integer rewardId, @RequestParam("mode") String mode, Model model) {
        model.addAttribute("mode", mode);
        RewardForm rewardForm = null;
        if (!model.containsAttribute("rewardForm")) {
            rewardForm = new RewardForm();
            model.addAttribute("rewardForm",
                    rewardForm);
        } else {
            rewardForm = (RewardForm) model.asMap().get("rewardForm");
        }

        User user = (User) request.getAttribute(WebKeys.USER);
        String userid = null;
        if (user != null)
            userid = user.getUserId() + "";

        RewardM rewardM = null;
        if (mode.equals("add")) {
            rewardForm = new RewardForm();
            rewardM = new RewardM();
        } else if (mode.equals("edit")) {
            rewardM = researchService.findRewardById(rewardId, userid);


            if (rewardM.getRewardCountry() != null) {
                CountryM countryM = researchService.findCountryById(rewardM.getRewardCountry());
                if (countryM != null) {
                    rewardM.setRewardCountryShow(countryM.getCountryNameTh());
                }
            }

            Date rewardDate = rewardM.getRewardDate();
            if (rewardDate != null)
                rewardForm.setRewardDate(format1.format(rewardDate));

        } else if (mode.equals("copy")) {
            rewardM = researchService.findRewardById(rewardId, userid);


            if (rewardM.getRewardCountry() != null) {
                CountryM countryM = researchService.findCountryById(rewardM.getRewardCountry());
                if (countryM != null) {
                    rewardM.setRewardCountryShow(countryM.getCountryNameTh());
                }
            }

            Date rewardDate = rewardM.getRewardDate();
            if (rewardDate != null)
                rewardForm.setRewardDate(format1.format(rewardDate));

            // reset for copy
            rewardM.setRewardCreators(null);
            rewardM.setDocAssignMappings(null);

        }

        rewardForm.setMode(mode);
        rewardForm.setRewardM(rewardM);
        model.addAttribute("rewardForm", rewardForm);
        return "docs/researchRewardAddEditView";

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
    public void getById(@RequestParam("rewardId") Integer rewardId, ResourceRequest request,
                        ResourceResponse response) throws PortletException, IOException {
        //ObjectMapper mapper = new ObjectMapper();
        String id = request.getParameter("rewardId");
        RewardM researchGroup = researchService.findRewardById(Integer.valueOf(rewardId), null);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        ResourceURL csvResource = response.createResourceURL();
        csvResource.setResourceID("research_group_resource_export");
        csvResource.setParameter("rewardId", rewardId + "");
        csvResource.setParameter("type", "csv");

        ResourceURL xmlResource = response.createResourceURL();
        xmlResource.setResourceID("research_group_resource_export");
        xmlResource.setParameter("rewardId", rewardId + "");
        xmlResource.setParameter("type", "xml");

        researchGroup.setCsvResource(csvResource.toString());
        researchGroup.setXmlResource(xmlResource.toString());

        try {
            //mapper.writeValue(response.getWriter(), researchGroup);
            customObjectMapper.writeValue(response.getWriter(), researchGroup);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @ResourceMapping(value = "research_group_resource_export")
    public void loadXls(@RequestParam("rewardId") Integer rewardId, @RequestParam("type") String type, ResourceRequest request,
                        ResourceResponse response) throws PortletException, IOException {
        //ObjectMapper mapper = new ObjectMapper();
        String filename = "researchGroup";
        response.setCharacterEncoding("UTF-8");
        String contentType = "text/xml";
        RewardM researchGroup = researchService.findRewardById(Integer.valueOf(rewardId), null);
        String content = "";
        if (type.equals("xml")) {
            filename = filename + ".xml";
            XStream xs = new XStream(new DomDriver());
            content = xs.toXML(researchGroup);

        } else {
            filename = filename + ".csv";
            contentType = "text/csv";
            ColumnPositionMappingStrategy strat = new ColumnPositionMappingStrategy();
            strat.setType(RewardM.class);
            String[] columns = new String[]{"rewardId", "groupCode", "groupTh"}; // the fields to bind do in your JavaBean
            strat.setColumnMapping(columns);
            StringWriter sw = new StringWriter();
            try {
                List<RewardM> list = new ArrayList<RewardM>(1);
                list.add(researchGroup);
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