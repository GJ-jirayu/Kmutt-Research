package th.ac.kmutt.research.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the JOURNAL_PAPERS_OTHER database table.
 * 
 */
@Entity
@Table(name="JOURNAL_PAPERS_OTHER")
@NamedQuery(name="JournalPapersOther.findAll", query="SELECT j FROM JournalPapersOther j")
public class JournalPapersOther implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="JOURNAL_PAPERS_ID")
	private Integer journalPapersId;

	@Column(name="ACCEPTED_DATE")
	private Timestamp acceptedDate;

	@Column(name="CREATED_BY")
	private String createdBy;

	@Column(name="CREATED_DATE")
	private Timestamp createdDate;

	private String issue;

	@Column(name="JOURNAL_NAME")
	private String journalName;

	@Column(name="JOURNAL_TYPE")
	private String journalType;

	private String level;

	private String month;

	private String page;

	@Column(name="UPDATED_BY")
	private String updatedBy;

	@Column(name="UPDATED_DATE")
	private Timestamp updatedDate;

	private String vol;

	private String year;

	public JournalPapersOther() {
	}

	public Integer getJournalPapersId() {
		return this.journalPapersId;
	}

	public void setJournalPapersId(Integer journalPapersId) {
		this.journalPapersId = journalPapersId;
	}

	public Timestamp getAcceptedDate() {
		return this.acceptedDate;
	}

	public void setAcceptedDate(Timestamp acceptedDate) {
		this.acceptedDate = acceptedDate;
	}

	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Timestamp getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
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

	public String getJournalType() {
		return this.journalType;
	}

	public void setJournalType(String journalType) {
		this.journalType = journalType;
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

	public Timestamp getUpdatedDate() {
		return this.updatedDate;
	}

	public void setUpdatedDate(Timestamp updatedDate) {
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

}