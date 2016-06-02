<%@ include file="/WEB-INF/jsp/include.jsp" %>
<%@ page contentType="text/html; charset=utf-8" %> 
<%@page import="javax.portlet.PortletURL"%>
 <portlet:resourceURL var="research_group_resource_get_xls"  id="research_group_resource_get_xls"></portlet:resourceURL>
<c:set var="ns"><portlet:namespace /></c:set>
<script type="text/javascript">
function <portlet:namespace/>confirmUndo(_urlUndo){ 
	 
	var agree=confirm(" ต้องการ Undo ข้อมูลหรือไม่? ");
	//alert(_urlDelete)
	if (agree){
	window.location.href = _urlUndo;
		return true ;
	}
	else{
		return false ;
	}
}
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
function <portlet:namespace />doDeleteItems(){
	var agree=confirm(" ต้องการลบข้อมูลหรือไม่? ");
	//alert(_urlDelete)
	if (agree){
		 var form = document.forms['patentForm'];
		 $("#<portlet:namespace/>mode").val('deleteItems');
		 form.submit();
	}
	else{
		return false ;
	}
	
}
function <portlet:namespace />doSubmitTab(tab){
	 var form = document.forms['patentForm'];
	   form.elements['<portlet:namespace />tab'].value=tab;
	   form.elements['<portlet:namespace />mode'].value="search";
	   form.submit();
}
function <portlet:namespace />doSubmitForm(){
	 var form = document.forms['patentForm'];
//	alert("aoe->"+form.aoe.value)
	 //alert("aoe->"+form.elements['aoe'].value)
	
	 form.submit();
	//document.getElementById("patentForm").submit();
	
}
function <portlet:namespace />doSubmitPaging(){
	 var form = document.forms['patentForm'];
	   form.elements['<portlet:namespace />mode'].value="search";
	   form.submit();
}
function <portlet:namespace />doSubmitPaging(){
	 var form = document.forms['patentForm'];
	   form.elements['<portlet:namespace />mode'].value="search";
	   form.submit();
}
function <portlet:namespace />doSearch(){
		$("#<portlet:namespace/>pageNo").val("1");
		<portlet:namespace />doSubmitPaging()
 }
function <portlet:namespace />doChangePage(){
	 <portlet:namespace />doSubmitPaging();
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
     <link href="<c:url value='/resources/css/smoothness/jquery-ui-1.9.1.custom.min.css'/>" type="text/css"  rel="stylesheet" />  
    <link rel="stylesheet" href="<c:url value="/resources/css/jquery.fileupload.css"/>" type="text/css"/>
    <style type="text/css">
    #breadcrumbs { display:none; }
    .ui-autocomplete-loading {
    background: white url('<c:url value="/resources/css/smoothness/images/ui-anim_basic_16x16.gif"/>') right center no-repeat;
  }  
    </style>
  </head>
  <body>
    <ul id="<portlet:namespace />myTab" class="nav nav-tabs" role="tablist">
      <li role="presentation" class='${patentForm.tab=="all"?"active":""}' style="cursor: pointer;"><a onclick="<portlet:namespace />doSubmitTab('all')" id="home-tab" role="tab" data-toggle="tab" aria-controls="home" aria-expanded="true">All</a></li>
      <li role="presentation" class='${patentForm.tab=="myData"?"active":""}'  style="cursor: pointer;"><a onclick="<portlet:namespace />doSubmitTab('myData')" role="tab" id="profile-tab" data-toggle="tab" aria-controls="profile" aria-expanded="false">My Data</a></li>
    </ul>
  <form:form  id="patentForm" modelAttribute="patentForm" method="post"  name="patentForm" action="${formAction}" enctype="multipart/form-data">
   <fieldset style="font-family: sans-serif;padding-top:5px"> 
            <input type="hidden" name="command" id="<portlet:namespace />common" value="${patentForm.command}" />
            <input type="hidden" name="mode" id="<portlet:namespace />mode"  value="${patentForm.mode}" />
              <input type="hidden" name="tab" id="<portlet:namespace />tab"  value="${patentForm.tab}" />
            <input type="hidden" name="pageNo"  id="<portlet:namespace />pageNo"  value="${patentForm.pageNo}" />
            <input type="hidden" id="<portlet:namespace />pageCount" value="${patentForm.pageCount}" />
             <input type="hidden" name="aoe" value="2"/>
         <div style="border: 1px solid #FFC299;background: #F9F9F9;padding: 10px"> 
                <table border="0" width="100%" style="font-size: 13px">
                <tbody>
                <tr>
                <td align="left" width="100%" colspan="2">  
                <span style="padding-left: 20px;font-size: 13px;">
  <div class="form-group has-success has-feedback">
    <label class="control-label" for="inputSuccess4">ระบุ key word</label>
      <form:input path="keySearch" cssStyle="display:inline;width:250px" cssClass="form-control"  id="keySearch" aria-describedby="inputSuccess4Status"/>
       <form:select path="filter" cssStyle="width:90px;" onchange="${ns}doSearch()">
    	<form:option value="0">ทั้งหมด</form:option>
    	<form:option value="1">อนุมัติ</form:option>
    	<form:option value="2">ร่าง</form:option>
    	<form:option value="3">ถังขยะ</form:option>
    </form:select>
    <button onclick="<portlet:namespace />doSearch()" type="button" class="btn btn-success" style="margin-top:-8px;">Search</button>

  </div>
</span>
                </td> 
                </tr>
                <tr>
                <td align="left" width="30%">  
                 <button  onclick='<portlet:namespace/>rederPage("<portlet:renderURL><portlet:param name="action" value="addEditView"/><portlet:param name="mode" value="add"/><portlet:param name="inventionId" value="0"/></portlet:renderURL>")' type="button" class="btn btn-primary">Add</button> 
               <button onclick="<portlet:namespace/>doDeleteItems()"  class="btn btn-danger" type="button">
               			<c:if test="${patentForm.filter=='3'}">
                 			Delete
                 		</c:if>
                 		<c:if test="${patentForm.filter!='3'}">
                 			Trash
                 		</c:if>
               </button>
 						 
                
                </td><td align="right" width="70%"> 
               
             
			
                </td>
                </tr>
                </tbody></table>  
                <div  id="search_section_application"> 
                    <table class="table-hover table-striped table-bordered table-condensed" border="1" style="font-size: 12px"> 
               <thead>    
                   <tr>    
                    <th width="2%"><div class="th_class"><input type="checkbox"  onchange="<portlet:namespace/>changeSelectAll(this)"/></div></th>  
                   <th width="7%"><div class="th_class">เลขที่คำขอ</div></th>  
                    <th width="35%"><div class="th_class">ชื่อสิ่งประดิษฐ์(T)</div></th>  
                    <th width="35%"><div class="th_class">ชื่อสิ่งประดิษฐ์(E)</div></th>  
                        <th width="5%"><div class="th_class">Created</div></th>  
                      <th width="5%"><div class="th_class">Updated</div></th>    
                <th width="11%"><div class="th_class"></div></th>  
                   </tr> 
                </thead> 
                <tbody> 
                 <c:set var="userIdAsString">${user.userId}</c:set>   
                <c:if test="${not empty patents}"> 
                 <c:forEach items="${patents}" var="patent" varStatus="loop"> 
               		 <tr style="cursor: pointer;">
               		  <c:set var="havePermissionOnDocs" value="${( userIdAsString == patent.createdBy 
                       				|| userIdAsString==patent.updatedBy || patent.isdocAssign ) }"></c:set>
                  		<td><form:checkbox path="ids" disabled="${!havePermissionOnDocs}"
                  					id="${ns}_x_${patent.inventionId}" value="${patent.inventionId}"/></td>  
                		<td style="text-align: left" onclick='<portlet:namespace/>rederPage("<portlet:renderURL><portlet:param name="action" value="addEditView"/><portlet:param name="mode" value="edit"/><portlet:param name="inventionId"><jsp:attribute name="value"><c:out value="${patent.inventionId}"/></jsp:attribute></portlet:param></portlet:renderURL>")' >${patent.proposeNo}</td>  
                		<td style="text-align: left" onclick='<portlet:namespace/>rederPage("<portlet:renderURL><portlet:param name="action" value="addEditView"/><portlet:param name="mode" value="edit"/><portlet:param name="inventionId"><jsp:attribute name="value"><c:out value="${patent.inventionId}"/></jsp:attribute></portlet:param></portlet:renderURL>")' >${patent.thaiName}</td>  
                		<td style="text-align: left" onclick='<portlet:namespace/>rederPage("<portlet:renderURL><portlet:param name="action" value="addEditView"/><portlet:param name="mode" value="edit"/><portlet:param name="inventionId"><jsp:attribute name="value"><c:out value="${patent.inventionId}"/></jsp:attribute></portlet:param></portlet:renderURL>")' >${patent.engName}</td>  
                			<fmt:formatDate type="time" value="${patent.createdDate}"   pattern="dd/MM/yyyy" var="createdDate" /> 
                		<td style="text-align: left" onclick='<portlet:namespace/>rederPage("<portlet:renderURL><portlet:param name="action" value="addEditView"/><portlet:param name="mode" value="edit"/><portlet:param name="inventionId"><jsp:attribute name="value"><c:out value="${patent.inventionId}"/></jsp:attribute></portlet:param></portlet:renderURL>")' >${createdDate} </td>  
                		 <fmt:formatDate type="time" value="${patent.updatedDate}"   pattern="dd/MM/yyyy" var="updatedDate" /> 
                		<td style="text-align: left" onclick='<portlet:namespace/>rederPage("<portlet:renderURL><portlet:param name="action" value="addEditView"/><portlet:param name="mode" value="edit"/><portlet:param name="inventionId"><jsp:attribute name="value"><c:out value="${patent.inventionId}"/></jsp:attribute></portlet:param></portlet:renderURL>")' >${updatedDate} </td>    
                		<td>
                		<div class="btn-group">
  							<a class="btn btn-primary dropdown-toggle" data-toggle="dropdown" href="#">
    							<i class="icon-align-justify icon-white"></i>  Action
    							<span class="caret"></span>
  							</a>
  <ul class="dropdown-menu">
  <c:if test="${havePermissionOnDocs}">
     <li><a onclick='<portlet:namespace/>rederPage("<portlet:renderURL><portlet:param name="action" value="addEditView"/><portlet:param name="mode" value="edit"/><portlet:param name="inventionId"><jsp:attribute name="value"><c:out value="${patent.inventionId}"/></jsp:attribute></portlet:param></portlet:renderURL>")'  ><i class="icon-edit"></i> Edit</a></li>
   </c:if>
    <li><a onclick='<portlet:namespace/>rederPage("<portlet:renderURL><portlet:param name="action" value="addEditView"/><portlet:param name="mode" value="copy"/><portlet:param name="inventionId"><jsp:attribute name="value"><c:out value="${patent.inventionId}"/></jsp:attribute></portlet:param></portlet:renderURL>")'  ><i class="icon-plus-sign"></i> Copy</a></li>
    
    <c:if test="${patentForm.filter=='3'}">
      <c:if test="${havePermissionOnDocs}">
    	<li><a onclick='return <portlet:namespace />confirmUndo("<portlet:actionURL><portlet:param name="action" value="undoItem"/><portlet:param name="inventionId"><jsp:attribute name="value"><c:out value="${patent.inventionId}"/></jsp:attribute></portlet:param></portlet:actionURL>")'><i class="icon-repeat"></i> Undo</a></li>
    	<li><a onclick='return <portlet:namespace />confirmDelete("<portlet:actionURL><portlet:param name="action" value="delete"/><portlet:param name="inventionId"><jsp:attribute name="value"><c:out value="${patent.inventionId}"/></jsp:attribute></portlet:param></portlet:actionURL>")'><i class="icon-trash"></i> Delete</a></li>
   	  </c:if>
   	 </c:if>
     <c:if test="${patentForm.filter!='3'}">
       <c:if test="${havePermissionOnDocs}">
     	<li><a onclick='return <portlet:namespace />confirmDelete("<portlet:actionURL><portlet:param name="action" value="delete"/><portlet:param name="inventionId"><jsp:attribute name="value"><c:out value="${patent.inventionId}"/></jsp:attribute></portlet:param></portlet:actionURL>")'><i class="icon-trash"></i> Trash</a></li>
       </c:if>
     </c:if>
     
    
  </ul>
</div>
<%--
                		   	<button onclick='<portlet:namespace/>rederPage("<portlet:renderURL><portlet:param name="action" value="addEditView"/><portlet:param name="mode" value="edit"/><portlet:param name="inventionId"><jsp:attribute name="value"><c:out value="${patent.inventionId}"/></jsp:attribute></portlet:param></portlet:renderURL>")'  class="btn btn-small" type="button">คัดลอก</button>
 						   	 <button onclick='return <portlet:namespace />confirmDelete("<portlet:actionURL><portlet:param name="action" value="delete"/><portlet:param name="inventionId"><jsp:attribute name="value"><c:out value="${patent.inventionId}"/></jsp:attribute></portlet:param></portlet:actionURL>")' class="btn btn-danger btn-small" type="button">ลบ</button>
 	 --%>					 
                		   <%--
                  			<i onclick='<portlet:namespace/>rederPage("<portlet:renderURL><portlet:param name="action" value="addEditView"/><portlet:param name="mode" value="edit"/><portlet:param name="inventionId"><jsp:attribute name="value"><c:out value="${patent.inventionId}"/></jsp:attribute></portlet:param></portlet:renderURL>")' class="icon-edit"></i>
                		<c:if test="${patent.docType=='DRAFT' && ( user.userId == patent.createdBy || user.userId==patent.updatedBy ) }">
                			<i  onclick='return <portlet:namespace />confirmDelete("<portlet:actionURL><portlet:param name="action" value="delete"/><portlet:param name="inventionId"><jsp:attribute name="value"><c:out value="${patent.inventionId}"/></jsp:attribute></portlet:param></portlet:actionURL>")' class="icon-trash"></i>
                		</c:if>
                		--%> 
                		</td>
              		</tr>
              </c:forEach>
              </c:if>
            </tbody>
          </table>
          
            <table border="0" width="100%" style="font-size: 13px">
                <tbody>
                <tr>
                <td align="left" width="70%"> 
                <span class="pagination">
 				 <ul>
    			 <c:if test="${patentForm.pageNo!=1}">
    				<li><a style="cursor: pointer;" onclick='<portlet:namespace/>goPrev()'>Prev</a></li>
    			</c:if>
    			 <c:if test="${not empty patentForm.pageCount}">
                     <c:forEach begin="${patentForm.pageStart}" end="${patentForm.pageEnd}"  var="loop">
                 	<c:if test="${patentForm.pageNo==(loop)}">
                 		<li><a class="active" style="background-color: #ddd" >${loop}</a></li>
                 	</c:if> 
                 	<c:if test="${patentForm.pageNo!=(loop)}">
                 		<li><a class="active" style="cursor: pointer;" onclick='<portlet:namespace/>goToPage("${loop}")'>${loop}</a></li>
                 	</c:if> 
                </c:forEach>
                <c:if test="${patentForm.pageEnd<patentForm.pageCount}">
                 		<li><a class="active" style="cursor: pointer;" onclick='<portlet:namespace/>goToPage("${patentForm.pageEnd}")'>...</a></li>
                 		<li><a class="active" style="cursor: pointer;" onclick='<portlet:namespace/>goToPage("${patentForm.pageCount}")'>${patentForm.pageCount}</a></li>
                 </c:if>
                 </c:if>
                 <c:if test="${patentForm.pageNo!=patentForm.pageCount}">
    				<li><a style="cursor: pointer;" onclick='<portlet:namespace/>goNext()'>Next</a></li>
    			</c:if>
  				</ul>
			</span>
                </td>
               <td align="right" width="30%"> 
               <form:select path="paging.pageSize" cssStyle="width:50px" onchange='${ns}doChangePage()'>
                	<form:option value="10">10</form:option>
                	<form:option value="20">20</form:option>
                	<form:option value="30">30</form:option>
                	<form:option value="50">50</form:option>
                </form:select> 
                </td>
                </tr>
           </tbody>
           </table>
                </div> 
      </div>
      </fieldset>  
 
</form:form>

    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster --> 
   
     <script src="<c:url value="/resources/js/jquery-1.11.2.min.js"/>"></script> 
    <script src="<c:url value="/resources/bootstrap/js/bootstrap.min.js"/>"></script>  
    <script  src="<c:url value="/resources/js/smoothness/jquery-ui-1.9.1.custom.min.js"/>"></script>
       <script src="<c:url value='/resources/js/bootbox.min.js'/>" type="text/javascript"></script> 
    
     <script type="text/javascript" src="<c:url value='/resources/js/vendor/jquery.ui.widget.js'/>"></script>
<script type="text/javascript" src="<c:url value='/resources/js/jquery.iframe-transport.js'/>"></script>

  <script type="text/javascript" src="<c:url value='/resources/js/jquery.fileupload.js'/>"></script>  
        <script>  
        
        function  <portlet:namespace/>rederPage(url){
        	window.location.href=url;
        }
     
        function <portlet:namespace/>displayElementEdit(mode,url,id){
    	   $("#groupCode").val('');
    	   $("#groupTh").val('');
    	   $("#<portlet:namespace/>mode").val(mode);
    	   $("#<portlet:namespace/>command").val("list");
    	   $("#<portlet:namespace/>export_import_element").hide();
    	   if(mode=='edit'){
    		   $("#<portlet:namespace/>export_import_element").show(); 
    	        <%--   url: '${groupInfo}' ,	url: '${ajax}' ,  --%>
    	      	    $.ajax({
    	      	  	url: url , 
    	      	       // type: 'POST',
    	      	       // datatype:'json',
    	      	     	 data: { researcherId: id }, 
    	      	         success: function(data){
    	      	        	 $("#researcherId").val(id)
    	      	        	 $("#groupCode").val(data.groupCode)
    		  				 $("#groupTh").val(data.groupTh);
    		   				$("#groupEng").val(data.groupEng);
    		   				$("#<portlet:namespace />_export_csv").val(data.csvResource);
    		   				$("#<portlet:namespace />_export_xml").val(data.xmlResource);
    		   			
    		   				$( "#<portlet:namespace/>element_edit" ).show( "slow");
    	      	        }
    	      	    }); 
    		  
    		
    	   }else{
    		   $( "#<portlet:namespace/>element_edit" ).show( "slow");   
    	   }
    	   
    	   //window.location.href='${list}'
    	 // document.getElementById("listForm").submit();
    	 
    	  
       }
        function <portlet:namespace/>goPrev(){
           	if($("#<portlet:namespace/>pageNo").val()!='1'){
           		var prev=parseInt($("#<portlet:namespace/>pageNo").val())-1;
           		$("#<portlet:namespace/>pageNo").val(prev);
           		<portlet:namespace />doSubmitPaging();
           	}
           }
           function <portlet:namespace/>goNext(){
           	var next=parseInt($("#<portlet:namespace/>pageNo").val());
           	if(next<parseInt($("#<portlet:namespace/>pageCount").val())){
           		next=next+1;
           		$("#<portlet:namespace/>pageNo").val(next);
           		<portlet:namespace />doSubmitPaging();
           	}
           } 
           function <portlet:namespace/>goToPage(pageNo){ 
        		$("#<portlet:namespace/>pageNo").val(pageNo)
           	<portlet:namespace />doSubmitPaging();
           }
           function <portlet:namespace />changeSelectAll(obj){
           	//alert(obj.checked)
           	$( "input[name='ids']" ).each(function() {
           		  //$( this ).addClass( "foo" );
           		  if(!$( this ).prop( "disabled"))
           			  $( this ).prop( "checked", obj.checked );
           		});
           		 
           }
      </script>  
  </body>
</html>	
