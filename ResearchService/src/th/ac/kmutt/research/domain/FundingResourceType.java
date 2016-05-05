package th.ac.kmutt.research.domain;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the FUNDING_RESOURCE_TYPE database table.
 * 
 */
@Entity
@Table(name="FUNDING_RESOURCE_TYPE")
@NamedQuery(name="FundingResourceType.findAll", query="SELECT f FROM FundingResourceType f")
public class FundingResourceType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="FUNDING_RESOURCE_TYPE_ID")
	private Integer fundingResourceTypeId;

	@Column(name="CREATED_BY")
	private String createdBy;

	@Column(name="CREATED_DATE")
	private Timestamp createdDate;

	@Column(name="FRT_CODE")
	private String frtCode;

	@Column(name="FRT_NAME")
	private String frtName;

	@Column(name="FRT_SOURCE")
	private String frtSource;

	@Column(name="UPDATED_BY")
	private String updatedBy;

	@Column(name="UPDATED_DATE")
	private Timestamp updatedDate;

	/*//bi-directional many-to-one association to FundingResource
	@OneToMany(mappedBy="fundingResourceType")
	private List<FundingResource> fundingResources;*/

	public FundingResourceType() {
	}

	public Integer getFundingResourceTypeId() {
		return this.fundingResourceTypeId;
	}

	public void setFundingResourceTypeId(Integer fundingResourceTypeId) {
		this.fundingResourceTypeId = fundingResourceTypeId;
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

	public String getFrtCode() {
		return this.frtCode;
	}

	public void setFrtCode(String frtCode) {
		this.frtCode = frtCode;
	}

	public String getFrtName() {
		return this.frtName;
	}

	public void setFrtName(String frtName) {
		this.frtName = frtName;
	}

	public String getFrtSource() {
		return this.frtSource;
	}

	public void setFrtSource(String frtSource) {
		this.frtSource = frtSource;
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

	
}