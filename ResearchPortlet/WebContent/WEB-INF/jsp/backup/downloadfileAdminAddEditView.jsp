<%@ include file="/WEB-INF/jsp/include.jsp" %>
<%@page session="false" contentType="text/html" pageEncoding="tis-620" %>
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
<script>
String.prototype.trim = function() {
	return this.replace(/^\s+|\s+$/g,"");
}
function <portlet:namespace/>handleGetData(obj) {
	//alert(obj)
	dwr.util.removeAllOptions('ndgSubIdSelect');
	var data = [ { ndgName:"-- เลือกกลุ่มย่อย --",ndgId:"0"} ];
	dwr.util.addOptions('ndgSubIdSelect', data, 'ndgId', 'ndgName');
	 if(obj!=null){
	  // alert(obj);
	   
	   dwr.util.addOptions('ndgSubIdSelect', obj, 'ndgId', 'ndgName');
	   
	  //alert(obj.name);
	   //var _x = obj[0];
	   //RecordsTable.removeAll('displayTable');
	   //RecordsTable.append('displayTable',_x);
	   //var _pageArray = obj[1];
	  // dwr.util.setValue('pageElement','<font style="font-family: Microsoft Sans Serif;font-size: 13px;">'+_pageArray[0]+'</font>', { escapeHtml:false });
	   //dwr.util.setValue('pageElementSummary','<font style="font-family: Microsoft Sans Serif;font-size: 13px;">'+_pageArray[1]+'</font>', { escapeHtml:false });
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
	 	var src = "http://172.17.1.109:10000/NTCDownloadServlet/DownloadServlet?"+_hotLink+"&mode=adminview";
		//alert(src)
	    var div = document.createElement("div");
	    document.body.appendChild(div);
	    div.innerHTML = "<iframe width='0' height='0' scrolling='no' frameborder='0' src='" + src + "'></iframe>";
		  // Create an IFRAME.

	}
function <portlet:namespace />doAction(_command,_mode){
//alert(_command+","+_mode);
var ndId = document.getElementById("ndId");
var command = document.getElementById("command");
//alert(ndId.value);
var form = document.forms['downloadAdminform'];
var _ndTitle = document.getElementById('ntcDownload.ndTitle');
if(_ndTitle.value.trim().length==0){
 	 alert("กรุณากรอก ชื่อหัวข้อ");
 	 return false;
 }
var _ndgSubIdSelect = document.getElementById("ndgSubIdSelect");
if(_ndgSubIdSelect.value == '0' ){
  	 alert("กรุณากรอก หมวดหมู่ย่อย");
  	 return false;
  }
var _chkChoiceArrayValue = form.elements['ntcDownload.ndType']; //1=url , 2 =file download 
var isChecked = false;
var indexChecked =0;
for(var i=0;i<_chkChoiceArrayValue.length;i++){
	if(_chkChoiceArrayValue[i].checked){
		isChecked =true;
		indexChecked = i;
		break;
	}
}
if(!isChecked){
	alert(" กรุณาเลือก Type");
	return false;
}
if(indexChecked==1){
	var _url = document.getElementById('url').value;
	if(!(_url!=null && _url!='' && _url!=' ')){
		alert("กรุณากรอก url")
		return false;
	}
	document.getElementById('ndFilePath').value = _url;
}else{
	if(_mode == 'add'){
		var _file =  document.getElementById('file').value;
		if(!(_file!=null && _file!='' && _file!=' ')){
			alert("กรุณาเลือกไฟล์")
			return false;
		} 
	}
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
function setChoice(_ndType){
	//var pictureElement = document.getElementById("pictureElement");
	if(_ndType == '1'){
		document.getElementById('pictureElement1').style.display = '';
		document.getElementById('pictureElement2').style.display = 'none';
		document.getElementById('pictureElementLabel1').style.display = '';
		document.getElementById('pictureElementLabel2').style.display = 'none';
		
	}else{ 
		document.getElementById('pictureElement1').style.display = 'none';
		document.getElementById('pictureElement2').style.display = '';
		document.getElementById('pictureElementLabel1').style.display = 'none';
		document.getElementById('pictureElementLabel2').style.display = '';
	}
	//pictureElement.innerHTML=//;
}
</script>
<portlet:actionURL var="formAction">
    <portlet:param name="action" value="doSubmit"/>
</portlet:actionURL> 
<table class="body_b" width="1000" border="0" align="center" cellpadding="0" cellspacing="0"  > 
<form:form  modelAttribute="downloadAdminform" method="post"  name="downloadAdminform" action="${formAction}" enctype="multipart/form-data">
<form:hidden path="ntcDownload.ndId"/>
<form:hidden path="ntcDownload.ndAlert"/>
<form:hidden path="ntcDownload.ndFileName" id="ndFileName"/>
<form:hidden path="ntcDownload.ndFilePath" id="ndFilePath"/>
<form:hidden path="ntcDownload.ndFileSize"/>
<form:hidden path="ntcDownload.ndHotlink"/>

<form:hidden path="ndId" id="ndId"/>
<form:hidden path="ndgId" id="ndgId"/>
<form:hidden path="ndgSubId" id="ndgSubId"/>
<form:hidden path="ndgName" id="ndgName"/>
<form:hidden path="ndgSubName" id="ndgSubName"/>                        
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
          <td class="font_News_Catgory" style="color: ">
            <p class="textsmall12"  style="color:#666666">&nbsp;&nbsp;&nbsp;&nbsp;หมวดหมู่หลัก : <c:out value="${downloadAdminform.ndgName}"/></p></td>          
          </tr>
          <tr>          
          <td class="font_News_Catgory" style="color: ">
            <p class="textsmall12"  style="color:#666666">&nbsp;&nbsp;&nbsp;&nbsp;หมวดหมู่ย่อย : <c:out value="${downloadAdminform.ndgSubName}"/></p></td>          
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
                  <td width="14" valign="bottom"><img src='<%=renderResponse.encodeURL(renderRequest.getContextPath()+"/images/approval_03.jpg") %>' width="14" height="31" alt="" /></td>
                </tr>
                <tr>
                  <td colspan="3" style="border:#0e589e 1px solid"><table width="100%" border="0" cellspacing="0" cellpadding="0">
                    <tr>
                      <td><table width="100%" border="0" cellspacing="0" cellpadding="5" class="textlineblue3"> 
                          <tr>
                            <td width="40%" align="right" bgcolor="#F0F0F0" class="font_topic_detail">หมวดหมู่หลัก(*) :</td>
                            <td width="60%" align="left" bgcolor="#FCFCFC" class="textlineblue3">
                            <table width="555" border="0" cellspacing="5" cellpadding="0">
                                <tr>
                                  <td width="555"><table width="100%" border="0" cellspacing="0" cellpadding="0">
                                      <tr>
                                        <td>
										  <c:if test="${downloadAdminform.mode=='add' || downloadAdminform.mode=='edit'}">
    												<form:select path="ndgIdSelect" id="ndgIdSelect" onchange='getGroupList(this)'>
    												 <form:options items="${groupList}" itemValue="ndgId" itemLabel="ndgName"/>    												
    											</form:select>  
    									  </c:if>
										  <c:if test="${downloadAdminform.mode=='view'}">
    											&nbsp;<form:select path="ndgIdSelect" disabled="true">
    												 <form:options items="${groupList}" itemValue="ndgId" itemLabel="ndgName"/>    												
    											</form:select>  
										  </c:if>
										 </td>
                                      </tr>
                                  </table></td>
                                </tr> 
                            </table></td>
                          </tr>
                          <tr>
                            <td width="40%" align="right" bgcolor="#F0F0F0" class="font_topic_detail">หมวดหมู่ย่อย(*) :</td>
                            <td width="60%" align="left" bgcolor="#FCFCFC" class="textlineblue3">
                            <table width="555" border="0" cellspacing="5" cellpadding="0">
                                <tr>
                                  <td width="555"><table width="100%" border="0" cellspacing="0" cellpadding="0">
                                      <tr>
                                        <td>
										  <c:if test="${downloadAdminform.mode=='add' || downloadAdminform.mode=='edit'}">
    												<form:select path="ndgSubIdSelect" id="ndgSubIdSelect">
    												 <form:options items="${subGroupList}" itemValue="ndgId" itemLabel="ndgName"/>    												
    											</form:select>  
    									  </c:if>
										  <c:if test="${downloadAdminform.mode=='view'}">
    											&nbsp;<form:select path="ndgSubIdSelect" disabled="true">
    												 <form:options items="${subGroupList}" itemValue="ndgId" itemLabel="ndgName"/>    												
    											</form:select>  
										  </c:if>
										 </td>
                                      </tr>
                                  </table></td>
                                </tr> 
                            </table></td>
                          </tr>
                          <tr>
                          <tr>
                            <td width="40%" align="right" bgcolor="#F0F0F0" class="font_topic_detail">ชื่อหัวข้อ(*) :</td>
                            <td width="60%" align="left" bgcolor="#FCFCFC" class="textlineblue3">
                            <table width="555" border="0" cellspacing="5" cellpadding="0">
                                <tr>
                                  <td width="555"><table width="100%" border="0" cellspacing="0" cellpadding="0">
                                      <tr>
                                        <td>
										  <c:if test="${downloadAdminform.mode=='add' || downloadAdminform.mode=='edit'}">
    												<form:input path="ntcDownload.ndTitle"/>
    									  </c:if>
										  <c:if test="${downloadAdminform.mode=='view'}">
    											&nbsp;<c:out value="${downloadAdminform.ntcDownload.ndTitle}"></c:out>
										  </c:if>
										 </td>
                                      </tr>
                                  </table></td>
                                </tr> 
                            </table></td>
                          </tr>
                          <tr>
                            <td width="40%" align="right" bgcolor="#F0F0F0" class="font_topic_detail">type(*) :</td>
                            <td width="60%" align="left" bgcolor="#FCFCFC" class="textlineblue3">
                           <table width="46%" border="0" cellspacing="0" cellpadding="3">
                            <tr class="Font_top3">
                              <td width="12%" align="left">
                               <c:if test="${downloadAdminform.mode=='add' || downloadAdminform.mode=='edit'}">
    									 <form:radiobutton path="ntcDownload.ndType" value="2" onclick='setChoice("2")'/>
    									  </c:if>
										  <c:if test="${downloadAdminform.mode=='view'}">
    										<c:out value="${downloadAdminform.ntcDownload.ndType}"></c:out>
							  </c:if>
                              </td>
                              <td width="35%">Upload </td>
                              <td width="12%">
                               <c:if test="${downloadAdminform.mode=='add' || downloadAdminform.mode=='edit'}">
    									<form:radiobutton path="ntcDownload.ndType" value="1"  onclick='setChoice("1")'/>
    							</c:if>
										  <c:if test="${downloadAdminform.mode=='view'}">
    										<c:out value="${downloadAdminform.ntcDownload.ndType}"></c:out>
							  </c:if>
                              </td>
                              <td width="41%">URL</td>
                            </tr>
                          </table></td>
                          </tr>
                          <tr>
                            <td width="40%" align="right" bgcolor="#F0F0F0" class="font_topic_detail">
                            		<span id="pictureElementLabel1" style="display: none;">URL(*) :</span>                              
                              		<span id="pictureElementLabel2" style="display: none;">ไฟล์(*) :</span></td>
                            <td width="60%" align="left" bgcolor="#FCFCFC" class="textlineblue3">
                            <table width="555" border="0" cellspacing="5" cellpadding="0">
                                <tr>
                                  <td width="555"><table width="100%" border="0" cellspacing="0" cellpadding="0">
                                      <tr>
                                        <td>
                                         <c:if test="${downloadAdminform.mode=='edit' && downloadAdminform.ntcDownload.ndType==1}">
										  		 <span id="pictureElement1">
										  			<input type="text" name="url"  style="width:300px;" id="url" value='<c:out value="${downloadAdminform.ntcDownload.ndFilePath}"/>'/>
										 		</span>  
										 		<span id="pictureElement2" style="display: none;"><input type="file" name="file" id="file"  /></span>
										  </c:if> 
										  <c:if test="${downloadAdminform.mode=='edit' && downloadAdminform.ntcDownload.ndType==2}">
										  		<span id="pictureElement1" style="display: none;">
										  			<input type="text" name="url"  style="width:300px;" id="url"/>
										 		</span> 
										  		<span id="pictureElement2"><input type="file" name="file" id="file"  /> <span style="cursor: pointer;text-decoration: underline" onclick='<portlet:namespace />downloadFile("<c:out value="${downloadAdminform.ntcDownload.ndHotlink}"/>")'><c:out value="${downloadAdminform.ntcDownload.ndFileName}"/></span></span>
										  </c:if>
											   <c:if test="${downloadAdminform.mode=='add' }">
										  		<span id="pictureElement1" style="display: none;">
										  			<input type="text" name="url"  style="width:300px;" id="url" />
										 		</span>                            
                              			        <span id="pictureElement2" style="display: none;"><input type="file" name="file" id="file"  /></span>
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
                            <table width="555" border="0" cellspacing="5" cellpadding="0">
                                <tr>
                                  <td width="555"><table width="100%" border="0" cellspacing="0" cellpadding="0">
                                      <tr>
                                        <td>
										  <c:if test="${downloadAdminform.mode=='add' || downloadAdminform.mode=='edit'}">
    												<form:textarea path="ntcDownload.ndDetail" cssStyle="width:300px;"/>
    									  </c:if>
										  <c:if test="${downloadAdminform.mode=='view'}">
    											&nbsp;<c:out value="${downloadAdminform.ntcDownload.ndDetail}"></c:out>
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
                                  		<input type="image" src='<%=renderResponse.encodeURL(renderRequest.getContextPath()+"/images/ok.jpg") %>'    alt="" onclick="return <portlet:namespace />doAction('doSaveFileDownload','${downloadAdminform.mode}')"/>
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
            					<portlet:param name="action" value="listFiles"/>
            					<portlet:param name="ndgId">
                             		<jsp:attribute name="value">
                                 			<c:out value="${downloadAdminform.ndgId}"/>
                             		</jsp:attribute>
                         		</portlet:param>
                         		<portlet:param name="ndgSubId">
                             		<jsp:attribute name="value">
                                 			<c:out value="${downloadAdminform.ndgSubId}"/>
                             		</jsp:attribute>
                         		</portlet:param>
                         		<portlet:param name="ndgName">
                             		<jsp:attribute name="value">
                                 			<c:out value="${downloadAdminform.ndgName}"/>
                             		</jsp:attribute>
                         		</portlet:param> 
                         		<portlet:param name="ndgSubName">
                             		<jsp:attribute name="value">
                                 			<c:out value="${downloadAdminform.ndgSubName}"/>
                             		</jsp:attribute>
                         		</portlet:param> 
         						</portlet:renderURL>">
    							&lt;&lt; กลับไป หมวดหมู่ย่อย
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
