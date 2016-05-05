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
import th.ac.kmutt.research.domain.FundingResourceType;
import th.ac.kmutt.research.model.FundingResourceTypeM;
import th.ac.kmutt.research.service.ResearchService;
import th.ac.kmutt.research.xstream.common.ImakeResultMessage;
import th.ac.kmutt.research.xstream.common.Paging;

public class FundingResourcesTypeResource extends BaseResource {
    private static final Logger logger = Logger.getLogger(ServiceConstant.LOG_APPENDER);
    @Autowired
    @Qualifier("researchServiceJpaImpl")
    private ResearchService researchService;
    //private ConsultantReportService consultantReportService;
    private com.thoughtworks.xstream.XStream xstream;

    public FundingResourcesTypeResource() {
        super();
        logger.debug("into constructor FundingResourceTypeResource");
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
            xstream.processAnnotations(FundingResourceTypeM.class);// or xstream.autodetectAnnotations(true); (Auto-detect  Annotations)
            FundingResourceTypeM xsource = new FundingResourceTypeM();
            Object xtarget = xstream.fromXML(in);
            if (xtarget != null) {
                xsource = (FundingResourceTypeM) xtarget;
                if (xsource != null) {
                    FundingResourceType domain = new FundingResourceType();
                    BeanUtils.copyProperties(xsource, domain);
                    if (xsource.getServiceName() != null
                            && xsource.getServiceName().length() != 0) {
                        String serviceName = xsource.getServiceName();
                        if (serviceName.equals(ServiceConstant.FUNDING_RESOURCES_TYPE_FIND_BY_ID)) {
                            domain = researchService.findFundingResourceTypeById(xsource.getFundingResourceTypeId());
                            ImakeResultMessage imakeMessage = new ImakeResultMessage();
                            if (domain != null) {
                                List<FundingResourceTypeM> models = new ArrayList<FundingResourceTypeM>(1);
                                FundingResourceTypeM model = new FundingResourceTypeM();
                                BeanUtils.copyProperties(domain, model);
                                model.setPagging(null);
                                models.add(model);
                                imakeMessage.setResultListObj(models);
                            }
                            return getRepresentation(entity, imakeMessage, xstream);
                        } else if (serviceName.equals(ServiceConstant.FUNDING_RESOURCES_TYPE_SAVE)) {
                            java.sql.Timestamp now = new java.sql.Timestamp(new Date().getTime());
                            domain.setCreatedDate(now);
                            domain.setUpdatedDate(now);
                            int updateRecord = researchService.saveFundingResourceType(domain);
                            return returnUpdateRecord(entity, xsource, updateRecord);
                        } else if (serviceName.equals(ServiceConstant.FUNDING_RESOURCES_TYPE_UPDATE)) {
                            java.sql.Timestamp updatedDate = new java.sql.Timestamp(new Date().getTime());
                            domain.setUpdatedDate(updatedDate);
                            int updateRecord = researchService.updateFundingResourceType(domain);
                            return returnUpdateRecord(entity, xsource, updateRecord);
                        } else if (serviceName.equals(ServiceConstant.FUNDING_RESOURCES_TYPE_ITEMS_DELETE)) {

                            String[] ids = xsource.getIds();
                            //logger.debug("xbpsTerm.getMsIds()="+xbpsTerm.getMsIds());
                            int updateRecord = 0;
                            for (int i = 0; i < ids.length; i++) {
                                FundingResourceType item = new FundingResourceType();
                                item.setFundingResourceTypeId(Integer.parseInt(ids[i]));
                                try {
                                    updateRecord = researchService.deleteFundingResourceType(item);
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
                        } else if (serviceName.equals(ServiceConstant.FUNDING_RESOURCES_TYPE_DELETE)) {
                            int updateRecord = 0;
                            try {
                                researchService.deleteFundingResourceType(domain);
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
                        } else if (serviceName.equals(ServiceConstant.FUNDING_RESOURCES_TYPE_SEARCH)) {
                            Paging page = xsource.getPaging();
                            @SuppressWarnings("rawtypes")
                            List result = (List) researchService.searchFundingResourceType(domain, page, xsource.getKeySearch());
                            if (result != null && result.size() == 2) {
                                java.util.ArrayList<FundingResourceType> domains = (java.util.ArrayList<FundingResourceType>) result
                                        .get(0);
                                String domains_size = (String) result.get(1);
                                ImakeResultMessage imakeMessage = new ImakeResultMessage();

                                List<FundingResourceTypeM> models = new ArrayList<FundingResourceTypeM>();
                                if (domains_size != null && domains_size.length() != 0)
                                    imakeMessage.setMaxRow(domains_size);
                                if (domains != null && domains.size() > 0) {
                                    models = getFundingResourceTypeModels(domains);
                                }
                                imakeMessage.setResultListObj(models);
                                return getRepresentation(entity, imakeMessage, xstream);
                            }
                        } else if (serviceName.equals(ServiceConstant.FUNDING_RESOURCES_TYPE_CHECK_UQ)) {

                            int updateRecord = researchService.checkUQFundingResourceType(domain);
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

    private List<FundingResourceTypeM> getFundingResourceTypeModels(
            java.util.ArrayList<FundingResourceType> domains) {
        List<FundingResourceTypeM> models = new ArrayList<FundingResourceTypeM>(
                domains.size());
        for (FundingResourceType domain : domains) {
            FundingResourceTypeM model = new FundingResourceTypeM();
            BeanUtils.copyProperties(domain, model);
            model.setPagging(null);
            models.add(model);
        }
        return models;
    }

    private Representation returnUpdateRecord(Representation entity, FundingResourceTypeM model, int updateRecord) {
        ImakeResultMessage imakeMessage = new ImakeResultMessage();
        List<FundingResourceTypeM> xsources = new ArrayList<FundingResourceTypeM>(1);
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
