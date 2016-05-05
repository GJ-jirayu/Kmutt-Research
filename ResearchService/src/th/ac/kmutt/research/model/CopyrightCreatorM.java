package th.ac.kmutt.research.model;

import java.io.Serializable;
import java.util.Date;

import th.ac.kmutt.research.xstream.common.ImakeXML;

import com.thoughtworks.xstream.annotations.XStreamAlias;


/**
 * The persistent class for the COPYRIGHT_CREATOR database table.
 * 
 */
@XStreamAlias("CopyrightCreatorM")
public class CopyrightCreatorM  extends ImakeXML implements Serializable {
	private static final long serialVersionUID = 1L;


	private String workLoadRatio;

	private Integer copyrightId;

	public String getWorkLoadRatio() {
		return workLoadRatio;
	}

	public void setWorkLoadRatio(String workLoadRatio) {
		this.workLoadRatio = workLoadRatio;
	}

	private String createdBy;

	private Date createdDate;

	private String creatorId;

	private Integer creatorItemList;
	private String creatorType;

	
	//private Integer organizationId;
	private OrganizationM organization;

	
	private String updatedBy;

	
	
	public CopyrightM getCopyright() {
		return copyright;
	}

	public void setCopyright(CopyrightM copyright) {
		this.copyright = copyright;
	}

	private Date updatedDate;

	//bi-directional one-to-one association to Copyright
	
	
	private CopyrightM copyright;

	public CopyrightCreatorM() {
	}

	public Integer getCopyrightId() {
		return this.copyrightId;
	}

	public void setCopyrightId(Integer copyrightId) {
		this.copyrightId = copyrightId;
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

	public String getCreatorType() {
		return creatorType;
	}

	public void setCreatorType(String creatorType) {
		this.creatorType = creatorType;
	}

	public void setCreatorId(String creatorId) {
		this.creatorId = creatorId;
	}



	public Integer getCreatorItemList() {
		return creatorItemList;
	}

	public void setCreatorItemList(Integer creatorItemList) {
		this.creatorItemList = creatorItemList;
	}


	public OrganizationM getOrganization() {
		return organization;
	}

	public void setOrganization(OrganizationM organization) {
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
}