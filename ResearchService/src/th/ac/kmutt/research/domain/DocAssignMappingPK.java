package th.ac.kmutt.research.domain;

import java.io.Serializable;

import javax.persistence.*;

/**
 * The primary key class for the DOC_ASSIGN_MAPPING database table.
 * 
 */
@Embeddable
public class DocAssignMappingPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="REF_ID")
	private Integer refId;

	@Column(name="REF_TYPE")
	private String refType;
	
	@Column(name="USER_ID")
	private String userId;

	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public DocAssignMappingPK() {
	}
	public Integer getRefId() {
		return this.refId;
	}
	public void setRefId(Integer refId) {
		this.refId = refId;
	}
	public String getRefType() {
		return this.refType;
	}
	public void setRefType(String refType) {
		this.refType = refType;
	}


}