package th.ac.kmutt.research.domain;

import java.io.Serializable;

import javax.persistence.*;

import java.sql.Timestamp;


/**
 * The persistent class for the ORGANIZATION_EXT database table.
 * 
 */
@Entity
@Table(name="ORGANIZATION_EXT")
@NamedQuery(name="OrganizationExt.findAll", query="SELECT o FROM OrganizationExt o")
public class OrganizationExt implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ORGANIZATION_EXT_ID")
	private Integer organizationExtId;

	@Column(name="CREATED_BY")
	private String createdBy;

	@Column(name="CREATED_DATE")
	private Timestamp createdDate;

	@Column(name="ORGANIZATION_EXT_CODE")
	private String organizationExtCode;

	@Column(name="ORGANIZATION_EXT_NAME")
	private String organizationExtName;

	@Column(name="UPDATED_BY")
	private String updatedBy;

	@Column(name="UPDATED_DATE")
	private Timestamp updatedDate;

	public OrganizationExt() {
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