package th.ac.kmutt.research.domain;

import java.io.Serializable;

import javax.persistence.*;

import java.sql.Timestamp;


/**
 * The persistent class for the PUBLISH_LEVEL database table.
 * 
 */
@Entity
@Table(name="PUBLISH_LEVEL")
@NamedQuery(name="PublishLevel.findAll", query="SELECT p FROM PublishLevel p")
public class PublishLevel implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="PUBLISH_LEVEL_ID")
	private Integer publishLevelId;

	@Column(name="CREATED_BY")
	private String createdBy;

	@Column(name="CREATED_DATE")
	private Timestamp createdDate;

	@Column(name="PL_CODE")
	private String plCode;

	@Column(name="PL_NAME")
	private String plName;

	@Column(name="UPDATED_BY")
	private String updatedBy;

	@Column(name="UPDATED_DATE")
	private Timestamp updatedDate;

	public PublishLevel() {
	}

	public Integer getPublishLevelId() {
		return this.publishLevelId;
	}

	public void setPublishLevelId(Integer publishLevelId) {
		this.publishLevelId = publishLevelId;
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

	public String getPlCode() {
		return this.plCode;
	}

	public void setPlCode(String plCode) {
		this.plCode = plCode;
	}

	public String getPlName() {
		return this.plName;
	}

	public void setPlName(String plName) {
		this.plName = plName;
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