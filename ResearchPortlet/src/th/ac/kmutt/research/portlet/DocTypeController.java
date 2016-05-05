package th.ac.kmutt.research.portlet;

import javax.portlet.PortletPreferences;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;
import org.springframework.web.portlet.bind.PortletRequestDataBinder;

import th.ac.kmutt.research.form.DocTypeForm;
import th.ac.kmutt.research.mapper.CustomObjectMapper;
import th.ac.kmutt.research.model.DocTypeM;
import th.ac.kmutt.research.service.ResearchService;
import th.ac.kmutt.research.utils.ImakeUtils;
import th.ac.kmutt.research.xstream.common.ImakeResultMessage;

import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.User;

@Controller("docTypeController")
@RequestMapping("VIEW")
@SessionAttributes({"docTypeForm"})
public class DocTypeController {

    private static final Logger logger = Logger
            .getLogger(DocTypeController.class);
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
        final String[] ALLOWED_FIELDS = {"docTypeM.dtName", "docTypeM.createdBy", "docTypeM.createdDate", "docTypeM.isCreate",
                "docTypeM.dtDesc", "docTypeM.isDelete", "docTypeM.isDisableForAdmin",
                "docTypeM.isRead", "docTypeM.isUpdate", "docTypeM.isView", "mode", "command", "keySearch",
                "isView", "isRead", "isDelete", "isDisableForAdmin", "isUpdate", "isCreate", "pageNo", "paging.pageSize", "ids", "tab", "filter"};

        binder.setAllowedFields(ALLOWED_FIELDS);
    }

    @RequestMapping
    // default (action=list)
    public String listResearchGroup(Model model) {
        DocTypeForm docTypeForm = null;
        if (!model.containsAttribute("docTypeForm")) {
            docTypeForm = new DocTypeForm();
            model.addAttribute("docTypeForm",
                    docTypeForm);
        } else {
            docTypeForm = (DocTypeForm) model.asMap().get("docTypeForm");
        }
        String keySearch = docTypeForm.getKeySearch();
        DocTypeM docTypeM = new DocTypeM();
        docTypeM.setKeySearch(keySearch);
        docTypeM.setPaging(docTypeForm.getPaging());
        docTypeM.getPaging().setPageNo(docTypeForm.getPageNo());

        ImakeResultMessage imakeResultMessage = researchService
                .listDocTypeM();
        model.addAttribute("docTypes", imakeResultMessage.getResultListObj());
        //docTypeForm.getPaging().setPageSize(ImakeUtils.PAGE_SIZE);
        docTypeForm.setPageCount(ImakeUtils.calculatePage(docTypeForm.getPaging().getPageSize(), Integer.parseInt(imakeResultMessage.getMaxRow())));
        int[] start_end = ImakeUtils.calculatePageStartEnd(docTypeForm.getPageNo(), docTypeForm.getPageCount());
        docTypeForm.setPageStart(start_end[0]);
        docTypeForm.setPageEnd(start_end[1]);
        return "setting/doctype";
    }

    @RequestMapping(params = "action=doSubmit") // action phase
    public void populateSite(javax.portlet.ActionRequest request, javax.portlet.ActionResponse response,
                             @ModelAttribute("docTypeForm") DocTypeForm docTypeForm,
                             BindingResult result, Model model) {
        String command = docTypeForm.getCommand();
        String mode = docTypeForm.getMode();
        User user = (User) request.getAttribute(WebKeys.USER);
        //if(command.equals("doSaveFAQ")){

        DocTypeM docTypeM = docTypeForm.getDocTypeM();
        docTypeM.setIsViews(docTypeForm.getIsView());
        docTypeM.setIsCreates(docTypeForm.getIsCreate());
        docTypeM.setIsUpdates(docTypeForm.getIsUpdate());
        docTypeM.setIsDeletes(docTypeForm.getIsDelete());
        docTypeM.setUpdatedBy(user.getUserId() + "");
        docTypeM.setIsDisableForAdmins(docTypeForm.getIsDisableForAdmin());
        researchService.updateDocTypeM(docTypeM);
        command = "list";
        response.setRenderParameter("action", command);
    }
}
