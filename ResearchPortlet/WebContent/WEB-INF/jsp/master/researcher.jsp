<%@ include file="/WEB-INF/jsp/include.jsp" %>
<%@ page contentType="text/html; charset=utf-8" %> 
<%@page import="javax.portlet.PortletURL"%>
 <portlet:resourceURL var="research_group_resource_get_xls"  id="research_group_resource_get_xls"></portlet:resourceURL>
 <c:set var="ns"><portlet:namespace /></c:set>
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
function <portlet:namespace />doDeleteItems(){
	var agree=confirm(" ต้องการลบข้อมูลหรือไม่? ");
	//alert(_urlDelete)
	if (agree){
		 var form = document.forms['researcherMasterForm'];
		 $("#<portlet:namespace/>mode").val('deleteItems');
		 form.submit();
	}
	else{
		return false ;
	}
	
}
function <portlet:namespace />doSubmitForm(){
	 var form = document.forms['researcherMasterForm'];
//	alert("aoe->"+form.aoe.value)
	 //alert("aoe->"+form.elements['aoe'].value)
	
	 form.submit();
	//document.getElementById("researcherMasterForm").submit();
	
}
function <portlet:namespace />doSubmitPaging(){
	 var form = document.forms['researcherMasterForm'];
	   form.elements['<portlet:namespace />mode'].value="search";
	   form.submit();
}
function <portlet:namespace />doSearch(){
	$("#<portlet:namespace/>pageNo").val("1");
	<portlet:namespace />doSubmitPaging();
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
    <link rel="stylesheet" href="<c:url value="/resources/css/jquery.fileupload.css"/>" type="text/css"/> 
    <style type="text/css">
    #breadcrumbs { display:none; } 
    </style>
  </head>
  <body>
  <form:form  id="researcherMasterForm" modelAttribute="researcherMasterForm" method="post"  name="researcherMasterForm" action="${formAction}" enctype="multipart/form-data">
   <fieldset style="font-family: sans-serif;padding-top:5px"> 
            <input type="hidden" name="command" id="<portlet:namespace />common" value="${researcherMasterForm.command}" />
            <input type="hidden" name="mode" id="<portlet:namespace />mode"  value="${researcherMasterForm.mode}" />
              <input type="hidden" name="pageNo"  id="<portlet:namespace />pageNo"  value="${researcherMasterForm.pageNo}" />
            <input type="hidden" id="<portlet:namespace />pageCount" value="${researcherMasterForm.pageCount}" />
            
         <div style="border: 1px solid #FFC299;background: #F9F9F9;padding: 10px"> 
                <table border="0" width="100%" style="font-size: 13px">
                <tbody>
                <tr>
                <td align="left" width="100%" colspan="2">  
                <span style="padding-left: 20px;font-size: 13px;">
                <c:if test="${not empty errorMessage}"> 
                		<div class="alert alert-error">
                			 <button type="button" class="close" data-dismiss="alert">&times;</button>
  							 <spring:message code="${errorMessage}"/>
						</div>
				</c:if>
  <div class="form-group has-success has-feedback">
    <label class="control-label" for="inputSuccess4">ระบุ key word</label>
      <form:input path="keySearch" cssStyle="display:inline;width:250px" cssClass="form-control"  id="keySearch" aria-describedby="inputSuccess4Status"/>
    <button onclick="<portlet:namespace />doSearch()" type="button" class="btn btn-success" style="margin-top:-8px;">Search</button>
  </div>
</span>
                </td> 
                </tr>
                <tr>
                <td align="left" width="30%">  
                <button  onclick='<portlet:namespace/>rederPage("<portlet:renderURL><portlet:param name="action" value="addEditView"/><portlet:param name="mode" value="add"/><portlet:param name="researcherId" value="0"/></portlet:renderURL>")' type="button" class="btn btn-primary">Add</button> 
                  <button onclick="<portlet:namespace/>doDeleteItems()"  class="btn btn-danger" type="button">ลบ</button> 
                
                </td><td align="right" width="70%"> 
               
                </td>
                </tr>
                </tbody></table>  
                <div  id="search_section_application"> 
                    <table class="table-hover table-striped table-bordered table-condensed" border="1" style="font-size: 12px"> 
               <thead>    
                   <tr>    
                    <th width="2%"><div class="th_class"><input type="checkbox"  onchange="<portlet:namespace/>changeSelectAll(this)"/></div></th>  
                 <%--  <th width="10%"><div class="th_class">รหัสนักวิจัย</div></th>  --%>  
                   <th width="19%"><div class="th_class">คำนำหน้า/ตำแหน่งทางวิชาการ</div></th> 
                    <th width="29%"><div class="th_class">ชื่อนักวิจัย/นามสกุล(T)</div></th>  
                    <th width="29%"><div class="th_class">ชื่อนักวิจัย/นามสกุล(E)</div></th>  
                     <th width="5%"><div class="th_class">Created</div></th>  
                      <th width="5%"><div class="th_class">Updated</div></th>   
                <th width="11%"><div class="th_class"></div></th>  
                   </tr> 
                </thead> 
                <tbody>    
                <c:if test="${not empty researchers}"> 
                 <c:forEach items="${researchers}" var="researcher" varStatus="loop"> 
               		 <tr style="cursor: pointer;">
                 <td style="text-align: left"><form:checkbox path="ids" id="${ns}_x_${researcher.researcherId}" value="${researcher.researcherId}"/></td>  
                	<%--	<td style="text-align: left">${researcher.researcherCode}</td>  --%>  
                		<td onclick='<portlet:namespace/>rederPage("<portlet:renderURL><portlet:param name="action" value="addEditView"/><portlet:param name="mode" value="edit"/><portlet:param name="researcherId"><jsp:attribute name="value"><c:out value="${researcher.researcherId}"/></jsp:attribute></portlet:param></portlet:renderURL>","${researcher.researcherId}")' style="text-align: left">${researcher.title.academicTitleName}/${researcher.academicPosition.positionName}</td>  
               	 		<%-- <td style="text-align: left">${researcher.academicTitle} ${researcher.academicPosition}</td>  --%>  
                		<td onclick='<portlet:namespace/>rederPage("<portlet:renderURL><portlet:param name="action" value="addEditView"/><portlet:param name="mode" value="edit"/><portlet:param name="researcherId"><jsp:attribute name="value"><c:out value="${researcher.researcherId}"/></jsp:attribute></portlet:param></portlet:renderURL>","${researcher.researcherId}")' style="text-align: left">${researcher.nameThai}  ${researcher.surnameThai}</td>  
                		<td onclick='<portlet:namespace/>rederPage("<portlet:renderURL><portlet:param name="action" value="addEditView"/><portlet:param name="mode" value="edit"/><portlet:param name="researcherId"><jsp:attribute name="value"><c:out value="${researcher.researcherId}"/></jsp:attribute></portlet:param></portlet:renderURL>","${researcher.researcherId}")' style="text-align: left">${researcher.nameEng}  ${researcher.surnameEng}</td>  
                				<fmt:formatDate type="time" value="${researcher.createdDate}"   pattern="dd/MM/yyyy" var="createdDate" /> 
                		<td onclick='<portlet:namespace/>rederPage("<portlet:renderURL><portlet:param name="action" value="addEditView"/><portlet:param name="mode" value="edit"/><portlet:param name="researcherId"><jsp:attribute name="value"><c:out value="${researcher.researcherId}"/></jsp:attribute></portlet:param></portlet:renderURL>","${researcher.researcherId}")' style="text-align: left">${createdDate} </td>  
                		 <fmt:formatDate type="time" value="${researcher.updatedDate}"   pattern="dd/MM/yyyy" var="updatedDate" /> 
                		<td onclick='<portlet:namespace/>rederPage("<portlet:renderURL><portlet:param name="action" value="addEditView"/><portlet:param name="mode" value="edit"/><portlet:param name="researcherId"><jsp:attribute name="value"><c:out value="${researcher.researcherId}"/></jsp:attribute></portlet:param></portlet:renderURL>","${researcher.researcherId}")' style="text-align: left">${updatedDate} </td> 
                		<td>
                		<div class="btn-group">
                			<a class="btn btn-primary dropdown-toggle" data-toggle="dropdown" href="#">
    							<i class="icon-align-justify icon-white"></i>  Action
    							<span class="caret"></span>
  							</a>
  							<ul class="dropdown-menu">
   								<li><a onclick='<portlet:namespace/>rederPage("<portlet:renderURL><portlet:param name="action" value="addEditView"/><portlet:param name="mode" value="edit"/><portlet:param name="researcherId"><jsp:attribute name="value"><c:out value="${researcher.researcherId}"/></jsp:attribute></portlet:param></portlet:renderURL>","${researcher.researcherId}")'><i class="icon-edit"></i> Edit</a></li>
    							
    							<li><a onclick='return <portlet:namespace />confirmDelete("<portlet:actionURL><portlet:param name="action" value="delete"/><portlet:param name="researcherId"><jsp:attribute name="value"><c:out value="${researcher.researcherId}"/></jsp:attribute></portlet:param></portlet:actionURL>")'><i class="icon-trash"></i> Delete</a></li>
  							</ul>
						</div>
<%--
                  				<button onclick='<portlet:namespace/>rederPage("<portlet:renderURL><portlet:param name="action" value="addEditView"/><portlet:param name="mode" value="edit"/><portlet:param name="researcherId"><jsp:attribute name="value"><c:out value="${researcher.researcherId}"/></jsp:attribute></portlet:param></portlet:renderURL>","${researcher.researcherId}")'  class="btn btn-small" type="button">คัดลอก</button>
 						   	 <button onclick='return <portlet:namespace />confirmDelete("<portlet:actionURL><portlet:param name="action" value="delete"/><portlet:param name="researcherId"><jsp:attribute name="value"><c:out value="${researcher.researcherId}"/></jsp:attribute></portlet:param></portlet:actionURL>")'  class="btn btn-danger btn-small" type="button">ลบ</button>
 	--%>
 						<%--
                  			<i onclick='<portlet:namespace/>rederPage("<portlet:renderURL><portlet:param name="action" value="addEditView"/><portlet:param name="mode" value="edit"/><portlet:param name="researcherId"><jsp:attribute name="value"><c:out value="${researcher.researcherId}"/></jsp:attribute></portlet:param></portlet:renderURL>","${researcher.researcherId}")' class="icon-edit"></i>
                			<i  onclick='return <portlet:namespace />confirmDelete("<portlet:actionURL><portlet:param name="action" value="delete"/><portlet:param name="researcherId"><jsp:attribute name="value"><c:out value="${researcher.researcherId}"/></jsp:attribute></portlet:param></portlet:actionURL>")' class="icon-trash"></i></td>
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
    			 <c:if test="${researcherMasterForm.pageNo!=1}">
    				<li><a style="cursor: pointer;" onclick='<portlet:namespace/>goPrev()'>Prev</a></li>
    			</c:if>
    			 <c:if test="${not empty researcherMasterForm.pageCount}">
                     <c:forEach begin="${researcherMasterForm.pageStart}" end="${researcherMasterForm.pageEnd}"  var="loop">
                 	<c:if test="${researcherMasterForm.pageNo==(loop)}">
                 		<li><a class="active" style="background-color: #ddd" >${loop}</a></li>
                 	</c:if> 
                 	<c:if test="${researcherMasterForm.pageNo!=(loop)}">
                 		<li><a class="active" style="cursor: pointer;" onclick='<portlet:namespace/>goToPage("${loop}")'>${loop}</a></li>
                 	</c:if> 
                </c:forEach>
                <c:if test="${researcherMasterForm.pageEnd<researcherMasterForm.pageCount}">
                 		<li><a class="active" style="cursor: pointer;" onclick='<portlet:namespace/>goToPage("${researcherMasterForm.pageEnd}")'>...</a></li>
                 		<li><a class="active" style="cursor: pointer;" onclick='<portlet:namespace/>goToPage("${researcherMasterForm.pageCount}")'>${researcherMasterForm.pageCount}</a></li>
                 </c:if>
                 </c:if>
                 <c:if test="${researcherMasterForm.pageNo!=researcherMasterForm.pageCount}">
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
           		  $( this ).prop( "checked", obj.checked );
           		});
           		 
           }
      </script>  
  </body>
</html>	
