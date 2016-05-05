<%@ include file="/WEB-INF/jsp/include.jsp" %>
<%@ page contentType="text/html; charset=utf-8" %> 
<%@page import="javax.portlet.PortletURL"%>
 
 <script  src="<c:url value="/dwr/engine.js"/>"></script>  
<script  src="<c:url value="/dwr/util.js"/>"></script>  
<script  src="<c:url value="/dwr/interface/ResearchAjax.js"/>"></script>
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
		 var form = document.forms['fundingResourceTypeMasterForm'];
		 $("#<portlet:namespace/>mode").val('deleteItems');
		 form.submit();
	}
	else{
		return false ;
	}
	
}
function <portlet:namespace />doSubmitForm(){
    var fundingResourceType={
    		frtCode:$('#frtCode').val()
    }
   // alert(researchGroup.groupCode)
   if($("#<portlet:namespace/>mode").val()=='add'){
	  ResearchAjax.checkUQFundingResourceType(fundingResourceType,  function(data) {
	     if(data>0){
	    	 alert(" ข้อมูลซ้ำ  ")
	    	 $('#frtCode').focus()
	    	 return false;
	     }else{
	    	 <portlet:namespace />doSubmitFormValidated();
	     }
	    	 
	  });
   }else{
   	<portlet:namespace />doSubmitFormValidated();
   }
}
function <portlet:namespace />doSubmitFormValidated(){
	 var form = document.forms['fundingResourceTypeMasterForm'];
	 form.submit();
}
function <portlet:namespace />doSubmitPaging(){
	 var form = document.forms['fundingResourceTypeMasterForm'];
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
function <portlet:namespace/>doAction(){ 
	return true;
}                                                  
</script>
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
    <title>Finance Resource Type</title>

    <!-- Bootstrap core CSS --> 
    <link rel="stylesheet" href="<c:url value="/resources/bootstrap/css/bootstrap.min.css"/>" type="text/css"/>
     <link rel="stylesheet" href="<c:url value="/resources/css/jquery.fileupload.css"/>" type="text/css"/>
    <style type="text/css">
    #breadcrumbs { display:none; } 
    </style>
  </head>

  <body>
  <form:form  id="fundingResourceTypeMasterForm" modelAttribute="fundingResourceTypeMasterForm" method="post"  name="fundingResourceTypeMasterForm" action="${formAction}" enctype="multipart/form-data">
   <fieldset style="font-family: sans-serif;padding-top:5px"> 
            <input type="hidden" name="command" id="<portlet:namespace />common" value="${fundingResourceTypeMasterForm.command}" />
              <input type="hidden" name="mode" id="<portlet:namespace />mode"  value="${fundingResourceTypeMasterForm.mode}" />
                <input type="hidden" name="pageNo"  id="<portlet:namespace />pageNo"  value="${fundingResourceTypeMasterForm.pageNo}" />
            <input type="hidden" id="<portlet:namespace />pageCount" value="${fundingResourceTypeMasterForm.pageCount}" />
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
                <td align="center" width="100%" colspan="2">
                 <div id="<portlet:namespace />_success_alert" class="alert alert-success" style="display: none">
  <button type="button" class="close" data-dismiss="alert">&times;</button>
  <strong>Import Success !</strong>
</div>  
                 <div id="<portlet:namespace/>element_edit" style="border: 1px solid #FFC299;background: #F9F9F9;padding: 10px; display: none">
                 <table border="0" width="100%" style="border: 0px solid #FFC299;font-size:13px">
                  <input type="hidden" id="<portlet:namespace />_export_csv"/> 
                    	  <input type="hidden" id="<portlet:namespace />_export_xml"/> 
                    	   <input type="hidden" id="<portlet:namespace />_import_csv"/>
                    	    <input type="hidden" id="<portlet:namespace />_import_xml"/>
                 <tr>
              		<td align="right" width="20%">
                        <span>
                        	<label>รหัสประเภทแหล่งทุน:<span style="color: red;font-size: 50;"> </span> 
                        	</label>
                        </span> 
             	 	</td>
              	 	<td width="80%" colspan="3">
                    	<div>
                    	 <form:input path="fundingResourceTypeM.frtCode" cssStyle="display:inline;width:50px" cssClass="form-control"  id="frtCode" placeholder=""/>
                       </div>
              		</td> 
            	</tr> 
            	  <tr>
              		<td align="right" width="20%">
                        <span>
                        	<label>ชื่อประเภทแหล่งทุน:<span style="color: red;font-size: 50;"> </span> 
                        	</label>
                        </span> 
             	 	</td>
              	 	<td width="80%" colspan="3">
                    	<div>
                    	 <form:hidden path="fundingResourceTypeM.fundingResourceTypeId"  id="fundingResourceTypeId"/>
                    	 <form:input path="fundingResourceTypeM.frtName" cssStyle="display:inline;width:400px" cssClass="form-control"  id="frtName" placeholder=""/>
                       </div>
              		</td> 
            	</tr> 
            	  <tr>
              		<td align="right" width="20%">
                        <span>
                        	<label>ในประเทศ / ต่างประเทศ:<span style="color: red;font-size: 50;"> </span> 
                        	</label>
                        </span> 
             	 	</td>
              	 	<td width="80%" colspan="3">
                    	<div> <%--	
                    	<form:radiobutton path="fundingResourceTypeM.frtSource" value="I" cssClass="form-control"/>ในประเทศ&nbsp;&nbsp;&nbsp;
						<form:radiobutton path="fundingResourceTypeM.frtSource" value="E" cssClass="form-control"/>ต่างประเทศ
						 --%>
						 <form:radiobutton path="fundingResourceTypeM.frtSource" value="0" cssClass="form-control"/>ในประเทศ&nbsp;&nbsp;&nbsp;
						<form:radiobutton path="fundingResourceTypeM.frtSource" value="1" cssClass="form-control"/>ต่างประเทศ
                       </div>
              		</td> 
            	</tr> 
              <tr>
              <td align="center" colspan="4" width="20%">
           <button type="button" onclick="<portlet:namespace />doSubmitForm()" class="btn btn-primary">Submit</button>
               			 <span  id="<portlet:namespace/>export_import_element" style="display: none">
               			<button type="button" onclick="<portlet:namespace />exportFile('csv')" class="btn">Export to CSV</button>
               			<span class="btn fileinput-button">
        					<span>Import from CSV</span>
        					<input id="<portlet:namespace />_import_csv_bt" type="file" name="import_csv" multiple>
    					</span>
    					<span id="csv_loading" style="display: none">
    						<img style="width:30px;" src="<c:url value="/resources/images/loading_file.gif"/>"/>
    					</span>
               			<button type="button" onclick="<portlet:namespace />exportFile('xml')" class="btn">Export to XML</button>
               			<span class="btn fileinput-button">
        					<span>Import from XML</span>
        					<input id="<portlet:namespace />_import_xml_bt" type="file" name="import_xml" multiple>
    					</span>
    					<span id="xml_loading" style="display: none">
    						<img style="width:30px;" src="<c:url value="/resources/images/loading_file.gif"/>"/>
    					</span>
               		</span>
              </td>
            </tr> 
            </table>
                      
                 </div>
                </td>
                </tr>
                
                <tr>
                <td align="left" width="30%">  
                <button  onclick="<portlet:namespace />displayElementEdit('add','0','0')" type="button" class="btn btn-primary">Add</button> 
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
                   <th width="20%"><div class="th_class">รหัสประเภทแหล่งทุน</div></th>  
                    <th width="38%"><div class="th_class">ชื่อประเภทแหล่งทุน</div></th>  
                    <th width="19%"><div class="th_class">ในประเทศ / ต่างประเทศ</div></th>   
                     <th width="5%"><div class="th_class">Created</div></th>  
                      <th width="5%"><div class="th_class">Updated</div></th>        
                <th width="11%"><div class="th_class"></div></th>  
                   </tr> 
                </thead> 
                <tbody>    
                <c:if test="${not empty fundingResourceTypes}"> 
                 <c:forEach items="${fundingResourceTypes}" var="fundingResourceType" varStatus="loop"> 
               		 <tr style="cursor: pointer;">
                		 <td style="text-align: left"><form:checkbox path="ids" id="${ns}_x_${fundingResourceType.fundingResourceTypeId}" value="${fundingResourceType.fundingResourceTypeId}"/></td>  
                		<td onclick='<portlet:namespace />displayElementEdit("edit","<portlet:resourceURL  id="research_group_resource_get_byid"><portlet:param name="fundingResourceTypeId"><jsp:attribute name="value"><c:out value="${fundingResourceType.fundingResourceTypeId}"/></jsp:attribute></portlet:param></portlet:resourceURL>","${fundingResourceType.fundingResourceTypeId}")' style="text-align: left">${fundingResourceType.frtCode}</td>  
               	 		<td onclick='<portlet:namespace />displayElementEdit("edit","<portlet:resourceURL  id="research_group_resource_get_byid"><portlet:param name="fundingResourceTypeId"><jsp:attribute name="value"><c:out value="${fundingResourceType.fundingResourceTypeId}"/></jsp:attribute></portlet:param></portlet:resourceURL>","${fundingResourceType.fundingResourceTypeId}")' style="text-align: left">${fundingResourceType.frtName}</td>  
               	 			
                		<td onclick='<portlet:namespace />displayElementEdit("edit","<portlet:resourceURL  id="research_group_resource_get_byid"><portlet:param name="fundingResourceTypeId"><jsp:attribute name="value"><c:out value="${fundingResourceType.fundingResourceTypeId}"/></jsp:attribute></portlet:param></portlet:resourceURL>","${fundingResourceType.fundingResourceTypeId}")' style="text-align: left">
                		<%--
                		<c:if test="${fundingResourceType.frtSource=='I'}">
                		ในประเทศ
                		</c:if>
                		<c:if test="${fundingResourceType.frtSource=='E'}">
                		ต่างประเทศ
                		</c:if>
                		 --%>
                		 <c:if test="${fundingResourceType.frtSource=='0'}">
                		ในประเทศ
                		</c:if>
                		<c:if test="${fundingResourceType.frtSource=='1'}">
                		ต่างประเทศ
                		</c:if>
                		</td>  
                		<fmt:formatDate type="time" value="${fundingResourceType.createdDate}"   pattern="dd/MM/yyyy" var="createdDate" /> 
                		<td onclick='<portlet:namespace />displayElementEdit("edit","<portlet:resourceURL  id="research_group_resource_get_byid"><portlet:param name="fundingResourceTypeId"><jsp:attribute name="value"><c:out value="${fundingResourceType.fundingResourceTypeId}"/></jsp:attribute></portlet:param></portlet:resourceURL>","${fundingResourceType.fundingResourceTypeId}")' style="text-align: left">${createdDate} </td>  
                		 <fmt:formatDate type="time" value="${fundingResourceType.updatedDate}"   pattern="dd/MM/yyyy" var="updatedDate" /> 
                		<td onclick='<portlet:namespace />displayElementEdit("edit","<portlet:resourceURL  id="research_group_resource_get_byid"><portlet:param name="fundingResourceTypeId"><jsp:attribute name="value"><c:out value="${fundingResourceType.fundingResourceTypeId}"/></jsp:attribute></portlet:param></portlet:resourceURL>","${fundingResourceType.fundingResourceTypeId}")' style="text-align: left">${updatedDate} </td>  
                		<td>
                		<div class="btn-group">
                			<a class="btn btn-primary dropdown-toggle" data-toggle="dropdown" href="#">
    							<i class="icon-align-justify icon-white"></i>  Action
    							<span class="caret"></span>
  							</a>
  							<ul class="dropdown-menu">
   								<li><a onclick='<portlet:namespace />displayElementEdit("edit","<portlet:resourceURL  id="research_group_resource_get_byid"><portlet:param name="fundingResourceTypeId"><jsp:attribute name="value"><c:out value="${fundingResourceType.fundingResourceTypeId}"/></jsp:attribute></portlet:param></portlet:resourceURL>","${fundingResourceType.fundingResourceTypeId}")'><i class="icon-edit"></i> Edit</a></li>
    							<li><a onclick='<portlet:namespace />displayElementEdit("copy","<portlet:resourceURL  id="research_group_resource_get_byid"><portlet:param name="fundingResourceTypeId"><jsp:attribute name="value"><c:out value="${fundingResourceType.fundingResourceTypeId}"/></jsp:attribute></portlet:param></portlet:resourceURL>","${fundingResourceType.fundingResourceTypeId}")' ><i class="icon-plus-sign"></i> Copy</a></li>
    							<li><a onclick='return <portlet:namespace />confirmDelete("<portlet:actionURL><portlet:param name="action" value="delete"/><portlet:param name="fundingResourceTypeId"><jsp:attribute name="value"><c:out value="${fundingResourceType.fundingResourceTypeId}"/></jsp:attribute></portlet:param></portlet:actionURL>")'  ><i class="icon-trash"></i> Delete</a></li>
  							</ul>
						</div>
<%--
                  				<button onclick='displayElementEdit("edit","<portlet:resourceURL  id="research_group_resource_get_byid"><portlet:param name="fundingResourceTypeId"><jsp:attribute name="value"><c:out value="${fundingResourceType.fundingResourceTypeId}"/></jsp:attribute></portlet:param></portlet:resourceURL>","${fundingResourceType.fundingResourceTypeId}")' class="btn btn-small" type="button">คัดลอก</button>
 						   	 <button  onclick='return <portlet:namespace />confirmDelete("<portlet:actionURL><portlet:param name="action" value="delete"/><portlet:param name="fundingResourceTypeId"><jsp:attribute name="value"><c:out value="${fundingResourceType.fundingResourceTypeId}"/></jsp:attribute></portlet:param></portlet:actionURL>")' class="btn btn-danger btn-small" type="button">ลบ</button>
 	--%>
 			 <%--
                  			<i onclick='displayElementEdit("edit","<portlet:resourceURL  id="research_group_resource_get_byid"><portlet:param name="fundingResourceTypeId"><jsp:attribute name="value"><c:out value="${fundingResourceType.fundingResourceTypeId}"/></jsp:attribute></portlet:param></portlet:resourceURL>","${fundingResourceType.fundingResourceTypeId}")' class="icon-edit"></i>
                			<i  onclick='return <portlet:namespace />confirmDelete("<portlet:actionURL><portlet:param name="action" value="delete"/><portlet:param name="fundingResourceTypeId"><jsp:attribute name="value"><c:out value="${fundingResourceType.fundingResourceTypeId}"/></jsp:attribute></portlet:param></portlet:actionURL>")' class="icon-trash"></i></td>
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
    			 <c:if test="${fundingResourceTypeMasterForm.pageNo!=1}">
    				<li><a style="cursor: pointer;" onclick='<portlet:namespace/>goPrev()'>Prev</a></li>
    			</c:if>
    			 <c:if test="${not empty fundingResourceTypeMasterForm.pageCount}">
                     <c:forEach begin="${fundingResourceTypeMasterForm.pageStart}" end="${fundingResourceTypeMasterForm.pageEnd}"  var="loop">
                 	<c:if test="${fundingResourceTypeMasterForm.pageNo==(loop)}">
                 		<li><a class="active" style="background-color: #ddd" >${loop}</a></li>
                 	</c:if> 
                 	<c:if test="${fundingResourceTypeMasterForm.pageNo!=(loop)}">
                 		<li><a class="active" style="cursor: pointer;" onclick='<portlet:namespace/>goToPage("${loop}")'>${loop}</a></li>
                 	</c:if> 
                </c:forEach>
                <c:if test="${fundingResourceTypeMasterForm.pageEnd<fundingResourceTypeMasterForm.pageCount}">
                 		<li><a class="active" style="cursor: pointer;" onclick='<portlet:namespace/>goToPage("${fundingResourceTypeMasterForm.pageEnd}")'>...</a></li>
                 		<li><a class="active" style="cursor: pointer;" onclick='<portlet:namespace/>goToPage("${fundingResourceTypeMasterForm.pageCount}")'>${fundingResourceTypeMasterForm.pageCount}</a></li>
                 </c:if>
                 </c:if>
                 <c:if test="${fundingResourceTypeMasterForm.pageNo!=fundingResourceTypeMasterForm.pageCount}">
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
        function <portlet:namespace />displayElementEdit(mode,url,id){
    	   $("#frtCode").val('');
    	   $("#frtName").val('');
    	   $('input[name="fundingResourceTypeM.frtSource"][value="I"]').prop('checked',true);
    	   $("#<portlet:namespace/>mode").val(mode);
       	   $("#<portlet:namespace/>command").val("list");
       	 $("#<portlet:namespace/>export_import_element").hide();
  	   if(mode=='edit'){
  		   $("#<portlet:namespace/>export_import_element").show(); 
    	      	    $.ajax({
    	      	  	url: url , 
    	      	     	 data: { fundingResourceTypeId: id }, 
    	      	         success: function(data){
    	      	        	$('#frtCode').prop('readonly',true);
    	      	        	 $("#fundingResourceTypeId").val(id)
    	      	        	 $("#frtCode").val(data.frtCode)
    		  				 $("#frtName").val(data.frtName);
    	      	        	$("#<portlet:namespace />_export_csv").val(data.csvResource);
    		   				$("#<portlet:namespace />_export_xml").val(data.xmlResource);
    		   				$("#<portlet:namespace />_import_csv").val(data.csvUploadResource);
    		   				$("#<portlet:namespace />_import_xml").val(data.xmlUploadResource);
    		   				<portlet:namespace/>initUpload("<portlet:namespace />_import_csv_bt",data.csvUploadResource,'csv');
    		   				<portlet:namespace/>initUpload("<portlet:namespace />_import_xml_bt",data.xmlUploadResource,'xml');
    	      	        	//$('input[name="fundingResourceTypeM.frtSource"]').val(data.frtSource);
    	      	        	$('input[name="fundingResourceTypeM.frtSource"][value="'+data.frtSource+'"]').prop('checked',true);
    		   				$( "#<portlet:namespace/>element_edit" ).show( "slow");
    	      	        }
    	      	    }); 
    		  
    		
    	   }else if(mode=='copy'){
      		   $("#<portlet:namespace/>export_import_element").show(); 
      		 $("#<portlet:namespace/>mode").val('add');
	      	    $.ajax({
	      	  	url: url , 
	      	     	 data: { fundingResourceTypeId: id }, 
	      	         success: function(data){
	      	        	$('#frtCode').prop('readonly',false);
	      	        	// $("#fundingResourceTypeId").val(id)
	      	        	 //$("#frtCode").val(data.frtCode)
		  				 $("#frtName").val(data.frtName);
	      	        
	      	        	//$('input[name="fundingResourceTypeM.frtSource"]').val(data.frtSource);
	      	        	$('input[name="fundingResourceTypeM.frtSource"][value="'+data.frtSource+'"]').prop('checked',true);
		   				$( "#<portlet:namespace/>element_edit" ).show( "slow");
	      	        }
	      	    }); 
		  
		
	   }else{
    		   $('#frtCode').prop('readonly',false);
    		   $( "#<portlet:namespace/>element_edit" ).show( "slow");   
    	   }
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
           function <portlet:namespace/>exportFile(type){
       		var src = $("#<portlet:namespace />_export_"+type).val();
       		  var div = document.createElement("div");
       		     document.body.appendChild(div);
       		    div.innerHTML = "<iframe width='0' height='0' scrolling='no' frameborder='0' src='" + src + "'></iframe>";
       		}
          
          function <portlet:namespace/>initUpload(_element,_url,_type){
               var acceptFileTypes;
               if(_type=='csv')
               	acceptFileTypes=/(\.|\/)(csv)$/i;
               if(_type=='xml')
               	acceptFileTypes=/(\.|\/)(xml)$/i;
             $('#'+_element).fileupload({
           	  add: function(e, data) {
           	        var uploadErrors = [];
           	        if(data.originalFiles[0]['type'].length && !acceptFileTypes.test(data.originalFiles[0]['type'])) {
           	            uploadErrors.push('Not an accepted file type');
           	        }
           	        if(data.originalFiles[0]['size'].length && data.originalFiles[0]['size'] > 5000000) {
           	            uploadErrors.push('Filesize is too big');
           	        }
           	        if(uploadErrors.length > 0) {
           	            alert(uploadErrors.join("\n"));
           	        } else {
           	            data.submit();
           	        }
           	}, 
                 url: _url,
                 dataType: 'json',
                 submit:function (e, data) {
               	   <portlet:namespace />showUploading(_type+"_loading",true);
                 },
                 done: function (e, data) {
                  $("#<portlet:namespace />_success_alert").show('slow');// alert alert-success ,  alert alert-info , alert alert-error
                  <portlet:namespace />showUploading(_type+"_loading",false);
                 },
                 progressall: function (e, data) {
               	  //alert(data.loaded)
                     var progress = parseInt(data.loaded / data.total * 100, 10);
                     $('#progress .progress-bar').css(
                         'width',
                         progress + '%'
                     );
                 }
             }).prop('disabled', !$.support.fileInput)
             	.parent().addClass($.support.fileInput ? undefined : 'disabled');
          }
          function <portlet:namespace />showUploading(element_,isShow){
             	if(isShow)
             		$("#"+element_).show("slow");
             	else
             		$("#"+element_).hide("slow");
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
