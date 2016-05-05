package th.ac.kmutt.research.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import th.ac.kmutt.research.xstream.common.ImakeXML;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("JournalPaperM")
public class JournalPaperM extends ImakeXML implements Serializable {
	private static final long serialVersionUID = 1L;

	
	private Integer journalPapersId;

	private String biblioGraphicDb;	
	public String getBiblioGraphicDb() {
		return biblioGraphicDb;
	}

	public void setBiblioGraphicDb(String biblioGraphicDb) {
		this.biblioGraphicDb = biblioGraphicDb;
	}

	private String createdBy;

	
	private Date createdDate;

	
	private String docType;

	
	private String engName;

	
	private Integer journalPapersType;
	private String journalPapersTypeShow;
	private String journalPapersLevelShow;
	private String researchProjectShow;
	private String organizationShow;
	private String researchGroupShow;
	private String proofParticipationResource; //  Proof_Participation
	private String proofAcademicResource; //Proof_Academic	
	private String flag;
	
	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getProofParticipationResource() {
		return proofParticipationResource;
	}

	public String getResearchProjectShow() {
		return researchProjectShow;
	}

	public void setResearchProjectShow(String researchProjectShow) {
		this.researchProjectShow = researchProjectShow;
	}

	public void setProofParticipationResource(String proofParticipationResource) {
		this.proofParticipationResource = proofParticipationResource;
	}

	public String getProofAcademicResource() {
		return proofAcademicResource;
	}

	public void setProofAcademicResource(String proofAcademicResource) {
		this.proofAcademicResource = proofAcademicResource;
	}

	public String getJournalPapersTypeShow() {
		return journalPapersTypeShow;
	}

	public void setJournalPapersTypeShow(String journalPapersTypeShow) {
		this.journalPapersTypeShow = journalPapersTypeShow;
	}

	public String getJournalPapersLevelShow() {
		return journalPapersLevelShow;
	}

	public void setJournalPapersLevelShow(String journalPapersLevelShow) {
		this.journalPapersLevelShow = journalPapersLevelShow;
	}

	private Integer organizationId;
	
	public String getOrganizationShow() {
		return organizationShow;
	}

	public void setOrganizationShow(String organizationShow) {
		this.organizationShow = organizationShow;
	}

	private String permissions;

	
	private String publishLanguage;

	
	private String publishType;

	private String remark;

	
	private Integer researchGroupId;
	
	private Integer type; //1= journal , 2=conference	
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getResearchGroupShow() {
		return researchGroupShow;
	}

	public void setResearchGroupShow(String researchGroupShow) {
		this.researchGroupShow = researchGroupShow;
	}

	private Integer researchProjectId;

	
	private String thaiName;

	
	private String updatedBy;

	
	private Date updatedDate;

	private JournalPapersConferenceM journalPapersConference;

	private JournalPapersJournalM journalPapersJournal;


	private JournalPapersQualityAssuranceM journalPapersQualityAssurance;
	
	private List<JournalPapersDocumentM> journalPapersDocuments;
	private List<JournalPapersWriterM> journalPapersWriters;
	private List<DocAssignMappingM> docAssignMappings;
	
	private JournalPapersDocumentM journalPapersDocument;
	private JournalPapersWriterM journalPapersWriter;

//	private JournalPapersWriterM journalPapersWriter;
	private String budgetYear;
	public String getBudgetYear() {
		return budgetYear;
	}

	public void setBudgetYear(String budgetYear) {
		this.budgetYear = budgetYear;
	}

	public List<DocAssignMappingM> getDocAssignMappings() {
		return docAssignMappings;
	}

	public void setDocAssignMappings(List<DocAssignMappingM> docAssignMappings) {
		this.docAssignMappings = docAssignMappings;
	}

	public JournalPaperM(){
	 journalPapersConference =new JournalPapersConferenceM();
	 journalPapersJournal =new JournalPapersJournalM();
	}

	public Integer getJournalPapersId() {
		return this.journalPapersId;
	}

	public void setJournalPapersId(Integer journalPapersId) {
		this.journalPapersId = journalPapersId;
	}

	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getDocType() {
		return this.docType;
	}

	public void setDocType(String docType) {
		this.docType = docType;
	}

	public String getEngName() {
		return this.engName;
	}

	public void setEngName(String engName) {
		this.engName = engName;
	}

	public Integer getJournalPapersType() {
		return this.journalPapersType;
	}

	public void setJournalPapersType(Integer journalPapersType) {
		this.journalPapersType = journalPapersType;
	}

	public Integer getOrganizationId() {
		return this.organizationId;
	}

	public void setOrganizationId(Integer organizationId) {
		this.organizationId = organizationId;
	}

	public String getPermissions() {
		return this.permissions;
	}

	public void setPermissions(String permissions) {
		this.permissions = permissions;
	}

	public String getPublishLanguage() {
		return this.publishLanguage;
	}

	public void setPublishLanguage(String publishLanguage) {
		this.publishLanguage = publishLanguage;
	}

	public String getPublishType() {
		return this.publishType;
	}

	public void setPublishType(String publishType) {
		this.publishType = publishType;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getResearchGroupId() {
		return this.researchGroupId;
	}

	public void setResearchGroupId(Integer researchGroupId) {
		this.researchGroupId = researchGroupId;
	}

	public Integer getResearchProjectId() {
		return this.researchProjectId;
	}

	public void setResearchProjectId(Integer researchProjectId) {
		this.researchProjectId = researchProjectId;
	}

	public String getThaiName() {
		return this.thaiName;
	}

	public void setThaiName(String thaiName) {
		this.thaiName = thaiName;
	}

	public String getUpdatedBy() {
		return this.updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getUpdatedDate() {
		return this.updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public JournalPapersConferenceM getJournalPapersConference() {
		return journalPapersConference;
	}

	public void setJournalPapersConference(
			JournalPapersConferenceM journalPapersConference) {
		this.journalPapersConference = journalPapersConference;
	}

	public JournalPapersJournalM getJournalPapersJournal() {
		return journalPapersJournal;
	}

	public void setJournalPapersJournal(JournalPapersJournalM journalPapersJournal) {
		this.journalPapersJournal = journalPapersJournal;
	}

	public JournalPapersQualityAssuranceM getJournalPapersQualityAssurance() {
		return journalPapersQualityAssurance;
	}

	public void setJournalPapersQualityAssurance(
			JournalPapersQualityAssuranceM journalPapersQualityAssurance) {
		this.journalPapersQualityAssurance = journalPapersQualityAssurance;
	}
/*
	public JournalPapersWriterM getJournalPapersWriter() {
		return journalPapersWriter;
	}

	public void setJournalPapersWriter(JournalPapersWriterM journalPapersWriter) {
		this.journalPapersWriter = journalPapersWriter;
	}
*/

	public List<JournalPapersDocumentM> getJournalPapersDocuments() {
		return journalPapersDocuments;
	}

	public void setJournalPapersDocuments(
			List<JournalPapersDocumentM> journalPapersDocuments) {
		this.journalPapersDocuments = journalPapersDocuments;
	}

	public List<JournalPapersWriterM> getJournalPapersWriters() {
		return journalPapersWriters;
	}

	public JournalPapersDocumentM getJournalPapersDocument() {
		return journalPapersDocument;
	}

	public void setJournalPapersDocument(
			JournalPapersDocumentM journalPapersDocument) {
		this.journalPapersDocument = journalPapersDocument;
	}

	public JournalPapersWriterM getJournalPapersWriter() {
		return journalPapersWriter;
	}

	public void setJournalPapersWriter(JournalPapersWriterM journalPapersWriter) {
		this.journalPapersWriter = journalPapersWriter;
	}

	public void setJournalPapersWriters(
			List<JournalPapersWriterM> journalPapersWriters) {
		this.journalPapersWriters = journalPapersWriters;
	}

}