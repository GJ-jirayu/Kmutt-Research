package th.ac.kmutt.research.model;

import java.io.Serializable;
import java.sql.Timestamp;

import th.ac.kmutt.research.xstream.common.ImakeXML;

import com.thoughtworks.xstream.annotations.XStreamAlias;


/**
 * The persistent class for the DOC_ASSIGN_MAPPING database table.
 * 
 */
@XStreamAlias("DocAssignMappingM")
public class DocAssignMappingM extends ImakeXML implements Serializable{
	private static final long serialVersionUID = 1L;

	
	private Integer refId;

	
	private String refType;
	private String userId;
	
	private String createdBy;

	
	private Timestamp createdDate;

	
	private String updatedBy;

	
	private Timestamp updatedDate;

	private th.ac.kmutt.research.portal.model.UserPortalM userPortal;
	
	

	public DocAssignMappingM() {
	}

	public Integer getRefId() {
		return refId;
	}

	public void setRefId(Integer refId) {
		this.refId = refId;
	}

	public String getRefType() {
		return refType;
	}

	public void setRefType(String refType) {
		this.refType = refType;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Timestamp getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Timestamp getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Timestamp updatedDate) {
		this.updatedDate = updatedDate;
	}

	public String getUserId() {
		return userId;
	}

	
	public th.ac.kmutt.research.portal.model.UserPortalM getUserPortal() {
		return userPortal;
	}

	public void setUserPortal(
			th.ac.kmutt.research.portal.model.UserPortalM userPortal) {
		this.userPortal = userPortal;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}	