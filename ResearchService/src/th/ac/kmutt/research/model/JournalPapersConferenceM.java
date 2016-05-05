package th.ac.kmutt.research.model;

import java.io.Serializable;
import java.util.Date;

import th.ac.kmutt.research.xstream.common.ImakeXML;

import com.thoughtworks.xstream.annotations.XStreamAlias;
@XStreamAlias("JournalPapersConferenceM")
public class JournalPapersConferenceM extends ImakeXML implements Serializable {
	private static final long serialVersionUID = 1L;

	
	private Integer journalPapersId;

	private Integer city;
	private String cityName;

	
	private String conferenceName;

	private Integer country;
	private String countryShow;
	
	public String getCountryShow() {
		return countryShow;
	}

	public void setCountryShow(String countryShow) {
		this.countryShow = countryShow;
	}

	private String createdBy;

	
	private Date createdDate;

	
	private Date endDate;
	private String endDateShow;

	public String getEndDateShow() {
		return endDateShow;
	}

	public void setEndDateShow(String endDateShow) {
		this.endDateShow = endDateShow;
	}

	public String getStartDateShow() {
		return startDateShow;
	}

	public void setStartDateShow(String startDateShow) {
		this.startDateShow = startDateShow;
	}

	private String level;
	private String levelShow;

	private String location;

	private String page;

	
	private Date startDate;
	private String startDateShow;
	
	private String updatedBy;

	
	private Date updatedDate;

	private JournalPaperM journalPaper;

	public JournalPapersConferenceM() {
	}

	public Integer getJournalPapersId() {
		return this.journalPapersId;
	}

	public void setJournalPapersId(Integer journalPapersId) {
		this.journalPapersId = journalPapersId;
	}

	public Integer getCity() {
		return this.city;
	}

	public void setCity(Integer city) {
		this.city = city;
	}

	public String getConferenceName() {
		return this.conferenceName;
	}

	public void setConferenceName(String conferenceName) {
		this.conferenceName = conferenceName;
	}

	public Integer getCountry() {
		return this.country;
	}

	public void setCountry(Integer country) {
		this.country = country;
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

	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getLevel() {
		return this.level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getLocation() {
		return this.location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getPage() {
		return this.page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
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

	public JournalPaperM getJournalPaper() {
		return journalPaper;
	}

	public String getLevelShow() {
		return levelShow;
	}

	public void setLevelShow(String levelShow) {
		this.levelShow = levelShow;
	}

	public void setJournalPaper(JournalPaperM journalPaper) {
		this.journalPaper = journalPaper;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
}