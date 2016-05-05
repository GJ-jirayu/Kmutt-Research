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
import th.ac.kmutt.research.form.CountryMasterForm;
import th.ac.kmutt.research.mapper.CustomObjectMapper;
import th.ac.kmutt.research.model.CountryM;
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

@Controller("countryMasterController")
@RequestMapping("VIEW")
@SessionAttributes({"countryMasterForm"})
public class CountryMasterController {
    private static final Logger logger = Logger
            .getLogger(CountryMasterController.class);
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
        final String[] ALLOWED_FIELDS = {"countryM.countryId", "countryM.createdBy", "countryM.createdDate",
                "countryM.countryCode", "countryM.countryNameTh", "countryM.countryNameEng", "countryM.updatedBy",
                "countryM.updatedDate", "mode", "command", "keySearch", "pageNo", "paging.pageSize", "ids", "tab", "filter"};

        binder.setAllowedFields(ALLOWED_FIELDS);
    }

    @RequestMapping
    // default (action=list)
    public String listCountry(Model model) {
        CountryMasterForm countryMasterForm = null;
        if (!model.containsAttribute("countryMasterForm")) {
            countryMasterForm = new CountryMasterForm();
            model.addAttribute("countryMasterForm",
                    countryMasterForm);
        } else {
            countryMasterForm = (CountryMasterForm) model.asMap().get("countryMasterForm");
        }
        String keySearch = countryMasterForm.getKeySearch();
        CountryM countryM = new CountryM();
        countryM.setKeySearch(keySearch);
        countryM.setPaging(countryMasterForm.getPaging());
        countryM.getPaging().setPageNo(countryMasterForm.getPageNo());

        ImakeResultMessage imakeResultMessage = researchService
                .searchCountryM(countryM);
        model.addAttribute("countrys", imakeResultMessage.getResultListObj());
        //countryMasterForm.getPaging().setPageSize(ImakeUtils.PAGE_SIZE);
        countryMasterForm.setPageCount(ImakeUtils.calculatePage(countryMasterForm.getPaging().getPageSize(), Integer.parseInt(imakeResultMessage.getMaxRow())));
        int[] start_end = ImakeUtils.calculatePageStartEnd(countryMasterForm.getPageNo(), countryMasterForm.getPageCount());
        countryMasterForm.setPageStart(start_end[0]);
        countryMasterForm.setPageEnd(start_end[1]);
        return "master/country";
    }

    @RequestMapping(params = "action=doSubmit") // action phase
    public void populateSite(javax.portlet.ActionRequest request, javax.portlet.ActionResponse response,
                             @ModelAttribute("countryMasterForm") CountryMasterForm countryMasterForm,
                             BindingResult result, Model model) {
        String command = countryMasterForm.getCommand();
        String mode = countryMasterForm.getMode();
        User user = (User) request.getAttribute(WebKeys.USER);
        countryMasterForm.getCountryM().setUpdatedBy(user.getUserId() + "");
        if (mode != null) {
            if (mode.equals("add")) {
                countryMasterForm.getCountryM().setCreatedBy(user.getUserId() + "");
                countryMasterForm.getCountryM().setCountryId(null);
                researchService.saveCountryM(countryMasterForm.getCountryM());
            } else if (mode.equals("edit")) {
                researchService.updateCountryM(countryMasterForm.getCountryM());
            } else if (mode.equals("deleteItems")) {
                if (countryMasterForm.getIds() != null && countryMasterForm.getIds().length > 0) {
                    researchService.deleteItemsCountryM(countryMasterForm.getIds());
                }
            } else {
            }
        }
        command = "list";
        //}
        response.setRenderParameter("action", command);
    }

    @RequestMapping(params = "action=delete")
    public void removeCountry(@RequestParam("countryId") Integer countryId,
                              ActionResponse response, Model model) {
        CountryM countryM = new CountryM();
        countryM.setCountryId(countryId);
        int recordCount = researchService.deleteCountryM(countryM);
        if (recordCount == -9)
            model.addAttribute(ServiceConstant.ERROR_MESSAGE_KEY, ServiceConstant.ERROR_CONSTRAINT_VIOLATION_MESSAGE_CODE);
        response.setRenderParameter("action", "list");
    }

    @ResourceMapping(value = "research_group_resource_get_byid")
    public void getById(@RequestParam("countryId") Integer countryId, ResourceRequest request,
                        ResourceResponse response) throws PortletException, IOException {
        //ObjectMapper mapper = new ObjectMapper();
        String id = request.getParameter("countryId");
        CountryM country = researchService.findCountryById(Integer.valueOf(countryId));
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        ResourceURL csvResource = response.createResourceURL();
        csvResource.setResourceID("research_group_resource_export");
        csvResource.setParameter("countryId", countryId + "");
        csvResource.setParameter("type", "csv");

        ResourceURL csvUploadResource = response.createResourceURL();
        csvUploadResource.setResourceID("research_group_resource_upload");
        csvUploadResource.setParameter("countryId", countryId + "");
        csvUploadResource.setParameter("type", "csv");

        ResourceURL xmlResource = response.createResourceURL();
        xmlResource.setResourceID("research_group_resource_export");
        xmlResource.setParameter("countryId", countryId + "");
        xmlResource.setParameter("type", "xml");

        ResourceURL xmlUploadResource = response.createResourceURL();
        xmlUploadResource.setResourceID("research_group_resource_upload");
        xmlUploadResource.setParameter("countryId", countryId + "");
        xmlUploadResource.setParameter("type", "xml");

        country.setCsvUploadResource(csvUploadResource.toString());
        country.setXmlUploadResource(xmlUploadResource.toString());
        country.setCsvResource(csvResource.toString());
        country.setXmlResource(xmlResource.toString());
        try {
            //mapper.writeValue(response.getWriter(), country);
            customObjectMapper.writeValue(response.getWriter(), country);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @ResourceMapping(value = "research_group_resource_export")
    public void loadXls(@RequestParam("countryId") Integer countryId, @RequestParam("type") String type, ResourceRequest request,
                        ResourceResponse response) throws PortletException, IOException {
        //ObjectMapper mapper = new ObjectMapper();
        String filename = "country";
        response.setCharacterEncoding("UTF-8");
        String contentType = "text/xml";
        CountryM country = researchService.findCountryById(Integer.valueOf(countryId));
        String content = "";
        if (type.equals("xml")) {
            filename = filename + ".xml";
            XStream xs = new XStream(new DomDriver());
            xs.processAnnotations(CountryM.class);
            content = xs.toXML(country);

        } else {
            filename = filename + ".csv";
            contentType = "text/csv";
            ColumnPositionMappingStrategy strat = new ColumnPositionMappingStrategy();
            strat.setType(CountryM.class);
            String[] columns = new String[]{"countryNameEng", "countryNameTh"}; // the fields to bind do in your JavaBean
            strat.setColumnMapping(columns);
            StringWriter sw = new StringWriter();
            try {
                List<CountryM> list = new ArrayList<CountryM>(1);
                list.add(country);
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
    public void resourceUpload(@RequestParam("countryId") Integer countryId, @RequestParam("type") String type, ResourceRequest request,
                               ResourceResponse response) throws PortletException, IOException {
        //ObjectMapper mapper = new ObjectMapper();
        String status = "success";
        UploadPortletRequest uploadRequest = PortalUtil
                .getUploadPortletRequest(request);
        String sourceFileName = uploadRequest.getFileName("import_" + type);
        File file = uploadRequest.getFile("import_" + type);
        CountryM country = researchService.findCountryById(Integer.valueOf(countryId));
        User user = (User) request.getAttribute(WebKeys.USER);

        CountryM model = new CountryM();
        BeanUtils.copyProperties(country, model);
        model.setUpdatedBy(user.getUserId() + "");
        if (type.equals("csv")) {
            HeaderColumnNameTranslateMappingStrategy<CountryM> beanStrategy = new HeaderColumnNameTranslateMappingStrategy<CountryM>();
            beanStrategy.setType(CountryM.class);

            Map<String, String> columnMapping = new HashMap<String, String>();
            columnMapping.put("countryNameEng", "countryNameEng");
            columnMapping.put("countryNameTh", "countryNameTh");

            //columnMapping.put("Salary", "salary");

            beanStrategy.setColumnMapping(columnMapping);

            CsvToBean<CountryM> csvToBean = new CsvToBean<CountryM>();
            CSVReader reader = null;
            try {
                reader = new CSVReader(new FileReader(file));
                List<CountryM> models = csvToBean.parse(beanStrategy, reader);
                if (models != null && models.size() > 0) {
                    CountryM model_return = models.get(0);
                    setPropertiesBean(model_return, model);

                }
            } catch (Exception ex) {
                ex.printStackTrace();
            } finally {
                reader.close();
            }

        } else if (type.equals("xml")) {
            XStream xs = new XStream(new DomDriver());
            xs.processAnnotations(CountryM.class);
            Object xtarget = xs.fromXML(file);

            if (xtarget != null) {
                CountryM model_return = (CountryM) xtarget;
                setPropertiesBean(model_return, model);
            }

        }
        if (model != null)
            researchService.updateCountryM(model);
        //model.setGroupTh(sourceFileName);
        try {
            customObjectMapper.writeValue(response.getWriter(), status);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private CountryM setPropertiesBean(CountryM source, CountryM target) {
        target.setCountryNameEng(source.getCountryNameEng());
        target.setCountryNameTh(source.getCountryNameTh());
		/*target.setGroupEng(source.getGroupEng());*/
        return target;
    }
}
