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
import th.ac.kmutt.research.domain.Copyright;
import th.ac.kmutt.research.domain.CopyrightCreator;
import th.ac.kmutt.research.domain.CopyrightCreatorPK;
import th.ac.kmutt.research.domain.CopyrightDocument;
import th.ac.kmutt.research.domain.CopyrightDocumentPK;
import th.ac.kmutt.research.domain.DocAssignMapping;
import th.ac.kmutt.research.domain.DocAssignMappingPK;
import th.ac.kmutt.research.domain.Organization;
import th.ac.kmutt.research.domain.ResearchProject;
import th.ac.kmutt.research.domain.WorkType;
import th.ac.kmutt.research.model.CopyrightCreatorM;
import th.ac.kmutt.research.model.CopyrightDocumentM;
import th.ac.kmutt.research.model.CopyrightM;
import th.ac.kmutt.research.model.DocAssignMappingM;
import th.ac.kmutt.research.model.OrganizationM;
import th.ac.kmutt.research.model.ResearchProjectM;
import th.ac.kmutt.research.model.WorkTypeM;
import th.ac.kmutt.research.portal.model.UserPortalM;
import th.ac.kmutt.research.service.ResearchService;
import th.ac.kmutt.research.xstream.common.ImakeResultMessage;
import th.ac.kmutt.research.xstream.common.Paging;

public class CopyRightResource extends BaseResource {
    private static final Logger logger = Logger.getLogger(ServiceConstant.LOG_APPENDER);
    @Autowired
    @Qualifier("researchServiceJpaImpl")
    private ResearchService researchService;
    //private ConsultantReportService consultantReportService;
    private com.thoughtworks.xstream.XStream xstream;

    public CopyRightResource() {
        super();
        logger.debug("into constructor ResearchCopyRightResource");
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
            xstream.processAnnotations(CopyrightM.class);// or xstream.autodetectAnnotations(true); (Auto-detect  Annotations)
            CopyrightM xsource = new CopyrightM();
            Object xtarget = xstream.fromXML(in);
            if (xtarget != null) {
                xsource = (CopyrightM) xtarget;
                if (xsource != null) {
                    Copyright domain = new Copyright();
                    BeanUtils.copyProperties(xsource, domain);

                    if (xsource.getResearchProject() != null && xsource.getResearchProject().getResearchProjectId() != null) {
                        ResearchProject researchProject = new ResearchProject();
                        BeanUtils.copyProperties(xsource.getResearchProject(), researchProject);
                        domain.setResearchProject(researchProject);
                    }

                    if (xsource.getInnovativeWorksType() != null && xsource.getInnovativeWorksType().getWorkTypeId() != null) {
                        WorkType worksType = new WorkType();
                        BeanUtils.copyProperties(xsource.getInnovativeWorksType(), worksType);
                        domain.setInnovativeWorksType(worksType);
                    }
                    CopyrightCreator copyrightCreator = new CopyrightCreator();
                    if (xsource.getCopyrightCreator() != null) {
                        BeanUtils.copyProperties(xsource.getCopyrightCreator(), copyrightCreator);
                        CopyrightCreatorPK pk = new CopyrightCreatorPK();

                        if (xsource.getCopyrightCreator().getCopyrightId() != null)
                            pk.setCopyrightId(xsource.getCopyrightCreator().getCopyrightId());
                        if (xsource.getCopyrightCreator().getCreatorItemList() != null)
                            pk.setCreatorItemList(xsource.getCopyrightCreator().getCreatorItemList());
                        copyrightCreator.setId(pk);

                        if (xsource.getCopyrightCreator().getOrganization() != null && xsource.getCopyrightCreator().getOrganization().getOrganizationId() != null) {
                            Organization organization = new Organization();
                            BeanUtils.copyProperties(xsource.getCopyrightCreator().getOrganization(), organization);
                            copyrightCreator.setOrganization(organization);
                        }
                    }

                    CopyrightDocument copyrightDocument = new CopyrightDocument();
                    if (xsource.getCopyrightDocument() != null) {
                        BeanUtils.copyProperties(xsource.getCopyrightDocument(), copyrightDocument);
                        CopyrightDocumentPK pk = new CopyrightDocumentPK();
                        //	pk.setDocumentId(1);

                        if (xsource.getCopyrightDocument().getCopyrightId() != null)
                            pk.setCopyrightId(xsource.getCopyrightDocument().getCopyrightId());
                        if (xsource.getCopyrightDocument().getItemList() != null)
                            pk.setItemList(xsource.getCopyrightDocument().getItemList());

                        copyrightDocument.setId(pk);
                    }


                    if (xsource.getServiceName() != null
                            && xsource.getServiceName().length() != 0) {
                        String serviceName = xsource.getServiceName();
                        if (serviceName.equals(ServiceConstant.COPYRIGHT_FIND_BY_ID)) {
                            domain = researchService.findResearchCopyRightById(xsource.getCopyrightId(), xsource.getUserid());
                            ImakeResultMessage imakeMessage = new ImakeResultMessage();
                            if (domain != null) {
                                List<CopyrightM> models = new ArrayList<CopyrightM>(1);
                                CopyrightM model = new CopyrightM();
                                BeanUtils.copyProperties(domain, model);

                                if (domain.getResearchProject() != null) {
                                    ResearchProjectM researchProjectM = new ResearchProjectM();
                                    BeanUtils.copyProperties(domain.getResearchProject(), researchProjectM);
                                    model.setResearchProject(researchProjectM);
                                }
                                if (domain.getInnovativeWorksType() != null) {
                                    WorkTypeM worksTypeM = new WorkTypeM();
                                    BeanUtils.copyProperties(domain.getInnovativeWorksType(), worksTypeM);
                                    model.setInnovativeWorksType(worksTypeM);
                                }

                                List<CopyrightCreatorM> copyrightCreatorms = listCopyrightCreator(xsource.getCopyrightId());

                                model.setCopyrightCreators(copyrightCreatorms);

                                List<DocAssignMappingM> docAssignMappingms = listDocAssignMapping(xsource.getCopyrightId());
                                model.setDocAssignMappings(docAssignMappingms);
                                List<CopyrightDocumentM> copyrightDocumentms = listCopyrightDocument(xsource.getCopyrightId());
                                model.setCopyrightDocuments(copyrightDocumentms);
                                model.setPagging(null);
                                models.add(model);

                                imakeMessage.setResultListObj(models);
                            }
                            return getRepresentation(entity, imakeMessage, xstream);
                        } else if (serviceName.equals(ServiceConstant.COPYRIGHT_SAVE)) {
                            java.sql.Timestamp now = new java.sql.Timestamp(new Date().getTime());
                            domain.setCreatedDate(now);
                            domain.setUpdatedDate(now);
                            domain.setFlag(ServiceConstant.FLAG_ACTIVE);
                            int updateRecord = researchService.saveCopyright(domain);
                            return returnUpdateRecord(entity, xsource, updateRecord);
                        } else if (serviceName.equals(ServiceConstant.COPYRIGHT_UPDATE)) {
                            java.sql.Timestamp updatedDate = new java.sql.Timestamp(new Date().getTime());
                            domain.setUpdatedDate(updatedDate);
                            int updateRecord = researchService.updateCopyright(domain);
                            return returnUpdateRecord(entity, xsource, updateRecord);
                        } else if (serviceName.equals(ServiceConstant.COPYRIGHT_ITEMS_DELETE)) {

                            String[] ids = xsource.getIds();
                            //logger.debug("xbpsTerm.getMsIds()="+xbpsTerm.getMsIds());
                            int updateRecord = 0;
                            for (int i = 0; i < ids.length; i++) {
                                Copyright item = new Copyright();
                                item.setCopyrightId(Integer.parseInt(ids[i]));

                                item = researchService.findResearchCopyRightById(item.getCopyrightId(), xsource.getUserid());
                                //int updateRecord=0;
                                if (item.getFlag() != null && item.getFlag().equals(ServiceConstant.FLAG_INACTIVE))
                                    updateRecord = researchService.deleteCopyright(item);
                                else {
                                    item.setUpdateType(ServiceConstant.UPDATE_TYEP_FLAG);
                                    item.setFlag(ServiceConstant.FLAG_INACTIVE);
                                    updateRecord = researchService.updateFlagCopyright(item);
                                }
                            }
                            return returnUpdateRecord(entity, xsource, updateRecord);
                        } else if (serviceName.equals(ServiceConstant.COPYRIGHT_DELETE)) {
                            domain = researchService.findResearchCopyRightById(domain.getCopyrightId(), xsource.getUserid());
                            int updateRecord = 0;
                            if (domain.getFlag() != null && domain.getFlag().equals(ServiceConstant.FLAG_INACTIVE))
                                updateRecord = researchService.deleteCopyright(domain);
                            else {
                                domain.setUpdateType(ServiceConstant.UPDATE_TYEP_FLAG);
                                domain.setFlag(ServiceConstant.FLAG_INACTIVE);
                                updateRecord = researchService.updateFlagCopyright(domain);
                            }


                            return returnUpdateRecord(entity, xsource, updateRecord);
                        } else if (serviceName.equals(ServiceConstant.COPYRIGHT_UPDATE_FLAG)) {
                            int updateRecord = researchService.updateFlagCopyright(domain);
                            return returnUpdateRecord(entity, xsource, updateRecord);
                        } else if (serviceName.equals(ServiceConstant.COPYRIGHT_SEARCH)) {
                            Paging page = xsource.getPaging();
                            @SuppressWarnings("rawtypes")
                            List result = (List) researchService.searchCopyright(domain, page, xsource.getKeySearch(), xsource.getUserid());
                            if (result != null && result.size() == 2) {
                                java.util.ArrayList<Copyright> domains = (java.util.ArrayList<Copyright>) result
                                        .get(0);
                                String domains_size = (String) result.get(1);
                                ImakeResultMessage imakeMessage = new ImakeResultMessage();

                                List<CopyrightM> models = new ArrayList<CopyrightM>();
                                if (domains_size != null && domains_size.length() != 0)
                                    imakeMessage.setMaxRow(domains_size);
                                if (domains != null && domains.size() > 0) {
                                    models = getCopyrightModels(domains);
                                }
                                imakeMessage.setResultListObj(models);
                                return getRepresentation(entity, imakeMessage, xstream);
                            }
                        } else if (serviceName.equals(ServiceConstant.COPYRIGHT_CREATOR_SAVE)) {

                            java.sql.Timestamp now = new java.sql.Timestamp(new Date().getTime());
                            copyrightCreator.setCreatedDate(now);
                            copyrightCreator.setUpdatedDate(now);

                            int updateRecord = researchService.saveCopyrightCreator(copyrightCreator);
                            return returnUpdateRecord(entity, xsource, updateRecord);
                        } else if (serviceName.equals(ServiceConstant.COPYRIGHT_CREATOR_UPDATE)) {
                            java.sql.Timestamp updatedDate = new java.sql.Timestamp(new Date().getTime());
                            copyrightCreator.setUpdatedDate(updatedDate);
                            CopyrightCreator old = researchService.findCopyrightCreatorById(copyrightCreator.getId());
                            copyrightCreator.setCreatedBy(old.getCreatedBy());
                            copyrightCreator.setCreatedDate(old.getCreatedDate());
                            int updateRecord = researchService.updateCopyrightCreator(copyrightCreator);
                            return returnUpdateRecord(entity, xsource, updateRecord);
                        } else if (serviceName.equals(ServiceConstant.COPYRIGHT_CREATOR_LIST)) {

                            List<CopyrightCreatorM> copyrightCreatorms = listCopyrightCreator(copyrightCreator.getId().getCopyrightId());
                            ImakeResultMessage imakeMessage = new ImakeResultMessage();
                            List<CopyrightM> models = new ArrayList<CopyrightM>(1);
                            xsource.setCopyrightCreators(copyrightCreatorms);
                            models.add(xsource);
                            imakeMessage.setResultListObj(models);
                            return getRepresentation(entity, imakeMessage, xstream);

                        } else if (serviceName.equals(ServiceConstant.COPYRIGHT_CREATOR_DELETE)) {
                            int updateRecord = researchService.deleteCopyrightCreator(copyrightCreator);
                            return returnUpdateRecord(entity, xsource, updateRecord);
                        } else if (serviceName.equals(ServiceConstant.COPYRIGHT_CREATOR_FIND_BY_ID)) {
                            CopyrightCreator copyrightCreatord = researchService.findCopyrightCreatorById(copyrightCreator.getId());
                            ImakeResultMessage imakeMessage = new ImakeResultMessage();
                            List<CopyrightM> models = new ArrayList<CopyrightM>(1);

                            CopyrightCreatorM copyrightCreatorM = new CopyrightCreatorM();
                            if (copyrightCreatord != null) {
                                BeanUtils.copyProperties(copyrightCreatord, copyrightCreatorM);
                                if (copyrightCreatord.getId() != null) {
                                    copyrightCreatorM.setCopyrightId(copyrightCreatord.getId().getCopyrightId());
                                    copyrightCreatorM.setCreatorItemList(copyrightCreatord.getId().getCreatorItemList());
                                }
                                if (copyrightCreatord.getOrganization() != null) {
                                    OrganizationM organization = new OrganizationM();
                                    BeanUtils.copyProperties(copyrightCreatord.getOrganization(), organization);
                                    copyrightCreatorM.setOrganization(organization);
                                }
                            }
                            xsource.setCopyrightCreator(copyrightCreatorM);
                            models.add(xsource);
                            imakeMessage.setResultListObj(models);
                            return getRepresentation(entity, imakeMessage, xstream);
                        }
                        //copyrightDocument
                        else if (serviceName.equals(ServiceConstant.COPYRIGHT_DOCUMENT_SAVE)) {

                            java.sql.Timestamp now = new java.sql.Timestamp(new Date().getTime());
                            copyrightDocument.setCreatedDate(now);
                            copyrightDocument.setUpdatedDate(now);
                            int updateRecord = researchService.saveCopyrightDocument(copyrightDocument);
                            return returnUpdateRecord(entity, xsource, updateRecord);
                        } else if (serviceName.equals(ServiceConstant.COPYRIGHT_DOCUMENT_UPDATE)) {
                            java.sql.Timestamp updatedDate = new java.sql.Timestamp(new Date().getTime());
                            CopyrightDocument old = researchService.findCopyrightDocumentById(copyrightDocument.getId());
                            copyrightDocument.setCreatedBy(old.getCreatedBy());
                            copyrightDocument.setCreatedDate(old.getCreatedDate());
                            copyrightDocument.setUpdatedDate(updatedDate);
                            int updateRecord = researchService.updateCopyrightDocument(copyrightDocument);
                            return returnUpdateRecord(entity, xsource, updateRecord);
                        } else if (serviceName.equals(ServiceConstant.COPYRIGHT_DOCUMENT_LIST)) {

                            List<CopyrightDocumentM> copyrightDocumentms = listCopyrightDocument(copyrightDocument.getId().getCopyrightId());

                            ImakeResultMessage imakeMessage = new ImakeResultMessage();
                            List<CopyrightM> models = new ArrayList<CopyrightM>(1);
                            xsource.setCopyrightDocuments(copyrightDocumentms);
                            models.add(xsource);
                            imakeMessage.setResultListObj(models);
                            return getRepresentation(entity, imakeMessage, xstream);

                        } else if (serviceName.equals(ServiceConstant.COPYRIGHT_DOCUMENT_DELETE)) {
                            int updateRecord = researchService.deleteCopyrightDocument(copyrightDocument);
                            return returnUpdateRecord(entity, xsource, updateRecord);
                        } else if (serviceName.equals(ServiceConstant.COPYRIGHT_DOCUMENT_FIND_BY_ID)) {
                            CopyrightDocument copyrightDocumentd = researchService.findCopyrightDocumentById(copyrightDocument.getId());
                            ImakeResultMessage imakeMessage = new ImakeResultMessage();
                            List<CopyrightM> models = new ArrayList<CopyrightM>(1);

                            CopyrightDocumentM copyrightDocumentM = new CopyrightDocumentM();
                            if (copyrightDocumentd != null) {
                                BeanUtils.copyProperties(copyrightDocumentd, copyrightDocumentM);
                                if (copyrightDocumentd.getId() != null) {
                                    copyrightDocumentM.setItemList(copyrightDocumentd.getId().getItemList());
                                    copyrightDocumentM.setCopyrightId(copyrightDocumentd.getId().getCopyrightId());
                                    //copyrightDocumentM.setDocumentId(copyrightDocumentd.getId().getDocumentId());
                                }
                            }
                            xsource.setCopyrightDocument(copyrightDocumentM);
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

    private List<CopyrightM> getCopyrightModels(
            java.util.ArrayList<Copyright> domains) {
        List<CopyrightM> models = new ArrayList<CopyrightM>(
                domains.size());
        for (Copyright domain : domains) {
            CopyrightM model = new CopyrightM();
            BeanUtils.copyProperties(domain, model);
            model.setPagging(null);
            models.add(model);
        }
        return models;
    }

    private List<CopyrightCreatorM> listCopyrightCreator(Integer copyrightId) {
        List<CopyrightCreator> copyrightCreatords = researchService.listCopyrightCreator(copyrightId);
        List<CopyrightCreatorM> copyrightCreatorms = new ArrayList<CopyrightCreatorM>(
                copyrightCreatords.size());
        for (CopyrightCreator copyrightCreatord : copyrightCreatords) {
            CopyrightCreatorM copyrightCreatorm = new CopyrightCreatorM();
            BeanUtils.copyProperties(copyrightCreatord, copyrightCreatorm);
            copyrightCreatorm.setPagging(null);

            if (copyrightCreatord.getId() != null) {
                copyrightCreatorm.setCopyrightId(copyrightCreatord.getId().getCopyrightId());
                copyrightCreatorm.setCreatorItemList(copyrightCreatord.getId().getCreatorItemList());
            }
            if (copyrightCreatord.getOrganization() != null) {
                OrganizationM organizationM = new OrganizationM();
                BeanUtils.copyProperties(copyrightCreatord.getOrganization(), organizationM);
                copyrightCreatorm.setOrganization(organizationM);
            }
            copyrightCreatorms.add(copyrightCreatorm);
        }
        return copyrightCreatorms;
    }

    private List<DocAssignMappingM> listDocAssignMapping(Integer copyrightId) {
        DocAssignMapping docAssignMapping = new DocAssignMapping();
        DocAssignMappingPK docPk = new DocAssignMappingPK();
        docPk.setRefId(copyrightId);
        docPk.setRefType(ServiceConstant.DOC_TYPE_COPYRIGHT);
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

    private List<CopyrightDocumentM> listCopyrightDocument(Integer inventionId) {
        List<CopyrightDocument> documentds = researchService.listCopyrightDocument(inventionId);

        List<CopyrightDocumentM> documentms = new ArrayList<CopyrightDocumentM>(
                documentds.size());
        for (CopyrightDocument documentd : documentds) {
            CopyrightDocumentM documentm = new CopyrightDocumentM();
            BeanUtils.copyProperties(documentd, documentm);

            if (documentd.getId() != null) {
                documentm.setCopyrightId(documentd.getId().getCopyrightId());
                documentm.setItemList(documentd.getId().getItemList());
            }
            documentm.setPagging(null);
            documentms.add(documentm);
        }
        return documentms;
    }

    private Representation returnUpdateRecord(Representation entity, CopyrightM model, int updateRecord) {
        ImakeResultMessage imakeMessage = new ImakeResultMessage();
        List<CopyrightM> xsources = new ArrayList<CopyrightM>(1);
        model.setUpdateRecord(updateRecord);
        xsources.add(model);
        imakeMessage.setResultListObj(xsources);
        return getRepresentation(entity, imakeMessage, xstream);
    }

    @Override
    protected Representation get(Variant variant) throws ResourceException {
        // TODO Auto-generated method stub
        Copyright domain = researchService.findResearchCopyRightById(3, null);
        ImakeResultMessage imakeMessage = new ImakeResultMessage();
        List<CopyrightM> models = new ArrayList<CopyrightM>(1);
        CopyrightM model = new CopyrightM();
        BeanUtils.copyProperties(domain, model);

        if (domain.getResearchProject() != null) {
            ResearchProjectM researchProjectM = new ResearchProjectM();
            BeanUtils.copyProperties(domain.getResearchProject(), researchProjectM);
            model.setResearchProject(researchProjectM);
        }
        if (domain.getInnovativeWorksType() != null) {
            WorkTypeM worksTypeM = new WorkTypeM();
            BeanUtils.copyProperties(domain.getInnovativeWorksType(), worksTypeM);
            model.setInnovativeWorksType(worksTypeM);
        }

        List<CopyrightCreatorM> copyrightCreatorms = listCopyrightCreator(3);

        model.setCopyrightCreators(copyrightCreatorms);

        List<DocAssignMappingM> docAssignMappingms = listDocAssignMapping(3);
        model.setDocAssignMappings(docAssignMappingms);
        List<CopyrightDocumentM> copyrightDocumentms = listCopyrightDocument(3);
        model.setCopyrightDocuments(copyrightDocumentms);
        model.setPagging(null);
        models.add(model);

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
