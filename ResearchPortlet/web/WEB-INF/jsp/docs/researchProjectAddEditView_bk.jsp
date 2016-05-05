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
<%-- <portlet:param name="researchProjectId" value="${researchProjectForm.researchProjectM.researchProjectId}"/> --%>
<portlet:resourceURL var="research_group_resource_get_byid" id="research_group_resource_get_byid">

</portlet:resourceURL>
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
        .aoe_small{width: 500px !important;}
        .aoe_width{width: 1000px !important;}
        .aui .modal{
            left:30%
        }
    </style>

</head>

<body>
<form:form  id="researchProjectForm" modelAttribute="researchProjectForm" method="post"  name="researchProjectForm" action="${formAction}" enctype="multipart/form-data">
    <fieldset style="font-family: sans-serif;padding-top:5px">
        <input type="hidden" name="command" id="<portlet:namespace />common" value="${researchProjectForm.command}" />
        <input type="hidden" name="mode" id="<portlet:namespace />mode"  value="${researchProjectForm.mode}" />
        <form:hidden path="researchProjectM.researchProjectId"  id="researchProjectId"/>


        <input type="hidden" id="<portlet:namespace />_export_proof_participation" value="${researchProjectForm.researchProjectM.proofParticipationResource}"/>
        <input type="hidden" id="<portlet:namespace />_export_proof_academic" value="${researchProjectForm.researchProjectM.proofAcademicResource}"/>

        <c:set var="userIdAsString">${user.userId}</c:set>
        <c:set var="havePermissionOnDocs" value="${researchProjectForm.mode=='edit' && researchProjectForm.researchProjectM.docType=='DRAFT'
    								&& ( userIdAsString == researchProjectForm.researchProjectM.createdBy 
                       				|| userIdAsString==researchProjectForm.researchProjectM.updatedBy || researchProjectForm.researchProjectM.isdocAssign ) }"></c:set>
        <div style="border: 1px solid #FFC299;background: #F9F9F9;padding: 10px">
            <div class="accordion" id="accordion2">
                <div class="accordion-group">
                    <div class="accordion-heading">
                        <a href="${list}" style="padding-right: 20px;" >Back</a>
                        <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion2" href="#collapseOne">
                            ข้อมูลโครงการ
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
                                        <label><span>ปีงบประมาณ:
                        </span> </label>
                                    </td>
                                    <td width="30%">

                                        <form:input path="researchProjectM.budgetYear" maxlength="4"  cssStyle="width:50px" cssClass="form-control"

                                                />
                                        <span id="error" style="color: Red; display: none"></span>
                                    </td>
                                    <td width="50%">

                                    </td>
                                </tr>
                                <tr valign="top">
                                    <td align="right" width="20%">
                        <span><label>ขื่อโครงการ(T):<span style="color: red;font-size: 20;"> </span>
                        </label></span>
                                    </td>
                                    <td width="80%" colspan="3">

                                        <form:textarea path="researchProjectM.thaiName" cssStyle="width:500px" rows="2"/>
                                    </td>
                                </tr>
                                <tr valign="top">
                                    <td align="right" width="20%">
                        <span><label>ขื่อโครงการ(E):<span style="color: red;font-size: 20;"> </span>
                        </label></span>
                                    </td>
                                    <td width="80%" colspan="3">

                                        <form:textarea path="researchProjectM.engName" cssStyle="width:500px" rows="2"/>
                                    </td>
                                </tr>
                                <tr valign="top">
                                    <td align="right" width="20%">
                        <span><label>รายละเอียดโครงการ:<span style="color: red;font-size: 20;"> </span>
                        </label></span>
                                    </td>
                                    <td width="80%" colspan="3">

                                        <form:textarea path="researchProjectM.detail" cssStyle="width:500px" rows="2"/>
                                    </td>
                                </tr>
                                <tr valign="top">
                                    <td align="right" width="20%">
                        <span><label>รหัสประเภทโครงการ:<span style="color: red;font-size: 20;"> </span>
                        </label></span>
                                    </td>
                                    <td width="80%" colspan="3">

                                        <form:select path="researchProjectM.projectType">
                                            <form:option value="1">การวิจัยพื้นฐาน</form:option>
                                            <form:option value="2">การวิจัยประยุกต์</form:option>
                                            <form:option value="3">การวิจัยพัฒนา</form:option>
                                        </form:select>

                                            <%---
                                               <form:input path="researchProjectM.projectType" cssClass="form-control"/>
                                             --%>
                                    </td>
                                </tr>
                                <tr valign="top">
                                    <td align="right" width="20%">
                        <span><label>รหัสหมวด NRCT:<span style="color: red;font-size: 20;"> </span>
                        </label></span>
                                    </td>
                                    <td width="80%" colspan="3">

                                        <form:input path="researchProjectM.nrctCatelogry" cssClass="form-control"/>
                                    </td>
                                </tr>
                                <tr valign="top">
                                    <td align="right" width="20%">
                        <span><label>วัตถุประสงค์:<span style="color: red;font-size: 20;"> </span>
                        </label></span>
                                    </td>
                                    <td width="80%" colspan="3">

                                        <form:textarea path="researchProjectM.objectiveTitle" cssStyle="width:500px" rows="2"/>
                                    </td>
                                </tr>
                                <tr valign="top">
                                    <td align="right" width="20%">
                        <span><label>บทคัดย่อ:<span style="color: red;font-size: 20;"> </span>
                        </label></span>
                                    </td>
                                    <td width="80%" colspan="3">

                                        <form:textarea path="researchProjectM.abstractTitle" cssStyle="width:500px" rows="2"/>
                                    </td>
                                </tr>
                                <tr valign="top">
                                    <td align="right" width="20%">
                        <span><label>คำค้น:<span style="color: red;font-size: 20;"> </span>
                        </label></span>
                                    </td>
                                    <td width="80%" colspan="3">

                                        <form:input path="researchProjectM.keyworkTitle" cssClass="form-control"/>
                                    </td>
                                </tr>
                                <tr valign="top">
                                    <td align="right" width="20%">
                        <span><label>อ้างอิง:<span style="color: red;font-size: 20;"> </span>
                        </label></span>
                                    </td>
                                    <td width="80%" colspan="3">

                                        <form:input path="researchProjectM.reference" cssClass="form-control"/>
                                    </td>
                                </tr>
                                <tr  >
                                    <td align="right" width="20%">
                        <span><label>ระยะที่:<span style="color: red;font-size: 50;"> </span>
                        </label></span>
                                    </td>
                                    <td width="80%" colspan="3">
                                        <div>

                                            <form:input path="researchProjectM.phase" cssStyle="width:50px;" cssClass="form-control"/>
                                        </div>
                                    </td>
                                </tr>
                                <tr  >
                                    <td align="right" width="20%">
                        <span><label>กลุ่มวิจัย:<span style="color: red;font-size: 20;"> </span>
                        </label></span>
                                    </td>
                                    <td width="80%" colspan="3">
                                        <div>
                                            <form:hidden path="researchProjectM.researchGroupId"/>
                                            <input  type="text" style="background-color:
                    rgb(250, 250, 198);width:350px" class="form-control"
                                                    id="researchGroupId_assign_autocomplete" placeholder="" value="${researchProjectForm.researchProjectM.researchGroupShow}"/>
                                            <button style="margin-top: -8px" onclick="<portlet:namespace />researchGroupPopup('',true,1)" type="button" class="btn btn-warning btn-small">...</button>
                                                <%--
                                                <span id="researchGroupId_assignShow">${researchProjectForm.researchProjectM.researchGroupShow} </span>
                                                --%>
                                                <%--
                                                <input type="hidden" id="docsAssign" name="docsAssign" />
                                               <input  type="text" style="background-color: rgb(250, 250, 198);width:50px" class="form-control" id="docs_assign_autocomplete" placeholder=""/>
                                               <span id="docs_assignShow"> </span>
                                                --%>
                                                <%--
                                                  <form:input path="researchProjectM.researchGroupId" cssStyle="display:inline;background-color: rgb(250, 250, 198);" cssClass="form-control"/>
                                                   --%>
                                        </div>
                                    </td>
                                </tr>
                                <tr  >
                                    <td align="right" width="20%">
                        <span><label>หน่วยงาน:<span style="color: red;font-size: 20;"> </span>
                        </label></span>
                                    </td>
                                    <td width="80%" colspan="3">
                                        <div>
                                            <form:hidden path="researchProjectM.organizationId"/>
                                            <input  type="text" style="background-color: rgb(250, 250, 198);width:350px"
                                                    class="form-control" id="organizationId_assign_autocomplete"
                                                    value="${researchProjectForm.researchProjectM.organizationShow}"
                                                    placeholder=""/>
                                            <button style="margin-top: -8px" onclick="<portlet:namespace />organizationPopup('',true,1)" type="button" class="btn btn-warning btn-small">...</button>
                                                <%--
                                                 <span id="organizationId_assignShow">${researchProjectForm.researchProjectM.organizationShow}</span>
                                                  --%>
                                                <%--
                                                <input type="hidden" id="docsAssign" name="docsAssign" />
                                               <input  type="text" style="background-color: rgb(250, 250, 198);width:50px" class="form-control" id="docs_assign_autocomplete" placeholder=""/>
                                               <span id="docs_assignShow"> </span>
                                                --%>


                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td align="right" width="20%">
                                        <label><span>วันที่เริ่มโครงการ:<span style="color: red;font-size: 20;"></span>
                        </span> </label>
                                    </td>
                                    <td width="30%">
                     <span>
                    
                    <%--
                      <fmt:formatDate  type="time" value="${researchProjectForm.researchProjectM.startDate}"   pattern="dd/MM/yyyy" var="startDate" />
                       --%>
                      <input type="text" value="${researchProjectForm.startDate}" readonly="readonly" id="startDate" name="startDate" style="width:75px" class="form-control"/>
                    </span>
                                    </td>
                                    <td align="left" width="20%" colspan="2">
                        <span style="font-size: 16px"> วันที่จบโครงการ:<span style="color: red;font-size: 20;"></span> 
                   <%--   <fmt:formatDate type="time" value="${researchProjectForm.researchProjectM.endDate}"   pattern="dd/MM/yyyy" var="endDate" /> 
                    --%>
                      <input type="text" value="${researchProjectForm.endDate}" readonly="readonly" id="endDate" name="endDate" style="width:75px" class="form-control"/>  
                        </span>
                                    </td>
                                </tr>
                                <tr>
                                    <td align="right" width="20%">
                                        <label><span>งบประมาณที่เสนอขอ:<span style="color: red;font-size: 20;"></span>
                        </span> </label>
                                    </td>
                                    <td width="30%">

                                        <form:input path="researchProjectM.purposeBudget" cssStyle="width:75px" cssClass="form-control"/>

                                    </td>
                                    <td align="left" width="20%" colspan="2">
                        <span style="font-size:16px"> งบประมาณที่อนุมัติ:<span style="color: red;font-size: 20;"></span>  
                        
                         <form:input path="researchProjectM.submitBudget" cssStyle="width:75px" cssClass="form-control"/>
                        </span>
                                    </td>
                                </tr>
                                <tr  >
                                    <td align="right" width="20%">
                        <span><label>แหล่งทุน:<span style="color: red;font-size: 20;"> </span>
                        </label></span>
                                    </td>
                                    <td width="80%" colspan="3">
                                        <div>
                                            <form:hidden path="researchProjectM.fundingResourceId"/>
                                            <input  type="text" style="background-color: rgb(250, 250, 198);width:350px"
                                                    class="form-control" id="fundingResourceId_assign_autocomplete"
                                                    value="${researchProjectForm.researchProjectM.fundingResourceShow}"
                                                    placeholder=""/>
                                            <button style="margin-top: -8px" onclick="<portlet:namespace />fundingResourcePopup('',true,1)" type="button" class="btn btn-warning btn-small">...</button>
                                                <%-- <span id="fundingResourceId_assignShow">${researchProjectForm.researchProjectM.fundingResourceShow} </span>


                                                 <form:input path="researchProjectM.fundingResourceId" cssStyle="display:inline;background-color: rgb(250, 250, 198);" cssClass="form-control"/>
                                                  --%>
                                        </div>
                                    </td>
                                </tr>
                                    <%--
                                    <tr>
                                      <td align="right" width="20%">
                                                <label><span>ประเทศ:<span style="color: red;font-size: 20;"></span>
                                                </span> </label>
                                      </td>
                                      <td width="30%">
                                             <span>
                                              <input type="text" style="display:inline;background-color: rgb(250, 250, 198);width:200px" placeholder="">

                                            </span>
                                      </td>
                                       <td align="left" width="20%" colspan="2">

                                      </td>
                                    </tr>
                                     --%>
                                <tr>
                                    <td align="right" width="20%">
                                        <label><span>รายงานฉบับสมบูรณ์:<span style="color: red;font-size: 20;"></span>
                        </span> </label>
                                    </td>
                                    <td width="30%">
                      <span style="padding-left:5px;font-size: 16px"> กำหนดส่ง:<span style="color: red;font-size: 20;">
                        </span> 
                     <%--   <fmt:formatDate type="time" value="${researchProjectForm.researchProjectM.reportDuedate}"   pattern="dd/MM/yyyy" var="reportDuedate" />
                      --%>
                      <input type="text" value="${researchProjectForm.reportDuedate}" readonly="readonly" id="reportDuedate" name="reportDuedate" style="width:75px" class="form-control"/>  
                      </span>
                                    </td>
                                    <td align="left" width="20%" colspan="2">
                        <span style="font-size: 16px"> ส่งจริง:<span style="color:red;font-size: 20;"></span>  
                      <%--  <fmt:formatDate type="time" value="${researchProjectForm.researchProjectM.reportSubmitDate}"   pattern="dd/MM/yyyy" var="reportSubmitDate" />
                       --%>
                      <input type="text" value="${researchProjectForm.reportSubmitDate}" readonly="readonly" id="reportSubmitDate" name="reportSubmitDate" style="width:75px" class="form-control"/>  
                         </span>
                                    </td>
                                </tr>
                                <tr valign="top">
                                    <td align="right" width="20%">
                        <span><label>หมายเหตุ:<span style="color:red;font-size:20;"> </span>
                        </label></span>
                                    </td>
                                    <td width="80%" colspan="3">

                                        <form:textarea path="researchProjectM.remark" cssStyle="width:500px" rows="2"/>
                                    </td>
                                </tr>
                                <c:if test="${mode=='edit'}">
                                    <tr valign="top">
                                        <td align="right" width="20%">
                        <span><label>Assign to:<span style="color: red;font-size: 20;"> </span>
                        </label></span>
                                        </td>
                                        <td width="80%" colspan="3">
                                            <input type="hidden" id="docsAssign" name="docsAssign" />
                                            <input  type="text" style="background-color: rgb(250, 250, 198);width:250px" class="form-control" id="docs_assign_autocomplete" placeholder=""/>
                                            <c:if test="${havePermissionOnDocs}">
                                                <span id="docs_assignShow"> </span>
                                                <button style="margin-top: -8px" onclick="<portlet:namespace />docsAssignPopup('',true,1)" type="button" class="btn btn-warning btn-small">...</button>
                                            </c:if>

                                                <%--  --%>
                                        </td>
                                    </tr>
                                </c:if>
                                <tr valign="top">
                                    <td align="right" width="20%">
                        <span><label><span style="color: red;font-size: 20;"> </span>
                        </label></span>
                                    </td>
                                    <td width="80%" colspan="3">
              <span id="<portlet:namespace/>doctype_assign_list">
               <c:if test="${not empty researchProjectForm.researchProjectM.docAssignMappings}">
                   <table class="table table-hover table-hover table-striped table-bordered table-condensed" border="0" style="width:30%;font-size: 12px">
                       <c:forEach items="${researchProjectForm.researchProjectM.docAssignMappings}" var="docAssignMapping" varStatus="loop">
                           <tr style="cursor: pointer;">
                               <td width="5%" style="text-align: left">${loop.index+1}</td>
                               <td  width="95%" style="text-align: left">
                                       ${docAssignMapping.userPortal.emailAddress}
                               </td>
                               <c:if test="${havePermissionOnDocs}">
                                   <td width="5%"  style="text-align: center">
                                       <button onclick='<portlet:namespace />doDeleteDocAssignMappingAjax("${docAssignMapping.refId}","${docAssignMapping.refType}","${docAssignMapping.userId}")' class="btn btn-danger btn-small" type="button">ลบ</button>
                                   </td>
                               </c:if>
                           </tr>
                       </c:forEach>
                   </table>
               </c:if>
              </span>
                                    </td>
                                </tr>
                                <tr>
                                    <td align="center" colspan="4" width="20%">
                                        <form:hidden path="researchProjectM.docType"/>
                                        <c:if test="${mode=='edit'}">
                                            <c:if test="${havePermissionOnDocs}">

                                                <button type="button" onclick="<portlet:namespace />doSubmitForm('DRAFT')" class="btn btn-primary">Save as Draft</button>
                                                <button type="button" onclick="<portlet:namespace />doSubmitForm('PUBLISH')" class="btn btn-primary">Submit</button>
                                                <button class="btn btn-danger" type="button" onclick='return <portlet:namespace />confirmDelete("<portlet:actionURL><portlet:param name="action" value="delete"/><portlet:param name="researchProjectId"><jsp:attribute name="value"><c:out value="${researchProjectForm.researchProjectM.researchProjectId}"/></jsp:attribute></portlet:param></portlet:actionURL>")'>ลบ</button>
                                            </c:if>
                                        </c:if>
                                        <c:if test="${mode=='add' || mode =='copy'}">
                                            <button type="button" onclick="<portlet:namespace />doSubmitForm('DRAFT')" class="btn btn-primary">Save as Draft</button>
                                            <%--
                                            <button type="button" onclick="<portlet:namespace />doSubmitForm('PUBLISH')" class="btn btn-primary">Submit</button>
                                             --%>
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
                            นักวิจัย
                        </a>
                    </div>
                    <div id="collapseTwo" class="accordion-body collapse" style="height: 0px;">
                        <div class="accordion-inner">
                            <table border="0" width="100%" style="font-size: 13px">
                                <tbody>
                                <tr>
                                    <td align="center" width="100%" colspan="2">
                                        <div id="<portlet:namespace/>element_researcher" style="border: 1px solid #FFC299;background: #F9F9F9;padding: 10px;display: none">
                                            <table border="0" width="100%" style="border: 0px solid #FFC299;font-size:13px">
                                                <tr>
                                                    <td align="right" width="30%">
                        		<span>
                        			<label>นักวิจัย:<span style="color: red;font-size: 50;"> </span>
                                    </label>
                        		</span>
                                                    </td>
                                                    <td width="70%" colspan="3">
                                                        <div>

                                                            <input type="hidden" id="<portlet:namespace/>researcher_mode"/>
                                                            <input type="hidden" id="<portlet:namespace/>researcher_itemList"/>

                                                            <input type="hidden" id="<portlet:namespace/>researcher_researcherId"/>
                                                            <input type="hidden" id="<portlet:namespace/>researcher_researcherName"/>
                                                            <input type="hidden" id="<portlet:namespace/>researcher_positionId"/>
                                                            <input type="hidden" id="<portlet:namespace/>researcher_organizationId"/>
                                                            <input  type="text" style="background-color:
                    rgb(250, 250, 198);" class="form-control"
                                                                    id="researcherId_assign_autocomplete" placeholder="" />
                                                            <button id="<portlet:namespace/>researcherListAll" style="margin-top: -8px;display: none" onclick="<portlet:namespace />researcherPopup('',true,1)" type="button" class="btn btn-warning btn-small">...</button>

                                                                <%--
                                                                 <span id="researcherId_assignShow"></span>
                                                                  --%>
                                                        </div>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td align="right" width="30%">
                        		<span>
                        			<label>ตำแหน่ง:<span style="color: red;font-size: 50;"> </span>
                                    </label>
                        		</span>
                                                    </td>
                                                    <td width="70%" colspan="3">
                                                        <div>
                                                            <input type="text" id="<portlet:namespace/>researcher_position" readonly="readonly" class="form-control">

                                                        </div>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td align="right" width="30%">
                        		<span>
                        			<label>บริษัท/องค์กร:<span style="color: red;font-size: 50;"> </span>
                                    </label>
                        		</span>
                                                    </td>
                                                    <td width="70%" colspan="3">
                                                        <div>
                                                            <input type="text" id="<portlet:namespace/>researcher_organization" readonly="readonly" class="form-control">
                                                        </div>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td align="right" width="30%">
                        		<span>
                        			<label>เป็นหัวหน้าโครงการ?:<span style="color: red;font-size: 50;"> </span>
                                    </label>
                        		</span>
                                                    </td>
                                                    <td width="70%" colspan="3">
                                                        <div>
                                                            <input type="checkbox" id="<portlet:namespace/>researcher_isprojectLeader"  class="form-control">
                                                        </div>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td align="right" width="30%">
                        		<span>
                        			<label>ภาระการทำงาน:<span style="color: red;font-size: 50;"> </span>
                                    </label>
                        		</span>
                                                    </td>
                                                    <td width="70%" colspan="3">
                                                        <div>
                                                            <input type="text" id="<portlet:namespace/>researcher_workLoadRatio" style="width:75px"  class="form-control">
                                                        </div>
                                                    </td>
                                                </tr>
                                                <c:if test="${havePermissionOnDocs}">
                                                    <tr>
                                                        <td align="center" colspan="4" width="20%">
                                                            <button type="button" onclick="<portlet:namespace />doSubmitResearcherAjax()" class="btn btn-primary">Submit</button>
                                                        </td>
                                                    </tr>
                                                </c:if>
                                            </table>
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td align="left" width="30%">

                                    </td><td align="right" width="70%">
                <span sytle="padding-left:20px">
               <c:if test="${havePermissionOnDocs}">
                   <button type="button" onclick="<portlet:namespace />exportProofFile('participation')" class="btn">Export Participation</button>
                   <button type="button" onclick="<portlet:namespace />exportProofFile('academic')" class="btn">Export Academic</button>
                   <button  onclick="<portlet:namespace/>displayResearcher('add','0')" type="button" class="btn btn-primary">Add</button>
               </c:if>
               </span >
                                </td>
                                </tr>


                                </tbody></table>
                 <span id="<portlet:namespace/>researcher_item_list">
        <table class="table table-hover table-striped table-bordered table-condensed" border="1" style="font-size: 12px">
            <thead>
            <tr>
                <th width="5%" style="text-align: center"><div class="th_class">#</div></th>
                    <%--
                    <th width="15%" style="text-align: center"><div class="th_class">ตำแหน่ง</div></th>
                     --%>
                <th width="25%" style="text-align: center"><div class="th_class">ชื่อ - สกุล</div></th>
                <th width="27%" style="text-align: center"><div class="th_class">ภาควิชา/หน่วยงาน/บริษัท/องค์กร</div></th>
                <th width="14%" style="text-align: center"><div class="th_class">เป็นหัวหน้าโครงการ</div></th>
                <th width="14%" style="text-align: center"><div class="th_class">ภาระการทำงาน</div></th>
                <c:if test="${havePermissionOnDocs}">
                    <th width="10%"><div class="th_class"></div></th>
                </c:if>
            </tr>
            </thead>
            <tbody>

            <c:if test="${not empty researchProjectForm.researchProjectM.researchers}">
                <c:forEach items="${researchProjectForm.researchProjectM.researchers}" var="researcher" varStatus="loop">

                    <tr style="cursor: pointer;">
                        <td onclick='<portlet:namespace />doEditResearcherAjax("${researcher.researchProjectId}","${researcher.itemList}")'  style="text-align: left">${loop.index+1}</td>
                            <%--
                            <td style="text-align: left">${researcher.position.positionName}</td>
                             --%>


                        <td onclick='<portlet:namespace />doEditResearcherAjax("${researcher.researchProjectId}","${researcher.itemList}")' style="text-align: left">${researcher.position.positionName} ${researcher.researcherName}</td>
                        <td onclick='<portlet:namespace />doEditResearcherAjax("${researcher.researchProjectId}","${researcher.itemList}")' style="text-align: left">${researcher.organization.orgName}</td>
                        <td onclick='<portlet:namespace />doEditResearcherAjax("${researcher.researchProjectId}","${researcher.itemList}")' style="text-align: center">
                            <c:if test="${researcher.isprojectLeader=='T'}">
                                เป็น
                            </c:if>
                            <c:if test="${researcher.isprojectLeader!='T'}">
                                ไม่เป็น
                            </c:if>
                        </td>
                        <td onclick='<portlet:namespace />doEditResearcherAjax("${researcher.researchProjectId}","${researcher.itemList}")'  style="text-align: center">${researcher.workLoadRatio}</td>
                        <c:if test="${havePermissionOnDocs}">
                            <td style="text-align: center">
                                <button onclick='<portlet:namespace />doEditResearcherAjax("${researcher.researchProjectId}","${researcher.itemList}")' class="btn btn-small" type="button">แก้ใข</button>
                                <button onclick='<portlet:namespace />doDeleteResearcherAjax("${researcher.researchProjectId}","${researcher.itemList}")' class="btn btn-danger btn-small" type="button">ลบ</button>
                            </td>
                        </c:if>
                    </tr>

                </c:forEach>
            </c:if>


            </tbody>
        </table>
          </span>
                        </div>
                    </div>
                </div>
                <div class="accordion-group">
                    <div class="accordion-heading">
                        <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion2" href="#collapseThree">
                            ผลความก้าวหน้า
                        </a>
                    </div>
                    <div id="collapseThree" class="accordion-body collapse" style="height: 0px;">
                        <div class="accordion-inner">
                            <table border="0" width="100%" style="font-size: 13px">
                                <tbody>
                                <tr>
                                    <td align="center" width="100%" colspan="2">
                                        <div id="<portlet:namespace/>element_progress" style="border: 1px solid #FFC299;background: #F9F9F9;padding: 10px;display: none">
                                            <table border="0" width="100%" style="border: 0px solid #FFC299;font-size:13px">
                                                <tr>
                                                    <td align="right" width="30%">
                        		<span>
                        			<label>Due Date:<span style="color: red;font-size: 50;"> </span>
                                    </label>
                        		</span>
                                                    </td>
                                                    <td width="70%" colspan="3">
                                                        <div>
                                                            <input type="hidden" id="<portlet:namespace/>progress_mode" />
                                                            <input type="hidden" id="<portlet:namespace/>progress_itemList" />
                                                            <input type="hidden" id="<portlet:namespace/>progress_progressItemId" />

                                                            <input type="text" id="<portlet:namespace/>progress_dueDate" readonly="readonly" style="width:75px" class="form-control">
                                                        </div>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td align="right" width="30%">
                        		<span>
                        			<label>Submit Date:<span style="color: red;font-size: 50;"> </span>
                                    </label>
                        		</span>
                                                    </td>
                                                    <td width="70%" colspan="3">
                                                        <div>
                                                            <input type="text" id="<portlet:namespace/>progress_submitDate" readonly="readonly" style="width:75px" class="form-control">
                                                        </div>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td align="right" width="30%">
                        		<span>
                        			<label>Progress(%):<span style="color: red;font-size: 50;"> </span>
                                    </label>
                        		</span>
                                                    </td>
                                                    <td width="70%" colspan="3">
                                                        <div>
                                                            <input type="text" id="<portlet:namespace/>progress_progressPercentage" style="width:75px" class="form-control">
                                                        </div>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td align="right" width="30%">
                        		<span>
                        			<label>Remark:<span style="color: red;font-size: 50;"> </span>
                                    </label>
                        		</span>
                                                    </td>
                                                    <td width="70%" colspan="3">
                                                        <div>
                                                            <textarea rows="2" cols="2" class="500px" id="<portlet:namespace/>progress_remark"></textarea>

                                                        </div>
                                                    </td>
                                                </tr>
                                                <c:if test="${havePermissionOnDocs}">
                                                    <tr>
                                                        <td align="center" colspan="4" width="20%">
                                                            <button type="button" onclick="<portlet:namespace />doSubmitProgressAjax()" class="btn btn-primary">Submit</button>
                                                        </td>
                                                    </tr>
                                                </c:if>
                                            </table>
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td align="left" width="70%">

                                    </td><td align="right" width="30%">
                <span sytle="padding-left:20px">
               <c:if test="${havePermissionOnDocs}">
                   <button  onclick="<portlet:namespace/>displayProgress('add','0')" type="button" class="btn btn-primary">Add</button>
               </c:if>
               </span >
                                </td>
                                </tr>
                                </tbody></table>
                 <span id="<portlet:namespace/>progress_item_list">
        <table class="table table-hover table-striped table-bordered table-condensed" border="1" style="font-size: 12px">
            <thead>
            <tr> <!-- 50 42+8 -->
                <th width="5%" style="text-align: center"><div class="th_class">#</div></th>
                <th width="8%" style="text-align: center"><div class="th_class">Due Date</div></th>
                <th width="8%" style="text-align: center"><div class="th_class">Submit Date</div></th>
                <th width="5%" style="text-align: center"><div class="th_class">Progress(%)</div></th>
                <th width="38%" style="text-align: center"><div class="th_class">Remark</div></th>
                <c:if test="${havePermissionOnDocs}">
                    <th width="8%"><div class="th_class"></div></th>
                </c:if>
            </tr>
            </thead>
            <tbody>
            <c:if test="${not empty researchProjectForm.researchProjectM.progresList}">
                <c:forEach items="${researchProjectForm.researchProjectM.progresList}" var="progress" varStatus="loop">
                    <tr style="cursor: pointer;">
                        <td onclick='<portlet:namespace />doEditProgressAjax("${progress.researchProjectId}","${progress.itemList}")' style="text-align: left">${loop.index+1}</td>
                        <td onclick='<portlet:namespace />doEditProgressAjax("${progress.researchProjectId}","${progress.itemList}")' style="text-align: center">
                                <%--
                                    <fmt:formatDate type="time" value="${progress.dueDate}"   pattern="dd/MM/yyyy" var="dueDate" />
                                     --%>
                                ${progress.dueDateShow}
                        </td>
                        <td onclick='<portlet:namespace />doEditProgressAjax("${progress.researchProjectId}","${progress.itemList}")' style="text-align: center">
                                <%--
                                    <fmt:formatDate type="time" value="${progress.submitDate}"   pattern="dd/MM/yyyy" var="submitDate" />
                                     --%>
                                ${progress.submitDateShow}
                        </td>
                        <td onclick='<portlet:namespace />doEditProgressAjax("${progress.researchProjectId}","${progress.itemList}")' style="text-align: center">${progress.progressPercentage}</td>
                        <td onclick='<portlet:namespace />doEditProgressAjax("${progress.researchProjectId}","${progress.itemList}")' style="text-align: left">${progress.remark}</td>
                        <c:if test="${havePermissionOnDocs}">
                            <td style="text-align: center">
                                <button onclick='<portlet:namespace />doEditProgressAjax("${progress.researchProjectId}","${progress.itemList}")' class="btn btn-small" type="button">แก้ใข</button>
                                <button onclick='<portlet:namespace />doDeleteProgressAjax("${progress.researchProjectId}","${progress.itemList}")' class="btn btn-danger btn-small" type="button">ลบ</button>
                            </td>
                        </c:if>
                    </tr>
                </c:forEach>
            </c:if>
            </tbody>
        </table>
          </span>
                        </div>
                    </div>

                </div>
                <div class="accordion-group">
                    <div class="accordion-heading">
                        <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion2" href="#collapseFour">
                            การรับเงินงวด
                        </a>
                    </div>
                    <div id="collapseFour" class="accordion-body collapse" style="height: 0px;">
                        <div class="accordion-inner">
                            <table border="0" width="100%" style="font-size: 13px">
                                <tbody>
                                <tr>
                                    <td align="center" width="100%" colspan="2">
                                        <div id="<portlet:namespace/>element_payment" style="border: 1px solid #FFC299;background: #F9F9F9;padding: 10px;display: none">
                                            <table border="0" width="100%" style="border: 0px solid #FFC299;font-size:13px">
                                                <tr>
                                                    <td align="right" width="30%">
                        		<span>
                        			<label>Received Date:<span style="color: red;font-size: 50;"> </span>
                                    </label>
                        		</span>
                                                    </td>
                                                    <td width="70%" colspan="3">
                                                        <div>
                                                            <input type="text" id="<portlet:namespace/>payment_receivedDate" readonly="readonly" style="width:75px" class="form-control">
                                                        </div>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td align="right" width="30%">
                        		<span>
                        			<label>Received NO:<span style="color: red;font-size: 50;"> </span>
                                    </label>
                        		</span>
                                                    </td>
                                                    <td width="70%" colspan="3">
                                                        <div>

                                                            <input type="hidden" id="<portlet:namespace/>payment_mode" />
                                                            <input type="hidden" id="<portlet:namespace/>payment_itemList" />
                                                            <input type="hidden" id="<portlet:namespace/>payment_progressItemId" />
                                                            <input type="text" id="<portlet:namespace/>payment_receiptNo" style="width:75px" class="form-control">
                                                        </div>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td align="right" width="30%">
                        		<span>
                        			<label>Amount Received:<span style="color: red;font-size: 50;"> </span>
                                    </label>
                        		</span>
                                                    </td>
                                                    <td width="70%" colspan="3">
                                                        <div>
                                                            <input type="text" id="<portlet:namespace/>payment_amountReceived" style="width:75px" class="form-control">
                                                        </div>
                                                    </td>
                                                </tr>
                                                <c:if test="${havePermissionOnDocs}">
                                                    <tr>
                                                        <td align="center" colspan="4" width="20%">
                                                            <button type="button" onclick="<portlet:namespace />doSubmitPaymentAjax()" class="btn btn-primary">Submit</button>
                                                        </td>
                                                    </tr>
                                                </c:if>
                                            </table>
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td align="left" width="70%">

                                    </td><td align="right" width="30%">
                <span sytle="padding-left:20px">
                <c:if test="${havePermissionOnDocs}">
                    <button  onclick="<portlet:namespace/>displayPayment('add','0')" type="button" class="btn btn-primary">Add</button>
                </c:if>
               </span >
                                </td>
                                </tr>
                                </tbody></table>
                 <span id="<portlet:namespace/>payment_item_list">
        <table class="table table-hover table-striped table-bordered table-condensed" border="1" style="font-size: 12px">
            <thead>
            <tr> <!-- 50 42+8 -->
                <th width="5%" style="text-align: center"><div class="th_class">#</div></th>
                <th width="8%" style="text-align: center"><div class="th_class">Received Date</div></th>
                <th width="21%" style="text-align: center"><div class="th_class">Received NO</div></th>
                <th width="20%" style="text-align: center"><div class="th_class">Amount Received</div></th>
                <c:if test="${havePermissionOnDocs}">
                    <th width="8%"><div class="th_class"></div></th>
                </c:if>
            </tr>
            </thead>
            <tbody>
            <c:if test="${not empty researchProjectForm.researchProjectM.payments}">
                <c:forEach items="${researchProjectForm.researchProjectM.payments}" var="payment" varStatus="loop">
                    <tr style="cursor: pointer;">
                        <td onclick='<portlet:namespace />doEditPaymentAjax("${payment.researchProjectId}","${payment.itemList}")' style="text-align: left">${loop.index+1}</td>
                        <td onclick='<portlet:namespace />doEditPaymentAjax("${payment.researchProjectId}","${payment.itemList}")' style="text-align: center">
                                <%--
                                  <fmt:formatDate type="time" value="${payment.receivedDate}"   pattern="dd/MM/yyyy" var="receivedDate" />
                                   --%>
                                ${payment.receivedDateShow}
                        </td>
                        <td onclick='<portlet:namespace />doEditPaymentAjax("${payment.researchProjectId}","${payment.itemList}")' style="text-align: left">${payment.receiptNo}</td>
                        <td onclick='<portlet:namespace />doEditPaymentAjax("${payment.researchProjectId}","${payment.itemList}")' style="text-align: right;">
                            <fmt:formatNumber type="number" pattern="###,###,###.00" value="${payment.amountReceived}"  var="amountReceived"  />
                                ${amountReceived}
                        </td>
                        <c:if test="${havePermissionOnDocs}">
                            <td style="text-align: center">
                                <button onclick='<portlet:namespace />doEditPaymentAjax("${payment.researchProjectId}","${payment.itemList}")' class="btn btn-small" type="button">แก้ใข</button>
                                <button onclick='<portlet:namespace />doDeletePaymentAjax("${payment.researchProjectId}","${payment.itemList}")' class="btn btn-danger btn-small" type="button">ลบ</button>
                            </td>
                        </c:if>
                    </tr>
                </c:forEach>
            </c:if>
            </tbody>
        </table>
          </span>
                        </div>
                    </div>
                </div>
                <div class="accordion-group">
                    <div class="accordion-heading">
                        <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion2" href="#collapseFive">
                            การเบิกเงินงวด
                        </a>
                    </div>
                    <div id="collapseFive" class="accordion-body collapse" style="height: 0px;">
                        <div class="accordion-inner">
                            <table border="0" width="100%" style="font-size: 13px">
                                <tbody>
                                <tr>
                                    <td align="center" width="100%" colspan="2">
                                        <div id="<portlet:namespace/>element_withdraw" style="border: 1px solid #FFC299;background: #F9F9F9;padding: 10px;display: none">
                                            <table border="0" width="100%" style="border: 0px solid #FFC299;font-size:13px">
                                                <tr>
                                                    <td align="right" width="30%">
                        		<span>
                        			<label>Withdraw Date:<span style="color: red;font-size: 50;"> </span>
                                    </label>
                        		</span>
                                                    </td>
                                                    <td width="70%" colspan="3">
                                                        <div>
                                                            <input type="hidden" id="<portlet:namespace/>withdraw_mode" />
                                                            <input type="hidden" id="<portlet:namespace/>withdraw_itemList" />
                                                            <input type="hidden" id="<portlet:namespace/>withdraw_withdrawItemId" />
                                                            <input type="text" id="<portlet:namespace/>withdraw_date" readonly="readonly" style="width:75px" class="form-control">
                                                        </div>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td align="right" width="30%">
                        		<span>
                        			<label>Amount Withdraw:<span style="color: red;font-size: 50;"> </span>
                                    </label>
                        		</span>
                                                    </td>
                                                    <td width="70%" colspan="3">
                                                        <div>
                                                            <input type="text" id="<portlet:namespace/>withdraw_amount" style="width:75px" class="form-control">
                                                        </div>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td align="right" width="30%">
                        		<span>
                        			<label>Balance:<span style="color: red;font-size: 50;"> </span>
                                    </label>
                        		</span>
                                                    </td>
                                                    <td width="70%" colspan="3">
                                                        <div>
                                                            <input type="text" id="<portlet:namespace/>withdraw_balance" style="width:75px" class="form-control">
                                                        </div>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td align="right" width="30%">
                        		<span>
                        			<label>Remark:<span style="color: red;font-size: 50;"> </span>
                                    </label>
                        		</span>
                                                    </td>
                                                    <td width="70%" colspan="3">
                                                        <div>
                                                            <textarea rows="2" cols="2" class="500px" id="<portlet:namespace/>withdraw_remark"></textarea>

                                                        </div>
                                                    </td>
                                                </tr>
                                                <c:if test="${havePermissionOnDocs}">
                                                    <tr>
                                                        <td align="center" colspan="4" width="20%">
                                                            <button type="button" onclick="<portlet:namespace />doSubmitWithdrawAjax()" class="btn btn-primary">Submit</button>
                                                        </td>
                                                    </tr>
                                                </c:if>
                                            </table>
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td align="left" width="70%">

                                    </td><td align="right" width="30%">
                <span sytle="padding-left:20px">
              <c:if test="${havePermissionOnDocs}">
                  <button  onclick="<portlet:namespace/>displayWithdraw('add','0')" type="button" class="btn btn-primary">Add</button>
              </c:if>
               </span >
                                </td>
                                </tr>
                                </tbody></table>
                 <span id="<portlet:namespace/>withdraw_item_list">
        <table class="table table-hover table-striped table-bordered table-condensed" border="1" style="font-size: 12px">
            <thead>
            <tr> <!-- 50 42+8 -->
                <th width="5%" style="text-align: center"><div class="th_class">#</div></th>
                <th width="10%" style="text-align: center"><div class="th_class">Withdraw Date</div></th>
                <th width="12%" style="text-align: center"><div class="th_class">Amount Withdraw</div></th>
                <th width="10%" style="text-align: center"><div class="th_class">Balance</div></th>
                <th width="34%" style="text-align: center"><div class="th_class">Remark</div></th>
                <c:if test="${havePermissionOnDocs}">
                    <th width="8%"><div class="th_class"></div></th>
                </c:if>
            </tr>
            </thead>
            <tbody>

            <c:if test="${not empty researchProjectForm.researchProjectM.withdraws}">
                <c:forEach items="${researchProjectForm.researchProjectM.withdraws}" var="withdraw" varStatus="loop">
                    <tr style="cursor: pointer;">
                        <td onclick='<portlet:namespace />doEditWithdrawAjax("${withdraw.researchProjectId}","${withdraw.itemList}")' style="text-align: left">${loop.index+1}</td>
                        <td onclick='<portlet:namespace />doEditWithdrawAjax("${withdraw.researchProjectId}","${withdraw.itemList}")' style="text-align: center">
                                <%--
                                <fmt:formatDate type="time" value="${withdraw.withdrawDate}"   pattern="dd/MM/yyyy" var="withdrawDate" />
                                 --%>
                                ${withdraw.withdrawDateShow}
                        </td>
                        <td onclick='<portlet:namespace />doEditWithdrawAjax("${withdraw.researchProjectId}","${withdraw.itemList}")' style="text-align: right;">
                            <fmt:formatNumber type="number" pattern="###,###,###.00" value="${withdraw.amountWithdraw}"  var="amountWithdraw"  />
                                ${amountWithdraw}
                        </td>
                        <td onclick='<portlet:namespace />doEditWithdrawAjax("${withdraw.researchProjectId}","${withdraw.itemList}")' style="text-align: right">
                            <fmt:formatNumber type="number" pattern="###,###,###.00" value="${withdraw.balance}"  var="balance"  />
                                ${balance}
                        </td>
                        <td onclick='<portlet:namespace />doEditWithdrawAjax("${withdraw.researchProjectId}","${withdraw.itemList}")' style="text-align: left">${withdraw.remark}</td>
                        <c:if test="${havePermissionOnDocs}">
                            <td style="text-align: center">
                                <button onclick='<portlet:namespace />doEditWithdrawAjax("${withdraw.researchProjectId}","${withdraw.itemList}")' class="btn btn-small" type="button">แก้ใข</button>
                                <button onclick='<portlet:namespace />doDeleteWithdrawAjax("${withdraw.researchProjectId}","${withdraw.itemList}")' class="btn btn-danger btn-small" type="button">ลบ</button>
                            </td>
                        </c:if>
                    </tr>
                </c:forEach>
            </c:if>
            </tbody>
        </table>
          </span>
                        </div>
                    </div>
                </div>
                <div class="accordion-group">
                    <div class="accordion-heading">
                        <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion2" href="#collapseSix">
                            เอกสาร
                        </a>
                    </div>
                    <div id="collapseSix" class="accordion-body collapse" style="height: 0px;">
                        <div class="accordion-inner">
                            <table border="0" width="100%" style="font-size: 13px">
                                <tbody>
                                <tr>
                                    <td align="center" width="100%" colspan="2">
                                        <div id="<portlet:namespace/>element_document" style="border: 1px solid #FFC299;background: #F9F9F9;padding: 10px;display: none">
                                            <table border="0" width="100%" style="border: 0px solid #FFC299;font-size:13px">
                                                <tr>
                                                    <td align="right" width="30%">
                        		<span>
                        			<label>Document Name:<span style="color: red;font-size: 50;"> </span>
                                    </label>
                        		</span>
                                                    </td>
                                                    <td width="70%" colspan="3">
                                                        <div>

                                                            <input type="hidden" id="<portlet:namespace/>document_mode"/>
                                                            <input type="hidden" id="<portlet:namespace/>document_itemList"/>
                                                            <input type="text" id="<portlet:namespace/>document_description" style="width:75px" class="form-control">
                                                        </div>
                                                    </td>
                                                </tr>
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
                                                <c:if test="${havePermissionOnDocs}">
                                                    <tr>
                                                        <td align="center" colspan="4" width="20%">
                                                            <button type="button" onclick="<portlet:namespace />doSubmitDocumentAjax()" class="btn btn-primary">Submit</button>
                                                        </td>
                                                    </tr>
                                                </c:if>
                                            </table>
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td align="left" width="70%">

                                    </td><td align="right" width="30%">
                <span sytle="padding-left:20px">
              
               <c:if test="${havePermissionOnDocs}">
                   <button  onclick="<portlet:namespace/>displayDocument('add','0')" type="button" class="btn btn-primary">Add</button>
               </c:if>
               </span >
                                </td>
                                </tr>
                                </tbody></table>
                 <span id="<portlet:namespace/>document_item_list"> 
        <table class="table table-hover table-striped table-bordered table-condensed" border="1" style="font-size: 12px">
            <thead>
            <tr> <!-- 50 42+8 -->
                <th width="5%" style="text-align: center"><div class="th_class">#</div></th>
                <th width="85%" style="text-align: center"><div class="th_class">Document Name</div></th>
                <c:if test="${havePermissionOnDocs}">
                    <th width="10%"><div class="th_class"></div></th>
                </c:if>
            </tr>
            </thead>
            <tbody>

            <c:if test="${not empty researchProjectForm.researchProjectM.documents}">
                <c:forEach items="${researchProjectForm.researchProjectM.documents}" var="document" varStatus="loop">
                    <tr style="cursor: pointer;">
                        <td onclick='<portlet:namespace />doEditDocumentAjax("${document.researchProjectId}","${document.itemList}")' style="text-align: left">${loop.index+1}</td>
                        <c:if test="${not empty document.fileName}" >
                            <td onclick='<portlet:namespace />doEditDocumentAjax("${document.researchProjectId}","${document.itemList}")' style="text-align: left">${document.description}  [ <a style="text-decoration: underline;" onclick='<portlet:namespace />downloadFile("${document.itemList}","${document.researchProjectId}")'>${document.fileName}</a> ]</td>
                        </c:if>
                        <c:if test="${empty document.fileName}" >
                            <td onclick='<portlet:namespace />doEditDocumentAjax("${document.researchProjectId}","${document.itemList}")' style="text-align: left">${document.description}  </td>
                        </c:if>
                        <c:if test="${havePermissionOnDocs}">
                            <td style="text-align: center">
                                <button onclick='<portlet:namespace />doEditDocumentAjax("${document.researchProjectId}","${document.itemList}")' class="btn btn-small" type="button">แก้ใข</button>
                                <button onclick='<portlet:namespace />doDeleteDocumentAjax("${document.researchProjectId}","${document.itemList}")' class="btn btn-danger btn-small" type="button">ลบ</button>
                            </td>
                        </c:if>
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

<script src="<c:url value="/resources/js/kmuttPortal.js"/>"></script>
<script>
    $(document).ready(function() {
        var _path="${url}";


        $("#endDate" ).datepicker({
            showOn: "button",
            buttonImage: '<c:url value="/resources/images/calendar.gif"/>',
            buttonImageOnly: true,
            dateFormat:"dd/mm/yy"
        });
        $("#startDate" ).datepicker({
            showOn: "button",
            buttonImage: '<c:url value="/resources/images/calendar.gif"/>',
            buttonImageOnly: true,
            dateFormat:"dd/mm/yy"
        });
        $("#reportDuedate" ).datepicker({
            showOn: "button",
            buttonImage: '<c:url value="/resources/images/calendar.gif"/>',
            buttonImageOnly: true,
            dateFormat:"dd/mm/yy"
        });
        $("#reportSubmitDate" ).datepicker({
            showOn: "button",
            buttonImage: '<c:url value="/resources/images/calendar.gif"/>',
            buttonImageOnly: true,
            dateFormat:"dd/mm/yy"
        });
        $("#<portlet:namespace/>withdraw_date" ).datepicker({
            showOn: "button",
            buttonImage: '<c:url value="/resources/images/calendar.gif"/>',
            buttonImageOnly: true,
            dateFormat:"dd/mm/yy"
        });
        $("#<portlet:namespace/>payment_receivedDate" ).datepicker({
            showOn: "button",
            buttonImage: '<c:url value="/resources/images/calendar.gif"/>',
            buttonImageOnly: true,
            dateFormat:"dd/mm/yy"
        });
        $("#<portlet:namespace/>progress_dueDate" ).datepicker({
            showOn: "button",
            buttonImage: '<c:url value="/resources/images/calendar.gif"/>',
            buttonImageOnly: true,
            dateFormat:"dd/mm/yy"
        });
        $("#<portlet:namespace/>progress_submitDate" ).datepicker({
            showOn: "button",
            buttonImage: '<c:url value="/resources/images/calendar.gif"/>',
            buttonImageOnly: true,
            dateFormat:"dd/mm/yy"
        });


        $('#researchGroupId_assign_autocomplete').autocomplete({
            source: function( request, response ) {
                var researchGroupM={
                    keySearch:request.term
                }
                ResearchAjax.getResearchGroupList(researchGroupM,{
                    callback:function(data){
                        data=data.resultListObj;
                        if(data!=null && data.length>0){
                            response( $.map( data, function( item ) {
                                return {
                                    label: item.groupTh ,
                                    value: item.groupTh,
                                    name: item.groupTh,
                                    id: item.researchGroupId,
                                    code: item.groupCode
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
                $('input[id="researchProjectM.researchGroupId"]').val(ui.item.id);
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

        $('#fundingResourceId_assign_autocomplete').autocomplete({
            source: function( request, response ) {
                var modelM={
                    keySearch:request.term
                }
                ResearchAjax.getFundingResourceList(modelM,{
                    callback:function(data){
                        data=data.resultListObj;
                        if(data!=null && data.length>0){
                            response( $.map( data, function( item ) {
                                return {
                                    label: item.frNameThai ,
                                    value: item.frNameThai,
                                    name: item.frNameThai ,
                                    id: item.fundingResourceId
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
                $('input[id="researchProjectM.fundingResourceId"]').val(ui.item.id);
                $("#fundingResourceId_assignShow").html(" "+ui.item.name+" ");
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
                                    label: item.orgName,
                                    value: item.orgName,
                                    name: item.orgName ,
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
                $('input[id="researchProjectM.organizationId"]').val(ui.item.id);
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

        $('#researcherId_assign_autocomplete').autocomplete({
            source: function( request, response ) {
                var modelM={
                    keySearch:request.term
                }
                ResearchAjax.getResearcherList(modelM,{
                    callback:function(data){
                        data=data.resultListObj;
                        if(data!=null && data.length>0){
                            response( $.map( data, function( item ) {
                                return {
                                    label:  item.nameThai +" "+item.surnameThai,
                                    value:  item.nameThai +" "+item.surnameThai ,
                                    name: item.nameThai +" "+item.surnameThai ,
                                    id: item.researcherId
                                    ,

                                    position:(item.position!=null && item.position.positionName)?item.position.positionName:"",
                                    positionId:(item.position!=null && item.position.positionId)?item.position.positionId:"",
                                    organization:(item.organization!=null && item.organization.orgName)?item.organization.orgName:"",
                                    organizationId:(item.organization!=null && item.organization.organizationId)?item.organization.organizationId:""
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

                $('#<portlet:namespace/>researcher_researcherId').val(ui.item.id);
                $('#<portlet:namespace/>researcher_researcherName').val(ui.item.name);
                $('#<portlet:namespace/>researcher_positionId').val(ui.item.positionId);
                $('#<portlet:namespace/>researcher_organizationId').val(ui.item.organizationId);

                $("#researcherId_assignShow").html(" "+ui.item.name+" ");
                $('#<portlet:namespace/>researcher_position').val(ui.item.position);
                $('#<portlet:namespace/>researcher_organization').val(ui.item.organization);

                return false;
            },
            open: function() {
                $( this ).removeClass( "ui-corner-all" ).addClass( "ui-corner-top" );
            },
            close: function() {
                $( this ).removeClass( "ui-corner-top" ).addClass( "ui-corner-all" );
            }
        });

        $('#docs_assign_autocomplete').autocomplete({
            source: function( request, response ) {
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
                this.value = ui.item.name;
                $('#docsAssign').val(ui.item.id);
                //$('#docsAssign').val(ui.item.name);
                var buttonShow='<button style=\"margin-top: -8px"\" onclick=\'<portlet:namespace />doAddDocAssignMappingAjax("'+$("#researchProjectId").val()+'","RESEARCH","'+ui.item.id+'")\' class="btn btn-primary btn-small" type="button">Add</button>'
                //$("#docs_assignShow").html(" "+ui.item.name+" "+buttonShow);
                $("#docs_assignShow").html(buttonShow);
                $("#docs_assignShow").show("slow");
                return false;
            },
            open: function() {
                $( this ).removeClass( "ui-corner-all" ).addClass( "ui-corner-top" );
            },
            close: function() {
                $( this ).removeClass( "ui-corner-top" ).addClass( "ui-corner-all" );
            }
        });


        var ele_numbers=["<portlet:namespace/>researcher_workLoadRatio","<portlet:namespace/>progress_progressPercentage"]
        for(var i=0;i<ele_numbers.length;i++){
            $('#'+ele_numbers[i]).keyup(function() {
                var dInput = this.value;
                // alert(dInput.length)
                if ($.trim(dInput).length > 0 && !<portlet:namespace />validateDigitOnly(dInput)) {
                    //alert('กรอก  เฉพาะตัวเลขเท่านั้น.');
                    $(this).val("");
                    $(this).focus();
                    return false;
                }
                if(parseInt(dInput)>100){
                    //alert('กรอก ค่ามากเกิน 100.');
                    $(this).val("");
                    $(this).focus();
                    return false;
                }
            });
        }

        var budget_numbers=["researchProjectM.purposeBudget","researchProjectM.submitBudget"];
        for(var i=0;i<budget_numbers.length;i++){
            $('input[id=\"'+budget_numbers[i]+'\"]').keyup(function() {
                var dInput = this.value;
                // alert(dInput.length)
                if ($.trim(dInput).length > 0 && !<portlet:namespace />validateDigit(dInput)) {
                    //alert('กรอก  เฉพาะตัวเลขเท่านั้น.');
                    $(this).val("");
                    $(this).focus();
                    return false;
                }

            });
        }
        /*
         $('input[id="researchProjectM.purposeBudget"]').keyup(function() {
         var dInput = this.value;
         // alert(dInput.length)
         if ($.trim(dInput).length > 0 && !<portlet:namespace />validateDigit(dInput)) {
         //alert('กรอก  เฉพาะตัวเลขเท่านั้น.');
         $('input[id="researchProjectM.purposeBudget"]').val("");
         $('input[id="researchProjectM.purposeBudget"]').focus();
         return false;
         }

         });
         $('input[id="researchProjectM.submitBudget"]').keyup(function() {
         var dInput = this.value;
         // alert(dInput.length)
         if ($.trim(dInput).length > 0 && !<portlet:namespace />validateDigit(dInput)) {
         //alert('กรอก  เฉพาะตัวเลขเท่านั้น.');
         $('input[id="researchProjectM.submitBudget"]').val("");
         $('input[id="researchProjectM.submitBudget"]').focus();
         return false;
         }

         });
         */
        var ids=["<portlet:namespace/>withdraw_balance","<portlet:namespace/>payment_amountReceived","<portlet:namespace/>withdraw_amount"];
        for(var i=0;i<ids.length;i++){
            $('#'+ids[i]).keyup(function() {
                var dInput = this.value;
                // alert(dInput.length)
                if ($.trim(dInput).length > 0 && !<portlet:namespace />validateDigit(dInput)) {
                    //alert('กรอก  เฉพาะตัวเลขเท่านั้น.');
                    $(this).val("");
                    $(this).focus();
                    return false;
                }

            });
        }
        $('#researchProjectM\\.budgetYear').keyup(function() {
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

    <%-- START Researcher  --%>
    function <portlet:namespace />doEditResearcherAjax(researchProjectId,itemList){
        //alert(researchProjectId)
        var researchProjectResearcher={
            itemList:itemList,
            researchProjectId:researchProjectId,
            researcherItemId:1
        }
        ResearchAjax.findResearchProjectResearcherById(researchProjectResearcher,{
            callback:function(data){
                if(data!=null){
                    $("#researcherId_assign_autocomplete").prop("readonly","readonly")
                    $("#researcherId_assign_autocomplete").css("background-color","");

                    $("#<portlet:namespace/>researcher_researcherId").val(data.researcherId);
                    $("#<portlet:namespace/>researcher_researcherName").val(data.researcherName);
                    var positionId='';
                    var positionName='';
                    if(data.position!=null){
                        positionId=data.position.positionId;
                        positionName=data.position.positionName;
                    }
                    var organizationId='';
                    var orgName='';
                    if(data.organization!=null){
                        organizationId=data.organization.organizationId;
                        orgName=data.organization.orgName;
                    }
                    $("#<portlet:namespace/>researcher_positionId").val(positionId);
                    $("#<portlet:namespace/>researcher_organizationId").val(organizationId);
                    // $("#researcherId_assign_autocomplete").val(data.researcherId);
                    $("#researcherId_assign_autocomplete").val(data.researcherName);
                    $("#researcherId_assignShow").html(data.researcherName);
                    $("#<portlet:namespace/>researcher_position").val(positionName);
                    $("#<portlet:namespace/>researcher_organization").val(orgName);
                    var isprojectleader=false;
                    if(data.isprojectLeader=='T')
                        isprojectleader=true;
                    $("#<portlet:namespace/>researcher_isprojectLeader").prop("checked",isprojectleader);
                    $("#<portlet:namespace/>researcher_workLoadRatio").val(data.workLoadRatio);

                    $( "#<portlet:namespace/>researcher_mode" ).val("edit");

                    $( "#<portlet:namespace/>researcher_itemList" ).val(itemList);
                    $( "#<portlet:namespace/>researcher_researcherItemId" ).val("1");


                    $( "#<portlet:namespace/>element_researcher" ).show( "slow");
                    $( "#<portlet:namespace/>researcherListAll" ).hide( "slow");
                }
            }
        });
    }
    function <portlet:namespace />doDeleteResearcherAjax(researchProjectId,itemList){
        var agree=confirm(" ต้องการลบข้อมูลหรือไม่? ");
        if (agree){
            <portlet:namespace />showDownlod("<portlet:namespace/>researcher_item_list");
            var researchProjectResearcher={
                researchProjectId:researchProjectId,
                itemList:itemList
            }
            ResearchAjax.deleteResearchProjectResearcher(researchProjectResearcher,{
                callback:function(data){
                    <portlet:namespace/>render_researcher_item(data);
                    $( "#<portlet:namespace/>element_researcher" ).hide( "slow");
                }
            });
            return true ;
        }
        else{
            return false ;
        }


    }
    function <portlet:namespace />doSubmitResearcherAjax(){
        var mode=$( "#<portlet:namespace/>researcher_mode" ).val();
        //alert(mode)
        var positionId=$('#<portlet:namespace/>researcher_positionId').val();
        var position;
        if(positionId.length>0){
            position={
                positionId:positionId
            };
        }else{
            position={

            };
        }
        var organizationId=$('#<portlet:namespace/>researcher_organizationId').val();
        var organization;
        if(organizationId.length>0){
            organization={
                organizationId:organizationId
            };
        }else{
            organization={

            };
        }
        var isprojectLeader="F";
        if($('#<portlet:namespace/>researcher_isprojectLeader').prop("checked"))
            isprojectLeader="T"

        var researchProjectResearcher={
            researchProjectId:$("#researchProjectId").val(),
            researcherItemId:1,
            isprojectLeader:isprojectLeader,
            researcherDept:$('#<portlet:namespace/>researcher_organization').val(),
            workLoadRatio:$('#<portlet:namespace/>researcher_workLoadRatio').val(),
            organization:organization,
            position:position,
            researcherId:$('#<portlet:namespace/>researcher_researcherId').val(),
            researcherName:$('#<portlet:namespace/>researcher_researcherName').val(),
            createdBy:"${user.userId}",
            updatedBy:"${user.userId}",
            itemList:$( "#<portlet:namespace/>researcher_itemList" ).val()
        }
        //alert(researchProjectResearcher)

        ResearchAjax.countResearchProjectResearcher(researchProjectResearcher,{
            callback:function(data){
                var max_percent=data+parseInt(researchProjectResearcher.workLoadRatio);
                if(max_percent>100){
                    $('#<portlet:namespace/>researcher_workLoadRatio').val("")
                    $('#<portlet:namespace/>researcher_workLoadRatio').focus()
                    //alert(" สัดส่วนการทำงาน เกิน 100 ")
                    return false;
                }else{
                    <portlet:namespace />showDownlod("<portlet:namespace/>researcher_item_list");
                    ResearchAjax.updateResearchProjectResearcher(researchProjectResearcher,mode,{
                        callback:function(data){
                            <portlet:namespace/>render_researcher_item(data);
                            $( "#<portlet:namespace/>element_researcher" ).hide( "slow");
                        }
                    });
                }

            }
        });



    }
    <%-- END Researcher  --%>

    <%-- START Progress  --%>
    function <portlet:namespace />doEditProgressAjax(researchProjectId,itemList){

        var researchProjectProgress={
            itemList:itemList,
            researchProjectId:researchProjectId,
            progressItemId:1
        }
        ResearchAjax.findResearchProjectProgressById(researchProjectProgress,{
            callback:function(data){
                if(data!=null){
                    $( "#<portlet:namespace/>progress_dueDate" ).val(data.dueDateShow);
                    $( "#<portlet:namespace/>progress_remark" ).val(data.remark);
                    $( "#<portlet:namespace/>progress_progressPercentage" ).val(data.progressPercentage);
                    $( "#<portlet:namespace/>progress_submitDate" ).val(data.submitDateShow);
                    $( "#<portlet:namespace/>progress_mode" ).val("edit");

                    $( "#<portlet:namespace/>progress_itemList" ).val(itemList);
                    $( "#<portlet:namespace/>progress_progressItemId" ).val("1");


                    $( "#<portlet:namespace/>element_progress" ).show( "slow");
                }
            }
        });
    }
    function <portlet:namespace />doDeleteProgressAjax(researchProjectId,itemList){
        var agree=confirm(" ต้องการลบข้อมูลหรือไม่? ");
        if (agree){
            <portlet:namespace />showDownlod("<portlet:namespace/>progress_item_list");
            var researchProjectProgress={
                researchProjectId:researchProjectId,
                itemList:itemList
            }
            ResearchAjax.deleteResearchProjectProgress(researchProjectProgress,{
                callback:function(data){
                    <portlet:namespace/>render_progress_item(data);
                    $( "#<portlet:namespace/>element_progress" ).hide( "slow");
                }
            });
            return true ;
        }
        else{
            return false ;
        }


    }

    // Date dueDate;
    // Date submitDate;
    function <portlet:namespace />doSubmitProgressAjax(){
        var mode=$( "#<portlet:namespace/>progress_mode" ).val();;
        var researchProjectProgress={
            researchProjectId:$("#researchProjectId").val(),
            progressItemId:1,
            progressPercentage:$( "#<portlet:namespace/>progress_progressPercentage" ).val(),
            remark:$( "#<portlet:namespace/>progress_remark" ).val(),
            dueDateShow:$( "#<portlet:namespace/>progress_dueDate" ).val(),//"31/03/2015",
            submitDateShow:$( "#<portlet:namespace/>progress_submitDate" ).val(),
            createdBy:"${user.userId}",
            updatedBy:"${user.userId}",
            itemList:$( "#<portlet:namespace/>progress_itemList" ).val()
        }
        <portlet:namespace />showDownlod("<portlet:namespace/>progress_item_list");
        ResearchAjax.updateResearchProjectProgress(researchProjectProgress,mode,{
            callback:function(data){
                <portlet:namespace/>render_progress_item(data);
                $( "#<portlet:namespace/>element_progress" ).hide( "slow");
            }
        });
    }
    <%-- END Progress  --%>

    <%-- START Payment  --%>

    function <portlet:namespace />doEditPaymentAjax(researchProjectId,itemList){

        var researchProjectPayment={
            itemList:itemList,
            researchProjectId:researchProjectId,
            paymentItemId:1
        }

        ResearchAjax.findResearchProjectPaymentById(researchProjectPayment,{
            callback:function(data){
                if(data!=null){
                    $( "#<portlet:namespace/>payment_receivedDate" ).val(data.receivedDateShow);
                    $( "#<portlet:namespace/>payment_receiptNo" ).val(data.receiptNo);
                    $( "#<portlet:namespace/>payment_amountReceived" ).val(data.amountReceived);
                    $( "#<portlet:namespace/>payment_mode" ).val("edit");
                    $( "#<portlet:namespace/>payment_itemList" ).val(itemList);
                    $( "#<portlet:namespace/>payment_paymentItemId" ).val("1");


                    $( "#<portlet:namespace/>element_payment" ).show( "slow");
                }
            }
        });
    }

    function <portlet:namespace />doDeletePaymentAjax(researchProjectId,itemList){
        var agree=confirm(" ต้องการลบข้อมูลหรือไม่? ");
        if (agree){
            <portlet:namespace />showDownlod("<portlet:namespace/>payment_item_list");
            var researchProjectPaymentM={
                researchProjectId:researchProjectId,
                itemList:itemList
            }
            ResearchAjax.deleteResearchProjectPayment(researchProjectPaymentM,{
                callback:function(data){
                    <portlet:namespace/>render_payment_item(data);
                    $( "#<portlet:namespace/>element_payment" ).hide( "slow");
                }
            });
            return true ;
        }
        else{
            return false ;
        }


    }

    // Date receivedDate;

    function <portlet:namespace />doSubmitPaymentAjax(){
        var mode=$( "#<portlet:namespace/>payment_mode" ).val();



        var researchProjectPayment={
            researchProjectId:$("#researchProjectId").val(),
            paymentItemId:1,
            amountReceived:$( "#<portlet:namespace/>payment_amountReceived" ).val(),
            receiptNo:$( "#<portlet:namespace/>payment_receiptNo" ).val(),
            receivedDateShow:$( "#<portlet:namespace/>payment_receivedDate" ).val(),
            createdBy:"${user.userId}",
            updatedBy:"${user.userId}",
            itemList:$( "#<portlet:namespace/>payment_itemList" ).val()
        }
        <portlet:namespace />showDownlod("<portlet:namespace/>payment_item_list");
        ResearchAjax.updateResearchProjectPayment(researchProjectPayment,mode,{
            callback:function(data){
                <portlet:namespace/>render_payment_item(data);
                $( "#<portlet:namespace/>element_payment" ).hide( "slow");
            }
        });
    }
    <%-- END Payment  --%>

    <%-- START Withdraw  --%>
    function <portlet:namespace />doEditWithdrawAjax(researchProjectId,itemList){

        var researchProjectWithdraw={
            itemList:itemList,
            researchProjectId:researchProjectId,
            withdrawItemId:1
        }

        ResearchAjax.findResearchProjectWithdrawById(researchProjectWithdraw,{
            callback:function(data){
                if(data!=null){
                    $( "#<portlet:namespace/>withdraw_date" ).val(data.withdrawDateShow);
                    $( "#<portlet:namespace/>withdraw_amount" ).val(data.amountWithdraw);
                    $( "#<portlet:namespace/>withdraw_balance" ).val(data.balance);
                    $( "#<portlet:namespace/>withdraw_remark" ).val(data.remark);

                    $( "#<portlet:namespace/>withdraw_mode" ).val("edit")
                    $( "#<portlet:namespace/>withdraw_itemList" ).val(itemList);
                    $( "#<portlet:namespace/>withdraw_withdrawItemId" ).val("1");


                    $( "#<portlet:namespace/>element_withdraw" ).show( "slow");
                }
            }
        });
    }

    function <portlet:namespace />doDeleteWithdrawAjax(researchProjectId,itemList){
        var agree=confirm(" ต้องการลบข้อมูลหรือไม่? ");
        if (agree){
            <portlet:namespace />showDownlod("<portlet:namespace/>withdraw_item_list");
            var researchProjectWithdraw={
                researchProjectId:researchProjectId,
                itemList:itemList
            }
            ResearchAjax.deleteResearchProjectWithdraw(researchProjectWithdraw,{
                callback:function(data){
                    <portlet:namespace/>render_withdraw_item(data);
                    $( "#<portlet:namespace/>element_withdraw" ).hide( "slow");
                }
            });
            return true ;
        }
        else{
            return false ;
        }


    }
    function <portlet:namespace />doSubmitWithdrawAjax(){

        var mode=$( "#<portlet:namespace/>withdraw_mode" ).val();
        // Date withdrawDate;
        var withdraw_date = $( "#<portlet:namespace/>withdraw_date" ).val();
        var withdraw_amount= $( "#<portlet:namespace/>withdraw_amount" ).val();
        var withdraw_balance= $( "#<portlet:namespace/>withdraw_balance" ).val();
        var withdraw_remark= $( "#<portlet:namespace/>withdraw_remark" ).val();
        var researchProjectWithdraw={
            researchProjectId:$("#researchProjectId").val(),
            withdrawItemId:1,
            //description:"desc",
            amountWithdraw:withdraw_amount,
            balance:withdraw_balance,
            remark:withdraw_remark,
            withdrawDateShow:withdraw_date,//"31/03/2015",
            createdBy:"${user.userId}",
            updatedBy:"${user.userId}",
            itemList:$( "#<portlet:namespace/>withdraw_itemList" ).val()
        }
        <portlet:namespace />showDownlod("<portlet:namespace/>withdraw_item_list");
        ResearchAjax.updateResearchProjectWithdraw(researchProjectWithdraw,mode,{
            callback:function(data){
                <portlet:namespace/>render_withdraw_item(data);
                $( "#<portlet:namespace/>element_withdraw" ).hide( "slow");
            }
        });
    }
    <%-- END Withdraw  --%>

    <%-- Start Document  --%>

    function <portlet:namespace />doEditDocumentAjax(researchProjectId,itemList){
        var researchProjectDocument={
            itemList:itemList,
            researchProjectId:researchProjectId,
            documentId:1
        }

        ResearchAjax.findResearchProjectDocumentById(researchProjectDocument,{
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
    function <portlet:namespace />doDeleteDocumentAjax(researchProjectId,itemList){
        var agree=confirm(" ต้องการลบข้อมูลหรือไม่? ");
        if (agree){
            <portlet:namespace />showDownlod("<portlet:namespace/>document_item_list");
            var researchProjectDocument={
                researchProjectId:researchProjectId,
                itemList:itemList
            };

            ResearchAjax.deleteResearchProjectDocument(researchProjectDocument,'researchProject',{
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
    function <portlet:namespace />upload(){
        var mode="add";
        //alert($("#researchProjectId").val())
        //return false;
        var researchProjectDocument={
            researchProjectId:$("#researchProjectId").val(),
            documentId:1,
            description:$("#<portlet:namespace/>document_description").val() ,
            createdBy:"${user.userId}",
            updatedBy:"${user.userId}"
        }
        <portlet:namespace />showDownlod("<portlet:namespace/>document_item_list");





    }

    function <portlet:namespace />doSubmitDocumentAjax(){
        var mode= $("#<portlet:namespace/>document_mode").val();
        //alert($("#researchProjectId").val())
        //return false;
        var researchProjectDocument={
            researchProjectId:$("#researchProjectId").val(),
            documentId:1,
            description:$("#<portlet:namespace/>document_description").val() ,
            createdBy:"${user.userId}",
            updatedBy:"${user.userId}",
            itemList:$( "#<portlet:namespace/>document_itemList" ).val()
        }
        <portlet:namespace />showDownlod("<portlet:namespace/>document_item_list");
        var file = dwr.util.getValue('<portlet:namespace/>uploadFile');
        //alert(file)
        ResearchAjax.uploadResearchProjectDocument(file,researchProjectDocument,"researchProject",mode, {
            callback:function(data){
                <portlet:namespace/>render_document_item(data);
                $( "#<portlet:namespace/>element_document" ).hide( "slow");
            }
        });
        /*
         ResearchAjax.updateResearchProjectDocument(researchProjectDocument,mode,{
         callback:function(data){
        <portlet:namespace/>render_document_item(data);
         $( "#<portlet:namespace/>element_document" ).hide( "slow");
         }
         });
         */
    }
    <%-- END Document  --%>

    function <portlet:namespace/>render_researcher_item(obj){


        var str="<table class=\"table table-hover table-striped table-bordered table-condensed\" border=\"1\" style=\"font-size: 12px\"> "+
                " <thead>    "+
                "  <tr>"+
                "  		<th width=\"5%\" style=\"text-align: center\"><div class=\"th_class\">#</div></th>"+
                    //	"		<th width=\"15%\" style=\"text-align: center\"><div class=\"th_class\">ตำแหน่ง</div></th>"+
                "		<th width=\"25%\" style=\"text-align: center\"><div class=\"th_class\">ชื่อ - สกุล</div></th>  "+
                "		<th width=\"27%\" style=\"text-align: center\"><div class=\"th_class\">ภาควิชา/หน่วยงาน/บริษัท/องค์กร</div></th> "+
                "		<th width=\"14%\" style=\"text-align: center\"><div class=\"th_class\">เป็นหัวหน้าโครงการ</div></th> "+
                "		<th width=\"14%\" style=\"text-align: center\"><div class=\"th_class\">ภาระการทำงาน</div></th> "+
                "		<th width=\"10%\"><div class=\"th_class\"></div></th>  "+
                "	</tr> 		"+
                " </thead> 	"+
                " <tbody>  	";
        if(obj!=null){
            for(var i=0;i<obj.length;i++){
                str=str+" 	<tr style=\"cursor: pointer;\">"+
                        " <td onclick=\"<portlet:namespace />doEditResearcherAjax(\'"+obj[i].researchProjectId+"\',\'"+obj[i].itemList+"\')\" style=\"text-align: left\">"+(i+1)+"</td>"+
                            //" <td style=\"text-align: left\">"+((obj[i].position!=null)?$.trim(obj[i].position.positionName):"")+"</td> "+
                        " <td onclick=\"<portlet:namespace />doEditResearcherAjax(\'"+obj[i].researchProjectId+"\',\'"+obj[i].itemList+"\')\" style=\"text-align: left\">"+((obj[i].position!=null)?$.trim(obj[i].position.positionName):"")+" "+$.trim(obj[i].researcherName)+"</td> "+
                        " <td onclick=\"<portlet:namespace />doEditResearcherAjax(\'"+obj[i].researchProjectId+"\',\'"+obj[i].itemList+"\')\" style=\"text-align: left\">"+((obj[i].organization!=null)?$.trim(obj[i].organization.orgName):"")+"</td> "+
                        " <td onclick=\"<portlet:namespace />doEditResearcherAjax(\'"+obj[i].researchProjectId+"\',\'"+obj[i].itemList+"\')\" style=\"text-align: center\">";
                if($.trim(obj[i].isprojectLeader)=="T"){
                    str=str+"เป็น" ;
                }else{
                    str=str+"ไม่เป็น" ;
                }
                str=str+" </td> " +
                        " <td onclick=\"<portlet:namespace />doEditResearcherAjax(\'"+obj[i].researchProjectId+"\',\'"+obj[i].itemList+"\')\" style=\"text-align: center\">"+$.trim(obj[i].workLoadRatio)+"</td> "+
                        " <td style=\"text-align: center\">"+
                        "  <button onclick=\"<portlet:namespace />doEditResearcherAjax(\'"+obj[i].researchProjectId+"\',\'"+obj[i].itemList+"\')\" class=\"btn btn-small\" type=\"button\">แก้ใข</button> "+
                        "  <button onclick=\"<portlet:namespace />doDeleteResearcherAjax(\'"+obj[i].researchProjectId+"\',\'"+obj[i].itemList+"\')\" class=\"btn btn-danger btn-small\" type=\"button\">ลบ</button> "+
                        " </td>  "+
                        " </tr> ";
            }
        }
        str=str+"</tbody>"+
                "</table>";
        $("#<portlet:namespace/>researcher_item_list").html(str);

    }

    function <portlet:namespace/>render_progress_item(obj){
        var str="<table class=\"table table-hover table-striped table-bordered table-condensed\" border=\"1\" style=\"font-size: 12px\"> "+
                " <thead>    "+
                "  <tr>"+
                "  		<th width=\"5%\" style=\"text-align: center\"><div class=\"th_class\">#</div></th>"+
                "		<th width=\"8%\" style=\"text-align: center\"><div class=\"th_class\">Due Date</div></th>"+
                "		<th width=\"8%\" style=\"text-align: center\"><div class=\"th_class\">Submit Date</div></th>  "+
                "		<th width=\"5%\" style=\"text-align: center\"><div class=\"th_class\">Progress(%)</div></th> "+
                "		<th width=\"36%\" style=\"text-align: center\"><div class=\"th_class\">Remark</div></th> "+
                "		<th width=\"8%\"><div class=\"th_class\"></div></th>  "+
                "	</tr> 		"+
                " </thead> 	"+
                " <tbody>  	";
        if(obj!=null){
            for(var i=0;i<obj.length;i++){
                str=str+" 	<tr style=\"cursor: pointer;\">"+
                        " <td onclick=\"<portlet:namespace />doEditProgressAjax(\'"+obj[i].researchProjectId+"\',\'"+obj[i].itemList+"\')\" style=\"text-align: left\">"+(i+1)+"</td>"+
                        " <td onclick=\"<portlet:namespace />doEditProgressAjax(\'"+obj[i].researchProjectId+"\',\'"+obj[i].itemList+"\')\" style=\"text-align: center\">"+$.trim(obj[i].dueDateShow)+"</td> "+
                        " <td onclick=\"<portlet:namespace />doEditProgressAjax(\'"+obj[i].researchProjectId+"\',\'"+obj[i].itemList+"\')\" style=\"text-align: center\">"+$.trim(obj[i].submitDateShow)+"</td> "+
                        " <td onclick=\"<portlet:namespace />doEditProgressAjax(\'"+obj[i].researchProjectId+"\',\'"+obj[i].itemList+"\')\" style=\"text-align: center\">"+$.trim(obj[i].progressPercentage)+"</td> "+
                        " <td onclick=\"<portlet:namespace />doEditProgressAjax(\'"+obj[i].researchProjectId+"\',\'"+obj[i].itemList+"\')\" style=\"text-align: left\">"+$.trim(obj[i].remark)+"</td> "+
                        " <td style=\"text-align: center\">"+
                        "  <button onclick=\"<portlet:namespace />doEditProgressAjax(\'"+obj[i].researchProjectId+"\',\'"+obj[i].itemList+"\')\" class=\"btn btn-small\" type=\"button\">แก้ใข</button> "+
                        "  <button onclick=\"<portlet:namespace />doDeleteProgressAjax(\'"+obj[i].researchProjectId+"\',\'"+obj[i].itemList+"\')\" class=\"btn btn-danger btn-small\" type=\"button\">ลบ</button> "+
                        " </td>  "+
                        " </tr> ";
            }
        }
        str=str+"</tbody>"+
                "</table>";
        $("#<portlet:namespace/>progress_item_list").html(str);

    }

    function <portlet:namespace/>render_payment_item(obj){
        var str="<table class=\"table table-hover table-striped table-bordered table-condensed\" border=\"1\" style=\"font-size: 12px\"> "+
                " <thead>    "+
                "  <tr>"+
                "  		<th width=\"5%\" style=\"text-align: center\"><div class=\"th_class\">#</div></th>"+
                "		<th width=\"8%\" style=\"text-align: center\"><div class=\"th_class\">Received Date</div></th>"+
                "		<th width=\"21%\" style=\"text-align: center\"><div class=\"th_class\">Received NO</div></th>  "+
                "		<th width=\"20%\" style=\"text-align: center\"><div class=\"th_class\">Amount Received</div></th> "+
                "		<th width=\"8%\"><div class=\"th_class\"></div></th>  "+
                "	</tr> 		"+
                " </thead> 	"+
                " <tbody>  	";
        if(obj!=null){
            for(var i=0;i<obj.length;i++){
                str=str+" 	<tr style=\"cursor: pointer;\">"+
                        " <td onclick=\"<portlet:namespace />doEditPaymentAjax(\'"+obj[i].researchProjectId+"\',\'"+obj[i].itemList+"\')\" style=\"text-align: left\">"+(i+1)+"</td>"+
                        " <td onclick=\"<portlet:namespace />doEditPaymentAjax(\'"+obj[i].researchProjectId+"\',\'"+obj[i].itemList+"\')\" style=\"text-align: center\">"+$.trim(obj[i].receivedDateShow)+"</td> "+
                        " <td onclick=\"<portlet:namespace />doEditPaymentAjax(\'"+obj[i].researchProjectId+"\',\'"+obj[i].itemList+"\')\" style=\"text-align: left\">"+$.trim(obj[i].receiptNo)+"</td> "+
                        " <td onclick=\"<portlet:namespace />doEditPaymentAjax(\'"+obj[i].researchProjectId+"\',\'"+obj[i].itemList+"\')\" style=\"text-align: right\">"+$.trim(obj[i].amountReceivedShow)+"</td> "+
                        " <td style=\"text-align: center\">"+
                        "  <button onclick=\"<portlet:namespace />doEditPaymentAjax(\'"+obj[i].researchProjectId+"\',\'"+obj[i].itemList+"\')\" class=\"btn btn-small\" type=\"button\">แก้ใข</button> "+
                        "  <button onclick=\"<portlet:namespace />doDeletePaymentAjax(\'"+obj[i].researchProjectId+"\',\'"+obj[i].itemList+"\')\" class=\"btn btn-danger btn-small\" type=\"button\">ลบ</button> "+
                        " </td>  "+
                        " </tr> ";
            }
        }
        str=str+"</tbody>"+
                "</table>";
        $("#<portlet:namespace/>payment_item_list").html(str);

    }

    function <portlet:namespace/>render_withdraw_item(obj){
        var str="<table class=\"table table-hover table-striped table-bordered table-condensed\" border=\"1\" style=\"font-size: 12px\"> "+
                " <thead>    "+
                "  <tr>"+
                "  		<th width=\"5%\" style=\"text-align: center\"><div class=\"th_class\">#</div></th>"+
                "		<th width=\"10%\" style=\"text-align: center\"><div class=\"th_class\">Withdraw Date</div></th>"+
                "		<th width=\"12%\" style=\"text-align: center\"><div class=\"th_class\">Amount Withdraw</div></th>  "+
                "		<th width=\"10%\" style=\"text-align: center\"><div class=\"th_class\">Balance</div></th> "+
                "		<th width=\"34%\" style=\"text-align: center\"><div class=\"th_class\">Remark</div></th> "+
                "		<th width=\"8%\"><div class=\"th_class\"></div></th>  "+
                "	</tr> 		"+
                " </thead> 	"+
                " <tbody>  	";

        if(obj!=null){
            for(var i=0;i<obj.length;i++){
                str=str+" 	<tr style=\"cursor: pointer;\">"+
                        " <td onclick=\"<portlet:namespace />doEditWithdrawAjax(\'"+obj[i].researchProjectId+"\',\'"+obj[i].itemList+"\')\" style=\"text-align: left\">"+(i+1)+"</td>"+
                        " <td onclick=\"<portlet:namespace />doEditWithdrawAjax(\'"+obj[i].researchProjectId+"\',\'"+obj[i].itemList+"\')\" style=\"text-align: center\">"+$.trim(obj[i].withdrawDateShow)+"</td> "+
                        " <td onclick=\"<portlet:namespace />doEditWithdrawAjax(\'"+obj[i].researchProjectId+"\',\'"+obj[i].itemList+"\')\" style=\"text-align: right\">"+$.trim(obj[i].amountWithdrawShow)+"</td> "+
                        " <td onclick=\"<portlet:namespace />doEditWithdrawAjax(\'"+obj[i].researchProjectId+"\',\'"+obj[i].itemList+"\')\" style=\"text-align: right\">"+$.trim(obj[i].balanceShow)+"</td> "+
                        " <td onclick=\"<portlet:namespace />doEditWithdrawAjax(\'"+obj[i].researchProjectId+"\',\'"+obj[i].itemList+"\')\" style=\"text-align: left\">"+$.trim(obj[i].remark)+"</td> "+
                        " <td style=\"text-align: center\">"+
                        "  <button onclick=\"<portlet:namespace />doEditWithdrawAjax(\'"+obj[i].researchProjectId+"\',\'"+obj[i].itemList+"\')\" class=\"btn btn-small\" type=\"button\">แก้ใข</button> "+
                        "  <button onclick=\"<portlet:namespace />doDeleteWithdrawAjax(\'"+obj[i].researchProjectId+"\',\'"+obj[i].itemList+"\')\" class=\"btn btn-danger btn-small\" type=\"button\">ลบ</button> "+
                        " </td>  "+
                        " </tr> ";
            }
        }
        str=str+"</tbody>"+
                "</table>";
        $("#<portlet:namespace/>withdraw_item_list").html(str);

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
                        " <td onclick=\"<portlet:namespace />doEditDocumentAjax(\'"+obj[i].researchProjectId+"\',\'"+obj[i].itemList+"\')\" style=\"text-align: left\">"+(i+1)+"</td>";
                if($.trim(obj[i].fileName).length>0){
                    str=str+"<td onclick=\"<portlet:namespace />doEditDocumentAjax(\'"+obj[i].researchProjectId+"\',\'"+obj[i].itemList+"\')\" style=\"text-align: left\">"+$.trim(obj[i].description)+"  [ <a style=\"text-decoration: underline;\" onclick=\'<portlet:namespace />downloadFile(\""+obj[i].itemList+"\",\""+obj[i].researchProjectId+"\")\'>"+obj[i].fileName+"</a>]</td> ";
                }else{
                    str=str+"<td onclick=\"<portlet:namespace />doEditDocumentAjax(\'"+obj[i].researchProjectId+"\',\'"+obj[i].itemList+"\')\" style=\"text-align: left\">"+$.trim(obj[i].description)+"</td> ";
                }
                str=str+""+
                        " <td style=\"text-align: center\">"+
                        "  <button onclick=\"<portlet:namespace />doEditDocumentAjax(\'"+obj[i].researchProjectId+"\',\'"+obj[i].itemList+"\')\" class=\"btn btn-small\" type=\"button\">แก้ใข</button> "+
                        "  <button onclick=\"<portlet:namespace />doDeleteDocumentAjax(\'"+obj[i].researchProjectId+"\',\'"+obj[i].itemList+"\')\" class=\"btn btn-danger btn-small\" type=\"button\">ลบ</button> "+
                        " </td>  "+
                        " </tr> ";
            }
        }
        str=str+"</tbody>"+
                "</table>";
        $("#<portlet:namespace/>document_item_list").html(str);

    }


    function <portlet:namespace/>displayResearcher(mode,id){
        $("#<portlet:namespace/>researcher_researcherId").val("");
        $("#<portlet:namespace/>researcher_researcherName").val("");
        $("#<portlet:namespace/>researcher_positionId").val("");
        $("#<portlet:namespace/>researcher_organizationId").val("");
        $("#researcherId_assign_autocomplete").prop("readonly",false)
        $("#researcherId_assign_autocomplete").val("");
        $("#researcherId_assign_autocomplete").css("background-color","rgb(250, 250, 198)");

        $("#researcherId_assignShow").html("");
        $("#<portlet:namespace/>researcher_position").val("");
        $("#<portlet:namespace/>researcher_organization").val("");
        $("#<portlet:namespace/>researcher_isprojectLeader").prop("checked",false);
        $("#<portlet:namespace/>researcher_workLoadRatio").val("");
        if(mode=='edit'){

        }else{
            $( "#<portlet:namespace/>researcher_mode" ).val("add");
            $( "#<portlet:namespace/>element_researcher" ).show( "slow");
            $( "#<portlet:namespace/>researcherListAll" ).show( "slow");
        }
    }
    function <portlet:namespace/>displayProgress(mode,id){
        $( "#<portlet:namespace/>progress_dueDate" ).val('');
        $( "#<portlet:namespace/>progress_remark" ).val('');
        $( "#<portlet:namespace/>progress_progressPercentage" ).val('');
        $( "#<portlet:namespace/>progress_submitDate" ).val('');
        $( "#<portlet:namespace/>progress_itemList" ).val('');
        $( "#<portlet:namespace/>progress_progressItemId" ).val('1');
        if(mode=='edit'){

        }else{
            $( "#<portlet:namespace/>progress_mode" ).val("add");
            $( "#<portlet:namespace/>element_progress" ).show( "slow");
        }
    }
    function <portlet:namespace/>displayPayment(mode,id){
        $( "#<portlet:namespace/>payment_receivedDate" ).val('');
        $( "#<portlet:namespace/>payment_receiptNo" ).val('');
        $( "#<portlet:namespace/>payment_amountReceived" ).val('');
        $( "#<portlet:namespace/>payment_itemList" ).val('');
        $( "#<portlet:namespace/>payment_progressItemId" ).val('1');

        if(mode=='edit'){

        }else{
            $( "#<portlet:namespace/>payment_mode" ).val("add");
            $( "#<portlet:namespace/>element_payment" ).show( "slow");
        }
    }
    function <portlet:namespace/>displayWithdraw(mode,id){
        $( "#<portlet:namespace/>withdraw_date" ).val('');
        $( "#<portlet:namespace/>withdraw_amount" ).val('');
        $( "#<portlet:namespace/>withdraw_balance" ).val('');
        $( "#<portlet:namespace/>withdraw_remark" ).val('');

        $( "#<portlet:namespace/>withdraw_itemList" ).val("");
        $( "#<portlet:namespace/>withdraw_withdrawItemId" ).val("1");


        if(mode=='edit'){

        }else{
            $( "#<portlet:namespace/>withdraw_mode" ).val("add")
            $( "#<portlet:namespace/>element_withdraw" ).show( "slow");
        }
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

    function <portlet:namespace/>exportProofFile(type){
        //return false;
        //alert('${research_group_resource_get_byid}')
        $.ajax({
            url: '${research_group_resource_get_byid}' ,
            // type: 'POST',
            // datatype:'json',
            // data: { researchGroupId: id },
            success: function(data){
                var src;
                //alert(data)
                if(type=='participation'){
                    src=data.proofParticipationResource
                }else
                    src=data.proofAcademicResource
                //alert(type)

                //	alert(src)
                var div = document.createElement("div");
                document.body.appendChild(div);
                div.innerHTML = "<iframe width='0' height='0' scrolling='no' frameborder='0' src='" + src + "'></iframe>";

            }
        });


    }

    <%-- START DocAssign  --%>
    function <portlet:namespace />doDeleteDocAssignMappingAjax(refId,refType,userId){
        //alert(researchProjectId)
        var agree=confirm(" ต้องการลบข้อมูลหรือไม่? ");
        if (agree){
            <portlet:namespace />showDownlod("<portlet:namespace/>doctype_assign_list");
            var docAssignMapping={
                refId:refId,
                refType:refType,
                userId:userId
            }
            ResearchAjax.deleteDocAssignMapping(docAssignMapping,{
                callback:function(data){
                    <portlet:namespace/>render_docAssign_item(data);
                    //$( "#<portlet:namespace/>element_doctype" ).hide( "slow");
                }
            });
            return true ;
        }
        else{
            return false ;
        }


    }
    function <portlet:namespace />doAddDocAssignMappingAjax(refId,refType,userId){
        var mode="add";

        var docAssignMapping={
            refId:refId,
            refType:refType,
            userId:userId,
            createdBy:"${user.userId}",
            updatedBy:"${user.userId}"
        }

        // alert(researchProjectCopyrightCreator)
        <portlet:namespace />showDownlod("<portlet:namespace/>doctype_assign_list");
        ResearchAjax.saveDocAssignMapping(docAssignMapping,mode,{
            callback:function(data){
                //alert(data+"xxx")
                <portlet:namespace/>render_docAssign_item(data);
                $( "#docs_assignShow" ).hide( "slow");
                $("#docs_assign_autocomplete").val('');
                $("#docsAssign").val('');

            }
        });
    }
    function <portlet:namespace/>render_docAssign_item(obj){
        var str="<table class=\"table table-hover table-striped table-bordered table-condensed\" border=\"0\" style=\"width:30%;font-size: 12px\"> ";
        obj=obj.resultListObj;
        if(obj!=null){

            for(var i=0;i<obj.length;i++){
                str=str+" 	<tr style=\"cursor: pointer;\">"+
                        " <td width=\"5%\" style=\"text-align: left\">"+(i+1)+"</td>"+
                        " <td style=\"text-align: left\">"+obj[i].userPortal.emailAddress+"</td>"+
                        " <td width=\"5%\"  style=\"text-align: center\">"+
                        "<button onclick=\'<portlet:namespace />doDeleteDocAssignMappingAjax(\""+obj[i].refId+"\",\""+obj[i].refType+"\",\""+obj[i].userId+"\")\' class=\"btn btn-danger btn-small\" type=\"button\">ลบ</button>"+
                        "</td>"+
                        " </tr> ";
            }
        }

        str=str+"</table>";
        $("#<portlet:namespace/>doctype_assign_list").html(str);

    }

    function <portlet:namespace />downloadFile(ref1,ref2){
        ResearchAjax.downloadFile(ref1,ref2,"researchProject", function(data) {
            dwr.engine.openInDownload(data);
        });
    }
    <%-- END DocAssign  --%>

    // var file = $('#uploadFile');

    function <portlet:namespace />count(s1, letter) {
        var match=s1.match( new RegExp(letter,'g') );
        if(match!=null)
            return match.length
        else
            return 0;
        //return s1.match( new RegExp(letter,'g') ).length;
    }
    function <portlet:namespace />validateDigitTwoDigit(sDigit) {
        return (sDigit.indexOf('\.')!=sDigit.length-2);

    }
    // count('Yes. I want. to. place a. lot of. dots.','\\.'); //=> 6
    function <portlet:namespace />validateDigit(sDigit) {
        //var filter = /^(\d+(?:[\.]\d{2})?)$/;
        var count=<portlet:namespace />count(sDigit,'\\.');
        //alert(sDigit.indexOf('\.'))
        var filter =/^[0-9.]+$/
        //	var filter=/^\d+(\.\d\d)?$/;
        // var filter=/^\d$/;
        if (filter.test(sDigit)) {
            if(count>1 || sDigit.indexOf('\.')==0 || sDigit.indexOf('\.')==sDigit.length)
                return false;
            else
                return true;
        }
        else {
            return false;
        }
    }
    function <portlet:namespace />validateDigitOnly(sDigit) {

        //alert(sDigit.indexOf('\.'))
        var filter =/^[0-9]+$/
        //	var filter=/^\d+(\.\d\d)?$/;
        // var filter=/^\d$/;
        if (filter.test(sDigit)) {
            return true;
        }
        else {
            return false;
        }
    }
    function <portlet:namespace />validateDigit2(sDigit) {
        var filter = /^\d*[0-9](|.\d*[0-9]|,\d*[0-9])?$/;
        // var filter=/^\d$/;
        if (filter.test(sDigit)) {
            return true;
        }
        else {
            return false;
        }
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
    function <portlet:namespace />doSubmitForm(status){
        var budgetYear=$('input[id="researchProjectM.budgetYear"]').val();
        //   var dInput = this.value;
        // alert(dInput.length)
        if ($.trim(budgetYear).length > 0 && !<portlet:namespace />validateYear(budgetYear)) {
            //alert('กรอก  ปีงบประมาณให้ถูกต้อง.');
            $('input[id="researchProjectM.budgetYear"]').val("");
            $('input[id="researchProjectM.budgetYear"]').focus();
            return false;
        }



        $('input[id="researchProjectM.docType"]').val(status);
        var form = document.forms['researchProjectForm'];
        form.submit();
    }
    function <portlet:namespace />showDownlod(element_){
        //<c:url value='/resources/images/ajax_loading.gif'/>loading.gif
        var download_str='<table border="0" width="100%"><tr><td  align="center"><img style="width:60px;" src="<c:url value="/resources/images/loading.gif"/>"/></td></tr></table>';
        $("#"+element_).html(download_str);

    }

    function IsNumeric(e) {
        var keyCode = e.which ? e.which : e.keyCode
        var ret = ((keyCode >= 48 && keyCode <= 57));
        // alert(ret)
        if(!ret){
            $("#error").html("Number Only.");
            $("#error").show();
        }
        else{
            $("#error").html("");
            $("#error").hide();
        }
        //  document.getElementById("error").style.display = ret ? "none" : "inline";

        return ret;
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
        else if(f_name=='fundingResource')
            <portlet:namespace />fundingResourcePopup($("#<portlet:namespace />keySearch_"+f_name).val(),false,pageNo)
        else  if(f_name=='docsAssign')
            <portlet:namespace />docsAssignPopup($("#<portlet:namespace />keySearch_"+f_name).val(),false,pageNo)
        else  if(f_name=='researcher')
            <portlet:namespace />researcherPopup($("#<portlet:namespace />keySearch_"+f_name).val(),false,pageNo)

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
                $('input[id="researchProjectM.researchGroupId"]').val(objID);
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
                $('input[id="researchProjectM.organizationId"]').val(objID);
                $("#organizationId_assign_autocomplete").val(data.orgName);


                bootbox.hideAll();
            }
        });
    }
    <%-- end organization Popup --%>

    <%-- start organization Popup --%>
    function <portlet:namespace />fundingResourcePopup(keySearch,init,pageNo){
        var keyBox=" <div>"+
                "<input type=\"text\"  style=\"display:inline;width:250px\" class=\"form-control\" "+
                " id=\"<portlet:namespace />keySearch_fundingResource\" onkeypress=\"<portlet:namespace />chk(\'fundingResource\',event)\" aria-describedby=\"inputSuccess4Status\" value=\""+keySearch+"\"/>"+
                "<button onclick=\"<portlet:namespace />doSearchBox('fundingResource',1)\" type=\"button\" class=\"btn btn-success\" style=\"margin-top:-8px;margin-left:5px;\">Search</button>"+
                "</div>";
        var str="<table class=\"table table-hover table-striped table-bordered table-condensed\" border=\"1\" style=\"font-size: 12px\"> "+
                " <thead>    "+
                "  <tr>"+
                    //	"  		<th width=\"20%\" style=\"text-align: center\"><div class=\"th_class\">รหัสกลุ่มวิจัย</div></th>"+
                    //	"		<th width=\"40%\" style=\"text-align: center\"><div class=\"th_class\">ชื่อกลุ่มวิจัยภาษาไทย</div></th>  "+
                "		<th width=\"10%\" style=\"text-align: center\"><div class=\"th_class\">รหัสแหล่งทุน</div></th> "+
                "		<th width=\"10%\" style=\"text-align: center\"><div class=\"th_class\">รหัสประเภทแหล่งทุน</div></th> "+
                "		<th width=\"30%\" style=\"text-align: center\"><div class=\"th_class\">ชื่อแหล่งทุน(T)</div></th> "+
                "		<th width=\"30%\" style=\"text-align: center\"><div class=\"th_class\">ชื่อแหล่งทุน(E)</div></th> "+
                "		<th width=\"10%\" style=\"text-align: center\"><div class=\"th_class\">ชื่อย่อแหล่งทุน</div></th> "+
                "	</tr> 		"+
                " </thead><tbody>";

        var pageObj={
            pageSize:PAGE_SIZE_POPUP,
            pageNo:pageNo
        }
        var fundingResourceM={
            keySearch:keySearch,
            paging:pageObj
        }
        ResearchAjax.getFundingResourceList(fundingResourceM,{
            callback:function(data){
                var maxRow=data.maxRow;
                var lastpage=data.lastpage;
                data=data.resultListObj;
                if(data!=null && data.length>0){
                    for(var i=0;i<data.length;i++){
                        str=str+"<tr  onclick=\"<portlet:namespace />selectfundingResource('"+data[i].fundingResourceId+"')\" style=\"cursor: pointer;\">"+
                                "<td style=\"text-align: left\"  >"+data[i].fundingResourceCode+"</td>"+
                                "<td style=\"text-align: left\"  >"+data[i].fundingResourceType.frtCode+" / +"+data[i].fundingResourceType.frtName+"</td>"+
                                "<td style=\"text-align: left\"  >"+((data[i].frNameThai!=null)?(data[i].frNameThai):(""))+"</td>"+
                                "<td style=\"text-align: left\"  >"+((data[i].frNameEng!=null)?(data[i].frNameEng):(""))+"</td>"+
                                "<td style=\"text-align: left\"  >"+((data[i].frShortName!=null)?(data[i].frShortName):(""))+"</td>"+
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
                    pagingStr=pagingStr+"<li><a style=\"cursor: pointer;\" onclick='<portlet:namespace />doSearchBox(\"fundingResource\","+(pageNo-1)+")'>Prev</a></li>";

                for(var j=pageStart;j<=pageEnd;j++){
                    if(pageNo==j){
                        pagingStr=pagingStr+"<li><a class=\"active\" style=\"background-color: #ddd\" >"+j+"</a></li>";
                    }
                    else{
                        pagingStr=pagingStr+"<li><a class=\"active\" style=\"cursor: pointer;\" onclick='<portlet:namespace />doSearchBox(\"fundingResource\","+(j)+")'>"+j+"</a></li>";
                    }
                }
                if(pageEnd<lastpage){
                    pagingStr=pagingStr+"<li><a class=\"active\" style=\"cursor: pointer;\" onclick='<portlet:namespace />doSearchBox(\"fundingResource\","+(pageEnd)+")'>...</a></li>";
                    pagingStr=pagingStr+"<li><a class=\"active\" style=\"cursor: pointer;\" onclick='<portlet:namespace />doSearchBox(\"fundingResource\","+(lastpage)+")'>"+lastpage+"</a></li>";
                }
                if(pageNo!=lastpage){
                    pagingStr=pagingStr+"<li><a style=\"cursor: pointer;\" onclick='<portlet:namespace />doSearchBox(\"fundingResource\","+((pageNo<lastpage)?(pageNo+1):(lastpage))+")'>Next</a></li>";
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
                    bootbox.dialog(keyBox+"<div id=\"<portlet:namespace />fundingResourcePopupTable\">"+str+pagingStr+"</div>",[{
                        "label" : "Close",
                        "class" : "btn-danger",
                        "callback": function() {
                        }

                    }]);
                }else{
                    $("#<portlet:namespace />fundingResourcePopupTable").html(str+pagingStr);
                }
            }
        });
    }
    function <portlet:namespace />selectfundingResource(objID){
        ResearchAjax.findFundingResourceById(objID,{
            callback:function(data){
                $('input[id="researchProjectM.fundingResourceId"]').val(objID);
                $("#fundingResourceId_assign_autocomplete").val(data.frNameThai);


                bootbox.hideAll();
            }
        });
    }
    <%-- end researcher Popup --%>

    <%-- start docsAssign Popup --%>
    function <portlet:namespace />docsAssignPopup(keySearch,init,pageNo){
        var keyBox=" <div>"+
                "<input type=\"text\"  style=\"display:inline;width:250px\" class=\"form-control\" "+
                " id=\"<portlet:namespace />keySearch_docsAssign\" onkeypress=\"<portlet:namespace />chk(\'docsAssign\',event)\" aria-describedby=\"inputSuccess4Status\" value=\""+keySearch+"\"/>"+
                "<button onclick=\"<portlet:namespace />doSearchBox('docsAssign',1)\" type=\"button\" class=\"btn btn-success\" style=\"margin-top:-8px;margin-left:5px;\">Search</button>"+
                "</div>";
        var str="<table class=\"table table-hover table-striped table-bordered table-condensed\" border=\"1\" style=\"font-size: 12px\"> "+
                " <thead>    "+
                "  <tr>"+
                    //	"  		<th width=\"20%\" style=\"text-align: center\"><div class=\"th_class\">รหัสกลุ่มวิจัย</div></th>"+
                    //	"		<th width=\"40%\" style=\"text-align: center\"><div class=\"th_class\">ชื่อกลุ่มวิจัยภาษาไทย</div></th>  "+
                "		<th width=\"100%\" style=\"text-align: center\"><div class=\"th_class\">ชื่อ นามสกุล</div></th> "+

                "	</tr> 		"+
                " </thead><tbody>";

        var pageObj={
            pageSize:PAGE_SIZE_POPUP,
            pageNo:pageNo
        }
        var docsAssignM={
            keySearch:keySearch,
            paging:pageObj
        }
        ResearchAjax.getUserPortalList(docsAssignM,{
            callback:function(data){
                var maxRow=data.maxRow;
                var lastpage=data.lastpage;
                data=data.resultListObj;
                if(data!=null && data.length>0){
                    for(var i=0;i<data.length;i++){
                        str=str+"<tr  onclick=\"<portlet:namespace />selectdocsAssign('"+data[i].userId+"')\" style=\"cursor: pointer;\">"+
                                "<td style=\"text-align: left\"  >"+data[i].emailAddress +" [ "+ data[i].firstName+" "+data[i].lastName+"]"+"</td>"
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
                    pagingStr=pagingStr+"<li><a style=\"cursor: pointer;\" onclick='<portlet:namespace />doSearchBox(\"docsAssign\","+(pageNo-1)+")'>Prev</a></li>";

                for(var j=pageStart;j<=pageEnd;j++){
                    if(pageNo==j){
                        pagingStr=pagingStr+"<li><a class=\"active\" style=\"background-color: #ddd\" >"+j+"</a></li>";
                    }
                    else{
                        pagingStr=pagingStr+"<li><a class=\"active\" style=\"cursor: pointer;\" onclick='<portlet:namespace />doSearchBox(\"docsAssign\","+(j)+")'>"+j+"</a></li>";
                    }
                }
                if(pageEnd<lastpage){
                    pagingStr=pagingStr+"<li><a class=\"active\" style=\"cursor: pointer;\" onclick='<portlet:namespace />doSearchBox(\"docsAssign\","+(pageEnd)+")'>...</a></li>";
                    pagingStr=pagingStr+"<li><a class=\"active\" style=\"cursor: pointer;\" onclick='<portlet:namespace />doSearchBox(\"docsAssign\","+(lastpage)+")'>"+lastpage+"</a></li>";
                }
                if(pageNo!=lastpage){
                    pagingStr=pagingStr+"<li><a style=\"cursor: pointer;\" onclick='<portlet:namespace />doSearchBox(\"docsAssign\","+((pageNo<lastpage)?(pageNo+1):(lastpage))+")'>Next</a></li>";
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
                    bootbox.dialog(keyBox+"<div id=\"<portlet:namespace />docsAssignPopupTable\">"+str+pagingStr+"</div>",[{
                        "label" : "Close",
                        "class" : "btn-danger",
                        "callback": function() {
                        }

                    }]);
                }else{
                    $("#<portlet:namespace />docsAssignPopupTable").html(str+pagingStr);
                }
            }
        });
    }
    function <portlet:namespace />selectdocsAssign(objID){
        <portlet:namespace />doAddDocAssignMappingAjax($("#researchProjectId").val(),"RESEARCH",objID);
        bootbox.hideAll();
    }
    <%-- end docsAssign Popup --%>


    <%-- start researcher Popup --%>
    function <portlet:namespace />researcherPopup(keySearch,init,pageNo){
        var keyBox=" <div>"+
                "<input type=\"text\"  style=\"display:inline;width:250px\" class=\"form-control\" "+
                " id=\"<portlet:namespace />keySearch_researcher\" onkeypress=\"<portlet:namespace />chk(\'researcher\',event)\" aria-describedby=\"inputSuccess4Status\" value=\""+keySearch+"\"/>"+
                "<button onclick=\"<portlet:namespace />doSearchBox('researcher',1)\" type=\"button\" class=\"btn btn-success\" style=\"margin-top:-8px;margin-left:5px;\">Search</button>"+
                "</div>";
        var str="<table class=\"table table-hover table-striped table-bordered table-condensed\" border=\"1\" style=\"font-size: 12px\"> "+
                " <thead>    "+
                "  <tr>"+
                "		<th width=\"50%\" style=\"text-align: center\"><div class=\"th_class\">ชื่อนักวิจัย/นามสกุล(T)</div></th> "+
                "		<th width=\"50%\" style=\"text-align: center\"><div class=\"th_class\">ชื่อนักวิจัย/นามสกุล(E)</div></th> "+
                "	</tr> 		"+
                " </thead><tbody>";

        var pageObj={
            pageSize:PAGE_SIZE_POPUP,
            pageNo:pageNo
        }
        var researcherM={
            keySearch:keySearch,
            paging:pageObj
        }
        ResearchAjax.getResearcherList(researcherM,{
            callback:function(data){
                var maxRow=data.maxRow;
                var lastpage=data.lastpage;
                data=data.resultListObj;
                if(data!=null && data.length>0){
                    for(var i=0;i<data.length;i++){
                        str=str+"<tr  onclick=\"<portlet:namespace />selectresearcher('"+data[i].researcherId+"')\" style=\"cursor: pointer;\">"+
                                "<td style=\"text-align: left\"  >"+data[i].nameThai+" "+data[i].surnameThai+"</td>"+
                                "<td style=\"text-align: left\"  >"+data[i].nameEng+" "+data[i].surnameEng+"</td>"+
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
                    pagingStr=pagingStr+"<li><a style=\"cursor: pointer;\" onclick='<portlet:namespace />doSearchBox(\"researcher\","+(pageNo-1)+")'>Prev</a></li>";

                for(var j=pageStart;j<=pageEnd;j++){
                    if(pageNo==j){
                        pagingStr=pagingStr+"<li><a class=\"active\" style=\"background-color: #ddd\" >"+j+"</a></li>";
                    }
                    else{
                        pagingStr=pagingStr+"<li><a class=\"active\" style=\"cursor: pointer;\" onclick='<portlet:namespace />doSearchBox(\"researcher\","+(j)+")'>"+j+"</a></li>";
                    }
                }
                if(pageEnd<lastpage){
                    pagingStr=pagingStr+"<li><a class=\"active\" style=\"cursor: pointer;\" onclick='<portlet:namespace />doSearchBox(\"researcher\","+(pageEnd)+")'>...</a></li>";
                    pagingStr=pagingStr+"<li><a class=\"active\" style=\"cursor: pointer;\" onclick='<portlet:namespace />doSearchBox(\"researcher\","+(lastpage)+")'>"+lastpage+"</a></li>";
                }
                if(pageNo!=lastpage){
                    pagingStr=pagingStr+"<li><a style=\"cursor: pointer;\" onclick='<portlet:namespace />doSearchBox(\"researcher\","+((pageNo<lastpage)?(pageNo+1):(lastpage))+")'>Next</a></li>";
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
                    bootbox.dialog(keyBox+"<div id=\"<portlet:namespace />researcherPopupTable\">"+str+pagingStr+"</div>",[{
                        "label" : "Close",
                        "class" : "btn-danger",
                        "callback": function() {
                        }

                    }]);
                }else{
                    $("#<portlet:namespace />researcherPopupTable").html(str+pagingStr);
                }
            }
        });
    }
    function <portlet:namespace />selectresearcher(objID){
        ResearchAjax.findResearcherById(objID,{
            callback:function(data){
                $('input[id="researchProjectM.researcherId"]').val(objID);
                $("#researcherId_assign_autocomplete").val(data.nameThai+" "+data.surnameThai);

                $('#<portlet:namespace/>researcher_researcherId').val(objID);
                $('#<portlet:namespace/>researcher_researcherName').val(data.nameThai+" "+data.surnameThai);
                $('#<portlet:namespace/>researcher_positionId').val((data.position!=null && data.position.positionId)?data.position.positionId:"");
                $('#<portlet:namespace/>researcher_organizationId').val((data.organization!=null && data.organization.organizationId)?data.organization.organizationId:"");

                $('#<portlet:namespace/>researcher_position').val((data.position!=null && data.position.positionName)?data.position.positionName:"");
                $('#<portlet:namespace/>researcher_organization').val((data.organization!=null && data.organization.orgName)?data.organization.orgName:"");

                bootbox.hideAll();
            }
        });
    }
    <%-- end researcher Popup --%>

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
</script>
</body>
</html>
