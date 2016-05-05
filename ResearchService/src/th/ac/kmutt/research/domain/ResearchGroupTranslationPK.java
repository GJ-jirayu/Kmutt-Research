package th.ac.kmutt.research.domain;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the RESEARCH_GROUP_TRANSLATIONS database table.
 * 
 */
@Embeddable
public class ResearchGroupTranslationPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="RESEARCH_GROUP_ID")
	private Integer researchGroupId;

	private String language;

	public ResearchGroupTranslationPK() {
	}
	public Integer getResearchGroupId() {
		return this.researchGroupId;
	}
	public void setResearchGroupId(Integer researchGroupId) {
		this.researchGroupId = researchGroupId;
	}
	public String getLanguage() {
		return this.language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}

	
}