package th.ac.kmutt.research.portlet;


import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.PortletPreferences;
import javax.portlet.PortletRequest;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.portlet.ResourceURL;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
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

import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.portlet.PortletResponseUtil;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.User;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.opencsv.CSVReader;
import com.opencsv.bean.BeanToCsv;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.HeaderColumnNameTranslateMappingStrategy;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import th.ac.kmutt.research.constant.ServiceConstant;
import th.ac.kmutt.research.form.ResearchGroupMasterForm;
import th.ac.kmutt.research.mapper.CustomObjectMapper;
import th.ac.kmutt.research.model.ResearchGroupM;
import th.ac.kmutt.research.service.ResearchService;
import th.ac.kmutt.research.utils.ImakeUtils;
import th.ac.kmutt.research.xstream.common.ImakeResultMessage;

@Controller("researchGroupMasterController")
@RequestMapping("VIEW")
@SessionAttributes({"researchGroupMasterForm"})
public class ResearchGroupMasterController {
    private static final Logger logger = Logger
            .getLogger(ResearchGroupMasterController.class);
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
        final String[] ALLOWED_FIELDS = {"researchGroupM.researchGroupId", "researchGroupM.createdBy", "researchGroupM.createdDate",
                "researchGroupM.groupCode", "researchGroupM.permissions", "researchGroupM.updatedBy",
                "researchGroupM.updatedDate", "researchGroupM.groupTh", "researchGroupM.groupEng", "mode", "command", "keySearch", "pageNo", "paging.pageSize", "ids", "tab", "filter"};

        binder.setAllowedFields(ALLOWED_FIELDS);
    }

    @RequestMapping
    // default (action=list)
    public String listResearchGroup(PortletRequest request, Model model) {
        ResearchGroupMasterForm researchGroupMasterForm = null;
        if (!model.containsAttribute("researchGroupMasterForm")) {
            researchGroupMasterForm = new ResearchGroupMasterForm();
            model.addAttribute("researchGroupMasterForm",
                    researchGroupMasterForm);
        } else {
            researchGroupMasterForm = (ResearchGroupMasterForm) model.asMap().get("researchGroupMasterForm");
        }
        String keySearch = researchGroupMasterForm.getKeySearch();
        ResearchGroupM researchGroupM = new ResearchGroupM();
        researchGroupM.setKeySearch(keySearch);
        researchGroupM.setPaging(researchGroupMasterForm.getPaging());
        researchGroupM.getPaging().setPageNo(researchGroupMasterForm.getPageNo());
        /*List<ResearchGroupM> researchGroups = researchService
				.searchResearchGroupM(researchGroupM);*/

        ImakeResultMessage imakeResultMessage = researchService
                .searchResearchGroupM(researchGroupM);
        model.addAttribute("researchGroups", imakeResultMessage.getResultListObj());
        //researchGroupMasterForm.getPaging().setPageSize(ImakeUtils.PAGE_SIZE);
        researchGroupMasterForm.setPageCount(ImakeUtils.calculatePage(researchGroupMasterForm.getPaging().getPageSize(), Integer.parseInt(imakeResultMessage.getMaxRow())));
        int[] start_end = ImakeUtils.calculatePageStartEnd(researchGroupMasterForm.getPageNo(), researchGroupMasterForm.getPageCount());
        researchGroupMasterForm.setPageStart(start_end[0]);
        researchGroupMasterForm.setPageEnd(start_end[1]);


        User user = (User) request.getAttribute(WebKeys.USER);

        ThemeDisplay td = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
        User user2 = td.getUser();
       // RoleServiceUtil.getUserRoles(user.getUserId());
        //	RoleServiceUtil.h
		/*try {
			RoleServiceUtil.getUserRoles(user.getUserId());
		} catch (PortalException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SystemException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}*/
        return "master/researchGroup";
    }

    @RequestMapping(params = "action=add")
    // render phase
    public String showResearchGroupMasterForm(Model model) {
        // Used for the initial form as well as for redisplaying with errors.
		/*
		 * if (!model.containsAttribute("site")) { model.addAttribute("site",
		 * new PetSite()); }
		 */
        model.addAttribute("researchGroupMasterForm",
                new ResearchGroupMasterForm());
        return "master/researchGroup";
    }

    @RequestMapping(params = "action=doSubmit") // action phase
    public void populateSite(javax.portlet.ActionRequest request, javax.portlet.ActionResponse response,
                             @ModelAttribute("researchGroupMasterForm") ResearchGroupMasterForm researchGroupMasterForm,
                             BindingResult result, Model model) {
        // new PetSiteValidator().validate(petSite, result);
		/*
		 * if (!result.hasErrors()) { this.petSites.put(petSite.getName(),
		 * petSite.getUrl()); status.setComplete();
		 * response.setRenderParameter("action", "list"); }
		 */
        User user = (User) request.getAttribute(WebKeys.USER);
        String command = researchGroupMasterForm.getCommand();
        String mode = researchGroupMasterForm.getMode();
        researchGroupMasterForm.getResearchGroupM().setUpdatedBy(user.getUserId() + "");
        //if(command.equals("doSaveFAQ")){
        if (mode != null) {
            if (mode.equals("add")) {
                researchGroupMasterForm.getResearchGroupM().setCreatedBy(user.getUserId() + "");
                researchGroupMasterForm.getResearchGroupM().setResearchGroupId(null);
                Integer refId = researchService.saveResearchGroupM(researchGroupMasterForm.getResearchGroupM());
                if (refId == -1) {

                }
            } else if (mode.equals("edit")) {
                researchService.updateResearchGroupM(researchGroupMasterForm.getResearchGroupM());
            } else if (mode.equals("deleteItems")) {
                if (researchGroupMasterForm.getIds() != null && researchGroupMasterForm.getIds().length > 0) {
                    researchService.deleteItemsResearchGroupM(researchGroupMasterForm.getIds());
                }
            }
        }
        //response.setRenderParameter("nfaqSiteId",faqform.getNfaqSiteId());
        command = "list";
        //}
        response.setRenderParameter("action", command);
    }

    @RequestMapping(params = "action=delete")
    public void removeSite(@RequestParam("researchGroupId") Integer researchGroupId,
                           ActionResponse response, Model model) {
        ResearchGroupM researchGroupM = new ResearchGroupM();
        researchGroupM.setResearchGroupId(researchGroupId);
        int recordCount = researchService.deleteResearchGroupM(researchGroupM);
        if (recordCount == -9)
            model.addAttribute(ServiceConstant.ERROR_MESSAGE_KEY, ServiceConstant.ERROR_CONSTRAINT_VIOLATION_MESSAGE_CODE);
        response.setRenderParameter("action", "list");
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
    public void getById(@RequestParam("researchGroupId") Integer researchGroupId, ResourceRequest request,
                        ResourceResponse response) throws PortletException, IOException {
        //ObjectMapper mapper = new ObjectMapper();
        String id = request.getParameter("researchGroupId");
        ResearchGroupM researchGroup = researchService.findResearchGroupById(Integer.valueOf(researchGroupId));
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        ResourceURL csvResource = response.createResourceURL();
        csvResource.setResourceID("research_group_resource_export");
        csvResource.setParameter("researchGroupId", researchGroupId + "");
        csvResource.setParameter("type", "csv");

        ResourceURL csvUploadResource = response.createResourceURL();
        csvUploadResource.setResourceID("research_group_resource_upload");
        csvUploadResource.setParameter("researchGroupId", researchGroupId + "");
        csvUploadResource.setParameter("type", "csv");

        ResourceURL xmlResource = response.createResourceURL();
        xmlResource.setResourceID("research_group_resource_export");
        xmlResource.setParameter("researchGroupId", researchGroupId + "");
        xmlResource.setParameter("type", "xml");

        ResourceURL xmlUploadResource = response.createResourceURL();
        xmlUploadResource.setResourceID("research_group_resource_upload");
        xmlUploadResource.setParameter("researchGroupId", researchGroupId + "");
        xmlUploadResource.setParameter("type", "xml");

        researchGroup.setCsvUploadResource(csvUploadResource.toString());
        researchGroup.setXmlUploadResource(xmlUploadResource.toString());
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
    public void loadXls(@RequestParam("researchGroupId") Integer researchGroupId, @RequestParam("type") String type, ResourceRequest request,
                        ResourceResponse response) throws PortletException, IOException {
        //ObjectMapper mapper = new ObjectMapper();
        String filename = "researchGroup";
        response.setCharacterEncoding("UTF-8");
        String contentType = "text/xml";
        ResearchGroupM researchGroup = researchService.findResearchGroupById(Integer.valueOf(researchGroupId));
        String content = "";
        if (type.equals("xml")) {
            filename = filename + ".xml";
            XStream xs = new XStream(new DomDriver());
            xs.processAnnotations(ResearchGroupM.class);
            content = xs.toXML(researchGroup);

        } else {
            filename = filename + ".csv";
            contentType = "text/csv";
            ColumnPositionMappingStrategy strat = new ColumnPositionMappingStrategy();
            strat.setType(ResearchGroupM.class);
            String[] columns = new String[]{"researchGroupId", "groupCode", "groupTh", "groupEng"}; // the fields to bind do in your JavaBean
            strat.setColumnMapping(columns);
            StringWriter sw = new StringWriter();
            try {
                List<ResearchGroupM> list = new ArrayList<ResearchGroupM>(1);
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

    @ResourceMapping(value = "research_group_resource_upload")
    public void resourceUpload(@RequestParam("researchGroupId") Integer researchGroupId, @RequestParam("type") String type, ResourceRequest request,
                               ResourceResponse response) throws PortletException, IOException {
        //ObjectMapper mapper = new ObjectMapper();
        String status = "success";
        UploadPortletRequest uploadRequest = PortalUtil
                .getUploadPortletRequest(request);
        String sourceFileName = uploadRequest.getFileName("import_" + type);
        File file = uploadRequest.getFile("import_" + type);
        ResearchGroupM researchGroup = researchService.findResearchGroupById(Integer.valueOf(researchGroupId));
        User user = (User) request.getAttribute(WebKeys.USER);

        ResearchGroupM model = new ResearchGroupM();
        BeanUtils.copyProperties(researchGroup, model);
        model.setUpdatedBy(user.getUserId() + "");
        if (type.equals("csv")) {
            HeaderColumnNameTranslateMappingStrategy<ResearchGroupM> beanStrategy = new HeaderColumnNameTranslateMappingStrategy<ResearchGroupM>();
            beanStrategy.setType(ResearchGroupM.class);

            Map<String, String> columnMapping = new HashMap<String, String>();
            columnMapping.put("researchGroupId", "researchGroupId");
            columnMapping.put("groupCode", "groupCode");
            columnMapping.put("groupTh", "groupTh");
            columnMapping.put("groupEng", "groupEng");
            //columnMapping.put("Salary", "salary");

            beanStrategy.setColumnMapping(columnMapping);

            CsvToBean<ResearchGroupM> csvToBean = new CsvToBean<ResearchGroupM>();
            CSVReader reader = null;
            try {
                reader = new CSVReader(new FileReader(file));
                List<ResearchGroupM> models = csvToBean.parse(beanStrategy, reader);
                if (models != null && models.size() > 0) {
                    ResearchGroupM model_return = models.get(0);
                    setPropertiesBean(model_return, model);

                }
            } catch (Exception ex) {
                ex.printStackTrace();
            } finally {
                reader.close();
            }

        } else if (type.equals("xml")) {
            XStream xs = new XStream(new DomDriver());
            xs.processAnnotations(ResearchGroupM.class);
            Object xtarget = xs.fromXML(file);

            if (xtarget != null) {
                ResearchGroupM model_return = (ResearchGroupM) xtarget;
                setPropertiesBean(model_return, model);
            }

        }
        if (model != null)
            researchService.updateResearchGroupM(model);
        model.setGroupTh(sourceFileName);
        try {
            customObjectMapper.writeValue(response.getWriter(), status);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private ResearchGroupM setPropertiesBean(ResearchGroupM source, ResearchGroupM target) {
        target.setGroupCode(source.getGroupCode());
        target.setGroupTh(source.getGroupTh());
        target.setGroupEng(source.getGroupEng());
        return target;
    }
}
