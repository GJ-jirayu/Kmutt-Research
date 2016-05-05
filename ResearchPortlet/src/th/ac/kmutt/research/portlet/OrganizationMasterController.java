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
import th.ac.kmutt.research.form.OrganizationMasterForm;
import th.ac.kmutt.research.mapper.CustomObjectMapper;
import th.ac.kmutt.research.model.OrganizationM;
import th.ac.kmutt.research.service.ResearchService;
import th.ac.kmutt.research.utils.ImakeUtils;
import th.ac.kmutt.research.xstream.common.ImakeResultMessage;

import com.liferay.portal.kernel.portlet.PortletResponseUtil;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.User;
import com.liferay.portal.util.PortalUtil;
import com.opencsv.CSVReader;
import com.opencsv.bean.BeanToCsv;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.HeaderColumnNameTranslateMappingStrategy;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

@Controller("organizationMasterController")
@RequestMapping("VIEW")
@SessionAttributes({"organizationMasterForm"})
public class OrganizationMasterController {
    private static final Logger logger = Logger
            .getLogger(OrganizationMasterController.class);
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
        final String[] ALLOWED_FIELDS = {"organizationM.organizationId", "organizationM.createdBy", "organizationM.createdDate",
                "organizationM.orgCampusCode", "organizationM.orgInstitutionCode", "organizationM.orgDeptCode", "organizationM.orgWorkCode",
                "organizationM.orgName", "organizationM.updatedBy",
                "organizationM.updatedDate", "mode", "command", "keySearch", "pageNo", "paging.pageSize", "ids", "tab", "filter"};

        binder.setAllowedFields(ALLOWED_FIELDS);
    }

    @RequestMapping
    // default (action=list)
    public String listOrganization(Model model) {
        OrganizationMasterForm organizationMasterForm = null;
        if (!model.containsAttribute("organizationMasterForm")) {
            organizationMasterForm = new OrganizationMasterForm();
            model.addAttribute("organizationMasterForm",
                    organizationMasterForm);
        } else {
            organizationMasterForm = (OrganizationMasterForm) model.asMap().get("organizationMasterForm");
        }
        String keySearch = organizationMasterForm.getKeySearch();
        OrganizationM organizationM = new OrganizationM();
        organizationM.setKeySearch(keySearch);
        organizationM.setPaging(organizationMasterForm.getPaging());
        organizationM.getPaging().setPageNo(organizationMasterForm.getPageNo());

        ImakeResultMessage imakeResultMessage = researchService
                .searchOrganizationM(organizationM);
        model.addAttribute("organizations", imakeResultMessage.getResultListObj());
        //organizationMasterForm.getPaging().setPageSize(ImakeUtils.PAGE_SIZE);
        organizationMasterForm.setPageCount(ImakeUtils.calculatePage(organizationMasterForm.getPaging().getPageSize(), Integer.parseInt(imakeResultMessage.getMaxRow())));
        int[] start_end = ImakeUtils.calculatePageStartEnd(organizationMasterForm.getPageNo(), organizationMasterForm.getPageCount());
        organizationMasterForm.setPageStart(start_end[0]);
        organizationMasterForm.setPageEnd(start_end[1]);
        return "master/organization";
    }

    @RequestMapping(params = "action=doSubmit") // action phase
    public void populateSite(javax.portlet.ActionRequest request, javax.portlet.ActionResponse response,
                             @ModelAttribute("organizationMasterForm") OrganizationMasterForm organizationMasterForm,
                             BindingResult result, Model model) {
        String command = organizationMasterForm.getCommand();
        String mode = organizationMasterForm.getMode();
        User user = (User) request.getAttribute(WebKeys.USER);
        organizationMasterForm.getOrganizationM().setUpdatedBy(user.getUserId() + "");
        if (mode != null) {
            if (mode.equals("add")) {
                organizationMasterForm.getOrganizationM().setCreatedBy(user.getUserId() + "");
                organizationMasterForm.getOrganizationM().setOrganizationId(null);
                researchService.saveOrganizationM(organizationMasterForm.getOrganizationM());
            } else if (mode.equals("edit")) {
                researchService.updateOrganizationM(organizationMasterForm.getOrganizationM());
            } else if (mode.equals("deleteItems")) {
                if (organizationMasterForm.getIds() != null && organizationMasterForm.getIds().length > 0) {
                    researchService.deleteItemsOrganizationM(organizationMasterForm.getIds());
                }
            } else {
            }
        }
        command = "list";
        //}
        response.setRenderParameter("action", command);
    }

    @RequestMapping(params = "action=delete")
    public void removeSite(@RequestParam("organizationId") Integer organizationId,
                           ActionResponse response, Model model) {
        OrganizationM organizationM = new OrganizationM();
        organizationM.setOrganizationId(organizationId);
        int recordCount = researchService.deleteOrganizationM(organizationM);
        if (recordCount == -9)
            model.addAttribute(ServiceConstant.ERROR_MESSAGE_KEY, ServiceConstant.ERROR_CONSTRAINT_VIOLATION_MESSAGE_CODE);
        response.setRenderParameter("action", "list");
    }

    @ResourceMapping(value = "research_group_resource_get_byid")
    public void getById(@RequestParam("organizationId") Integer organizationId, ResourceRequest request,
                        ResourceResponse response) throws PortletException, IOException {
        String id = request.getParameter("organizationId");
        OrganizationM organization = researchService.findOrganizationById(Integer.valueOf(organizationId));
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        ResourceURL csvResource = response.createResourceURL();
        csvResource.setResourceID("research_group_resource_export");
        csvResource.setParameter("organizationId", organizationId + "");
        csvResource.setParameter("type", "csv");

        ResourceURL csvUploadResource = response.createResourceURL();
        csvUploadResource.setResourceID("research_group_resource_upload");
        csvUploadResource.setParameter("organizationId", organizationId + "");
        csvUploadResource.setParameter("type", "csv");

        ResourceURL xmlResource = response.createResourceURL();
        xmlResource.setResourceID("research_group_resource_export");
        xmlResource.setParameter("organizationId", organizationId + "");
        xmlResource.setParameter("type", "xml");

        ResourceURL xmlUploadResource = response.createResourceURL();
        xmlUploadResource.setResourceID("research_group_resource_upload");
        xmlUploadResource.setParameter("organizationId", organizationId + "");
        xmlUploadResource.setParameter("type", "xml");

        organization.setCsvUploadResource(csvUploadResource.toString());
        organization.setXmlUploadResource(xmlUploadResource.toString());
        organization.setCsvResource(csvResource.toString());
        organization.setXmlResource(xmlResource.toString());
        try {
            customObjectMapper.writeValue(response.getWriter(), organization);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @ResourceMapping(value = "research_group_resource_export")
    public void loadXls(@RequestParam("organizationId") Integer organizationId, @RequestParam("type") String type, ResourceRequest request,
                        ResourceResponse response) throws PortletException, IOException {
        //ObjectMapper mapper = new ObjectMapper();
        String filename = "organization";
        response.setCharacterEncoding("UTF-8");
        String contentType = "text/xml";
        OrganizationM organization = researchService.findOrganizationById(Integer.valueOf(organizationId));
        String content = "";
        if (type.equals("xml")) {
            filename = filename + ".xml";
            XStream xs = new XStream(new DomDriver());
            xs.processAnnotations(OrganizationM.class);
            content = xs.toXML(organization);

        } else {
            filename = filename + ".csv";
            contentType = "text/csv";
            ColumnPositionMappingStrategy strat = new ColumnPositionMappingStrategy();
            strat.setType(OrganizationM.class);

            String[] columns = new String[]{"orgCampusCode", "orgInstitutionCode", "orgDeptCode", "orgWorkCode", "orgName"}; // the fields to bind do in your JavaBean
            strat.setColumnMapping(columns);
            StringWriter sw = new StringWriter();
            try {
                List<OrganizationM> list = new ArrayList<OrganizationM>(1);
                list.add(organization);
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
    public void resourceUpload(@RequestParam("organizationId") Integer organizationId, @RequestParam("type") String type, ResourceRequest request,
                               ResourceResponse response) throws PortletException, IOException {
        //ObjectMapper mapper = new ObjectMapper();
        String status = "success";
        UploadPortletRequest uploadRequest = PortalUtil
                .getUploadPortletRequest(request);
        String sourceFileName = uploadRequest.getFileName("import_" + type);
        File file = uploadRequest.getFile("import_" + type);
        OrganizationM organization = researchService.findOrganizationById(Integer.valueOf(organizationId));
        User user = (User) request.getAttribute(WebKeys.USER);

        OrganizationM model = new OrganizationM();
        BeanUtils.copyProperties(organization, model);
        model.setUpdatedBy(user.getUserId() + "");
        if (type.equals("csv")) {
            HeaderColumnNameTranslateMappingStrategy<OrganizationM> beanStrategy = new HeaderColumnNameTranslateMappingStrategy<OrganizationM>();
            beanStrategy.setType(OrganizationM.class);

            Map<String, String> columnMapping = new HashMap<String, String>();
            columnMapping.put("orgCampusCode", "orgCampusCode");
            columnMapping.put("orgInstitutionCode", "orgInstitutionCode");
            columnMapping.put("orgDeptCode", "orgDeptCode");
            columnMapping.put("orgWorkCode", "orgWorkCode");
            columnMapping.put("orgName", "orgName");
            //columnMapping.put("Salary", "salary");

            beanStrategy.setColumnMapping(columnMapping);

            CsvToBean<OrganizationM> csvToBean = new CsvToBean<OrganizationM>();
            CSVReader reader = null;
            try {
                reader = new CSVReader(new FileReader(file));
                List<OrganizationM> models = csvToBean.parse(beanStrategy, reader);
                if (models != null && models.size() > 0) {
                    OrganizationM model_return = models.get(0);
                    setPropertiesBean(model_return, model);

                }
            } catch (Exception ex) {
                ex.printStackTrace();
            } finally {
                reader.close();
            }

        } else if (type.equals("xml")) {
            XStream xs = new XStream(new DomDriver());
            xs.processAnnotations(OrganizationM.class);
            Object xtarget = xs.fromXML(file);

            if (xtarget != null) {
                OrganizationM model_return = (OrganizationM) xtarget;
                setPropertiesBean(model_return, model);
            }

        }
        if (model != null)
            researchService.updateOrganizationM(model);
        //model.setGroupTh(sourceFileName);
        try {
            customObjectMapper.writeValue(response.getWriter(), status);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private OrganizationM setPropertiesBean(OrganizationM source, OrganizationM target) {

        target.setOrgCampusCode(source.getOrgCampusCode());
        target.setOrgInstitutionCode(source.getOrgInstitutionCode());
        target.setOrgDeptCode(source.getOrgDeptCode());
        target.setOrgName(source.getOrgName());
        return target;
    }
}
