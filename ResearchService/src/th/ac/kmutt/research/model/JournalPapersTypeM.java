package th.ac.kmutt.research.model;

import java.io.Serializable;
import java.sql.Timestamp;

import th.ac.kmutt.research.xstream.common.ImakeXML;

import com.thoughtworks.xstream.annotations.XStreamAlias;
@XStreamAlias("JournalPapersTypeM")
public class JournalPapersTypeM extends ImakeXML implements Serializable{
	private static final long serialVersionUID = 1L;

	
	private Integer journalPapersTypeId;

	
	private String createdBy;

	
	private Timestamp createdDate;

	
	private String jptCode;

	
	private String jptName;

	
	private String updatedBy;

	
	private Timestamp updatedDate;

	public JournalPapersTypeM() {
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