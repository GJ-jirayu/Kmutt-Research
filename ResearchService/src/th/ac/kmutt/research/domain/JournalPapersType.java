package th.ac.kmutt.research.domain;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the JOURNAL_PAPERS_TYPE database table.
 * 
 */
@Entity
@Table(name="JOURNAL_PAPERS_TYPE")
@NamedQuery(name="JournalPapersType.findAll", query="SELECT j FROM JournalPapersType j")
public class JournalPapersType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="JOURNAL_PAPERS_TYPE_ID")
	private Integer journalPapersTypeId;

	@Column(name="CREATED_BY")
	private String createdBy;

	@Column(name="CREATED_DATE")
	private Timestamp createdDate;

	@Column(name="JPT_CODE")
	private String jptCode;

	@Column(name="JPT_NAME")
	private String jptName;

	@Column(name="UPDATED_BY")
	private String updatedBy;

	@Column(name="UPDATED_DATE")
	private Timestamp updatedDate;

	public JournalPapersType() {
	}

	public Integer getJournalPapersTypeId() {
		return this.journalPapersTypeId;
	}

	public void setJournalPapersTypeId(Integer journalPapersTypeId) {
		this.journalPapersTypeId = journalPapersTypeId;
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

	public String getJptCode() {
		return this.jptCode;
	}

	public void setJptCode(String jptCode) {
		this.jptCode = jptCode;
	}

	public String getJptName() {
		return this.jptName;
	}

	public void setJptName(String jptName) {
		this.jptName = jptName;
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

}