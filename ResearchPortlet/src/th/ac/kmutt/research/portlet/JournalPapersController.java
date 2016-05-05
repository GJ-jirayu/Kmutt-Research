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
import javax.portlet.RenderRequest;
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
import th.ac.kmutt.research.form.JournalPapersForm;
import th.ac.kmutt.research.mapper.CustomObjectMapper;
import th.ac.kmutt.research.model.*;
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

@Controller("journalPapersController")
@RequestMapping("VIEW")
@SessionAttributes({"journalPapersForm"})
public class JournalPapersController {
    private static final Logger logger = Logger
            .getLogger(JournalPapersController.class);
    private static ResourceBundle bundle;

    static {
        bundle = ResourceBundle.getBundle("config");
    }

    private String ATTACH_PATH = bundle.getString("downloadPath");
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
        final String[] ALLOWED_FIELDS = {"journalPaperM.journalPapersId", "journalPaperM.createdBy", "journalPaperM.createdDate",
                "journalPaperM.engName", "journalPaperM.thaiName", "journalPaperM.updatedBy",
                "journalPaperM.updatedDate", "journalPaperM.researchProjectId", "journalPaperM.researchGroupId",
                "mode", "command", "keySearch",
                "journalPaperM.remark", "journalPaperM.publishType", "journalPaperM.publishLanguage",
                "journalPaperM.remark", "journalPaperM.organizationId", "journalPaperM.journalPapersType",
                "journalPaperM.journalPapersConference.journalPapersId", "journalPaperM.journalPapersConference.city", "journalPaperM.journalPapersConference.conferenceName",
                "journalPaperM.journalPapersConference.level", "journalPaperM.journalPapersConference.location", "journalPaperM.journalPapersConference.page",
                "journalPaperM.journalPapersJournal.journalPapersId", "journalPaperM.journalPapersJournal.issue", "journalPaperM.journalPapersJournal.journalName",
                "journalPaperM.journalPapersJournal.level", "journalPaperM.journalPapersJournal.month", "journalPaperM.journalPapersJournal.page",
                "journalPaperM.journalPapersJournal.vol", "journalPaperM.journalPapersJournal.year",
                "journalPaperM.docType", "type", "journalPaperM.type", "journalPaperM.budgetYear", "journalPaperM.journalPapersConference.country",
                "journalPaperM.journalPapersConference.endDate", "journalPaperM.journalPapersConference.startDate",
                "journalPaperM.journalPapersJournal.acceptedDateShow","journalPaperM.journalPapersJournal.ispublished",
                "pageInit", "docsAssign", "pageNo", "paging.pageSize", "ids", "tab", "filter"};

        binder.setAllowedFields(ALLOWED_FIELDS);
    }

    @RequestMapping
    // default (action=list)
    public String listJournalPapers(RenderRequest request, Model model) {
        User user = (User) request.getAttribute(WebKeys.USER);
        JournalPapersForm journalPapersForm = null;
        if (!model.containsAttribute("journalPapersForm")) {
            journalPapersForm = new JournalPapersForm();
            model.addAttribute("journalPapersForm",
                    journalPapersForm);
        } else {
            journalPapersForm = (JournalPapersForm) model.asMap().get("journalPapersForm");
        }
        String keySearch = journalPapersForm.getKeySearch();
        JournalPaperM journalPaperM = new JournalPaperM();
        journalPaperM.setKeySearch(keySearch);
        journalPaperM.setPaging(journalPapersForm.getPaging());
        journalPaperM.getPaging().setPageNo(journalPapersForm.getPageNo());
        journalPaperM.setType(1);
        journalPaperM.setUserid(user.getUserId() + "");
        if (journalPapersForm.getTab() == null)
            journalPapersForm.setTab(ServiceConstant.TAB_ALL);
        if (journalPapersForm.getFilter() == null)
            journalPapersForm.setFilter(ServiceConstant.FILTERS[0]);
        journalPaperM.setTab(journalPapersForm.getTab());
        journalPaperM.setFilter(journalPapersForm.getFilter());

        ImakeResultMessage imakeResultMessage = researchService
                .searchJournalPaperM(journalPaperM);
        ;
        model.addAttribute("journalPapers", imakeResultMessage.getResultListObj());
        //journalPapersForm.getPaging().setPageSize(ImakeUtils.PAGE_SIZE);
        journalPapersForm.setPageCount(ImakeUtils.calculatePage(journalPapersForm.getPaging().getPageSize(), Integer.parseInt(imakeResultMessage.getMaxRow())));
        int[] start_end = ImakeUtils.calculatePageStartEnd(journalPapersForm.getPageNo(), journalPapersForm.getPageCount());
        journalPapersForm.setPageStart(start_end[0]);
        journalPapersForm.setPageEnd(start_end[1]);
        return "docs/journalPapersList";
    }

    @RequestMapping(params = "action=doSubmit") // action phase
    public void populateSite(javax.portlet.ActionRequest request, javax.portlet.ActionResponse response,
                             @ModelAttribute("journalPapersForm") JournalPapersForm journalPapersForm,
                             BindingResult result, Model model) {
        // new PetSiteValidator().validate(petSite, result);
        /*
		 * if (!result.hasErrors()) { this.petSites.put(petSite.getName(),
		 * petSite.getUrl()); status.setComplete();
		 * response.setRenderParameter("action", "list"); }
		 */

        String command = journalPapersForm.getCommand();
        String mode = journalPapersForm.getMode();
        User user = (User) request.getAttribute(WebKeys.USER);
        //journalPapersForm.setPageInit("journalPapersList");
        journalPapersForm.getJournalPaperM().setUpdatedBy(user.getUserId() + "");

        journalPapersForm.getJournalPaperM().setType(ServiceConstant.JOURNAL_PAPERS_TYPE_JOURNAL);
        if (journalPapersForm.getJournalPaperM().getType() == 2) {
            //journalPapersForm.setPageInit("journalPapersConferenceList");
            String endDate = journalPapersForm.getJournalPaperM().getJournalPapersConference().getEndDateShow();
            String startDate = journalPapersForm.getJournalPaperM().getJournalPapersConference().getStartDateShow();
            if (startDate != null && startDate.trim().length() > 0)
                try {
                    journalPapersForm.getJournalPaperM().getJournalPapersConference().setStartDate(format1.parse(startDate));
                } catch (ParseException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            if (endDate != null && endDate.trim().length() > 0)
                try {
                    journalPapersForm.getJournalPaperM().getJournalPapersConference().setEndDate(format1.parse(endDate));
                } catch (ParseException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
        }
        if(journalPapersForm.getJournalPaperM().getJournalPapersJournal().getIspublished()==null) {
            journalPapersForm.getJournalPaperM().getJournalPapersJournal().setIspublished("0");
        }
        String acceptedDateShow = journalPapersForm.getJournalPaperM().getJournalPapersJournal().getAcceptedDateShow();
        Date acceptedDate=null;
        if (acceptedDateShow != null && acceptedDateShow.trim().length() > 0
                && journalPapersForm.getJournalPaperM().getJournalPapersJournal().getIspublished().equals("0"))
            try {
                acceptedDate=format1.parse(acceptedDateShow);
            } catch (ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        journalPapersForm.getJournalPaperM().getJournalPapersJournal().setAcceptedDate(acceptedDate);

        if(journalPapersForm.getJournalPaperM().getJournalPapersJournal().getIspublished().equals("0")) {
           // journalPapersForm.getJournalPaperM().getJournalPapersJournal().setIspublished("0");
            journalPapersForm.getJournalPaperM().getJournalPapersJournal().setVol("");
            journalPapersForm.getJournalPaperM().getJournalPapersJournal().setIssue("");
            journalPapersForm.getJournalPaperM().getJournalPapersJournal().setPage("");
            journalPapersForm.getJournalPaperM().getJournalPapersJournal().setMonth("");
            journalPapersForm.getJournalPaperM().getJournalPapersJournal().setYear("");
        }
        //String docsAssign=journalPapersForm.getDocsAssign();
        command = "addEditView";
        Integer refId = null;
        System.out.println("xxxx-->"+journalPapersForm.getJournalPaperM().getJournalPapersJournal().getIspublished());
        if (mode != null) {
            if (mode.equals("add") || mode.equals("copy")) {
                journalPapersForm.getJournalPaperM().setCreatedBy(user.getUserId() + "");
                journalPapersForm.getJournalPaperM().setJournalPapersId(null);
                refId = researchService.saveJournalPaperM(journalPapersForm.getJournalPaperM());
            } else if (mode.equals("edit")) {
                refId = researchService.updateJournalPaperM(journalPapersForm.getJournalPaperM());
            } else if (mode.equals("deleteItems")) {
                if (journalPapersForm.getIds() != null && journalPapersForm.getIds().length > 0) {
                    researchService.deleteItemsJournalPaperM(1, journalPapersForm.getIds());
                }
                command = "list";
            } else {
                command = "list";
            }
				/*if(refId!=null)
					if(docsAssign!=null && docsAssign.trim().length()>0){
						DocAssignMappingM mapping =new DocAssignMappingM();
						mapping.setRefId(refId);
						mapping.setRefType("JOURNAL");
						mapping.setUserId(docsAssign);
						researchService.saveDocAssignMapping(mapping);
					}*/
        }

        response.setRenderParameter("action", command);

        //	response.setRenderParameter("pageInit", journalPapersForm.getPageInit());
        if (mode.equals("add") || mode.equals("edit") || mode.equals("copy")) {

            //	response.setRenderParameter("pageInit", "journalPapersAddEditView");

            response.setRenderParameter("journalPapersId", refId + "");
            response.setRenderParameter("mode", "edit");
        }

    }

    @RequestMapping(params = "action=delete")
    public void removeSite(@RequestParam("journalPapersId") Integer journalPapersId,
                           ActionResponse response) {
        JournalPaperM journalPaperM = new JournalPaperM();
        journalPaperM.setJournalPapersId(journalPapersId);
        //journalPaperM.setFlag("0");
        //journalPaperM.setUpdateType("flag");
        journalPaperM.setType(1);
        //researchService.updateFlagJournalPaperM(journalPaperM);
        researchService.deleteJournalPaperM(journalPaperM);

        response.setRenderParameter("action", "list");
        //response.setRenderParameter("pageInit", pageInit);
    }

    @RequestMapping(params = "action=undoItem")
    public void undoItem(@RequestParam("journalPapersId") Integer journalPapersId,
                         ActionResponse response) {
        JournalPaperM journalPaperM = new JournalPaperM();
        journalPaperM.setJournalPapersId(journalPapersId);
        journalPaperM.setType(1);
        journalPaperM.setUpdateType(ServiceConstant.UPDATE_TYEP_FLAG);
        journalPaperM.setFlag(ServiceConstant.FLAG_ACTIVE);
        researchService.updateFlagJournalPaperM(journalPaperM);
        //researchService.deleteResearchProjectM(researchProjectM);
        response.setRenderParameter("action", "list");
    }

    @RequestMapping(params = "action=addEditView")  // render phase
    public String addEditViewUser(javax.portlet.RenderRequest request, @RequestParam(value = "journalPapersId", required = false) Integer journalPapersId,
                                  @RequestParam("mode") String mode,
                                  RenderResponse response, Model model) {
        model.addAttribute("mode", mode);
        JournalPapersForm journalPapersForm = null;
        if (!model.containsAttribute("journalPapersForm")) {
            journalPapersForm = new JournalPapersForm();

            model.addAttribute("journalPapersForm",
                    journalPapersForm);
        } else {
            journalPapersForm = (JournalPapersForm) model.asMap().get("journalPapersForm");
        }

        User user = (User) request.getAttribute(WebKeys.USER);
        String userid = null;
        if (user != null)
            userid = user.getUserId() + "";

        JournalPaperM journalPaperM = null;
        if (mode.equals("add")) {
            journalPaperM = new JournalPaperM();
            journalPapersForm.getJournalPaperM().getJournalPapersJournal().setIspublished("1");
        } else if (mode.equals("edit")) {
            journalPaperM = researchService.findJournalPapersById(journalPapersId, userid);
			
		/*	private String ;
			private String ;
			private String organizationShow;
			private String researchGroupShow;*/
            //	"journalPaperM.journalPapersConference.endDate","journalPaperM.journalPapersConference.startDate",

            if (journalPaperM.getOrganizationId() != null) {
                OrganizationM organizationM = researchService.findOrganizationById(journalPaperM.getOrganizationId());
                if (organizationM != null) {
                    journalPaperM.setOrganizationShow(organizationM.getOrgName());
                }
            }
            if (journalPaperM.getJournalPapersType() != null) {
                JournalPapersTypeM journalPapersTypeM = researchService.findJournalPapersTypeById(journalPaperM.getJournalPapersType());
                if (journalPapersTypeM != null) {
                    journalPaperM.setJournalPapersTypeShow(journalPapersTypeM.getJptName());
                }
            }
            if (journalPaperM.getResearchProjectId() != null) {
                ResearchProjectM researchProjectM = researchService.findResearchProjectById(journalPaperM.getResearchProjectId(), null);
                if (researchProjectM != null) {
                    journalPaperM.setResearchProjectShow(researchProjectM.getThaiName());
                }
            }
            if (journalPaperM.getResearchGroupId() != null) {
                ResearchGroupM researchGroupM = researchService.findResearchGroupById(journalPaperM.getResearchGroupId());
                if (researchGroupM != null) {
                    journalPaperM.setResearchGroupShow(researchGroupM.getGroupTh());
                }
            }


            if (journalPaperM.getJournalPapersJournal() != null && journalPaperM.getJournalPapersJournal().getLevel()!=null
                    && journalPaperM.getJournalPapersJournal().getLevel().trim().length()>0) {
                PublishLevelM publishLevelM =
                        researchService.findPublishLevelById(Integer.parseInt(journalPaperM.getJournalPapersJournal().getLevel()));
                if (publishLevelM != null) {
                    journalPaperM.setJournalPapersLevelShow(publishLevelM.getPlName());
                }
            }
            if (journalPaperM.getType() != null && journalPaperM.getType() == 2) {
                if (journalPaperM.getJournalPapersConference() != null) {
                    if (journalPaperM.getJournalPapersConference().getCountry() != null) {
                        CountryM countryM = researchService.findCountryById(journalPaperM.getJournalPapersConference().getCountry());
                        if (countryM != null) {
                            journalPaperM.getJournalPapersConference().setCountryShow(countryM.getCountryNameTh());
                        }
                    }
                    Date endDate = journalPaperM.getJournalPapersConference().getEndDate();
                    Date startDate = journalPaperM.getJournalPapersConference().getStartDate();

                    if (startDate != null)
                        journalPaperM.getJournalPapersConference().setStartDateShow(format1.format(startDate));

                    if (endDate != null)
                        journalPaperM.getJournalPapersConference().setEndDateShow(format1.format(endDate));


                }

            }
            Date acceptedDate = journalPaperM.getJournalPapersJournal().getAcceptedDate();

            if (acceptedDate != null)
                journalPaperM.getJournalPapersJournal().setAcceptedDateShow(format1.format(acceptedDate));


            ResourceURL proofParticipationResource = response.createResourceURL();
            proofParticipationResource.setResourceID("research_journalPapers_resource_download");
            proofParticipationResource.setParameter("journalPapersId", journalPapersId + "");
            proofParticipationResource.setParameter("type", "Proof_Participation");

            ResourceURL proofAcademicResource = response.createResourceURL();
            proofAcademicResource.setResourceID("research_journalPapers_resource_downloads");
            proofAcademicResource.setParameter("journalPapersId", journalPapersId + "");
            proofAcademicResource.setParameter("type", "Proof_Academic");

            journalPaperM.setProofAcademicResource(proofAcademicResource.toString());
            journalPaperM.setProofParticipationResource(proofParticipationResource.toString());
        } else if (mode.equals("copy")) {
            journalPaperM = researchService.findJournalPapersById(journalPapersId, userid);
            if (journalPaperM.getOrganizationId() != null) {
                OrganizationM organizationM = researchService.findOrganizationById(journalPaperM.getOrganizationId());
                if (organizationM != null) {
                    journalPaperM.setOrganizationShow(organizationM.getOrgName());
                }
            }
            if (journalPaperM.getJournalPapersType() != null) {
                JournalPapersTypeM journalPapersTypeM = researchService.findJournalPapersTypeById(journalPaperM.getJournalPapersType());
                if (journalPapersTypeM != null) {
                    journalPaperM.setJournalPapersTypeShow(journalPapersTypeM.getJptName());
                }
            }
            if (journalPaperM.getResearchProjectId() != null) {
                ResearchProjectM researchProjectM = researchService.findResearchProjectById(journalPaperM.getResearchProjectId(), null);
                if (researchProjectM != null) {
                    journalPaperM.setResearchProjectShow(researchProjectM.getThaiName());
                }
            }
            if (journalPaperM.getResearchGroupId() != null) {
                ResearchGroupM researchGroupM = researchService.findResearchGroupById(journalPaperM.getResearchGroupId());
                if (researchGroupM != null) {
                    journalPaperM.setResearchGroupShow(researchGroupM.getGroupTh());
                }
            }
            if (journalPaperM.getJournalPapersJournal() != null && journalPaperM.getJournalPapersJournal().getLevel()!=null
                    && journalPaperM.getJournalPapersJournal().getLevel().trim().length()>0) {
                PublishLevelM publishLevelM =
                        researchService.findPublishLevelById(Integer.parseInt(journalPaperM.getJournalPapersJournal().getLevel()));
                if (publishLevelM != null) {
                    journalPaperM.setJournalPapersLevelShow(publishLevelM.getPlName());
                }
            }
            if (journalPaperM.getType() != null && journalPaperM.getType() == 2) {
                if (journalPaperM.getJournalPapersConference() != null) {
                    if (journalPaperM.getJournalPapersConference().getCountry() != null) {
                        CountryM countryM = researchService.findCountryById(journalPaperM.getJournalPapersConference().getCountry());
                        if (countryM != null) {
                            journalPaperM.getJournalPapersConference().setCountryShow(countryM.getCountryNameTh());
                        }
                    }
                    Date endDate = journalPaperM.getJournalPapersConference().getEndDate();
                    Date startDate = journalPaperM.getJournalPapersConference().getStartDate();

                    if (startDate != null)
                        journalPaperM.getJournalPapersConference().setStartDateShow(format1.format(startDate));

                    if (endDate != null)
                        journalPaperM.getJournalPapersConference().setEndDateShow(format1.format(endDate));


                }

            }

            Date acceptedDate = journalPaperM.getJournalPapersJournal().getAcceptedDate();

            if (acceptedDate != null)
                journalPaperM.getJournalPapersJournal().setAcceptedDateShow(format1.format(acceptedDate));

            //reset for copy
            journalPaperM.setJournalPapersDocuments(null);
            journalPaperM.setJournalPapersWriters(null);
            journalPaperM.setDocAssignMappings(null);

            ResourceURL proofParticipationResource = response.createResourceURL();
            proofParticipationResource.setResourceID("research_journalPapers_resource_download");
            proofParticipationResource.setParameter("journalPapersId", journalPapersId + "");
            proofParticipationResource.setParameter("type", "Proof_Participation");

            ResourceURL proofAcademicResource = response.createResourceURL();
            proofAcademicResource.setResourceID("research_journalPapers_resource_downloads");
            proofAcademicResource.setParameter("journalPapersId", journalPapersId + "");
            proofAcademicResource.setParameter("type", "Proof_Academic");

            journalPaperM.setProofAcademicResource(proofAcademicResource.toString());
            journalPaperM.setProofParticipationResource(proofParticipationResource.toString());
        }
        journalPapersForm.setMode(mode);
        journalPapersForm.setJournalPaperM(journalPaperM);
        model.addAttribute("journalPapersForm", journalPapersForm);
        return "docs/journalPapersAddEditView";

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
    public void getById(@RequestParam("journalPapersId") Integer journalPapersId, ResourceRequest request,
                        ResourceResponse response) throws PortletException, IOException {
        //ObjectMapper mapper = new ObjectMapper();
        String id = request.getParameter("journalPapersId");
        JournalPaperM journalPaper = researchService.findJournalPapersById(Integer.valueOf(journalPapersId), null);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");

        ResourceURL proofParticipationResource = response.createResourceURL();
        proofParticipationResource.setResourceID("research_journalPapers_resource_download");
        proofParticipationResource.setParameter("journalPapersId", journalPapersId + "");
        proofParticipationResource.setParameter("type", "Proof_Participation");

        ResourceURL proofAcademicResource = response.createResourceURL();
        proofAcademicResource.setResourceID("research_journalPapers_resource_downloads");
        proofAcademicResource.setParameter("journalPapersId", journalPapersId + "");
        proofAcademicResource.setParameter("type", "Proof_Academic");

        ResourceURL csvResource = response.createResourceURL();
        csvResource.setResourceID("research_group_resource_export");
        csvResource.setParameter("journalPapersId", journalPapersId + "");
        csvResource.setParameter("type", "csv");

        ResourceURL xmlResource = response.createResourceURL();
        xmlResource.setResourceID("research_group_resource_export");
        xmlResource.setParameter("journalPapersId", journalPapersId + "");
        xmlResource.setParameter("type", "xml");

        journalPaper.setCsvResource(csvResource.toString());
        journalPaper.setXmlResource(xmlResource.toString());

        try {
            //mapper.writeValue(response.getWriter(), journalPaper);
            customObjectMapper.writeValue(response.getWriter(), journalPaper);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @ResourceMapping(value = "research_group_resource_export")
    public void loadXls(@RequestParam("journalPapersId") Integer journalPapersId, @RequestParam("type") String type, ResourceRequest request,
                        ResourceResponse response) throws PortletException, IOException {
        //ObjectMapper mapper = new ObjectMapper();
        String filename = "journalPaper";
        response.setCharacterEncoding("UTF-8");
        String contentType = "text/xml";
        JournalPaperM journalPaper = researchService.findJournalPapersById(Integer.valueOf(journalPapersId), null);
        String content = "";
        if (type.equals("xml")) {
            filename = filename + ".xml";
            XStream xs = new XStream(new DomDriver());
            content = xs.toXML(journalPaper);

        } else {
            filename = filename + ".csv";
            contentType = "text/csv";
            ColumnPositionMappingStrategy strat = new ColumnPositionMappingStrategy();
            strat.setType(JournalPaperM.class);
            String[] columns = new String[]{"journalPapersId", "groupCode", "groupTh"}; // the fields to bind do in your JavaBean
            strat.setColumnMapping(columns);
            StringWriter sw = new StringWriter();
            try {
                List<JournalPaperM> list = new ArrayList<JournalPaperM>(1);
                list.add(journalPaper);
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
    public void research_project_resource_download(@RequestParam("journalPapersId") Integer journalPapersId, @RequestParam("type") String type, ResourceRequest request,
                                                   ResourceResponse response) throws PortletException, IOException {
        //ObjectMapper mapper = new ObjectMapper();
        JournalPaperM journalPaperM = researchService.findJournalPapersById(Integer.valueOf(journalPapersId), null);
        JournalPapersJournalM journalPapersJournalM = journalPaperM.getJournalPapersJournal();
        List<JournalPapersWriterM> journalPapersWriters = journalPaperM.getJournalPapersWriters();
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
            cell.setCellValue(journalPapersJournalM.getJournalName());
            //cell.setCellValue("TEST");

            row_code = sheet1.getRow(8);
            cell = row_code.getCell(3);
            if (journalPapersWriters != null && journalPapersWriters.size() > 0) {
                int size = journalPapersWriters.size();
                //List<ResearchProjectResearcherM> researcherms= journalPapersWriters;
                cell.setCellValue(size + "");
                int row_start = 11;
                int column_name = 0;
                int column_percent = 5;
                for (JournalPapersWriterM journalPapersWriter : journalPapersWriters) {
                    // set name
                    row_code = sheet1.getRow(row_start);
                    cell = row_code.getCell(column_name);
                    cell.setCellValue(journalPapersWriter.getResearcher().getNameThai());

                    // set percent
                    cell = row_code.getCell(column_percent);
                    cell.setCellValue(journalPapersWriter.getWorkLoadRatio());
                    row_start++;
                }
            }

        } catch (IOException e2) {
            // TODO Auto-generated catch block
            e2.printStackTrace();
        } finally {
            fin.close();
        }
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

        DocumentConverter converter = new OpenOfficeDocumentConverter(connection);
        converter.convert(new File(outputFile_xls), new File(outputFile_pdf));

        // close the connection
        connection.disconnect();

        String filename = type;//"researchProject";
        response.setCharacterEncoding("UTF-8");
        String contentType = "application/pdf";
        byte[] content = IOUtils.toByteArray(new FileInputStream(outputFile_pdf));
        // ResearchProjectM researchProject=researchService.findResearchProjectById(Integer.valueOf(journalPapersId));
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
