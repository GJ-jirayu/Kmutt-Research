package th.ac.kmutt.research.rest.resource;

import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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
import th.ac.kmutt.research.domain.Patent;
import th.ac.kmutt.research.domain.PatentCreator;
import th.ac.kmutt.research.domain.PatentCreatorPK;
import th.ac.kmutt.research.domain.PatentDocument;
import th.ac.kmutt.research.domain.PatentDocumentPK;
import th.ac.kmutt.research.domain.PatentEditDate;
import th.ac.kmutt.research.domain.PatentEditDatePK;
import th.ac.kmutt.research.domain.PatentFeePayment;
import th.ac.kmutt.research.domain.PatentFeePaymentPK;
import th.ac.kmutt.research.domain.PatentInherited;
import th.ac.kmutt.research.domain.PatentInheritedPK;
import th.ac.kmutt.research.domain.PatentRightholder;
import th.ac.kmutt.research.domain.PatentRightholderPK;
import th.ac.kmutt.research.model.DocAssignMappingM;
import th.ac.kmutt.research.model.OrganizationM;
import th.ac.kmutt.research.model.PatentCreatorM;
import th.ac.kmutt.research.model.PatentDocumentM;
import th.ac.kmutt.research.model.PatentEditDateM;
import th.ac.kmutt.research.model.PatentFeePaymentM;
import th.ac.kmutt.research.model.PatentInheritedM;
import th.ac.kmutt.research.model.PatentM;
import th.ac.kmutt.research.model.PatentRightholderM;
import th.ac.kmutt.research.portal.model.UserPortalM;
import th.ac.kmutt.research.service.ResearchService;
import th.ac.kmutt.research.xstream.common.ImakeResultMessage;
import th.ac.kmutt.research.xstream.common.Paging;

public class PatentResource extends BaseResource {
    private static final Logger logger = Logger.getLogger(ServiceConstant.LOG_APPENDER);
    private static SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
    private static SimpleDateFormat format2 = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
    private static DecimalFormat df2 = new DecimalFormat("#,###,###,##0.00");
    @Autowired
    @Qualifier("researchServiceJpaImpl")
    private ResearchService researchService;
    //private ConsultantReportService consultantReportService;
    private com.thoughtworks.xstream.XStream xstream;

    public PatentResource() {
        super();
        logger.debug("into constructor PatentResource");
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
            xstream.processAnnotations(PatentM.class);// or xstream.autodetectAnnotations(true); (Auto-detect  Annotations)
            PatentM xsource = new PatentM();
            Object xtarget = xstream.fromXML(in);
            if (xtarget != null) {
                xsource = (PatentM) xtarget;
                if (xsource != null) {
                    Patent domain = new Patent();
                    BeanUtils.copyProperties(xsource, domain);

					/*private PatentCreatorM patentCreator;

					private PatentEditDateM patentEditDate;

					private PatentFeePaymentM patentFeePayment;

					private PatentInheritedM patentInherited;

					private PatentRightholderM patentRightholder;
					
					private PatentDocumentM patentDocument;*/

                    PatentCreator patentCreator = new PatentCreator();
                    if (xsource.getPatentCreator() != null) {
                        BeanUtils.copyProperties(xsource.getPatentCreator(), patentCreator);
                        PatentCreatorPK pk = new PatentCreatorPK();

                        if (xsource.getPatentCreator().getInventionId() != null)
                            pk.setInventionId(xsource.getPatentCreator().getInventionId());
                        if (xsource.getPatentCreator().getCreatorItemList() != null)
                            pk.setCreatorItemList(xsource.getPatentCreator().getCreatorItemList());
                        patentCreator.setId(pk);

                        if (xsource.getPatentCreator().getOrganization() != null && xsource.getPatentCreator().getOrganization().getOrganizationId() != null) {
                            Organization organization = new Organization();
                            BeanUtils.copyProperties(xsource.getPatentCreator().getOrganization(), organization);
                            patentCreator.setOrganization(organization);
                        }
                    }

                    PatentEditDate patentEditDate = new PatentEditDate();
                    if (xsource.getPatentEditDate() != null) {
                        BeanUtils.copyProperties(xsource.getPatentEditDate(), patentEditDate);
                        PatentEditDatePK pk = new PatentEditDatePK();
                        if (xsource.getPatentEditDate().getInventionId() != null)
                            pk.setInventionId(xsource.getPatentEditDate().getInventionId());
                        if (xsource.getPatentEditDate().getEditItemList() != null)
                            pk.setEditItemList(xsource.getPatentEditDate().getEditItemList());
						/*if(xsource.getPatentEditDate().getYears()!=null)
							pk.setYears(xsource.getPatentEditDate().getYears());*/
                        patentEditDate.setId(pk);
                        if (xsource.getPatentEditDate().getEditDateShow() != null)
                            try {
                                patentEditDate.setEditDate(format2.parse(xsource.getPatentEditDate().getEditDateShow()));
                            } catch (ParseException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                    }

                    PatentFeePayment patentFeePayment = new PatentFeePayment();
                    if (xsource.getPatentFeePayment() != null) {
                        BeanUtils.copyProperties(xsource.getPatentFeePayment(), patentFeePayment);
                        PatentFeePaymentPK pk = new PatentFeePaymentPK();

                        if (xsource.getPatentFeePayment().getInventionId() != null)
                            pk.setInventionId(xsource.getPatentFeePayment().getInventionId());
                        if (xsource.getPatentFeePayment().getItemList() != null)
                            pk.setItemList(xsource.getPatentFeePayment().getItemList());
                        if (xsource.getPatentFeePayment().getYears() != null)
                            pk.setYears(xsource.getPatentFeePayment().getYears());
                        patentFeePayment.setId(pk);
                        if (xsource.getPatentFeePayment().getDateShow() != null)
                            try {
                                patentFeePayment.setDate(format2.parse(xsource.getPatentFeePayment().getDateShow()));
                            } catch (ParseException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                    }


                    PatentInherited patentInherited = new PatentInherited();
                    if (xsource.getPatentInherited() != null) {
                        BeanUtils.copyProperties(xsource.getPatentInherited(), patentInherited);
                        PatentInheritedPK pk = new PatentInheritedPK();

                        if (xsource.getPatentInherited().getInventionId() != null)
                            pk.setInventionId(xsource.getPatentInherited().getInventionId());
                        if (xsource.getPatentInherited().getInheritedItemList() != null)
                            pk.setInheritedItemList(xsource.getPatentInherited().getInheritedItemList());
                        patentInherited.setId(pk);
                        if (xsource.getPatentInherited().getOrganization() != null && xsource.getPatentInherited().getOrganization().getOrganizationId() != null) {
                            Organization organization = new Organization();
                            BeanUtils.copyProperties(xsource.getPatentInherited().getOrganization(), organization);
                            patentInherited.setOrganization(organization);
                        }
                    }


                    PatentRightholder patentRightholder = new PatentRightholder();
                    if (xsource.getPatentRightholder() != null) {
                        BeanUtils.copyProperties(xsource.getPatentRightholder(), patentRightholder);
                        PatentRightholderPK pk = new PatentRightholderPK();

                        if (xsource.getPatentRightholder().getInventionId() != null)
                            pk.setInventionId(xsource.getPatentRightholder().getInventionId());
                        if (xsource.getPatentRightholder().getRightholderItemList() != null)
                            pk.setRightholderItemList(xsource.getPatentRightholder().getRightholderItemList());
                        patentRightholder.setId(pk);
                        if (xsource.getPatentRightholder().getOrganization() != null && xsource.getPatentRightholder().getOrganization().getOrganizationId() != null) {
                            Organization organization = new Organization();
                            BeanUtils.copyProperties(xsource.getPatentRightholder().getOrganization(), organization);
                            patentRightholder.setOrganization(organization);
                        }
                    }


                    PatentDocument patentDocument = new PatentDocument();
                    if (xsource.getPatentDocument() != null) {
                        BeanUtils.copyProperties(xsource.getPatentDocument(), patentDocument);
                        PatentDocumentPK pk = new PatentDocumentPK();
                        pk.setDocumentId(1);

                        if (xsource.getPatentDocument().getInventionId() != null)
                            pk.setInventionId(xsource.getPatentDocument().getInventionId());
                        if (xsource.getPatentDocument().getItemList() != null)
                            pk.setItemList(xsource.getPatentDocument().getItemList());

                        patentDocument.setId(pk);
                    }

                    if (xsource.getServiceName() != null
                            && xsource.getServiceName().length() != 0) {
                        String serviceName = xsource.getServiceName();
                        if (serviceName.equals(ServiceConstant.PATENT_FIND_BY_ID)) {
                            domain = researchService.findPatentById(xsource.getInventionId(), xsource.getUserid());
                            ImakeResultMessage imakeMessage = new ImakeResultMessage();
                            if (domain != null) {
                                List<PatentM> models = new ArrayList<PatentM>(1);
                                PatentM model = new PatentM();
                                BeanUtils.copyProperties(domain, model);
                                model.setPagging(null);
                                List<PatentCreatorM> patentCreatorms = listPatentCreator(xsource.getInventionId());
                                model.setPatentCreators(patentCreatorms);
                                List<PatentDocumentM> patentDocumentms = listPatentDocument(xsource.getInventionId());
                                model.setPatentDocuments(patentDocumentms);

                                List<PatentEditDateM> patentEditDatems = listPatentEditDate(xsource.getInventionId());
                                model.setPatentEditDates(patentEditDatems);
                                List<PatentFeePaymentM> patentFeePaymentms = listPatentFeePayment(xsource.getInventionId());
                                model.setPatentFeePayments(patentFeePaymentms);

                                List<PatentInheritedM> patentInheritedms = listPatentInherited(xsource.getInventionId());
                                model.setPatentInheriteds(patentInheritedms);

                                List<PatentRightholderM> patentRightholderms = listPatentRightholder(xsource.getInventionId());
                                model.setPatentRightholders(patentRightholderms);

                                List<DocAssignMappingM> docAssignMappingms = listDocAssignMapping(xsource.getInventionId());
                                model.setDocAssignMappings(docAssignMappingms);

                                models.add(model);
                                imakeMessage.setResultListObj(models);
                            }
                            return getRepresentation(entity, imakeMessage, xstream);
                        } else if (serviceName.equals(ServiceConstant.PATENT_SAVE)) {
                            java.sql.Timestamp now = new java.sql.Timestamp(new Date().getTime());
                            domain.setCreatedDate(now);
                            domain.setUpdatedDate(now);
                            domain.setFlag(ServiceConstant.FLAG_ACTIVE);
                            int updateRecord = researchService.savePatent(domain);
                            return returnUpdateRecord(entity, xsource, updateRecord);
                        } else if (serviceName.equals(ServiceConstant.PATENT_UPDATE)) {
                            java.sql.Timestamp updatedDate = new java.sql.Timestamp(new Date().getTime());
                            domain.setUpdatedDate(updatedDate);
                            int updateRecord = researchService.updatePatent(domain);
                            return returnUpdateRecord(entity, xsource, updateRecord);
                        } else if (serviceName.equals(ServiceConstant.PATENT_ITEMS_DELETE)) {

                            String[] ids = xsource.getIds();
                            //logger.debug("xbpsTerm.getMsIds()="+xbpsTerm.getMsIds());
                            int updateRecord = 0;
                            for (int i = 0; i < ids.length; i++) {
                                Patent item = new Patent();
                                item.setInventionId(Integer.parseInt(ids[i]));

                                item = researchService.findPatentById(item.getInventionId(), xsource.getUserid());
                                if (item.getFlag() != null && item.getFlag().equals(ServiceConstant.FLAG_INACTIVE))
                                    updateRecord = researchService.deletePatent(item);
                                else {
                                    item.setUpdateType(ServiceConstant.UPDATE_TYEP_FLAG);
                                    item.setFlag(ServiceConstant.FLAG_INACTIVE);
                                    updateRecord = researchService.updateFlagPatent(item);
                                }

                            }
                            return returnUpdateRecord(entity, xsource, updateRecord);

                        } else if (serviceName.equals(ServiceConstant.PATENT_DELETE)) {
                            domain = researchService.findPatentById(domain.getInventionId(), xsource.getUserid());
                            int updateRecord = 0;
                            if (domain.getFlag() != null && domain.getFlag().equals(ServiceConstant.FLAG_INACTIVE))
                                updateRecord = researchService.deletePatent(domain);
                            else {
                                domain.setUpdateType(ServiceConstant.UPDATE_TYEP_FLAG);
                                domain.setFlag(ServiceConstant.FLAG_INACTIVE);
                                updateRecord = researchService.updateFlagPatent(domain);
                            }

                            return returnUpdateRecord(entity, xsource, updateRecord);
                        } else if (serviceName.equals(ServiceConstant.PATENT_UPDATE_FLAG)) {
                            int updateRecord = researchService.updateFlagPatent(domain);
                            return returnUpdateRecord(entity, xsource, updateRecord);
                        } else if (serviceName.equals(ServiceConstant.PATENT_SEARCH)) {
                            Paging page = xsource.getPaging();
                            @SuppressWarnings("rawtypes")
                            List result = (List) researchService.searchPatent(domain, page, xsource.getKeySearch(), xsource.getUserid());
                            if (result != null && result.size() == 2) {
                                java.util.ArrayList<Patent> domains = (java.util.ArrayList<Patent>) result
                                        .get(0);
                                String domains_size = (String) result.get(1);
                                ImakeResultMessage imakeMessage = new ImakeResultMessage();

                                List<PatentM> models = new ArrayList<PatentM>();
                                if (domains_size != null && domains_size.length() != 0)
                                    imakeMessage.setMaxRow(domains_size);
                                if (domains != null && domains.size() > 0) {
                                    models = getPatentModels(domains);
                                }
                                imakeMessage.setResultListObj(models);
                                return getRepresentation(entity, imakeMessage, xstream);
                            }
                        }
                        // creator
                        else if (serviceName.equals(ServiceConstant.PATENT_CREATOR_SAVE)) {

                            java.sql.Timestamp now = new java.sql.Timestamp(new Date().getTime());
                            patentCreator.setCreatedDate(now);
                            patentCreator.setUpdatedDate(now);
                            int updateRecord = researchService.savePatentCreator(patentCreator);
                            return returnUpdateRecord(entity, xsource, updateRecord);
                        } else if (serviceName.equals(ServiceConstant.PATENT_CREATOR_UPDATE)) {
                            java.sql.Timestamp updatedDate = new java.sql.Timestamp(new Date().getTime());
                            patentCreator.setUpdatedDate(updatedDate);
                            PatentCreator old = researchService.findPatentCreatorById(patentCreator.getId());
                            patentCreator.setCreatedBy(old.getCreatedBy());
                            patentCreator.setCreatedDate(old.getCreatedDate());
                            int updateRecord = researchService.updatePatentCreator(patentCreator);
                            return returnUpdateRecord(entity, xsource, updateRecord);
                        } else if (serviceName.equals(ServiceConstant.PATENT_CREATOR_LIST)) {
                            List<PatentCreatorM> patentCreatorms = listPatentCreator(patentCreator.getId().getInventionId());

                            ImakeResultMessage imakeMessage = new ImakeResultMessage();
                            List<PatentM> models = new ArrayList<PatentM>(1);
                            xsource.setPatentCreators(patentCreatorms);
                            models.add(xsource);
                            imakeMessage.setResultListObj(models);
                            return getRepresentation(entity, imakeMessage, xstream);

                        } else if (serviceName.equals(ServiceConstant.PATENT_CREATOR_DELETE)) {
                            int updateRecord = researchService.deletePatentCreator(patentCreator);
                            return returnUpdateRecord(entity, xsource, updateRecord);
                        } else if (serviceName.equals(ServiceConstant.PATENT_CREATOR_FIND_BY_ID)) {
                            PatentCreator patentCreatord = researchService.findPatentCreatorById(patentCreator.getId());
                            ImakeResultMessage imakeMessage = new ImakeResultMessage();
                            List<PatentM> models = new ArrayList<PatentM>(1);

                            PatentCreatorM patentCreatorM = new PatentCreatorM();
                            if (patentCreatord != null) {
                                BeanUtils.copyProperties(patentCreatord, patentCreatorM);
                                if (patentCreatord.getId() != null) {
                                    patentCreatorM.setInventionId(patentCreatord.getId().getInventionId());
                                    patentCreatorM.setCreatorItemList(patentCreatord.getId().getCreatorItemList());
                                }
                                if (patentCreatord.getOrganization() != null) {
                                    OrganizationM organization = new OrganizationM();
                                    BeanUtils.copyProperties(patentCreatord.getOrganization(), organization);
                                    patentCreatorM.setOrganization(organization);
                                }
                            }
                            xsource.setPatentCreator(patentCreatorM);
                            models.add(xsource);
                            imakeMessage.setResultListObj(models);
                            return getRepresentation(entity, imakeMessage, xstream);
                        }

                        //patentEditDate
                        else if (serviceName.equals(ServiceConstant.PATENT_EDIT_DATE_SAVE)) {

                            java.sql.Timestamp now = new java.sql.Timestamp(new Date().getTime());
                            patentEditDate.setCreatedDate(now);
                            patentEditDate.setUpdatedDate(now);
                            int updateRecord = researchService.savePatentEditDate(patentEditDate);
                            return returnUpdateRecord(entity, xsource, updateRecord);
                        } else if (serviceName.equals(ServiceConstant.PATENT_EDIT_DATE_UPDATE)) {
                            java.sql.Timestamp updatedDate = new java.sql.Timestamp(new Date().getTime());
                            patentEditDate.setUpdatedDate(updatedDate);
                            PatentEditDate old = researchService.findPatentEditDateById(patentEditDate.getId());
                            patentEditDate.setCreatedBy(old.getCreatedBy());
                            patentEditDate.setCreatedDate(old.getCreatedDate());
                            int updateRecord = researchService.updatePatentEditDate(patentEditDate);
                            return returnUpdateRecord(entity, xsource, updateRecord);
                        } else if (serviceName.equals(ServiceConstant.PATENT_EDIT_DATE_LIST)) {

                            List<PatentEditDateM> patentEditDatems = listPatentEditDate(patentEditDate.getId().getInventionId());

                            ImakeResultMessage imakeMessage = new ImakeResultMessage();
                            List<PatentM> models = new ArrayList<PatentM>(1);
                            xsource.setPatentEditDates(patentEditDatems);
                            models.add(xsource);
                            imakeMessage.setResultListObj(models);
                            return getRepresentation(entity, imakeMessage, xstream);

                        } else if (serviceName.equals(ServiceConstant.PATENT_EDIT_DATE_DELETE)) {
                            int updateRecord = researchService.deletePatentEditDate(patentEditDate);
                            return returnUpdateRecord(entity, xsource, updateRecord);
                        } else if (serviceName.equals(ServiceConstant.PATENT_EDIT_DATE_FIND_BY_ID)) {
                            PatentEditDate patentEditDated = researchService.findPatentEditDateById(patentEditDate.getId());
                            ImakeResultMessage imakeMessage = new ImakeResultMessage();
                            List<PatentM> models = new ArrayList<PatentM>(1);

                            PatentEditDateM patentEditDateM = new PatentEditDateM();
                            if (patentEditDated != null) {
                                BeanUtils.copyProperties(patentEditDated, patentEditDateM);
                                if (patentEditDated.getId() != null) {

                                    patentEditDateM.setInventionId(patentEditDated.getId().getInventionId());
                                    patentEditDateM.setEditItemList(patentEditDated.getId().getEditItemList());
                                }
                                if (patentEditDated.getEditDate() != null)
                                    patentEditDateM.setEditDateShow(format1.format(patentEditDated.getEditDate()));
                            }
                            xsource.setPatentEditDate(patentEditDateM);
                            models.add(xsource);
                            imakeMessage.setResultListObj(models);
                            return getRepresentation(entity, imakeMessage, xstream);
                        }

                        //patentFeePayment
                        else if (serviceName.equals(ServiceConstant.PATENT_FEE_PAYMENT_SAVE)) {

                            java.sql.Timestamp now = new java.sql.Timestamp(new Date().getTime());
                            patentFeePayment.setCreatedDate(now);
                            patentFeePayment.setUpdatedDate(now);
                            int updateRecord = researchService.savePatentFeePayment(patentFeePayment);
                            return returnUpdateRecord(entity, xsource, updateRecord);
                        } else if (serviceName.equals(ServiceConstant.PATENT_FEE_PAYMENT_UPDATE)) {
                            java.sql.Timestamp updatedDate = new java.sql.Timestamp(new Date().getTime());
                            patentFeePayment.setUpdatedDate(updatedDate);
                            PatentFeePayment old = researchService.findPatentFeePaymentById(patentFeePayment.getId());
                            patentFeePayment.setCreatedBy(old.getCreatedBy());
                            patentFeePayment.setCreatedDate(old.getCreatedDate());
                            int updateRecord = researchService.updatePatentFeePayment(patentFeePayment);
                            return returnUpdateRecord(entity, xsource, updateRecord);
                        } else if (serviceName.equals(ServiceConstant.PATENT_FEE_PAYMENT_LIST)) {

                            List<PatentFeePaymentM> patentFeePaymentms = listPatentFeePayment(patentFeePayment.getId().getInventionId());

                            ImakeResultMessage imakeMessage = new ImakeResultMessage();
                            List<PatentM> models = new ArrayList<PatentM>(1);
                            xsource.setPatentFeePayments(patentFeePaymentms);
                            models.add(xsource);
                            imakeMessage.setResultListObj(models);
                            return getRepresentation(entity, imakeMessage, xstream);

                        } else if (serviceName.equals(ServiceConstant.PATENT_FEE_PAYMENT_DELETE)) {
                            int updateRecord = researchService.deletePatentFeePayment(patentFeePayment);
                            return returnUpdateRecord(entity, xsource, updateRecord);
                        } else if (serviceName.equals(ServiceConstant.PATENT_FEE_PAYMENT_FIND_BY_ID)) {
                            PatentFeePayment patentFeePaymentd = researchService.findPatentFeePaymentById(patentFeePayment.getId());
                            ImakeResultMessage imakeMessage = new ImakeResultMessage();
                            List<PatentM> models = new ArrayList<PatentM>(1);


                            PatentFeePaymentM patentFeePaymentM = new PatentFeePaymentM();
                            if (patentFeePaymentd != null) {
                                BeanUtils.copyProperties(patentFeePaymentd, patentFeePaymentM);
                                if (patentFeePaymentd.getId() != null) {
                                    patentFeePaymentM.setItemList(patentFeePaymentd.getId().getItemList());
                                    patentFeePaymentM.setInventionId(patentFeePaymentd.getId().getInventionId());
                                    patentFeePaymentM.setYears(patentFeePaymentd.getId().getYears());
                                }
                                if (patentFeePaymentd.getDate() != null)
                                    patentFeePaymentM.setDateShow(format1.format(patentFeePaymentd.getDate()));

                            }
                            xsource.setPatentFeePayment(patentFeePaymentM);
                            models.add(xsource);
                            imakeMessage.setResultListObj(models);
                            return getRepresentation(entity, imakeMessage, xstream);
                        }

                        //patentInherited
                        else if (serviceName.equals(ServiceConstant.PATENT_INHERITED_SAVE)) {

                            java.sql.Timestamp now = new java.sql.Timestamp(new Date().getTime());
                            patentInherited.setCreatedDate(now);
                            patentInherited.setUpdatedDate(now);
                            int updateRecord = researchService.savePatentInherited(patentInherited);
                            return returnUpdateRecord(entity, xsource, updateRecord);
                        } else if (serviceName.equals(ServiceConstant.PATENT_INHERITED_UPDATE)) {
                            java.sql.Timestamp updatedDate = new java.sql.Timestamp(new Date().getTime());
                            patentInherited.setUpdatedDate(updatedDate);
                            int updateRecord = researchService.updatePatentInherited(patentInherited);
                            return returnUpdateRecord(entity, xsource, updateRecord);
                        } else if (serviceName.equals(ServiceConstant.PATENT_INHERITED_LIST)) {
                            List<PatentInheritedM> patentInheritedms = listPatentInherited(patentInherited.getId().getInventionId());

                            ImakeResultMessage imakeMessage = new ImakeResultMessage();
                            List<PatentM> models = new ArrayList<PatentM>(1);
                            xsource.setPatentInheriteds(patentInheritedms);
                            models.add(xsource);
                            imakeMessage.setResultListObj(models);
                            return getRepresentation(entity, imakeMessage, xstream);

                        } else if (serviceName.equals(ServiceConstant.PATENT_INHERITED_DELETE)) {
                            int updateRecord = researchService.deletePatentInherited(patentInherited);
                            return returnUpdateRecord(entity, xsource, updateRecord);
                        } else if (serviceName.equals(ServiceConstant.PATENT_INHERITED_FIND_BY_ID)) {
                            PatentInherited patentDocumentd = researchService.findPatentInheritedById(patentInherited.getId());
                            ImakeResultMessage imakeMessage = new ImakeResultMessage();
                            List<PatentM> models = new ArrayList<PatentM>(1);

                            PatentInheritedM patentInheritedM = new PatentInheritedM();
                            if (patentDocumentd != null) {
                                BeanUtils.copyProperties(patentDocumentd, patentInheritedM);
                                if (patentDocumentd.getId() != null) {
                                    patentInheritedM.setInventionId(patentDocumentd.getId().getInventionId());
                                    patentInheritedM.setInheritedItemList(patentDocumentd.getId().getInheritedItemList());
                                }
                            }
                            xsource.setPatentInherited(patentInheritedM);
                            models.add(xsource);
                            imakeMessage.setResultListObj(models);
                            return getRepresentation(entity, imakeMessage, xstream);
                        }

                        //patentRightholder
                        else if (serviceName.equals(ServiceConstant.PATENT_RIGHTHOLDER_SAVE)) {

                            java.sql.Timestamp now = new java.sql.Timestamp(new Date().getTime());
                            patentRightholder.setCreatedDate(now);
                            patentRightholder.setUpdatedDate(now);
                            int updateRecord = researchService.savePatentRightholder(patentRightholder);
                            return returnUpdateRecord(entity, xsource, updateRecord);
                        } else if (serviceName.equals(ServiceConstant.PATENT_RIGHTHOLDER_UPDATE)) {
                            java.sql.Timestamp updatedDate = new java.sql.Timestamp(new Date().getTime());
                            patentRightholder.setUpdatedDate(updatedDate);
                            int updateRecord = researchService.updatePatentRightholder(patentRightholder);
                            return returnUpdateRecord(entity, xsource, updateRecord);
                        } else if (serviceName.equals(ServiceConstant.PATENT_RIGHTHOLDER_LIST)) {
                            List<PatentRightholderM> patentRightholderms = listPatentRightholder(patentRightholder.getId().getInventionId());

                            ImakeResultMessage imakeMessage = new ImakeResultMessage();
                            List<PatentM> models = new ArrayList<PatentM>(1);
                            xsource.setPatentRightholders(patentRightholderms);
                            models.add(xsource);
                            imakeMessage.setResultListObj(models);
                            return getRepresentation(entity, imakeMessage, xstream);

                        } else if (serviceName.equals(ServiceConstant.PATENT_RIGHTHOLDER_DELETE)) {
                            int updateRecord = researchService.deletePatentRightholder(patentRightholder);
                            return returnUpdateRecord(entity, xsource, updateRecord);
                        } else if (serviceName.equals(ServiceConstant.PATENT_RIGHTHOLDER_FIND_BY_ID)) {
                            PatentRightholder patentRightholderd = researchService.findPatentRightholderById(patentRightholder.getId());
                            ImakeResultMessage imakeMessage = new ImakeResultMessage();
                            List<PatentM> models = new ArrayList<PatentM>(1);
                            ;
                            PatentRightholderM patentRightholderM = new PatentRightholderM();
                            if (patentRightholderd != null) {
                                BeanUtils.copyProperties(patentRightholderd, patentRightholderM);
                                if (patentRightholderd.getId() != null) {
                                    patentRightholderM.setInventionId(patentRightholderd.getId().getInventionId());
                                    patentRightholderM.setRightholderItemList(patentRightholderd.getId().getRightholderItemList());
                                }
                            }
                            xsource.setPatentRightholder(patentRightholderM);
                            models.add(xsource);
                            imakeMessage.setResultListObj(models);
                            return getRepresentation(entity, imakeMessage, xstream);
                        }

                        //patentDocument
                        else if (serviceName.equals(ServiceConstant.PATENT_DOCUMENT_SAVE)) {

                            java.sql.Timestamp now = new java.sql.Timestamp(new Date().getTime());
                            patentDocument.setCreatedDate(now);
                            patentDocument.setUpdatedDate(now);
                            int updateRecord = researchService.savePatentDocument(patentDocument);
                            return returnUpdateRecord(entity, xsource, updateRecord);
                        } else if (serviceName.equals(ServiceConstant.PATENT_DOCUMENT_UPDATE)) {
                            java.sql.Timestamp updatedDate = new java.sql.Timestamp(new Date().getTime());
                            PatentDocument old = researchService.findPatentDocumentById(patentDocument.getId());
                            patentDocument.setCreatedBy(old.getCreatedBy());
                            patentDocument.setCreatedDate(old.getCreatedDate());
                            patentDocument.setUpdatedDate(updatedDate);
                            int updateRecord = researchService.updatePatentDocument(patentDocument);
                            return returnUpdateRecord(entity, xsource, updateRecord);
                        } else if (serviceName.equals(ServiceConstant.PATENT_DOCUMENT_LIST)) {

                            List<PatentDocumentM> patentDocumentms = listPatentDocument(patentDocument.getId().getInventionId());

                            ImakeResultMessage imakeMessage = new ImakeResultMessage();
                            List<PatentM> models = new ArrayList<PatentM>(1);
                            xsource.setPatentDocuments(patentDocumentms);
                            models.add(xsource);
                            imakeMessage.setResultListObj(models);
                            return getRepresentation(entity, imakeMessage, xstream);

                        } else if (serviceName.equals(ServiceConstant.PATENT_DOCUMENT_DELETE)) {
                            int updateRecord = researchService.deletePatentDocument(patentDocument);
                            return returnUpdateRecord(entity, xsource, updateRecord);
                        } else if (serviceName.equals(ServiceConstant.PATENT_DOCUMENT_FIND_BY_ID)) {
                            PatentDocument patentDocumentd = researchService.findPatentDocumentById(patentDocument.getId());
                            ImakeResultMessage imakeMessage = new ImakeResultMessage();
                            List<PatentM> models = new ArrayList<PatentM>(1);

                            PatentDocumentM patentDocumentM = new PatentDocumentM();
                            if (patentDocumentd != null) {
                                BeanUtils.copyProperties(patentDocumentd, patentDocumentM);
                                if (patentDocumentd.getId() != null) {
                                    patentDocumentM.setItemList(patentDocumentd.getId().getItemList());
                                    patentDocumentM.setInventionId(patentDocumentd.getId().getInventionId());
                                    patentDocumentM.setDocumentId(patentDocumentd.getId().getDocumentId());
                                }
                            }
                            xsource.setPatentDocument(patentDocumentM);
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

    private List<PatentDocumentM> listPatentDocument(Integer inventionId) {
        List<PatentDocument> documentds = researchService.listPatentDocument(inventionId);

        List<PatentDocumentM> documentms = new ArrayList<PatentDocumentM>(
                documentds.size());
        for (PatentDocument documentd : documentds) {
            PatentDocumentM documentm = new PatentDocumentM();
            BeanUtils.copyProperties(documentd, documentm);

            if (documentd.getId() != null) {
                documentm.setInventionId(documentd.getId().getInventionId());
                documentm.setItemList(documentd.getId().getItemList());
            }
            documentm.setPagging(null);
            documentms.add(documentm);
        }
        return documentms;
    }

    private List<PatentInheritedM> listPatentInherited(Integer inventionId) {
        List<PatentInherited> patentInheritedds = researchService.listPatentInherited(inventionId);
        List<PatentInheritedM> patentInheritedms = new ArrayList<PatentInheritedM>(
                patentInheritedds.size());
        for (PatentInherited patentInheritedd : patentInheritedds) {
            PatentInheritedM patentInheritedm = new PatentInheritedM();
            BeanUtils.copyProperties(patentInheritedd, patentInheritedm);
            patentInheritedm.setPagging(null);
            if (patentInheritedd.getId() != null) {
                patentInheritedm.setInventionId(patentInheritedd.getId().getInventionId());
                patentInheritedm.setInheritedItemList(patentInheritedd.getId().getInheritedItemList());
            }
            if (patentInheritedd.getOrganization() != null) {
                OrganizationM organizationM = new OrganizationM();
                BeanUtils.copyProperties(patentInheritedd.getOrganization(), organizationM);
                patentInheritedm.setOrganization(organizationM);
            }
            patentInheritedms.add(patentInheritedm);
        }
        return patentInheritedms;
    }

    private List<PatentRightholderM> listPatentRightholder(Integer inventionId) {
        List<PatentRightholder> patentRightholderds = researchService.listPatentRightholder(inventionId);
        List<PatentRightholderM> patentRightholderms = new ArrayList<PatentRightholderM>(
                patentRightholderds.size());
        for (PatentRightholder patentRightholderd : patentRightholderds) {
            PatentRightholderM patentRightholderm = new PatentRightholderM();
            BeanUtils.copyProperties(patentRightholderd, patentRightholderm);
            patentRightholderm.setPagging(null);
            if (patentRightholderd.getId() != null) {
                patentRightholderm.setInventionId(patentRightholderd.getId().getInventionId());
                patentRightholderm.setRightholderItemList(patentRightholderd.getId().getRightholderItemList());
            }
            if (patentRightholderd.getOrganization() != null) {
                OrganizationM organizationM = new OrganizationM();
                BeanUtils.copyProperties(patentRightholderd.getOrganization(), organizationM);
                patentRightholderm.setOrganization(organizationM);
            }
            patentRightholderms.add(patentRightholderm);
        }
        return patentRightholderms;
    }

    private List<PatentCreatorM> listPatentCreator(Integer inventionId) {
        List<PatentCreator> patentCreatords = researchService.listPatentCreator(inventionId);
        List<PatentCreatorM> patentCreatorms = new ArrayList<PatentCreatorM>(
                patentCreatords.size());
        for (PatentCreator patentCreatord : patentCreatords) {
            PatentCreatorM patentCreatorm = new PatentCreatorM();
            BeanUtils.copyProperties(patentCreatord, patentCreatorm);
            patentCreatorm.setPagging(null);
            if (patentCreatord.getId() != null) {
                patentCreatorm.setInventionId(patentCreatord.getId().getInventionId());
                patentCreatorm.setCreatorItemList(patentCreatord.getId().getCreatorItemList());
            }
            if (patentCreatord.getOrganization() != null) {
                OrganizationM organizationM = new OrganizationM();
                BeanUtils.copyProperties(patentCreatord.getOrganization(), organizationM);
                patentCreatorm.setOrganization(organizationM);
            }
            patentCreatorms.add(patentCreatorm);
        }
        return patentCreatorms;
    }

    private List<PatentEditDateM> listPatentEditDate(Integer inventionId) {
        List<PatentEditDate> patentEditDateds = researchService.listPatentEditDate(inventionId);

        List<PatentEditDateM> patentEditDatems = new ArrayList<PatentEditDateM>(
                patentEditDateds.size());
        for (PatentEditDate patentEditDated : patentEditDateds) {
            PatentEditDateM patentEditDatem = new PatentEditDateM();
            BeanUtils.copyProperties(patentEditDated, patentEditDatem);
            patentEditDatem.setPagging(null);
            if (patentEditDated.getId() != null) {
                patentEditDatem.setInventionId(patentEditDated.getId().getInventionId());
                patentEditDatem.setEditItemList(patentEditDated.getId().getEditItemList());

            }
            if (patentEditDated.getEditDate() != null)
                patentEditDatem.setEditDateShow(format1.format(patentEditDated.getEditDate()));
            patentEditDatems.add(patentEditDatem);
        }

        return patentEditDatems;

    }

    private List<PatentFeePaymentM> listPatentFeePayment(Integer inventionId) {
        List<PatentFeePayment> patentFeePaymentds = researchService.listPatentFeePayment(inventionId);
        List<PatentFeePaymentM> patentFeePaymentms = new ArrayList<PatentFeePaymentM>(
                patentFeePaymentds.size());
        for (PatentFeePayment patentFeePaymentd : patentFeePaymentds) {
            PatentFeePaymentM patentFeePaymentm = new PatentFeePaymentM();
            BeanUtils.copyProperties(patentFeePaymentd, patentFeePaymentm);
            patentFeePaymentm.setPagging(null);
            if (patentFeePaymentd.getId() != null) {
                patentFeePaymentm.setInventionId(patentFeePaymentd.getId().getInventionId());
                patentFeePaymentm.setItemList(patentFeePaymentd.getId().getItemList());
                patentFeePaymentm.setYears(patentFeePaymentd.getId().getYears());
            }
            if (patentFeePaymentd.getDate() != null)
                patentFeePaymentm.setDateShow(format1.format(patentFeePaymentd.getDate()));
            if (patentFeePaymentd.getAmount() != null)
                patentFeePaymentm.setAmountShow(df2.format(patentFeePaymentd.getAmount()));
            patentFeePaymentms.add(patentFeePaymentm);
        }
        return patentFeePaymentms;
    }

    private List<DocAssignMappingM> listDocAssignMapping(Integer refId) {
        DocAssignMapping docAssignMapping = new DocAssignMapping();
        DocAssignMappingPK docPk = new DocAssignMappingPK();
        docPk.setRefId(refId);
        docPk.setRefType(ServiceConstant.DOC_TYPE_PATENT);
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

    private List<PatentM> getPatentModels(
            java.util.ArrayList<Patent> domains) {
        List<PatentM> models = new ArrayList<PatentM>(
                domains.size());
        for (Patent domain : domains) {
            PatentM model = new PatentM();
            BeanUtils.copyProperties(domain, model);
            model.setPagging(null);
            models.add(model);
        }
        return models;
    }

    private Representation returnUpdateRecord(Representation entity, PatentM model, int updateRecord) {
        ImakeResultMessage imakeMessage = new ImakeResultMessage();
        List<PatentM> xsources = new ArrayList<PatentM>(1);
        model.setUpdateRecord(updateRecord);
        xsources.add(model);
        imakeMessage.setResultListObj(xsources);
        return getRepresentation(entity, imakeMessage, xstream);
    }

    @Override
    protected Representation get(Variant variant) throws ResourceException {
        // TODO Auto-generated method stub
        Patent domain = researchService.findPatentById(3, null);
        ImakeResultMessage imakeMessage = new ImakeResultMessage();
        List<PatentM> models = new ArrayList<PatentM>(1);
        PatentM model = new PatentM();
        BeanUtils.copyProperties(domain, model);
        model.setPagging(null);
        List<PatentCreatorM> patentCreatorms = listPatentCreator(3);
        model.setPatentCreators(patentCreatorms);
        List<PatentDocumentM> patentDocumentms = listPatentDocument(3);
        model.setPatentDocuments(patentDocumentms);

        List<PatentEditDateM> patentEditDatems = listPatentEditDate(3);
        model.setPatentEditDates(patentEditDatems);
        List<PatentFeePaymentM> patentFeePaymentms = listPatentFeePayment(3);
        model.setPatentFeePayments(patentFeePaymentms);

        List<PatentInheritedM> patentInheritedms = listPatentInherited(3);
        model.setPatentInheriteds(patentInheritedms);

        List<PatentRightholderM> patentRightholderms = listPatentRightholder(3);
        model.setPatentRightholders(patentRightholderms);

        List<DocAssignMappingM> docAssignMappingms = listDocAssignMapping(3);
        model.setDocAssignMappings(docAssignMappingms);

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
