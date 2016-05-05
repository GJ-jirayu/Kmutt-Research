package th.ac.kmutt.research.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import th.ac.kmutt.research.xstream.common.ImakeXML;

import com.thoughtworks.xstream.annotations.XStreamAlias;
@XStreamAlias("ResearchProjectM")
public class ResearchProjectM extends ImakeXML implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer researchProjectId;

	private String abstractTitle;

	private String axaptaId;


	private String detail;


	private String keyworkTitle;

	private String nrctCatelogry;

	private String objectiveTitle;

	private String projectType;

	private String reference;
	
	private Integer budgetYear;

	
	private String createdBy;

	
	private Date createdDate;

	
	private String docType;

	
	private Date endDate;

	
	private String engName;

	
	private Integer fundingResourceId;
	private String fundingResourceShow;
	
	private Integer organizationId;
	private String organizationShow;
	
	private Integer researchGroupId;
	private String researchGroupShow;
	
	private Integer permissions;

	private String phase;

	
	private BigDecimal purposeBudget;

	private String remark;


	private Date reportDuedate;

	private Date reportSubmitDate;
	

	
	private String proofParticipationResource; //  Proof_Participation
	private String proofAcademicResource; //Proof_Academic
	
	private String flag;	

	
	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getProofParticipationResource() {
		return proofParticipationResource;
	}

	public void setProofParticipationResource(String proofParticipationResource) {
		this.proofParticipationResource = proofParticipationResource;
	}

	public String getProofAcademicResource() {
		return proofAcademicResource;
	}

	public void setProofAcademicResource(String proofAcademicResource) {
		this.proofAcademicResource = proofAcademicResource;
	}

	public String getFundingResourceShow() {
		return fundingResourceShow;
	}

	public void setFundingResourceShow(String fundingResourceShow) {
		this.fundingResourceShow = fundingResourceShow;
	}

	public String getOrganizationShow() {
		return organizationShow;
	}

	public void setOrganizationShow(String organizationShow) {
		this.organizationShow = organizationShow;
	}

	public String getResearchGroupShow() {
		return researchGroupShow;
	}

	public void setResearchGroupShow(String researchGroupShow) {
		this.researchGroupShow = researchGroupShow;
	}

	private String researchProjectCode;

	
	private Date startDate;

	
	private BigDecimal submitBudget;

	
	private String thaiName;

	
	private String updatedBy;

	
	private Date updatedDate;

	private List<ResearchProjectDocumentM> documents;
	private List<ResearchProjectPaymentM> payments;
	private List<ResearchProjectProgressM> progresList;
	private List<ResearchProjectResearcherM> researchers;
	private List<ResearchProjectWithdrawM>	 withdraws;
	private List<DocAssignMappingM> docAssignMappings;	
	
	private ResearchProjectDocumentM document;
	private ResearchProjectPaymentM payment;
	private ResearchProjectProgressM progress;
	private ResearchProjectResearcherM researcher;
	private ResearchProjectWithdrawM	 withdraw;

	public ResearchProjectM() {
	}

	public Integer getResearchProjectId() {
		return this.researchProjectId;
	}

	public List<ResearchProjectDocumentM> getDocuments() {
		return documents;
	}

	public void setDocuments(List<ResearchProjectDocumentM> documents) {
		this.documents = documents;
	}

	public List<DocAssignMappingM> getDocAssignMappings() {
		return docAssignMappings;
	}

	public void setDocAssignMappings(List<DocAssignMappingM> docAssignMappings) {
		this.docAssignMappings = docAssignMappings;
	}

	public List<ResearchProjectPaymentM> getPayments() {
		return payments;
	}

	public void setPayments(List<ResearchProjectPaymentM> payments) {
		this.payments = payments;
	}

	public List<ResearchProjectProgressM> getProgresList() {
		return progresList;
	}

	public void setProgresList(List<ResearchProjectProgressM> progresList) {
		this.progresList = progresList;
	}

	public List<ResearchProjectResearcherM> getResearchers() {
		return researchers;
	}

	public void setResearchers(List<ResearchProjectResearcherM> researchers) {
		this.researchers = researchers;
	}

	public List<ResearchProjectWithdrawM> getWithdraws() {
		return withdraws;
	}

	public void setWithdraws(List<ResearchProjectWithdrawM> withdraws) {
		this.withdraws = withdraws;
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

	public ResearchProjectDocumentM getDocument() {
		return document;
	}

	public void setDocument(ResearchProjectDocumentM document) {
		this.document = document;
	}

	public ResearchProjectPaymentM getPayment() {
		return payment;
	}

	public void setPayment(ResearchProjectPaymentM payment) {
		this.payment = payment;
	}

	public ResearchProjectProgressM getProgress() {
		return progress;
	}

	public void setProgress(ResearchProjectProgressM progress) {
		this.progress = progress;
	}

	public ResearchProjectResearcherM getResearcher() {
		return researcher;
	}

	public void setResearcher(ResearchProjectResearcherM researcher) {
		this.researcher = researcher;
	}

	public ResearchProjectWithdrawM getWithdraw() {
		return withdraw;
	}

	public void setWithdraw(ResearchProjectWithdrawM withdraw) {
		this.withdraw = withdraw;
	}

	
}