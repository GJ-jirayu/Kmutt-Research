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
import th.ac.kmutt.research.form.OrganizationExtMasterForm;
import th.ac.kmutt.research.mapper.CustomObjectMapper;
import th.ac.kmutt.research.model.OrganizationExtM;
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

@Controller("organizationExtMasterController")
@RequestMapping("VIEW")
@SessionAttributes({"organizationExtMasterForm"})
public class OrganizationExtMasterController {
    private static final Logger logger = Logger
            .getLogger(OrganizationExtMasterController.class);
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
        final String[] ALLOWED_FIELDS = {"organizationExtM.organizationExtId", "organizationExtM.createdBy", "organizationExtM.createdDate",
                "organizationExtM.organizationExtCode", "organizationExtM.organizationExtName", "organizationExtM.updatedBy",
                "organizationExtM.updatedDate", "mode", "command", "keySearch", "pageNo", "paging.pageSize", "ids", "tab", "filter"};

        binder.setAllowedFields(ALLOWED_FIELDS);
    }

    @RequestMapping
    // default (action=list)
    public String listOrganizationExt(Model model) {
        OrganizationExtMasterForm organizationExtMasterForm = null;
        if (!model.containsAttribute("organizationExtMasterForm")) {
            organizationExtMasterForm = new OrganizationExtMasterForm();
            model.addAttribute("organizationExtMasterForm",
                    organizationExtMasterForm);
        } else {
            organizationExtMasterForm = (OrganizationExtMasterForm) model.asMap().get("organizationExtMasterForm");
        }
        String keySearch = organizationExtMasterForm.getKeySearch();
        OrganizationExtM organizationExtM = new OrganizationExtM();
        organizationExtM.setKeySearch(keySearch);
        organizationExtM.setPaging(organizationExtMasterForm.getPaging());
        organizationExtM.getPaging().setPageNo(organizationExtMasterForm.getPageNo());

        ImakeResultMessage imakeResultMessage = researchService
                .searchOrganizationExtM(organizationExtM);
        model.addAttribute("organizationExts", imakeResultMessage.getResultListObj());
        //organizationExtMasterForm.getPaging().setPageSize(ImakeUtils.PAGE_SIZE);
        organizationExtMasterForm.setPageCount(ImakeUtils.calculatePage(organizationExtMasterForm.getPaging().getPageSize(), Integer.parseInt(imakeResultMessage.getMaxRow())));
        int[] start_end = ImakeUtils.calculatePageStartEnd(organizationExtMasterForm.getPageNo(), organizationExtMasterForm.getPageCount());
        organizationExtMasterForm.setPageStart(start_end[0]);
        organizationExtMasterForm.setPageEnd(start_end[1]);
        return "master/organizationExt";
    }

    @RequestMapping(params = "action=add")
    // render phase
    public String showOrganizationExtMasterForm(Model model) {
        // Used for the initial form as well as for redisplaying with errors.
        /*
		 * if (!model.containsAttribute("site")) { model.addAttribute("site",
		 * new PetSite()); }
		 */
        model.addAttribute("organizationExtMasterForm",
                new OrganizationExtMasterForm());
        return "master/organizationExt";
    }

    @RequestMapping(params = "action=doSubmit") // action phase
    public void populateSite(javax.portlet.ActionRequest request, javax.portlet.ActionResponse response,
                             @ModelAttribute("organizationExtMasterForm") OrganizationExtMasterForm organizationExtMasterForm,
                             BindingResult result, Model model) {
        // new PetSiteValidator().validate(petSite, result);
		/*
		 * if (!result.hasErrors()) { this.petSites.put(petSite.getName(),
		 * petSite.getUrl()); status.setComplete();
		 * response.setRenderParameter("action", "list"); }
		 */
        String command = organizationExtMasterForm.getCommand();
        String mode = organizationExtMasterForm.getMode();
        User user = (User) request.getAttribute(WebKeys.USER);
        organizationExtMasterForm.getOrganizationExtM().setUpdatedBy(user.getUserId() + "");
        //if(command.equals("doSaveFAQ")){
        if (mode != null) {
            if (mode.equals("add")) {
                organizationExtMasterForm.getOrganizationExtM().setCreatedBy(user.getUserId() + "");
                organizationExtMasterForm.getOrganizationExtM().setOrganizationExtId(null);
                researchService.saveOrganizationExtM(organizationExtMasterForm.getOrganizationExtM());
            } else if (mode.equals("edit")) {
                researchService.updateOrganizationExtM(organizationExtMasterForm.getOrganizationExtM());
            } else if (mode.equals("deleteItems")) {
                if (organizationExtMasterForm.getIds() != null && organizationExtMasterForm.getIds().length > 0) {
                    researchService.deleteItemsOrganizationExtM(organizationExtMasterForm.getIds());
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
    public void removeSite(@RequestParam("organizationExtId") Integer organizationExtId,
                           ActionResponse response, Model model) {
        OrganizationExtM organizationExtM = new OrganizationExtM();
        organizationExtM.setOrganizationExtId(organizationExtId);
        int recordCount = researchService.deleteOrganizationExtM(organizationExtM);
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
    public void getById(@RequestParam("organizationExtId") Integer organizationExtId, ResourceRequest request,
                        ResourceResponse response) throws PortletException, IOException {
        //ObjectMapper mapper = new ObjectMapper();
        String id = request.getParameter("organizationExtId");
        OrganizationExtM organizationExt = researchService.findOrganizationExtById(Integer.valueOf(organizationExtId));
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        ResourceURL csvResource = response.createResourceURL();
        csvResource.setResourceID("research_group_resource_export");
        csvResource.setParameter("organizationExtId", organizationExtId + "");
        csvResource.setParameter("type", "csv");

        ResourceURL csvUploadResource = response.createResourceURL();
        csvUploadResource.setResourceID("research_group_resource_upload");
        csvUploadResource.setParameter("organizationExtId", organizationExtId + "");
        csvUploadResource.setParameter("type", "csv");

        ResourceURL xmlResource = response.createResourceURL();
        xmlResource.setResourceID("research_group_resource_export");
        xmlResource.setParameter("organizationExtId", organizationExtId + "");
        xmlResource.setParameter("type", "xml");

        ResourceURL xmlUploadResource = response.createResourceURL();
        xmlUploadResource.setResourceID("research_group_resource_upload");
        xmlUploadResource.setParameter("organizationExtId", organizationExtId + "");
        xmlUploadResource.setParameter("type", "xml");

        organizationExt.setCsvUploadResource(csvUploadResource.toString());
        organizationExt.setXmlUploadResource(xmlUploadResource.toString());
        organizationExt.setCsvResource(csvResource.toString());
        organizationExt.setXmlResource(xmlResource.toString());
        try {
            //mapper.writeValue(response.getWriter(), organizationExt);
            customObjectMapper.writeValue(response.getWriter(), organizationExt);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @ResourceMapping(value = "research_group_resource_export")
    public void loadXls(@RequestParam("organizationExtId") Integer organizationExtId, @RequestParam("type") String type, ResourceRequest request,
                        ResourceResponse response) throws PortletException, IOException {
        //ObjectMapper mapper = new ObjectMapper();
        String filename = "organizationExt";
        response.setCharacterEncoding("UTF-8");
        String contentType = "text/xml";
        OrganizationExtM organizationExt = researchService.findOrganizationExtById(Integer.valueOf(organizationExtId));
        String content = "";
        if (type.equals("xml")) {
            filename = filename + ".xml";
            XStream xs = new XStream(new DomDriver());
            xs.processAnnotations(OrganizationExtM.class);
            content = xs.toXML(organizationExt);

        } else {
            filename = filename + ".csv";
            contentType = "text/csv";
            ColumnPositionMappingStrategy strat = new ColumnPositionMappingStrategy();
            strat.setType(OrganizationExtM.class);
            String[] columns = new String[]{"organizationExtName"}; // the fields to bind do in your JavaBean
            strat.setColumnMapping(columns);
            StringWriter sw = new StringWriter();
            try {
                List<OrganizationExtM> list = new ArrayList<OrganizationExtM>(1);
                list.add(organizationExt);
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
    public void resourceUpload(@RequestParam("organizationExtId") Integer organizationExtId, @RequestParam("type") String type, ResourceRequest request,
                               ResourceResponse response) throws PortletException, IOException {
        //ObjectMapper mapper = new ObjectMapper();
        String status = "success";
        UploadPortletRequest uploadRequest = PortalUtil
                .getUploadPortletRequest(request);
        String sourceFileName = uploadRequest.getFileName("import_" + type);
        File file = uploadRequest.getFile("import_" + type);
        OrganizationExtM organizationExt = researchService.findOrganizationExtById(Integer.valueOf(organizationExtId));
        User user = (User) request.getAttribute(WebKeys.USER);

        OrganizationExtM model = new OrganizationExtM();
        BeanUtils.copyProperties(organizationExt, model);
        model.setUpdatedBy(user.getUserId() + "");
        if (type.equals("csv")) {
            HeaderColumnNameTranslateMappingStrategy<OrganizationExtM> beanStrategy = new HeaderColumnNameTranslateMappingStrategy<OrganizationExtM>();
            beanStrategy.setType(OrganizationExtM.class);

            Map<String, String> columnMapping = new HashMap<String, String>();
            columnMapping.put("organizationExtName", "organizationExtName");

            //columnMapping.put("Salary", "salary");

            beanStrategy.setColumnMapping(columnMapping);

            CsvToBean<OrganizationExtM> csvToBean = new CsvToBean<OrganizationExtM>();
            CSVReader reader = null;
            try {
                reader = new CSVReader(new FileReader(file));
                List<OrganizationExtM> models = csvToBean.parse(beanStrategy, reader);
                if (models != null && models.size() > 0) {
                    OrganizationExtM model_return = models.get(0);
                    setPropertiesBean(model_return, model);

                }
            } catch (Exception ex) {
                ex.printStackTrace();
            } finally {
                reader.close();
            }

        } else if (type.equals("xml")) {
            XStream xs = new XStream(new DomDriver());
            xs.processAnnotations(OrganizationExtM.class);
            Object xtarget = xs.fromXML(file);

            if (xtarget != null) {
                OrganizationExtM model_return = (OrganizationExtM) xtarget;
                setPropertiesBean(model_return, model);
            }

        }
        if (model != null)
            researchService.updateOrganizationExtM(model);
        // model.setGroupTh(sourceFileName);
        try {
            customObjectMapper.writeValue(response.getWriter(), status);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private OrganizationExtM setPropertiesBean(OrganizationExtM source, OrganizationExtM target) {
        target.setOrganizationExtName(source.getOrganizationExtName());
	/*	target.setGroupTh(source.getGroupTh());
		target.setGroupEng(source.getGroupEng());*/
        return target;
    }
}
