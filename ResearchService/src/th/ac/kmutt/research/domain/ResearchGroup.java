package th.ac.kmutt.research.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the RESEARCH_GROUP database table.
 * 
 */
@Entity
@Table(name="RESEARCH_GROUP")
@NamedQuery(name="ResearchGroup.findAll", query="SELECT r FROM ResearchGroup r")
public class ResearchGroup implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="RESEARCH_GROUP_ID")
	private Integer researchGroupId;

	@Column(name="CREATED_BY")
	private String createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CREATED_DATE")
	private Date createdDate;

	@Column(name="GROUP_CODE")
	private String groupCode;

	@Column(name="GROUP_ENG")
	private String groupEng;

	@Column(name="GROUP_TH")
	private String groupTh;

	private String permissions;

	@Column(name="UPDATED_BY")
	private String updatedBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="UPDATED_DATE")
	private Date updatedDate;

	/*//bi-directional many-to-one association to ResearcherGroup
	@OneToMany(mappedBy="researchGroup")
	private List<ResearcherGroup> researcherGroups;*/

	public ResearchGroup() {
	}

	public Integer getResearchGroupId() {
		return this.researchGroupId;
	}

	public void setResearchGroupId(Integer researchGroupId) {
		this.researchGroupId = researchGroupId;
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

	public String getGroupCode() {
		return this.groupCode;
	}

	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}

	public String getGroupEng() {
		return this.groupEng;
	}

	public void setGroupEng(String groupEng) {
		this.groupEng = groupEng;
	}

	public String getGroupTh() {
		return this.groupTh;
	}

	public void setGroupTh(String groupTh) {
		this.groupTh = groupTh;
	}

	public String getPermissions() {
		return this.permissions;
	}

	public void setPermissions(String permissions) {
		this.permissions = permissions;
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

	
}