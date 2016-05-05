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

import th.ac.kmutt.research.constant.ServiceConstant;
import th.ac.kmutt.research.form.PublishedLevelMasterForm;
import th.ac.kmutt.research.mapper.CustomObjectMapper;
import th.ac.kmutt.research.model.PublishLevelM;
import th.ac.kmutt.research.service.ResearchService;
import th.ac.kmutt.research.utils.ImakeUtils;
import th.ac.kmutt.research.xstream.common.ImakeResultMessage;

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

@Controller("publishedLevelMasterController")
@RequestMapping("VIEW")
@SessionAttributes({"publishedLevelMasterForm"})
public class PublishedLevelMasterController {
    private static final Logger logger = Logger
            .getLogger(PublishedLevelMasterController.class);
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
        final String[] ALLOWED_FIELDS = {"publishLevelM.publishLevelId", "publishLevelM.createdBy", "publishLevelM.createdDate",
                "publishLevelM.plCode", "publishLevelM.plName", "publishLevelM.updatedBy",
                "publishLevelM.updatedDate", "mode", "command", "keySearch", "pageNo", "paging.pageSize", "ids", "tab", "filter"};

        binder.setAllowedFields(ALLOWED_FIELDS);
    }

    @RequestMapping
    // default (action=list)
    public String listPublishedLevel(Model model) {
        PublishedLevelMasterForm publishedLevelMasterForm = null;
        if (!model.containsAttribute("publishedLevelMasterForm")) {
            publishedLevelMasterForm = new PublishedLevelMasterForm();
            model.addAttribute("publishedLevelMasterForm",
                    publishedLevelMasterForm);
        } else {
            publishedLevelMasterForm = (PublishedLevelMasterForm) model.asMap().get("publishedLevelMasterForm");
        }
        String keySearch = publishedLevelMasterForm.getKeySearch();
        PublishLevelM publishedLevelM = new PublishLevelM();
        publishedLevelM.setKeySearch(keySearch);
        publishedLevelM.setPaging(publishedLevelMasterForm.getPaging());
        publishedLevelM.getPaging().setPageNo(publishedLevelMasterForm.getPageNo());

        ImakeResultMessage imakeResultMessage = researchService
                .searchPublishLevelM(publishedLevelM);
        model.addAttribute("publishedLevels", imakeResultMessage.getResultListObj());
        //publishedLevelMasterForm.getPaging().setPageSize(ImakeUtils.PAGE_SIZE);
        publishedLevelMasterForm.setPageCount(ImakeUtils.calculatePage(publishedLevelMasterForm.getPaging().getPageSize(), Integer.parseInt(imakeResultMessage.getMaxRow())));
        int[] start_end = ImakeUtils.calculatePageStartEnd(publishedLevelMasterForm.getPageNo(), publishedLevelMasterForm.getPageCount());
        publishedLevelMasterForm.setPageStart(start_end[0]);
        publishedLevelMasterForm.setPageEnd(start_end[1]);
        return "master/publishedLevel";
    }

    @RequestMapping(params = "action=add")
    // render phase
    public String showPublishedLevelMasterForm(Model model) {
        // Used for the initial form as well as for redisplaying with errors.
        /*
		 * if (!model.containsAttribute("site")) { model.addAttribute("site",
		 * new PetSite()); }
		 */
        model.addAttribute("publishedLevelMasterForm",
                new PublishedLevelMasterForm());
        return "master/publishedLevel";
    }

    @RequestMapping(params = "action=doSubmit") // action phase
    public void populateSite(javax.portlet.ActionRequest request, javax.portlet.ActionResponse response,
                             @ModelAttribute("publishedLevelMasterForm") PublishedLevelMasterForm publishedLevelMasterForm,
                             BindingResult result, Model model) {
        // new PetSiteValidator().validate(petSite, result);
		/*
		 * if (!result.hasErrors()) { this.petSites.put(petSite.getName(),
		 * petSite.getUrl()); status.setComplete();
		 * response.setRenderParameter("action", "list"); }
		 */
        String command = publishedLevelMasterForm.getCommand();
        String mode = publishedLevelMasterForm.getMode();
        User user = (User) request.getAttribute(WebKeys.USER);
        publishedLevelMasterForm.getPublishLevelM().setUpdatedBy(user.getUserId() + "");
        //if(command.equals("doSaveFAQ")){
        if (mode != null) {
            if (mode.equals("add")) {
                publishedLevelMasterForm.getPublishLevelM().setCreatedBy(user.getUserId() + "");
                publishedLevelMasterForm.getPublishLevelM().setPublishLevelId(null);
                researchService.savePublishLevelM(publishedLevelMasterForm.getPublishLevelM());
            } else if (mode.equals("edit")) {
                researchService.updatePublishLevelM(publishedLevelMasterForm.getPublishLevelM());
            } else if (mode.equals("deleteItems")) {
                if (publishedLevelMasterForm.getIds() != null && publishedLevelMasterForm.getIds().length > 0) {
                    researchService.deleteItemsPublishLevelM(publishedLevelMasterForm.getIds());
                }
            } else {
            }
        }
        //response.setRenderParameter("nfaqSiteId",faqform.getNfaqSiteId());
        command = "list";
        //}
        response.setRenderParameter("action", command);
    }

    @RequestMapping(params = "action=delete")
    public void removeSite(@RequestParam("publishedLevelId") Integer publishedLevelId,
                           ActionResponse response, Model model) {
        PublishLevelM publishedLevelM = new PublishLevelM();
        publishedLevelM.setPublishLevelId(publishedLevelId);
        int recordCount = researchService.deletePublishLevelM(publishedLevelM);
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
    public void getById(@RequestParam("publishedLevelId") Integer publishedLevelId, ResourceRequest request,
                        ResourceResponse response) throws PortletException, IOException {
        //ObjectMapper mapper = new ObjectMapper();
        String id = request.getParameter("publishedLevelId");
        PublishLevelM publishedLevel = researchService.findPublishLevelById(Integer.valueOf(publishedLevelId));
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        ResourceURL csvResource = response.createResourceURL();
        csvResource.setResourceID("research_group_resource_export");
        csvResource.setParameter("publishedLevelId", publishedLevelId + "");
        csvResource.setParameter("type", "csv");

        ResourceURL csvUploadResource = response.createResourceURL();
        csvUploadResource.setResourceID("research_group_resource_upload");
        csvUploadResource.setParameter("publishedLevelId", publishedLevelId + "");
        csvUploadResource.setParameter("type", "csv");

        ResourceURL xmlResource = response.createResourceURL();
        xmlResource.setResourceID("research_group_resource_export");
        xmlResource.setParameter("publishedLevelId", publishedLevelId + "");
        xmlResource.setParameter("type", "xml");

        ResourceURL xmlUploadResource = response.createResourceURL();
        xmlUploadResource.setResourceID("research_group_resource_upload");
        xmlUploadResource.setParameter("publishedLevelId", publishedLevelId + "");
        xmlUploadResource.setParameter("type", "xml");

        publishedLevel.setCsvUploadResource(csvUploadResource.toString());
        publishedLevel.setXmlUploadResource(xmlUploadResource.toString());
        publishedLevel.setCsvResource(csvResource.toString());
        publishedLevel.setXmlResource(xmlResource.toString());
        try {
            //mapper.writeValue(response.getWriter(), publishedLevel);
            customObjectMapper.writeValue(response.getWriter(), publishedLevel);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @ResourceMapping(value = "research_group_resource_export")
    public void loadXls(@RequestParam("publishedLevelId") Integer publishedLevelId, @RequestParam("type") String type, ResourceRequest request,
                        ResourceResponse response) throws PortletException, IOException {
        //ObjectMapper mapper = new ObjectMapper();
        String filename = "publishLevel";
        response.setCharacterEncoding("UTF-8");
        String contentType = "text/xml";
        PublishLevelM publishLevel = researchService.findPublishLevelById(Integer.valueOf(publishedLevelId));
        String content = "";
        if (type.equals("xml")) {
            filename = filename + ".xml";
            XStream xs = new XStream(new DomDriver());
            xs.processAnnotations(PublishLevelM.class);
            content = xs.toXML(publishLevel);

        } else {
            filename = filename + ".csv";
            contentType = "text/csv";
            ColumnPositionMappingStrategy strat = new ColumnPositionMappingStrategy();
            strat.setType(PublishLevelM.class);
            String[] columns = new String[]{"plName"}; // the fields to bind do in your JavaBean
            strat.setColumnMapping(columns);
            StringWriter sw = new StringWriter();
            try {
                List<PublishLevelM> list = new ArrayList<PublishLevelM>(1);
                list.add(publishLevel);
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
    public void resourceUpload(@RequestParam("publishedLevelId") Integer publishedLevelId, @RequestParam("type") String type, ResourceRequest request,
                               ResourceResponse response) throws PortletException, IOException {
        //ObjectMapper mapper = new ObjectMapper();
        String status = "success";
        UploadPortletRequest uploadRequest = PortalUtil
                .getUploadPortletRequest(request);
        String sourceFileName = uploadRequest.getFileName("import_" + type);
        File file = uploadRequest.getFile("import_" + type);
        PublishLevelM publishLevel = researchService.findPublishLevelById(Integer.valueOf(publishedLevelId));
        User user = (User) request.getAttribute(WebKeys.USER);

        PublishLevelM model = new PublishLevelM();
        BeanUtils.copyProperties(publishLevel, model);
        model.setUpdatedBy(user.getUserId() + "");
        if (type.equals("csv")) {
            HeaderColumnNameTranslateMappingStrategy<PublishLevelM> beanStrategy = new HeaderColumnNameTranslateMappingStrategy<PublishLevelM>();
            beanStrategy.setType(PublishLevelM.class);

            Map<String, String> columnMapping = new HashMap<String, String>();
            columnMapping.put("plName", "plName");
				      /*  columnMapping.put("groupCode", "groupCode");
				        columnMapping.put("groupTh", "groupTh");
				        columnMapping.put("groupEng", "groupEng");*/
            //columnMapping.put("Salary", "salary");

            beanStrategy.setColumnMapping(columnMapping);

            CsvToBean<PublishLevelM> csvToBean = new CsvToBean<PublishLevelM>();
            CSVReader reader = null;
            try {
                reader = new CSVReader(new FileReader(file));
                List<PublishLevelM> models = csvToBean.parse(beanStrategy, reader);
                if (models != null && models.size() > 0) {
                    PublishLevelM model_return = models.get(0);
                    setPropertiesBean(model_return, model);

                }
            } catch (Exception ex) {
                ex.printStackTrace();
            } finally {
                reader.close();
            }

        } else if (type.equals("xml")) {
            XStream xs = new XStream(new DomDriver());
            xs.processAnnotations(PublishLevelM.class);
            Object xtarget = xs.fromXML(file);

            if (xtarget != null) {
                PublishLevelM model_return = (PublishLevelM) xtarget;
                setPropertiesBean(model_return, model);
            }

        }
        if (model != null)
            researchService.updatePublishLevelM(model);
        //model.setGroupTh(sourceFileName);
        try {
            customObjectMapper.writeValue(response.getWriter(), status);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private PublishLevelM setPropertiesBean(PublishLevelM source, PublishLevelM target) {
        target.setPlName(source.getPlName());
		/*	target.setGroupTh(source.getGroupTh());
		target.setGroupEng(source.getGroupEng());*/
        return target;
    }
}
