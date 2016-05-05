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
import th.ac.kmutt.research.form.PublishedTypeMasterForm;
import th.ac.kmutt.research.mapper.CustomObjectMapper;
import th.ac.kmutt.research.model.PublishTypeM;
//import th.ac.kmutt.research.model.ResearchGroupM;
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

@Controller("publishedTypeMasterController")
@RequestMapping("VIEW")
@SessionAttributes({"publishedTypeMasterForm"})
public class PublishedTypeMasterController {
    private static final Logger logger = Logger
            .getLogger(PublishedTypeMasterController.class);
    @Autowired
    @Qualifier("researchServiceWSImpl")
    private ResearchService researchService;

    @Autowired
    private CustomObjectMapper customObjectMapper;

    @InitBinder
    public void initBinder(PortletRequestDataBinder binder, PortletPreferences preferences) {
        logger.debug("initBinder");
        binder.registerCustomEditor(byte[].class, new ByteArrayMultipartFileEditor());
        final String[] ALLOWED_FIELDS = {"publishTypeM.publishTypeId", "publishTypeM.createdBy", "publishTypeM.createdDate",
                "publishTypeM.ptCode", "publishTypeM.ptName", "publishTypeM.updatedBy",
                "publishTypeM.updatedDate", "mode", "command", "keySearch", "pageNo", "paging.pageSize", "ids", "tab", "filter"};

        binder.setAllowedFields(ALLOWED_FIELDS);
    }

    @RequestMapping
    // default (action=list)
    public String listPublishedType(Model model) {
        PublishedTypeMasterForm publishedTypeMasterForm = null;
        if (!model.containsAttribute("publishedTypeMasterForm")) {
            publishedTypeMasterForm = new PublishedTypeMasterForm();
            model.addAttribute("publishedTypeMasterForm",
                    publishedTypeMasterForm);
        } else {
            publishedTypeMasterForm = (PublishedTypeMasterForm) model.asMap().get("publishedTypeMasterForm");
        }
        String keySearch = publishedTypeMasterForm.getKeySearch();
        PublishTypeM publishedTypeM = new PublishTypeM();
        publishedTypeM.setKeySearch(keySearch);
        publishedTypeM.setPaging(publishedTypeMasterForm.getPaging());
        publishedTypeM.getPaging().setPageNo(publishedTypeMasterForm.getPageNo());

        ImakeResultMessage imakeResultMessage = researchService
                .searchPublishTypeM(publishedTypeM);
        model.addAttribute("publishedTypes", imakeResultMessage.getResultListObj());
        //publishedTypeMasterForm.getPaging().setPageSize(ImakeUtils.PAGE_SIZE);
        publishedTypeMasterForm.setPageCount(ImakeUtils.calculatePage(publishedTypeMasterForm.getPaging().getPageSize(), Integer.parseInt(imakeResultMessage.getMaxRow())));
        int[] start_end = ImakeUtils.calculatePageStartEnd(publishedTypeMasterForm.getPageNo(), publishedTypeMasterForm.getPageCount());
        publishedTypeMasterForm.setPageStart(start_end[0]);
        publishedTypeMasterForm.setPageEnd(start_end[1]);

        return "master/publishedType";
    }

    @RequestMapping(params = "action=doSubmit") // action phase
    public void populatepPublishedType(javax.portlet.ActionRequest request, javax.portlet.ActionResponse response,
                                       @ModelAttribute("publishedTypeMasterForm") PublishedTypeMasterForm publishedTypeMasterForm,
                                       BindingResult result, Model model) {
        String command = publishedTypeMasterForm.getCommand();
        String mode = publishedTypeMasterForm.getMode();
        User user = (User) request.getAttribute(WebKeys.USER);
        publishedTypeMasterForm.getPublishTypeM().setUpdatedBy(user.getUserId() + "");
        if (mode != null) {
            if (mode.equals("add")) {
                publishedTypeMasterForm.getPublishTypeM().setCreatedBy(user.getUserId() + "");
                publishedTypeMasterForm.getPublishTypeM().setPublishTypeId(null);
                researchService.savePublishTypeM(publishedTypeMasterForm.getPublishTypeM());
            } else if (mode.equals("edit")) {
                researchService.updatePublishTypeM(publishedTypeMasterForm.getPublishTypeM());
            } else if (mode.equals("deleteItems")) {
                if (publishedTypeMasterForm.getIds() != null && publishedTypeMasterForm.getIds().length > 0) {
                    researchService.deleteItemsPublishTypeM(publishedTypeMasterForm.getIds());
                }
            } else {
            }
        }
        command = "list";
        response.setRenderParameter("action", command);
    }

    @RequestMapping(params = "action=delete")
    public void removePublishedType(@RequestParam("publishedTypeId") Integer publishedTypeId,
                                    ActionResponse response, Model model) {
        PublishTypeM publishedTypeM = new PublishTypeM();
        publishedTypeM.setPublishTypeId(publishedTypeId);
        int recordCount = researchService.deletePublishTypeM(publishedTypeM);
        if (recordCount == -9)
            model.addAttribute(ServiceConstant.ERROR_MESSAGE_KEY, ServiceConstant.ERROR_CONSTRAINT_VIOLATION_MESSAGE_CODE);
        response.setRenderParameter("action", "list");
    }

    @ResourceMapping(value = "research_group_resource_get_byid")
    public void getById(@RequestParam("publishedTypeId") Integer publishedTypeId, ResourceRequest request,
                        ResourceResponse response) throws PortletException, IOException {
        //ObjectMapper mapper = new ObjectMapper();
        String id = request.getParameter("publishedTypeId");
        PublishTypeM publishedType = researchService.findPublishTypeById(Integer.valueOf(publishedTypeId));
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        ResourceURL csvResource = response.createResourceURL();
        csvResource.setResourceID("research_group_resource_export");
        csvResource.setParameter("publishedTypeId", publishedTypeId + "");
        csvResource.setParameter("type", "csv");

        ResourceURL csvUploadResource = response.createResourceURL();
        csvUploadResource.setResourceID("research_group_resource_upload");
        csvUploadResource.setParameter("publishedTypeId", publishedTypeId + "");
        csvUploadResource.setParameter("type", "csv");

        ResourceURL xmlResource = response.createResourceURL();
        xmlResource.setResourceID("research_group_resource_export");
        xmlResource.setParameter("publishedTypeId", publishedTypeId + "");
        xmlResource.setParameter("type", "xml");

        ResourceURL xmlUploadResource = response.createResourceURL();
        xmlUploadResource.setResourceID("research_group_resource_upload");
        xmlUploadResource.setParameter("publishedTypeId", publishedTypeId + "");
        xmlUploadResource.setParameter("type", "xml");

        publishedType.setCsvUploadResource(csvUploadResource.toString());
        publishedType.setXmlUploadResource(xmlUploadResource.toString());
        publishedType.setCsvResource(csvResource.toString());
        publishedType.setXmlResource(xmlResource.toString());
        try {
            customObjectMapper.writeValue(response.getWriter(), publishedType);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @ResourceMapping(value = "research_group_resource_export")
    public void loadXls(@RequestParam("publishedTypeId") Integer publishedTypeId, @RequestParam("type") String type, ResourceRequest request,
                        ResourceResponse response) throws PortletException, IOException {
        //ObjectMapper mapper = new ObjectMapper();
        String filename = "publishedType";
        response.setCharacterEncoding("UTF-8");
        String contentType = "text/xml";
        PublishTypeM publishedType = researchService.findPublishTypeById(Integer.valueOf(publishedTypeId));
        String content = "";
        if (type.equals("xml")) {
            filename = filename + ".xml";
            XStream xs = new XStream(new DomDriver());
            xs.processAnnotations(PublishTypeM.class);
            content = xs.toXML(publishedType);

        } else {
            filename = filename + ".csv";
            contentType = "text/csv";
            ColumnPositionMappingStrategy strat = new ColumnPositionMappingStrategy();
            strat.setType(PublishTypeM.class);
            String[] columns = new String[]{"ptName"}; // the fields to bind do in your JavaBean
            strat.setColumnMapping(columns);
            StringWriter sw = new StringWriter();
            try {
                List<PublishTypeM> list = new ArrayList<PublishTypeM>(1);
                list.add(publishedType);
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
    }

    @ResourceMapping(value = "research_group_resource_upload")
    public void resourceUpload(@RequestParam("publishedTypeId") Integer publishedTypeId, @RequestParam("type") String type, ResourceRequest request,
                               ResourceResponse response) throws PortletException, IOException {
        //ObjectMapper mapper = new ObjectMapper();
        String status = "success";
        UploadPortletRequest uploadRequest = PortalUtil
                .getUploadPortletRequest(request);
        String sourceFileName = uploadRequest.getFileName("import_" + type);
        File file = uploadRequest.getFile("import_" + type);
        PublishTypeM publishedType = researchService.findPublishTypeById(Integer.valueOf(publishedTypeId));
        User user = (User) request.getAttribute(WebKeys.USER);

        PublishTypeM model = new PublishTypeM();
        BeanUtils.copyProperties(publishedType, model);
        model.setUpdatedBy(user.getUserId() + "");
        if (type.equals("csv")) {
            HeaderColumnNameTranslateMappingStrategy<PublishTypeM> beanStrategy = new HeaderColumnNameTranslateMappingStrategy<PublishTypeM>();
            beanStrategy.setType(PublishTypeM.class);

            Map<String, String> columnMapping = new HashMap<String, String>();
            columnMapping.put("ptName", "ptName");
                       /* columnMapping.put("groupCode", "groupCode");
				        columnMapping.put("groupTh", "groupTh");
				        columnMapping.put("groupEng", "groupEng");*/
            //columnMapping.put("Salary", "salary");

            beanStrategy.setColumnMapping(columnMapping);

            CsvToBean<PublishTypeM> csvToBean = new CsvToBean<PublishTypeM>();
            CSVReader reader = null;
            try {
                reader = new CSVReader(new FileReader(file));
                List<PublishTypeM> models = csvToBean.parse(beanStrategy, reader);
                if (models != null && models.size() > 0) {
                    PublishTypeM model_return = models.get(0);
                    setPropertiesBean(model_return, model);

                }
            } catch (Exception ex) {
                ex.printStackTrace();
            } finally {
                reader.close();
            }

        } else if (type.equals("xml")) {
            XStream xs = new XStream(new DomDriver());
            xs.processAnnotations(PublishTypeM.class);
            Object xtarget = xs.fromXML(file);

            if (xtarget != null) {
                PublishTypeM model_return = (PublishTypeM) xtarget;
                setPropertiesBean(model_return, model);
            }

        }
        if (model != null)
            researchService.updatePublishTypeM(model);
        //model.setGroupTh(sourceFileName);
        try {
            customObjectMapper.writeValue(response.getWriter(), status);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private PublishTypeM setPropertiesBean(PublishTypeM source, PublishTypeM target) {
        target.setPtName(source.getPtName());
		/*target.setGroupTh(source.getGroupTh());
		target.setGroupEng(source.getGroupEng());*/
        return target;
    }
}
