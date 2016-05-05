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
import th.ac.kmutt.research.form.FundingResourceTypeMasterForm;
import th.ac.kmutt.research.mapper.CustomObjectMapper;
import th.ac.kmutt.research.model.FundingResourceTypeM;
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

@Controller("fundingResourceTypeMasterController")
@RequestMapping("VIEW")
@SessionAttributes({"fundingResourceTypeMasterForm"})
public class FundingResourceTypeMasterController {
    private static final Logger logger = Logger
            .getLogger(FundingResourceTypeMasterController.class);
    @Autowired
    @Qualifier("researchServiceWSImpl")
    private ResearchService researchService;

    @Autowired
    private CustomObjectMapper customObjectMapper;

    @InitBinder
    public void initBinder(PortletRequestDataBinder binder, PortletPreferences preferences) {
        logger.debug("initBinder");
        binder.registerCustomEditor(byte[].class, new ByteArrayMultipartFileEditor());
        final String[] ALLOWED_FIELDS = {"fundingResourceTypeM.fundingResourceTypeId", "fundingResourceTypeM.createdBy", "fundingResourceTypeM.createdDate",
                "fundingResourceTypeM.frtCode", "fundingResourceTypeM.frtName", "fundingResourceTypeM.updatedBy",
                "fundingResourceTypeM.updatedDate", "fundingResourceTypeM.frtSource", "mode", "command", "keySearch", "pageNo", "paging.pageSize", "ids", "tab", "filter"};

        binder.setAllowedFields(ALLOWED_FIELDS);
    }

    @RequestMapping
    // default (action=list)
    public String listFundingResourceType(Model model) {
        FundingResourceTypeMasterForm fundingResourceTypeMasterForm = null;
        if (!model.containsAttribute("fundingResourceTypeMasterForm")) {
            fundingResourceTypeMasterForm = new FundingResourceTypeMasterForm();
            model.addAttribute("fundingResourceTypeMasterForm",
                    fundingResourceTypeMasterForm);
        } else {
            fundingResourceTypeMasterForm = (FundingResourceTypeMasterForm) model.asMap().get("fundingResourceTypeMasterForm");
        }
        String keySearch = fundingResourceTypeMasterForm.getKeySearch();
        FundingResourceTypeM fundingResourceTypeM = new FundingResourceTypeM();
        fundingResourceTypeM.setKeySearch(keySearch);
        fundingResourceTypeM.setPaging(fundingResourceTypeMasterForm.getPaging());
        fundingResourceTypeM.getPaging().setPageNo(fundingResourceTypeMasterForm.getPageNo());

        ImakeResultMessage imakeResultMessage = researchService
                .searchFundingResourceTypeM(fundingResourceTypeM);
        model.addAttribute("fundingResourceTypes", imakeResultMessage.getResultListObj());
        //fundingResourceTypeMasterForm.getPaging().setPageSize(ImakeUtils.PAGE_SIZE);
        fundingResourceTypeMasterForm.setPageCount(ImakeUtils.calculatePage(fundingResourceTypeMasterForm.getPaging().getPageSize(), Integer.parseInt(imakeResultMessage.getMaxRow())));
        int[] start_end = ImakeUtils.calculatePageStartEnd(fundingResourceTypeMasterForm.getPageNo(), fundingResourceTypeMasterForm.getPageCount());
        fundingResourceTypeMasterForm.setPageStart(start_end[0]);
        fundingResourceTypeMasterForm.setPageEnd(start_end[1]);
        return "master/fundingResourceType";
    }

    @RequestMapping(params = "action=doSubmit") // action phase
    public void populateSite(javax.portlet.ActionRequest request, javax.portlet.ActionResponse response,
                             @ModelAttribute("fundingResourceTypeMasterForm") FundingResourceTypeMasterForm fundingResourceTypeMasterForm,
                             BindingResult result, Model model) {
        String command = fundingResourceTypeMasterForm.getCommand();
        String mode = fundingResourceTypeMasterForm.getMode();
        User user = (User) request.getAttribute(WebKeys.USER);
        fundingResourceTypeMasterForm.getFundingResourceTypeM().setUpdatedBy(user.getUserId() + "");
        //if(command.equals("doSaveFAQ")){
        if (mode != null) {
            if (mode.equals("add")) {
                fundingResourceTypeMasterForm.getFundingResourceTypeM().setCreatedBy(user.getUserId() + "");
                fundingResourceTypeMasterForm.getFundingResourceTypeM().setFundingResourceTypeId(null);
                researchService.saveFundingResourceTypeM(fundingResourceTypeMasterForm.getFundingResourceTypeM());
            } else if (mode.equals("edit")) {
                researchService.updateFundingResourceTypeM(fundingResourceTypeMasterForm.getFundingResourceTypeM());
            } else if (mode.equals("deleteItems")) {
                if (fundingResourceTypeMasterForm.getIds() != null && fundingResourceTypeMasterForm.getIds().length > 0) {
                    researchService.deleteItemsFundingResourceTypeM(fundingResourceTypeMasterForm.getIds());
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
    public void removeSite(@RequestParam("fundingResourceTypeId") Integer fundingResourceTypeId,
                           ActionResponse response, Model model) {
        FundingResourceTypeM fundingResourceTypeM = new FundingResourceTypeM();
        fundingResourceTypeM.setFundingResourceTypeId(fundingResourceTypeId);
        int recordCount = researchService.deleteFundingResourceTypeM(fundingResourceTypeM);
        if (recordCount == -9)
            model.addAttribute(ServiceConstant.ERROR_MESSAGE_KEY, ServiceConstant.ERROR_CONSTRAINT_VIOLATION_MESSAGE_CODE);
        response.setRenderParameter("action", "list");
    }

    @ResourceMapping(value = "research_group_resource_get_byid")
    public void getById(@RequestParam("fundingResourceTypeId") Integer fundingResourceTypeId, ResourceRequest request,
                        ResourceResponse response) throws PortletException, IOException {
        String id = request.getParameter("fundingResourceTypeId");
        FundingResourceTypeM fundingResourceType = researchService.findFundingResourceTypeById(Integer.valueOf(fundingResourceTypeId));
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        ResourceURL csvResource = response.createResourceURL();
        csvResource.setResourceID("research_group_resource_export");
        csvResource.setParameter("fundingResourceTypeId", fundingResourceTypeId + "");
        csvResource.setParameter("type", "csv");

        ResourceURL csvUploadResource = response.createResourceURL();
        csvUploadResource.setResourceID("research_group_resource_upload");
        csvUploadResource.setParameter("fundingResourceTypeId", fundingResourceTypeId + "");
        csvUploadResource.setParameter("type", "csv");

        ResourceURL xmlResource = response.createResourceURL();
        xmlResource.setResourceID("research_group_resource_export");
        xmlResource.setParameter("fundingResourceTypeId", fundingResourceTypeId + "");
        xmlResource.setParameter("type", "xml");

        ResourceURL xmlUploadResource = response.createResourceURL();
        xmlUploadResource.setResourceID("research_group_resource_upload");
        xmlUploadResource.setParameter("fundingResourceTypeId", fundingResourceTypeId + "");
        xmlUploadResource.setParameter("type", "xml");

        fundingResourceType.setCsvUploadResource(csvUploadResource.toString());
        fundingResourceType.setXmlUploadResource(xmlUploadResource.toString());
        fundingResourceType.setCsvResource(csvResource.toString());
        fundingResourceType.setXmlResource(xmlResource.toString());
        try {
            customObjectMapper.writeValue(response.getWriter(), fundingResourceType);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @ResourceMapping(value = "research_group_resource_export")
    public void loadXls(@RequestParam("fundingResourceTypeId") Integer fundingResourceTypeId, @RequestParam("type") String type, ResourceRequest request,
                        ResourceResponse response) throws PortletException, IOException {
        //ObjectMapper mapper = new ObjectMapper();
        String filename = "fundingResourceType";
        response.setCharacterEncoding("UTF-8");
        String contentType = "text/xml";
        FundingResourceTypeM fundingResourceType = researchService.findFundingResourceTypeById(Integer.valueOf(fundingResourceTypeId));
        String content = "";
        if (type.equals("xml")) {
            filename = filename + ".xml";
            XStream xs = new XStream(new DomDriver());
            xs.processAnnotations(FundingResourceTypeM.class);
            content = xs.toXML(fundingResourceType);

        } else {
            filename = filename + ".csv";
            contentType = "text/csv";
            ColumnPositionMappingStrategy strat = new ColumnPositionMappingStrategy();
            strat.setType(FundingResourceTypeM.class);
            String[] columns = new String[]{"frtName"}; // the fields to bind do in your JavaBean
            strat.setColumnMapping(columns);
            StringWriter sw = new StringWriter();
            try {
                List<FundingResourceTypeM> list = new ArrayList<FundingResourceTypeM>(1);
                list.add(fundingResourceType);
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
    public void resourceUpload(@RequestParam("fundingResourceTypeId") Integer fundingResourceTypeId, @RequestParam("type") String type, ResourceRequest request,
                               ResourceResponse response) throws PortletException, IOException {
        //ObjectMapper mapper = new ObjectMapper();
        String status = "success";
        UploadPortletRequest uploadRequest = PortalUtil
                .getUploadPortletRequest(request);
        String sourceFileName = uploadRequest.getFileName("import_" + type);
        File file = uploadRequest.getFile("import_" + type);
        FundingResourceTypeM fundingResourceType = researchService.findFundingResourceTypeById(Integer.valueOf(fundingResourceTypeId));
        User user = (User) request.getAttribute(WebKeys.USER);

        FundingResourceTypeM model = new FundingResourceTypeM();
        BeanUtils.copyProperties(fundingResourceType, model);
        model.setUpdatedBy(user.getUserId() + "");
        if (type.equals("csv")) {
            HeaderColumnNameTranslateMappingStrategy<FundingResourceTypeM> beanStrategy = new HeaderColumnNameTranslateMappingStrategy<FundingResourceTypeM>();
            beanStrategy.setType(FundingResourceTypeM.class);

            Map<String, String> columnMapping = new HashMap<String, String>();
            columnMapping.put("frtName", "frtName");
				      /*  columnMapping.put("groupCode", "groupCode");
				        columnMapping.put("groupTh", "groupTh");
				        columnMapping.put("groupEng", "groupEng");*/
            //columnMapping.put("Salary", "salary");

            beanStrategy.setColumnMapping(columnMapping);

            CsvToBean<FundingResourceTypeM> csvToBean = new CsvToBean<FundingResourceTypeM>();
            CSVReader reader = null;
            try {
                reader = new CSVReader(new FileReader(file));
                List<FundingResourceTypeM> models = csvToBean.parse(beanStrategy, reader);
                if (models != null && models.size() > 0) {
                    FundingResourceTypeM model_return = models.get(0);
                    setPropertiesBean(model_return, model);

                }
            } catch (Exception ex) {
                ex.printStackTrace();
            } finally {
                reader.close();
            }

        } else if (type.equals("xml")) {
            XStream xs = new XStream(new DomDriver());
            xs.processAnnotations(FundingResourceTypeM.class);
            Object xtarget = xs.fromXML(file);

            if (xtarget != null) {
                FundingResourceTypeM model_return = (FundingResourceTypeM) xtarget;
                setPropertiesBean(model_return, model);
            }

        }
        if (model != null)
            researchService.updateFundingResourceTypeM(model);
        //model.setGroupTh(sourceFileName);
        try {
            customObjectMapper.writeValue(response.getWriter(), status);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private FundingResourceTypeM setPropertiesBean(FundingResourceTypeM source, FundingResourceTypeM target) {
        target.setFrtName(source.getFrtName());
		/*target.setGroupTh(source.getGroupTh());
		target.setGroupEng(source.getGroupEng());*/
        return target;
    }
}
