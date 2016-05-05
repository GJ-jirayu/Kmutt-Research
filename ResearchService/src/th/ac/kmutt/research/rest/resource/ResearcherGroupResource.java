package th.ac.kmutt.research.rest.resource;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.exception.ConstraintViolationException;
import org.restlet.representation.Representation;
import org.restlet.representation.Variant;
import org.restlet.resource.ResourceException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import th.ac.kmutt.research.constant.ServiceConstant;
import th.ac.kmutt.research.domain.ResearchGroup;
import th.ac.kmutt.research.domain.ResearcherGroup;
import th.ac.kmutt.research.domain.ResearcherGroupPK;
import th.ac.kmutt.research.model.ResearchGroupM;
import th.ac.kmutt.research.model.ResearcherGroupM;
import th.ac.kmutt.research.service.ResearchService;
import th.ac.kmutt.research.xstream.common.ImakeResultMessage;
import th.ac.kmutt.research.xstream.common.Paging;

public class ResearcherGroupResource extends BaseResource {
    private static final Logger logger = Logger.getLogger(ServiceConstant.LOG_APPENDER);
    @Autowired
    @Qualifier("researchServiceJpaImpl")
    private ResearchService researchService;
    //private ConsultantReportService consultantReportService;
    private com.thoughtworks.xstream.XStream xstream;

    public ResearcherGroupResource() {
        super();
        logger.debug("into constructor ResearcherGroup");
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
            xstream.processAnnotations(ResearcherGroupM.class);// or xstream.autodetectAnnotations(true); (Auto-detect  Annotations)
            ResearcherGroupM xsource = new ResearcherGroupM();
            Object xtarget = xstream.fromXML(in);
            if (xtarget != null) {
                xsource = (ResearcherGroupM) xtarget;
                if (xsource != null) {
                    ResearcherGroup domain = new ResearcherGroup();
                    BeanUtils.copyProperties(xsource, domain);
                    ResearcherGroupPK pk = new ResearcherGroupPK();
                    if (xsource.getResearcherId() != null)
                        pk.setResearcherId(xsource.getResearcherId());
                    if (xsource.getResearchGroupId() != null)
                        pk.setResearchGroupId(xsource.getResearchGroupId());
                    domain.setId(pk);
                    if (xsource.getResearchGroup() != null && xsource.getResearchGroup().getResearchGroupId() != null) {
                        ResearchGroup researchGroup = new ResearchGroup();
                        BeanUtils.copyProperties(xsource.getResearchGroup(), researchGroup);
                        domain.setResearchGroup(researchGroup);
                    }


                    if (xsource.getServiceName() != null
                            && xsource.getServiceName().length() != 0) {
                        String serviceName = xsource.getServiceName();
                        if (serviceName.equals(ServiceConstant.RESEARCHER_GROUP_FIND_BY_ID)) {
                            domain = researchService.findResearcherGroupById(xsource.getResearchGroupId(), xsource.getResearcherId());
                            ImakeResultMessage imakeMessage = new ImakeResultMessage();
                            if (domain != null) {
                                List<ResearcherGroupM> models = new ArrayList<ResearcherGroupM>(1);
                                ResearcherGroupM model = new ResearcherGroupM();
                                BeanUtils.copyProperties(domain, model);
                                if (domain.getResearchGroup() != null) {
                                    ResearchGroupM researchGroupM = new ResearchGroupM();
                                    BeanUtils.copyProperties(domain.getResearchGroup(), researchGroupM);
                                    model.setResearchGroup(researchGroupM);
                                }
                                model.setPagging(null);
                                models.add(model);
                                imakeMessage.setResultListObj(models);
                            }
                            return getRepresentation(entity, imakeMessage, xstream);
                        } else if (serviceName.equals(ServiceConstant.RESEARCHER_GROUP_SAVE)) {
                            java.sql.Timestamp now = new java.sql.Timestamp(new Date().getTime());
                            domain.setCreatedDate(now);
                            domain.setUpdatedDate(now);
                            int updateRecord = researchService.saveResearcherGroup(domain);
                            return returnUpdateRecord(entity, xsource, updateRecord);
                        } else if (serviceName.equals(ServiceConstant.RESEARCHER_GROUP_UPDATE)) {
                            java.sql.Timestamp updatedDate = new java.sql.Timestamp(new Date().getTime());
                            domain.setUpdatedDate(updatedDate);
                            int updateRecord = researchService.updateResearcherGroup(domain);
                            return returnUpdateRecord(entity, xsource, updateRecord);
                        } else if (serviceName.equals(ServiceConstant.RESEARCHER_GROUP_ITEMS_DELETE)) {

							/*String[] mcaIds=xsource.getMcaIds().split(",");
							//logger.debug("xbpsTerm.getMsIds()="+xbpsTerm.getMsIds());
							int updateRecord=0;
							for (int i = 0; i <mcaIds.length; i++) {
								th.co.aoe.makedev.missconsult.hibernate.bean.MissCandidate item = new th.co.aoe.makedev.missconsult.hibernate.bean.MissCandidate();
								item.setMcaId(Long.parseLong(mcaIds[i]));
								updateRecord=missCandidateService.deleteMissCandidate(item);
							}
							return returnUpdateRecord(entity,xsource,updateRecord);*/
                        } else if (serviceName.equals(ServiceConstant.RESEARCHER_GROUP_DELETE)) {
                            int updateRecord = 0;
                            try {
                                researchService.deleteResearcherGroup(domain);
                            } catch (Exception e) {
                                Throwable t = e.getCause();

                                while ((t != null) && !(t instanceof ConstraintViolationException)) {
                                    t = t.getCause();
                                }

                                if (t instanceof ConstraintViolationException) {
                                    updateRecord = -9;
                                }
                            }
                            return returnUpdateRecord(entity, xsource, updateRecord);
                        } else if (serviceName.equals(ServiceConstant.RESEARCHER_GROUP_SEARCH)) {
                            Paging page = xsource.getPaging();
                            @SuppressWarnings("rawtypes")
                            List result = (List) researchService.searchResearcherGroup(domain, page, xsource.getKeySearch());
                            if (result != null && result.size() == 2) {
                                java.util.ArrayList<ResearcherGroup> domains = (java.util.ArrayList<ResearcherGroup>) result
                                        .get(0);
                                String domains_size = (String) result.get(1);
                                ImakeResultMessage imakeMessage = new ImakeResultMessage();

                                List<ResearcherGroupM> models = new ArrayList<ResearcherGroupM>();
                                if (domains_size != null && domains_size.length() != 0)
                                    imakeMessage.setMaxRow(domains_size);
                                if (domains != null && domains.size() > 0) {
                                    models = getResearcherGroupModels(domains);
                                }
                                imakeMessage.setResultListObj(models);
                                return getRepresentation(entity, imakeMessage, xstream);
                            }
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

    private List<ResearcherGroupM> getResearcherGroupModels(
            java.util.ArrayList<ResearcherGroup> domains) {
        List<ResearcherGroupM> models = new ArrayList<ResearcherGroupM>(
                domains.size());
        for (ResearcherGroup domain : domains) {
            ResearcherGroupM model = new ResearcherGroupM();
            BeanUtils.copyProperties(domain, model);
            model.setPagging(null);
            models.add(model);
        }
        return models;
    }

    private Representation returnUpdateRecord(Representation entity, ResearcherGroupM model, int updateRecord) {
        ImakeResultMessage imakeMessage = new ImakeResultMessage();
        List<ResearcherGroupM> xsources = new ArrayList<ResearcherGroupM>(1);
        model.setUpdateRecord(updateRecord);
        xsources.add(model);
        imakeMessage.setResultListObj(xsources);
        return getRepresentation(entity, imakeMessage, xstream);
    }

    @Override
    protected Representation get(Variant variant) throws ResourceException {
        // TODO Auto-generated method stub
        return null;
    }


    public com.thoughtworks.xstream.XStream getXstream() {
        return xstream;
    }

    public void setXstream(com.thoughtworks.xstream.XStream xstream) {
        this.xstream = xstream;
    }

}
