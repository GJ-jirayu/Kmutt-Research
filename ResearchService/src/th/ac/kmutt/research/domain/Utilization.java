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
 * The persistent class for the UTILIZATION database table.
 * 
 */
@Entity
@Table(name="UTILIZATION")
@NamedQuery(name="Utilization.findAll", query="SELECT u FROM Utilization u")
public class Utilization extends DomainCommon implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private UtilizationPK id;

	@Column(name="BUDGET_YEAR")
	private Integer budgetYear;

	@Column(name="CREATED_BY")
	private String createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CREATED_DATE")
	private Date createdDate;

	@Column(name="DOC_TYPE")
	private String docType;

	@Column(name="UPDATED_BY")
	private String updatedBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="UPDATED_DATE")
	private Date updatedDate;
	
	@Column(name="FILE_NAME")
	private String fileName;

	@Column(name="FILE_PATH")
	private String filePath;
	
	@Column(name="REF_HOTLINK")
	private String refHotlink;

	/*@Column(name="UTILIZATION_TYPE_ID")
	private Integer utilizationTypeId;*/
	@NotFound(action=NotFoundAction.IGNORE)  
	@ManyToOne
	@JoinColumn(name="UTILIZATION_TYPE_ID")
	private UtilizationType utilizationType;
	
	@Column(name="OUTCOME")
	private String outCome;

	@Column(name="FLAG")
	private String flag;
	
	@NotFound(action=NotFoundAction.IGNORE)  
	@ManyToOne
	@JoinColumn(name="RESEARCH_PROJECT_ID",insertable=false,updatable=false)
	private ResearchProject researchProject;
	public ResearchProject getResearchProject() {
		return researchProject;
	}

	public void setResearchProject(ResearchProject researchProject) {
		this.researchProject = researchProject;
	}

	public Utilization() {
	}

	public UtilizationPK getId() {
		return this.id;
	}

	public void setId(UtilizationPK id) {
		this.id = id;
	}

	public Integer getBudgetYear() {
		return this.budgetYear;
	}

	public void setBudgetYear(Integer budgetYear) {
		this.budgetYear = budgetYear;
	}

	public String getOutCome() {
		return outCome;
	}

	public void setOutCome(String outCome) {
		this.outCome = outCome;
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

	public UtilizationType getUtilizationType() {
		return utilizationType;
	}

	public void setUtilizationType(UtilizationType utilizationType) {
		this.utilizationType = utilizationType;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getRefHotlink() {
		return refHotlink;
	}

	public void setRefHotlink(String refHotlink) {
		this.refHotlink = refHotlink;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}


}