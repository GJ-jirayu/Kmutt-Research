package th.ac.kmutt.research.domain;

import java.io.Serializable;

import javax.persistence.*;

import java.sql.Timestamp;


/**
 * The persistent class for the WORK_TYPE database table.
 * 
 */
@Entity
@Table(name="WORK_TYPE")
@NamedQuery(name="WorkType.findAll", query="SELECT w FROM WorkType w")
public class WorkType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="WORK_TYPE_ID")
	private Integer workTypeId;

	@Column(name="CREATED_BY")
	private String createdBy;

	@Column(name="CREATED_DATE")
	private Timestamp createdDate;

	@Column(name="UPDATED_BY")
	private String updatedBy;

	@Column(name="UPDATED_DATE")
	private Timestamp updatedDate;

	@Column(name="WT_CODE")
	private String wtCode;

	@Column(name="WT_NAME")
	private String wtName;

	public WorkType() {
	}

	public Integer getWorkTypeId() {
		return this.workTypeId;
	}

	public void setWorkTypeId(Integer workTypeId) {
		this.workTypeId = workTypeId;
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

	public String getWtCode() {
		return this.wtCode;
	}

	public void setWtCode(String wtCode) {
		this.wtCode = wtCode;
	}

	public String getWtName() {
		return this.wtName;
	}

	public void setWtName(String wtName) {
		this.wtName = wtName;
	}

}