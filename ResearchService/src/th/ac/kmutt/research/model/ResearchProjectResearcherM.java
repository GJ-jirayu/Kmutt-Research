package th.ac.kmutt.research.model;

import java.io.Serializable;
import java.util.Date;

import th.ac.kmutt.research.xstream.common.ImakeXML;

import com.thoughtworks.xstream.annotations.XStreamAlias;
@XStreamAlias("ResearchProjectResearcherM")
public class ResearchProjectResearcherM extends ImakeXML implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer itemList;	
	private Integer researchProjectId;

	
	private Integer researcherItemId;


	
	private String createdBy;

	
	private Date createdDate;

	
	private String isprojectLeader;

	
	//private Integer organizationId;
	private OrganizationM organization;
	
	//private Integer positionId;
	private PositionM position;
	
	private String researcherDept;



	private Integer researcherId;

	
	private String researcherName;

	
	private String updatedBy;

	
	private Date updatedDate;

	
	private Integer workLoadRatio; 

	private ResearchProjectM researchProject;
	
	public Integer getItemList() {
		return itemList;
	}

	public void setItemList(Integer itemList) {
		this.itemList = itemList;
	}
	public ResearchProjectResearcherM() {
	}

	public Integer getResearchProjectId() {
		return researchProjectId;
	}

	public void setResearchProjectId(Integer researchProjectId) {
		this.researchProjectId = researchProjectId;
	}

	public Integer getResearcherItemId() {
		return researcherItemId;
	}

	public void setResearcherItemId(Integer researcherItemId) {
		this.researcherItemId = researcherItemId;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getIsprojectLeader() {
		return isprojectLeader;
	}

	public void setIsprojectLeader(String isprojectLeader) {
		this.isprojectLeader = isprojectLeader;
	}

	/*public Integer getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Integer organizationId) {
		this.organizationId = organizationId;
	}*/

	/*public Integer getPositionId() {
		return positionId;
	}

	public void setPositionId(Integer positionId) {
		this.positionId = positionId;
	}*/

	public PositionM getPosition() {
		return position;
	}

	public OrganizationM getOrganization() {
		return organization;
	}

	public void setOrganization(OrganizationM organization) {
		this.organization = organization;
	}

	public void setPosition(PositionM position) {
		this.position = position;
	}

	public String getResearcherDept() {
		return researcherDept;
	}

	public void setResearcherDept(String researcherDept) {
		this.researcherDept = researcherDept;
	}

	public Integer getResearcherId() {
		return researcherId;
	}

	public void setResearcherId(Integer researcherId) {
		this.researcherId = researcherId;
	}

	public String getResearcherName() {
		return researcherName;
	}

	public void setResearcherName(String researcherName) {
		this.researcherName = researcherName;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public Integer getWorkLoadRatio() {
		return workLoadRatio;
	}

	public void setWorkLoadRatio(Integer workLoadRatio) {
		this.workLoadRatio = workLoadRatio;
	}

	public ResearchProjectM getResearchProject() {
		return researchProject;
	}

	public void setResearchProject(ResearchProjectM researchProject) {
		this.researchProject = researchProject;
	}

	
}