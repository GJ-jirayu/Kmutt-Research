package th.ac.kmutt.research.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * The primary key class for the UTILIZATION database table.
 * 
 */
@Embeddable
public class UtilizationPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	@JoinColumn(name="RESEARCH_PROJECT_ID")
	private ResearchProject researchProject;
	//bi-directional many-to-one association to ResearchProject
	
	//private Integer researchProjectId;
	
	@Column(name="UTILIZATION_ITEM_LIST")
	private Integer utilizationItemList;

	public UtilizationPK() {
	}
	public ResearchProject getResearchProject() {
		return researchProject;
	}

	public void setResearchProject(ResearchProject researchProject) {
		this.researchProject = researchProject;
	}	
	public Integer getUtilizationItemList() {
		return this.utilizationItemList;
	}
	public void setUtilizationItemList(Integer utilizationItemList) {
		this.utilizationItemList = utilizationItemList;
	}
}