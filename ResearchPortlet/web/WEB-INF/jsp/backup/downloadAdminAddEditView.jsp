<%@ include file="/WEB-INF/jsp/include.jsp" %>
<%@page session="false" contentType="text/html" pageEncoding="tis-620" %>
<link rel="stylesheet" href="<c:url value="/css/css_b.css"/>" type="text/css"/>
<script>
function <portlet:namespace />doAction(_command,_mode){
//alert(_command+","+_mode);
var nfaqId = document.getElementById("nfaqId");
var command = document.getElementById("command");

//alert(nfaqId.value+","+command.value);
command.value=_command;
var agree ;
//alert(_urlDelete)
if(_mode == 'edit')
agree = confirm(" ต้องการแก้ไขข้อมูลหรือไม่? ");
else
agree = confirm(" ต้องการเพิ่มข้อมูลหรือไม่? ");
if (agree){
//window.location.href = _urlDelete;
	return true ;
}
else{
	return false ;
} 
	return true;//false;
}
</script>
<portlet:actionURL var="formAction">
    <portlet:param name="action" value="doSubmit"/>
</portlet:actionURL> 
<table class="body_b" width="1000" border="0" align="center" cellpadding="0" cellspacing="0"  > 
<form:form  modelAttribute="faqAdminform" method="post" action="${formAction}">
<form:hidden path="ntcfaq.nfaqId" id="nfaqId"/>
<form:hidden path="ntcfaq.nfaqLevel"/>
<form:hidden path="ntcfaq.nfaqRef"/>
<form:hidden path="command" id="command"/> 
<tr>
<td >
</td>
</tr>
<tr>
  <td  align="center" valign="top">
  <table width="100%" border="0" cellspacing="0" cellpadding="0"  height="167">
   <tr>
          <td><img src='<%=renderResponse.encodeURL(renderRequest.getContextPath()+"/images/faqs1.jpg") %>' width="1000" height="120" alt="" /></td>
        </tr>
    <tr>
      <td valign="top"><table width="950" border="0" align="center" cellpadding="20" cellspacing="0">
        <tr>
          <td class="font_News_Catgory">&gt;  FAQs</td>
          </tr>
          
        <tr>
          <td><table width="917" border="0" align="center" cellpadding="0" cellspacing="0">
            <tr>
              <td>&nbsp;</td>
            </tr>
            <tr>
              <td><table width="800" border="0" align="center" cellpadding="0" cellspacing="0">
                <tr>
                  <td width="13"><img src='<%=renderResponse.encodeURL(renderRequest.getContextPath()+"/images/approval_01.jpg") %>'  width="13" height="31" alt="" /></td>
                  
<c:if test="${faqAdminform.mode=='add'}">
    <c:set value="" var="nfaqNameShow"/> 
</c:if>
<c:if test="${faqAdminform.mode=='edit'}">
	<c:set value="${faqAdminform.nfaqName}" var="nfaqNameShow"/>
</c:if>
<c:if test="${faqAdminform.mode=='view'}">
	<c:set value="${faqAdminform.nfaqName}" var="nfaqNameShow"/> 
</c:if>
                  <td width="773" class="textsmall12" style='background:url("<%=renderResponse.encodeURL(renderRequest.getContextPath()+"/images/approval_02.jpg") %>") bottom repeat-x; color:#FFFFFF'><p><br />
                  </p></td>
                  <td width="14"><img src='<%=renderResponse.encodeURL(renderRequest.getContextPath()+"/images/approval_03.jpg") %>' width="14" height="31" alt="" /></td>
                </tr>
                <tr>
                  <td colspan="3" style="border:#0e589e 1px solid"><table width="100%" border="0" cellspacing="0" cellpadding="0">
                    <tr>
                      <td><table width="100%" border="0" cellspacing="0" cellpadding="5" class="textlineblue3"> 
                          <tr>
                            <td width="15%" align="center" bgcolor="#F0F0F0" class="font_topic_detail">หมวดหมู่</td>
                            <td width="85%" align="left" bgcolor="#FCFCFC" class="font_topic_detail">
                            <table width="655" border="0" cellspacing="5" cellpadding="0">
                                <tr>
                                  <td width="655"><table width="100%" border="0" cellspacing="0" cellpadding="0">
                                      <tr>
                                        <td>
										  <c:if test="${faqAdminform.mode=='add' || faqAdminform.mode=='edit'}">
    												<form:input path="ntcfaq.nfaqName"/>
    									  </c:if>
										  <c:if test="${faqAdminform.mode=='view'}">
    											<c:out value="${faqAdminform.ntcfaq.nfaqName}"></c:out>
										  </c:if>
										 </td>
                                      </tr>
                                  </table></td>
                                </tr> 
                            </table></td>
                          </tr> 
                      </table></td>
                    </tr>
                    <tr>
                      <td><table width="100%" border="0" cellspacing="0" cellpadding="5">
                          <tr>
                            <td width="15%" class="textlineblue1">
                            <a href="<portlet:renderURL>
            					<portlet:param name="action" value="list"/>
         						</portlet:renderURL>">
    							&lt;&lt; กลับไป
							</a>
                           </td>
                            <td width="85%" align="center"><table width="15%" border="0" cellspacing="0" cellpadding="5">
                                <tr> 
                               <td  colspan="2" align="center">
                                  <c:if test="${faqAdminform.mode=='add' || faqAdminform.mode=='edit'}"> 
                                      <label>
                                  		<input type="image" src='<%=renderResponse.encodeURL(renderRequest.getContextPath()+"/images/ok.jpg") %>'    alt="" onclick="return <portlet:namespace />doAction('doSaveFAQ','${faqAdminform.mode}')"/>
                                      </label>
                                  </c:if>
                                  <c:if test="${faqAdminform.mode=='view'}">
                                  	  <label>
                                  		<input type="image" src='<%=renderResponse.encodeURL(renderRequest.getContextPath()+"/images/ok.jpg") %>'    alt=""/>
                                      </label>
    							  </c:if>                            
                                  </td> 
                                </tr>
                            </table></td>
                          </tr>
                      </table></td>
                    </tr>
                  </table>                    <br>				  </td>
                </tr>
              </table>
			  
			  </td>
            </tr>
          </table></td>
        </tr>
        <tr>
          <td>&nbsp;</td>
        </tr>
      </table></td>
      </tr>
  </table></td>
  </tr> 
</form:form>
</table> 
