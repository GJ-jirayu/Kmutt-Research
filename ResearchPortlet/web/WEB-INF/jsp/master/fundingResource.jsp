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
		 var form = document.forms['fundingResourceMasterForm'];
		 $("#<portlet:namespace/>mode").val('deleteItems');
		 form.submit();
	}
	else{
		return false ;
	}
	
}
function <portlet:namespace />doSubmitForm(){
    var fundingResource={
    		fundingResourceCode:$('#fundingResourceCode').val()
    }
   // alert(researchGroup.groupCode)
   if($("#<portlet:namespace/>mode").val()=='add'){
	  ResearchAjax.checkUQFundingResource(fundingResource,  function(data) {
	     if(data>0){
	    	 alert(" ข้อมูลซ้ำ  ")
	    	 $('#fundingResourceCode').focus()
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
	 var form = document.forms['fundingResourceMasterForm'];
	 form.submit();
}
function <portlet:namespace />doSubmitPaging(){
	 var form = document.forms['fundingResourceMasterForm'];
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
    <link href="<c:url value='/resources/css/smoothness/jquery-ui-1.9.1.custom.min.css'/>" type="text/css"  rel="stylesheet" /> 
    <link rel="stylesheet" href="<c:url value="/resources/css/jquery.fileupload.css"/>" type="text/css"/>
    <style type="text/css">
    #breadcrumbs { 
    display:none;
     } 
    
   .ui-autocomplete-loading {
    background: white url('<c:url value="/resources/css/smoothness/images/ui-anim_basic_16x16.gif"/>') right center no-repeat;
  } 
  .aoe_small{width: 500px !important;margin-left:-250px}
 /* .aoe_width{width: 1000px !important;margin-left:-500px} */
  .aoe_width{width: 1000px !important;}
  .aui .modal{
  left:30%
  }  
    </style>
  </head>

  <body>
<form:form  id="fundingResourceMasterForm" modelAttribute="fundingResourceMasterForm" method="post"  name="fundingResourceMasterForm" action="${formAction}" enctype="multipart/form-data">
   <fieldset style="font-family: sans-serif;padding-top:5px"> 
           <input type="hidden" name="command" id="<portlet:namespace />common" value="${fundingResourceMasterForm.command}" />
              <input type="hidden" name="mode" id="<portlet:namespace />mode"  value="${fundingResourceMasterForm.mode}" />
                <input type="hidden" name="pageNo"  id="<portlet:namespace />pageNo"  value="${fundingResourceMasterForm.pageNo}" />
            <input type="hidden" id="<portlet:namespace />pageCount" value="${fundingResourceMasterForm.pageCount}" />
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
                        	<label>รหัสแหล่งทุน:<span style="color: red;font-size: 50;"> </span> 
                        	</label>
                        </span> 
             	 	</td>
              	 	<td width="80%" colspan="3">
                    	<div> 
                    	<form:input path="fundingResourceM.fundingResourceCode" cssStyle="display:inline;width:50px" cssClass="form-control"  id="fundingResourceCode" placeholder=""/>   
                       </div>
              		</td> 
            	</tr> 
            	  <tr>
              		<td align="right" width="20%">
                        <span>
                        	<label>รหัสประเภทแหล่งทุน:<span style="color: red;font-size: 50;"> </span> 
                        	</label>
                        </span>
                    
             	 	</td>
              	 	<td width="80%" colspan="3">
                    	<span> 
                    	 <form:hidden path="fundingResourceM.fundingResourceId"  id="fundingResourceId"/>
                    	  <form:hidden path="fundingResourceM.fundingResourceType.fundingResourceTypeId"  id="fundingResourceType.fundingResourceTypeId" />
                    	 <input type="text" id="fundingResourceTypeId_autocomplete" style="display:inline;width:150px;background: rgb(250, 250, 198);" class="form-control"  />
                    	   <button style="margin-top: -8px" onclick="<portlet:namespace />fundingResourceTypePopup('',true,1)" type="button" class="btn btn-warning btn-small">...</button>
                    	<%--
                    	 <form:input path="fundingResourceM.fundingResourceType.fundingResourceTypeId" cssStyle="display:inline;width:50px;background:yellow" cssClass="form-control"  id="fundingResourceType.fundingResourceTypeId" placeholder=""/>
                    	  --%>   
                       </span>
                       <%--
                         <span id=fundingResourceTypeIdShow></span>
                          --%> 
              		</td> 
            	</tr>   
            	 <tr>
              		<td align="right" width="20%">
                        <span>
                        	<label>ชื่อแหล่งทุน (T):<span style="color: red;font-size: 50;"> </span> 
                        	</label>
                        </span>
                      
             	 	</td>
              	 	<td width="80%" colspan="3">
                    	<span> 
                    	<form:textarea path="fundingResourceM.frNameThai"  rows="2" cols="3" cssClass="form-control"  id="frNameThai" placeholder=""/>   
                    
                       </span>
              		</td> 
            	</tr>   
            	 <tr>
              		<td align="right" width="20%">
                        <span>
                        	<label>ชื่อแหล่งทุน (E):<span style="color: red;font-size: 50;"> </span> 
                        	</label>
                        </span>
                      
             	 	</td>
              	 	<td width="80%" colspan="3">
                    	<span> 
                    	 <form:textarea path="fundingResourceM.frNameEng"  rows="2" cols="3" cssClass="form-control"  id="frNameEng" placeholder=""/>   
                       </span>
              		</td> 
            	</tr>     
            	 <tr>
              		<td align="right" width="20%">
                        <span>
                        	<label>ชื่อย่อแหล่งทุน:<span style="color: red;font-size: 50;"> </span> 
                        	</label>
                        </span>
                      
             	 	</td>
              	 	<td width="80%" colspan="3">
                    	<span> 
                    	 <form:input path="fundingResourceM.frShortName" cssStyle="display:inline;" cssClass="form-control"  id="frShortName" placeholder=""/>   
                       </span>
              		</td> 
            	</tr>  
            	  <tr>
              		<td align="right" width="20%">
                        <span>
                        	<label>รหัสประเทศ (เจ้าของแหล่งทุน): <span style="color: red;font-size: 50;"> </span> 
                        	</label>
                        </span>
                      
             	 	</td>
              	 	<td width="80%" colspan="3">
                    	<span> 
                    	  <form:hidden path="fundingResourceM.country.countryId"  id="countryId" />
                    	 <input type="text" id="countryId_autocomplete" style="display:inline;width:150px;background: rgb(250, 250, 198)" class="form-control"  />
                    	   <button style="margin-top: -8px" onclick="<portlet:namespace />countryPopup('',true,1)" type="button" class="btn btn-warning btn-small">...</button>
                    	<%--
                    	 <form:input path="fundingResourceM.country.countryId" cssStyle="display:inline;width:50px;background:yellow" cssClass="form-control"  id="countryId" placeholder=""/>
                    	  --%>   
                       </span>
                       <%--
                         <span id=countryIdShow></span>
                         --%>
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
               <button  onclick="<portlet:namespace/>doDeleteItems()" class="btn btn-danger" type="button">ลบ</button> 
                </td><td align="right" width="70%"> 
                
                </td>
                </tr>
                </tbody></table>  
                <div  id="search_section_application"> 
                    <table class="table-hover table-striped table-bordered table-condensed" border="1" style="font-size: 12px"> 
               <thead>    
                   <tr>  
                    <th width="2%"><div class="th_class"><input type="checkbox"  onchange="<portlet:namespace/>changeSelectAll(this)"/></div></th>  
                   <th width="10%"><div class="th_class">รหัสแหล่งทุน</div></th>  
                   <th width="10%"><div class="th_class">รหัสประเภทแหล่งทุน</div></th>  
                   <th width="19%"><div class="th_class">ชื่อแหล่งทุน(T)</div></th>  
                   <th width="19%"><div class="th_class">ชื่อแหล่งทุน(E)</div></th>  
                   <th width="9%"><div class="th_class">ชื่อย่อแหล่งทุน</div></th>  
                   <th width="5%"><div class="th_class">รหัสประเทศ</div></th>
                      <th width="5%"><div class="th_class">Created</div></th>  
                      <th width="5%"><div class="th_class">Updated</div></th>    
                   <th width="11%"><div class="th_class"></div></th>  
                   </tr> 
                </thead> 
                <tbody>    
                <c:if test="${not empty fundingResources}"> 
                 <c:forEach items="${fundingResources}" var="fundingResource" varStatus="loop"> 
               		 <tr style="cursor: pointer;">
                 <td style="text-align: left"><form:checkbox path="ids" id="${ns}_x_${fundingResource.fundingResourceId}" value="${fundingResource.fundingResourceId}"/></td>  
                		<td onclick='<portlet:namespace />displayElementEdit("edit","<portlet:resourceURL  id="research_group_resource_get_byid"><portlet:param name="fundingResourceId"><jsp:attribute name="value"><c:out value="${fundingResource.fundingResourceId}"/></jsp:attribute></portlet:param></portlet:resourceURL>","${fundingResource.fundingResourceId}")' style="text-align: left">${fundingResource.fundingResourceCode}</td>  
               	 		<td onclick='<portlet:namespace />displayElementEdit("edit","<portlet:resourceURL  id="research_group_resource_get_byid"><portlet:param name="fundingResourceId"><jsp:attribute name="value"><c:out value="${fundingResource.fundingResourceId}"/></jsp:attribute></portlet:param></portlet:resourceURL>","${fundingResource.fundingResourceId}")' style="text-align: left">${fundingResource.fundingResourceType.frtCode} /  ${fundingResource.fundingResourceType.frtName}</td>  
               	 		<td onclick='<portlet:namespace />displayElementEdit("edit","<portlet:resourceURL  id="research_group_resource_get_byid"><portlet:param name="fundingResourceId"><jsp:attribute name="value"><c:out value="${fundingResource.fundingResourceId}"/></jsp:attribute></portlet:param></portlet:resourceURL>","${fundingResource.fundingResourceId}")' style="text-align: left">${fundingResource.frNameThai}</td>  
               	 		<td onclick='<portlet:namespace />displayElementEdit("edit","<portlet:resourceURL  id="research_group_resource_get_byid"><portlet:param name="fundingResourceId"><jsp:attribute name="value"><c:out value="${fundingResource.fundingResourceId}"/></jsp:attribute></portlet:param></portlet:resourceURL>","${fundingResource.fundingResourceId}")' style="text-align: left">${fundingResource.frNameEng}</td>  
               	 		<td onclick='<portlet:namespace />displayElementEdit("edit","<portlet:resourceURL  id="research_group_resource_get_byid"><portlet:param name="fundingResourceId"><jsp:attribute name="value"><c:out value="${fundingResource.fundingResourceId}"/></jsp:attribute></portlet:param></portlet:resourceURL>","${fundingResource.fundingResourceId}")' style="text-align: left">${fundingResource.frShortName}</td>  
               	 		<td onclick='<portlet:namespace />displayElementEdit("edit","<portlet:resourceURL  id="research_group_resource_get_byid"><portlet:param name="fundingResourceId"><jsp:attribute name="value"><c:out value="${fundingResource.fundingResourceId}"/></jsp:attribute></portlet:param></portlet:resourceURL>","${fundingResource.fundingResourceId}")' style="text-align: left">${fundingResource.country.countryCode} / ${fundingResource.country.countryNameTh}</td> 
               	 			<fmt:formatDate type="time" value="${fundingResource.createdDate}"   pattern="dd/MM/yyyy" var="createdDate" /> 
                		<td onclick='<portlet:namespace />displayElementEdit("edit","<portlet:resourceURL  id="research_group_resource_get_byid"><portlet:param name="fundingResourceId"><jsp:attribute name="value"><c:out value="${fundingResource.fundingResourceId}"/></jsp:attribute></portlet:param></portlet:resourceURL>","${fundingResource.fundingResourceId}")' style="text-align: left">${createdDate} </td>  
                		 <fmt:formatDate type="time" value="${fundingResource.updatedDate}"   pattern="dd/MM/yyyy" var="updatedDate" /> 
                		<td onclick='<portlet:namespace />displayElementEdit("edit","<portlet:resourceURL  id="research_group_resource_get_byid"><portlet:param name="fundingResourceId"><jsp:attribute name="value"><c:out value="${fundingResource.fundingResourceId}"/></jsp:attribute></portlet:param></portlet:resourceURL>","${fundingResource.fundingResourceId}")' style="text-align: left">${updatedDate} </td>   
                		<td>
                		<div class="btn-group">
                			<a class="btn btn-primary dropdown-toggle" data-toggle="dropdown" href="#">
    							<i class="icon-align-justify icon-white"></i>  Action
    							<span class="caret"></span>
  							</a>
  							<ul class="dropdown-menu">
   								<li><a onclick='<portlet:namespace />displayElementEdit("edit","<portlet:resourceURL  id="research_group_resource_get_byid"><portlet:param name="fundingResourceId"><jsp:attribute name="value"><c:out value="${fundingResource.fundingResourceId}"/></jsp:attribute></portlet:param></portlet:resourceURL>","${fundingResource.fundingResourceId}")'><i class="icon-edit"></i> Edit</a></li>
    							<li><a onclick='<portlet:namespace />displayElementEdit("copy","<portlet:resourceURL  id="research_group_resource_get_byid"><portlet:param name="fundingResourceId"><jsp:attribute name="value"><c:out value="${fundingResource.fundingResourceId}"/></jsp:attribute></portlet:param></portlet:resourceURL>","${fundingResource.fundingResourceId}")'  ><i class="icon-plus-sign"></i> Copy</a></li>
    							<li><a onclick='return <portlet:namespace />confirmDelete("<portlet:actionURL><portlet:param name="action" value="delete"/><portlet:param name="fundingResourceId"><jsp:attribute name="value"><c:out value="${fundingResource.fundingResourceId}"/></jsp:attribute></portlet:param></portlet:actionURL>")' ><i class="icon-trash"></i> Delete</a></li>
  							</ul>
						</div>
<%--
                  				<button onclick='displayElementEdit("edit","<portlet:resourceURL  id="research_group_resource_get_byid"><portlet:param name="fundingResourceId"><jsp:attribute name="value"><c:out value="${fundingResource.fundingResourceId}"/></jsp:attribute></portlet:param></portlet:resourceURL>","${fundingResource.fundingResourceId}")'   class="btn btn-small" type="button">คัดลอก</button>
 						   	 <button onclick='return <portlet:namespace />confirmDelete("<portlet:actionURL><portlet:param name="action" value="delete"/><portlet:param name="fundingResourceId"><jsp:attribute name="value"><c:out value="${fundingResource.fundingResourceId}"/></jsp:attribute></portlet:param></portlet:actionURL>")'  class="btn btn-danger btn-small" type="button">ลบ</button>
 	--%>
 			 <%--
                  			<i onclick='displayElementEdit("edit","<portlet:resourceURL  id="research_group_resource_get_byid"><portlet:param name="fundingResourceId"><jsp:attribute name="value"><c:out value="${fundingResource.fundingResourceId}"/></jsp:attribute></portlet:param></portlet:resourceURL>","${fundingResource.fundingResourceId}")' class="icon-edit"></i>
                			<i  onclick='return <portlet:namespace />confirmDelete("<portlet:actionURL><portlet:param name="action" value="delete"/><portlet:param name="fundingResourceId"><jsp:attribute name="value"><c:out value="${fundingResource.fundingResourceId}"/></jsp:attribute></portlet:param></portlet:actionURL>")' class="icon-trash"></i></td>
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
    			 <c:if test="${fundingResourceMasterForm.pageNo!=1}">
    				<li><a style="cursor: pointer;" onclick='<portlet:namespace/>goPrev()'>Prev</a></li>
    			</c:if>
    			 <c:if test="${not empty fundingResourceMasterForm.pageCount}">
                     <c:forEach begin="${fundingResourceMasterForm.pageStart}" end="${fundingResourceMasterForm.pageEnd}"  var="loop">
                 	<c:if test="${fundingResourceMasterForm.pageNo==(loop)}">
                 		<li><a class="active" style="background-color: #ddd" >${loop}</a></li>
                 	</c:if> 
                 	<c:if test="${fundingResourceMasterForm.pageNo!=(loop)}">
                 		<li><a class="active" style="cursor: pointer;" onclick='<portlet:namespace/>goToPage("${loop}")'>${loop}</a></li>
                 	</c:if> 
                </c:forEach>
                <c:if test="${fundingResourceMasterForm.pageEnd<fundingResourceMasterForm.pageCount}">
                 		<li><a class="active" style="cursor: pointer;" onclick='<portlet:namespace/>goToPage("${fundingResourceMasterForm.pageEnd}")'>...</a></li>
                 		<li><a class="active" style="cursor: pointer;" onclick='<portlet:namespace/>goToPage("${fundingResourceMasterForm.pageCount}")'>${fundingResourceMasterForm.pageCount}</a></li>
                 </c:if>
                 </c:if>
                 <c:if test="${fundingResourceMasterForm.pageNo!=fundingResourceMasterForm.pageCount}">
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
<portlet:resourceURL var="research_group_resource_id"  id="research_group_resource_id"></portlet:resourceURL>
    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster --> 
   <script src="<c:url value="/resources/js/jquery-1.11.2.min.js"/>"></script> 
    <script  src="<c:url value="/resources/bootstrap/js/bootstrap.min.js"/>"></script>  
        <script src="<c:url value='/resources/js/bootbox.min.js'/>" type="text/javascript"></script> 
     <script  src="<c:url value="/resources/js/smoothness/jquery-ui-1.9.1.custom.min.js"/>"></script>
      <script type="text/javascript" src="<c:url value='/resources/js/vendor/jquery.ui.widget.js'/>"></script>
<script type="text/javascript" src="<c:url value='/resources/js/jquery.iframe-transport.js'/>"></script>

  <script type="text/javascript" src="<c:url value='/resources/js/jquery.fileupload.js'/>"></script>  
       <script src="<c:url value="/resources/js/kmuttPortal.js"/>"></script> 
       <script>  
      
       function <portlet:namespace />displayElementEdit(mode,url,id){
    	     //fundingResourceType.frtCode frNameThai frNameEng frShortName
    	   
       	  // $('#input[id="fundingResourceType.frtCode"]').val('');
       	   $("#fundingResourceCode").val('');
       	  $("#fundingResourceId").val('');
       	  $("#frNameThai").val('');
       	  $("#frNameEng").val('');
       	  $("#frShortName").val('');
       	  
       	  $('input[id="fundingResourceType.fundingResourceTypeId"]').val('');
       	  $('#fundingResourceTypeId_autocomplete').val('');
       	  $('#fundingResourceTypeIdShow').html("");
          
       	$("#countryId").val('');	
        $('#countryId_autocomplete').val('')
 		$('#countryIdShow').html("");	
       	   $("#<portlet:namespace/>mode").val(mode);
       	   $("#<portlet:namespace/>command").val("list");
       	
       	 $("#<portlet:namespace/>export_import_element").hide();
  	   if(mode=='edit'){
  		   $("#<portlet:namespace/>export_import_element").show(); 
       	      	    $.ajax({
       	      	  	url: url , 
       	      	     	 data: { fundingResourceId: id }, 
       	      	         success: function(data){
       	      	        $('#fundingResourceCode').prop('readonly',true);
       	      	        	 $("#fundingResourceId").val(id)
       	      	        	 //alert(data.fundingResourceType.frtCode)
       	      	        	//
       	      	        	  $('input[id="fundingResourceType.fundingResourceTypeId"]').val(data.fundingResourceType.fundingResourceTypeId)
       	      	        	  $('#fundingResourceTypeId_autocomplete').val(data.fundingResourceType.frtName)
       		   				 $("#fundingResourceCode").val(data.fundingResourceCode); 
       	      	    	     $('#fundingResourceTypeIdShow').html(" [ "+data.fundingResourceType.frtName+" ]");
       	      	   	 		 $("#frNameThai").val(data.frNameThai); 
       	     	 			 $("#frNameEng").val(data.frNameEng); 
       	 					 $("#frShortName").val(data.frShortName); 
       	 				 $("#countryId").val(data.country.countryId); 
       	 				$('#countryId_autocomplete').val(data.country.countryNameTh)
       	 			 $('#countryIdShow').html(" [ "+data.country.countryNameTh+" ]");	
       	 			$("#<portlet:namespace />_export_csv").val(data.csvResource);
	   				$("#<portlet:namespace />_export_xml").val(data.xmlResource);
	   				$("#<portlet:namespace />_import_csv").val(data.csvUploadResource);
	   				$("#<portlet:namespace />_import_xml").val(data.xmlUploadResource);
	   				<portlet:namespace/>initUpload("<portlet:namespace />_import_csv_bt",data.csvUploadResource,'csv');
	   				<portlet:namespace/>initUpload("<portlet:namespace />_import_xml_bt",data.xmlUploadResource,'xml');
       		   				$( "#<portlet:namespace/>element_edit" ).show( "slow");
       	      	        }
       	      	    }); 
       	   }else if(mode=='copy'){
      		   $("#<portlet:namespace/>export_import_element").show(); 
      		 $("#<portlet:namespace/>mode").val('add');
  	      	    $.ajax({
  	      	  	url: url , 
  	      	     	 data: { fundingResourceId: id }, 
  	      	         success: function(data){
  	      	        $('#fundingResourceCode').prop('readonly',false);
  	      	        	// $("#fundingResourceId").val(id)
  	      	        	 // alert(data.fundingResourceType.frtCode)
  	      	        	 // 
  	      	        	  $('input[id="fundingResourceType.fundingResourceTypeId"]').val(data.fundingResourceType.fundingResourceTypeId)
  	      	        	  $('#fundingResourceTypeId_autocomplete').val(data.fundingResourceType.frtName)
  		   				 $("#fundingResourceCode").val(data.fundingResourceCode); 
  	      	    	     $('#fundingResourceTypeIdShow').html(" [ "+data.fundingResourceType.frtName+" ]");
  	      	   	 		 $("#frNameThai").val(data.frNameThai); 
  	     	 			 $("#frNameEng").val(data.frNameEng); 
  	 					 $("#frShortName").val(data.frShortName); 
  	 				 $("#countryId").val(data.country.countryId); 
  	 				$('#countryId_autocomplete').val(data.country.countryNameTh)
  	 			 $('#countryIdShow').html(" [ "+data.country.countryNameTh+" ]");	
  	 			
  		   				$( "#<portlet:namespace/>element_edit" ).show( "slow");
  	      	        }
  	      	    }); 
  	   }else{
       		$('#fundingResourceCode').prop('readonly',false);
       		   $( "#<portlet:namespace/>element_edit" ).show( "slow");   
       	   }
       	$('html, body').animate({scrollTop : 200},800);
          }
       $( document ).ready(function() {
       	  // Handler for .ready() called.
       	
             $('#fundingResourceTypeId_autocomplete').autocomplete({
       		  source: function( request, response ) {    
       			  //$("#pjCustomerNo").val(ui.item.label); 
       			//  var queryiner=" '%"+request.term+"%' limit 15";
       			var modelM={
      		    		keySearch:request.term
      		    }	
       			ResearchAjax.getFundingResourceTypeList(modelM,{
       					callback:function(data){ 
       						data=data.resultListObj;
       					/*	if(data.resultMessage.msgCode=='ok'){
       							data=data.resultListObj;
       						}else{// Error Code
       							//alert(dwr.util.toDescriptiveString(data.resultMessage.exception, 2));
       							  bootbox.dialog(data.resultMessage.msgDesc,[{
       								    "label" : "Close",
       								     "class" : "btn-danger"
       							 }]);
       							 return false;
       						}
       					*/ 
       						if(data!=null && data.length>0){
       							response( $.map( data, function( item ) {
       					          return {
       					        	  label: item.frtCode +" [ "+ item.frtName+" ]",
       					        	  value: item.fundingResourceTypeId,
       					        	  name: item.frtName,
       					        	  id: item.fundingResourceTypeId,
       					        	  code: item.frtCode
       					          };
       					        }));
       						}else{
       							var xx=[]; 
       							response( $.map(xx));
       						}
       					}
       			 });		  
       		  },
       		  minLength: 1,
       		  select: function( event, ui ) { 
       			  this.value = ui.item.name;
       			$('input[id="fundingResourceType.fundingResourceTypeId"]').val(ui.item.id);
       			$("#fundingResourceTypeIdShow").html(" [ "+ui.item.name+" ]");
       		      return false;
       		  },
       		  open: function() {
       		    $( this ).removeClass( "ui-corner-all" ).addClass( "ui-corner-top" );
       		  },
       		  close: function() {
       		    $( this ).removeClass( "ui-corner-top" ).addClass( "ui-corner-all" );
       		  }
       		}); 
             $('#countryId_autocomplete').autocomplete({
          		  source: function( request, response ) {    
          			//var queryiner=" '%"+request.term+"%' limit 15";
          			var modelM={
      		    		keySearch:request.term
      		    }	
          			ResearchAjax.getCountryList(modelM,{
          					callback:function(data){
          						data=data.resultListObj;
          						if(data!=null && data.length>0){
          							response( $.map( data, function( item ) {
          					          return {
          					        	  label: item.countryCode +" [ "+item.countryNameTh+" ]",
          					        	  value: item.countryCode,
          					        	  name: item.countryNameTh,
          					        	  id: item.countryId,
          					        	  code: item.countryCode
          					          };
          					        }));
          						}else{
          							var xx=[]; 
          							response( $.map(xx));
          						}
          					}
          			 });		  
          		  },
          		  minLength: 1,
          		  select: function( event, ui ) { 
          			  this.value = ui.item.name;
          			$('#countryId').val(ui.item.id);
          			$("#countryIdShow").html(" [ "+ui.item.name+" ]");
          		      return false;
          		  },
          		  open: function() {
          		    $( this ).removeClass( "ui-corner-all" ).addClass( "ui-corner-top" );
          		  },
          		  close: function() {
          		    $( this ).removeClass( "ui-corner-top" ).addClass( "ui-corner-all" );
          		  }
          		}); 
             
       	});
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
         
         function <portlet:namespace />doSearchBox(f_name,pageNo){
         	if(f_name=='fundingResourceType')
         		<portlet:namespace />fundingResourceTypePopup($("#<portlet:namespace />keySearch_"+f_name).val(),false,pageNo)
         	else if(f_name=='country')
             	<portlet:namespace />countryPopup($("#<portlet:namespace />keySearch_"+f_name).val(),false,pageNo)	
         }
         function <portlet:namespace/>chk(f_name,ev) {
         	var key;
         	
         	ev = ev || event;
         	key = ev.keyCode;
         	//alert(ev+",key->"+key)
         	if (key == 13) { 
         		// if(f_name=='researchGroup')
         			<portlet:namespace />doSearchBox(f_name,1);
         		return false;
         	}
         		return true;
         }
         <%-- start researchProject Popup --%>
         function <portlet:namespace />countryPopup(keySearch,init,pageNo){
            	var keyBox=" <div>"+
             		   		"<input type=\"text\"  style=\"display:inline;width:250px\" class=\"form-control\" "+ 
             		   		" id=\"<portlet:namespace />keySearch_country\" onkeypress=\"<portlet:namespace />chk(\'country\',event)\" aria-describedby=\"inputSuccess4Status\" value=\""+keySearch+"\"/>"+
                        		"<button onclick=\"<portlet:namespace />doSearchBox('country',1)\" type=\"button\" class=\"btn btn-success\" style=\"margin-top:-8px;margin-left:5px;\">Search</button>"+
         			   "</div>";
            	var str="<table class=\"table table-hover table-striped table-bordered table-condensed\" border=\"1\" style=\"font-size: 12px\"> "+
        				" <thead>    "+
        				"  <tr>"+
        				"		<th width=\"10%\" style=\"text-align: center\"><div class=\"th_class\">รหัสประเทศ</div></th> "+
        				"		<th width=\"45%\" style=\"text-align: center\"><div class=\"th_class\">ชื่อประเทศ(T)</div></th> "+
        				"		<th width=\"45%\" style=\"text-align: center\"><div class=\"th_class\">ชื่อประเทศ(E)</div></th> "+
        				"	</tr> 		"+
        				" </thead><tbody>";
        			
        				var pageObj={
        						pageSize:PAGE_SIZE_POPUP,
        						pageNo:pageNo
        				}
        				var countryM={
        	  		    		keySearch:keySearch,
        	  		    		paging:pageObj
        	  		    }	
            	ResearchAjax.getCountryList(countryM,{
        			callback:function(data){ 
        				var maxRow=data.maxRow;
        				var lastpage=data.lastpage;
        				data=data.resultListObj;
        				if(data!=null && data.length>0){
        				  for(var i=0;i<data.length;i++){
        					str=str+"<tr  onclick=\"<portlet:namespace />selectcountry('"+data[i].countryId+"')\" style=\"cursor: pointer;\">"+
        		    		"<td style=\"text-align: left\"  >"+data[i].countryCode +"</td>"+
        		    		"<td style=\"text-align: left\"  >"+((data[i].countryNameTh!=null)?(data[i].countryNameTh):("")) +"</td>"+
        		    		"<td style=\"text-align: left\"  >"+((data[i].countryNameEng!=null)?(data[i].countryNameEng):("")) +"</td>"+
        		    		"</tr>";	
        				   }
        				}
        				
        				str=str+"</tbody></table>";
        				var pageArray=calculatePageStartEnd(pageNo,lastpage);
        				var pageStart=pageArray[0];
        				var pageEnd=pageArray[1];
        				var pagingStr="<table border=\"0\" width=\"100%\" style=\"font-size: 13px\">"+
                     				"<tbody>"+
                     				"<tr>"+
                     				"<td align=\"left\" width=\"70%\">"+ 
                     				"<span class=\"pagination\">"+
                     				"<ul>";
                     				if(pageNo!=1)
                     					pagingStr=pagingStr+"<li><a style=\"cursor: pointer;\" onclick='<portlet:namespace />doSearchBox(\"country\","+(pageNo-1)+")'>Prev</a></li>";
                     			
                     				for(var j=pageStart;j<=pageEnd;j++){
                     					if(pageNo==j){
                     						pagingStr=pagingStr+"<li><a class=\"active\" style=\"background-color: #ddd\" >"+j+"</a></li>";
                     					}
                     					else{
                     						pagingStr=pagingStr+"<li><a class=\"active\" style=\"cursor: pointer;\" onclick='<portlet:namespace />doSearchBox(\"country\","+(j)+")'>"+j+"</a></li>";
                     					}
                     				}
                     				if(pageEnd<lastpage){
                     					pagingStr=pagingStr+"<li><a class=\"active\" style=\"cursor: pointer;\" onclick='<portlet:namespace />doSearchBox(\"country\","+(pageEnd)+")'>...</a></li>";
                     					pagingStr=pagingStr+"<li><a class=\"active\" style=\"cursor: pointer;\" onclick='<portlet:namespace />doSearchBox(\"country\","+(lastpage)+")'>"+lastpage+"</a></li>";
                     				}
                     				if(pageNo!=lastpage){
                     					pagingStr=pagingStr+"<li><a style=\"cursor: pointer;\" onclick='<portlet:namespace />doSearchBox(\"country\","+((pageNo<lastpage)?(pageNo+1):(lastpage))+")'>Next</a></li>";
                     				}
                     				pagingStr=pagingStr+"</ul>"+
                     				
                              
                     				"</span>"+
                     				"</td>"+
                     				"<td align=\"right\" width=\"30%\">"+ 
                     				"</td>"+
                     				"</tr>"+
                     				"</tbody>"+
                     				"</table>";
        			if(init){
        				bootbox.classes("aoe_width");
        				bootbox.dialog(keyBox+"<div id=\"<portlet:namespace />countryPopupTable\">"+str+pagingStr+"</div>",[{
        				    "label" : "Close",
        				     "class" : "btn-danger",
        				  "callback": function() {
        			    }
        				   
        			   }]);
        			}else{
        				$("#<portlet:namespace />countryPopupTable").html(str+pagingStr);
        			}
        		 }
        	  });	
         }
         function <portlet:namespace />selectcountry(objID){
         	
         	ResearchAjax.findCountryById(objID,{
     			callback:function(data){
     				$('#countryId').val(objID);
     				$("#countryId_autocomplete").val(data.countryNameTh);
     				bootbox.hideAll();
     			}
      		});
         }
         <%-- end country Popup --%>
         
         <%-- start fundingResourceType Popup --%>
         function <portlet:namespace />fundingResourceTypePopup(keySearch,init,pageNo){
            	var keyBox=" <div>"+
             		   		"<input type=\"text\"  style=\"display:inline;width:250px\" class=\"form-control\" "+ 
             		   		" id=\"<portlet:namespace />keySearch_fundingResourceType\" onkeypress=\"<portlet:namespace />chk(\'fundingResourceType\',event)\" aria-describedby=\"inputSuccess4Status\" value=\""+keySearch+"\"/>"+
                        		"<button onclick=\"<portlet:namespace />doSearchBox('fundingResourceType',1)\" type=\"button\" class=\"btn btn-success\" style=\"margin-top:-8px;margin-left:5px;\">Search</button>"+
         			   "</div>";
            	var str="<table class=\"table table-hover table-striped table-bordered table-condensed\" border=\"1\" style=\"font-size: 12px\"> "+
        				" <thead>    "+
        				"  <tr>"+
        				"		<th width=\"10%\" style=\"text-align: center\"><div class=\"th_class\">รหัสประเภทแหล่งทุน</div></th> "+
        				"		<th width=\"70%\" style=\"text-align: center\"><div class=\"th_class\">ชื่อประเภทแหล่งทุน</div></th> "+
        				"		<th width=\"20%\" style=\"text-align: center\"><div class=\"th_class\">ในประเทศ / ต่างประเทศ</div></th> "+
        				"	</tr> 		"+
        				" </thead><tbody>";
        				
        				var pageObj={
        						pageSize:PAGE_SIZE_POPUP,
        						pageNo:pageNo
        				}
        				var fundingResourceTypeM={
        	  		    		keySearch:keySearch,
        	  		    		paging:pageObj
        	  		    }	
            	ResearchAjax.getFundingResourceTypeList(fundingResourceTypeM,{
        			callback:function(data){ 
        				var maxRow=data.maxRow;
        				var lastpage=data.lastpage;
        				data=data.resultListObj;
        				if(data!=null && data.length>0){
        				  for(var i=0;i<data.length;i++){
        					str=str+"<tr  onclick=\"<portlet:namespace />selectfundingResourceType('"+data[i].fundingResourceTypeId+"')\" style=\"cursor: pointer;\">"+
        		    		"<td style=\"text-align: left\"  >"+data[i].frtCode +"</td>"+
        		    		"<td style=\"text-align: left\"  >"+((data[i].frtName!=null)?(data[i].frtName):("")) +"</td>"+
        		    		"<td style=\"text-align: left\"  >";
        		    	/*	if(data[i].frtName!=null && data[i].frtName=='0')
        		    			str=str+"ในประเทศ";
        		    		else
        		    			str=str+"ต่างประเทศ";
        		    	*/
        		    		str=str+"</td>"+
        		    		"</tr>";	
        				   }
        				}
        				
        				str=str+"</tbody></table>";
        				var pageArray=calculatePageStartEnd(pageNo,lastpage);
        				var pageStart=pageArray[0];
        				var pageEnd=pageArray[1];
        				var pagingStr="<table border=\"0\" width=\"100%\" style=\"font-size: 13px\">"+
                     				"<tbody>"+
                     				"<tr>"+
                     				"<td align=\"left\" width=\"70%\">"+ 
                     				"<span class=\"pagination\">"+
                     				"<ul>";
                     				if(pageNo!=1)
                     					pagingStr=pagingStr+"<li><a style=\"cursor: pointer;\" onclick='<portlet:namespace />doSearchBox(\"fundingResourceType\","+(pageNo-1)+")'>Prev</a></li>";
                     			
                     				for(var j=pageStart;j<=pageEnd;j++){
                     					if(pageNo==j){
                     						pagingStr=pagingStr+"<li><a class=\"active\" style=\"background-color: #ddd\" >"+j+"</a></li>";
                     					}
                     					else{
                     						pagingStr=pagingStr+"<li><a class=\"active\" style=\"cursor: pointer;\" onclick='<portlet:namespace />doSearchBox(\"fundingResourceType\","+(j)+")'>"+j+"</a></li>";
                     					}
                     				}
                     				if(pageEnd<lastpage){
                     					pagingStr=pagingStr+"<li><a class=\"active\" style=\"cursor: pointer;\" onclick='<portlet:namespace />doSearchBox(\"fundingResourceType\","+(pageEnd)+")'>...</a></li>";
                     					pagingStr=pagingStr+"<li><a class=\"active\" style=\"cursor: pointer;\" onclick='<portlet:namespace />doSearchBox(\"fundingResourceType\","+(lastpage)+")'>"+lastpage+"</a></li>";
                     				}
                     				if(pageNo!=lastpage){
                     					pagingStr=pagingStr+"<li><a style=\"cursor: pointer;\" onclick='<portlet:namespace />doSearchBox(\"fundingResourceType\","+((pageNo<lastpage)?(pageNo+1):(lastpage))+")'>Next</a></li>";
                     				}
                     				pagingStr=pagingStr+"</ul>"+
                     				
                              
                     				"</span>"+
                     				"</td>"+
                     				"<td align=\"right\" width=\"30%\">"+ 
                     				"</td>"+
                     				"</tr>"+
                     				"</tbody>"+
                     				"</table>";
        			if(init){
        				bootbox.classes("aoe_width");
        				bootbox.dialog(keyBox+"<div id=\"<portlet:namespace />fundingResourceTypePopupTable\">"+str+pagingStr+"</div>",[{
        				    "label" : "Close",
        				     "class" : "btn-danger",
        				  "callback": function() {
        			    }
        				   
        			   }]);
        			}else{
        				$("#<portlet:namespace />fundingResourceTypePopupTable").html(str+pagingStr);
        			}
        		 }
        	  });	
         }
         function <portlet:namespace />selectfundingResourceType(objID){
         	
         	ResearchAjax.findFundingResourceTypeById(objID,{
     			callback:function(data){
     				$('input[id="fundingResourceType.fundingResourceTypeId"]').val(objID);
     				$("#fundingResourceTypeId_autocomplete").val(data.frtName);
     				bootbox.hideAll();
     			}
      		});
         }
         <%-- end fundingResourceType Popup --%>
      </script>  
  </body>
</html>	
