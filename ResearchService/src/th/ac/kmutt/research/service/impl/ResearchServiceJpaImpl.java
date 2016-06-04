package th.ac.kmutt.research.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

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
import th.ac.kmutt.research.model.DocTypeM;
import th.ac.kmutt.research.model.JournalPaperM;
import th.ac.kmutt.research.portal.domain.UserPortal;
import th.ac.kmutt.research.repository.ResearchRepository;
import th.ac.kmutt.research.service.ResearchService;
import th.ac.kmutt.research.xstream.common.Paging;

@Service("researchServiceJpaImpl")
public class ResearchServiceJpaImpl implements ResearchService {


    @Autowired
    @Qualifier("researchRepository")
    private ResearchRepository researchRepository;

    @Override
    public List<JournalPaperM> listJournalPaperAll() {
        // TODO Auto-generated method stub
        List<JournalPaper> list = researchRepository.listJournalPaperAll();

        List<JournalPaperM> listResult = new ArrayList<JournalPaperM>(list.size());
        for (JournalPaper journalPaper : list) {
            JournalPaperM journalPaperM = new JournalPaperM();
            BeanUtils.copyProperties(journalPaper, journalPaperM);
            listResult.add(journalPaperM);
        }

        return listResult;
    }

    @Override
    public Integer saveResearchGroup(ResearchGroup transientInstance)
            throws DataAccessException {
        // TODO Auto-generated method stub
        return researchRepository.saveResearchGroup(transientInstance);
    }

    @Override
    public Integer updateResearchGroup(ResearchGroup transientInstance
    ) throws DataAccessException {
        // TODO Auto-generated method stub
        return researchRepository.updateResearchGroup(transientInstance);
    }

    @Override
    public Integer deleteResearchGroup(ResearchGroup persistentInstance) throws DataAccessException
    //throws DataAccessException { throws ImakeException
    {
        // TODO Auto-generated method stub
        return researchRepository.deleteResearchGroup(persistentInstance);
    }

    @Override
    public ResearchGroup findResearchGroupById(Integer researchGroupId)
            throws DataAccessException {
        // TODO Auto-generated method stub
        return researchRepository.findResearchGroupById(researchGroupId);
    }

    @Override
    public List searchResearchGroup(ResearchGroup persistentInstance,
                                    Paging paging, String keySearch) throws DataAccessException {
        // TODO Auto-generated method stub
        return researchRepository.searchResearchGroup(persistentInstance, paging, keySearch);
    }

    @Override
    public Integer saveUtilizationType(UtilizationType transientInstance)
            throws DataAccessException {
        // TODO Auto-generated method stub
        return researchRepository.saveUtilizationType(transientInstance);
    }

    @Override
    public Integer updateUtilizationType(UtilizationType transientInstance)
            throws DataAccessException {
        // TODO Auto-generated method stub
        return researchRepository.updateUtilizationType(transientInstance);
    }

    @Override
    public Integer deleteUtilizationType(UtilizationType persistentInstance)
            throws DataAccessException {
        // TODO Auto-generated method stub
        return researchRepository.deleteUtilizationType(persistentInstance);
    }

    @Override
    public UtilizationType findUtilizationTypeById(Integer researchGroupId)
            throws DataAccessException {
        // TODO Auto-generated method stub
        return researchRepository.findUtilizationTypeById(researchGroupId);
    }

    @Override
    public List searchUtilizationType(UtilizationType persistentInstance,
                                      Paging paging, String keySearch) throws DataAccessException {
        // TODO Auto-generated method stub
        return researchRepository.searchUtilizationType(persistentInstance, paging, keySearch);
    }

    @Override
    public Integer savePublishType(PublishType transientInstance)
            throws DataAccessException {
        // TODO Auto-generated method stub
        return researchRepository.savePublishType(transientInstance);
    }

    @Override
    public Integer updatePublishType(PublishType transientInstance)
            throws DataAccessException {
        // TODO Auto-generated method stub
        return researchRepository.updatePublishType(transientInstance);
    }

    @Override
    public Integer deletePublishType(PublishType persistentInstance)
            throws DataAccessException {
        // TODO Auto-generated method stub
        return researchRepository.deletePublishType(persistentInstance);
    }

    @Override
    public PublishType findPublishTypeById(Integer researchGroupId)
            throws DataAccessException {
        // TODO Auto-generated method stub
        return researchRepository.findPublishTypeById(researchGroupId);
    }

    @Override
    public List searchPublishType(PublishType persistentInstance,
                                  Paging paging, String keySearch) throws DataAccessException {
        // TODO Auto-generated method stub
        return researchRepository.searchPublishType(persistentInstance, paging, keySearch);
    }

    @Override
    public Integer savePosition(Position transientInstance)
            throws DataAccessException {
        // TODO Auto-generated method stub
        return researchRepository.savePosition(transientInstance);
    }

    @Override
    public Integer updatePosition(Position transientInstance)
            throws DataAccessException {
        // TODO Auto-generated method stub
        return researchRepository.updatePosition(transientInstance);
    }

    @Override
    public Integer deletePosition(Position persistentInstance)
            throws DataAccessException {
        // TODO Auto-generated method stub
        return researchRepository.deletePosition(persistentInstance);
    }

    @Override
    public Position findPositionById(Integer researchGroupId)
            throws DataAccessException {
        // TODO Auto-generated method stub
        return researchRepository.findPositionById(researchGroupId);
    }

    @Override
    public List searchPosition(Position persistentInstance, Paging paging,
                               String keySearch) throws DataAccessException {
        // TODO Auto-generated method stub
        return researchRepository.searchPosition(persistentInstance, paging, keySearch);
    }

    @Override
    public Integer saveOrganization(Organization transientInstance)
            throws DataAccessException {
        // TODO Auto-generated method stub
        return researchRepository.saveOrganization(transientInstance);
    }

    @Override
    public Integer updateOrganization(Organization transientInstance)
            throws DataAccessException {
        // TODO Auto-generated method stub
        return researchRepository.updateOrganization(transientInstance);
    }

    @Override
    public Integer deleteOrganization(Organization persistentInstance)
            throws DataAccessException {
        // TODO Auto-generated method stub
        return researchRepository.deleteOrganization(persistentInstance);
    }

    @Override
    public Organization findOrganizationById(Integer researchGroupId)
            throws DataAccessException {
        // TODO Auto-generated method stub
        return researchRepository.findOrganizationById(researchGroupId);
    }

    @Override
    public List searchOrganization(Organization persistentInstance,
                                   Paging paging, String keySearch) throws DataAccessException {
        // TODO Auto-generated method stub
        return researchRepository.searchOrganization(persistentInstance, paging, keySearch);
    }

    @Override
    public Integer saveCountry(Country transientInstance)
            throws DataAccessException {
        // TODO Auto-generated method stub
        return researchRepository.saveCountry(transientInstance);
    }

    @Override
    public Integer updateCountry(Country transientInstance)
            throws DataAccessException {
        // TODO Auto-generated method stub
        return researchRepository.updateCountry(transientInstance);
    }

    @Override
    public Integer deleteCountry(Country persistentInstance)
            throws DataAccessException {
        // TODO Auto-generated method stub
        return researchRepository.deleteCountry(persistentInstance);
    }

    @Override
    public Country findCountryById(Integer researchGroupId)
            throws DataAccessException {
        // TODO Auto-generated method stub
        return researchRepository.findCountryById(researchGroupId);
    }

    @Override
    public List searchCountry(Country persistentInstance, Paging paging,
                              String keySearch) throws DataAccessException {
        // TODO Auto-generated method stub
        return researchRepository.searchCountry(persistentInstance, paging, keySearch);
    }

    @Override
    public Integer saveWorkType(WorkType transientInstance)
            throws DataAccessException {
        // TODO Auto-generated method stub
        return researchRepository.saveWorkType(transientInstance);
    }

    @Override
    public Integer updateWorkType(WorkType transientInstance)
            throws DataAccessException {
        // TODO Auto-generated method stub
        return researchRepository.updateWorkType(transientInstance);
    }

    @Override
    public Integer deleteWorkType(WorkType persistentInstance)
            throws DataAccessException {
        // TODO Auto-generated method stub
        return researchRepository.deleteWorkType(persistentInstance);
    }

    @Override
    public WorkType findWorkTypeById(Integer researchGroupId)
            throws DataAccessException {
        // TODO Auto-generated method stub
        return researchRepository.findWorkTypeById(researchGroupId);
    }

    @Override
    public List searchWorkType(WorkType persistentInstance, Paging paging,
                               String keySearch) throws DataAccessException {
        // TODO Auto-generated method stub
        return researchRepository.searchWorkType(persistentInstance, paging, keySearch);
    }

    @Override
    public Integer saveJournalPapersType(JournalPapersType transientInstance)
            throws DataAccessException {
        // TODO Auto-generated method stub
        return researchRepository.saveJournalPapersType(transientInstance);
    }

    @Override
    public Integer updateJournalPapersType(JournalPapersType transientInstance)
            throws DataAccessException {
        // TODO Auto-generated method stub
        return researchRepository.updateJournalPapersType(transientInstance);
    }

    @Override
    public Integer deleteJournalPapersType(JournalPapersType persistentInstance)
            throws DataAccessException {
        // TODO Auto-generated method stub
        return researchRepository.deleteJournalPapersType(persistentInstance);
    }

    @Override
    public JournalPapersType findJournalPapersTypeById(Integer researchGroupId)
            throws DataAccessException {
        // TODO Auto-generated method stub
        return researchRepository.findJournalPapersTypeById(researchGroupId);
    }

    @Override
    public List searchJournalPapersType(JournalPapersType persistentInstance,
                                        Paging paging, String keySearch) throws DataAccessException {
        // TODO Auto-generated method stub
        return researchRepository.searchJournalPapersType(persistentInstance, paging, keySearch);
    }

    @Override
    public Integer saveFundingResourceType(FundingResourceType transientInstance)
            throws DataAccessException {
        // TODO Auto-generated method stub
        return researchRepository.saveFundingResourceType(transientInstance);
    }

    @Override
    public Integer updateFundingResourceType(
            FundingResourceType transientInstance) throws DataAccessException {
        // TODO Auto-generated method stub
        return researchRepository.updateFundingResourceType(transientInstance);
    }

    @Override
    public Integer deleteFundingResourceType(
            FundingResourceType persistentInstance) throws DataAccessException {
        // TODO Auto-generated method stub
        return researchRepository.deleteFundingResourceType(persistentInstance);
    }

    @Override
    public FundingResourceType findFundingResourceTypeById(
            Integer researchGroupId) throws DataAccessException {
        // TODO Auto-generated method stub
        return researchRepository.findFundingResourceTypeById(researchGroupId);
    }

    @Override
    public List searchFundingResourceType(
            FundingResourceType persistentInstance, Paging paging,
            String keySearch) throws DataAccessException {
        // TODO Auto-generated method stub
        return researchRepository.searchFundingResourceType(persistentInstance, paging, keySearch);
    }

    @Override
    public Integer saveResearcher(Researcher transientInstance)
            throws DataAccessException {
        // TODO Auto-generated method stub
        return researchRepository.saveResearcher(transientInstance);
    }

    @Override
    public Integer updateResearcher(Researcher transientInstance)
            throws DataAccessException {
        // TODO Auto-generated method stub
        return researchRepository.updateResearcher(transientInstance);
    }

    @Override
    public Integer deleteResearcher(Researcher persistentInstance)
            throws DataAccessException {
        // TODO Auto-generated method stub
        return researchRepository.deleteResearcher(persistentInstance);
    }

    @Override
    public Researcher findResearcherById(Integer researchGroupId)
            throws DataAccessException {
        // TODO Auto-generated method stub
        return researchRepository.findResearcherById(researchGroupId);
    }

    @Override
    public List searchResearcher(Researcher persistentInstance, Paging paging,
                                 String keySearch) throws DataAccessException {
        // TODO Auto-generated method stub
        return researchRepository.searchResearcher(persistentInstance, paging, keySearch);
    }

    @Override
    public Integer saveResearcherGroup(
            ResearcherGroup transientInstance)
            throws DataAccessException {
        // TODO Auto-generated method stub
        return researchRepository.saveResearcherGroup(transientInstance);
    }

    @Override
    public Integer updateResearcherGroup(
            ResearcherGroup transientInstance)
            throws DataAccessException {
        // TODO Auto-generated method stub
        return researchRepository.updateResearcherGroup(transientInstance);
    }

    @Override
    public Integer deleteResearcherGroup(
            ResearcherGroup persistentInstance)
            throws DataAccessException {
        // TODO Auto-generated method stub
        return researchRepository.deleteResearcherGroup(persistentInstance);
    }

    @Override
    public ResearcherGroup findResearcherGroupById(
            Integer researchGroupId, Integer researcherId) throws DataAccessException {
        // TODO Auto-generated method stub
        return researchRepository.findResearcherGroupById(researchGroupId, researcherId);
    }

    @Override
    public List searchResearcherGroup(
            ResearcherGroup persistentInstance, Paging paging,
            String keySearch) throws DataAccessException {
        // TODO Auto-generated method stub
        return researchRepository.searchResearcherGroup(persistentInstance, paging, keySearch);
    }

    @Override
    public Integer savePublishLevel(PublishLevel transientInstance)
            throws DataAccessException {
        // TODO Auto-generated method stub
        return researchRepository.savePublishLevel(transientInstance);
    }

    @Override
    public Integer updatePublishLevel(PublishLevel transientInstance)
            throws DataAccessException {
        // TODO Auto-generated method stub
        return researchRepository.updatePublishLevel(transientInstance);
    }

    @Override
    public Integer deletePublishLevel(PublishLevel persistentInstance)
            throws DataAccessException {
        // TODO Auto-generated method stub
        return researchRepository.deletePublishLevel(persistentInstance);
    }

    @Override
    public PublishLevel findPublishLevelById(Integer researchGroupId)
            throws DataAccessException {
        // TODO Auto-generated method stub
        return researchRepository.findPublishLevelById(researchGroupId);
    }

    @Override
    public List searchPublishLevel(PublishLevel persistentInstance,
                                   Paging paging, String keySearch) throws DataAccessException {
        // TODO Auto-generated method stub
        return researchRepository.searchPublishLevel(persistentInstance, paging, keySearch);
    }

    @Override
    public Integer saveOrganizationExt(OrganizationExt transientInstance)
            throws DataAccessException {
        // TODO Auto-generated method stub
        return researchRepository.saveOrganizationExt(transientInstance);
    }

    @Override
    public Integer updateOrganizationExt(OrganizationExt transientInstance)
            throws DataAccessException {
        // TODO Auto-generated method stub
        return researchRepository.updateOrganizationExt(transientInstance);
    }

    @Override
    public Integer deleteOrganizationExt(OrganizationExt persistentInstance)
            throws DataAccessException {
        // TODO Auto-generated method stub
        return researchRepository.deleteOrganizationExt(persistentInstance);
    }

    @Override
    public OrganizationExt findOrganizationExtById(Integer researchGroupId)
            throws DataAccessException {
        // TODO Auto-generated method stub
        return researchRepository.findOrganizationExtById(researchGroupId);
    }

    @Override
    public List searchOrganizationExt(OrganizationExt persistentInstance,
                                      Paging paging, String keySearch) throws DataAccessException {
        // TODO Auto-generated method stub
        return researchRepository.searchOrganizationExt(persistentInstance, paging, keySearch);
    }

    @Override
    public Integer saveFundingResource(FundingResource transientInstance)
            throws DataAccessException {
        // TODO Auto-generated method stub
        return researchRepository.saveFundingResource(transientInstance);
    }

    @Override
    public Integer updateFundingResource(FundingResource transientInstance)
            throws DataAccessException {
        // TODO Auto-generated method stub
        return researchRepository.updateFundingResource(transientInstance);
    }

    @Override
    public Integer deleteFundingResource(FundingResource persistentInstance)
            throws DataAccessException {
        // TODO Auto-generated method stub
        return researchRepository.deleteFundingResource(persistentInstance);
    }

    @Override
    public FundingResource findFundingResourceById(Integer researchGroupId)
            throws DataAccessException {
        // TODO Auto-generated method stub
        return researchRepository.findFundingResourceById(researchGroupId);
    }

    @Override
    public List searchFundingResource(FundingResource persistentInstance,
                                      Paging paging, String keySearch) throws DataAccessException {
        // TODO Auto-generated method stub
        return researchRepository.searchFundingResource(persistentInstance, paging, keySearch);
    }

    @Override
    public Integer saveResearchProject(ResearchProject transientInstance) {
        // TODO Auto-generated method stub
        return researchRepository.saveResearchProject(transientInstance);
    }

    @Override
    public Integer updateResearchProject(ResearchProject transientInstance) {
        // TODO Auto-generated method stub
        return researchRepository.updateResearchProject(transientInstance);
    }

    @Override
    public Integer deleteResearchProject(ResearchProject persistentInstance) {
        // TODO Auto-generated method stub
        return researchRepository.deleteResearchProject(persistentInstance);
    }

    @Override
    public ResearchProject findResearchProjectById(Integer researchGroupId, String userid) {
        // TODO Auto-generated method stub
        return researchRepository.findResearchProjectById(researchGroupId, userid);
    }

    @Override
    public List searchResearchProject(ResearchProject persistentInstance,
                                      Paging paging, String keySearch, String userid) throws DataAccessException {
        // TODO Auto-generated method stub
        return researchRepository.searchResearchProject(persistentInstance, paging, keySearch, userid);
    }

    @Override
    public Integer saveJournalPaper(JournalPaper transientInstance) {
        // TODO Auto-generated method stub
        return researchRepository.saveJournalPaper(transientInstance);
    }

    @Override
    public Integer updateJournalPaper(JournalPaper transientInstance) {
        // TODO Auto-generated method stub
        return researchRepository.updateJournalPaper(transientInstance);
    }

    @Override
    public Integer deleteJournalPaper(JournalPaper persistentInstance) {
        // TODO Auto-generated method stub
        return researchRepository.deleteJournalPaper(persistentInstance);
    }

    @Override
    public JournalPaper findJournalPapersById(Integer researchGroupId, String userid) {
        // TODO Auto-generated method stub
        return researchRepository.findJournalPapersById(researchGroupId, userid);
    }

    @Override
    public List searchJournalPaper(JournalPaper persistentInstance,
                                   Paging paging, String keySearch, String userid) throws DataAccessException {
        // TODO Auto-generated method stub
        return researchRepository.searchJournalPaper(persistentInstance, paging, keySearch, userid);
    }

    @Override
    public Integer saveUtilization(Utilization transientInstance) {
        // TODO Auto-generated method stub
        return researchRepository.saveUtilization(transientInstance);
    }

    @Override
    public Integer updateUtilization(Utilization transientInstance) {
        // TODO Auto-generated method stub
        return researchRepository.updateUtilization(transientInstance);
    }

    @Override
    public Integer deleteUtilization(Utilization persistentInstance) {
        // TODO Auto-generated method stub
        return researchRepository.deleteUtilization(persistentInstance);
    }

    @Override
    public List<Utilization> findUtilizationById(Integer researchGroupId) {
        // TODO Auto-generated method stub
        return researchRepository.findUtilizationById(researchGroupId);
    }

    @Override
    public List searchUtilization(Utilization persistentInstance,
                                  Paging paging, String keySearch) throws DataAccessException {
        // TODO Auto-generated method stub
        return researchRepository.searchUtilization(persistentInstance, paging, keySearch);
    }

    @Override
    public Integer saveCopyright(Copyright transientInstance) {
        // TODO Auto-generated method stub
        return researchRepository.saveCopyright(transientInstance);
    }

    @Override
    public Integer updateCopyright(Copyright transientInstance) {
        // TODO Auto-generated method stub
        return researchRepository.updateCopyright(transientInstance);
    }

    @Override
    public Integer deleteCopyright(Copyright persistentInstance) {
        // TODO Auto-generated method stub
        return researchRepository.deleteCopyright(persistentInstance);
    }

    @Override
    public Copyright findResearchCopyRightById(Integer researchGroupId, String userid) {
        // TODO Auto-generated method stub
        return researchRepository.findResearchCopyRightById(researchGroupId, userid);
    }

    @Override
    public List searchCopyright(Copyright persistentInstance,
                                Paging paging, String keySearch, String userid) throws DataAccessException {
        // TODO Auto-generated method stub
        return researchRepository.searchCopyright(persistentInstance, paging, keySearch, userid);
    }

    @Override
    public Integer saveReward(Reward transientInstance) {
        // TODO Auto-generated method stub
        return researchRepository.saveReward(transientInstance);
    }

    @Override
    public Integer updateReward(Reward transientInstance) {
        // TODO Auto-generated method stub
        return researchRepository.updateReward(transientInstance);
    }

    @Override
    public Integer deleteReward(Reward persistentInstance) {
        // TODO Auto-generated method stub
        return researchRepository.deleteReward(persistentInstance);
    }

    @Override
    public Reward findRewardById(Integer researchGroupId, String userid) {
        // TODO Auto-generated method stub
        return researchRepository.findRewardById(researchGroupId, userid);
    }

    @Override
    public List searchReward(Reward persistentInstance,
                             Paging paging, String keySearch, String userid) throws DataAccessException {
        // TODO Auto-generated method stub
        return researchRepository.searchReward(persistentInstance, paging, keySearch, userid);
    }

    @Override
    public Integer savePatent(Patent transientInstance) {
        // TODO Auto-generated method stub
        return researchRepository.savePatent(transientInstance);
    }

    @Override
    public Integer updatePatent(Patent transientInstance) {
        // TODO Auto-generated method stub
        return researchRepository.updatePatent(transientInstance);
    }

    @Override
    public Integer deletePatent(Patent persistentInstance) {
        // TODO Auto-generated method stub
        return researchRepository.deletePatent(persistentInstance);
    }

    @Override
    public Patent findPatentById(Integer researchGroupId, String userid) {
        // TODO Auto-generated method stub
        return researchRepository.findPatentById(researchGroupId, userid);
    }

    @Override
    public List searchPatent(Patent persistentInstance,
                             Paging paging, String keySearch, String userid) throws DataAccessException {
        // TODO Auto-generated method stub
        return researchRepository.searchPatent(persistentInstance, paging, keySearch, userid);
    }

    @Override
    public Integer updateDocTypeM(DocTypeM transientInstance) {
        // TODO Auto-generated method stub
        return researchRepository.updateDocTypeM(transientInstance);
    }

    @Override
    public List listDocTypeM() throws DataAccessException {
        // TODO Auto-generated method stub
        return researchRepository.listDocTypeM();
    }

    @Override
    public List searchUserPortal(UserPortal persistentInstance, Paging paging,
                                 String keySearch) throws DataAccessException {
        // TODO Auto-generated method stub
        return researchRepository.searchUserPortal(persistentInstance, paging, keySearch);
    }

    @Override
    public UserPortal findUserPortalById(Integer researchGroupId) {
        // TODO Auto-generated method stub
        return researchRepository.findUserPortalById(researchGroupId);
    }

    @Override
    public List<ResearchProjectDocument> listResearchProjectDocument(
            Integer researchProjectId) {
        // TODO Auto-generated method stub
        return researchRepository.listResearchProjectDocument(researchProjectId);
    }

    @Override
    public List<ResearchProjectPayment> listResearchProjectPayment(
            Integer researchProjectId) {
        // TODO Auto-generated method stub
        return researchRepository.listResearchProjectPayment(researchProjectId);
    }

    @Override
    public List<ResearchProjectProgress> listResearchProjectProgress(
            Integer researchProjectId) {
        // TODO Auto-generated method stub
        return researchRepository.listResearchProjectProgress(researchProjectId);
    }

    @Override
    public List<ResearchProjectResearcher> listResearchProjectResearcher(
            Integer researchProjectId) {
        // TODO Auto-generated method stub
        return researchRepository.listResearchProjectResearcher(researchProjectId);
    }

    @Override
    public List<ResearchProjectWithdraw> listResearchProjectWithdraw(
            Integer researchProjectId) {
        // TODO Auto-generated method stub
        return researchRepository.listResearchProjectWithdraw(researchProjectId);
    }

    @Override
    public List<CopyrightCreator> listCopyrightCreator(Integer copyrightId) {
        // TODO Auto-generated method stub
        return researchRepository.listCopyrightCreator(copyrightId);
    }

    @Override
    public List<RewardCreator> listRewardCreator(Integer rewardId) {
        // TODO Auto-generated method stub
        return researchRepository.listRewardCreator(rewardId);
    }

    @Override
    public List<PatentCreator> listPatentCreator(Integer inventionId) {
        // TODO Auto-generated method stub
        return researchRepository.listPatentCreator(inventionId);
    }

    @Override
    public List<PatentDocument> listPatentDocument(Integer inventionId) {
        // TODO Auto-generated method stub
        return researchRepository.listPatentDocument(inventionId);
    }

    @Override
    public List<PatentEditDate> listPatentEditDate(Integer inventionId) {
        // TODO Auto-generated method stub
        return researchRepository.listPatentEditDate(inventionId);
    }

    @Override
    public List<PatentFeePayment> listPatentFeePayment(Integer inventionId) {
        // TODO Auto-generated method stub
        return researchRepository.listPatentFeePayment(inventionId);
    }

    @Override
    public List<PatentInherited> listPatentInherited(Integer inventionId) {
        // TODO Auto-generated method stub
        return researchRepository.listPatentInherited(inventionId);
    }

    @Override
    public List<PatentRightholder> listPatentRightholder(Integer inventionId) {
        // TODO Auto-generated method stub
        return researchRepository.listPatentRightholder(inventionId);
    }

    @Override
    public Integer saveDocAssignMapping(DocAssignMapping transientInstance) {
        // TODO Auto-generated method stub
        return researchRepository.saveDocAssignMapping(transientInstance);
    }

    @Override
    public Integer deleteDocAssignMapping(DocAssignMapping persistentInstance) {
        // TODO Auto-generated method stub
        return researchRepository.deleteDocAssignMapping(persistentInstance);
    }

    @Override
    public DocAssignMapping findDocAssignMappingById(Integer refId, String refType) {
        // TODO Auto-generated method stub
        return researchRepository.findDocAssignMappingById(refId, refType);
    }

    @Override
    public List listDocAssignMapping(DocAssignMapping persistentInstance)
            throws DataAccessException {
        // TODO Auto-generated method stub
        return researchRepository.listDocAssignMapping(persistentInstance);
    }

    @Override
    public Integer deleteUtilizationItem(Utilization persistentInstance) {
        // TODO Auto-generated method stub
        return researchRepository.deleteUtilizationItem(persistentInstance);
    }

    @Override
    public Integer saveResearchProjectDocument(
            ResearchProjectDocument transientInstance) {
        // TODO Auto-generated method stub
        return researchRepository.saveResearchProjectDocument(transientInstance);
    }

    @Override
    public Integer updateResearchProjectDocument(
            ResearchProjectDocument transientInstance) {
        // TODO Auto-generated method stub
        return researchRepository.updateResearchProjectDocument(transientInstance);
    }

    @Override
    public Integer deleteResearchProjectDocument(
            ResearchProjectDocument persistentInstance) {
        // TODO Auto-generated method stub
        return researchRepository.deleteResearchProjectDocument(persistentInstance);
    }

    @Override
    public Integer saveResearchProjectPayment(
            ResearchProjectPayment transientInstance) {
        // TODO Auto-generated method stub
        return researchRepository.saveResearchProjectPayment(transientInstance);
    }

    @Override
    public Integer updateResearchProjectPayment(
            ResearchProjectPayment transientInstance) {
        // TODO Auto-generated method stub
        return researchRepository.updateResearchProjectPayment(transientInstance);
    }

    @Override
    public Integer deleteResearchProjectPayment(
            ResearchProjectPayment persistentInstance) {
        // TODO Auto-generated method stub
        return researchRepository.deleteResearchProjectPayment(persistentInstance);
    }

    @Override
    public Integer saveResearchProjectProgress(
            ResearchProjectProgress transientInstance) {
        // TODO Auto-generated method stub
        return researchRepository.saveResearchProjectProgress(transientInstance);
    }

    @Override
    public Integer updateResearchProjectProgress(
            ResearchProjectProgress transientInstance) {
        // TODO Auto-generated method stub
        return researchRepository.updateResearchProjectProgress(transientInstance);
    }

    @Override
    public Integer deleteResearchProjectProgress(
            ResearchProjectProgress persistentInstance) {
        // TODO Auto-generated method stub
        return researchRepository.deleteResearchProjectProgress(persistentInstance);
    }

    @Override
    public Integer saveResearchProjectResearcher(
            ResearchProjectResearcher transientInstance) {
        // TODO Auto-generated method stub
        return researchRepository.saveResearchProjectResearcher(transientInstance);
    }

    @Override
    public Integer updateResearchProjectResearcher(
            ResearchProjectResearcher transientInstance) {
        // TODO Auto-generated method stub
        return researchRepository.updateResearchProjectResearcher(transientInstance);
    }

    @Override
    public Integer deleteResearchProjectResearcher(
            ResearchProjectResearcher persistentInstance) {
        // TODO Auto-generated method stub
        return researchRepository.deleteResearchProjectResearcher(persistentInstance);
    }

    @Override
    public Integer saveResearchProjectWithdraw(
            ResearchProjectWithdraw transientInstance) {
        // TODO Auto-generated method stub
        return researchRepository.saveResearchProjectWithdraw(transientInstance);
    }

    @Override
    public Integer updateResearchProjectWithdraw(
            ResearchProjectWithdraw transientInstance) {
        // TODO Auto-generated method stub
        return researchRepository.updateResearchProjectWithdraw(transientInstance);
    }

    @Override
    public Integer deleteResearchProjectWithdraw(
            ResearchProjectWithdraw persistentInstance) {
        // TODO Auto-generated method stub
        return researchRepository.deleteResearchProjectWithdraw(persistentInstance);
    }

    @Override
    public List<JournalPapersDocument> listJournalPapersDocument(
            Integer researchProjectId) {
        // TODO Auto-generated method stub
        return researchRepository.listJournalPapersDocument(researchProjectId);
    }

    @Override
    public List<JournalPapersWriter> listJournalPapersWriter(
            Integer researchProjectId) {
        // TODO Auto-generated method stub
        return researchRepository.listJournalPapersWriter(researchProjectId);
    }

    @Override
    public Integer saveJournalPapersDocument(
            JournalPapersDocument transientInstance) {
        // TODO Auto-generated method stub
        return researchRepository.saveJournalPapersDocument(transientInstance);
    }

    @Override
    public Integer updateJournalPapersDocument(
            JournalPapersDocument transientInstance) {
        // TODO Auto-generated method stub
        return researchRepository.updateJournalPapersDocument(transientInstance);
    }

    @Override
    public Integer deleteJournalPapersDocument(
            JournalPapersDocument persistentInstance) {
        // TODO Auto-generated method stub
        return researchRepository.deleteJournalPapersDocument(persistentInstance);
    }

    @Override
    public Integer saveJournalPapersWriter(JournalPapersWriter transientInstance) {
        // TODO Auto-generated method stub
        return researchRepository.saveJournalPapersWriter(transientInstance);
    }

    @Override
    public Integer updateJournalPapersWriter(
            JournalPapersWriter transientInstance) {
        // TODO Auto-generated method stub
        return researchRepository.updateJournalPapersWriter(transientInstance);
    }

    @Override
    public Integer deleteJournalPapersWriter(
            JournalPapersWriter persistentInstance) {
        // TODO Auto-generated method stub
        return researchRepository.deleteJournalPapersWriter(persistentInstance);
    }

    @Override
    public Integer saveCopyrightCreator(CopyrightCreator transientInstance) {
        // TODO Auto-generated method stub
        return researchRepository.saveCopyrightCreator(transientInstance);
    }

    @Override
    public Integer updateCopyrightCreator(CopyrightCreator transientInstance) {
        // TODO Auto-generated method stub
        return researchRepository.updateCopyrightCreator(transientInstance);
    }

    @Override
    public Integer deleteCopyrightCreator(CopyrightCreator persistentInstance) {
        // TODO Auto-generated method stub
        return researchRepository.deleteCopyrightCreator(persistentInstance);
    }

    @Override
    public Integer saveRewardCreator(RewardCreator transientInstance) {
        // TODO Auto-generated method stub
        return researchRepository.saveRewardCreator(transientInstance);
    }

    @Override
    public Integer updateRewardCreator(RewardCreator transientInstance) {
        // TODO Auto-generated method stub
        return researchRepository.updateRewardCreator(transientInstance);
    }

    @Override
    public Integer deleteRewardCreator(RewardCreator persistentInstance) {
        // TODO Auto-generated method stub
        return researchRepository.deleteRewardCreator(persistentInstance);
    }

    @Override
    public Integer savePatentCreator(PatentCreator transientInstance) {
        // TODO Auto-generated method stub
        return researchRepository.savePatentCreator(transientInstance);
    }

    @Override
    public Integer updatePatentCreator(PatentCreator transientInstance) {
        // TODO Auto-generated method stub
        return researchRepository.updatePatentCreator(transientInstance);
    }

    @Override
    public Integer deletePatentCreator(PatentCreator persistentInstance) {
        // TODO Auto-generated method stub
        return researchRepository.deletePatentCreator(persistentInstance);
    }

    @Override
    public Integer savePatentDocument(PatentDocument transientInstance) {
        // TODO Auto-generated method stub
        return researchRepository.savePatentDocument(transientInstance);
    }

    @Override
    public Integer updatePatentDocument(PatentDocument transientInstance) {
        // TODO Auto-generated method stub
        return researchRepository.updatePatentDocument(transientInstance);
    }

    @Override
    public Integer deletePatentDocument(PatentDocument persistentInstance) {
        // TODO Auto-generated method stub
        return researchRepository.deletePatentDocument(persistentInstance);
    }

    @Override
    public Integer savePatentEditDate(PatentEditDate transientInstance) {
        // TODO Auto-generated method stub
        return researchRepository.savePatentEditDate(transientInstance);
    }

    @Override
    public Integer updatePatentEditDate(PatentEditDate transientInstance) {
        // TODO Auto-generated method stub
        return researchRepository.updatePatentEditDate(transientInstance);
    }

    @Override
    public Integer deletePatentEditDate(PatentEditDate persistentInstance) {
        // TODO Auto-generated method stub
        return researchRepository.deletePatentEditDate(persistentInstance);
    }

    @Override
    public Integer savePatentFeePayment(PatentFeePayment transientInstance) {
        // TODO Auto-generated method stub
        return researchRepository.savePatentFeePayment(transientInstance);
    }

    @Override
    public Integer updatePatentFeePayment(PatentFeePayment transientInstance) {
        // TODO Auto-generated method stub
        return researchRepository.updatePatentFeePayment(transientInstance);
    }

    @Override
    public Integer deletePatentFeePayment(PatentFeePayment persistentInstance) {
        // TODO Auto-generated method stub
        return researchRepository.deletePatentFeePayment(persistentInstance);
    }

    @Override
    public Integer savePatentInherited(PatentInherited transientInstance) {
        // TODO Auto-generated method stub
        return researchRepository.savePatentInherited(transientInstance);
    }

    @Override
    public Integer updatePatentInherited(PatentInherited transientInstance) {
        // TODO Auto-generated method stub
        return researchRepository.updatePatentInherited(transientInstance);
    }

    @Override
    public Integer deletePatentInherited(PatentInherited persistentInstance) {
        // TODO Auto-generated method stub
        return researchRepository.deletePatentInherited(persistentInstance);
    }

    @Override
    public Integer savePatentRightholder(PatentRightholder transientInstance) {
        // TODO Auto-generated method stub
        return researchRepository.savePatentRightholder(transientInstance);
    }

    @Override
    public Integer updatePatentRightholder(PatentRightholder transientInstance) {
        // TODO Auto-generated method stub
        return researchRepository.updatePatentRightholder(transientInstance);
    }

    @Override
    public Integer deletePatentRightholder(PatentRightholder persistentInstance) {
        // TODO Auto-generated method stub
        return researchRepository.deletePatentRightholder(persistentInstance);
    }

    @Override
    public Integer saveTitle(Title transientInstance)
            throws DataAccessException {
        // TODO Auto-generated method stub
        return researchRepository.saveTitle(transientInstance);
    }

    @Override
    public Integer updateTitle(Title transientInstance)
            throws DataAccessException {
        // TODO Auto-generated method stub
        return researchRepository.updateTitle(transientInstance);
    }

    @Override
    public Integer deleteTitle(Title persistentInstance)
            throws DataAccessException {
        // TODO Auto-generated method stub
        return researchRepository.deleteTitle(persistentInstance);
    }

    @Override
    public Title findTitleById(Integer researchGroupId)
            throws DataAccessException {
        // TODO Auto-generated method stub
        return researchRepository.findTitleById(researchGroupId);
    }

    @Override
    public List searchTitle(Title persistentInstance, Paging paging,
                            String keySearch) throws DataAccessException {
        // TODO Auto-generated method stub
        return researchRepository.searchTitle(persistentInstance, paging, keySearch);
    }

    @Override
    public Integer saveJournalPapersJournal(
            JournalPapersJournal transientInstance) {
        // TODO Auto-generated method stub
        return researchRepository.saveJournalPapersJournal(transientInstance);
    }

    @Override
    public Integer updateJournalPapersJournal(
            JournalPapersJournal transientInstance) {
        // TODO Auto-generated method stub
        return researchRepository.updateJournalPapersJournal(transientInstance);
    }

    @Override
    public Integer deleteJournalPapersJournal(
            JournalPapersJournal persistentInstance) {
        // TODO Auto-generated method stub
        return researchRepository.deleteJournalPapersJournal(persistentInstance);
    }

    @Override
    public JournalPapersJournal findJournalPapersJournalsById(
            Integer researchGroupId) {
        // TODO Auto-generated method stub
        return researchRepository.findJournalPapersJournalsById(researchGroupId);
    }

    @Override
    public Integer saveJournalPapersConference(
            JournalPapersConference transientInstance) {
        // TODO Auto-generated method stub
        return researchRepository.saveJournalPapersConference(transientInstance);
    }

    @Override
    public Integer updateJournalPapersConference(
            JournalPapersConference transientInstance) {
        // TODO Auto-generated method stub
        return researchRepository.updateJournalPapersConference(transientInstance);
    }

    @Override
    public Integer deleteJournalPapersConference(
            JournalPapersConference persistentInstance) {
        // TODO Auto-generated method stub
        return researchRepository.deleteJournalPapersConference(persistentInstance);
    }

    @Override
    public JournalPapersConference findJournalPapersConferencesById(
            Integer researchGroupId) {
        // TODO Auto-generated method stub
        return researchRepository.findJournalPapersConferencesById(researchGroupId);
    }

    @Override
    public List<ResearcherGroup> listResearcherGroup(Integer researchProjectId) {
        // TODO Auto-generated method stub
        return researchRepository.listResearcherGroup(researchProjectId);
    }

    @Override
    public ResearchProjectDocument findResearchProjectDocumentById(
            ResearchProjectDocumentPK id) {
        // TODO Auto-generated method stub
        return researchRepository.findResearchProjectDocumentById(id);
    }

    @Override
    public ResearchProjectPayment findResearchProjectPaymentById(
            ResearchProjectPaymentPK id) {
        // TODO Auto-generated method stub
        return researchRepository.findResearchProjectPaymentById(id);
    }

    @Override
    public ResearchProjectProgress findResearchProjectProgressById(
            ResearchProjectProgressPK id) {
        // TODO Auto-generated method stub
        return researchRepository.findResearchProjectProgressById(id);
    }

    @Override
    public ResearchProjectResearcher findResearchProjectResearcherById(
            ResearchProjectResearcherPK id) {
        // TODO Auto-generated method stub
        return researchRepository.findResearchProjectResearcherById(id);
    }

    @Override
    public ResearchProjectWithdraw findResearchProjectWithdrawById(
            ResearchProjectWithdrawPK id) {
        // TODO Auto-generated method stub
        return researchRepository.findResearchProjectWithdrawById(id);
    }

    @Override
    public JournalPapersDocument findJournalPapersDocumentById(
            JournalPapersDocumentPK id) {
        // TODO Auto-generated method stub
        return researchRepository.findJournalPapersDocumentById(id);
    }

    @Override
    public JournalPapersWriter findJournalPapersWriterById(
            JournalPapersWriterPK id) {
        // TODO Auto-generated method stub
        return researchRepository.findJournalPapersWriterById(id);
    }

    @Override
    public CopyrightCreator findCopyrightCreatorById(CopyrightCreatorPK id) {
        // TODO Auto-generated method stub
        return researchRepository.findCopyrightCreatorById(id);
    }

    @Override
    public RewardCreator findRewardCreatorById(RewardCreatorPK id) {
        // TODO Auto-generated method stub
        return researchRepository.findRewardCreatorById(id);
    }

    @Override
    public PatentCreator findPatentCreatorById(PatentCreatorPK id) {
        // TODO Auto-generated method stub
        return researchRepository.findPatentCreatorById(id);
    }

    @Override
    public PatentDocument findPatentDocumentById(PatentDocumentPK id) {
        // TODO Auto-generated method stub
        return researchRepository.findPatentDocumentById(id);
    }

    @Override
    public PatentEditDate findPatentEditDateById(PatentEditDatePK id) {
        // TODO Auto-generated method stub
        return researchRepository.findPatentEditDateById(id);
    }

    @Override
    public PatentFeePayment findPatentFeePaymentById(PatentFeePaymentPK id) {
        // TODO Auto-generated method stub
        return researchRepository.findPatentFeePaymentById(id);
    }

    @Override
    public PatentInherited findPatentInheritedById(PatentInheritedPK id) {
        // TODO Auto-generated method stub
        return researchRepository.findPatentInheritedById(id);
    }

    @Override
    public PatentRightholder findPatentRightholderById(PatentRightholderPK id) {
        // TODO Auto-generated method stub
        return researchRepository.findPatentRightholderById(id);
    }

    @Override
    public Utilization findUtilizationItemById(UtilizationPK id) {
        // TODO Auto-generated method stub
        return researchRepository.findUtilizationItemById(id);
    }

    @Override
    public Integer checkUQResearchGroup(ResearchGroup transientInstance)
            throws DataAccessException {
        // TODO Auto-generated method stub
        return researchRepository.checkUQResearchGroup(transientInstance);
    }

    @Override
    public Integer checkUQUtilizationType(UtilizationType transientInstance)
            throws DataAccessException {
        // TODO Auto-generated method stub
        return researchRepository.checkUQUtilizationType(transientInstance);
    }

    @Override
    public Integer checkUQPublishType(PublishType transientInstance)
            throws DataAccessException {
        // TODO Auto-generated method stub
        return researchRepository.checkUQPublishType(transientInstance);
    }

    @Override
    public Integer checkUQPosition(Position transientInstance)
            throws DataAccessException {
        // TODO Auto-generated method stub
        return researchRepository.checkUQPosition(transientInstance);
    }

    @Override
    public Integer checkUQCountry(Country transientInstance)
            throws DataAccessException {
        // TODO Auto-generated method stub
        return researchRepository.checkUQCountry(transientInstance);
    }

    @Override
    public Integer checkUQWorkType(WorkType transientInstance)
            throws DataAccessException {
        // TODO Auto-generated method stub
        return researchRepository.checkUQWorkType(transientInstance);
    }

    @Override
    public Integer checkUQJournalPapersType(JournalPapersType transientInstance)
            throws DataAccessException {
        // TODO Auto-generated method stub
        return researchRepository.checkUQJournalPapersType(transientInstance);
    }

    @Override
    public Integer checkUQFundingResourceType(
            FundingResourceType transientInstance) throws DataAccessException {
        // TODO Auto-generated method stub
        return researchRepository.checkUQFundingResourceType(transientInstance);
    }

    @Override
    public Integer checkUQPublishLevel(PublishLevel transientInstance)
            throws DataAccessException {
        // TODO Auto-generated method stub
        return researchRepository.checkUQPublishLevel(transientInstance);
    }

    @Override
    public Integer checkUQOrganizationExt(OrganizationExt transientInstance)
            throws DataAccessException {
        // TODO Auto-generated method stub
        return researchRepository.checkUQOrganizationExt(transientInstance);
    }

    @Override
    public Integer checkUQFundingResource(FundingResource transientInstance)
            throws DataAccessException {
        // TODO Auto-generated method stub
        return researchRepository.checkUQFundingResource(transientInstance);
    }

    @Override
    public Integer countJournalPapersWriter(
            JournalPapersWriter transientInstance) {
        // TODO Auto-generated method stub
        return researchRepository.countJournalPapersWriter(transientInstance);
    }

    @Override
    public Integer countResearchProjectResearcher(
            ResearchProjectResearcher transientInstance) {
        // TODO Auto-generated method stub
        return researchRepository.countResearchProjectResearcher(transientInstance);
    }

    @Override
    public List<UtilizationDocument> listUtilizationDocument(Integer utilizationItemList, Integer researchProjectId) {
        // TODO Auto-generated method stub
        return researchRepository.listUtilizationDocument(utilizationItemList, researchProjectId);
    }

    @Override
    public Integer saveUtilizationDocument(UtilizationDocument transientInstance) {
        // TODO Auto-generated method stub
        return researchRepository.saveUtilizationDocument(transientInstance);
    }

    @Override
    public Integer updateUtilizationDocument(
            UtilizationDocument transientInstance) {
        // TODO Auto-generated method stub
        return researchRepository.updateUtilizationDocument(transientInstance);
    }

    @Override
    public Integer deleteUtilizationDocument(
            UtilizationDocument persistentInstance) {
        // TODO Auto-generated method stub
        return researchRepository.deleteUtilizationDocument(persistentInstance);
    }

    @Override
    public UtilizationDocument findUtilizationDocumentById(
            UtilizationDocumentPK id) {
        // TODO Auto-generated method stub
        return researchRepository.findUtilizationDocumentById(id);
    }

    @Override
    public List<CopyrightDocument> listCopyrightDocument(Integer copyrightId) {
        // TODO Auto-generated method stub
        return researchRepository.listCopyrightDocument(copyrightId);
    }

    @Override
    public Integer saveCopyrightDocument(CopyrightDocument transientInstance) {
        // TODO Auto-generated method stub
        return researchRepository.saveCopyrightDocument(transientInstance);
    }

    @Override
    public Integer updateCopyrightDocument(CopyrightDocument transientInstance) {
        // TODO Auto-generated method stub
        return researchRepository.updateCopyrightDocument(transientInstance);
    }

    @Override
    public Integer deleteCopyrightDocument(CopyrightDocument persistentInstance) {
        // TODO Auto-generated method stub
        return researchRepository.deleteCopyrightDocument(persistentInstance);
    }

    @Override
    public CopyrightDocument findCopyrightDocumentById(CopyrightDocumentPK id) {
        // TODO Auto-generated method stub
        return researchRepository.findCopyrightDocumentById(id);
    }

    @Override
    public List<RewardDocument> listRewardDocument(Integer rewardId) {
        // TODO Auto-generated method stub
        return researchRepository.listRewardDocument(rewardId);
    }

    @Override
    public Integer saveRewardDocument(RewardDocument transientInstance) {
        // TODO Auto-generated method stub
        return researchRepository.saveRewardDocument(transientInstance);
    }

    @Override
    public Integer updateRewardDocument(RewardDocument transientInstance) {
        // TODO Auto-generated method stub
        return researchRepository.updateRewardDocument(transientInstance);
    }

    @Override
    public Integer deleteRewardDocument(RewardDocument persistentInstance) {
        // TODO Auto-generated method stub
        return researchRepository.deleteRewardDocument(persistentInstance);
    }

    @Override
    public RewardDocument findRewardDocumentById(RewardDocumentPK id) {
        // TODO Auto-generated method stub
        return researchRepository.findRewardDocumentById(id);
    }

    @Override
    public Integer updateFlagResearchProject(ResearchProject persistentInstance) {
        // TODO Auto-generated method stub
        return researchRepository.updateFlagResearchProject(persistentInstance);
    }

    @Override
    public Integer updateFlagUtilizationItem(Utilization persistentInstance) {
        return researchRepository.updateFlagUtilizationItem(persistentInstance);
    }

    @Override
    public Integer updateFlagJournalPaper(JournalPaper persistentInstance) {
        // TODO Auto-generated method stub
        return researchRepository.updateFlagJournalPaper(persistentInstance);
    }

    @Override
    public Integer updateFlagCopyright(Copyright persistentInstance) {
        // TODO Auto-generated method stub
        return researchRepository.updateFlagCopyright(persistentInstance);
    }

    @Override
    public Integer updateFlagReward(Reward persistentInstance) {
        // TODO Auto-generated method stub
        return researchRepository.updateFlagReward(persistentInstance);
    }

    @Override
    public Integer updateFlagPatent(Patent persistentInstance) {
        // TODO Auto-generated method stub
        return researchRepository.updateFlagPatent(persistentInstance);
    }

	@Override
	public List searchAcademicTitle(Title persistentInstance, Paging paging, String keySearch)
			throws DataAccessException {
		// TODO Auto-generated method stub
		  return researchRepository.searchAcademicTitle(persistentInstance, paging, keySearch);
	}


}
