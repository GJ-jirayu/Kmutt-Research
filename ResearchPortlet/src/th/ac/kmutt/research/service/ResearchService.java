package th.ac.kmutt.research.service;

import java.util.List;

import th.ac.kmutt.research.model.CopyrightCreatorM;
import th.ac.kmutt.research.model.CopyrightDocumentM;
import th.ac.kmutt.research.model.CopyrightM;
import th.ac.kmutt.research.model.CountryM;
import th.ac.kmutt.research.model.DocAssignMappingM;
import th.ac.kmutt.research.model.DocTypeM;
import th.ac.kmutt.research.model.FundingResourceM;
import th.ac.kmutt.research.model.FundingResourceTypeM;
import th.ac.kmutt.research.model.JournalPaperM;
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
import th.ac.kmutt.research.model.PatentM;
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
import th.ac.kmutt.research.model.RewardM;
import th.ac.kmutt.research.model.TitleM;
import th.ac.kmutt.research.model.UtilizationDocumentM;
import th.ac.kmutt.research.model.UtilizationM;
import th.ac.kmutt.research.model.UtilizationTypeM;
import th.ac.kmutt.research.model.WorkTypeM;
import th.ac.kmutt.research.portal.model.UserPortalM;
import th.ac.kmutt.research.xstream.common.ImakeResultMessage;

//import th.ac.kmutt.research.bean.McDegree;

public interface ResearchService {
	public List<JournalPaperM> selectAll();
	
	// 
	public Integer saveResearchGroupM(ResearchGroupM researchGroupM) ;
	public Integer updateResearchGroupM(ResearchGroupM researchGroupM) ;
	public Integer deleteResearchGroupM(ResearchGroupM researchGroupM);
	public Integer deleteItemsResearchGroupM(String[] researchGroupId);
	public ResearchGroupM findResearchGroupById(Integer researchGroupId);
	//public List<ResearchGroupM> searchResearchGroupM(ResearchGroupM researchGroupM) ;
	public ImakeResultMessage searchResearchGroupM(ResearchGroupM researchGroupM) ;
	public List<ResearchGroupM> listResearchGroupM();
	

	// UtilizationType
	public Integer saveUtilizationTypeM(UtilizationTypeM transientInstance) ;
	public Integer updateUtilizationTypeM(UtilizationTypeM transientInstance)  ;
	public Integer deleteUtilizationTypeM(UtilizationTypeM persistentInstance)  ;	
	public Integer deleteItemsUtilizationTypeM(String[] utilizationTypeId);
	public UtilizationTypeM findUtilizationTypeById(Integer utilizationTypeId) ;
	@SuppressWarnings("rawtypes")
	public ImakeResultMessage searchUtilizationTypeM(UtilizationTypeM persistentInstance)  ;
	public UtilizationM findUtilizationItemById(UtilizationM persistentInstance)  ;		
	// PublishType
	public Integer savePublishTypeM(PublishTypeM transientInstance) ;
	public Integer updatePublishTypeM(PublishTypeM transientInstance)  ;
	public Integer deletePublishTypeM(PublishTypeM persistentInstance)  ;
	public Integer deleteItemsPublishTypeM(String[] researchGroupId);
	public PublishTypeM findPublishTypeById(Integer researchGroupId)  ;
	@SuppressWarnings("rawtypes")
	public ImakeResultMessage searchPublishTypeM(PublishTypeM persistentInstance)  ;
	
	// Position
	public Integer savePositionM(PositionM transientInstance) ;
	public Integer updatePositionM(PositionM transientInstance)  ;
	public Integer deletePositionM(PositionM persistentInstance)  ;
	public Integer deleteItemsPositionM(String[] researchGroupId);
	public PositionM findPositionById(Integer researchGroupId)  ;
	@SuppressWarnings("rawtypes")
	public ImakeResultMessage searchPositionM(PositionM persistentInstance)  ;
	
	// Organization
	public Integer saveOrganizationM(OrganizationM transientInstance) ;
	public Integer updateOrganizationM(OrganizationM transientInstance)  ;
	public Integer deleteOrganizationM(OrganizationM persistentInstance)  ;
	public Integer deleteItemsOrganizationM(String[] researchGroupId);
	public OrganizationM findOrganizationById(Integer researchGroupId)  ;
	@SuppressWarnings("rawtypes")
	public ImakeResultMessage searchOrganizationM(OrganizationM persistentInstance)  ;
	
	// Country
	public Integer saveCountryM(CountryM transientInstance) ;
	public Integer updateCountryM(CountryM transientInstance)  ;
	public Integer deleteCountryM(CountryM persistentInstance)  ;
	public Integer deleteItemsCountryM(String[] researchGroupId);
	public CountryM findCountryById(Integer researchGroupId)  ;
	@SuppressWarnings("rawtypes")
	public ImakeResultMessage searchCountryM(CountryM persistentInstance)  ;
	
	// WorkType
	public Integer saveWorkTypeM(WorkTypeM transientInstance) ;
	public Integer updateWorkTypeM(WorkTypeM transientInstance)  ;
	public Integer deleteWorkTypeM(WorkTypeM persistentInstance)  ;
	public Integer deleteItemsWorkTypeM(String[] researchGroupId);
	public WorkTypeM findWorkTypeById(Integer researchGroupId)  ;
	@SuppressWarnings("rawtypes")
	public ImakeResultMessage searchWorkTypeM(WorkTypeM persistentInstance)  ;
	
	// JournalPapersType
	public Integer saveJournalPapersTypeM(JournalPapersTypeM transientInstance) ;
	public Integer updateJournalPapersTypeM(JournalPapersTypeM transientInstance)  ;
	public Integer deleteJournalPapersTypeM(JournalPapersTypeM persistentInstance)  ;
	public Integer deleteItemsJournalPapersTypeM(String[] researchGroupId);
	public JournalPapersTypeM findJournalPapersTypeById(Integer researchGroupId)  ;
	@SuppressWarnings("rawtypes")
	public ImakeResultMessage searchJournalPapersTypeM(JournalPapersTypeM persistentInstance)  ;
	
	// FundingResourceType
	public Integer saveFundingResourceTypeM(FundingResourceTypeM transientInstance) ;
	public Integer updateFundingResourceTypeM(FundingResourceTypeM transientInstance)  ;
	public Integer deleteFundingResourceTypeM(FundingResourceTypeM persistentInstance)  ;
	public Integer deleteItemsFundingResourceTypeM(String[] researchGroupId);
	public FundingResourceTypeM findFundingResourceTypeById(Integer researchGroupId)  ;
	@SuppressWarnings("rawtypes")
	public ImakeResultMessage searchFundingResourceTypeM(FundingResourceTypeM persistentInstance)  ;
	
	// Researcher
	public Integer saveResearcherM(ResearcherM transientInstance) ;
	public Integer updateResearcherM(ResearcherM transientInstance)  ;
	public Integer deleteResearcherM(ResearcherM persistentInstance)  ;
	public Integer deleteItemsResearcherM(String[] researchGroupId);
	public ResearcherM findResearcherById(Integer researchGroupId)  ;
	@SuppressWarnings("rawtypes")
	public ImakeResultMessage searchResearcherM(ResearcherM persistentInstance)  ;
	
	// ResearcherGroup
	public Integer saveResearcherGroupM(ResearcherGroupM transientInstance) ;
	public Integer updateResearcherGroupM(ResearcherGroupM transientInstance)  ;
	public Integer deleteResearcherGroupM(ResearcherGroupM persistentInstance)  ;
	public Integer deleteItemsResearcherGroupM(String[] researchGroupId);
	public ResearcherGroupM findResearcherGroupById(Integer researchGroupId, Integer researcherId)  ;
	@SuppressWarnings("rawtypes")
	public ImakeResultMessage searchResearcherGroupM(ResearcherGroupM persistentInstance)  ;
	public List<ResearcherGroupM> listResearcherGroupM(Integer researcherId)	;
	// PublishLevel
	public Integer savePublishLevelM(PublishLevelM transientInstance) ;
	public Integer updatePublishLevelM(PublishLevelM transientInstance)  ;
	public Integer deletePublishLevelM(PublishLevelM persistentInstance)  ;
	public Integer deleteItemsPublishLevelM(String[] researchGroupId);
	public PublishLevelM findPublishLevelById(Integer researchGroupId)  ;
	@SuppressWarnings("rawtypes")
	public ImakeResultMessage searchPublishLevelM(PublishLevelM persistentInstance)  ;
	
	// OrganizationExt
	public Integer saveOrganizationExtM(OrganizationExtM transientInstance) ;
	public Integer updateOrganizationExtM(OrganizationExtM transientInstance)  ;
	public Integer deleteOrganizationExtM(OrganizationExtM persistentInstance)  ;
	public Integer deleteItemsOrganizationExtM(String[] researchGroupId);
	public OrganizationExtM findOrganizationExtById(Integer researchGroupId)  ;
	@SuppressWarnings("rawtypes")
	public ImakeResultMessage searchOrganizationExtM(OrganizationExtM persistentInstance)  ;
	
	// FundingResource
	public Integer saveFundingResourceM(FundingResourceM transientInstance) ;
	public Integer updateFundingResourceM(FundingResourceM transientInstance)  ;
	public Integer deleteFundingResourceM(FundingResourceM persistentInstance)  ;
	public Integer deleteItemsFundingResourceM(String[] researchGroupId);
	public FundingResourceM findFundingResourceById(Integer researchGroupId)  ;
	@SuppressWarnings("rawtypes")
	public ImakeResultMessage searchFundingResourceM(FundingResourceM persistentInstance) ; 
	
	// Docs
	// ResearchProject
	public Integer saveResearchProjectM(ResearchProjectM transientInstance) ;
	public Integer updateResearchProjectM(ResearchProjectM transientInstance)  ;
	public Integer deleteResearchProjectM(ResearchProjectM persistentInstance)  ;
	public Integer deleteItemsResearchProjectM(String[] researchGroupId);
	public ResearchProjectM findResearchProjectById(Integer researchGroupId,String userid)  ;
	@SuppressWarnings("rawtypes")
	public ImakeResultMessage searchResearchProjectM(ResearchProjectM persistentInstance) ; 
	public Integer updateFlagResearchProjectM(ResearchProjectM persistentInstance)  ;	

	public List<ResearchProjectDocumentM> listResearchProjectDocument(Integer researchProjectId);
	public List<ResearchProjectPaymentM> listResearchProjectPayment(Integer researchProjectId);
	public List<ResearchProjectProgressM> listResearchProjectProgress(Integer researchProjectId);
	public List<ResearchProjectResearcherM> listResearchProjectResearcher(Integer researchProjectId);
	public List<ResearchProjectWithdrawM> listResearchProjectWithdraw(Integer researchProjectId);
	public Integer saveResearchProjectDocument(ResearchProjectDocumentM transientInstance) ;
	public Integer updateResearchProjectDocument(ResearchProjectDocumentM transientInstance)  ;
	public Integer deleteResearchProjectDocument(ResearchProjectDocumentM persistentInstance)  ;
	//public Integer deleteItemsResearchGroupM(String[] researchGroupId);
	
	public Integer saveResearchProjectPayment(ResearchProjectPaymentM transientInstance) ;
	public Integer updateResearchProjectPayment(ResearchProjectPaymentM transientInstance)  ;
	public Integer deleteResearchProjectPayment(ResearchProjectPaymentM persistentInstance)  ;
	//public Integer deleteItemsResearchGroupM(String[] researchGroupId);
	
	public Integer saveResearchProjectProgress(ResearchProjectProgressM transientInstance) ;
	public Integer updateResearchProjectProgress(ResearchProjectProgressM transientInstance)  ;
	public Integer deleteResearchProjectProgress(ResearchProjectProgressM persistentInstance)  ;
	//public Integer deleteItemsResearchGroupM(String[] researchGroupId);
	
	public Integer saveResearchProjectResearcher(ResearchProjectResearcherM transientInstance) ;
	public Integer updateResearchProjectResearcher(ResearchProjectResearcherM transientInstance)  ;
	public Integer deleteResearchProjectResearcher(ResearchProjectResearcherM persistentInstance)  ;
	//public Integer deleteItemsResearchGroupM(String[] researchGroupId);
	
	public Integer saveResearchProjectWithdraw(ResearchProjectWithdrawM transientInstance) ;
	public Integer updateResearchProjectWithdraw(ResearchProjectWithdrawM transientInstance)  ;
	public Integer deleteResearchProjectWithdraw(ResearchProjectWithdrawM persistentInstance)  ;
	//public Integer deleteItemsResearchGroupM(String[] researchGroupId);
		
	// JournalPapers
	public Integer saveJournalPaperM(JournalPaperM transientInstance) ;
	public Integer updateJournalPaperM(JournalPaperM transientInstance)  ;
	public Integer deleteJournalPaperM(JournalPaperM persistentInstance)  ;
	public Integer deleteItemsJournalPaperM(Integer type,String[] researchGroupId);
	public JournalPaperM findJournalPapersById(Integer researchGroupId,String userid)  ;
	@SuppressWarnings("rawtypes")
	public ImakeResultMessage searchJournalPaperM(JournalPaperM persistentInstance) ; 
	public Integer updateFlagJournalPaperM(JournalPaperM persistentInstance)  ;
	
	public List<JournalPapersDocumentM> listJournalPapersDocument(Integer researchProjectId);
	public List<JournalPapersWriterM> listJournalPapersWriter(Integer researchProjectId);
	public Integer saveJournalPapersDocument(JournalPapersDocumentM transientInstance) ;
	public Integer updateJournalPapersDocument(JournalPapersDocumentM transientInstance)  ;
	public Integer deleteJournalPapersDocument(JournalPapersDocumentM persistentInstance)  ;
	//public Integer deleteItemsResearchGroupM(String[] researchGroupId);
	
	public Integer saveJournalPapersWriter(JournalPapersWriterM transientInstance) ;
	public Integer updateJournalPapersWriter(JournalPapersWriterM transientInstance)  ;
	public Integer deleteJournalPapersWriter(JournalPapersWriterM persistentInstance)  ;
	//public Integer deleteItemsResearchGroupM(String[] researchGroupId);
		
	// Utilization
	public Integer saveUtilizationM(UtilizationM transientInstance) ;
	public Integer updateUtilizationM(UtilizationM transientInstance)  ;
	public Integer deleteUtilizationM(UtilizationM persistentInstance)  ;
	public Integer deleteItemsUtilizationM(String[] researchGroupId);
	public Integer deleteUtilizationItem(UtilizationM persistentInstance)  ;
	public UtilizationM findUtilizationById(Integer researchProjectId,Integer utilizationItemList) ;
	@SuppressWarnings("rawtypes")
	public ImakeResultMessage searchUtilizationM(UtilizationM persistentInstance) ; 
	public Integer updateFlagUtilization(UtilizationM persistentInstance)  ;
	
	// Copyright
	public Integer saveCopyrightM(CopyrightM transientInstance) ;
	public Integer updateCopyrightM(CopyrightM transientInstance)  ;
	public Integer deleteCopyrightM(CopyrightM persistentInstance)  ;
	public Integer deleteItemsCopyrightM(String[] researchGroupId);
	public CopyrightM findResearchCopyRightById(Integer researchGroupId,String userid)  ;
	@SuppressWarnings("rawtypes")
	public ImakeResultMessage searchCopyrightM(CopyrightM persistentInstance) ; 
	public Integer updateFlagCopyright(CopyrightM persistentInstance)  ;
	
	public List<CopyrightCreatorM> listCopyrightCreator(Integer copyrightId);
	public Integer saveCopyrightCreator(CopyrightCreatorM transientInstance) ;
	public Integer updateCopyrightCreator(CopyrightCreatorM transientInstance)  ;
	public Integer deleteCopyrightCreator(CopyrightCreatorM persistentInstance)  ;
	//public Integer deleteItemsResearchGroupM(String[] researchGroupId);
	
		
	// Reward
	public Integer saveRewardM(RewardM transientInstance) ;
	public Integer updateRewardM(RewardM transientInstance)  ;
	public Integer deleteRewardM(RewardM persistentInstance)  ;
	public Integer deleteItemsRewardM(String[] researchGroupId);
	public RewardM findRewardById(Integer researchGroupId,String userid)  ;
	@SuppressWarnings("rawtypes")
	public ImakeResultMessage searchRewardM(RewardM persistentInstance) ; 
	public Integer updateFlagReward(RewardM persistentInstance)  ;	
	
	public List<RewardCreatorM> listRewardCreator(Integer rewardId);	
	public Integer saveRewardCreator(RewardCreatorM transientInstance) ;
	public Integer updateRewardCreator(RewardCreatorM transientInstance)  ;
	public Integer deleteRewardCreator(RewardCreatorM persistentInstance)  ;
	//public Integer deleteItemsResearchGroupM(String[] researchGroupId);
	
	
	// Patent
	public Integer savePatentM(PatentM transientInstance) ;
	public Integer updatePatentM(PatentM transientInstance)  ;
	public Integer deletePatentM(PatentM persistentInstance)  ;
	public Integer deleteItemsPatentM(String[] researchGroupId);
	public PatentM findPatentById(Integer researchGroupId,String userid)  ;
	@SuppressWarnings("rawtypes")
	public ImakeResultMessage searchPatentM(PatentM persistentInstance) ;
	public Integer updateFlagPatent(PatentM persistentInstance)  ;
	
	public List<PatentCreatorM> listPatentCreator(Integer inventionId);	
	public List<PatentDocumentM> listPatentDocument(Integer inventionId);	
	public List<PatentEditDateM> listPatentEditDate(Integer inventionId);	
	public List<PatentFeePaymentM> listPatentFeePayment(Integer inventionId);	
	public List<PatentInheritedM> listPatentInherited(Integer inventionId);	
	public List<PatentRightholderM> listPatentRightholder(Integer inventionId);
	
	public Integer savePatentCreator(PatentCreatorM transientInstance) ;
	public Integer updatePatentCreator(PatentCreatorM transientInstance)  ;
	public Integer deletePatentCreator(PatentCreatorM persistentInstance)  ;
	//public Integer deleteItemsResearchGroupM(String[] researchGroupId);
	
	public Integer savePatentDocument(PatentDocumentM transientInstance) ;
	public Integer updatePatentDocument(PatentDocumentM transientInstance)  ;
	public Integer deletePatentDocument(PatentDocumentM persistentInstance)  ;
	//public Integer deleteItemsResearchGroupM(String[] researchGroupId);
	
	public Integer savePatentEditDate(PatentEditDateM transientInstance) ;
	public Integer updatePatentEditDate(PatentEditDateM transientInstance)  ;
	public Integer deletePatentEditDate(PatentEditDateM persistentInstance)  ;
	//public Integer deleteItemsResearchGroupM(String[] researchGroupId);
	
	public Integer savePatentFeePayment(PatentFeePaymentM transientInstance) ;
	public Integer updatePatentFeePayment(PatentFeePaymentM transientInstance)  ;
	public Integer deletePatentFeePayment(PatentFeePaymentM persistentInstance)  ;
	//public Integer deleteItemsResearchGroupM(String[] researchGroupId);
	
	public Integer savePatentInherited(PatentInheritedM transientInstance) ;
	public Integer updatePatentInherited(PatentInheritedM transientInstance)  ;
	public Integer deletePatentInherited(PatentInheritedM persistentInstance)  ;
	//public Integer deleteItemsResearchGroupM(String[] researchGroupId);
	
	public Integer savePatentRightholder(PatentRightholderM transientInstance) ;
	public Integer updatePatentRightholder(PatentRightholderM transientInstance)  ;
	public Integer deletePatentRightholder(PatentRightholderM persistentInstance)  ;
	//public Integer deleteItemsResearchGroupM(String[] researchGroupId);
	
	// DocType
	public Integer updateDocTypeM(DocTypeM transientInstance)  ;
	@SuppressWarnings("rawtypes")
	public ImakeResultMessage listDocTypeM() ; 

	public UserPortalM findUserPortalById(Integer researchGroupId);
	public ImakeResultMessage searchUserPortal(UserPortalM persistentInstance) ;
	
	// DocAssignMapping
	public Integer saveDocAssignMapping(DocAssignMappingM transientInstance) ;
	public Integer deleteDocAssignMapping(DocAssignMappingM persistentInstance)  ;	
	public DocAssignMappingM findDocAssignMappingById(Integer refId,String refType);
	@SuppressWarnings("rawtypes")
	public ImakeResultMessage listDocAssignMappingt(DocAssignMappingM persistentInstance);
	
	
	// Title
	public Integer saveTitleM(TitleM transientInstance) ;
	public Integer updateTitleM(TitleM transientInstance)  ;
	public Integer deleteTitleM(TitleM persistentInstance)  ;
	public Integer deleteItemsTitleM(String[] researchGroupId);
	public TitleM findTitleById(Integer researchGroupId)  ;
	@SuppressWarnings("rawtypes")
	public ImakeResultMessage searchTitleM(TitleM persistentInstance)  ;
	
	
	public ResearchProjectDocumentM findResearchProjectDocumentById(
			ResearchProjectDocumentM id);
	public ResearchProjectPaymentM findResearchProjectPaymentById(
			ResearchProjectPaymentM id) ;

	public ResearchProjectProgressM findResearchProjectProgressById(
			ResearchProjectProgressM id) ;

	public ResearchProjectResearcherM findResearchProjectResearcherById(
			ResearchProjectResearcherM id);

	public ResearchProjectWithdrawM findResearchProjectWithdrawById(
			ResearchProjectWithdrawM id) ;

	public JournalPapersDocumentM findJournalPapersDocumentById(
			JournalPapersDocumentM id) ;

	public JournalPapersWriterM findJournalPapersWriterById(
			JournalPapersWriterM id) ;
	public CopyrightCreatorM findCopyrightCreatorById(CopyrightCreatorM id) ;

	public RewardCreatorM findRewardCreatorById(RewardCreatorM id);

	public PatentCreatorM findPatentCreatorById(PatentCreatorM id);

	public PatentDocumentM findPatentDocumentById(PatentDocumentM id) ;

	public PatentEditDateM findPatentEditDateById(PatentEditDateM id);

	public PatentFeePaymentM findPatentFeePaymentById(PatentFeePaymentM id) ;

	public PatentInheritedM findPatentInheritedById(PatentInheritedM id);

	public PatentRightholderM findPatentRightholderById(PatentRightholderM id);

	public Integer checkUQResearchGroup(ResearchGroupM transientInstance) ;	
	public Integer checkUQUtilizationType(UtilizationTypeM transientInstance) ;
	public Integer checkUQPublishType(PublishTypeM transientInstance) ;
	public Integer checkUQPosition(PositionM transientInstance) ;
	public Integer checkUQCountry(CountryM transientInstance) ;
	public Integer checkUQWorkType(WorkTypeM transientInstance) ;
	public Integer checkUQJournalPapersType(JournalPapersTypeM transientInstance) ;
	public Integer checkUQFundingResourceType(FundingResourceTypeM transientInstance) ;
	public Integer checkUQPublishLevel(PublishLevelM transientInstance) ;
	public Integer checkUQOrganizationExt(OrganizationExtM transientInstance) ;
	public Integer checkUQFundingResource(FundingResourceM transientInstance) ;
	public Integer countJournalPapersWriter(JournalPapersWriterM transientInstance); 
	public Integer countResearchProjectResearcher(ResearchProjectResearcherM transientInstance);
	
	
	public List<UtilizationDocumentM> listUtilizationDocument(Integer utilizationItemList,Integer researchProjectId) ;
	public Integer saveUtilizationDocument(UtilizationDocumentM transientInstance) ;
	public Integer updateUtilizationDocument(UtilizationDocumentM transientInstance) ;
	public Integer deleteUtilizationDocument(UtilizationDocumentM persistentInstance);
	//public Integer deleteItemsResearchGroupM(String[] researchGroupId);
	public UtilizationDocumentM findUtilizationDocumentById(UtilizationDocumentM id) ;

	public List<CopyrightDocumentM> listCopyrightDocument(Integer copyrightId);
	public Integer saveCopyrightDocument(CopyrightDocumentM transientInstance);
	public Integer updateCopyrightDocument(CopyrightDocumentM transientInstance) ;
	public Integer deleteCopyrightDocument(CopyrightDocumentM persistentInstance);
	//public Integer deleteItemsResearchGroupM(String[] researchGroupId);
	public CopyrightDocumentM findCopyrightDocumentById(CopyrightDocumentM id);

	public List<RewardDocumentM> listRewardDocument(Integer rewardId);
	public Integer saveRewardDocument(RewardDocumentM transientInstance);
	public Integer updateRewardDocument(RewardDocumentM transientInstance) ;
	public Integer deleteRewardDocument(RewardDocumentM persistentInstance) ;
	//	public Integer deleteItemsResearchGroupM(String[] researchGroupId);
	public RewardDocumentM findRewardDocumentById(RewardDocumentM id) ;
	
}
