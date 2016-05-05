package th.ac.kmutt.research.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the UTILIZATION_DOCUMENT database table.
 * 
 */
@Entity
@Table(name="UTILIZATION_DOCUMENT")
@NamedQuery(name="UtilizationDocument.findAll", query="SELECT u FROM UtilizationDocument u")
public class UtilizationDocument implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private UtilizationDocumentPK id;

	@Column(name="CREATED_BY")
	private String createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CREATED_DATE")
	private Date createdDate;

	private String description;

	@Column(name="FILE_NAME")
	private String fileName;

	@Column(name="FILE_PATH")
	private String filePath;

	@Column(name="REF_HOTLINK")
	private String refHotlink;

	@Column(name="UPDATED_BY")
	private String updatedBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="UPDATED_DATE")
	private Date updatedDate;

	//bi-directional many-to-one association to Utilization
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="RESEARCH_PROJECT_ID", referencedColumnName="RESEARCH_PROJECT_ID",insertable=false,updatable=false),
		@JoinColumn(name="UTILIZATION_ITEM_LIST", referencedColumnName="UTILIZATION_ITEM_LIST",insertable=false,updatable=false)
		})
	private Utilization utilization;

	public UtilizationDocument() {
	}

	public UtilizationDocumentPK getId() {
		return this.id;
	}

	public void setId(UtilizationDocumentPK id) {
		this.id = id;
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

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getFileName() {
		return this.fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFilePath() {
		return this.filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getRefHotlink() {
		return this.refHotlink;
	}

	public void setRefHotlink(String refHotlink) {
		this.refHotlink = refHotlink;
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

	public Utilization getUtilization() {
		return this.utilization;
	}

	public void setUtilization(Utilization utilization) {
		this.utilization = utilization;
	}

}