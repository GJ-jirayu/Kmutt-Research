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
import th.ac.kmutt.research.form.PositionMasterForm;
import th.ac.kmutt.research.mapper.CustomObjectMapper;
import th.ac.kmutt.research.model.PositionM;
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

@Controller("positionMasterController")
@RequestMapping("VIEW")
@SessionAttributes({"positionMasterForm"})
public class PositionMasterController {
    private static final Logger logger = Logger
            .getLogger(PositionMasterController.class);
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
        final String[] ALLOWED_FIELDS = {"positionM.positionId", "positionM.createdBy", "positionM.createdDate",
                "positionM.positionCode", "positionM.positionName", "positionM.updatedBy", "positionM.positionType",
                "positionM.updatedDate", "mode", "command", "keySearch", "pageNo", "paging.pageSize", "ids", "tab", "filter"};

        binder.setAllowedFields(ALLOWED_FIELDS);
    }

    @RequestMapping
    // default (action=list)
    public String listPosition(javax.portlet.RenderRequest request, Model model) {
        PositionMasterForm positionMasterForm = null;
        if (!model.containsAttribute("positionMasterForm")) {
            positionMasterForm = new PositionMasterForm();
            model.addAttribute("positionMasterForm",
                    positionMasterForm);
        } else {
            positionMasterForm = (PositionMasterForm) model.asMap().get("positionMasterForm");
        }
        String keySearch = positionMasterForm.getKeySearch();
        PositionM positionM = new PositionM();
        positionM.setKeySearch(keySearch);

        positionM.setPaging(positionMasterForm.getPaging());
        positionM.getPaging().setPageNo(positionMasterForm.getPageNo());

        ImakeResultMessage imakeResultMessage = researchService
                .searchPositionM(positionM);
        model.addAttribute("positions", imakeResultMessage.getResultListObj());
        //positionMasterForm.getPaging().setPageSize(ImakeUtils.PAGE_SIZE);
        positionMasterForm.setPageCount(ImakeUtils.calculatePage(positionMasterForm.getPaging().getPageSize(), Integer.parseInt(imakeResultMessage.getMaxRow())));
        int[] start_end = ImakeUtils.calculatePageStartEnd(positionMasterForm.getPageNo(), positionMasterForm.getPageCount());
        positionMasterForm.setPageStart(start_end[0]);
        positionMasterForm.setPageEnd(start_end[1]);
        return "master/position";
    }

    @RequestMapping(params = "action=add")
    // render phase
    public String showPositionMasterForm(Model model) {
        model.addAttribute("positionMasterForm",
                new PositionMasterForm());
        return "master/position";
    }

    @RequestMapping(params = "action=doSubmit") // action phase
    public void populateSite(javax.portlet.ActionRequest request, javax.portlet.ActionResponse response,
                             @ModelAttribute("positionMasterForm") PositionMasterForm positionMasterForm,
                             BindingResult result, Model model) {
        String command = positionMasterForm.getCommand();
        String mode = positionMasterForm.getMode();
        User user = (User) request.getAttribute(WebKeys.USER);
        positionMasterForm.getPositionM().setUpdatedBy(user.getUserId() + "");
        if (mode != null) {
            if (mode.equals("add")) {
                positionMasterForm.getPositionM().setCreatedBy(user.getUserId() + "");
                positionMasterForm.getPositionM().setPositionId(null);
                researchService.savePositionM(positionMasterForm.getPositionM());
            } else if (mode.equals("edit")) {
                researchService.updatePositionM(positionMasterForm.getPositionM());
            } else if (mode.equals("deleteItems")) {
                if (positionMasterForm.getIds() != null && positionMasterForm.getIds().length > 0) {
                    researchService.deleteItemsPositionM(positionMasterForm.getIds());
                }
            } else {
            }
        }
        command = "list";
        //}
        response.setRenderParameter("action", command);
    }

    @RequestMapping(params = "action=delete")
    public void removeSite(@RequestParam("positionId") Integer positionId,
                           ActionResponse response, Model model) {
        PositionM positionM = new PositionM();
        positionM.setPositionId(positionId);
        int recordCount = researchService.deletePositionM(positionM);
        if (recordCount == -9)
            model.addAttribute(ServiceConstant.ERROR_MESSAGE_KEY, ServiceConstant.ERROR_CONSTRAINT_VIOLATION_MESSAGE_CODE);
        response.setRenderParameter("action", "list");
    }

    @ResourceMapping(value = "research_group_resource_get_byid")
    public void getById(@RequestParam("positionId") Integer positionId, ResourceRequest request,
                        ResourceResponse response) throws PortletException, IOException {
        String id = request.getParameter("positionId");
        PositionM position = researchService.findPositionById(Integer.valueOf(positionId));
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        ResourceURL csvResource = response.createResourceURL();
        csvResource.setResourceID("research_group_resource_export");
        csvResource.setParameter("positionId", positionId + "");
        csvResource.setParameter("type", "csv");

        ResourceURL csvUploadResource = response.createResourceURL();
        csvUploadResource.setResourceID("research_group_resource_upload");
        csvUploadResource.setParameter("positionId", positionId + "");
        csvUploadResource.setParameter("type", "csv");

        ResourceURL xmlResource = response.createResourceURL();
        xmlResource.setResourceID("research_group_resource_export");
        xmlResource.setParameter("positionId", positionId + "");
        xmlResource.setParameter("type", "xml");

        ResourceURL xmlUploadResource = response.createResourceURL();
        xmlUploadResource.setResourceID("research_group_resource_upload");
        xmlUploadResource.setParameter("positionId", positionId + "");
        xmlUploadResource.setParameter("type", "xml");

        position.setCsvUploadResource(csvUploadResource.toString());
        position.setXmlUploadResource(xmlUploadResource.toString());
        position.setCsvResource(csvResource.toString());
        position.setXmlResource(xmlResource.toString());
        try {
            //mapper.writeValue(response.getWriter(), position);
            customObjectMapper.writeValue(response.getWriter(), position);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @ResourceMapping(value = "research_group_resource_export")
    public void loadXls(@RequestParam("positionId") Integer positionId, @RequestParam("type") String type, ResourceRequest request,
                        ResourceResponse response) throws PortletException, IOException {
        //ObjectMapper mapper = new ObjectMapper();
        String filename = "position";
        response.setCharacterEncoding("UTF-8");
        String contentType = "text/xml";
        PositionM position = researchService.findPositionById(Integer.valueOf(positionId));
        String content = "";
        if (type.equals("xml")) {
            filename = filename + ".xml";
            XStream xs = new XStream(new DomDriver());
            xs.processAnnotations(PositionM.class);
            content = xs.toXML(position);

        } else {
            filename = filename + ".csv";
            contentType = "text/csv";
            ColumnPositionMappingStrategy strat = new ColumnPositionMappingStrategy();
            strat.setType(PositionM.class);
            String[] columns = new String[]{"positionName"}; // the fields to bind do in your JavaBean
            strat.setColumnMapping(columns);
            StringWriter sw = new StringWriter();
            try {
                List<PositionM> list = new ArrayList<PositionM>(1);
                list.add(position);
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
    public void resourceUpload(@RequestParam("positionId") Integer positionId, @RequestParam("type") String type, ResourceRequest request,
                               ResourceResponse response) throws PortletException, IOException {
        //ObjectMapper mapper = new ObjectMapper();
        String status = "success";
        UploadPortletRequest uploadRequest = PortalUtil
                .getUploadPortletRequest(request);
        String sourceFileName = uploadRequest.getFileName("import_" + type);
        File file = uploadRequest.getFile("import_" + type);
        PositionM position = researchService.findPositionById(Integer.valueOf(positionId));
        User user = (User) request.getAttribute(WebKeys.USER);

        PositionM model = new PositionM();
        BeanUtils.copyProperties(position, model);
        model.setUpdatedBy(user.getUserId() + "");
        if (type.equals("csv")) {
            HeaderColumnNameTranslateMappingStrategy<PositionM> beanStrategy = new HeaderColumnNameTranslateMappingStrategy<PositionM>();
            beanStrategy.setType(PositionM.class);

            Map<String, String> columnMapping = new HashMap<String, String>();
            columnMapping.put("positionName", "positionName");
				      /*  columnMapping.put("groupCode", "groupCode");
				        columnMapping.put("groupTh", "groupTh");
				        columnMapping.put("groupEng", "groupEng");*/
            //columnMapping.put("Salary", "salary");

            beanStrategy.setColumnMapping(columnMapping);

            CsvToBean<PositionM> csvToBean = new CsvToBean<PositionM>();
            CSVReader reader = null;
            try {
                reader = new CSVReader(new FileReader(file));
                List<PositionM> models = csvToBean.parse(beanStrategy, reader);
                if (models != null && models.size() > 0) {
                    PositionM model_return = models.get(0);
                    setPropertiesBean(model_return, model);

                }
            } catch (Exception ex) {
                ex.printStackTrace();
            } finally {
                reader.close();
            }

        } else if (type.equals("xml")) {
            XStream xs = new XStream(new DomDriver());
            xs.processAnnotations(PositionM.class);
            Object xtarget = xs.fromXML(file);

            if (xtarget != null) {
                PositionM model_return = (PositionM) xtarget;
                setPropertiesBean(model_return, model);
            }

        }
        if (model != null)
            researchService.updatePositionM(model);
        //	model.setGroupTh(sourceFileName);
        try {
            customObjectMapper.writeValue(response.getWriter(), status);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private PositionM setPropertiesBean(PositionM source, PositionM target) {
        target.setPositionName(source.getPositionName());
		/*target.setGroupTh(source.getGroupTh());
		target.setGroupEng(source.getGroupEng());*/
        return target;
    }
}
