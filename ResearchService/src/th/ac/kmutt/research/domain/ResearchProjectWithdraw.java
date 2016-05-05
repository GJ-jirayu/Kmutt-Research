package th.ac.kmutt.research.domain;

import java.io.Serializable;

import javax.persistence.*;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the RESEARCH_PROJECT_WITHDRAW database table.
 * 
 */
@Entity
@Table(name="RESEARCH_PROJECT_WITHDRAW")
@NamedQuery(name="ResearchProjectWithdraw.findAll", query="SELECT r FROM ResearchProjectWithdraw r")
public class ResearchProjectWithdraw implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ResearchProjectWithdrawPK id;

	@Column(name="AMOUNT_WITHDRAW")
	private BigDecimal amountWithdraw;

	private BigDecimal balance;

	@Column(name="CREATED_BY")
	private String createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CREATED_DATE")
	private Date createdDate;

	@Column(name="LIST_ITEM")
	private String listItem;

	private String remark;

	@Column(name="UPDATED_BY")
	private String updatedBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="UPDATED_DATE")
	private Date updatedDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="WITHDRAW_DATE")
	private Date withdrawDate;

	//bi-directional many-to-one association to ResearchProject
	@NotFound(action=NotFoundAction.IGNORE)  
	@ManyToOne
	@JoinColumn(name="RESEARCH_PROJECT_ID",insertable=false,updatable=false)
	private ResearchProject researchProject;

	public ResearchProjectWithdraw() {
	}

	public ResearchProjectWithdrawPK getId() {
		return this.id;
	}

	public void setId(ResearchProjectWithdrawPK id) {
		this.id = id;
	}

	public BigDecimal getAmountWithdraw() {
		return this.amountWithdraw;
	}

	public void setAmountWithdraw(BigDecimal amountWithdraw) {
		this.amountWithdraw = amountWithdraw;
	}

	public BigDecimal getBalance() {
		return this.balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
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

	public String getListItem() {
		return this.listItem;
	}

	public void setListItem(String listItem) {
		this.listItem = listItem;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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

	public Date getWithdrawDate() {
		return this.withdrawDate;
	}

	public void setWithdrawDate(Date withdrawDate) {
		this.withdrawDate = withdrawDate;
	}

	public ResearchProject getResearchProject() {
		return this.researchProject;
	}

	public void setResearchProject(ResearchProject researchProject) {
		this.researchProject = researchProject;
	}

}