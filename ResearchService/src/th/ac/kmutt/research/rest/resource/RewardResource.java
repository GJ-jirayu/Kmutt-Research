package th.ac.kmutt.research.rest.resource;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.restlet.representation.Representation;
import org.restlet.representation.Variant;
import org.restlet.resource.ResourceException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import th.ac.kmutt.research.constant.ServiceConstant;
import th.ac.kmutt.research.domain.DocAssignMapping;
import th.ac.kmutt.research.domain.DocAssignMappingPK;
import th.ac.kmutt.research.domain.Organization;
import th.ac.kmutt.research.domain.ResearchProject;
import th.ac.kmutt.research.domain.Reward;
import th.ac.kmutt.research.domain.RewardCreator;
import th.ac.kmutt.research.domain.RewardCreatorPK;
import th.ac.kmutt.research.domain.RewardDocument;
import th.ac.kmutt.research.domain.RewardDocumentPK;
import th.ac.kmutt.research.model.DocAssignMappingM;
import th.ac.kmutt.research.model.OrganizationM;
import th.ac.kmutt.research.model.ResearchProjectM;
import th.ac.kmutt.research.model.RewardCreatorM;
import th.ac.kmutt.research.model.RewardDocumentM;
import th.ac.kmutt.research.model.RewardM;
import th.ac.kmutt.research.portal.model.UserPortalM;
import th.ac.kmutt.research.service.ResearchService;
import th.ac.kmutt.research.xstream.common.ImakeResultMessage;
import th.ac.kmutt.research.xstream.common.Paging;

public class RewardResource extends BaseResource {
    private static final Logger logger = Logger.getLogger(ServiceConstant.LOG_APPENDER);
    @Autowired
    @Qualifier("researchServiceJpaImpl")
    private ResearchService researchService;
    //private ConsultantReportService consultantReportService;
    private com.thoughtworks.xstream.XStream xstream;

    public RewardResource() {
        super();
        logger.debug("into constructor RewardResource");
        // TODO Auto-generated constructor stub
    }

    @Override
    protected void doInit() throws ResourceException {
        // TODO Auto-generated method stub
        super.doInit();
        logger.debug("into doInit");
    }

    @Override
    protected Representation post(Representation entity, Variant variant)
            throws ResourceException {
        // TODO Auto-generated method stub
        logger.debug("into Post ConsultantReportResource 2");
        InputStream in = null;
        try {
            in = entity.getStream();
            xstream.processAnnotations(RewardM.class);// or xstream.autodetectAnnotations(true); (Auto-detect  Annotations)
            RewardM xsource = new RewardM();
            Object xtarget = xstream.fromXML(in);
            if (xtarget != null) {
                xsource = (RewardM) xtarget;
                if (xsource != null) {
                    Reward domain = new Reward();
                    BeanUtils.copyProperties(xsource, domain);
                    if (xsource.getResearchProject() != null && xsource.getResearchProject().getResearchProjectId() != null) {
                        ResearchProject researchProject = new ResearchProject();
                        BeanUtils.copyProperties(xsource.getResearchProject(), researchProject);
                        domain.setResearchProject(researchProject);
                    }
                    RewardCreator rewardCreator = new RewardCreator();
                    if (xsource.getRewardCreator() != null) {
                        BeanUtils.copyProperties(xsource.getRewardCreator(), rewardCreator);
                        RewardCreatorPK pk = new RewardCreatorPK();

                        if (xsource.getRewardCreator().getRewardId() != null)
                            pk.setRewardId(xsource.getRewardCreator().getRewardId());
                        if (xsource.getRewardCreator().getCreatorItemList() != null)
                            pk.setCreatorItemList(xsource.getRewardCreator().getCreatorItemList());
                        rewardCreator.setId(pk);

                        if (xsource.getRewardCreator().getOrganization() != null && xsource.getRewardCreator().getOrganization().getOrganizationId() != null) {
                            Organization organization = new Organization();
                            BeanUtils.copyProperties(xsource.getRewardCreator().getOrganization(), organization);
                            rewardCreator.setOrganization(organization);
                        }

                    }

                    RewardDocument patentDocument = new RewardDocument();
                    if (xsource.getRewardDocument() != null) {
                        BeanUtils.copyProperties(xsource.getRewardDocument(), patentDocument);
                        RewardDocumentPK pk = new RewardDocumentPK();
                        //pk.setDocumentId(1);

                        if (xsource.getRewardDocument().getRewardId() != null)
                            pk.setRewardId(xsource.getRewardDocument().getRewardId());
                        if (xsource.getRewardDocument().getItemList() != null)
                            pk.setItemList(xsource.getRewardDocument().getItemList());

                        patentDocument.setId(pk);
                    }


                    if (xsource.getServiceName() != null
                            && xsource.getServiceName().length() != 0) {
                        String serviceName = xsource.getServiceName();
                        if (serviceName.equals(ServiceConstant.REWARD_FIND_BY_ID)) {
                            domain = researchService.findRewardById(xsource.getRewardId(), xsource.getUserid());
                            ImakeResultMessage imakeMessage = new ImakeResultMessage();
                            if (domain != null) {
                                List<RewardM> models = new ArrayList<RewardM>(1);
                                RewardM model = new RewardM();
                                BeanUtils.copyProperties(domain, model);
                                if (domain.getResearchProject() != null) {
                                    ResearchProjectM researchProject = new ResearchProjectM();
                                    BeanUtils.copyProperties(domain.getResearchProject(), researchProject);
                                    model.setResearchProject(researchProject);
                                }
                                model.setPagging(null);
                                List<RewardCreatorM> rewardCreatorms = listRewardCreator(xsource.getRewardId());

                                model.setRewardCreators(rewardCreatorms);

                                List<DocAssignMappingM> docAssignMappingms = listDocAssignMapping(xsource.getRewardId());
                                model.setDocAssignMappings(docAssignMappingms);

                                models.add(model);
                                /*researchService.
								rewardCreators*/
                                imakeMessage.setResultListObj(models);
                            }
                            return getRepresentation(entity, imakeMessage, xstream);
                        } else if (serviceName.equals(ServiceConstant.REWARD_SAVE)) {
                            java.sql.Timestamp now = new java.sql.Timestamp(new Date().getTime());
                            domain.setCreatedDate(now);
                            domain.setUpdatedDate(now);
                            domain.setFlag(ServiceConstant.FLAG_ACTIVE);
                            int updateRecord = researchService.saveReward(domain);
                            return returnUpdateRecord(entity, xsource, updateRecord);
                        } else if (serviceName.equals(ServiceConstant.REWARD_UPDATE)) {
                            java.sql.Timestamp updatedDate = new java.sql.Timestamp(new Date().getTime());
                            domain.setUpdatedDate(updatedDate);
                            int updateRecord = researchService.updateReward(domain);
                            return returnUpdateRecord(entity, xsource, updateRecord);
                        } else if (serviceName.equals(ServiceConstant.REWARD_ITEMS_DELETE)) {

                            String[] ids = xsource.getIds();
                            //logger.debug("xbpsTerm.getMsIds()="+xbpsTerm.getMsIds());
                            int updateRecord = 0;
                            for (int i = 0; i < ids.length; i++) {
                                Reward item = new Reward();
                                item.setRewardId(Integer.parseInt(ids[i]));

                                item = researchService.findRewardById(item.getRewardId(), xsource.getUserid());

                                if (item.getFlag() != null && item.getFlag().equals(ServiceConstant.FLAG_INACTIVE))
                                    updateRecord = researchService.deleteReward(item);
                                else {
                                    item.setUpdateType(ServiceConstant.UPDATE_TYEP_FLAG);
                                    item.setFlag(ServiceConstant.FLAG_INACTIVE);
                                    updateRecord = researchService.updateFlagReward(item);
                                }

                            }
                            return returnUpdateRecord(entity, xsource, updateRecord);
                        } else if (serviceName.equals(ServiceConstant.REWARD_DELETE)) {
                            domain = researchService.findRewardById(domain.getRewardId(), xsource.getUserid());
                            int updateRecord = 0;
                            if (domain.getFlag() != null && domain.getFlag().equals(ServiceConstant.FLAG_INACTIVE))
                                updateRecord = researchService.deleteReward(domain);
                            else {
                                domain.setUpdateType(ServiceConstant.UPDATE_TYEP_FLAG);
                                domain.setFlag(ServiceConstant.FLAG_INACTIVE);
                                updateRecord = researchService.updateFlagReward(domain);
                            }

                            return returnUpdateRecord(entity, xsource, updateRecord);
                        } else if (serviceName.equals(ServiceConstant.REWARD_UPDATE_FLAG)) {
                            int updateRecord = researchService.updateFlagReward(domain);
                            return returnUpdateRecord(entity, xsource, updateRecord);
                        } else if (serviceName.equals(ServiceConstant.REWARD_SEARCH)) {
                            Paging page = xsource.getPaging();
                            @SuppressWarnings("rawtypes")
                            List result = (List) researchService.searchReward(domain, page, xsource.getKeySearch(), xsource.getUserid());
                            if (result != null && result.size() == 2) {
                                java.util.ArrayList<Reward> domains = (java.util.ArrayList<Reward>) result
                                        .get(0);
                                String domains_size = (String) result.get(1);
                                ImakeResultMessage imakeMessage = new ImakeResultMessage();

                                List<RewardM> models = new ArrayList<RewardM>();
                                if (domains_size != null && domains_size.length() != 0)
                                    imakeMessage.setMaxRow(domains_size);
                                if (domains != null && domains.size() > 0) {
                                    models = getRewardModels(domains);
                                }
                                imakeMessage.setResultListObj(models);
                                return getRepresentation(entity, imakeMessage, xstream);
                            }
                        } else if (serviceName.equals(ServiceConstant.REWARD_CREATOR_SAVE)) {

                            java.sql.Timestamp now = new java.sql.Timestamp(new Date().getTime());
                            rewardCreator.setCreatedDate(now);
                            rewardCreator.setUpdatedDate(now);
                            int updateRecord = researchService.saveRewardCreator(rewardCreator);
                            return returnUpdateRecord(entity, xsource, updateRecord);
                        } else if (serviceName.equals(ServiceConstant.REWARD_CREATOR_UPDATE)) {
                            java.sql.Timestamp updatedDate = new java.sql.Timestamp(new Date().getTime());
                            rewardCreator.setUpdatedDate(updatedDate);
                            RewardCreator old = researchService.findRewardCreatorById(rewardCreator.getId());
                            rewardCreator.setCreatedBy(old.getCreatedBy());
                            rewardCreator.setCreatedDate(old.getCreatedDate());
                            int updateRecord = researchService.updateRewardCreator(rewardCreator);
                            return returnUpdateRecord(entity, xsource, updateRecord);
                        } else if (serviceName.equals(ServiceConstant.REWARD_CREATOR_LIST)) {

                            List<RewardCreatorM> rewardCreatorms = listRewardCreator(rewardCreator.getId().getRewardId());
                            ImakeResultMessage imakeMessage = new ImakeResultMessage();
                            List<RewardM> models = new ArrayList<RewardM>(1);
                            xsource.setRewardCreators(rewardCreatorms);
                            models.add(xsource);
                            imakeMessage.setResultListObj(models);
                            return getRepresentation(entity, imakeMessage, xstream);

                        } else if (serviceName.equals(ServiceConstant.REWARD_CREATOR_DELETE)) {
                            int updateRecord = researchService.deleteRewardCreator(rewardCreator);
                            return returnUpdateRecord(entity, xsource, updateRecord);
                        } else if (serviceName.equals(ServiceConstant.REWARD_CREATOR_FIND_BY_ID)) {
                            RewardCreator rewardCreatord = researchService.findRewardCreatorById(rewardCreator.getId());
                            ImakeResultMessage imakeMessage = new ImakeResultMessage();
                            List<RewardM> models = new ArrayList<RewardM>(1);

                            RewardCreatorM rewardCreatorM = new RewardCreatorM();
                            if (rewardCreatord != null) {
                                BeanUtils.copyProperties(rewardCreatord, rewardCreatorM);
                                if (rewardCreatord.getId() != null) {
                                    rewardCreatorM.setRewardId(rewardCreatord.getId().getRewardId());
                                    rewardCreatorM.setCreatorItemList(rewardCreatord.getId().getCreatorItemList());
                                }
                                if (rewardCreatord.getOrganization() != null) {
                                    OrganizationM organization = new OrganizationM();
                                    BeanUtils.copyProperties(rewardCreatord.getOrganization(), organization);
                                    rewardCreatorM.setOrganization(organization);
                                }
                            }
                            xsource.setRewardCreator(rewardCreatorM);
                            models.add(xsource);
                            imakeMessage.setResultListObj(models);
                            return getRepresentation(entity, imakeMessage, xstream);
                        }
                        //patentDocument
                        else if (serviceName.equals(ServiceConstant.REWARD_DOCUMENT_SAVE)) {

                            java.sql.Timestamp now = new java.sql.Timestamp(new Date().getTime());
                            patentDocument.setCreatedDate(now);
                            patentDocument.setUpdatedDate(now);
                            int updateRecord = researchService.saveRewardDocument(patentDocument);
                            return returnUpdateRecord(entity, xsource, updateRecord);
                        } else if (serviceName.equals(ServiceConstant.REWARD_DOCUMENT_UPDATE)) {
                            java.sql.Timestamp updatedDate = new java.sql.Timestamp(new Date().getTime());
                            RewardDocument old = researchService.findRewardDocumentById(patentDocument.getId());
                            patentDocument.setCreatedBy(old.getCreatedBy());
                            patentDocument.setCreatedDate(old.getCreatedDate());
                            patentDocument.setUpdatedDate(updatedDate);
                            int updateRecord = researchService.updateRewardDocument(patentDocument);
                            return returnUpdateRecord(entity, xsource, updateRecord);
                        } else if (serviceName.equals(ServiceConstant.REWARD_DOCUMENT_LIST)) {

                            List<RewardDocumentM> patentDocumentms = listRewardDocument(patentDocument.getId().getRewardId());

                            ImakeResultMessage imakeMessage = new ImakeResultMessage();
                            List<RewardM> models = new ArrayList<RewardM>(1);
                            xsource.setRewardDocuments(patentDocumentms);
                            models.add(xsource);
                            imakeMessage.setResultListObj(models);
                            return getRepresentation(entity, imakeMessage, xstream);

                        } else if (serviceName.equals(ServiceConstant.REWARD_DOCUMENT_DELETE)) {
                            int updateRecord = researchService.deleteRewardDocument(patentDocument);
                            return returnUpdateRecord(entity, xsource, updateRecord);
                        } else if (serviceName.equals(ServiceConstant.REWARD_DOCUMENT_FIND_BY_ID)) {
                            RewardDocument patentDocumentd = researchService.findRewardDocumentById(patentDocument.getId());
                            ImakeResultMessage imakeMessage = new ImakeResultMessage();
                            List<RewardM> models = new ArrayList<RewardM>(1);

                            RewardDocumentM patentDocumentM = new RewardDocumentM();
                            if (patentDocumentd != null) {
                                BeanUtils.copyProperties(patentDocumentd, patentDocumentM);
                                if (patentDocumentd.getId() != null) {
                                    patentDocumentM.setItemList(patentDocumentd.getId().getItemList());
                                    patentDocumentM.setRewardId(patentDocumentd.getId().getRewardId());
                                    //patentDocumentM.setDocumentId(patentDocumentd.getId().getDocumentId());
                                }
                            }
                            xsource.setRewardDocument(patentDocumentM);
                            models.add(xsource);
                            imakeMessage.setResultListObj(models);
                            return getRepresentation(entity, imakeMessage, xstream);
                        }

                    } else {
                    }
                }

            }

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            logger.debug(" into Finally Call");
            try {
                if (in != null)
                    in.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;

    }

    private List<RewardCreatorM> listRewardCreator(Integer rewardId) {
        List<RewardCreator> rewardCreatords = researchService.listRewardCreator(rewardId);
        List<RewardCreatorM> rewardCreatorms = new ArrayList<RewardCreatorM>(
                rewardCreatords.size());
        for (RewardCreator rewardCreatord : rewardCreatords) {
            RewardCreatorM rewardCreatorm = new RewardCreatorM();
            BeanUtils.copyProperties(rewardCreatord, rewardCreatorm);
            if (rewardCreatord.getId() != null) {
                rewardCreatorm.setRewardId(rewardCreatord.getId().getRewardId());
                rewardCreatorm.setCreatorItemList(rewardCreatord.getId().getCreatorItemList());
            }
            if (rewardCreatord.getOrganization() != null) {
                OrganizationM organizationM = new OrganizationM();
                BeanUtils.copyProperties(rewardCreatord.getOrganization(), organizationM);
                rewardCreatorm.setOrganization(organizationM);
            }
            rewardCreatorm.setPagging(null);
            rewardCreatorms.add(rewardCreatorm);
        }
        return rewardCreatorms;
    }

    private List<RewardM> getRewardModels(
            java.util.ArrayList<Reward> domains) {
        List<RewardM> models = new ArrayList<RewardM>(
                domains.size());
        for (Reward domain : domains) {
            RewardM model = new RewardM();
            BeanUtils.copyProperties(domain, model);
            model.setPagging(null);
            models.add(model);
        }
        return models;
    }

    private List<DocAssignMappingM> listDocAssignMapping(Integer refId) {
        DocAssignMapping docAssignMapping = new DocAssignMapping();
        DocAssignMappingPK docPk = new DocAssignMappingPK();
        docPk.setRefId(refId);
        docPk.setRefType(ServiceConstant.DOC_TYPE_REWARD);
        docAssignMapping.setId(docPk);
        List<DocAssignMapping> docAssignMappingds = researchService.listDocAssignMapping(docAssignMapping);
        List<DocAssignMappingM> docAssignMappingms = new ArrayList<DocAssignMappingM>(
                docAssignMappingds.size());
        for (DocAssignMapping docAssignMappingd : docAssignMappingds) {
            DocAssignMappingM docAssignMappingm = new DocAssignMappingM();
            BeanUtils.copyProperties(docAssignMappingd, docAssignMappingm);
            docAssignMappingm.setPagging(null);

            if (docAssignMappingd.getId() != null) {
                docAssignMappingm.setRefType(docAssignMappingd.getId().getRefType());
                docAssignMappingm.setRefId(docAssignMappingd.getId().getRefId());
                docAssignMappingm.setUserId(docAssignMappingd.getId().getUserId());
            }
            if (docAssignMappingd.getUserPortal() != null) {
                UserPortalM userPortalM = new UserPortalM();
                BeanUtils.copyProperties(docAssignMappingd.getUserPortal(), userPortalM);
                docAssignMappingm.setUserPortal(userPortalM);
            }
            docAssignMappingms.add(docAssignMappingm);
        }
        return docAssignMappingms;
    }

    private Representation returnUpdateRecord(Representation entity, RewardM model, int updateRecord) {
        ImakeResultMessage imakeMessage = new ImakeResultMessage();
        List<RewardM> xsources = new ArrayList<RewardM>(1);
        model.setUpdateRecord(updateRecord);
        xsources.add(model);
        imakeMessage.setResultListObj(xsources);
        return getRepresentation(entity, imakeMessage, xstream);
    }

    private List<RewardDocumentM> listRewardDocument(Integer inventionId) {
        List<RewardDocument> documentds = researchService.listRewardDocument(inventionId);

        List<RewardDocumentM> documentms = new ArrayList<RewardDocumentM>(
                documentds.size());
        for (RewardDocument documentd : documentds) {
            RewardDocumentM documentm = new RewardDocumentM();
            BeanUtils.copyProperties(documentd, documentm);

            if (documentd.getId() != null) {
                documentm.setRewardId(documentd.getId().getRewardId());
                documentm.setItemList(documentd.getId().getItemList());
            }
            documentm.setPagging(null);
            documentms.add(documentm);
        }
        return documentms;
    }

    @Override
    protected Representation get(Variant variant) throws ResourceException {
        // TODO Auto-generated method stub
        Reward domain = researchService.findRewardById(3, null);


        ImakeResultMessage imakeMessage = new ImakeResultMessage();
        List<RewardM> models = new ArrayList<RewardM>(1);
        RewardM model = new RewardM();
        BeanUtils.copyProperties(domain, model);
        if (domain.getResearchProject() != null) {
            ResearchProjectM researchProject = new ResearchProjectM();
            BeanUtils.copyProperties(domain.getResearchProject(), researchProject);
            model.setResearchProject(researchProject);
        }
        model.setPagging(null);
        List<RewardCreatorM> rewardCreatorms = listRewardCreator(3);

        model.setRewardCreators(rewardCreatorms);

        List<DocAssignMappingM> docAssignMappingms = listDocAssignMapping(3);
        model.setDocAssignMappings(docAssignMappingms);

        models.add(model);
			/*researchService.
			rewardCreators*/
        imakeMessage.setResultListObj(models);
        return getRepresentation(null, imakeMessage, xstream);
    }


    public com.thoughtworks.xstream.XStream getXstream() {
        return xstream;
    }

    public void setXstream(com.thoughtworks.xstream.XStream xstream) {
        this.xstream = xstream;
    }

}
