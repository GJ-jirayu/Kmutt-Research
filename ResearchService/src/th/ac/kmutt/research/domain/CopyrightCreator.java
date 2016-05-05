package th.ac.kmutt.research.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;


/**
 * The persistent class for the COPYRIGHT_CREATOR database table.
 * 
 */
@Entity
@Table(name="COPYRIGHT_CREATOR")
@NamedQuery(name="CopyrightCreator.findAll", query="SELECT c FROM CopyrightCreator c")
public class CopyrightCreator implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private CopyrightCreatorPK id;

	@Column(name="CREATED_BY")
	private String createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CREATED_DATE")
	private Date createdDate;

	@Column(name="CREATOR_ID")
	private String creatorId;
/*
	@Column(name="ORGANIZATION_ID")
	private int organizationId;*/
	@NotFound(action=NotFoundAction.IGNORE)  
	@ManyToOne
	@JoinColumn(name="ORGANIZATION_ID")
	private Organization organization;

	@Column(name="UPDATED_BY")
	private String updatedBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="UPDATED_DATE")
	private Date updatedDate;

	@Column(name="WORK_LOAD_RATIO")
	private String workLoadRatio;
  
	 @Column(name="CREATOR_TYPE")
	 private String creatorType;
	
	//bi-directional many-to-one association to FundingResourceType
	@NotFound(action=NotFoundAction.IGNORE)  
	@ManyToOne
	@JoinColumn(name="COPYRIGHT_ID",insertable=false,updatable=false)
	private Copyright copyright;
		

	public CopyrightCreator() {
	}

	public CopyrightCreatorPK getId() {
		return this.id;
	}

	public void setId(CopyrightCreatorPK id) {
		this.id = id;
	}

	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getCreatorId() {
		return this.creatorId;
	}

	public void setCreatorId(String creatorId) {
		this.creatorId = creatorId;
	}

	

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public String getUpdatedBy() {
		return this.updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getUpdatedDate() {
		return this.updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public String getWorkLoadRatio() {
		return this.workLoadRatio;
	}

	public void setWorkLoadRatio(String workLoadRatio) {
		this.workLoadRatio = workLoadRatio;
	}

	public Copyright getCopyright() {
		return this.copyright;
	}

	public String getCreatorType() {
		return creatorType;
	}

	public void setCreatorType(String creatorType) {
		this.creatorType = creatorType;
	}

	public void setCopyright(Copyright copyright) {
		this.copyright = copyright;
	}

}