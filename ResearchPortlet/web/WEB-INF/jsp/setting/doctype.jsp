<%@ include file="/WEB-INF/jsp/include.jsp" %>
<%@ page contentType="text/html; charset=utf-8" %> 
<%@page import="javax.portlet.PortletURL"%>
 <portlet:resourceURL var="research_group_resource_get_xls"  id="research_group_resource_get_xls"></portlet:resourceURL>
<script type="text/javascript">
function <portlet:namespace/>confirmDelete(_urlDelete){ 
	 
		var agree=confirm(" ต้องการลบข้อมูลหรือไม่? ");
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
function <portlet:namespace />doSubmitForm(){
	 var form = document.forms['docTypeForm'];
//	alert("aoe->"+form.aoe.value)
	 //alert("aoe->"+form.elements['aoe'].value)
	
	 form.submit();
	//document.getElementById("docTypeForm").submit();
	
}
function <portlet:namespace />doSearch(){
	  // $( "#element_edit" ).hide( "slow");
	   var form = document.forms['docTypeForm'];
	 //  alert(form.aoe)
	   form.elements['<portlet:namespace />mode'].value="search";
		 //alert("aoe->"+form.aoe.value)
		 //alert("aoe->"+form.elements['aoe'].value)
		
		 form.submit();
 }

function <portlet:namespace/>exportFile(type){
	
	var src = $("#<portlet:namespace />_export_"+type).val();
	//alert(src)
	//$("#download_xls").attr("href",src);
	//$("#download_xls").click();
	  var div = document.createElement("div");
	     document.body.appendChild(div);
	    div.innerHTML = "<iframe width='0' height='0' scrolling='no' frameborder='0' src='" + src + "'></iframe>";
	/*    
	 	var src = "http://172.17.1.109:10000/NTCDownloadServlet/DownloadServlet?"+_hotLink+"&mode=adminview";
	 
	    var div = document.createElement("div");
	    document.body.appendChild(div);
	    div.innerHTML = "<iframe width='0' height='0' scrolling='no' frameborder='0' src='" + src + "'></iframe>";
		  // Create an IFRAME.
*/
	}
function  <portlet:namespace/>importFile(type){
	
}
function <portlet:namespace/>doAction(){ 
	
		//alert(_command+","+_mode);
		//var ndId = document.getElementById("ndId");
		//var command = document.getElementById("command");
	return true;
	//alert(ndgNameValue)
	//if(ndgNameValue!=null && ndgNameValue != '' && Value != ' ' ){
		//return true;
	//}
	//else {
		//alert(" กรุณากรอกข้อความที่จะค้นหา");
		//return false;
	//}			
	
}                                                  
</script>
<%--
<portlet:renderURL var="formAction">
    <portlet:param name="action" value="listDownloadGroups"/>
</portlet:renderURL>
 --%> 
<portlet:actionURL var="formAction">
    <portlet:param name="action" value="doSubmit"/>
</portlet:actionURL> 
<portlet:renderURL var="list">
    <portlet:param name="action" value="list"/>
</portlet:renderURL> 
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
   <meta http-equiv="Pragma" content="no-cache">
	<meta http-equiv="Cache-Control" content="no-cache">
	<meta http-equiv="Expires" content="0">  

    <title><spring:message code="research.group"/></title>

    <!-- Bootstrap core CSS --> 
    <link rel="stylesheet" href="<c:url value="/resources/bootstrap/css/bootstrap.min.css"/>" type="text/css"/>
    
    <style type="text/css">
    #breadcrumbs { display:none; } 
    </style>
  </head>
  <body>
  <form:form  id="docTypeForm" modelAttribute="docTypeForm" method="post"  name="docTypeForm" action="${formAction}" enctype="multipart/form-data">
   <fieldset style="font-family: sans-serif;padding-top:5px"> 
            <input type="hidden" name="command" id="<portlet:namespace />common" value="${docTypeForm.command}" />
            <input type="hidden" name="mode" id="<portlet:namespace />mode"  value="${docTypeForm.mode}" />
             <input type="hidden" name="aoe" value="2"/>
         <div style="border: 1px solid #FFC299;background: #F9F9F9;padding: 10px"> 
                <div  id="search_section_application"> 
                    <table class="table table-striped table-bordered table-condensed" border="1" style="font-size: 12px"> 
               <thead>    
                   <tr>    	
                   		<th width="40%"><div class="th_class" style="text-align: center" >Doc Type</div></th>  
                   		<th width="10%"><div class="th_class" style="text-align: center" >View</div></th> 
                   		<th width="10%"><div class="th_class" style="text-align: center" >Create</div></th> 
                    	<th width="10%"><div class="th_class" style="text-align: center" >Update</div></th> 
                    	<th width="10%"><div class="th_class" style="text-align: center" >Delete</div></th>  
                    	<th width="20%"><div class="th_class" style="text-align: center" >Disable For Admin</div></th>   
                   </tr> 
                </thead> 
                <tbody>    
                <c:if test="${not empty docTypes}"> 
                 <c:forEach items="${docTypes}" var="docType" varStatus="loop"> 
               		 <tr style="cursor: pointer;">
                		<td style="text-align: left">${docType.dtName}</td>  
                		<td style="text-align: center">
                			<c:if test="${docType.isView=='1'}">
                				<form:checkbox path="isView" checked="true" value="${docType.dtName}" />
                			</c:if>
                			<c:if test="${docType.isView!='1'}">
                				<form:checkbox path="isView"  value="${docType.dtName}" />
                			</c:if>
                		</td>  
                		<td style="text-align: center">
                			<c:if test="${docType.isCreate=='1'}">
                				<form:checkbox path="isView" checked="true" value="${docType.dtName}" />
                			</c:if>
                			<c:if test="${docType.isCreate!='1'}">
                				<form:checkbox path="isCreate"  value="${docType.dtName}" />
                			</c:if>
                		</td>  
                		<td style="text-align: center">
                			<c:if test="${docType.isUpdate=='1'}">
                				<form:checkbox path="isUpdate" checked="true" value="${docType.dtName}" />
                			</c:if>
                			<c:if test="${docType.isUpdate!='1'}">
                				<form:checkbox path="isUpdate"  value="${docType.dtName}" />
                			</c:if>
                		</td>  
                		<td style="text-align: center">
                			<c:if test="${docType.isDelete=='1'}">
                				<form:checkbox path="isDelete" checked="true" value="${docType.dtName}" />
                			</c:if>
                			<c:if test="${docType.isDelete!='1'}">
                				<form:checkbox path="isDelete"  value="${docType.dtName}" />
                			</c:if>
                		</td>  
                		<td style="text-align: center">
                			<c:if test="${docType.isDisableForAdmin=='1'}">
                				<form:checkbox path="isDisableForAdmin" checked="true" value="${docType.dtName}" />
                			</c:if>
                			<c:if test="${docType.isDisableForAdmin!='1'}">
                				<form:checkbox path="isDisableForAdmin"  value="${docType.dtName}" />
                			</c:if>
                		</td>  
              		</tr>
              </c:forEach>
              </c:if>
            </tbody>
          </table>
                </div> 
                <div>
                	<button type="button" onclick="<portlet:namespace />doSubmitForm()" class="btn btn-primary">Submit</button>
                </div>
               
      </div>
      </fieldset>  
 
</form:form>

    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster --> 
   
     <script src="<c:url value="/resources/js/jquery-1.11.2.min.js"/>"></script> 
    <script src="<c:url value="/resources/bootstrap/js/bootstrap.min.js"/>"></script>  
  </body>
</html>	
