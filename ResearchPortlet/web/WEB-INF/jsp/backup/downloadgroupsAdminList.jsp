<%@ include file="/WEB-INF/jsp/include.jsp" %>
<%@page session="false" contentType="text/html" pageEncoding="tis-620" %>
<%--
<script src='<%=renderResponse.encodeURL(renderRequest.getContextPath() + "/js/jquery-1.3.1.min.js")%>'></script>
 --%>

<%@page import="javax.portlet.PortletURL"%>
<link rel="stylesheet" href="<c:url value="/css/css_b.css"/>" type="text/css"/>
<script type="text/javascript">
function <portlet:namespace/>confirmDelete(_urlDelete){ 
	 
		var agree=confirm(" ��ͧ���ź�������������? ");
		//alert(_urlDelete)
		if (agree){
		window.location.href = _urlDelete;
			return true ;
		}
		else{
			return false ;
		}
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
	var ndgNameValue = document.getElementById("ndgName").value;
	return true;
	//alert(ndgNameValue)
	//if(ndgNameValue!=null && ndgNameValue != '' && Value != ' ' ){
		//return true;
	//}
	//else {
		//alert(" ��سҡ�͡��ͤ������Ф���");
		//return false;
	//}			
	
}                                                  
</script>

<portlet:renderURL var="formAction">
    <portlet:param name="action" value="listDownloadGroups"/>
</portlet:renderURL> 

<form:form  modelAttribute="downloadAdminform" method="post"  action="${formAction}">
<form:hidden path="command" id="command"/>
<table class="body_b" width="1000" border="0" align="center" cellpadding="0" cellspacing="0"  >

<tr>
<td style="padding-left:10px;">
</td>
</tr>
<tr>
  <td  align="center" valign="top">
  <table width="100%" border="0" cellspacing="0" cellpadding="0"  height="167">
   <tr>
        <td><img src='<%=renderResponse.encodeURL(renderRequest.getContextPath()+"/images/download.jpg") %>' width="1000" height="120" alt="" /></td>
   </tr>
    <tr>
      <td valign="top"><table width="950" border="0" align="center" cellpadding="20" cellspacing="0">
        <tr>
          <td class="font_News_Catgory" align="left"><p>&gt;  download files</p></td>
          </tr>
          
        <tr>
          <td><table width="917" border="0" align="center" cellpadding="0" cellspacing="0">
            <tr>
              <td><table width="100%" border="0" align="center" cellpadding="5" cellspacing="0">
                <tr>
                  <td width="26%">
                    <form:input path="ndgName" id="ndgName"  size="50" cssStyle="size: 50;width: 200"/>  
                  </td>
                  <td width="9%"> 
                       <input type="image" src='<%=renderResponse.encodeURL(renderRequest.getContextPath()+"/images/sc1.jpg") %>'  width="60" height="21" alt="" onclick="return <portlet:namespace />doAction()"/>
                  </td>
                  <td width="65%" align="right">   
          <a href="<portlet:renderURL>
                      <portlet:param name="action" value="AddEditViewDownloadGroup"/>
                      <portlet:param name="mode" value="add"/>
                      <portlet:param name="ndgId" value="0"/>                    
                      </portlet:renderURL>" title="������Ǵ������ѡ">
             ������Ǵ������ѡ
          </a>
      </td>
                </tr>
                </table></td>
            </tr>
            <tr>
              <td>
			  <table width="100%" border="0" cellspacing="0" cellpadding="0">
                    <tr  style="color:#FFFFFF" class="textsmall12">
                      <td width="150" background='<%=renderResponse.encodeURL(renderRequest.getContextPath()+"/images/approval_02.jpg") %>'><img src='<%=renderResponse.encodeURL(renderRequest.getContextPath()+"/images/approval_01.jpg") %>' alt="" width="13" height="31" align="absmiddle" /><strong>&nbsp;&nbsp;&nbsp;&nbsp;������Ǵ������ѡ</strong></td>
                      <td align="left" background='<%=renderResponse.encodeURL(renderRequest.getContextPath()+"/images/approval_02.jpg") %>'><strong>������Ǵ������ѡ</strong></td>
                      <td width="21" align="right" background='<%=renderResponse.encodeURL(renderRequest.getContextPath()+"/images/approval_02.jpg") %>'><img src='<%=renderResponse.encodeURL(renderRequest.getContextPath()+"/images/approval_03.jpg") %>' width="14" height="31" alt="" /></td>
                    </tr>
                  </table>
			  <table width="924" border="0" align="center" cellpadding="0" cellspacing="0">
                <tr>
                  <td style="border:#0e589e 1px solid"><table width="100%" border="0" cellspacing="0" cellpadding="0">
                    <tr>
                      <td><table width="100%" border="0" cellspacing="0" cellpadding="5" class="textlineblue3">
                        <c:forEach items="${ntcdownloadgroupadmins}" var="ntcdownloadgroup" varStatus="loop">
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
		<td width="113" align="center" bgcolor="${col0}">		
		<a href="<portlet:renderURL>
                         <portlet:param name="action" value="AddEditViewDownloadGroup"/>
                         <portlet:param name="mode" value="view"/>
                         <portlet:param name="ndgId">
                             <jsp:attribute name="value">
                                 <c:out value="${ntcdownloadgroup.ndgId}"/>
                             </jsp:attribute>
                         </portlet:param>
                      </portlet:renderURL>"  class="textlineblue1">
       	<c:out value="${pageObj.startIndex+loop.index+1}" />
       	</a>
       	</td>          
           <td width="557" bgcolor="${col1}">
             <a href="<portlet:renderURL>
                         <portlet:param name="action" value="AddEditViewDownloadGroup"/>
                         <portlet:param name="mode" value="view"/>
                         <portlet:param name="ndgId">
                             <jsp:attribute name="value">
                                 <c:out value="${ntcdownloadgroup.ndgId}"/>
                             </jsp:attribute>
                         </portlet:param>
                      </portlet:renderURL>" class="textlineblue1">
                <c:out value="${ntcdownloadgroup.ndgName}"/>
             </a>
          </td>
          
          <td  width="88" align="center" bgcolor="${col2}">
             <a href="<portlet:renderURL>
                         <portlet:param name="action" value="listDownloadSubGroups"/>
                      	 <portlet:param name="ndgId">
                             <jsp:attribute name="value">
                                <c:out value="${ntcdownloadgroup.ndgId}"/>
                             </jsp:attribute>
                         </portlet:param>
                         <portlet:param name="ndgName">
                             <jsp:attribute name="value">
                                <c:out value="${ntcdownloadgroup.ndgName}"/>
                             </jsp:attribute>
                         </portlet:param>
                      </portlet:renderURL>">
                      ��Ǵ��������                     
                </a>
          </td>
          <td width="63" align="center" bgcolor="${col3}">
             <a href="<portlet:renderURL>
                         <portlet:param name="action" value="AddEditViewDownloadGroup"/>
                      	  <portlet:param name="mode" value="edit"/>
                         <portlet:param name="ndgId">
                             <jsp:attribute name="value">
                                <c:out value="${ntcdownloadgroup.ndgId}"/>
                             </jsp:attribute>
                         </portlet:param>
                      </portlet:renderURL>">
                      ���
                </a>
          </td>
          <td width="44" align="center" bgcolor="${col4}">
          	<span style="cursor: pointer;text-decoration: underline" title="ź"  onclick='return <portlet:namespace />confirmDelete("<portlet:actionURL><portlet:param name="action" value="deleteDownloadGroup"/><portlet:param name="ndgId"><jsp:attribute name="value"><c:out value="${ntcdownloadgroup.ndgId}"/></jsp:attribute></portlet:param></portlet:actionURL>")'>ź</span>
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
                                                                                              	 downloadFormObj =  request.getAttribute("downloadAdminform");
                                                                                                 th.or.ntc.utils.Pagging pagging = (th.or.ntc.utils.Pagging)obj;
                                                                                                 ResearcherMasterForm downloadsForm = (ResearcherMasterForm)downloadFormObj;
                                                                                                 String ndgName = downloadsForm.getNdgName();
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
                                                                                          		urlPrevFirst.setParameter("action","list");
                                                                                          		urlPrevFirst.setParameter("pageNo","1");
                                                                                          		urlPrevFirst.setParameter("ndgName",ndgName);
                                                                                          		
                                                                                          		PortletURL urlPrev = renderResponse.createRenderURL();
                                                                                          		urlPrev.setParameter("action","list");
                                                                                          		urlPrev.setParameter("pageNo",(pagingScript_currentPage-1)+"");
                                                                                          		urlPrev.setParameter("ndgName",ndgName);
                                                                                          		
                                                                                          		PortletURL urlCurrentPage = renderResponse.createRenderURL();
                                                                                          		urlCurrentPage.setParameter("action","list");
                                                                                          		urlCurrentPage.setParameter("pageNo",(pagingScript_currentPage)+"");
                                                                                          		urlCurrentPage.setParameter("ndgName",ndgName);
                                                                                          		
                                                                                          		PortletURL urlNextEnd = renderResponse.createRenderURL();
                                                                                          		urlNextEnd.setParameter("action","list");
                                                                                          		urlNextEnd.setParameter("pageNo",pagingScript_pageCount+"");
                                                                                          		urlNextEnd.setParameter("ndgName",ndgName);
                                                                                          		
                                                                                          		PortletURL urlNext= renderResponse.createRenderURL();
                                                                                          		urlNext.setParameter("action","list");
                                                                                          		urlNext.setParameter("pageNo",(pagingScript_currentPage+1)+"");
                                                                                          		urlNext.setParameter("ndgName",ndgName);
                                                                                          		
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
                                                                                          			url.setParameter("action","list");
                                                                                          			url.setParameter("pageNo",pageRunner+"");
                                                                                          			url.setParameter("ndgName",ndgName);
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
                              <td align="right" class="textlineblue3"> ˹�ҷ�� <%=pagingScript_currentPage %> �ҡ <%=pagingScript_pageCount %>	</td>
                            </tr>
                             <% }%>
                          </table></td>
                        </tr>
                        <% if(pagingScript_recordCount==0){%>
                         <tr>
       <td colspan="5" 
           align="center" class="textlineblue3">��辺������</td>
       
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
        <tr>
          <td></td>
        </tr>
      </table></td>
      </tr>
  </table></td>
  </tr>
</table> 
</form:form>	
