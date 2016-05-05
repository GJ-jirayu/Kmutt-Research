package th.ac.kmutt.research.service.impl;


/**
 * @author Chatchai Pimtun
 */
public class DownloadsServiceImpl extends PostCommon {//implements DownloadsService {
 
/*	public void deleteNtcDownload(String key) {
        // TODO Auto-generated method stub
		NtcDownloadXML ntcDownloadXML = new NtcDownloadXML();
		NtcDownload ntcDownload = new NtcDownload();
		ntcDownload.setNdId(key);
		ntcDownloadXML.setServiceName(ServiceConstant.NTC_DOWNLOAD_DELETE);
		ntcDownloadXML.setNtcDownload(ntcDownload);
		postMessage(ntcDownloadXML,ntcDownloadXML.getClass().getName(),"downloads/",false);
	}

	public void deleteNtcDownloadCount(String key) {
		// TODO Auto-generated method stub
		NtcDownloadXML ntcDownloadXML = new NtcDownloadXML();
		NtcDownloadCount ntcDownloadCount = new NtcDownloadCount();
		ntcDownloadCount.setNdId(key);
		ntcDownloadXML.setServiceName(ServiceConstant.NTC_DOWNLOAD_COUNT_DELETE);
		ntcDownloadXML.setNtcDownloadCount(ntcDownloadCount);
		postMessage(ntcDownloadXML,ntcDownloadXML.getClass().getName(),"downloads/",false);
	}

	public void deleteNtcDownloadGroup(String key) {
		// TODO Auto-generated method stub
		NtcDownloadXML ntcDownloadXML = new NtcDownloadXML();
		NtcDownloadGroup ntcDownloadGroup = new NtcDownloadGroup();
		NtcDownload ntcDownload = new NtcDownload();
		ntcDownloadGroup.setNdgId(key);
		ntcDownloadXML.setServiceName(ServiceConstant.NTC_DOWNLOAD_GROUP_DELETE);
		ntcDownload.setNtcDownloadSubGroup(ntcDownloadGroup);
		ntcDownloadXML.setNtcDownload(ntcDownload);
		postMessage(ntcDownloadXML,ntcDownloadXML.getClass().getName(),"downloads/",false);
	}

	public NtcDownload getNtcDownload(String key) {
		// TODO Auto-generated method stub
		NtcDownloadXML ntcDownloadXML = new NtcDownloadXML();
		NtcDownload ntcDownload = new NtcDownload();
		ntcDownload.setNdId(key);
		ntcDownloadXML.setServiceName(ServiceConstant.NTC_DOWNLOAD_FIND_BY_ID);
		ntcDownloadXML.setNtcDownload(ntcDownload);
		VResultMessage  vresultMessage = postMessage(ntcDownloadXML,ntcDownloadXML.getClass().getName(),"downloads/",true);		
		return (NtcDownload)vresultMessage.getResultListObj().get(0);
	}

	public NtcDownloadCount getNtcDownloadCount(String key) {
		// TODO Auto-generated method stub
		NtcDownloadXML ntcDownloadXML = new NtcDownloadXML();
		NtcDownloadCount ntcDownloadCount = new NtcDownloadCount();
		ntcDownloadCount.setNdId(key);
		ntcDownloadXML.setServiceName(ServiceConstant.NTC_DOWNLOAD_COUNT_FIND_BY_ID);
		ntcDownloadXML.setNtcDownloadCount(ntcDownloadCount);
		VResultMessage  vresultMessage = postMessage(ntcDownloadXML,ntcDownloadXML.getClass().getName(),"downloads/",true);		
		return (NtcDownloadCount)vresultMessage.getResultListObj().get(0);
	}

	public NtcDownloadGroup getNtcDownloadGroup(String key) {
		// TODO Auto-generated method stub
		NtcDownloadXML ntcDownloadXML = new NtcDownloadXML();
		NtcDownload ntcDownload= new NtcDownload();
		NtcDownloadGroup ntcDownloadGroup = new NtcDownloadGroup();
		ntcDownloadGroup.setNdgId(key);
		ntcDownload.setNtcDownloadSubGroup(ntcDownloadGroup);
		ntcDownloadXML.setServiceName(ServiceConstant.NTC_DOWNLOAD_GROUP_FIND_BY_ID);
		ntcDownloadXML.setNtcDownload(ntcDownload);
		VResultMessage  vresultMessage = postMessage(ntcDownloadXML,ntcDownloadXML.getClass().getName(),"downloads/",true);		
		return (NtcDownloadGroup)vresultMessage.getResultListObj().get(0);
	}

	public void saveNtcDownload(NtcDownload ntcDownload) {
		// TODO Auto-generated method stub 
		//ntcFaq.setServiceName(ServiceConstant.FAQ_SAVE);
		NtcDownloadXML ntcDownloadXML = new NtcDownloadXML();
		ntcDownloadXML.setServiceName(ntcDownload.getServiceName());
		ntcDownloadXML.setNtcDownload(ntcDownload);
		postMessage(ntcDownloadXML,ntcDownloadXML.getClass().getName(),"downloads/",false);
	}

	public void saveNtcDownloadCount(NtcDownloadCount ntcDownloadCount) {
		// TODO Auto-generated method stub
		NtcDownloadXML ntcDownloadXML = new NtcDownloadXML();
		ntcDownloadXML.setServiceName(ntcDownloadCount.getServiceName()); 
		ntcDownloadXML.setNtcDownloadCount(ntcDownloadCount);
		postMessage(ntcDownloadXML,ntcDownloadXML.getClass().getName(),"downloads/",false);
	}

	public void saveNtcDownloadGroup(NtcDownloadGroup ntcDownloadGroup) {
		
		// TODO Auto-generated method stub
		NtcDownloadXML ntcDownloadXML = new NtcDownloadXML();
		ntcDownloadXML.setServiceName(ntcDownloadGroup.getServiceName());
		NtcDownload ntcDownload = new NtcDownload();
		ntcDownload.setNtcDownloadSubGroup(ntcDownloadGroup);
		ntcDownloadXML.setNtcDownload(ntcDownload);
		postMessage(ntcDownloadXML,ntcDownloadXML.getClass().getName(),"downloads/",false);
	}

	public VResultMessage searchNtcDownload(VwNtcDownload vwntcDownload) {
		// TODO Auto-generated method stub
		NtcDownloadXML ntcDownloadXML = new NtcDownloadXML();
		ntcDownloadXML.setServiceName(ServiceConstant.NTC_DOWNLOAD_SEARCH);
		ntcDownloadXML.setVwNtcDownload(vwntcDownload);
		VResultMessage  vresultMessage = postMessage(ntcDownloadXML,ntcDownloadXML.getClass().getName(),"downloads/",true); 
		return    vresultMessage ;
	}

	public VResultMessage searchNtcDownloadCount(
			NtcDownloadCount ntcDownloadCount) {
		// TODO Auto-generated method stub
		NtcDownloadXML ntcDownloadXML = new NtcDownloadXML();
		ntcDownloadXML.setServiceName(ServiceConstant.NTC_DOWNLOAD_COUNT_SEARCH);
		ntcDownloadXML.setNtcDownloadCount(ntcDownloadCount);
		VResultMessage  vresultMessage = postMessage(ntcDownloadXML,ntcDownloadXML.getClass().getName(),"downloads/",true); 
		return    vresultMessage ;
	}

	public VResultMessage searchNtcDownloadGroup(
			NtcDownloadGroup ntcDownloadGroup) {
		// TODO Auto-generated method stub
		NtcDownloadXML ntcDownloadXML = new NtcDownloadXML();
		ntcDownloadXML.setServiceName(ServiceConstant.NTC_DOWNLOAD_GROUP_SEARCH);
		NtcDownload ntcDownload = new NtcDownload();
		ntcDownload.setNtcDownloadSubGroup(ntcDownloadGroup);
		ntcDownloadXML.setNtcDownload(ntcDownload);
		VResultMessage  vresultMessage = postMessage(ntcDownloadXML,ntcDownloadXML.getClass().getName(),"downloads/",true); 
		return    vresultMessage ;
	}

	public void deleteNtcDownloadSubGroup(String key) {
		// TODO Auto-generated method stub
		NtcDownloadXML ntcDownloadXML = new NtcDownloadXML();
		NtcDownloadGroup ntcDownloadGroup = new NtcDownloadGroup();
		NtcDownload ntcDownload = new NtcDownload();
		ntcDownloadGroup.setNdgId(key);
		ntcDownloadXML.setServiceName(ServiceConstant.NTC_DOWNLOAD_SUB_GROUP_DELETE);
		ntcDownload.setNtcDownloadSubGroup(ntcDownloadGroup);
		ntcDownloadXML.setNtcDownload(ntcDownload);
		postMessage(ntcDownloadXML,ntcDownloadXML.getClass().getName(),"downloads/",false);
	}

	public NtcDownloadGroup getNtcDownloadSubGroup(String key) {
		// TODO Auto-generated method stub
		NtcDownloadXML ntcDownloadXML = new NtcDownloadXML();
		NtcDownload ntcDownload= new NtcDownload();
		NtcDownloadGroup ntcDownloadGroup = new NtcDownloadGroup();
		ntcDownloadGroup.setNdgId(key);
		ntcDownload.setNtcDownloadSubGroup(ntcDownloadGroup);
		ntcDownloadXML.setServiceName(ServiceConstant.NTC_DOWNLOAD_SUB_GROUP_FIND_BY_ID);
		ntcDownloadXML.setNtcDownload(ntcDownload);
		VResultMessage  vresultMessage = postMessage(ntcDownloadXML,ntcDownloadXML.getClass().getName(),"downloads/",true);		
		return (NtcDownloadGroup)vresultMessage.getResultListObj().get(0);
	}

	public void saveNtcDownloadSubGroup(NtcDownloadGroup ntcDownloadGroup) {
		// TODO Auto-generated method stub
		NtcDownloadXML ntcDownloadXML = new NtcDownloadXML();
		ntcDownloadXML.setServiceName(ntcDownloadGroup.getServiceName());
		NtcDownload ntcDownload = new NtcDownload();
		ntcDownload.setNtcDownloadSubGroup(ntcDownloadGroup);
		ntcDownloadXML.setNtcDownload(ntcDownload);
		postMessage(ntcDownloadXML,ntcDownloadXML.getClass().getName(),"downloads/",false);
	}

	public VResultMessage searchNtcDownloadSubGroup(
			NtcDownloadGroup ntcDownloadGroup) {
		// TODO Auto-generated method stub
		NtcDownloadXML ntcDownloadXML = new NtcDownloadXML();
		ntcDownloadXML.setServiceName(ServiceConstant.NTC_DOWNLOAD_SUB_GROUP_SEARCH);
		NtcDownload ntcDownload = new NtcDownload();
		ntcDownload.setNtcDownloadSubGroup(ntcDownloadGroup);
		ntcDownloadXML.setNtcDownload(ntcDownload);
		VResultMessage  vresultMessage = postMessage(ntcDownloadXML,ntcDownloadXML.getClass().getName(),"downloads/",true); 
		return    vresultMessage ;
	}

	public VResultMessage listNtcDownloadSubGroup(NtcDownloadGroup ntcDownloadGroup) {
		// TODO Auto-generated method stub
		NtcDownloadXML ntcDownloadXML = new NtcDownloadXML();
		ntcDownloadXML.setServiceName(ServiceConstant.NTC_DOWNLOAD_SUB_GROUP_LIST);
		NtcDownload ntcDownload = new NtcDownload();
		ntcDownload.setNtcDownloadSubGroup(ntcDownloadGroup);
		ntcDownloadXML.setNtcDownload(ntcDownload);
		VResultMessage  vresultMessage = postMessage(ntcDownloadXML,ntcDownloadXML.getClass().getName(),"downloads/",true); 
		return    vresultMessage ;
	}

	public VResultMessage listNtcDownloadGroup(NtcDownloadGroup ntcDownloadGroup) {
		// TODO Auto-generated method stub
		NtcDownloadXML ntcDownloadXML = new NtcDownloadXML();
		ntcDownloadXML.setServiceName(ServiceConstant.NTC_DOWNLOAD_GROUP_LIST);
		NtcDownload ntcDownload = new NtcDownload();
		ntcDownload.setNtcDownloadSubGroup(ntcDownloadGroup);
		ntcDownloadXML.setNtcDownload(ntcDownload);
		VResultMessage  vresultMessage = postMessage(ntcDownloadXML,ntcDownloadXML.getClass().getName(),"downloads/",true); 
		return    vresultMessage ;
	}*/


}
