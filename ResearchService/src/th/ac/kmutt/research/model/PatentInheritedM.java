package th.ac.kmutt.research.model;

import java.io.Serializable;
import java.util.Date;

import th.ac.kmutt.research.xstream.common.ImakeXML;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("PatentInheritedM")
public class PatentInheritedM  extends ImakeXML implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer inventionId;

	private String createdBy;

	
	private Date createdDate;

	
	private Integer inheritedItemList;

	
	//private Integer organizationId;
	private OrganizationM organization;
	

	
	private String updatedBy;

	
	private Date updatedDate;
	private PatentM patent;

	public PatentInheritedM() {
	}

	public Integer getInventionId() {
		return this.inventionId;
	}

	public void setInventionId(Integer inventionId) {
		this.inventionId = inventionId;
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

	public Integer getInheritedItemList() {
		return this.inheritedItemList;
	}

	public void setInheritedItemList(Integer inheritedItemList) {
		this.inheritedItemList = inheritedItemList;
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

	public PatentM getPatent() {
		return patent;
	}

	public void setPatent(PatentM patent) {
		this.patent = patent;
	}

	
}