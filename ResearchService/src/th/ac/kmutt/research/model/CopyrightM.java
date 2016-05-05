package th.ac.kmutt.research.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import th.ac.kmutt.research.xstream.common.ImakeXML;

import com.thoughtworks.xstream.annotations.XStreamAlias;


/**
 * The persistent class for the COPYRIGHT database table.
 * 
 */
@XStreamAlias("CopyrightM")
public class CopyrightM  extends ImakeXML implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer copyrightId;

	private String budgetYear;

	private String copyrightCode;

	private String createdBy;

	private Date createdDate;

	private String docType;

	private String engName;

	//private String innovativeWorksType;
	private WorkTypeM  innovativeWorksType;
	
	private CopyrightDocumentM copyrightDocument;
	
	private String flag;

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public CopyrightDocumentM getCopyrightDocument() {
		return copyrightDocument;
	}

	public void setCopyrightDocument(CopyrightDocumentM copyrightDocument) {
		this.copyrightDocument = copyrightDocument;
	}

	private List<CopyrightDocumentM> copyrightDocuments;
	public List<CopyrightDocumentM> getCopyrightDocuments() {
		return copyrightDocuments;
	}

	public void setCopyrightDocuments(List<CopyrightDocumentM> copyrightDocuments) {
		this.copyrightDocuments = copyrightDocuments;
	}

	public String getCopyrightCode() {
		return copyrightCode;
	}

	public void setCopyrightCode(String copyrightCode) {
		this.copyrightCode = copyrightCode;
	}

	public WorkTypeM getInnovativeWorksType() {
		return innovativeWorksType;
	}

	public void setInnovativeWorksType(WorkTypeM innovativeWorksType) {
		this.innovativeWorksType = innovativeWorksType;
	}

	private String permissions;

	private Date proposeDate;

	private String proposeNo;

	private Date receiveDate;

	private String remark;

	private String requestNo;

	//private Integer researchProjectId;
	private ResearchProjectM researchProject;

	private Integer researchType;

	private String thaiName;

	private String updatedBy;

	private Date updatedDate;

	//private CopyrightCreatorM copyrightCreator;
	private List<CopyrightCreatorM> copyrightCreators;
	private List<DocAssignMappingM> docAssignMappings;	
	private CopyrightCreatorM copyrightCreator;

	public CopyrightCreatorM getCopyrightCreator() {
		return copyrightCreator;
	}

	public void setCopyrightCreator(CopyrightCreatorM copyrightCreator) {
		this.copyrightCreator = copyrightCreator;
	}

	public List<CopyrightCreatorM> getCopyrightCreators() {
		return copyrightCreators;
	}

	public void setCopyrightCreators(List<CopyrightCreatorM> copyrightCreators) {
		this.copyrightCreators = copyrightCreators;
	}

	public CopyrightM() {
	}

	public Integer getCopyrightId() {
		return this.copyrightId;
	}

	public List<DocAssignMappingM> getDocAssignMappings() {
		return docAssignMappings;
	}

	public void setDocAssignMappings(List<DocAssignMappingM> docAssignMappings) {
		this.docAssignMappings = docAssignMappings;
	}

	public void setCopyrightId(Integer copyrightId) {
		this.copyrightId = copyrightId;
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


	public ResearchProjectM getResearchProject() {
		return researchProject;
	}

	public void setResearchProject(ResearchProjectM researchProject) {
		this.researchProject = researchProject;
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

	public String getEngName() {
		return this.engName;
	}

	public void setEngName(String engName) {
		this.engName = engName;
	}

	public String getPermissions() {
		return this.permissions;
	}

	public void setPermissions(String permissions) {
		this.permissions = permissions;
	}

	public Date getProposeDate() {
		return this.proposeDate;
	}

	public void setProposeDate(Date proposeDate) {
		this.proposeDate = proposeDate;
	}

	public String getProposeNo() {
		return this.proposeNo;
	}

	public void setProposeNo(String proposeNo) {
		this.proposeNo = proposeNo;
	}

	public Date getReceiveDate() {
		return this.receiveDate;
	}

	public void setReceiveDate(Date receiveDate) {
		this.receiveDate = receiveDate;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getRequestNo() {
		return this.requestNo;
	}

	public void setRequestNo(String requestNo) {
		this.requestNo = requestNo;
	}

	

	public Integer getResearchType() {
		return this.researchType;
	}

	public void setResearchType(Integer researchType) {
		this.researchType = researchType;
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