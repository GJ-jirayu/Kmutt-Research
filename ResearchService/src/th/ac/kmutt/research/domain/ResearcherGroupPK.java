package th.ac.kmutt.research.domain;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the RESEARCHER_GROUP database table.
 * 
 */
@Embeddable
public class ResearcherGroupPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="RESEARCH_GROUP_ID", insertable=false, updatable=false)
	private Integer researchGroupId;

	@Column(name="RESEARCHER_ID")
	private Integer researcherId;

	public ResearcherGroupPK() {
	}
	public Integer getResearchGroupId() {
		return this.researchGroupId;
	}
	public void setResearchGroupId(Integer researchGroupId) {
		this.researchGroupId = researchGroupId;
	}
	public Integer getResearcherId() {
		return this.researcherId;
	}
	public void setResearcherId(Integer researcherId) {
		this.researcherId = researcherId;
	}

}