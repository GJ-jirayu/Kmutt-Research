package th.ac.kmutt.research.domain;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the DOC_TYPE database table.
 * 
 */
@Entity
@Table(name="DOC_TYPE")
@NamedQuery(name="DocType.findAll", query="SELECT d FROM DocType d")
public class DocType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="DT_NAME")
	private String dtName;

	@Column(name="CREATED_BY")
	private String createdBy;

	@Column(name="CREATED_DATE")
	private Timestamp createdDate;

	@Column(name="DT_DESC")
	private String dtDesc;

	@Column(name="IS_DELETE")
	private String isDelete;
	
	@Column(name="IS_CREATE")
	private String isCreate;


	@Column(name="IS_DISABLE_FOR_ADMIN")
	private String isDisableForAdmin;

	@Column(name="IS_READ")
	private String isRead;

	@Column(name="IS_UPDATE")
	private String isUpdate;

	@Column(name="IS_VIEW")
	private String isView;

	@Column(name="UPDATED_BY")
	private String updatedBy;

	@Column(name="UPDATED_DATE")
	private Timestamp updatedDate;

	public DocType() {
	}

	public String getDtName() {
		return this.dtName;
	}

	public void setDtName(String dtName) {
		this.dtName = dtName;
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

	public String getDtDesc() {
		return this.dtDesc;
	}

	public void setDtDesc(String dtDesc) {
		this.dtDesc = dtDesc;
	}

	public String getIsDelete() {
		return this.isDelete;
	}

	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}

	public String getIsDisableForAdmin() {
		return this.isDisableForAdmin;
	}

	public void setIsDisableForAdmin(String isDisableForAdmin) {
		this.isDisableForAdmin = isDisableForAdmin;
	}

	public String getIsRead() {
		return this.isRead;
	}

	public void setIsRead(String isRead) {
		this.isRead = isRead;
	}

	public String getIsUpdate() {
		return this.isUpdate;
	}

	public void setIsUpdate(String isUpdate) {
		this.isUpdate = isUpdate;
	}

	public String getIsView() {
		return this.isView;
	}

	public void setIsView(String isView) {
		this.isView = isView;
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

	public String getIsCreate() {
		return isCreate;
	}

	public void setIsCreate(String isCreate) {
		this.isCreate = isCreate;
	}

	public void setUpdatedDate(Timestamp updatedDate) {
		this.updatedDate = updatedDate;
	}

}