package th.ac.kmutt.research.model;

import java.io.Serializable;
import java.sql.Timestamp;

import th.ac.kmutt.research.xstream.common.ImakeXML;

import com.thoughtworks.xstream.annotations.XStreamAlias;


@XStreamAlias("WorkTypeM")
public class WorkTypeM  extends ImakeXML implements Serializable {
	private static final long serialVersionUID = 1L;

	
	private Integer workTypeId;

	
	private String createdBy;

	
	private Timestamp createdDate;

	
	private String updatedBy;

	
	private Timestamp updatedDate;

	
	private String wtCode;

	
	private String wtName;

	public WorkTypeM() {
	}

	public Integer getWorkTypeId() {
		return this.workTypeId;
	}

	public void setWorkTypeId(Integer workTypeId) {
		this.workTypeId = workTypeId;
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

	public String getWtCode() {
		return this.wtCode;
	}

	public void setWtCode(String wtCode) {
		this.wtCode = wtCode;
	}

	public String getWtName() {
		return this.wtName;
	}

	public void setWtName(String wtName) {
		this.wtName = wtName;
	}

}