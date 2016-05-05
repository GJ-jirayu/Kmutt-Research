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
import th.ac.kmutt.research.domain.Organization;
import th.ac.kmutt.research.domain.Position;
import th.ac.kmutt.research.domain.ResearchGroup;
import th.ac.kmutt.research.domain.Researcher;
import th.ac.kmutt.research.domain.ResearcherGroup;
import th.ac.kmutt.research.domain.ResearcherGroupPK;
import th.ac.kmutt.research.domain.Title;
import th.ac.kmutt.research.model.OrganizationM;
import th.ac.kmutt.research.model.PositionM;
import th.ac.kmutt.research.model.ResearchGroupM;
import th.ac.kmutt.research.model.ResearcherGroupM;
import th.ac.kmutt.research.model.ResearcherM;
import th.ac.kmutt.research.model.TitleM;
import th.ac.kmutt.research.service.ResearchService;
import th.ac.kmutt.research.xstream.common.ImakeResultMessage;
import th.ac.kmutt.research.xstream.common.Paging;

public class ResearcherResource extends BaseResource {
    private static final Logger logger = Logger.getLogger(ServiceConstant.LOG_APPENDER);
    @Autowired
    @Qualifier("researchServiceJpaImpl")
    private ResearchService researchService;
    //private ConsultantReportService consultantReportService;
    private com.thoughtworks.xstream.XStream xstream;

    public ResearcherResource() {
        super();
        logger.debug("into constructor ResearcherResource");
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
            xstream.processAnnotations(ResearcherM.class);// or xstream.autodetectAnnotations(true); (Auto-detect  Annotations)
            ResearcherM xsource = new ResearcherM();
            Object xtarget = xstream.fromXML(in);
            if (xtarget != null) {
                xsource = (ResearcherM) xtarget;
                if (xsource != null) {
                    Researcher domain = new Researcher();
                    BeanUtils.copyProperties(xsource, domain);

                    ResearcherGroup researcherGroup = new ResearcherGroup();
                    if (xsource.getResearcherGroup() != null) {
                        BeanUtils.copyProperties(xsource.getResearcherGroup(), researcherGroup);
                        ResearcherGroupPK pk = new ResearcherGroupPK();
                        if (xsource.getResearcherGroup().getResearcherId() != null)
                            pk.setResearcherId(xsource.getResearcherGroup().getResearcherId());
                        if (xsource.getResearcherGroup().getResearchGroupId() != null)
                            pk.setResearchGroupId(xsource.getResearcherGroup().getResearchGroupId());
                        researcherGroup.setId(pk);
                        if (xsource.getResearcherGroup().getResearchGroup() != null && xsource.getResearcherGroup().getResearchGroup().getResearchGroupId() != null) {
                            ResearchGroup researchGroup = new ResearchGroup();
                            BeanUtils.copyProperties(xsource.getResearcherGroup().getResearchGroup(), researchGroup);
                            researcherGroup.setResearchGroup(researchGroup);
                        }
                    }

                    if (xsource.getPosition() != null) {
                        Position position = new Position();
                        BeanUtils.copyProperties(xsource.getPosition(), position);
                        domain.setPosition(position);
                    }
                    if (xsource.getAcademicPosition() != null) {
                        Position position = new Position();
                        BeanUtils.copyProperties(xsource.getAcademicPosition(), position);
                        domain.setAcademicPosition(position);
                    }
                    if (xsource.getAcademicTitle() != null) {
                        Title titleM = new Title();
                        BeanUtils.copyProperties(xsource.getAcademicTitle(), titleM);
                        domain.setAcademicTitle(titleM);
                    }
                    if (xsource.getTitle() != null) {
                        Title titleM = new Title();
                        BeanUtils.copyProperties(xsource.getTitle(), titleM);
                        domain.setTitle(titleM);
                    }

                    if (xsource.getOrganization() != null) {
                        Organization organizationM = new Organization();
                        BeanUtils.copyProperties(xsource.getOrganization(), organizationM);
                        domain.setOrganization(organizationM);
                    }

                    if (xsource.getServiceName() != null
                            && xsource.getServiceName().length() != 0) {
                        String serviceName = xsource.getServiceName();
                        if (serviceName.equals(ServiceConstant.RESEARCHER_FIND_BY_ID)) {
                            domain = researchService.findResearcherById(xsource.getResearcherId());
                            ImakeResultMessage imakeMessage = new ImakeResultMessage();
                            if (domain != null) {
                                List<ResearcherM> models = new ArrayList<ResearcherM>(1);
                                ResearcherM model = new ResearcherM();
                                BeanUtils.copyProperties(domain, model);
                                if (domain.getPosition() != null) {
                                    PositionM position = new PositionM();
                                    BeanUtils.copyProperties(domain.getPosition(), position);
                                    model.setPosition(position);
                                }
                                if (domain.getAcademicPosition() != null) {
                                    PositionM position = new PositionM();
                                    BeanUtils.copyProperties(domain.getAcademicPosition(), position);
                                    model.setAcademicPosition(position);
                                }
                                if (domain.getAcademicTitle() != null) {
                                    TitleM titleM = new TitleM();
                                    BeanUtils.copyProperties(domain.getAcademicTitle(), titleM);
                                    model.setAcademicTitle(titleM);
                                }
                                if (domain.getTitle() != null) {
                                    TitleM titleM = new TitleM();
                                    BeanUtils.copyProperties(domain.getTitle(), titleM);
                                    model.setTitle(titleM);
                                }

                                if (domain.getOrganization() != null) {
                                    OrganizationM organizationM = new OrganizationM();
                                    BeanUtils.copyProperties(domain.getOrganization(), organizationM);
                                    model.setOrganization(organizationM);
                                }
                                List<ResearcherGroupM> researcherGroupM = listResearcherGroup(xsource.getResearcherId());
                                model.setResearcherGroups(researcherGroupM);
                                model.setPagging(null);
                                models.add(model);
                                imakeMessage.setResultListObj(models);
                            }
                            return getRepresentation(entity, imakeMessage, xstream);
                        } else if (serviceName.equals(ServiceConstant.RESEARCHER_GROUP_LIST)) {
                            List<ResearcherGroupM> researcherGroupms = listResearcherGroup(researcherGroup.getId().getResearcherId());

                            ImakeResultMessage imakeMessage = new ImakeResultMessage();
                            List<ResearcherM> models = new ArrayList<ResearcherM>(1);
                            xsource.setResearcherGroups(researcherGroupms);
                            models.add(xsource);
                            imakeMessage.setResultListObj(models);
                            return getRepresentation(entity, imakeMessage, xstream);

                        } else if (serviceName.equals(ServiceConstant.RESEARCHER_GROUP_SAVE)) {
                            java.sql.Timestamp now = new java.sql.Timestamp(new Date().getTime());
                            domain.setCreatedDate(now);
                            domain.setUpdatedDate(now);
                            int updateRecord = researchService.saveResearcherGroup(researcherGroup);
                            return returnUpdateRecord(entity, xsource, updateRecord);
                        } else if (serviceName.equals(ServiceConstant.RESEARCHER_GROUP_UPDATE)) {
                            java.sql.Timestamp updatedDate = new java.sql.Timestamp(new Date().getTime());
                            domain.setUpdatedDate(updatedDate);
                            int updateRecord = researchService.updateResearcherGroup(researcherGroup);
                            return returnUpdateRecord(entity, xsource, updateRecord);
                        } else if (serviceName.equals(ServiceConstant.RESEARCHER_GROUP_DELETE)) {
                            int updateRecord = 0;
                            try {
                                researchService.deleteResearcherGroup(researcherGroup);
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
                        } else if (serviceName.equals(ServiceConstant.RESEARCHER_SAVE)) {
                            java.sql.Timestamp now = new java.sql.Timestamp(new Date().getTime());
                            domain.setCreatedDate(now);
                            domain.setUpdatedDate(now);
                            int updateRecord = researchService.saveResearcher(domain);
                            return returnUpdateRecord(entity, xsource, updateRecord);
                        } else if (serviceName.equals(ServiceConstant.RESEARCHER_UPDATE)) {
                            java.sql.Timestamp updatedDate = new java.sql.Timestamp(new Date().getTime());
                            domain.setUpdatedDate(updatedDate);
                            int updateRecord = researchService.updateResearcher(domain);
                            return returnUpdateRecord(entity, xsource, updateRecord);
                        } else if (serviceName.equals(ServiceConstant.RESEARCHER_ITEMS_DELETE)) {

                            String[] ids = xsource.getIds();
                            //logger.debug("xbpsTerm.getMsIds()="+xbpsTerm.getMsIds());
                            int updateRecord = 0;
                            for (int i = 0; i < ids.length; i++) {
                                Researcher item = new Researcher();
                                item.setResearcherId(Integer.parseInt(ids[i]));
                                try {
                                    updateRecord = researchService.deleteResearcher(item);
                                } catch (Exception e) {
                                    Throwable t = e.getCause();

                                    while ((t != null) && !(t instanceof ConstraintViolationException)) {
                                        t = t.getCause();
                                    }

                                    if (t instanceof ConstraintViolationException) {
                                        updateRecord = -9;
                                    }
                                }
                            }
                            return returnUpdateRecord(entity, xsource, updateRecord);
                        } else if (serviceName.equals(ServiceConstant.RESEARCHER_DELETE)) {
                            int updateRecord = 0;
                            try {
                                researchService.deleteResearcher(domain);
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
                        } else if (serviceName.equals(ServiceConstant.RESEARCHER_SEARCH)) {
                            Paging page = xsource.getPaging();
                            @SuppressWarnings("rawtypes")
                            List result = (List) researchService.searchResearcher(domain, page, xsource.getKeySearch());
                            if (result != null && result.size() == 2) {
                                java.util.ArrayList<Researcher> domains = (java.util.ArrayList<Researcher>) result
                                        .get(0);
                                String domains_size = (String) result.get(1);
                                ImakeResultMessage imakeMessage = new ImakeResultMessage();

                                List<ResearcherM> models = new ArrayList<ResearcherM>();
                                if (domains_size != null && domains_size.length() != 0)
                                    imakeMessage.setMaxRow(domains_size);
                                if (domains != null && domains.size() > 0) {
                                    models = getResearcherModels(domains);
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

    private List<ResearcherM> getResearcherModels(
            java.util.ArrayList<Researcher> domains) {
        List<ResearcherM> models = new ArrayList<ResearcherM>(
                domains.size());
        for (Researcher domain : domains) {
            ResearcherM model = new ResearcherM();
            BeanUtils.copyProperties(domain, model);

            if (domain.getPosition() != null) {
                PositionM position = new PositionM();
                BeanUtils.copyProperties(domain.getPosition(), position);
                model.setPosition(position);
            }
            if (domain.getAcademicPosition() != null) {
                PositionM position = new PositionM();
                BeanUtils.copyProperties(domain.getAcademicPosition(), position);
                model.setAcademicPosition(position);
            }
            if (domain.getAcademicTitle() != null) {
                TitleM titleM = new TitleM();
                BeanUtils.copyProperties(domain.getAcademicTitle(), titleM);
                model.setAcademicTitle(titleM);
            }
            if (domain.getTitle() != null) {
                TitleM titleM = new TitleM();
                BeanUtils.copyProperties(domain.getTitle(), titleM);
                model.setTitle(titleM);
            }

            if (domain.getOrganization() != null) {
                OrganizationM organizationM = new OrganizationM();
                BeanUtils.copyProperties(domain.getOrganization(), organizationM);
                model.setOrganization(organizationM);
            }

			/*if(domain.getOrganization()!=null){
				OrganizationM organization =new OrganizationM();
				BeanUtils.copyProperties(domain.getOrganization(), organization);
				model.setOrganization(organization);(organization);
			}*/
            model.setPagging(null);
            models.add(model);
        }
        return models;
    }

    private List<ResearcherGroupM> listResearcherGroup(Integer researcherId) {
        List<ResearcherGroup> researcherGroups = researchService.listResearcherGroup(researcherId);
        List<ResearcherGroupM> researcherGroupms = new ArrayList<ResearcherGroupM>(
                researcherGroups.size());
        for (ResearcherGroup researcherGroupd : researcherGroups) {
            ResearcherGroupM researcherGroupm = new ResearcherGroupM();
            BeanUtils.copyProperties(researcherGroupd, researcherGroupm);
            researcherGroupm.setPagging(null);
            if (researcherGroupd.getId() != null) {
                researcherGroupm.setResearcherId(researcherGroupd.getId().getResearcherId());
                researcherGroupm.setResearchGroupId(researcherGroupd.getId().getResearchGroupId());
            }
            if (researcherGroupd.getResearchGroup() != null) {
                ResearchGroupM researchGroupM = new ResearchGroupM();
                BeanUtils.copyProperties(researcherGroupd.getResearchGroup(), researchGroupM);
                researcherGroupm.setResearchGroup(researchGroupM);
            }
            researcherGroupms.add(researcherGroupm);
        }
        return researcherGroupms;
    }

    private Representation returnUpdateRecord(Representation entity, ResearcherM model, int updateRecord) {
        ImakeResultMessage imakeMessage = new ImakeResultMessage();
        List<ResearcherM> xsources = new ArrayList<ResearcherM>(1);
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
