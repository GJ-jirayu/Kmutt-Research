package th.ac.kmutt.research.domain;

import java.io.Serializable;

import javax.persistence.*;

import java.sql.Timestamp;


/**
 * The persistent class for the ORGANIZATION database table.
 * 
 */
@Entity
@Table(name="ORGANIZATION")
@NamedQuery(name="Organization.findAll", query="SELECT o FROM Organization o")
public class Organization implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ORGANIZATION_ID")
	private Integer organizationId;

	@Column(name="CREATED_BY")
	private String createdBy;

	@Column(name="CREATED_DATE")
	private Timestamp createdDate;

	@Column(name="ORG_CAMPUS_CODE")
	private String orgCampusCode;

	@Column(name="ORG_DEPT_CODE")
	private String orgDeptCode;

	@Column(name="ORG_INSTITUTION_CODE")
	private String orgInstitutionCode;

	@Column(name="ORG_NAME")
	private String orgName;

	@Column(name="ORG_WORK_CODE")
	private String orgWorkCode;

	@Column(name="UPDATED_BY")
	private String updatedBy;

	@Column(name="UPDATED_DATE")
	private Timestamp updatedDate;

	public Organization() {
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