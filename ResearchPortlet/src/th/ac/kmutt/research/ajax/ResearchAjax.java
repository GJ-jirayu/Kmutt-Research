package th.ac.kmutt.research.ajax;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.ServletContext;

import org.apache.commons.io.IOUtils;
import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;
import org.directwebremoting.io.FileTransfer;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import th.ac.kmutt.research.model.CopyrightCreatorM;
import th.ac.kmutt.research.model.CopyrightDocumentM;
import th.ac.kmutt.research.model.CountryM;
import th.ac.kmutt.research.model.DocAssignMappingM;
import th.ac.kmutt.research.model.FundingResourceM;
import th.ac.kmutt.research.model.FundingResourceTypeM;
import th.ac.kmutt.research.model.JournalPapersDocumentM;
import th.ac.kmutt.research.model.JournalPapersTypeM;
import th.ac.kmutt.research.model.JournalPapersWriterM;
import th.ac.kmutt.research.model.OrganizationExtM;
import th.ac.kmutt.research.model.OrganizationM;
import th.ac.kmutt.research.model.PatentCreatorM;
import th.ac.kmutt.research.model.PatentDocumentM;
import th.ac.kmutt.research.model.PatentEditDateM;
import th.ac.kmutt.research.model.PatentFeePaymentM;
import th.ac.kmutt.research.model.PatentInheritedM;
import th.ac.kmutt.research.model.PatentRightholderM;
import th.ac.kmutt.research.model.PositionM;
import th.ac.kmutt.research.model.PublishLevelM;
import th.ac.kmutt.research.model.PublishTypeM;
import th.ac.kmutt.research.model.ResearchGroupM;
import th.ac.kmutt.research.model.ResearchProjectDocumentM;
import th.ac.kmutt.research.model.ResearchProjectM;
import th.ac.kmutt.research.model.ResearchProjectPaymentM;
import th.ac.kmutt.research.model.ResearchProjectProgressM;
import th.ac.kmutt.research.model.ResearchProjectResearcherM;
import th.ac.kmutt.research.model.ResearchProjectWithdrawM;
import th.ac.kmutt.research.model.ResearcherGroupM;
import th.ac.kmutt.research.model.ResearcherM;
import th.ac.kmutt.research.model.RewardCreatorM;
import th.ac.kmutt.research.model.RewardDocumentM;
import th.ac.kmutt.research.model.TitleM;
import th.ac.kmutt.research.model.UtilizationDocumentM;
import th.ac.kmutt.research.model.UtilizationM;
import th.ac.kmutt.research.model.UtilizationTypeM;
import th.ac.kmutt.research.model.WorkTypeM;
import th.ac.kmutt.research.portal.model.UserPortalM;
import th.ac.kmutt.research.service.ResearchService;
import th.ac.kmutt.research.utils.ImakeUtils;
import th.ac.kmutt.research.xstream.common.ImakeResultMessage;

public class ResearchAjax {
    private static ResourceBundle bundle;

    static {
        bundle = ResourceBundle.getBundle("config");
    }
  private ResearchService researchService;

    public ResearchAjax() {
        WebContext ctx = WebContextFactory.get();
        ServletContext servletContext = ctx.getServletContext();
        WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
        researchService = (ResearchService) wac.getBean("researchServiceWSImpl");
    }

    public FileTransfer downloadFile(String ref1, String ref2, String module) throws Exception {
        FileInputStream fin = null;
        String fileName = "";
        if (module.equals("researchProject")) {

            ResearchProjectDocumentM doc = new ResearchProjectDocumentM();
            doc.setItemList(Integer.valueOf(ref1));
            doc.setResearchProjectId(Integer.valueOf(ref2));
            doc.setDocumentId(1);
            doc = researchService.findResearchProjectDocumentById(doc);
            //Path path = Paths.get(bundle.getString(module+"UploadPath"),doc.getFilePath());
            fileName = doc.getFileName();
            fin = new FileInputStream(bundle.getString(module + "UploadPath") + doc.getFilePath());
        } else if (module.equals("patent")) {
            PatentDocumentM doc = new PatentDocumentM();
            doc.setItemList(Integer.valueOf(ref1));
            doc.setInventionId(Integer.valueOf(ref2));
            doc.setDocumentId(1);
            doc = researchService.findPatentDocumentById(doc);
            //Path path = Paths.get(bundle.getString(module+"UploadPath"),doc.getFilePath());
            fileName = doc.getFileName();
            fin = new FileInputStream(bundle.getString(module + "UploadPath") + doc.getFilePath());
        } else if (module.equals("journalPapers")) {
            JournalPapersDocumentM doc = new JournalPapersDocumentM();
            doc.setItemList(Integer.valueOf(ref1));
            doc.setJournalPapersId(Integer.valueOf(ref2));
            doc.setDocumentId(1);
            doc = researchService.findJournalPapersDocumentById(doc);
            //Path path = Paths.get(bundle.getString(module+"UploadPath"),doc.getFilePath());
            fileName = doc.getFileName();
            fin = new FileInputStream(bundle.getString(module + "UploadPath") + doc.getFilePath());
        } else if (module.equals("copyRight")) {
            CopyrightDocumentM doc = new CopyrightDocumentM();
            doc.setItemList(Integer.valueOf(ref1));
            doc.setCopyrightId(Integer.valueOf(ref2));
            //doc.setDocumentId(1);
            doc = researchService.findCopyrightDocumentById(doc);
            //Path path = Paths.get(bundle.getString(module+"UploadPath"),doc.getFilePath());
            fileName = doc.getFileName();
            fin = new FileInputStream(bundle.getString(module + "UploadPath") + doc.getFilePath());
        } else if (module.equals("reward")) {
            RewardDocumentM doc = new RewardDocumentM();
            doc.setItemList(Integer.valueOf(ref1));
            doc.setRewardId(Integer.valueOf(ref2));
            //	doc.setDocumentId(1);
            doc = researchService.findRewardDocumentById(doc);
            //Path path = Paths.get(bundle.getString(module+"UploadPath"),doc.getFilePath());
            fileName = doc.getFileName();
            fin = new FileInputStream(bundle.getString(module + "UploadPath") + doc.getFilePath());
        } else if (module.equals("utilization")) {
            UtilizationDocumentM doc = new UtilizationDocumentM();
            doc.setItemList(Integer.valueOf(ref1));
            String[] ids = ref2.split(",");
            doc.setResearchProjectId(Integer.valueOf(ids[0]));
            doc.setUtilizationItemList(Integer.valueOf(ids[1]));
            //doc.setDocumentId(1);
            doc = researchService.findUtilizationDocumentById(doc);
            //Path path = Paths.get(bundle.getString(module+"UploadPath"),doc.getFilePath());
            fileName = doc.getFileName();
            fin = new FileInputStream(bundle.getString(module + "UploadPath") + doc.getFilePath());
        }

        byte[] bytes = null;
        if (fin != null) {
            try {
                bytes = IOUtils.toByteArray(fin);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } finally {
                if (fin != null)
                    fin.close();
            }
        }

        // ByteArrayOutputStream buffer = new ByteArrayOutputStream();

        //application/pdf
        // application/xml
        //application/zip
        //application/gzip
        String mime_type = "application/octet-stream";

        return new FileTransfer(fileName, mime_type, bytes);
    }

    public String uploadFile(InputStream fin) {
        FileOutputStream out = null;
        try {
            out = new FileOutputStream("/Users/imake/Desktop/aoe.jpg");
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        byte[] bytes = null;
        try {
            bytes = IOUtils.toByteArray(fin);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {
            out.write(bytes);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                out.close();
                fin.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return "aa";
    }
    /*
    public ImakeResultMessage listPosition2(String key) {
    	System.out.println("listPosition2->"+key);
    	System.out.println("researchService->"+researchService);
         
    	PositionM positionM = new PositionM();
    	positionM.setKeySearch(key);
        return researchService.searchPositionM(positionM);
		 
    }
    */
    //public ImakeResultMessage listPosition(String queyr){
    public ImakeResultMessage listPosition(PositionM positionM) {
    	//System.out.println("listPositionxxx->"+positionM);
    	//System.out.println("researchService->"+researchService);
        /*PositionM positionM =new PositionM();
    	positionM.setKeySearch(queyr);*/
        return researchService.searchPositionM(positionM);
		/*List<FundingResourceTypeM> fundingResourceTypes=researchService.searchFundingResourceTypeM(fundingResourceTypeM);
		return fundingResourceTypes;*/
    }

    public PositionM findPositionById(Integer researchGroupId) {
        return researchService.findPositionById(researchGroupId);
    }

    //public ImakeResultMessage listTitle(String queyr){
    public ImakeResultMessage listTitle(TitleM titleM) {
    	/*TitleM titleM =new TitleM();
    	titleM.setKeySearch(queyr);*/
        return researchService.searchTitleM(titleM);
		/*List<FundingResourceTypeM> fundingResourceTypes=researchService.searchFundingResourceTypeM(fundingResourceTypeM);
		return fundingResourceTypes;*/
    }
    //public ImakeResultMessage listTitle(String queyr){
    public ImakeResultMessage listAcademicTitle(TitleM titleM) {
    	/*TitleM titleM =new TitleM();
    	titleM.setKeySearch(queyr);*/
        return researchService.searchAcademicTitleM(titleM);
		/*List<FundingResourceTypeM> fundingResourceTypes=researchService.searchFundingResourceTypeM(fundingResourceTypeM);
		return fundingResourceTypes;*/
    }
    public TitleM findTitleById(Integer researchGroupId) {
        return researchService.findTitleById(researchGroupId);
    }

    //    public ImakeResultMessage listDocAssignMapping(String queyr){
    public ImakeResultMessage listDocAssignMapping(FundingResourceTypeM fundingResourceTypeM) {
		/*FundingResourceTypeM fundingResourceTypeM =new FundingResourceTypeM();
		fundingResourceTypeM.setKeySearch(queyr);*/
        return researchService.searchFundingResourceTypeM(fundingResourceTypeM);
		/*List<FundingResourceTypeM> fundingResourceTypes=researchService.searchFundingResourceTypeM(fundingResourceTypeM);
		return fundingResourceTypes;*/
    }

    //public List<FundingResourceTypeM> getFundingResourceTypeList(String queyr){
    //public ImakeResultMessage getFundingResourceTypeList(String queyr){
    public ImakeResultMessage getFundingResourceTypeList(FundingResourceTypeM fundingResourceTypeM) {
		/*FundingResourceTypeM fundingResourceTypeM =new FundingResourceTypeM();
		fundingResourceTypeM.setKeySearch(queyr);*/
        return researchService.searchFundingResourceTypeM(fundingResourceTypeM);
		/*List<FundingResourceTypeM> fundingResourceTypes=researchService.searchFundingResourceTypeM(fundingResourceTypeM);
		return fundingResourceTypes;*/
    }

    public FundingResourceTypeM findFundingResourceTypeById(Integer researchGroupId) {
        return researchService.findFundingResourceTypeById(researchGroupId);
    }

    //public List<CountryM> getCountryList(String queyr){
    //public ImakeResultMessage getCountryList(String queyr){
    public ImakeResultMessage getCountryList(CountryM countryM) {
		/*CountryM countryM =new CountryM();
		countryM.setKeySearch(queyr);*/
        return researchService.searchCountryM(countryM);
        //return countrys;
    }

    public CountryM findCountryById(Integer researchGroupId) {
        return researchService.findCountryById(researchGroupId);
    }

    //public ImakeResultMessage getPublishLevelList(String queyr){
    public ImakeResultMessage getPublishLevelList(PublishLevelM publishLevelM) {
		/*PublishLevelM publishLevelM =new PublishLevelM();
		publishLevelM.setKeySearch(queyr);*/
        return researchService.searchPublishLevelM(publishLevelM);
        //return countrys;
    }
    public PublishLevelM findPublishLevelById(Integer researchGroupId) {
        return researchService.findPublishLevelById(researchGroupId);
    }
        //public ImakeResultMessage getPublishTypeList(String queyr){
    public ImakeResultMessage getPublishTypeList(PublishTypeM publishTypeM) {
		/*PublishTypeM publishTypeM =new PublishTypeM();
		publishTypeM.setKeySearch(queyr);*/
        return researchService.searchPublishTypeM(publishTypeM);
        //return countrys;
    }
    //public List<UserPortalM> getUserPortalList(String queyr){
    //public ImakeResultMessage getUserPortalList(String queyr){
    public ImakeResultMessage getUserPortalList(UserPortalM userPortalM) {
		/*UserPortalM userPortalM =new UserPortalM();
		userPortalM.setKeySearch(queyr);*/
        return researchService.searchUserPortal(userPortalM);
        //return userPortals;
    }

    //public List<ResearchGroupM> getResearchGroupList(String queyr){
    public ImakeResultMessage getResearchGroupList(ResearchGroupM researchGroupM) {
		/*ResearchGroupM researchGroupM =new ResearchGroupM();
		researchGroupM.setKeySearch(queyr);*/
        //List<ResearchGroupM> researchGroups=researchService.searchResearchGroupM(researchGroupM);
        return researchService.searchResearchGroupM(researchGroupM);
    }

    //Reward
    public List<ResearcherGroupM> updateResearcherGroup(ResearcherGroupM researcherGroup, String mode) {
        if (mode.equals("add")) {
            researchService.saveResearcherGroupM(researcherGroup);
        } else if (mode.equals("edit")) {
            researchService.updateResearcherGroupM(researcherGroup);
        }
        return researchService.listResearcherGroupM(researcherGroup.getResearcherId());
    }

    public List<ResearcherGroupM> deleteResearcherGroup(ResearcherGroupM researcherGroup) {
        researchService.deleteResearcherGroupM(researcherGroup);
        return researchService.listResearcherGroupM(researcherGroup.getResearcherId());
    }

    public ResearcherGroupM findResearcherGroupById(ResearcherGroupM researcherGroup) {
        return researchService.findResearcherGroupById(researcherGroup.getResearchGroupId(), researcherGroup.getResearcherId());
    }

    public ResearchGroupM findResearchGroupById(Integer researchGroupId) {
        return researchService.findResearchGroupById(researchGroupId);
    }

    //public List<FundingResourceM> getFundingResourceList(String queyr){
    //public ImakeResultMessage getFundingResourceList(String queyr){
    public ImakeResultMessage getFundingResourceList(FundingResourceM fundingResourceM) {
		/*FundingResourceM fundingResourceM =new FundingResourceM();
		fundingResourceM.setKeySearch(queyr);*/
        return researchService.searchFundingResourceM(fundingResourceM);
        //return fundingResources;
    }

    public FundingResourceM findFundingResourceById(Integer researchGroupId) {
        return researchService.findFundingResourceById(researchGroupId);
    }

    //public List<OrganizationM> getOrganizationList(String queyr){
    //public ImakeResultMessage getOrganizationList(String queyr){
    public ImakeResultMessage getOrganizationList(OrganizationM organizationM) {
		/*OrganizationM organizationM =new OrganizationM();
		organizationM.setKeySearch(queyr);*/
        return researchService.searchOrganizationM(organizationM);
        //return organizations;
    }

    public OrganizationM findOrganizationById(Integer researchGroupId) {
        return researchService.findOrganizationById(researchGroupId);
    }

    //public List<ResearchProjectM> getResearchProjectList(String queyr){
    //public ImakeResultMessage getResearchProjectList(String queyr){
    public ImakeResultMessage getResearchProjectList(ResearchProjectM researchProjectM) {
		/*ResearchProjectM researchProjectM =new ResearchProjectM();
		researchProjectM.setKeySearch(queyr);*/
        return researchService.searchResearchProjectM(researchProjectM);
        //return researchProjects;
    }

    public ResearchProjectM findResearchProjectById(Integer researchGroupId) {
        return researchService.findResearchProjectById(researchGroupId, null);
    }

    //public List<WorkTypeM> getWorkTypeList(String queyr){
    //public ImakeResultMessage getWorkTypeList(String queyr){
    public ImakeResultMessage getWorkTypeList(WorkTypeM workTypeM) {
		/*WorkTypeM workTypeM =new WorkTypeM();
		workTypeM.setKeySearch(queyr);*/
        return researchService.searchWorkTypeM(workTypeM);
        //return workTypes;
    }

    public WorkTypeM findWorkTypeById(Integer researchGroupId) {
        return researchService.findWorkTypeById(researchGroupId);
    }


    //public List<JournalPapersTypeM> getJournalPapersTypeList(String queyr){
    //public ImakeResultMessage getJournalPapersTypeList(String queyr){
    public ImakeResultMessage getJournalPapersTypeList(JournalPapersTypeM journalPapersTypeM) {
		/*JournalPapersTypeM journalPapersTypeM =new JournalPapersTypeM();
		journalPapersTypeM.setKeySearch(queyr);*/
        return researchService.searchJournalPapersTypeM(journalPapersTypeM);
        //return workTypes;
    }

    public JournalPapersTypeM findJournalPapersTypeById(Integer researchGroupId) {
        return researchService.findJournalPapersTypeById(researchGroupId);
    }

    //public List<UtilizationTypeM> getUtilizationTypeList(String queyr){
    public ImakeResultMessage getUtilizationTypeList(UtilizationTypeM utilizationTypeM) {
		/*UtilizationTypeM utilizationTypeM =new UtilizationTypeM();
		utilizationTypeM.setKeySearch(queyr);*/
        return researchService.searchUtilizationTypeM(utilizationTypeM);
        //return workTypes;
    }

    public UtilizationTypeM findUtilizationTypeById(Integer researchGroupId) {
        return researchService.findUtilizationTypeById(researchGroupId);
    }

    //public List<ResearcherM> getResearcherList(String queyr){
    //public ImakeResultMessage getResearcherList(String queyr){
    public ImakeResultMessage getResearcherList(ResearcherM researcherM) {
		/*ResearcherM researcherM =new ResearcherM();
		researcherM.setKeySearch(queyr);*/
        return researchService.searchResearcherM(researcherM);
        //return researchers;
    }

    public ResearcherM findResearcherById(Integer researchGroupId) {
        return researchService.findResearcherById(researchGroupId);
    }

    //Utilization
    public List<UtilizationM> updateUtilization(UtilizationM utilizationM, String mode) {
        if (mode.equals("add")) {
            //  utilizationM.setUtilizationTypeId(utilizationTypeId);
            researchService.saveUtilizationM(utilizationM);
        } else if (mode.equals("edit")) {
            researchService.updateUtilizationM(utilizationM);
        }
        UtilizationM model = researchService.findUtilizationById(utilizationM.getResearchProject().getResearchProjectId(), null);

        return model.getUtilizations();
    }

    public List<UtilizationM> deleteUtilizationItem(UtilizationM utilizationM) {

        researchService.deleteUtilizationItem(utilizationM);

        UtilizationM model = researchService.findUtilizationById(utilizationM.getResearchProject().getResearchProjectId(), null);

        return model.getUtilizations();
    }

    public UtilizationM findUtilizationItemById(UtilizationM utilizationM) {
        return researchService.findUtilizationItemById(utilizationM);
    }

    //Reward
    public List<RewardCreatorM> updateRewardCreator(RewardCreatorM rewardCreatorM, String mode) {
        if (mode.equals("add")) {
            researchService.saveRewardCreator(rewardCreatorM);
        } else if (mode.equals("edit")) {
            researchService.updateRewardCreator(rewardCreatorM);
        }
        return researchService.listRewardCreator(rewardCreatorM.getRewardId());
    }

    public List<RewardCreatorM> deleteRewardCreator(RewardCreatorM rewardCreatorM) {
        researchService.deleteRewardCreator(rewardCreatorM);
        return researchService.listRewardCreator(rewardCreatorM.getRewardId());
    }

    public RewardCreatorM findRewardCreatorById(RewardCreatorM researchProjectDocumentM) {
        return researchService.findRewardCreatorById(researchProjectDocumentM);
    }


    // Patent
    public List<PatentCreatorM> updatePatentCreator(PatentCreatorM patentCreatorM, String mode) {
        if (mode.equals("add")) {
            researchService.savePatentCreator(patentCreatorM);
        } else if (mode.equals("edit")) {
            researchService.updatePatentCreator(patentCreatorM);
        }
        return researchService.listPatentCreator(patentCreatorM.getInventionId());
    }

    public List<PatentCreatorM> deletePatentCreatorM(PatentCreatorM patentCreatorM) {
        researchService.deletePatentCreator(patentCreatorM);
        return researchService.listPatentCreator(patentCreatorM.getInventionId());
    }

    public PatentCreatorM findPatentCreatorById(PatentCreatorM researchProjectDocumentM) {
        return researchService.findPatentCreatorById(researchProjectDocumentM);
    }

    public List<PatentDocumentM> updatePatentDocument(org.directwebremoting.io.FileTransfer fin, PatentDocumentM patentDocumentM, String module, String mode) {
        String[] files = uploadDocument(fin, module);
        if (files != null) {
            patentDocumentM.setFileName(files[0]);
            patentDocumentM.setFilePath(files[1]);
            patentDocumentM.setRefHotlink(files[2]);
        }
        if (mode.equals("add")) {
            researchService.savePatentDocument(patentDocumentM);
        } else if (mode.equals("edit")) {
            PatentDocumentM doc = researchService.findPatentDocumentById(patentDocumentM);
            if (files != null) {
                if (doc.getFilePath() != null) {
                    //String  ndFilePath = bundle.getString(module+"UploadPath")+doc.getFilePath();
                    Path path_delete = Paths.get(bundle.getString(module + "UploadPath"), doc.getFilePath());
                    try {
                        Files.deleteIfExists(path_delete);
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            } else {
                patentDocumentM.setFileName(doc.getFileName());
                patentDocumentM.setFilePath(doc.getFilePath());
                patentDocumentM.setRefHotlink(doc.getRefHotlink());
            }
            researchService.updatePatentDocument(patentDocumentM);
            ;

        }

        return researchService.listPatentDocument(patentDocumentM.getInventionId());
    }

    public List<PatentDocumentM> deletePatentDocumentM(PatentDocumentM patentDocumentM, String module) {
        PatentDocumentM doc = researchService.findPatentDocumentById(patentDocumentM);
        if (doc.getFilePath() != null) {
            //String  ndFilePath = bundle.getString(module+"UploadPath")+doc.getFilePath();
            Path path = Paths.get(bundle.getString(module + "UploadPath"), doc.getFilePath());
            try {
                Files.deleteIfExists(path);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
        researchService.deletePatentDocument(patentDocumentM);
        return researchService.listPatentDocument(patentDocumentM.getInventionId());
    }

    public PatentDocumentM findPatentDocumentById(PatentDocumentM researchProjectDocumentM) {
        return researchService.findPatentDocumentById(researchProjectDocumentM);
    }


    public List<PatentEditDateM> updatePatentEditDate(PatentEditDateM patentEditDateM, String mode) {
        if (mode.equals("add")) {
            researchService.savePatentEditDate(patentEditDateM);
        } else if (mode.equals("edit")) {
            researchService.updatePatentEditDate(patentEditDateM);
        }
        return researchService.listPatentEditDate(patentEditDateM.getInventionId());
    }

    public List<PatentEditDateM> deletePatentEditDateM(PatentEditDateM patentEditDateM) {
        researchService.deletePatentEditDate(patentEditDateM);
        return researchService.listPatentEditDate(patentEditDateM.getInventionId());
    }

    public PatentEditDateM findPatentEditDateById(PatentEditDateM researchProjectDocumentM) {
        return researchService.findPatentEditDateById(researchProjectDocumentM);
    }


    public List<PatentFeePaymentM> updatePatentFeePayment(PatentFeePaymentM patentFeePaymentM, String mode) {
        if (mode.equals("add")) {
            researchService.savePatentFeePayment(patentFeePaymentM);
        } else if (mode.equals("edit")) {
            researchService.updatePatentFeePayment(patentFeePaymentM);
        }
        return researchService.listPatentFeePayment(patentFeePaymentM.getInventionId());
    }

    public List<PatentFeePaymentM> deletePatentFeePaymentM(PatentFeePaymentM patentFeePaymentM) {
        researchService.deletePatentFeePayment(patentFeePaymentM);
        return researchService.listPatentFeePayment(patentFeePaymentM.getInventionId());
    }

    public PatentFeePaymentM findPatentFeePaymentById(PatentFeePaymentM researchProjectDocumentM) {
        return researchService.findPatentFeePaymentById(researchProjectDocumentM);
    }


    public List<PatentInheritedM> updatePatentInherited(PatentInheritedM patentInheritedM, String mode) {
        if (mode.equals("add")) {
            researchService.savePatentInherited(patentInheritedM);
        } else if (mode.equals("edit")) {
            researchService.updatePatentInherited(patentInheritedM);
        }
        return researchService.listPatentInherited(patentInheritedM.getInventionId());
    }

    public List<PatentInheritedM> deletePatentInheritedM(PatentInheritedM patentInheritedM) {
        researchService.deletePatentInherited(patentInheritedM);
        return researchService.listPatentInherited(patentInheritedM.getInventionId());
    }

    public PatentInheritedM findPatentInheritedById(PatentInheritedM researchProjectDocumentM) {
        return researchService.findPatentInheritedById(researchProjectDocumentM);
    }


    public List<PatentRightholderM> updatePatentRightholder(PatentRightholderM patentRightholderM, String mode) {
        if (mode.equals("add")) {
            researchService.savePatentRightholder(patentRightholderM);
        } else if (mode.equals("edit")) {
            researchService.updatePatentRightholder(patentRightholderM);
        }
        return researchService.listPatentRightholder(patentRightholderM.getInventionId());
    }

    public List<PatentRightholderM> deletePatentRightholderM(PatentRightholderM patentRightholderM) {
        researchService.deletePatentRightholder(patentRightholderM);
        return researchService.listPatentRightholder(patentRightholderM.getInventionId());
    }

    public PatentRightholderM findPatentRightholderById(PatentRightholderM researchProjectDocumentM) {
        return researchService.findPatentRightholderById(researchProjectDocumentM);
    }


    // Copyright
    public List<CopyrightCreatorM> updateCopyrightCreator(CopyrightCreatorM copyrightCreatorM, String mode) {
        if (mode.equals("add")) {
            researchService.saveCopyrightCreator(copyrightCreatorM);
        } else if (mode.equals("edit")) {
            researchService.updateCopyrightCreator(copyrightCreatorM);
        }
        return researchService.listCopyrightCreator(copyrightCreatorM.getCopyrightId());
    }

    public List<CopyrightCreatorM> deleteCopyrightCreator(CopyrightCreatorM copyrightCreatorM) {
        researchService.deleteCopyrightCreator(copyrightCreatorM);
        return researchService.listCopyrightCreator(copyrightCreatorM.getCopyrightId());
    }

    public CopyrightCreatorM findCopyrightCreatorById(CopyrightCreatorM researchProjectDocumentM) {
        return researchService.findCopyrightCreatorById(researchProjectDocumentM);
    }

    // Research Project
    public List<ResearchProjectDocumentM> updateResearchProjectDocument(ResearchProjectDocumentM researchProjectDocumentM, String mode) {
        if (mode.equals("add")) {
            researchService.saveResearchProjectDocument(researchProjectDocumentM);
        } else if (mode.equals("edit")) {
            researchService.updateResearchProjectDocument(researchProjectDocumentM);
        }
        return researchService.listResearchProjectDocument(researchProjectDocumentM.getResearchProjectId());
    }

    public List<ResearchProjectDocumentM> deleteResearchProjectDocument(ResearchProjectDocumentM researchProjectDocumentM, String module) {
        ResearchProjectDocumentM doc = researchService.findResearchProjectDocumentById(researchProjectDocumentM);
        if (doc.getFilePath() != null) {
            //String  ndFilePath = bundle.getString(module+"UploadPath")+doc.getFilePath();
            Path path = Paths.get(bundle.getString(module + "UploadPath"), doc.getFilePath());
            try {
                Files.deleteIfExists(path);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
        researchService.deleteResearchProjectDocument(researchProjectDocumentM);


        return researchService.listResearchProjectDocument(researchProjectDocumentM.getResearchProjectId());
    }

    public ResearchProjectDocumentM findResearchProjectDocumentById(ResearchProjectDocumentM researchProjectDocumentM) {
        return researchService.findResearchProjectDocumentById(researchProjectDocumentM);
    }

    public String[] uploadDocument(org.directwebremoting.io.FileTransfer fin, String module) {
        String filename = null;
        String pathFolder = null;
        String hotLink = null;
        boolean isUpload = false;
        if (fin.getSize() > 0 && fin.getFilename() != null && fin.getFilename().trim().length() > 0) {
            isUpload = true;
            FileOutputStream out = null;
            // String ATTACH_PATH=bundle.getString(module+"UploadPath");
	    /*	researchProjectUploadPath=/aoe/upload/researchProject/
	    			journalPapersUploadPath=/aoe/upload/journalPapers/
	    			copyRightUploadPath=/aoe/upload/copyRight/
	    			patentUploadPath=/aoe/upload/patent/
	    			rewardUploadPath=/aoe/upload/reward/
	    			utilizationUploadPath=/aoe/upload/utilization/*/

            long current = System.currentTimeMillis();
            org.joda.time.DateTime dt1 = new org.joda.time.DateTime(new Date().getTime());

            String monthStr = dt1.getMonthOfYear() + "";
            String yearStr = dt1.getYear() + "";
            monthStr = monthStr.length() > 1 ? monthStr : "0" + monthStr;

            pathFolder = yearStr + "_" + monthStr + "";
            String ndFilePath = bundle.getString(module + "UploadPath") + pathFolder;
            String path = ndFilePath;
            ImakeUtils.createDirectoryIfNeeded(path);

            filename = fin.getFilename();// multipart.getOriginalFilename();
            String[] filenameSplit = filename.split("\\.");
            String extension = "";
            if (filenameSplit != null && filenameSplit.length > 0) {
                extension = filenameSplit[filenameSplit.length - 1];
            }
            hotLink = current + "" + ImakeUtils.genToken();
            String ndPathFileGen = hotLink + "." + extension;
            pathFolder = pathFolder + "/" + ndPathFileGen;
            //	 FileInputStream fin= new FileInputStream(file)
            // fos = new FileOutputStream(path+"/"+ndPathFileGen);

            try {
                out = new FileOutputStream(path + "/" + ndPathFileGen);
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            byte[] bytes = null;
            try {
                bytes = IOUtils.toByteArray(fin.getInputStream());
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            try {
                out.write(bytes);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } finally {
                try {
                    out.close();
                    //fin.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
        if (isUpload)
            return new String[]{filename, pathFolder, hotLink};
        else
            return null;
    }

    // public ResearchProjectDocumentM uploadResearchProjectDocument(InputStream fin,ResearchProjectDocumentM researchProjectDocumentM,String module){
    public List<ResearchProjectDocumentM> uploadResearchProjectDocument(org.directwebremoting.io.FileTransfer fin,
                                                                        ResearchProjectDocumentM researchProjectDocumentM, String module, String mode) {
        String[] files = uploadDocument(fin, module);
        if (files != null) {
            researchProjectDocumentM.setFileName(files[0]);
            researchProjectDocumentM.setFilePath(files[1]);
            researchProjectDocumentM.setRefHotlink(files[2]);
        }
        if (mode.equals("add")) {
            researchService.saveResearchProjectDocument(researchProjectDocumentM);
        } else if (mode.equals("edit")) {
            ResearchProjectDocumentM doc = researchService.findResearchProjectDocumentById(researchProjectDocumentM);
            if (files != null) {
                if (doc.getFilePath() != null) {
                    //String  ndFilePath = bundle.getString(module+"UploadPath")+doc.getFilePath();
                    Path path_delete = Paths.get(bundle.getString(module + "UploadPath"), doc.getFilePath());
                    try {
                        Files.deleteIfExists(path_delete);
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            } else {
                researchProjectDocumentM.setFileName(doc.getFileName());
                researchProjectDocumentM.setFilePath(doc.getFilePath());
                researchProjectDocumentM.setRefHotlink(doc.getRefHotlink());
            }
            researchService.updateResearchProjectDocument(researchProjectDocumentM);

        }
        //researchService.s
        return researchService.listResearchProjectDocument(researchProjectDocumentM.getResearchProjectId());
    }

    public List<ResearchProjectPaymentM> updateResearchProjectPayment(ResearchProjectPaymentM researchProjectPaymentM, String mode) {
        if (mode.equals("add")) {
            researchService.saveResearchProjectPayment(researchProjectPaymentM);
        } else if (mode.equals("edit")) {
            researchService.updateResearchProjectPayment(researchProjectPaymentM);
        }
        return researchService.listResearchProjectPayment(researchProjectPaymentM.getResearchProjectId());
    }

    public List<ResearchProjectPaymentM> deleteResearchProjectPayment(ResearchProjectPaymentM researchProjectPaymentM) {
        researchService.deleteResearchProjectPayment(researchProjectPaymentM);
        return researchService.listResearchProjectPayment(researchProjectPaymentM.getResearchProjectId());
    }

    public ResearchProjectPaymentM findResearchProjectPaymentById(ResearchProjectPaymentM researchProjectDocumentM) {
        return researchService.findResearchProjectPaymentById(researchProjectDocumentM);
    }

    public List<ResearchProjectProgressM> updateResearchProjectProgress(ResearchProjectProgressM researchProjectProgressM, String mode) {
        if (mode.equals("add")) {
            researchService.saveResearchProjectProgress(researchProjectProgressM);
        } else if (mode.equals("edit")) {
            researchService.updateResearchProjectProgress(researchProjectProgressM);
        }
        return researchService.listResearchProjectProgress(researchProjectProgressM.getResearchProjectId());
    }

    public List<ResearchProjectProgressM> deleteResearchProjectProgress(ResearchProjectProgressM researchProjectProgressM) {
        researchService.deleteResearchProjectProgress(researchProjectProgressM);
        return researchService.listResearchProjectProgress(researchProjectProgressM.getResearchProjectId());
    }

    public ResearchProjectProgressM findResearchProjectProgressById(ResearchProjectProgressM researchProjectDocumentM) {
        return researchService.findResearchProjectProgressById(researchProjectDocumentM);
    }

    public List<ResearchProjectResearcherM> updateResearchProjectResearcher(ResearchProjectResearcherM researchProjectResearcherM, String mode) {
        if (mode.equals("add")) {
            researchService.saveResearchProjectResearcher(researchProjectResearcherM);
        } else if (mode.equals("edit")) {
            researchService.updateResearchProjectResearcher(researchProjectResearcherM);
        }
        return researchService.listResearchProjectResearcher(researchProjectResearcherM.getResearchProjectId());
    }

    public List<ResearchProjectResearcherM> deleteResearchProjectResearcher(ResearchProjectResearcherM researchProjectResearcherM) {
        researchService.deleteResearchProjectResearcher(researchProjectResearcherM);
        return researchService.listResearchProjectResearcher(researchProjectResearcherM.getResearchProjectId());
    }

    public ResearchProjectResearcherM findResearchProjectResearcherById(ResearchProjectResearcherM researchProjectDocumentM) {
        return researchService.findResearchProjectResearcherById(researchProjectDocumentM);
    }

    public List<ResearchProjectWithdrawM> updateResearchProjectWithdraw(ResearchProjectWithdrawM researchProjectWithdrawM, String mode) {
        if (mode.equals("add")) {
            researchService.saveResearchProjectWithdraw(researchProjectWithdrawM);
        } else if (mode.equals("edit")) {
            researchService.updateResearchProjectWithdraw(researchProjectWithdrawM);
        }
        return researchService.listResearchProjectWithdraw(researchProjectWithdrawM.getResearchProjectId());
    }

    public List<ResearchProjectWithdrawM> deleteResearchProjectWithdraw(ResearchProjectWithdrawM researchProjectWithdrawM) {
        researchService.deleteResearchProjectWithdraw(researchProjectWithdrawM);
        return researchService.listResearchProjectWithdraw(researchProjectWithdrawM.getResearchProjectId());
    }

    public ResearchProjectWithdrawM findResearchProjectWithdrawById(ResearchProjectWithdrawM researchProjectDocumentM) {
        return researchService.findResearchProjectWithdrawById(researchProjectDocumentM);
    }


    // Journal Paper

    public List<JournalPapersDocumentM> updateJournalPapersDocument(org.directwebremoting.io.FileTransfer fin,
                                                                    JournalPapersDocumentM journalPapersDocumentM, String module, String mode) {
        String[] files = uploadDocument(fin, module);
        if (files != null) {
            journalPapersDocumentM.setFileName(files[0]);
            journalPapersDocumentM.setFilePath(files[1]);
            journalPapersDocumentM.setRefHotlink(files[2]);
        }
        if (mode.equals("add")) {
            researchService.saveJournalPapersDocument(journalPapersDocumentM);
        } else if (mode.equals("edit")) {
            JournalPapersDocumentM doc = researchService.findJournalPapersDocumentById(journalPapersDocumentM);
            if (files != null) {
                if (doc.getFilePath() != null) {
                    //String  ndFilePath = bundle.getString(module+"UploadPath")+doc.getFilePath();
                    Path path_delete = Paths.get(bundle.getString(module + "UploadPath"), doc.getFilePath());
                    try {
                        Files.deleteIfExists(path_delete);
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            } else {
                journalPapersDocumentM.setFileName(doc.getFileName());
                journalPapersDocumentM.setFilePath(doc.getFilePath());
                journalPapersDocumentM.setRefHotlink(doc.getRefHotlink());
            }
            researchService.updateJournalPapersDocument(journalPapersDocumentM);

        }

        return researchService.listJournalPapersDocument(journalPapersDocumentM.getJournalPapersId());
    }

    public List<JournalPapersDocumentM> deleteJournalPapersDocument(JournalPapersDocumentM journalPapersDocumentM, String module) {
        JournalPapersDocumentM doc = researchService.findJournalPapersDocumentById(journalPapersDocumentM);
        if (doc.getFilePath() != null) {
            Path path = Paths.get(bundle.getString(module + "UploadPath"), doc.getFilePath());
            try {
                Files.deleteIfExists(path);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
        researchService.deleteJournalPapersDocument(journalPapersDocumentM);
        return researchService.listJournalPapersDocument(journalPapersDocumentM.getJournalPapersId());
    }

    public JournalPapersDocumentM findJournalPapersDocumentById(JournalPapersDocumentM researchProjectDocumentM) {
        return researchService.findJournalPapersDocumentById(researchProjectDocumentM);
    }


    public List<JournalPapersWriterM> updateJournalPapersWriter(JournalPapersWriterM journalPapersWriterM, String mode) {
        if (mode.equals("add")) {
            researchService.saveJournalPapersWriter(journalPapersWriterM);
        } else if (mode.equals("edit")) {
            researchService.updateJournalPapersWriter(journalPapersWriterM);
        }
        return researchService.listJournalPapersWriter(journalPapersWriterM.getJournalPapersId());
    }

    public List<JournalPapersWriterM> deleteJournalPapersWriter(JournalPapersWriterM journalPapersWriterM) {
        researchService.deleteJournalPapersWriter(journalPapersWriterM);
        return researchService.listJournalPapersWriter(journalPapersWriterM.getJournalPapersId());
    }

    public JournalPapersWriterM findJournalPapersWriterById(JournalPapersWriterM researchProjectDocumentM) {
        return researchService.findJournalPapersWriterById(researchProjectDocumentM);
    }

    //

    // DocAssignMapping
    public ImakeResultMessage saveDocAssignMapping(DocAssignMappingM docAssignMappingM, String mode) {
        if (mode.equals("add")) {
            researchService.saveDocAssignMapping(docAssignMappingM);
        } else if (mode.equals("edit")) {
            //researchService.updateDocAssignMapping(docAssignMappingM);
        }
        return researchService.listDocAssignMappingt(docAssignMappingM);
    }

    public ImakeResultMessage deleteDocAssignMapping(DocAssignMappingM docAssignMappingM) {
        researchService.deleteDocAssignMapping(docAssignMappingM);
        return researchService.listDocAssignMappingt(docAssignMappingM);
    }

    public Integer checkUQResearchGroup(ResearchGroupM transientInstance) {
        return researchService.checkUQResearchGroup(transientInstance);
    }

    public Integer checkUQUtilizationType(UtilizationTypeM transientInstance) {
        return researchService.checkUQUtilizationType(transientInstance);
    }

    public Integer checkUQPublishType(PublishTypeM transientInstance) {
        return researchService.checkUQPublishType(transientInstance);
    }

    public Integer checkUQPosition(PositionM transientInstance) {
        return researchService.checkUQPosition(transientInstance);
    }

    public Integer checkUQCountry(CountryM transientInstance) {
        return researchService.checkUQCountry(transientInstance);
    }

    public Integer checkUQWorkType(WorkTypeM transientInstance) {
        return researchService.checkUQWorkType(transientInstance);
    }

    public Integer checkUQJournalPapersType(JournalPapersTypeM transientInstance) {
        return researchService.checkUQJournalPapersType(transientInstance);
    }

    public Integer checkUQFundingResourceType(FundingResourceTypeM transientInstance) {
        return researchService.checkUQFundingResourceType(transientInstance);
    }

    public Integer checkUQPublishLevel(PublishLevelM transientInstance) {
        return researchService.checkUQPublishLevel(transientInstance);
    }

    public Integer checkUQOrganizationExt(OrganizationExtM transientInstance) {
        return researchService.checkUQOrganizationExt(transientInstance);
    }

    public Integer checkUQFundingResource(FundingResourceM transientInstance) {
        return researchService.checkUQFundingResource(transientInstance);
    }

    public Integer countJournalPapersWriter(JournalPapersWriterM transientInstance) {
        return researchService.countJournalPapersWriter(transientInstance);
    }

    public Integer countResearchProjectResearcher(ResearchProjectResearcherM transientInstance) {
        return researchService.countResearchProjectResearcher(transientInstance);
    }

    public List<CopyrightDocumentM> updateCopyrightDocument(org.directwebremoting.io.FileTransfer fin, CopyrightDocumentM copyrightDocumentM, String module, String mode) {
        String[] files = uploadDocument(fin, module);
        if (files != null) {
            copyrightDocumentM.setFileName(files[0]);
            copyrightDocumentM.setFilePath(files[1]);
            copyrightDocumentM.setRefHotlink(files[2]);
        }
        if (mode.equals("add")) {
            researchService.saveCopyrightDocument(copyrightDocumentM);
        } else if (mode.equals("edit")) {
            CopyrightDocumentM doc = researchService.findCopyrightDocumentById(copyrightDocumentM);
            if (files != null) {
                if (doc.getFilePath() != null) {
                    //String  ndFilePath = bundle.getString(module+"UploadPath")+doc.getFilePath();
                    Path path_delete = Paths.get(bundle.getString(module + "UploadPath"), doc.getFilePath());
                    try {
                        Files.deleteIfExists(path_delete);
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            } else {
                copyrightDocumentM.setFileName(doc.getFileName());
                copyrightDocumentM.setFilePath(doc.getFilePath());
                copyrightDocumentM.setRefHotlink(doc.getRefHotlink());
            }
            researchService.updateCopyrightDocument(copyrightDocumentM);
            ;

        }

        return researchService.listCopyrightDocument(copyrightDocumentM.getCopyrightId());
    }

    public List<CopyrightDocumentM> deleteCopyrightDocumentM(CopyrightDocumentM copyrightDocumentM, String module) {
        CopyrightDocumentM doc = researchService.findCopyrightDocumentById(copyrightDocumentM);
        if (doc.getFilePath() != null) {
            //String  ndFilePath = bundle.getString(module+"UploadPath")+doc.getFilePath();
            Path path = Paths.get(bundle.getString(module + "UploadPath"), doc.getFilePath());
            try {
                Files.deleteIfExists(path);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
        researchService.deleteCopyrightDocument(copyrightDocumentM);
        return researchService.listCopyrightDocument(copyrightDocumentM.getCopyrightId());
    }

    public CopyrightDocumentM findCopyrightDocumentById(CopyrightDocumentM researchProjectDocumentM) {
        return researchService.findCopyrightDocumentById(researchProjectDocumentM);
    }


    public List<RewardDocumentM> updateRewardDocument(org.directwebremoting.io.FileTransfer fin, RewardDocumentM rewardDocumentM, String module, String mode) {
        String[] files = uploadDocument(fin, module);
        if (files != null) {
            rewardDocumentM.setFileName(files[0]);
            rewardDocumentM.setFilePath(files[1]);
            rewardDocumentM.setRefHotlink(files[2]);
        }
        if (mode.equals("add")) {
            researchService.saveRewardDocument(rewardDocumentM);
        } else if (mode.equals("edit")) {
            RewardDocumentM doc = researchService.findRewardDocumentById(rewardDocumentM);
            if (files != null) {
                if (doc.getFilePath() != null) {
                    //String  ndFilePath = bundle.getString(module+"UploadPath")+doc.getFilePath();
                    Path path_delete = Paths.get(bundle.getString(module + "UploadPath"), doc.getFilePath());
                    try {
                        Files.deleteIfExists(path_delete);
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            } else {
                rewardDocumentM.setFileName(doc.getFileName());
                rewardDocumentM.setFilePath(doc.getFilePath());
                rewardDocumentM.setRefHotlink(doc.getRefHotlink());
            }
            researchService.updateRewardDocument(rewardDocumentM);
            ;

        }

        return researchService.listRewardDocument(rewardDocumentM.getRewardId());
    }

    public List<RewardDocumentM> deleteRewardDocumentM(RewardDocumentM rewardDocumentM, String module) {
        RewardDocumentM doc = researchService.findRewardDocumentById(rewardDocumentM);
        if (doc.getFilePath() != null) {
            //String  ndFilePath = bundle.getString(module+"UploadPath")+doc.getFilePath();
            Path path = Paths.get(bundle.getString(module + "UploadPath"), doc.getFilePath());
            try {
                Files.deleteIfExists(path);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
        researchService.deleteRewardDocument(rewardDocumentM);
        return researchService.listRewardDocument(rewardDocumentM.getRewardId());
    }

    public RewardDocumentM findRewardDocumentById(RewardDocumentM researchProjectDocumentM) {
        return researchService.findRewardDocumentById(researchProjectDocumentM);
    }


    public List<UtilizationDocumentM> updateUtilizationDocument(org.directwebremoting.io.FileTransfer fin, UtilizationDocumentM utilizationDocumentM, String module, String mode) {
        String[] files = uploadDocument(fin, module);
        if (files != null) {
            utilizationDocumentM.setFileName(files[0]);
            utilizationDocumentM.setFilePath(files[1]);
            utilizationDocumentM.setRefHotlink(files[2]);
        }
        if (mode.equals("add")) {
            researchService.saveUtilizationDocument(utilizationDocumentM);
        } else if (mode.equals("edit")) {
            UtilizationDocumentM doc = researchService.findUtilizationDocumentById(utilizationDocumentM);
            if (files != null) {
                if (doc.getFilePath() != null) {
                    //String  ndFilePath = bundle.getString(module+"UploadPath")+doc.getFilePath();
                    Path path_delete = Paths.get(bundle.getString(module + "UploadPath"), doc.getFilePath());
                    try {
                        Files.deleteIfExists(path_delete);
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            } else {
                utilizationDocumentM.setFileName(doc.getFileName());
                utilizationDocumentM.setFilePath(doc.getFilePath());
                utilizationDocumentM.setRefHotlink(doc.getRefHotlink());
            }
            researchService.updateUtilizationDocument(utilizationDocumentM);
            ;

        }

        return researchService.listUtilizationDocument(utilizationDocumentM.getUtilizationItemList(), utilizationDocumentM.getResearchProjectId());
    }

    public List<UtilizationDocumentM> deleteUtilizationDocumentM(UtilizationDocumentM utilizationDocumentM, String module) {
        UtilizationDocumentM doc = researchService.findUtilizationDocumentById(utilizationDocumentM);
        if (doc.getFilePath() != null) {
            //String  ndFilePath = bundle.getString(module+"UploadPath")+doc.getFilePath();
            Path path = Paths.get(bundle.getString(module + "UploadPath"), doc.getFilePath());
            try {
                Files.deleteIfExists(path);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
        researchService.deleteUtilizationDocument(utilizationDocumentM);
        return researchService.listUtilizationDocument(utilizationDocumentM.getUtilizationItemList(), utilizationDocumentM.getResearchProjectId());
    }

    public UtilizationDocumentM findUtilizationDocumentById(UtilizationDocumentM researchProjectDocumentM) {
        return researchService.findUtilizationDocumentById(researchProjectDocumentM);
    }

}
