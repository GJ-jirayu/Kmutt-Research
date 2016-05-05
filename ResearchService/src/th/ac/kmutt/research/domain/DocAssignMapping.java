package th.ac.kmutt.research.domain;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;


/**
 * The persistent class for the DOC_ASSIGN_MAPPING database table.
 * 
 */
@Entity
@Table(name="DOC_ASSIGN_MAPPING")
@NamedQuery(name="DocAssignMapping.findAll", query="SELECT d FROM DocAssignMapping d")
public class DocAssignMapping implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private DocAssignMappingPK id;

	@Column(name="CREATED_BY")
	private String createdBy;

	@Column(name="CREATED_DATE")
	private Timestamp createdDate;

	@Column(name="UPDATED_BY")
	private String updatedBy;

	@Column(name="UPDATED_DATE")
	private Timestamp updatedDate;

	@Transient	
	private th.ac.kmutt.research.portal.domain.UserPortal userPortal;
	
	public th.ac.kmutt.research.portal.domain.UserPortal getUserPortal() {
		return userPortal;
	}

	public void setUserPortal(
			th.ac.kmutt.research.portal.domain.UserPortal userPortal) {
		this.userPortal = userPortal;
	}

	public DocAssignMapping() {
	}

	public DocAssignMappingPK getId() {
		return this.id;
	}

	public void setId(DocAssignMappingPK id) {
		this.id = id;
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



}