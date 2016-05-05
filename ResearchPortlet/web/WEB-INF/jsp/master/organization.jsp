<%@ include file="/WEB-INF/jsp/include.jsp" %>
<%@ page contentType="text/html; charset=utf-8" %> 
<%@page import="javax.portlet.PortletURL"%>
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
		 var form = document.forms['organizationMasterForm'];
		 $("#<portlet:namespace/>mode").val('deleteItems');
		 form.submit();
	}
	else{
		return false ;
	}
	
}
function <portlet:namespace />doSubmitForm(){
	 var form = document.forms['organizationMasterForm'];
	 form.submit();
}
function <portlet:namespace />doSubmitPaging(){
	 var form = document.forms['organizationMasterForm'];
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

    <title>Organization</title>

    <!-- Bootstrap core CSS --> 
    <link rel="stylesheet" href="<c:url value="/resources/bootstrap/css/bootstrap.min.css"/>" type="text/css"/>
     <link rel="stylesheet" href="<c:url value="/resources/css/jquery.fileupload.css"/>" type="text/css"/>
    <style type="text/css">
    #breadcrumbs { display:none; } 
    </style>
  </head>

  <body>
<form:form  id="organizationMasterForm" modelAttribute="organizationMasterForm" method="post"  name="organizationMasterForm" action="${formAction}" enctype="multipart/form-data">
   <fieldset style="font-family: sans-serif;padding-top:5px"> 
           <input type="hidden" name="command" id="<portlet:namespace />common" value="${organizationMasterForm.command}" />
              <input type="hidden" name="mode" id="<portlet:namespace />mode"  value="${organizationMasterForm.mode}" />
                <input type="hidden" name="pageNo"  id="<portlet:namespace />pageNo"  value="${organizationMasterForm.pageNo}" />
            <input type="hidden" id="<portlet:namespace />pageCount" value="${organizationMasterForm.pageCount}" />
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
                <form class="form-inline" role="form">
  <div class="form-group has-success has-feedback">
    <label class="control-label" for="inputSuccess4">ระบุ key word</label>
  	  <form:input path="keySearch" cssStyle="display:inline;width:250px" cssClass="form-control"  id="keySearch" aria-describedby="inputSuccess4Status"/>
      <button onclick="<portlet:namespace />doSearch()" type="button" class="btn btn-success" style="margin-top:-8px;">Search</button>
  </div>
</form>
</span>
                </td> 
                </tr>
                <tr>
                <td align="center" width="100%" colspan="2">
                 <div id="<portlet:namespace />_success_alert" class="alert alert-success" style="display: none">
  <button type="button" class="close" data-dismiss="alert">&times;</button>
  <strong>Import Success !</strong>
</div>  
                 <div id="<portlet:namespace/>element_edit" style="border: 1px solid #FFC299;background: #F9F9F9;padding: 10px;display: none">
                 <table border="0" width="100%" style="border: 0px solid #FFC299;font-size:13px">
        			 <input type="hidden" id="<portlet:namespace />_export_csv"/> 
                    	  <input type="hidden" id="<portlet:namespace />_export_xml"/> 
                    	   <input type="hidden" id="<portlet:namespace />_import_csv"/>
                    	    <input type="hidden" id="<portlet:namespace />_import_xml"/> 
                 <tr>
              		<td align="right" width="20%">
                        <span>
                        	<label>รหัสวิทยาเขต:<span style="color: red;font-size: 50;"> </span> 
                        	</label>
                        </span> 
             	 	</td>
              	 	<td width="80%" colspan="3">
                    	<div> 
                    	<form:input path="organizationM.orgCampusCode" cssStyle="display:inline;width:50px" cssClass="form-control"  id="orgCampusCode" placeholder=""/>   
                       </div>
              		</td> 
            	</tr> 
            	  <tr>
              		<td align="right" width="20%">
                        <span>
                        	<label>รหัส คณะ/สำนัก/สถาบัน:<span style="color: red;font-size: 50;"> </span> 
                        	</label>
                        </span> 
             	 	</td>
              	 	<td width="80%" colspan="3">
                    	<div> 
                    	 <form:hidden path="organizationM.organizationId"  id="organizationId"/>
                    	 <form:input path="organizationM.orgInstitutionCode" cssStyle="display:inline;width:400px" cssClass="form-control"  id="orgInstitutionCode" placeholder=""/>
                       </div>
              		</td> 
            	</tr>   
            	 <tr>
              		<td align="right" width="20%">
                        <span>
                        	<label>ภาควิชา/ฝ่าย/กอง:<span style="color: red;font-size: 50;"> </span> 
                        	</label>
                        </span> 
             	 	</td>
              	 	<td width="80%" colspan="3">
                    	<div> 
                    	 <form:input path="organizationM.orgDeptCode" cssStyle="display:inline;width:400px" cssClass="form-control"  id="orgDeptCode" placeholder=""/>
                       </div>
              		</td> 
            	</tr>  
            	<tr>
              		<td align="right" width="20%">
                        <span>
                        	<label>รหัสงาน:<span style="color: red;font-size: 50;"> </span> 
                        	</label>
                        </span> 
             	 	</td>
              	 	<td width="80%" colspan="3">
                    	<div> 
                    	 <form:input path="organizationM.orgWorkCode" cssStyle="display:inline;width:400px" cssClass="form-control"  id="orgWorkCode" placeholder=""/>
                       </div>
              		</td> 
            	</tr>  
            	<tr>
              		<td align="right" width="20%">
                        <span>
                        	<label>ชื่อหน่วยงานมจธ. ที่อยู่นอกสทบ.:<span style="color: red;font-size: 50;"> </span> 
                        	</label>
                        </span> 
             	 	</td>
              	 	<td width="80%" colspan="3">
                    	<div> 
                    	 <form:input path="organizationM.orgName" cssStyle="display:inline;width:400px" cssClass="form-control"  id="orgName" placeholder=""/>
                       </div>
              		</td> 
            	</tr>  
            	<tr>
              		<td colspan="4" width="100%"  >
                        <span>
                        	<label>
                        	<span>
						  		<textarea rows="5" cols="3" style="width: 100%;" readonly="readonly">
						  		(หมายเหตุ : การเพิ่มหน่วยงานภายนอกสทบ. เข้าระบบเพื่อความสมบูรณ์ของข้อมูล ต้องเพิ่มหน่วยงานในระดับที่สูงกว่าก่อน เช่น
						  		สมมุติว่าในระบบเรามีข้อมูลดังนี้แล้ว
						  		รหัส 10000000 มจธ.บางมด
						  		รหัส 10500000 สำนักงานวิจัยและบริการวิทยาศาตร์และเทคโนโลยี่
						  		
						  		และเราต้องการเพิ่มหน่วยงานในระดับ "งาน" เข้าระบบ
						  		ดังนี้ 10577771 งานyyyyyyyyyy
						  		
						  		หากเพิ่มข้อมูลนี้เข้าระบบ จะทำให้ข้อมูลหน่วยงานในระดับของ "ภาควิชา/ฝ่าย/กอง" ขาดหายไป
						  		ดังนั้นจึงจำเป็นที่ท่านต้องเพิ่มหน่วยงานที่ระดับของ "ภาควิชา/ฝ่าย/กอง" เข้าไปก่อน
						  		ดังนี้ 10577000 ฝ่ายxxxxxxx แล้วจึงเพิ่ม
						  		10577771 งานyyyyyyyyyy เข้าไป ข้อมูลในระบบจึงจะครบถ้วนสมบูรณ์)
						  		</textarea> 
						     </span> 
                        	</label>
                        </span> 
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
                   <th width="30%"><div class="th_class">รหัส</div></th>  
                    <th width="47%"><div class="th_class">ชื่อหน่วยงานมจธ. ที่อยู่นอกสทบ.</div></th> 
                        <th width="5%"><div class="th_class">Created</div></th>  
                      <th width="5%"><div class="th_class">Updated</div></th>     
                   <th width="11%"><div class="th_class"></div></th>  
                   </tr> 
                </thead> 
                <tbody>    
                <c:if test="${not empty organizations}"> 
                 <c:forEach items="${organizations}" var="organization" varStatus="loop"> 
               		 <tr style="cursor: pointer;">
               		 <td style="text-align: left"><form:checkbox path="ids" id="${ns}_x_${organization.organizationId}" value="${organization.organizationId}"/></td>  
               		    <td onclick='<portlet:namespace />displayElementEdit("edit","<portlet:resourceURL  id="research_group_resource_get_byid"><portlet:param name="organizationId"><jsp:attribute name="value"><c:out value="${organization.organizationId}"/></jsp:attribute></portlet:param></portlet:resourceURL>","${organization.organizationId}")' style="text-align: left">${organization.orgCampusCode}${organization.orgInstitutionCode}${organization.orgDeptCode}${organization.orgWorkCode}</td>  
               	 		<td onclick='<portlet:namespace />displayElementEdit("edit","<portlet:resourceURL  id="research_group_resource_get_byid"><portlet:param name="organizationId"><jsp:attribute name="value"><c:out value="${organization.organizationId}"/></jsp:attribute></portlet:param></portlet:resourceURL>","${organization.organizationId}")' style="text-align: left">${organization.orgName}</td>  
               	 				<fmt:formatDate type="time" value="${organization.createdDate}"   pattern="dd/MM/yyyy" var="createdDate" /> 
                		<td onclick='<portlet:namespace />displayElementEdit("edit","<portlet:resourceURL  id="research_group_resource_get_byid"><portlet:param name="organizationId"><jsp:attribute name="value"><c:out value="${organization.organizationId}"/></jsp:attribute></portlet:param></portlet:resourceURL>","${organization.organizationId}")' style="text-align: left">${createdDate} </td>  
                		 <fmt:formatDate type="time" value="${organization.updatedDate}"   pattern="dd/MM/yyyy" var="updatedDate" /> 
                		<td onclick='<portlet:namespace />displayElementEdit("edit","<portlet:resourceURL  id="research_group_resource_get_byid"><portlet:param name="organizationId"><jsp:attribute name="value"><c:out value="${organization.organizationId}"/></jsp:attribute></portlet:param></portlet:resourceURL>","${organization.organizationId}")' style="text-align: left">${updatedDate} </td> 
                		<td>
                		<div class="btn-group">
                			<a class="btn btn-primary dropdown-toggle" data-toggle="dropdown" href="#">
    							<i class="icon-align-justify icon-white"></i>  Action
    							<span class="caret"></span>
  							</a>
  							<ul class="dropdown-menu">
   								<li><a onclick='<portlet:namespace />displayElementEdit("edit","<portlet:resourceURL  id="research_group_resource_get_byid"><portlet:param name="organizationId"><jsp:attribute name="value"><c:out value="${organization.organizationId}"/></jsp:attribute></portlet:param></portlet:resourceURL>","${organization.organizationId}")'><i class="icon-edit"></i> Edit</a></li>
    							<li><a onclick='<portlet:namespace />displayElementEdit("copy","<portlet:resourceURL  id="research_group_resource_get_byid"><portlet:param name="organizationId"><jsp:attribute name="value"><c:out value="${organization.organizationId}"/></jsp:attribute></portlet:param></portlet:resourceURL>","${organization.organizationId}")' ><i class="icon-plus-sign"></i> Copy</a></li>
    							<li><a onclick='return <portlet:namespace />confirmDelete("<portlet:actionURL><portlet:param name="action" value="delete"/><portlet:param name="organizationId"><jsp:attribute name="value"><c:out value="${organization.organizationId}"/></jsp:attribute></portlet:param></portlet:actionURL>")' ><i class="icon-trash"></i> Delete</a></li>
  							</ul>
						</div>
<%--
                  			 <button onclick='displayElementEdit("edit","<portlet:resourceURL  id="research_group_resource_get_byid"><portlet:param name="organizationId"><jsp:attribute name="value"><c:out value="${organization.organizationId}"/></jsp:attribute></portlet:param></portlet:resourceURL>","${organization.organizationId}")'   class="btn btn-small" type="button">คัดลอก</button>
 						   	 <button onclick='return <portlet:namespace />confirmDelete("<portlet:actionURL><portlet:param name="action" value="delete"/><portlet:param name="organizationId"><jsp:attribute name="value"><c:out value="${organization.organizationId}"/></jsp:attribute></portlet:param></portlet:actionURL>")'  class="btn btn-danger btn-small" type="button">ลบ</button>
 	--%>
 			<%--
                  			<i onclick='displayElementEdit("edit","<portlet:resourceURL  id="research_group_resource_get_byid"><portlet:param name="organizationId"><jsp:attribute name="value"><c:out value="${organization.organizationId}"/></jsp:attribute></portlet:param></portlet:resourceURL>","${organization.organizationId}")' class="icon-edit"></i>
                			<i  onclick='return <portlet:namespace />confirmDelete("<portlet:actionURL><portlet:param name="action" value="delete"/><portlet:param name="organizationId"><jsp:attribute name="value"><c:out value="${organization.organizationId}"/></jsp:attribute></portlet:param></portlet:actionURL>")' class="icon-trash"></i></td>
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
    			 <c:if test="${organizationMasterForm.pageNo!=1}">
    				<li><a style="cursor: pointer;" onclick='<portlet:namespace/>goPrev()'>Prev</a></li>
    			</c:if>
    			 <c:if test="${not empty organizationMasterForm.pageCount}">
                     <c:forEach begin="${organizationMasterForm.pageStart}" end="${organizationMasterForm.pageEnd}"  var="loop">
                 	<c:if test="${organizationMasterForm.pageNo==(loop)}">
                 		<li><a class="active" style="background-color: #ddd" >${loop}</a></li>
                 	</c:if> 
                 	<c:if test="${organizationMasterForm.pageNo!=(loop)}">
                 		<li><a class="active" style="cursor: pointer;" onclick='<portlet:namespace/>goToPage("${loop}")'>${loop}</a></li>
                 	</c:if> 
                </c:forEach>
                <c:if test="${organizationMasterForm.pageEnd<organizationMasterForm.pageCount}">
                 		<li><a class="active" style="cursor: pointer;" onclick='<portlet:namespace/>goToPage("${organizationMasterForm.pageEnd}")'>...</a></li>
                 		<li><a class="active" style="cursor: pointer;" onclick='<portlet:namespace/>goToPage("${organizationMasterForm.pageCount}")'>${organizationMasterForm.pageCount}</a></li>
                 </c:if>
                 </c:if>
                 <c:if test="${organizationMasterForm.pageNo!=organizationMasterForm.pageCount}">
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
    <script  src="<c:url value="/resources/bootstrap/js/bootstrap.min.js"/>"></script>  
       <script src="<c:url value='/resources/js/bootbox.min.js'/>" type="text/javascript"></script>
        <script type="text/javascript" src="<c:url value='/resources/js/vendor/jquery.ui.widget.js'/>"></script>
<script type="text/javascript" src="<c:url value='/resources/js/jquery.iframe-transport.js'/>"></script>

  <script type="text/javascript" src="<c:url value='/resources/js/jquery.fileupload.js'/>"></script>   
    
       <script>  
       function <portlet:namespace />displayElementEdit(mode,url,id){
    	 
       	   $("#orgCampusCode").val('');
       	   $("#orgInstitutionCode").val('');
       	   $("#orgDeptCode").val('');
       	   $("#orgWorkCode").val('');
       	   $("#orgName").val('');
       	   $("#<portlet:namespace/>mode").val(mode);
       	   $("#<portlet:namespace/>command").val("list");
       	 $("#<portlet:namespace/>export_import_element").hide();
  	   if(mode=='edit'){
  		   $("#<portlet:namespace/>export_import_element").show(); 
       	      	    $.ajax({
       	      	  	url: url , 
       	      	     	 data: { organizationId: id }, 
       	      	         success: function(data){
       	      	        	 $("#organizationId").val(id)
       	      	        	 $("#orgCampusCode").val(data.orgCampusCode);
       	      	            $("#orgInstitutionCode").val(data.orgInstitutionCode);
       	      	             $("#orgDeptCode").val(data.orgDeptCode);
       	      	           $("#orgWorkCode").val(data.orgWorkCode);
       		   				$("#orgName").val(data.orgName);
       		   			$("#<portlet:namespace />_export_csv").val(data.csvResource);
		   				$("#<portlet:namespace />_export_xml").val(data.xmlResource);
		   				$("#<portlet:namespace />_import_csv").val(data.csvUploadResource);
		   				$("#<portlet:namespace />_import_xml").val(data.xmlUploadResource);
		   				<portlet:namespace/>initUpload("<portlet:namespace />_import_csv_bt",data.csvUploadResource,'csv');
		   				<portlet:namespace/>initUpload("<portlet:namespace />_import_xml_bt",data.xmlUploadResource,'xml');
       		   				$( "#<portlet:namespace/>element_edit" ).show( "slow");
       	      	        }
       	      	    }); 
       	   }else  if(mode=='copy'){
      		   $("#<portlet:namespace/>export_import_element").show(); 
      		 $("#<portlet:namespace/>mode").val('add');
  	      	    $.ajax({
  	      	  	url: url , 
  	      	     	 data: { organizationId: id }, 
  	      	         success: function(data){
  	      	        	// $("#organizationId").val(id)
  	      	        	 //$("#orgCampusCode").val(data.orgCampusCode);
  	      	            $("#orgInstitutionCode").val(data.orgInstitutionCode);
  	      	             $("#orgDeptCode").val(data.orgDeptCode);
  	      	           $("#orgWorkCode").val(data.orgWorkCode);
  		   				$("#orgName").val(data.orgName);
  		   			
  		   				$( "#<portlet:namespace/>element_edit" ).show( "slow");
  	      	        }
  	      	    }); 
  	   }else{
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
