package th.ac.kmutt.research.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the RESEARCH_PROJECT database table.
 * 
 */
@Entity
@Table(name="RESEARCH_PROJECT")
@NamedQuery(name="ResearchProject.findAll", query="SELECT r FROM ResearchProject r")
public class ResearchProject extends DomainCommon implements Serializable {
	private static final long serialVersionUID = 1L;

	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="RESEARCH_PROJECT_ID")
	private Integer researchProjectId;

	@Column(name="ABSTRACT_TITLE")
	private String abstractTitle;

	@Column(name="AXAPTA_ID")
	private String axaptaId;


	private String detail;


	@Column(name="KEYWORK_TITLE")
	private String keyworkTitle;

	@Column(name="NRCT_CATELOGRY")
	private String nrctCatelogry;

	@Column(name="OBJECTIVE_TITLE")
	private String objectiveTitle;

	@Column(name="PROJECT_TYPE")
	private String projectType;

	private String reference;
	@Column(name="FLAG",columnDefinition="default '1'")
	private String flag;

	
	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getAbstractTitle() {
		return abstractTitle;
	}

	public void setAbstractTitle(String abstractTitle) {
		this.abstractTitle = abstractTitle;
	}

	public String getAxaptaId() {
		return axaptaId;
	}

	public void setAxaptaId(String axaptaId) {
		this.axaptaId = axaptaId;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getKeyworkTitle() {
		return keyworkTitle;
	}

	public void setKeyworkTitle(String keyworkTitle) {
		this.keyworkTitle = keyworkTitle;
	}

	public String getNrctCatelogry() {
		return nrctCatelogry;
	}

	public void setNrctCatelogry(String nrctCatelogry) {
		this.nrctCatelogry = nrctCatelogry;
	}

	public String getObjectiveTitle() {
		return objectiveTitle;
	}

	public void setObjectiveTitle(String objectiveTitle) {
		this.objectiveTitle = objectiveTitle;
	}

	public String getProjectType() {
		return projectType;
	}

	public void setProjectType(String projectType) {
		this.projectType = projectType;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	@Column(name="BUDGET_YEAR")
	private Integer budgetYear;

	@Column(name="CREATED_BY")
	private String createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CREATED_DATE")
	private Date createdDate;

	@Column(name="DOC_TYPE")
	private String docType;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="END_DATE")
	private Date endDate;

	@Column(name="ENG_NAME")
	private String engName;

	@Column(name="FUNDING_RESOURCE_ID")
	private Integer fundingResourceId;

	@Column(name="ORGANIZATION_ID")
	private Integer organizationId;

	private Integer permissions;

	private String phase;

	@Column(name="PURPOSE_BUDGET")
	private BigDecimal purposeBudget;

	private String remark;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="REPORT_DUEDATE")
	private Date reportDuedate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="REPORT_SUBMIT_DATE")
	private Date reportSubmitDate;

	@Column(name="RESEARCH_GROUP_ID")
	private Integer researchGroupId;

	@Column(name="RESEARCH_PROJECT_CODE")
	private String researchProjectCode;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="START_DATE")
	private Date startDate;

	@Column(name="SUBMIT_BUDGET")
	private BigDecimal submitBudget;

	@Column(name="THAI_NAME")
	private String thaiName;

	@Column(name="UPDATED_BY")
	private String updatedBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="UPDATED_DATE")
	private Date updatedDate;

	

	public ResearchProject() {
	}

	public Integer getResearchProjectId() {
		return this.researchProjectId;
	}

	public void setResearchProjectId(Integer researchProjectId) {
		this.researchProjectId = researchProjectId;
	}

	public Integer getBudgetYear() {
		return this.budgetYear;
	}

	public void setBudgetYear(Integer budgetYear) {
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

	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getEngName() {
		return this.engName;
	}

	public void setEngName(String engName) {
		this.engName = engName;
	}

	public Integer getFundingResourceId() {
		return this.fundingResourceId;
	}

	public void setFundingResourceId(Integer fundingResourceId) {
		this.fundingResourceId = fundingResourceId;
	}

	public Integer getOrganizationId() {
		return this.organizationId;
	}

	public void setOrganizationId(Integer organizationId) {
		this.organizationId = organizationId;
	}

	public Integer getPermissions() {
		return this.permissions;
	}

	public void setPermissions(Integer permissions) {
		this.permissions = permissions;
	}

	public String getPhase() {
		return this.phase;
	}

	public void setPhase(String phase) {
		this.phase = phase;
	}

	public BigDecimal getPurposeBudget() {
		return this.purposeBudget;
	}

	public void setPurposeBudget(BigDecimal purposeBudget) {
		this.purposeBudget = purposeBudget;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}



	public Date getReportDuedate() {
		return reportDuedate;
	}

	public void setReportDuedate(Date reportDuedate) {
		this.reportDuedate = reportDuedate;
	}

	public Date getReportSubmitDate() {
		return reportSubmitDate;
	}

	public void setReportSubmitDate(Date reportSubmitDate) {
		this.reportSubmitDate = reportSubmitDate;
	}

	public Integer getResearchGroupId() {
		return this.researchGroupId;
	}

	public void setResearchGroupId(Integer researchGroupId) {
		this.researchGroupId = researchGroupId;
	}

	public String getResearchProjectCode() {
		return this.researchProjectCode;
	}

	public void setResearchProjectCode(String researchProjectCode) {
		this.researchProjectCode = researchProjectCode;
	}

	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public BigDecimal getSubmitBudget() {
		return this.submitBudget;
	}

	public void setSubmitBudget(BigDecimal submitBudget) {
		this.submitBudget = submitBudget;
	}

	public String getThaiName() {
		return this.thaiName;
	}

	public void setThaiName(String thaiName) {
		this.thaiName = thaiName;
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