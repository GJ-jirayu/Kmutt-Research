package th.ac.kmutt.research.portlet;

import java.io.IOException;
import java.io.StringWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.PortletPreferences;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.portlet.ResourceURL;

import org.apache.log4j.Logger;
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
import th.ac.kmutt.research.form.PatentForm;
import th.ac.kmutt.research.mapper.CustomObjectMapper;
import th.ac.kmutt.research.model.OrganizationM;
import th.ac.kmutt.research.model.PatentM;
import th.ac.kmutt.research.model.ResearchGroupM;
import th.ac.kmutt.research.model.ResearchProjectM;
import th.ac.kmutt.research.service.ResearchService;
import th.ac.kmutt.research.utils.ImakeUtils;
import th.ac.kmutt.research.xstream.common.ImakeResultMessage;

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

@Controller("patentController")
@RequestMapping("VIEW")
@SessionAttributes({"patentForm"})
public class PatentController {
    private static final Logger logger = Logger
            .getLogger(PatentController.class);
    @Autowired
    @Qualifier("researchServiceWSImpl")
    private ResearchService researchService;
    private static SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
    @Autowired
    private CustomObjectMapper customObjectMapper;

    @InitBinder
    public void initBinder(PortletRequestDataBinder binder, PortletPreferences preferences) {
        logger.debug("initBinder");
        binder.registerCustomEditor(byte[].class, new ByteArrayMultipartFileEditor());
        //String a[] = new String[]{"ntcfaq.nfaqName"};
        final String[] ALLOWED_FIELDS = {"patentM.inventionId", "patentM.createdBy", "patentM.createdDate",
                "patentM.inventionCode", "patentM.announcementDate", "patentM.updatedBy",
                "patentM.updatedDate", "patentM.announcementPayDate", "patentM.budgetYear",
                "patentM.docType", "patentM.engName", "patentM.integerernalOrganizationId",
                "patentM.inventionType", "patentM.patentDate", "patentM.patentNo",
                "patentM.payDate", "patentM.proposeBookDate", "patentM.proposeDate",
                "patentM.proposeNo", "patentM.receiveDate", "patentM.remark",
                "patentM.researchGroup", "patentM.researchProjectId", "patentM.result",
                "patentM.rpoposeType", "patentM.thaiName", "patentM.verifyDate",
                "proposeDate", "verifyDate", "announcementDate", "announcementPayDate", "proposeBookDate", "receiveDate", "patentDate", "payDate",
                "mode", "command", "keySearch"
                , "docsAssign", "pageNo", "paging.pageSize", "ids", "tab", "filter"};


        binder.setAllowedFields(ALLOWED_FIELDS);
    }

    @RequestMapping
    // default (action=list)
    public String listPatent(javax.portlet.RenderRequest request, Model model) {
        User user = (User) request.getAttribute(WebKeys.USER);
        PatentForm patentForm = null;
        if (!model.containsAttribute("patentForm")) {
            patentForm = new PatentForm();
            model.addAttribute("patentForm",
                    patentForm);
        } else {
            patentForm = (PatentForm) model.asMap().get("patentForm");
        }
        String keySearch = patentForm.getKeySearch();
        PatentM patentM = new PatentM();
        patentM.setKeySearch(keySearch);
        patentM.setPaging(patentForm.getPaging());
        patentM.getPaging().setPageNo(patentForm.getPageNo());
        patentM.setUserid(user.getUserId() + "");
        if (patentForm.getTab() == null)
            patentForm.setTab(ServiceConstant.TAB_ALL);
        if (patentForm.getFilter() == null)
            patentForm.setFilter(ServiceConstant.FILTERS[0]);
        patentM.setTab(patentForm.getTab());
        patentM.setFilter(patentForm.getFilter());

        ImakeResultMessage imakeResultMessage = researchService
                .searchPatentM(patentM);
        model.addAttribute("patents", imakeResultMessage.getResultListObj());
        //patentForm.getPaging().setPageSize(ImakeUtils.PAGE_SIZE);
        patentForm.setPageCount(ImakeUtils.calculatePage(patentForm.getPaging().getPageSize(), Integer.parseInt(imakeResultMessage.getMaxRow())));
        int[] start_end = ImakeUtils.calculatePageStartEnd(patentForm.getPageNo(), patentForm.getPageCount());
        patentForm.setPageStart(start_end[0]);
        patentForm.setPageEnd(start_end[1]);
        return "docs/researchPatentList";
    }

    @RequestMapping(params = "action=add")
    // render phase
    public String showPatentForm(Model model) {
        // Used for the initial form as well as for redisplaying with errors.
        /*
		 * if (!model.containsAttribute("site")) { model.addAttribute("site",
		 * new PetSite()); }
		 */
        PatentForm patentForm = new PatentForm();
        patentForm.setMode("add");
        model.addAttribute("patentForm",
                patentForm);
        return "docs/researchPatentListAddEditView";
    }

    @RequestMapping(params = "action=doSubmit") // action phase
    public void populateSite(javax.portlet.ActionRequest request, javax.portlet.ActionResponse response,
                             @ModelAttribute("patentForm") PatentForm patentForm,
                             BindingResult result, Model model) {
        // new PetSiteValidator().validate(petSite, result);
		/*
		 * if (!result.hasErrors()) { this.petSites.put(petSite.getName(),
		 * petSite.getUrl()); status.setComplete();
		 * response.setRenderParameter("action", "list"); }
		 */
        String command = patentForm.getCommand();
        String mode = patentForm.getMode();
        User user = (User) request.getAttribute(WebKeys.USER);
        patentForm.getPatentM().setUpdatedBy(user.getUserId() + "");
        String proposeDate = patentForm.getProposeDate();
        String verifyDate = patentForm.getVerifyDate();
        String announcementDate = patentForm.getAnnouncementDate();
        String announcementPayDate = patentForm.getAnnouncementPayDate();
        String proposeBookDate = patentForm.getProposeBookDate();
        String receiveDate = patentForm.getReceiveDate();
        String patentDate = patentForm.getPatentDate();
        String payDate = patentForm.getPayDate();

        if (proposeDate != null && proposeDate.trim().length() > 0)
            try {
                patentForm.getPatentM().setProposeDate(format1.parse(proposeDate));
            } catch (ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        if (verifyDate != null && verifyDate.trim().length() > 0)
            try {
                patentForm.getPatentM().setVerifyDate(format1.parse(verifyDate));
            } catch (ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        if (announcementDate != null && announcementDate.trim().length() > 0)
            try {
                patentForm.getPatentM().setAnnouncementDate(format1.parse(announcementDate));
            } catch (ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        if (announcementPayDate != null && announcementPayDate.trim().length() > 0)
            try {
                patentForm.getPatentM().setAnnouncementPayDate(format1.parse(announcementPayDate));
            } catch (ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        if (proposeBookDate != null && proposeBookDate.trim().length() > 0)
            try {
                patentForm.getPatentM().setProposeBookDate(format1.parse(proposeBookDate));
            } catch (ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        if (receiveDate != null && receiveDate.trim().length() > 0)
            try {
                patentForm.getPatentM().setReceiveDate(format1.parse(receiveDate));
            } catch (ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        if (patentDate != null && patentDate.trim().length() > 0)
            try {
                patentForm.getPatentM().setPatentDate(format1.parse(patentDate));
            } catch (ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        if (payDate != null && payDate.trim().length() > 0)
            try {
                patentForm.getPatentM().setPayDate(format1.parse(payDate));
            } catch (ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        //String docsAssign=patentForm.getDocsAssign();
        command = "addEditView";
        Integer refId = null;
        if (mode != null) {
            if (mode.equals("add") || mode.equals("copy")) {
                patentForm.getPatentM().setCreatedBy(user.getUserId() + "");
                patentForm.getPatentM().setResearchProjectId(null);
                patentForm.getPatentM().setInventionId(null);
                refId = researchService.savePatentM(patentForm.getPatentM());
            } else if (mode.equals("edit")) {
                refId = researchService.updatePatentM(patentForm.getPatentM());
            } else if (mode.equals("deleteItems")) {
                if (patentForm.getIds() != null && patentForm.getIds().length > 0) {
                    researchService.deleteItemsPatentM(patentForm.getIds());
                }
                command = "list";
            } else {
                command = "list";
            }
				/*if(refId!=null)
					if(docsAssign!=null && docsAssign.trim().length()>0){
						DocAssignMappingM mapping =new DocAssignMappingM();
						mapping.setRefId(refId);
						mapping.setRefType("PATENT");
						mapping.setUserId(docsAssign);
						researchService.saveDocAssignMapping(mapping);
					}	*/
        }

        //}
        response.setRenderParameter("action", command);
        if (mode.equals("add") || mode.equals("edit") || mode.equals("copy")) {
            response.setRenderParameter("inventionId", refId + "");
            response.setRenderParameter("mode", "edit");
        }
    }

    @RequestMapping(params = "action=delete")
    public void removeSite(@RequestParam("inventionId") Integer inventionId,
                           ActionResponse response) {
        PatentM patentM = new PatentM();
        patentM.setInventionId(inventionId);
        //researchService.deletePatentM(patentM);
		/*patentM.setFlag("0");
		patentM.setUpdateType("flag");*/
        //researchService.updateFlagPatent(patentM);
        researchService.deletePatentM(patentM);
        response.setRenderParameter("action", "list");
    }

    @RequestMapping(params = "action=undoItem")
    public void undoItem(@RequestParam("inventionId") Integer inventionId,
                         ActionResponse response) {
        PatentM patentM = new PatentM();
        patentM.setInventionId(inventionId);
        patentM.setUpdateType(ServiceConstant.UPDATE_TYEP_FLAG);
        patentM.setFlag(ServiceConstant.FLAG_ACTIVE);
        researchService.updateFlagPatent(patentM);
        //researchService.deleteResearchProjectM(researchProjectM);
        response.setRenderParameter("action", "list");
    }

    @RequestMapping(params = "action=addEditView")  // render phase
    public String addEditViewUser(javax.portlet.RenderRequest request, @RequestParam(value = "inventionId", required = false) Integer inventionId, @RequestParam("mode") String mode, Model model) {
        model.addAttribute("mode", mode);
        PatentForm patentForm = null;
        if (!model.containsAttribute("patentForm")) {
            patentForm = new PatentForm();
            model.addAttribute("patentForm",
                    patentForm);
        } else {
            patentForm = (PatentForm) model.asMap().get("patentForm");

        }

        User user = (User) request.getAttribute(WebKeys.USER);
        String userid = null;
        if (user != null)
            userid = user.getUserId() + "";

        PatentM rewardM = null;
        if (mode.equals("add")) {
            patentForm = new PatentForm();
            rewardM = new PatentM();
        } else if (mode.equals("edit")) {
            rewardM = researchService.findPatentById(inventionId, userid);
            if (rewardM.getResearchProjectId() != null) {
                ResearchProjectM researchProjectM = researchService.findResearchProjectById(rewardM.getResearchProjectId(), null);
                if (researchProjectM != null) {
                    rewardM.setResearchProjectShow(researchProjectM.getThaiName());
                }
            }
            if (rewardM.getIntegerernalOrganizationId() != null) {
                OrganizationM organizationM = researchService.findOrganizationById(rewardM.getIntegerernalOrganizationId());
                if (organizationM != null) {
                    rewardM.setIntegerernalOrganizationShow(organizationM.getOrgName());
                }
            }
            if (rewardM.getResearchGroup() != null) {
                ResearchGroupM researchGroupM = researchService.findResearchGroupById(rewardM.getResearchGroup());
                if (researchGroupM != null) {
                    rewardM.setResearchGroupShow(researchGroupM.getGroupTh());
                }
            }

            Date proposeDate = rewardM.getProposeDate();
            Date verifyDate = rewardM.getVerifyDate();
            Date announcementDate = rewardM.getAnnouncementDate();
            Date announcementPayDate = rewardM.getAnnouncementPayDate();
            Date proposeBookDate = rewardM.getProposeBookDate();
            Date receiveDate = rewardM.getReceiveDate();
            Date patentDate = rewardM.getPatentDate();
            Date payDate = rewardM.getPayDate();

            if (proposeDate != null)
                patentForm.setProposeDate(format1.format(proposeDate));

            if (verifyDate != null)
                patentForm.setVerifyDate(format1.format(verifyDate));

            if (announcementDate != null)
                patentForm.setAnnouncementDate(format1.format(announcementDate));

            if (announcementPayDate != null)
                patentForm.setAnnouncementPayDate(format1.format(announcementPayDate));

            if (proposeBookDate != null)
                patentForm.setProposeBookDate(format1.format(proposeBookDate));

            if (receiveDate != null)
                patentForm.setReceiveDate(format1.format(receiveDate));

            if (patentDate != null)
                patentForm.setPatentDate(format1.format(patentDate));

            if (payDate != null)
                patentForm.setPayDate(format1.format(payDate));

        } else if (mode.equals("copy")) {
            rewardM = researchService.findPatentById(inventionId, userid);
            if (rewardM.getResearchProjectId() != null) {
                ResearchProjectM researchProjectM = researchService.findResearchProjectById(rewardM.getResearchProjectId(), null);
                if (researchProjectM != null) {
                    rewardM.setResearchProjectShow(researchProjectM.getThaiName());
                }
            }
            if (rewardM.getIntegerernalOrganizationId() != null) {
                OrganizationM organizationM = researchService.findOrganizationById(rewardM.getIntegerernalOrganizationId());
                if (organizationM != null) {
                    rewardM.setIntegerernalOrganizationShow(organizationM.getOrgName());
                }
            }
            if (rewardM.getResearchGroup() != null) {
                ResearchGroupM researchGroupM = researchService.findResearchGroupById(rewardM.getResearchGroup());
                if (researchGroupM != null) {
                    rewardM.setResearchGroupShow(researchGroupM.getGroupTh());
                }
            }

            Date proposeDate = rewardM.getProposeDate();
            Date verifyDate = rewardM.getVerifyDate();
            Date announcementDate = rewardM.getAnnouncementDate();
            Date announcementPayDate = rewardM.getAnnouncementPayDate();
            Date proposeBookDate = rewardM.getProposeBookDate();
            Date receiveDate = rewardM.getReceiveDate();
            Date patentDate = rewardM.getPatentDate();
            Date payDate = rewardM.getPayDate();

            if (proposeDate != null)
                patentForm.setProposeDate(format1.format(proposeDate));

            if (verifyDate != null)
                patentForm.setVerifyDate(format1.format(verifyDate));

            if (announcementDate != null)
                patentForm.setAnnouncementDate(format1.format(announcementDate));

            if (announcementPayDate != null)
                patentForm.setAnnouncementPayDate(format1.format(announcementPayDate));

            if (proposeBookDate != null)
                patentForm.setProposeBookDate(format1.format(proposeBookDate));

            if (receiveDate != null)
                patentForm.setReceiveDate(format1.format(receiveDate));

            if (patentDate != null)
                patentForm.setPatentDate(format1.format(patentDate));

            if (payDate != null)
                patentForm.setPayDate(format1.format(payDate));

            //reset for copy
            rewardM.setPatentCreators(null);
            rewardM.setPatentDocuments(null);
            rewardM.setPatentEditDates(null);
            rewardM.setPatentFeePayments(null);
            rewardM.setPatentInheriteds(null);
            rewardM.setPatentRightholders(null);
            rewardM.setDocAssignMappings(null);
        }
        patentForm.setMode(mode);
        patentForm.setPatentM(rewardM);
        model.addAttribute("patentForm", patentForm);
        return "docs/researchPatentAddEditView";

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
    public void getById(@RequestParam("patentId") Integer patentId, ResourceRequest request,
                        ResourceResponse response) throws PortletException, IOException {
        //ObjectMapper mapper = new ObjectMapper();
        String id = request.getParameter("patentId");
        PatentM patent = researchService.findPatentById(Integer.valueOf(patentId), null);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        ResourceURL csvResource = response.createResourceURL();
        csvResource.setResourceID("research_group_resource_export");
        csvResource.setParameter("patentId", patentId + "");
        csvResource.setParameter("type", "csv");

        ResourceURL xmlResource = response.createResourceURL();
        xmlResource.setResourceID("research_group_resource_export");
        xmlResource.setParameter("patentId", patentId + "");
        xmlResource.setParameter("type", "xml");

        patent.setCsvResource(csvResource.toString());
        patent.setXmlResource(xmlResource.toString());

        try {
            //mapper.writeValue(response.getWriter(), patent);
            customObjectMapper.writeValue(response.getWriter(), patent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @ResourceMapping(value = "research_group_resource_export")
    public void loadXls(@RequestParam("patentId") Integer patentId, @RequestParam("type") String type, ResourceRequest request,
                        ResourceResponse response) throws PortletException, IOException {
        //ObjectMapper mapper = new ObjectMapper();
        String filename = "patent";
        response.setCharacterEncoding("UTF-8");
        String contentType = "text/xml";
        PatentM patent = researchService.findPatentById(Integer.valueOf(patentId), null);
        String content = "";
        if (type.equals("xml")) {
            filename = filename + ".xml";
            XStream xs = new XStream(new DomDriver());
            content = xs.toXML(patent);

        } else {
            filename = filename + ".csv";
            contentType = "text/csv";
            ColumnPositionMappingStrategy strat = new ColumnPositionMappingStrategy();
            strat.setType(PatentM.class);
            String[] columns = new String[]{"patentId", "groupCode", "groupTh"}; // the fields to bind do in your JavaBean
            strat.setColumnMapping(columns);
            StringWriter sw = new StringWriter();
            try {
                List<PatentM> list = new ArrayList<PatentM>(1);
                list.add(patent);
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
}
