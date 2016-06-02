<%@ include file="/WEB-INF/jsp/include.jsp" %>
<%@ page contentType="text/html; charset=utf-8" %> 
<%@page import="javax.portlet.PortletURL"%>
 <portlet:resourceURL var="research_doc_download_pdf_form1"  id="research_doc_download">
 	<portlet:param name="fileType" value="Proof_of_Participation_Academic_Work_Form_v1" />
 	<portlet:param name="formType" value="pdf" />
 </portlet:resourceURL>
 <portlet:resourceURL var="research_doc_download_pdf_form2"  id="research_doc_download">
 	<portlet:param name="fileType" value="Proof_of_Participation_Form_v1" />
 	<portlet:param name="formType" value="pdf" />
 </portlet:resourceURL>
 <portlet:resourceURL var="research_doc_download_doc_form1"  id="research_doc_download">
 	<portlet:param name="fileType" value="Proof_of_Participation_Academic_Work_Form_v1" />
 	<portlet:param name="formType" value="doc" />
 </portlet:resourceURL>
 <portlet:resourceURL var="research_doc_download_doc_form2"  id="research_doc_download">
 	<portlet:param name="fileType" value="Proof_of_Participation_Form_v1" />
 	<portlet:param name="formType" value="doc" />
 </portlet:resourceURL>
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
function <portlet:namespace/>exportFile(src){
	
	  var div = document.createElement("div");
	     document.body.appendChild(div);
	    div.innerHTML = "<iframe width='0' height='0' scrolling='no' frameborder='0' src='" + src + "'></iframe>";
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
    

    <title><spring:message code="research.group"/></title>

    <!-- Bootstrap core CSS --> 
    <link rel="stylesheet" href="<c:url value="/resources/bootstrap/css/bootstrap.min.css"/>" type="text/css"/>
    
    <style type="text/css">
    #breadcrumbs { display:none; } 
    </style>
  </head>

  <body>
  <form:form  id="downloadDocForm" modelAttribute="downloadDocForm" method="post"  name="downloadDocForm" action="${formAction}" enctype="multipart/form-data">
   <fieldset style="font-family: sans-serif;padding-top:5px"> 
            <input type="hidden" name="command" id="<portlet:namespace />common" value="${downloadDocForm.command}" />
            <input type="hidden" name="mode" id="<portlet:namespace />mode"  value="${downloadDocForm.mode}" />
             <input type="hidden" name="aoe" value="2"/>
         <div style="border: 1px solid #FFC299;background: #F9F9F9;padding: 10px"> 
                <div  id="search_section_application"> 
                    <table class="table table-hover table-striped table-bordered table-condensed" border="1" style="font-size: 12px"> 
               <thead>    
                   <tr>    
                   <th width="5%"><div class="th_class">ID</div></th>  
                    <th width="85%"><div class="th_class">Title</div></th>  
                    <th width="10%"><div class="th_class"></div></th>  
                   </tr> 
                </thead> 
                <tbody>  
                <tr style="cursor: pointer;">
                		<td style="text-align: left">1.</td>  
               	 		<td style="text-align: left">แบบแสดงหลักฐานการมีส่วนร่วม</td>  
                		<td style="text-align: center">
                		   <img src="<c:url value="/resources/images/PDF.gif"/>" onclick="<portlet:namespace/>exportFile('${research_doc_download_pdf_form2}')"/>
                		   <img src="<c:url value="/resources/images/DOC.png"/>" onclick="<portlet:namespace/>exportFile('${research_doc_download_doc_form2}')"/>
 						</td> 
              		</tr>
              		 <tr style="cursor: pointer;">
                 
                		<td style="text-align: left">2.</td>  
               	 		<td style="text-align: left">แบบแสดงหลักฐานการมีส่วนร่วมในผลงานทางวิชาการ</td>  
                		<td style="text-align: center">
 						  <img src="<c:url value="/resources/images/PDF.gif"/>" onclick="<portlet:namespace/>exportFile('${research_doc_download_pdf_form1}')"/>
                		   <img src="<c:url value="/resources/images/DOC.png"/>" onclick="<portlet:namespace/>exportFile('${research_doc_download_doc_form1}')"/>
 						</td> 
              		</tr>
                 <%-- 
                <c:if test="${not empty researchGroups}"> 
                 <c:forEach items="${researchGroups}" var="researchGroup" varStatus="loop"> 
               		 <tr style="cursor: pointer;">
                 
                		<td style="text-align: left">${researchGroup.groupCode}</td>  
               	 		<td style="text-align: left">${researchGroup.groupTh}</td>  
                		<td style="text-align: center">
 							<i class="glyphicon glyphicon-list"></i>
                  			<i onclick='<portlet:namespace/>displayElementEdit("edit","<portlet:resourceURL  id="research_group_resource_get_byid"><portlet:param name="researchGroupId"><jsp:attribute name="value"><c:out value="${researchGroup.researchGroupId}"/></jsp:attribute></portlet:param></portlet:resourceURL>","${researchGroup.researchGroupId}")' class="icon-edit"></i>
                			<i  onclick='return <portlet:namespace />confirmDelete("<portlet:actionURL><portlet:param name="action" value="delete"/><portlet:param name="researchGroupId"><jsp:attribute name="value"><c:out value="${researchGroup.researchGroupId}"/></jsp:attribute></portlet:param></portlet:actionURL>")' class="icon-trash"></i></td> 
              		</tr>
              </c:forEach>
              </c:if>
               --%>
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
       
  </body>
</html>	
