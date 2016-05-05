package th.ac.kmutt.research.rest.resource;

import java.io.IOException;
import java.io.InputStream;
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
import th.ac.kmutt.research.domain.JournalPaper;
import th.ac.kmutt.research.domain.JournalPapersConference;
import th.ac.kmutt.research.domain.JournalPapersDocument;
import th.ac.kmutt.research.domain.JournalPapersDocumentPK;
import th.ac.kmutt.research.domain.JournalPapersJournal;
import th.ac.kmutt.research.domain.JournalPapersWriter;
import th.ac.kmutt.research.domain.JournalPapersWriterPK;
import th.ac.kmutt.research.domain.Organization;
import th.ac.kmutt.research.domain.Position;
import th.ac.kmutt.research.domain.ResearchProject;
import th.ac.kmutt.research.domain.Researcher;
import th.ac.kmutt.research.model.DocAssignMappingM;
import th.ac.kmutt.research.model.JournalPaperM;
import th.ac.kmutt.research.model.JournalPapersConferenceM;
import th.ac.kmutt.research.model.JournalPapersDocumentM;
import th.ac.kmutt.research.model.JournalPapersJournalM;
import th.ac.kmutt.research.model.JournalPapersWriterM;
import th.ac.kmutt.research.model.OrganizationM;
import th.ac.kmutt.research.model.PositionM;
import th.ac.kmutt.research.model.ResearcherM;
import th.ac.kmutt.research.model.TitleM;
import th.ac.kmutt.research.portal.model.UserPortalM;
import th.ac.kmutt.research.service.ResearchService;
import th.ac.kmutt.research.xstream.common.ImakeResultMessage;
import th.ac.kmutt.research.xstream.common.Paging;

public class JournalPapersResource extends BaseResource {
    private static final Logger logger = Logger.getLogger(ServiceConstant.LOG_APPENDER);
    private static SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
    @Autowired
    @Qualifier("researchServiceJpaImpl")
    private ResearchService researchService;
    //private ConsultantReportService consultantReportService;
    private com.thoughtworks.xstream.XStream xstream;

    public JournalPapersResource() {
        super();
        logger.debug("into constructor JournalPapersResource");
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
            xstream.processAnnotations(JournalPaperM.class);// or xstream.autodetectAnnotations(true); (Auto-detect  Annotations)
            JournalPaperM xsource = new JournalPaperM();
            Object xtarget = xstream.fromXML(in);
            if (xtarget != null) {
                xsource = (JournalPaperM) xtarget;
                if (xsource != null) {
                    JournalPaper domain = new JournalPaper();
                    BeanUtils.copyProperties(xsource, domain);


                    JournalPapersDocument journalPapersDocument = new JournalPapersDocument();
                    if (xsource.getJournalPapersDocument() != null) {
                        BeanUtils.copyProperties(xsource.getJournalPapersDocument(), journalPapersDocument);
                        JournalPapersDocumentPK pk1 = new JournalPapersDocumentPK();
                        pk1.setDocumentId(1);
                        if (xsource.getJournalPapersDocument().getJournalPapersId() != null)
                            pk1.setJournalPapersId(xsource.getJournalPapersDocument().getJournalPapersId());
                        if (xsource.getJournalPapersDocument().getItemList() != null)
                            pk1.setItemList(xsource.getJournalPapersDocument().getItemList());
                        journalPapersDocument.setId(pk1);
                    }

                    JournalPapersWriter journalPapersWriter = new JournalPapersWriter();
                    if (xsource.getJournalPapersWriter() != null) {
                        BeanUtils.copyProperties(xsource.getJournalPapersWriter(), journalPapersWriter);
                        JournalPapersWriterPK pk1 = new JournalPapersWriterPK();
                        pk1.setWriterItemId(1);
                        if (xsource.getJournalPapersWriter().getJournalPapersId() != null)
                            pk1.setJournalPapersId(xsource.getJournalPapersWriter().getJournalPapersId());
                        if (xsource.getJournalPapersWriter().getItemList() != null)
                            pk1.setItemList(xsource.getJournalPapersWriter().getItemList());
                        if (xsource.getJournalPapersWriter().getPosition() != null && xsource.getJournalPapersWriter().getPosition().getPositionId() != null) {
                            Position position = new Position();
                            BeanUtils.copyProperties(xsource.getJournalPapersWriter().getPosition(), position);
                            journalPapersWriter.setPosition(position);
                        }
                        if (xsource.getJournalPapersWriter().getOrganization() != null && xsource.getJournalPapersWriter().getOrganization().getOrganizationId() != null) {
                            Organization organization = new Organization();
                            BeanUtils.copyProperties(xsource.getJournalPapersWriter().getOrganization(), organization);
                            journalPapersWriter.setOrganization(organization);
                        }
                        if (xsource.getJournalPapersWriter().getResearcher() != null && xsource.getJournalPapersWriter().getResearcher().getResearcherId() != null) {
                            Researcher researcher = new Researcher();
                            BeanUtils.copyProperties(xsource.getJournalPapersWriter().getResearcher(), researcher);
                            journalPapersWriter.setResearcher(researcher);
                        }

                        journalPapersWriter.setId(pk1);

                    }

                    if (xsource.getServiceName() != null
                            && xsource.getServiceName().length() != 0) {
                        String serviceName = xsource.getServiceName();
                        if (serviceName.equals(ServiceConstant.JOURNAL_PAPERS_FIND_BY_ID)) {
                            domain = researchService.findJournalPapersById(xsource.getJournalPapersId(), xsource.getUserid());
                            ImakeResultMessage imakeMessage = new ImakeResultMessage();
                            if (domain != null) {
                                List<JournalPaperM> models = new ArrayList<JournalPaperM>(1);
                                JournalPaperM model = new JournalPaperM();
                                BeanUtils.copyProperties(domain, model);
                                //if(domain.getResearchProjectId())
                                if (domain.getJournalPapersConference() != null) {
                                    JournalPapersConferenceM journalPapersConferenceM = new JournalPapersConferenceM();
                                    BeanUtils.copyProperties(domain.getJournalPapersConference(), journalPapersConferenceM);
                                    if (domain.getJournalPapersConference().getStartDate() != null) {
                                        journalPapersConferenceM.setStartDateShow(format1.format(domain.getJournalPapersConference().getStartDate()));
                                    }
                                    if (domain.getJournalPapersConference().getEndDate() != null) {
                                        journalPapersConferenceM.setEndDateShow(format1.format(domain.getJournalPapersConference().getEndDate()));
                                    }
                                    model.setJournalPapersConference(journalPapersConferenceM);
                                }
                                if (domain.getJournalPapersJournal() != null) {
                                    JournalPapersJournalM journalPapersJournal = new JournalPapersJournalM();
                                    BeanUtils.copyProperties(domain.getJournalPapersJournal(), journalPapersJournal);
                                    model.setJournalPapersJournal(journalPapersJournal);
                                }
                                model.setPagging(null);
                                List<JournalPapersDocumentM> journalPapersDocumentms = listJournalPapersDocument(xsource.getJournalPapersId());

                                model.setJournalPapersDocuments(journalPapersDocumentms);
                                List<JournalPapersWriterM> journalPapersWriterms = listJournalPapersWriter(xsource.getJournalPapersId());
                                model.setJournalPapersWriters(journalPapersWriterms);

                                List<DocAssignMappingM> docAssignMappingms = listDocAssignMapping(xsource.getJournalPapersId());
                                model.setDocAssignMappings(docAssignMappingms);

                                models.add(model);
                                imakeMessage.setResultListObj(models);
                            }
                            return getRepresentation(entity, imakeMessage, xstream);
                        } else if (serviceName.equals(ServiceConstant.JOURNAL_PAPERS_SAVE)) {
                            java.sql.Timestamp now = new java.sql.Timestamp(new Date().getTime());
                            domain.setCreatedDate(now);
                            domain.setUpdatedDate(now);
                            domain.setFlag(ServiceConstant.FLAG_ACTIVE);
                            int updateRecord = researchService.saveJournalPaper(domain);
                            //	entityManager.flush();
                            Integer types = domain.getType();

                            if (types == 1) { // journal
                                if (xsource.getJournalPapersJournal() != null) {
                                    JournalPapersJournal journalPapersJournal = new JournalPapersJournal();
                                    BeanUtils.copyProperties(xsource.getJournalPapersJournal(), journalPapersJournal);
                                    domain.setJournalPapersJournal(journalPapersJournal);
                                }
                                domain.getJournalPapersJournal().setJournalPapersId(updateRecord);
                                domain.getJournalPapersJournal().setCreatedDate(now);
                                domain.getJournalPapersJournal().setUpdatedDate(now);
                                domain.getJournalPapersJournal().setCreatedBy(domain.getCreatedBy());
                                domain.getJournalPapersJournal().setUpdatedBy(domain.getUpdatedBy());
                                researchService.saveJournalPapersJournal(domain.getJournalPapersJournal());

                            } else if (types == 2) {
                                if (xsource.getJournalPapersConference() != null) {
                                    JournalPapersConference journalPapersConference = new JournalPapersConference();
                                    BeanUtils.copyProperties(xsource.getJournalPapersConference(), journalPapersConference);
                                    domain.setJournalPapersConference(journalPapersConference);
                                }
                                domain.getJournalPapersConference().setCreatedDate(now);
                                domain.getJournalPapersConference().setUpdatedDate(now);
                                domain.getJournalPapersConference().setCreatedBy(domain.getCreatedBy());
                                domain.getJournalPapersConference().setUpdatedBy(domain.getUpdatedBy());
                                domain.getJournalPapersConference().setJournalPapersId(updateRecord);
                                researchService.saveJournalPapersConference(domain.getJournalPapersConference());

                            }
                            return returnUpdateRecord(entity, xsource, updateRecord);
                        } else if (serviceName.equals(ServiceConstant.JOURNAL_PAPERS_UPDATE)) {
                            java.sql.Timestamp updatedDate = new java.sql.Timestamp(new Date().getTime());
                            domain.setUpdatedDate(updatedDate);
                            int updateRecord = researchService.updateJournalPaper(domain);
                            Integer types = domain.getType();

                            if (types == 1) { // journal
                                if (xsource.getJournalPapersJournal() != null) {
                                    JournalPapersJournal journalPapersJournal = new JournalPapersJournal();
                                    BeanUtils.copyProperties(xsource.getJournalPapersJournal(), journalPapersJournal);
                                    domain.setJournalPapersJournal(journalPapersJournal);
                                }
                                domain.getJournalPapersJournal().setJournalPapersId(updateRecord);
                                domain.getJournalPapersJournal().setCreatedDate(updatedDate);
                                domain.getJournalPapersJournal().setUpdatedDate(updatedDate);
                                domain.getJournalPapersJournal().setCreatedBy(domain.getCreatedBy());
                                domain.getJournalPapersJournal().setUpdatedBy(domain.getUpdatedBy());
                                researchService.updateJournalPapersJournal(domain.getJournalPapersJournal());

                            } else if (types == 2) {
                                if (xsource.getJournalPapersConference() != null) {
                                    JournalPapersConference journalPapersConference = new JournalPapersConference();
                                    BeanUtils.copyProperties(xsource.getJournalPapersConference(), journalPapersConference);
                                    domain.setJournalPapersConference(journalPapersConference);
                                }
                                domain.getJournalPapersConference().setCreatedDate(updatedDate);
                                domain.getJournalPapersConference().setUpdatedDate(updatedDate);
                                domain.getJournalPapersConference().setCreatedBy(domain.getCreatedBy());
                                domain.getJournalPapersConference().setUpdatedBy(domain.getUpdatedBy());
                                domain.getJournalPapersConference().setJournalPapersId(updateRecord);
                                researchService.updateJournalPapersConference(domain.getJournalPapersConference());

                            }

                            return returnUpdateRecord(entity, xsource, updateRecord);
                        } else if (serviceName.equals(ServiceConstant.JOURNAL_PAPERS_ITEMS_DELETE)) {

                            String[] ids = xsource.getIds();
                            //logger.debug("xbpsTerm.getMsIds()="+xbpsTerm.getMsIds());
                            int updateRecord = 0;

                            Integer types = domain.getType();
                            for (int i = 0; i < ids.length; i++) {
                                Integer id = Integer.parseInt(ids[i]);
                                JournalPaper item_domain = new JournalPaper();
                                item_domain.setJournalPapersId(id);
                                boolean isDeleted = false;
                                item_domain = researchService.findJournalPapersById(domain.getJournalPapersId(), xsource.getUserid());
                                //int updateRecord=0;
                                if (item_domain.getFlag() != null && item_domain.getFlag().equals(ServiceConstant.FLAG_INACTIVE)) {
                                    updateRecord = researchService.deleteJournalPaper(item_domain);
                                    isDeleted = true;
                                } else {
                                    item_domain.setUpdateType(ServiceConstant.UPDATE_TYEP_FLAG);
                                    item_domain.setFlag(ServiceConstant.FLAG_INACTIVE);
                                    updateRecord = researchService.updateFlagJournalPaper(item_domain);
                                }

                                if (isDeleted) {
                                    if (types == 1) {
                                        JournalPapersJournal item = new JournalPapersJournal();
                                        item.setJournalPapersId(id);
                                        updateRecord = researchService.deleteJournalPapersJournal(item);
                                    } else if (types == 2) {
                                        JournalPapersConference item = new JournalPapersConference();
                                        item.setJournalPapersId(id);
                                        updateRecord = researchService.deleteJournalPapersConference(item);
                                    }
                                }
                            }
                            return returnUpdateRecord(entity, xsource, updateRecord);
                        } else if (serviceName.equals(ServiceConstant.JOURNAL_PAPERS_DELETE)) {
                            boolean isDeleted = false;
                            domain = researchService.findJournalPapersById(domain.getJournalPapersId(), xsource.getUserid());
                            int updateRecord = 0;
                            if (domain.getFlag() != null && domain.getFlag().equals(ServiceConstant.FLAG_INACTIVE)) {
                                updateRecord = researchService.deleteJournalPaper(domain);
                                isDeleted = true;
                            } else {
                                domain.setUpdateType(ServiceConstant.UPDATE_TYEP_FLAG);
                                domain.setFlag(ServiceConstant.FLAG_INACTIVE);
                                updateRecord = researchService.updateFlagJournalPaper(domain);
                            }

                            Integer types = domain.getType();
                            if (isDeleted) {
                                if (types == 1) { // journal
                                    if (xsource.getJournalPapersJournal() != null) {
                                        JournalPapersJournal journalPapersJournal = new JournalPapersJournal();
                                        BeanUtils.copyProperties(xsource.getJournalPapersJournal(), journalPapersJournal);
                                        domain.setJournalPapersJournal(journalPapersJournal);
                                    }
                                    domain.getJournalPapersJournal().setJournalPapersId(updateRecord);
                                    //	domain.getJournalPapersJournal().setCreatedDate(now);
                                    //	domain.getJournalPapersJournal().setUpdatedDate(updatedDate);
                                    //	domain.getJournalPapersJournal().setCreatedBy(domain.getCreatedBy());
                                    //	domain.getJournalPapersJournal().setUpdatedBy(domain.getUpdatedBy());

                                    researchService.deleteJournalPapersJournal(domain.getJournalPapersJournal());

                                } else if (types == 2) {
                                    if (xsource.getJournalPapersConference() != null) {
                                        JournalPapersConference journalPapersConference = new JournalPapersConference();
                                        BeanUtils.copyProperties(xsource.getJournalPapersConference(), journalPapersConference);
                                        domain.setJournalPapersConference(journalPapersConference);
                                    }
                                    //domain.getJournalPapersConference().setCreatedDate(now);
                                    //domain.getJournalPapersConference().setUpdatedDate(updatedDate);
                                    //domain.getJournalPapersConference().setCreatedBy(domain.getCreatedBy());
                                    //domain.getJournalPapersConference().setUpdatedBy(domain.getUpdatedBy());
                                    domain.getJournalPapersConference().setJournalPapersId(updateRecord);

                                    researchService.deleteJournalPapersConference(domain.getJournalPapersConference());

                                }
                            }
                            return returnUpdateRecord(entity, xsource, updateRecord);
                        } else if (serviceName.equals(ServiceConstant.JOURNAL_PAPERS_UPDATE_FLAG)) {
                            int updateRecord = researchService.updateFlagJournalPaper(domain);
                            return returnUpdateRecord(entity, xsource, updateRecord);
                        } else if (serviceName.equals(ServiceConstant.JOURNAL_PAPERS_SEARCH)) {
                            Paging page = xsource.getPaging();
                            @SuppressWarnings("rawtypes")
                            List result = (List) researchService.searchJournalPaper(domain, page, xsource.getKeySearch(), xsource.getUserid());
                            if (result != null && result.size() == 2) {
                                java.util.ArrayList<JournalPaper> domains = (java.util.ArrayList<JournalPaper>) result
                                        .get(0);
                                String domains_size = (String) result.get(1);
                                ImakeResultMessage imakeMessage = new ImakeResultMessage();

                                List<JournalPaperM> models = new ArrayList<JournalPaperM>();
                                if (domains_size != null && domains_size.length() != 0)
                                    imakeMessage.setMaxRow(domains_size);
                                if (domains != null && domains.size() > 0) {
                                    models = getJournalPaperModels(domains);
                                }
                                imakeMessage.setResultListObj(models);
                                return getRepresentation(entity, imakeMessage, xstream);
                            }
                        } else if (serviceName.equals(ServiceConstant.JOURNAL_PAPERS_DOCUMENT_SAVE)) {
                            /*private JournalPapersDocumentM journalPapersDocument;
							private JournalPapersWriterM journalPapersWriter;*/

                            java.sql.Timestamp now = new java.sql.Timestamp(new Date().getTime());
                            journalPapersDocument.setCreatedDate(now);
                            journalPapersDocument.setUpdatedDate(now);
                            int updateRecord = researchService.saveJournalPapersDocument(journalPapersDocument);
                            return returnUpdateRecord(entity, xsource, updateRecord);
                        } else if (serviceName.equals(ServiceConstant.JOURNAL_PAPERS_DOCUMENT_UPDATE)) {
                            java.sql.Timestamp updatedDate = new java.sql.Timestamp(new Date().getTime());
                            journalPapersDocument.setUpdatedDate(updatedDate);
                            JournalPapersDocument old = researchService.findJournalPapersDocumentById(journalPapersDocument.getId());
                            journalPapersDocument.setCreatedBy(old.getCreatedBy());
                            journalPapersDocument.setCreatedDate(old.getCreatedDate());
                            int updateRecord = researchService.updateJournalPapersDocument(journalPapersDocument);
                            return returnUpdateRecord(entity, xsource, updateRecord);
                        } else if (serviceName.equals(ServiceConstant.JOURNAL_PAPERS_DOCUMENT_LIST)) {

                            List<JournalPapersDocumentM> journalPapersDocumentms = listJournalPapersDocument(journalPapersDocument.getId().getJournalPapersId());

                            ImakeResultMessage imakeMessage = new ImakeResultMessage();
                            List<JournalPaperM> models = new ArrayList<JournalPaperM>(1);
                            xsource.setJournalPapersDocuments(journalPapersDocumentms);
                            models.add(xsource);
                            imakeMessage.setResultListObj(models);
                            return getRepresentation(entity, imakeMessage, xstream);

                        } else if (serviceName.equals(ServiceConstant.JOURNAL_PAPERS_DOCUMENT_DELETE)) {
                            int updateRecord = researchService.deleteJournalPapersDocument(journalPapersDocument);
                            return returnUpdateRecord(entity, xsource, updateRecord);
                        } else if (serviceName.equals(ServiceConstant.JOURNAL_PAPERS_DOCUMENT_FIND_BY_ID)) {
                            JournalPapersDocument journalPapersDocumentd = researchService.findJournalPapersDocumentById(journalPapersDocument.getId());
                            ImakeResultMessage imakeMessage = new ImakeResultMessage();
                            List<JournalPaperM> models = new ArrayList<JournalPaperM>(1);


                            JournalPapersDocumentM journalPapersDocumentM = new JournalPapersDocumentM();
                            if (journalPapersDocumentd != null) {
                                BeanUtils.copyProperties(journalPapersDocumentd, journalPapersDocumentM);
                                if (journalPapersDocumentd.getId() != null) {
                                    journalPapersDocumentM.setItemList(journalPapersDocumentd.getId().getItemList());
                                    journalPapersDocumentM.setJournalPapersId(journalPapersDocumentd.getId().getJournalPapersId());
                                    journalPapersDocumentM.setDocumentId(journalPapersDocumentd.getId().getDocumentId());
                                }
                            }
                            xsource.setJournalPapersDocument(journalPapersDocumentM);
                            models.add(xsource);
                            imakeMessage.setResultListObj(models);
                            return getRepresentation(entity, imakeMessage, xstream);
                        } else if (serviceName.equals(ServiceConstant.JOURNAL_PAPERS_WRITER_SAVE)) {
							/*private JournalPapersDocumentM journalPapersDocument;
							private JournalPapersWriterM journalPapersWriter;*/

                            java.sql.Timestamp now = new java.sql.Timestamp(new Date().getTime());
                            journalPapersWriter.setCreatedDate(now);
                            journalPapersWriter.setUpdatedDate(now);
                            int updateRecord = researchService.saveJournalPapersWriter(journalPapersWriter);
                            return returnUpdateRecord(entity, xsource, updateRecord);
                        } else if (serviceName.equals(ServiceConstant.JOURNAL_PAPERS_WRITER_UPDATE)) {
                            java.sql.Timestamp updatedDate = new java.sql.Timestamp(new Date().getTime());

                            JournalPapersWriter old = researchService.findJournalPapersWriterById(journalPapersWriter.getId());
                            journalPapersWriter.setCreatedBy(old.getCreatedBy());
                            journalPapersWriter.setCreatedDate(old.getCreatedDate());

                            journalPapersWriter.setUpdatedDate(updatedDate);
                            int updateRecord = researchService.updateJournalPapersWriter(journalPapersWriter);
                            return returnUpdateRecord(entity, xsource, updateRecord);
                        } else if (serviceName.equals(ServiceConstant.JOURNAL_PAPERS_WRITER_LIST)) {

                            List<JournalPapersWriterM> journalPapersWriterms = listJournalPapersWriter(journalPapersWriter.getId().getJournalPapersId());

                            ImakeResultMessage imakeMessage = new ImakeResultMessage();
                            List<JournalPaperM> models = new ArrayList<JournalPaperM>(1);
                            xsource.setJournalPapersWriters(journalPapersWriterms);
                            models.add(xsource);
                            imakeMessage.setResultListObj(models);
                            return getRepresentation(entity, imakeMessage, xstream);

                        } else if (serviceName.equals(ServiceConstant.JOURNAL_PAPERS_WRITER_DELETE)) {
                            int updateRecord = researchService.deleteJournalPapersWriter(journalPapersWriter);
                            return returnUpdateRecord(entity, xsource, updateRecord);
                        } else if (serviceName.equals(ServiceConstant.JOURNAL_PAPERS_WRITER_COUNT)) {

                            int updateRecord = researchService.countJournalPapersWriter(journalPapersWriter);
                            return returnUpdateRecord(entity, xsource, updateRecord);
                        } else if (serviceName.equals(ServiceConstant.JOURNAL_PAPERS_WRITER_FIND_BY_ID)) {
                            JournalPapersWriter journalPapersWriterd = researchService.findJournalPapersWriterById(journalPapersWriter.getId());
                            ImakeResultMessage imakeMessage = new ImakeResultMessage();
                            List<JournalPaperM> models = new ArrayList<JournalPaperM>(1);


                            JournalPapersWriterM journalPapersWriterM = new JournalPapersWriterM();
                            if (journalPapersWriterd != null) {
                                BeanUtils.copyProperties(journalPapersWriterd, journalPapersWriterM);
                                if (journalPapersWriterd.getId() != null) {
                                    journalPapersWriterM.setItemList(journalPapersWriterd.getId().getItemList());
                                    journalPapersWriterM.setJournalPapersId(journalPapersWriterd.getId().getJournalPapersId());
                                    journalPapersWriterM.setWriterItemId(journalPapersWriterd.getId().getWriterItemId());
                                }
                                if (journalPapersWriterd.getPosition() != null) {
                                    PositionM positionM = new PositionM();
                                    BeanUtils.copyProperties(journalPapersWriterd.getPosition(), positionM);
                                    journalPapersWriterM.setPosition(positionM);
                                }
                                if (journalPapersWriterd.getOrganization() != null) {
                                    OrganizationM organization = new OrganizationM();
                                    BeanUtils.copyProperties(journalPapersWriterd.getOrganization(), organization);
                                    journalPapersWriterM.setOrganization(organization);
                                }
                                if (journalPapersWriterd.getResearcher() != null) {
                                    ResearcherM researcher = new ResearcherM();
                                    BeanUtils.copyProperties(journalPapersWriterd.getResearcher(), researcher);
                                    journalPapersWriterM.setResearcher(researcher);
                                }
                            }
                            xsource.setJournalPapersWriter(journalPapersWriterM);
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

    private List<JournalPapersWriterM> listJournalPapersWriter(Integer journalPapersId) {
        List<JournalPapersWriter> journalPapersWriterds = researchService.listJournalPapersWriter(journalPapersId);
        List<JournalPapersWriterM> journalPapersWriterms = new ArrayList<JournalPapersWriterM>(
                journalPapersWriterds.size());
        for (JournalPapersWriter journalPapersWriterd : journalPapersWriterds) {
            JournalPapersWriterM journalPapersWriterm = new JournalPapersWriterM();
            BeanUtils.copyProperties(journalPapersWriterd, journalPapersWriterm);
            if (journalPapersWriterd.getId() != null) {
                journalPapersWriterm.setJournalPapersId(journalPapersWriterd.getId().getJournalPapersId());
                journalPapersWriterm.setItemList(journalPapersWriterd.getId().getItemList());
            }
            if (journalPapersWriterd.getPosition() != null) {
                PositionM positionM = new PositionM();
                BeanUtils.copyProperties(journalPapersWriterd.getPosition(), positionM);
                journalPapersWriterm.setPosition(positionM);
            }
            if (journalPapersWriterd.getOrganization() != null) {
                OrganizationM organizationM = new OrganizationM();
                BeanUtils.copyProperties(journalPapersWriterd.getOrganization(), organizationM);
                journalPapersWriterm.setOrganization(organizationM);
            }
            if (journalPapersWriterd.getResearcher() != null) {
                ResearcherM researcherM = new ResearcherM();
                BeanUtils.copyProperties(journalPapersWriterd.getResearcher(), researcherM);
                if (journalPapersWriterd.getResearcher().getTitle() != null) {
                    TitleM titleM = new TitleM();
                    BeanUtils.copyProperties(journalPapersWriterd.getResearcher().getTitle(), titleM);
                    researcherM.setTitle(titleM);
                }
                if (journalPapersWriterd.getResearcher().getAcademicTitle() != null) {
                    TitleM academicTitle = new TitleM();
                    BeanUtils.copyProperties(journalPapersWriterd.getResearcher().getAcademicTitle(), academicTitle);
                    researcherM.setAcademicTitle(academicTitle);
                }

                journalPapersWriterm.setResearcher(researcherM);
            }


            journalPapersWriterm.setPagging(null);
            journalPapersWriterms.add(journalPapersWriterm);
        }
        return journalPapersWriterms;
    }

    private List<JournalPapersDocumentM> listJournalPapersDocument(Integer journalPapersId) {
        List<JournalPapersDocument> documentds = researchService.listJournalPapersDocument(journalPapersId);
        List<JournalPapersDocumentM> documentms = new ArrayList<JournalPapersDocumentM>(
                documentds.size());
        for (JournalPapersDocument documentd : documentds) {
            JournalPapersDocumentM documentm = new JournalPapersDocumentM();
            BeanUtils.copyProperties(documentd, documentm);

            if (documentd.getId() != null) {
                documentm.setJournalPapersId(documentd.getId().getJournalPapersId());
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
        docPk.setRefType(ServiceConstant.DOC_TYPE_JOURNAL);
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

    private List<JournalPaperM> getJournalPaperModels(
            java.util.ArrayList<JournalPaper> domains) {
        List<JournalPaperM> models = new ArrayList<JournalPaperM>(
                domains.size());
        for (JournalPaper domain : domains) {
            JournalPaperM model = new JournalPaperM();
            BeanUtils.copyProperties(domain, model);
            model.setPagging(null);
            models.add(model);
        }
        return models;
    }

    private Representation returnUpdateRecord(Representation entity, JournalPaperM model, int updateRecord) {
        ImakeResultMessage imakeMessage = new ImakeResultMessage();
        List<JournalPaperM> xsources = new ArrayList<JournalPaperM>(1);
        model.setUpdateRecord(updateRecord);
        xsources.add(model);
        imakeMessage.setResultListObj(xsources);
        return getRepresentation(entity, imakeMessage, xstream);
    }

    @Override
    protected Representation get(Variant variant) throws ResourceException {
        // TODO Auto-generated method stub
        JournalPaperM xsource = new JournalPaperM();
        JournalPaper domain = researchService.findJournalPapersById(2, null);

        ImakeResultMessage imakeMessage = new ImakeResultMessage();
        List<JournalPaperM> models = new ArrayList<JournalPaperM>(1);
        JournalPaperM model = new JournalPaperM();
        BeanUtils.copyProperties(domain, model);
        //if(domain.getResearchProjectId())
        if (domain.getJournalPapersConference() != null) {
            JournalPapersConferenceM journalPapersConferenceM = new JournalPapersConferenceM();
            BeanUtils.copyProperties(domain.getJournalPapersConference(), journalPapersConferenceM);
            if (domain.getJournalPapersConference().getStartDate() != null) {
                journalPapersConferenceM.setStartDateShow(format1.format(domain.getJournalPapersConference().getStartDate()));
            }
            if (domain.getJournalPapersConference().getEndDate() != null) {
                journalPapersConferenceM.setEndDateShow(format1.format(domain.getJournalPapersConference().getEndDate()));
            }
            model.setJournalPapersConference(journalPapersConferenceM);
        }
        if (domain.getJournalPapersJournal() != null) {
            JournalPapersJournalM journalPapersJournal = new JournalPapersJournalM();
            BeanUtils.copyProperties(domain.getJournalPapersJournal(), journalPapersJournal);
            model.setJournalPapersJournal(journalPapersJournal);
        }
        model.setPagging(null);
        List<JournalPapersDocumentM> journalPapersDocumentms = listJournalPapersDocument(xsource.getJournalPapersId());

        model.setJournalPapersDocuments(journalPapersDocumentms);
        List<JournalPapersWriterM> journalPapersWriterms = listJournalPapersWriter(xsource.getJournalPapersId());
        model.setJournalPapersWriters(journalPapersWriterms);

        List<DocAssignMappingM> docAssignMappingms = listDocAssignMapping(xsource.getJournalPapersId());
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