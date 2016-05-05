package th.ac.kmutt.research.model;

import java.io.Serializable;
import java.sql.Timestamp;

import th.ac.kmutt.research.xstream.common.ImakeXML;

import com.thoughtworks.xstream.annotations.XStreamAlias;
@XStreamAlias("PublishTypeM")
public class PublishTypeM extends ImakeXML implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer publishTypeId;

	
	private String createdBy;

	
	private Timestamp createdDate;

	
	private String ptCode;

	
	private String ptName;

	
	private String updatedBy;

	
	private Timestamp updatedDate;

	public PublishTypeM() {
	}

	public Integer getPublishTypeId() {
		return this.publishTypeId;
	}

	public void setPublishTypeId(Integer publishTypeId) {
		this.publishTypeId = publishTypeId;
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

	public String getPtCode() {
		return this.ptCode;
	}

	public void setPtCode(String ptCode) {
		this.ptCode = ptCode;
	}

	public String getPtName() {
		return this.ptName;
	}

	public void setPtName(String ptName) {
		this.ptName = ptName;
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