package th.ac.kmutt.research.model;

import java.io.Serializable;
import java.sql.Timestamp;

import th.ac.kmutt.research.xstream.common.ImakeXML;

import com.thoughtworks.xstream.annotations.XStreamAlias;
@XStreamAlias("UtilizationTypeM")
public class UtilizationTypeM extends ImakeXML implements Serializable {
	private static final long serialVersionUID = 1L;

	
	private Integer utilizationTypeId;

	
	private String createdBy;

	
	private Timestamp createdDate;

	
	private String updatedBy;

	
	private Timestamp updatedDate;

	
	private String utilizationCode;

	
	private String utilizationName;

	public UtilizationTypeM() {
	}

	public Integer getUtilizationTypeId() {
		return this.utilizationTypeId;
	}

	public void setUtilizationTypeId(Integer utilizationTypeId) {
		this.utilizationTypeId = utilizationTypeId;
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

	public String getUtilizationCode() {
		return this.utilizationCode;
	}

	public void setUtilizationCode(String utilizationCode) {
		this.utilizationCode = utilizationCode;
	}

	public String getUtilizationName() {
		return this.utilizationName;
	}

	public void setUtilizationName(String utilizationName) {
		this.utilizationName = utilizationName;
	}

}