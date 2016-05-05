package th.ac.kmutt.research.model;

import java.io.Serializable;
import java.sql.Timestamp;

import th.ac.kmutt.research.xstream.common.ImakeXML;

import com.thoughtworks.xstream.annotations.XStreamAlias;
@XStreamAlias("OrganizationM")
public class OrganizationM extends ImakeXML implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer organizationId;

	
	private String createdBy;

	
	private Timestamp createdDate;

	
	private String orgCampusCode;

	
	private String orgDeptCode;

	
	private String orgInstitutionCode;

	
	private String orgName;

	
	private String orgWorkCode;

	
	private String updatedBy;

	
	private Timestamp updatedDate;

	public OrganizationM() {
	}

	public Integer getOrganizationId() {
		return this.organizationId;
	}

	public void setOrganizationId(Integer organizationId) {
		this.organizationId = organizationId;
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

	public String getOrgCampusCode() {
		return this.orgCampusCode;
	}

	public void setOrgCampusCode(String orgCampusCode) {
		this.orgCampusCode = orgCampusCode;
	}

	public String getOrgDeptCode() {
		return this.orgDeptCode;
	}

	public void setOrgDeptCode(String orgDeptCode) {
		this.orgDeptCode = orgDeptCode;
	}

	public String getOrgInstitutionCode() {
		return this.orgInstitutionCode;
	}

	public void setOrgInstitutionCode(String orgInstitutionCode) {
		this.orgInstitutionCode = orgInstitutionCode;
	}

	public String getOrgName() {
		return this.orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getOrgWorkCode() {
		return this.orgWorkCode;
	}

	public void setOrgWorkCode(String orgWorkCode) {
		this.orgWorkCode = orgWorkCode;
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