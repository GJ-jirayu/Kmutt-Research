package th.ac.kmutt.research.repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import th.ac.kmutt.research.constant.ServiceConstant;
import th.ac.kmutt.research.domain.Copyright;
import th.ac.kmutt.research.domain.CopyrightCreator;
import th.ac.kmutt.research.domain.CopyrightCreatorPK;
import th.ac.kmutt.research.domain.CopyrightDocument;
import th.ac.kmutt.research.domain.CopyrightDocumentPK;
import th.ac.kmutt.research.domain.Country;
import th.ac.kmutt.research.domain.DocAssignMapping;
import th.ac.kmutt.research.domain.DocAssignMappingPK;
import th.ac.kmutt.research.domain.DocType;
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
import th.ac.kmutt.research.domain.ResearcherGroupPK;
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
import th.ac.kmutt.research.portal.domain.UserPortal;
import th.ac.kmutt.research.xstream.common.Paging;

@Repository("researchRepository")
@Transactional
public class ResearchRepository {
    private static final String SELECT_QUERY = "select p from JournalPaper p";

    @Autowired
    @PersistenceContext(unitName = "HibernatePersistenceUnit")
    private EntityManager entityManager;

    @Autowired
    @PersistenceContext(unitName = "HibernatePersistenceLiferayUnit")
    private EntityManager portalEntityManager;

    public List<JournalPaper> listJournalPaperAll() {
        // TODO Auto-generated method stub
        Query query = entityManager.createQuery(SELECT_QUERY);
        return query.getResultList();
    }

    public List<UserPortal> listUserPortal() {
        // TODO Auto-generated method stub

        Query query = portalEntityManager.createQuery("select p from UserPortal2 p");//,th.ac.kmutt.research.portal.domain.UserPortal.class);
        List list1 = query.getResultList();
        query = portalEntityManager.createQuery("select p from UserPortal1 p");
        List list2 = query.getResultList();
        return null;
    }

    public UserPortal findUserPortalById(Integer researchGroupId)
            throws DataAccessException {
        // TODO Auto-generated method stub
        return entityManager.find(UserPortal.class, researchGroupId);

    }

    public List searchUserPortal(UserPortal persistentInstance,
                                 Paging pagging, String keySearch) throws DataAccessException {
        // TODO Auto-generated method stub
        StringBuffer sb = new StringBuffer("");
        if (keySearch != null && keySearch.trim().length() > 0) {
            sb.append(" where ( p.emailAddress like '%" + keySearch.trim()
                    + "%' or p.firstName like '%" + keySearch.trim() + "%' "
                    + " or p.screenName like '%" + keySearch.trim() + "%' "
                    + " or p.lastName like '%" + keySearch.trim() + "%' ) ");
        }
        ArrayList transList = new ArrayList();
        Query query = portalEntityManager.createQuery(
                " select p from UserPortal2 p " + sb.toString(),
                UserPortal.class);
        query.setFirstResult((pagging.getPageNo() - 1) * pagging.getPageSize());
        query.setMaxResults(pagging.getPageSize());
        transList.add(query.getResultList());

        query = portalEntityManager
                .createQuery("select count(p) from UserPortal2 p "
                        + sb.toString());
        long count = (Long) query.getSingleResult();
        transList.add(String.valueOf(count));
        return transList;
    }

    public Integer saveResearchGroup(ResearchGroup transientInstance)
            throws DataAccessException {
        // TODO Auto-generated method stub
        Query query = entityManager
                .createQuery("select count(p) from ResearchGroup p where p.groupCode=:groupCode ");
        query.setParameter("groupCode", transientInstance.getGroupCode());
        long count = (Long) query.getSingleResult();
        if (count > 0) {
            return -1;
        } else {
            entityManager.persist(transientInstance);
            return transientInstance.getResearchGroupId();
        }
    }

    public Integer checkUQResearchGroup(ResearchGroup transientInstance)
            throws DataAccessException {
        // TODO Auto-generated method stub
        Query query = entityManager
                .createQuery("select count(1) from ResearchGroup p where p.groupCode=:groupCode ");
        query.setParameter("groupCode", transientInstance.getGroupCode());
        Long count = (Long) query.getSingleResult();
        return count.intValue();
    }

    public Integer updateResearchGroup(ResearchGroup transientInstance)
            throws DataAccessException {
        // TODO Auto-generated method stub
        entityManager.merge(transientInstance);
        return transientInstance.getResearchGroupId();
    }

    public Integer deleteResearchGroup(ResearchGroup persistentInstance)
            throws DataAccessException {
        //{
        // TODO Auto-generated method stub
        int deletedCount = 0;
        //try{
        deletedCount = entityManager.createQuery(
                "delete from ResearchGroup where researchGroupId="
                        + persistentInstance.getResearchGroupId())
                .executeUpdate();
        /*}catch (Exception e) {
            deletedCount=-9;
			
		    Throwable t = e.getCause();
		    while ((t != null) && !(t instanceof ConstraintViolationException)) {
		        t = t.getCause();
		    }
		    if (t instanceof ConstraintViolationException) {
		        // Here you're sure you have a ConstraintViolationException, you can handle it
		    	   deletedCount=-9;
		    	 //  return deletedCount;
		    }
		 }*/

        return deletedCount;
    }

    public ResearchGroup findResearchGroupById(Integer researchGroupId)
            throws DataAccessException {
        // TODO Auto-generated method stub
        return entityManager.find(ResearchGroup.class, researchGroupId);

    }

    public List searchResearchGroup(ResearchGroup persistentInstance,
                                    Paging pagging, String keySearch) throws DataAccessException {
        // TODO Auto-generated method stub
        StringBuffer sb = new StringBuffer("");
        if (keySearch != null && keySearch.trim().length() > 0) {
            sb.append(" where ( p.groupCode like '%" + keySearch.trim()
                    + "%' or p.groupTh like '%" + keySearch.trim() + "%' "
                    + "or p.groupEng like '%" + keySearch.trim() + "%' ) ");
        }
        ArrayList transList = new ArrayList();
        Query query = entityManager.createQuery(
                " select p from ResearchGroup p " + sb.toString(),
                ResearchGroup.class);
        query.setFirstResult((pagging.getPageNo() - 1) * pagging.getPageSize());
        query.setMaxResults(pagging.getPageSize());
        transList.add(query.getResultList());

        query = entityManager
                .createQuery("select count(p) from ResearchGroup p "
                        + sb.toString());
        long count = (Long) query.getSingleResult();
        transList.add(String.valueOf(count));
        return transList;
    }

    public Integer saveUtilizationType(UtilizationType transientInstance)
            throws DataAccessException {
        // TODO Auto-generated method stub
        entityManager.persist(transientInstance);
        return transientInstance.getUtilizationTypeId();
    }

    public Integer updateUtilizationType(UtilizationType transientInstance)
            throws DataAccessException {
        // TODO Auto-generated method stub
        entityManager.merge(transientInstance);
        return transientInstance.getUtilizationTypeId();
    }

    public Integer deleteUtilizationType(UtilizationType persistentInstance)
            throws DataAccessException {
        // TODO Auto-generated method stub
        int deletedCount = entityManager.createQuery(
                "delete from UtilizationType where utilizationTypeId="
                        + persistentInstance.getUtilizationTypeId())
                .executeUpdate();
        return deletedCount;
    }

    public UtilizationType findUtilizationTypeById(Integer utilizationTypeId)
            throws DataAccessException {
        // TODO Auto-generated method stub
        return entityManager.find(UtilizationType.class, utilizationTypeId);
    }

    public Integer checkUQUtilizationType(UtilizationType transientInstance)
            throws DataAccessException {
        // TODO Auto-generated method stub
        Query query = entityManager
                .createQuery("select count(p) from UtilizationType p where p.utilizationCode=:utilizationCode ");
        query.setParameter("utilizationCode", transientInstance.getUtilizationCode());
        Long count = (Long) query.getSingleResult();
        return count.intValue();
    }

    public List searchUtilizationType(UtilizationType persistentInstance,
                                      Paging pagging, String keySearch) throws DataAccessException {
        // TODO Auto-generated method stub
        StringBuffer sb = new StringBuffer("");
        if (keySearch != null && keySearch.trim().length() > 0) {
            sb.append(" where ( p.utilizationCode like '%" + keySearch.trim()
                    + "%'  " + "or p.utilizationName like '%"
                    + keySearch.trim() + "%' ) ");
        }
        ArrayList transList = new ArrayList();
        Query query = entityManager.createQuery(
                " select p from UtilizationType p " + sb.toString(),
                UtilizationType.class);
        query.setFirstResult((pagging.getPageNo() - 1) * pagging.getPageSize());
        query.setMaxResults(pagging.getPageSize());
        transList.add(query.getResultList());

        query = entityManager
                .createQuery("select count(p) from UtilizationType p "
                        + sb.toString());
        long count = (Long) query.getSingleResult();
        transList.add(String.valueOf(count));
        return transList;
    }

    public Integer savePublishType(PublishType transientInstance)
            throws DataAccessException {
        // TODO Auto-generated method stub
        entityManager.persist(transientInstance);
        return transientInstance.getPublishTypeId();
    }

    public Integer updatePublishType(PublishType transientInstance)
            throws DataAccessException {
        // TODO Auto-generated method stub
        entityManager.merge(transientInstance);
        return transientInstance.getPublishTypeId();
    }

    public Integer deletePublishType(PublishType persistentInstance)
            throws DataAccessException {
        // TODO Auto-generated method stub
        int deletedCount = entityManager.createQuery(
                "delete from PublishType where publishTypeId="
                        + persistentInstance.getPublishTypeId())
                .executeUpdate();
        return deletedCount;
    }

    public PublishType findPublishTypeById(Integer publishTypeId)
            throws DataAccessException {
        // TODO Auto-generated method stub
        return entityManager.find(PublishType.class, publishTypeId);
    }

    public Integer checkUQPublishType(PublishType transientInstance)
            throws DataAccessException {
        // TODO Auto-generated method stub
        Query query = entityManager
                .createQuery("select count(p) from PublishType p where p.ptCode=:ptCode ");
        query.setParameter("ptCode", transientInstance.getPtCode());
        Long count = (Long) query.getSingleResult();
        return count.intValue();
    }

    public List searchPublishType(PublishType persistentInstance,
                                  Paging pagging, String keySearch) throws DataAccessException {
        // TODO Auto-generated method stub
        StringBuffer sb = new StringBuffer("");
        if (keySearch != null && keySearch.trim().length() > 0) {
            sb.append(" where ( p.ptCode like '%" + keySearch.trim() + "%'  "
                    + "or p.ptName like '%" + keySearch.trim() + "%' ) ");
        }
        ArrayList transList = new ArrayList();
        Query query = entityManager.createQuery(" select p from PublishType p "
                + sb.toString(), PublishType.class);
        query.setFirstResult((pagging.getPageNo() - 1) * pagging.getPageSize());
        query.setMaxResults(pagging.getPageSize());
        transList.add(query.getResultList());

        query = entityManager.createQuery("select count(p) from PublishType p "
                + sb.toString());
        long count = (Long) query.getSingleResult();
        transList.add(String.valueOf(count));
        return transList;
    }

    public Integer savePosition(Position transientInstance)
            throws DataAccessException {
        // TODO Auto-generated method stub
        entityManager.persist(transientInstance);
        return transientInstance.getPositionId();
    }

    public Integer updatePosition(Position transientInstance)
            throws DataAccessException {
        // TODO Auto-generated method stub
        entityManager.merge(transientInstance);
        return transientInstance.getPositionId();
    }

    public Integer deletePosition(Position persistentInstance)
            throws DataAccessException {
        // TODO Auto-generated method stub
        int deletedCount = entityManager.createQuery(
                "delete from Position where positionId="
                        + persistentInstance.getPositionId()).executeUpdate();
        return deletedCount;
    }

    public Position findPositionById(Integer positionId)
            throws DataAccessException {
        // TODO Auto-generated method stub
        return entityManager.find(Position.class, positionId);
    }

    public Integer checkUQPosition(Position transientInstance)
            throws DataAccessException {
        // TODO Auto-generated method stub
        Query query = entityManager
                .createQuery("select count(p) from Position p where p.positionCode=:positionCode ");
        query.setParameter("positionCode", transientInstance.getPositionCode());
        Long count = (Long) query.getSingleResult();
        return count.intValue();
    }

    public List searchPosition(Position persistentInstance, Paging pagging,
                               String keySearch) throws DataAccessException {
        // TODO Auto-generated method stub
        StringBuffer sb = new StringBuffer("");
        if (keySearch != null && keySearch.trim().length() > 0) {
            sb.append(" where ( p.positionCode like '%" + keySearch.trim()
                    + "%' " + "or p.positionName like '%" + keySearch.trim()
                    + "%' ) ");
        }
        ArrayList transList = new ArrayList();
        Query query = entityManager.createQuery(" select p from Position p "
                + sb.toString(), Position.class);
        query.setFirstResult((pagging.getPageNo() - 1) * pagging.getPageSize());
        query.setMaxResults(pagging.getPageSize());
        transList.add(query.getResultList());

        query = entityManager.createQuery("select count(p) from Position p "
                + sb.toString());
        long count = (Long) query.getSingleResult();
        transList.add(String.valueOf(count));
        return transList;
    }

    public Integer saveOrganization(Organization transientInstance)
            throws DataAccessException {
        // TODO Auto-generated method stub
        entityManager.persist(transientInstance);
        return transientInstance.getOrganizationId();
    }

    public Integer updateOrganization(Organization transientInstance)
            throws DataAccessException {
        // TODO Auto-generated method stub
        entityManager.merge(transientInstance);
        return transientInstance.getOrganizationId();
    }

    public Integer deleteOrganization(Organization persistentInstance)
            throws DataAccessException {
        // TODO Auto-generated method stub
        int deletedCount = entityManager.createQuery(
                "delete from Organization where organizationId="
                        + persistentInstance.getOrganizationId())
                .executeUpdate();
        return deletedCount;
    }

    public Organization findOrganizationById(Integer organizationId)
            throws DataAccessException {
        // TODO Auto-generated method stub
        return entityManager.find(Organization.class, organizationId);
    }

    public List searchOrganization(Organization persistentInstance,
                                   Paging pagging, String keySearch) throws DataAccessException {
        // TODO Auto-generated method stub
        StringBuffer sb = new StringBuffer("");


        if (keySearch != null && keySearch.trim().length() > 0) {
			/*sb.append(" where ( p.orgCampusCode like '%" + keySearch.trim()+ "%' "+
				    " or p.orgDeptCode like '%"+ keySearch.trim() + "%' "+
				    " or p.orgInstitutionCode like '%"+ keySearch.trim() + "%' "+
				    " or p.orgWorkCode like '%"+ keySearch.trim() + "%' "+
					" or p.orgName like '%" + keySearch.trim()+ "%' "+
				     ") ");*/
            //	sb.append(" where ( concat(p.orgCampusCode,p.orgDeptCode,p.orgInstitutionCode,p.orgWorkCode) like '%" + keySearch.trim()+ "%' "+
            sb.append(" where ( concat(p.ORG_CAMPUS_CODE,p.ORG_INSTITUTION_CODE,p.ORG_DEPT_CODE,p.ORG_WORK_CODE) like '%" + keySearch.trim() + "%'  " +

                    " or p.ORG_NAME like '%" + keySearch.trim() + "%' " +
                    ") ");
        }
        ArrayList transList = new ArrayList();
        Query query = entityManager.createNativeQuery(
                " select p.* from ORGANIZATION p " + sb.toString(), Organization.class);
        //Organization.class);
        query.setFirstResult((pagging.getPageNo() - 1) * pagging.getPageSize());
        query.setMaxResults(pagging.getPageSize());
        transList.add(query.getResultList());

        query = entityManager
                .createNativeQuery("select count(*) from ORGANIZATION p "
                        + sb.toString());
        java.math.BigInteger count = (java.math.BigInteger) query.getSingleResult();
        transList.add(String.valueOf(count));
        return transList;
    }

    public Integer saveCountry(Country transientInstance)
            throws DataAccessException {
        // TODO Auto-generated method stub
        entityManager.persist(transientInstance);
        return transientInstance.getCountryId();
    }

    public Integer updateCountry(Country transientInstance)
            throws DataAccessException {
        // TODO Auto-generated method stub
        entityManager.merge(transientInstance);
        return transientInstance.getCountryId();
    }

    public Integer deleteCountry(Country persistentInstance)
            throws DataAccessException {
        // TODO Auto-generated method stub
        int deletedCount = entityManager.createQuery(
                "delete from Country where countryId="
                        + persistentInstance.getCountryId()).executeUpdate();
        return deletedCount;
    }

    public Country findCountryById(Integer countryId)
            throws DataAccessException {
        // TODO Auto-generated method stub
        return entityManager.find(Country.class, countryId);
    }

    public Integer checkUQCountry(Country transientInstance)
            throws DataAccessException {
        // TODO Auto-generated method stub
        Query query = entityManager
                .createQuery("select count(p) from Country p where p.countryCode=:countryCode ");
        query.setParameter("countryCode", transientInstance.getCountryCode());
        Long count = (Long) query.getSingleResult();
        return count.intValue();
    }

    public List searchCountry(Country persistentInstance, Paging pagging,
                              String keySearch) throws DataAccessException {
        // TODO Auto-generated method stub
        StringBuffer sb = new StringBuffer("");
        if (keySearch != null && keySearch.trim().length() > 0) {
            sb.append(" where ( p.countryCode like '%" + keySearch.trim()
                    + "%' or p.countryNameEng like '%" + keySearch.trim()
                    + "%' " + "or p.countryNameTh like '%" + keySearch.trim()
                    + "%' ) ");
        }
        ArrayList transList = new ArrayList();
        Query query = entityManager.createQuery(" select p from Country p "
                + sb.toString(), Country.class);
        query.setFirstResult((pagging.getPageNo() - 1) * pagging.getPageSize());
        query.setMaxResults(pagging.getPageSize());
        transList.add(query.getResultList());

        query = entityManager.createQuery("select count(p) from Country p "
                + sb.toString());
        long count = (Long) query.getSingleResult();
        transList.add(String.valueOf(count));
        return transList;
    }

    public Integer saveWorkType(WorkType transientInstance)
            throws DataAccessException {
        // TODO Auto-generated method stub
        entityManager.persist(transientInstance);
        return transientInstance.getWorkTypeId();
    }

    public Integer updateWorkType(WorkType transientInstance)
            throws DataAccessException {
        // TODO Auto-generated method stub
        entityManager.merge(transientInstance);
        return transientInstance.getWorkTypeId();
    }

    public Integer deleteWorkType(WorkType persistentInstance)
            throws DataAccessException {
        // TODO Auto-generated method stub
        int deletedCount = entityManager.createQuery(
                "delete from WorkType where workTypeId="
                        + persistentInstance.getWorkTypeId()).executeUpdate();
        return deletedCount;
    }

    public WorkType findWorkTypeById(Integer workTypeId)
            throws DataAccessException {
        // TODO Auto-generated method stub
        return entityManager.find(WorkType.class, workTypeId);
    }

    public Integer checkUQWorkType(WorkType transientInstance)
            throws DataAccessException {
        // TODO Auto-generated method stub
        Query query = entityManager
                .createQuery("select count(p) from WorkType p where p.wtCode=:wtCode ");
        query.setParameter("wtCode", transientInstance.getWtCode());
        Long count = (Long) query.getSingleResult();
        return count.intValue();
    }

    public List searchWorkType(WorkType persistentInstance, Paging pagging,
                               String keySearch) throws DataAccessException {
        // TODO Auto-generated method stub
        StringBuffer sb = new StringBuffer("");
        if (keySearch != null && keySearch.trim().length() > 0) {
            sb.append(" where ( p.wtCode like '%" + keySearch.trim() + "%'  "
                    + "or p.wtName like '%" + keySearch.trim() + "%' ) ");
        }
        ArrayList transList = new ArrayList();
        Query query = entityManager.createQuery(" select p from WorkType p "
                + sb.toString(), WorkType.class);
        query.setFirstResult((pagging.getPageNo() - 1) * pagging.getPageSize());
        query.setMaxResults(pagging.getPageSize());
        transList.add(query.getResultList());

        query = entityManager.createQuery("select count(p) from WorkType p "
                + sb.toString());
        long count = (Long) query.getSingleResult();
        transList.add(String.valueOf(count));
        return transList;
    }

    public Integer saveJournalPapersType(JournalPapersType transientInstance)
            throws DataAccessException {
        // TODO Auto-generated method stub
        entityManager.persist(transientInstance);
        return transientInstance.getJournalPapersTypeId();
    }

    public Integer updateJournalPapersType(JournalPapersType transientInstance)
            throws DataAccessException {
        // TODO Auto-generated method stub
        entityManager.merge(transientInstance);
        return transientInstance.getJournalPapersTypeId();
    }

    public Integer deleteJournalPapersType(JournalPapersType persistentInstance)
            throws DataAccessException {
        // TODO Auto-generated method stub
        int deletedCount = entityManager.createQuery(
                "delete from JournalPapersType where journalPapersTypeId="
                        + persistentInstance.getJournalPapersTypeId())
                .executeUpdate();
        return deletedCount;
    }

    public JournalPapersType findJournalPapersTypeById(
            Integer journalPapersTypeId) throws DataAccessException {
        // TODO Auto-generated method stub
        return entityManager.find(JournalPapersType.class, journalPapersTypeId);
    }

    public Integer checkUQJournalPapersType(JournalPapersType transientInstance)
            throws DataAccessException {
        // TODO Auto-generated method stub
        Query query = entityManager
                .createQuery("select count(p) from JournalPapersType p where p.jptCode=:jptCode ");
        query.setParameter("jptCode", transientInstance.getJptCode());
        Long count = (Long) query.getSingleResult();
        return count.intValue();
    }

    public List searchJournalPapersType(JournalPapersType persistentInstance,
                                        Paging pagging, String keySearch) throws DataAccessException {
        // TODO Auto-generated method stub
        StringBuffer sb = new StringBuffer("");
        if (keySearch != null && keySearch.trim().length() > 0) {
            sb.append(" where ( p.jptCode like '%" + keySearch.trim() + "%' "
                    + "or p.jptName like '%" + keySearch.trim() + "%' ) ");
        }
        ArrayList transList = new ArrayList();
        Query query = entityManager.createQuery(
                " select p from JournalPapersType p " + sb.toString(),
                JournalPapersType.class);
        query.setFirstResult((pagging.getPageNo() - 1) * pagging.getPageSize());
        query.setMaxResults(pagging.getPageSize());
        transList.add(query.getResultList());

        query = entityManager
                .createQuery("select count(p) from JournalPapersType p "
                        + sb.toString());
        long count = (Long) query.getSingleResult();
        transList.add(String.valueOf(count));
        return transList;
    }

    public Integer saveFundingResourceType(FundingResourceType transientInstance)
            throws DataAccessException {
        // TODO Auto-generated method stub
        entityManager.persist(transientInstance);
        return transientInstance.getFundingResourceTypeId();
    }

    public Integer updateFundingResourceType(
            FundingResourceType transientInstance) throws DataAccessException {
        // TODO Auto-generated method stub
        entityManager.merge(transientInstance);
        return transientInstance.getFundingResourceTypeId();
    }

    public Integer deleteFundingResourceType(
            FundingResourceType persistentInstance) throws DataAccessException {
        // TODO Auto-generated method stub
        int deletedCount = entityManager.createQuery(
                "delete from FundingResourceType where fundingResourceTypeId="
                        + persistentInstance.getFundingResourceTypeId())
                .executeUpdate();
        return deletedCount;
    }

    public FundingResourceType findFundingResourceTypeById(
            Integer fundingResourceTypeId) throws DataAccessException {
        // TODO Auto-generated method stub
        return entityManager.find(FundingResourceType.class,
                fundingResourceTypeId);
    }

    public Integer checkUQFundingResourceType(FundingResourceType transientInstance)
            throws DataAccessException {
        // TODO Auto-generated method stub
        Query query = entityManager
                .createQuery("select count(p) from FundingResourceType p where p.frtCode=:frtCode ");
        query.setParameter("frtCode", transientInstance.getFrtCode());
        Long count = (Long) query.getSingleResult();
        return count.intValue();
    }

    public List searchFundingResourceType(
            FundingResourceType persistentInstance, Paging pagging,
            String keySearch) throws DataAccessException {
        // TODO Auto-generated method stub
        StringBuffer sb = new StringBuffer("");
        if (keySearch != null && keySearch.trim().length() > 0) {
            sb.append(" where ( p.frtCode like '%" + keySearch.trim()
                    + "%' or p.frtName like '%" + keySearch.trim() + "%' "
                    + "or p.frtSource like '%" + keySearch.trim() + "%' ) ");
        }
        ArrayList transList = new ArrayList();
        Query query = entityManager.createQuery(
                " select p from FundingResourceType p " + sb.toString(),
                FundingResourceType.class);
        query.setFirstResult((pagging.getPageNo() - 1) * pagging.getPageSize());
        query.setMaxResults(pagging.getPageSize());
        transList.add(query.getResultList());

        query = entityManager
                .createQuery("select count(p) from FundingResourceType p "
                        + sb.toString());
        long count = (Long) query.getSingleResult();
        transList.add(String.valueOf(count));
        return transList;
    }

    public Integer saveResearcher(Researcher transientInstance)
            throws DataAccessException {
        // TODO Auto-generated method stub
        entityManager.persist(transientInstance);
        return transientInstance.getResearcherId();
    }

    public Integer updateResearcher(Researcher transientInstance)
            throws DataAccessException {
        // TODO Auto-generated method stub
        entityManager.merge(transientInstance);
        return transientInstance.getResearcherId();
    }

    public Integer deleteResearcher(Researcher persistentInstance)
            throws DataAccessException {
        // TODO Auto-generated method stub
        int deletedCount = entityManager.createQuery(
                "delete from Researcher where researcherId="
                        + persistentInstance.getResearcherId()).executeUpdate();
        return deletedCount;
    }

    public Researcher findResearcherById(Integer researcherId)
            throws DataAccessException {
        // TODO Auto-generated method stub
        return entityManager.find(Researcher.class, researcherId);
    }

    public List searchResearcher(Researcher persistentInstance, Paging pagging,
                                 String keySearch) throws DataAccessException {
        // TODO Auto-generated method stub
        StringBuffer sb = new StringBuffer("");
        if (keySearch != null && keySearch.trim().length() > 0) {
            sb.append(" where ( p.researcherCode like '%" + keySearch.trim()
                    + "%' or p.nameThai like '%" + keySearch.trim() + "%' "
                    + "or p.nameEng like '%" + keySearch.trim() + "%' ) ");
        }
        ArrayList transList = new ArrayList();
        Query query = entityManager.createQuery(" select p from Researcher p "
                + sb.toString(), Researcher.class).setFirstResult(0).setMaxResults(10);


        query.setFirstResult((pagging.getPageNo() - 1) * pagging.getPageSize());
        query.setMaxResults(pagging.getPageSize());
        transList.add(query.getResultList());

        query = entityManager.createQuery("select count(p) from Researcher p "
                + sb.toString());
        long count = (Long) query.getSingleResult();
        transList.add(String.valueOf(count));
        return transList;
    }

    public Integer saveResearcherGroup(
            ResearcherGroup transientInstance)
            throws DataAccessException {
        // TODO Auto-generated method stub

        Query query = entityManager.createQuery("select count(p) from ResearcherGroup p " +
                " where id.researchGroupId=" + transientInstance.getId().getResearchGroupId()

                + " and id.researcherId='" + transientInstance.getId().getResearcherId() + "'");

        java.lang.Long count = (java.lang.Long) query.getSingleResult();
        if (!(count != null && count > 0))
            entityManager.persist(transientInstance);
        return transientInstance.getId().getResearcherId();
    }

    public Integer updateResearcherGroup(
            ResearcherGroup transientInstance)
            throws DataAccessException {
        // TODO Auto-generated method stub
        entityManager.merge(transientInstance);
        return transientInstance.getId().getResearcherId();
    }

    public Integer deleteResearcherGroup(
            ResearcherGroup persistentInstance)
            throws DataAccessException {
        // TODO Auto-generated method stub
        int deletedCount = entityManager.createQuery(
                "delete from ResearcherGroup where id.researchGroupId="
                        + persistentInstance.getId().getResearchGroupId()
                        + " and id.researcherId="
                        + persistentInstance.getId().getResearcherId())
                .executeUpdate();
        return deletedCount;
    }

    public ResearcherGroup findResearcherGroupById(
            Integer researchGroupId, Integer researcherId)
            throws DataAccessException {
        // TODO Auto-generated method stub
        ResearcherGroupPK pk = new ResearcherGroupPK();
        pk.setResearcherId(researcherId);
        pk.setResearchGroupId(researchGroupId);
        return entityManager.find(ResearcherGroup.class, pk);
    }

    public List searchResearcherGroup(
            ResearcherGroup persistentInstance, Paging pagging,
            String keySearch) throws DataAccessException {
        // TODO Auto-generated method stub
        StringBuffer sb = new StringBuffer("");
		/*
		 * if(keySearch!=null && keySearch.trim().length()>0){
		 * sb.append(" where ( p.groupCode like '%"
		 * +keySearch.trim()+"%' or p.groupTh like '%"+keySearch.trim()+"%' " +
		 * "or p.groupTh like '%"+keySearch.trim()+"%' ) ") ; }
		 */
        ArrayList transList = new ArrayList();
        Query query = entityManager.createQuery(
                " select p from ResearcherGroup p " + sb.toString(),
                ResearcherGroup.class);
        query.setFirstResult((pagging.getPageNo() - 1) * pagging.getPageSize());
        query.setMaxResults(pagging.getPageSize());
        transList.add(query.getResultList());

        query = entityManager
                .createQuery("select count(p) from ResearcherGroup p "
                        + sb.toString());
        long count = (Long) query.getSingleResult();
        transList.add(String.valueOf(count));
        return transList;
    }

    public Integer savePublishLevel(PublishLevel transientInstance)
            throws DataAccessException {
        // TODO Auto-generated method stub
        entityManager.persist(transientInstance);
        return transientInstance.getPublishLevelId();
    }

    public Integer updatePublishLevel(PublishLevel transientInstance)
            throws DataAccessException {
        // TODO Auto-generated method stub
        entityManager.merge(transientInstance);
        return transientInstance.getPublishLevelId();
    }

    public Integer deletePublishLevel(PublishLevel persistentInstance)
            throws DataAccessException {
        // TODO Auto-generated method stub
        int deletedCount = entityManager.createQuery(
                "delete from PublishLevel where publishLevelId="
                        + persistentInstance.getPublishLevelId())
                .executeUpdate();
        return deletedCount;
    }

    public PublishLevel findPublishLevelById(Integer publishLevelId)
            throws DataAccessException {
        // TODO Auto-generated method stub
        return entityManager.find(PublishLevel.class, publishLevelId);
    }

    public Integer checkUQPublishLevel(PublishLevel transientInstance)
            throws DataAccessException {
        // TODO Auto-generated method stub
        Query query = entityManager
                .createQuery("select count(p) from PublishLevel p where p.plCode=:plCode ");
        query.setParameter("plCode", transientInstance.getPlCode());
        Long count = (Long) query.getSingleResult();
        return count.intValue();
    }

    public List searchPublishLevel(PublishLevel persistentInstance,
                                   Paging pagging, String keySearch) throws DataAccessException {
        // TODO Auto-generated method stub
        StringBuffer sb = new StringBuffer("");
        if (keySearch != null && keySearch.trim().length() > 0) {
            sb.append(" where ( p.plCode like '%" + keySearch.trim() + "%' "
                    + "or p.plName like '%" + keySearch.trim() + "%' ) ");
        }
        ArrayList transList = new ArrayList();
        Query query = entityManager.createQuery(
                " select p from PublishLevel p " + sb.toString(),
                PublishLevel.class);
        query.setFirstResult((pagging.getPageNo() - 1) * pagging.getPageSize());
        query.setMaxResults(pagging.getPageSize());
        transList.add(query.getResultList());

        query = entityManager.createQuery("select count(p) from PublishLevel p "
                + sb.toString());
        long count = (Long) query.getSingleResult();
        transList.add(String.valueOf(count));
        return transList;
    }

    public Integer saveOrganizationExt(OrganizationExt transientInstance)
            throws DataAccessException {
        // TODO Auto-generated method stub
        entityManager.persist(transientInstance);
        return transientInstance.getOrganizationExtId();
    }

    public Integer updateOrganizationExt(OrganizationExt transientInstance)
            throws DataAccessException {
        // TODO Auto-generated method stub
        entityManager.merge(transientInstance);
        return transientInstance.getOrganizationExtId();
    }

    public Integer deleteOrganizationExt(OrganizationExt persistentInstance)
            throws DataAccessException {
        // TODO Auto-generated method stub
        int deletedCount = entityManager.createQuery(
                "delete from OrganizationExt where organizationExtId="
                        + persistentInstance.getOrganizationExtId())
                .executeUpdate();
        return deletedCount;
    }

    public OrganizationExt findOrganizationExtById(Integer organizationExtId)
            throws DataAccessException {
        // TODO Auto-generated method stub
        return entityManager.find(OrganizationExt.class, organizationExtId);
    }

    public Integer checkUQOrganizationExt(OrganizationExt transientInstance)
            throws DataAccessException {
        // TODO Auto-generated method stub
        Query query = entityManager
                .createQuery("select count(p) from OrganizationExt p where p.organizationExtCode=:organizationExtCode ");
        query.setParameter("organizationExtCode", transientInstance.getOrganizationExtCode());
        Long count = (Long) query.getSingleResult();
        return count.intValue();
    }

    public List searchOrganizationExt(OrganizationExt persistentInstance,
                                      Paging pagging, String keySearch) throws DataAccessException {
        // TODO Auto-generated method stub
        StringBuffer sb = new StringBuffer("");
        if (keySearch != null && keySearch.trim().length() > 0) {
            sb.append(" where ( p.organizationExtCode like '%" + keySearch.trim()
                    + "%' or p.organizationExtName like '%" + keySearch.trim()
                    + "%' ) ");
        }
        ArrayList transList = new ArrayList();
        Query query = entityManager.createQuery(
                " select p from OrganizationExt p " + sb.toString(),
                OrganizationExt.class);
        query.setFirstResult((pagging.getPageNo() - 1) * pagging.getPageSize());
        query.setMaxResults(pagging.getPageSize());
        transList.add(query.getResultList());

        query = entityManager
                .createQuery("select count(p) from OrganizationExt p "
                        + sb.toString());
        long count = (Long) query.getSingleResult();
        transList.add(String.valueOf(count));
        return transList;
    }

    public Integer saveFundingResource(FundingResource transientInstance)
            throws DataAccessException {
        // TODO Auto-generated method stub
        entityManager.persist(transientInstance);
        return transientInstance.getFundingResourceId();
    }

    public Integer updateFundingResource(FundingResource transientInstance)
            throws DataAccessException {
        // TODO Auto-generated method stub
        entityManager.merge(transientInstance);
        return transientInstance.getFundingResourceId();
    }

    public Integer deleteFundingResource(FundingResource persistentInstance)
            throws DataAccessException {
        // TODO Auto-generated method stub
        int deletedCount = entityManager.createQuery(
                "delete from FundingResource where fundingResourceId="
                        + persistentInstance.getFundingResourceId())
                .executeUpdate();
        return deletedCount;
    }

    public FundingResource findFundingResourceById(Integer fundingResourceId)
            throws DataAccessException {
        // TODO Auto-generated method stub
        FundingResource resource = entityManager.find(FundingResource.class, fundingResourceId);
        return resource;
    }

    public Integer checkUQFundingResource(FundingResource transientInstance)
            throws DataAccessException {
        // TODO Auto-generated method stub
        Query query = entityManager
                .createQuery("select count(p) from FundingResource p where p.fundingResourceCode=:fundingResourceCode ");
        query.setParameter("fundingResourceCode", transientInstance.getFundingResourceCode());
        Long count = (Long) query.getSingleResult();
        return count.intValue();
    }

    public List searchFundingResource(FundingResource persistentInstance,
                                      Paging pagging, String keySearch) throws DataAccessException {
        // TODO Auto-generated method stub
        StringBuffer sb = new StringBuffer("");
        if (keySearch != null && keySearch.trim().length() > 0) {
            sb.append(" where ( p.frNameEng like '%" + keySearch.trim()
                    + "%' or p.frNameThai like '%" + keySearch.trim() + "%' "
                    + " or p.fundingResourceCode like '%" + keySearch.trim() + "%' "
                    + "or p.frShortName like '%" + keySearch.trim() + "%' ) ");
        }
        ArrayList transList = new ArrayList();
        Query query = entityManager.createQuery(
                " select p from FundingResource p " + sb.toString(),
                FundingResource.class);
        query.setFirstResult((pagging.getPageNo() - 1) * pagging.getPageSize());
        query.setMaxResults(pagging.getPageSize());
        transList.add(query.getResultList());

        query = entityManager
                .createQuery("select count(p) from FundingResource p "
                        + sb.toString());
        long count = (Long) query.getSingleResult();
        transList.add(String.valueOf(count));
        return transList;
    }

    public Integer saveResearchProject(ResearchProject transientInstance) {
        // TODO Auto-generated method stub
        entityManager.persist(transientInstance);
        return transientInstance.getResearchProjectId();
    }

    public Integer updateResearchProject(ResearchProject transientInstance) {
        // TODO Auto-generated method stub
        entityManager.merge(transientInstance);
        return transientInstance.getResearchProjectId();
    }

    public Integer deleteResearchProject(ResearchProject persistentInstance) {
        // TODO Auto-generated method stub
        int deletedCount = entityManager.createQuery(
                "delete from ResearchProject where researchProjectId="
                        + persistentInstance.getResearchProjectId()).executeUpdate();
        return deletedCount;
    }

    public Integer updateFlagResearchProject(ResearchProject persistentInstance) {
        // TODO Auto-generated method stub
        Query query = null;
        if (persistentInstance.getUpdateType() != null && persistentInstance.getUpdateType().equals("flag")) {
            query = entityManager.createQuery(
                    " update ResearchProject p set p.flag=:flag where p.researchProjectId=:researchProjectId ");
            query.setParameter("flag", persistentInstance.getFlag());
        } else { // docType
            query = entityManager.createQuery(
                    " update ResearchProject p set p.docType=:docType where p.researchProjectId=:researchProjectId ");
            query.setParameter("docType", persistentInstance.getDocType());
        }

        query.setParameter("researchProjectId", persistentInstance.getResearchProjectId());
        Integer rowUpdate=query.executeUpdate();

        return persistentInstance.getResearchProjectId();
    }

    public Integer updateFlagUtilizationItem(Utilization persistentInstance) {
        // TODO Auto-generated method stub

        Query query = null;
        if (persistentInstance.getUpdateType() != null && persistentInstance.getUpdateType().equals("flag")) {
            query = entityManager.createQuery(
                    " update Utilization p set p.flag=:flag where p.id.utilizationItemList=:utilizationItemList "
                            + "and p.researchProject.researchProjectId=:researchProjectId ");
            query.setParameter("flag", persistentInstance.getFlag());
        } else { // docType
            query = entityManager.createQuery(
                    " update Utilization p set p.docType=:docType where p.id.utilizationItemList=:utilizationItemList "
                            + "and p.researchProject.researchProjectId=:researchProjectId ");
            query.setParameter("docType", persistentInstance.getDocType());
        }

        query.setParameter("utilizationItemList", persistentInstance.getId().getUtilizationItemList());
        query.setParameter("researchProjectId", persistentInstance.getId().getResearchProject().getResearchProjectId());


        return query.executeUpdate();
    }

    public ResearchProject findResearchProjectById(Integer researchProjectId, String userid) {
        // TODO Auto-generated method stub
        ResearchProject researchProject = entityManager.find(ResearchProject.class, researchProjectId);
        // find docAssignMapping
        if (userid != null && researchProject != null) {
            Query query = entityManager.createQuery("select x from DocAssignMapping x where x.id.refId=:refId and x.id.refType=:refType ");
            query.setParameter("refId", researchProject.getResearchProjectId());
            query.setParameter("refType", ServiceConstant.DOC_TYPE_RESEARCH);
            List<DocAssignMapping> docAssignList = query.getResultList();

            HashMap<String, String> map = new HashMap<String, String>();
            for (DocAssignMapping i : docAssignList) map.put(i.getId().getUserId(), i.getId().getUserId());
            researchProject.setDocAssignMapping(map);
            if (userid != null)
                researchProject.setIsdocAssign(map.containsKey(userid));
        }
        return researchProject;
    }

    public List searchResearchProject(ResearchProject persistentInstance,
                                      Paging pagging, String keySearch, String userid) throws DataAccessException {
        // TODO Auto-generated method stub
        String tab = persistentInstance.getTab();
        String filter = persistentInstance.getFilter();
        StringBuffer sb = new StringBuffer("");

        if (keySearch != null && keySearch.trim().length() > 0) {
            sb.append(" and ( project.BUDGET_YEAR like '%" + keySearch.trim() + "%' "
                    + " or project.RESEARCH_PROJECT_CODE like '%" + keySearch.trim() + "%' "
                    + " or project.ENG_NAME like '%" + keySearch.trim() + "%' "
                    + " or project.THAI_NAME like '%" + keySearch.trim() + "%' "
                    + ") ");
        }

        StringBuffer sb_doctype = new StringBuffer();
        if (filter != null) {
            if (!filter.equals(ServiceConstant.FILTERS[3])) {
                if (filter.equals(ServiceConstant.FILTERS[1])
                         ) { // DOCTYPES=PUBLISH
                    sb.append(" and project.DOC_TYPE ='" + ServiceConstant.DOCTYPES[0] + "' ");
                    sb_doctype.append(" and project.DOC_TYPE ='" + ServiceConstant.DOCTYPES[0] + "' ");
                } else if (filter.equals(ServiceConstant.FILTERS[2])) { // DOCTYPES=DRAFT
                    sb.append(" and project.DOC_TYPE ='" + ServiceConstant.DOCTYPES[1] + "' ");
                    sb_doctype.append(" and project.DOC_TYPE ='" + ServiceConstant.DOCTYPES[1] + "' ");
                }
                sb.append(" and project.FLAG =1 ");
                sb_doctype.append(" and project.FLAG =1 ");
            } else { // Trash
                sb.append(" and project.FLAG =0 ");
                sb_doctype.append(" and project.FLAG =0 ");
            }
            if(!tab.equals(ServiceConstant.TAB_MY_DATA) && filter.equals(ServiceConstant.FILTERS[0])){
                sb.append(" and project.DOC_TYPE ='" + ServiceConstant.DOCTYPES[0] + "' ");
                sb_doctype.append(" and project.DOC_TYPE ='" + ServiceConstant.DOCTYPES[0] + "' ");
            }
            // aoe comment @ 05/11/2015
            //if(!filter.equals(ServiceConstant.FILTERS[0])) {
                sb.append(" and ( project.CREATED_BY ='" + userid + "' ");
                sb.append(" or  exists (select * from DOC_ASSIGN_MAPPING  "
                        + " mapping where  project.research_project_id=mapping.ref_id  "
                        + " and mapping.ref_type='RESEARCH' and mapping.USER_ID='" + userid + "' "
                        + sb_doctype.toString()
                        + " ) ) ");
            //}
        }
        if (tab != null && tab.equals(ServiceConstant.TAB_MY_DATA)) {
            sb.append(" and project.CREATED_BY ='" + userid + "' ");
            /* sb.append(" or  exists (select * from DOC_ASSIGN_MAPPING  "
                    + " mapping where  project.research_project_id=mapping.ref_id  "
                    + " and mapping.ref_type='RESEARCH' and mapping.USER_ID='" + userid + "' "
                    + sb_doctype.toString()
                    + " ) ");
             */
        }
        Query query = entityManager.createNativeQuery(
                " select project.* from RESEARCH_PROJECT project "
                        + "  where"
                        + "  1=1 "
                        + sb.toString(),
                ResearchProject.class);

        ArrayList transList = new ArrayList();

        query.setFirstResult((pagging.getPageNo() - 1) * pagging.getPageSize());
        query.setMaxResults(pagging.getPageSize());
        // find docAssignMapping
        List<ResearchProject> resultList = query.getResultList();
        for (ResearchProject researchProject : resultList) {
            query = entityManager.createQuery("select x from DocAssignMapping x where x.id.refId=:refId and x.id.refType=:refType ");
            query.setParameter("refId", researchProject.getResearchProjectId());
            query.setParameter("refType", ServiceConstant.DOC_TYPE_RESEARCH);
            List<DocAssignMapping> docAssignList = query.getResultList();

            HashMap<String, String> map = new HashMap<String, String>();
            for (DocAssignMapping i : docAssignList) map.put(i.getId().getUserId(), i.getId().getUserId());
            researchProject.setDocAssignMapping(map);
            if (userid != null)
                researchProject.setIsdocAssign(map.containsKey(userid));
        }
        transList.add(resultList);

        query = entityManager.createNativeQuery("select count(*) from RESEARCH_PROJECT project  "
                + "  where "
                + "  1=1 "
                + sb.toString());
        java.math.BigInteger count = (java.math.BigInteger) query.getSingleResult();
        transList.add(String.valueOf(count.longValue()));
        return transList;
    }

    public Integer saveJournalPaper(JournalPaper transientInstance) {
        // TODO Auto-generated method stub
        entityManager.persist(transientInstance);
        Integer journalPapersId = transientInstance.getJournalPapersId();
        Integer types = transientInstance.getType();

        return transientInstance.getJournalPapersId();
    }

    public Integer updateJournalPaper(JournalPaper transientInstance) {
        // TODO Auto-generated method stub
        entityManager.merge(transientInstance);
        return transientInstance.getJournalPapersId();
    }

    public Integer deleteJournalPaper(JournalPaper persistentInstance) {
        // TODO Auto-generated method stub
        int deletedCount = entityManager.createQuery(
                "delete from JournalPaper where journalPapersId="
                        + persistentInstance.getJournalPapersId()).executeUpdate();
        return deletedCount;
    }

    public Integer updateFlagJournalPaper(JournalPaper persistentInstance) {
        // TODO Auto-generated method stub
        Query query = null;
        if (persistentInstance.getUpdateType() != null && persistentInstance.getUpdateType().equals("flag")) {
            query = entityManager.createQuery(
                    " update JournalPaper p set p.flag=:flag where p.journalPapersId=:journalPapersId ");
            query.setParameter("flag", persistentInstance.getFlag());
        } else { // docType
            query = entityManager.createQuery(
                    " update JournalPaper p set p.docType=:docType where p.journalPapersId=:journalPapersId ");
            query.setParameter("docType", persistentInstance.getDocType());
        }

        query.setParameter("journalPapersId", persistentInstance.getJournalPapersId());
        query.executeUpdate();

        return persistentInstance.getJournalPapersId();
    }

    public JournalPaper findJournalPapersById(Integer journalPapersId, String userid) {
        // TODO Auto-generated method stub
        JournalPaper journalPaper = entityManager.find(JournalPaper.class, journalPapersId);
        // find docAssignMapping
        if (userid != null && journalPaper != null) {
            Query query = entityManager.createQuery("select x from DocAssignMapping x where x.id.refId=:refId and x.id.refType=:refType ");
            query.setParameter("refId", journalPaper.getJournalPapersId());
            query.setParameter("refType", ServiceConstant.DOC_TYPE_JOURNAL);
            List<DocAssignMapping> docAssignList = query.getResultList();

            HashMap<String, String> map = new HashMap<String, String>();
            for (DocAssignMapping i : docAssignList) map.put(i.getId().getUserId(), i.getId().getUserId());
            journalPaper.setDocAssignMapping(map);
            if (userid != null)
                journalPaper.setIsdocAssign(map.containsKey(userid));
        }
        return journalPaper;
    }

    public List searchJournalPaper(JournalPaper persistentInstance,
                                   Paging pagging, String keySearch, String userid) throws DataAccessException {
        // TODO Auto-generated method stub
        String tab = persistentInstance.getTab();
        String filter = persistentInstance.getFilter();
        StringBuffer sb = new StringBuffer(""
                + " where "
                + " 1=1 ");
        //+ " ( p.flag !=0 or p.flag is null ) ");
        boolean isWhere = false;
        if (persistentInstance.getType() != null) {
            //sb.append(" and p.type="+persistentInstance.getType() );
            sb.append(" and p.TYPE=" + persistentInstance.getType());
            isWhere = true;
        }
        if (keySearch != null && keySearch.trim().length() > 0) {
			/*String where=" where ";
			if(isWhere)*/
            String where = " and ";
			/*sb.append(" "+where+" ( p.engName like '%" + keySearch.trim() + "%' "
					+ " or p.thaiName like '%" + keySearch.trim() + "%'"
					+ " or p.budgetYear like '%" + keySearch.trim() + "%'"
					+ " ) ");*/
            sb.append(" " + where + " ( p.ENG_NAME like '%" + keySearch.trim() + "%' "
                    + " or p.THAI_NAME like '%" + keySearch.trim() + "%'"
                    //  + " or p.BUDGET_YEAR like '%" + keySearch.trim() + "%'"
                    + " ) ");
        }

        StringBuffer sb_doctype = new StringBuffer();
        if (filter != null) {
            if (!filter.equals(ServiceConstant.FILTERS[3])) {
                if (filter.equals(ServiceConstant.FILTERS[1])
                        ) { // DOCTYPES=PUBLISH
                    sb.append(" and p.DOC_TYPE ='" + ServiceConstant.DOCTYPES[0] + "' ");
                    sb_doctype.append(" and p.DOC_TYPE ='" + ServiceConstant.DOCTYPES[0] + "' ");
                } else if (filter.equals(ServiceConstant.FILTERS[2])) { // DOCTYPES=DRAFT
                    sb.append(" and p.DOC_TYPE ='" + ServiceConstant.DOCTYPES[1] + "' ");
                    sb_doctype.append(" and p.DOC_TYPE ='" + ServiceConstant.DOCTYPES[1] + "' ");
                }
                sb.append(" and p.FLAG =1 ");
                sb_doctype.append(" and p.FLAG =1 ");
            } else { // Trash
                sb.append(" and p.FLAG =0 ");
                sb_doctype.append(" and p.FLAG =0 ");
            }
            if(!tab.equals(ServiceConstant.TAB_MY_DATA) && filter.equals(ServiceConstant.FILTERS[0])){
                sb.append(" and p.DOC_TYPE ='" + ServiceConstant.DOCTYPES[0] + "' ");
                sb_doctype.append(" and p.DOC_TYPE ='" + ServiceConstant.DOCTYPES[0] + "' ");
            }
            // aoe comment @ 05/11/2015

            //if(!filter.equals(ServiceConstant.FILTERS[0])) {
                sb.append(" and ( p.CREATED_BY ='" + userid + "' ");
                sb.append(" or  exists (select * from DOC_ASSIGN_MAPPING  "
                        + " mapping where  p.JOURNAL_PAPERS_ID=mapping.ref_id  "
                        + " and mapping.ref_type='JOURNAL' and mapping.USER_ID='" + userid + "' "
                        + sb_doctype.toString()
                        + " ) ) ");
           //}

        }

        if (tab != null && tab.equals(ServiceConstant.TAB_MY_DATA)) {
            //sb.append(" and p.createdBy ='"+userid+"' ");
            sb.append(" and p.CREATED_BY ='" + userid + "' ");
            /*
            sb.append(" or  exists (select * from DOC_ASSIGN_MAPPING  "
                    + " mapping where  p.JOURNAL_PAPERS_ID=mapping.ref_id  "
                    + " and mapping.ref_type='JOURNAL' and mapping.USER_ID='" + userid + "' "
                    + sb_doctype.toString()
                    + "  ) ");
                    */
        }

        ArrayList transList = new ArrayList();
		/*Query query = entityManager.createQuery(
				" select p from JournalPaper p " + sb.toString(),
				JournalPaper.class);*/
        Query query = entityManager.createNativeQuery(
                " select p.* from JOURNAL_PAPERS p " + sb.toString(),
                JournalPaper.class);

        query.setFirstResult((pagging.getPageNo() - 1) * pagging.getPageSize());
        query.setMaxResults(pagging.getPageSize());

        // find docAssignMapping
        List<JournalPaper> resultList = query.getResultList();
        for (JournalPaper journalPaper : resultList) {
            query = entityManager.createQuery("select x from DocAssignMapping x where x.id.refId=:refId and x.id.refType=:refType ");
            query.setParameter("refId", journalPaper.getJournalPapersId());
            query.setParameter("refType", ServiceConstant.DOC_TYPE_JOURNAL);
            List<DocAssignMapping> docAssignList = query.getResultList();

            HashMap<String, String> map = new HashMap<String, String>();
            for (DocAssignMapping i : docAssignList) map.put(i.getId().getUserId(), i.getId().getUserId());
            journalPaper.setDocAssignMapping(map);
            if (userid != null)
                journalPaper.setIsdocAssign(map.containsKey(userid));
        }
        transList.add(resultList);
				

	/*	query = entityManager.createQuery("select count(p) from JournalPaper p "
				+ sb.toString());*/
        query = entityManager.createNativeQuery("select count(*) from JOURNAL_PAPERS p  "
			/*	+ "  where "
				+ "  1=1 "*/
                + sb.toString());
        //	long count = (Long) query.getSingleResult();
        java.math.BigInteger count = (java.math.BigInteger) query.getSingleResult();
        transList.add(String.valueOf(count.longValue()));

        //transList.add(String.valueOf(count));
        return transList;
    }

    public Integer saveUtilization(Utilization transientInstance) {
        // TODO Auto-generated method stub
        Query query = entityManager.createQuery("select max(p.id.utilizationItemList) from Utilization p "
                + " where p.researchProject.researchProjectId=" + transientInstance.getResearchProject().getResearchProjectId());

        java.lang.Integer count = (java.lang.Integer) query.getSingleResult();
        if (count == null)
            count = 1;
        transientInstance.getId().setUtilizationItemList(count + 1);
        entityManager.persist(transientInstance);
        return transientInstance.getResearchProject().getResearchProjectId();
    }

    public Integer updateUtilization(Utilization transientInstance) {
        // TODO Auto-generated method stub
        entityManager.merge(transientInstance);
        return transientInstance.getId().getResearchProject().getResearchProjectId();
    }

    public Integer deleteUtilization(Utilization persistentInstance) {
        // TODO Auto-generated method stub
        int deletedCount = entityManager.createQuery(
                "delete from Utilization where researchProject.researchProjectId="
                        + persistentInstance.getId().getResearchProject().getResearchProjectId()
                //+" and utilizationItemList="+persistentInstance.getId().getUtilizationItemList()
        ).executeUpdate();
        return deletedCount;
    }

    public Integer deleteUtilizationItem(Utilization persistentInstance) {
        // TODO Auto-generated method stub
        int deletedCount = entityManager.createQuery(
                "delete from Utilization where researchProject.researchProjectId="
                        + persistentInstance.getId().getResearchProject().getResearchProjectId()
                        + " and id.utilizationItemList=" + persistentInstance.getId().getUtilizationItemList()
        ).executeUpdate();
        return deletedCount;
    }

    public List<Utilization> findUtilizationById(Integer researchProjectId) {
        // TODO Auto-generated method stub
        StringBuffer sb = new StringBuffer("where p.researchProject.researchProjectId=:researchProjectId");
        Query query = entityManager.createQuery(
                " select p from Utilization p " + sb.toString(),
                Utilization.class);
        query.setParameter("researchProjectId", researchProjectId);
        return query.getResultList();
    }

    public List searchUtilization(Utilization persistentInstance,
                                  Paging pagging, String keySearch) throws DataAccessException {
        // TODO Auto-generated method stub
        StringBuffer sb = new StringBuffer("");
        if (keySearch != null && keySearch.trim().length() > 0) {
            sb.append(" where ( p.researchProject.thaiName like '%" + keySearch.trim() + "%' "
                    + " or p.researchProject.researchProjectCode like '%" + keySearch.trim() + "%'"
                    + " or p.utilizationType.utilizationName like '%" + keySearch.trim() + "%'"
                    + " or p.budgetYear like '%" + keySearch.trim() + "%'"
                    + " ) ");
        }
        ArrayList transList = new ArrayList();
        Query query = entityManager.createQuery(
                " select p from Utilization p " + sb.toString(),
                Utilization.class);
        query.setFirstResult((pagging.getPageNo() - 1) * pagging.getPageSize());
        query.setMaxResults(pagging.getPageSize());

        transList.add(query.getResultList());
        List<Utilization> us = (List<Utilization>) transList.get(0);
        query = entityManager.createQuery("select count(p) from Utilization p "
                + sb.toString());
        long count = (Long) query.getSingleResult();
        transList.add(String.valueOf(count));
        return transList;
    }

    public Integer saveCopyright(Copyright transientInstance) {
        // TODO Auto-generated method stub
        //transientInstance.getResearchProject().
        entityManager.persist(transientInstance);
        return transientInstance.getCopyrightId();
    }

    public Integer updateCopyright(Copyright transientInstance) {
        // TODO Auto-generated method stub
        entityManager.merge(transientInstance);
        return transientInstance.getCopyrightId();
    }

    public Integer deleteCopyright(Copyright persistentInstance) {
        // TODO Auto-generated method stub
        int deletedCount = entityManager.createQuery(
                "delete from Copyright where copyrightId="
                        + persistentInstance.getCopyrightId()).executeUpdate();
        return deletedCount;
    }

    public Integer updateFlagCopyright(Copyright persistentInstance) {
        // TODO Auto-generated method stub
        Query query = null;
        if (persistentInstance.getUpdateType() != null && persistentInstance.getUpdateType().equals("flag")) {
            query = entityManager.createQuery(
                    " update Copyright p set p.flag=:flag where p.copyrightId=:copyrightId ");
            query.setParameter("flag", persistentInstance.getFlag());
        } else { // docType
            query = entityManager.createQuery(
                    " update Copyright p set p.docType=:docType where p.copyrightId=:copyrightId ");
            query.setParameter("docType", persistentInstance.getDocType());
        }

        query.setParameter("copyrightId", persistentInstance.getCopyrightId());
        query.executeUpdate();

        return persistentInstance.getCopyrightId();
    }

    public Copyright findResearchCopyRightById(Integer copyrightId, String userid) {
        // TODO Auto-generated method stub
        Copyright copyright = entityManager.find(Copyright.class, copyrightId);

        // find docAssignMapping
        if (userid != null && copyright != null) {
            Query query = entityManager.createQuery("select x from DocAssignMapping x where x.id.refId=:refId and x.id.refType=:refType ");
            query.setParameter("refId", copyright.getCopyrightId());
            query.setParameter("refType", ServiceConstant.DOC_TYPE_COPYRIGHT);
            List<DocAssignMapping> docAssignList = query.getResultList();

            HashMap<String, String> map = new HashMap<String, String>();
            for (DocAssignMapping i : docAssignList) map.put(i.getId().getUserId(), i.getId().getUserId());
            copyright.setDocAssignMapping(map);
            if (userid != null)
                copyright.setIsdocAssign(map.containsKey(userid));
        }
        return copyright;
    }

    public List searchCopyright(Copyright persistentInstance,
                                Paging pagging, String keySearch, String userid) throws DataAccessException {
        // TODO Auto-generated method stub
        String tab = persistentInstance.getTab();
        String filter = persistentInstance.getFilter();
        StringBuffer sb = new StringBuffer(" where 1=1 ");
        // + " ( p.flag !=0 or p.flag is null ) ");
        if (keySearch != null && keySearch.trim().length() > 0) {
			/*sb.append(" and ( p.budgetYear like '%" + keySearch.trim() + "%' "
					+ "or p.engName like '%" + keySearch.trim() + "%' "
					+ "or p.thaiName like '%" + keySearch.trim() + "%' "
					+ "or p.requestNo like '%" + keySearch.trim() + "%' "
					+ " ) ");*/
            sb.append(" and ( p.BUDGET_YEAR like '%" + keySearch.trim() + "%' "
                    + "or p.ENG_NAME like '%" + keySearch.trim() + "%' "
                    + "or p.THAI_NAME like '%" + keySearch.trim() + "%' "
                    + "or p.REQUEST_NO like '%" + keySearch.trim() + "%' "
                    + " ) ");
        }

        StringBuffer sb_doctype = new StringBuffer();
        if (filter != null) {
            if (!filter.equals(ServiceConstant.FILTERS[3])) {
                if (filter.equals(ServiceConstant.FILTERS[1])
                        ) { // DOCTYPES=PUBLISH
                    sb.append(" and p.DOC_TYPE ='" + ServiceConstant.DOCTYPES[0] + "' ");
                    sb_doctype.append(" and p.DOC_TYPE ='" + ServiceConstant.DOCTYPES[0] + "' ");
                } else if (filter.equals(ServiceConstant.FILTERS[2])) { // DOCTYPES=DRAFT
                    sb.append(" and p.DOC_TYPE ='" + ServiceConstant.DOCTYPES[1] + "' ");
                    sb_doctype.append(" and p.DOC_TYPE ='" + ServiceConstant.DOCTYPES[1] + "' ");
                }
                sb.append(" and p.FLAG =1 ");
                sb_doctype.append(" and p.FLAG =1 ");
            } else { // Trash
                sb.append(" and p.FLAG =0 ");
                sb_doctype.append(" and p.FLAG =0 ");
            }
            if(!tab.equals(ServiceConstant.TAB_MY_DATA) && filter.equals(ServiceConstant.FILTERS[0])){
                sb.append(" and p.DOC_TYPE ='" + ServiceConstant.DOCTYPES[0] + "' ");
                sb_doctype.append(" and p.DOC_TYPE ='" + ServiceConstant.DOCTYPES[0] + "' ");
            }
            // aoe comment @ 05/11/2015
            // if(!filter.equals(ServiceConstant.FILTERS[0])) {
                sb.append(" and ( p.CREATED_BY ='" + userid + "' ");
                sb.append(" or  exists (select * from DOC_ASSIGN_MAPPING  "
                        + " mapping where  p.COPYRIGHT_ID=mapping.ref_id  "
                        + " and mapping.ref_type='COPYRIGHT' and mapping.USER_ID='" + userid + "' "
                        + sb_doctype.toString()
                        + " ) ) ");
            //}
        }

        if (tab != null && tab.equals(ServiceConstant.TAB_MY_DATA)) {
            sb.append(" and p.CREATED_BY ='" + userid + "' ");
            /*
            sb.append(" or  exists (select * from DOC_ASSIGN_MAPPING  "
                    + " mapping where  p.COPYRIGHT_ID=mapping.ref_id  "
                    + " and mapping.ref_type='COPYRIGHT' and mapping.USER_ID='" + userid + "' "
                    + sb_doctype.toString()
                    + " ) ");
                    */
        }

        ArrayList transList = new ArrayList();
		/*Query query = entityManager.createQuery(
				" select p from Copyright p " + sb.toString(),*/
        Query query = entityManager.createNativeQuery(
                " select p.* from COPYRIGHT p " + sb.toString(),
                Copyright.class);
        query.setFirstResult((pagging.getPageNo() - 1) * pagging.getPageSize());
        query.setMaxResults(pagging.getPageSize());

        // find docAssignMapping
        List<Copyright> resultList = query.getResultList();
        for (Copyright copyright : resultList) {
            query = entityManager.createQuery("select x from DocAssignMapping x where x.id.refId=:refId and x.id.refType=:refType ");
            query.setParameter("refId", copyright.getCopyrightId());
            query.setParameter("refType", ServiceConstant.DOC_TYPE_COPYRIGHT);
            List<DocAssignMapping> docAssignList = query.getResultList();

            HashMap<String, String> map = new HashMap<String, String>();
            for (DocAssignMapping i : docAssignList) map.put(i.getId().getUserId(), i.getId().getUserId());
            copyright.setDocAssignMapping(map);
            if (userid != null)
                copyright.setIsdocAssign(map.containsKey(userid));
        }
        transList.add(resultList);

		/*query = entityManager.createQuery("select count(p) from Copyright p "
				+ sb.toString());*/
        query = entityManager.createNativeQuery("select count(*) from COPYRIGHT p  " + sb.toString());

        java.math.BigInteger count = (java.math.BigInteger) query.getSingleResult();
        transList.add(String.valueOf(count.longValue()));
		/*long count = (Long) query.getSingleResult();
		transList.add(String.valueOf(count));*/
        return transList;
    }

    public Integer saveReward(Reward transientInstance) {
        // TODO Auto-generated method stub
        entityManager.persist(transientInstance);
        return transientInstance.getRewardId();
    }

    public Integer updateReward(Reward transientInstance) {
        // TODO Auto-generated method stub
        entityManager.merge(transientInstance);
        return transientInstance.getRewardId();
    }

    public Integer deleteReward(Reward persistentInstance) {
        // TODO Auto-generated method stub
        int deletedCount = entityManager.createQuery(
                "delete from Reward where rewardId="
                        + persistentInstance.getRewardId()).executeUpdate();
        return deletedCount;
    }

    public Integer updateFlagReward(Reward persistentInstance) {
        // TODO Auto-generated method stub
        Query query = null;
        if (persistentInstance.getUpdateType() != null && persistentInstance.getUpdateType().equals("flag")) {
            query = entityManager.createQuery(
                    " update Reward p set p.flag=:flag where p.rewardId=:rewardId ");
            query.setParameter("flag", persistentInstance.getFlag());
        } else { // docType
            query = entityManager.createQuery(
                    " update Reward p set p.docType=:docType where p.rewardId=:rewardId ");
            query.setParameter("docType", persistentInstance.getDocType());
        }

        query.setParameter("rewardId", persistentInstance.getRewardId());
        query.executeUpdate();

        return persistentInstance.getRewardId();
    }

    public Reward findRewardById(Integer rewardId, String userid) {
        // TODO Auto-generated method stub
        Reward reward = entityManager.find(Reward.class, rewardId);
        // find docAssignMapping
        if (userid != null && reward != null) {
            Query query = entityManager.createQuery("select x from DocAssignMapping x where x.id.refId=:refId and x.id.refType=:refType ");
            query.setParameter("refId", reward.getRewardId());
            query.setParameter("refType", ServiceConstant.DOC_TYPE_REWARD);
            List<DocAssignMapping> docAssignList = query.getResultList();

            HashMap<String, String> map = new HashMap<String, String>();
            for (DocAssignMapping i : docAssignList) map.put(i.getId().getUserId(), i.getId().getUserId());
            reward.setDocAssignMapping(map);
            if (userid != null)
                reward.setIsdocAssign(map.containsKey(userid));
        }
        return reward;
    }

    public List searchReward(Reward persistentInstance,
                             Paging pagging, String keySearch, String userid) throws DataAccessException {
        // TODO Auto-generated method stub
        String tab = persistentInstance.getTab();
        String filter = persistentInstance.getFilter();
        StringBuffer sb = new StringBuffer(" where 1=1 ");
        // + " ( p.flag !=0 or p.flag is null ) ");
        if (keySearch != null && keySearch.trim().length() > 0) {
		/*	sb.append(" and ( p.budgetYear like '%" + keySearch.trim() + "%' "
					+ "or p.rewardFrom like '%" + keySearch.trim() + "%' "
					+ "or p.rewardName like '%" + keySearch.trim() + "%' "
					+ " ) ");*/
            sb.append(" and ( p.BUDGET_YEAR like '%" + keySearch.trim() + "%' "
                    + "or p.REWARD_FROM like '%" + keySearch.trim() + "%' "
                    + "or p.REWARD_NAME like '%" + keySearch.trim() + "%' "
                    + " ) ");
        }

        StringBuffer sb_doctype = new StringBuffer();
        if (filter != null) {
            if (!filter.equals(ServiceConstant.FILTERS[3])) {
                if (filter.equals(ServiceConstant.FILTERS[1])
                        ) { // DOCTYPES=PUBLISH
                    sb.append(" and p.DOC_TYPE ='" + ServiceConstant.DOCTYPES[0] + "' ");
                    sb_doctype.append(" and p.DOC_TYPE ='" + ServiceConstant.DOCTYPES[0] + "' ");
                } else if (filter.equals(ServiceConstant.FILTERS[2])) { // DOCTYPES=DRAFT
                    sb.append(" and p.DOC_TYPE ='" + ServiceConstant.DOCTYPES[1] + "' ");
                    sb_doctype.append(" and p.DOC_TYPE ='" + ServiceConstant.DOCTYPES[1] + "' ");
                }
                sb.append(" and p.FLAG =1 ");
                sb_doctype.append(" and p.FLAG =1 ");
            } else { // Trash
                sb.append(" and p.FLAG =0 ");
                sb_doctype.append(" and p.FLAG =0 ");
            }
            if(!tab.equals(ServiceConstant.TAB_MY_DATA) && filter.equals(ServiceConstant.FILTERS[0])){
                sb.append(" and p.DOC_TYPE ='" + ServiceConstant.DOCTYPES[0] + "' ");
                sb_doctype.append(" and p.DOC_TYPE ='" + ServiceConstant.DOCTYPES[0] + "' ");
            }
            // aoe comment @ 05/11/2015
            //if(!filter.equals(ServiceConstant.FILTERS[0])) {
                sb.append(" and ( p.CREATED_BY ='" + userid + "' ");
                sb.append(" or  exists (select * from DOC_ASSIGN_MAPPING  "
                        + " mapping where  p.REWARD_ID=mapping.ref_id  "
                        + " and mapping.ref_type='REWARD' and mapping.USER_ID='" + userid + "' "
                        + sb_doctype.toString()
                        + " ) ) ");
            //}
        }
        if (tab != null && tab.equals(ServiceConstant.TAB_MY_DATA)) {
            sb.append(" and p.CREATED_BY ='" + userid + "' ");
            /*
            sb.append(" or  exists (select * from DOC_ASSIGN_MAPPING  "
                    + " mapping where  p.REWARD_ID=mapping.ref_id  "
                    + " and mapping.ref_type='REWARD' and mapping.USER_ID='" + userid + "'"
                    + sb_doctype.toString()
                    + "  ) ");
               */
        }
        ArrayList transList = new ArrayList();
		/*Query query = entityManager.createQuery(
				" select p from Reward p " + sb.toString(),*/
        Query query = entityManager.createNativeQuery(
                " select p.* from REWARD p " + sb.toString(),
                Reward.class);
        query.setFirstResult((pagging.getPageNo() - 1) * pagging.getPageSize());
        query.setMaxResults(pagging.getPageSize());

        // find docAssignMapping
        List<Reward> resultList = query.getResultList();
        for (Reward reward : resultList) {
            query = entityManager.createQuery("select x from DocAssignMapping x where x.id.refId=:refId and x.id.refType=:refType ");
            query.setParameter("refId", reward.getRewardId());
            query.setParameter("refType", ServiceConstant.DOC_TYPE_REWARD);
            List<DocAssignMapping> docAssignList = query.getResultList();

            HashMap<String, String> map = new HashMap<String, String>();
            for (DocAssignMapping i : docAssignList) map.put(i.getId().getUserId(), i.getId().getUserId());
            reward.setDocAssignMapping(map);
            if (userid != null)
                reward.setIsdocAssign(map.containsKey(userid));
        }
        transList.add(resultList);

		/*query = entityManager.createQuery("select count(p) from Reward p "
				+ sb.toString());*/
        query = entityManager.createNativeQuery("select count(*) from REWARD p  " + sb.toString());
		/*long count = (Long) query.getSingleResult();
		transList.add(String.valueOf(count));*/
        java.math.BigInteger count = (java.math.BigInteger) query.getSingleResult();
        transList.add(String.valueOf(count.longValue()));
        return transList;
    }

    public Integer savePatent(Patent transientInstance) {
        // TODO Auto-generated method stub
        entityManager.persist(transientInstance);
        return transientInstance.getInventionId();
    }

    public Integer updatePatent(Patent transientInstance) {
        // TODO Auto-generated method stub
        entityManager.merge(transientInstance);
        return transientInstance.getInventionId();
    }

    public Integer deletePatent(Patent persistentInstance) {
        // TODO Auto-generated method stub
        int deletedCount = entityManager.createQuery(
                "delete from Patent where inventionId="
                        + persistentInstance.getInventionId()).executeUpdate();
        return deletedCount;
    }

    public Integer updateFlagPatent(Patent persistentInstance) {
        // TODO Auto-generated method stub
        Query query = null;
        if (persistentInstance.getUpdateType() != null && persistentInstance.getUpdateType().equals("flag")) {
            query = entityManager.createQuery(
                    " update Patent p set p.flag=:flag where p.inventionId=:inventionId ");
            query.setParameter("flag", persistentInstance.getFlag());
        } else { // docType
            query = entityManager.createQuery(
                    " update Patent p set p.docType=:docType where p.inventionId=:inventionId ");
            query.setParameter("docType", persistentInstance.getDocType());
        }


        query.setParameter("inventionId", persistentInstance.getInventionId());
        query.executeUpdate();

        return persistentInstance.getInventionId();
    }

    public Patent findPatentById(Integer inventionId, String userid) {
        // TODO Auto-generated method stub
        Patent patent = entityManager.find(Patent.class, inventionId);
        // find docAssignMapping
        if (userid != null && patent != null) {
            Query query = entityManager.createQuery("select x from DocAssignMapping x where x.id.refId=:refId and x.id.refType=:refType ");
            query.setParameter("refId", patent.getInventionId());
            query.setParameter("refType", ServiceConstant.DOC_TYPE_PATENT);
            List<DocAssignMapping> docAssignList = query.getResultList();

            HashMap<String, String> map = new HashMap<String, String>();
            for (DocAssignMapping i : docAssignList) map.put(i.getId().getUserId(), i.getId().getUserId());
            patent.setDocAssignMapping(map);
            if (userid != null)
                patent.setIsdocAssign(map.containsKey(userid));
        }
        return patent;
    }

    public List searchPatent(Patent persistentInstance,
                             Paging pagging, String keySearch, String userid) throws DataAccessException {
        // TODO Auto-generated method stub
        String tab = persistentInstance.getTab();
        String filter = persistentInstance.getFilter();
        StringBuffer sb = new StringBuffer(" where 1=1 ");
        // + " ( p.flag !=0 or p.flag is null ) ");
        if (keySearch != null && keySearch.trim().length() > 0) {
			/*sb.append(" and ( p.proposeNo like '%" + keySearch.trim() + "%' "
					+ "or p.engName like '%" + keySearch.trim() + "%' "
					+ "or p.thaiName like '%" + keySearch.trim() + "%' "
					+ " ) ");*/
            sb.append(" and ( p.PROPOSE_NO like '%" + keySearch.trim() + "%' "
                    + "or p.ENG_NAME like '%" + keySearch.trim() + "%' "
                    + "or p.THAI_NAME like '%" + keySearch.trim() + "%' "
                    + " ) ");
        }

        StringBuffer sb_doctype = new StringBuffer();
        if (filter != null) {
            if (!filter.equals(ServiceConstant.FILTERS[3])) {
                if (filter.equals(ServiceConstant.FILTERS[1])
                        ) { // DOCTYPES=PUBLISH
                    sb.append(" and p.DOC_TYPE ='" + ServiceConstant.DOCTYPES[0] + "' ");
                    sb_doctype.append(" and p.DOC_TYPE ='" + ServiceConstant.DOCTYPES[0] + "' ");
                } else if (filter.equals(ServiceConstant.FILTERS[2])) { // DOCTYPES=DRAFT
                    sb.append(" and p.DOC_TYPE ='" + ServiceConstant.DOCTYPES[1] + "' ");
                    sb_doctype.append(" and p.DOC_TYPE ='" + ServiceConstant.DOCTYPES[1] + "' ");
                }
                sb.append(" and p.FLAG =1 ");
                sb_doctype.append(" and p.FLAG =1 ");
            } else { // Trash
                sb.append(" and p.FLAG =0 ");
                sb_doctype.append(" and p.FLAG =0 ");
            }
            if(!tab.equals(ServiceConstant.TAB_MY_DATA) && filter.equals(ServiceConstant.FILTERS[0])){
                sb.append(" and p.DOC_TYPE ='" + ServiceConstant.DOCTYPES[0] + "' ");
                sb_doctype.append(" and p.DOC_TYPE ='" + ServiceConstant.DOCTYPES[0] + "' ");
            }
            // aoe comment @ 05/11/2015
            //if(!filter.equals(ServiceConstant.FILTERS[0])) {
                sb.append(" and ( p.CREATED_BY ='" + userid + "' ");
                sb.append(" or  exists (select * from DOC_ASSIGN_MAPPING  "
                        + " mapping where  p.INVENTION_ID=mapping.ref_id  "
                        + " and mapping.ref_type='PATENT' and mapping.USER_ID='" + userid + "' "
                        + sb_doctype.toString()
                        + " ) ) ");
            //}
        }
        if (tab != null && tab.equals(ServiceConstant.TAB_MY_DATA)) {
            sb.append(" and p.CREATED_BY ='" + userid + "' ");
        }
        ArrayList transList = new ArrayList();
		/*Query query = entityManager.createQuery(
				" select p from Patent p " + sb.toString(),*/
        Query query = entityManager.createNativeQuery(
                " select p.* from PATENT p " + sb.toString(),
                Patent.class);
        query.setFirstResult((pagging.getPageNo() - 1) * pagging.getPageSize());
        query.setMaxResults(pagging.getPageSize());

        // find docAssignMapping
        List<Patent> resultList = query.getResultList();
        for (Patent patent : resultList) {
            query = entityManager.createQuery("select x from DocAssignMapping x where x.id.refId=:refId and x.id.refType=:refType ");
            query.setParameter("refId", patent.getInventionId());
            query.setParameter("refType", ServiceConstant.DOC_TYPE_PATENT);
            List<DocAssignMapping> docAssignList = query.getResultList();

            HashMap<String, String> map = new HashMap<String, String>();
            for (DocAssignMapping i : docAssignList) map.put(i.getId().getUserId(), i.getId().getUserId());
            patent.setDocAssignMapping(map);
            if (userid != null)
                patent.setIsdocAssign(map.containsKey(userid));
        }
        transList.add(resultList);

		/*query = entityManager.createQuery("select count(p) from Patent p "
				+ sb.toString());*/
        query = entityManager.createNativeQuery("select count(*) from PATENT p  " + sb.toString());
		/*long count = (Long) query.getSingleResult();
		transList.add(String.valueOf(count));*/
        java.math.BigInteger count = (java.math.BigInteger) query.getSingleResult();
        transList.add(String.valueOf(count.longValue()));
        return transList;
    }

    public Integer updateDocTypeM(DocTypeM transientInstance) {
        // TODO Auto-generated method stub
		/*private String[] isViews;
	    private String[] isUpdates;
	    private String[] isDeletes;
	    private String[] isCreates;
	    private String[] isDisableForAdmins;*/
        Query query = entityManager.createQuery(
                " select p from DocType p ",
                DocType.class);
        List<DocType> doctyps = query.getResultList();
        String disable = "0";
        for (DocType docType : doctyps) {
            docType.setIsCreate(disable);
            docType.setIsDelete(disable);
            docType.setIsDisableForAdmin(disable);
            docType.setIsRead(disable);
            docType.setIsUpdate(disable);
            docType.setIsView(disable);
            entityManager.merge(docType);
            entityManager.flush();
        }
        //  iddnt m = em.createQuery("update Roleuser r set r.firstName = 'Jignesh H' where r.userID=9").executeUpdate();

        String[] isViews = transientInstance.getIsViews();
        String[] isUpdates = transientInstance.getIsUpdates();
        String[] isDeletes = transientInstance.getIsDeletes();
        String[] isCreates = transientInstance.getIsCreates();
        String[] isDisableForAdmins = transientInstance.getIsDisableForAdmins();
        java.sql.Timestamp updatedDate = new java.sql.Timestamp(new Date().getTime());
        if (isViews != null) {
            for (int i = 0; i < isViews.length; i++) {
                query = entityManager.createQuery(
                        " update DocType p set p.isView='1' ,p.updatedDate=:updatedDate where p.dtName=:dtName ");
                query.setParameter("dtName", isViews[i]);
                query.setParameter("updatedDate", updatedDate);
                query.executeUpdate();
                entityManager.flush();
            }
        }
        if (isUpdates != null) {
            for (int i = 0; i < isUpdates.length; i++) {
                query = entityManager.createQuery(
                        " update DocType p set p.isUpdate='1',p.updatedDate=:updatedDate where p.dtName=:dtName ");
                query.setParameter("dtName", isUpdates[i]);
                query.setParameter("updatedDate", updatedDate);
                query.executeUpdate();
                entityManager.flush();
            }
        }
        if (isDeletes != null) {
            for (int i = 0; i < isDeletes.length; i++) {
                query = entityManager.createQuery(
                        " update DocType p set p.isDelete='1',p.updatedDate=:updatedDate where p.dtName=:dtName ");
                query.setParameter("dtName", isDeletes[i]);
                query.setParameter("updatedDate", updatedDate);
                query.executeUpdate();
                entityManager.flush();
            }
        }
        if (isCreates != null) {
            for (int i = 0; i < isCreates.length; i++) {
                query = entityManager.createQuery(
                        " update DocType p set p.isCreate='1',p.updatedDate=:updatedDate where p.dtName=:dtName ");
                query.setParameter("dtName", isCreates[i]);
                query.setParameter("updatedDate", updatedDate);
                query.executeUpdate();
                entityManager.flush();
            }
        }
        if (isDisableForAdmins != null) {
            for (int i = 0; i < isDisableForAdmins.length; i++) {
                query = entityManager.createQuery(
                        " update DocType p set p.isDisableForAdmin='1',p.updatedDate=:updatedDate where p.dtName=:dtName ");
                query.setParameter("dtName", isDisableForAdmins[i]);
                query.setParameter("updatedDate", updatedDate);
                query.executeUpdate();
                entityManager.flush();
            }
        }
        return 1;
    }

    public List listDocTypeM() throws DataAccessException {
        // TODO Auto-generated method stub
        StringBuffer sb = new StringBuffer("");

        ArrayList transList = new ArrayList();
        Query query = entityManager.createQuery(
                " select p from DocType p " + sb.toString(),
                DocType.class);
        transList.add(query.getResultList());

        query = entityManager.createQuery("select count(p) from DocType p "
                + sb.toString());
        long count = (Long) query.getSingleResult();
        transList.add(String.valueOf(count));
        return transList;
    }

    public List<ResearchProjectDocument> listResearchProjectDocument(
            Integer researchProjectId) {
        // TODO Auto-generated method stub
        Query query = entityManager.createQuery(
                " select p from ResearchProjectDocument p where p.id.researchProjectId=:researchProjectId ",
                ResearchProjectDocument.class);
        query.setParameter("researchProjectId", researchProjectId);
        return query.getResultList();
    }

    public List<ResearchProjectPayment> listResearchProjectPayment(
            Integer researchProjectId) {
        // TODO Auto-generated method stub
        Query query = entityManager.createQuery(
                " select p from ResearchProjectPayment p where p.id.researchProjectId=:researchProjectId ",
                ResearchProjectPayment.class);
        query.setParameter("researchProjectId", researchProjectId);
        return query.getResultList();
    }

    public List<ResearchProjectProgress> listResearchProjectProgress(
            Integer researchProjectId) {
        // TODO Auto-generated method stub
        Query query = entityManager.createQuery(
                " select p from ResearchProjectProgress p where p.id.researchProjectId=:researchProjectId ",
                ResearchProjectProgress.class);
        query.setParameter("researchProjectId", researchProjectId);
        return query.getResultList();
    }

    public List<ResearchProjectResearcher> listResearchProjectResearcher(
            Integer researchProjectId) {
        // TODO Auto-generated method stub
        Query query = entityManager.createQuery(
                " select p from ResearchProjectResearcher p where p.id.researchProjectId=:researchProjectId ",
                ResearchProjectResearcher.class);
        query.setParameter("researchProjectId", researchProjectId);
        return query.getResultList();
    }

    public List<ResearchProjectWithdraw> listResearchProjectWithdraw(
            Integer researchProjectId) {
        // TODO Auto-generated method stub
        Query query = entityManager.createQuery(
                " select p from ResearchProjectWithdraw p where p.id.researchProjectId=:researchProjectId ",
                ResearchProjectWithdraw.class);
        query.setParameter("researchProjectId", researchProjectId);
        return query.getResultList();
    }

    public List<CopyrightCreator> listCopyrightCreator(Integer copyrightId) {
        // TODO Auto-generated method stub
        Query query = entityManager.createQuery(
                " select p from CopyrightCreator p where p.id.copyrightId=:copyrightId ",
                CopyrightCreator.class);
        query.setParameter("copyrightId", copyrightId);
        return query.getResultList();
    }

    public List<RewardCreator> listRewardCreator(Integer rewardId) {
        // TODO Auto-generated method stub
        Query query = entityManager.createQuery(
                " select p from RewardCreator p where p.id.rewardId=:rewardId ",
                RewardCreator.class);
        query.setParameter("rewardId", rewardId);
        return query.getResultList();
    }

    public List<PatentCreator> listPatentCreator(Integer inventionId) {
        // TODO Auto-generated method stub
        Query query = entityManager.createQuery(
                " select p from PatentCreator p where p.id.inventionId=:inventionId ",
                PatentCreator.class);
        query.setParameter("inventionId", inventionId);
        return query.getResultList();
    }

    public List<PatentDocument> listPatentDocument(Integer inventionId) {
        // TODO Auto-generated method stub
        Query query = entityManager.createQuery(
                " select p from PatentDocument p where p.id.inventionId=:inventionId ",
                PatentDocument.class);
        query.setParameter("inventionId", inventionId);
        return query.getResultList();
    }

    public List<PatentEditDate> listPatentEditDate(Integer inventionId) {
        // TODO Auto-generated method stub
        Query query = entityManager.createQuery(
                " select p from PatentEditDate p where p.id.inventionId=:inventionId ",
                PatentEditDate.class);
        query.setParameter("inventionId", inventionId);
        return query.getResultList();
    }

    public List<PatentFeePayment> listPatentFeePayment(Integer inventionId) {
        // TODO Auto-generated method stub
        Query query = entityManager.createQuery(
                " select p from PatentFeePayment p where p.id.inventionId=:inventionId ",
                PatentFeePayment.class);
        query.setParameter("inventionId", inventionId);
        return query.getResultList();
    }

    public List<PatentInherited> listPatentInherited(Integer inventionId) {
        // TODO Auto-generated method stub
        Query query = entityManager.createQuery(
                " select p from PatentInherited p where p.id.inventionId=:inventionId ",
                PatentInherited.class);
        query.setParameter("inventionId", inventionId);
        return query.getResultList();
    }

    public List<PatentRightholder> listPatentRightholder(Integer inventionId) {
        // TODO Auto-generated method stub
        Query query = entityManager.createQuery(
                " select p from PatentRightholder p where p.id.inventionId=:inventionId ",
                PatentRightholder.class);
        query.setParameter("inventionId", inventionId);
        return query.getResultList();
    }

    public Integer saveDocAssignMapping(DocAssignMapping transientInstance) {
        Query query = entityManager.createQuery("select count(p) from DocAssignMapping p " +
                " where id.refId=" + transientInstance.getId().getRefId() +
                " and id.refType='" + transientInstance.getId().getRefType() + "'"
                + " and id.userId='" + transientInstance.getId().getUserId() + "'");

        java.lang.Long count = (java.lang.Long) query.getSingleResult();
        if (!(count != null && count > 0))
            entityManager.persist(transientInstance);
        return transientInstance.getId().getRefId();
    }

    public Integer deleteDocAssignMapping(DocAssignMapping persistentInstance) {
        int deletedCount = entityManager.createQuery(
                "delete from DocAssignMapping where id.refId=" + persistentInstance.getId().getRefId() +
                        " and id.refType='" + persistentInstance.getId().getRefType() + "'"
                        + " and id.userId='" + persistentInstance.getId().getUserId() + "'").executeUpdate();
        return deletedCount;
    }

    public DocAssignMapping findDocAssignMappingById(Integer refId, String refType) {
        DocAssignMappingPK pk = new DocAssignMappingPK();
        pk.setRefId(refId);
        pk.setRefType(refType);
        return entityManager.find(DocAssignMapping.class, pk);

    }

    @SuppressWarnings("rawtypes")
    public List listDocAssignMapping(DocAssignMapping persistentInstance) throws DataAccessException {
        Integer refId = persistentInstance.getId().getRefId();
        String refType = persistentInstance.getId().getRefType();

        Query query = entityManager.createQuery(
                " select p from DocAssignMapping p where p.id.refId=:refId and p.id.refType=:refType ",
                DocAssignMapping.class);
        query.setParameter("refId", refId);
        query.setParameter("refType", refType);
        List<DocAssignMapping> docAssignMappings = query.getResultList();
        for (DocAssignMapping docAssignMapping : docAssignMappings) {
            th.ac.kmutt.research.portal.domain.UserPortal user =
                    portalEntityManager.find(th.ac.kmutt.research.portal.domain.UserPortal.class, Integer.valueOf(docAssignMapping.getId().getUserId()));
            if (user != null) {
                docAssignMapping.setUserPortal(user);
            }
        }
		
		/*Query query = portalEntityManager.createQuery(
				" select p from UserPortal2 p " + sb.toString(),
				UserPortal.class);
		query.setFirstResult((pagging.getPageNo()-1) * pagging.getPageSize()); */
        return docAssignMappings;
    }

    public Integer saveResearchProjectDocument(
            ResearchProjectDocument transientInstance) {
        // TODO Auto-generated method stub
        Query query = entityManager.createQuery("select max(p.id.itemList) from ResearchProjectDocument p "
                + " where p.id.researchProjectId=" + transientInstance.getId().getResearchProjectId());

        java.lang.Integer count = (java.lang.Integer) query.getSingleResult();
        if (count == null)
            count = 1;
        transientInstance.getId().setItemList(count + 1);
        entityManager.persist(transientInstance);
        return transientInstance.getId().getResearchProjectId();
    }

    public Integer updateResearchProjectDocument(
            ResearchProjectDocument transientInstance) {
        // TODO Auto-generated method stub
        entityManager.merge(transientInstance);
        return transientInstance.getId().getResearchProjectId();
    }

    public Integer deleteResearchProjectDocument(
            ResearchProjectDocument persistentInstance) {
        // TODO Auto-generated method stub

        int deletedCount = entityManager.createQuery(
                "delete from ResearchProjectDocument where id.researchProjectId="
                        + persistentInstance.getId().getResearchProjectId()
                        + " and id.itemList=" + persistentInstance.getId().getItemList()
        ).executeUpdate();
        return deletedCount;
    }

    public Integer saveResearchProjectPayment(
            ResearchProjectPayment transientInstance) {
        // TODO Auto-generated method stub
        Query query = entityManager.createQuery("select max(p.id.itemList) from ResearchProjectPayment p "
                + " where p.id.researchProjectId=" + transientInstance.getId().getResearchProjectId());

        java.lang.Integer count = (java.lang.Integer) query.getSingleResult();
        if (count == null)
            count = 1;
        transientInstance.getId().setItemList(count + 1);
        entityManager.persist(transientInstance);
        return transientInstance.getId().getResearchProjectId();
    }

    public Integer updateResearchProjectPayment(
            ResearchProjectPayment transientInstance) {
        // TODO Auto-generated method stub
        entityManager.merge(transientInstance);
        return transientInstance.getId().getResearchProjectId();
    }

    public Integer deleteResearchProjectPayment(
            ResearchProjectPayment persistentInstance) {
        // TODO Auto-generated method stub
        int deletedCount = entityManager.createQuery(
                "delete from ResearchProjectPayment where id.researchProjectId="
                        + persistentInstance.getId().getResearchProjectId()
                        + " and id.itemList=" + persistentInstance.getId().getItemList()
        ).executeUpdate();
        return deletedCount;
    }

    public Integer saveResearchProjectProgress(
            ResearchProjectProgress transientInstance) {
        // TODO Auto-generated method stub

        Query query = entityManager.createQuery("select max(p.id.itemList) from ResearchProjectProgress p "
                + " where p.id.researchProjectId=" + transientInstance.getId().getResearchProjectId());

        java.lang.Integer count = (java.lang.Integer) query.getSingleResult();
        if (count == null)
            count = 1;
        transientInstance.getId().setItemList(count + 1);
        entityManager.persist(transientInstance);
        return transientInstance.getId().getResearchProjectId();
    }

    public Integer updateResearchProjectProgress(
            ResearchProjectProgress transientInstance) {
        // TODO Auto-generated method stub
        entityManager.merge(transientInstance);
        return transientInstance.getId().getResearchProjectId();
    }

    public Integer deleteResearchProjectProgress(
            ResearchProjectProgress persistentInstance) {
        // TODO Auto-generated method stub
        int deletedCount = entityManager.createQuery(
                "delete from ResearchProjectProgress where id.researchProjectId="
                        + persistentInstance.getId().getResearchProjectId()
                        + " and id.itemList=" + persistentInstance.getId().getItemList()
        ).executeUpdate();
        return deletedCount;
    }

    public Integer saveResearchProjectResearcher(
            ResearchProjectResearcher transientInstance) {
        // TODO Auto-generated method stub
        // check duplicate
        Query query = entityManager.createQuery("select count(p) from ResearchProjectResearcher p "
                + " where p.id.researchProjectId=" + transientInstance.getId().getResearchProjectId()
                + " and p.researcherId=" + transientInstance.getResearcherId());

        java.lang.Long count_exist = (java.lang.Long) query.getSingleResult();
        if (count_exist == 0) {
            query = entityManager.createQuery("select max(p.id.itemList) from ResearchProjectResearcher p "
                    + " where p.id.researchProjectId=" + transientInstance.getId().getResearchProjectId());

            java.lang.Integer count = (java.lang.Integer) query.getSingleResult();
            if (count == null)
                count = 1;
            transientInstance.getId().setItemList(count + 1);
            entityManager.persist(transientInstance);
        }
        return transientInstance.getId().getResearchProjectId();
    }

    public Integer updateResearchProjectResearcher(
            ResearchProjectResearcher transientInstance) {
        // TODO Auto-generated method stub
        entityManager.merge(transientInstance);
        return transientInstance.getId().getResearchProjectId();
    }

    public Integer deleteResearchProjectResearcher(
            ResearchProjectResearcher persistentInstance) {
        // TODO Auto-generated method stub
        int deletedCount = entityManager.createQuery(
                "delete from ResearchProjectResearcher where id.researchProjectId="
                        + persistentInstance.getId().getResearchProjectId()
                        + " and id.itemList=" + persistentInstance.getId().getItemList()
        ).executeUpdate();
        return deletedCount;
    }

    public Integer saveResearchProjectWithdraw(
            ResearchProjectWithdraw transientInstance) {
        // TODO Auto-generated method stub
        Query query = entityManager.createQuery("select max(p.id.itemList) from ResearchProjectWithdraw p "
                + " where p.id.researchProjectId=" + transientInstance.getId().getResearchProjectId());

        java.lang.Integer count = (java.lang.Integer) query.getSingleResult();
        if (count == null)
            count = 1;
        transientInstance.getId().setItemList(count + 1);
        entityManager.persist(transientInstance);
        return transientInstance.getId().getResearchProjectId();
    }

    public Integer updateResearchProjectWithdraw(
            ResearchProjectWithdraw transientInstance) {
        // TODO Auto-generated method stub
        entityManager.merge(transientInstance);
        return transientInstance.getId().getResearchProjectId();
    }

    public Integer deleteResearchProjectWithdraw(
            ResearchProjectWithdraw persistentInstance) {
        // TODO Auto-generated method stub
        int deletedCount = entityManager.createQuery(
                "delete from ResearchProjectWithdraw where id.researchProjectId="
                        + persistentInstance.getId().getResearchProjectId()
                        + " and id.itemList=" + persistentInstance.getId().getItemList()
        ).executeUpdate();
        return deletedCount;
    }

    public List<JournalPapersDocument> listJournalPapersDocument(
            Integer journalPapersId) {
        // TODO Auto-generated method stub
        Query query = entityManager.createQuery(
                " select p from JournalPapersDocument p where p.id.journalPapersId=:journalPapersId ",
                JournalPapersDocument.class);
        query.setParameter("journalPapersId", journalPapersId);
        return query.getResultList();
    }

    public List<JournalPapersWriter> listJournalPapersWriter(
            Integer journalPapersId) {
        // TODO Auto-generated method stub
        Query query = entityManager.createQuery(
                " select p from JournalPapersWriter p where p.id.journalPapersId=:journalPapersId ",
                JournalPapersWriter.class);
        query.setParameter("journalPapersId", journalPapersId);
        return query.getResultList();
    }

    public Integer saveJournalPapersDocument(
            JournalPapersDocument transientInstance) {
        // TODO Auto-generated method stub
        Query query = entityManager.createQuery("select max(p.id.itemList) from JournalPapersDocument p "
                + " where p.id.journalPapersId=" + transientInstance.getId().getJournalPapersId());

        java.lang.Integer count = (java.lang.Integer) query.getSingleResult();
        if (count == null)
            count = 1;
        transientInstance.getId().setItemList(count + 1);
        entityManager.persist(transientInstance);
        return transientInstance.getId().getJournalPapersId();
    }

    public Integer updateJournalPapersDocument(
            JournalPapersDocument transientInstance) {
        // TODO Auto-generated method stub
        entityManager.merge(transientInstance);
        return transientInstance.getId().getJournalPapersId();
    }

    public Integer deleteJournalPapersDocument(
            JournalPapersDocument persistentInstance) {
        // TODO Auto-generated method stub
        int deletedCount = entityManager.createQuery(
                "delete from JournalPapersDocument where id.journalPapersId="
                        + persistentInstance.getId().getJournalPapersId()
                        + " and id.itemList=" + persistentInstance.getId().getItemList()
        ).executeUpdate();
        return deletedCount;
    }

    public Integer saveJournalPapersWriter(JournalPapersWriter transientInstance) {
        // TODO Auto-generated method stub
        // check duplicate
        Query query = entityManager.createQuery("select count(p) from JournalPapersWriter p "
                + " where p.id.journalPapersId=" + transientInstance.getId().getJournalPapersId()
                + " and p.researcher.researcherId=" + transientInstance.getResearcher().getResearcherId());

        java.lang.Long count_exist = (java.lang.Long) query.getSingleResult();
        if (count_exist == 0) {
            query = entityManager.createQuery("select max(p.id.itemList) from JournalPapersWriter p "
                    + " where p.id.journalPapersId=" + transientInstance.getId().getJournalPapersId());

            java.lang.Integer count = (java.lang.Integer) query.getSingleResult();
            if (count == null)
                count = 1;
            transientInstance.getId().setItemList(count + 1);
            entityManager.persist(transientInstance);
        }
        return transientInstance.getId().getJournalPapersId();
    }

    public Integer updateJournalPapersWriter(
            JournalPapersWriter transientInstance) {
        // TODO Auto-generated method stub
        entityManager.merge(transientInstance);
        return transientInstance.getId().getJournalPapersId();
    }

    public Integer deleteJournalPapersWriter(
            JournalPapersWriter persistentInstance) {
        // TODO Auto-generated method stub
        int deletedCount = entityManager.createQuery(
                "delete from JournalPapersWriter where id.journalPapersId="
                        + persistentInstance.getId().getJournalPapersId()
                        + " and id.itemList=" + persistentInstance.getId().getItemList()
        ).executeUpdate();
        return deletedCount;
    }

    public Integer saveCopyrightCreator(CopyrightCreator transientInstance) {
        // TODO Auto-generated method stub
        Query query = entityManager.createQuery("select max(p.id.creatorItemList) from CopyrightCreator p "
                + " where p.id.copyrightId=" + transientInstance.getId().getCopyrightId());

        java.lang.Integer count = (java.lang.Integer) query.getSingleResult();
        if (count == null)
            count = 1;
        transientInstance.getId().setCreatorItemList(count + 1);
        entityManager.persist(transientInstance);
        return transientInstance.getId().getCopyrightId();
    }

    public Integer updateCopyrightCreator(CopyrightCreator transientInstance) {
        // TODO Auto-generated method stub
        entityManager.merge(transientInstance);
        return transientInstance.getId().getCopyrightId();
    }

    public Integer deleteCopyrightCreator(CopyrightCreator persistentInstance) {
        // TODO Auto-generated method stub
        int deletedCount = entityManager.createQuery(
                "delete from CopyrightCreator where id.copyrightId="
                        + persistentInstance.getId().getCopyrightId()
                        + " and id.creatorItemList=" + persistentInstance.getId().getCreatorItemList()
        ).executeUpdate();
        return deletedCount;
    }

    public Integer saveRewardCreator(RewardCreator transientInstance) {
        // TODO Auto-generated method stub
        Query query = entityManager.createQuery("select max(p.id.creatorItemList) from RewardCreator p "
                + " where p.id.rewardId=" + transientInstance.getId().getRewardId());

        java.lang.Integer count = (java.lang.Integer) query.getSingleResult();
        if (count == null)
            count = 1;
        transientInstance.getId().setCreatorItemList(count + 1);
        entityManager.persist(transientInstance);
        return transientInstance.getId().getRewardId();
    }

    public Integer updateRewardCreator(RewardCreator transientInstance) {
        // TODO Auto-generated method stub
        entityManager.merge(transientInstance);
        return transientInstance.getId().getRewardId();
    }

    public Integer deleteRewardCreator(RewardCreator persistentInstance) {
        // TODO Auto-generated method stub
        int deletedCount = entityManager.createQuery(
                "delete from RewardCreator where id.rewardId="
                        + persistentInstance.getId().getRewardId()
                        + " and id.creatorItemList=" + persistentInstance.getId().getCreatorItemList()
        ).executeUpdate();
        return deletedCount;
    }

    public Integer savePatentCreator(PatentCreator transientInstance) {
        // TODO Auto-generated method stub
        Query query = entityManager.createQuery("select max(p.id.creatorItemList) from PatentCreator p "
                + " where p.id.inventionId=" + transientInstance.getId().getInventionId());

        java.lang.Integer count = (java.lang.Integer) query.getSingleResult();
        if (count == null)
            count = 1;
        transientInstance.getId().setCreatorItemList(count + 1);
        entityManager.persist(transientInstance);
        return transientInstance.getId().getInventionId();
    }

    public Integer updatePatentCreator(PatentCreator transientInstance) {
        // TODO Auto-generated method stub
        entityManager.merge(transientInstance);
        return transientInstance.getId().getInventionId();
    }

    public Integer deletePatentCreator(PatentCreator persistentInstance) {
        // TODO Auto-generated method stub
        int deletedCount = entityManager.createQuery(
                "delete from PatentCreator where id.inventionId="
                        + persistentInstance.getId().getInventionId()
                        + " and id.creatorItemList=" + persistentInstance.getId().getCreatorItemList()
        ).executeUpdate();
        return deletedCount;
    }

    public Integer savePatentDocument(PatentDocument transientInstance) {
        // TODO Auto-generated method stub
        Query query = entityManager.createQuery("select max(p.id.itemList) from PatentDocument p "
                + " where p.id.inventionId=" + transientInstance.getId().getInventionId());

        java.lang.Integer count = (java.lang.Integer) query.getSingleResult();
        if (count == null)
            count = 1;
        transientInstance.getId().setItemList(count + 1);
        entityManager.persist(transientInstance);
        return transientInstance.getId().getInventionId();
    }

    public Integer updatePatentDocument(PatentDocument transientInstance) {
        // TODO Auto-generated method stub
        entityManager.merge(transientInstance);
        return transientInstance.getId().getInventionId();
    }

    public Integer deletePatentDocument(PatentDocument persistentInstance) {
        // TODO Auto-generated method stub
        int deletedCount = entityManager.createQuery(
                "delete from PatentDocument where id.inventionId="
                        + persistentInstance.getId().getInventionId()
                        + " and id.itemList=" + persistentInstance.getId().getItemList()
        ).executeUpdate();
        return deletedCount;
    }

    public Integer savePatentEditDate(PatentEditDate transientInstance) {
        // TODO Auto-generated method stub
        Query query = entityManager.createQuery("select max(p.id.editItemList) from PatentEditDate p "
                + " where p.id.inventionId=" + transientInstance.getId().getInventionId());

        java.lang.Integer count = (java.lang.Integer) query.getSingleResult();
        if (count == null)
            count = 1;
        transientInstance.getId().setEditItemList(count + 1);
        entityManager.persist(transientInstance);
        return transientInstance.getId().getInventionId();
    }

    public Integer updatePatentEditDate(PatentEditDate transientInstance) {
        // TODO Auto-generated method stub
        entityManager.merge(transientInstance);
        return transientInstance.getId().getInventionId();
    }

    public Integer deletePatentEditDate(PatentEditDate persistentInstance) {
        // TODO Auto-generated method stub
        int deletedCount = entityManager.createQuery(
                "delete from PatentEditDate where id.inventionId="
                        + persistentInstance.getId().getInventionId()
                        + " and id.editItemList=" + persistentInstance.getId().getEditItemList()
        ).executeUpdate();
        return deletedCount;
    }

    public Integer savePatentFeePayment(PatentFeePayment transientInstance) {
        // TODO Auto-generated method stub
        Query query = entityManager.createQuery("select max(p.id.itemList) from PatentFeePayment p "
                + " where p.id.inventionId=" + transientInstance.getId().getInventionId());

        java.lang.Integer count = (java.lang.Integer) query.getSingleResult();
        if (count == null)
            count = 1;
        transientInstance.getId().setItemList(count + 1);
        entityManager.persist(transientInstance);
        return transientInstance.getId().getInventionId();
    }

    public Integer updatePatentFeePayment(PatentFeePayment transientInstance) {
        // TODO Auto-generated method stub
        entityManager.merge(transientInstance);
        return transientInstance.getId().getInventionId();
    }

    public Integer deletePatentFeePayment(PatentFeePayment persistentInstance) {
        // TODO Auto-generated method stub

        int deletedCount = entityManager.createQuery(
                "delete from PatentFeePayment where id.inventionId="
                        + persistentInstance.getId().getInventionId()
                        + " and id.itemList=" + persistentInstance.getId().getItemList()
                        + " and id.years=" + persistentInstance.getId().getYears()
        ).executeUpdate();
        return deletedCount;
    }

    public Integer savePatentInherited(PatentInherited transientInstance) {
        // TODO Auto-generated method stub
        Query query = entityManager.createQuery("select max(p.id.inheritedItemList) from PatentInherited p "
                + " where p.id.inventionId=" + transientInstance.getId().getInventionId());

        java.lang.Integer count = (java.lang.Integer) query.getSingleResult();
        if (count == null)
            count = 1;
        transientInstance.getId().setInheritedItemList(count + 1);
        entityManager.persist(transientInstance);
        return transientInstance.getId().getInventionId();
    }

    public Integer updatePatentInherited(PatentInherited transientInstance) {
        // TODO Auto-generated method stub
        entityManager.merge(transientInstance);
        return transientInstance.getId().getInventionId();
    }

    public Integer deletePatentInherited(PatentInherited persistentInstance) {
        // TODO Auto-generated method stub
        int deletedCount = entityManager.createQuery(
                "delete from PatentInherited where id.inventionId="
                        + persistentInstance.getId().getInventionId()
                        + " and id.inheritedItemList=" + persistentInstance.getId().getInheritedItemList()
        ).executeUpdate();
        return deletedCount;
    }

    public Integer savePatentRightholder(PatentRightholder transientInstance) {
        // TODO Auto-generated method stub
        Query query = entityManager.createQuery("select max(p.id.rightholderItemList) from PatentRightholder p "
                + " where p.id.inventionId=" + transientInstance.getId().getInventionId());

        java.lang.Integer count = (java.lang.Integer) query.getSingleResult();
        if (count == null)
            count = 1;
        transientInstance.getId().setRightholderItemList(count + 1);
        entityManager.persist(transientInstance);
        return transientInstance.getId().getInventionId();
    }

    public Integer updatePatentRightholder(PatentRightholder transientInstance) {
        // TODO Auto-generated method stub
        entityManager.merge(transientInstance);
        return transientInstance.getId().getInventionId();
    }

    public Integer deletePatentRightholder(PatentRightholder persistentInstance) {
        // TODO Auto-generated method stub
        int deletedCount = entityManager.createQuery(
                "delete from PatentRightholder where id.inventionId="
                        + persistentInstance.getId().getInventionId()
                        + " and id.rightholderItemList=" + persistentInstance.getId().getRightholderItemList()
        ).executeUpdate();
        return deletedCount;
    }

    public Integer saveTitle(Title transientInstance)
            throws DataAccessException {
        // TODO Auto-generated method stub
        entityManager.persist(transientInstance);
        return transientInstance.getTitleId();
    }

    public Integer updateTitle(Title transientInstance)
            throws DataAccessException {
        // TODO Auto-generated method stub
        entityManager.merge(transientInstance);
        return transientInstance.getTitleId();
    }

    public Integer deleteTitle(Title persistentInstance)
            throws DataAccessException {
        // TODO Auto-generated method stub
        int deletedCount = entityManager.createQuery(
                "delete from Title where titleId="
                        + persistentInstance.getTitleId()).executeUpdate();
        return deletedCount;
    }

    public Title findTitleById(Integer titleId)
            throws DataAccessException {
        // TODO Auto-generated method stub
        return entityManager.find(Title.class, titleId);
    }

    public List searchTitle(Title persistentInstance, Paging pagging,
                            String keySearch) throws DataAccessException {
        // TODO Auto-generated method stub
        StringBuffer sb = new StringBuffer("");
        if (keySearch != null && keySearch.trim().length() > 0) {
            sb.append(" where ( p.academicTitleName like '%" + keySearch.trim()
                    + "%' ) ");
        }
        ArrayList transList = new ArrayList();
        Query query = entityManager.createQuery(" select p from Title p "
                + sb.toString(), Title.class);
        query.setFirstResult((pagging.getPageNo() - 1) * pagging.getPageSize());
        query.setMaxResults(pagging.getPageSize());
        transList.add(query.getResultList());

        query = entityManager.createQuery("select count(p) from Title p "
                + sb.toString());
        long count = (Long) query.getSingleResult();
        transList.add(String.valueOf(count));
        return transList;
    }

    public Integer saveJournalPapersJournal(
            JournalPapersJournal transientInstance) {
        // TODO Auto-generated method stub
        entityManager.persist(transientInstance);
        return transientInstance.getJournalPapersId();
    }

    public Integer updateJournalPapersJournal(
            JournalPapersJournal transientInstance) {
        // TODO Auto-generated method stub
        entityManager.merge(transientInstance);
        return transientInstance.getJournalPapersId();
    }

    public Integer deleteJournalPapersJournal(
            JournalPapersJournal persistentInstance) {
        // TODO Auto-generated method stub
        int deletedCount = entityManager.createQuery(
                "delete from JournalPapersJournal where journalPapersId="
                        + persistentInstance.getJournalPapersId()).executeUpdate();
        return deletedCount;
    }

    public JournalPapersJournal findJournalPapersJournalsById(
            Integer journalPapersId) {
        // TODO Auto-generated method stub
        return entityManager.find(JournalPapersJournal.class, journalPapersId);
		/*// find docAssignMapping
				if(userid!=null && copyright!=null){
						Query	query = entityManager.createQuery("select x from DocAssignMapping x where x.id.refId=:refId and x.id.refType=:refType ");
							query.setParameter("refId", copyright.getCopyrightId());
							query.setParameter("refType",ServiceConstant.DOC_TYPE_COPYRIGHT);
							List<DocAssignMapping> docAssignList=query.getResultList();
							
							HashMap<String,String> map = new HashMap<String,String>();
							for (DocAssignMapping i : docAssignList) map.put(i.getId().getUserId(),i.getId().getUserId());
							copyright.setDocAssignMapping(map);
							if(userid!=null)
								copyright.setIsdocAssign(map.containsKey(userid));
				}*/
    }

    public Integer saveJournalPapersConference(
            JournalPapersConference transientInstance) {
        // TODO Auto-generated method stub
        entityManager.persist(transientInstance);
        return transientInstance.getJournalPapersId();
    }

    public Integer updateJournalPapersConference(
            JournalPapersConference transientInstance) {
        // TODO Auto-generated method stub
        entityManager.merge(transientInstance);
        return transientInstance.getJournalPapersId();
    }

    public Integer deleteJournalPapersConference(
            JournalPapersConference persistentInstance) {
        // TODO Auto-generated method stub
        int deletedCount = entityManager.createQuery(
                "delete from JournalPapersConference where journalPapersId="
                        + persistentInstance.getJournalPapersId()).executeUpdate();
        return deletedCount;
    }

    public JournalPapersConference findJournalPapersConferencesById(
            Integer journalPapersId) {
        // TODO Auto-generated method stub
        return entityManager.find(JournalPapersConference.class, journalPapersId);
		/*// find docAssignMapping
				if(userid!=null && copyright!=null){
						Query	query = entityManager.createQuery("select x from DocAssignMapping x where x.id.refId=:refId and x.id.refType=:refType ");
							query.setParameter("refId", copyright.getCopyrightId());
							query.setParameter("refType",ServiceConstant.DOC_TYPE_COPYRIGHT);
							List<DocAssignMapping> docAssignList=query.getResultList();
							
							HashMap<String,String> map = new HashMap<String,String>();
							for (DocAssignMapping i : docAssignList) map.put(i.getId().getUserId(),i.getId().getUserId());
							copyright.setDocAssignMapping(map);
							if(userid!=null)
								copyright.setIsdocAssign(map.containsKey(userid));
				}*/
    }

    public List<ResearcherGroup> listResearcherGroup(
            Integer researcherId) {
        // TODO Auto-generated method stub
        Query query = entityManager.createQuery(
                " select p from ResearcherGroup p where p.id.researcherId=:researcherId ",
                ResearcherGroup.class);
        query.setParameter("researcherId", researcherId);
        return query.getResultList();
    }

    public ResearchProjectDocument findResearchProjectDocumentById(
            ResearchProjectDocumentPK id) {
        // TODO Auto-generated method stub
        return entityManager.find(ResearchProjectDocument.class, id);
    }

    public ResearchProjectPayment findResearchProjectPaymentById(
            ResearchProjectPaymentPK id) {
        // TODO Auto-generated method stub
        return entityManager.find(ResearchProjectPayment.class, id);
    }

    public ResearchProjectProgress findResearchProjectProgressById(
            ResearchProjectProgressPK id) {
        // TODO Auto-generated method stub
        return entityManager.find(ResearchProjectProgress.class, id);
    }

    public ResearchProjectResearcher findResearchProjectResearcherById(
            ResearchProjectResearcherPK id) {
        // TODO Auto-generated method stub
        return entityManager.find(ResearchProjectResearcher.class, id);
    }

    public ResearchProjectWithdraw findResearchProjectWithdrawById(
            ResearchProjectWithdrawPK id) {
        // TODO Auto-generated method stub
        return entityManager.find(ResearchProjectWithdraw.class, id);
    }

    public JournalPapersDocument findJournalPapersDocumentById(
            JournalPapersDocumentPK id) {
        // TODO Auto-generated method stub
        return entityManager.find(JournalPapersDocument.class, id);
    }

    public JournalPapersWriter findJournalPapersWriterById(
            JournalPapersWriterPK id) {
        // TODO Auto-generated method stub
        return entityManager.find(JournalPapersWriter.class, id);
    }

    public CopyrightCreator findCopyrightCreatorById(CopyrightCreatorPK id) {
        // TODO Auto-generated method stub
        return entityManager.find(CopyrightCreator.class, id);
    }

    public RewardCreator findRewardCreatorById(RewardCreatorPK id) {
        // TODO Auto-generated method stub
        return entityManager.find(RewardCreator.class, id);
    }

    public PatentCreator findPatentCreatorById(PatentCreatorPK id) {
        // TODO Auto-generated method stub
        return entityManager.find(PatentCreator.class, id);
    }

    public PatentDocument findPatentDocumentById(PatentDocumentPK id) {
        // TODO Auto-generated method stub
        return entityManager.find(PatentDocument.class, id);
    }

    public PatentEditDate findPatentEditDateById(PatentEditDatePK id) {
        // TODO Auto-generated method stub
        return entityManager.find(PatentEditDate.class, id);
    }

    public PatentFeePayment findPatentFeePaymentById(PatentFeePaymentPK id) {
        // TODO Auto-generated method stub
        return entityManager.find(PatentFeePayment.class, id);
    }

    public PatentInherited findPatentInheritedById(PatentInheritedPK id) {
        // TODO Auto-generated method stub
        return entityManager.find(PatentInherited.class, id);
    }

    public PatentRightholder findPatentRightholderById(PatentRightholderPK id) {
        // TODO Auto-generated method stub
        return entityManager.find(PatentRightholder.class, id);
    }

    public Utilization findUtilizationItemById(UtilizationPK id) {
        // TODO Auto-generated method stub
        Utilization utilization = entityManager.find(Utilization.class, id);
		/*// find docAssignMapping
				if(userid!=null && utilization!=null){
						Query	query = entityManager.createQuery("select x from DocAssignMapping x where x.id.refId=:refId and x.id.refType=:refType ");
							query.setParameter("refId", utilization.getId().getUCopyrightId());
							query.setParameter("refType",ServiceConstant.DOC_TYPE_COPYRIGHT);
							List<DocAssignMapping> docAssignList=query.getResultList();
							
							HashMap<String,String> map = new HashMap<String,String>();
							for (DocAssignMapping i : docAssignList) map.put(i.getId().getUserId(),i.getId().getUserId());
							utilization.setDocAssignMapping(map);
							if(userid!=null)
								utilization.setIsdocAssign(map.containsKey(userid));
				}*/
        return utilization;
    }

    public Integer countJournalPapersWriter(
            JournalPapersWriter transientInstance) {
        // TODO Auto-generated method stub
        StringBuffer sb = new StringBuffer();
        sb.append("select sum(p.workLoadRatio) from JournalPapersWriter p "
                + " where p.id.journalPapersId=" + transientInstance.getId().getJournalPapersId());

        if (transientInstance.getId().getItemList() != null) {
            sb.append(" and p.id.itemList!=" + transientInstance.getId().getItemList());
        }
        Query query = entityManager.createQuery(sb.toString());
        java.lang.Long count = (java.lang.Long) query.getSingleResult();
        if (count == null)
            count = 0l;

        return count.intValue();
    }

    public Integer countResearchProjectResearcher(
            ResearchProjectResearcher transientInstance) {
        // TODO Auto-generated method stub
        StringBuffer sb = new StringBuffer();
        sb.append("select sum(p.workLoadRatio) from ResearchProjectResearcher p "
                + " where p.id.researchProjectId=" + transientInstance.getId().getResearchProjectId());

        if (transientInstance.getId().getItemList() != null) {
            sb.append(" and p.id.itemList!=" + transientInstance.getId().getItemList());
        }
        Query query = entityManager.createQuery(sb.toString());
        java.lang.Long count = (java.lang.Long) query.getSingleResult();
        if (count == null)
            count = 0l;

        return count.intValue();
    }

    private Integer utilizationItemList;

    @Column(name = "RESEARCH_PROJECT_ID", insertable = false, updatable = false)
    private Integer researchProjectId;


    public List<UtilizationDocument> listUtilizationDocument(Integer utilizationItemList, Integer researchProjectId) {
        // TODO Auto-generated method stub
        Query query = entityManager.createQuery(
                " select p from UtilizationDocument p where p.id.utilizationItemList=:utilizationItemList and "
                        + " p.id.researchProjectId=:researchProjectId  ",
                UtilizationDocument.class);
        query.setParameter("utilizationItemList", utilizationItemList);
        query.setParameter("researchProjectId", researchProjectId);
        return query.getResultList();
    }

    public Integer saveUtilizationDocument(UtilizationDocument transientInstance) {
        // TODO Auto-generated method stub
        Query query = entityManager.createQuery("select max(p.id.itemList) from UtilizationDocument p "
                + " where p.id.researchProjectId=" + transientInstance.getId().getResearchProjectId() +
                " and p.id.utilizationItemList=" + transientInstance.getId().getUtilizationItemList());

        java.lang.Integer count = (java.lang.Integer) query.getSingleResult();
        if (count == null)
            count = 1;
        transientInstance.getId().setItemList(count + 1);
        entityManager.persist(transientInstance);
        return transientInstance.getId().getUtilizationItemList();
    }

    public Integer updateUtilizationDocument(
            UtilizationDocument transientInstance) {
        // TODO Auto-generated method stub
        entityManager.merge(transientInstance);
        return transientInstance.getId().getUtilizationItemList();
    }

    public Integer deleteUtilizationDocument(
            UtilizationDocument persistentInstance) {
        // TODO Auto-generated method stub
        int deletedCount = entityManager.createQuery(
                "delete from UtilizationDocument where id.researchProjectId="
                        + persistentInstance.getId().getResearchProjectId()
                        + " and id.utilizationItemList=" + persistentInstance.getId().getUtilizationItemList()
                        + " and id.itemList=" + persistentInstance.getId().getItemList()
        ).executeUpdate();
        return deletedCount;
    }

    public UtilizationDocument findUtilizationDocumentById(
            UtilizationDocumentPK id) {
        // TODO Auto-generated method stub
        return entityManager.find(UtilizationDocument.class, id);
    }

    public List<CopyrightDocument> listCopyrightDocument(Integer copyrightId) {
        // TODO Auto-generated method stub
        Query query = entityManager.createQuery(
                " select p from CopyrightDocument p where p.id.copyrightId=:copyrightId ",
                CopyrightDocument.class);
        query.setParameter("copyrightId", copyrightId);
        return query.getResultList();
    }

    public Integer saveCopyrightDocument(CopyrightDocument transientInstance) {
        // TODO Auto-generated method stub
        Query query = entityManager.createQuery("select max(p.id.itemList) from CopyrightDocument p "
                + " where p.id.copyrightId=" + transientInstance.getId().getCopyrightId());

        java.lang.Integer count = (java.lang.Integer) query.getSingleResult();
        if (count == null)
            count = 1;
        transientInstance.getId().setItemList(count + 1);
        entityManager.persist(transientInstance);
        return transientInstance.getId().getCopyrightId();
    }

    public Integer updateCopyrightDocument(CopyrightDocument transientInstance) {
        // TODO Auto-generated method stub
        entityManager.merge(transientInstance);
        return transientInstance.getId().getCopyrightId();
    }

    public Integer deleteCopyrightDocument(CopyrightDocument persistentInstance) {
        // TODO Auto-generated method stub
        int deletedCount = entityManager.createQuery(
                "delete from CopyrightDocument where id.copyrightId="
                        + persistentInstance.getId().getCopyrightId()
                        + " and id.itemList=" + persistentInstance.getId().getItemList()
        ).executeUpdate();
        return deletedCount;
    }

    public CopyrightDocument findCopyrightDocumentById(CopyrightDocumentPK id) {
        // TODO Auto-generated method stub
        return entityManager.find(CopyrightDocument.class, id);

    }

    public List<RewardDocument> listRewardDocument(Integer rewardId) {
        // TODO Auto-generated method stub
        Query query = entityManager.createQuery(
                " select p from RewardDocument p where p.id.rewardId=:rewardId ",
                RewardDocument.class);
        query.setParameter("rewardId", rewardId);
        return query.getResultList();
    }

    public Integer saveRewardDocument(RewardDocument transientInstance) {
        // TODO Auto-generated method stub
        Query query = entityManager.createQuery("select max(p.id.itemList) from RewardDocument p "
                + " where p.id.rewardId=" + transientInstance.getId().getRewardId());

        java.lang.Integer count = (java.lang.Integer) query.getSingleResult();
        if (count == null)
            count = 1;
        transientInstance.getId().setItemList(count + 1);
        entityManager.persist(transientInstance);
        return transientInstance.getId().getRewardId();
    }

    public Integer updateRewardDocument(RewardDocument transientInstance) {
        // TODO Auto-generated method stub
        entityManager.merge(transientInstance);
        return transientInstance.getId().getRewardId();
    }

    public Integer deleteRewardDocument(RewardDocument persistentInstance) {
        // TODO Auto-generated method stub
        int deletedCount = entityManager.createQuery(
                "delete from RewardDocument where id.rewardId="
                        + persistentInstance.getId().getRewardId()
                        + " and id.itemList=" + persistentInstance.getId().getItemList()
        ).executeUpdate();
        return deletedCount;
    }

    public RewardDocument findRewardDocumentById(RewardDocumentPK id) {
        // TODO Auto-generated method stub
        return entityManager.find(RewardDocument.class, id);
    }

}
