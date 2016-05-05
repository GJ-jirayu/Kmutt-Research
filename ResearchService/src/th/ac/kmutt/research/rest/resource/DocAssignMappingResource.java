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
import th.ac.kmutt.research.model.DocAssignMappingM;
import th.ac.kmutt.research.portal.model.UserPortalM;
import th.ac.kmutt.research.service.ResearchService;
import th.ac.kmutt.research.xstream.common.ImakeResultMessage;

public class DocAssignMappingResource extends BaseResource {
    private static final Logger logger = Logger.getLogger(ServiceConstant.LOG_APPENDER);
    @Autowired
    @Qualifier("researchServiceJpaImpl")
    private ResearchService researchService;
    //private ConsultantReportService consultantReportService;
    private com.thoughtworks.xstream.XStream xstream;

    public DocAssignMappingResource() {
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
            xstream.processAnnotations(DocAssignMappingM.class);// or xstream.autodetectAnnotations(true); (Auto-detect  Annotations)
            DocAssignMappingM xsource = new DocAssignMappingM();
            Object xtarget = xstream.fromXML(in);
            if (xtarget != null) {
                xsource = (DocAssignMappingM) xtarget;
                if (xsource != null) {
                    DocAssignMapping domain = new DocAssignMapping();
                    BeanUtils.copyProperties(xsource, domain, "id");

                    DocAssignMappingPK pk = new DocAssignMappingPK();
                    pk.setRefId(xsource.getRefId());
                    pk.setRefType(xsource.getRefType());
                    pk.setUserId(xsource.getUserId());
                    domain.setId(pk);
                    if (xsource.getServiceName() != null
                            && xsource.getServiceName().length() != 0) {
                        String serviceName = xsource.getServiceName();

                        if (serviceName.equals(ServiceConstant.DOC_ASSIGN_MAPPING_BY_ID)) {
                            domain = researchService.findDocAssignMappingById(xsource.getRefId(), xsource.getRefType());
                            if (domain != null) {
                                ImakeResultMessage imakeMessage = new ImakeResultMessage();
                                List<DocAssignMappingM> models = new ArrayList<DocAssignMappingM>(1);
                                DocAssignMappingM model = new DocAssignMappingM();
                                BeanUtils.copyProperties(domain, model);
                                model.setPagging(null);
                                models.add(model);
                                imakeMessage.setResultListObj(models);
                                return getRepresentation(entity, imakeMessage, xstream);
                            }
                        } else if (serviceName.equals(ServiceConstant.DOC_ASSIGN_MAPPING_SAVE)) {
                            java.sql.Timestamp now = new java.sql.Timestamp(new Date().getTime());
                            domain.setCreatedDate(now);
                            domain.setUpdatedDate(now);
                            int updateRecord = researchService.saveDocAssignMapping(domain);
                            return returnUpdateRecord(entity, xsource, updateRecord);
                        } else if (serviceName.equals(ServiceConstant.DOC_ASSIGN_MAPPING_DELETE)) {
                            int updateRecord = researchService.deleteDocAssignMapping(domain);
                            return returnUpdateRecord(entity, xsource, updateRecord);
                        } else if (serviceName.equals(ServiceConstant.DOC_ASSIGN_MAPPING_LIST)) {
                            //Paging page = xsource.getPaging();
                            @SuppressWarnings("rawtypes")
                            List<DocAssignMappingM> models = listDocAssignMapping(domain);
                            // List<DocAssignMappingM> models = new ArrayList<DocAssignMappingM>();

                            ImakeResultMessage imakeMessage = new ImakeResultMessage();

                            imakeMessage.setResultListObj(models);
                            return getRepresentation(entity, imakeMessage, xstream);
                            //}
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

    private List<DocAssignMappingM> listDocAssignMapping(DocAssignMapping docAssignMapping) {
        /*DocAssignMapping docAssignMapping =new DocAssignMapping();
		DocAssignMappingPK docPk=new DocAssignMappingPK();
		docPk.setRefId(copyrightId);
		docPk.setRefType(ServiceConstant.DOC_TYPE_COPYRIGHT);
		docAssignMapping.setId(docPk);*/
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

    private List<DocAssignMappingM> getDocAssignMappingModels(
            java.util.ArrayList<DocAssignMapping> domains) {
        List<DocAssignMappingM> models = new ArrayList<DocAssignMappingM>(
                domains.size());
        for (DocAssignMapping domain : domains) {
            DocAssignMappingM model = new DocAssignMappingM();
            BeanUtils.copyProperties(domain, model);
            model.setPagging(null);
            models.add(model);
        }
        return models;
    }

    private Representation returnUpdateRecord(Representation entity, DocAssignMappingM model, int updateRecord) {
        ImakeResultMessage imakeMessage = new ImakeResultMessage();
        List<DocAssignMappingM> xsources = new ArrayList<DocAssignMappingM>(1);
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
