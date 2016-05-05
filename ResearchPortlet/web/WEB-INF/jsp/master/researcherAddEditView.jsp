<%@ include file="/WEB-INF/jsp/include.jsp" %>
<%@ page contentType="text/html; charset=utf-8" %> 
<%@page import="javax.portlet.PortletURL"%>
<script  src="<c:url value="/dwr/engine.js"/>"></script>  
<script  src="<c:url value="/dwr/util.js"/>"></script>  
<script  src="<c:url value="/dwr/interface/ResearchAjax.js"/>"></script>   
 <c:url var="url" value="/" />
  <portlet:actionURL var="formAction">
    <portlet:param name="action" value="doSubmit"/>
</portlet:actionURL>
  <portlet:renderURL var="list">
    <portlet:param name="action" value="list"/>
</portlet:renderURL>
<!DOCTYPE html>
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
	
    <title>Research</title> 
    <!-- Bootstrap core CSS -->
     <link rel="stylesheet" href="<c:url value="/resources/bootstrap/css/bootstrap.min.css"/>" type="text/css"/> 
      <link href="<c:url value='/resources/css/smoothness/jquery-ui-1.9.1.custom.min.css'/>" type="text/css"  rel="stylesheet" /> 
    <link rel="stylesheet" href="<c:url value="/resources/css/jquery.fileupload.css"/>" type="text/css"/>
    <style type="text/css">
  		#breadcrumbs { display:none; }
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
  <form:form  id="researcherMasterForm" modelAttribute="researcherMasterForm" method="post"  name="researcherMasterForm" action="${formAction}" enctype="multipart/form-data">
   
        <fieldset style="font-family: sans-serif;padding-top:5px">
         <input type="hidden" name="command" id="<portlet:namespace />common" value="${researcherMasterForm.command}" />
              <input type="hidden" name="mode" id="<portlet:namespace />mode"  value="${researcherMasterForm.mode}" />
   <form:hidden path="researcherM.researcherId"  id="researcherId"/> 
    <div style="border: 1px solid #FFC299;background: #F9F9F9;padding: 10px">
    <div class="accordion" id="accordion2">
                <div class="accordion-group">
                  <div class="accordion-heading">
                     <a href="${list}" style="padding-right: 20px;" >Back</a> 
                    <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion2" href="#collapseOne">
                      ข้อมูลผู้วิจัย
                    </a>
                  </div>
                  <div id="collapseOne" class="accordion-body in collapse" style="height: auto;">
                    <div class="accordion-inner">
                       <table border="0" width="100%" style="border: 0px solid #FFC299;font-size:13px">
            <tr>
              <td width="100%" colspan="4"></td>
            </tr>
            <tr>
              <td align="right" width="20%">
                        <label><span>ประเภทผู้วิจัย:</span> 
                       </label>
              </td>
              <td width="80%" colspan="2">
              	 	<table border="0" style="width:100%">
              	 		<tr>
              	 			<td width="30%"><form:radiobutton path="researcherM.researcherTypeId" value="1"/>ข้าราชการ/พนักงาน </td>
              	 			<td width="70%"><form:radiobutton path="researcherM.researcherTypeId" value="2"/>ลูกจ้างโครงการที่ได้ผ่าน สทบ.</td>
              	 		</tr>
              	 		<tr>
              	 			<td><form:radiobutton path="researcherM.researcherTypeId" value="3"/>ลูกจ้างโครงการที่ไม่ได้ผ่าน สทบ </td>
              	 			<td><form:radiobutton path="researcherM.researcherTypeId" value="4"/>บุคลลภายนอก มจธ.</td>
              	 		</tr>
              	 		<tr>
              	 			<td><form:radiobutton path="researcherM.researcherTypeId" value="5"/>นักศึกษา</td>
              	 			<td>
              	 			<%--
              	 			<form:radiobutton path="researcherM.researcherTypeId" value="6"/>>นักศึกษาพิเศษ
              	 			 --%>
              	 			</td>
              	 		</tr>
              	 	</table>
              </td>
              
            </tr>
            <tr valign="top">
              <td align="right" width="20%">
                        <span><label>คำนำหน้า:<span style="color: red;font-size: 20;"> </span> 
                        </label></span> 
              </td>
              <td width="80%" colspan="3">
                 <%--  <form:input path="researcherM.title.titleId" 
                   cssStyle="display:inline;background-color: rgb(250, 250, 198);width:100px" cssClass="form-control"/>
                     <div>
                      --%>
                     <form:hidden path="researcherM.title.titleId"/> 
                   <input  type="text" style="background-color: rgb(250, 250, 198);" 
                   class="form-control" id="titleId_assign_autocomplete" 
                   value="${researcherMasterForm.researcherM.title.titleName}" 
                   placeholder=""/>
                     <button style="margin-top: -8px" onclick="<portlet:namespace />titlePopup('',true,1)" type="button" class="btn btn-warning btn-small">...</button>
               <%--
                   <span id="titleId_assignShow">${researcherMasterForm.researcherM.title.academicTitleName}</span>
                    --%>
                <%--
                      <form:input path="journalPaperM.positionId" cssStyle="display:inline;background-color: rgb(250, 250, 198);width:100px" cssClass="form-control"/>
                     --%> 
                       </div>
              </td> 
            </tr> 
            <tr valign="top">
              <td align="right" width="20%">
                        <span><label>คำนำหน้าทางวิชาการ:<span style="color: red;font-size: 20;"> </span> 
                        </label></span> 
              </td>
              <td width="80%" colspan="3">
                <%--    <form:input path="researcherM.academicTitle.titleId" 
                   cssStyle="display:inline;background-color: rgb(250, 250, 198);width:100px" cssClass="form-control"/>
                    <div>
                     --%>
                     <div>
                     <form:hidden path="researcherM.academicTitle.titleId"/> 
                   <input  type="text" style="background-color: rgb(250, 250, 198);" 
                   class="form-control" id="academic_titleId_assign_autocomplete" 
                   value="${researcherMasterForm.researcherM.academicTitle.academicTitleName}" 
                   placeholder=""/>
                     <button style="margin-top: -8px" onclick="<portlet:namespace />academicTitlePopup('',true,1)" type="button" class="btn btn-warning btn-small">...</button>
                   <%--
                   <span id="academic_titleId_assignShow">${researcherMasterForm.researcherM.academicTitle.academicTitleName}</span>
                    --%>
                <%--
                      <form:input path="journalPaperM.positionId" cssStyle="display:inline;background-color: rgb(250, 250, 198);width:100px" cssClass="form-control"/>
                     --%> 
                       </div>
              </td> 
            </tr>   
            <tr  >
              <td align="right" width="20%">
                        <span><label>ตำแหน่งทางวิชาการ:<span style="color: red;font-size: 20;"> </span> 
                        </label></span> 
              </td>
              <td width="80%" colspan="3">
                   <%-- <div> 
                     <form:input path="researcherM.academicPosition.positionId"  
                     cssStyle="display:inline;background-color: rgb(250, 250, 198);width:100px" cssClass="form-control"/>
                   </div>
                    --%>
                   <div>
                     <form:hidden path="researcherM.academicPosition.positionId"/> 
                   <input  type="text" style="background-color: rgb(250, 250, 198);" 
                   class="form-control" id="academic_positionId_assign_autocomplete" 
                   value="${researcherMasterForm.researcherM.academicPosition.positionName}" 
                   placeholder=""/>
                   <button style="margin-top: -8px" onclick="<portlet:namespace />academicPositionPopup('',true,1)" type="button" class="btn btn-warning btn-small">...</button>
                   <%--
                   <span id="academic_positionId_assignShow">${researcherMasterForm.researcherM.academicPosition.positionName}</span>
                    --%>
                <%--
                      <form:input path="journalPaperM.positionId" cssStyle="display:inline;background-color: rgb(250, 250, 198);width:100px" cssClass="form-control"/>
                     --%> 
                       </div>
              </td> 
            </tr>  
             <tr  >
              <td align="right" width="20%"> 
              <span><label>ชื่อนักวิจัย(T):<span style="color: red;font-size: 20;"> </span> 
                        </label></span> 
              </td>
              <td width="80%" colspan="3">
                   <form:input path="researcherM.nameThai"  cssClass="form-control"/>
              </td> 
            </tr>  
            <tr  >
              <td align="right" width="20%">
                        <span><label>นามสกุล(T):<span style="color: red;font-size: 50;"> </span> 
                        </label></span> 
              </td>
              <td width="80%" colspan="3">
                    <div>
                    <form:input path="researcherM.surnameThai"  cssClass="form-control"/>
                       </div>
              </td> 
            </tr> 
             <tr  >
              <td align="right" width="20%"> 
              <span><label>ชื่อนักวิจัย(E):<span style="color: red;font-size: 20;"> </span> 
                        </label></span> 
              </td>
              <td width="80%" colspan="3">
                    <form:input path="researcherM.nameEng"  cssClass="form-control"/>
              </td> 
            </tr>  
            <tr  >
              <td align="right" width="20%">
                        <span><label>นามสกุล(E):<span style="color: red;font-size: 50;"> </span> 
                        </label></span> 
              </td>
              <td width="80%" colspan="3">
                    <div> 
                     <form:input path="researcherM.surnameEng" cssClass="form-control"/>
                       </div>
              </td> 
            </tr> 
            <%--
            <tr  >
              <td align="right" width="20%">
                        <span><label>เลขบัตรประจำตัวประชาชน:<span style="color: red;font-size: 20;"> </span> 
                        </label></span> 
              </td>
              <td width="80%" colspan="3">
                    <div>  
                   <form:input path="researcherM.cardId" cssStyle="width:100px" cssClass="form-control"/>
                       </div>
              </td> 
            </tr>
             --%>  
             <tr  >
              <td align="right" width="20%">
                        <span><label>วิทยาเขต:<span style="color: red;font-size: 20;"> </span> 
                        </label></span> 
              </td>
              <td width="80%" colspan="3">
                    <div>
                     <form:input path="researcherM.deptCode" cssStyle="" cssClass="form-control"/>
                    </div>
              </td> 
            </tr>  
          <tr>
              <td align="right" width="20%">
                        <label><span>งาน:<span style="color: red;font-size: 20;"></span> 
                        </span> </label>
              </td>
             <td width="80%" colspan="3">
                    <div>
                    	<form:input path="researcherM.workCode" cssStyle="" cssClass="form-control"/>
                     </div>
              </td> 
            </tr> 
             <tr>
              <td align="right" width="20%">
                        <label><span>ตำหน่งทางสายงาน:<span style="color: red;font-size: 20;"></span> 
                        </span> </label>
              </td>
             <td width="80%" colspan="3">
                    
                        <div>
                     <form:hidden path="researcherM.position.positionId"/> 
                   <input  type="text" style="background-color: rgb(250, 250, 198);" 
                   class="form-control" id="positionId_assign_autocomplete" 
                   value="${researcherMasterForm.researcherM.position.positionName}" 
                   placeholder=""/>
                     <button style="margin-top: -8px" onclick="<portlet:namespace />positionPopup('',true,1)" type="button" class="btn btn-warning btn-small">...</button>
                   <%--
                   <span id="positionId_assignShow">${researcherMasterForm.researcherM.position.positionName}</span>
                   --%>
                <%--
                      <form:input path="journalPaperM.positionId" cssStyle="display:inline;background-color: rgb(250, 250, 198);width:100px" cssClass="form-control"/>
                     --%> 
                       </div>
              </td> 
            </tr>
             <tr  >
              <td align="right" width="20%">
                        <span><label>บริษัท/องค์กร:<span style="color: red;font-size: 20;"> </span> 
                        </label></span> 
              </td>
              <td width="80%" colspan="3">
                <div>
                     <form:hidden path="researcherM.organization.organizationId"/> 
                   <input  type="text" style="background-color: rgb(250, 250, 198);" 
                   class="form-control" id="organizationId_assign_autocomplete" 
                   value="${researcherMasterForm.researcherM.organization.orgName}" 
                   placeholder=""/>
                    <button style="margin-top: -8px" onclick="<portlet:namespace />organizationPopup('',true,1)" type="button" class="btn btn-warning btn-small">...</button>
                    <%--
                   <span id="organizationId_assignShow">${researcherMasterForm.researcherM.organization.orgName}</span>
                    --%>
                <%--
                      <form:input path="journalPaperM.organizationId" cssStyle="display:inline;background-color: rgb(250, 250, 198);width:100px" cssClass="form-control"/>
                     --%> 
                       </div>
              </td> 
            </tr>  
           <tr>
              <td align="center" colspan="4" width="20%">
                       <button type="button" onclick="<portlet:namespace />doSubmitForm()" class="btn btn-primary">Submit</button>  
              </td>
              
            </tr> 
              
          </table>
                    </div>
                  </div>
                </div>
                <div class="accordion-group">
                  <div class="accordion-heading">
                    <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion2" href="#collapseTwo">
                      กลุ่มวิจัย
                    </a>
                  </div>
                  <div id="collapseTwo" class="accordion-body collapse" style="height: 0px;">
                    <div class="accordion-inner"> 
         <table border="0" width="100%" style="font-size: 13px">
                <tbody> 
                 <tr>
                <td align="center" width="100%" colspan="2">  
                 <div id="<portlet:namespace/>element_researcherGroup" style="border: 1px solid #FFC299;background: #F9F9F9;padding: 10px;display: none">
                 	<table border="0" width="100%" style="border: 0px solid #FFC299;font-size:13px">
            			 <tr>
              				<td align="right" width="30%">
                        		<span>
                        			<label>กลุ่มวิจัย:<span style="color: red;font-size: 50;"> </span> 
                        			</label>
                        		</span> 
             	 			</td>
              	 			<td width="70%" colspan="3">
                    			  <div>
                    			   <input type="hidden" id="<portlet:namespace/>researcherGroup_mode" />
                    			  	<input type="hidden" id="<portlet:namespace/>researcherGroup_researchGroupId"> 
                    			   <input type="hidden" id="<portlet:namespace/>researchGroupId"> 
                    			
                   						<input  type="text" style="background-color: rgb(250, 250, 198);width:350px" 
                   class="form-control" id="researchGroupId_assign_autocomplete" 
                   placeholder=""/>
                    <button style="margin-top: -8px" onclick="<portlet:namespace />researchGroupPopup('',true,1)" type="button" class="btn btn-warning btn-small">...</button>
                    <%--
                   <span id="researchGroupId_assignShow"></span>
                    --%> 
                      		 </div>
              				</td> 
            			</tr>
              			<tr>
              				<td align="center" colspan="4" width="20%">
                  				<button type="button" onclick="<portlet:namespace />doSubmitResearcherGroupAjax()" class="btn btn-primary">Submit</button>  
              				</td>
            			</tr> 
            		</table>
                 </div>
                </td>
                </tr>    
                <tr>
                <td align="left" width="70%">   

                </td><td align="right" width="30%"> 
                <span sytle="padding-left:20px">
                 <c:if test="${researcherMasterForm.mode=='edit'}">
                 	<button  onclick="<portlet:namespace/>displayResearcherGroup('add','0')" type="button" class="btn btn-primary">Add</button>
                 </c:if>
               </span >
                </td>
                </tr>
                </tbody></table>  
                  <span id="<portlet:namespace/>researcherGroup_item_list"> 
        <table class="table table-hover table-striped table-bordered table-condensed" border="1" style="font-size: 12px"> 
               <thead>    
                   <tr> <!-- 50 42+8 -->
                   <th width="5%" style="text-align: center"><div class="th_class">#</div></th> 
                 <th width="90%" style="text-align: center"><div class="th_class">กลุ่มวิจัย</div></th>  
                <th width="5%"><div class="th_class"></div></th>  
                   </tr> 
                </thead> 
                <tbody>    
                <c:if test="${not empty researcherMasterForm.researcherM.researcherGroups}"> 
                <c:forEach items="${researcherMasterForm.researcherM.researcherGroups}" var="researcherGroup" varStatus="loop"> 
                   <tr style="cursor: pointer;">
                 <td style="text-align: left">${loop.index+1}</td>
                     	<td style="text-align: left">${researcherGroup.researchGroup.groupTh}</td>
                <td style="text-align: center">
                 
               <button onclick='<portlet:namespace />doDeleteResearcherGroupAjax("${researcherGroup.researcherId}","${researcherGroup.researchGroupId}")' class="btn btn-danger btn-small" type="button">ลบ</button>  
                </td> 
                </tr>
                </c:forEach>
                </c:if>
                
            </tbody>
          </table> 
          </span>
                    </div>
                  </div>
                </div>
               
              </div> 
        
</div>
</fieldset>
     </form:form>
    <div class="container">
      <!-- Example row of columns -->
      <div class="row">
         
      </div>

      <hr>

      <footer>
          <%-- <p>&copy; Company 2014</p> --%>
      </footer>
    </div> <!-- /container -->


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
    
    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <%-- 
    <script src="../assets/js/ie10-viewport-bug-workaround.js"></script>
     --%>
     
       <script src="<c:url value="/resources/js/kmuttPortal.js"/>"></script> 
      <script>
    $(document).ready(function() {  
    	var _path="${url}";
    	$('#titleId_assign_autocomplete').autocomplete({
  		  source: function( request, response ) {    
  			var modelM={
  		    		keySearch:request.term
  		    }		
  			ResearchAjax.listTitle(modelM,{
  					callback:function(data){ 
  						data=data.resultListObj;
  						if(data!=null && data.length>0){
  							response( $.map( data, function( item ) {
  					          return {
  					        	  label: item.titleName ,
  					        	  value: item.titleName,
  					        	  name: item.titleName,
  					        	  id: item.titleId
  					        	  //code: item.frtCode
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
  			$('input[id="researcherM.title.titleId"]').val(ui.item.id);
  			$("#titleId_assignShow").html(" "+ui.item.name+" ");
  		      return false;
  		  },
  		  open: function() {
  		    $( this ).removeClass( "ui-corner-all" ).addClass( "ui-corner-top" );
  		  },
  		  close: function() {
  		    $( this ).removeClass( "ui-corner-top" ).addClass( "ui-corner-all" );
  		  }
  		});
    	
    	$('#academic_titleId_assign_autocomplete').autocomplete({
    		  source: function( request, response ) {    
    			  var modelM={
        		    		keySearch:request.term
        		    }	
    			ResearchAjax.listTitle(modelM,{
    					callback:function(data){ 
    						data=data.resultListObj;
    						if(data!=null && data.length>0){
    							response( $.map( data, function( item ) {
    					          return {
    					        	  label: item.academicTitleName ,
      					        	  value: item.academicTitleName,
      					        	  name: item.academicTitleName,
      					        	  id: item.titleId
    					        	  //code: item.frtCode
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
    			$('input[id="researcherM.academicTitle.titleId"]').val(ui.item.id);
    			$("#academic_titleId_assignShow").html(" "+ui.item.name+" ");
    		      return false;
    		  },
    		  open: function() {
    		    $( this ).removeClass( "ui-corner-all" ).addClass( "ui-corner-top" );
    		  },
    		  close: function() {
    		    $( this ).removeClass( "ui-corner-top" ).addClass( "ui-corner-all" );
    		  }
    		});
    	
    	
    	
    	 $('#academic_positionId_assign_autocomplete').autocomplete({
   		  source: function( request, response ) {    
   			var modelM={
  		    		keySearch:request.term
  		    }	
   			ResearchAjax.listPosition(modelM,{
   					callback:function(data){ 
   						data=data.resultListObj;
   						if(data!=null && data.length>0){
   							response( $.map( data, function( item ) {
   					          return {
   					        	  label: item.positionName ,
   					        	  value: item.positionName,
   					        	  name: item.positionName,
   					        	  id: item.positionId
   					        	  //code: item.frtCode
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
   			$('input[id="researcherM.academicPosition.positionId"]').val(ui.item.id);
   			$("#academic_positionId_assignShow").html(" "+ui.item.name+" ");
   		      return false;
   		  },
   		  open: function() {
   		    $( this ).removeClass( "ui-corner-all" ).addClass( "ui-corner-top" );
   		  },
   		  close: function() {
   		    $( this ).removeClass( "ui-corner-top" ).addClass( "ui-corner-all" );
   		  }
   		});
    	 
    	 $('#positionId_assign_autocomplete').autocomplete({
      		  source: function( request, response ) {    
      			var modelM={
      		    		keySearch:request.term
      		    }	
      			ResearchAjax.listPosition(modelM,{
      					callback:function(data){ 
      						data=data.resultListObj;
      						if(data!=null && data.length>0){
      							response( $.map( data, function( item ) {
      					          return {
      					        	  label: item.positionName ,
       					        	  value: item.positionName,
       					        	  name: item.positionName,
       					        	  id: item.positionId
      					        	  //code: item.frtCode
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
      			$('input[id="researcherM.position.positionId"]').val(ui.item.id);
      			$("#positionId_assignShow").html(" "+ui.item.name+" ");
      		      return false;
      		  },
      		  open: function() {
      		    $( this ).removeClass( "ui-corner-all" ).addClass( "ui-corner-top" );
      		  },
      		  close: function() {
      		    $( this ).removeClass( "ui-corner-top" ).addClass( "ui-corner-all" );
      		  }
      		});
    	
    	 $('#organizationId_assign_autocomplete').autocomplete({
    		  source: function( request, response ) {    
    			  var modelM={
        		    		keySearch:request.term
        		    }	
    			ResearchAjax.getOrganizationList(modelM,{
    					callback:function(data){ 
    						data=data.resultListObj;
    						if(data!=null && data.length>0){
    							response( $.map( data, function( item ) {
    					          return {
    					        	  label: item.orgName ,
    					        	  value: item.orgName,
    					        	  name: item.orgName,
    					        	  id: item.organizationId
    					        	  //code: item.frtCode
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
    			$('input[id="researcherM.organization.organizationId"]').val(ui.item.id);
    			$("#organizationId_assignShow").html(" "+ui.item.name+" ");
    		      return false;
    		  },
    		  open: function() {
    		    $( this ).removeClass( "ui-corner-all" ).addClass( "ui-corner-top" );
    		  },
    		  close: function() {
    		    $( this ).removeClass( "ui-corner-top" ).addClass( "ui-corner-all" );
    		  }
    		});
   	
    	 $('#researchGroupId_assign_autocomplete').autocomplete({
     		  source: function( request, response ) {    
     			 var modelM={
       		    		keySearch:request.term
       		    }	
     			ResearchAjax.getResearchGroupList(modelM,{
     					callback:function(data){ 
     						data=data.resultListObj;
     						if(data!=null && data.length>0){
     							response( $.map( data, function( item ) {
     					          return {
     					        	  label: item.groupTh ,
     					        	  value: item.groupTh ,
     					        	  name: item.groupTh ,
     					        	  id: item.researchGroupId
     					        	  //code: item.frtCode
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
     			$('#<portlet:namespace/>researchGroupId').val(ui.item.id);
     			$("#researchGroupId_assignShow").html(" "+ui.item.name+" ");
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
    
    <%-- Start ResearcherGroup  --%>
    function <portlet:namespace />doEditResearcherGroupAjax(researcherId,researchGroupId){

       	var researcherGroup={
       			researcherId:researcherId,
       			researchGroupId:researchGroupId
       			
       	}
    	
    	ResearchAjax.findResearcherGroupById(researcherGroup,{
				callback:function(data){ 
					if(data!=null){
						//alert(researcherId+","+researchGroupId)
						$( "#researchGroupId_assignShow" ).html(data.researchGroup.groupTh); 
						$( "#researchGroupId_assign_autocomplete" ).val(researchGroupId);
						$( "#<portlet:namespace/>researcherGroup_mode" ).val("edit");
					
						$( "#<portlet:namespace/>researcherGroup_researchGroupId" ).val(researchGroupId);
						$( "#<portlet:namespace/>researchGroupId" ).val(researchGroupId);
           			   
						$( "#<portlet:namespace/>element_researcherGroup" ).show( "slow");   
					}
				}
		 });	
      }
    
    function <portlet:namespace />doDeleteResearcherGroupAjax(researcherId,researchGroupId){
    	
    	var agree=confirm(" ต้องการลบข้อมูลหรือไม่? ");
		if (agree){
			<portlet:namespace />showDownlod("<portlet:namespace/>researcherGroup_item_list");
		   	 var researchProjectResearcherGroup={
		   			researcherId:researcherId,
		   			researchGroupId:researchGroupId
		        };
		   
		   	ResearchAjax.deleteResearcherGroup(researchProjectResearcherGroup,{
						callback:function(data){ 
							<portlet:namespace/>render_document_item(data);
							 $( "#<portlet:namespace/>element_researcherGroup" ).hide( "slow"); 
						}
				 });
			return true ;
		}
		else{
			return false ;
		}
     	
   }
    function <portlet:namespace />doSubmitResearcherGroupAjax(){
    	var mode=$( "#<portlet:namespace/>researcherGroup_mode" ).val();
     //alert(mode)	
    	//alert($("#researcherId").val())
    	//return false;
       var	 researchGroupId =$('#<portlet:namespace/>researchGroupId').val();
	var researchGroup={
			researchGroupId:researchGroupId
	};
	 
        var researchProjectResearcherGroup={
        		researcherId:$("#researcherId").val(),
        		researchGroupId:researchGroupId,
        		researchGroup:researchGroup,
        		createdBy:"${user.userId}",
    			updatedBy:"${user.userId}"
        }
        <portlet:namespace />showDownlod("<portlet:namespace/>researcherGroup_item_list");
    	ResearchAjax.updateResearcherGroup(researchProjectResearcherGroup,mode,{
				callback:function(data){ 
					<portlet:namespace/>render_document_item(data);
					 $( "#<portlet:namespace/>element_researcherGroup" ).hide( "slow");   
				}
		 });	
    }
    function <portlet:namespace/>displayResearcherGroup(mode,id){
    	$("#<portlet:namespace/>researchGroupId").val('');
    	$("#researchGroupId_assign_autocomplete").val('');
    	$("#researchGroupId_assignShow").html('');
	
	
		$( "#<portlet:namespace/>researcherGroup_researchGroupId" ).val("");
   	   if(mode=='edit'){
   		  
   	   }else{
   		$( "#<portlet:namespace/>researcherGroup_mode" ).val("add");
   		   $( "#<portlet:namespace/>element_researcherGroup" ).show( "slow");   
   	   }
   	   
      }

    function <portlet:namespace/>render_document_item(obj){
    	  var str="<table class=\"table table-hover table-striped table-bordered table-condensed\" border=\"1\" style=\"font-size: 12px\"> "+
    	  			" <thead>    "+
          			"  <tr>"+
          			"  		<th width=\"5%\" style=\"text-align: center\"><div class=\"th_class\">#</div></th>"+ 
        			"		<th width=\"90%\" style=\"text-align: center\"><div class=\"th_class\">กลุ่มวิจัย</div></th>"+  
       				"		<th width=\"5%\"><div class=\"th_class\"></div></th>  "+
          			"	</tr> 		"+
       				" </thead> 	"+
       				" <tbody>  	";
       				if(obj!=null){
       					for(var i=0;i<obj.length;i++){
       						str=str+" 	<tr style=\"cursor: pointer;\">"+
       						" <td style=\"text-align: left\">"+(i+1)+"</td>"+
       						" <td style=\"text-align: left\">"+$.trim(obj[i].researchGroup.groupTh)+"</td> "+
       						" <td style=\"text-align: center\">"+
       					//	"  <button onclick=\"<portlet:namespace />doEditResearcherGroupAjax(\'"+obj[i].researcherId+"\',\'"+obj[i].researchGroupId+"\')\" class=\"btn btn-small\" type=\"button\">คัดลอก</button> "+
       						"  <button onclick=\"<portlet:namespace />doDeleteResearcherGroupAjax(\'"+obj[i].researcherId+"\',\'"+obj[i].researchGroupId+"\')\" class=\"btn btn-danger btn-small\" type=\"button\">ลบ</button> "+
       						" </td>  "+
       						" </tr> ";
       					}
       				}
       				str=str+"</tbody>"+
       					"</table>";
    	  $("#<portlet:namespace/>researcherGroup_item_list").html(str);
    	
    }
    <%-- END ResearcherGroup  --%>
    function <portlet:namespace />doSubmitForm(){
      	 var form = document.forms['researcherMasterForm'];
      	 form.submit();
      }
    function <portlet:namespace />showDownlod(element_){
    	//<c:url value='/resources/images/ajax_loading.gif'/>loading.gif
    	var download_str='<table border="0" width="100%"><tr><td  align="center"><img style="width:60px;" src="<c:url value="/resources/images/loading.gif"/>"/></td></tr></table>';
    	$("#"+element_).html(download_str);
    
    }
    
    function <portlet:namespace />doSearchBox(f_name,pageNo){
     	if(f_name=='researchGroup')
     		<portlet:namespace />researchGroupPopup($("#<portlet:namespace />keySearch_"+f_name).val(),false,pageNo)
     	else if(f_name=='organization')
    		<portlet:namespace />organizationPopup($("#<portlet:namespace />keySearch_"+f_name).val(),false,pageNo)
    	else if(f_name=='position')
    		<portlet:namespace />positionPopup($("#<portlet:namespace />keySearch_"+f_name).val(),false,pageNo)
    	else if(f_name=='academicPosition')
    		<portlet:namespace />academicPositionPopup($("#<portlet:namespace />keySearch_"+f_name).val(),false,pageNo)
    	else if(f_name=='title')
    		<portlet:namespace />titlePopup($("#<portlet:namespace />keySearch_"+f_name).val(),false,pageNo)
    	else if(f_name=='academicTitle')
    		<portlet:namespace />academicTitlePopup($("#<portlet:namespace />keySearch_"+f_name).val(),false,pageNo)
    			
     
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
    <%-- start ResearchGroup Popup --%>
    function <portlet:namespace />researchGroupPopup(keySearch,init,pageNo){
       	var keyBox=" <div>"+
        		   		"<input type=\"text\"  style=\"display:inline;width:250px\" class=\"form-control\" "+ 
        		   		" id=\"<portlet:namespace />keySearch_researchGroup\" onkeypress=\"<portlet:namespace />chk(\'researchGroup\',event)\" aria-describedby=\"inputSuccess4Status\" value=\""+keySearch+"\"/>"+
                   		"<button onclick=\"<portlet:namespace />doSearchBox('researchGroup',1)\" type=\"button\" class=\"btn btn-success\" style=\"margin-top:-8px;margin-left:5px;\">Search</button>"+
    			   "</div>";
       	var str="<table class=\"table table-hover table-striped table-bordered table-condensed\" border=\"1\" style=\"font-size: 12px\"> "+
   				" <thead>    "+
   				"  <tr>"+
   				"  		<th width=\"20%\" style=\"text-align: center\"><div class=\"th_class\">รหัสกลุ่มวิจัย</div></th>"+ 
   				"		<th width=\"40%\" style=\"text-align: center\"><div class=\"th_class\">ชื่อกลุ่มวิจัยภาษาไทย</div></th>  "+
   				"		<th width=\"40%\" style=\"text-align: center\"><div class=\"th_class\">ช่ือกลุ่มวิจัยภาษาอังกฤษ</div></th> "+
   				"	</tr> 		"+
   				" </thead><tbody>";
   				var pageObj={
   						pageSize:PAGE_SIZE_POPUP,
   						pageNo:pageNo
   				}
   				var researchGroupM={
   	  		    		keySearch:keySearch,
   	  		    		paging:pageObj
   	  		    }	
       	ResearchAjax.getResearchGroupList(researchGroupM,{
   			callback:function(data){ 
   				var maxRow=data.maxRow;
   				var lastpage=data.lastpage;
   				data=data.resultListObj;
   				if(data!=null && data.length>0){
   				  for(var i=0;i<data.length;i++){
   					str=str+"<tr  onclick=\"<portlet:namespace />selectResearchGroup('"+data[i].researchGroupId+"')\" style=\"cursor: pointer;\">"+
   		    		"<td style=\"text-align: left\"  >"+data[i].groupCode+"</td>"+  
   		    		"<td style=\"text-align: left\"  >"+data[i].groupTh+"</td>"+ 
   		    		"<td style=\"text-align: left\"  >"+data[i].groupEng+"</td>"+ 
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
                					pagingStr=pagingStr+"<li><a style=\"cursor: pointer;\" onclick='<portlet:namespace />doSearchBox(\"researchGroup\","+(pageNo-1)+")'>Prev</a></li>";
                			
                				for(var j=pageStart;j<=pageEnd;j++){
                					if(pageNo==j){
                						pagingStr=pagingStr+"<li><a class=\"active\" style=\"background-color: #ddd\" >"+j+"</a></li>";
                					}
                					else{
                						pagingStr=pagingStr+"<li><a class=\"active\" style=\"cursor: pointer;\" onclick='<portlet:namespace />doSearchBox(\"researchGroup\","+(j)+")'>"+j+"</a></li>";
                					}
                				}
                				if(pageEnd<lastpage){
                					pagingStr=pagingStr+"<li><a class=\"active\" style=\"cursor: pointer;\" onclick='<portlet:namespace />doSearchBox(\"researchGroup\","+(pageEnd)+")'>...</a></li>";
                					pagingStr=pagingStr+"<li><a class=\"active\" style=\"cursor: pointer;\" onclick='<portlet:namespace />doSearchBox(\"researchGroup\","+(lastpage)+")'>"+lastpage+"</a></li>";
                				}
                				if(pageNo!=lastpage){
                					pagingStr=pagingStr+"<li><a style=\"cursor: pointer;\" onclick='<portlet:namespace />doSearchBox(\"researchGroup\","+((pageNo<lastpage)?(pageNo+1):(lastpage))+")'>Next</a></li>";
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
   				bootbox.dialog(keyBox+"<div id=\"<portlet:namespace />researchGroupPopupTable\">"+str+pagingStr+"</div>",[{
   				    "label" : "Close",
   				     "class" : "btn-danger",
   				  "callback": function() {
   			    }
   				   
   			   }]);
   			}else{
   				$("#<portlet:namespace />researchGroupPopupTable").html(str+pagingStr);
   			}
   		 }
   	  });	
    }
    function <portlet:namespace />selectResearchGroup(objID){
    	ResearchAjax.findResearchGroupById(objID,{
			callback:function(data){ 
				$('#<portlet:namespace/>researchGroupId').val(objID);
   				$("#researchGroupId_assignShow").html(" "+data.groupTh+" ");
   				$("#researchGroupId_assign_autocomplete").val(data.groupTh)
				bootbox.hideAll();
			}
 		});
    }
    <%-- end ResearchGroup Popup --%>
    
    <%-- start organization Popup --%>
    function <portlet:namespace />organizationPopup(keySearch,init,pageNo){
       	var keyBox=" <div>"+
        		   		"<input type=\"text\"  style=\"display:inline;width:250px\" class=\"form-control\" "+ 
        		   		" id=\"<portlet:namespace />keySearch_organization\" onkeypress=\"<portlet:namespace />chk(\'organization\',event)\" aria-describedby=\"inputSuccess4Status\" value=\""+keySearch+"\"/>"+
                   		"<button onclick=\"<portlet:namespace />doSearchBox('organization',1)\" type=\"button\" class=\"btn btn-success\" style=\"margin-top:-8px;margin-left:5px;\">Search</button>"+
    			   "</div>";
       	var str="<table class=\"table table-hover table-striped table-bordered table-condensed\" border=\"1\" style=\"font-size: 12px\"> "+
   				" <thead>    "+
   				"  <tr>"+
   			//	"  		<th width=\"20%\" style=\"text-align: center\"><div class=\"th_class\">รหัสกลุ่มวิจัย</div></th>"+ 
   			//	"		<th width=\"40%\" style=\"text-align: center\"><div class=\"th_class\">ชื่อกลุ่มวิจัยภาษาไทย</div></th>  "+
   				"		<th width=\"100%\" style=\"text-align: center\"><div class=\"th_class\">ชื่อหน่วยงานมจธ. ที่อยู่นอกสทบ.</div></th> "+
   				"	</tr> 		"+
   				" </thead><tbody>";
   			  
   				var pageObj={
   						pageSize:PAGE_SIZE_POPUP,
   						pageNo:pageNo
   				}
   				var organizationM={
   	  		    		keySearch:keySearch,
   	  		    		paging:pageObj
   	  		    }	
       	ResearchAjax.getOrganizationList(organizationM,{
   			callback:function(data){ 
   				var maxRow=data.maxRow;
   				var lastpage=data.lastpage;
   				data=data.resultListObj;
   				if(data!=null && data.length>0){
   				  for(var i=0;i<data.length;i++){
   					str=str+"<tr  onclick=\"<portlet:namespace />selectOrganization('"+data[i].organizationId+"')\" style=\"cursor: pointer;\">"+
   		    		"<td style=\"text-align: left\"  >"+data[i].orgName+"</td>"+  
   		    	
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
                					pagingStr=pagingStr+"<li><a style=\"cursor: pointer;\" onclick='<portlet:namespace />doSearchBox(\"organization\","+(pageNo-1)+")'>Prev</a></li>";
                			
                				for(var j=pageStart;j<=pageEnd;j++){
                					if(pageNo==j){
                						pagingStr=pagingStr+"<li><a class=\"active\" style=\"background-color: #ddd\" >"+j+"</a></li>";
                					}
                					else{
                						pagingStr=pagingStr+"<li><a class=\"active\" style=\"cursor: pointer;\" onclick='<portlet:namespace />doSearchBox(\"organization\","+(j)+")'>"+j+"</a></li>";
                					}
                				}
                				if(pageEnd<lastpage){
                					pagingStr=pagingStr+"<li><a class=\"active\" style=\"cursor: pointer;\" onclick='<portlet:namespace />doSearchBox(\"organization\","+(pageEnd)+")'>...</a></li>";
                					pagingStr=pagingStr+"<li><a class=\"active\" style=\"cursor: pointer;\" onclick='<portlet:namespace />doSearchBox(\"organization\","+(lastpage)+")'>"+lastpage+"</a></li>";
                				}
                				if(pageNo!=lastpage){
                					pagingStr=pagingStr+"<li><a style=\"cursor: pointer;\" onclick='<portlet:namespace />doSearchBox(\"organization\","+((pageNo<lastpage)?(pageNo+1):(lastpage))+")'>Next</a></li>";
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
   				bootbox.dialog(keyBox+"<div id=\"<portlet:namespace />organizationPopupTable\">"+str+pagingStr+"</div>",[{
   				    "label" : "Close",
   				     "class" : "btn-danger",
   				  "callback": function() {
   			    }
   				   
   			   }]);
   			}else{
   				$("#<portlet:namespace />organizationPopupTable").html(str+pagingStr);
   			}
   		 }
   	  });	
    }
    function <portlet:namespace />selectOrganization(objID){
    	ResearchAjax.findOrganizationById(objID,{
			callback:function(data){ 
				$('input[id="researcherM.organization.organizationId"]').val(objID);
   				$("#organizationId_assign_autocomplete").val(data.orgName);
				bootbox.hideAll();
			}
 		});
    }
    <%-- end organization Popup --%>
    
    
    <%-- start organization Popup --%>
    function <portlet:namespace />positionPopup(keySearch,init,pageNo){
       	var keyBox=" <div>"+
        		   		"<input type=\"text\"  style=\"display:inline;width:250px\" class=\"form-control\" "+ 
        		   		" id=\"<portlet:namespace />keySearch_position\" onkeypress=\"<portlet:namespace />chk(\'position\',event)\" aria-describedby=\"inputSuccess4Status\" value=\""+keySearch+"\"/>"+
                   		"<button onclick=\"<portlet:namespace />doSearchBox('position',1)\" type=\"button\" class=\"btn btn-success\" style=\"margin-top:-8px;margin-left:5px;\">Search</button>"+
    			   "</div>";
       	var str="<table class=\"table table-hover table-striped table-bordered table-condensed\" border=\"1\" style=\"font-size: 12px\"> "+
   				" <thead>    "+
   				"  <tr>"+
   			//	"  		<th width=\"20%\" style=\"text-align: center\"><div class=\"th_class\">รหัสกลุ่มวิจัย</div></th>"+ 
   				"		<th width=\"40%\" style=\"text-align: center\"><div class=\"th_class\">รหัสตำแหน่ง</div></th>  "+
   				"		<th width=\"60%\" style=\"text-align: center\"><div class=\"th_class\">ชื่อตำแหน่ง</div></th> "+
   				"	</tr> 		"+
   				" </thead><tbody>";
   		
   				var pageObj={
   						pageSize:PAGE_SIZE_POPUP,
   						pageNo:pageNo
   				}
   				var positionM={
   	  		    		keySearch:keySearch,
   	  		    		paging:pageObj
   	  		    }	
       	ResearchAjax.listPosition(positionM,{
   			callback:function(data){ 
   				var maxRow=data.maxRow;
   				var lastpage=data.lastpage;
   				data=data.resultListObj;
   				if(data!=null && data.length>0){
   				  for(var i=0;i<data.length;i++){
   					str=str+"<tr  onclick=\"<portlet:namespace />selectposition('"+data[i].positionId+"')\" style=\"cursor: pointer;\">"+
   		    		"<td style=\"text-align: left\"  >"+data[i].positionCode+"</td>"+  
   		    		"<td style=\"text-align: left\"  >"+data[i].positionName+"</td>"+  
   		    		
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
                					pagingStr=pagingStr+"<li><a style=\"cursor: pointer;\" onclick='<portlet:namespace />doSearchBox(\"position\","+(pageNo-1)+")'>Prev</a></li>";
                			
                				for(var j=pageStart;j<=pageEnd;j++){
                					if(pageNo==j){
                						pagingStr=pagingStr+"<li><a class=\"active\" style=\"background-color: #ddd\" >"+j+"</a></li>";
                					}
                					else{
                						pagingStr=pagingStr+"<li><a class=\"active\" style=\"cursor: pointer;\" onclick='<portlet:namespace />doSearchBox(\"position\","+(j)+")'>"+j+"</a></li>";
                					}
                				}
                				if(pageEnd<lastpage){
                					pagingStr=pagingStr+"<li><a class=\"active\" style=\"cursor: pointer;\" onclick='<portlet:namespace />doSearchBox(\"position\","+(pageEnd)+")'>...</a></li>";
                					pagingStr=pagingStr+"<li><a class=\"active\" style=\"cursor: pointer;\" onclick='<portlet:namespace />doSearchBox(\"position\","+(lastpage)+")'>"+lastpage+"</a></li>";
                				}
                				if(pageNo!=lastpage){
                					pagingStr=pagingStr+"<li><a style=\"cursor: pointer;\" onclick='<portlet:namespace />doSearchBox(\"position\","+((pageNo<lastpage)?(pageNo+1):(lastpage))+")'>Next</a></li>";
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
   				bootbox.dialog(keyBox+"<div id=\"<portlet:namespace />positionPopupTable\">"+str+pagingStr+"</div>",[{
   				    "label" : "Close",
   				     "class" : "btn-danger",
   				  "callback": function() {
   			    }
   				   
   			   }]);
   			}else{
   				$("#<portlet:namespace />positionPopupTable").html(str+pagingStr);
   			}
   		 }
   	  });	
    }
    function <portlet:namespace />selectposition(objID){
    	ResearchAjax.findPositionById(objID,{
			callback:function(data){ 
				$('input[id="researcherM.position.positionId"]').val(objID);
   				$("#positionId_assign_autocomplete").val(data.positionName);
   				
				bootbox.hideAll();
			}
 		});
    }
    <%-- end position Popup --%>
    
    
    <%-- start academicPosition Popup --%>
    function <portlet:namespace />academicPositionPopup(keySearch,init,pageNo){
       	var keyBox=" <div>"+
        		   		"<input type=\"text\"  style=\"display:inline;width:250px\" class=\"form-control\" "+ 
        		   		" id=\"<portlet:namespace />keySearch_academicPosition\" onkeypress=\"<portlet:namespace />chk(\'academicPosition\',event)\" aria-describedby=\"inputSuccess4Status\" value=\""+keySearch+"\"/>"+
                   		"<button onclick=\"<portlet:namespace />doSearchBox('academicPosition',1)\" type=\"button\" class=\"btn btn-success\" style=\"margin-top:-8px;margin-left:5px;\">Search</button>"+
    			   "</div>";
       	var str="<table class=\"table table-hover table-striped table-bordered table-condensed\" border=\"1\" style=\"font-size: 12px\"> "+
   				" <thead>    "+
   				"  <tr>"+
   			//	"  		<th width=\"20%\" style=\"text-align: center\"><div class=\"th_class\">รหัสกลุ่มวิจัย</div></th>"+ 
   				"		<th width=\"40%\" style=\"text-align: center\"><div class=\"th_class\">รหัสตำแหน่ง</div></th>  "+
   				"		<th width=\"60%\" style=\"text-align: center\"><div class=\"th_class\">ชื่อตำแหน่ง</div></th> "+
   				"	</tr> 		"+
   				" </thead><tbody>";
   		
   				var pageObj={
   						pageSize:PAGE_SIZE_POPUP,
   						pageNo:pageNo
   				}
   				var academicPositionM={
   	  		    		keySearch:keySearch,
   	  		    		paging:pageObj
   	  		    }	
       	ResearchAjax.listPosition(academicPositionM,{
   			callback:function(data){ 
   				var maxRow=data.maxRow;
   				var lastpage=data.lastpage;
   				data=data.resultListObj;
   				if(data!=null && data.length>0){
   				  for(var i=0;i<data.length;i++){
   					str=str+"<tr  onclick=\"<portlet:namespace />selectacademicPosition('"+data[i].positionId+"')\" style=\"cursor: pointer;\">"+
   		    		"<td style=\"text-align: left\"  >"+data[i].positionCode+"</td>"+  
   		    		"<td style=\"text-align: left\"  >"+data[i].positionName+"</td>"+  
   		    		
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
                					pagingStr=pagingStr+"<li><a style=\"cursor: pointer;\" onclick='<portlet:namespace />doSearchBox(\"academicPosition\","+(pageNo-1)+")'>Prev</a></li>";
                			
                				for(var j=pageStart;j<=pageEnd;j++){
                					if(pageNo==j){
                						pagingStr=pagingStr+"<li><a class=\"active\" style=\"background-color: #ddd\" >"+j+"</a></li>";
                					}
                					else{
                						pagingStr=pagingStr+"<li><a class=\"active\" style=\"cursor: pointer;\" onclick='<portlet:namespace />doSearchBox(\"academicPosition\","+(j)+")'>"+j+"</a></li>";
                					}
                				}
                				if(pageEnd<lastpage){
                					pagingStr=pagingStr+"<li><a class=\"active\" style=\"cursor: pointer;\" onclick='<portlet:namespace />doSearchBox(\"academicPosition\","+(pageEnd)+")'>...</a></li>";
                					pagingStr=pagingStr+"<li><a class=\"active\" style=\"cursor: pointer;\" onclick='<portlet:namespace />doSearchBox(\"academicPosition\","+(lastpage)+")'>"+lastpage+"</a></li>";
                				}
                				if(pageNo!=lastpage){
                					pagingStr=pagingStr+"<li><a style=\"cursor: pointer;\" onclick='<portlet:namespace />doSearchBox(\"academicPosition\","+((pageNo<lastpage)?(pageNo+1):(lastpage))+")'>Next</a></li>";
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
   				bootbox.dialog(keyBox+"<div id=\"<portlet:namespace />academicPositionPopupTable\">"+str+pagingStr+"</div>",[{
   				    "label" : "Close",
   				     "class" : "btn-danger",
   				  "callback": function() {
   			    }
   				   
   			   }]);
   			}else{
   				$("#<portlet:namespace />academicPositionPopupTable").html(str+pagingStr);
   			}
   		 }
   	  });	
    }
    function <portlet:namespace />selectacademicPosition(objID){
    	ResearchAjax.findPositionById(objID,{
			callback:function(data){ 
				$('input[id="researcherM.academicPosition.positionId"]').val(objID);
   				$("#academic_positionId_assign_autocomplete").val(data.positionName);
   				
				bootbox.hideAll();
			}
 		});
    }
    <%-- end academicPosition Popup --%>
    
    <%-- start title Popup --%>
    function <portlet:namespace />titlePopup(keySearch,init,pageNo){
       	var keyBox=" <div>"+
        		   		"<input type=\"text\"  style=\"display:inline;width:250px\" class=\"form-control\" "+ 
        		   		" id=\"<portlet:namespace />keySearch_title\" onkeypress=\"<portlet:namespace />chk(\'title\',event)\" aria-describedby=\"inputSuccess4Status\" value=\""+keySearch+"\"/>"+
                   		"<button onclick=\"<portlet:namespace />doSearchBox('title',1)\" type=\"button\" class=\"btn btn-success\" style=\"margin-top:-8px;margin-left:5px;\">Search</button>"+
    			   "</div>";
       	var str="<table class=\"table table-hover table-striped table-bordered table-condensed\" border=\"1\" style=\"font-size: 12px\"> "+
   				" <thead>    "+
   				"  <tr>"+
   			//	"  		<th width=\"20%\" style=\"text-align: center\"><div class=\"th_class\">รหัสกลุ่มวิจัย</div></th>"+ 
   				"		<th width=\"40%\" style=\"text-align: center\"><div class=\"th_class\">รหัสคำนำหน้า</div></th>  "+
   				"		<th width=\"60%\" style=\"text-align: center\"><div class=\"th_class\">ชื่อคำนำหน้า</div></th> "+
   				"	</tr> 		"+
   				" </thead><tbody>";
   				
   				var pageObj={
   						pageSize:PAGE_SIZE_POPUP,
   						pageNo:pageNo
   				}
   				var titleM={
   	  		    		keySearch:keySearch,
   	  		    		paging:pageObj
   	  		    }	
       	ResearchAjax.listTitle(titleM,{
   			callback:function(data){ 
   				var maxRow=data.maxRow;
   				var lastpage=data.lastpage;
   				data=data.resultListObj;
   				if(data!=null && data.length>0){
   				  for(var i=0;i<data.length;i++){
   					str=str+"<tr  onclick=\"<portlet:namespace />selecttitle('"+data[i].titleId+"')\" style=\"cursor: pointer;\">"+
   		    		"<td style=\"text-align: left\"  >"+data[i].titleCode+"</td>"+  
   		    		"<td style=\"text-align: left\"  >"+data[i].titleName+"</td>"+  
   		    		
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
                					pagingStr=pagingStr+"<li><a style=\"cursor: pointer;\" onclick='<portlet:namespace />doSearchBox(\"title\","+(pageNo-1)+")'>Prev</a></li>";
                			
                				for(var j=pageStart;j<=pageEnd;j++){
                					if(pageNo==j){
                						pagingStr=pagingStr+"<li><a class=\"active\" style=\"background-color: #ddd\" >"+j+"</a></li>";
                					}
                					else{
                						pagingStr=pagingStr+"<li><a class=\"active\" style=\"cursor: pointer;\" onclick='<portlet:namespace />doSearchBox(\"title\","+(j)+")'>"+j+"</a></li>";
                					}
                				}
                				if(pageEnd<lastpage){
                					pagingStr=pagingStr+"<li><a class=\"active\" style=\"cursor: pointer;\" onclick='<portlet:namespace />doSearchBox(\"title\","+(pageEnd)+")'>...</a></li>";
                					pagingStr=pagingStr+"<li><a class=\"active\" style=\"cursor: pointer;\" onclick='<portlet:namespace />doSearchBox(\"title\","+(lastpage)+")'>"+lastpage+"</a></li>";
                				}
                				if(pageNo!=lastpage){
                					pagingStr=pagingStr+"<li><a style=\"cursor: pointer;\" onclick='<portlet:namespace />doSearchBox(\"title\","+((pageNo<lastpage)?(pageNo+1):(lastpage))+")'>Next</a></li>";
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
   				bootbox.dialog(keyBox+"<div id=\"<portlet:namespace />titlePopupTable\">"+str+pagingStr+"</div>",[{
   				    "label" : "Close",
   				     "class" : "btn-danger",
   				  "callback": function() {
   			    }
   				   
   			   }]);
   			}else{
   				$("#<portlet:namespace />titlePopupTable").html(str+pagingStr);
   			}
   		 }
   	  });	
    }
    function <portlet:namespace />selecttitle(objID){
    	ResearchAjax.findTitleById(objID,{
			callback:function(data){ 
				
				$('input[id="researcherM.title.titleId"]').val(objID);
   				$("#titleId_assign_autocomplete").val(data.titleName);
   				
				bootbox.hideAll();
			}
 		});
    }
			
    <%-- end title Popup --%>
    
    <%-- start academicTitle Popup --%>
    function <portlet:namespace />academicTitlePopup(keySearch,init,pageNo){
       	var keyBox=" <div>"+
        		   		"<input type=\"text\"  style=\"display:inline;width:250px\" class=\"form-control\" "+ 
        		   		" id=\"<portlet:namespace />keySearch_academicTitle\" onkeypress=\"<portlet:namespace />chk(\'academicTitle\',event)\" aria-describedby=\"inputSuccess4Status\" value=\""+keySearch+"\"/>"+
                   		"<button onclick=\"<portlet:namespace />doSearchBox('academicTitle',1)\" type=\"button\" class=\"btn btn-success\" style=\"margin-top:-8px;margin-left:5px;\">Search</button>"+
    			   "</div>";
       	var str="<table class=\"table table-hover table-striped table-bordered table-condensed\" border=\"1\" style=\"font-size: 12px\"> "+
   				" <thead>    "+
   				"  <tr>"+
   			//	"  		<th width=\"20%\" style=\"text-align: center\"><div class=\"th_class\">รหัสกลุ่มวิจัย</div></th>"+ 
   				"		<th width=\"40%\" style=\"text-align: center\"><div class=\"th_class\">รหัสคำนำหน้า</div></th>  "+
   				"		<th width=\"60%\" style=\"text-align: center\"><div class=\"th_class\">ชื่อคำนำหน้า</div></th> "+
   				"	</tr> 		"+
   				" </thead><tbody>";
   			
   				var pageObj={
   						pageSize:PAGE_SIZE_POPUP,
   						pageNo:pageNo
   				}
   				var academicTitleM={
   	  		    		keySearch:keySearch,
   	  		    		paging:pageObj
   	  		    }	
       	ResearchAjax.listTitle(academicTitleM,{
   			callback:function(data){ 
   				var maxRow=data.maxRow;
   				var lastpage=data.lastpage;
   				data=data.resultListObj;
   				if(data!=null && data.length>0){
   				  for(var i=0;i<data.length;i++){
   					str=str+"<tr  onclick=\"<portlet:namespace />selectacademicTitle('"+data[i].titleId+"')\" style=\"cursor: pointer;\">"+
   		    		"<td style=\"text-align: left\"  >"+data[i].academicTitleCode+"</td>"+  
   		    		"<td style=\"text-align: left\"  >"+data[i].academicTitleName+"</td>"+  
   		    		
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
                					pagingStr=pagingStr+"<li><a style=\"cursor: pointer;\" onclick='<portlet:namespace />doSearchBox(\"academicTitle\","+(pageNo-1)+")'>Prev</a></li>";
                			
                				for(var j=pageStart;j<=pageEnd;j++){
                					if(pageNo==j){
                						pagingStr=pagingStr+"<li><a class=\"active\" style=\"background-color: #ddd\" >"+j+"</a></li>";
                					}
                					else{
                						pagingStr=pagingStr+"<li><a class=\"active\" style=\"cursor: pointer;\" onclick='<portlet:namespace />doSearchBox(\"academicTitle\","+(j)+")'>"+j+"</a></li>";
                					}
                				}
                				if(pageEnd<lastpage){
                					pagingStr=pagingStr+"<li><a class=\"active\" style=\"cursor: pointer;\" onclick='<portlet:namespace />doSearchBox(\"academicTitle\","+(pageEnd)+")'>...</a></li>";
                					pagingStr=pagingStr+"<li><a class=\"active\" style=\"cursor: pointer;\" onclick='<portlet:namespace />doSearchBox(\"academicTitle\","+(lastpage)+")'>"+lastpage+"</a></li>";
                				}
                				if(pageNo!=lastpage){
                					pagingStr=pagingStr+"<li><a style=\"cursor: pointer;\" onclick='<portlet:namespace />doSearchBox(\"academicTitle\","+((pageNo<lastpage)?(pageNo+1):(lastpage))+")'>Next</a></li>";
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
   				bootbox.dialog(keyBox+"<div id=\"<portlet:namespace />academicTitlePopupTable\">"+str+pagingStr+"</div>",[{
   				    "label" : "Close",
   				     "class" : "btn-danger",
   				  "callback": function() {
   			    }
   				   
   			   }]);
   			}else{
   				$("#<portlet:namespace />academicTitlePopupTable").html(str+pagingStr);
   			}
   		 }
   	  });	
    }
    function <portlet:namespace />selectacademicTitle(objID){
    	ResearchAjax.findTitleById(objID,{
			callback:function(data){ 
				
				$('input[id="researcherM.academicTitle.titleId"]').val(objID);
   				$("#academic_titleId_assign_autocomplete").val(data.academicTitleName);
   			
				bootbox.hideAll();
			}
 		});
    }
    <%-- end title Popup --%>
    </script>
  </body>
</html>
