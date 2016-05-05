package th.ac.kmutt.research.model;

import java.io.Serializable;
import java.util.Date;

import th.ac.kmutt.research.xstream.common.ImakeXML;

import com.thoughtworks.xstream.annotations.XStreamAlias;
@XStreamAlias("JournalPapersQualityAssuranceM")
public class JournalPapersQualityAssuranceM extends ImakeXML implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer journalPapersId;

	private String beallist;

	
	private String createdBy;

	
	private Date createdDate;

	private String hindex;

	
	private String impactFactor;

	private String quartile;

	
	private String updatedBy;

	
	private Date updatedDate;

	private JournalPaperM journalPaper;

	public JournalPapersQualityAssuranceM() {
	}

	public Integer getJournalPapersId() {
		return this.journalPapersId;
	}

	public void setJournalPapersId(Integer journalPapersId) {
		this.journalPapersId = journalPapersId;
	}

	public String getBeallist() {
		return this.beallist;
	}

	public void setBeallist(String beallist) {
		this.beallist = beallist;
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

	public String getHindex() {
		return this.hindex;
	}

	public void setHindex(String hindex) {
		this.hindex = hindex;
	}

	public String getImpactFactor() {
		return this.impactFactor;
	}

	public void setImpactFactor(String impactFactor) {
		this.impactFactor = impactFactor;
	}

	public String getQuartile() {
		return this.quartile;
	}

	public void setQuartile(String quartile) {
		this.quartile = quartile;
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

	public JournalPaperM getJournalPaper() {
		return journalPaper;
	}

	public void setJournalPaper(JournalPaperM journalPaper) {
		this.journalPaper = journalPaper;
	}

	

}