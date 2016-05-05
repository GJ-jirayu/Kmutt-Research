package th.ac.kmutt.research.domain;

import java.io.Serializable;

import javax.persistence.*;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import java.util.Date;


/**
 * The persistent class for the RESEARCH_PROJECT_PROGRESS database table.
 * 
 */
@Entity
@Table(name="RESEARCH_PROJECT_PROGRESS")
@NamedQuery(name="ResearchProjectProgress.findAll", query="SELECT r FROM ResearchProjectProgress r")
public class ResearchProjectProgress implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ResearchProjectProgressPK id;

	@Column(name="CREATED_BY")
	private String createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CREATED_DATE")
	private Date createdDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DUE_DATE")
	private Date dueDate;

	@Column(name="PROGRESS_PERCENTAGE")
	private String progressPercentage;

	private String remark;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="SUBMIT_DATE")
	private Date submitDate;

	@Column(name="UPDATED_BY")
	private String updatedBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="UPDATED_DATE")
	private Date updatedDate;

	//bi-directional many-to-one association to ResearchProject
	@NotFound(action=NotFoundAction.IGNORE)  
	@ManyToOne
	@JoinColumn(name="RESEARCH_PROJECT_ID",insertable=false,updatable=false)
	private ResearchProject researchProject;

	public ResearchProjectProgress() {
	}

	public ResearchProjectProgressPK getId() {
		return this.id;
	}

	public void setId(ResearchProjectProgressPK id) {
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

	public Date getDueDate() {
		return this.dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public String getProgressPercentage() {
		return this.progressPercentage;
	}

	public void setProgressPercentage(String progressPercentage) {
		this.progressPercentage = progressPercentage;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getSubmitDate() {
		return this.submitDate;
	}

	public void setSubmitDate(Date submitDate) {
		this.submitDate = submitDate;
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

	public ResearchProject getResearchProject() {
		return this.researchProject;
	}

	public void setResearchProject(ResearchProject researchProject) {
		this.researchProject = researchProject;
	}

}