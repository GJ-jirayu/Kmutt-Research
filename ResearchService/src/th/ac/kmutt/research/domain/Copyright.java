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
 * The persistent class for the COPYRIGHT database table.
 * 
 */
@Entity
@Table(name="COPYRIGHT")
@NamedQuery(name="Copyright.findAll", query="SELECT c FROM Copyright c")
public class Copyright extends DomainCommon  implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="COPYRIGHT_ID")
	private Integer copyrightId;

	@Column(name="BUDGET_YEAR")
	private String budgetYear;

	@Column(name="COPYRIGHT_CODE")
	private String copyrightCode;

	@Column(name="CREATED_BY")
	private String createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CREATED_DATE")
	private Date createdDate;

	@Column(name="DOC_TYPE")
	private String docType;

	@Column(name="ENG_NAME")
	private String engName;

	@NotFound(action=NotFoundAction.IGNORE)  
	@ManyToOne
	@JoinColumn(name="INNOVATIVE_WORKS_TYPE")
	private WorkType innovativeWorksType;
	//@Column(name="INNOVATIVE_WORKS_TYPE")
	//private String innovativeWorksType;
	

	private String permissions;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="PROPOSE_DATE")
	private Date proposeDate;

	@Column(name="PROPOSE_NO")
	private String proposeNo;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="RECEIVE_DATE")
	private Date receiveDate;

	private String remark;

	@Column(name="REQUEST_NO")
	private String requestNo;

	@NotFound(action=NotFoundAction.IGNORE)  
	@ManyToOne
	@JoinColumn(name="RESEARCH_PROJECT_ID")
	private ResearchProject researchProject;
	
	//@Column(name="RESEARCH_PROJECT_ID")
	//private Integer researchProjectId;
	//private ResearchProject researchProject;

	@Column(name="RESEARCH_TYPE")
	private Integer researchType;

	@Column(name="THAI_NAME")
	private String thaiName;

	@Column(name="UPDATED_BY")
	private String updatedBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="UPDATED_DATE")
	private Date updatedDate;

	@Column(name="FLAG")
	private String flag;
/*	//bi-directional one-to-one association to CopyrightCreator
	@OneToOne(mappedBy="copyright")
	private CopyrightCreator copyrightCreator;
*/
	public Copyright() {
	}

	public Integer getCopyrightId() {
		return this.copyrightId;
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

	public String getCopyrightCode() {
		return this.copyrightCode;
	}

	public void setCopyrightCode(String copyrightCode) {
		this.copyrightCode = copyrightCode;
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

	

	public WorkType getInnovativeWorksType() {
		return innovativeWorksType;
	}

	public void setInnovativeWorksType(WorkType innovativeWorksType) {
		this.innovativeWorksType = innovativeWorksType;
	}

	

	public ResearchProject getResearchProject() {
		return researchProject;
	}

	public void setResearchProject(ResearchProject researchProject) {
		this.researchProject = researchProject;
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

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	/*public CopyrightCreator getCopyrightCreator() {
		return this.copyrightCreator;
	}

	public void setCopyrightCreator(CopyrightCreator copyrightCreator) {
		this.copyrightCreator = copyrightCreator;
	}*/

}