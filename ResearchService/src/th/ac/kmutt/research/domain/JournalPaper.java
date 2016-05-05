package th.ac.kmutt.research.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;


/**
 * The persistent class for the JOURNAL_PAPERS database table.
 * 
 */
@Entity
@Table(name="JOURNAL_PAPERS")
@NamedQuery(name="JournalPaper.findAll", query="SELECT j FROM JournalPaper j")
public class JournalPaper extends DomainCommon  implements Serializable {
	private static final long serialVersionUID = 1L;


	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="JOURNAL_PAPERS_ID")
	private Integer journalPapersId;
	
	@Column(name="BIBLIO_GRAPHIC_DB")
	private String biblioGraphicDb;


	@Column(name="CREATED_BY")
	private String createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CREATED_DATE")
	private Date createdDate;

	@Column(name="DOC_TYPE")
	private String docType;

	@Column(name="ENG_NAME")
	private String engName;

	@Column(name="JOURNAL_PAPERS_TYPE")
	private Integer journalPapersType;

	@Column(name="ORGANIZATION_ID")
	private Integer organizationId;

	private String permissions;

	@Column(name="PUBLISH_LANGUAGE")
	private String publishLanguage;

	@Column(name="PUBLISH_TYPE")
	private String publishType;

	private String remark;

	@Column(name="RESEARCH_GROUP_ID")
	private Integer researchGroupId;

	@Column(name="RESEARCH_PROJECT_ID")
	private Integer researchProjectId;

	@Column(name="THAI_NAME")
	private String thaiName; 

	@Column(name="UPDATED_BY")
	private String updatedBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="UPDATED_DATE")
	private Date updatedDate;
	
	@Column(name="BUDGET_YEAR")
	private String budgetYear; 
	
	@Column(name="TYPE")
	private Integer type; //1= journal , 2=conference
	
	
	@Column(name="FLAG")
	private String flag;
	
	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getBudgetYear() {
		return budgetYear;
	}

	public void setBudgetYear(String budgetYear) {
		this.budgetYear = budgetYear;
	}

	//bi-directional one-to-one association to JournalPapersConference
	@NotFound(action=NotFoundAction.IGNORE)  
	@OneToOne(mappedBy="journalPaper")
	private JournalPapersConference journalPapersConference;

	/*//bi-directional many-to-one association to JournalPapersDocument
	@OneToMany(mappedBy="journalPaper")
	private List<JournalPapersDocument> journalPapersDocuments;*/

	//bi-directional one-to-one association to JournalPapersJournal
	@NotFound(action=NotFoundAction.IGNORE)  
	@OneToOne(mappedBy="journalPaper")
	private JournalPapersJournal journalPapersJournal;

	//bi-directional one-to-one association to JournalPapersQualityAssurance
	@NotFound(action=NotFoundAction.IGNORE)  
	@OneToOne(mappedBy="journalPaper")
	private JournalPapersQualityAssurance journalPapersQualityAssurance;
/*
	//bi-directional one-to-one association to JournalPapersWriter
	@NotFound(action=NotFoundAction.IGNORE)  
	@OneToOne(mappedBy="journalPaper")
	private JournalPapersWriter journalPapersWriter;*/

	public JournalPaper() {
	}

	public String getBiblioGraphicDb() {
		return biblioGraphicDb;
	}

	public void setBiblioGraphicDb(String biblioGraphicDb) {
		this.biblioGraphicDb = biblioGraphicDb;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
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

	public JournalPapersConference getJournalPapersConference() {
		return this.journalPapersConference;
	}

	public void setJournalPapersConference(JournalPapersConference journalPapersConference) {
		this.journalPapersConference = journalPapersConference;
	}

	
	public JournalPapersJournal getJournalPapersJournal() {
		return this.journalPapersJournal;
	}

	public void setJournalPapersJournal(JournalPapersJournal journalPapersJournal) {
		this.journalPapersJournal = journalPapersJournal;
	}

	public JournalPapersQualityAssurance getJournalPapersQualityAssurance() {
		return this.journalPapersQualityAssurance;
	}

	public void setJournalPapersQualityAssurance(JournalPapersQualityAssurance journalPapersQualityAssurance) {
		this.journalPapersQualityAssurance = journalPapersQualityAssurance;
	}

	/*public JournalPapersWriter getJournalPapersWriter() {
		return this.journalPapersWriter;
	}

	public void setJournalPapersWriter(JournalPapersWriter journalPapersWriter) {
		this.journalPapersWriter = journalPapersWriter;
	}*/

}