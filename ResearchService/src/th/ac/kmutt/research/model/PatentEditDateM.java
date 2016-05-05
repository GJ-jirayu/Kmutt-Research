package th.ac.kmutt.research.model;

import java.io.Serializable;
import java.util.Date;

import th.ac.kmutt.research.xstream.common.ImakeXML;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("PatentEditDateM")
public class PatentEditDateM  extends ImakeXML implements Serializable {
	private static final long serialVersionUID = 1L;

	
	private Integer inventionId;
	

	//private Integer years;	
	
	
	

	
	private Date editDate;
	private String editDateShow;

	
	private Integer editItemList;

	private String remark;

	private String createdBy;
	private String updatedBy;

	private Date createdDate;
	private Date updatedDate;

	private PatentM patent;

	/*public Integer getYears() {
		return years;
	}

	public void setYears(Integer years) {
		this.years = years;
	}
*/
	public PatentEditDateM() {
	}

	public Integer getInventionId() {
		return this.inventionId;
	}

	public void setInventionId(Integer inventionId) {
		this.inventionId = inventionId;
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

	public Date getEditDate() {
		return this.editDate;
	}

	public void setEditDate(Date editDate) {
		this.editDate = editDate;
	}

	public Integer getEditItemList() {
		return this.editItemList;
	}

	public void setEditItemList(Integer editItemList) {
		this.editItemList = editItemList;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getEditDateShow() {
		return editDateShow;
	}

	public void setEditDateShow(String editDateShow) {
		this.editDateShow = editDateShow;
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

	public PatentM getPatent() {
		return patent;
	}

	public void setPatent(PatentM patent) {
		this.patent = patent;
	}

	

}