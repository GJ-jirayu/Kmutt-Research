<%@ include file="/WEB-INF/jsp/include.jsp" %>
<%@page session="false" contentType="text/html" pageEncoding="tis-620" %>
<%--
<script src='<%=renderResponse.encodeURL(renderRequest.getContextPath() + "/js/jquery-1.3.1.min.js")%>'></script>
 --%>

<%@page import="javax.portlet.PortletURL"%>
<link rel="stylesheet" href="<c:url value="/css/css_b.css"/>" type="text/css"/>
<script type="text/javascript"
        src='<%= renderResponse.encodeURL(renderRequest.getContextPath()
				+ "/dwr/interface/NTCDownloadsAjax.js") %>'> 
</script>
<script type="text/javascript"
        src='<%= renderResponse.encodeURL(renderRequest.getContextPath()
				+ "/dwr/engine.js") %>'> 
</script>
<script type="text/javascript"
        src='<%= renderResponse.encodeURL(renderRequest.getContextPath()
				+ "/dwr/util.js") %>'> 
</script>
<script type="text/javascript">
function <portlet:namespace/>handleGetData(obj) {
	//alert(obj)
	dwr.util.removeAllOptions('ndgSubIdSelect');
	var data = [ { ndgName:"-- เลือกกลุ่มย่อย --",ndgId:"0"} ];
	dwr.util.addOptions('ndgSubIdSelect', data, 'ndgId', 'ndgName');
	 if(obj!=null){
	  // alert(obj);
	   
	   dwr.util.addOptions('ndgSubIdSelect', obj, 'ndgId', 'ndgName');
	   }
	}
function getGroupList(_parentObj) {
	//alert(_parentObj)
	var _parentId ;
	if(_parentObj!=null){
		for(var i =0 ;i<_parentObj.length;i++){
			if(_parentObj[i].selected){
				_parentId = _parentObj[i].value;
				break;
			}
		}
	}
	//alert(_parentId)
	var p = {
			ndgParent:_parentId
			};

	NTCDownloadsAjax.getGroupList(p,<portlet:namespace/>handleGetData);
  }
function <portlet:namespace/>downloadFile(_hotLink){
	 //alert('download'+_x );
	 	var src = "http://inter.ntc.or.th:10000/NTCDownloadServlet/DownloadServlet?"+_hotLink;
	 	//var src = "http://localhost:8081/NTCDownloadServlet/DownloadServlet?"+_hotLink;
	// 	alert(src)
	    var div = document.createElement("div");
	    document.body.appendChild(div);
	    div.innerHTML = "<iframe width='0' height='0' scrolling='no' frameborder='0' src='" + src + "'></iframe>";
		  // Create an IFRAME.
 	var _form =document.getElementById('downloadViewform');
 	//_form.submit();
	}  
function <portlet:namespace/>chk(ev) {
	var key;
	ev = ev || event;
	key = ev.keyCode;
	if (key == 13) { 
		<portlet:namespace/>doAction();
		return false;
	}
		return true;
}
function <portlet:namespace/>doAction(){ 
	var ndTitleValue = document.getElementById("ndTitle").value;
	return true;
	//alert(ndFileNameValue)
	//if(ndFileNameValue!=null && ndFileNameValue != '' && ndFileNameValue != ' ' ){
		//return true;
	//}
	//else {
		//alert(" กรุณากรอกข้อความที่จะค้นหา");
		//return false;
	//}			
	
}                                                  
</script>

<portlet:actionURL  var="formAction">
    <portlet:param name="action" value="doSubmit"/>
</portlet:actionURL> 

<form:form  modelAttribute="downloadViewform" method="post"  id="downloadViewform" action="${formAction}">
<form:hidden path="command" id="command"/>
 <table  width="980" border="0" align="center" cellpadding="0" cellspacing="0"  style="border:solid 1px #CCCCCC;" >

<tr>
<td style="padding-left:10px;">
</td>
</tr>
<tr>
  <td  align="center" valign="top">
  <table width="100%" border="0" cellspacing="0" cellpadding="0"  height="167">
     <tr>
        <td><img src='<%=renderResponse.encodeURL(renderRequest.getContextPath()+"/images/download.jpg") %>' width="980" height="120" alt="" /></td>
   </tr> <tr>
      <td valign="top"><table width="950" border="0" align="center" cellpadding="5" cellspacing="0">
        <tr>
          <td class="font_News_Catgory" align="left"><p>&gt;  Download files</p></td>
          </tr>
          
        <tr>
          <td><table width="917" border="0" align="center" cellpadding="0" cellspacing="0">
             <tr>
              <td><table width="100%" border="0" align="center" cellpadding="5" cellspacing="0">
                <tr>
                  <td colspan="3" align="left">
                   หมวดหมู่หลัก :<form:select path="ndgIdSelect" id="ndgIdSelect" onchange='getGroupList(this)'>
    												 <form:options items="${groupList}" itemValue="ndgId" itemLabel="ndgName"/>    												
    											</form:select>   
                  </td>
                  
                </tr>
                </table></td>
            </tr>
            <tr>
              <td><table width="100%" border="0" align="center" cellpadding="5" cellspacing="0">
                <tr>
                  <td colspan="3" align="left">
                   หมวดหมู่ย่อย :<form:select path="ndgSubIdSelect" id="ndgSubIdSelect">
    												 <form:options items="${subGroupList}" itemValue="ndgId" itemLabel="ndgName"/>    												
    											</form:select>  
                  </td>
                  
                </tr>
                </table></td>
            </tr>
            <tr>
              <td><table width="100%" border="0" align="center" cellpadding="5" cellspacing="0">
                <tr>
                  <td width="26%">
                    <form:input path="ndTitle" id="ndTitle"   size="50" cssStyle="size: 50;width: 200"/>  
                  </td>
                  <td width="9%"> 
                       <input type="image" src='<%=renderResponse.encodeURL(renderRequest.getContextPath()+"/images/sc1.jpg") %>'  width="60" height="21" alt="" onclick="return <portlet:namespace />doAction()"/>
                  </td>
                  <td width="65%">&nbsp;</td>
                </tr>
                </table></td>
            </tr>
            <tr>
              <td>
			  <table width="100%" border="0" cellspacing="0" cellpadding="0">
                    <tr  style="color:#FFFFFF" class="textsmall12">
                      <td width="184" background='<%=renderResponse.encodeURL(renderRequest.getContextPath()+"/images/approval_02.jpg") %>'><img src='<%=renderResponse.encodeURL(renderRequest.getContextPath()+"/images/approval_01.jpg") %>' alt="" width="13" height="31" align="absmiddle" /><strong>&nbsp;&nbsp;&nbsp;&nbsp;ชื่อหัวข้อ</strong></td>
                      <td width="207" colspan="2" align="left" background='<%=renderResponse.encodeURL(renderRequest.getContextPath()+"/images/approval_02.jpg") %>'><strong>ชื่อไฟล์/URL</strong></td>
                      <td width="88" colspan="2" align="left" background='<%=renderResponse.encodeURL(renderRequest.getContextPath()+"/images/approval_02.jpg") %>'><strong>หมวดหมู่หลัก</strong></td>
                      <td width="88" colspan="2" align="left" background='<%=renderResponse.encodeURL(renderRequest.getContextPath()+"/images/approval_02.jpg") %>'><strong>หมวดหมู่ย่อย</strong></td>
                      <td width="88" align="left" background='<%=renderResponse.encodeURL(renderRequest.getContextPath()+"/images/approval_02.jpg") %>'><strong>ขนาดไฟล์</strong></td>
                      <td width="162" colspan="2" align="left" background='<%=renderResponse.encodeURL(renderRequest.getContextPath()+"/images/approval_02.jpg") %>'><strong>จำนวนครั้งที่ดาวน์โหลด</strong></td>
                      <td width="14" align="right" background='<%=renderResponse.encodeURL(renderRequest.getContextPath()+"/images/approval_02.jpg") %>'><img src='<%=renderResponse.encodeURL(renderRequest.getContextPath()+"/images/approval_03.jpg") %>' width="14" height="31" alt="" /></td>
                    </tr>
                  </table>
			  <table width="924" border="0" align="center" cellpadding="0" cellspacing="0">
                <tr>
                  <td style="border:#0e589e 1px solid"><table width="100%" border="0" cellspacing="0" cellpadding="0">
                    <tr>
                      <td><table width="100%" border="0" cellspacing="0" cellpadding="5" class="textlineblue3">
                        <c:forEach items="${ntcdownloadviews}" var="vwntcdownload" varStatus="loop">
      <tr> 
       <c:set value="${pageObj.startIndex+loop.index}" var="page"/>   
       <c:choose>
    	 <c:when test="${loop.index mod 2 == 0}">
    		<c:set value="#cfcfcf" var="col0"/>
    		<c:set value="#e6e6e6" var="col1"/>
    		<c:set value="#cfcfcf" var="col2"/>
    		<c:set value="#e6e6e6" var="col3"/>
    		<c:set value="#cfcfcf" var="col4"/>     			
    	 </c:when>
    	 <c:otherwise>
    		<c:set value="#E5E5E5" var="col0"/>
    		<c:set value="#FFFFFF" var="col1"/>
    		<c:set value="#d8d8d8" var="col2"/>
    		<c:set value="#f0f0f0" var="col3"/>
    		<c:set value="#d8d8d8" var="col4"/>    			
    	 </c:otherwise>   
		</c:choose>
		<td width="163" align="left" bgcolor="${col0}" class="textsmall12">&nbsp;&nbsp;
		<a href="<portlet:renderURL>
                         <portlet:param name="action" value="viewDownload"/>
                         <portlet:param name="mode" value="edit"/>
                         <portlet:param name="ndId">
                             <jsp:attribute name="value">
                                 <c:out value="${vwntcdownload.ndId}"/>
                             </jsp:attribute>
                         </portlet:param>
                         <portlet:param name="ndgId">
                             <jsp:attribute name="value">
                                <c:out value="${downloadViewform.ndgIdSelect}"/>
                             </jsp:attribute>
                         </portlet:param>
                          <portlet:param name="ndgSubId">
                             <jsp:attribute name="value">
                                <c:out value="${downloadViewform.ndgSubIdSelect}"/>
                             </jsp:attribute>
                         </portlet:param>
                         <portlet:param name="ndTitle">
                             <jsp:attribute name="value">
                                <c:out value="${downloadViewform.ndTitle}"/>
                             </jsp:attribute>
                         </portlet:param>                         
                      </portlet:renderURL>"  class="textlineblue1">
       	<c:out value="${vwntcdownload.ndTitle}" />
       	</a>
       	</td>          
           <td width="100" bgcolor="${col1}" colspan="2" align="left" class="textsmall12">&nbsp;&nbsp;
             <a href="<portlet:renderURL>
                         <portlet:param name="action" value="viewDownload"/>
                         <portlet:param name="mode" value="edit"/>
                         <portlet:param name="ndId">
                             <jsp:attribute name="value">
                                 <c:out value="${vwntcdownload.ndId}"/>
                             </jsp:attribute>
                         </portlet:param>
                         <portlet:param name="ndgId">
                             <jsp:attribute name="value">
                                <c:out value="${downloadViewform.ndgIdSelect}"/>
                             </jsp:attribute>
                         </portlet:param>
                          <portlet:param name="ndgSubId">
                             <jsp:attribute name="value">
                                <c:out value="${downloadViewform.ndgSubIdSelect}"/>
                             </jsp:attribute>
                         </portlet:param>
                         <portlet:param name="ndTitle">
                             <jsp:attribute name="value">
                                <c:out value="${downloadViewform.ndTitle}"/>
                             </jsp:attribute>
                         </portlet:param> 
                      </portlet:renderURL>" class="textlineblue1"> 
                      		<c:out value="${vwntcdownload.ndFileName}"/></a>                
             
              		<c:if test="${vwntcdownload.ndAlert == 'Page Can not be found'}">
                       		  &nbsp;&nbsp;<font color="red">Page Can not be found</font>						
					  </c:if> 
          </td> 
          <td>
            <c:out value="${vwntcdownload.ndgName}"/> </td> 
             <td   align="center" bgcolor="${col4}" >
                   <c:out value="${vwntcdownload.ndgSubName}"/></td> 
           <td width="124" bgcolor="${col2}" >
             <a href="<portlet:renderURL>
                         <portlet:param name="action" value="viewDownload"/>
                         <portlet:param name="mode" value="edit"/>
                         <portlet:param name="ndId">
                             <jsp:attribute name="value">
                                 <c:out value="${vwntcdownload.ndId}"/>
                             </jsp:attribute>
                         </portlet:param>
                         <portlet:param name="ndgId">
                             <jsp:attribute name="value">
                                <c:out value="${downloadViewform.ndgIdSelect}"/>
                             </jsp:attribute>
                         </portlet:param>
                          <portlet:param name="ndgSubId">
                             <jsp:attribute name="value">
                                <c:out value="${downloadViewform.ndgSubIdSelect}"/>
                             </jsp:attribute>
                         </portlet:param>
                         <portlet:param name="ndTitle">
                             <jsp:attribute name="value">
                                <c:out value="${downloadViewform.ndTitle}"/>
                             </jsp:attribute>
                         </portlet:param>
                      </portlet:renderURL>" class="textlineblue1">
                      <c:if test="${vwntcdownload.ndType == 2}">
                       		  <c:set value="${vwntcdownload.ndFileSize/1024}" var="fileSize"></c:set>
                       		  <jsp:useBean id="fileSize"  type="java.lang.Double" />
								<%
								//float p = (float)Math.pow(10,2);
								//fileSize = fileSize * p;
								  float tmp = Math.round(fileSize.doubleValue()*10)/10;
								  //float temp = (float)tmp/p;
							//	double  fileSizeShow=Math.round(temp);
								out.println(tmp+" KB");
								//out.println("  "+fileSize+" KB");
								%>   						
					  </c:if> 
             </a>
          </td> 
           <td width="44" bgcolor="${col3}" colspan="2" align="center">
             <a href="<portlet:renderURL>
                         <portlet:param name="action" value="viewDownload"/>
                         <portlet:param name="mode" value="edit"/>
                         <portlet:param name="ndId">
                             <jsp:attribute name="value">
                                 <c:out value="${vwntcdownload.ndId}"/>
                             </jsp:attribute>
                         </portlet:param>
                         <portlet:param name="ndgId">
                             <jsp:attribute name="value">
                                <c:out value="${downloadViewform.ndgIdSelect}"/>
                             </jsp:attribute>
                         </portlet:param>
                          <portlet:param name="ndgSubId">
                             <jsp:attribute name="value">
                                <c:out value="${downloadViewform.ndgSubIdSelect}"/>
                             </jsp:attribute>
                         </portlet:param>
                         <portlet:param name="ndTitle">
                             <jsp:attribute name="value">
                                <c:out value="${downloadViewform.ndTitle}"/>
                             </jsp:attribute>
                         </portlet:param>
                      </portlet:renderURL>" class="textlineblue1">
               	<c:out value="${vwntcdownload.ndCount}"/>
             </a>
          </td> 
          <td width="44" align="center"  bgcolor="${col4}">
             <img src='<%=renderResponse.encodeURL(renderRequest.getContextPath()+"/images/load.png") %>' width="14" height="14" alt="" style="cursor: pointer;" title="Download file"
             onclick='<portlet:namespace />downloadFile("<c:out value="${vwntcdownload.ndHotlink}"/>")'/>             
          </td>          
       </tr>
    </c:forEach>
    </table>
    </td>
    </tr>
     <tr>
                      <td><table width="100%" border="0" cellspacing="0" cellpadding="5">
                        <tr>
                          <td width="10%" class="textlineblue1"></td>
                          <td width="90%"><table width="100%"  border="0" cellspacing="0" cellpadding="0">
                            <tr>
                              <td><table width="158" border="0" align="right" cellpadding="2" cellspacing="5">
                            <tr class="font_news_upate">
                            <!-- 
                            <td>
                             -->
                            <c:set value="${pageObj}" var="page"/>        
       <%
               	Object obj = null;
                                                                                                 Object downloadFormObj = null; 
                                                                                              	 obj =  request.getAttribute("pageObj");
                                                                                              	 downloadFormObj =  request.getAttribute("downloadViewform");
                                                                                                 th.or.ntc.utils.Pagging pagging = (th.or.ntc.utils.Pagging)obj;
                                                                                                 ResearcherMasterForm downloadForm = (ResearcherMasterForm)downloadFormObj;
                                                                                                 String ndTitle = downloadForm.getNdTitle();
                                                                                                 String ndgIdSelect =  downloadForm.getNdgIdSelect();
                                                                                                 String ndgSubIdSelect = downloadForm.getNdgSubIdSelect();
                                                                                                 int PAGE_BETWEEN = 3;
                                                                                                  int pagingScript_currentPage=pagging.getPageNo();
                                                                                          		int pagingScript_recordCount=pagging.getTotalRecord();
                                                                                          		int pagingScript_recordPerPage=pagging.getPageSize();//RECORD_PERPAGE;
                                                                                          		
                                                                                          		int plus = pagingScript_recordCount%pagingScript_recordPerPage!=0?1:0;
                                                                                          		int  pagingScript_pageCount=new Double(Math.floor((pagingScript_recordCount)/pagingScript_recordPerPage)).intValue()+plus;
                                                                                           
                                                                                          		int startIndex =1;
                                                                                          		int endIndex = 0;
                                                                                          		if(pagingScript_currentPage-PAGE_BETWEEN>0){
                                                                                          	if(pagingScript_pageCount==pagingScript_currentPage)
                                                                                          		startIndex = pagingScript_currentPage- 2;//(PAGE_BETWEEN-1);
                                                                                          	else			 
                                                                                          		startIndex = pagingScript_currentPage- 1;//(PAGE_BETWEEN-1);
                                                                                          		} 
                                                                                          		if(pagingScript_pageCount>=(startIndex+3)){
                                                                                          	endIndex = startIndex+2;//(3-1);
                                                                                          		}else{
                                                                                          	endIndex = pagingScript_pageCount;
                                                                                          		}
                                                                                          		PortletURL urlPrevFirst = renderResponse.createRenderURL();
                                                                                          		urlPrevFirst.setParameter("action","listFiles");
                                                                                          		urlPrevFirst.setParameter("pageNo","1");
                                                                                          		urlPrevFirst.setParameter("ndTitle",ndTitle);
                                                                                          		urlPrevFirst.setParameter("ndgSubId",ndgSubIdSelect);
                                                                                          		urlPrevFirst.setParameter("ndgId",ndgIdSelect); 
                                                                                          		
                                                                                          		PortletURL urlPrev = renderResponse.createRenderURL();
                                                                                          		urlPrev.setParameter("action","listFiles");
                                                                                          		urlPrev.setParameter("pageNo",(pagingScript_currentPage-1)+"");
                                                                                          		urlPrev.setParameter("ndTitle",ndTitle);
                                                                                          		urlPrev.setParameter("ndgSubId",ndgSubIdSelect);
                                                                                          		urlPrev.setParameter("ndgId",ndgIdSelect);
                                                                                          		
                                                                                          		PortletURL urlCurrentPage = renderResponse.createRenderURL();
                                                                                          		urlCurrentPage.setParameter("action","listFiles");
                                                                                          		urlCurrentPage.setParameter("pageNo",(pagingScript_currentPage)+"");
                                                                                          		urlCurrentPage.setParameter("ndTitle",ndTitle);
                                                                                          		urlCurrentPage.setParameter("ndgSubId",ndgSubIdSelect);
                                                                                          		urlCurrentPage.setParameter("ndgId",ndgIdSelect);
                                                                                          		
                                                                                          		PortletURL urlNextEnd = renderResponse.createRenderURL();
                                                                                          		urlNextEnd.setParameter("action","listFiles");
                                                                                          		urlNextEnd.setParameter("pageNo",pagingScript_pageCount+"");
                                                                                          		urlNextEnd.setParameter("ndTitle",ndTitle);
                                                                                          		urlNextEnd.setParameter("ndgSubId",ndgSubIdSelect);
                                                                                          		urlNextEnd.setParameter("ndgId",ndgIdSelect);
                                                                                          		
                                                                                          		PortletURL urlNext= renderResponse.createRenderURL();
                                                                                          		urlNext.setParameter("action","listFiles");
                                                                                          		urlNext.setParameter("pageNo",(pagingScript_currentPage+1)+"");
                                                                                          		urlNext.setParameter("ndTitle",ndTitle);
                                                                                          		urlNext.setParameter("ndgSubId",ndgSubIdSelect);
                                                                                          		urlNext.setParameter("ndgId",ndgIdSelect);
                                                                                          		
                                                                                          		String pagingScript_pageListStr=""; 
                                                                                          	 	String pagingScript_pagePrevStr="<td width=\"32\" onclick=\"window.location.href='"+urlPrevFirst.toString()+"'\" align=\"center\" style=\"cursor: pointer;\" bgcolor=\"#999999\">&lt;&lt;</td>"+
                                                                                          	 	"<td width=\"32\" onclick=\"window.location.href='"+urlPrev.toString()+"'\" align=\"center\" style=\"cursor: pointer;\" bgcolor=\"#999999\">&lt;</td>";
                                                                                          	 	
                                                                                          	  
                                                                                          		String pagingScript_pageNextStr="<td width=\"32\" onclick=\"window.location.href='"+urlNext.toString()+"'\" align=\"center\" style=\"cursor: pointer;\" bgcolor=\"#999999\">&gt;</td>"+
                                                                                          		"<td width=\"32\" onclick=\"window.location.href='"+urlNextEnd.toString()+"'\" align=\"center\" style=\"cursor: pointer;\" bgcolor=\"#999999\">&gt;&gt;</td>";	
                                                                                          		  
                                                                                          		String pagingScript_pagePrevFirstStr="<td width=\"32\" onclick=\"window.location.href='"+urlPrevFirst.toString()+"'\" align=\"center\" style=\"cursor: pointer;\" bgcolor=\"#999999\">&lt;&lt;</td>";
                                                                                          		 
                                                                                          		String pagingScript_pageNextEndStr="<td width=\"32\" onclick=\"window.location.href='"+urlNextEnd.toString()+"'\" align=\"center\" style=\"cursor: pointer;\" bgcolor=\"#999999\">&gt;&gt;</td>";
                                                                                            
                                                                                          		String pagingScript_pageListStrReturn="";
                                                                                          		for(int j=startIndex;j<=endIndex;j++)
                                                                                          		{
                                                                                          	 	int pageRunner=j;
                                                                                          		 
                                                                                                   
                                                                                          	if(pageRunner>0 && pageRunner<=pagingScript_pageCount){
                                                                                          		if(pageRunner==pagingScript_currentPage){
                                                                                          		 
                                                                                          				 pagingScript_pageListStr+="<td width=\"32\"  align=\"center\" style=\"cursor: pointer;\" bgcolor=\"#666666\">"+pageRunner+"</td>";
                                                                                          				 	 
                                                                                          		}
                                                                                          		else{		
                                                                                          			PortletURL url = renderResponse.createRenderURL();
                                                                                          			url.setParameter("action","listFiles");
                                                                                          			url.setParameter("pageNo",pageRunner+"");
                                                                                          			url.setParameter("ndTitle",ndTitle);
                                                                                          			url.setParameter("ndgSubId",ndgSubIdSelect);
                                                                                          			url.setParameter("ndgId",ndgIdSelect);
                                                                                          			pagingScript_pageListStr+="<td width=\"32\" onclick=\"window.location.href='"+url.toString()+"'\" align=\"center\" style=\"cursor: pointer;\" bgcolor=\"#999999\">"+pageRunner+"</td>";
                                                                                          		 }
                                                                                          	}
                                                                                          		 
                                                                                          		}
                                                                                           
                                                                                          		if(startIndex ==1){
                                                                                          	if(startIndex==endIndex){
                                                                                          		pagingScript_pageListStr  =  pagingScript_pagePrevFirstStr + pagingScript_pageListStr +pagingScript_pageNextEndStr;
                                                                                          	}else if(pagingScript_pageCount==pagingScript_currentPage){
                                                                                          		pagingScript_pageListStr = pagingScript_pagePrevStr + pagingScript_pageListStr +pagingScript_pageNextEndStr;//+=pagingScript_pageNextStr;
                                                                                          	}else{
                                                                                          		pagingScript_pageListStr  =  pagingScript_pagePrevFirstStr + pagingScript_pageListStr +pagingScript_pageNextStr;
                                                                                          	} 
                                                                                          		} else if(pagingScript_pageCount==pagingScript_currentPage){
                                                                                          	pagingScript_pageListStr = pagingScript_pagePrevStr + pagingScript_pageListStr +pagingScript_pageNextEndStr;//+=pagingScript_pageNextStr;
                                                                                          		}else {
                                                                                          	pagingScript_pageListStr = pagingScript_pagePrevStr + pagingScript_pageListStr +pagingScript_pageNextStr;//+=pagingScript_pageNextStr;
                                                                                          		}
                                                                                          		pageContext.setAttribute("pagingScript_pageListStr",pagingScript_pageListStr);
               %>
         
          <% if(pagingScript_recordCount!=0){%>
        	 <c:out value="${pagingScript_pageListStr}" escapeXml="false"></c:out>
         <%}%> 
                            </tr>
                          </table></td>
                            </tr>
                             <% if(pagingScript_recordCount!=0){%>
                            <tr>
                              <td align="right" class="textlineblue3"> หน้าที่ <%=pagingScript_currentPage %> จาก <%=pagingScript_pageCount %>	</td>
                            </tr>
                             <% }else{%>
                              <tr>
                              <td align="center" class="textlineblue3">ไม่พบข้อมูล</td>
                            </tr>
                             <%}%> 
                          </table></td>
                        </tr>
                        
                      </table></td>
                    </tr>
                  </table></td>
                </tr>
                 
              </table></td>
            </tr>
          </table></td>
        </tr>
        <tr>
          <td></td>
        </tr>
      </table></td>
      </tr>
  </table></td>
  </tr>
</table> 
</form:form>	
