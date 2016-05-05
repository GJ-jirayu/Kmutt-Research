package th.ac.kmutt.research.domain;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;


/**
 * The persistent class for the FUNDING_RESOURCE database table.
 * 
 */
@Entity
@Table(name="FUNDING_RESOURCE")
@NamedQuery(name="FundingResource.findAll", query="SELECT f FROM FundingResource f")
public class FundingResource implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="FUNDING_RESOURCE_ID")
	private Integer fundingResourceId;

	@Column(name="CREATED_BY")
	private String createdBy;

	@Column(name="CREATED_DATE")
	private Timestamp createdDate;

	@Column(name="FR_NAME_ENG")
	private String frNameEng;

	@Column(name="FR_NAME_THAI")
	private String frNameThai;

	@Column(name="FR_SHORT_NAME")
	private String frShortName;

	@Column(name="FUNDING_RESOURCE_CODE")
	private String fundingResourceCode;

	@Column(name="UPDATED_BY")
	private String updatedBy;

	@Column(name="UPDATED_DATE")
	private Timestamp updatedDate;

	//bi-directional many-to-one association to Country
	@NotFound(action=NotFoundAction.IGNORE)  
	@ManyToOne
	@JoinColumn(name="COUNTRY_ID")
	private Country country;

	//bi-directional many-to-one association to FundingResourceType
	@NotFound(action=NotFoundAction.IGNORE)  
	@ManyToOne
	@JoinColumn(name="FUNDING_RESOURCE_TYPE_ID")
	private FundingResourceType fundingResourceType;

	public FundingResource() {
	}

	public Integer getFundingResourceId() {
		return this.fundingResourceId;
	}

	public void setFundingResourceId(Integer fundingResourceId) {
		this.fundingResourceId = fundingResourceId;
	}

	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Timestamp getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	public String getFrNameEng() {
		return this.frNameEng;
	}

	public void setFrNameEng(String frNameEng) {
		this.frNameEng = frNameEng;
	}

	public String getFrNameThai() {
		return this.frNameThai;
	}

	public void setFrNameThai(String frNameThai) {
		this.frNameThai = frNameThai;
	}

	public String getFrShortName() {
		return this.frShortName;
	}

	public void setFrShortName(String frShortName) {
		this.frShortName = frShortName;
	}

	public String getFundingResourceCode() {
		return this.fundingResourceCode;
	}

	public void setFundingResourceCode(String fundingResourceCode) {
		this.fundingResourceCode = fundingResourceCode;
	}

	public String getUpdatedBy() {
		return this.updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Timestamp getUpdatedDate() {
		return this.updatedDate;
	}

	public void setUpdatedDate(Timestamp updatedDate) {
		this.updatedDate = updatedDate;
	}

	public Country getCountry() {
		return this.country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public FundingResourceType getFundingResourceType() {
		return this.fundingResourceType;
	}

	public void setFundingResourceType(FundingResourceType fundingResourceType) {
		this.fundingResourceType = fundingResourceType;
	}

}