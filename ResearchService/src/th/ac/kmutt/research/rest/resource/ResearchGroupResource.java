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
import th.ac.kmutt.research.model.ResearchGroupM;
import th.ac.kmutt.research.service.ResearchService;
import th.ac.kmutt.research.xstream.common.ImakeMessage;
import th.ac.kmutt.research.xstream.common.ImakeResultMessage;
import th.ac.kmutt.research.xstream.common.Paging;

public class ResearchGroupResource extends BaseResource {
    private static final Logger logger = Logger.getLogger(ServiceConstant.LOG_APPENDER);
    @Autowired
    @Qualifier("researchServiceJpaImpl")
    private ResearchService researchService;
    //private ConsultantReportService consultantReportService;
    private com.thoughtworks.xstream.XStream xstream;

    public ResearchGroupResource() {
        super();
        logger.debug("into constructor ResearchGroupResource");
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
            xstream.processAnnotations(ResearchGroupM.class);// or xstream.autodetectAnnotations(true); (Auto-detect  Annotations)
            ResearchGroupM xsource = new ResearchGroupM();
            Object xtarget = xstream.fromXML(in);
            if (xtarget != null) {
                xsource = (ResearchGroupM) xtarget;
                if (xsource != null) {
                    ResearchGroup domain = new ResearchGroup();
                    BeanUtils.copyProperties(xsource, domain);
                    if (xsource.getServiceName() != null
                            && xsource.getServiceName().length() != 0) {
                        String serviceName = xsource.getServiceName();
                        if (serviceName.equals(ServiceConstant.RESEARCH_GROUP_FIND_BY_ID)) {
                            domain = researchService.findResearchGroupById(xsource.getResearchGroupId());
                            ImakeResultMessage imakeMessage = new ImakeResultMessage();
                            if (domain != null) {
                                List<ResearchGroupM> models = new ArrayList<ResearchGroupM>(1);
                                ResearchGroupM model = new ResearchGroupM();
                                BeanUtils.copyProperties(domain, model);
                                model.setPagging(null);
                                models.add(model);
                                imakeMessage.setResultListObj(models);
                            }
                            return getRepresentation(entity, imakeMessage, xstream);
                        } else if (serviceName.equals(ServiceConstant.RESEARCH_GROUP_SAVE)) {
                            java.sql.Timestamp now = new java.sql.Timestamp(new Date().getTime());
                            domain.setCreatedDate(now);
                            domain.setUpdatedDate(now);
                            int updateRecord = researchService.saveResearchGroup(domain);
                            return returnUpdateRecord(entity, xsource, updateRecord, null);
                        } else if (serviceName.equals(ServiceConstant.RESEARCH_GROUP_CHECK_UQ)) {

                            int updateRecord = researchService.checkUQResearchGroup(domain);
                            return returnUpdateRecord(entity, xsource, updateRecord, null);
                        } else if (serviceName.equals(ServiceConstant.RESEARCH_GROUP_UPDATE)) {
                            java.sql.Timestamp updatedDate = new java.sql.Timestamp(new Date().getTime());
                            domain.setUpdatedDate(updatedDate);
                            int updateRecord = researchService.updateResearchGroup(domain);
                            return returnUpdateRecord(entity, xsource, updateRecord, null);
                        } else if (serviceName.equals(ServiceConstant.RESEARCH_GROUP_ITEMS_DELETE)) {

                            String[] mcaIds = xsource.getIds();
                            //logger.debug("xbpsTerm.getMsIds()="+xbpsTerm.getMsIds());
                            int updateRecord = 0;
                            for (int i = 0; i < mcaIds.length; i++) {
                                ResearchGroup item = new ResearchGroup();
                                item.setResearchGroupId(Integer.parseInt(mcaIds[i]));
                                try {
                                    updateRecord = researchService.deleteResearchGroup(item);
                                } catch (Exception e) {
                                    // TODO Auto-generated catch block
                                    e.printStackTrace();
                                }
                            }
                            ImakeMessage message = new ImakeMessage("-9", "xx");
                            return returnUpdateRecord(entity, xsource, updateRecord, message);
                        } else if (serviceName.equals(ServiceConstant.RESEARCH_GROUP_DELETE)) {

                            int updateRecord = 0;
                            try {
                                updateRecord = researchService.deleteResearchGroup(domain);
                            } catch (Exception e) {
                                Throwable t = e.getCause();

                                while ((t != null) && !(t instanceof ConstraintViolationException)) {
                                    t = t.getCause();
                                }

                                if (t instanceof ConstraintViolationException) {
                                    updateRecord = -9;
                                }
                            }
                            ImakeMessage message = new ImakeMessage("-9", "xx");
                            return returnUpdateRecord(entity, xsource, updateRecord, message);
                        } else if (serviceName.equals(ServiceConstant.RESEARCH_GROUP_SEARCH)) {
                            Paging page = xsource.getPaging();
                            @SuppressWarnings("rawtypes")
                            List result = (List) researchService.searchResearchGroup(domain, page, xsource.getKeySearch());
                            if (result != null && result.size() == 2) {
                                java.util.ArrayList<ResearchGroup> domains = (java.util.ArrayList<ResearchGroup>) result
                                        .get(0);
                                String domains_size = (String) result.get(1);
                                ImakeResultMessage imakeMessage = new ImakeResultMessage();

                                List<ResearchGroupM> models = new ArrayList<ResearchGroupM>();
                                if (domains_size != null && domains_size.length() != 0)
                                    imakeMessage.setMaxRow(domains_size);
                                if (domains != null && domains.size() > 0) {
                                    models = getResearchGroupModels(domains);
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

    private List<ResearchGroupM> getResearchGroupModels(
            java.util.ArrayList<ResearchGroup> domains) {
        List<ResearchGroupM> models = new ArrayList<ResearchGroupM>(
                domains.size());
        for (ResearchGroup domain : domains) {
            ResearchGroupM model = new ResearchGroupM();
            BeanUtils.copyProperties(domain, model);
            model.setPagging(null);
            models.add(model);
        }
        return models;
    }

    private Representation returnUpdateRecord(Representation entity, ResearchGroupM model, int updateRecord, ImakeMessage resultMessage) {

        ImakeResultMessage imakeMessage = new ImakeResultMessage();
        List<ResearchGroupM> xsources = new ArrayList<ResearchGroupM>(1);
        model.setUpdateRecord(updateRecord);
        xsources.add(model);
        imakeMessage.setResultListObj(xsources);
        imakeMessage.setResultMessage(resultMessage);
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
