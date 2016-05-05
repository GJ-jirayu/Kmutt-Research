package th.ac.kmutt.research.portlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.net.ConnectException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.PortletPreferences;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.portlet.ResourceURL;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
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
import th.ac.kmutt.research.form.ReSearchProjectForm;
import th.ac.kmutt.research.mapper.CustomObjectMapper;
import th.ac.kmutt.research.model.FundingResourceM;
import th.ac.kmutt.research.model.OrganizationM;
import th.ac.kmutt.research.model.ResearchGroupM;
import th.ac.kmutt.research.model.ResearchProjectM;
import th.ac.kmutt.research.model.ResearchProjectResearcherM;
import th.ac.kmutt.research.service.ResearchService;
import th.ac.kmutt.research.utils.ImakeUtils;
import th.ac.kmutt.research.utils.TokenUtils;
import th.ac.kmutt.research.xstream.common.ImakeResultMessage;

import com.artofsolving.jodconverter.DocumentConverter;
import com.artofsolving.jodconverter.openoffice.connection.OpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.connection.SocketOpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.converter.OpenOfficeDocumentConverter;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.portlet.PortletResponseUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.User;
import com.liferay.portal.theme.ThemeDisplay;
import com.opencsv.bean.BeanToCsv;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

@Controller("researchProjectController")
@RequestMapping("VIEW")
@SessionAttributes({"researchProjectForm"})
public class ReSearchProjectController {
    private static final Logger logger = Logger
            .getLogger(ReSearchProjectController.class);
    private static ResourceBundle bundle;

    static {
        bundle = ResourceBundle.getBundle("config");
    }

    private String ATTACH_PATH = bundle.getString("downloadPath");

    private static SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
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
        final String[] ALLOWED_FIELDS = {"researchProjectM.researchProjectId", "researchProjectM.createdBy", "researchProjectM.createdDate",
                "researchProjectM.budgetYear", "researchProjectM.abstractTitle", "researchProjectM.updatedBy",
                "researchProjectM.updatedDate", "researchProjectM.axaptaId", "researchProjectM.detail",
                "researchProjectM.keyworkTitle", "researchProjectM.nrctCatelogry", "researchProjectM.objectiveTitle",
                "mode", "command", "keySearch",
                "researchProjectM.projectType", "researchProjectM.reference", "researchProjectM.engName",
                "researchProjectM.fundingResourceId", "researchProjectM.organizationId", "researchProjectM.phase",
                "researchProjectM.purposeBudget", "researchProjectM.submitBudget", "researchProjectM.remark", "researchProjectM.reportDuedate",
                "researchProjectM.researchGroupId", "researchProjectM.researchProjectCode", "researchProjectM.thaiName",
                "researchProjectM.docType", "endDate", "startDate", "reportDuedate", "reportSubmitDate"
                , "docsAssign", "pageNo", "paging.pageSize", "ids", "tab", "filter"};


        binder.setAllowedFields(ALLOWED_FIELDS);
    }

    @RequestMapping
    // default (action=list)
    public String listResearchProject(javax.portlet.RenderRequest request, Model model) {
        User user = (User) request.getAttribute(WebKeys.USER);

        ReSearchProjectForm researchProjectForm = null;
        if (!model.containsAttribute("researchProjectForm")) {
            researchProjectForm = new ReSearchProjectForm();
            model.addAttribute("researchProjectForm",
                    researchProjectForm);
        } else {
            researchProjectForm = (ReSearchProjectForm) model.asMap().get("researchProjectForm");
        }
        if (researchProjectForm.getTab() == null)
            researchProjectForm.setTab(ServiceConstant.TAB_ALL);
        if (researchProjectForm.getFilter() == null)
            researchProjectForm.setFilter(ServiceConstant.FILTERS[0]);
        //researchProjectForm.getResearchProjectM().setUserid();
        String keySearch = researchProjectForm.getKeySearch();
        ResearchProjectM researchProjectM = new ResearchProjectM();
        researchProjectM.setKeySearch(keySearch);
        researchProjectM.setUserid(user.getUserId() + "");

        researchProjectM.setPaging(researchProjectForm.getPaging());
        researchProjectM.getPaging().setPageNo(researchProjectForm.getPageNo());
        researchProjectM.setTab(researchProjectForm.getTab());
        researchProjectM.setFilter(researchProjectForm.getFilter());
        ImakeResultMessage imakeResultMessage = researchService
                .searchResearchProjectM(researchProjectM);
        model.addAttribute("researchProjects", imakeResultMessage.getResultListObj());
        //researchProjectForm.getPaging().setPageSize(ImakeUtils.PAGE_SIZE);
        researchProjectForm.setPageCount(ImakeUtils.calculatePage(researchProjectForm.getPaging().getPageSize(), Integer.parseInt(imakeResultMessage.getMaxRow())));
        int[] start_end = ImakeUtils.calculatePageStartEnd(researchProjectForm.getPageNo(), researchProjectForm.getPageCount());
        researchProjectForm.setPageStart(start_end[0]);
        researchProjectForm.setPageEnd(start_end[1]);
        return "docs/researchProjectList";
    }

    @RequestMapping(params = "action=add")
    // render phase
    public String showReSearchProjectForm(Model model) {
        // Used for the initial form as well as for redisplaying with errors.
        /*
		 * if (!model.containsAttribute("site")) { model.addAttribute("site",
		 * new PetSite()); }
		 */
        ReSearchProjectForm reSearchProjectForm = new ReSearchProjectForm();
        reSearchProjectForm.setMode("add");
        model.addAttribute("researchProjectForm",
                reSearchProjectForm);
        return "docs/researchProjectAddEditView";
    }

    @RequestMapping(params = "action=doSubmit") // action phase
    public void populateSite(javax.portlet.ActionRequest request, javax.portlet.ActionResponse response,
                             @ModelAttribute("researchProjectForm") ReSearchProjectForm researchProjectForm,
                             BindingResult result, Model model) {
        // new PetSiteValidator().validate(petSite, result);
		/*
		 * if (!result.hasErrors()) { this.petSites.put(petSite.getName(),
		 * petSite.getUrl()); status.setComplete();
		 * response.setRenderParameter("action", "list"); }
		 */
        String command = researchProjectForm.getCommand();
        String mode = researchProjectForm.getMode();


        User user = (User) request.getAttribute(WebKeys.USER);
        researchProjectForm.getResearchProjectM().setUpdatedBy(user.getUserId() + "");
        //if(command.equals("doSaveFAQ")){
        String endDate = researchProjectForm.getEndDate();
        String startDate = researchProjectForm.getStartDate();
        String reportDuedate = researchProjectForm.getReportDuedate();
        String reportSubmitDate = researchProjectForm.getReportSubmitDate();

        if (startDate != null && startDate.trim().length() > 0)
            try {
                researchProjectForm.getResearchProjectM().setStartDate(format1.parse(startDate));
            } catch (ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        if (endDate != null && endDate.trim().length() > 0)
            try {
                researchProjectForm.getResearchProjectM().setEndDate(format1.parse(endDate));
            } catch (ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        if (reportDuedate != null && reportDuedate.trim().length() > 0)
            try {
                researchProjectForm.getResearchProjectM().setReportDuedate(format1.parse(reportDuedate));
            } catch (ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        if (reportSubmitDate != null && reportSubmitDate.trim().length() > 0)
            try {
                researchProjectForm.getResearchProjectM().setReportSubmitDate(format1.parse(reportSubmitDate));
            } catch (ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        Integer refId = null;
        command = "addEditView";
        if (mode != null) {
            if (mode.equals("add") || mode.equals("copy")) {
                researchProjectForm.getResearchProjectM().setCreatedBy(user.getUserId() + "");
                researchProjectForm.getResearchProjectM().setResearchProjectId(null);
                refId = researchService.saveResearchProjectM(researchProjectForm.getResearchProjectM());
            } else if (mode.equals("edit")) {
                refId = researchService.updateResearchProjectM(researchProjectForm.getResearchProjectM());
            } else if (mode.equals("deleteItems")) {
                if (researchProjectForm.getIds() != null && researchProjectForm.getIds().length > 0) {
                    researchService.deleteItemsResearchProjectM(researchProjectForm.getIds());
                }
                command = "list";
            } else {
                command = "list";
            }
        }

        response.setRenderParameter("action", command);
        if (mode.equals("add") || mode.equals("edit") || mode.equals("copy")) {
            response.setRenderParameter("researchProjectId", refId + "");
            response.setRenderParameter("mode", "edit");
        }

    }

    @RequestMapping(params = "action=delete")
    public void removeSite(@RequestParam("researchProjectId") Integer researchProjectId,
                           ActionResponse response, Model model) {
        ResearchProjectM researchProjectM = new ResearchProjectM();
        researchProjectM.setResearchProjectId(researchProjectId);
        //researchProjectM.setUpdateType("flag");
        //researchProjectM.setFlag("0");
        //researchService.updateFlagResearchProjectM(researchProjectM);
        int recordCount = researchService.deleteResearchProjectM(researchProjectM);
        if (recordCount == -9)
            model.addAttribute(ServiceConstant.ERROR_MESSAGE_KEY, ServiceConstant.ERROR_CONSTRAINT_VIOLATION_MESSAGE_CODE);
        response.setRenderParameter("action", "list");
    }

    @RequestMapping(params = "action=undoItem")
    public void undoItem(@RequestParam("researchProjectId") Integer researchProjectId,
                         ActionResponse response) {
        ResearchProjectM researchProjectM = new ResearchProjectM();
        researchProjectM.setResearchProjectId(researchProjectId);
        researchProjectM.setUpdateType(ServiceConstant.UPDATE_TYEP_FLAG);
        researchProjectM.setFlag(ServiceConstant.FLAG_ACTIVE);
        researchService.updateFlagResearchProjectM(researchProjectM);
        //researchService.deleteResearchProjectM(researchProjectM);
        response.setRenderParameter("action", "list");
    }

    @RequestMapping(params = "action=addEditView")  // render phase
    public String addEditViewUser(javax.portlet.RenderRequest request, @RequestParam(value = "researchProjectId", required = false) Integer researchProjectId, @RequestParam("mode") String mode,
                                  RenderResponse response, Model model) {
        model.addAttribute("mode", mode);
        ReSearchProjectForm researchProjectForm = null;
        if (!model.containsAttribute("researchProjectForm")) {
            researchProjectForm = new ReSearchProjectForm();
            model.addAttribute("researchProjectForm",
                    researchProjectForm);
        } else {
            researchProjectForm = (ReSearchProjectForm) model.asMap().get("researchProjectForm");
        }

        ResearchProjectM researchProjectM = null;
        Date endDate = null;
        Date startDate = null;
        Date reportDuedate = null;
        Date reportSubmitDate = null;
        User user = (User) request.getAttribute(WebKeys.USER);
        String userid = null;
        if (user != null)
            userid = user.getUserId() + "";

        if (mode.equals("add")) {
            researchProjectForm = new ReSearchProjectForm();
            researchProjectM = new ResearchProjectM();
        } else if (mode.equals("edit")) {
            researchProjectM = researchService.findResearchProjectById(researchProjectId, userid);
            if (researchProjectM.getFundingResourceId() != null) {
                FundingResourceM fundingResourceM = researchService.findFundingResourceById(researchProjectM.getFundingResourceId());
                if (fundingResourceM != null) {
                    researchProjectM.setFundingResourceShow(fundingResourceM.getFrNameThai());
                }
            }
            if (researchProjectM.getOrganizationId() != null) {
                OrganizationM organizationM = researchService.findOrganizationById(researchProjectM.getOrganizationId());
                if (organizationM != null) {
                    researchProjectM.setOrganizationShow(organizationM.getOrgName());
                }
            }
            if (researchProjectM.getResearchGroupId() != null) {
                ResearchGroupM researchGroupM = researchService.findResearchGroupById(researchProjectM.getResearchGroupId());
                if (researchGroupM != null) {
                    researchProjectM.setResearchGroupShow(researchGroupM.getGroupTh());
                }
            }
            endDate = researchProjectM.getEndDate();
            startDate = researchProjectM.getStartDate();
            reportDuedate = researchProjectM.getReportDuedate();
            reportSubmitDate = researchProjectM.getReportSubmitDate();
            //researchProjectM.getStartDate();
            if (startDate != null)
                researchProjectForm.setStartDate(format1.format(startDate));
            if (endDate != null)
                researchProjectForm.setEndDate(format1.format(endDate));
            if (reportDuedate != null)
                researchProjectForm.setReportDuedate(format1.format(reportDuedate));
            if (reportSubmitDate != null)
                researchProjectForm.setReportSubmitDate(format1.format(reportSubmitDate));
        } else if (mode.equals("copy")) {
            researchProjectM = researchService.findResearchProjectById(researchProjectId, userid);
            if (researchProjectM.getFundingResourceId() != null) {
                FundingResourceM fundingResourceM = researchService.findFundingResourceById(researchProjectM.getFundingResourceId());
                if (fundingResourceM != null) {
                    researchProjectM.setFundingResourceShow(fundingResourceM.getFrNameThai());
                }
            }
            if (researchProjectM.getOrganizationId() != null) {
                OrganizationM organizationM = researchService.findOrganizationById(researchProjectM.getOrganizationId());
                if (organizationM != null) {
                    researchProjectM.setOrganizationShow(organizationM.getOrgName());
                }
            }
            if (researchProjectM.getResearchGroupId() != null) {
                ResearchGroupM researchGroupM = researchService.findResearchGroupById(researchProjectM.getResearchGroupId());
                if (researchGroupM != null) {
                    researchProjectM.setResearchGroupShow(researchGroupM.getGroupTh());
                }
            }
            //reset for copy
            researchProjectM.setDocuments(null);

            researchProjectM.setPayments(null);

            researchProjectM.setProgresList(null);

            researchProjectM.setResearchers(null);

            researchProjectM.setWithdraws(null);

            researchProjectM.setDocAssignMappings(null);

            endDate = researchProjectM.getEndDate();
            startDate = researchProjectM.getStartDate();
            reportDuedate = researchProjectM.getReportDuedate();
            reportSubmitDate = researchProjectM.getReportSubmitDate();
            //researchProjectM.getStartDate();
            if (startDate != null)
                researchProjectForm.setStartDate(format1.format(startDate));
            if (endDate != null)
                researchProjectForm.setEndDate(format1.format(endDate));
            if (reportDuedate != null)
                researchProjectForm.setReportDuedate(format1.format(reportDuedate));
            if (reportSubmitDate != null)
                researchProjectForm.setReportSubmitDate(format1.format(reportSubmitDate));
        }

        researchProjectForm.setMode(mode);
        researchProjectForm.setResearchProjectM(researchProjectM);
        model.addAttribute("researchProjectForm",
                researchProjectForm);
        return "docs/researchProjectAddEditView";

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
    }

    @ResourceMapping(value = "research_group_resource_get_byid")
    public void getById(@RequestParam("researchProjectId") Integer researchProjectId, ResourceRequest request,
                        ResourceResponse response) throws PortletException, IOException {
        //ObjectMapper mapper = new ObjectMapper();
        String id = request.getParameter("researchProjectId");
        ResearchProjectM researchProject = researchService.findResearchProjectById(Integer.valueOf(researchProjectId), null);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");

        ResourceURL proofParticipationResource = response.createResourceURL();
        proofParticipationResource.setResourceID("research_project_resource_download");

        //proofParticipationResource.setParameter("researchProjectId", researchProjectId+"");
        proofParticipationResource.setParameter("type", "Proof_Participation");

        ResourceURL proofAcademicResource = response.createResourceURL();
        proofAcademicResource.setResourceID("research_project_resource_download");
        //proofAcademicResource.setParameter("researchProjectId", researchProjectId+"");
        proofAcademicResource.setParameter("type", "Proof_Academic");

        ResourceURL csvResource = response.createResourceURL();
        csvResource.setResourceID("research_group_resource_export");
        csvResource.setParameter("researchProjectId", researchProjectId + "");
        csvResource.setParameter("type", "csv");

        ResourceURL xmlResource = response.createResourceURL();
        xmlResource.setResourceID("research_group_resource_export");
        xmlResource.setParameter("researchProjectId", researchProjectId + "");
        xmlResource.setParameter("type", "xml");

        researchProject.setCsvResource(csvResource.toString());
        researchProject.setXmlResource(xmlResource.toString());

        researchProject.setProofAcademicResource(proofAcademicResource.toString());
        researchProject.setProofParticipationResource(proofParticipationResource.toString());

        try {
            //mapper.writeValue(response.getWriter(), researchProject);
            customObjectMapper.writeValue(response.getWriter(), researchProject);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @ResourceMapping(value = "research_group_resource_export")
    public void loadXls(@RequestParam("researchProjectId") Integer researchProjectId, @RequestParam("type") String type, ResourceRequest request,
                        ResourceResponse response) throws PortletException, IOException {
        //ObjectMapper mapper = new ObjectMapper();
        String filename = "researchProject";
        response.setCharacterEncoding("UTF-8");
        String contentType = "text/xml";
        ResearchProjectM researchProject = researchService.findResearchProjectById(Integer.valueOf(researchProjectId), null);
        String content = "";
        if (type.equals("xml")) {
            filename = filename + ".xml";
            XStream xs = new XStream(new DomDriver());
            content = xs.toXML(researchProject);

        } else {
            filename = filename + ".csv";
            contentType = "text/csv";
            ColumnPositionMappingStrategy strat = new ColumnPositionMappingStrategy();
            strat.setType(ResearchProjectM.class);
            String[] columns = new String[]{"researchProjectId", "groupCode", "groupTh"}; // the fields to bind do in your JavaBean
            strat.setColumnMapping(columns);
            StringWriter sw = new StringWriter();
            try {
                List<ResearchProjectM> list = new ArrayList<ResearchProjectM>(1);
                list.add(researchProject);
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

    @ResourceMapping(value = "research_project_resource_download")
    public void research_project_resource_download(@RequestParam("researchProjectId") Integer researchProjectId, @RequestParam("type") String type, ResourceRequest request,
                                                   ResourceResponse response) throws PortletException, IOException {
        //ObjectMapper mapper = new ObjectMapper();
        ResearchProjectM researchProject = researchService.findResearchProjectById(Integer.valueOf(researchProjectId), null);
        FileInputStream fin = null;
        try {
            fin = new FileInputStream(new File(ATTACH_PATH + type + ".xls"));
        } catch (FileNotFoundException e2) {
            // TODO Auto-generated catch block
            e2.printStackTrace();
        }
        ThemeDisplay themeDisplay = (ThemeDisplay) request
                .getAttribute(WebKeys.THEME_DISPLAY);
        //HSSFWorkbook wb = new HSSFWorkbook(inputFileUpload.getInputStream());
        HSSFWorkbook wb = null;
        try {
            wb = new HSSFWorkbook(fin);
            Sheet sheet1 = wb.getSheetAt(0); // getImage Config
            Row row_code = sheet1.getRow(4);
            Cell cell = row_code.getCell(2);
            cell.setCellValue(researchProject.getThaiName());
            //cell.setCellValue("TEST");

            row_code = sheet1.getRow(8);
            cell = row_code.getCell(3);
            if (researchProject.getResearchers() != null && researchProject.getResearchers().size() > 0) {
                int size = researchProject.getResearchers().size();
                List<ResearchProjectResearcherM> researcherms = researchProject.getResearchers();
                cell.setCellValue(size + "");
                int row_start = 11;
                int column_name = 0;
                int column_percent = 5;
                for (ResearchProjectResearcherM researchProjectResearcherm : researcherms) {
                    // set name
                    row_code = sheet1.getRow(row_start);
                    cell = row_code.getCell(column_name);
                    cell.setCellValue(researchProjectResearcherm.getResearcherName());

                    // set percent
                    cell = row_code.getCell(column_percent);
                    cell.setCellValue(researchProjectResearcherm.getWorkLoadRatio());
                    row_start++;
                }
            }

        } catch (IOException e2) {
            // TODO Auto-generated catch block
            e2.printStackTrace();
        } finally {
            fin.close();
        }
        //wb.getCreationHelper().createFormulaEvaluator().evaluateAll();
        //byte[] xxx=wb.getBytes();
        //String filePath=ATTACH_PATH+type+".xls";
        String token = TokenUtils.genToken(20);
        String outputFile_pdf = ATTACH_PATH + token + ".pdf";
        String outputFile_xls = ATTACH_PATH + token + ".xls";
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(outputFile_xls);
            wb.write(fos);
            //fos.write(xxx);
        } catch (FileNotFoundException e2) {
            // TODO Auto-generated catch block
            e2.printStackTrace();
        } finally {
            fos.close();
            wb.close();
        }


        // connect to an OpenOffice.org instance running on port 8100
        OpenOfficeConnection connection = new SocketOpenOfficeConnection(8100);
        try {
            connection.connect();
        } catch (ConnectException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        //	  ByteArrayOutputStream bOutput = new ByteArrayOutputStream();
        // convert
        //Proof_of_Participation_Form_v1
        //String xls="xx.xls";


        DocumentConverter converter = new OpenOfficeDocumentConverter(connection);
        converter.convert(new File(outputFile_xls), new File(outputFile_pdf));


        // close the connection
        connection.disconnect();

        String filename = type;//"researchProject";
        response.setCharacterEncoding("UTF-8");
        String contentType = "application/pdf";
        byte[] content = IOUtils.toByteArray(new FileInputStream(outputFile_pdf));
        // ResearchProjectM researchProject=researchService.findResearchProjectById(Integer.valueOf(researchProjectId));
        //String content="";

        String contentDispositionType = "attachment; filename=\"" + type + "\"";
        Path path = Paths.get(ATTACH_PATH, token + ".pdf");
        Path path2 = Paths.get(ATTACH_PATH, token + ".xls");

        try {
            Files.deleteIfExists(path);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {
            Files.deleteIfExists(path2);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        PortletResponseUtil.sendFile(request, response, filename, content, contentType, contentDispositionType);

    }


}