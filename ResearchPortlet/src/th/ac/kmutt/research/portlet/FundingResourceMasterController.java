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
import th.ac.kmutt.research.form.FundingResourceMasterForm;
import th.ac.kmutt.research.mapper.CustomObjectMapper;
import th.ac.kmutt.research.model.FundingResourceM;
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

@Controller("fundingResourceMasterController")
@RequestMapping("VIEW")
@SessionAttributes({"fundingResourceMasterForm"})
public class FundingResourceMasterController {
    private static final Logger logger = Logger
            .getLogger(FundingResourceMasterController.class);
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
        final String[] ALLOWED_FIELDS = {"fundingResourceM.fundingResourceId", "fundingResourceM.createdBy", "fundingResourceM.createdDate",
                "fundingResourceM.fundingResourceType.fundingResourceTypeId", "fundingResourceM.fundingResourceType.frtCode", "fundingResourceM.fundingResourceCode", "fundingResourceM.frCode", "fundingResourceM.updatedBy",
                "fundingResourceM.updatedDate", "fundingResourceM.frNameThai", "fundingResourceM.frNameEng", "fundingResourceM.frShortName",
                "fundingResourceM.country.countryId", "mode", "command", "keySearch", "pageNo", "paging.pageSize", "ids", "tab", "filter"};

        binder.setAllowedFields(ALLOWED_FIELDS);
    }

    @RequestMapping
    // default (action=list)
    public String listFundingResource(Model model) {
        FundingResourceMasterForm fundingResourceMasterForm = null;
        if (!model.containsAttribute("fundingResourceMasterForm")) {
            fundingResourceMasterForm = new FundingResourceMasterForm();
            model.addAttribute("fundingResourceMasterForm",
                    fundingResourceMasterForm);
        } else {
            fundingResourceMasterForm = (FundingResourceMasterForm) model.asMap().get("fundingResourceMasterForm");
        }
        String keySearch = fundingResourceMasterForm.getKeySearch();
        FundingResourceM fundingResourceM = new FundingResourceM();
        fundingResourceM.setKeySearch(keySearch);
        fundingResourceM.setPaging(fundingResourceMasterForm.getPaging());
        fundingResourceM.getPaging().setPageNo(fundingResourceMasterForm.getPageNo());

        ImakeResultMessage imakeResultMessage = researchService
                .searchFundingResourceM(fundingResourceM);
        model.addAttribute("fundingResources", imakeResultMessage.getResultListObj());
        //fundingResourceMasterForm.getPaging().setPageSize(ImakeUtils.PAGE_SIZE);
        fundingResourceMasterForm.setPageCount(ImakeUtils.calculatePage(fundingResourceMasterForm.getPaging().getPageSize(), Integer.parseInt(imakeResultMessage.getMaxRow())));
        int[] start_end = ImakeUtils.calculatePageStartEnd(fundingResourceMasterForm.getPageNo(), fundingResourceMasterForm.getPageCount());
        fundingResourceMasterForm.setPageStart(start_end[0]);
        fundingResourceMasterForm.setPageEnd(start_end[1]);
        return "master/fundingResource";
    }

    @RequestMapping(params = "action=add")
    // render phase
    public String showFundingResourceMasterForm(Model model) {
        // Used for the initial form as well as for redisplaying with errors.
        /*
		 * if (!model.containsAttribute("site")) { model.addAttribute("site",
		 * new PetSite()); }
		 */
        model.addAttribute("fundingResourceMasterForm",
                new FundingResourceMasterForm());
        return "master/fundingResource";
    }

    @RequestMapping(params = "action=doSubmit") // action phase
    public void populateSite(javax.portlet.ActionRequest request, javax.portlet.ActionResponse response,
                             @ModelAttribute("fundingResourceMasterForm") FundingResourceMasterForm fundingResourceMasterForm,
                             BindingResult result, Model model) {
        // new PetSiteValidator().validate(petSite, result);
		/*
		 * if (!result.hasErrors()) { this.petSites.put(petSite.getName(),
		 * petSite.getUrl()); status.setComplete();
		 * response.setRenderParameter("action", "list"); }
		 */
        String command = fundingResourceMasterForm.getCommand();
        String mode = fundingResourceMasterForm.getMode();
        User user = (User) request.getAttribute(WebKeys.USER);
        fundingResourceMasterForm.getFundingResourceM().setUpdatedBy(user.getUserId() + "");
        //if(command.equals("doSaveFAQ")){
        if (mode != null) {
            if (mode.equals("add")) {
                fundingResourceMasterForm.getFundingResourceM().setCreatedBy(user.getUserId() + "");
                fundingResourceMasterForm.getFundingResourceM().setFundingResourceId(null);
                researchService.saveFundingResourceM(fundingResourceMasterForm.getFundingResourceM());
            } else if (mode.equals("edit")) {
                researchService.updateFundingResourceM(fundingResourceMasterForm.getFundingResourceM());
            } else if (mode.equals("deleteItems")) {
                if (fundingResourceMasterForm.getIds() != null && fundingResourceMasterForm.getIds().length > 0) {
                    researchService.deleteItemsFundingResourceM(fundingResourceMasterForm.getIds());
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
    public void removeSite(@RequestParam("fundingResourceId") Integer fundingResourceId,
                           ActionResponse response, Model model) {
        FundingResourceM fundingResourceM = new FundingResourceM();
        fundingResourceM.setFundingResourceId(fundingResourceId);
        int recordCount = researchService.deleteFundingResourceM(fundingResourceM);
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
    public void getById(@RequestParam("fundingResourceId") Integer fundingResourceId, ResourceRequest request,
                        ResourceResponse response) throws PortletException, IOException {
        //ObjectMapper mapper = new ObjectMapper();
        String id = request.getParameter("fundingResourceId");
        FundingResourceM fundingResource = researchService.findFundingResourceById(Integer.valueOf(fundingResourceId));
        response.setCharacterEncoding("UTF-8");
        ResourceURL csvResource = response.createResourceURL();
        csvResource.setResourceID("research_group_resource_export");
        csvResource.setParameter("fundingResourceId", fundingResourceId + "");
        csvResource.setParameter("type", "csv");

        ResourceURL csvUploadResource = response.createResourceURL();
        csvUploadResource.setResourceID("research_group_resource_upload");
        csvUploadResource.setParameter("fundingResourceId", fundingResourceId + "");
        csvUploadResource.setParameter("type", "csv");

        ResourceURL xmlResource = response.createResourceURL();
        xmlResource.setResourceID("research_group_resource_export");
        xmlResource.setParameter("fundingResourceId", fundingResourceId + "");
        xmlResource.setParameter("type", "xml");

        ResourceURL xmlUploadResource = response.createResourceURL();
        xmlUploadResource.setResourceID("research_group_resource_upload");
        xmlUploadResource.setParameter("fundingResourceId", fundingResourceId + "");
        xmlUploadResource.setParameter("type", "xml");

        fundingResource.setCsvUploadResource(csvUploadResource.toString());
        fundingResource.setXmlUploadResource(xmlUploadResource.toString());
        fundingResource.setCsvResource(csvResource.toString());
        fundingResource.setXmlResource(xmlResource.toString());
        response.setContentType("application/json");
        try {
            //mapper.writeValue(response.getWriter(), fundingResource);
            customObjectMapper.writeValue(response.getWriter(), fundingResource);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @ResourceMapping(value = "research_group_resource_export")
    public void loadXls(@RequestParam("fundingResourceId") Integer fundingResourceId, @RequestParam("type") String type, ResourceRequest request,
                        ResourceResponse response) throws PortletException, IOException {
        //ObjectMapper mapper = new ObjectMapper();
        String filename = "fundingResource";
        response.setCharacterEncoding("UTF-8");
        String contentType = "text/xml";
        FundingResourceM fundingResource = researchService.findFundingResourceById(Integer.valueOf(fundingResourceId));
        String content = "";
        if (type.equals("xml")) {
            filename = filename + ".xml";
            XStream xs = new XStream(new DomDriver());
            xs.processAnnotations(FundingResourceM.class);
            content = xs.toXML(fundingResource);

        } else {
            filename = filename + ".csv";
            contentType = "text/csv";
            ColumnPositionMappingStrategy strat = new ColumnPositionMappingStrategy();
            strat.setType(FundingResourceM.class);
            String[] columns = new String[]{"frNameEng", "frNameThai", "frShortName"}; // the fields to bind do in your JavaBean
            strat.setColumnMapping(columns);
            StringWriter sw = new StringWriter();
            try {
                List<FundingResourceM> list = new ArrayList<FundingResourceM>(1);
                list.add(fundingResource);
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
    public void resourceUpload(@RequestParam("fundingResourceId") Integer fundingResourceId, @RequestParam("type") String type, ResourceRequest request,
                               ResourceResponse response) throws PortletException, IOException {
        //ObjectMapper mapper = new ObjectMapper();
        String status = "success";
        UploadPortletRequest uploadRequest = PortalUtil
                .getUploadPortletRequest(request);
        String sourceFileName = uploadRequest.getFileName("import_" + type);
        File file = uploadRequest.getFile("import_" + type);
        FundingResourceM fundingResource = researchService.findFundingResourceById(Integer.valueOf(fundingResourceId));
        User user = (User) request.getAttribute(WebKeys.USER);

        FundingResourceM model = new FundingResourceM();
        BeanUtils.copyProperties(fundingResource, model);
        model.setUpdatedBy(user.getUserId() + "");
        if (type.equals("csv")) {
            HeaderColumnNameTranslateMappingStrategy<FundingResourceM> beanStrategy = new HeaderColumnNameTranslateMappingStrategy<FundingResourceM>();
            beanStrategy.setType(FundingResourceM.class);

            Map<String, String> columnMapping = new HashMap<String, String>();
            columnMapping.put("frNameEng", "frNameEng");
            columnMapping.put("frNameThai", "frNameThai");
            columnMapping.put("frShortName", "frShortName");
            //columnMapping.put("Salary", "salary");

            beanStrategy.setColumnMapping(columnMapping);

            CsvToBean<FundingResourceM> csvToBean = new CsvToBean<FundingResourceM>();
            CSVReader reader = null;
            try {
                reader = new CSVReader(new FileReader(file));
                List<FundingResourceM> models = csvToBean.parse(beanStrategy, reader);
                if (models != null && models.size() > 0) {
                    FundingResourceM model_return = models.get(0);
                    setPropertiesBean(model_return, model);

                }
            } catch (Exception ex) {
                ex.printStackTrace();
            } finally {
                reader.close();
            }

        } else if (type.equals("xml")) {
            XStream xs = new XStream(new DomDriver());
            xs.processAnnotations(FundingResourceM.class);
            Object xtarget = xs.fromXML(file);

            if (xtarget != null) {
                FundingResourceM model_return = (FundingResourceM) xtarget;
                setPropertiesBean(model_return, model);
            }

        }
        if (model != null)
            researchService.updateFundingResourceM(model);
        //model.setGroupTh(sourceFileName);
        try {
            customObjectMapper.writeValue(response.getWriter(), status);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private FundingResourceM setPropertiesBean(FundingResourceM source, FundingResourceM target) {
        target.setFrNameEng(source.getFrNameEng());
        target.setFrNameThai(source.getFrNameThai());
        target.setFrShortName(source.getFrShortName());
        return target;
    }

}
