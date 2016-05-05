package th.ac.kmutt.research.portlet;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.ResourceBundle;

import javax.portlet.PortletException;
import javax.portlet.PortletPreferences;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.portlet.bind.PortletRequestDataBinder;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import th.ac.kmutt.research.form.DownloadDocForm;
import th.ac.kmutt.research.mapper.CustomObjectMapper;
import th.ac.kmutt.research.model.ResearchGroupM;
import th.ac.kmutt.research.service.ResearchService;

import com.liferay.portal.kernel.portlet.PortletResponseUtil;

@Controller("downloadDocController")
@RequestMapping("VIEW")
@SessionAttributes({"downloadDocForm"})
public class DownloadDocController {

    private static final Logger logger = Logger
            .getLogger(ResearchGroupMasterController.class);
    private static ResourceBundle bundle;

    static {
        bundle = ResourceBundle.getBundle("config");
    }

    private String ATTACH_PATH = bundle.getString("downloadPath");
    @Autowired
    @Qualifier("researchServiceWSImpl")
    private ResearchService researchService;

    @Autowired
    private CustomObjectMapper customObjectMapper;

    @InitBinder
    public void initBinder(PortletRequestDataBinder binder, PortletPreferences preferences) {
    /*	logger.debug("initBinder");
		binder.registerCustomEditor(byte[].class, new ByteArrayMultipartFileEditor());
		//String a[] = new String[]{"ntcfaq.nfaqName"};
		final String[] ALLOWED_FIELDS={"researchGroupM.researchGroupId","researchGroupM.createdBy","researchGroupM.createdDate",
				"researchGroupM.groupCode","researchGroupM.permissions","researchGroupM.updatedBy",
				"researchGroupM.updatedDate","researchGroupM.groupTh","researchGroupM.groupEng","mode", "command","keySearch"};
			
			binder.setAllowedFields(ALLOWED_FIELDS);		*/
    }

    @RequestMapping
    // default (action=list)
    public String listResearchGroup(Model model) {
        DownloadDocForm downloadDocForm = null;
        if (!model.containsAttribute("downloadDocForm")) {
            downloadDocForm = new DownloadDocForm();
            model.addAttribute("downloadDocForm",
                    downloadDocForm);
        } else {
            downloadDocForm = (DownloadDocForm) model.asMap().get("downloadDocForm");
        }
        String keySearch = downloadDocForm.getKeySearch();
        ResearchGroupM researchGroupM = new ResearchGroupM();
        researchGroupM.setKeySearch(keySearch);
		/*List<ResearchGroupM> researchGroups = researchService
				.searchResearchGroupM(researchGroupM);
		model.addAttribute("researchGroups", researchGroups);
		*/
        return "download/downloaddocs";
    }


    @ResourceMapping(value = "research_doc_download")
    public void loadXls(@RequestParam("fileType") String fileType, @RequestParam("formType") String formType, ResourceRequest request,
                        ResourceResponse response) throws PortletException, IOException {
        //ObjectMapper mapper = new ObjectMapper();
		/*Proof_of_Participation_Academic_Work_Form_v1.doc
		Proof_of_Participation_Academic_Work_Form_v1.pdf
		Proof_of_Participation_Form_v1.doc
		Proof_of_Participation_Form_v1.pdf*/
        FileInputStream fin = null;
        try {
            String contentType = "";
            if (formType.equals("pdf")) {
                contentType = "application/pdf";
            } else if (formType.equals("doc")) {
                contentType = "application/msword";
            }
            String filename = fileType + "." + formType;
            String filePath = ATTACH_PATH + filename;
            response.setCharacterEncoding("UTF-8");
            String contentDispositionType = "attachment; filename=\"" + filename + "\"";
            fin = new FileInputStream(filePath);
            byte[] bytes = IOUtils.toByteArray(fin);
            PortletResponseUtil.sendFile(request, response, filename, bytes, contentType, contentDispositionType);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fin != null) {
                fin.close();
            }
        }
    }
}
