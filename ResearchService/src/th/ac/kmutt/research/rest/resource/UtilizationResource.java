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
import th.ac.kmutt.research.domain.ResearchProject;
import th.ac.kmutt.research.domain.Utilization;
import th.ac.kmutt.research.domain.UtilizationDocument;
import th.ac.kmutt.research.domain.UtilizationDocumentPK;
import th.ac.kmutt.research.domain.UtilizationPK;
import th.ac.kmutt.research.domain.UtilizationType;
import th.ac.kmutt.research.model.ResearchProjectM;
import th.ac.kmutt.research.model.UtilizationDocumentM;
import th.ac.kmutt.research.model.UtilizationM;
import th.ac.kmutt.research.model.UtilizationTypeM;
import th.ac.kmutt.research.service.ResearchService;
import th.ac.kmutt.research.xstream.common.ImakeResultMessage;
import th.ac.kmutt.research.xstream.common.Paging;

public class UtilizationResource extends BaseResource {
    private static final Logger logger = Logger.getLogger(ServiceConstant.LOG_APPENDER);
    @Autowired
    @Qualifier("researchServiceJpaImpl")
    private ResearchService researchService;
    //private ConsultantReportService consultantReportService;
    private com.thoughtworks.xstream.XStream xstream;

    public UtilizationResource() {
        super();
        logger.debug("into constructor UtilizationResource");
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
            xstream.processAnnotations(UtilizationM.class);// or xstream.autodetectAnnotations(true); (Auto-detect  Annotations)
            UtilizationM xsource = new UtilizationM();
            Object xtarget = xstream.fromXML(in);
            if (xtarget != null) {
                xsource = (UtilizationM) xtarget;
                if (xsource != null) {
                    Utilization domain = new Utilization();
                    UtilizationPK pk = new UtilizationPK();
                    BeanUtils.copyProperties(xsource, domain);
                    if (xsource.getResearchProject() != null) {
                        ResearchProject researchProject = new ResearchProject();
                        BeanUtils.copyProperties(xsource.getResearchProject(), researchProject);
                        domain.setResearchProject(researchProject);
                        pk.setResearchProject(researchProject);
                    }

                    if (xsource.getUtilizationType() != null) {
                        UtilizationType utilizationType = new UtilizationType();
                        BeanUtils.copyProperties(xsource.getUtilizationType(), utilizationType);
                        domain.setUtilizationType(utilizationType);

                    }
                    if (xsource.getUtilizationItemList() != null)
                        pk.setUtilizationItemList(xsource.getUtilizationItemList());
                    domain.setId(pk);

                    UtilizationDocument patentDocument = new UtilizationDocument();
                    if (xsource.getUtilizationDocument() != null) {
                        BeanUtils.copyProperties(xsource.getUtilizationDocument(), patentDocument);
                        UtilizationDocumentPK docpk = new UtilizationDocumentPK();
                        //pk.setDocumentId(1);

                        if (xsource.getUtilizationDocument().getResearchProjectId() != null)
                            docpk.setResearchProjectId(xsource.getUtilizationDocument().getResearchProjectId());
                        if (xsource.getUtilizationDocument().getUtilizationItemList() != null)
                            docpk.setUtilizationItemList(xsource.getUtilizationDocument().getUtilizationItemList());
                        if (xsource.getUtilizationDocument().getItemList() != null)
                            docpk.setItemList(xsource.getUtilizationDocument().getItemList());

                        patentDocument.setId(docpk);
                    }

                    if (xsource.getServiceName() != null
                            && xsource.getServiceName().length() != 0) {
                        String serviceName = xsource.getServiceName();
                        if (serviceName.equals(ServiceConstant.UTILIZATION_FIND_BY_ID)) {
                            ResearchProject researchProject = researchService.findResearchProjectById(xsource.getResearchProject().getResearchProjectId(), xsource.getUserid());
                            List<Utilization> utilizations = researchService.findUtilizationById(xsource.getResearchProject().getResearchProjectId());
                            UtilizationM model = new UtilizationM();
                            /*List<DocAssignMappingM>  docAssignMappingms = listDocAssignMapping(xsource.getCopyrightId());
							model.setDocAssignMappings(docAssignMappingms);*/
                            if (researchProject != null) {
                                ResearchProjectM researchProjectM = new ResearchProjectM();
                                BeanUtils.copyProperties(researchProject, researchProjectM);
                                List<UtilizationM> models = new ArrayList<UtilizationM>(
                                        utilizations.size());
                                for (Utilization u : utilizations) {
                                    UtilizationM um = new UtilizationM();
                                    BeanUtils.copyProperties(u, um, "researchProject", "utilizationType");
                                    if (u.getResearchProject() != null) {
                                        ResearchProjectM rm = new ResearchProjectM();
                                        BeanUtils.copyProperties(u.getResearchProject(), rm);
                                        um.setResearchProject(rm);
                                    }
                                    if (u.getUtilizationType() != null) {
                                        UtilizationTypeM utilizationTypeM = new UtilizationTypeM();
                                        BeanUtils.copyProperties(u.getUtilizationType(), utilizationTypeM);
                                        um.setUtilizationType(utilizationTypeM);
                                    }
                                    um.setUtilizationItemList(u.getId().getUtilizationItemList());

                                    um.setPagging(null);
                                    models.add(um);
                                }
                                model.setUtilizations(models);
                                model.setResearchProject(researchProjectM);
                            }
                            ImakeResultMessage imakeMessage = new ImakeResultMessage();
                            List<UtilizationM> models_return = new ArrayList<UtilizationM>(1);

                            //BeanUtils.copyProperties(domain,model);
                            model.setPagging(null);
                            models_return.add(model);
                            imakeMessage.setResultListObj(models_return);
                            return getRepresentation(entity, imakeMessage, xstream);

                        } else if (serviceName.equals(ServiceConstant.UTILIZATION_SAVE)) {
                            java.sql.Timestamp now = new java.sql.Timestamp(new Date().getTime());
                            domain.setCreatedDate(now);
                            domain.setUpdatedDate(now);
                            domain.setFlag(ServiceConstant.FLAG_ACTIVE);
                            int updateRecord = researchService.saveUtilization(domain);
                            return returnUpdateRecord(entity, xsource, updateRecord);
                        } else if (serviceName.equals(ServiceConstant.UTILIZATION_UPDATE)) {
                            java.sql.Timestamp updatedDate = new java.sql.Timestamp(new Date().getTime());
                            domain.setUpdatedDate(updatedDate);
                            Utilization old = researchService.findUtilizationItemById(domain.getId());
                            domain.setCreatedBy(old.getCreatedBy());
                            domain.setCreatedDate(old.getCreatedDate());
                            int updateRecord = researchService.updateUtilization(domain);
                            return returnUpdateRecord(entity, xsource, updateRecord);
                        } else if (serviceName.equals(ServiceConstant.UTILIZATION_ITEMS_DELETE)) {

                            String[] ids = xsource.getIds();
                            //logger.debug("xbpsTerm.getMsIds()="+xbpsTerm.getMsIds());
                            int updateRecord = 0;
                            for (int i = 0; i < ids.length; i++) {
                                String[] id_array = ids[i].split("_");
                                Utilization item = new Utilization();
                                UtilizationPK key = new UtilizationPK();

                                ResearchProject researchProject = new ResearchProject();
                                researchProject.setResearchProjectId(Integer.parseInt(id_array[0]));

                                key.setResearchProject(researchProject);
                                key.setUtilizationItemList(Integer.parseInt(id_array[1]));
                                item.setId(key);
								
								/*item = researchService.findUtilizationItemById(item.getId());
								
								if(item.getFlag()!=null && item.getFlag().equals(ServiceConstant.FLAG_INACTIVE))
									updateRecord=researchService.deleteUtilizationItem(item);
								else {
									item.setFlag(ServiceConstant.FLAG_INACTIVE);
									updateRecord=researchService.updateFlagUtilizationItem(item);
								}*/
                                updateRecord = researchService.deleteUtilizationItem(item);
                            }
                            return returnUpdateRecord(entity, xsource, updateRecord);
                        } else if (serviceName.equals(ServiceConstant.UTILIZATION_UPDATE_FLAG)) {
                            int updateRecord = researchService.updateFlagUtilizationItem(domain);
                            return returnUpdateRecord(entity, xsource, updateRecord);
                        } else if (serviceName.equals(ServiceConstant.UTILIZATION_DELETE)) {

                            domain = researchService.findUtilizationItemById(domain.getId());
                            int updateRecord = 0;
                            if (domain.getFlag() != null && domain.getFlag().equals(ServiceConstant.FLAG_INACTIVE))
                                updateRecord = researchService.deleteUtilizationItem(domain);
                                //updateRecord=researchService.deleteUtilization(domain);
                            else {
                                domain.setFlag(ServiceConstant.FLAG_INACTIVE);
                                updateRecord = researchService.updateFlagUtilizationItem(domain);
                            }

                            return returnUpdateRecord(entity, xsource, updateRecord);
                        } else if (serviceName.equals(ServiceConstant.UTILIZATION_LIST_DELETE)) {
                            int updateRecord = 0;
							/*domain = researchService.findUtilizationItemById(domain.getId());
						
							if(domain.getFlag()!=null && domain.getFlag().equals(ServiceConstant.FLAG_INACTIVE))
								updateRecord=researchService.deleteUtilizationItem(domain);
							else {
								domain.setFlag(ServiceConstant.FLAG_INACTIVE);
								updateRecord=researchService.updateFlagUtilizationItem(domain);
							}
							*/
                            updateRecord = researchService.deleteUtilizationItem(domain);
                            return returnUpdateRecord(entity, xsource, updateRecord);
                        } else if (serviceName.equals(ServiceConstant.UTILIZATION_SEARCH)) {
                            Paging page = xsource.getPaging();
                            @SuppressWarnings("rawtypes")
                            List result = (List) researchService.searchUtilization(domain, page, xsource.getKeySearch());
                            if (result != null && result.size() == 2) {
                                java.util.ArrayList<Utilization> domains = (java.util.ArrayList<Utilization>) result
                                        .get(0);
                                String domains_size = (String) result.get(1);
                                ImakeResultMessage imakeMessage = new ImakeResultMessage();

                                List<UtilizationM> models = new ArrayList<UtilizationM>();
                                if (domains_size != null && domains_size.length() != 0)
                                    imakeMessage.setMaxRow(domains_size);
                                if (domains != null && domains.size() > 0) {
                                    models = getUtilizationModels(domains);
                                }
                                imakeMessage.setResultListObj(models);
                                return getRepresentation(entity, imakeMessage, xstream);
                            }
                        } else if (serviceName.equals(ServiceConstant.UTILIZATION_ITEM_FIND_BY_ID)) {
                            Utilization utilizationd = researchService.findUtilizationItemById(domain.getId());
                            ImakeResultMessage imakeMessage = new ImakeResultMessage();
                            List<UtilizationM> models = new ArrayList<UtilizationM>(1);

                            UtilizationM utilizationM = new UtilizationM();
                            if (utilizationd != null) {
                                BeanUtils.copyProperties(utilizationd, utilizationM);
                                if (utilizationd.getId() != null) {

                                    if (utilizationd.getId().getResearchProject() != null) {
                                        ResearchProjectM researchProjectM = new ResearchProjectM();
                                        BeanUtils.copyProperties(utilizationd.getId().getResearchProject(), researchProjectM);
                                        utilizationM.setResearchProject(researchProjectM);
                                    }
                                    utilizationM.setUtilizationItemList(utilizationd.getId().getUtilizationItemList());
                                }
                                if (utilizationd.getResearchProject() != null) {
                                    ResearchProjectM researchProjectM = new ResearchProjectM();
                                    BeanUtils.copyProperties(utilizationd.getResearchProject(), researchProjectM);
                                    utilizationM.setResearchProject(researchProjectM);
                                }
                                if (utilizationd.getUtilizationType() != null) {
                                    UtilizationTypeM utilizationTypeM = new UtilizationTypeM();
                                    BeanUtils.copyProperties(utilizationd.getUtilizationType(), utilizationTypeM);
                                    utilizationM.setUtilizationType(utilizationTypeM);
                                }
                            }
                            //xsource.setUtilization(utilizationM);
                            models.add(utilizationM);
                            imakeMessage.setResultListObj(models);
                            return getRepresentation(entity, imakeMessage, xstream);
                        }

                        //patentDocument
                        else if (serviceName.equals(ServiceConstant.UTILIZATION_DOCUMENT_SAVE)) {

                            java.sql.Timestamp now = new java.sql.Timestamp(new Date().getTime());
                            patentDocument.setCreatedDate(now);
                            patentDocument.setUpdatedDate(now);
                            int updateRecord = researchService.saveUtilizationDocument(patentDocument);
                            return returnUpdateRecord(entity, xsource, updateRecord);
                        } else if (serviceName.equals(ServiceConstant.UTILIZATION_DOCUMENT_UPDATE)) {
                            java.sql.Timestamp updatedDate = new java.sql.Timestamp(new Date().getTime());
                            UtilizationDocument old = researchService.findUtilizationDocumentById(patentDocument.getId());
                            patentDocument.setCreatedBy(old.getCreatedBy());
                            patentDocument.setCreatedDate(old.getCreatedDate());
                            patentDocument.setUpdatedDate(updatedDate);
                            int updateRecord = researchService.updateUtilizationDocument(patentDocument);
                            return returnUpdateRecord(entity, xsource, updateRecord);
                        } else if (serviceName.equals(ServiceConstant.UTILIZATION_DOCUMENT_LIST)) {

                            List<UtilizationDocumentM> patentDocumentms = listUtilizationDocument(patentDocument.getId().getUtilizationItemList(),
                                    patentDocument.getId().getResearchProjectId());

                            ImakeResultMessage imakeMessage = new ImakeResultMessage();
                            List<UtilizationM> models = new ArrayList<UtilizationM>(1);
                            xsource.setUtilizationDocuments(patentDocumentms);
                            models.add(xsource);
                            imakeMessage.setResultListObj(models);
                            return getRepresentation(entity, imakeMessage, xstream);

                        } else if (serviceName.equals(ServiceConstant.UTILIZATION_DOCUMENT_DELETE)) {
                            int updateRecord = researchService.deleteUtilizationDocument(patentDocument);
                            return returnUpdateRecord(entity, xsource, updateRecord);
                        } else if (serviceName.equals(ServiceConstant.UTILIZATION_DOCUMENT_FIND_BY_ID)) {
                            UtilizationDocument patentDocumentd = researchService.findUtilizationDocumentById(patentDocument.getId());
                            ImakeResultMessage imakeMessage = new ImakeResultMessage();
                            List<UtilizationM> models = new ArrayList<UtilizationM>(1);

                            UtilizationDocumentM patentDocumentM = new UtilizationDocumentM();
                            if (patentDocumentd != null) {
                                BeanUtils.copyProperties(patentDocumentd, patentDocumentM);
                                if (patentDocumentd.getId() != null) {
                                    patentDocumentM.setItemList(patentDocumentd.getId().getItemList());
                                    patentDocumentM.setUtilizationItemList(patentDocumentd.getId().getUtilizationItemList());
                                    patentDocumentM.setResearchProjectId(patentDocumentd.getId().getResearchProjectId());
                                }
                            }
                            xsource.setUtilizationDocument(patentDocumentM);
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

    private List<UtilizationM> getUtilizationModels(
            java.util.ArrayList<Utilization> domains) {
        List<UtilizationM> models = new ArrayList<UtilizationM>(
                domains.size());
        for (Utilization domain : domains) {
            UtilizationM model = new UtilizationM();
            BeanUtils.copyProperties(domain, model, "researchProject", "utilizationType");
            if (domain.getResearchProject() != null) {
                ResearchProjectM researchProjectM = new ResearchProjectM();
                BeanUtils.copyProperties(domain.getResearchProject(), researchProjectM);
                model.setResearchProject(researchProjectM);
            }
            if (domain.getUtilizationType() != null) {
                UtilizationTypeM utilizationTypeM = new UtilizationTypeM();
                BeanUtils.copyProperties(domain.getUtilizationType(), utilizationTypeM);
                model.setUtilizationType(utilizationTypeM);
            }
            UtilizationPK pk = domain.getId();
            model.setUtilizationItemList(pk.getUtilizationItemList());
            model.setPagging(null);
            models.add(model);
        }
        return models;
    }

    private Representation returnUpdateRecord(Representation entity, UtilizationM model, int updateRecord) {
        ImakeResultMessage imakeMessage = new ImakeResultMessage();
        List<UtilizationM> xsources = new ArrayList<UtilizationM>(1);
        model.setUpdateRecord(updateRecord);
        xsources.add(model);
        imakeMessage.setResultListObj(xsources);
        return getRepresentation(entity, imakeMessage, xstream);
    }

    private List<UtilizationDocumentM> listUtilizationDocument(Integer utilizationItemList, Integer researchProjectId) {
        List<UtilizationDocument> documentds = researchService.listUtilizationDocument(utilizationItemList, researchProjectId);

        List<UtilizationDocumentM> documentms = new ArrayList<UtilizationDocumentM>(
                documentds.size());
        for (UtilizationDocument documentd : documentds) {
            UtilizationDocumentM documentm = new UtilizationDocumentM();
            BeanUtils.copyProperties(documentd, documentm);

            if (documentd.getId() != null) {
                documentm.setUtilizationItemList(documentd.getId().getUtilizationItemList());
                documentm.setResearchProjectId(documentd.getId().getResearchProjectId());
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
        ResearchProject researchProject = researchService.findResearchProjectById(46, null);
        List<Utilization> utilizations = researchService.findUtilizationById(46);
        UtilizationM model = new UtilizationM();
		/*List<DocAssignMappingM>  docAssignMappingms = listDocAssignMapping(xsource.getCopyrightId());
		model.setDocAssignMappings(docAssignMappingms);*/
        ResearchProjectM researchProjectM = new ResearchProjectM();
        BeanUtils.copyProperties(researchProject, researchProjectM);
        List<UtilizationM> models = new ArrayList<UtilizationM>(
                utilizations.size());
        for (Utilization u : utilizations) {
            UtilizationM um = new UtilizationM();
            BeanUtils.copyProperties(u, um, "researchProject", "utilizationType");
            if (u.getResearchProject() != null) {
                ResearchProjectM rm = new ResearchProjectM();
                BeanUtils.copyProperties(u.getResearchProject(), rm);
                um.setResearchProject(rm);
            }
            if (u.getUtilizationType() != null) {
                UtilizationTypeM utilizationTypeM = new UtilizationTypeM();
                BeanUtils.copyProperties(u.getUtilizationType(), utilizationTypeM);
                um.setUtilizationType(utilizationTypeM);
            }
            um.setUtilizationItemList(u.getId().getUtilizationItemList());

            um.setPagging(null);
            models.add(um);
        }
        model.setUtilizations(models);
        model.setResearchProject(researchProjectM);

        ImakeResultMessage imakeMessage = new ImakeResultMessage();
        List<UtilizationM> models_return = new ArrayList<UtilizationM>(1);

        //BeanUtils.copyProperties(domain,model);
        model.setPagging(null);
        models_return.add(model);
        imakeMessage.setResultListObj(models_return);
        return getRepresentation(null, imakeMessage, xstream);
    }


    public com.thoughtworks.xstream.XStream getXstream() {
        return xstream;
    }

    public void setXstream(com.thoughtworks.xstream.XStream xstream) {
        this.xstream = xstream;
    }

}