package th.ac.kmutt.research.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import th.ac.kmutt.research.xstream.common.ImakeXML;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("PatentM")
public class PatentM  extends ImakeXML implements Serializable {
	private static final long serialVersionUID = 1L;


	
	private Integer inventionId;
	private String inventionCode;

	
	private Date announcementDate;

	
	private Date announcementPayDate;

	
	private String budgetYear;

	
	private String createdBy;

	
	private Date createdDate;

	
	private String docType;

	
	private String engName;

	
	private Integer integerernalOrganizationId;
	private String integerernalOrganizationShow;

	
	

	private String inventionType;

	
	private Date patentDate;

	
	private String patentNo;

	
	private Date payDate;

	private String permissions;

	
	private Date proposeBookDate;

	
	private Date proposeDate;

	
	private String proposeNo;

	
	private Date receiveDate;

	private String remark;

	
	private Integer researchGroup;
	private String researchGroupShow;
	private String flag;
	
	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getResearchGroupShow() {
		return researchGroupShow;
	}

	public void setResearchGroupShow(String researchGroupShow) {
		this.researchGroupShow = researchGroupShow;
	}

	private Integer researchProjectId;
	private String researchProjectShow;

	public String getResearchProjectShow() {
		return researchProjectShow;
	}

	public void setResearchProjectShow(String researchProjectShow) {
		this.researchProjectShow = researchProjectShow;
	}

	private String result;

	
	private String rpoposeType;

	
	private String thaiName;

	
	private String updatedBy;

	
	private Date updatedDate;

	
	private Date verifyDate;

	
	
	private List<PatentCreatorM> patentCreators;

	private List<PatentEditDateM> patentEditDates;

	private List<PatentFeePaymentM> patentFeePayments;

	private List<PatentInheritedM> patentInheriteds;

	private List<PatentRightholderM> patentRightholders;
	
	private List<PatentDocumentM> patentDocuments;
	
	private List<DocAssignMappingM> docAssignMappings;
	
	
	public List<DocAssignMappingM> getDocAssignMappings() {
		return docAssignMappings;
	}

	public void setDocAssignMappings(List<DocAssignMappingM> docAssignMappings) {
		this.docAssignMappings = docAssignMappings;
	}

	private PatentCreatorM patentCreator;

	private PatentEditDateM patentEditDate;

	private PatentFeePaymentM patentFeePayment;

	private PatentInheritedM patentInherited;

	private PatentRightholderM patentRightholder;
	
	private PatentDocumentM patentDocument;
	

	public PatentM() {
	}

	public Integer getInventionId() {
		return this.inventionId;
	}

	public void setInventionId(Integer inventionId) {
		this.inventionId = inventionId;
	}

	public Date getAnnouncementDate() {
		return this.announcementDate;
	}

	public void setAnnouncementDate(Date announcementDate) {
		this.announcementDate = announcementDate;
	}

	public Date getAnnouncementPayDate() {
		return this.announcementPayDate;
	}

	public void setAnnouncementPayDate(Date announcementPayDate) {
		this.announcementPayDate = announcementPayDate;
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

	public String getEngName() {
		return this.engName;
	}

	public void setEngName(String engName) {
		this.engName = engName;
	}

	

	public String getInventionType() {
		return this.inventionType;
	}

	public void setInventionType(String inventionType) {
		this.inventionType = inventionType;
	}

	public Date getPatentDate() {
		return this.patentDate;
	}

	public void setPatentDate(Date patentDate) {
		this.patentDate = patentDate;
	}

	public String getPatentNo() {
		return this.patentNo;
	}

	public void setPatentNo(String patentNo) {
		this.patentNo = patentNo;
	}

	public Date getPayDate() {
		return this.payDate;
	}

	public void setPayDate(Date payDate) {
		this.payDate = payDate;
	}

	public String getPermissions() {
		return this.permissions;
	}

	public void setPermissions(String permissions) {
		this.permissions = permissions;
	}

	public Date getProposeBookDate() {
		return this.proposeBookDate;
	}

	public void setProposeBookDate(Date proposeBookDate) {
		this.proposeBookDate = proposeBookDate;
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

	public Integer getResearchGroup() {
		return this.researchGroup;
	}

	public void setResearchGroup(Integer researchGroup) {
		this.researchGroup = researchGroup;
	}

	public Integer getResearchProjectId() {
		return this.researchProjectId;
	}

	public void setResearchProjectId(Integer researchProjectId) {
		this.researchProjectId = researchProjectId;
	}

	public String getResult() {
		return this.result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getRpoposeType() {
		return this.rpoposeType;
	}

	public void setRpoposeType(String rpoposeType) {
		this.rpoposeType = rpoposeType;
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

	public Date getVerifyDate() {
		return this.verifyDate;
	}

	public void setVerifyDate(Date verifyDate) {
		this.verifyDate = verifyDate;
	}

	

	
	public Integer getIntegerernalOrganizationId() {
		return integerernalOrganizationId;
	}

	public void setIntegerernalOrganizationId(Integer integerernalOrganizationId) {
		this.integerernalOrganizationId = integerernalOrganizationId;
	}

	public String getIntegerernalOrganizationShow() {
		return integerernalOrganizationShow;
	}

	public void setIntegerernalOrganizationShow(String integerernalOrganizationShow) {
		this.integerernalOrganizationShow = integerernalOrganizationShow;
	}

	public List<PatentCreatorM> getPatentCreators() {
		return patentCreators;
	}

	public void setPatentCreators(List<PatentCreatorM> patentCreators) {
		this.patentCreators = patentCreators;
	}

	public List<PatentEditDateM> getPatentEditDates() {
		return patentEditDates;
	}

	public void setPatentEditDates(List<PatentEditDateM> patentEditDates) {
		this.patentEditDates = patentEditDates;
	}

	public List<PatentFeePaymentM> getPatentFeePayments() {
		return patentFeePayments;
	}

	public void setPatentFeePayments(List<PatentFeePaymentM> patentFeePayments) {
		this.patentFeePayments = patentFeePayments;
	}

	public List<PatentInheritedM> getPatentInheriteds() {
		return patentInheriteds;
	}

	public void setPatentInheriteds(List<PatentInheritedM> patentInheriteds) {
		this.patentInheriteds = patentInheriteds;
	}

	public List<PatentRightholderM> getPatentRightholders() {
		return patentRightholders;
	}

	public void setPatentRightholders(List<PatentRightholderM> patentRightholders) {
		this.patentRightholders = patentRightholders;
	}

	

	public List<PatentDocumentM> getPatentDocuments() {
		return patentDocuments;
	}

	public void setPatentDocuments(List<PatentDocumentM> patentDocuments) {
		this.patentDocuments = patentDocuments;
	}

	public String getInventionCode() {
		return inventionCode;
	}

	public void setInventionCode(String inventionCode) {
		this.inventionCode = inventionCode;
	}

	public PatentCreatorM getPatentCreator() {
		return patentCreator;
	}

	public void setPatentCreator(PatentCreatorM patentCreator) {
		this.patentCreator = patentCreator;
	}

	public PatentEditDateM getPatentEditDate() {
		return patentEditDate;
	}

	public void setPatentEditDate(PatentEditDateM patentEditDate) {
		this.patentEditDate = patentEditDate;
	}

	public PatentFeePaymentM getPatentFeePayment() {
		return patentFeePayment;
	}

	public void setPatentFeePayment(PatentFeePaymentM patentFeePayment) {
		this.patentFeePayment = patentFeePayment;
	}

	public PatentInheritedM getPatentInherited() {
		return patentInherited;
	}

	public void setPatentInherited(PatentInheritedM patentInherited) {
		this.patentInherited = patentInherited;
	}

	public PatentRightholderM getPatentRightholder() {
		return patentRightholder;
	}

	public void setPatentRightholder(PatentRightholderM patentRightholder) {
		this.patentRightholder = patentRightholder;
	}

	public PatentDocumentM getPatentDocument() {
		return patentDocument;
	}

	public void setPatentDocument(PatentDocumentM patentDocument) {
		this.patentDocument = patentDocument;
	}

}