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
 * The persistent class for the UTILIZATION_TYPE database table.
 * 
 */
@Entity
@Table(name="UTILIZATION_TYPE")
@NamedQuery(name="UtilizationType.findAll", query="SELECT u FROM UtilizationType u")
public class UtilizationType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="UTILIZATION_TYPE_ID")
	private Integer utilizationTypeId;

	@Column(name="CREATED_BY")
	private String createdBy;

	@Column(name="CREATED_DATE")
	private Timestamp createdDate;

	@Column(name="UPDATED_BY")
	private String updatedBy;

	@Column(name="UPDATED_DATE")
	private Timestamp updatedDate;

	@Column(name="UTILIZATION_CODE")
	private String utilizationCode;

	@Column(name="UTILIZATION_NAME")
	private String utilizationName;

	public UtilizationType() {
	}

	public Integer getUtilizationTypeId() {
		return this.utilizationTypeId;
	}

	public void setUtilizationTypeId(Integer utilizationTypeId) {
		this.utilizationTypeId = utilizationTypeId;
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

	public String getUtilizationCode() {
		return this.utilizationCode;
	}

	public void setUtilizationCode(String utilizationCode) {
		this.utilizationCode = utilizationCode;
	}

	public String getUtilizationName() {
		return this.utilizationName;
	}

	public void setUtilizationName(String utilizationName) {
		this.utilizationName = utilizationName;
	}

}