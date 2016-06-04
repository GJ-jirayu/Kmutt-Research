package th.ac.kmutt.research.service.impl;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import org.springframework.stereotype.Service;

import th.ac.kmutt.research.constant.ServiceConstant;
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
import th.ac.kmutt.research.service.ResearchService;
import th.ac.kmutt.research.xstream.common.ImakeResultMessage;

@Service("researchServiceWSImpl")
public class ResearchServiceWSImpl extends PostCommon implements ResearchService {
    private static SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy", Locale.US);

    @Override
    public List<JournalPaperM> selectAll() {
        // TODO Auto-generated method stub

        return null;
    }

    @Override
    public Integer saveResearchGroupM(ResearchGroupM researchGroupM) {
        // TODO Auto-generated method stub
        researchGroupM.setServiceName(ServiceConstant.RESEARCH_GROUP_SAVE);
        ImakeResultMessage imakeMessage = postMessage(researchGroupM, researchGroupM.getClass().getName(), "researchGroup", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((ResearchGroupM) imakeMessage.getResultListObj().get(0)).getUpdateRecord();
        else
            return null;
    }

    @Override
    public Integer updateResearchGroupM(ResearchGroupM researchGroupM) {
        // TODO Auto-generated method stub
        researchGroupM.setServiceName(ServiceConstant.RESEARCH_GROUP_UPDATE);
        ImakeResultMessage imakeMessage = postMessage(researchGroupM, researchGroupM.getClass().getName(), "researchGroup", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((ResearchGroupM) imakeMessage.getResultListObj().get(0)).getUpdateRecord();
        else
            return null;
    }

    @Override
    public Integer deleteResearchGroupM(ResearchGroupM researchGroupM) {
        // TODO Auto-generated method stub
        researchGroupM.setServiceName(ServiceConstant.RESEARCH_GROUP_DELETE);
        ImakeResultMessage imakeMessage = postMessage(researchGroupM, researchGroupM.getClass().getName(), "researchGroup", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((ResearchGroupM) imakeMessage.getResultListObj().get(0)).getUpdateRecord();
        else
            return null;
    }

    @Override
    public Integer deleteItemsResearchGroupM(String[] researchGroupId) {
        // TODO Auto-generated method stub
        ResearchGroupM researchGroupM = new ResearchGroupM();
        researchGroupM.setIds(researchGroupId);
        researchGroupM.setServiceName(ServiceConstant.RESEARCH_GROUP_ITEMS_DELETE);
        ImakeResultMessage imakeMessage = postMessage(researchGroupM, researchGroupM.getClass().getName(), "researchGroup", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((ResearchGroupM) imakeMessage.getResultListObj().get(0)).getUpdateRecord();
        else
            return null;
    }

    @Override
    public ResearchGroupM findResearchGroupById(Integer researchGroupId) {
        // TODO Auto-generated method stub
        ResearchGroupM researchGroupM = new ResearchGroupM();
        researchGroupM.setResearchGroupId(researchGroupId);
        researchGroupM.setServiceName(ServiceConstant.RESEARCH_GROUP_FIND_BY_ID);
        ImakeResultMessage imakeMessage = postMessage(researchGroupM, researchGroupM.getClass().getName(), "researchGroup", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return (ResearchGroupM) imakeMessage.getResultListObj().get(0);
        else
            return null;
    }

    public ImakeResultMessage searchResearchGroupM(
            ResearchGroupM researchGroupM) {
        // TODO Auto-generated method stub
        researchGroupM.setServiceName(ServiceConstant.RESEARCH_GROUP_SEARCH);
        return postMessage(researchGroupM, researchGroupM.getClass().getName(), "researchGroup", true);
    }

    @Override
    public List<ResearchGroupM> listResearchGroupM() {
        // TODO Auto-generated method stub
        return null;
    }

    public static void main(String[] args) {
        ResearchServiceWSImpl impl = new ResearchServiceWSImpl();
        ResearchGroupM researchGroupM = new ResearchGroupM();
        researchGroupM.setGroupCode("002");
        researchGroupM.setResearchGroupId(1);
    }

    @Override
    public Integer saveUtilizationTypeM(UtilizationTypeM utilizationTypeM) {
        // TODO Auto-generated method stub
        utilizationTypeM.setServiceName(ServiceConstant.UTILIZATION_TYPE_SAVE);
        ImakeResultMessage imakeMessage = postMessage(utilizationTypeM, utilizationTypeM.getClass().getName(), "utilizationType", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((UtilizationTypeM) imakeMessage.getResultListObj().get(0)).getUpdateRecord();
        else
            return null;
    }

    @Override
    public Integer updateUtilizationTypeM(UtilizationTypeM utilizationTypeM) {
        // TODO Auto-generated method stub
        utilizationTypeM.setServiceName(ServiceConstant.UTILIZATION_TYPE_UPDATE);
        ImakeResultMessage imakeMessage = postMessage(utilizationTypeM, utilizationTypeM.getClass().getName(), "utilizationType", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((UtilizationTypeM) imakeMessage.getResultListObj().get(0)).getUpdateRecord();
        else
            return null;
    }

    @Override
    public Integer deleteUtilizationTypeM(UtilizationTypeM utilizationTypeM) {
        // TODO Auto-generated method stub
        utilizationTypeM.setServiceName(ServiceConstant.UTILIZATION_TYPE_DELETE);
        ImakeResultMessage imakeMessage = postMessage(utilizationTypeM, utilizationTypeM.getClass().getName(), "utilizationType", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((UtilizationTypeM) imakeMessage.getResultListObj().get(0)).getUpdateRecord();
        else
            return null;
    }

    @Override
    public UtilizationTypeM findUtilizationTypeById(Integer utilizationTypeId) {
        // TODO Auto-generated method stub
        UtilizationTypeM utilizationTypeM = new UtilizationTypeM();
        utilizationTypeM.setUtilizationTypeId(utilizationTypeId);
        utilizationTypeM.setServiceName(ServiceConstant.UTILIZATION_TYPE_FIND_BY_ID);
        ImakeResultMessage imakeMessage = postMessage(utilizationTypeM, utilizationTypeM.getClass().getName(), "utilizationType", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return (UtilizationTypeM) imakeMessage.getResultListObj().get(0);
        else
            return null;
    }

    @Override
    public ImakeResultMessage searchUtilizationTypeM(UtilizationTypeM utilizationTypeM
    ) {
        // TODO Auto-generated method stub
        utilizationTypeM.setServiceName(ServiceConstant.UTILIZATION_TYPE_SEARCH);
        return postMessage(utilizationTypeM, utilizationTypeM.getClass().getName(), "utilizationType", true);
        //return imakeMessage.getResultListObj();
    }

    @Override
    public Integer savePublishTypeM(PublishTypeM publishTypeM) {
        // TODO Auto-generated method stub
        publishTypeM.setServiceName(ServiceConstant.PUBLISH_TYPE_SAVE);
        ImakeResultMessage imakeMessage = postMessage(publishTypeM, publishTypeM.getClass().getName(), "publishType", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((PublishTypeM) imakeMessage.getResultListObj().get(0)).getUpdateRecord();
        else
            return null;
    }

    @Override
    public Integer updatePublishTypeM(PublishTypeM publishTypeM) {
        // TODO Auto-generated method stub
        publishTypeM.setServiceName(ServiceConstant.PUBLISH_TYPE_UPDATE);
        ImakeResultMessage imakeMessage = postMessage(publishTypeM, publishTypeM.getClass().getName(), "publishType", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((PublishTypeM) imakeMessage.getResultListObj().get(0)).getUpdateRecord();
        else
            return null;
    }

    @Override
    public Integer deletePublishTypeM(PublishTypeM publishTypeM) {
        // TODO Auto-generated method stub
        publishTypeM.setServiceName(ServiceConstant.PUBLISH_TYPE_DELETE);
        ImakeResultMessage imakeMessage = postMessage(publishTypeM, publishTypeM.getClass().getName(), "publishType", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((PublishTypeM) imakeMessage.getResultListObj().get(0)).getUpdateRecord();
        else
            return null;
    }

    @Override
    public PublishTypeM findPublishTypeById(Integer publishTypeId) {
        // TODO Auto-generated method stub
        PublishTypeM publishTypeM = new PublishTypeM();
        publishTypeM.setPublishTypeId(publishTypeId);
        publishTypeM.setServiceName(ServiceConstant.PUBLISH_TYPE_FIND_BY_ID);
        ImakeResultMessage imakeMessage = postMessage(publishTypeM, publishTypeM.getClass().getName(), "publishType", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return (PublishTypeM) imakeMessage.getResultListObj().get(0);
        else
            return null;
    }

    @Override
    public ImakeResultMessage searchPublishTypeM(PublishTypeM publishTypeM
    ) {
        // TODO Auto-generated method stub
        publishTypeM.setServiceName(ServiceConstant.PUBLISH_TYPE_SEARCH);
        return postMessage(publishTypeM, publishTypeM.getClass().getName(), "publishType", true);
        //return imakeMessage.getResultListObj();
    }

    @Override
    public Integer savePositionM(PositionM positionM) {
        // TODO Auto-generated method stub
        positionM.setServiceName(ServiceConstant.POSITION_SAVE);
        ImakeResultMessage imakeMessage = postMessage(positionM, positionM.getClass().getName(), "position", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((PositionM) imakeMessage.getResultListObj().get(0)).getUpdateRecord();
        else
            return null;
    }

    @Override
    public Integer updatePositionM(PositionM positionM) {
        // TODO Auto-generated method stub
        positionM.setServiceName(ServiceConstant.POSITION_UPDATE);
        ImakeResultMessage imakeMessage = postMessage(positionM, positionM.getClass().getName(), "position", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((PositionM) imakeMessage.getResultListObj().get(0)).getUpdateRecord();
        else
            return null;
    }

    @Override
    public Integer deletePositionM(PositionM positionM) {
        // TODO Auto-generated method stub
        positionM.setServiceName(ServiceConstant.POSITION_DELETE);
        ImakeResultMessage imakeMessage = postMessage(positionM, positionM.getClass().getName(), "position", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((PositionM) imakeMessage.getResultListObj().get(0)).getUpdateRecord();
        else
            return null;
    }

    @Override
    public PositionM findPositionById(Integer positionId) {
        // TODO Auto-generated method stub
        PositionM positionM = new PositionM();
        positionM.setPositionId(positionId);
        positionM.setServiceName(ServiceConstant.POSITION_FIND_BY_ID);
        ImakeResultMessage imakeMessage = postMessage(positionM, positionM.getClass().getName(), "position", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return (PositionM) imakeMessage.getResultListObj().get(0);
        else
            return null;
    }

    @Override
    public ImakeResultMessage searchPositionM(PositionM positionM) {
        // TODO Auto-generated method stub
        positionM.setServiceName(ServiceConstant.POSITION_SEARCH);
        return postMessage(positionM, positionM.getClass().getName(), "position", true);
        //return imakeMessage.getResultListObj();
    }

    @Override
    public Integer saveOrganizationM(OrganizationM organizationM) {
        // TODO Auto-generated method stub
        organizationM.setServiceName(ServiceConstant.ORGANIZATION_SAVE);
        ImakeResultMessage imakeMessage = postMessage(organizationM, organizationM.getClass().getName(), "organization", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((OrganizationM) imakeMessage.getResultListObj().get(0)).getUpdateRecord();
        else
            return null;
    }

    @Override
    public Integer updateOrganizationM(OrganizationM organizationM) {
        // TODO Auto-generated method stub
        organizationM.setServiceName(ServiceConstant.ORGANIZATION_UPDATE);
        ImakeResultMessage imakeMessage = postMessage(organizationM, organizationM.getClass().getName(), "organization", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((OrganizationM) imakeMessage.getResultListObj().get(0)).getUpdateRecord();
        else
            return null;
    }

    @Override
    public Integer deleteOrganizationM(OrganizationM organizationM) {
        // TODO Auto-generated method stub
        organizationM.setServiceName(ServiceConstant.ORGANIZATION_DELETE);
        ImakeResultMessage imakeMessage = postMessage(organizationM, organizationM.getClass().getName(), "organization", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((OrganizationM) imakeMessage.getResultListObj().get(0)).getUpdateRecord();
        else
            return null;
    }

    @Override
    public OrganizationM findOrganizationById(Integer organizationId) {
        // TODO Auto-generated method stub
        OrganizationM organizationM = new OrganizationM();
        organizationM.setOrganizationId(organizationId);
        organizationM.setServiceName(ServiceConstant.ORGANIZATION_FIND_BY_ID);
        ImakeResultMessage imakeMessage = postMessage(organizationM, organizationM.getClass().getName(), "organization", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return (OrganizationM) imakeMessage.getResultListObj().get(0);
        else
            return null;
    }

    @Override
    public ImakeResultMessage searchOrganizationM(OrganizationM organizationM
    ) {
        // TODO Auto-generated method stub
        organizationM.setServiceName(ServiceConstant.ORGANIZATION_SEARCH);
        return postMessage(organizationM, organizationM.getClass().getName(), "organization", true);
        //return imakeMessage.getResultListObj();
    }

    @Override
    public Integer saveCountryM(CountryM countryM) {
        // TODO Auto-generated method stub
        countryM.setServiceName(ServiceConstant.COUNTRY_SAVE);
        ImakeResultMessage imakeMessage = postMessage(countryM, countryM.getClass().getName(), "country", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((CountryM) imakeMessage.getResultListObj().get(0)).getUpdateRecord();
        else
            return null;
    }

    @Override
    public Integer updateCountryM(CountryM countryM) {
        // TODO Auto-generated method stub
        countryM.setServiceName(ServiceConstant.COUNTRY_UPDATE);
        ImakeResultMessage imakeMessage = postMessage(countryM, countryM.getClass().getName(), "country", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((CountryM) imakeMessage.getResultListObj().get(0)).getUpdateRecord();
        else
            return null;
    }

    @Override
    public Integer deleteCountryM(CountryM countryM) {
        // TODO Auto-generated method stub
        countryM.setServiceName(ServiceConstant.COUNTRY_DELETE);
        ImakeResultMessage imakeMessage = postMessage(countryM, countryM.getClass().getName(), "country", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((CountryM) imakeMessage.getResultListObj().get(0)).getUpdateRecord();
        else
            return null;
    }

    @Override
    public CountryM findCountryById(Integer countryId) {
        // TODO Auto-generated method stub
        CountryM country = new CountryM();
        country.setCountryId(countryId);
        country.setServiceName(ServiceConstant.COUNTRY_FIND_BY_ID);
        ImakeResultMessage imakeMessage = postMessage(country, country.getClass().getName(), "country", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return (CountryM) imakeMessage.getResultListObj().get(0);
        else
            return null;
    }

    @Override
    public ImakeResultMessage searchCountryM(CountryM countryM) {
        // TODO Auto-generated method stub
        countryM.setServiceName(ServiceConstant.COUNTRY_SEARCH);
        return postMessage(countryM, countryM.getClass().getName(), "country", true);
        //return imakeMessage.getResultListObj();
    }

    @Override
    public Integer saveWorkTypeM(WorkTypeM workTypeM) {
        // TODO Auto-generated method stub
        workTypeM.setServiceName(ServiceConstant.WORK_TYPE_SAVE);
        ImakeResultMessage imakeMessage = postMessage(workTypeM, workTypeM.getClass().getName(), "workType", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((WorkTypeM) imakeMessage.getResultListObj().get(0)).getUpdateRecord();
        else
            return null;
    }

    @Override
    public Integer updateWorkTypeM(WorkTypeM workTypeM) {
        // TODO Auto-generated method stub
        workTypeM.setServiceName(ServiceConstant.WORK_TYPE_UPDATE);
        ImakeResultMessage imakeMessage = postMessage(workTypeM, workTypeM.getClass().getName(), "workType", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((WorkTypeM) imakeMessage.getResultListObj().get(0)).getUpdateRecord();
        else
            return null;
    }

    @Override
    public Integer deleteWorkTypeM(WorkTypeM workTypeM) {
        // TODO Auto-generated method stub
        workTypeM.setServiceName(ServiceConstant.WORK_TYPE_DELETE);
        ImakeResultMessage imakeMessage = postMessage(workTypeM, workTypeM.getClass().getName(), "workType", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((WorkTypeM) imakeMessage.getResultListObj().get(0)).getUpdateRecord();
        else
            return null;
    }

    @Override
    public WorkTypeM findWorkTypeById(Integer workTypeId) {
        // TODO Auto-generated method stub
        WorkTypeM workTypeM = new WorkTypeM();
        workTypeM.setWorkTypeId(workTypeId);
        workTypeM.setServiceName(ServiceConstant.WORK_TYPE_FIND_BY_ID);
        ImakeResultMessage imakeMessage = postMessage(workTypeM, workTypeM.getClass().getName(), "workType", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return (WorkTypeM) imakeMessage.getResultListObj().get(0);
        else
            return null;
    }

    @Override
    public ImakeResultMessage searchWorkTypeM(WorkTypeM workTypeM) {
        // TODO Auto-generated method stub
        workTypeM.setServiceName(ServiceConstant.WORK_TYPE_SEARCH);
        return postMessage(workTypeM, workTypeM.getClass().getName(), "workType", true);
        //return imakeMessage.getResultListObj();
    }

    @Override
    public Integer saveJournalPapersTypeM(JournalPapersTypeM journalPapersTypeM) {
        // TODO Auto-generated method stub
        journalPapersTypeM.setServiceName(ServiceConstant.JOURNAL_PAPERS_TYPE_SAVE);
        ImakeResultMessage imakeMessage = postMessage(journalPapersTypeM, journalPapersTypeM.getClass().getName(), "journalPapersType", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((JournalPapersTypeM) imakeMessage.getResultListObj().get(0)).getUpdateRecord();
        else
            return null;
    }

    @Override
    public Integer updateJournalPapersTypeM(JournalPapersTypeM journalPapersTypeM) {
        // TODO Auto-generated method stub
        journalPapersTypeM.setServiceName(ServiceConstant.JOURNAL_PAPERS_TYPE_UPDATE);
        ImakeResultMessage imakeMessage = postMessage(journalPapersTypeM, journalPapersTypeM.getClass().getName(), "journalPapersType", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((JournalPapersTypeM) imakeMessage.getResultListObj().get(0)).getUpdateRecord();
        else
            return null;
    }

    @Override
    public Integer deleteJournalPapersTypeM(
            JournalPapersTypeM journalPapersTypeM) {
        // TODO Auto-generated method stub
        journalPapersTypeM.setServiceName(ServiceConstant.JOURNAL_PAPERS_TYPE_DELETE);
        ImakeResultMessage imakeMessage = postMessage(journalPapersTypeM, journalPapersTypeM.getClass().getName(), "journalPapersType", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((JournalPapersTypeM) imakeMessage.getResultListObj().get(0)).getUpdateRecord();
        else
            return null;
    }

    @Override
    public JournalPapersTypeM findJournalPapersTypeById(Integer journalPapersTypeId) {
        // TODO Auto-generated method stub
        JournalPapersTypeM journalPapersTypeM = new JournalPapersTypeM();
        journalPapersTypeM.setJournalPapersTypeId(journalPapersTypeId);
        journalPapersTypeM.setServiceName(ServiceConstant.JOURNAL_PAPERS_TYPE_FIND_BY_ID);
        ImakeResultMessage imakeMessage = postMessage(journalPapersTypeM, journalPapersTypeM.getClass().getName(), "journalPapersType", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return (JournalPapersTypeM) imakeMessage.getResultListObj().get(0);
        else
            return null;
    }

    @Override
    public ImakeResultMessage searchJournalPapersTypeM(JournalPapersTypeM journalPapersTypeM
    ) {
        // TODO Auto-generated method stub
        journalPapersTypeM.setServiceName(ServiceConstant.JOURNAL_PAPERS_TYPE_SEARCH);
        return postMessage(journalPapersTypeM, journalPapersTypeM.getClass().getName(), "journalPapersType", true);
        //return imakeMessage.getResultListObj();
    }

    @Override
    public Integer saveFundingResourceTypeM(
            FundingResourceTypeM fundingResourcesTypeM) {
        // TODO Auto-generated method stub
        fundingResourcesTypeM.setServiceName(ServiceConstant.FUNDING_RESOURCES_TYPE_SAVE);
        ImakeResultMessage imakeMessage = postMessage(fundingResourcesTypeM, fundingResourcesTypeM.getClass().getName(), "fundingResourcesType", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((FundingResourceTypeM) imakeMessage.getResultListObj().get(0)).getUpdateRecord();
        else
            return null;
    }

    @Override
    public Integer updateFundingResourceTypeM(
            FundingResourceTypeM fundingResourcesTypeM) {
        // TODO Auto-generated method stub
        fundingResourcesTypeM.setServiceName(ServiceConstant.FUNDING_RESOURCES_TYPE_UPDATE);
        ImakeResultMessage imakeMessage = postMessage(fundingResourcesTypeM, fundingResourcesTypeM.getClass().getName(), "fundingResourcesType", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((FundingResourceTypeM) imakeMessage.getResultListObj().get(0)).getUpdateRecord();
        else
            return null;
    }

    @Override
    public Integer deleteFundingResourceTypeM(
            FundingResourceTypeM fundingResourcesTypeM) {
        // TODO Auto-generated method stub
        fundingResourcesTypeM.setServiceName(ServiceConstant.FUNDING_RESOURCES_TYPE_DELETE);
        ImakeResultMessage imakeMessage = postMessage(fundingResourcesTypeM, fundingResourcesTypeM.getClass().getName(), "fundingResourcesType", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((FundingResourceTypeM) imakeMessage.getResultListObj().get(0)).getUpdateRecord();
        else
            return null;
    }

    @Override
    public FundingResourceTypeM findFundingResourceTypeById(
            Integer fundingResourcesTypeId) {
        // TODO Auto-generated method stub
        FundingResourceTypeM fundingResourcesTypeM = new FundingResourceTypeM();
        fundingResourcesTypeM.setFundingResourceTypeId(fundingResourcesTypeId);
        fundingResourcesTypeM.setServiceName(ServiceConstant.FUNDING_RESOURCES_TYPE_FIND_BY_ID);
        ImakeResultMessage imakeMessage = postMessage(fundingResourcesTypeM, fundingResourcesTypeM.getClass().getName(), "fundingResourcesType", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return (FundingResourceTypeM) imakeMessage.getResultListObj().get(0);
        else
            return null;
    }

    @Override
    public ImakeResultMessage searchFundingResourceTypeM(
            FundingResourceTypeM fundingResourcesTypeM) {
        // TODO Auto-generated method stub
        fundingResourcesTypeM.setServiceName(ServiceConstant.FUNDING_RESOURCES_TYPE_SEARCH);
        return postMessage(fundingResourcesTypeM, fundingResourcesTypeM.getClass().getName(), "fundingResourcesType", true);
        //return imakeMessage.getResultListObj();
    }

    @Override
    public Integer saveResearcherM(ResearcherM researcherM) {
        // TODO Auto-generated method stub
        researcherM.setServiceName(ServiceConstant.RESEARCHER_SAVE);
        ImakeResultMessage imakeMessage = postMessage(researcherM, researcherM.getClass().getName(), "researcher", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((ResearcherM) imakeMessage.getResultListObj().get(0)).getUpdateRecord();
        else
            return null;
    }

    @Override
    public Integer updateResearcherM(ResearcherM researcherM) {
        // TODO Auto-generated method stub
        researcherM.setServiceName(ServiceConstant.RESEARCHER_UPDATE);
        ImakeResultMessage imakeMessage = postMessage(researcherM, researcherM.getClass().getName(), "researcher", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((ResearcherM) imakeMessage.getResultListObj().get(0)).getUpdateRecord();
        else
            return null;
    }

    @Override
    public Integer deleteResearcherM(ResearcherM researcherM) {
        // TODO Auto-generated method stub
        researcherM.setServiceName(ServiceConstant.RESEARCHER_DELETE);
        ImakeResultMessage imakeMessage = postMessage(researcherM, researcherM.getClass().getName(), "researcher", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((ResearcherM) imakeMessage.getResultListObj().get(0)).getUpdateRecord();
        else
            return null;
    }

    @Override
    public ResearcherM findResearcherById(Integer researcherId) {
        // TODO Auto-generated method stub
        ResearcherM researcherM = new ResearcherM();
        researcherM.setResearcherId(researcherId);
        researcherM.setServiceName(ServiceConstant.RESEARCHER_FIND_BY_ID);
        ImakeResultMessage imakeMessage = postMessage(researcherM, researcherM.getClass().getName(), "researcher", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return (ResearcherM) imakeMessage.getResultListObj().get(0);
        else
            return null;
    }

    @Override
    public ImakeResultMessage searchResearcherM(ResearcherM researcherM
    ) {
        // TODO Auto-generated method stub
        researcherM.setServiceName(ServiceConstant.RESEARCHER_SEARCH);
        return postMessage(researcherM, researcherM.getClass().getName(), "researcher", true);
        //return imakeMessage.getResultListObj();
    }

    @Override
    public Integer saveResearcherGroupM(
            ResearcherGroupM researcherGroupM) {
        // TODO Auto-generated method stub
        researcherGroupM.setServiceName(ServiceConstant.RESEARCHER_GROUP_SAVE);
        ImakeResultMessage imakeMessage = postMessage(researcherGroupM, researcherGroupM.getClass().getName(), "researcherGroup", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((ResearcherGroupM) imakeMessage.getResultListObj().get(0)).getUpdateRecord();
        else
            return null;
    }

    @Override
    public Integer updateResearcherGroupM(
            ResearcherGroupM researcherGroupM) {
        // TODO Auto-generated method stub
        researcherGroupM.setServiceName(ServiceConstant.RESEARCHER_GROUP_UPDATE);
        ImakeResultMessage imakeMessage = postMessage(researcherGroupM, researcherGroupM.getClass().getName(), "researcherGroup", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((ResearcherGroupM) imakeMessage.getResultListObj().get(0)).getUpdateRecord();
        else
            return null;
    }

    @Override
    public Integer deleteResearcherGroupM(
            ResearcherGroupM researcherGroupM) {
        // TODO Auto-generated method stub
        researcherGroupM.setServiceName(ServiceConstant.RESEARCHER_GROUP_DELETE);
        ImakeResultMessage imakeMessage = postMessage(researcherGroupM, researcherGroupM.getClass().getName(), "researcherGroup", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((ResearcherGroupM) imakeMessage.getResultListObj().get(0)).getUpdateRecord();
        else
            return null;
    }

    @Override
    public ResearcherGroupM findResearcherGroupById(
            Integer researcherGroupId, Integer researcherId) {
        // TODO Auto-generated method stub
        ResearcherGroupM researcherGroupM = new ResearcherGroupM();
        researcherGroupM.setResearchGroupId(researcherGroupId);
        researcherGroupM.setResearcherId(researcherId);
        researcherGroupM.setServiceName(ServiceConstant.RESEARCHER_GROUP_FIND_BY_ID);
        ImakeResultMessage imakeMessage = postMessage(researcherGroupM, researcherGroupM.getClass().getName(), "researcherGroup", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return (ResearcherGroupM) imakeMessage.getResultListObj().get(0);
        else
            return null;
    }

    @Override
    public ImakeResultMessage searchResearcherGroupM(
            ResearcherGroupM researcherGroupM) {
        // TODO Auto-generated method stub
        researcherGroupM.setServiceName(ServiceConstant.RESEARCHER_GROUP_SEARCH);
        return postMessage(researcherGroupM, researcherGroupM.getClass().getName(), "researcherGroup", true);
        //return imakeMessage.getResultListObj();
    }

    @Override
    public Integer savePublishLevelM(PublishLevelM publishLevelM) {
        // TODO Auto-generated method stub
        publishLevelM.setServiceName(ServiceConstant.PUBLISH_LEVEL_SAVE);
        ImakeResultMessage imakeMessage = postMessage(publishLevelM, publishLevelM.getClass().getName(), "publishLevel", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((PublishLevelM) imakeMessage.getResultListObj().get(0)).getUpdateRecord();
        else
            return null;
    }

    @Override
    public Integer updatePublishLevelM(PublishLevelM publishLevelM) {
        // TODO Auto-generated method stub
        publishLevelM.setServiceName(ServiceConstant.PUBLISH_LEVEL_UPDATE);
        ImakeResultMessage imakeMessage = postMessage(publishLevelM, publishLevelM.getClass().getName(), "publishLevel", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((PublishLevelM) imakeMessage.getResultListObj().get(0)).getUpdateRecord();
        else
            return null;
    }

    @Override
    public Integer deletePublishLevelM(PublishLevelM publishLevelM) {
        // TODO Auto-generated method stub
        publishLevelM.setServiceName(ServiceConstant.PUBLISH_LEVEL_DELETE);
        ImakeResultMessage imakeMessage = postMessage(publishLevelM, publishLevelM.getClass().getName(), "publishLevel", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((PublishLevelM) imakeMessage.getResultListObj().get(0)).getUpdateRecord();
        else
            return null;
    }

    @Override
    public PublishLevelM findPublishLevelById(Integer publishLevelId) {
        // TODO Auto-generated method stub
        PublishLevelM publishLevelM = new PublishLevelM();
        publishLevelM.setPublishLevelId(publishLevelId);
        publishLevelM.setServiceName(ServiceConstant.PUBLISH_LEVEL_FIND_BY_ID);
        ImakeResultMessage imakeMessage = postMessage(publishLevelM, publishLevelM.getClass().getName(), "publishLevel", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return (PublishLevelM) imakeMessage.getResultListObj().get(0);
        else
            return null;
    }

    @Override
    public ImakeResultMessage searchPublishLevelM(PublishLevelM publishLevelM
    ) {
        // TODO Auto-generated method stub
        publishLevelM.setServiceName(ServiceConstant.PUBLISH_LEVEL_SEARCH);
        return postMessage(publishLevelM, publishLevelM.getClass().getName(), "publishLevel", true);
        //return imakeMessage.getResultListObj();
    }

    @Override
    public Integer saveOrganizationExtM(OrganizationExtM organizationExtM) {
        // TODO Auto-generated method stub
        organizationExtM.setServiceName(ServiceConstant.ORGANIZATION_EXT_SAVE);
        ImakeResultMessage imakeMessage = postMessage(organizationExtM, organizationExtM.getClass().getName(), "organizationExt", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((OrganizationExtM) imakeMessage.getResultListObj().get(0)).getUpdateRecord();
        else
            return null;
    }

    @Override
    public Integer updateOrganizationExtM(OrganizationExtM organizationExtM) {
        // TODO Auto-generated method stub
        organizationExtM.setServiceName(ServiceConstant.ORGANIZATION_EXT_UPDATE);
        ImakeResultMessage imakeMessage = postMessage(organizationExtM, organizationExtM.getClass().getName(), "organizationExt", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((OrganizationExtM) imakeMessage.getResultListObj().get(0)).getUpdateRecord();
        else
            return null;
    }

    @Override
    public Integer deleteOrganizationExtM(OrganizationExtM organizationExtM) {
        // TODO Auto-generated method stub
        organizationExtM.setServiceName(ServiceConstant.ORGANIZATION_EXT_DELETE);
        ImakeResultMessage imakeMessage = postMessage(organizationExtM, organizationExtM.getClass().getName(), "organizationExt", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((OrganizationExtM) imakeMessage.getResultListObj().get(0)).getUpdateRecord();
        else
            return null;
    }

    @Override
    public OrganizationExtM findOrganizationExtById(Integer organizationExtId) {
        // TODO Auto-generated method stub
        OrganizationExtM organizationExtM = new OrganizationExtM();
        organizationExtM.setOrganizationExtId(organizationExtId);
        organizationExtM.setServiceName(ServiceConstant.ORGANIZATION_EXT_FIND_BY_ID);
        ImakeResultMessage imakeMessage = postMessage(organizationExtM, organizationExtM.getClass().getName(), "organizationExt", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return (OrganizationExtM) imakeMessage.getResultListObj().get(0);
        else
            return null;
    }

    @Override
    public ImakeResultMessage searchOrganizationExtM(OrganizationExtM organizationExtM
    ) {
        // TODO Auto-generated method stub
        organizationExtM.setServiceName(ServiceConstant.ORGANIZATION_EXT_SEARCH);
        return postMessage(organizationExtM, organizationExtM.getClass().getName(), "organizationExt", true);
        //return imakeMessage.getResultListObj();
    }

    @Override
    public Integer saveFundingResourceM(FundingResourceM fundingResourcesM) {
        // TODO Auto-generated method stub
        fundingResourcesM.setServiceName(ServiceConstant.FUNDING_RESOURCES_SAVE);
        ImakeResultMessage imakeMessage = postMessage(fundingResourcesM, fundingResourcesM.getClass().getName(), "fundingResources", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((FundingResourceM) imakeMessage.getResultListObj().get(0)).getUpdateRecord();
        else
            return null;
    }

    @Override
    public Integer updateFundingResourceM(FundingResourceM fundingResourcesM) {
        // TODO Auto-generated method stub
        fundingResourcesM.setServiceName(ServiceConstant.FUNDING_RESOURCES_UPDATE);
        ImakeResultMessage imakeMessage = postMessage(fundingResourcesM, fundingResourcesM.getClass().getName(), "fundingResources", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((FundingResourceM) imakeMessage.getResultListObj().get(0)).getUpdateRecord();
        else
            return null;
    }

    @Override
    public Integer deleteFundingResourceM(FundingResourceM fundingResourcesM) {
        // TODO Auto-generated method stub
        fundingResourcesM.setServiceName(ServiceConstant.FUNDING_RESOURCES_DELETE);
        ImakeResultMessage imakeMessage = postMessage(fundingResourcesM, fundingResourcesM.getClass().getName(), "fundingResources", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((FundingResourceM) imakeMessage.getResultListObj().get(0)).getUpdateRecord();
        else
            return null;
    }

    @Override
    public FundingResourceM findFundingResourceById(Integer fundingResourcesId) {
        // TODO Auto-generated method stub
        FundingResourceM fundingResourcesM = new FundingResourceM();
        fundingResourcesM.setFundingResourceId(fundingResourcesId);
        fundingResourcesM.setServiceName(ServiceConstant.FUNDING_RESOURCES_FIND_BY_ID);
        ImakeResultMessage imakeMessage = postMessage(fundingResourcesM, fundingResourcesM.getClass().getName(), "fundingResources", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return (FundingResourceM) imakeMessage.getResultListObj().get(0);
        else
            return null;
    }

    @Override
    public ImakeResultMessage searchFundingResourceM(FundingResourceM fundingResourcesM
    ) {
        // TODO Auto-generated method stub
        fundingResourcesM.setServiceName(ServiceConstant.FUNDING_RESOURCES_SEARCH);
        return postMessage(fundingResourcesM, fundingResourcesM.getClass().getName(), "fundingResources", true);
        //return imakeMessage.getResultListObj();
    }

    @Override
    public Integer saveResearchProjectM(ResearchProjectM researchProject) {
        // TODO Auto-generated method stub
        researchProject.setServiceName(ServiceConstant.RESEARCH_PROJECT_SAVE);
        ImakeResultMessage imakeMessage = postMessage(researchProject, researchProject.getClass().getName(), "researchProject", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((ResearchProjectM) imakeMessage.getResultListObj().get(0)).getUpdateRecord();
        else
            return null;
    }

    @Override
    public Integer updateResearchProjectM(ResearchProjectM researchProject) {
        // TODO Auto-generated method stub
        researchProject.setServiceName(ServiceConstant.RESEARCH_PROJECT_UPDATE);
        ImakeResultMessage imakeMessage = postMessage(researchProject, researchProject.getClass().getName(), "researchProject", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((ResearchProjectM) imakeMessage.getResultListObj().get(0)).getUpdateRecord();
        else
            return null;
    }

    @Override
    public Integer deleteResearchProjectM(ResearchProjectM researchProject) {
        // TODO Auto-generated method stub
        researchProject.setServiceName(ServiceConstant.RESEARCH_PROJECT_DELETE);
        ImakeResultMessage imakeMessage = postMessage(researchProject, researchProject.getClass().getName(), "researchProject", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((ResearchProjectM) imakeMessage.getResultListObj().get(0)).getUpdateRecord();
        else
            return null;
    }

    @Override
    public ResearchProjectM findResearchProjectById(Integer researchProjectId, String userid) {
        // TODO Auto-generated method stub
        ResearchProjectM researchProjectM = new ResearchProjectM();
        researchProjectM.setResearchProjectId(researchProjectId);
        researchProjectM.setUserid(userid);
        researchProjectM.setServiceName(ServiceConstant.RESEARCH_PROJECT_FIND_BY_ID);
        ImakeResultMessage imakeMessage = postMessage(researchProjectM, researchProjectM.getClass().getName(), "researchProject", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return (ResearchProjectM) imakeMessage.getResultListObj().get(0);
        else
            return null;
    }

    @Override
    public ImakeResultMessage searchResearchProjectM(ResearchProjectM researchProject) {
        // TODO Auto-generated method stub
        researchProject.setServiceName(ServiceConstant.RESEARCH_PROJECT_SEARCH);
        return postMessage(researchProject, researchProject.getClass().getName(), "researchProject", true);
        //return imakeMessage.getResultListObj();
    }

    @Override
    public Integer saveJournalPaperM(JournalPaperM journalPapers) {
        // TODO Auto-generated method stub
        journalPapers.setServiceName(ServiceConstant.JOURNAL_PAPERS_SAVE);
        ImakeResultMessage imakeMessage = postMessage(journalPapers, journalPapers.getClass().getName(), "journalPapers", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((JournalPaperM) imakeMessage.getResultListObj().get(0)).getUpdateRecord();
        else
            return null;
    }

    @Override
    public Integer updateJournalPaperM(JournalPaperM journalPapers) {
        // TODO Auto-generated method stub
        journalPapers.setServiceName(ServiceConstant.JOURNAL_PAPERS_UPDATE);
        ImakeResultMessage imakeMessage = postMessage(journalPapers, journalPapers.getClass().getName(), "journalPapers", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((JournalPaperM) imakeMessage.getResultListObj().get(0)).getUpdateRecord();
        else
            return null;
    }

    @Override
    public Integer deleteJournalPaperM(JournalPaperM journalPapers) {
        // TODO Auto-generated method stub
        journalPapers.setServiceName(ServiceConstant.JOURNAL_PAPERS_DELETE);
        ImakeResultMessage imakeMessage = postMessage(journalPapers, journalPapers.getClass().getName(), "journalPapers", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((JournalPaperM) imakeMessage.getResultListObj().get(0)).getUpdateRecord();
        else
            return null;
    }

    @Override
    public JournalPaperM findJournalPapersById(Integer journalPapersId, String userid) {
        // TODO Auto-generated method stub
        JournalPaperM journalPaperM = new JournalPaperM();
        journalPaperM.setJournalPapersId(journalPapersId);
        journalPaperM.setUserid(userid);
        journalPaperM.setServiceName(ServiceConstant.JOURNAL_PAPERS_FIND_BY_ID);
        ImakeResultMessage imakeMessage = postMessage(journalPaperM, journalPaperM.getClass().getName(), "journalPapers", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return (JournalPaperM) imakeMessage.getResultListObj().get(0);
        else
            return null;
    }

    @Override
    public ImakeResultMessage searchJournalPaperM(JournalPaperM journalPapers) {
        // TODO Auto-generated method stub
        journalPapers.setServiceName(ServiceConstant.JOURNAL_PAPERS_SEARCH);
        return postMessage(journalPapers, journalPapers.getClass().getName(), "journalPapers", true);
        //return imakeMessage.getResultListObj();
    }

    @Override
    public Integer saveUtilizationM(UtilizationM utilization) {
        // TODO Auto-generated method stub
        utilization.setServiceName(ServiceConstant.UTILIZATION_SAVE);
        ImakeResultMessage imakeMessage = postMessage(utilization, utilization.getClass().getName(), "utilization", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((UtilizationM) imakeMessage.getResultListObj().get(0)).getUpdateRecord();
        else
            return null;
    }

    @Override
    public Integer updateUtilizationM(UtilizationM utilization) {
        // TODO Auto-generated method stub
        utilization.setServiceName(ServiceConstant.UTILIZATION_UPDATE);
        ImakeResultMessage imakeMessage = postMessage(utilization, utilization.getClass().getName(), "utilization", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((UtilizationM) imakeMessage.getResultListObj().get(0)).getUpdateRecord();
        else
            return null;
    }

    @Override
    public Integer deleteUtilizationM(UtilizationM utilization) {
        // TODO Auto-generated method stub
        utilization.setServiceName(ServiceConstant.UTILIZATION_DELETE);
        ImakeResultMessage imakeMessage = postMessage(utilization, utilization.getClass().getName(), "utilization", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((UtilizationM) imakeMessage.getResultListObj().get(0)).getUpdateRecord();
        else
            return null;
    }

    @Override
    public Integer deleteUtilizationItem(UtilizationM utilization) {
        // TODO Auto-generated method stub
        utilization.setServiceName(ServiceConstant.UTILIZATION_LIST_DELETE);
        ImakeResultMessage imakeMessage = postMessage(utilization, utilization.getClass().getName(), "utilization", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((UtilizationM) imakeMessage.getResultListObj().get(0)).getUpdateRecord();
        else
            return null;
    }

    @Override
    public UtilizationM findUtilizationById(Integer researchProjectId, Integer utilizationItemList) {
        // TODO Auto-generated method stub
        UtilizationM utilizationM = new UtilizationM();
        ResearchProjectM researchProjectM = new ResearchProjectM();
        researchProjectM.setResearchProjectId(researchProjectId);
        utilizationM.setResearchProject(researchProjectM);
        utilizationM.setUtilizationItemList(utilizationItemList);
        utilizationM.setServiceName(ServiceConstant.UTILIZATION_FIND_BY_ID);
        ImakeResultMessage imakeMessage = postMessage(utilizationM, utilizationM.getClass().getName(), "utilization", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return (UtilizationM) imakeMessage.getResultListObj().get(0);
        else
            return null;
    }

    @Override
    public ImakeResultMessage searchUtilizationM(UtilizationM utilization) {
        // TODO Auto-generated method stub
        utilization.setServiceName(ServiceConstant.UTILIZATION_SEARCH);
        return postMessage(utilization, utilization.getClass().getName(), "utilization", true);
        //return imakeMessage.getResultListObj();
    }

    @Override
    public Integer saveCopyrightM(CopyrightM researchCopyRight) {
        // TODO Auto-generated method stub
        researchCopyRight.setServiceName(ServiceConstant.COPYRIGHT_SAVE);
        ImakeResultMessage imakeMessage = postMessage(researchCopyRight, researchCopyRight.getClass().getName(), "copyright", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((CopyrightM) imakeMessage.getResultListObj().get(0)).getUpdateRecord();
        else
            return null;
    }

    @Override
    public Integer updateCopyrightM(CopyrightM researchCopyRight) {
        // TODO Auto-generated method stub
        researchCopyRight.setServiceName(ServiceConstant.COPYRIGHT_UPDATE);
        ImakeResultMessage imakeMessage = postMessage(researchCopyRight, researchCopyRight.getClass().getName(), "copyright", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((CopyrightM) imakeMessage.getResultListObj().get(0)).getUpdateRecord();
        else
            return null;
    }

    @Override
    public Integer deleteCopyrightM(
            CopyrightM researchCopyRight) {
        // TODO Auto-generated method stub
        researchCopyRight.setServiceName(ServiceConstant.COPYRIGHT_DELETE);
        ImakeResultMessage imakeMessage = postMessage(researchCopyRight, researchCopyRight.getClass().getName(), "copyright", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((CopyrightM) imakeMessage.getResultListObj().get(0)).getUpdateRecord();
        else
            return null;
    }

    @Override
    public CopyrightM findResearchCopyRightById(Integer copyrightId, String userid) {
        // TODO Auto-generated method stub
        CopyrightM researchCopyrightM = new CopyrightM();
        researchCopyrightM.setCopyrightId(copyrightId);
        researchCopyrightM.setUserid(userid);
        researchCopyrightM.setServiceName(ServiceConstant.COPYRIGHT_FIND_BY_ID);
        ImakeResultMessage imakeMessage = postMessage(researchCopyrightM, researchCopyrightM.getClass().getName(), "copyright", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return (CopyrightM) imakeMessage.getResultListObj().get(0);
        else
            return null;
    }

    @Override
    public ImakeResultMessage searchCopyrightM(CopyrightM researchCopyRight) {
        // TODO Auto-generated method stub
        researchCopyRight.setServiceName(ServiceConstant.COPYRIGHT_SEARCH);
        return postMessage(researchCopyRight, researchCopyRight.getClass().getName(), "copyright", true);
        //return imakeMessage.getResultListObj();
    }

    @Override
    public Integer saveRewardM(RewardM researchReward) {
        // TODO Auto-generated method stub
        researchReward.setServiceName(ServiceConstant.REWARD_SAVE);
        ImakeResultMessage imakeMessage = postMessage(researchReward, researchReward.getClass().getName(), "reward", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((RewardM) imakeMessage.getResultListObj().get(0)).getUpdateRecord();
        else
            return null;
    }

    @Override
    public Integer updateRewardM(RewardM researchReward) {
        // TODO Auto-generated method stub
        researchReward.setServiceName(ServiceConstant.REWARD_UPDATE);
        ImakeResultMessage imakeMessage = postMessage(researchReward, researchReward.getClass().getName(), "reward", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((RewardM) imakeMessage.getResultListObj().get(0)).getUpdateRecord();
        else
            return null;
    }

    @Override
    public Integer deleteRewardM(RewardM researchReward) {
        // TODO Auto-generated method stub
        researchReward.setServiceName(ServiceConstant.REWARD_DELETE);
        ImakeResultMessage imakeMessage = postMessage(researchReward, researchReward.getClass().getName(), "reward", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((RewardM) imakeMessage.getResultListObj().get(0)).getUpdateRecord();
        else
            return null;
    }

    @Override
    public RewardM findRewardById(Integer rewardId, String userid) {
        // TODO Auto-generated method stub
        RewardM researchRewardM = new RewardM();
        researchRewardM.setRewardId(rewardId);
        researchRewardM.setUserid(userid);
        researchRewardM.setServiceName(ServiceConstant.REWARD_FIND_BY_ID);
        ImakeResultMessage imakeMessage = postMessage(researchRewardM, researchRewardM.getClass().getName(), "reward", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return (RewardM) imakeMessage.getResultListObj().get(0);
        else
            return null;
    }

    @Override
    public ImakeResultMessage searchRewardM(RewardM researchReward) {
        // TODO Auto-generated method stub
        researchReward.setServiceName(ServiceConstant.REWARD_SEARCH);
        return postMessage(researchReward, researchReward.getClass().getName(), "reward", true);
        //return imakeMessage.getResultListObj();
    }

    @Override
    public Integer savePatentM(PatentM researchPatent) {
        // TODO Auto-generated method stub
        researchPatent.setServiceName(ServiceConstant.PATENT_SAVE);
        ImakeResultMessage imakeMessage = postMessage(researchPatent, researchPatent.getClass().getName(), "patent", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((PatentM) imakeMessage.getResultListObj().get(0)).getUpdateRecord();
        else
            return null;
    }

    @Override
    public Integer updatePatentM(PatentM researchPatent) {
        // TODO Auto-generated method stub
        researchPatent.setServiceName(ServiceConstant.PATENT_UPDATE);
        ImakeResultMessage imakeMessage = postMessage(researchPatent, researchPatent.getClass().getName(), "patent", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((PatentM) imakeMessage.getResultListObj().get(0)).getUpdateRecord();
        else
            return null;
    }

    @Override
    public Integer deletePatentM(PatentM researchPatent) {
        // TODO Auto-generated method stub
        researchPatent.setServiceName(ServiceConstant.PATENT_DELETE);
        ImakeResultMessage imakeMessage = postMessage(researchPatent, researchPatent.getClass().getName(), "patent", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((PatentM) imakeMessage.getResultListObj().get(0)).getUpdateRecord();
        else
            return null;
    }

    @Override
    public PatentM findPatentById(Integer inventionId, String userid) {
        // TODO Auto-generated method stub
        PatentM researchPatentM = new PatentM();
        researchPatentM.setInventionId(inventionId);
        researchPatentM.setUserid(userid);
        researchPatentM.setServiceName(ServiceConstant.PATENT_FIND_BY_ID);
        ImakeResultMessage imakeMessage = postMessage(researchPatentM, researchPatentM.getClass().getName(), "patent", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return (PatentM) imakeMessage.getResultListObj().get(0);
        else
            return null;
    }

    @Override
    public ImakeResultMessage searchPatentM(PatentM researchPatent) {
        // TODO Auto-generated method stub
        researchPatent.setServiceName(ServiceConstant.PATENT_SEARCH);
        return postMessage(researchPatent, researchPatent.getClass().getName(), "patent", true);
        //return imakeMessage.getResultListObj();
    }

    @Override
    public Integer updateDocTypeM(DocTypeM docType) {
        // TODO Auto-generated method stub
        docType.setServiceName(ServiceConstant.DOCTYPE_UPDATE);
        ImakeResultMessage imakeMessage = postMessage(docType, docType.getClass().getName(), "doctype", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((DocTypeM) imakeMessage.getResultListObj().get(0)).getUpdateRecord();
        else
            return null;
    }

    @Override
    public ImakeResultMessage listDocTypeM() {
        // TODO Auto-generated method stub
        DocTypeM docType = new DocTypeM();
        docType.setServiceName(ServiceConstant.DOCTYPE_LIST);
        return postMessage(docType, docType.getClass().getName(), "doctype", true);
        //return imakeMessage.getResultListObj();
    }

    @Override
    public UserPortalM findUserPortalById(Integer userId) {
        // TODO Auto-generated method stub
        UserPortalM userPortalM = new UserPortalM();
        userPortalM.setUserId(userId);
        userPortalM.setServiceName(ServiceConstant.USER_PORTAL_FIND_BY_ID);
        ImakeResultMessage imakeMessage = postMessage(userPortalM, userPortalM.getClass().getName(), "userportal", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return (UserPortalM) imakeMessage.getResultListObj().get(0);
        else
            return null;
    }

    @Override
    public ImakeResultMessage searchUserPortal(UserPortalM userPortal) {
        // TODO Auto-generated method stub
        userPortal.setServiceName(ServiceConstant.USER_PORTAL_SEARCH);
        return postMessage(userPortal, userPortal.getClass().getName(), "userportal", true);
        //return imakeMessage.getResultListObj();
    }

    @Override
    public Integer saveDocAssignMapping(DocAssignMappingM docAssignMapping) {
        // TODO Auto-generated method stub
        docAssignMapping.setServiceName(ServiceConstant.DOC_ASSIGN_MAPPING_SAVE);
        ImakeResultMessage imakeMessage = postMessage(docAssignMapping, docAssignMapping.getClass().getName(), "docassign", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((DocAssignMappingM) imakeMessage.getResultListObj().get(0)).getUpdateRecord();
        else
            return null;
    }

    @Override
    public Integer deleteDocAssignMapping(DocAssignMappingM docAssignMapping) {
        // TODO Auto-generated method stub
        docAssignMapping.setServiceName(ServiceConstant.DOC_ASSIGN_MAPPING_DELETE);
        ImakeResultMessage imakeMessage = postMessage(docAssignMapping, docAssignMapping.getClass().getName(), "docassign", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((DocAssignMappingM) imakeMessage.getResultListObj().get(0)).getUpdateRecord();
        else
            return null;
    }

    @Override
    public DocAssignMappingM findDocAssignMappingById(Integer refId,
                                                      String refType) {
        // TODO Auto-generated method stub
        DocAssignMappingM docAssignMappingM = new DocAssignMappingM();
        docAssignMappingM.setRefId(refId);
        docAssignMappingM.setRefType(refType);
        docAssignMappingM.setServiceName(ServiceConstant.DOC_ASSIGN_MAPPING_BY_ID);
        ImakeResultMessage imakeMessage = postMessage(docAssignMappingM, docAssignMappingM.getClass().getName(), "docassign", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return (DocAssignMappingM) imakeMessage.getResultListObj().get(0);
        else
            return null;
    }

    @Override
    public ImakeResultMessage listDocAssignMappingt(DocAssignMappingM docAssignMappingM) {
        // TODO Auto-generated method stub
        //DocAssignMappingM docAssignMappingM =new DocAssignMappingM();
        docAssignMappingM.setServiceName(ServiceConstant.DOC_ASSIGN_MAPPING_LIST);
        return postMessage(docAssignMappingM, docAssignMappingM.getClass().getName(), "docassign", true);
        //return imakeMessage.getResultListObj();
    }

    @Override
    public List<ResearcherGroupM> listResearcherGroupM(Integer researcherId) {
        ResearcherGroupM researcherGroupM = new ResearcherGroupM();
        researcherGroupM.setResearcherId(researcherId);

        ResearcherM researcherM = new ResearcherM();
        researcherM.setResearcherGroup(researcherGroupM);

        researcherM.setServiceName(ServiceConstant.RESEARCHER_GROUP_LIST);
        ImakeResultMessage imakeMessage = postMessage(researcherM, researcherM.getClass().getName(), "researcher", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((ResearcherM) imakeMessage.getResultListObj().get(0)).getResearcherGroups();
        else
            return null;
    }

    @Override
    public List<ResearchProjectDocumentM> listResearchProjectDocument(
            Integer researchProjectId) {
        // TODO Auto-generated method stub
        ResearchProjectDocumentM researchProjectDocumentM = new ResearchProjectDocumentM();
        researchProjectDocumentM.setResearchProjectId(researchProjectId);

        ResearchProjectM researchProject = new ResearchProjectM();
        researchProject.setDocument(researchProjectDocumentM);

        researchProject.setServiceName(ServiceConstant.RESEARCH_PROJECT_DOCUMENT_LIST);
        ImakeResultMessage imakeMessage = postMessage(researchProject, researchProject.getClass().getName(), "researchProject", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((ResearchProjectM) imakeMessage.getResultListObj().get(0)).getDocuments();
        else
            return null;
    }

    @Override
    public List<ResearchProjectPaymentM> listResearchProjectPayment(
            Integer researchProjectId) {
        // TODO Auto-generated method stub
        ResearchProjectPaymentM researchProjectPaymentM = new ResearchProjectPaymentM();
        researchProjectPaymentM.setResearchProjectId(researchProjectId);

        ResearchProjectM researchProject = new ResearchProjectM();
        researchProject.setPayment(researchProjectPaymentM);

        researchProject.setServiceName(ServiceConstant.RESEARCH_PROJECT_PAYMENT_LIST);
        ImakeResultMessage imakeMessage = postMessage(researchProject, researchProject.getClass().getName(), "researchProject", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((ResearchProjectM) imakeMessage.getResultListObj().get(0)).getPayments();
        else
            return null;
    }

    @Override
    public List<ResearchProjectProgressM> listResearchProjectProgress(
            Integer researchProjectId) {
        // TODO Auto-generated method stub
        ResearchProjectProgressM researchProjectProgressM = new ResearchProjectProgressM();
        researchProjectProgressM.setResearchProjectId(researchProjectId);

        ResearchProjectM researchProject = new ResearchProjectM();
        researchProject.setProgress(researchProjectProgressM);

        researchProject.setServiceName(ServiceConstant.RESEARCH_PROJECT_PROGRESS_LIST);
        ImakeResultMessage imakeMessage = postMessage(researchProject, researchProject.getClass().getName(), "researchProject", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((ResearchProjectM) imakeMessage.getResultListObj().get(0)).getProgresList();
        else
            return null;
    }

    @Override
    public List<ResearchProjectResearcherM> listResearchProjectResearcher(
            Integer researchProjectId) {
        // TODO Auto-generated method stub
        ResearchProjectResearcherM researchProjectResearcherM = new ResearchProjectResearcherM();
        researchProjectResearcherM.setResearchProjectId(researchProjectId);

        ResearchProjectM researchProject = new ResearchProjectM();
        researchProject.setResearcher(researchProjectResearcherM);

        researchProject.setServiceName(ServiceConstant.RESEARCH_PROJECT_RESEARCHER_LIST);
        ImakeResultMessage imakeMessage = postMessage(researchProject, researchProject.getClass().getName(), "researchProject", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((ResearchProjectM) imakeMessage.getResultListObj().get(0)).getResearchers();
        else
            return null;
    }

    @Override
    public List<ResearchProjectWithdrawM> listResearchProjectWithdraw(
            Integer researchProjectId) {
        // TODO Auto-generated method stub
        ResearchProjectWithdrawM researchProjectWithdrawM = new ResearchProjectWithdrawM();
        researchProjectWithdrawM.setResearchProjectId(researchProjectId);

        ResearchProjectM researchProject = new ResearchProjectM();
        researchProject.setWithdraw(researchProjectWithdrawM);

        researchProject.setServiceName(ServiceConstant.RESEARCH_PROJECT_WITHDRAW_LIST);
        ImakeResultMessage imakeMessage = postMessage(researchProject, researchProject.getClass().getName(), "researchProject", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((ResearchProjectM) imakeMessage.getResultListObj().get(0)).getWithdraws();
        else
            return null;
    }

    @Override
    public Integer saveResearchProjectDocument(
            ResearchProjectDocumentM transientInstance) {
        // TODO Auto-generated method stub
        ResearchProjectM researchProjectM = new ResearchProjectM();
        researchProjectM.setDocument(transientInstance);
        researchProjectM.setServiceName(ServiceConstant.RESEARCH_PROJECT_DOCUMENT_SAVE);
        ImakeResultMessage imakeMessage = postMessage(researchProjectM, researchProjectM.getClass().getName(), "researchProject", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((ResearchProjectM) imakeMessage.getResultListObj().get(0)).getUpdateRecord();
        else
            return null;
    }

    @Override
    public Integer updateResearchProjectDocument(
            ResearchProjectDocumentM transientInstance) {
        // TODO Auto-generated method stub
        ResearchProjectM researchProjectM = new ResearchProjectM();
        researchProjectM.setDocument(transientInstance);
        researchProjectM.setServiceName(ServiceConstant.RESEARCH_PROJECT_DOCUMENT_UPDATE);
        ImakeResultMessage imakeMessage = postMessage(researchProjectM, researchProjectM.getClass().getName(), "researchProject", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((ResearchProjectM) imakeMessage.getResultListObj().get(0)).getUpdateRecord();
        else
            return null;
    }

    @Override
    public Integer deleteResearchProjectDocument(
            ResearchProjectDocumentM persistentInstance) {
        // TODO Auto-generated method stub
        ResearchProjectM researchProjectM = new ResearchProjectM();
        researchProjectM.setDocument(persistentInstance);
        researchProjectM.setServiceName(ServiceConstant.RESEARCH_PROJECT_DOCUMENT_DELETE);
        ImakeResultMessage imakeMessage = postMessage(researchProjectM, researchProjectM.getClass().getName(), "researchProject", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((ResearchProjectM) imakeMessage.getResultListObj().get(0)).getUpdateRecord();
        else
            return null;
    }

    @Override
    public Integer saveResearchProjectPayment(
            ResearchProjectPaymentM transientInstance) {
        // TODO Auto-generated method stub
        ResearchProjectM researchProjectM = new ResearchProjectM();
        researchProjectM.setPayment(transientInstance);
        researchProjectM.setServiceName(ServiceConstant.RESEARCH_PROJECT_PAYMENT_SAVE);
        ImakeResultMessage imakeMessage = postMessage(researchProjectM, researchProjectM.getClass().getName(), "researchProject", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((ResearchProjectM) imakeMessage.getResultListObj().get(0)).getUpdateRecord();
        else
            return null;
    }

    @Override
    public Integer updateResearchProjectPayment(
            ResearchProjectPaymentM transientInstance) {
        // TODO Auto-generated method stub
        ResearchProjectM researchProjectM = new ResearchProjectM();
        researchProjectM.setPayment(transientInstance);
        researchProjectM.setServiceName(ServiceConstant.RESEARCH_PROJECT_PAYMENT_UPDATE);
        ImakeResultMessage imakeMessage = postMessage(researchProjectM, researchProjectM.getClass().getName(), "researchProject", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((ResearchProjectM) imakeMessage.getResultListObj().get(0)).getUpdateRecord();
        else
            return null;
    }

    @Override
    public Integer deleteResearchProjectPayment(
            ResearchProjectPaymentM persistentInstance) {
        // TODO Auto-generated method stub
        ResearchProjectM researchProjectM = new ResearchProjectM();
        researchProjectM.setPayment(persistentInstance);
        researchProjectM.setServiceName(ServiceConstant.RESEARCH_PROJECT_PAYMENT_DELETE);
        ImakeResultMessage imakeMessage = postMessage(researchProjectM, researchProjectM.getClass().getName(), "researchProject", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((ResearchProjectM) imakeMessage.getResultListObj().get(0)).getUpdateRecord();
        else
            return null;
    }

    @Override
    public Integer saveResearchProjectProgress(
            ResearchProjectProgressM transientInstance) {
        // TODO Auto-generated method stub
        ResearchProjectM researchProjectM = new ResearchProjectM();
        researchProjectM.setProgress(transientInstance);
        researchProjectM.setServiceName(ServiceConstant.RESEARCH_PROJECT_PROGRESS_SAVE);
        ImakeResultMessage imakeMessage = postMessage(researchProjectM, researchProjectM.getClass().getName(), "researchProject", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((ResearchProjectM) imakeMessage.getResultListObj().get(0)).getUpdateRecord();
        else
            return null;
    }

    @Override
    public Integer updateResearchProjectProgress(
            ResearchProjectProgressM transientInstance) {
        // TODO Auto-generated method stub
        ResearchProjectM researchProjectM = new ResearchProjectM();
        researchProjectM.setProgress(transientInstance);
        researchProjectM.setServiceName(ServiceConstant.RESEARCH_PROJECT_PROGRESS_UPDATE);
        ImakeResultMessage imakeMessage = postMessage(researchProjectM, researchProjectM.getClass().getName(), "researchProject", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((ResearchProjectM) imakeMessage.getResultListObj().get(0)).getUpdateRecord();
        else
            return null;
    }

    @Override
    public Integer deleteResearchProjectProgress(
            ResearchProjectProgressM persistentInstance) {
        // TODO Auto-generated method stub
        ResearchProjectM researchProjectM = new ResearchProjectM();
        researchProjectM.setProgress(persistentInstance);
        researchProjectM.setServiceName(ServiceConstant.RESEARCH_PROJECT_PROGRESS_DELETE);
        ImakeResultMessage imakeMessage = postMessage(researchProjectM, researchProjectM.getClass().getName(), "researchProject", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((ResearchProjectM) imakeMessage.getResultListObj().get(0)).getUpdateRecord();
        else
            return null;
    }

    @Override
    public Integer saveResearchProjectResearcher(
            ResearchProjectResearcherM transientInstance) {
        // TODO Auto-generated method stub
        ResearchProjectM researchProjectM = new ResearchProjectM();
        researchProjectM.setResearcher(transientInstance);
        researchProjectM.setServiceName(ServiceConstant.RESEARCH_PROJECT_RESEARCHER_SAVE);
        ImakeResultMessage imakeMessage = postMessage(researchProjectM, researchProjectM.getClass().getName(), "researchProject", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((ResearchProjectM) imakeMessage.getResultListObj().get(0)).getUpdateRecord();
        else
            return null;
    }

    @Override
    public Integer updateResearchProjectResearcher(
            ResearchProjectResearcherM transientInstance) {
        // TODO Auto-generated method stub
        ResearchProjectM researchProjectM = new ResearchProjectM();
        researchProjectM.setResearcher(transientInstance);
        researchProjectM.setServiceName(ServiceConstant.RESEARCH_PROJECT_RESEARCHER_UPDATE);
        ImakeResultMessage imakeMessage = postMessage(researchProjectM, researchProjectM.getClass().getName(), "researchProject", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((ResearchProjectM) imakeMessage.getResultListObj().get(0)).getUpdateRecord();
        else
            return null;
    }

    @Override
    public Integer deleteResearchProjectResearcher(
            ResearchProjectResearcherM persistentInstance) {
        // TODO Auto-generated method stub
        ResearchProjectM researchProjectM = new ResearchProjectM();
        researchProjectM.setResearcher(persistentInstance);
        researchProjectM.setServiceName(ServiceConstant.RESEARCH_PROJECT_RESEARCHER_DELETE);
        ImakeResultMessage imakeMessage = postMessage(researchProjectM, researchProjectM.getClass().getName(), "researchProject", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((ResearchProjectM) imakeMessage.getResultListObj().get(0)).getUpdateRecord();
        else
            return null;
    }

    @Override
    public Integer saveResearchProjectWithdraw(
            ResearchProjectWithdrawM transientInstance) {
        // TODO Auto-generated method stub
        ResearchProjectM researchProjectM = new ResearchProjectM();
        researchProjectM.setWithdraw(transientInstance);
        researchProjectM.setServiceName(ServiceConstant.RESEARCH_PROJECT_WITHDRAW_SAVE);
        ImakeResultMessage imakeMessage = postMessage(researchProjectM, researchProjectM.getClass().getName(), "researchProject", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((ResearchProjectM) imakeMessage.getResultListObj().get(0)).getUpdateRecord();
        else
            return null;
    }

    @Override
    public Integer updateResearchProjectWithdraw(
            ResearchProjectWithdrawM transientInstance) {
        // TODO Auto-generated method stub
        ResearchProjectM researchProjectM = new ResearchProjectM();
        researchProjectM.setWithdraw(transientInstance);
        researchProjectM.setServiceName(ServiceConstant.RESEARCH_PROJECT_WITHDRAW_UPDATE);
        ImakeResultMessage imakeMessage = postMessage(researchProjectM, researchProjectM.getClass().getName(), "researchProject", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((ResearchProjectM) imakeMessage.getResultListObj().get(0)).getUpdateRecord();
        else
            return null;
    }

    @Override
    public Integer deleteResearchProjectWithdraw(
            ResearchProjectWithdrawM persistentInstance) {
        // TODO Auto-generated method stub
        ResearchProjectM researchProjectM = new ResearchProjectM();
        researchProjectM.setWithdraw(persistentInstance);
        researchProjectM.setServiceName(ServiceConstant.RESEARCH_PROJECT_WITHDRAW_DELETE);
        ImakeResultMessage imakeMessage = postMessage(researchProjectM, researchProjectM.getClass().getName(), "researchProject", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((ResearchProjectM) imakeMessage.getResultListObj().get(0)).getUpdateRecord();
        else
            return null;
    }

    @Override
    public List<JournalPapersDocumentM> listJournalPapersDocument(
            Integer journalPapersId) {
        // TODO Auto-generated method stub
        JournalPapersDocumentM journalPapersDocumentM = new JournalPapersDocumentM();
        journalPapersDocumentM.setJournalPapersId(journalPapersId);

        JournalPaperM journalPaperM = new JournalPaperM();
        journalPaperM.setJournalPapersDocument(journalPapersDocumentM);

        journalPaperM.setServiceName(ServiceConstant.JOURNAL_PAPERS_DOCUMENT_LIST);
        ImakeResultMessage imakeMessage = postMessage(journalPaperM, journalPaperM.getClass().getName(), "journalPapers", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((JournalPaperM) imakeMessage.getResultListObj().get(0)).getJournalPapersDocuments();
        else
            return null;
    }

    @Override
    public List<JournalPapersWriterM> listJournalPapersWriter(
            Integer journalPapersId) {
        // TODO Auto-generated method stub
        JournalPapersWriterM journalPapersWriterM = new JournalPapersWriterM();
        journalPapersWriterM.setJournalPapersId(journalPapersId);

        JournalPaperM journalPaperM = new JournalPaperM();
        journalPaperM.setJournalPapersWriter(journalPapersWriterM);

        journalPaperM.setServiceName(ServiceConstant.JOURNAL_PAPERS_WRITER_LIST);
        ImakeResultMessage imakeMessage = postMessage(journalPaperM, journalPaperM.getClass().getName(), "journalPapers", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((JournalPaperM) imakeMessage.getResultListObj().get(0)).getJournalPapersWriters();
        else
            return null;
    }

    @Override
    public Integer saveJournalPapersDocument(
            JournalPapersDocumentM transientInstance) {
        // TODO Auto-generated method stub
        JournalPaperM journalPaperM = new JournalPaperM();
        journalPaperM.setJournalPapersDocument(transientInstance);
        journalPaperM.setServiceName(ServiceConstant.JOURNAL_PAPERS_DOCUMENT_SAVE);
        ImakeResultMessage imakeMessage = postMessage(journalPaperM, journalPaperM.getClass().getName(), "journalPapers", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((JournalPaperM) imakeMessage.getResultListObj().get(0)).getUpdateRecord();
        else
            return null;
    }

    @Override
    public Integer updateJournalPapersDocument(
            JournalPapersDocumentM transientInstance) {
        // TODO Auto-generated method stub
        JournalPaperM journalPaperM = new JournalPaperM();
        journalPaperM.setJournalPapersDocument(transientInstance);
        journalPaperM.setServiceName(ServiceConstant.JOURNAL_PAPERS_DOCUMENT_UPDATE);
        ImakeResultMessage imakeMessage = postMessage(journalPaperM, journalPaperM.getClass().getName(), "journalPapers", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((JournalPaperM) imakeMessage.getResultListObj().get(0)).getUpdateRecord();
        else
            return null;
    }

    @Override
    public Integer deleteJournalPapersDocument(
            JournalPapersDocumentM persistentInstance) {
        // TODO Auto-generated method stub
        JournalPaperM journalPaperM = new JournalPaperM();
        journalPaperM.setJournalPapersDocument(persistentInstance);
        journalPaperM.setServiceName(ServiceConstant.JOURNAL_PAPERS_DOCUMENT_DELETE);
        ImakeResultMessage imakeMessage = postMessage(journalPaperM, journalPaperM.getClass().getName(), "journalPapers", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((JournalPaperM) imakeMessage.getResultListObj().get(0)).getUpdateRecord();
        else
            return null;
    }

    @Override
    public Integer saveJournalPapersWriter(
            JournalPapersWriterM transientInstance) {
        // TODO Auto-generated method stub
        JournalPaperM journalPaperM = new JournalPaperM();
        journalPaperM.setJournalPapersWriter(transientInstance);
        journalPaperM.setServiceName(ServiceConstant.JOURNAL_PAPERS_WRITER_SAVE);
        ImakeResultMessage imakeMessage = postMessage(journalPaperM, journalPaperM.getClass().getName(), "journalPapers", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((JournalPaperM) imakeMessage.getResultListObj().get(0)).getUpdateRecord();
        else
            return null;
    }

    @Override
    public Integer updateJournalPapersWriter(
            JournalPapersWriterM transientInstance) {
        // TODO Auto-generated method stub
        JournalPaperM journalPaperM = new JournalPaperM();
        journalPaperM.setJournalPapersWriter(transientInstance);
        journalPaperM.setServiceName(ServiceConstant.JOURNAL_PAPERS_WRITER_UPDATE);
        ImakeResultMessage imakeMessage = postMessage(journalPaperM, journalPaperM.getClass().getName(), "journalPapers", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((JournalPaperM) imakeMessage.getResultListObj().get(0)).getUpdateRecord();
        else
            return null;
    }

    @Override
    public Integer deleteJournalPapersWriter(
            JournalPapersWriterM persistentInstance) {
        // TODO Auto-generated method stub
        JournalPaperM journalPaperM = new JournalPaperM();
        journalPaperM.setJournalPapersWriter(persistentInstance);
        journalPaperM.setServiceName(ServiceConstant.JOURNAL_PAPERS_WRITER_DELETE);
        ImakeResultMessage imakeMessage = postMessage(journalPaperM, journalPaperM.getClass().getName(), "journalPapers", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((JournalPaperM) imakeMessage.getResultListObj().get(0)).getUpdateRecord();
        else
            return null;
    }

    @Override
    public List<CopyrightCreatorM> listCopyrightCreator(Integer copyrightId) {
        // TODO Auto-generated method stub
        CopyrightCreatorM copyrightCreatorM = new CopyrightCreatorM();
        copyrightCreatorM.setCopyrightId(copyrightId);

        CopyrightM copyrightM = new CopyrightM();
        copyrightM.setCopyrightCreator(copyrightCreatorM);

        copyrightM.setServiceName(ServiceConstant.COPYRIGHT_CREATOR_LIST);
        ImakeResultMessage imakeMessage = postMessage(copyrightM, copyrightM.getClass().getName(), "copyright", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((CopyrightM) imakeMessage.getResultListObj().get(0)).getCopyrightCreators();
        else
            return null;
    }

    @Override
    public Integer saveCopyrightCreator(CopyrightCreatorM transientInstance) {
        // TODO Auto-generated method stub
        CopyrightM copyrightM = new CopyrightM();
        copyrightM.setCopyrightCreator(transientInstance);
        copyrightM.setServiceName(ServiceConstant.COPYRIGHT_CREATOR_SAVE);
        ImakeResultMessage imakeMessage = postMessage(copyrightM, copyrightM.getClass().getName(), "copyright", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((CopyrightM) imakeMessage.getResultListObj().get(0)).getUpdateRecord();
        else
            return null;
    }

    @Override
    public Integer updateCopyrightCreator(CopyrightCreatorM transientInstance) {
        // TODO Auto-generated method stub
        CopyrightM copyrightM = new CopyrightM();
        copyrightM.setCopyrightCreator(transientInstance);
        copyrightM.setServiceName(ServiceConstant.COPYRIGHT_CREATOR_UPDATE);
        ImakeResultMessage imakeMessage = postMessage(copyrightM, copyrightM.getClass().getName(), "copyright", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((CopyrightM) imakeMessage.getResultListObj().get(0)).getUpdateRecord();
        else
            return null;
    }

    @Override
    public Integer deleteCopyrightCreator(CopyrightCreatorM persistentInstance) {
        // TODO Auto-generated method stub
        CopyrightM copyrightM = new CopyrightM();
        copyrightM.setCopyrightCreator(persistentInstance);
        copyrightM.setServiceName(ServiceConstant.COPYRIGHT_CREATOR_DELETE);
        ImakeResultMessage imakeMessage = postMessage(copyrightM, copyrightM.getClass().getName(), "copyright", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((CopyrightM) imakeMessage.getResultListObj().get(0)).getUpdateRecord();
        else
            return null;
    }

    @Override
    public List<RewardCreatorM> listRewardCreator(Integer rewardId) {
        // TODO Auto-generated method stub
        RewardCreatorM rewardCreatorM = new RewardCreatorM();
        rewardCreatorM.setRewardId(rewardId);

        RewardM rewardM = new RewardM();
        rewardM.setRewardCreator(rewardCreatorM);

        rewardM.setServiceName(ServiceConstant.REWARD_CREATOR_LIST);
        ImakeResultMessage imakeMessage = postMessage(rewardM, rewardM.getClass().getName(), "reward", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((RewardM) imakeMessage.getResultListObj().get(0)).getRewardCreators();
        else
            return null;
    }

    @Override
    public Integer saveRewardCreator(RewardCreatorM transientInstance) {
        // TODO Auto-generated method stub
        RewardM rewardM = new RewardM();
        rewardM.setRewardCreator(transientInstance);
        rewardM.setServiceName(ServiceConstant.REWARD_CREATOR_SAVE);
        ImakeResultMessage imakeMessage = postMessage(rewardM, rewardM.getClass().getName(), "reward", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((RewardM) imakeMessage.getResultListObj().get(0)).getUpdateRecord();
        else
            return null;
    }

    @Override
    public Integer updateRewardCreator(RewardCreatorM transientInstance) {
        // TODO Auto-generated method stub
        RewardM rewardM = new RewardM();
        rewardM.setRewardCreator(transientInstance);
        rewardM.setServiceName(ServiceConstant.REWARD_CREATOR_UPDATE);
        ImakeResultMessage imakeMessage = postMessage(rewardM, rewardM.getClass().getName(), "reward", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((RewardM) imakeMessage.getResultListObj().get(0)).getUpdateRecord();
        else
            return null;
    }

    @Override
    public Integer deleteRewardCreator(RewardCreatorM persistentInstance) {
        // TODO Auto-generated method stub
        RewardM rewardM = new RewardM();
        rewardM.setRewardCreator(persistentInstance);
        rewardM.setServiceName(ServiceConstant.REWARD_CREATOR_DELETE);
        ImakeResultMessage imakeMessage = postMessage(rewardM, rewardM.getClass().getName(), "reward", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((RewardM) imakeMessage.getResultListObj().get(0)).getUpdateRecord();
        else
            return null;
    }

    @Override
    public List<PatentCreatorM> listPatentCreator(Integer inventionId) {
        // TODO Auto-generated method stub
        PatentCreatorM patentCreatorM = new PatentCreatorM();
        patentCreatorM.setInventionId(inventionId);

        PatentM patentM = new PatentM();
        patentM.setPatentCreator(patentCreatorM);

        patentM.setServiceName(ServiceConstant.PATENT_CREATOR_LIST);
        ImakeResultMessage imakeMessage = postMessage(patentM, patentM.getClass().getName(), "patent", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((PatentM) imakeMessage.getResultListObj().get(0)).getPatentCreators();
        else
            return null;
    }

    @Override
    public List<PatentDocumentM> listPatentDocument(Integer inventionId) {
        // TODO Auto-generated method stub
        PatentDocumentM patentDocumentM = new PatentDocumentM();
        patentDocumentM.setInventionId(inventionId);

        PatentM patentM = new PatentM();
        patentM.setPatentDocument(patentDocumentM);

        patentM.setServiceName(ServiceConstant.PATENT_DOCUMENT_LIST);
        ImakeResultMessage imakeMessage = postMessage(patentM, patentM.getClass().getName(), "patent", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((PatentM) imakeMessage.getResultListObj().get(0)).getPatentDocuments();
        else
            return null;
    }

    @Override
    public List<PatentEditDateM> listPatentEditDate(Integer inventionId) {
        // TODO Auto-generated method stub
        PatentEditDateM patentEditDateM = new PatentEditDateM();
        patentEditDateM.setInventionId(inventionId);

        PatentM patentM = new PatentM();
        patentM.setPatentEditDate(patentEditDateM);

        patentM.setServiceName(ServiceConstant.PATENT_EDIT_DATE_LIST);
        ImakeResultMessage imakeMessage = postMessage(patentM, patentM.getClass().getName(), "patent", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((PatentM) imakeMessage.getResultListObj().get(0)).getPatentEditDates();
        else
            return null;
    }

    @Override
    public List<PatentFeePaymentM> listPatentFeePayment(Integer inventionId) {
        // TODO Auto-generated method stub
        PatentFeePaymentM patentFeePaymentM = new PatentFeePaymentM();
        patentFeePaymentM.setInventionId(inventionId);

        PatentM patentM = new PatentM();
        patentM.setPatentFeePayment(patentFeePaymentM);

        patentM.setServiceName(ServiceConstant.PATENT_FEE_PAYMENT_LIST);
        ImakeResultMessage imakeMessage = postMessage(patentM, patentM.getClass().getName(), "patent", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((PatentM) imakeMessage.getResultListObj().get(0)).getPatentFeePayments();
        else
            return null;
    }

    @Override
    public List<PatentInheritedM> listPatentInherited(Integer inventionId) {
        // TODO Auto-generated method stub
        PatentInheritedM patentInheritedM = new PatentInheritedM();
        patentInheritedM.setInventionId(inventionId);

        PatentM patentM = new PatentM();
        patentM.setPatentInherited(patentInheritedM);

        patentM.setServiceName(ServiceConstant.PATENT_INHERITED_LIST);
        ImakeResultMessage imakeMessage = postMessage(patentM, patentM.getClass().getName(), "patent", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((PatentM) imakeMessage.getResultListObj().get(0)).getPatentInheriteds();
        else
            return null;
    }

    @Override
    public List<PatentRightholderM> listPatentRightholder(Integer inventionId) {
        // TODO Auto-generated method stub
        PatentRightholderM patentRightholderM = new PatentRightholderM();
        patentRightholderM.setInventionId(inventionId);

        PatentM patentM = new PatentM();
        patentM.setPatentRightholder(patentRightholderM);

        patentM.setServiceName(ServiceConstant.PATENT_RIGHTHOLDER_LIST);
        ImakeResultMessage imakeMessage = postMessage(patentM, patentM.getClass().getName(), "patent", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((PatentM) imakeMessage.getResultListObj().get(0)).getPatentRightholders();
        else
            return null;
    }

    @Override
    public Integer savePatentCreator(PatentCreatorM transientInstance) {
        // TODO Auto-generated method stub
        PatentM patentM = new PatentM();
        patentM.setPatentCreator(transientInstance);
        patentM.setServiceName(ServiceConstant.PATENT_CREATOR_SAVE);
        ImakeResultMessage imakeMessage = postMessage(patentM, patentM.getClass().getName(), "patent", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((PatentM) imakeMessage.getResultListObj().get(0)).getUpdateRecord();
        else
            return null;
    }

    @Override
    public Integer updatePatentCreator(PatentCreatorM transientInstance) {
        // TODO Auto-generated method stub
        PatentM patentM = new PatentM();
        patentM.setPatentCreator(transientInstance);
        patentM.setServiceName(ServiceConstant.PATENT_CREATOR_UPDATE);
        ImakeResultMessage imakeMessage = postMessage(patentM, patentM.getClass().getName(), "patent", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((PatentM) imakeMessage.getResultListObj().get(0)).getUpdateRecord();
        else
            return null;
    }

    @Override
    public Integer deletePatentCreator(PatentCreatorM persistentInstance) {
        // TODO Auto-generated method stub
        PatentM patentM = new PatentM();
        patentM.setPatentCreator(persistentInstance);
        patentM.setServiceName(ServiceConstant.PATENT_CREATOR_DELETE);
        ImakeResultMessage imakeMessage = postMessage(patentM, patentM.getClass().getName(), "patent", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((PatentM) imakeMessage.getResultListObj().get(0)).getUpdateRecord();
        else
            return null;
    }

    @Override
    public Integer savePatentDocument(PatentDocumentM transientInstance) {
        // TODO Auto-generated method stub
        PatentM patentM = new PatentM();
        patentM.setPatentDocument(transientInstance);
        patentM.setServiceName(ServiceConstant.PATENT_DOCUMENT_SAVE);
        ImakeResultMessage imakeMessage = postMessage(patentM, patentM.getClass().getName(), "patent", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((PatentM) imakeMessage.getResultListObj().get(0)).getUpdateRecord();
        else
            return null;
    }

    @Override
    public Integer updatePatentDocument(PatentDocumentM transientInstance) {
        // TODO Auto-generated method stub
        PatentM patentM = new PatentM();
        patentM.setPatentDocument(transientInstance);
        patentM.setServiceName(ServiceConstant.PATENT_DOCUMENT_UPDATE);
        ImakeResultMessage imakeMessage = postMessage(patentM, patentM.getClass().getName(), "patent", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((PatentM) imakeMessage.getResultListObj().get(0)).getUpdateRecord();
        else
            return null;
    }

    @Override
    public Integer deletePatentDocument(PatentDocumentM persistentInstance) {
        // TODO Auto-generated method stub
        PatentM patentM = new PatentM();
        patentM.setPatentDocument(persistentInstance);
        patentM.setServiceName(ServiceConstant.PATENT_DOCUMENT_DELETE);
        ImakeResultMessage imakeMessage = postMessage(patentM, patentM.getClass().getName(), "patent", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((PatentM) imakeMessage.getResultListObj().get(0)).getUpdateRecord();
        else
            return null;
    }

    @Override
    public Integer savePatentEditDate(PatentEditDateM transientInstance) {
        // TODO Auto-generated method stub
        PatentM patentM = new PatentM();
        patentM.setPatentEditDate(transientInstance);
        patentM.setServiceName(ServiceConstant.PATENT_EDIT_DATE_SAVE);
        ImakeResultMessage imakeMessage = postMessage(patentM, patentM.getClass().getName(), "patent", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((PatentM) imakeMessage.getResultListObj().get(0)).getUpdateRecord();
        else
            return null;
    }

    @Override
    public Integer updatePatentEditDate(PatentEditDateM transientInstance) {
        // TODO Auto-generated method stub
        PatentM patentM = new PatentM();
        patentM.setPatentEditDate(transientInstance);
        patentM.setServiceName(ServiceConstant.PATENT_EDIT_DATE_UPDATE);
        ImakeResultMessage imakeMessage = postMessage(patentM, patentM.getClass().getName(), "patent", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((PatentM) imakeMessage.getResultListObj().get(0)).getUpdateRecord();
        else
            return null;
    }

    @Override
    public Integer deletePatentEditDate(PatentEditDateM persistentInstance) {
        // TODO Auto-generated method stub
        PatentM patentM = new PatentM();
        patentM.setPatentEditDate(persistentInstance);
        patentM.setServiceName(ServiceConstant.PATENT_EDIT_DATE_DELETE);
        ImakeResultMessage imakeMessage = postMessage(patentM, patentM.getClass().getName(), "patent", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((PatentM) imakeMessage.getResultListObj().get(0)).getUpdateRecord();
        else
            return null;
    }

    @Override
    public Integer savePatentFeePayment(PatentFeePaymentM transientInstance) {
        // TODO Auto-generated method stub
        PatentM patentM = new PatentM();
        patentM.setPatentFeePayment(transientInstance);
        patentM.setServiceName(ServiceConstant.PATENT_FEE_PAYMENT_SAVE);
        ImakeResultMessage imakeMessage = postMessage(patentM, patentM.getClass().getName(), "patent", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((PatentM) imakeMessage.getResultListObj().get(0)).getUpdateRecord();
        else
            return null;
    }

    @Override
    public Integer updatePatentFeePayment(PatentFeePaymentM transientInstance) {
        // TODO Auto-generated method stub
        PatentM patentM = new PatentM();
        patentM.setPatentFeePayment(transientInstance);
        patentM.setServiceName(ServiceConstant.PATENT_FEE_PAYMENT_UPDATE);
        ImakeResultMessage imakeMessage = postMessage(patentM, patentM.getClass().getName(), "patent", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((PatentM) imakeMessage.getResultListObj().get(0)).getUpdateRecord();
        else
            return null;
    }

    @Override
    public Integer deletePatentFeePayment(PatentFeePaymentM persistentInstance) {
        // TODO Auto-generated method stub
        PatentM patentM = new PatentM();
        patentM.setPatentFeePayment(persistentInstance);
        patentM.setServiceName(ServiceConstant.PATENT_FEE_PAYMENT_DELETE);
        ImakeResultMessage imakeMessage = postMessage(patentM, patentM.getClass().getName(), "patent", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((PatentM) imakeMessage.getResultListObj().get(0)).getUpdateRecord();
        else
            return null;
    }

    @Override
    public Integer savePatentInherited(PatentInheritedM transientInstance) {
        // TODO Auto-generated method stub
        PatentM patentM = new PatentM();
        patentM.setPatentInherited(transientInstance);
        patentM.setServiceName(ServiceConstant.PATENT_INHERITED_SAVE);
        ImakeResultMessage imakeMessage = postMessage(patentM, patentM.getClass().getName(), "patent", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((PatentM) imakeMessage.getResultListObj().get(0)).getUpdateRecord();
        else
            return null;
    }

    @Override
    public Integer updatePatentInherited(PatentInheritedM transientInstance) {
        // TODO Auto-generated method stub
        PatentM patentM = new PatentM();
        patentM.setPatentInherited(transientInstance);
        patentM.setServiceName(ServiceConstant.PATENT_INHERITED_UPDATE);
        ImakeResultMessage imakeMessage = postMessage(patentM, patentM.getClass().getName(), "patent", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((PatentM) imakeMessage.getResultListObj().get(0)).getUpdateRecord();
        else
            return null;
    }

    @Override
    public Integer deletePatentInherited(PatentInheritedM persistentInstance) {
        // TODO Auto-generated method stub
        PatentM patentM = new PatentM();
        patentM.setPatentInherited(persistentInstance);
        patentM.setServiceName(ServiceConstant.PATENT_INHERITED_DELETE);
        ImakeResultMessage imakeMessage = postMessage(patentM, patentM.getClass().getName(), "patent", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((PatentM) imakeMessage.getResultListObj().get(0)).getUpdateRecord();
        else
            return null;
    }

    @Override
    public Integer savePatentRightholder(PatentRightholderM transientInstance) {
        // TODO Auto-generated method stub
        PatentM patentM = new PatentM();
        patentM.setPatentRightholder(transientInstance);
        patentM.setServiceName(ServiceConstant.PATENT_RIGHTHOLDER_SAVE);
        ImakeResultMessage imakeMessage = postMessage(patentM, patentM.getClass().getName(), "patent", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((PatentM) imakeMessage.getResultListObj().get(0)).getUpdateRecord();
        else
            return null;
    }

    @Override
    public Integer updatePatentRightholder(PatentRightholderM transientInstance) {
        // TODO Auto-generated method stub
        PatentM patentM = new PatentM();
        patentM.setPatentRightholder(transientInstance);
        patentM.setServiceName(ServiceConstant.PATENT_RIGHTHOLDER_UPDATE);
        ImakeResultMessage imakeMessage = postMessage(patentM, patentM.getClass().getName(), "patent", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((PatentM) imakeMessage.getResultListObj().get(0)).getUpdateRecord();
        else
            return null;
    }

    @Override
    public Integer deletePatentRightholder(PatentRightholderM persistentInstance) {
        // TODO Auto-generated method stub
        PatentM patentM = new PatentM();
        patentM.setPatentRightholder(persistentInstance);
        patentM.setServiceName(ServiceConstant.PATENT_RIGHTHOLDER_DELETE);
        ImakeResultMessage imakeMessage = postMessage(patentM, patentM.getClass().getName(), "patent", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((PatentM) imakeMessage.getResultListObj().get(0)).getUpdateRecord();
        else
            return null;
    }

    @Override
    public Integer saveTitleM(TitleM titleM) {
        // TODO Auto-generated method stub
        titleM.setServiceName(ServiceConstant.TITLE_SAVE);
        ImakeResultMessage imakeMessage = postMessage(titleM, titleM.getClass().getName(), "title", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((TitleM) imakeMessage.getResultListObj().get(0)).getUpdateRecord();
        else
            return null;
    }

    @Override
    public Integer updateTitleM(TitleM titleM) {
        // TODO Auto-generated method stub
        titleM.setServiceName(ServiceConstant.TITLE_UPDATE);
        ImakeResultMessage imakeMessage = postMessage(titleM, titleM.getClass().getName(), "title", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((TitleM) imakeMessage.getResultListObj().get(0)).getUpdateRecord();
        else
            return null;
    }

    @Override
    public Integer deleteTitleM(TitleM titleM) {
        // TODO Auto-generated method stub
        titleM.setServiceName(ServiceConstant.TITLE_DELETE);
        ImakeResultMessage imakeMessage = postMessage(titleM, titleM.getClass().getName(), "title", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((TitleM) imakeMessage.getResultListObj().get(0)).getUpdateRecord();
        else
            return null;
    }

    @Override
    public TitleM findTitleById(Integer titleId) {
        // TODO Auto-generated method stub
        TitleM titleM = new TitleM();
        titleM.setTitleId(titleId);
        titleM.setServiceName(ServiceConstant.TITLE_FIND_BY_ID);
        ImakeResultMessage imakeMessage = postMessage(titleM, titleM.getClass().getName(), "title", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return (TitleM) imakeMessage.getResultListObj().get(0);
        else
            return null;
    }

    @Override
    public ImakeResultMessage searchTitleM(TitleM titleM) {
        // TODO Auto-generated method stub
        titleM.setServiceName(ServiceConstant.TITLE_SEARCH);
        return postMessage(titleM, titleM.getClass().getName(), "title", true);
    }

    @Override
    public ResearchProjectDocumentM findResearchProjectDocumentById(
            ResearchProjectDocumentM id) {
        // TODO Auto-generated method stub
        ResearchProjectM researchProjectM = new ResearchProjectM();
        researchProjectM.setDocument(id);
        researchProjectM.setServiceName(ServiceConstant.RESEARCH_PROJECT_DOCUMENT_FIND_BY_ID);
        ImakeResultMessage imakeMessage = postMessage(researchProjectM, researchProjectM.getClass().getName(), "researchProject", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((ResearchProjectM) imakeMessage.getResultListObj().get(0)).getDocument();
        else
            return null;
    }

    @Override
    public ResearchProjectPaymentM findResearchProjectPaymentById(
            ResearchProjectPaymentM id) {
        // TODO Auto-generated method stub
        ResearchProjectM researchProjectM = new ResearchProjectM();
        researchProjectM.setPayment(id);
        researchProjectM.setServiceName(ServiceConstant.RESEARCH_PROJECT_PAYMENT_FIND_BY_ID);
        ImakeResultMessage imakeMessage = postMessage(researchProjectM, researchProjectM.getClass().getName(), "researchProject", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0) {

            ResearchProjectPaymentM model = ((ResearchProjectM) imakeMessage.getResultListObj().get(0)).getPayment();
            return model;
        } else
            return null;
    }

    @Override
    public ResearchProjectProgressM findResearchProjectProgressById(
            ResearchProjectProgressM id) {
        // TODO Auto-generated method stub
        ResearchProjectM researchProjectM = new ResearchProjectM();
        researchProjectM.setProgress(id);
        researchProjectM.setServiceName(ServiceConstant.RESEARCH_PROJECT_PROGRESS_FIND_BY_ID);
        ImakeResultMessage imakeMessage = postMessage(researchProjectM, researchProjectM.getClass().getName(), "researchProject", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0) {
            //imakeMessage.getResultListObj().get(0)
            ResearchProjectProgressM model = ((ResearchProjectM) imakeMessage.getResultListObj().get(0)).getProgress();
            /*if(model.getSubmitDate()!=null)
				model.setSubmitDateShow(format1.format(model.getSubmitDate()));
			if(model.getDueDate()!=null)
				model.setDueDateShow(format1.format(model.getDueDate()))*/
            ;
            return model;
        } else
            return null;
    }

    @Override
    public ResearchProjectResearcherM findResearchProjectResearcherById(
            ResearchProjectResearcherM id) {
        // TODO Auto-generated method stub
        ResearchProjectM researchProjectM = new ResearchProjectM();
        researchProjectM.setResearcher(id);
        researchProjectM.setServiceName(ServiceConstant.RESEARCH_PROJECT_RESEARCHER_FIND_BY_ID);
        ImakeResultMessage imakeMessage = postMessage(researchProjectM, researchProjectM.getClass().getName(), "researchProject", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((ResearchProjectM) imakeMessage.getResultListObj().get(0)).getResearcher();
        else
            return null;
    }

    @Override
    public ResearchProjectWithdrawM findResearchProjectWithdrawById(
            ResearchProjectWithdrawM id) {
        // TODO Auto-generated method stub
        ResearchProjectM researchProjectM = new ResearchProjectM();
        researchProjectM.setWithdraw(id);
        researchProjectM.setServiceName(ServiceConstant.RESEARCH_PROJECT_WITHDRAW_FIND_BY_ID);
        ImakeResultMessage imakeMessage = postMessage(researchProjectM, researchProjectM.getClass().getName(), "researchProject", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((ResearchProjectM) imakeMessage.getResultListObj().get(0)).getWithdraw();
        else
            return null;
    }

    @Override
    public JournalPapersDocumentM findJournalPapersDocumentById(
            JournalPapersDocumentM id) {
        // TODO Auto-generated method stub
        JournalPaperM journalPaperM = new JournalPaperM();
        journalPaperM.setJournalPapersDocument(id);
        journalPaperM.setServiceName(ServiceConstant.JOURNAL_PAPERS_DOCUMENT_FIND_BY_ID);
        ImakeResultMessage imakeMessage = postMessage(journalPaperM, journalPaperM.getClass().getName(), "journalPapers", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((JournalPaperM) imakeMessage.getResultListObj().get(0)).getJournalPapersDocument();
        else
            return null;
    }

    @Override
    public JournalPapersWriterM findJournalPapersWriterById(
            JournalPapersWriterM id) {
        // TODO Auto-generated method stub
        JournalPaperM journalPaperM = new JournalPaperM();
        journalPaperM.setJournalPapersWriter(id);
        journalPaperM.setServiceName(ServiceConstant.JOURNAL_PAPERS_WRITER_FIND_BY_ID);
        ImakeResultMessage imakeMessage = postMessage(journalPaperM, journalPaperM.getClass().getName(), "journalPapers", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0) {
            JournalPaperM model = (JournalPaperM) imakeMessage.getResultListObj().get(0);
            return model.getJournalPapersWriter();
        } else
            return null;
    }

    @Override
    public CopyrightCreatorM findCopyrightCreatorById(CopyrightCreatorM id) {
        // TODO Auto-generated method stub
        CopyrightM copyrightM = new CopyrightM();
        copyrightM.setCopyrightCreator(id);
        copyrightM.setServiceName(ServiceConstant.COPYRIGHT_CREATOR_FIND_BY_ID);
        ImakeResultMessage imakeMessage = postMessage(copyrightM, copyrightM.getClass().getName(), "copyright", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((CopyrightM) imakeMessage.getResultListObj().get(0)).getCopyrightCreator();
        else
            return null;
    }

    @Override
    public RewardCreatorM findRewardCreatorById(RewardCreatorM id) {
        // TODO Auto-generated method stub
        RewardM rewardM = new RewardM();
        rewardM.setRewardCreator(id);
        rewardM.setServiceName(ServiceConstant.REWARD_CREATOR_FIND_BY_ID);
        ImakeResultMessage imakeMessage = postMessage(rewardM, rewardM.getClass().getName(), "reward", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((RewardM) imakeMessage.getResultListObj().get(0)).getRewardCreator();
        else
            return null;
    }

    @Override
    public PatentCreatorM findPatentCreatorById(PatentCreatorM id) {
        // TODO Auto-generated method stub
        PatentM patentM = new PatentM();
        patentM.setPatentCreator(id);
        patentM.setServiceName(ServiceConstant.PATENT_CREATOR_FIND_BY_ID);
        ImakeResultMessage imakeMessage = postMessage(patentM, patentM.getClass().getName(), "patent", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((PatentM) imakeMessage.getResultListObj().get(0)).getPatentCreator();
        else
            return null;
    }

    @Override
    public PatentDocumentM findPatentDocumentById(PatentDocumentM id) {
        // TODO Auto-generated method stub
        PatentM patentM = new PatentM();
        patentM.setPatentDocument(id);
        patentM.setServiceName(ServiceConstant.PATENT_DOCUMENT_FIND_BY_ID);
        ImakeResultMessage imakeMessage = postMessage(patentM, patentM.getClass().getName(), "patent", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((PatentM) imakeMessage.getResultListObj().get(0)).getPatentDocument();
        else
            return null;
    }

    @Override
    public PatentEditDateM findPatentEditDateById(PatentEditDateM id) {
        // TODO Auto-generated method stub
        PatentM patentM = new PatentM();
        patentM.setPatentEditDate(id);
        patentM.setServiceName(ServiceConstant.PATENT_EDIT_DATE_FIND_BY_ID);
        ImakeResultMessage imakeMessage = postMessage(patentM, patentM.getClass().getName(), "patent", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((PatentM) imakeMessage.getResultListObj().get(0)).getPatentEditDate();
        else
            return null;
    }

    @Override
    public PatentFeePaymentM findPatentFeePaymentById(PatentFeePaymentM id) {
        // TODO Auto-generated method stub
        PatentM patentM = new PatentM();
        patentM.setPatentFeePayment(id);
        patentM.setServiceName(ServiceConstant.PATENT_FEE_PAYMENT_FIND_BY_ID);
        ImakeResultMessage imakeMessage = postMessage(patentM, patentM.getClass().getName(), "patent", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((PatentM) imakeMessage.getResultListObj().get(0)).getPatentFeePayment();
        else
            return null;
    }

    @Override
    public PatentInheritedM findPatentInheritedById(PatentInheritedM id) {
        // TODO Auto-generated method stub
        PatentM patentM = new PatentM();
        patentM.setPatentInherited(id);
        patentM.setServiceName(ServiceConstant.PATENT_INHERITED_FIND_BY_ID);
        ImakeResultMessage imakeMessage = postMessage(patentM, patentM.getClass().getName(), "patent", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((PatentM) imakeMessage.getResultListObj().get(0)).getPatentInherited();
        else
            return null;
    }

    @Override
    public PatentRightholderM findPatentRightholderById(PatentRightholderM id) {
        // TODO Auto-generated method stub
        PatentM patentM = new PatentM();
        patentM.setPatentRightholder(id);
        patentM.setServiceName(ServiceConstant.PATENT_RIGHTHOLDER_FIND_BY_ID);
        ImakeResultMessage imakeMessage = postMessage(patentM, patentM.getClass().getName(), "patent", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((PatentM) imakeMessage.getResultListObj().get(0)).getPatentRightholder();
        else
            return null;
    }

    @Override
    public UtilizationM findUtilizationItemById(
            UtilizationM UtilizationM) {
        // TODO Auto-generated method stub

        UtilizationM.setServiceName(ServiceConstant.UTILIZATION_ITEM_FIND_BY_ID);
        ImakeResultMessage imakeMessage = postMessage(UtilizationM, UtilizationM.getClass().getName(), "utilization", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((UtilizationM) imakeMessage.getResultListObj().get(0));
        else
            return null;
    }

    @Override
    public Integer checkUQResearchGroup(ResearchGroupM researchGroupM) {
        // TODO Auto-generated method stub
        researchGroupM.setServiceName(ServiceConstant.RESEARCH_GROUP_CHECK_UQ);
        ImakeResultMessage imakeMessage = postMessage(researchGroupM, researchGroupM.getClass().getName(), "researchGroup", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((ResearchGroupM) imakeMessage.getResultListObj().get(0)).getUpdateRecord();
        else
            return null;
    }

    @Override
    public Integer checkUQUtilizationType(UtilizationTypeM utilizationTypeM) {
        // TODO Auto-generated method stub
        utilizationTypeM.setServiceName(ServiceConstant.UTILIZATION_TYPE_CHECK_UQ);
        ImakeResultMessage imakeMessage = postMessage(utilizationTypeM, utilizationTypeM.getClass().getName(), "utilizationType", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((UtilizationTypeM) imakeMessage.getResultListObj().get(0)).getUpdateRecord();
        else
            return null;
    }

    @Override
    public Integer checkUQPublishType(PublishTypeM publishTypeM) {
        // TODO Auto-generated method stub
        publishTypeM.setServiceName(ServiceConstant.PUBLISH_TYPE_CHECK_UQ);
        ImakeResultMessage imakeMessage = postMessage(publishTypeM, publishTypeM.getClass().getName(), "publishType", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((PublishTypeM) imakeMessage.getResultListObj().get(0)).getUpdateRecord();
        else
            return null;
    }

    @Override
    public Integer checkUQPosition(PositionM positionM) {
        // TODO Auto-generated method stub
        positionM.setServiceName(ServiceConstant.POSITION_CHECK_UQ);
        ImakeResultMessage imakeMessage = postMessage(positionM, positionM.getClass().getName(), "position", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((PositionM) imakeMessage.getResultListObj().get(0)).getUpdateRecord();
        else
            return null;
    }

    @Override
    public Integer checkUQCountry(CountryM countryM) {
        // TODO Auto-generated method stub
        countryM.setServiceName(ServiceConstant.COUNTRY_CHECK_UQ);
        ImakeResultMessage imakeMessage = postMessage(countryM, countryM.getClass().getName(), "country", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((CountryM) imakeMessage.getResultListObj().get(0)).getUpdateRecord();
        else
            return null;
    }

    @Override
    public Integer checkUQWorkType(WorkTypeM workTypeM) {
        // TODO Auto-generated method stub
        workTypeM.setServiceName(ServiceConstant.WORK_TYPE_CHECK_UQ);
        ImakeResultMessage imakeMessage = postMessage(workTypeM, workTypeM.getClass().getName(), "workType", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((WorkTypeM) imakeMessage.getResultListObj().get(0)).getUpdateRecord();
        else
            return null;
    }

    @Override
    public Integer checkUQJournalPapersType(JournalPapersTypeM journalPapersTypeM) {
        // TODO Auto-generated method stub
        journalPapersTypeM.setServiceName(ServiceConstant.JOURNAL_PAPERS_TYPE_CHECK_UQ);
        ImakeResultMessage imakeMessage = postMessage(journalPapersTypeM, journalPapersTypeM.getClass().getName(), "journalPapersType", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((JournalPapersTypeM) imakeMessage.getResultListObj().get(0)).getUpdateRecord();
        else
            return null;
    }

    @Override
    public Integer checkUQFundingResourceType(
            FundingResourceTypeM fundingResourceTypeM) {
        // TODO Auto-generated method stub
        fundingResourceTypeM.setServiceName(ServiceConstant.FUNDING_RESOURCES_TYPE_CHECK_UQ);
        ImakeResultMessage imakeMessage = postMessage(fundingResourceTypeM, fundingResourceTypeM.getClass().getName(), "fundingResourcesType", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((FundingResourceTypeM) imakeMessage.getResultListObj().get(0)).getUpdateRecord();
        else
            return null;
    }

    @Override
    public Integer checkUQPublishLevel(PublishLevelM publishLevelM) {
        // TODO Auto-generated method stub
        publishLevelM.setServiceName(ServiceConstant.PUBLISH_LEVEL_CHECK_UQ);
        ImakeResultMessage imakeMessage = postMessage(publishLevelM, publishLevelM.getClass().getName(), "publishLevel", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((PublishLevelM) imakeMessage.getResultListObj().get(0)).getUpdateRecord();
        else
            return null;
    }

    @Override
    public Integer checkUQOrganizationExt(OrganizationExtM organizationExtM) {
        // TODO Auto-generated method stub
        organizationExtM.setServiceName(ServiceConstant.ORGANIZATION_EXT_CHECK_UQ);
        ImakeResultMessage imakeMessage = postMessage(organizationExtM, organizationExtM.getClass().getName(), "organizationExt", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((OrganizationExtM) imakeMessage.getResultListObj().get(0)).getUpdateRecord();
        else
            return null;
    }

    @Override
    public Integer checkUQFundingResource(FundingResourceM fundingResourceM) {
        // TODO Auto-generated method stub
        fundingResourceM.setServiceName(ServiceConstant.FUNDING_RESOURCES_CHECK_UQ);
        ImakeResultMessage imakeMessage = postMessage(fundingResourceM, fundingResourceM.getClass().getName(), "fundingResources", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((FundingResourceM) imakeMessage.getResultListObj().get(0)).getUpdateRecord();
        else
            return null;
    }

    @Override
    public Integer countJournalPapersWriter(
            JournalPapersWriterM transientInstance) {
        // TODO Auto-generated method stub
        JournalPaperM journalPaperM = new JournalPaperM();
        journalPaperM.setJournalPapersWriter(transientInstance);
        journalPaperM.setServiceName(ServiceConstant.JOURNAL_PAPERS_WRITER_COUNT);
        ImakeResultMessage imakeMessage = postMessage(journalPaperM, journalPaperM.getClass().getName(), "journalPapers", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0) {
            JournalPaperM model = (JournalPaperM) imakeMessage.getResultListObj().get(0);
            return model.getUpdateRecord();
        } else
            return 0;
    }

    @Override
    public Integer countResearchProjectResearcher(
            ResearchProjectResearcherM transientInstance) {
        // TODO Auto-generated method stub
        ResearchProjectM researchProjectM = new ResearchProjectM();
        researchProjectM.setResearcher(transientInstance);
        researchProjectM.setServiceName(ServiceConstant.RESEARCH_PROJECT_RESEARCHER_COUNT);
        ImakeResultMessage imakeMessage = postMessage(researchProjectM, researchProjectM.getClass().getName(), "researchProject", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0) {
            ResearchProjectM model = (ResearchProjectM) imakeMessage.getResultListObj().get(0);
            return model.getUpdateRecord();
        } else
            return 0;
    }

    @Override
    public List<UtilizationDocumentM> listUtilizationDocument(
            Integer utilizationItemList, Integer researchProjectId) {
        // TODO Auto-generated method stub
        UtilizationDocumentM utilizationDocumentM = new UtilizationDocumentM();
        utilizationDocumentM.setUtilizationItemList(utilizationItemList);
        utilizationDocumentM.setResearchProjectId(utilizationItemList);
        UtilizationM utilizationM = new UtilizationM();
        utilizationM.setUtilizationDocument(utilizationDocumentM);

        utilizationM.setServiceName(ServiceConstant.UTILIZATION_DOCUMENT_LIST);
        ImakeResultMessage imakeMessage = postMessage(utilizationM, utilizationM.getClass().getName(), "utilization", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((UtilizationM) imakeMessage.getResultListObj().get(0)).getUtilizationDocuments();
        else
            return null;
    }

    @Override
    public Integer saveUtilizationDocument(
            UtilizationDocumentM transientInstance) {
        // TODO Auto-generated method stub
        UtilizationM utilizationM = new UtilizationM();
        utilizationM.setUtilizationDocument(transientInstance);
        utilizationM.setServiceName(ServiceConstant.UTILIZATION_DOCUMENT_SAVE);
        ImakeResultMessage imakeMessage = postMessage(utilizationM, utilizationM.getClass().getName(), "utilization", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((UtilizationM) imakeMessage.getResultListObj().get(0)).getUpdateRecord();
        else
            return null;
    }

    @Override
    public Integer updateUtilizationDocument(
            UtilizationDocumentM transientInstance) {
        // TODO Auto-generated method stub
        UtilizationM utilizationM = new UtilizationM();
        utilizationM.setUtilizationDocument(transientInstance);
        utilizationM.setServiceName(ServiceConstant.UTILIZATION_DOCUMENT_UPDATE);
        ImakeResultMessage imakeMessage = postMessage(utilizationM, utilizationM.getClass().getName(), "utilization", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((UtilizationM) imakeMessage.getResultListObj().get(0)).getUpdateRecord();
        else
            return null;
    }

    @Override
    public Integer deleteUtilizationDocument(
            UtilizationDocumentM persistentInstance) {
        // TODO Auto-generated method stub
        UtilizationM utilizationM = new UtilizationM();
        utilizationM.setUtilizationDocument(persistentInstance);
        utilizationM.setServiceName(ServiceConstant.UTILIZATION_DOCUMENT_DELETE);
        ImakeResultMessage imakeMessage = postMessage(utilizationM, utilizationM.getClass().getName(), "utilization", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((UtilizationM) imakeMessage.getResultListObj().get(0)).getUpdateRecord();
        else
            return null;
    }

    @Override
    public UtilizationDocumentM findUtilizationDocumentById(
            UtilizationDocumentM id) {
        // TODO Auto-generated method stub
        UtilizationM utilizationM = new UtilizationM();
        utilizationM.setUtilizationDocument(id);
        utilizationM.setServiceName(ServiceConstant.UTILIZATION_DOCUMENT_FIND_BY_ID);
        ImakeResultMessage imakeMessage = postMessage(utilizationM, utilizationM.getClass().getName(), "utilization", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((UtilizationM) imakeMessage.getResultListObj().get(0)).getUtilizationDocument();
        else
            return null;
    }

    @Override
    public List<CopyrightDocumentM> listCopyrightDocument(Integer copyrightId) {
        // TODO Auto-generated method stub
        CopyrightDocumentM copyrightDocumentM = new CopyrightDocumentM();
        copyrightDocumentM.setCopyrightId(copyrightId);

        CopyrightM copyrightM = new CopyrightM();
        copyrightM.setCopyrightDocument(copyrightDocumentM);

        copyrightM.setServiceName(ServiceConstant.COPYRIGHT_DOCUMENT_LIST);
        ImakeResultMessage imakeMessage = postMessage(copyrightM, copyrightM.getClass().getName(), "copyright", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((CopyrightM) imakeMessage.getResultListObj().get(0)).getCopyrightDocuments();
        else
            return null;
    }

    @Override
    public Integer saveCopyrightDocument(CopyrightDocumentM transientInstance) {
        // TODO Auto-generated method stub
        CopyrightM copyrightM = new CopyrightM();
        copyrightM.setCopyrightDocument(transientInstance);
        copyrightM.setServiceName(ServiceConstant.COPYRIGHT_DOCUMENT_SAVE);
        ImakeResultMessage imakeMessage = postMessage(copyrightM, copyrightM.getClass().getName(), "copyright", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((CopyrightM) imakeMessage.getResultListObj().get(0)).getUpdateRecord();
        else
            return null;
    }

    @Override
    public Integer updateCopyrightDocument(CopyrightDocumentM transientInstance) {
        // TODO Auto-generated method stub
        CopyrightM copyrightM = new CopyrightM();
        copyrightM.setCopyrightDocument(transientInstance);
        copyrightM.setServiceName(ServiceConstant.COPYRIGHT_DOCUMENT_UPDATE);
        ImakeResultMessage imakeMessage = postMessage(copyrightM, copyrightM.getClass().getName(), "copyright", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((CopyrightM) imakeMessage.getResultListObj().get(0)).getUpdateRecord();
        else
            return null;
    }

    @Override
    public Integer deleteCopyrightDocument(CopyrightDocumentM persistentInstance) {
        // TODO Auto-generated method stub
        CopyrightM copyrightM = new CopyrightM();
        copyrightM.setCopyrightDocument(persistentInstance);
        copyrightM.setServiceName(ServiceConstant.COPYRIGHT_DOCUMENT_DELETE);
        ImakeResultMessage imakeMessage = postMessage(copyrightM, copyrightM.getClass().getName(), "copyright", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((CopyrightM) imakeMessage.getResultListObj().get(0)).getUpdateRecord();
        else
            return null;
    }

    @Override
    public CopyrightDocumentM findCopyrightDocumentById(CopyrightDocumentM id) {
        // TODO Auto-generated method stub
        CopyrightM copyrightM = new CopyrightM();
        copyrightM.setCopyrightDocument(id);
        copyrightM.setServiceName(ServiceConstant.COPYRIGHT_DOCUMENT_FIND_BY_ID);
        ImakeResultMessage imakeMessage = postMessage(copyrightM, copyrightM.getClass().getName(), "copyright", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((CopyrightM) imakeMessage.getResultListObj().get(0)).getCopyrightDocument();
        else
            return null;
    }

    @Override
    public List<RewardDocumentM> listRewardDocument(Integer rewardId) {
        // TODO Auto-generated method stub
        RewardDocumentM rewardDocumentM = new RewardDocumentM();
        rewardDocumentM.setRewardId(rewardId);

        RewardM rewardM = new RewardM();
        rewardM.setRewardDocument(rewardDocumentM);

        rewardM.setServiceName(ServiceConstant.REWARD_DOCUMENT_LIST);
        ImakeResultMessage imakeMessage = postMessage(rewardM, rewardM.getClass().getName(), "reward", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((RewardM) imakeMessage.getResultListObj().get(0)).getRewardDocuments();
        else
            return null;
    }

    @Override
    public Integer saveRewardDocument(RewardDocumentM transientInstance) {
        // TODO Auto-generated method stub
        RewardM rewardM = new RewardM();
        rewardM.setRewardDocument(transientInstance);
        rewardM.setServiceName(ServiceConstant.REWARD_DOCUMENT_SAVE);
        ImakeResultMessage imakeMessage = postMessage(rewardM, rewardM.getClass().getName(), "reward", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((RewardM) imakeMessage.getResultListObj().get(0)).getUpdateRecord();
        else
            return null;
    }

    @Override
    public Integer updateRewardDocument(RewardDocumentM transientInstance) {
        // TODO Auto-generated method stub
        RewardM rewardM = new RewardM();
        rewardM.setRewardDocument(transientInstance);
        rewardM.setServiceName(ServiceConstant.REWARD_DOCUMENT_UPDATE);
        ImakeResultMessage imakeMessage = postMessage(rewardM, rewardM.getClass().getName(), "reward", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((RewardM) imakeMessage.getResultListObj().get(0)).getUpdateRecord();
        else
            return null;
    }

    @Override
    public Integer deleteRewardDocument(RewardDocumentM persistentInstance) {
        // TODO Auto-generated method stub
        RewardM rewardM = new RewardM();
        rewardM.setRewardDocument(persistentInstance);
        rewardM.setServiceName(ServiceConstant.REWARD_DOCUMENT_DELETE);
        ImakeResultMessage imakeMessage = postMessage(rewardM, rewardM.getClass().getName(), "reward", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((RewardM) imakeMessage.getResultListObj().get(0)).getUpdateRecord();
        else
            return null;
    }

    @Override
    public RewardDocumentM findRewardDocumentById(RewardDocumentM id) {
        // TODO Auto-generated method stub
        RewardM rewardM = new RewardM();
        rewardM.setRewardDocument(id);
        rewardM.setServiceName(ServiceConstant.REWARD_DOCUMENT_FIND_BY_ID);
        ImakeResultMessage imakeMessage = postMessage(rewardM, rewardM.getClass().getName(), "reward", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((RewardM) imakeMessage.getResultListObj().get(0)).getRewardDocument();
        else
            return null;
    }

    @Override
    public Integer updateFlagResearchProjectM(
            ResearchProjectM pesearchProject) {
        // TODO Auto-generated method stub
        pesearchProject.setServiceName(ServiceConstant.RESEARCH_PROJECT_UPDATE_FLAG);
        ImakeResultMessage imakeMessage = postMessage(pesearchProject, pesearchProject.getClass().getName(), "researchProject", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((ResearchProjectM) imakeMessage.getResultListObj().get(0)).getUpdateRecord();
        else
            return null;
    }

    @Override
    public Integer updateFlagJournalPaperM(JournalPaperM journalPaper) {
        // TODO Auto-generated method stub
        journalPaper.setServiceName(ServiceConstant.JOURNAL_PAPERS_UPDATE_FLAG);
        ImakeResultMessage imakeMessage = postMessage(journalPaper, journalPaper.getClass().getName(), "journalPapers", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((JournalPaperM) imakeMessage.getResultListObj().get(0)).getUpdateRecord();
        else
            return null;
    }

    @Override
    public Integer updateFlagCopyright(CopyrightM copyright) {
        // TODO Auto-generated method stub
        copyright.setServiceName(ServiceConstant.COPYRIGHT_UPDATE_FLAG);
        ImakeResultMessage imakeMessage = postMessage(copyright, copyright.getClass().getName(), "copyright", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((CopyrightM) imakeMessage.getResultListObj().get(0)).getUpdateRecord();
        else
            return null;
    }

    @Override
    public Integer updateFlagReward(RewardM reward) {
        // TODO Auto-generated method stub
        reward.setServiceName(ServiceConstant.REWARD_UPDATE_FLAG);
        ImakeResultMessage imakeMessage = postMessage(reward, reward.getClass().getName(), "reward", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((RewardM) imakeMessage.getResultListObj().get(0)).getUpdateRecord();
        else
            return null;
    }

    @Override
    public Integer updateFlagPatent(PatentM patent) {
        // TODO Auto-generated method stub
        patent.setServiceName(ServiceConstant.PATENT_UPDATE_FLAG);
        ImakeResultMessage imakeMessage = postMessage(patent, patent.getClass().getName(), "patent", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((PatentM) imakeMessage.getResultListObj().get(0)).getUpdateRecord();
        else
            return null;
    }

    @Override
    public Integer deleteItemsUtilizationTypeM(String[] utilizationTypeId) {
        // TODO Auto-generated method stub
        UtilizationTypeM utilizationTypeM = new UtilizationTypeM();
        utilizationTypeM.setIds(utilizationTypeId);
        utilizationTypeM.setServiceName(ServiceConstant.UTILIZATION_TYPE_ITEMS_DELETE);
        ImakeResultMessage imakeMessage = postMessage(utilizationTypeM, utilizationTypeM.getClass().getName(), "utilizationType", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((UtilizationTypeM) imakeMessage.getResultListObj().get(0)).getUpdateRecord();
        else
            return null;
    }

    @Override
    public Integer deleteItemsPublishTypeM(String[] researchGroupId) {
        // TODO Auto-generated method stub
        PublishTypeM publishTypeM = new PublishTypeM();
        publishTypeM.setIds(researchGroupId);
        publishTypeM.setServiceName(ServiceConstant.PUBLISH_TYPE_ITEMS_DELETE);
        ImakeResultMessage imakeMessage = postMessage(publishTypeM, publishTypeM.getClass().getName(), "publishType", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((PublishTypeM) imakeMessage.getResultListObj().get(0)).getUpdateRecord();
        else
            return null;
    }

    @Override
    public Integer deleteItemsPositionM(String[] researchGroupId) {
        // TODO Auto-generated method stub
        PositionM positionM = new PositionM();
        positionM.setIds(researchGroupId);
        positionM.setServiceName(ServiceConstant.POSITION_ITEMS_DELETE);
        ImakeResultMessage imakeMessage = postMessage(positionM, positionM.getClass().getName(), "position", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((PositionM) imakeMessage.getResultListObj().get(0)).getUpdateRecord();
        else
            return null;
    }

    @Override
    public Integer deleteItemsOrganizationM(String[] researchGroupId) {
        // TODO Auto-generated method stub
        OrganizationM organizationM = new OrganizationM();
        organizationM.setIds(researchGroupId);
        organizationM.setServiceName(ServiceConstant.ORGANIZATION_ITEMS_DELETE);
        ImakeResultMessage imakeMessage = postMessage(organizationM, organizationM.getClass().getName(), "organization", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((OrganizationM) imakeMessage.getResultListObj().get(0)).getUpdateRecord();
        else
            return null;
    }

    @Override
    public Integer deleteItemsCountryM(String[] researchGroupId) {
        // TODO Auto-generated method stub
        CountryM countryM = new CountryM();
        countryM.setIds(researchGroupId);
        countryM.setServiceName(ServiceConstant.COUNTRY_ITEMS_DELETE);
        ImakeResultMessage imakeMessage = postMessage(countryM, countryM.getClass().getName(), "country", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((CountryM) imakeMessage.getResultListObj().get(0)).getUpdateRecord();
        else
            return null;
    }

    @Override
    public Integer deleteItemsWorkTypeM(String[] researchGroupId) {
        // TODO Auto-generated method stub
        WorkTypeM workTypeM = new WorkTypeM();
        workTypeM.setIds(researchGroupId);
        workTypeM.setServiceName(ServiceConstant.WORK_TYPE_ITEMS_DELETE);
        ImakeResultMessage imakeMessage = postMessage(workTypeM, workTypeM.getClass().getName(), "workType", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((WorkTypeM) imakeMessage.getResultListObj().get(0)).getUpdateRecord();
        else
            return null;
    }

    @Override
    public Integer deleteItemsJournalPapersTypeM(String[] researchGroupId) {
        // TODO Auto-generated method stub
        JournalPapersTypeM journalPapersTypeM = new JournalPapersTypeM();
        journalPapersTypeM.setIds(researchGroupId);
        journalPapersTypeM.setServiceName(ServiceConstant.JOURNAL_PAPERS_TYPE_ITEMS_DELETE);
        ImakeResultMessage imakeMessage = postMessage(journalPapersTypeM, journalPapersTypeM.getClass().getName(), "journalPapersType", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((JournalPapersTypeM) imakeMessage.getResultListObj().get(0)).getUpdateRecord();
        else
            return null;
    }

    @Override
    public Integer deleteItemsFundingResourceTypeM(String[] researchGroupId) {
        // TODO Auto-generated method stub
        FundingResourceTypeM fundingResourceTypeM = new FundingResourceTypeM();
        fundingResourceTypeM.setIds(researchGroupId);
        fundingResourceTypeM.setServiceName(ServiceConstant.FUNDING_RESOURCES_TYPE_ITEMS_DELETE);
        ImakeResultMessage imakeMessage = postMessage(fundingResourceTypeM, fundingResourceTypeM.getClass().getName(), "fundingResourcesType", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((FundingResourceTypeM) imakeMessage.getResultListObj().get(0)).getUpdateRecord();
        else
            return null;
    }

    @Override
    public Integer deleteItemsResearcherM(String[] researchGroupId) {
        // TODO Auto-generated method stub
        ResearcherM researcherM = new ResearcherM();
        researcherM.setIds(researchGroupId);
        researcherM.setServiceName(ServiceConstant.RESEARCHER_GROUP_ITEMS_DELETE);
        ImakeResultMessage imakeMessage = postMessage(researcherM, researcherM.getClass().getName(), "researcher", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((ResearcherM) imakeMessage.getResultListObj().get(0)).getUpdateRecord();
        else
            return null;
    }

    @Override
    public Integer deleteItemsResearcherGroupM(String[] researchGroupId) {
        // TODO Auto-generated method stub
        ResearcherGroupM researcherGroupM = new ResearcherGroupM();
        researcherGroupM.setIds(researchGroupId);
        researcherGroupM.setServiceName(ServiceConstant.RESEARCHER_GROUP_ITEMS_DELETE);
        ImakeResultMessage imakeMessage = postMessage(researcherGroupM, researcherGroupM.getClass().getName(), "researcherGroup", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((ResearcherGroupM) imakeMessage.getResultListObj().get(0)).getUpdateRecord();
        else
            return null;
    }

    @Override
    public Integer deleteItemsPublishLevelM(String[] researchGroupId) {
        // TODO Auto-generated method stub
        PublishLevelM publishLevelM = new PublishLevelM();
        publishLevelM.setIds(researchGroupId);
        publishLevelM.setServiceName(ServiceConstant.PUBLISH_LEVEL_ITEMS_DELETE);
        ImakeResultMessage imakeMessage = postMessage(publishLevelM, publishLevelM.getClass().getName(), "publishLevel", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((PublishLevelM) imakeMessage.getResultListObj().get(0)).getUpdateRecord();
        else
            return null;
    }

    @Override
    public Integer deleteItemsOrganizationExtM(String[] researchGroupId) {
        // TODO Auto-generated method stub
        OrganizationExtM organizationExtM = new OrganizationExtM();
        organizationExtM.setIds(researchGroupId);
        organizationExtM.setServiceName(ServiceConstant.ORGANIZATION_EXT_ITEMS_DELETE);
        ImakeResultMessage imakeMessage = postMessage(organizationExtM, organizationExtM.getClass().getName(), "organizationExt", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((OrganizationExtM) imakeMessage.getResultListObj().get(0)).getUpdateRecord();
        else
            return null;
    }

    @Override
    public Integer deleteItemsFundingResourceM(String[] researchGroupId) {
        // TODO Auto-generated method stub
        FundingResourceM fundingResourceM = new FundingResourceM();
        fundingResourceM.setIds(researchGroupId);
        fundingResourceM.setServiceName(ServiceConstant.FUNDING_RESOURCES_ITEMS_DELETE);
        ImakeResultMessage imakeMessage = postMessage(fundingResourceM, fundingResourceM.getClass().getName(), "fundingResources", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((FundingResourceM) imakeMessage.getResultListObj().get(0)).getUpdateRecord();
        else
            return null;

    }

    @Override
    public Integer deleteItemsResearchProjectM(String[] researchGroupId) {
        // TODO Auto-generated method stub
        ResearchProjectM researchProjectM = new ResearchProjectM();
        researchProjectM.setIds(researchGroupId);
        researchProjectM.setServiceName(ServiceConstant.RESEARCH_PROJECT_ITEMS_DELETE);
        ImakeResultMessage imakeMessage = postMessage(researchProjectM, researchProjectM.getClass().getName(), "researchProject", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((ResearchProjectM) imakeMessage.getResultListObj().get(0)).getUpdateRecord();
        else
            return null;
    }

    @Override
    public Integer deleteItemsJournalPaperM(Integer type, String[] researchGroupId) {
        // TODO Auto-generated method stub
        JournalPaperM journalPaperM = new JournalPaperM();
        journalPaperM.setIds(researchGroupId);
        journalPaperM.setType(type);
        journalPaperM.setServiceName(ServiceConstant.JOURNAL_PAPERS_ITEMS_DELETE);
        ImakeResultMessage imakeMessage = postMessage(journalPaperM, journalPaperM.getClass().getName(), "journalPapers", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((JournalPaperM) imakeMessage.getResultListObj().get(0)).getUpdateRecord();
        else
            return null;
    }

    @Override
    public Integer deleteItemsUtilizationM(String[] researchGroupId) {
        // TODO Auto-generated method stub
        UtilizationM utilizationM = new UtilizationM();
        utilizationM.setIds(researchGroupId);
        utilizationM.setServiceName(ServiceConstant.UTILIZATION_ITEMS_DELETE);
        ImakeResultMessage imakeMessage = postMessage(utilizationM, utilizationM.getClass().getName(), "utilization", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((UtilizationM) imakeMessage.getResultListObj().get(0)).getUpdateRecord();
        else
            return null;
    }

    @Override
    public Integer deleteItemsCopyrightM(String[] researchGroupId) {
        // TODO Auto-generated method stub
        CopyrightM copyrightM = new CopyrightM();
        copyrightM.setIds(researchGroupId);
        copyrightM.setServiceName(ServiceConstant.COPYRIGHT_ITEMS_DELETE);
        ImakeResultMessage imakeMessage = postMessage(copyrightM, copyrightM.getClass().getName(), "copyright", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((CopyrightM) imakeMessage.getResultListObj().get(0)).getUpdateRecord();
        else
            return null;
    }

    @Override
    public Integer deleteItemsRewardM(String[] researchGroupId) {
        // TODO Auto-generated method stub
        RewardM rewardM = new RewardM();
        rewardM.setIds(researchGroupId);
        rewardM.setServiceName(ServiceConstant.REWARD_ITEMS_DELETE);
        ImakeResultMessage imakeMessage = postMessage(rewardM, rewardM.getClass().getName(), "reward", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((RewardM) imakeMessage.getResultListObj().get(0)).getUpdateRecord();
        else
            return null;
    }

    @Override
    public Integer deleteItemsPatentM(String[] researchGroupId) {
        // TODO Auto-generated method stub
        PatentM patentM = new PatentM();
        patentM.setIds(researchGroupId);
        patentM.setServiceName(ServiceConstant.PATENT_ITEMS_DELETE);
        ImakeResultMessage imakeMessage = postMessage(patentM, patentM.getClass().getName(), "patent", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((PatentM) imakeMessage.getResultListObj().get(0)).getUpdateRecord();
        else
            return null;
    }

    @Override
    public Integer deleteItemsTitleM(String[] researchGroupId) {
        // TODO Auto-generated method stub
        TitleM titleM = new TitleM();
        titleM.setIds(researchGroupId);
        titleM.setServiceName(ServiceConstant.TITLE_ITEMS_DELETE);
        ImakeResultMessage imakeMessage = postMessage(titleM, titleM.getClass().getName(), "title", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((TitleM) imakeMessage.getResultListObj().get(0)).getUpdateRecord();
        else
            return null;
    }

    @Override
    public Integer updateFlagUtilization(UtilizationM persistentInstance) {
        // TODO Auto-generated method stub
        persistentInstance.setServiceName(ServiceConstant.UTILIZATION_UPDATE_FLAG);
        ImakeResultMessage imakeMessage = postMessage(persistentInstance, persistentInstance.getClass().getName(), "utilization", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((UtilizationM) imakeMessage.getResultListObj().get(0)).getUpdateRecord();
        else
            return null;
    } 
	@Override
	public ImakeResultMessage searchAcademicTitleM(TitleM titleM) {
		// TODO Auto-generated method stub
		 	titleM.setServiceName(ServiceConstant.ACADEMIC_TITLE_SEARCH);
	        return postMessage(titleM, titleM.getClass().getName(), "title", true);
	}
	
	/*public Integer countJournalPapersWriter(JournalPapersWriterM transientInstance) {
		return researchService.countJournalPapersWriter(transientInstance);	
	}
	public Integer countResearchProjectResearcher(ResearchProjectResearcherM transientInstance) {
		return researchService.countResearchProjectResearcher(transientInstance);	
	}	*/

}
