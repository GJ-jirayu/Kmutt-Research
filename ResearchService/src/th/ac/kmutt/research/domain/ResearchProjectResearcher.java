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
 * The persistent class for the RESEARCH_PROJECT_RESEARCHER database table.
 * 
 */
@Entity
@Table(name="RESEARCH_PROJECT_RESEARCHER")
@NamedQuery(name="ResearchProjectResearcher.findAll", query="SELECT r FROM ResearchProjectResearcher r")
public class ResearchProjectResearcher implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ResearchProjectResearcherPK id;

	@Column(name="CREATED_BY")
	private String createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CREATED_DATE")
	private Date createdDate;

	@Column(name="ISPROJECT_LEADER")
	private String isprojectLeader;

	/*@Column(name="ORGANIZATION_ID")
	private Integer organizationId;*/
	
	@NotFound(action=NotFoundAction.IGNORE)  
	@ManyToOne
	@JoinColumn(name="ORGANIZATION_ID")
	private Organization organization;
	/*@Column(name="POSITION_ID")
	private Integer positionId;*/
	
	@NotFound(action=NotFoundAction.IGNORE)  
	@ManyToOne
	@JoinColumn(name="POSITION_ID")
	private Position position;// positionId; x
	
	@Column(name="RESEARCHER_DEPT")
	private String researcherDept;

	@Column(name="RESEARCHER_ID")
	private Integer researcherId;

	@Column(name="RESEARCHER_NAME")
	private String researcherName;

	@Column(name="UPDATED_BY")
	private String updatedBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="UPDATED_DATE")
	private Date updatedDate;

	@Column(name="WORK_LOAD_RATIO")
	private Integer workLoadRatio;

	//bi-directional many-to-one association to ResearchProject
	@NotFound(action=NotFoundAction.IGNORE)  
	@ManyToOne
	@JoinColumn(name="RESEARCH_PROJECT_ID",insertable=false,updatable=false)
	private ResearchProject researchProject;

	public ResearchProjectResearcher() {
	}

	public ResearchProjectResearcherPK getId() {
		return this.id;
	}

	public void setId(ResearchProjectResearcherPK id) {
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

	public String getIsprojectLeader() {
		return this.isprojectLeader;
	}

	public void setIsprojectLeader(String isprojectLeader) {
		this.isprojectLeader = isprojectLeader;
	}

	/*public Integer getOrganizationId() {
		return this.organizationId;
	}

	public void setOrganizationId(Integer organizationId) {
		this.organizationId = organizationId;
	}*/

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	/*public Integer getPositionId() {
		return this.positionId;
	}

	public void setPositionId(Integer positionId) {
		this.positionId = positionId;
	}
*/
	public String getResearcherDept() {
		return this.researcherDept;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public void setResearcherDept(String researcherDept) {
		this.researcherDept = researcherDept;
	}

	public Integer getResearcherId() {
		return this.researcherId;
	}

	public void setResearcherId(Integer researcherId) {
		this.researcherId = researcherId;
	}

	public String getResearcherName() {
		return this.researcherName;
	}

	public void setResearcherName(String researcherName) {
		this.researcherName = researcherName;
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

	public Integer getWorkLoadRatio() {
		return this.workLoadRatio;
	}

	public void setWorkLoadRatio(Integer workLoadRatio) {
		this.workLoadRatio = workLoadRatio;
	}

	public ResearchProject getResearchProject() {
		return this.researchProject;
	}

	public void setResearchProject(ResearchProject researchProject) {
		this.researchProject = researchProject;
	}

}