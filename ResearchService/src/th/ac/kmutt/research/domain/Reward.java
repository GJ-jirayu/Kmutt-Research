package th.ac.kmutt.research.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;


/**
 * The persistent class for the REWARD database table.
 * 
 */
@Entity
@Table(name="REWARD")
@NamedQuery(name="Reward.findAll", query="SELECT r FROM Reward r")
public class Reward extends DomainCommon implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="REWARD_ID")
	private Integer rewardId;

	@Column(name="BUDGET_YEAR")
	private String budgetYear;

	@Column(name="CREATED_BY")
	private String createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CREATED_DATE")
	private Date createdDate;

	@Column(name="DOC_TYPE")
	private String docType;

	private String permissions;

	private String remark;

	/*@Column(name="RESEARCH_PROJECT_ID")
	private Integer researchProjectId;x*/
	@NotFound(action=NotFoundAction.IGNORE)  
	@ManyToOne
	@JoinColumn(name="RESEARCH_PROJECT_ID")
	private ResearchProject researchProject; // researchProjectId
	
	@Column(name="REWARD_FROM")
	private String rewardFrom;

	@Column(name="REWARD_CODE")
	private String rewardCode;

	@Column(name="REWARD_COUNTRY")
	private Integer rewardCountry;
	

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="REWARD_DATE")
	private Date rewardDate;



	@Column(name="REWARD_NAME")
	private String rewardName;
	
	@Column(name="REWARD_LOCATION")
	private String rewardLocation;

	@Column(name="UPDATED_BY")
	private String updatedBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="UPDATED_DATE")
	private Date updatedDate;
	
	@Column(name="FLAG")
	private String flag;
	
	
/*
	//bi-directional one-to-one association to RewardCreator
	@NotFound(action=NotFoundAction.IGNORE)  
	@OneToOne(mappedBy="reward")
	private RewardCreator rewardCreator;*/

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public Reward() {
	}

	public Integer getRewardId() {
		return this.rewardId;
	}

	public void setRewardId(Integer rewardId) {
		this.rewardId = rewardId;
	}

	public String getBudgetYear() {
		return this.budgetYear;
	}

	public void setBudgetYear(String budgetYear) {
		this.budgetYear = budgetYear;
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

	public String getDocType() {
		return this.docType;
	}

	public void setDocType(String docType) {
		this.docType = docType;
	}

	public String getPermissions() {
		return this.permissions;
	}

	public void setPermissions(String permissions) {
		this.permissions = permissions;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}



	public ResearchProject getResearchProject() {
		return researchProject;
	}

	public void setResearchProject(ResearchProject researchProject) {
		this.researchProject = researchProject;
	}

	public String getRewardCode() {
		return this.rewardCode;
	}

	public void setRewardCode(String rewardCode) {
		this.rewardCode = rewardCode;
	}

	public Integer getRewardCountry() {
		return this.rewardCountry;
	}

	public void setRewardCountry(Integer rewardCountry) {
		this.rewardCountry = rewardCountry;
	}

	public Date getRewardDate() {
		return this.rewardDate;
	}

	public void setRewardDate(Date rewardDate) {
		this.rewardDate = rewardDate;
	}

	public String getRewardFrom() {
		return this.rewardFrom;
	}

	public void setRewardFrom(String rewardFrom) {
		this.rewardFrom = rewardFrom;
	}

	public String getRewardName() {
		return this.rewardName;
	}

	public void setRewardName(String rewardName) {
		this.rewardName = rewardName;
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

	public String getRewardLocation() {
		return rewardLocation;
	}

	public void setRewardLocation(String rewardLocation) {
		this.rewardLocation = rewardLocation;
	}

	

}