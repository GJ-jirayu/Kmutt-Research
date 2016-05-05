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
import th.ac.kmutt.research.domain.OrganizationExt;
import th.ac.kmutt.research.model.OrganizationExtM;
import th.ac.kmutt.research.service.ResearchService;
import th.ac.kmutt.research.xstream.common.ImakeResultMessage;
import th.ac.kmutt.research.xstream.common.Paging;

public class OrganizationExtResource extends BaseResource {
    private static final Logger logger = Logger.getLogger(ServiceConstant.LOG_APPENDER);
    @Autowired
    @Qualifier("researchServiceJpaImpl")
    private ResearchService researchService;
    //private ConsultantReportService consultantReportService;
    private com.thoughtworks.xstream.XStream xstream;

    public OrganizationExtResource() {
        super();
        logger.debug("into constructor OrganizationExtResource");
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
            xstream.processAnnotations(OrganizationExtM.class);// or xstream.autodetectAnnotations(true); (Auto-detect  Annotations)
            OrganizationExtM xsource = new OrganizationExtM();
            Object xtarget = xstream.fromXML(in);
            if (xtarget != null) {
                xsource = (OrganizationExtM) xtarget;
                if (xsource != null) {
                    OrganizationExt domain = new OrganizationExt();
                    BeanUtils.copyProperties(xsource, domain);
                    if (xsource.getServiceName() != null
                            && xsource.getServiceName().length() != 0) {
                        String serviceName = xsource.getServiceName();
                        if (serviceName.equals(ServiceConstant.ORGANIZATION_EXT_FIND_BY_ID)) {
                            domain = researchService.findOrganizationExtById(xsource.getOrganizationExtId());
                            ImakeResultMessage imakeMessage = new ImakeResultMessage();
                            if (domain != null) {
                                List<OrganizationExtM> models = new ArrayList<OrganizationExtM>(1);
                                OrganizationExtM model = new OrganizationExtM();
                                BeanUtils.copyProperties(domain, model);
                                model.setPagging(null);
                                models.add(model);
                                imakeMessage.setResultListObj(models);
                            }
                            return getRepresentation(entity, imakeMessage, xstream);
                        } else if (serviceName.equals(ServiceConstant.ORGANIZATION_EXT_SAVE)) {
                            java.sql.Timestamp now = new java.sql.Timestamp(new Date().getTime());
                            domain.setCreatedDate(now);
                            domain.setUpdatedDate(now);
                            int updateRecord = researchService.saveOrganizationExt(domain);
                            return returnUpdateRecord(entity, xsource, updateRecord);
                        } else if (serviceName.equals(ServiceConstant.ORGANIZATION_EXT_UPDATE)) {
                            java.sql.Timestamp updatedDate = new java.sql.Timestamp(new Date().getTime());
                            domain.setUpdatedDate(updatedDate);
                            int updateRecord = researchService.updateOrganizationExt(domain);
                            return returnUpdateRecord(entity, xsource, updateRecord);
                        } else if (serviceName.equals(ServiceConstant.ORGANIZATION_EXT_ITEMS_DELETE)) {

                            String[] ids = xsource.getIds();
                            //logger.debug("xbpsTerm.getMsIds()="+xbpsTerm.getMsIds());
                            int updateRecord = 0;
                            for (int i = 0; i < ids.length; i++) {
                                OrganizationExt item = new OrganizationExt();
                                item.setOrganizationExtId(Integer.parseInt(ids[i]));
                                try {
                                    updateRecord = researchService.deleteOrganizationExt(item);
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
                        } else if (serviceName.equals(ServiceConstant.ORGANIZATION_EXT_DELETE)) {
                            int updateRecord = 0;
                            try {
                                researchService.deleteOrganizationExt(domain);
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
                        } else if (serviceName.equals(ServiceConstant.ORGANIZATION_EXT_SEARCH)) {
                            Paging page = xsource.getPaging();
                            @SuppressWarnings("rawtypes")
                            List result = (List) researchService.searchOrganizationExt(domain, page, xsource.getKeySearch());
                            if (result != null && result.size() == 2) {
                                java.util.ArrayList<OrganizationExt> domains = (java.util.ArrayList<OrganizationExt>) result
                                        .get(0);
                                String domains_size = (String) result.get(1);
                                ImakeResultMessage imakeMessage = new ImakeResultMessage();

                                List<OrganizationExtM> models = new ArrayList<OrganizationExtM>();
                                if (domains_size != null && domains_size.length() != 0)
                                    imakeMessage.setMaxRow(domains_size);
                                if (domains != null && domains.size() > 0) {
                                    models = getOrganizationExtModels(domains);
                                }
                                imakeMessage.setResultListObj(models);
                                return getRepresentation(entity, imakeMessage, xstream);
                            }
                        } else if (serviceName.equals(ServiceConstant.ORGANIZATION_EXT_CHECK_UQ)) {

                            int updateRecord = researchService.checkUQOrganizationExt(domain);
                            return returnUpdateRecord(entity, xsource, updateRecord);
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

    private List<OrganizationExtM> getOrganizationExtModels(
            java.util.ArrayList<OrganizationExt> domains) {
        List<OrganizationExtM> models = new ArrayList<OrganizationExtM>(
                domains.size());
        for (OrganizationExt domain : domains) {
            OrganizationExtM model = new OrganizationExtM();
            BeanUtils.copyProperties(domain, model);
            model.setPagging(null);
            models.add(model);
        }
        return models;
    }

    private Representation returnUpdateRecord(Representation entity, OrganizationExtM model, int updateRecord) {
        ImakeResultMessage imakeMessage = new ImakeResultMessage();
        List<OrganizationExtM> xsources = new ArrayList<OrganizationExtM>(1);
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
