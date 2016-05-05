package th.ac.kmutt.research.model;

import java.io.Serializable;
import java.sql.Timestamp;

import th.ac.kmutt.research.xstream.common.ImakeXML;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("OrganizationExtM")
public class OrganizationExtM extends ImakeXML implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer organizationExtId;

	
	private String createdBy;

	
	private Timestamp createdDate;

	
	private String organizationExtCode;

	
	private String organizationExtName;

	
	private String updatedBy;

	
	private Timestamp updatedDate;

	public OrganizationExtM() {
	}

	public Integer getOrganizationExtId() {
		return this.organizationExtId;
	}

	public void setOrganizationExtId(Integer organizationExtId) {
		this.organizationExtId = organizationExtId;
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

	public String getOrganizationExtCode() {
		return this.organizationExtCode;
	}

	public void setOrganizationExtCode(String organizationExtCode) {
		this.organizationExtCode = organizationExtCode;
	}

	public String getOrganizationExtName() {
		return this.organizationExtName;
	}

	public void setOrganizationExtName(String organizationExtName) {
		this.organizationExtName = organizationExtName;
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