<%@ include file="/WEB-INF/jsp/include.jsp" %>
<%@page session="false" contentType="text/html" pageEncoding="tis-620" %>
<link rel="stylesheet" href="<c:url value="/css/css_b.css"/>" type="text/css"/>
<script>
String.prototype.trim = function() {
	return this.replace(/^\s+|\s+$/g,"");
}
function <portlet:namespace />doAction(_command,_mode){
//alert(_command+","+_mode);
var ndgId = document.getElementById("ndgId");
var command = document.getElementById("command");
var _ndgName = document.getElementById("ntcDownloadGroup.ndgName");
if(_ndgName.value.trim().length==0){
  	 alert("กรุณากรอก หมวดหมู่หลัก");
  	 return false;
  }
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
<form:form  modelAttribute="downloadAdminform" method="post" action="${formAction}">
<form:hidden path="ntcDownloadGroup.ndgId" id="ndgId"/>
<form:hidden path="ntcDownloadGroup.ndgCode"/>
<form:hidden path="ntcDownloadGroup.ndgLevel"/>
<form:hidden path="ntcDownloadGroup.ndgParent"/>
<form:hidden path="command" id="command"/> 
<tr>
<td >
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
              <td>&nbsp;</td>
            </tr>
            <tr>
              <td><table width="800" border="0" align="center" cellpadding="0" cellspacing="0">
                <tr>
                  <td width="13" valign="bottom"><img src='<%=renderResponse.encodeURL(renderRequest.getContextPath()+"/images/approval_01.jpg") %>'  width="13" height="31" alt="" /></td>
             
                  <td width="773" class="textsmall12" style='background:url("<%=renderResponse.encodeURL(renderRequest.getContextPath()+"/images/approval_02.jpg") %>") bottom repeat-x; color:#FFFFFF'><p><br />
                  </p></td>
                  <td width="14"  valign="bottom"><img src='<%=renderResponse.encodeURL(renderRequest.getContextPath()+"/images/approval_03.jpg") %>' width="14" height="31" alt="" /></td>
                </tr>
                <tr>
                  <td colspan="3" style="border:#0e589e 1px solid"><table width="100%" border="0" cellspacing="0" cellpadding="0">
                    <tr>
                      <td><table width="100%" border="0" cellspacing="0" cellpadding="5" class="textlineblue3"> 
                          <tr>
                            <td width="40%" align="right" bgcolor="#F0F0F0" class="font_topic_detail">หมวดหมู่หลัก(*) :</td>
                            <td width="60%" align="left" bgcolor="#FCFCFC" class="textlineblue3">
                            <table width="550" border="0" cellspacing="5" cellpadding="0">
                                <tr>
                                  <td width="550"><table width="100%" border="0" cellspacing="0" cellpadding="0">
                                      <tr>
                                        <td>
										  <c:if test="${downloadAdminform.mode=='add' || downloadAdminform.mode=='edit'}">
    												<form:input path="ntcDownloadGroup.ndgName"/>
    									  </c:if>
										  <c:if test="${downloadAdminform.mode=='view'}">
    											&nbsp;<c:out value="${downloadAdminform.ntcDownloadGroup.ndgName}"></c:out>
										  </c:if>
										 </td>
                                      </tr> 
                                  </table></td>
                                </tr> 
                            </table></td>
                          </tr>
                           <tr>
                            <td valign="top" width="40%" align="right" bgcolor="#F0F0F0" class="font_topic_detail">รายละเอียดและคำอธิบาย :</td>
                            <td width="60%" align="left" bgcolor="#FCFCFC" class="textlineblue3">
                            <table width="550" border="0" cellspacing="5" cellpadding="0">
                                <tr>
                                  <td width="550"><table width="100%" border="0" cellspacing="0" cellpadding="0">
                                      <tr>
                                        <td>
										  <c:if test="${downloadAdminform.mode=='add' || downloadAdminform.mode=='edit'}">
    												<form:textarea path="ntcDownloadGroup.ndgDesc" cssStyle="width:300px;"/>
    									  </c:if>
										  <c:if test="${downloadAdminform.mode=='view'}">
    											&nbsp;<c:out value="${downloadAdminform.ntcDownloadGroup.ndgDesc}"></c:out>
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
                            <td width="85%" align="center"><table width="15%" border="0" cellspacing="0" cellpadding="5">
                                <tr> 
                               <td  colspan="2" align="center">
                                  <c:if test="${downloadAdminform.mode=='add' || downloadAdminform.mode=='edit'}"> 
                                      <label>
                                  		<input type="image" src='<%=renderResponse.encodeURL(renderRequest.getContextPath()+"/images/ok.jpg") %>'    alt="" onclick="return <portlet:namespace />doAction('doSaveDownloadGroup','${downloadAdminform.mode}')"/>
                                  	  </label>
                                  </c:if>
                                  <c:if test="${downloadAdminform.mode=='view'}">
                                  	  <label>
                                  		
                                      </label>
    							  </c:if>                            
                                  </td> 
                                </tr>
                            </table></td>
                            <td width="15%" class="textlineblue1" align="left">
                            
                           </td>
                          </tr>
                          <tr> 
                            <td width="85%" align="left" colspan="3"><table width="15%" border="0" cellspacing="0" cellpadding="5">
                              
                                                   
                            </table></td>                            
                          </tr>
                           
                      </table></td>
                    </tr>
                  </table>    <br>				  </td>
                </tr>
                 <tr> 
                               <td  colspan="2" align="left">
                                  <a href="<portlet:renderURL>
            					<portlet:param name="action" value="list"/>
         						</portlet:renderURL>">
    							&lt;&lt; กลับไปหมวดหมู่หลัก
							</a>                            
                                  </td> 
                                </tr> 
              </table>
			  
			  </td>
            </tr>
            
          </table></td>
        </tr>
        <tr>
          <td>&nbsp;
        </tr>
      </table></td>
      </tr>
  </table></td>
  </tr> 
</form:form>
</table> 
