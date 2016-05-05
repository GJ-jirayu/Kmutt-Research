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
import org.hibernate.exception.ConstraintViolationException;
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
import th.ac.kmutt.research.domain.Position;
import th.ac.kmutt.research.domain.ResearchProject;
import th.ac.kmutt.research.domain.ResearchProjectDocument;
import th.ac.kmutt.research.domain.ResearchProjectDocumentPK;
import th.ac.kmutt.research.domain.ResearchProjectPayment;
import th.ac.kmutt.research.domain.ResearchProjectPaymentPK;
import th.ac.kmutt.research.domain.ResearchProjectProgress;
import th.ac.kmutt.research.domain.ResearchProjectProgressPK;
import th.ac.kmutt.research.domain.ResearchProjectResearcher;
import th.ac.kmutt.research.domain.ResearchProjectResearcherPK;
import th.ac.kmutt.research.domain.ResearchProjectWithdraw;
import th.ac.kmutt.research.domain.ResearchProjectWithdrawPK;
import th.ac.kmutt.research.model.DocAssignMappingM;
import th.ac.kmutt.research.model.OrganizationM;
import th.ac.kmutt.research.model.PositionM;
import th.ac.kmutt.research.model.ResearchProjectDocumentM;
import th.ac.kmutt.research.model.ResearchProjectM;
import th.ac.kmutt.research.model.ResearchProjectPaymentM;
import th.ac.kmutt.research.model.ResearchProjectProgressM;
import th.ac.kmutt.research.model.ResearchProjectResearcherM;
import th.ac.kmutt.research.model.ResearchProjectWithdrawM;
import th.ac.kmutt.research.portal.model.UserPortalM;
import th.ac.kmutt.research.service.ResearchService;
import th.ac.kmutt.research.xstream.common.ImakeResultMessage;
import th.ac.kmutt.research.xstream.common.Paging;

public class ResearchProjectResource extends BaseResource {
    private static final Logger logger = Logger.getLogger(ServiceConstant.LOG_APPENDER);
    // private static SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy HH:mm");
    private static SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
    private static SimpleDateFormat format2 = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
    private static DecimalFormat df2 = new DecimalFormat("#,###,###,##0.00");

    @Autowired
    @Qualifier("researchServiceJpaImpl")
    private ResearchService researchService;
    //private ConsultantReportService consultantReportService;
    private com.thoughtworks.xstream.XStream xstream;

    public ResearchProjectResource() {
        super();
        logger.debug("into constructor ResearchProjectResource");
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
            xstream.processAnnotations(ResearchProjectM.class);// or xstream.autodetectAnnotations(true); (Auto-detect  Annotations)
            ResearchProjectM xsource = new ResearchProjectM();
            Object xtarget = xstream.fromXML(in);
            if (xtarget != null) {
                xsource = (ResearchProjectM) xtarget;
                if (xsource != null) {
                    ResearchProject domain = new ResearchProject();
                    BeanUtils.copyProperties(xsource, domain);

				/*	private ResearchProjectDocumentM document;
					private ResearchProjectPaymentM payment;
					private ResearchProjectProgressM progress;
					private ResearchProjectResearcherM researcher;
					private ResearchProjectWithdrawM	 withdraw;*/

                    ResearchProjectDocument document = new ResearchProjectDocument();
                    if (xsource.getDocument() != null) {
                        BeanUtils.copyProperties(xsource.getDocument(), document);
                        ResearchProjectDocumentPK pk = new ResearchProjectDocumentPK();
                        pk.setDocumentId(1);
                        if (xsource.getDocument().getItemList() != null)
                            pk.setItemList(xsource.getDocument().getItemList());
                        if (xsource.getDocument().getResearchProjectId() != null)
                            pk.setResearchProjectId(xsource.getDocument().getResearchProjectId());
                        document.setId(pk);
                    }

                    ResearchProjectPayment payment = new ResearchProjectPayment();
                    if (xsource.getPayment() != null) {
                        BeanUtils.copyProperties(xsource.getPayment(), payment);
                        ResearchProjectPaymentPK pk = new ResearchProjectPaymentPK();
                        pk.setPaymentItemId(1);
                        if (xsource.getPayment().getItemList() != null)
                            pk.setItemList(xsource.getPayment().getItemList());
                        if (xsource.getPayment().getResearchProjectId() != null)
                            pk.setResearchProjectId(xsource.getPayment().getResearchProjectId());

                        if (xsource.getPayment().getReceivedDateShow() != null)
                            try {
                                payment.setReceivedDate(format2.parse(xsource.getPayment().getReceivedDateShow()));
                            } catch (ParseException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                        payment.setId(pk);
                    }

                    ResearchProjectProgress progress = new ResearchProjectProgress();
                    if (xsource.getProgress() != null) {
                        BeanUtils.copyProperties(xsource.getProgress(), progress);
                        ResearchProjectProgressPK pk = new ResearchProjectProgressPK();
                        pk.setProgressItemId(1);
                        if (xsource.getProgress().getItemList() != null)
                            pk.setItemList(xsource.getProgress().getItemList());
                        if (xsource.getProgress().getResearchProjectId() != null)
                            pk.setResearchProjectId(xsource.getProgress().getResearchProjectId());
                        if (xsource.getProgress().getProgressItemId() != null)
                            pk.setProgressItemId(xsource.getProgress().getProgressItemId());
                        // Date dueDate;
                        // Date submitDate;
                        if (xsource.getProgress().getSubmitDateShow() != null)
                            try {
                                progress.setSubmitDate(format2.parse(xsource.getProgress().getSubmitDateShow()));
                            } catch (ParseException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                        if (xsource.getProgress().getDueDateShow() != null)
                            try {
                                progress.setDueDate(format2.parse(xsource.getProgress().getDueDateShow()));
                            } catch (ParseException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                        progress.setId(pk);
                    }
                    ResearchProjectResearcher researcher = new ResearchProjectResearcher();
                    if (xsource.getResearcher() != null) {
                        BeanUtils.copyProperties(xsource.getResearcher(), researcher);
                        ResearchProjectResearcherPK pk = new ResearchProjectResearcherPK();
                        pk.setResearcherItemId(1);
                        if (xsource.getResearcher().getItemList() != null)
                            pk.setItemList(xsource.getResearcher().getItemList());
                        if (xsource.getResearcher().getResearchProjectId() != null)
                            pk.setResearchProjectId(xsource.getResearcher().getResearchProjectId());
                        if (xsource.getResearcher().getResearcherItemId() != null)
                            pk.setResearcherItemId(xsource.getResearcher().getResearcherItemId());
                        if (xsource.getResearcher().getPosition() != null && xsource.getResearcher().getPosition().getPositionId() != null) {
                            Position position = new Position();
                            BeanUtils.copyProperties(xsource.getResearcher().getPosition(), position);
                            researcher.setPosition(position);
                        }
                        if (xsource.getResearcher().getOrganization() != null && xsource.getResearcher().getOrganization().getOrganizationId() != null) {
                            Organization organization = new Organization();
                            BeanUtils.copyProperties(xsource.getResearcher().getOrganization(), organization);
                            researcher.setOrganization(organization);
                        }
                        researcher.setId(pk);
                    }

                    ResearchProjectWithdraw withdraw = new ResearchProjectWithdraw();
                    if (xsource.getWithdraw() != null) {
                        BeanUtils.copyProperties(xsource.getWithdraw(), withdraw);
                        ResearchProjectWithdrawPK pk = new ResearchProjectWithdrawPK();
                        pk.setWithdrawItemId(1);
                        if (xsource.getWithdraw().getItemList() != null)
                            pk.setItemList(xsource.getWithdraw().getItemList());
                        if (xsource.getWithdraw().getResearchProjectId() != null)
                            pk.setResearchProjectId(xsource.getWithdraw().getResearchProjectId());
                        if (xsource.getWithdraw().getWithdrawDateShow() != null)
                            try {
                                withdraw.setWithdrawDate(format2.parse(xsource.getWithdraw().getWithdrawDateShow()));
                            } catch (ParseException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                        withdraw.setId(pk);
                    }

                    if (xsource.getServiceName() != null
                            && xsource.getServiceName().length() != 0) {
                        String serviceName = xsource.getServiceName();
                        if (serviceName.equals(ServiceConstant.RESEARCH_PROJECT_FIND_BY_ID)) {
                            domain = researchService.findResearchProjectById(xsource.getResearchProjectId(), xsource.getUserid());
                            ImakeResultMessage imakeMessage = new ImakeResultMessage();
                            if (domain != null) {
                                List<ResearchProjectM> models = new ArrayList<ResearchProjectM>(1);
                                ResearchProjectM model = new ResearchProjectM();
                                BeanUtils.copyProperties(domain, model);
                                model.setPagging(null);

                                List<ResearchProjectDocumentM> documentms = listResearchProjectDocument(xsource.getResearchProjectId());
                                model.setDocuments(documentms);

                                List<ResearchProjectPaymentM> paymentms = listResearchProjectPayment(xsource.getResearchProjectId());
                                model.setPayments(paymentms);

                                List<ResearchProjectProgressM> progressms = listResearchProjectProgress(xsource.getResearchProjectId());
                                model.setProgresList(progressms);

                                List<ResearchProjectResearcherM> researcherms = listResearchProjectResearcher(xsource.getResearchProjectId());
                                model.setResearchers(researcherms);

                                List<ResearchProjectWithdrawM> withdrawms = listResearchProjectWithdraw(xsource.getResearchProjectId());
                                model.setWithdraws(withdrawms);

                                List<DocAssignMappingM> docAssignMappingms = listDocAssignMapping(xsource.getResearchProjectId());
                                model.setDocAssignMappings(docAssignMappingms);

                                models.add(model);
                                imakeMessage.setResultListObj(models);
                            }
                            return getRepresentation(entity, imakeMessage, xstream);
                        } else if (serviceName.equals(ServiceConstant.RESEARCH_PROJECT_SAVE)) {
                            java.sql.Timestamp now = new java.sql.Timestamp(new Date().getTime());
                            domain.setCreatedDate(now);
                            domain.setUpdatedDate(now);
                            domain.setFlag(ServiceConstant.FLAG_ACTIVE);
                            int updateRecord = researchService.saveResearchProject(domain);
                            return returnUpdateRecord(entity, xsource, updateRecord);
                        } else if (serviceName.equals(ServiceConstant.RESEARCH_PROJECT_UPDATE)) {
                            java.sql.Timestamp updatedDate = new java.sql.Timestamp(new Date().getTime());
                            domain.setUpdatedDate(updatedDate);
							/*ResearchProjectPayment old=researchService.findResearchProjectPaymentById(payment.getId());
							payment.setCreatedBy(old.getCreatedBy());
							payment.setCreatedDate(old.getCreatedDate());*/
                            int updateRecord = researchService.updateResearchProject(domain);
                            return returnUpdateRecord(entity, xsource, updateRecord);
                        } else if (serviceName.equals(ServiceConstant.RESEARCH_PROJECT_ITEMS_DELETE)) {

                            String[] ids = xsource.getIds();
                            //logger.debug("xbpsTerm.getMsIds()="+xbpsTerm.getMsIds());
                            int updateRecord = 0;
                            for (int i = 0; i < ids.length; i++) {
                                ResearchProject item = new ResearchProject();
                                item.setResearchProjectId(Integer.parseInt(ids[i]));
                                item = researchService.findResearchProjectById(item.getResearchProjectId(), xsource.getUserid());
                                if (item.getFlag() != null && item.getFlag().equals(ServiceConstant.FLAG_INACTIVE)) {
                                    try {
                                        updateRecord = researchService.deleteResearchProject(item);
                                    } catch (Exception e) {
                                        Throwable t = e.getCause();

                                        while ((t != null) && !(t instanceof ConstraintViolationException)) {
                                            t = t.getCause();
                                        }

                                        if (t instanceof ConstraintViolationException) {
                                            updateRecord = -9;
                                        }
                                    }
                                } else {
                                    item.setUpdateType(ServiceConstant.UPDATE_TYEP_FLAG);
                                    item.setFlag(ServiceConstant.FLAG_INACTIVE);
                                    updateRecord = researchService.updateFlagResearchProject(item);
                                }

                            }
                            return returnUpdateRecord(entity, xsource, updateRecord);

                        } else if (serviceName.equals(ServiceConstant.RESEARCH_PROJECT_DELETE)) {
                            domain = researchService.findResearchProjectById(domain.getResearchProjectId(), xsource.getUserid());
                            int updateRecord = 0;
                            if (domain.getFlag() != null && domain.getFlag().equals(ServiceConstant.FLAG_INACTIVE)) {
                                try {
                                    updateRecord = researchService.deleteResearchProject(domain);
                                } catch (Exception e) {
                                    Throwable t = e.getCause();

                                    while ((t != null) && !(t instanceof ConstraintViolationException)) {
                                        t = t.getCause();
                                    }

                                    if (t instanceof ConstraintViolationException) {
                                        updateRecord = -9;
                                    }
                                }
                            } else {
                                domain.setUpdateType(ServiceConstant.UPDATE_TYEP_FLAG);
                                domain.setFlag(ServiceConstant.FLAG_INACTIVE);
                                updateRecord = researchService.updateFlagResearchProject(domain);
                            }
                            return returnUpdateRecord(entity, xsource, updateRecord);
                        } else if (serviceName.equals(ServiceConstant.RESEARCH_PROJECT_UPDATE_FLAG)) {
                            int updateRecord = researchService.updateFlagResearchProject(domain);
                            return returnUpdateRecord(entity, xsource, updateRecord);
                        } else if (serviceName.equals(ServiceConstant.RESEARCH_PROJECT_RESEARCHER_COUNT)) {

                            int updateRecord = researchService.countResearchProjectResearcher(researcher);
                            return returnUpdateRecord(entity, xsource, updateRecord);
                        } else if (serviceName.equals(ServiceConstant.RESEARCH_PROJECT_SEARCH)) {
                            Paging page = xsource.getPaging();
                            @SuppressWarnings("rawtypes")
                            List result = (List) researchService.searchResearchProject(domain, page, xsource.getKeySearch(), xsource.getUserid());
                            if (result != null && result.size() == 2) {
                                java.util.ArrayList<ResearchProject> domains = (java.util.ArrayList<ResearchProject>) result
                                        .get(0);
                                String domains_size = (String) result.get(1);
                                ImakeResultMessage imakeMessage = new ImakeResultMessage();

                                List<ResearchProjectM> models = new ArrayList<ResearchProjectM>();
                                if (domains_size != null && domains_size.length() != 0)
                                    imakeMessage.setMaxRow(domains_size);
                                if (domains != null && domains.size() > 0) {
                                    models = getResearchProjectModels(domains);
                                }
                                imakeMessage.setResultListObj(models);
                                return getRepresentation(entity, imakeMessage, xstream);
                            }
                        }
                        // document
                        else if (serviceName.equals(ServiceConstant.RESEARCH_PROJECT_DOCUMENT_SAVE)) {

                            java.sql.Timestamp now = new java.sql.Timestamp(new Date().getTime());
                            document.setCreatedDate(now);
                            document.setUpdatedDate(now);
                            int updateRecord = researchService.saveResearchProjectDocument(document);
                            return returnUpdateRecord(entity, xsource, updateRecord);
                        } else if (serviceName.equals(ServiceConstant.RESEARCH_PROJECT_DOCUMENT_UPDATE)) {
                            java.sql.Timestamp updatedDate = new java.sql.Timestamp(new Date().getTime());
                            document.setUpdatedDate(updatedDate);
                            ResearchProjectDocument old = researchService.findResearchProjectDocumentById(document.getId());
                            document.setCreatedBy(old.getCreatedBy());
                            document.setCreatedDate(old.getCreatedDate());
                            int updateRecord = researchService.updateResearchProjectDocument(document);
                            return returnUpdateRecord(entity, xsource, updateRecord);
                        } else if (serviceName.equals(ServiceConstant.RESEARCH_PROJECT_DOCUMENT_LIST)) {
                            List<ResearchProjectDocumentM> documentms = listResearchProjectDocument(document.getId().getResearchProjectId());

                            ImakeResultMessage imakeMessage = new ImakeResultMessage();
                            List<ResearchProjectM> models = new ArrayList<ResearchProjectM>(1);
                            xsource.setDocuments(documentms);
                            models.add(xsource);
                            imakeMessage.setResultListObj(models);
                            return getRepresentation(entity, imakeMessage, xstream);

                        } else if (serviceName.equals(ServiceConstant.RESEARCH_PROJECT_DOCUMENT_DELETE)) {
                            int updateRecord = researchService.deleteResearchProjectDocument(document);
                            return returnUpdateRecord(entity, xsource, updateRecord);
                        } else if (serviceName.equals(ServiceConstant.RESEARCH_PROJECT_DOCUMENT_FIND_BY_ID)) {
                            ResearchProjectDocument researchProjectDocumentd = researchService.findResearchProjectDocumentById(document.getId());
                            ImakeResultMessage imakeMessage = new ImakeResultMessage();
                            List<ResearchProjectM> models = new ArrayList<ResearchProjectM>(1);

                            ResearchProjectDocumentM documentM = new ResearchProjectDocumentM();
                            if (researchProjectDocumentd != null) {
                                BeanUtils.copyProperties(researchProjectDocumentd, documentM);
                                if (researchProjectDocumentd.getId() != null) {
                                    documentM.setItemList(researchProjectDocumentd.getId().getItemList());
                                    documentM.setResearchProjectId(researchProjectDocumentd.getId().getResearchProjectId());
                                    documentM.setDocumentId(researchProjectDocumentd.getId().getDocumentId());
                                }
                            }
                            xsource.setDocument(documentM);
                            models.add(xsource);
                            imakeMessage.setResultListObj(models);
                            return getRepresentation(entity, imakeMessage, xstream);
                        }

                        // payment
                        else if (serviceName.equals(ServiceConstant.RESEARCH_PROJECT_PAYMENT_SAVE)) {

                            java.sql.Timestamp now = new java.sql.Timestamp(new Date().getTime());
                            payment.setCreatedDate(now);
                            payment.setUpdatedDate(now);
                            int updateRecord = researchService.saveResearchProjectPayment(payment);
                            return returnUpdateRecord(entity, xsource, updateRecord);
                        } else if (serviceName.equals(ServiceConstant.RESEARCH_PROJECT_PAYMENT_UPDATE)) {
                            java.sql.Timestamp updatedDate = new java.sql.Timestamp(new Date().getTime());
                            payment.setUpdatedDate(updatedDate);

                            ResearchProjectPayment old = researchService.findResearchProjectPaymentById(payment.getId());
                            payment.setCreatedBy(old.getCreatedBy());
                            payment.setCreatedDate(old.getCreatedDate());


                            int updateRecord = researchService.updateResearchProjectPayment(payment);
                            return returnUpdateRecord(entity, xsource, updateRecord);
                        } else if (serviceName.equals(ServiceConstant.RESEARCH_PROJECT_PAYMENT_LIST)) {
                            List<ResearchProjectPaymentM> paymentms = listResearchProjectPayment(payment.getId().getResearchProjectId());

                            ImakeResultMessage imakeMessage = new ImakeResultMessage();
                            List<ResearchProjectM> models = new ArrayList<ResearchProjectM>(1);
                            xsource.setPayments(paymentms);
                            models.add(xsource);
                            imakeMessage.setResultListObj(models);
                            return getRepresentation(entity, imakeMessage, xstream);

                        } else if (serviceName.equals(ServiceConstant.RESEARCH_PROJECT_PAYMENT_DELETE)) {
                            int updateRecord = researchService.deleteResearchProjectPayment(payment);
                            return returnUpdateRecord(entity, xsource, updateRecord);
                        } else if (serviceName.equals(ServiceConstant.RESEARCH_PROJECT_PAYMENT_FIND_BY_ID)) {
                            ResearchProjectPayment researchProjectPaymentd = researchService.findResearchProjectPaymentById(payment.getId());
                            ImakeResultMessage imakeMessage = new ImakeResultMessage();
                            List<ResearchProjectM> models = new ArrayList<ResearchProjectM>(1);

                            ResearchProjectPaymentM researchProjectPaymentM = new ResearchProjectPaymentM();
                            if (researchProjectPaymentd != null) {
                                BeanUtils.copyProperties(researchProjectPaymentd, researchProjectPaymentM);
                                if (researchProjectPaymentd.getId() != null) {
                                    researchProjectPaymentM.setItemList(researchProjectPaymentd.getId().getItemList());
                                    researchProjectPaymentM.setResearchProjectId(researchProjectPaymentd.getId().getResearchProjectId());
                                    researchProjectPaymentM.setPaymentItemId(researchProjectPaymentd.getId().getPaymentItemId());
                                }
                                if (researchProjectPaymentM.getReceivedDate() != null) {
                                    researchProjectPaymentM.setReceivedDateShow(format1.format(researchProjectPaymentM.getReceivedDate()));
                                }

                            }
                            xsource.setPayment(researchProjectPaymentM);
                            models.add(xsource);
                            imakeMessage.setResultListObj(models);
                            return getRepresentation(entity, imakeMessage, xstream);
                        }

                        //progress
                        else if (serviceName.equals(ServiceConstant.RESEARCH_PROJECT_PROGRESS_SAVE)) {

                            java.sql.Timestamp now = new java.sql.Timestamp(new Date().getTime());
                            progress.setCreatedDate(now);
                            progress.setUpdatedDate(now);
                            int updateRecord = researchService.saveResearchProjectProgress(progress);
                            return returnUpdateRecord(entity, xsource, updateRecord);
                        } else if (serviceName.equals(ServiceConstant.RESEARCH_PROJECT_PROGRESS_UPDATE)) {
                            java.sql.Timestamp updatedDate = new java.sql.Timestamp(new Date().getTime());
                            progress.setUpdatedDate(updatedDate);

                            ResearchProjectProgress old = researchService.findResearchProjectProgressById(progress.getId());
                            progress.setCreatedBy(old.getCreatedBy());
                            progress.setCreatedDate(old.getCreatedDate());

                            int updateRecord = researchService.updateResearchProjectProgress(progress);
                            return returnUpdateRecord(entity, xsource, updateRecord);
                        } else if (serviceName.equals(ServiceConstant.RESEARCH_PROJECT_PROGRESS_LIST)) {

                            List<ResearchProjectProgressM> progressms = listResearchProjectProgress(progress.getId().getResearchProjectId());

                            ImakeResultMessage imakeMessage = new ImakeResultMessage();
                            List<ResearchProjectM> models = new ArrayList<ResearchProjectM>(1);
                            xsource.setProgresList(progressms);
                            models.add(xsource);
                            imakeMessage.setResultListObj(models);
                            return getRepresentation(entity, imakeMessage, xstream);

                        } else if (serviceName.equals(ServiceConstant.RESEARCH_PROJECT_PROGRESS_DELETE)) {
                            int updateRecord = researchService.deleteResearchProjectProgress(progress);
                            return returnUpdateRecord(entity, xsource, updateRecord);
                        } else if (serviceName.equals(ServiceConstant.RESEARCH_PROJECT_PROGRESS_FIND_BY_ID)) {
                            ResearchProjectProgress researchProjectProgressd = researchService.findResearchProjectProgressById(progress.getId());
                            ImakeResultMessage imakeMessage = new ImakeResultMessage();
                            List<ResearchProjectM> models = new ArrayList<ResearchProjectM>(1);

                            ResearchProjectProgressM researchProjectProgressM = new ResearchProjectProgressM();
                            if (researchProjectProgressd != null) {
                                BeanUtils.copyProperties(researchProjectProgressd, researchProjectProgressM);
                                if (researchProjectProgressd.getId() != null) {
                                    researchProjectProgressM.setItemList(researchProjectProgressd.getId().getItemList());
                                    researchProjectProgressM.setResearchProjectId(researchProjectProgressd.getId().getResearchProjectId());
                                    researchProjectProgressM.setProgressItemId(researchProjectProgressd.getId().getProgressItemId());
                                }
                                if (researchProjectProgressd.getDueDate() != null) {
                                    researchProjectProgressM.setDueDateShow(format1.format(researchProjectProgressd.getDueDate()));
                                }
                                if (researchProjectProgressd.getSubmitDate() != null) {
                                    researchProjectProgressM.setSubmitDateShow(format1.format(researchProjectProgressd.getSubmitDate()));
                                }
                            }
                            xsource.setProgress(researchProjectProgressM);
                            models.add(xsource);
                            imakeMessage.setResultListObj(models);
                            return getRepresentation(entity, imakeMessage, xstream);
                        }

                        // researcher
                        else if (serviceName.equals(ServiceConstant.RESEARCH_PROJECT_RESEARCHER_SAVE)) {

                            java.sql.Timestamp now = new java.sql.Timestamp(new Date().getTime());
                            researcher.setCreatedDate(now);
                            researcher.setUpdatedDate(now);
                            int updateRecord = researchService.saveResearchProjectResearcher(researcher);
                            return returnUpdateRecord(entity, xsource, updateRecord);
                        } else if (serviceName.equals(ServiceConstant.RESEARCH_PROJECT_RESEARCHER_UPDATE)) {
                            java.sql.Timestamp updatedDate = new java.sql.Timestamp(new Date().getTime());
                            researcher.setUpdatedDate(updatedDate);
                            ResearchProjectResearcher old = researchService.findResearchProjectResearcherById(researcher.getId());
                            researcher.setCreatedBy(old.getCreatedBy());
                            researcher.setCreatedDate(old.getCreatedDate());


                            int updateRecord = researchService.updateResearchProjectResearcher(researcher);
                            return returnUpdateRecord(entity, xsource, updateRecord);
                        } else if (serviceName.equals(ServiceConstant.RESEARCH_PROJECT_RESEARCHER_LIST)) {

                            List<ResearchProjectResearcherM> researcherms = listResearchProjectResearcher(researcher.getId().getResearchProjectId());

                            ImakeResultMessage imakeMessage = new ImakeResultMessage();
                            List<ResearchProjectM> models = new ArrayList<ResearchProjectM>(1);
                            xsource.setResearchers(researcherms);
                            models.add(xsource);
                            imakeMessage.setResultListObj(models);
                            return getRepresentation(entity, imakeMessage, xstream);

                        } else if (serviceName.equals(ServiceConstant.RESEARCH_PROJECT_RESEARCHER_DELETE)) {
                            int updateRecord = researchService.deleteResearchProjectResearcher(researcher);
                            return returnUpdateRecord(entity, xsource, updateRecord);
                        } else if (serviceName.equals(ServiceConstant.RESEARCH_PROJECT_RESEARCHER_FIND_BY_ID)) {
                            ResearchProjectResearcher researchProjectResearcherd = researchService.findResearchProjectResearcherById(researcher.getId());
                            ImakeResultMessage imakeMessage = new ImakeResultMessage();
                            List<ResearchProjectM> models = new ArrayList<ResearchProjectM>(1);

                            ResearchProjectResearcherM researchProjectResearcherM = new ResearchProjectResearcherM();
                            if (researchProjectResearcherd != null) {
                                BeanUtils.copyProperties(researchProjectResearcherd, researchProjectResearcherM);
                                if (researchProjectResearcherd.getId() != null) {
                                    researchProjectResearcherM.setItemList(researchProjectResearcherd.getId().getItemList());
                                    researchProjectResearcherM.setResearchProjectId(researchProjectResearcherd.getId().getResearchProjectId());
                                    researchProjectResearcherM.setResearcherItemId(researchProjectResearcherd.getId().getResearcherItemId());
                                }
                                if (researchProjectResearcherd.getPosition() != null) {
                                    PositionM positionM = new PositionM();
                                    BeanUtils.copyProperties(researchProjectResearcherd.getPosition(), positionM);
                                    researchProjectResearcherM.setPosition(positionM);
                                }
                                if (researchProjectResearcherd.getOrganization() != null) {
                                    OrganizationM organizationM = new OrganizationM();
                                    BeanUtils.copyProperties(researchProjectResearcherd.getOrganization(), organizationM);
                                    researchProjectResearcherM.setOrganization(organizationM);
                                }
                            }

                            xsource.setResearcher(researchProjectResearcherM);
                            models.add(xsource);
                            imakeMessage.setResultListObj(models);
                            return getRepresentation(entity, imakeMessage, xstream);
                        }

                        //withdraw
                        else if (serviceName.equals(ServiceConstant.RESEARCH_PROJECT_WITHDRAW_SAVE)) {

                            java.sql.Timestamp now = new java.sql.Timestamp(new Date().getTime());
                            withdraw.setCreatedDate(now);
                            withdraw.setUpdatedDate(now);

                            int updateRecord = researchService.saveResearchProjectWithdraw(withdraw);
                            return returnUpdateRecord(entity, xsource, updateRecord);
                        } else if (serviceName.equals(ServiceConstant.RESEARCH_PROJECT_WITHDRAW_UPDATE)) {
                            java.sql.Timestamp updatedDate = new java.sql.Timestamp(new Date().getTime());
                            withdraw.setUpdatedDate(updatedDate);
                            ResearchProjectWithdraw old = researchService.findResearchProjectWithdrawById(withdraw.getId());
                            withdraw.setCreatedBy(old.getCreatedBy());
                            withdraw.setCreatedDate(old.getCreatedDate());

                            int updateRecord = researchService.updateResearchProjectWithdraw(withdraw);
                            return returnUpdateRecord(entity, xsource, updateRecord);
                        } else if (serviceName.equals(ServiceConstant.RESEARCH_PROJECT_WITHDRAW_LIST)) {

                            List<ResearchProjectWithdrawM> withdrawms = listResearchProjectWithdraw(withdraw.getId().getResearchProjectId());

                            ImakeResultMessage imakeMessage = new ImakeResultMessage();
                            List<ResearchProjectM> models = new ArrayList<ResearchProjectM>(1);
                            xsource.setWithdraws(withdrawms);
                            models.add(xsource);
                            imakeMessage.setResultListObj(models);
                            return getRepresentation(entity, imakeMessage, xstream);

                        } else if (serviceName.equals(ServiceConstant.RESEARCH_PROJECT_WITHDRAW_DELETE)) {
                            int updateRecord = researchService.deleteResearchProjectWithdraw(withdraw);
                            return returnUpdateRecord(entity, xsource, updateRecord);
                        } else if (serviceName.equals(ServiceConstant.RESEARCH_PROJECT_WITHDRAW_FIND_BY_ID)) {
                            ResearchProjectWithdraw researchProjectWithdrawd = researchService.findResearchProjectWithdrawById(withdraw.getId());
                            ImakeResultMessage imakeMessage = new ImakeResultMessage();
                            List<ResearchProjectM> models = new ArrayList<ResearchProjectM>(1);

                            ResearchProjectWithdrawM researchProjectWithdrawM = new ResearchProjectWithdrawM();
                            if (researchProjectWithdrawd != null) {
                                BeanUtils.copyProperties(researchProjectWithdrawd, researchProjectWithdrawM);
                                if (researchProjectWithdrawd.getId() != null) {
                                    researchProjectWithdrawM.setItemList(researchProjectWithdrawd.getId().getItemList());
                                    researchProjectWithdrawM.setResearchProjectId(researchProjectWithdrawd.getId().getResearchProjectId());
                                    researchProjectWithdrawM.setWithdrawItemId(researchProjectWithdrawd.getId().getWithdrawItemId());
                                }

                                if (researchProjectWithdrawd.getWithdrawDate() != null) {
                                    researchProjectWithdrawM.setWithdrawDateShow(format1.format(researchProjectWithdrawM.getWithdrawDate()));
                                }
                            }
                            xsource.setWithdraw(researchProjectWithdrawM);
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

    private List<ResearchProjectProgressM> listResearchProjectProgress(Integer researchProjectId) {
        List<ResearchProjectProgress> progressds = researchService.listResearchProjectProgress(researchProjectId);
        List<ResearchProjectProgressM> progressms = new ArrayList<ResearchProjectProgressM>(
                progressds.size());
        for (ResearchProjectProgress progressd : progressds) {
            ResearchProjectProgressM progressm = new ResearchProjectProgressM();
            BeanUtils.copyProperties(progressd, progressm);
            if (progressd.getId() != null) {
                progressm.setResearchProjectId(progressd.getId().getResearchProjectId());
                progressm.setItemList(progressd.getId().getItemList());
            }

            if (progressd.getDueDate() != null)
                progressm.setDueDateShow(format1.format(progressd.getDueDate()));
            if (progressd.getSubmitDate() != null)
                progressm.setSubmitDateShow(format1.format(progressd.getSubmitDate()));
            progressm.setPagging(null);
            progressms.add(progressm);
        }
        return progressms;
    }

    private List<ResearchProjectResearcherM> listResearchProjectResearcher(Integer researchProjectId) {
        List<ResearchProjectResearcher> researcherds = researchService.listResearchProjectResearcher(researchProjectId);
        List<ResearchProjectResearcherM> researcherms = new ArrayList<ResearchProjectResearcherM>(
                researcherds.size());
        for (ResearchProjectResearcher researcherd : researcherds) {
            ResearchProjectResearcherM researcherm = new ResearchProjectResearcherM();
            BeanUtils.copyProperties(researcherd, researcherm);
            if (researcherd.getPosition() != null) {
                PositionM positionM = new PositionM();
                BeanUtils.copyProperties(researcherd.getPosition(), positionM);
                researcherm.setPosition(positionM);
            }
            if (researcherd.getOrganization() != null) {
                OrganizationM organizationM = new OrganizationM();
                BeanUtils.copyProperties(researcherd.getOrganization(), organizationM);
                researcherm.setOrganization(organizationM);
            }
            if (researcherd.getId() != null) {
                researcherm.setResearchProjectId(researcherd.getId().getResearchProjectId());
                researcherm.setItemList(researcherd.getId().getItemList());
            }
            researcherm.setPagging(null);
            researcherms.add(researcherm);
        }
        return researcherms;
    }

    private List<ResearchProjectWithdrawM> listResearchProjectWithdraw(Integer researchProjectId) {
        List<ResearchProjectWithdraw> withdrawds = researchService.listResearchProjectWithdraw(researchProjectId);
        List<ResearchProjectWithdrawM> withdrawms = new ArrayList<ResearchProjectWithdrawM>(
                withdrawds.size());
        for (ResearchProjectWithdraw withdrawd : withdrawds) {
            ResearchProjectWithdrawM withdrawm = new ResearchProjectWithdrawM();
            BeanUtils.copyProperties(withdrawd, withdrawm);
            if (withdrawd.getId() != null) {
                withdrawm.setResearchProjectId(withdrawd.getId().getResearchProjectId());
                withdrawm.setItemList(withdrawd.getId().getItemList());
            }
            if (withdrawd.getWithdrawDate() != null)
                withdrawm.setWithdrawDateShow(format1.format(withdrawd.getWithdrawDate()));
            if (withdrawd.getBalance() != null)
                withdrawm.setBalanceShow(df2.format(withdrawd.getBalance()));
            if (withdrawd.getAmountWithdraw() != null)
                withdrawm.setAmountWithdrawShow(df2.format(withdrawd.getAmountWithdraw()));


            withdrawm.setPagging(null);
            withdrawms.add(withdrawm);
        }
        return withdrawms;
    }
	/**/

    private List<ResearchProjectPaymentM> listResearchProjectPayment(Integer researchProjectId) {
        List<ResearchProjectPayment> paymentds = researchService.listResearchProjectPayment(researchProjectId);
        List<ResearchProjectPaymentM> paymentms = new ArrayList<ResearchProjectPaymentM>(
                paymentds.size());
        for (ResearchProjectPayment paymentd : paymentds) {
            ResearchProjectPaymentM paymentm = new ResearchProjectPaymentM();
            BeanUtils.copyProperties(paymentd, paymentm);
            if (paymentd.getId() != null) {
                paymentm.setResearchProjectId(paymentd.getId().getResearchProjectId());
                paymentm.setItemList(paymentd.getId().getItemList());
            }
            if (paymentd.getReceivedDate() != null)
                paymentm.setReceivedDateShow(format1.format(paymentd.getReceivedDate()));
            if (paymentd.getAmountReceived() != null)
                paymentm.setAmountReceivedShow(df2.format(Double.valueOf(paymentd.getAmountReceived())));

            paymentm.setPagging(null);
            paymentms.add(paymentm);
        }
        return paymentms;
    }

    private List<ResearchProjectDocumentM> listResearchProjectDocument(Integer researchProjectId) {
        List<ResearchProjectDocument> documentds = researchService.listResearchProjectDocument(researchProjectId);
        List<ResearchProjectDocumentM> documentms = new ArrayList<ResearchProjectDocumentM>(
                documentds.size());
        for (ResearchProjectDocument documentd : documentds) {
            ResearchProjectDocumentM documentm = new ResearchProjectDocumentM();
            BeanUtils.copyProperties(documentd, documentm);

            if (documentd.getId() != null) {
                documentm.setResearchProjectId(documentd.getId().getResearchProjectId());
                documentm.setItemList(documentd.getId().getItemList());
            }
            documentm.setPagging(null);
            documentms.add(documentm);
        }
        return documentms;
    }

    private List<DocAssignMappingM> listDocAssignMapping(Integer refId) {
        DocAssignMapping docAssignMapping = new DocAssignMapping();
        DocAssignMappingPK docPk = new DocAssignMappingPK();
        docPk.setRefId(refId);
        docPk.setRefType(ServiceConstant.DOC_TYPE_RESEARCH);
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

    private List<ResearchProjectM> getResearchProjectModels(
            java.util.ArrayList<ResearchProject> domains) {
        List<ResearchProjectM> models = new ArrayList<ResearchProjectM>(
                domains.size());
        for (ResearchProject domain : domains) {
            ResearchProjectM model = new ResearchProjectM();
            BeanUtils.copyProperties(domain, model);
            model.setPagging(null);
            models.add(model);
        }
        return models;
    }

    private Representation returnUpdateRecord(Representation entity, ResearchProjectM model, int updateRecord) {
        ImakeResultMessage imakeMessage = new ImakeResultMessage();
        List<ResearchProjectM> xsources = new ArrayList<ResearchProjectM>(1);
        model.setUpdateRecord(updateRecord);
        xsources.add(model);
        imakeMessage.setResultListObj(xsources);
        return getRepresentation(entity, imakeMessage, xstream);
    }

    @Override
    protected Representation get(Variant variant) throws ResourceException {
        // TODO Auto-generated method stub
        ResearchProjectM xsource = new ResearchProjectM();

        ResearchProject domain = researchService.findResearchProjectById(6, null);

        ImakeResultMessage imakeMessage = new ImakeResultMessage();
        List<ResearchProjectM> models = new ArrayList<ResearchProjectM>(1);
        ResearchProjectM model = new ResearchProjectM();
        BeanUtils.copyProperties(domain, model);
        model.setPagging(null);

        List<ResearchProjectDocumentM> documentms = listResearchProjectDocument(xsource.getResearchProjectId());
        model.setDocuments(documentms);

        List<ResearchProjectPaymentM> paymentms = listResearchProjectPayment(xsource.getResearchProjectId());
        model.setPayments(paymentms);

        List<ResearchProjectProgressM> progressms = listResearchProjectProgress(xsource.getResearchProjectId());
        model.setProgresList(progressms);

        List<ResearchProjectResearcherM> researcherms = listResearchProjectResearcher(xsource.getResearchProjectId());
        model.setResearchers(researcherms);

        List<ResearchProjectWithdrawM> withdrawms = listResearchProjectWithdraw(xsource.getResearchProjectId());
        model.setWithdraws(withdrawms);

        List<DocAssignMappingM> docAssignMappingms = listDocAssignMapping(xsource.getResearchProjectId());
        model.setDocAssignMappings(docAssignMappingms);

        models.add(model);
        imakeMessage.setResultListObj(models);
        return getRepresentation(null, imakeMessage, xstream);

        //	 return null;
    }


    public com.thoughtworks.xstream.XStream getXstream() {
        return xstream;
    }

    public void setXstream(com.thoughtworks.xstream.XStream xstream) {
        this.xstream = xstream;
    }

}
