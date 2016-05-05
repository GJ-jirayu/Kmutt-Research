package th.ac.kmutt.research.service;

import java.util.List;

import org.springframework.dao.DataAccessException;

import th.ac.kmutt.research.domain.Copyright;
import th.ac.kmutt.research.domain.CopyrightCreator;
import th.ac.kmutt.research.domain.CopyrightCreatorPK;
import th.ac.kmutt.research.domain.CopyrightDocument;
import th.ac.kmutt.research.domain.CopyrightDocumentPK;
import th.ac.kmutt.research.domain.Country;
import th.ac.kmutt.research.domain.DocAssignMapping;
import th.ac.kmutt.research.domain.FundingResource;
import th.ac.kmutt.research.domain.FundingResourceType;
import th.ac.kmutt.research.domain.JournalPaper;
import th.ac.kmutt.research.domain.JournalPapersConference;
import th.ac.kmutt.research.domain.JournalPapersDocument;
import th.ac.kmutt.research.domain.JournalPapersDocumentPK;
import th.ac.kmutt.research.domain.JournalPapersJournal;
import th.ac.kmutt.research.domain.JournalPapersType;
import th.ac.kmutt.research.domain.JournalPapersWriter;
import th.ac.kmutt.research.domain.JournalPapersWriterPK;
import th.ac.kmutt.research.domain.Organization;
import th.ac.kmutt.research.domain.OrganizationExt;
import th.ac.kmutt.research.domain.Patent;
import th.ac.kmutt.research.domain.PatentCreator;
import th.ac.kmutt.research.domain.PatentCreatorPK;
import th.ac.kmutt.research.domain.PatentDocument;
import th.ac.kmutt.research.domain.PatentDocumentPK;
import th.ac.kmutt.research.domain.PatentEditDate;
import th.ac.kmutt.research.domain.PatentEditDatePK;
import th.ac.kmutt.research.domain.PatentFeePayment;
import th.ac.kmutt.research.domain.PatentFeePaymentPK;
import th.ac.kmutt.research.domain.PatentInherited;
import th.ac.kmutt.research.domain.PatentInheritedPK;
import th.ac.kmutt.research.domain.PatentRightholder;
import th.ac.kmutt.research.domain.PatentRightholderPK;
import th.ac.kmutt.research.domain.Position;
import th.ac.kmutt.research.domain.PublishLevel;
import th.ac.kmutt.research.domain.PublishType;
import th.ac.kmutt.research.domain.ResearchGroup;
import th.ac.kmutt.research.domain.ResearchProject;
import th.ac.kmutt.research.domain.ResearchProjectDocument;
import th.ac.kmutt.research.domain.ResearchProjectDocumentPK;
import th.ac.kmutt.research.domain.ResearchProjectPayment;
import th.ac.kmutt.research.domain.ResearchProjectPaymentPK;
import th.ac.kmutt.research.domain.ResearchProjectProgress;
import th.ac.kmutt.research.domain.ResearchProjectProgressPK;
import th.ac.kmutt.research.domain.ResearchProjectResearcher;
import th.ac.kmutt.research.domain.ResearchProjectResearcherPK;
import th.ac.kmutt.research.domain.ResearchProjectWithdraw;
import th.ac.kmutt.research.domain.ResearchProjectWithdrawPK;
import th.ac.kmutt.research.domain.Researcher;
import th.ac.kmutt.research.domain.ResearcherGroup;
import th.ac.kmutt.research.domain.Reward;
import th.ac.kmutt.research.domain.RewardCreator;
import th.ac.kmutt.research.domain.RewardCreatorPK;
import th.ac.kmutt.research.domain.RewardDocument;
import th.ac.kmutt.research.domain.RewardDocumentPK;
import th.ac.kmutt.research.domain.Title;
import th.ac.kmutt.research.domain.Utilization;
import th.ac.kmutt.research.domain.UtilizationDocument;
import th.ac.kmutt.research.domain.UtilizationDocumentPK;
import th.ac.kmutt.research.domain.UtilizationPK;
import th.ac.kmutt.research.domain.UtilizationType;
import th.ac.kmutt.research.domain.WorkType;
import th.ac.kmutt.research.exception.ImakeException;
import th.ac.kmutt.research.model.DocTypeM;
import th.ac.kmutt.research.model.JournalPaperM;
import th.ac.kmutt.research.portal.domain.UserPortal;
import th.ac.kmutt.research.xstream.common.Paging;


public interface ResearchService {
    public abstract List<JournalPaperM> listJournalPaperAll();

    // ResearchGroup
    public Integer saveResearchGroup(ResearchGroup transientInstance) throws DataAccessException;

    public Integer updateResearchGroup(ResearchGroup transientInstance) throws DataAccessException;

    public Integer deleteResearchGroup(ResearchGroup persistentInstance) throws DataAccessException;

    //throws DataAccessException throws ImakeException;
    public ResearchGroup findResearchGroupById(Integer researchGroupId) throws DataAccessException;

    @SuppressWarnings("rawtypes")
    public List searchResearchGroup(ResearchGroup persistentInstance, Paging pagging, String keySearch) throws DataAccessException;

    // UtilizationType
    public Integer saveUtilizationType(UtilizationType transientInstance) throws DataAccessException;

    public Integer updateUtilizationType(UtilizationType transientInstance) throws DataAccessException;

    public Integer deleteUtilizationType(UtilizationType persistentInstance) throws DataAccessException;

    public UtilizationType findUtilizationTypeById(Integer researchGroupId) throws DataAccessException;

    @SuppressWarnings("rawtypes")
    public List searchUtilizationType(UtilizationType persistentInstance, Paging pagging, String keySearch) throws DataAccessException;

    // PublishType
    public Integer savePublishType(PublishType transientInstance) throws DataAccessException;

    public Integer updatePublishType(PublishType transientInstance) throws DataAccessException;

    public Integer deletePublishType(PublishType persistentInstance) throws DataAccessException;

    public PublishType findPublishTypeById(Integer researchGroupId) throws DataAccessException;

    @SuppressWarnings("rawtypes")
    public List searchPublishType(PublishType persistentInstance, Paging pagging, String keySearch) throws DataAccessException;

    // Position
    public Integer savePosition(Position transientInstance) throws DataAccessException;

    public Integer updatePosition(Position transientInstance) throws DataAccessException;

    public Integer deletePosition(Position persistentInstance) throws DataAccessException;

    public Position findPositionById(Integer researchGroupId) throws DataAccessException;

    @SuppressWarnings("rawtypes")
    public List searchPosition(Position persistentInstance, Paging pagging, String keySearch) throws DataAccessException;

    // Organization
    public Integer saveOrganization(Organization transientInstance) throws DataAccessException;

    public Integer updateOrganization(Organization transientInstance) throws DataAccessException;

    public Integer deleteOrganization(Organization persistentInstance) throws DataAccessException;

    public Organization findOrganizationById(Integer researchGroupId) throws DataAccessException;

    @SuppressWarnings("rawtypes")
    public List searchOrganization(Organization persistentInstance, Paging pagging, String keySearch) throws DataAccessException;

    // Country
    public Integer saveCountry(Country transientInstance) throws DataAccessException;

    public Integer updateCountry(Country transientInstance) throws DataAccessException;

    public Integer deleteCountry(Country persistentInstance) throws DataAccessException;

    public Country findCountryById(Integer researchGroupId) throws DataAccessException;

    @SuppressWarnings("rawtypes")
    public List searchCountry(Country persistentInstance, Paging pagging, String keySearch) throws DataAccessException;

    // WorkType
    public Integer saveWorkType(WorkType transientInstance) throws DataAccessException;

    public Integer updateWorkType(WorkType transientInstance) throws DataAccessException;

    public Integer deleteWorkType(WorkType persistentInstance) throws DataAccessException;

    public WorkType findWorkTypeById(Integer researchGroupId) throws DataAccessException;

    @SuppressWarnings("rawtypes")
    public List searchWorkType(WorkType persistentInstance, Paging pagging, String keySearch) throws DataAccessException;

    // JournalPapersType
    public Integer saveJournalPapersType(JournalPapersType transientInstance) throws DataAccessException;

    public Integer updateJournalPapersType(JournalPapersType transientInstance) throws DataAccessException;

    public Integer deleteJournalPapersType(JournalPapersType persistentInstance) throws DataAccessException;

    public JournalPapersType findJournalPapersTypeById(Integer researchGroupId) throws DataAccessException;

    @SuppressWarnings("rawtypes")
    public List searchJournalPapersType(JournalPapersType persistentInstance, Paging pagging, String keySearch) throws DataAccessException;

    // FundingResourceType
    public Integer saveFundingResourceType(FundingResourceType transientInstance) throws DataAccessException;

    public Integer updateFundingResourceType(FundingResourceType transientInstance) throws DataAccessException;

    public Integer deleteFundingResourceType(FundingResourceType persistentInstance) throws DataAccessException;

    public FundingResourceType findFundingResourceTypeById(Integer researchGroupId) throws DataAccessException;

    @SuppressWarnings("rawtypes")
    public List searchFundingResourceType(FundingResourceType persistentInstance, Paging pagging, String keySearch) throws DataAccessException;

    // Researcher
    public Integer saveResearcher(Researcher transientInstance) throws DataAccessException;

    public Integer updateResearcher(Researcher transientInstance) throws DataAccessException;

    public Integer deleteResearcher(Researcher persistentInstance) throws DataAccessException;

    public Researcher findResearcherById(Integer researchGroupId) throws DataAccessException;

    @SuppressWarnings("rawtypes")
    public List searchResearcher(Researcher persistentInstance, Paging pagging, String keySearch) throws DataAccessException;

    public List<ResearcherGroup> listResearcherGroup(Integer researchProjectId);

    // ResearcherGroup
    public Integer saveResearcherGroup(ResearcherGroup transientInstance) throws DataAccessException;

    public Integer updateResearcherGroup(ResearcherGroup transientInstance) throws DataAccessException;

    public Integer deleteResearcherGroup(ResearcherGroup persistentInstance) throws DataAccessException;

    public ResearcherGroup findResearcherGroupById(Integer researchGroupId, Integer researcherId) throws DataAccessException;

    @SuppressWarnings("rawtypes")
    public List searchResearcherGroup(ResearcherGroup persistentInstance, Paging pagging, String keySearch) throws DataAccessException;

    // PublishLevel
    public Integer savePublishLevel(PublishLevel transientInstance) throws DataAccessException;

    public Integer updatePublishLevel(PublishLevel transientInstance) throws DataAccessException;

    public Integer deletePublishLevel(PublishLevel persistentInstance) throws DataAccessException;

    public PublishLevel findPublishLevelById(Integer researchGroupId) throws DataAccessException;

    @SuppressWarnings("rawtypes")
    public List searchPublishLevel(PublishLevel persistentInstance, Paging pagging, String keySearch) throws DataAccessException;

    // OrganizationExt
    public Integer saveOrganizationExt(OrganizationExt transientInstance) throws DataAccessException;

    public Integer updateOrganizationExt(OrganizationExt transientInstance) throws DataAccessException;

    public Integer deleteOrganizationExt(OrganizationExt persistentInstance) throws DataAccessException;

    public OrganizationExt findOrganizationExtById(Integer researchGroupId) throws DataAccessException;

    @SuppressWarnings("rawtypes")
    public List searchOrganizationExt(OrganizationExt persistentInstance, Paging pagging, String keySearch) throws DataAccessException;

    // FundingResource
    public Integer saveFundingResource(FundingResource transientInstance) throws DataAccessException;

    public Integer updateFundingResource(FundingResource transientInstance) throws DataAccessException;

    public Integer deleteFundingResource(FundingResource persistentInstance) throws DataAccessException;

    public FundingResource findFundingResourceById(Integer researchGroupId) throws DataAccessException;

    @SuppressWarnings("rawtypes")
    public List searchFundingResource(FundingResource persistentInstance, Paging pagging, String keySearch) throws DataAccessException;

    // Docs
    // ResearchProject
    public Integer saveResearchProject(ResearchProject transientInstance);

    public Integer updateResearchProject(ResearchProject transientInstance);

    public Integer deleteResearchProject(ResearchProject persistentInstance);

    public ResearchProject findResearchProjectById(Integer researchGroupId, String userid);

    @SuppressWarnings("rawtypes")
    public List searchResearchProject(ResearchProject persistentInstance, Paging pagging, String keySearch, String userid) throws DataAccessException;

    public Integer updateFlagResearchProject(ResearchProject persistentInstance);

    public List<ResearchProjectDocument> listResearchProjectDocument(Integer researchProjectId);

    public List<ResearchProjectPayment> listResearchProjectPayment(Integer researchProjectId);

    public List<ResearchProjectProgress> listResearchProjectProgress(Integer researchProjectId);

    public List<ResearchProjectResearcher> listResearchProjectResearcher(Integer researchProjectId);

    public List<ResearchProjectWithdraw> listResearchProjectWithdraw(Integer researchProjectId);

    public Integer saveResearchProjectDocument(ResearchProjectDocument transientInstance);

    public Integer updateResearchProjectDocument(ResearchProjectDocument transientInstance);

    public Integer deleteResearchProjectDocument(ResearchProjectDocument persistentInstance);

    public ResearchProjectDocument findResearchProjectDocumentById(ResearchProjectDocumentPK id);

    public Integer saveResearchProjectPayment(ResearchProjectPayment transientInstance);

    public Integer updateResearchProjectPayment(ResearchProjectPayment transientInstance);

    public Integer deleteResearchProjectPayment(ResearchProjectPayment persistentInstance);

    public ResearchProjectPayment findResearchProjectPaymentById(ResearchProjectPaymentPK id);

    public Integer saveResearchProjectProgress(ResearchProjectProgress transientInstance);

    public Integer updateResearchProjectProgress(ResearchProjectProgress transientInstance);

    public Integer deleteResearchProjectProgress(ResearchProjectProgress persistentInstance);

    public ResearchProjectProgress findResearchProjectProgressById(ResearchProjectProgressPK id);

    public Integer saveResearchProjectResearcher(ResearchProjectResearcher transientInstance);

    public Integer updateResearchProjectResearcher(ResearchProjectResearcher transientInstance);

    public Integer deleteResearchProjectResearcher(ResearchProjectResearcher persistentInstance);

    public ResearchProjectResearcher findResearchProjectResearcherById(ResearchProjectResearcherPK id);

    public Integer saveResearchProjectWithdraw(ResearchProjectWithdraw transientInstance);

    public Integer updateResearchProjectWithdraw(ResearchProjectWithdraw transientInstance);

    public Integer deleteResearchProjectWithdraw(ResearchProjectWithdraw persistentInstance);

    public ResearchProjectWithdraw findResearchProjectWithdrawById(ResearchProjectWithdrawPK id);


    // JournalPapers
    public Integer saveJournalPaper(JournalPaper transientInstance);

    public Integer updateJournalPaper(JournalPaper transientInstance);

    public Integer deleteJournalPaper(JournalPaper persistentInstance);

    public JournalPaper findJournalPapersById(Integer researchGroupId, String userid);

    @SuppressWarnings("rawtypes")
    public List searchJournalPaper(JournalPaper persistentInstance, Paging pagging, String keySearch, String userid) throws DataAccessException;

    public Integer updateFlagJournalPaper(JournalPaper persistentInstance);

    // JournalPapersJournal
    public Integer saveJournalPapersJournal(JournalPapersJournal transientInstance);

    public Integer updateJournalPapersJournal(JournalPapersJournal transientInstance);

    public Integer deleteJournalPapersJournal(JournalPapersJournal persistentInstance);

    public JournalPapersJournal findJournalPapersJournalsById(Integer researchGroupId);

    // JournalPapersConference
    public Integer saveJournalPapersConference(JournalPapersConference transientInstance);

    public Integer updateJournalPapersConference(JournalPapersConference transientInstance);

    public Integer deleteJournalPapersConference(JournalPapersConference persistentInstance);

    public JournalPapersConference findJournalPapersConferencesById(Integer researchGroupId);

    public List<JournalPapersDocument> listJournalPapersDocument(Integer researchProjectId);

    public List<JournalPapersWriter> listJournalPapersWriter(Integer researchProjectId);

    public Integer saveJournalPapersDocument(JournalPapersDocument transientInstance);

    public Integer updateJournalPapersDocument(JournalPapersDocument transientInstance);

    public Integer deleteJournalPapersDocument(JournalPapersDocument persistentInstance);

    public JournalPapersDocument findJournalPapersDocumentById(JournalPapersDocumentPK id);

    public Integer saveJournalPapersWriter(JournalPapersWriter transientInstance);

    public Integer updateJournalPapersWriter(JournalPapersWriter transientInstance);

    public Integer deleteJournalPapersWriter(JournalPapersWriter persistentInstance);

    public JournalPapersWriter findJournalPapersWriterById(JournalPapersWriterPK id);

    // Utilization
    public Integer saveUtilization(Utilization transientInstance);

    public Integer updateUtilization(Utilization transientInstance);

    public Integer deleteUtilization(Utilization persistentInstance);

    public Integer deleteUtilizationItem(Utilization persistentInstance);

    public List<Utilization> findUtilizationById(Integer researchGroupId);

    @SuppressWarnings("rawtypes")
    public List searchUtilization(Utilization persistentInstance, Paging pagging, String keySearch) throws DataAccessException;

    public Utilization findUtilizationItemById(UtilizationPK id);

    public Integer updateFlagUtilizationItem(Utilization persistentInstance);

    public List<UtilizationDocument> listUtilizationDocument(Integer utilizationItemList, Integer researchProjectId);

    public Integer saveUtilizationDocument(UtilizationDocument transientInstance);

    public Integer updateUtilizationDocument(UtilizationDocument transientInstance);

    public Integer deleteUtilizationDocument(UtilizationDocument persistentInstance);

    public UtilizationDocument findUtilizationDocumentById(UtilizationDocumentPK id);

    // Copyright
    public Integer saveCopyright(Copyright transientInstance);

    public Integer updateCopyright(Copyright transientInstance);

    public Integer deleteCopyright(Copyright persistentInstance);

    public Copyright findResearchCopyRightById(Integer researchGroupId, String userid);

    public Integer updateFlagCopyright(Copyright persistentInstance);

    public List<CopyrightDocument> listCopyrightDocument(Integer inventionId);

    public Integer saveCopyrightDocument(CopyrightDocument transientInstance);

    public Integer updateCopyrightDocument(CopyrightDocument transientInstance);

    public Integer deleteCopyrightDocument(CopyrightDocument persistentInstance);

    public CopyrightDocument findCopyrightDocumentById(CopyrightDocumentPK id);

    @SuppressWarnings("rawtypes")
    public List searchCopyright(Copyright persistentInstance, Paging pagging, String keySearch, String userid) throws DataAccessException;

    public List<CopyrightCreator> listCopyrightCreator(Integer copyrightId);

    public Integer saveCopyrightCreator(CopyrightCreator transientInstance);

    public Integer updateCopyrightCreator(CopyrightCreator transientInstance);

    public Integer deleteCopyrightCreator(CopyrightCreator persistentInstance);

    public CopyrightCreator findCopyrightCreatorById(CopyrightCreatorPK id);

    // Reward
    public Integer saveReward(Reward transientInstance);

    public Integer updateReward(Reward transientInstance);

    public Integer deleteReward(Reward persistentInstance);

    public Reward findRewardById(Integer researchGroupId, String userid);

    @SuppressWarnings("rawtypes")
    public List searchReward(Reward persistentInstance, Paging pagging, String keySearch, String userid) throws DataAccessException;

    public Integer updateFlagReward(Reward persistentInstance);

    public List<RewardCreator> listRewardCreator(Integer rewardId);

    public Integer saveRewardCreator(RewardCreator transientInstance);

    public Integer updateRewardCreator(RewardCreator transientInstance);

    public Integer deleteRewardCreator(RewardCreator persistentInstance);

    public RewardCreator findRewardCreatorById(RewardCreatorPK id);

    public List<RewardDocument> listRewardDocument(Integer inventionId);

    public Integer saveRewardDocument(RewardDocument transientInstance);

    public Integer updateRewardDocument(RewardDocument transientInstance);

    public Integer deleteRewardDocument(RewardDocument persistentInstance);

    public RewardDocument findRewardDocumentById(RewardDocumentPK id);

    // Patent
    public Integer savePatent(Patent transientInstance);

    public Integer updatePatent(Patent transientInstance);

    public Integer deletePatent(Patent persistentInstance);

    public Patent findPatentById(Integer researchGroupId, String userid);

    @SuppressWarnings("rawtypes")
    public List searchPatent(Patent persistentInstance, Paging pagging, String keySearch, String userid) throws DataAccessException;

    public Integer updateFlagPatent(Patent persistentInstance);

    public List<PatentCreator> listPatentCreator(Integer inventionId);

    public List<PatentEditDate> listPatentEditDate(Integer inventionId);

    public List<PatentFeePayment> listPatentFeePayment(Integer inventionId);

    public List<PatentInherited> listPatentInherited(Integer inventionId);

    public List<PatentRightholder> listPatentRightholder(Integer inventionId);

    public Integer savePatentCreator(PatentCreator transientInstance);

    public Integer updatePatentCreator(PatentCreator transientInstance);

    public Integer deletePatentCreator(PatentCreator persistentInstance);

    public PatentCreator findPatentCreatorById(PatentCreatorPK id);

    public List<PatentDocument> listPatentDocument(Integer inventionId);

    public Integer savePatentDocument(PatentDocument transientInstance);

    public Integer updatePatentDocument(PatentDocument transientInstance);

    public Integer deletePatentDocument(PatentDocument persistentInstance);

    public PatentDocument findPatentDocumentById(PatentDocumentPK id);

    public Integer savePatentEditDate(PatentEditDate transientInstance);

    public Integer updatePatentEditDate(PatentEditDate transientInstance);

    public Integer deletePatentEditDate(PatentEditDate persistentInstance);

    public PatentEditDate findPatentEditDateById(PatentEditDatePK id);

    public Integer savePatentFeePayment(PatentFeePayment transientInstance);

    public Integer updatePatentFeePayment(PatentFeePayment transientInstance);

    public Integer deletePatentFeePayment(PatentFeePayment persistentInstance);

    public PatentFeePayment findPatentFeePaymentById(PatentFeePaymentPK id);

    public Integer savePatentInherited(PatentInherited transientInstance);

    public Integer updatePatentInherited(PatentInherited transientInstance);

    public Integer deletePatentInherited(PatentInherited persistentInstance);

    public PatentInherited findPatentInheritedById(PatentInheritedPK id);

    public Integer savePatentRightholder(PatentRightholder transientInstance);

    public Integer updatePatentRightholder(PatentRightholder transientInstance);

    public Integer deletePatentRightholder(PatentRightholder persistentInstance);

    public PatentRightholder findPatentRightholderById(PatentRightholderPK id);

    // DocType
    public Integer updateDocTypeM(DocTypeM transientInstance);

    @SuppressWarnings("rawtypes")
    public List listDocTypeM() throws DataAccessException;

    public UserPortal findUserPortalById(Integer researchGroupId);

    public List searchUserPortal(UserPortal persistentInstance,
                                 Paging pagging, String keySearch) throws DataAccessException;


    // DocAssignMapping
    public Integer saveDocAssignMapping(DocAssignMapping transientInstance);

    public Integer deleteDocAssignMapping(DocAssignMapping persistentInstance);

    public DocAssignMapping findDocAssignMappingById(Integer refId, String refType);

    @SuppressWarnings("rawtypes")
    public List listDocAssignMapping(DocAssignMapping persistentInstance) throws DataAccessException;


    // Title
    public Integer saveTitle(Title transientInstance) throws DataAccessException;

    public Integer updateTitle(Title transientInstance) throws DataAccessException;

    public Integer deleteTitle(Title persistentInstance) throws DataAccessException;

    public Title findTitleById(Integer researchGroupId) throws DataAccessException;

    @SuppressWarnings("rawtypes")
    public List searchTitle(Title persistentInstance, Paging pagging, String keySearch) throws DataAccessException;

    public Integer checkUQResearchGroup(ResearchGroup transientInstance) throws DataAccessException;

    public Integer checkUQUtilizationType(UtilizationType transientInstance) throws DataAccessException;

    public Integer checkUQPublishType(PublishType transientInstance) throws DataAccessException;

    public Integer checkUQPosition(Position transientInstance) throws DataAccessException;

    public Integer checkUQCountry(Country transientInstance) throws DataAccessException;

    public Integer checkUQWorkType(WorkType transientInstance) throws DataAccessException;

    public Integer checkUQJournalPapersType(JournalPapersType transientInstance) throws DataAccessException;

    public Integer checkUQFundingResourceType(FundingResourceType transientInstance) throws DataAccessException;

    public Integer checkUQPublishLevel(PublishLevel transientInstance) throws DataAccessException;

    public Integer checkUQOrganizationExt(OrganizationExt transientInstance) throws DataAccessException;

    public Integer checkUQFundingResource(FundingResource transientInstance) throws DataAccessException;

    public Integer countJournalPapersWriter(JournalPapersWriter transientInstance);

    public Integer countResearchProjectResearcher(ResearchProjectResearcher transientInstance);
}
