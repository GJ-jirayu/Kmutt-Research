package th.ac.kmutt.research.xstream.common;

import java.io.Serializable;

public class FeedModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7330583357136493263L;
	private String id;
	private String title;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
}
