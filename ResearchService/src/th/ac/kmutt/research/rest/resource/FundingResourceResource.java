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
import th.ac.kmutt.research.domain.Country;
import th.ac.kmutt.research.domain.FundingResource;
import th.ac.kmutt.research.domain.FundingResourceType;
import th.ac.kmutt.research.model.CountryM;
import th.ac.kmutt.research.model.FundingResourceM;
import th.ac.kmutt.research.model.FundingResourceTypeM;
import th.ac.kmutt.research.service.ResearchService;
import th.ac.kmutt.research.xstream.common.ImakeResultMessage;
import th.ac.kmutt.research.xstream.common.Paging;

public class FundingResourceResource extends BaseResource {
    private static final Logger logger = Logger.getLogger(ServiceConstant.LOG_APPENDER);
    @Autowired
    @Qualifier("researchServiceJpaImpl")
    private ResearchService researchService;
    //private ConsultantReportService consultantReportService;
    private com.thoughtworks.xstream.XStream xstream;

    public FundingResourceResource() {
        super();
        logger.debug("into constructor FundingResourceResource");
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
            xstream.processAnnotations(FundingResourceM.class);// or xstream.autodetectAnnotations(true); (Auto-detect  Annotations)
            FundingResourceM xsource = new FundingResourceM();
            Object xtarget = xstream.fromXML(in);
            if (xtarget != null) {
                xsource = (FundingResourceM) xtarget;
                if (xsource != null) {
                    FundingResource domain = new FundingResource();
                    //fundingResourceType; countryM
                    BeanUtils.copyProperties(xsource, domain, "fundingResourceType");
                    if (xsource.getFundingResourceType() != null) {
                        FundingResourceType fundingResourceType = new FundingResourceType();
                        BeanUtils.copyProperties(xsource.getFundingResourceType(), fundingResourceType);
                        domain.setFundingResourceType(fundingResourceType);
                    }
                    if (xsource.getCountry() != null) {
                        Country country = new Country();
                        BeanUtils.copyProperties(xsource.getCountry(), country);
                        domain.setCountry(country);
                    }
                    if (xsource.getServiceName() != null
                            && xsource.getServiceName().length() != 0) {
                        String serviceName = xsource.getServiceName();
                        if (serviceName.equals(ServiceConstant.FUNDING_RESOURCES_FIND_BY_ID)) {
                            domain = researchService.findFundingResourceById(xsource.getFundingResourceId());
                            ImakeResultMessage imakeMessage = new ImakeResultMessage();
                            if (domain != null) {

                                List<FundingResourceM> models = new ArrayList<FundingResourceM>(1);
                                FundingResourceM model = new FundingResourceM();
                                //fundingResourceType; countryM
                                BeanUtils.copyProperties(domain, model, "fundingResourceType");
                                if (domain.getFundingResourceType() != null) {
                                    FundingResourceTypeM fundingResourceTypeM = new FundingResourceTypeM();
                                    BeanUtils.copyProperties(domain.getFundingResourceType(), fundingResourceTypeM);
                                    model.setFundingResourceType(fundingResourceTypeM);
                                }
                                if (domain.getCountry() != null) {
                                    CountryM countryM = new CountryM();
                                    BeanUtils.copyProperties(domain.getCountry(), countryM);
                                    model.setCountry(countryM);
                                }
                                model.setPagging(null);
                                models.add(model);
                                imakeMessage.setResultListObj(models);
                            }
                            return getRepresentation(entity, imakeMessage, xstream);
                        } else if (serviceName.equals(ServiceConstant.FUNDING_RESOURCES_SAVE)) {
                            java.sql.Timestamp now = new java.sql.Timestamp(new Date().getTime());
                            domain.setCreatedDate(now);
                            domain.setUpdatedDate(now);
                            int updateRecord = researchService.saveFundingResource(domain);
                            return returnUpdateRecord(entity, xsource, updateRecord);
                        } else if (serviceName.equals(ServiceConstant.FUNDING_RESOURCES_UPDATE)) {
                            java.sql.Timestamp updatedDate = new java.sql.Timestamp(new Date().getTime());
                            domain.setUpdatedDate(updatedDate);
                            int updateRecord = researchService.updateFundingResource(domain);
                            return returnUpdateRecord(entity, xsource, updateRecord);
                        } else if (serviceName.equals(ServiceConstant.FUNDING_RESOURCES_ITEMS_DELETE)) {
                            String[] ids = xsource.getIds();
                            //logger.debug("xbpsTerm.getMsIds()="+xbpsTerm.getMsIds());
                            int updateRecord = 0;
                            for (int i = 0; i < ids.length; i++) {
                                FundingResource item = new FundingResource();
                                item.setFundingResourceId(Integer.parseInt(ids[i]));
                                try {
                                    updateRecord = researchService.deleteFundingResource(item);
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
                        } else if (serviceName.equals(ServiceConstant.FUNDING_RESOURCES_DELETE)) {
                            int updateRecord = 0;
                            try {
                                updateRecord = researchService.deleteFundingResource(domain);
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
                        } else if (serviceName.equals(ServiceConstant.FUNDING_RESOURCES_SEARCH)) {
                            Paging page = xsource.getPaging();
                            @SuppressWarnings("rawtypes")
                            List result = (List) researchService.searchFundingResource(domain, page, xsource.getKeySearch());
                            if (result != null && result.size() == 2) {
                                java.util.ArrayList<FundingResource> domains = (java.util.ArrayList<FundingResource>) result
                                        .get(0);
                                String domains_size = (String) result.get(1);
                                ImakeResultMessage imakeMessage = new ImakeResultMessage();

                                List<FundingResourceM> models = new ArrayList<FundingResourceM>();
                                if (domains_size != null && domains_size.length() != 0)
                                    imakeMessage.setMaxRow(domains_size);
                                if (domains != null && domains.size() > 0) {
                                    models = getFundingResourceModels(domains);
                                }
                                imakeMessage.setResultListObj(models);
                                return getRepresentation(entity, imakeMessage, xstream);
                            }
                        } else if (serviceName.equals(ServiceConstant.FUNDING_RESOURCES_CHECK_UQ)) {

                            int updateRecord = researchService.checkUQFundingResource(domain);
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

    private List<FundingResourceM> getFundingResourceModels(
            java.util.ArrayList<FundingResource> domains) {
        List<FundingResourceM> models = new ArrayList<FundingResourceM>(
                domains.size());
        for (FundingResource domain : domains) {
            FundingResourceM model = new FundingResourceM();
            //	BeanUtils.copyProperties(domain, model);
            BeanUtils.copyProperties(domain, model, "fundingResourceType");
            if (domain.getFundingResourceType() != null) {
                FundingResourceTypeM fundingResourceTypeM = new FundingResourceTypeM();
                BeanUtils.copyProperties(domain.getFundingResourceType(), fundingResourceTypeM);
                model.setFundingResourceType(fundingResourceTypeM);
            }
            if (domain.getCountry() != null) {
                CountryM countryM = new CountryM();
                BeanUtils.copyProperties(domain.getCountry(), countryM);
                model.setCountry(countryM);
            }

            model.setPagging(null);
            models.add(model);
        }
        return models;
    }

    private Representation returnUpdateRecord(Representation entity, FundingResourceM model, int updateRecord) {
        ImakeResultMessage imakeMessage = new ImakeResultMessage();
        List<FundingResourceM> xsources = new ArrayList<FundingResourceM>(1);
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
