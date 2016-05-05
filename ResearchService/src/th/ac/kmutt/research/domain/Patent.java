package th.ac.kmutt.research.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the PATENT database table.
 * 
 */
@Entity
@Table(name="PATENT")
@NamedQuery(name="Patent.findAll", query="SELECT p FROM Patent p")
public class Patent extends DomainCommon implements Serializable {
	private static final long serialVersionUID = 1L;


	

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="INVENTION_ID")
	private Integer inventionId;
	

	@Column(name="INVENTION_CODE")
	private String inventionCode;


	public String getInventionCode() {
		return inventionCode;
	}

	public void setInventionCode(String inventionCode) {
		this.inventionCode = inventionCode;
	}

	

	public Integer getIntegerernalOrganizationId() {
		return integerernalOrganizationId;
	}



	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="ANNOUNCEMENT_DATE")
	private Date announcementDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="ANNOUNCEMENT_PAY_DATE")
	private Date announcementPayDate;

	@Column(name="BUDGET_YEAR")
	private String budgetYear;

	@Column(name="CREATED_BY")
	private String createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CREATED_DATE")
	private Date createdDate;

	@Column(name="DOC_TYPE")
	private String docType;

	@Column(name="ENG_NAME")
	private String engName;

	@Column(name="INTERNAL_ORGANIZATION_ID")
	private Integer integerernalOrganizationId;

	public void setIntegerernalOrganizationId(Integer integerernalOrganizationId) {
		this.integerernalOrganizationId = integerernalOrganizationId;
	}



	@Column(name="INVENTION_TYPE")
	private String inventionType;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="PATENT_DATE")
	private Date patentDate;

	@Column(name="PATENT_NO")
	private String patentNo;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="PAY_DATE")
	private Date payDate;

	private String permissions;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="PROPOSE_BOOK_DATE")
	private Date proposeBookDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="PROPOSE_DATE")
	private Date proposeDate;

	@Column(name="PROPOSE_NO")
	private String proposeNo;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="RECEIVE_DATE")
	private Date receiveDate;

	private String remark;

	@Column(name="RESEARCH_GROUP")
	private Integer researchGroup;

	@Column(name="RESEARCH_PROJECT_ID")
	private Integer researchProjectId;

	private String result;

	@Column(name="RPOPOSE_TYPE")
	private String rpoposeType;

	@Column(name="THAI_NAME")
	private String thaiName; 

	@Column(name="UPDATED_BY")
	private String updatedBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="UPDATED_DATE")
	private Date updatedDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="VERIFY_DATE")
	private Date verifyDate;
	
	@Column(name="FLAG")
	private String flag;

/*	//bi-directional one-to-one association to PatentCreator
	@NotFound(action=NotFoundAction.IGNORE)  
	@OneToOne(mappedBy="patent")
	private PatentCreator patentCreator;*/

	/*//bi-directional many-to-one association to PatentDocument
	@OneToMany(mappedBy="patent")
	private List<PatentDocument> patentDocuments;*/

/*	//bi-directional one-to-one association to PatentEditDate
	@NotFound(action=NotFoundAction.IGNORE)  
	@OneToOne(mappedBy="patent")
	private PatentEditDate patentEditDate;*/
/*
	//bi-directional one-to-one association to PatentFeePayment
	@NotFound(action=NotFoundAction.IGNORE)  
	@OneToOne(mappedBy="patent")
	private PatentFeePayment patentFeePayment;

	//bi-directional one-to-one association to PatentInherited
	@NotFound(action=NotFoundAction.IGNORE)  
	@OneToOne(mappedBy="patent")
	private PatentInherited patentInherited;

	//bi-directional one-to-one association to PatentRightholder
	@NotFound(action=NotFoundAction.IGNORE)  
	@OneToOne(mappedBy="patent")
	private PatentRightholder patentRightholder;*/

	public Patent() {
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

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

}