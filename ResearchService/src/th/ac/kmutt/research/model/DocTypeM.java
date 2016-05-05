package th.ac.kmutt.research.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import th.ac.kmutt.research.xstream.common.ImakeXML;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("DocTypeM")
public class DocTypeM  extends ImakeXML implements Serializable {
	private static final long serialVersionUID = 1L;

	private String dtName;

	
	private String createdBy;

	
	private Timestamp createdDate;

	
	private String dtDesc;

	
	private String isDelete;

	
	private String isDisableForAdmin;

	
	private String isRead; 

	
	private String isUpdate;

	
	private String isView;
	private String isCreate;
	
	private String[] isViews;
    private String[] isUpdates;
    private String[] isDeletes;
    private String[] isCreates;
    private String[] isDisableForAdmins;
	public String getIsCreate() {
		return isCreate;
	}

	public void setIsCreate(String isCreate) {
		this.isCreate = isCreate;
	}

	private String updatedBy;

	private List<DocTypeM> doctypes;
	
	private Timestamp updatedDate;

	public List<DocTypeM> getDoctypes() {
		return doctypes;
	}

	public void setDoctypes(List<DocTypeM> doctypes) {
		this.doctypes = doctypes;
	}

	public DocTypeM() {
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

	public void setUpdatedDate(Timestamp updatedDate) {
		this.updatedDate = updatedDate;
	}

	public String[] getIsViews() {
		return isViews;
	}

	public void setIsViews(String[] isViews) {
		this.isViews = isViews;
	}

	public String[] getIsUpdates() {
		return isUpdates;
	}

	public void setIsUpdates(String[] isUpdates) {
		this.isUpdates = isUpdates;
	}

	public String[] getIsDeletes() {
		return isDeletes;
	}

	public void setIsDeletes(String[] isDeletes) {
		this.isDeletes = isDeletes;
	}

	public String[] getIsCreates() {
		return isCreates;
	}

	public void setIsCreates(String[] isCreates) {
		this.isCreates = isCreates;
	}

	public String[] getIsDisableForAdmins() {
		return isDisableForAdmins;
	}

	public void setIsDisableForAdmins(String[] isDisableForAdmins) {
		this.isDisableForAdmins = isDisableForAdmins;
	}

}