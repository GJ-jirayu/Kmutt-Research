<%@ include file="/WEB-INF/jsp/include.jsp" %>
<%@page session="false" contentType="text/html" pageEncoding="tis-620" %>
<link rel="stylesheet" href="<c:url value="/css/css_b.css"/>" type="text/css"/>
 
<script> 
function <portlet:namespace/>downloadFile(_hotLink){
	 //alert('download'+_x );
	 	var src = "http://172.17.1.109:10000/NTCDownloadServlet/DownloadServlet?"+_hotLink+"&mode=adminview";
		//alert(src)
	    var div = document.createElement("div");
	    document.body.appendChild(div);
	    div.innerHTML = "<iframe width='0' height='0' scrolling='no' frameborder='0' src='" + src + "'></iframe>";
		  // Create an IFRAME.

	} 
</script>
<portlet:actionURL var="formAction">
    <portlet:param name="action" value="doSubmit"/>
</portlet:actionURL> 
<table class="body_b" width="1000" border="0" align="center" cellpadding="0" cellspacing="0"  > 
<form:form  modelAttribute="downloadViewform" method="post"  name="downloadViewform" action="${formAction}">                  
<form:hidden path="command" id="command"/> 
<tr>
<td >
</td>
</tr>
<tr>
  <td  align="center" valign="top">
    <table width="100%" border="0" cellspacing="0" cellpadding="0"  height="167">
   <tr>
          <td><img src="image_new/download.jpg" width="1000" height="120" alt="" /></td>
        </tr>
    <tr>
      <td valign="top"><table width="950" border="0" align="center" cellpadding="20" cellspacing="0">
        <tr>
          <td class="font_News_Catgory" align="left"><p>&gt;  download files</p></td>
          </tr>
          
        <tr>
          <td><table width="917" border="0" align="center" cellpadding="0" cellspacing="0">
             <tr>
              <td><table width="917" border="0" align="center" cellpadding="0" cellspacing="0">
                <tr>
                  <td>&nbsp;</td>
                  </tr>
                <tr>
                  <td style="border:#0e589e 1px solid"><table width="100%" border="0" cellspacing="0" cellpadding="5">
                    <tr>
                      <td width="20%" align="right" bgcolor="#EFEFEF" class="textlineblue3"><strong>หมวดหมู่หลัก</strong></td>
                      <td width="80%" bgcolor="#EFEFEF" class="Font_top2">:  <c:out value="${downloadViewform.vwntcDownload.ndgName}"/></td>
                    </tr>
                    <tr>
                      <td align="right" class="textlineblue3"><strong>หมวดหมู่ย่อย </strong></td>
                      <td class="Font_top2">:  <c:out value="${downloadViewform.vwntcDownload.ndgSubName}"/></td>
                    </tr>
                    <tr>
                      <td align="right" bgcolor="#EFEFEF" class="textlineblue3"><strong>ชื่อหัวข้อ</strong></td>
                      <td bgcolor="#EFEFEF" class="Font_top2">:  <c:out value="${downloadViewform.vwntcDownload.ndTitle}"/></td>
                    </tr>
                    <tr>
                      <td align="right" class="textlineblue3"><strong>ชื่อไฟล์ / URL</strong></td>
                      <td class="Font_top2">:  <c:out value="${downloadViewform.vwntcDownload.ndFileName}"/></td>
                    </tr>
                    <tr>
                      <td align="right" bgcolor="#EFEFEF" class="textlineblue3"><strong>รายละเอียดและคำอธิบาย</strong></td>
                      <td bgcolor="#EFEFEF" class="Font_top2">:  <c:out value="${downloadViewform.vwntcDownload.ndDetail}"/></td>
                    </tr>
                    <tr>
                      <td align="right" class="textlineblue3"><strong>ขนาดไฟล์</strong></td>
                      <td class="Font_top2">:   <c:if test="${downloadViewform.vwntcDownload.ndType == 2}">
                       		  <c:set value="${downloadViewform.vwntcDownload.ndFileSize/1024}" var="fileSize"></c:set>
                       		  <jsp:useBean id="fileSize"  type="java.lang.Double" />
								<% 
								  float tmp = Math.round(fileSize.doubleValue()*10)/10; 
								out.println(tmp+" KB."); 
								%>   						
					  </c:if> </td>
                    </tr>
                    <tr>
                      <td align="right" bgcolor="#EFEFEF" class="textlineblue3"><strong>จำนวนครั้งที่ดาวน์โหลด</strong></td>
                      <td bgcolor="#EFEFEF" class="Font_top2">:  <c:out value="${downloadViewform.vwntcDownload.ndCount}"/></td>
                    </tr>
                    <tr> 
                      <td align="left" class="textlineblue1"><a href="<portlet:renderURL>
            					<portlet:param name="action" value="listFiles"/>
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
         						</portlet:renderURL>">
    							&lt;&lt; กลับไป
							</a>     </td>
                      <td>&nbsp;</td>
                    </tr>
                    
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
</form:form>
</table> 
