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
import th.ac.kmutt.research.form.UtilizationTypeMasterForm;
import th.ac.kmutt.research.mapper.CustomObjectMapper;
//import th.ac.kmutt.research.model.ResearchGroupM;
import th.ac.kmutt.research.model.UtilizationTypeM;
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

@Controller("utilizationMasterController")
@RequestMapping("VIEW")
@SessionAttributes({"utilizationTypeMasterForm"})
public class UtilizationTypeMasterController {
    private static final Logger logger = Logger
            .getLogger(UtilizationTypeMasterController.class);
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
        final String[] ALLOWED_FIELDS = {"utilizationTypeM.utilizationTypeId", "utilizationTypeM.createdBy", "utilizationTypeM.createdDate",
                "utilizationTypeM.utilizationCode", "utilizationTypeM.utilizationName", "utilizationTypeM.updatedBy",
                "utilizationTypeM.updatedDate", "mode", "command", "keySearch", "pageNo", "paging.pageSize", "ids", "tab", "filter"};

        binder.setAllowedFields(ALLOWED_FIELDS);
    }

    @RequestMapping
    // default (action=list)
    public String listUtilizationType(Model model) {
        UtilizationTypeMasterForm utilizationTypeMasterForm = null;
        if (!model.containsAttribute("utilizationTypeMasterForm")) {
            utilizationTypeMasterForm = new UtilizationTypeMasterForm();
            model.addAttribute("utilizationTypeMasterForm",
                    utilizationTypeMasterForm);
        } else {
            utilizationTypeMasterForm = (UtilizationTypeMasterForm) model.asMap().get("utilizationTypeMasterForm");
        }
        String keySearch = utilizationTypeMasterForm.getKeySearch();
        UtilizationTypeM utilizationTypeM = new UtilizationTypeM();
        utilizationTypeM.setKeySearch(keySearch);
        utilizationTypeM.setPaging(utilizationTypeMasterForm.getPaging());
        utilizationTypeM.getPaging().setPageNo(utilizationTypeMasterForm.getPageNo());

        ImakeResultMessage imakeResultMessage = researchService
                .searchUtilizationTypeM(utilizationTypeM);
        model.addAttribute("utilizationTypes", imakeResultMessage.getResultListObj());
        //utilizationTypeMasterForm.getPaging().setPageSize(ImakeUtils.PAGE_SIZE);
        utilizationTypeMasterForm.setPageCount(ImakeUtils.calculatePage(utilizationTypeMasterForm.getPaging().getPageSize(), Integer.parseInt(imakeResultMessage.getMaxRow())));
        int[] start_end = ImakeUtils.calculatePageStartEnd(utilizationTypeMasterForm.getPageNo(), utilizationTypeMasterForm.getPageCount());
        utilizationTypeMasterForm.setPageStart(start_end[0]);
        utilizationTypeMasterForm.setPageEnd(start_end[1]);
        return "master/utilizationType";
    }

    @RequestMapping(params = "action=doSubmit") // action phase
    public void populateSite(javax.portlet.ActionRequest request, javax.portlet.ActionResponse response,
                             @ModelAttribute("utilizationTypeMasterForm") UtilizationTypeMasterForm utilizationTypeMasterForm,
                             BindingResult result, Model model) {
        String command = utilizationTypeMasterForm.getCommand();
        String mode = utilizationTypeMasterForm.getMode();
        User user = (User) request.getAttribute(WebKeys.USER);
        utilizationTypeMasterForm.getUtilizationTypeM().setUpdatedBy(user.getUserId() + "");
        if (mode != null) {
            if (mode.equals("add")) {
                utilizationTypeMasterForm.getUtilizationTypeM().setCreatedBy(user.getUserId() + "");
                utilizationTypeMasterForm.getUtilizationTypeM().setUtilizationTypeId(null);
                researchService.saveUtilizationTypeM(utilizationTypeMasterForm.getUtilizationTypeM());
            } else if (mode.equals("edit")) {
                researchService.updateUtilizationTypeM(utilizationTypeMasterForm.getUtilizationTypeM());
            } else if (mode.equals("deleteItems")) {
                if (utilizationTypeMasterForm.getIds() != null && utilizationTypeMasterForm.getIds().length > 0) {
                    researchService.deleteItemsUtilizationTypeM(utilizationTypeMasterForm.getIds());
                }
            } else {
            }
        }
        command = "list";
        //}
        response.setRenderParameter("action", command);
    }

    @RequestMapping(params = "action=delete")
    public void removeSite(@RequestParam("utilizationTypeId") Integer utilizationTypeId,
                           ActionResponse response, Model model) {
        UtilizationTypeM utilizationTypeM = new UtilizationTypeM();
        utilizationTypeM.setUtilizationTypeId(utilizationTypeId);
        int recordCount = researchService.deleteUtilizationTypeM(utilizationTypeM);
        if (recordCount == -9)
            model.addAttribute(ServiceConstant.ERROR_MESSAGE_KEY, ServiceConstant.ERROR_CONSTRAINT_VIOLATION_MESSAGE_CODE);
        response.setRenderParameter("action", "list");
    }

    @ResourceMapping(value = "research_group_resource_get_byid")
    public void getById(@RequestParam("utilizationTypeId") Integer utilizationTypeId, ResourceRequest request,
                        ResourceResponse response) throws PortletException, IOException {
        String id = request.getParameter("utilizationTypeId");
        UtilizationTypeM utilizationType = researchService.findUtilizationTypeById(Integer.valueOf(utilizationTypeId));
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        ResourceURL csvResource = response.createResourceURL();
        csvResource.setResourceID("research_group_resource_export");
        csvResource.setParameter("utilizationTypeId", utilizationTypeId + "");
        csvResource.setParameter("type", "csv");

        ResourceURL csvUploadResource = response.createResourceURL();
        csvUploadResource.setResourceID("research_group_resource_upload");
        csvUploadResource.setParameter("utilizationTypeId", utilizationTypeId + "");
        csvUploadResource.setParameter("type", "csv");

        ResourceURL xmlResource = response.createResourceURL();
        xmlResource.setResourceID("research_group_resource_export");
        xmlResource.setParameter("utilizationTypeId", utilizationTypeId + "");
        xmlResource.setParameter("type", "xml");

        ResourceURL xmlUploadResource = response.createResourceURL();
        xmlUploadResource.setResourceID("research_group_resource_upload");
        xmlUploadResource.setParameter("utilizationTypeId", utilizationTypeId + "");
        xmlUploadResource.setParameter("type", "xml");

        utilizationType.setCsvUploadResource(csvUploadResource.toString());
        utilizationType.setXmlUploadResource(xmlUploadResource.toString());
        utilizationType.setCsvResource(csvResource.toString());
        utilizationType.setXmlResource(xmlResource.toString());
        try {
            customObjectMapper.writeValue(response.getWriter(), utilizationType);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @ResourceMapping(value = "research_group_resource_export")
    public void loadXls(@RequestParam("utilizationTypeId") Integer utilizationTypeId, @RequestParam("type") String type, ResourceRequest request,
                        ResourceResponse response) throws PortletException, IOException {
        //ObjectMapper mapper = new ObjectMapper();
        String filename = "utilizationType";
        response.setCharacterEncoding("UTF-8");
        String contentType = "text/xml";
        UtilizationTypeM utilizationType = researchService.findUtilizationTypeById(Integer.valueOf(utilizationTypeId));
        String content = "";
        if (type.equals("xml")) {
            filename = filename + ".xml";
            XStream xs = new XStream(new DomDriver());
            xs.processAnnotations(UtilizationTypeM.class);
            content = xs.toXML(utilizationType);

        } else {
            filename = filename + ".csv";
            contentType = "text/csv";
            ColumnPositionMappingStrategy strat = new ColumnPositionMappingStrategy();
            strat.setType(UtilizationTypeM.class);
            String[] columns = new String[]{"utilizationName"}; // the fields to bind do in your JavaBean
            strat.setColumnMapping(columns);
            StringWriter sw = new StringWriter();
            try {
                List<UtilizationTypeM> list = new ArrayList<UtilizationTypeM>(1);
                list.add(utilizationType);
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
    public void resourceUpload(@RequestParam("utilizationTypeId") Integer utilizationTypeId, @RequestParam("type") String type, ResourceRequest request,
                               ResourceResponse response) throws PortletException, IOException {
        //ObjectMapper mapper = new ObjectMapper();
        String status = "success";
        UploadPortletRequest uploadRequest = PortalUtil
                .getUploadPortletRequest(request);
        String sourceFileName = uploadRequest.getFileName("import_" + type);
        File file = uploadRequest.getFile("import_" + type);
        UtilizationTypeM utilizationType = researchService.findUtilizationTypeById(Integer.valueOf(utilizationTypeId));
        User user = (User) request.getAttribute(WebKeys.USER);

        UtilizationTypeM model = new UtilizationTypeM();
        BeanUtils.copyProperties(utilizationType, model);
        model.setUpdatedBy(user.getUserId() + "");
        if (type.equals("csv")) {
            HeaderColumnNameTranslateMappingStrategy<UtilizationTypeM> beanStrategy = new HeaderColumnNameTranslateMappingStrategy<UtilizationTypeM>();
            beanStrategy.setType(UtilizationTypeM.class);

            Map<String, String> columnMapping = new HashMap<String, String>();
            columnMapping.put("utilizationName", "utilizationName");
                     /*   columnMapping.put("groupCode", "groupCode");
				        columnMapping.put("groupTh", "groupTh");
				        columnMapping.put("groupEng", "groupEng");*/
            //columnMapping.put("Salary", "salary");

            beanStrategy.setColumnMapping(columnMapping);

            CsvToBean<UtilizationTypeM> csvToBean = new CsvToBean<UtilizationTypeM>();
            CSVReader reader = null;
            try {
                reader = new CSVReader(new FileReader(file));
                List<UtilizationTypeM> models = csvToBean.parse(beanStrategy, reader);
                if (models != null && models.size() > 0) {
                    UtilizationTypeM model_return = models.get(0);
                    setPropertiesBean(model_return, model);

                }
            } catch (Exception ex) {
                ex.printStackTrace();
            } finally {
                reader.close();
            }

        } else if (type.equals("xml")) {
            XStream xs = new XStream(new DomDriver());
            xs.processAnnotations(UtilizationTypeM.class);
            Object xtarget = xs.fromXML(file);

            if (xtarget != null) {
                UtilizationTypeM model_return = (UtilizationTypeM) xtarget;
                setPropertiesBean(model_return, model);
            }

        }
        if (model != null)
            researchService.updateUtilizationTypeM(model);
        //	model.setGroupTh(sourceFileName);
        try {
            customObjectMapper.writeValue(response.getWriter(), status);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private UtilizationTypeM setPropertiesBean(UtilizationTypeM source, UtilizationTypeM target) {
        target.setUtilizationName(source.getUtilizationName());

        return target;
    }
}
