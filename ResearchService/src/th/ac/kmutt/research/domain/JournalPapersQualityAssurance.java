package th.ac.kmutt.research.domain;

import java.io.Serializable;

import javax.persistence.*;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import java.util.Date;


/**
 * The persistent class for the JOURNAL_PAPERS_QUALITY_ASSURANCE database table.
 * 
 */
@Entity
@Table(name="JOURNAL_PAPERS_QUALITY_ASSURANCE")
@NamedQuery(name="JournalPapersQualityAssurance.findAll", query="SELECT j FROM JournalPapersQualityAssurance j")
public class JournalPapersQualityAssurance implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="JOURNAL_PAPERS_ID")
	private Integer journalPapersId;

	private String beallist;

	@Column(name="CREATED_BY")
	private String createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CREATED_DATE")
	private Date createdDate;

	private String hindex;

	@Column(name="IMPACT_FACTOR")
	private String impactFactor;

	private String quartile;

	@Column(name="UPDATED_BY")
	private String updatedBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="UPDATED_DATE")
	private Date updatedDate;

	//bi-directional one-to-one association to JournalPaper
	@NotFound(action=NotFoundAction.IGNORE)  
	@OneToOne
	@JoinColumn(name="JOURNAL_PAPERS_ID")
	private JournalPaper journalPaper;

	public JournalPapersQualityAssurance() {
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

	public JournalPaper getJournalPaper() {
		return this.journalPaper;
	}

	public void setJournalPaper(JournalPaper journalPaper) {
		this.journalPaper = journalPaper;
	}

}