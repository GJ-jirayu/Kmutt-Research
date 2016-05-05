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
import th.ac.kmutt.research.form.JournalPapersTypeMasterForm;
import th.ac.kmutt.research.mapper.CustomObjectMapper;
import th.ac.kmutt.research.model.JournalPapersTypeM;
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

@Controller("journalPapersTypeMasterController")
@RequestMapping("VIEW")
@SessionAttributes({"journalPapersTypeMasterForm"})
public class JournalPapersTypeMasterController {
    private static final Logger logger = Logger
            .getLogger(JournalPapersTypeMasterController.class);
    @Autowired
    @Qualifier("researchServiceWSImpl")
    private ResearchService researchService;

    @Autowired
    private CustomObjectMapper customObjectMapper;

    @InitBinder
    public void initBinder(PortletRequestDataBinder binder, PortletPreferences preferences) {
        logger.debug("initBinder");
        binder.registerCustomEditor(byte[].class, new ByteArrayMultipartFileEditor());
        final String[] ALLOWED_FIELDS = {"journalPapersTypeM.journalPapersTypeId", "journalPapersTypeM.createdBy", "journalPapersTypeM.createdDate",
                "journalPapersTypeM.jptCode", "journalPapersTypeM.jptName", "journalPapersTypeM.updatedBy",
                "journalPapersTypeM.updatedDate", "mode", "command", "keySearch", "pageNo", "paging.pageSize", "ids", "tab", "filter"};

        binder.setAllowedFields(ALLOWED_FIELDS);
    }

    @RequestMapping
    // default (action=list)
    public String listJournalPapersType(Model model) {
        JournalPapersTypeMasterForm journalPapersTypeMasterForm = null;
        if (!model.containsAttribute("journalPapersTypeMasterForm")) {
            journalPapersTypeMasterForm = new JournalPapersTypeMasterForm();
            model.addAttribute("journalPapersTypeMasterForm",
                    journalPapersTypeMasterForm);
        } else {
            journalPapersTypeMasterForm = (JournalPapersTypeMasterForm) model.asMap().get("journalPapersTypeMasterForm");
        }
        String keySearch = journalPapersTypeMasterForm.getKeySearch();
        JournalPapersTypeM journalPapersTypeM = new JournalPapersTypeM();
        journalPapersTypeM.setKeySearch(keySearch);
        journalPapersTypeM.setPaging(journalPapersTypeMasterForm.getPaging());
        journalPapersTypeM.getPaging().setPageNo(journalPapersTypeMasterForm.getPageNo());

        ImakeResultMessage imakeResultMessage = researchService
                .searchJournalPapersTypeM(journalPapersTypeM);
        model.addAttribute("journalPapersTypes", imakeResultMessage.getResultListObj());
        //journalPapersTypeMasterForm.getPaging().setPageSize(ImakeUtils.PAGE_SIZE);
        journalPapersTypeMasterForm.setPageCount(ImakeUtils.calculatePage(journalPapersTypeMasterForm.getPaging().getPageSize(), Integer.parseInt(imakeResultMessage.getMaxRow())));
        int[] start_end = ImakeUtils.calculatePageStartEnd(journalPapersTypeMasterForm.getPageNo(), journalPapersTypeMasterForm.getPageCount());
        journalPapersTypeMasterForm.setPageStart(start_end[0]);
        journalPapersTypeMasterForm.setPageEnd(start_end[1]);
        return "master/journalPapersType";
    }


    @RequestMapping(params = "action=doSubmit") // action phase
    public void populateSite(javax.portlet.ActionRequest request, javax.portlet.ActionResponse response,
                             @ModelAttribute("journalPapersTypeMasterForm") JournalPapersTypeMasterForm journalPapersTypeMasterForm,
                             BindingResult result, Model model) {

        String command = journalPapersTypeMasterForm.getCommand();
        String mode = journalPapersTypeMasterForm.getMode();
        User user = (User) request.getAttribute(WebKeys.USER);
        journalPapersTypeMasterForm.getJournalPapersTypeM().setUpdatedBy(user.getUserId() + "");
        //if(command.equals("doSaveFAQ")){
        if (mode != null) {
            if (mode.equals("add")) {
                journalPapersTypeMasterForm.getJournalPapersTypeM().setCreatedBy(user.getUserId() + "");
                journalPapersTypeMasterForm.getJournalPapersTypeM().setJournalPapersTypeId(null);
                researchService.saveJournalPapersTypeM(journalPapersTypeMasterForm.getJournalPapersTypeM());
            } else if (mode.equals("edit")) {
                researchService.updateJournalPapersTypeM(journalPapersTypeMasterForm.getJournalPapersTypeM());
            } else if (mode.equals("deleteItems")) {
                if (journalPapersTypeMasterForm.getIds() != null && journalPapersTypeMasterForm.getIds().length > 0) {
                    researchService.deleteItemsJournalPapersTypeM(journalPapersTypeMasterForm.getIds());
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
    public void removeSite(@RequestParam("journalPapersTypeId") Integer journalPapersTypeId,
                           ActionResponse response, Model model) {
        JournalPapersTypeM journalPapersTypeM = new JournalPapersTypeM();
        journalPapersTypeM.setJournalPapersTypeId(journalPapersTypeId);
        int recordCount = researchService.deleteJournalPapersTypeM(journalPapersTypeM);
        if (recordCount == -9)
            model.addAttribute(ServiceConstant.ERROR_MESSAGE_KEY, ServiceConstant.ERROR_CONSTRAINT_VIOLATION_MESSAGE_CODE);
        response.setRenderParameter("action", "list");
    }

    @ResourceMapping(value = "research_group_resource_get_byid")
    public void getById(@RequestParam("journalPapersTypeId") Integer journalPapersTypeId, ResourceRequest request,
                        ResourceResponse response) throws PortletException, IOException {
        //String id=request.getParameter("journalPapersTypeId");
        JournalPapersTypeM journalPapersType = researchService.findJournalPapersTypeById(Integer.valueOf(journalPapersTypeId));
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        ResourceURL csvResource = response.createResourceURL();
        csvResource.setResourceID("research_group_resource_export");
        csvResource.setParameter("journalPapersTypeId", journalPapersTypeId + "");
        csvResource.setParameter("type", "csv");

        ResourceURL csvUploadResource = response.createResourceURL();
        csvUploadResource.setResourceID("research_group_resource_upload");
        csvUploadResource.setParameter("journalPapersTypeId", journalPapersTypeId + "");
        csvUploadResource.setParameter("type", "csv");

        ResourceURL xmlResource = response.createResourceURL();
        xmlResource.setResourceID("research_group_resource_export");
        xmlResource.setParameter("journalPapersTypeId", journalPapersTypeId + "");
        xmlResource.setParameter("type", "xml");

        ResourceURL xmlUploadResource = response.createResourceURL();
        xmlUploadResource.setResourceID("research_group_resource_upload");
        xmlUploadResource.setParameter("journalPapersTypeId", journalPapersTypeId + "");
        xmlUploadResource.setParameter("type", "xml");

        journalPapersType.setCsvUploadResource(csvUploadResource.toString());
        journalPapersType.setXmlUploadResource(xmlUploadResource.toString());
        journalPapersType.setCsvResource(csvResource.toString());
        journalPapersType.setXmlResource(xmlResource.toString());
        try {
            customObjectMapper.writeValue(response.getWriter(), journalPapersType);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @ResourceMapping(value = "research_group_resource_export")
    public void loadXls(@RequestParam("journalPapersTypeId") Integer journalPapersTypeId, @RequestParam("type") String type, ResourceRequest request,
                        ResourceResponse response) throws PortletException, IOException {
        //ObjectMapper mapper = new ObjectMapper();
        String filename = "journalPapersType";
        response.setCharacterEncoding("UTF-8");
        String contentType = "text/xml";
        JournalPapersTypeM journalPapersType = researchService.findJournalPapersTypeById(Integer.valueOf(journalPapersTypeId));
        String content = "";
        if (type.equals("xml")) {
            filename = filename + ".xml";
            XStream xs = new XStream(new DomDriver());
            xs.processAnnotations(JournalPapersTypeM.class);
            content = xs.toXML(journalPapersType);

        } else {
            filename = filename + ".csv";
            contentType = "text/csv";
            ColumnPositionMappingStrategy strat = new ColumnPositionMappingStrategy();
            strat.setType(JournalPapersTypeM.class);
            String[] columns = new String[]{"jptName"}; // the fields to bind do in your JavaBean
            strat.setColumnMapping(columns);
            StringWriter sw = new StringWriter();
            try {
                List<JournalPapersTypeM> list = new ArrayList<JournalPapersTypeM>(1);
                list.add(journalPapersType);
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
    public void resourceUpload(@RequestParam("journalPapersTypeId") Integer journalPapersTypeId, @RequestParam("type") String type, ResourceRequest request,
                               ResourceResponse response) throws PortletException, IOException {
        //ObjectMapper mapper = new ObjectMapper();
        String status = "success";
        UploadPortletRequest uploadRequest = PortalUtil
                .getUploadPortletRequest(request);
        String sourceFileName = uploadRequest.getFileName("import_" + type);
        File file = uploadRequest.getFile("import_" + type);
        JournalPapersTypeM journalPapersType = researchService.findJournalPapersTypeById(Integer.valueOf(journalPapersTypeId));
        User user = (User) request.getAttribute(WebKeys.USER);

        JournalPapersTypeM model = new JournalPapersTypeM();
        BeanUtils.copyProperties(journalPapersType, model);
        model.setUpdatedBy(user.getUserId() + "");
        if (type.equals("csv")) {
            HeaderColumnNameTranslateMappingStrategy<JournalPapersTypeM> beanStrategy = new HeaderColumnNameTranslateMappingStrategy<JournalPapersTypeM>();
            beanStrategy.setType(JournalPapersTypeM.class);

            Map<String, String> columnMapping = new HashMap<String, String>();
            columnMapping.put("jptName", "jptName");
				       /* columnMapping.put("groupCode", "groupCode");
				        columnMapping.put("groupTh", "groupTh");
				        columnMapping.put("groupEng", "groupEng");*/
            //columnMapping.put("Salary", "salary");

            beanStrategy.setColumnMapping(columnMapping);

            CsvToBean<JournalPapersTypeM> csvToBean = new CsvToBean<JournalPapersTypeM>();
            CSVReader reader = null;
            try {
                reader = new CSVReader(new FileReader(file));
                List<JournalPapersTypeM> models = csvToBean.parse(beanStrategy, reader);
                if (models != null && models.size() > 0) {
                    JournalPapersTypeM model_return = models.get(0);
                    setPropertiesBean(model_return, model);

                }
            } catch (Exception ex) {
                ex.printStackTrace();
            } finally {
                reader.close();
            }

        } else if (type.equals("xml")) {
            XStream xs = new XStream(new DomDriver());
            xs.processAnnotations(JournalPapersTypeM.class);
            Object xtarget = xs.fromXML(file);

            if (xtarget != null) {
                JournalPapersTypeM model_return = (JournalPapersTypeM) xtarget;
                setPropertiesBean(model_return, model);
            }

        }
        if (model != null)
            researchService.updateJournalPapersTypeM(model);
        //model.setGroupTh(sourceFileName);
        try {
            customObjectMapper.writeValue(response.getWriter(), status);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private JournalPapersTypeM setPropertiesBean(JournalPapersTypeM source, JournalPapersTypeM target) {
        target.setJptName(source.getJptName());
		/*target.setGroupTh(source.getGroupTh());
		target.setGroupEng(source.getGroupEng());*/
        return target;
    }
}
