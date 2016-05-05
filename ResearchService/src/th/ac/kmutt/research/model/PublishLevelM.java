package th.ac.kmutt.research.model;

import java.io.Serializable;
import java.sql.Timestamp;

import th.ac.kmutt.research.xstream.common.ImakeXML;

import com.thoughtworks.xstream.annotations.XStreamAlias;
@XStreamAlias("PublishLevelM")
public class PublishLevelM extends ImakeXML implements Serializable {
	private static final long serialVersionUID = 1L;

	
	private Integer publishLevelId;

	
	private String createdBy;

	
	private Timestamp createdDate;

	
	private String plCode;

	
	private String plName;

	
	private String updatedBy;

	
	private Timestamp updatedDate;

	public PublishLevelM() {
	}

	public Integer getPublishLevelId() {
		return this.publishLevelId;
	}

	public void setPublishLevelId(Integer publishLevelId) {
		this.publishLevelId = publishLevelId;
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

	public String getPlCode() {
		return this.plCode;
	}

	public void setPlCode(String plCode) {
		this.plCode = plCode;
	}

	public String getPlName() {
		return this.plName;
	}

	public void setPlName(String plName) {
		this.plName = plName;
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