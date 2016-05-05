package th.ac.kmutt.research.portlet;

import java.io.IOException;

import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.PortletPreferences;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

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
import th.ac.kmutt.research.form.ResearcherMasterForm;
import th.ac.kmutt.research.mapper.CustomObjectMapper;
import th.ac.kmutt.research.model.ResearcherM;
import th.ac.kmutt.research.service.ResearchService;
import th.ac.kmutt.research.utils.ImakeUtils;
import th.ac.kmutt.research.xstream.common.ImakeResultMessage;

import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.User;
import com.liferay.portal.theme.ThemeDisplay;

@Controller("researcherMasterController")
@RequestMapping("VIEW")
@SessionAttributes({"researcherMasterForm"})
public class ResearcherMasterController {
    private static final Logger logger = Logger
            .getLogger(ResearcherMasterController.class);
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
        final String[] ALLOWED_FIELDS = {"researcherM.researcherId", "researcherM.createdBy", "researcherM.createdDate",
                "researcherM.academicPosition.positionId", "researcherM.position.positionId", "researcherM.updatedBy",
                "researcherM.updatedDate", "researcherM.campusCode", "researcherM.deptCode",
                "researcherM.workCode", "researcherM.cardId", "researcherM.nameEng",
                "researcherM.institutionCode", "researcherM.academicTitle.titleId", "researcherM.title.titleId",
                "researcherM.organization.organizationId", "researcherM.nameThai", "researcherM.researcherCode",
                "researcherM.surnameEng", "researcherM.surnameThai", "researcherM.researcherTypeId",
                "mode", "command", "keySearch", "pageNo", "paging.pageSize", "ids", "tab", "filter"};

        binder.setAllowedFields(ALLOWED_FIELDS);
    }

    @RequestMapping
    // default (action=list)
    public String listResearcher(Model model) {
        ResearcherMasterForm researcherMasterForm = null;
        if (!model.containsAttribute("researcherMasterForm")) {
            researcherMasterForm = new ResearcherMasterForm();
            model.addAttribute("researcherMasterForm",
                    researcherMasterForm);
        } else {
            researcherMasterForm = (ResearcherMasterForm) model.asMap().get("researcherMasterForm");
        }
        String keySearch = researcherMasterForm.getKeySearch();
        ResearcherM researcherM = new ResearcherM();
        researcherM.setKeySearch(keySearch);
        researcherM.setPaging(researcherMasterForm.getPaging());
        researcherM.getPaging().setPageNo(researcherMasterForm.getPageNo());

        ImakeResultMessage imakeResultMessage = researchService
                .searchResearcherM(researcherM);
        model.addAttribute("researchers", imakeResultMessage.getResultListObj());
        //researcherMasterForm.getPaging().setPageSize(ImakeUtils.PAGE_SIZE);
        researcherMasterForm.setPageCount(ImakeUtils.calculatePage(researcherMasterForm.getPaging().getPageSize(), Integer.parseInt(imakeResultMessage.getMaxRow())));
        int[] start_end = ImakeUtils.calculatePageStartEnd(researcherMasterForm.getPageNo(), researcherMasterForm.getPageCount());
        researcherMasterForm.setPageStart(start_end[0]);
        researcherMasterForm.setPageEnd(start_end[1]);
        return "master/researcher";
    }

    @RequestMapping(params = "action=add")
    // render phase
    public String showResearcherMasterForm(Model model) {
        // Used for the initial form as well as for redisplaying with errors.
        /*
		 * if (!model.containsAttribute("site")) { model.addAttribute("site",
		 * new PetSite()); }
		 */
        ResearcherMasterForm researcherMasterForm = new ResearcherMasterForm();
        researcherMasterForm.setMode("add");
        model.addAttribute("researcherMasterForm",
                researcherMasterForm);
        return "master/researcher";
    }

    @RequestMapping(params = "action=doSubmit") // action phase
    public void populateSite(javax.portlet.ActionRequest request, javax.portlet.ActionResponse response,
                             @ModelAttribute("researcherMasterForm") ResearcherMasterForm researcherMasterForm,
                             BindingResult result, Model model) {
        // new PetSiteValidator().validate(petSite, result);
		/*
		 * if (!result.hasErrors()) { this.petSites.put(petSite.getName(),
		 * petSite.getUrl()); status.setComplete();
		 * response.setRenderParameter("action", "list"); }
		 */
        String command = researcherMasterForm.getCommand();
        String mode = researcherMasterForm.getMode();
        User user = (User) request.getAttribute(WebKeys.USER);
        researcherMasterForm.getResearcherM().setUpdatedBy(user.getUserId() + "");
        Integer refId = null;
        command = "addEditView";
        if (mode != null) {
            if (mode.equals("add")) {
                researcherMasterForm.getResearcherM().setCreatedBy(user.getUserId() + "");
                researcherMasterForm.getResearcherM().setResearcherId(null);
                refId = researchService.saveResearcherM(researcherMasterForm.getResearcherM());
            } else if (mode.equals("edit")) {
                refId = researchService.updateResearcherM(researcherMasterForm.getResearcherM());
            } else if (mode.equals("deleteItems")) {
                if (researcherMasterForm.getIds() != null && researcherMasterForm.getIds().length > 0) {
                    researchService.deleteItemsResearcherM(researcherMasterForm.getIds());
                }
            } else {
                command = "list";
            }
        }

        //}
        response.setRenderParameter("action", command);
        if (mode.equals("add") || mode.equals("edit")) {
            response.setRenderParameter("researcherId", refId + "");
            response.setRenderParameter("mode", "edit");
        }
    }

    @RequestMapping(params = "action=delete")
    public void removeSite(@RequestParam("researcherId") Integer researcherId,
                           ActionResponse response, Model model) {
        ResearcherM researcherM = new ResearcherM();
        researcherM.setResearcherId(researcherId);
        int recordCount = researchService.deleteResearcherM(researcherM);
        if (recordCount == -9)
            model.addAttribute(ServiceConstant.ERROR_MESSAGE_KEY, ServiceConstant.ERROR_CONSTRAINT_VIOLATION_MESSAGE_CODE);
        response.setRenderParameter("action", "list");
    }

    @RequestMapping(params = "action=addEditView")  // render phase
    public String addEditViewUser(@RequestParam(value = "researcherId", required = false) Integer researcherId, @RequestParam("mode") String mode, Model model) {
        model.addAttribute("mode", mode);
        ResearcherMasterForm researcherMasterForm = null;
        if (!model.containsAttribute("researcherMasterForm")) {
            researcherMasterForm = new ResearcherMasterForm();
            model.addAttribute("researcherMasterForm",
                    researcherMasterForm);
        } else {
            researcherMasterForm = (ResearcherMasterForm) model.asMap().get("researcherMasterForm");
        }

        researcherMasterForm.setMode(mode);
        ResearcherM researcherM = null;
        if (mode.equals("add")) {
            researcherM = new ResearcherM();
        } else if (mode.equals("edit")) {
            researcherM = researchService.findResearcherById(researcherId);
        }
        researcherMasterForm.setResearcherM(researcherM);
        model.addAttribute("researcherMasterForm", researcherMasterForm);
        return "master/researcherAddEditView";

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
    public void getById(@RequestParam("researcherId") Integer researcherId, ResourceRequest request,
                        ResourceResponse response) throws PortletException, IOException {
        //ObjectMapper mapper = new ObjectMapper();
        String id = request.getParameter("researcherId");
        ResearcherM researcher = researchService.findResearcherById(Integer.valueOf(researcherId));
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        try {
            //mapper.writeValue(response.getWriter(), researcher);
            customObjectMapper.writeValue(response.getWriter(), researcher);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
