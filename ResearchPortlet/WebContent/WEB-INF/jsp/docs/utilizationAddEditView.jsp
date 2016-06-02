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
  <form:form  id="utilizationForm" modelAttribute="utilizationForm" method="post"  name="utilizationForm" action="${formAction}" enctype="multipart/form-data">
  
        <fieldset style="font-family: sans-serif;padding-top:5px">
        <input type="hidden" name="command" id="<portlet:namespace />common" value="${utilizationForm.command}" />
              <input type="hidden" name="mode" id="<portlet:namespace />mode"  value="${utilizationForm.mode}" />
  
    <div style="border: 1px solid #FFC299;background: #F9F9F9;padding: 10px">
    <div class="accordion" id="accordion2">
                <div class="accordion-group">
                  <div class="accordion-heading">
                    <a href="${list}" style="padding-right: 20px;" >Back</a> 
                    <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion2" href="#collapseOne">
                      ข้อมูลงานวิจัยที่นำไปใช้ประโยชน์
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
                        <label><span>โครงการวิจัย:
                        </span> </label>
              </td>
              <td width="80%" colspan="3">
                
                     <c:if test="${utilizationForm.mode=='add'}">
                      <form:hidden path="utilizationM.researchProject.researchProjectId"  id="researchProjectId"/>
                 	<input  type="text" style="background-color: 
                    rgb(250, 250, 198);width:450px" class="form-control" 
                    id="researchProjectId_autocomplete" placeholder="" value="${utilizationForm.utilizationM.researchProject.thaiName}"/>
                    <button style="margin-top: -8px"  onclick="<portlet:namespace />researchProjectPopup('',true,1)" type="button" class="btn btn-warning btn-small">...</button>
                     </c:if>
                        <c:if test="${utilizationForm.mode=='edit'}">
                         <form:hidden path="utilizationM.researchProject.researchProjectId" readonly="true"  id="researchProjectId"/>
                 <input  type="text" style="width:450px" class="form-control" 
                    id="researchProjectId_autocomplete" placeholder="" readonly="true"  value="${utilizationForm.utilizationM.researchProject.thaiName}"/>
                        </c:if>
              
              </td>
             
            </tr>
            <%--
            <tr>
              <td align="right" width="20%">
                        <label><span>
                        </span> </label>
              </td>
              <td width="80%" colspan="3">
                    <form:textarea path="utilizationM.researchProject.thaiName" readonly="true" cssStyle="width:500px" cssClass="form-control" rows="2"/>
              </td>
             
            </tr>
             --%>
            <%--
            <tr valign="top">
              <td align="right" width="20%">
                        <span><label>Assign to:<span style="color: red;font-size: 20;"> </span> 
                        </label></span> 
              </td>
              <td width="80%" colspan="3">
              	
    	     		<input type="hidden" id="docsAssign" name="docsAssign"  />
                   <input  type="text" style="background-color: rgb(250, 250, 198);width:50px" class="form-control" id="docs_assign_autocomplete" placeholder=""/>
                   <span id="docs_assignShow"> </span>
                   <div>
                   <table border="1" width="50%" style="border: 1px solid #FFC299;font-size:13px">
                   	<tr>
                   		<td width="50%">นาย a <i style="padding-left: 10px;" class="icon-trash"></i> </td>
                   		
                   	</tr>
                   	<tr>
                   		<td width="50%">นาย b  <button class="btn btn-danger btn-small" type="button">ลบ</button> </td>
                   	</tr>
                   </table>
                   </div>
              </td> 
            </tr>    
             --%>
              <c:if test="${utilizationForm.mode=='add'}">
                <tr>
              <td align="right" width="20%">
                        <label><span>ด้านการนำไปใช้ประโยชน์:
                        </span> </label>
              </td>
              <td width="80%" colspan="3">
                 
                  <div> 
                    	 <form:hidden path="utilizationM.utilizationType.utilizationTypeId"/>
                    	<%-- <form:hidden path="utilizationM.utilizationType.utilizationTypeId"/>  --%> 
                   <input  type="text" style="background-color: 
                    rgb(250, 250, 198);width:350px" class="form-control" 
                    id="utilizationTypeId_add_autocomplete" placeholder="" value=""/>
                    <button style="margin-top: -8px" onclick="<portlet:namespace />utilizationTypeAddPopup('',true,1)" type="button" class="btn btn-warning btn-small">...</button>
              <%--
                   <span id="utilizationTypeId_add_assignShow"></span>
               --%>
                       </div>
              </td>
             
            </tr>
             <tr>
              <td align="right" width="20%">
                        <label><span>OutCome:
                        </span> </label>
              </td>
              <td width="80%" colspan="3">
                   <form:textarea path="utilizationM.outCome"  cssStyle="width:500px" cssClass="form-control" rows="2"/>  
              </td>
             
            </tr>
             <tr>
              <td align="right" width="20%">
                        <label><span>ปีงบประมาณ:
                        </span> </label>
              </td>
              <td width="80%" colspan="3">
          
                    <form:input path="utilizationM.budgetYear" maxlength="4" cssStyle="width:70px" cssClass="form-control" />
              </td>
             
            </tr>
  				 </c:if>
   
             <tr>
              <td align="center" colspan="4" width="20%">
               <c:if test="${utilizationForm.mode=='add'}">
                       <button type="button" onclick="<portlet:namespace />doSubmitForm()" class="btn btn-primary">Submit</button>
                    </c:if>  
              </td>
              
            </tr> 
          </table>
                    </div>
                  </div>
                </div>
                <div class="accordion-group">
                  <div class="accordion-heading">
                    <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion2" href="#collapseTwo">
                      นำไปใช้ประโยชน์ในด้าน
                    </a>
                  </div>
                  <div id="collapseTwo" class="accordion-body collapse" style="height: 0px;">
                    <div class="accordion-inner"> 
         <table border="0" width="100%" style="font-size: 13px">
                <tbody>
                <tr>
                <td align="center" width="100%" colspan="2">  
                 <div id="<portlet:namespace/>element_edit" style="border: 1px solid #FFC299;background: #F9F9F9;padding: 10px;display: none">
                 <table border="0" width="100%" style="border: 0px solid #FFC299;font-size:13px">
         
                 <tr>
              		<td align="right" width="20%">
                        <span>
                        	<label>ด้านการนำไปใช้ประโยชน์:<span style="color: red;font-size: 50;"> </span> 
                        	</label>
                        </span> 
             	 	</td>
              	 	<td width="80%" colspan="3">
                    	<div> 
                    	<input type="hidden" id="<portlet:namespace/>utilizationTypeId"/>
                    	<%-- <form:hidden path="utilizationM.utilizationType.utilizationTypeId"/>  --%> 
                   <input  type="text" style="background-color: 
                    rgb(250, 250, 198);width:350px" class="form-control" 
                    id="utilizationTypeId_autocomplete" placeholder="" value=""/>
                   <button id="<portlet:namespace/>buttonShowAll" style="margin-top: -8px;display: none" onclick="<portlet:namespace />utilizationTypeEditPopup('',true,1)" type="button" class="btn btn-warning btn-small">...</button>
                
                    <%--
                   <span id="utilizationTypeId_assignShow"></span>
               --%>
                       </div>
              		</td> 
            	</tr> 
            	<tr>
              		<td align="right" width="20%">
                        <span>
                        	<label>ปีงบประมาณ:<span style="color: red;font-size: 50;"> </span> 
                        	</label>
                        </span> 
             	 	</td>
              	 	<td width="80%" colspan="3">
                    	<div> 
                    	<input type="hidden" id="<portlet:namespace/>utilization_mode"/>
                    	<input type="hidden" id="<portlet:namespace/>utilization_utilizationItemList"/>
                     
                    	 <input type="text"  id="<portlet:namespace/>budgetYear" style="75px;" maxlength="4" class="form-control"  />
                    	
                         <%-- <form:textarea path="utilizationM.outCome"  cssStyle="width:500px" cssClass="form-control" rows="2"/>  --%>	
                       </div>
              		</td> 
            	</tr> 
            	 <tr>
              		<td align="right" width="20%">
                        <span>
                        	<label>OutCome:<span style="color: red;font-size: 50;"> </span> 
                        	</label>
                        </span> 
             	 	</td>
              	 	<td width="80%" colspan="3">
                    	<div> 
                    	 <textarea  id="<portlet:namespace/>outCome" rows="2" style="width:500px"></textarea>
                         <%-- <form:textarea path="utilizationM.outCome"  cssStyle="width:500px" cssClass="form-control" rows="2"/>  --%>	
                       </div>
              		</td> 
            	</tr>
            	<%--
            	<tr>
              		<td align="right" width="20%">
                        <span>
                        	<label>Document:<span style="color: red;font-size: 50;"> </span> 
                        	</label>
                        </span> 
             	 	</td>
              	 	<td width="80%" colspan="3">
                    	<div> 
                    	
                    			  <input type="hidden" id="<portlet:namespace/>document_mode"/>
				    	         <input type="hidden" id="<portlet:namespace/>document_itemList"/>
                    			 <input type="file" id="<portlet:namespace/>uploadFile"  name="<portlet:namespace/>uploadFile" />  
                      		
                       </div>
              		</td> 
            	</tr>
            	 --%>
            	 <%--
            	 <tr>
              				<td align="right" width="30%">
                        		<span>
                        			<label><span style="color: red;font-size: 50;"> </span> 
                        			</label>
                        		</span> 
             	 			</td>
              	 			<td width="70%" colspan="3">
                    			<div>
                    			 <input type="file" id="<portlet:namespace/>uploadFile"  name="<portlet:namespace/>uploadFile" /> 
                      		 </div>
              				</td> 
            			</tr>
            			 --%> 
              <tr>
              <td align="center" colspan="4" width="20%">
               <form:hidden path="utilizationM.docType"/>
                  <button type="button" onclick="<portlet:namespace />doSubmitFormAjax()" class="btn btn-primary">Submit</button>  
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
              <c:if test="${utilizationForm.mode=='edit'}"> 
                  <button  onclick="<portlet:namespace/>displayElementEdit('add','0')" type="button" class="btn btn-primary">Add</button> 
               </c:if> 
               </span >
                </td>
                </tr>
                </tbody></table>  
                <span id="<portlet:namespace/>utilization_item_list">
        <table class="table table-hover table-striped table-bordered table-condensed" border="1" style="font-size: 12px"> 
               <thead>    
                   <tr> <!-- 50 42+8 -->
                   <th width="5%" style="text-align: center"><div class="th_class">#</div></th> 
                 <th width="20%" style="text-align: center"><div class="th_class">ด้านการนำไปใช้ประโยชน์</div></th>  
                   <th width="55%" style="text-align: center"><div class="th_class">OutCome</div></th>  
                  <th width="10%" style="text-align: center"><div class="th_class">ปีงบประมาณ</div></th> 
                <th width="10%"><div class="th_class"></div></th>  
                   </tr> 
                </thead> 
                <tbody>   
                <c:if test="${not empty utilizationForm.utilizationM.utilizations}"> 
                 <c:forEach items="${utilizationForm.utilizationM.utilizations}" var="utilization" varStatus="loop"> 
                 	<tr style="cursor: pointer;">
                		<td onclick='<portlet:namespace />doEditUtilizationAjax("${utilization.researchProject.researchProjectId}","${utilization.utilizationItemList}")' style="text-align: left">${loop.index+1}</td>
                		<td onclick='<portlet:namespace />doEditUtilizationAjax("${utilization.researchProject.researchProjectId}","${utilization.utilizationItemList}")' style="text-align: left">${utilization.utilizationType.utilizationName}</td> 
                		<td onclick='<portlet:namespace />doEditUtilizationAjax("${utilization.researchProject.researchProjectId}","${utilization.utilizationItemList}")' style="text-align: left">${utilization.outCome}</td> 
                		<td onclick='<portlet:namespace />doEditUtilizationAjax("${utilization.researchProject.researchProjectId}","${utilization.utilizationItemList}")' style="text-align: center">${utilization.budgetYear}</td> 
                		<td style="text-align: center">
                		   <%--
                			<i onclick='displayElementEdit("edit","<portlet:resourceURL  id="research_group_resource_get_byid"><portlet:param name="utilizationId"><jsp:attribute name="value"><c:out value="${utilization.utilizationId}"/></jsp:attribute></portlet:param></portlet:resourceURL>","${utilization.utilizationId}")' class="icon-edit"></i>
                			 --%>
                			 <button onclick='<portlet:namespace />doEditUtilizationAjax("${utilization.researchProject.researchProjectId}","${utilization.utilizationItemList}")' class="btn btn-small" type="button">แก้ใข</button>
                			 <button onclick='<portlet:namespace />doDeleteUtilizationAjax("${utilization.researchProject.researchProjectId}","${utilization.utilizationItemList}")' class="btn btn-danger btn-small" type="button">ลบ</button> 
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
    	        $('#docs_assign_autocomplete').autocomplete({
    	     		  source: function( request, response ) {    
    	     			/* private Integer userId;
    	     			
    	     			private String emailAddress;
    	     			
    	     			private String firstName;
    	     			
    	     			private String lastName;
    	     			
    	     			private String screenName;
    	     			*/
    	     			var modelM={
    	      		    		keySearch:request.term
    	      		    }	
    	     			ResearchAjax.getUserPortalList(modelM,{
    	     					callback:function(data){ 
    	     						data=data.resultListObj;
    	     						if(data!=null && data.length>0){
    	     							response( $.map( data, function( item ) {
    	     					          return {
    	     					        	  label: item.emailAddress +" [ "+ item.firstName+" "+item.lastName+"]",
    	     					        	  value: item.userId,
    	     					        	  name: item.emailAddress +" [ "+ item.firstName+" "+item.lastName+"]",
    	     					        	  id: item.userId
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
    	     			  this.value = ui.item.id;
    	     			$('#docsAssign').val(ui.item.id);
    	     			$("#docs_assignShow").html(" "+ui.item.name+" ");
    	     		      return false;
    	     		  },
    	     		  open: function() {
    	     		    $( this ).removeClass( "ui-corner-all" ).addClass( "ui-corner-top" );
    	     		  },
    	     		  close: function() {
    	     		    $( this ).removeClass( "ui-corner-top" ).addClass( "ui-corner-all" );
    	     		  }
    	     		}); 
    	        
    	        
    	        $('#researchProjectId_autocomplete').autocomplete({
    	     		  source: function( request, response ) {    
    	     			 var modelM={
    	       		    		keySearch:request.term
    	       		    }	
    	     			ResearchAjax.getResearchProjectList(modelM,{
    	     					callback:function(data){
    	     						data=data.resultListObj;
    	     						if(data!=null && data.length>0){
    	     							response( $.map( data, function( item ) {
    	     					          return {
    	     					        	  label: item.thaiName,
    	     					        	  value: item.thaiName,
    	     					        	  name: item.thaiName,
    	     					        	  id: item.researchProjectId
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
    	     			$('#researchProjectId').val(ui.item.id);
    	     			//alert(ui.item.name)
    	     			$('textarea[id="utilizationM.researchProject.thaiName"]').val(ui.item.name);
    	     		      return false;
    	     		  },
    	     		  open: function() {
    	     		    $( this ).removeClass( "ui-corner-all" ).addClass( "ui-corner-top" );
    	     		  },
    	     		  close: function() {
    	     		    $( this ).removeClass( "ui-corner-top" ).addClass( "ui-corner-all" );
    	     		  }
    	     		}); 
    	        $('#utilizationTypeId_autocomplete').autocomplete({
  	     		  source: function( request, response ) {    
  	     			var modelM={
  	      		    		keySearch:request.term
  	      		    }	
  	     			ResearchAjax.getUtilizationTypeList(modelM,{
  	     					callback:function(data){ 
  	     						data=data.resultListObj;
  	     						if(data!=null && data.length>0){
  	     							response( $.map( data, function( item ) {
  	     					          return {
  	     					        	  label: item.utilizationName,
  	     					        	  value: item.utilizationName,
  	     					        	  name: item.utilizationName,
  	     					        	  id: item.utilizationTypeId
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
  	     			$('#<portlet:namespace/>utilizationTypeId').val(ui.item.id);
  	     			//alert(ui.item.name)
  	     			$('#utilizationTypeId_assignShow').html(" "+ui.item.name+" ");
  	     		      return false;
  	     		  },
  	     		  open: function() {
  	     		    $( this ).removeClass( "ui-corner-all" ).addClass( "ui-corner-top" );
  	     		  },
  	     		  close: function() {
  	     		    $( this ).removeClass( "ui-corner-top" ).addClass( "ui-corner-all" );
  	     		  }
  	     		}); 
    	        
    	      
           $('#utilizationTypeId_add_autocomplete').autocomplete({
	     		  source: function( request, response ) {    
	     			 var modelM={
	       		    		keySearch:request.term
	       		    }	
	     			ResearchAjax.getUtilizationTypeList(modelM,{
	     					callback:function(data){ 
	     						data=data.resultListObj;
	     						if(data!=null && data.length>0){
	     							response( $.map( data, function( item ) {
	     					          return {
	     					        	  label: item.utilizationName,
	     					        	  value: item.utilizationName,
	     					        	  name: item.utilizationName,
	     					        	  id: item.utilizationTypeId
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
	     			$('input[id="utilizationM.utilizationType.utilizationTypeId"]').val(ui.item.id);
	     			//alert(ui.item.name)
	     			$('#utilizationTypeId_add_assignShow').html(" "+ui.item.name+" ");
	     		      return false;
	     		  },
	     		  open: function() {
	     		    $( this ).removeClass( "ui-corner-all" ).addClass( "ui-corner-top" );
	     		  },
	     		  close: function() {
	     		    $( this ).removeClass( "ui-corner-top" ).addClass( "ui-corner-all" );
	     		  }
	     		}); 
           $('#utilizationM\\.budgetYear').keyup(function() {
        	    var dInput = this.value;
        	   // alert(dInput.length)
        	   if ($.trim(dInput).length > 0 && !<portlet:namespace />validateDigitOnly(dInput)) {
      	  		//alert('กรอก  เฉพาะตัวเลขเท่านั้น.');
      	  		$(this).val("");
      	  		$(this).focus();
      	  		return false;
     			 }
        	   
        	});         
    });
    function <portlet:namespace/>render_utilization_item(obj){
    	  var str="<table class=\"table table-hover table-striped table-bordered table-condensed\" border=\"1\" style=\"font-size: 12px\"> "+
    	  			" <thead>    "+
          			"  <tr>"+
          			"  		<th width=\"5%\" style=\"text-align: center\"><div class=\"th_class\">#</div></th>"+ 
        			"		<th width=\"20%\" style=\"text-align: center\"><div class=\"th_class\">ด้านการนำไปใช้ประโยชน์</div></th>"+  
          			"		<th width=\"55%\" style=\"text-align: center\"><div class=\"th_class\">OutCome</div></th>  "+
         			"		<th width=\"10%\" style=\"text-align: center\"><div class=\"th_class\">ปีงบประมาณ</div></th> "+
       				"		<th width=\"10%\"><div class=\"th_class\"></div></th>  "+
          			"	</tr> 		"+
       				" </thead> 	"+
       				" <tbody>  	";
       				if(obj!=null){
       					for(var i=0;i<obj.length;i++){
       						str=str+" 	<tr style=\"cursor: pointer;\">"+
       						" <td onclick=\"<portlet:namespace />doEditUtilizationAjax(\'"+obj[i].researchProject.researchProjectId+"\',\'"+obj[i].utilizationItemList+"\')\" style=\"text-align: left\">"+(i+1)+"</td>"+
       						" <td onclick=\"<portlet:namespace />doEditUtilizationAjax(\'"+obj[i].researchProject.researchProjectId+"\',\'"+obj[i].utilizationItemList+"\')\" style=\"text-align: left\">"+obj[i].utilizationType.utilizationName+"</td> "+
       						" <td onclick=\"<portlet:namespace />doEditUtilizationAjax(\'"+obj[i].researchProject.researchProjectId+"\',\'"+obj[i].utilizationItemList+"\')\" style=\"text-align: left\">"+obj[i].outCome+"</td> "+
       						" <td onclick=\"<portlet:namespace />doEditUtilizationAjax(\'"+obj[i].researchProject.researchProjectId+"\',\'"+obj[i].utilizationItemList+"\')\" style=\"text-align: center\">"+obj[i].budgetYear+"</td> "+
       						" <td style=\"text-align: center\">"+
       						"  <button onclick=\"<portlet:namespace />doEditUtilizationAjax(\'"+obj[i].researchProject.researchProjectId+"\',\'"+obj[i].utilizationItemList+"\')\" class=\"btn btn-small\" type=\"button\">แก้ใข</button> "+
       						"  <button onclick=\"<portlet:namespace />doDeleteUtilizationAjax(\'"+obj[i].researchProject.researchProjectId+"\',\'"+obj[i].utilizationItemList+"\')\" class=\"btn btn-danger btn-small\" type=\"button\">ลบ</button> "+
       						" </td>  "+
       						" </tr> ";
       					}
       				}
       				str=str+"</tbody>"+
       					"</table>";
    	  $("#<portlet:namespace/>utilization_item_list").html(str);
    	
    }
    function <portlet:namespace/>displayElementEdit(mode,id){
    $("#<portlet:namespace/>utilizationTypeId").val('');
	$("#utilizationTypeId_autocomplete").val('');
	$("#utilizationTypeId_assignShow").html('');
	$("#<portlet:namespace/>outCome").val('');
	$("#<portlet:namespace/>budgetYear").val('');
	$("#utilizationTypeId_autocomplete").prop("readonly",false)
	  $("#utilizationTypeId_autocomplete").val("");
		$("#utilizationTypeId_autocomplete").css("background-color","rgb(250, 250, 198)");
	$( "#<portlet:namespace/>utilization_utilizationItemList" ).val("");
	   if(mode=='edit'){
		  
	   }else{
		   $( "#<portlet:namespace/>utilization_mode" ).val('add')
		   $( "#<portlet:namespace/>element_edit" ).show( "slow");
		   $( "#<portlet:namespace/>buttonShowAll" ).show( "slow");  
	   }
    }
    function <portlet:namespace/>displayElementEditxxx(mode,url,id){
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
    
    
    function <portlet:namespace />doEditUtilizationAjax(researchProjectId,utilizationItemList){
    	var researchProject={
         		researchProjectId:researchProjectId
         }
       
    	var utilization={
    			researchProject:researchProject,
    			utilizationItemList:utilizationItemList	
    	};
     
    	ResearchAjax.findUtilizationItemById(utilization,{
				callback:function(data){
					//alert(data.utilizationType)
					if(data!=null){
					
						// $( "#<portlet:namespace/>utilization_creatorId" ).val(data.creatorId);
						var utilizationTypeId='';
						var utilizationName=''
						if(data.utilizationType!=null){
							//organizationId
							utilizationTypeId=data.utilizationType.utilizationTypeId;
							utilizationName=data.utilizationType.utilizationName;
						}
						$("#utilizationTypeId_autocomplete").prop("readonly","readonly")
						$("#utilizationTypeId_autocomplete").css("background-color","");
	                   
						$('#<portlet:namespace/>utilizationTypeId').val(utilizationTypeId);
						//$("#utilizationTypeId_autocomplete").val(utilizationTypeId);
						$("#utilizationTypeId_autocomplete").val(utilizationName);
						$("#utilizationTypeId_assignShow").html(utilizationName);
						 
						$( "#<portlet:namespace/>budgetYear" ).val(data.budgetYear);
						$( "#<portlet:namespace/>outCome" ).val(data.outCome);
						$( "#<portlet:namespace/>utilization_mode" ).val("edit");
						$( "#<portlet:namespace/>utilization_utilizationItemList" ).val(utilizationItemList);
						
			
           			   
						$( "#<portlet:namespace/>element_edit" ).show( "slow");   
						$( "#<portlet:namespace/>buttonShowAll" ).hide( "slow");  
					}
				}
		 });	
      }
    
    function <portlet:namespace />doDeleteUtilizationAjax(researchProjectId,utilizationItemList){
    	var agree=confirm(" ต้องการลบข้อมูลหรือไม่? ");
		if (agree){ 
			<portlet:namespace />showDownlod("<portlet:namespace/>utilization_item_list");
			var researchProject={
	         		researchProjectId:researchProjectId
	         }
	       
	    	var utilization={
	    			researchProject:researchProject,
	    			utilizationItemList:utilizationItemList	
	    	};
	    	ResearchAjax.deleteUtilizationItem(utilization,{
					callback:function(data){ 
						<portlet:namespace/>render_utilization_item(data);
						 $( "#<portlet:namespace/>element_edit" ).hide( "slow")
					}
			 });
			return true ;
		}
		else{
			return false ;
		}
		
		
    	 	
    }
    function <portlet:namespace />doSubmitFormAjax(){
    	var budgetYear_edit=$('#<portlet:namespace/>budgetYear').val();
     	 //   var dInput = this.value;
     	   // alert(dInput.length)
     	   if ($.trim(budgetYear_edit).length > 0 && !<portlet:namespace />validateYear(budgetYear_edit)) {
   	  		//alert('กรอก  ปีงบประมาณให้ถูกต้อง.');
   	  		$('#<portlet:namespace/>budgetYear').val("");
   	  		$('#<portlet:namespace/>budgetYear').focus();
   	  		return false;
  			 }
    	var mode=	$( "#<portlet:namespace/>utilization_mode" ).val();
        var researchProject={
        		researchProjectId:$("#researchProjectId").val()
        }
        var utilizationTypeId=$("#<portlet:namespace/>utilizationTypeId").val();
        var utilizationType={
        		utilizationTypeId:utilizationTypeId
        }
        
       
        <portlet:namespace />showDownlod("<portlet:namespace/>utilization_item_list");
    	var utilization={
    			researchProject:researchProject,
    			utilizationType:utilizationType,
    			outCome:$("#<portlet:namespace/>outCome").val(),
    			budgetYear:$("#<portlet:namespace/>budgetYear").val(),
    			docType:"PUBLISH",
    			createdBy:"${user.userId}",
    			updatedBy:"${user.userId}",
    			utilizationItemList: $( "#<portlet:namespace/>utilization_utilizationItemList" ).val()
    				
    	};
    	ResearchAjax.updateUtilization(utilization,mode,{
				callback:function(data){ 
					<portlet:namespace/>render_utilization_item(data);
					 $( "#<portlet:namespace/>element_edit" ).hide( "slow")
				}
		 });	
    	
    	
    }
    function <portlet:namespace />validateYear(sDigit) {
        
        var filter=/^\d{4}$/;
        if (filter.test(sDigit)) {
            return true;
        }
        else {
            return false;
        }
    }
    
 <%-- Start Document  --%>
    
    function <portlet:namespace />doEditDocumentAjax(inventionId,itemList){
    	
    	 var researchProjectDocument={
	   			inventionId:inventionId,
	   			itemList:itemList,
	   			documentId:1
	        };
	   
    	ResearchAjax.findPatentDocumentById(researchProjectDocument,{
				callback:function(data){ 
					if(data!=null){
						$( "#<portlet:namespace/>document_description" ).val(data.description);
				    	
				    	$( "#<portlet:namespace/>document_mode" ).val("edit")
				    	$( "#<portlet:namespace/>document_itemList" ).val(itemList);
           			   
						$( "#<portlet:namespace/>element_document" ).show( "slow");  
					
						
					}
				}
		 });	
    }
    
    function <portlet:namespace />doDeleteDocumentAjax(inventionId,itemList){
    	
    	var agree=confirm(" ต้องการลบข้อมูลหรือไม่? ");
		if (agree){
			<portlet:namespace />showDownlod("<portlet:namespace/>document_item_list");
		   	 var researchProjectDocument={
		   			inventionId:inventionId,
		   			itemList:itemList
		        };
		   
		   	ResearchAjax.deletePatentDocumentM(researchProjectDocument,"patent",{
						callback:function(data){ 
							<portlet:namespace/>render_document_item(data);
							 $( "#<portlet:namespace/>element_document" ).hide( "slow"); 
						}
				 });
			return true ;
		}
		else{
			return false ;
		}
     	
   }
    function <portlet:namespace />doSubmitDocumentAjax(){
    	var mode= $("#<portlet:namespace/>document_mode").val();
    	//alert($("#inventionId").val())
    	//return false;
        var researchProjectDocument={
        		inventionId:$("#inventionId").val(),
        		documentId:1,
        		description:$("#<portlet:namespace/>document_description").val() ,
        		createdBy:"${user.userId}",
    			updatedBy:"${user.userId}",
    			itemList:$( "#<portlet:namespace/>document_itemList" ).val()
        }
        <portlet:namespace />showDownlod("<portlet:namespace/>document_item_list");
        var file = dwr.util.getValue('<portlet:namespace/>uploadFile');
     //   ResearchAjax.uploadResearchProjectDocument(file,researchProjectDocument,"researchProject",mode, {
    	ResearchAjax.updatePatentDocument(file,researchProjectDocument,"patent",mode,{
				callback:function(data){ 
					<portlet:namespace/>render_document_item(data);
					 $( "#<portlet:namespace/>element_document" ).hide( "slow");   
				}
		 });	
    }
    function <portlet:namespace/>displayDocument(mode,id){
    	 $("#<portlet:namespace/>document_description").val('');
     	  $("#<portlet:namespace/>document_itemList").val(''); 
   	   if(mode=='edit'){
   		  
   	   }else{
   		$("#<portlet:namespace/>document_mode").val('add');
   		   $( "#<portlet:namespace/>element_document" ).show( "slow");   
   	   }
   	   
      }

    function <portlet:namespace/>render_document_item(obj){
    	  var str="<table class=\"table table-hover table-striped table-bordered table-condensed\" border=\"1\" style=\"font-size: 12px\"> "+
    	  			" <thead>    "+
          			"  <tr>"+
          			"  		<th width=\"5%\" style=\"text-align: center\"><div class=\"th_class\">#</div></th>"+ 
        			"		<th width=\"85%\" style=\"text-align: center\"><div class=\"th_class\">Document Name</div></th>"+  
       				"		<th width=\"10%\"><div class=\"th_class\"></div></th>  "+
          			"	</tr> 		"+
       				" </thead> 	"+
       				" <tbody>  	";
       				if(obj!=null){
       					for(var i=0;i<obj.length;i++){
       						str=str+" 	<tr style=\"cursor: pointer;\">"+
       						" <td style=\"text-align: left\">"+(i+1)+"</td>";
       						if($.trim(obj[i].fileName).length>0){
     							str=str+"<td style=\"text-align: left\">"+$.trim(obj[i].description)+"  [ <a style=\"text-decoration: underline;\" onclick=\'<portlet:namespace />downloadFile(\""+obj[i].itemList+"\",\""+obj[i].inventionId+"\")\'>"+obj[i].fileName+"</a>]</td> ";
     						}else{
     							str=str+"<td style=\"text-align: left\">"+$.trim(obj[i].description)+"</td> ";
     						}
     						str=str+""+
       						" <td style=\"text-align: center\">"+
       						"  <button onclick=\"<portlet:namespace />doEditDocumentAjax(\'"+obj[i].inventionId+"\',\'"+obj[i].itemList+"\')\" class=\"btn btn-small\" type=\"button\">แก้ใข</button> "+
       						"  <button onclick=\"<portlet:namespace />doDeleteDocumentAjax(\'"+obj[i].inventionId+"\',\'"+obj[i].itemList+"\')\" class=\"btn btn-danger btn-small\" type=\"button\">ลบ</button> "+
       						" </td>  "+
       						" </tr> ";
       					}
       				}
       				str=str+"</tbody>"+
       					"</table>";
    	  $("#<portlet:namespace/>document_item_list").html(str);
    	
    }
    <%-- END Document  --%>
    
    function <portlet:namespace />doSubmitForm(){
    	var budgetYear=$('input[id="utilizationM.budgetYear"]').val();
      	 //   var dInput = this.value;
      	   // alert(dInput.length)
      	   if ($.trim(budgetYear).length > 0 && !<portlet:namespace />validateYear(budgetYear)) {
    	  		//alert('กรอก  ปีงบประมาณให้ถูกต้อง.');
    	  		$('input[id="utilizationM.budgetYear"]').val("");
    	  		$('input[id="utilizationM.budgetYear"]').focus();
    	  		return false;
   			 }
      	 
      	   
    	$('input[id="utilizationM.docType"]').val("PUBLISH");
   	 var form = document.forms['utilizationForm'];
   	 form.submit();
   }
    function <portlet:namespace/>confirmDelete(_urlDelete){ 
   	 
		var agree=confirm(" ต้องการลบข้อมูลหรือไม่? ");
		//alert(_urlDelete)
		if (agree){
		//window.location.href = _urlDelete;
			return true ;
		}
		else{
			return false ;
		}
	} 
    function <portlet:namespace />showDownlod(element_){
    	//<c:url value='/resources/images/ajax_loading.gif'/>loading.gif
    	var download_str='<table border="0" width="100%"><tr><td  align="center"><img style="width:60px;" src="<c:url value="/resources/images/loading.gif"/>"/></td></tr></table>';
    	$("#"+element_).html(download_str);
    
    }
    function <portlet:namespace />validateDigitOnly(sDigit) {
		var filter =/^[0-9]+$/
	    if (filter.test(sDigit)) {
	        	return true;
	    }
	    else {
	        return false;
	    }
	}
    		
    function <portlet:namespace />doSearchBox(f_name,pageNo){
    	if(f_name=='researchGroup')
    		<portlet:namespace />researchGroupPopup($("#<portlet:namespace />keySearch_"+f_name).val(),false,pageNo)
    	else if(f_name=='organization')
    		<portlet:namespace />organizationPopup($("#<portlet:namespace />keySearch_"+f_name).val(),false,pageNo)
    	else if(f_name=='fundingResource')
    		<portlet:namespace />fundingResourcePopup($("#<portlet:namespace />keySearch_"+f_name).val(),false,pageNo)
    	else  if(f_name=='docsAssign')
    		<portlet:namespace />docsAssignPopup($("#<portlet:namespace />keySearch_"+f_name).val(),false,pageNo)
    	else if(f_name=='researchProject')
    		<portlet:namespace />researchProjectPopup($("#<portlet:namespace />keySearch_"+f_name).val(),false,pageNo)
    	else if(f_name=='utilizationTypeAdd')
    		<portlet:namespace />utilizationTypeAddPopup($("#<portlet:namespace />keySearch_"+f_name).val(),false,pageNo)
    	else if(f_name=='utilizationTypeEdit')
    		<portlet:namespace />utilizationTypeEditPopup($("#<portlet:namespace />keySearch_"+f_name).val(),false,pageNo)
    		
    		
    	
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
    function <portlet:namespace />researchProjectPopup(keySearch,init,pageNo){
       	var keyBox=" <div>"+
        		   		"<input type=\"text\"  style=\"display:inline;width:250px\" class=\"form-control\" "+ 
        		   		" id=\"<portlet:namespace />keySearch_researchProject\" onkeypress=\"<portlet:namespace />chk(\'researchProject\',event)\" aria-describedby=\"inputSuccess4Status\" value=\""+keySearch+"\"/>"+
                   		"<button onclick=\"<portlet:namespace />doSearchBox('researchProject',1)\" type=\"button\" class=\"btn btn-success\" style=\"margin-top:-8px;margin-left:5px;\">Search</button>"+
    			   "</div>";
       	var str="<table class=\"table table-hover table-striped table-bordered table-condensed\" border=\"1\" style=\"font-size: 12px\"> "+
   				" <thead>    "+
   				"  <tr>"+
   				"		<th width=\"10%\" style=\"text-align: center\"><div class=\"th_class\">ปีงบประมาณ</div></th> "+
   				"		<th width=\"45%\" style=\"text-align: center\"><div class=\"th_class\">ชื่อโครงการ(T)</div></th> "+
   				"		<th width=\"45%\" style=\"text-align: center\"><div class=\"th_class\">ชื่อโครงการ(E)</div></th> "+
   				"	</tr> 		"+
   				" </thead><tbody>";
   			  
   				var pageObj={
   						pageSize:PAGE_SIZE_POPUP,
   						pageNo:pageNo
   				}
   				var researchProjectM={
   	  		    		keySearch:keySearch,
   	  		    		paging:pageObj
   	  		    }	
       	ResearchAjax.getResearchProjectList(researchProjectM,{
   			callback:function(data){ 
   				var maxRow=data.maxRow;
   				var lastpage=data.lastpage;
   				data=data.resultListObj;
   				if(data!=null && data.length>0){
   				  for(var i=0;i<data.length;i++){
   					str=str+"<tr  onclick=\"<portlet:namespace />selectresearchProject('"+data[i].researchProjectId+"')\" style=\"cursor: pointer;\">"+
   		    		"<td style=\"text-align: left\"  >"+data[i].budgetYear +"</td>"+
   		    		"<td style=\"text-align: left\"  >"+((data[i].thaiName!=null)?(data[i].thaiName):("")) +"</td>"+
   		    		"<td style=\"text-align: left\"  >"+((data[i].engName!=null)?(data[i].engName):("")) +"</td>"+
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
                					pagingStr=pagingStr+"<li><a style=\"cursor: pointer;\" onclick='<portlet:namespace />doSearchBox(\"researchProject\","+(pageNo-1)+")'>Prev</a></li>";
                			
                				for(var j=pageStart;j<=pageEnd;j++){
                					if(pageNo==j){
                						pagingStr=pagingStr+"<li><a class=\"active\" style=\"background-color: #ddd\" >"+j+"</a></li>";
                					}
                					else{
                						pagingStr=pagingStr+"<li><a class=\"active\" style=\"cursor: pointer;\" onclick='<portlet:namespace />doSearchBox(\"researchProject\","+(j)+")'>"+j+"</a></li>";
                					}
                				}
                				if(pageEnd<lastpage){
                					pagingStr=pagingStr+"<li><a class=\"active\" style=\"cursor: pointer;\" onclick='<portlet:namespace />doSearchBox(\"researchProject\","+(pageEnd)+")'>...</a></li>";
                					pagingStr=pagingStr+"<li><a class=\"active\" style=\"cursor: pointer;\" onclick='<portlet:namespace />doSearchBox(\"researchProject\","+(lastpage)+")'>"+lastpage+"</a></li>";
                				}
                				if(pageNo!=lastpage){
                					pagingStr=pagingStr+"<li><a style=\"cursor: pointer;\" onclick='<portlet:namespace />doSearchBox(\"researchProject\","+((pageNo<lastpage)?(pageNo+1):(lastpage))+")'>Next</a></li>";
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
   				bootbox.dialog(keyBox+"<div id=\"<portlet:namespace />researchProjectPopupTable\">"+str+pagingStr+"</div>",[{
   				    "label" : "Close",
   				     "class" : "btn-danger",
   				  "callback": function() {
   			    }
   				   
   			   }]);
   			}else{
   				$("#<portlet:namespace />researchProjectPopupTable").html(str+pagingStr);
   			}
   		 }
   	  });	
    }
    function <portlet:namespace />selectresearchProject(objID){
    	
    	ResearchAjax.findResearchProjectById(objID,{
			callback:function(data){
				$('input[id="utilizationM.researchProject.researchProjectId"]').val(objID)
				$("#researchProjectId").val(objID)
				$("#researchProjectId_autocomplete").val(data.thaiName);
				bootbox.hideAll();
			}
 		});
    }
    <%-- end researchProject Popup --%>
    
    <%-- start utilizationTypeAdd Popup --%>
    function <portlet:namespace />utilizationTypeAddPopup(keySearch,init,pageNo){
       	var keyBox=" <div>"+
        		   		"<input type=\"text\"  style=\"display:inline;width:250px\" class=\"form-control\" "+ 
        		   		" id=\"<portlet:namespace />keySearch_utilizationTypeAdd\" onkeypress=\"<portlet:namespace />chk(\'utilizationTypeAdd\',event)\" aria-describedby=\"inputSuccess4Status\" value=\""+keySearch+"\"/>"+
                   		"<button onclick=\"<portlet:namespace />doSearchBox('utilizationTypeAdd',1)\" type=\"button\" class=\"btn btn-success\" style=\"margin-top:-8px;margin-left:5px;\">Search</button>"+
    			   "</div>";
       	var str="<table class=\"table table-hover table-striped table-bordered table-condensed\" border=\"1\" style=\"font-size: 12px\"> "+
   				" <thead>    "+
   				"  <tr>"+
   				"		<th width=\"10%\" style=\"text-align: center\"><div class=\"th_class\">รหัสด้านการนำไปใช้ประโยชน์</div></th> "+
   				"		<th width=\"90%\" style=\"text-align: center\"><div class=\"th_class\">ด้านการนำไปใช้ประโยชน์</div></th> "+
   				"	</tr> 		"+
   				" </thead><tbody>";
   				
   				var pageObj={
   						pageSize:PAGE_SIZE_POPUP,
   						pageNo:pageNo
   				}
   				var utilizationTypeAddM={
   	  		    		keySearch:keySearch,
   	  		    		paging:pageObj
   	  		    }	
       	ResearchAjax.getUtilizationTypeList(utilizationTypeAddM,{
   			callback:function(data){ 
   				var maxRow=data.maxRow;
   				var lastpage=data.lastpage;
   				data=data.resultListObj;
   				if(data!=null && data.length>0){
   				  for(var i=0;i<data.length;i++){
   					str=str+"<tr  onclick=\"<portlet:namespace />selectutilizationTypeAdd('"+data[i].utilizationTypeId+"')\" style=\"cursor: pointer;\">"+
   		    		"<td style=\"text-align: left\"  >"+data[i].utilizationCode +"</td>"+
   		    		"<td style=\"text-align: left\"  >"+((data[i].utilizationName!=null)?(data[i].utilizationName):("")) +"</td>"+
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
                					pagingStr=pagingStr+"<li><a style=\"cursor: pointer;\" onclick='<portlet:namespace />doSearchBox(\"utilizationTypeAdd\","+(pageNo-1)+")'>Prev</a></li>";
                			
                				for(var j=pageStart;j<=pageEnd;j++){
                					if(pageNo==j){
                						pagingStr=pagingStr+"<li><a class=\"active\" style=\"background-color: #ddd\" >"+j+"</a></li>";
                					}
                					else{
                						pagingStr=pagingStr+"<li><a class=\"active\" style=\"cursor: pointer;\" onclick='<portlet:namespace />doSearchBox(\"utilizationTypeAdd\","+(j)+")'>"+j+"</a></li>";
                					}
                				}
                				if(pageEnd<lastpage){
                					pagingStr=pagingStr+"<li><a class=\"active\" style=\"cursor: pointer;\" onclick='<portlet:namespace />doSearchBox(\"utilizationTypeAdd\","+(pageEnd)+")'>...</a></li>";
                					pagingStr=pagingStr+"<li><a class=\"active\" style=\"cursor: pointer;\" onclick='<portlet:namespace />doSearchBox(\"utilizationTypeAdd\","+(lastpage)+")'>"+lastpage+"</a></li>";
                				}
                				if(pageNo!=lastpage){
                					pagingStr=pagingStr+"<li><a style=\"cursor: pointer;\" onclick='<portlet:namespace />doSearchBox(\"utilizationTypeAdd\","+((pageNo<lastpage)?(pageNo+1):(lastpage))+")'>Next</a></li>";
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
   				bootbox.dialog(keyBox+"<div id=\"<portlet:namespace />utilizationTypeAddPopupTable\">"+str+pagingStr+"</div>",[{
   				    "label" : "Close",
   				     "class" : "btn-danger",
   				  "callback": function() {
   			    }
   				   
   			   }]);
   			}else{
   				$("#<portlet:namespace />utilizationTypeAddPopupTable").html(str+pagingStr);
   			}
   		 }
   	  });	
    }
    function <portlet:namespace />selectutilizationTypeAdd(objID){
    	
    	ResearchAjax.findUtilizationTypeById(objID,{
			callback:function(data){
				$('input[id="utilizationM.utilizationType.utilizationTypeId"]').val(objID)
				$("#utilizationTypeId_add_autocomplete").val(data.utilizationName);
			
				bootbox.hideAll();
			}
 		});
    }
    <%-- end utilizationTypeAdd Popup --%>
    
    <%-- start utilizationTypeEdit Popup --%>
    function <portlet:namespace />utilizationTypeEditPopup(keySearch,init,pageNo){
       	var keyBox=" <div>"+
        		   		"<input type=\"text\"  style=\"display:inline;width:250px\" class=\"form-control\" "+ 
        		   		" id=\"<portlet:namespace />keySearch_utilizationTypeEdit\" onkeypress=\"<portlet:namespace />chk(\'utilizationTypeEdit\',event)\" aria-describedby=\"inputSuccess4Status\" value=\""+keySearch+"\"/>"+
                   		"<button onclick=\"<portlet:namespace />doSearchBox('utilizationTypeEdit',1)\" type=\"button\" class=\"btn btn-success\" style=\"margin-top:-8px;margin-left:5px;\">Search</button>"+
    			   "</div>";
       	var str="<table class=\"table table-hover table-striped table-bordered table-condensed\" border=\"1\" style=\"font-size: 12px\"> "+
   				" <thead>    "+
   				"  <tr>"+
   				"		<th width=\"10%\" style=\"text-align: center\"><div class=\"th_class\">รหัสด้านการนำไปใช้ประโยชน์</div></th> "+
   				"		<th width=\"90%\" style=\"text-align: center\"><div class=\"th_class\">ด้านการนำไปใช้ประโยชน์</div></th> "+
   				"	</tr> 		"+
   				" </thead><tbody>";
   				
   				var pageObj={
   						pageSize:PAGE_SIZE_POPUP,
   						pageNo:pageNo
   				}
   				var utilizationTypeEditM={
   	  		    		keySearch:keySearch,
   	  		    		paging:pageObj
   	  		    }	
       	ResearchAjax.getUtilizationTypeList(utilizationTypeEditM,{
   			callback:function(data){ 
   				var maxRow=data.maxRow;
   				var lastpage=data.lastpage;
   				data=data.resultListObj;
   				if(data!=null && data.length>0){
   				  for(var i=0;i<data.length;i++){
   					str=str+"<tr  onclick=\"<portlet:namespace />selectutilizationTypeEdit('"+data[i].utilizationTypeId+"')\" style=\"cursor: pointer;\">"+
   		    		"<td style=\"text-align: left\"  >"+data[i].utilizationCode +"</td>"+
   		    		"<td style=\"text-align: left\"  >"+((data[i].utilizationName!=null)?(data[i].utilizationName):("")) +"</td>"+
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
                					pagingStr=pagingStr+"<li><a style=\"cursor: pointer;\" onclick='<portlet:namespace />doSearchBox(\"utilizationTypeEdit\","+(pageNo-1)+")'>Prev</a></li>";
                			
                				for(var j=pageStart;j<=pageEnd;j++){
                					if(pageNo==j){
                						pagingStr=pagingStr+"<li><a class=\"active\" style=\"background-color: #ddd\" >"+j+"</a></li>";
                					}
                					else{
                						pagingStr=pagingStr+"<li><a class=\"active\" style=\"cursor: pointer;\" onclick='<portlet:namespace />doSearchBox(\"utilizationTypeEdit\","+(j)+")'>"+j+"</a></li>";
                					}
                				}
                				if(pageEnd<lastpage){
                					pagingStr=pagingStr+"<li><a class=\"active\" style=\"cursor: pointer;\" onclick='<portlet:namespace />doSearchBox(\"utilizationTypeEdit\","+(pageEnd)+")'>...</a></li>";
                					pagingStr=pagingStr+"<li><a class=\"active\" style=\"cursor: pointer;\" onclick='<portlet:namespace />doSearchBox(\"utilizationTypeEdit\","+(lastpage)+")'>"+lastpage+"</a></li>";
                				}
                				if(pageNo!=lastpage){
                					pagingStr=pagingStr+"<li><a style=\"cursor: pointer;\" onclick='<portlet:namespace />doSearchBox(\"utilizationTypeEdit\","+((pageNo<lastpage)?(pageNo+1):(lastpage))+")'>Next</a></li>";
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
   				bootbox.dialog(keyBox+"<div id=\"<portlet:namespace />utilizationTypeEditPopupTable\">"+str+pagingStr+"</div>",[{
   				    "label" : "Close",
   				     "class" : "btn-danger",
   				  "callback": function() {
   			    }
   				   
   			   }]);
   			}else{
   				$("#<portlet:namespace />utilizationTypeEditPopupTable").html(str+pagingStr);
   			}
   		 }
   	  });	
    }
    function <portlet:namespace />selectutilizationTypeEdit(objID){
    	
    	ResearchAjax.findUtilizationTypeById(objID,{
			callback:function(data){
				$('#<portlet:namespace/>utilizationTypeId').val(objID)
				$("#utilizationTypeId_autocomplete").val(data.utilizationName);
				
				bootbox.hideAll();
			}
 		});
    }
    <%-- end utilizationTypeEdit Popup --%>
    </script>
  </body>
</html>
