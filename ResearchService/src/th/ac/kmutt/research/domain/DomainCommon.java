package th.ac.kmutt.research.domain;

import java.io.Serializable;
import java.util.HashMap;

import javax.persistence.Transient;

public class DomainCommon implements Serializable {
	private static final long serialVersionUID = 1L;
	@Transient	
	private String updateType;
	@Transient	
	private String tab;
	@Transient	
	private String filter;
	
	@Transient	
	private HashMap<String, String> docAssignMapping;
	
	@Transient	
	private Boolean isdocAssign;
	public String getUpdateType() {
		return updateType;
	}
	public void setUpdateType(String updateType) {
		this.updateType = updateType;
	}
	public String getTab() {
		return tab;
	}
	public void setTab(String tab) {
		this.tab = tab;
	}
	public String getFilter() {
		return filter;
	}
	public void setFilter(String filter) {
		this.filter = filter;
	}
	public HashMap<String, String> getDocAssignMapping() {
		return docAssignMapping;
	}
	public void setDocAssignMapping(HashMap<String, String> docAssignMapping) {
		this.docAssignMapping = docAssignMapping;
	}
	public Boolean getIsdocAssign() {
		return isdocAssign;
	}
	public void setIsdocAssign(Boolean isdocAssign) {
		this.isdocAssign = isdocAssign;
	}
	
	
}
