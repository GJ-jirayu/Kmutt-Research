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
import th.ac.kmutt.research.form.WorkTypeMasterForm;
import th.ac.kmutt.research.mapper.CustomObjectMapper;
import th.ac.kmutt.research.model.WorkTypeM;
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

@Controller("workTypeMasterController")
@RequestMapping("VIEW")
@SessionAttributes({"workTypeMasterForm"})
public class WorkTypeMasterController {
    private static final Logger logger = Logger
            .getLogger(WorkTypeMasterController.class);
    @Autowired
    @Qualifier("researchServiceWSImpl")
    private ResearchService researchService;

    @Autowired
    private CustomObjectMapper customObjectMapper;

    @InitBinder
    public void initBinder(PortletRequestDataBinder binder, PortletPreferences preferences) {
        logger.debug("initBinder");
        binder.registerCustomEditor(byte[].class, new ByteArrayMultipartFileEditor());
        final String[] ALLOWED_FIELDS = {"workTypeM.workTypeId", "workTypeM.createdBy", "workTypeM.createdDate",
                "workTypeM.wtCode", "workTypeM.wtName", "workTypeM.updatedBy",
                "workTypeM.updatedDate", "mode", "command", "keySearch", "pageNo", "paging.pageSize", "ids", "tab", "filter"};

        binder.setAllowedFields(ALLOWED_FIELDS);
    }

    @RequestMapping
    // default (action=list)
    public String listWorkType(Model model) {
        WorkTypeMasterForm workTypeMasterForm = null;
        if (!model.containsAttribute("workTypeMasterForm")) {
            workTypeMasterForm = new WorkTypeMasterForm();
            model.addAttribute("workTypeMasterForm",
                    workTypeMasterForm);
        } else {
            workTypeMasterForm = (WorkTypeMasterForm) model.asMap().get("workTypeMasterForm");
        }
        String keySearch = workTypeMasterForm.getKeySearch();
        WorkTypeM workTypeM = new WorkTypeM();
        workTypeM.setKeySearch(keySearch);
        workTypeM.setPaging(workTypeMasterForm.getPaging());
        workTypeM.getPaging().setPageNo(workTypeMasterForm.getPageNo());

        ImakeResultMessage imakeResultMessage = researchService
                .searchWorkTypeM(workTypeM);
        model.addAttribute("workTypes", imakeResultMessage.getResultListObj());
        //workTypeMasterForm.getPaging().setPageSize(ImakeUtils.PAGE_SIZE);
        workTypeMasterForm.setPageCount(ImakeUtils.calculatePage(workTypeMasterForm.getPaging().getPageSize(), Integer.parseInt(imakeResultMessage.getMaxRow())));
        int[] start_end = ImakeUtils.calculatePageStartEnd(workTypeMasterForm.getPageNo(), workTypeMasterForm.getPageCount());
        workTypeMasterForm.setPageStart(start_end[0]);
        workTypeMasterForm.setPageEnd(start_end[1]);
        return "master/workType";
    }

    @RequestMapping(params = "action=doSubmit") // action phase
    public void populateSite(javax.portlet.ActionRequest request, javax.portlet.ActionResponse response,
                             @ModelAttribute("workTypeMasterForm") WorkTypeMasterForm workTypeMasterForm,
                             BindingResult result, Model model) {
        String command = workTypeMasterForm.getCommand();
        String mode = workTypeMasterForm.getMode();
        User user = (User) request.getAttribute(WebKeys.USER);
        workTypeMasterForm.getWorkTypeM().setUpdatedBy(user.getUserId() + "");
        if (mode != null) {
            if (mode.equals("add")) {
                workTypeMasterForm.getWorkTypeM().setCreatedBy(user.getUserId() + "");
                workTypeMasterForm.getWorkTypeM().setWorkTypeId(null);
                researchService.saveWorkTypeM(workTypeMasterForm.getWorkTypeM());
            } else if (mode.equals("edit")) {
                researchService.updateWorkTypeM(workTypeMasterForm.getWorkTypeM());
            } else if (mode.equals("deleteItems")) {
                if (workTypeMasterForm.getIds() != null && workTypeMasterForm.getIds().length > 0) {
                    researchService.deleteItemsWorkTypeM(workTypeMasterForm.getIds());
                }
            } else {
            }
        }
        command = "list";
        //}
        response.setRenderParameter("action", command);
    }

    @RequestMapping(params = "action=delete")
    public void removeSite(@RequestParam("workTypeId") Integer workTypeId,
                           ActionResponse response, Model model) {
        WorkTypeM workTypeM = new WorkTypeM();
        workTypeM.setWorkTypeId(workTypeId);
        int recordCount = researchService.deleteWorkTypeM(workTypeM);
        if (recordCount == -9)
            model.addAttribute(ServiceConstant.ERROR_MESSAGE_KEY, ServiceConstant.ERROR_CONSTRAINT_VIOLATION_MESSAGE_CODE);
        response.setRenderParameter("action", "list");
    }

    @ResourceMapping(value = "research_group_resource_get_byid")
    public void getById(@RequestParam("workTypeId") Integer workTypeId, ResourceRequest request,
                        ResourceResponse response) throws PortletException, IOException {
        String id = request.getParameter("workTypeId");
        WorkTypeM workType = researchService.findWorkTypeById(Integer.valueOf(workTypeId));
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");

        ResourceURL csvResource = response.createResourceURL();
        csvResource.setResourceID("research_group_resource_export");
        csvResource.setParameter("workTypeId", workTypeId + "");
        csvResource.setParameter("type", "csv");

        ResourceURL csvUploadResource = response.createResourceURL();
        csvUploadResource.setResourceID("research_group_resource_upload");
        csvUploadResource.setParameter("workTypeId", workTypeId + "");
        csvUploadResource.setParameter("type", "csv");

        ResourceURL xmlResource = response.createResourceURL();
        xmlResource.setResourceID("research_group_resource_export");
        xmlResource.setParameter("workTypeId", workTypeId + "");
        xmlResource.setParameter("type", "xml");

        ResourceURL xmlUploadResource = response.createResourceURL();
        xmlUploadResource.setResourceID("research_group_resource_upload");
        xmlUploadResource.setParameter("workTypeId", workTypeId + "");
        xmlUploadResource.setParameter("type", "xml");

        workType.setCsvUploadResource(csvUploadResource.toString());
        workType.setXmlUploadResource(xmlUploadResource.toString());
        workType.setCsvResource(csvResource.toString());
        workType.setXmlResource(xmlResource.toString());
        try {
            customObjectMapper.writeValue(response.getWriter(), workType);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @ResourceMapping(value = "research_group_resource_export")
    public void loadXls(@RequestParam("workTypeId") Integer workTypeId, @RequestParam("type") String type, ResourceRequest request,
                        ResourceResponse response) throws PortletException, IOException {
        //ObjectMapper mapper = new ObjectMapper();
        String filename = "researchGroup";
        response.setCharacterEncoding("UTF-8");
        String contentType = "text/xml";
        WorkTypeM workTypeM = researchService.findWorkTypeById(Integer.valueOf(workTypeId));
        String content = "";
        if (type.equals("xml")) {
            filename = filename + ".xml";
            XStream xs = new XStream(new DomDriver());
            xs.processAnnotations(WorkTypeM.class);
            content = xs.toXML(workTypeM);

        } else {
            filename = filename + ".csv";
            contentType = "text/csv";
            ColumnPositionMappingStrategy strat = new ColumnPositionMappingStrategy();
            strat.setType(WorkTypeM.class);
            String[] columns = new String[]{"wtName"}; // the fields to bind do in your JavaBean
            strat.setColumnMapping(columns);
            StringWriter sw = new StringWriter();
            try {
                List<WorkTypeM> list = new ArrayList<WorkTypeM>(1);
                list.add(workTypeM);
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
    public void resourceUpload(@RequestParam("workTypeId") Integer workTypeId, @RequestParam("type") String type, ResourceRequest request,
                               ResourceResponse response) throws PortletException, IOException {
        //ObjectMapper mapper = new ObjectMapper();
        String status = "success";
        UploadPortletRequest uploadRequest = PortalUtil
                .getUploadPortletRequest(request);
        String sourceFileName = uploadRequest.getFileName("import_" + type);
        File file = uploadRequest.getFile("import_" + type);
        WorkTypeM researchGroup = researchService.findWorkTypeById(Integer.valueOf(workTypeId));
        User user = (User) request.getAttribute(WebKeys.USER);

        WorkTypeM model = new WorkTypeM();
        BeanUtils.copyProperties(researchGroup, model);
        model.setUpdatedBy(user.getUserId() + "");
        if (type.equals("csv")) {
            HeaderColumnNameTranslateMappingStrategy<WorkTypeM> beanStrategy = new HeaderColumnNameTranslateMappingStrategy<WorkTypeM>();
            beanStrategy.setType(WorkTypeM.class);

            Map<String, String> columnMapping = new HashMap<String, String>();
            columnMapping.put("wtName", "wtName");
                      /*  columnMapping.put("groupCode", "groupCode");
				        columnMapping.put("groupTh", "groupTh");
				        columnMapping.put("groupEng", "groupEng");*/
            //columnMapping.put("Salary", "salary");

            beanStrategy.setColumnMapping(columnMapping);

            CsvToBean<WorkTypeM> csvToBean = new CsvToBean<WorkTypeM>();
            CSVReader reader = null;
            try {
                reader = new CSVReader(new FileReader(file));
                List<WorkTypeM> models = csvToBean.parse(beanStrategy, reader);
                if (models != null && models.size() > 0) {
                    WorkTypeM model_return = models.get(0);
                    setPropertiesBean(model_return, model);

                }
            } catch (Exception ex) {
                ex.printStackTrace();
            } finally {
                reader.close();
            }

        } else if (type.equals("xml")) {
            XStream xs = new XStream(new DomDriver());
            xs.processAnnotations(WorkTypeM.class);
            Object xtarget = xs.fromXML(file);

            if (xtarget != null) {
                WorkTypeM model_return = (WorkTypeM) xtarget;
                setPropertiesBean(model_return, model);
            }

        }
        if (model != null)
            researchService.updateWorkTypeM(model);
        //model.setGroupTh(sourceFileName);
        try {
            customObjectMapper.writeValue(response.getWriter(), status);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private WorkTypeM setPropertiesBean(WorkTypeM source, WorkTypeM target) {
        target.setWtName(source.getWtName());
		/*target.setGroupTh(source.getGroupTh());
		target.setGroupEng(source.getGroupEng());*/
        return target;
    }
}
