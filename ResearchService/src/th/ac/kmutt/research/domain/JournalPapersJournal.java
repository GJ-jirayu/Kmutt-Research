package th.ac.kmutt.research.domain;

import java.io.Serializable;

import javax.persistence.*;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import java.util.Date;


/**
 * The persistent class for the JOURNAL_PAPERS_JOURNAL database table.
 * 
 */
@Entity
@Table(name="JOURNAL_PAPERS_JOURNAL")
@NamedQuery(name="JournalPapersJournal.findAll", query="SELECT j FROM JournalPapersJournal j")
public class JournalPapersJournal implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="JOURNAL_PAPERS_ID")
	private Integer journalPapersId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="ACCEPTED_DATE")
	private Date acceptedDate;

	@Column(name="CREATED_BY")
	private String createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CREATED_DATE")
	private Date createdDate;

	private String issue;

	@Column(name="JOURNAL_NAME")
	private String journalName;

	private String level;

	private String month;

	private String page;

	@Column(name="UPDATED_BY")
	private String updatedBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="UPDATED_DATE")
	private Date updatedDate;

	private String vol;

	private String year;

	//bi-directional one-to-one association to JournalPaper
	@NotFound(action=NotFoundAction.IGNORE)  
	@OneToOne
	@JoinColumn(name="JOURNAL_PAPERS_ID")
	private JournalPaper journalPaper;

	@Column(name="IS_PUBLISHED")
	public String ispublished;
	public JournalPapersJournal() {
	}

	public Integer getJournalPapersId() {
		return this.journalPapersId;
	}

	public void setJournalPapersId(Integer journalPapersId) {
		this.journalPapersId = journalPapersId;
	}

	public Date getAcceptedDate() {
		return this.acceptedDate;
	}

	public void setAcceptedDate(Date acceptedDate) {
		this.acceptedDate = acceptedDate;
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

	public String getIssue() {
		return this.issue;
	}

	public void setIssue(String issue) {
		this.issue = issue;
	}

	public String getJournalName() {
		return this.journalName;
	}

	public void setJournalName(String journalName) {
		this.journalName = journalName;
	}

	public String getLevel() {
		return this.level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getMonth() {
		return this.month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getPage() {
		return this.page;
	}

	public void setPage(String page) {
		this.page = page;
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

	public String getVol() {
		return this.vol;
	}

	public void setVol(String vol) {
		this.vol = vol;
	}

	public String getYear() {
		return this.year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public JournalPaper getJournalPaper() {
		return this.journalPaper;
	}

	public void setJournalPaper(JournalPaper journalPaper) {
		this.journalPaper = journalPaper;
	}

	public String getIspublished() {
		return ispublished;
	}

	public void setIspublished(String ispublished) {
		this.ispublished = ispublished;
	}
}