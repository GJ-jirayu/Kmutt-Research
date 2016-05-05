package th.ac.kmutt.research.model;

import java.io.Serializable;
import java.util.Date;

import th.ac.kmutt.research.xstream.common.ImakeXML;

import com.thoughtworks.xstream.annotations.XStreamAlias;
@XStreamAlias("JournalPapersJournalM")
public class JournalPapersJournalM extends ImakeXML implements Serializable {
	private static final long serialVersionUID = 1L;

	
	private Integer journalPapersId;

	
	private Date acceptedDate;
	private String acceptedDateShow;
	
	private String createdBy;

	
	private Date createdDate;

	private String issue;
	public String ispublished;
	
	public String getLevelShow() {
		return levelShow;
	}

	public void setLevelShow(String levelShow) {
		this.levelShow = levelShow;
	}

	private String journalName;

	private String level;
	private String levelShow;

	private String month;

	private String page;

	
	private String updatedBy;

	
	private Date updatedDate;

	private String vol;

	private String year;


	private JournalPaperM journalPaper;

	public JournalPapersJournalM() {
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

	public JournalPaperM getJournalPaper() {
		return journalPaper;
	}

	public void setJournalPaper(JournalPaperM journalPaper) {
		this.journalPaper = journalPaper;
	}

	public String getIspublished() {
		return ispublished;
	}

	public void setIspublished(String ispublished) {
		this.ispublished = ispublished;
	}

	public String getAcceptedDateShow() {
		return acceptedDateShow;
	}

	public void setAcceptedDateShow(String acceptedDateShow) {
		this.acceptedDateShow = acceptedDateShow;
	}
}