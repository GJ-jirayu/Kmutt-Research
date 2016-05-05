package th.ac.kmutt.research.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import th.ac.kmutt.research.xstream.common.ImakeXML;

import com.thoughtworks.xstream.annotations.XStreamAlias;
@XStreamAlias("RewardM")
public class RewardM  extends ImakeXML implements Serializable {
	private static final long serialVersionUID = 1L;

	
	private Integer rewardId;

	
	private String budgetYear;

	
	private String createdBy;

	
	private Date createdDate;

	
	private String docType;

	private String permissions;

	private String remark;

	
	//private Integer researchProjectId;
	private ResearchProjectM researchProject; // researchProjectId

	//private List<RewardCreatorM> rewardCreators;
	
	private String rewardCode;

	
	private Integer rewardCountry;
	private String rewardCountryShow;
	private RewardDocumentM rewardDocument;
	
	private String flag;
	
	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public RewardDocumentM getRewardDocument() {
		return rewardDocument;
	}

	public void setRewardDocument(RewardDocumentM rewardDocument) {
		this.rewardDocument = rewardDocument;
	}

	private List<RewardDocumentM> rewardDocuments;	
	public ResearchProjectM getResearchProject() {
		return researchProject;
	}

	public void setResearchProject(ResearchProjectM researchProject) {
		this.researchProject = researchProject;
	}
	
	public String getRewardCountryShow() {
		return rewardCountryShow;
	}

	public void setRewardCountryShow(String rewardCountryShow) {
		this.rewardCountryShow = rewardCountryShow;
	}

	private Date rewardDate;

	
	private String rewardFrom;
	private String rewardLocation;
	
	private String rewardName;

	
	private String updatedBy;

	
	private Date updatedDate;

	private RewardCreatorM rewardCreator;
	
	private List<RewardCreatorM> rewardCreators;
	private List<DocAssignMappingM> docAssignMappings;

	public RewardM() {
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

	public List<DocAssignMappingM> getDocAssignMappings() {
		return docAssignMappings;
	}

	public void setDocAssignMappings(List<DocAssignMappingM> docAssignMappings) {
		this.docAssignMappings = docAssignMappings;
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

	public RewardCreatorM getRewardCreator() {
		return rewardCreator;
	}

	public void setRewardCreator(RewardCreatorM rewardCreator) {
		this.rewardCreator = rewardCreator;
	}

	public List<RewardCreatorM> getRewardCreators() {
		return rewardCreators;
	}

	public void setRewardCreators(List<RewardCreatorM> rewardCreators) {
		this.rewardCreators = rewardCreators;
	}

	public String getRewardLocation() {
		return rewardLocation;
	}

	public List<RewardDocumentM> getRewardDocuments() {
		return rewardDocuments;
	}

	public void setRewardDocuments(List<RewardDocumentM> rewardDocuments) {
		this.rewardDocuments = rewardDocuments;
	}

	public void setRewardLocation(String rewardLocation) {
		this.rewardLocation = rewardLocation;
	}

}