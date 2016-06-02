<%@ include file="/WEB-INF/jsp/include.jsp" %>
<%@ page contentType="text/html; charset=utf-8" %>
<%@page import="javax.portlet.PortletURL" %>
<script src="<c:url value="/dwr/engine.js"/>"></script>
<script src="<c:url value="/dwr/util.js"/>"></script>
<script src="<c:url value="/dwr/interface/ResearchAjax.js"/>"></script>
<c:url var="url" value="/"/>
<c:set var="ns"><portlet:namespace/></c:set>
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
    <link href="<c:url value='/resources/css/smoothness/jquery-ui-1.9.1.custom.min.css'/>" type="text/css"
          rel="stylesheet"/>
    <link rel="stylesheet" href="<c:url value="/resources/css/jquery.fileupload.css"/>" type="text/css"/>
    <style type="text/css">
        #breadcrumbs {
            display: none;
        }

        .ui-autocomplete-loading {
            background: white url('<c:url value="/resources/css/smoothness/images/ui-anim_basic_16x16.gif"/>') right center no-repeat;
        }

        .aoe_small {
            width: 500px !important;
            margin-left: -250px
        }

        /* .aoe_width{width: 1000px !important;margin-left:-500px} */
        .aoe_width {
            width: 1000px !important;
        }

        .aui .modal {
            left: 30%
        }
    </style>

</head>

<body>
<form:form id="copyrightForm" modelAttribute="copyrightForm" method="post" name="copyrightForm" action="${formAction}"
           enctype="multipart/form-data">
    <fieldset style="font-family: sans-serif;padding-top:5px">
        <input type="hidden" name="command" id="${ns}common" value="${copyrightForm.command}"/>
        <input type="hidden" name="mode" id="${ns}mode" value="${copyrightForm.mode}"/>
        <form:hidden path="copyrightM.copyrightId" id="copyrightId"/>
        <c:set var="userIdAsString">${user.userId}</c:set>
        <c:set var="havePermissionOnDocs" value="${copyrightForm.mode=='edit' && copyrightForm.copyrightM.docType=='DRAFT' 
    								&& ( userIdAsString == copyrightForm.copyrightM.createdBy 
                       				|| userIdAsString==copyrightForm.copyrightM.updatedBy || copyrightForm.copyrightM.isdocAssign ) }"></c:set>
        <div style="border: 1px solid #FFC299;background: #F9F9F9;padding: 10px">
            <div class="accordion" id="accordion2">
                <div class="accordion-group">
                    <div class="accordion-heading">
                        <a href="${list}" style="padding-right: 20px;">Back</a>
                        <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion2"
                           href="#collapseOne">
                            ข้อมูลลิขสิทธิ์
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

                                        <form:input path="copyrightM.budgetYear" maxlength="4" cssClass="form-control"/>
                                    </td>
                                    <td width="50%">

                                    </td>
                                </tr>
                                <tr valign="top">
                                    <td align="right" width="20%">
                        <span><label>เลขที่คำขอ:<span style="color: red;font-size: 20;"> </span>
                        </label></span>
                                    </td>
                                    <td width="80%" colspan="3">

                                        <form:input path="copyrightM.requestNo" cssClass="form-control"/>
                                        <span style="color: red;font-size: 20;"><strong>(*ลข.984470)</strong></span>
                                    </td>
                                </tr>
                                <tr valign="top">
                                    <td align="right" width="20%">
                        <span><label>ชื่อผลงาน(T):<span style="color: red;font-size: 20;"> </span>
                        </label></span>
                                    </td>
                                    <td width="80%" colspan="3">

                                        <form:textarea path="copyrightM.thaiName" cssStyle="width:500px" rows="2"/>
                                    </td>
                                </tr>
                                <tr valign="top">
                                    <td align="right" width="20%">
                        <span><label>ชื่อผลงาน(E):<span style="color: red;font-size: 20;"> </span>
                        </label></span>
                                    </td>
                                    <td width="80%" colspan="3">

                                        <form:textarea path="copyrightM.engName" cssStyle="width:500px" rows="2"/>
                                    </td>
                                </tr>
                                <tr valign="top">
                                    <td align="right" width="20%">
                        <span><label>มาจากโครงการวิจัย:<span style="color: red;font-size: 20;"> </span>
                        </label></span>
                                    </td>
                                    <td width="80%" colspan="3">
                                        <form:hidden path="copyrightM.researchProject.researchProjectId"/>
                                        <input type="text" style="background-color: 
                    rgb(250, 250, 198);width:350px" class="form-control"
                                               id="researchProjectId_autocomplete" placeholder=""
                                               value="${copyrightForm.copyrightM.researchProject.thaiName}"/>
                                        <button style="margin-top: -8px"
                                                onclick="${ns}researchProjectPopup('',true,1)"
                                                type="button" class="btn btn-warning btn-small">...
                                        </button>
                                            <%--  <span id="researchProjectId_assignShow">${copyrightForm.copyrightM.researchProject.thaiName} </span>
                
                    <form:input path="copyrightM.researchProject.researchProjectId" cssStyle="display:inline;background-color: rgb(250, 250, 198);width:100px" cssClass="form-control"/>
                     --%>

                                    </td>
                                </tr>
                                <tr valign="top">
                                    <td align="right" width="20%">
                        <span><label>ประเภทงาน:<span style="color: red;font-size: 20;"> </span>
                        </label></span>
                                    </td>
                                    <td width="80%" colspan="3">

                                        <form:hidden path="copyrightM.innovativeWorksType.workTypeId"/>
                                        <input type="text" style="background-color: 
                    rgb(250, 250, 198);width:350px" class="form-control"
                                               id="workTypeId_autocomplete" placeholder=""
                                               value="${copyrightForm.copyrightM.innovativeWorksType.wtName}"/>
                                        <button style="margin-top: -8px"
                                                onclick="${ns}workTypePopup('',true,1)" type="button"
                                                class="btn btn-warning btn-small">...
                                        </button>
                                            <%--  <span id="workTypeId_assignShow">${copyrightForm.copyrightM.innovativeWorksType.wtName} </span>
           
                    <form:input path="copyrightM.innovativeWorksType.workTypeId" cssStyle="display:inline;background-color: rgb(250, 250, 198);width:100px" cssClass="form-control"/>
                     --%>

                                    </td>
                                </tr>
                                <tr>
                                    <td align="right" width="20%">
                        <span><label>วันที่ยื่นแจ้ง:<span style="color: red;font-size: 50;"> </span>
                        </label></span>
                                    </td>
                                    <td width="80%" colspan="3">
                                        <div>
                                                <%-- <fmt:formatDate type="time" value="${copyrightForm.copyrightM.proposeDate}"   pattern="dd/MM/yyyy" var="proposeDate" /> 
                     --%>
                                            <input type="text" value="${copyrightForm.proposeDate}" readonly="readonly"
                                                   id="proposeDate" name="proposeDate" style="width:75px" placeholder="dd/mm/yyyy"
                                                    class="form-control"/>
                                        </div>
                                    </td>
                                </tr>
                                <tr valign="top">
                                    <td align="right" width="20%">
                        <span><label>เลขที่หนังสือรับรองการแจ้งข้อมูล:<span style="color: red;font-size: 20;"> </span>
                        </label></span>
                                    </td>
                                    <td width="80%" colspan="3">

                                        <form:input path="copyrightM.proposeNo" cssClass="form-control"/>
                                        <span style="color: red;font-size: 20;"><strong>(*รลข.01)</strong></span>
                                    </td>
                                </tr>
                                <tr>
                                    <td align="right" width="20%">
                        <span><label>วันที่ออกหนังสือรับรอง:<span style="color: red;font-size: 50;"> </span>
                        </label></span>
                                    </td>
                                    <td width="80%" colspan="3">
                                        <div>
                                                <%--     <fmt:formatDate type="time" value="${copyrightForm.copyrightM.receiveDate}"   pattern="dd/MM/yyyy" var="receiveDate" /> 
                 --%>
                                            <input type="text" value="${copyrightForm.receiveDate}" readonly="readonly"
                                                   id="receiveDate" name="receiveDate" style="width:75px" placeholder="dd/mm/yyyy"
                                                    class="form-control"/>

                                        </div>
                                    </td>
                                </tr>

                                <tr>
                                    <td align="right" width="20%">
                        <span><label>หมายเหตุ:<span style="color: red;font-size: 50;"> </span>
                        </label></span>
                                    </td>
                                    <td width="80%" colspan="3">
                                        <div>

                                            <form:textarea path="copyrightM.remark" cssStyle="width:500px" rows="2"/>
                                        </div>
                                    </td>
                                </tr>
                                <c:if test="${mode=='edit'}">
                                    <tr valign="top">
                                        <td align="right" width="20%">
                        <span><label>Assign to:<span style="color: red;font-size: 20;"> </span>
                        </label></span>
                                        </td>
                                        <td width="80%" colspan="3">
                                            <input type="hidden" id="docsAssign" name="docsAssign"/>
                                            <input type="text" style="background-color: rgb(250, 250, 198);width:250px"
                                                   class="form-control" id="docs_assign_autocomplete" placeholder=""/>
                                            <c:if test="${havePermissionOnDocs}">
                                                <span id="docs_assignShow"> </span>
                                                <button style="margin-top: -8px"
                                                        onclick="${ns}docsAssignPopup('',true,1)"
                                                        type="button" class="btn btn-warning btn-small">...
                                                </button>
                                            </c:if>

                                        </td>
                                    </tr>
                                </c:if>
                                <tr valign="top">
                                    <td align="right" width="20%">
                        <span><label><span style="color: red;font-size: 20;"> </span>
                        </label></span>
                                    </td>
                                    <td width="80%" colspan="3">
              <span id="${ns}copyrightCreator_assign_list">
               <c:if test="${not empty copyrightForm.copyrightM.docAssignMappings}">
                   <table class="table table-hover table-striped table-bordered table-condensed" border="0"
                          style="width:30%;font-size: 12px">
                       <c:forEach items="${copyrightForm.copyrightM.docAssignMappings}" var="docAssignMapping"
                                  varStatus="loop">
                           <tr style="cursor: pointer;">
                               <td width="5%" style="text-align: left">${loop.index+1}</td>
                               <td width="95%" style="text-align: left">
                                       ${docAssignMapping.userPortal.emailAddress}
                               </td>
                               <c:if test="${havePermissionOnDocs}">
                                   <td width="5%" style="text-align: center">
                                       <button onclick='
                                           ${ns}doDeleteDocAssignMappingAjax("${docAssignMapping.refId}","${docAssignMapping.refType}","${docAssignMapping.userId}")'
                                               class="btn btn-danger btn-small" type="button">ลบ
                                       </button>
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
                                        <form:hidden path="copyrightM.docType"/>
                                        <c:if test="${mode=='edit'}">
                                            <c:if test="${havePermissionOnDocs}">
                                                <button type="button"
                                                        onclick="${ns}doSubmitForm('DRAFT')"
                                                        class="btn btn-primary">Save as Draft
                                                </button>
                                                <button type="button"
                                                        onclick="${ns}doSubmitForm('PUBLISH')"
                                                        class="btn btn-primary">Submit
                                                </button>
                                                <c:if test="${copyrightForm.copyrightM.flag=='0'}">
                                                    <button type="button" class="btn" onclick='return ${ns}confirmUndo("
                                                    <portlet:actionURL>
                                                        <portlet:param name="action" value="undoItem"/>
                                                    <portlet:param name="copyrightId">
                                                    <jsp:attribute name="value">
                                                        <c:out value="${copyrightForm.copyrightM.copyrightId}"/>
                                                    </jsp:attribute>
                                                    </portlet:param>
                                                    </portlet:actionURL>")'> Undo</button>
                                                </c:if>
                                                <button class="btn btn-danger" type="button"
                                                        onclick='return ${ns}confirmDelete("
                                                            <portlet:actionURL>
                                                                <portlet:param name="action" value="delete"/>
                                                                <portlet:param name="copyrightId">
                                                                    <jsp:attribute name="value">
                                                                        <c:out value="${copyrightForm.copyrightM.copyrightId}"/>
                                                                    </jsp:attribute>
                                                                </portlet:param>
                                                            </portlet:actionURL>")'>
                                                    <c:if test="${copyrightForm.copyrightM.flag=='1'}">
                                                        ลบ
                                                    </c:if>
                                                    <c:if test="${copyrightForm.copyrightM.flag=='0'}">
                                                        ลบ ถาวร
                                                    </c:if>
                                                </button>
                                            </c:if>
                                        </c:if>
                                        <c:if test="${mode=='add' || mode =='copy'}">
                                            <button type="button" onclick="${ns}doSubmitForm('DRAFT')"
                                                    class="btn btn-primary">Save Header
                                            </button>
                                            <%-- <button type="button" onclick="${ns}doSubmitForm('PUBLISH')" class="btn btn-primary">Submit</button>  --%>
                                        </c:if>
                                    </td>

                                </tr>
                            </table>
                        </div>
                    </div>
                </div>
                <div class="accordion-group">
                    <div class="accordion-heading">
                        <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion2"
                           href="#collapseTwo">
                            ผู้ประดิษฐ์
                        </a>
                    </div>
                    <div id="collapseTwo" class="accordion-body collapse" style="height: 0px;">
                        <div class="accordion-inner">
                            <table border="0" width="100%" style="font-size: 13px">
                                <tbody>


                                <tr>
                                    <td align="center" width="100%" colspan="2">
                                        <div id="${ns}element_copyrightCreator"
                                             style="border: 1px solid #FFC299;background: #F9F9F9;padding: 10px;display: none">
                                            <table border="0" width="100%"
                                                   style="border: 0px solid #FFC299;font-size:13px">
                                                <tr>
                                                    <td align="right" width="30%">
                        		<span>
                        			<label>บุคคล/หน่วยงาน:<span style="color: red;font-size: 50;"> </span>
                                    </label>
                        		</span>
                                                    </td>
                                                    <td width="70%" colspan="3">
                                                        <div>
                                                            <select id="creatorType" onchange="
                                                                ${ns}displayCorpType(this.value)">
                                                                <option value="1">บุคคล</option>
                                                                <option value="2">หน่วยงาน</option>
                                                            </select>

                                                        </div>
                                                    </td>
                                                </tr>
                                                <tr id="show_corp_type_1">
                                                    <td align="right" width="30%">
                        		<span>
                        			<label>รหัสประชาชน:<span style="color: red;font-size: 50;"> </span>
                                    </label>
                        		</span>
                                                    </td>
                                                    <td width="70%" colspan="3">
                                                        <div>

                                                            <input type="hidden"
                                                                   id="${ns}copyrightCreator_mode"/>
                                                            <input type="hidden"
                                                                   id="${ns}copyrightCreator_creatorItemList"/>
                                                            <input type="text"
                                                                   id="${ns}copyrightCreator_creatorId"
                                                                   class="form-control">
                                                        </div>
                                                    </td>
                                                </tr>

                                                <tr id="show_corp_type_2">
                                                    <td align="right" width="30%">
                        		<span>
                        			<label>คณะ/สำนัก:<span style="color: red;font-size: 50;"> </span>
                                    </label>
                        		</span>
                                                    </td>
                                                    <td width="70%" colspan="3">
                                                        <div>

                                                            <input type="hidden"
                                                                   id="${ns}copyrightCreator_organizationId"/>
                                                            <input type="text" style="background-color: 
                    rgb(250, 250, 198);" class="form-control"
                                                                   id="organizationId_assign_autocomplete"
                                                                   placeholder=""/>
                                                            <button id="${ns}organizationListAll"
                                                                    style="margin-top: -8px;display: none" onclick="
                                                                ${ns}organizationPopup('',true,1)"
                                                                    type="button" class="btn btn-warning btn-small">...
                                                            </button>
                                                                <%--
                   <span id="organizationId_assignShow"></span>
                    --%>
                                                        </div>
                                                    </td>
                                                </tr>
                                                <c:if test="${havePermissionOnDocs}">
                                                    <tr>
                                                        <td align="center" colspan="4" width="20%">
                                                            <button type="button" onclick="
                                                                ${ns}doSubmitCopyrightCreatorAjax()"
                                                                    class="btn btn-primary">Submit
                                                            </button>
                                                        </td>
                                                    </tr>
                                                </c:if>
                                            </table>
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td align="left" width="70%">

                                    </td>
                                    <td align="right" width="30%"> 
                <span sytle="padding-left:20px">
                  <c:if test="${copyrightForm.mode=='edit'}">
                      <c:if test="${havePermissionOnDocs}">
                          <button onclick="${ns}displayCopyrightCreator('add','0')" type="button"
                                  class="btn btn-primary">Add
                          </button>
                      </c:if>
                  </c:if>
               </span>
                                    </td>
                                </tr>
                                </tbody>
                            </table>  
                  <span id="${ns}copyrightCreator_item_list">
        <table class="table table-hover table-striped table-bordered table-condensed" border="1"
               style="font-size: 12px">
            <thead>
            <tr> <!-- 50 42+8 -->
                <th width="5%" style="text-align: center">
                    <div class="th_class">#</div>
                </th>
                <th width="15%" style="text-align: center">
                    <div class="th_class">บุคคล/หน่วยงาน</div>
                </th>
                <th width="30%" style="text-align: center">
                    <div class="th_class">รหัสประชาชน</div>
                </th>
                <th width="30%" style="text-align: center">
                    <div class="th_class">คณะ/สำนัก</div>
                </th>
                <c:if test="${havePermissionOnDocs}">
                    <th width="10%">
                        <div class="th_class"></div>
                    </th>
                </c:if>
            </tr>
            </thead>
            <tbody>
            <c:if test="${not empty copyrightForm.copyrightM.copyrightCreators}">
                <c:forEach items="${copyrightForm.copyrightM.copyrightCreators}" var="copyrightCreator"
                           varStatus="loop">

                    <tr style="cursor: pointer;">
                        <td onclick='
                            ${ns}doEditCopyrightCreatorAjax("${copyrightCreator.copyrightId}","${copyrightCreator.creatorItemList}")'
                            style="text-align: left">${loop.index+1}</td>
                        <td onclick='
                            ${ns}doEditCopyrightCreatorAjax("${copyrightCreator.copyrightId}","${copyrightCreator.creatorItemList}")'
                            style="text-align: left">
                            <c:if test="${copyrightCreator.creatorType=='1'}">
                                บุคลากร
                            </c:if>
                            <c:if test="${copyrightCreator.creatorType=='2'}">
                                หน่วยงาน
                            </c:if>
                        </td>
                        <td onclick='
                            ${ns}doEditCopyrightCreatorAjax("${copyrightCreator.copyrightId}","${copyrightCreator.creatorItemList}")'
                            style="text-align: left">
                            <c:if test="${copyrightCreator.creatorType=='1'}">
                                ${copyrightCreator.creatorId}
                            </c:if>
                        </td>
                        <td onclick='
                            ${ns}doEditCopyrightCreatorAjax("${copyrightCreator.copyrightId}","${copyrightCreator.creatorItemList}")'
                            style="text-align: left">
                            <c:if test="${copyrightCreator.creatorType=='2'}">

                                ${copyrightCreator.organization.orgName}
                            </c:if>
                        </td>
                        <c:if test="${havePermissionOnDocs}">
                            <td style="text-align: center">
                                <button onclick='
                                    ${ns}doEditCopyrightCreatorAjax("${copyrightCreator.copyrightId}","${copyrightCreator.creatorItemList}")'
                                        class="btn btn-small" type="button">แก้ใข
                                </button>
                                <button onclick='
                                    ${ns}doDeleteCopyrightCreatorAjax("${copyrightCreator.copyrightId}","${copyrightCreator.creatorItemList}")'
                                        class="btn btn-danger btn-small" type="button">ลบ
                                </button>
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
                        <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion2"
                           href="#collapseSeven">
                            เอกสาร
                        </a>
                    </div>
                    <div id="collapseSeven" class="accordion-body collapse" style="height: 0px;">
                        <div class="accordion-inner">
                            <table border="0" width="100%" style="font-size: 13px">
                                <tbody>
                                <tr>
                                    <td align="center" width="100%" colspan="2">
                                        <div id="${ns}element_document"
                                             style="border: 1px solid #FFC299;background: #F9F9F9;padding: 10px;display: none">
                                            <table border="0" width="100%"
                                                   style="border: 0px solid #FFC299;font-size:13px">
                                                <tr>
                                                    <td align="right" width="30%">
                        		<span>
                        			<label>Document Name:<span style="color: red;font-size: 50;"> </span>
                                    </label>
                        		</span>
                                                    </td>
                                                    <td width="70%" colspan="3">
                                                        <div>
                                                            <input type="hidden"
                                                                   id="${ns}document_mode"/>
                                                            <input type="hidden"
                                                                   id="${ns}document_itemList"/>
                                                            <input type="text"
                                                                   id="${ns}document_description"
                                                                   style="width:75px" class="form-control">
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
                                                            <input type="file" id="${ns}uploadFile"
                                                                   name="${ns}uploadFile"/>
                                                        </div>
                                                    </td>
                                                </tr>
                                                <c:if test="${havePermissionOnDocs}">
                                                    <tr>
                                                        <td align="center" colspan="4" width="20%">
                                                            <button type="button"
                                                                    onclick="${ns}doSubmitDocumentAjax()"
                                                                    class="btn btn-primary">Submit
                                                            </button>
                                                        </td>
                                                    </tr>
                                                </c:if>
                                            </table>
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td align="left" width="70%">

                                    </td>
                                    <td align="right" width="30%"> 
                <span sytle="padding-left:20px">
                  <c:if test="${copyrightForm.mode=='edit'}">
                      <c:if test="${havePermissionOnDocs}">
                          <button onclick="${ns}displayDocument('add','0')" type="button"
                                  class="btn btn-primary">Add
                          </button>
                      </c:if>
                  </c:if>
               </span>
                                    </td>
                                </tr>
                                </tbody>
                            </table>  
                 <span id="${ns}document_item_list"> 
        <table class="table table-hover table-striped table-bordered table-condensed" border="1"
               style="font-size: 12px">
            <thead>
            <tr> <!-- 50 42+8 -->
                <th width="5%" style="text-align: center">
                    <div class="th_class">#</div>
                </th>
                <th width="85%" style="text-align: center">
                    <div class="th_class">Document Name</div>
                </th>
                <c:if test="${havePermissionOnDocs}">
                    <th width="10%">
                        <div class="th_class"></div>
                    </th>
                </c:if>
            </tr>
            </thead>
            <tbody>
            <c:if test="${not empty copyrightForm.copyrightM.copyrightDocuments}">
                <c:forEach items="${copyrightForm.copyrightM.copyrightDocuments}" var="copyrightDocument"
                           varStatus="loop">
                    <tr style="cursor: pointer;">
                        <td onclick='
                            ${ns}doEditDocumentAjax("${copyrightDocument.copyrightId}","${copyrightDocument.itemList}")'
                            style="text-align: left">${loop.index+1}</td>
                        <c:if test="${not empty copyrightDocument.fileName}">
                            <td onclick='
                                ${ns}doEditDocumentAjax("${copyrightDocument.copyrightId}","${copyrightDocument.itemList}")'
                                style="text-align: left">${copyrightDocument.description} [ <a
                                    style="text-decoration: underline;" onclick='
                                ${ns}downloadFile("${copyrightDocument.itemList}","${copyrightDocument.copyrightId}")'>${copyrightDocument.fileName}</a>
                                ]
                            </td>
                        </c:if>
                        <c:if test="${empty copyrightDocument.fileName}">
                            <td onclick='
                                ${ns}doEditDocumentAjax("${copyrightDocument.copyrightId}","${copyrightDocument.itemList}")'
                                style="text-align: left">${copyrightDocument.description} </td>
                        </c:if>
                        <c:if test="${havePermissionOnDocs}">
                            <td style="text-align: center">
                                <button onclick='
                                    ${ns}doEditDocumentAjax("${copyrightDocument.copyrightId}","${copyrightDocument.itemList}")'
                                        class="btn btn-small" type="button">แก้ใข
                                </button>
                                <button onclick='
                                    ${ns}doDeleteDocumentAjax("${copyrightDocument.copyrightId}","${copyrightDocument.itemList}")'
                                        class="btn btn-danger btn-small" type="button">
                                    ลบ
                                </button>
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
</div>
<!-- /container -->


<!-- Bootstrap core JavaScript
    ================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="<c:url value="/resources/js/jquery-1.11.2.min.js"/>"></script>
<script src="<c:url value="/resources/bootstrap/js/bootstrap.min.js"/>"></script>
<script src="<c:url value="/resources/js/smoothness/jquery-ui-1.9.1.custom.min.js"/>"></script>
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
    $(document).ready(function () {
        var _path = "${url}";
        //alert(_path)

        $("#proposeDate").datepicker({
            showOn: "button",
            buttonImage: '<c:url value="/resources/images/calendar.gif"/>',
            buttonImageOnly: true,
            dateFormat: "dd/mm/yy"
        });
        $("#receiveDate").datepicker({
            showOn: "button",
            buttonImage: '<c:url value="/resources/images/calendar.gif"/>',
            buttonImageOnly: true,
            dateFormat: "dd/mm/yy"
        });
        $('#docs_assign_autocomplete').autocomplete({
            source: function (request, response) {
                var userPortalM = {
                    keySearch: request.term
                }
                ResearchAjax.getUserPortalList(userPortalM, {
                    callback: function (data) {
                        data = data.resultListObj;
                        if (data != null && data.length > 0) {
                            response($.map(data, function (item) {
                                return {
                                    label: item.emailAddress + " [ " + item.firstName + " " + item.lastName + "]",
                                    value: item.userId,
                                    name: item.emailAddress + " [ " + item.firstName + " " + item.lastName + "]",
                                    id: item.userId
                                    //code: item.frtCode
                                };
                            }));
                        } else {
                            var xx = [];
                            response($.map(xx));
                        }
                    }
                });
            },
            minLength: 1,
            select: function (event, ui) {
                this.value = ui.item.name;
                $('#docsAssign').val(ui.item.id);
                var buttonShow = '<button style=\"margin-top: -8px"\" onclick=\'${ns}doAddDocAssignMappingAjax("' + $("#copyrightId").val() + '","COPYRIGHT","' + ui.item.id + '")\' class="btn btn-primary btn-small" type="button">Add</button>'

                $("#docs_assignShow").html(buttonShow);
                $("#docs_assignShow").show("slow");
                return false;
            },
            open: function () {
                $(this).removeClass("ui-corner-all").addClass("ui-corner-top");
            },
            close: function () {
                $(this).removeClass("ui-corner-top").addClass("ui-corner-all");
            }
        });
        $('#workTypeId_autocomplete').autocomplete({
            source: function (request, response) {
                var workTypeM = {
                    keySearch: request.term
                }
                ResearchAjax.getWorkTypeList(workTypeM, {
                    callback: function (data) {
                        data = data.resultListObj;
                        if (data != null && data.length > 0) {
                            response($.map(data, function (item) {
                                return {
                                    label: item.wtName,
                                    value: item.wtName,
                                    name: item.wtName,
                                    id: item.workTypeId
                                    //code: item.frtCode
                                };
                            }));
                        } else {
                            var xx = [];
                            response($.map(xx));
                        }
                    }
                });
            },
            minLength: 1,
            select: function (event, ui) {
                this.value = ui.item.name;

                $('input[id="copyrightM.innovativeWorksType.workTypeId"]').val(ui.item.id);
                $("#workTypeId_assignShow").html(" " + ui.item.name + " ");
                return false;
            },
            open: function () {
                $(this).removeClass("ui-corner-all").addClass("ui-corner-top");
            },
            close: function () {
                $(this).removeClass("ui-corner-top").addClass("ui-corner-all");
            }
        });

        $('#researchProjectId_autocomplete').autocomplete({
            source: function (request, response) {
                var researchProjectM = {
                    keySearch: request.term
                }
                ResearchAjax.getResearchProjectList(researchProjectM, {
                    callback: function (data) {
                        data = data.resultListObj;
                        if (data != null && data.length > 0) {
                            response($.map(data, function (item) {
                                return {
                                    label: item.thaiName,
                                    value: item.thaiName,
                                    name: item.thaiName,
                                    id: item.researchProjectId
                                    //code: item.frtCode
                                };
                            }));
                        } else {
                            var xx = [];
                            response($.map(xx));
                        }
                    }
                });
            },
            minLength: 1,
            select: function (event, ui) {
                this.value = ui.item.name;

                $('input[id="copyrightM.researchProject.researchProjectId"]').val(ui.item.id);
                $("#researchProjectId_assignShow").html(" " + ui.item.name + " ");
                return false;
            },
            open: function () {
                $(this).removeClass("ui-corner-all").addClass("ui-corner-top");
            },
            close: function () {
                $(this).removeClass("ui-corner-top").addClass("ui-corner-all");
            }
        });


        $('#organizationId_assign_autocomplete').autocomplete({
            source: function (request, response) {
                var organizationM = {
                    keySearch: request.term
                }
                ResearchAjax.getOrganizationList(organizationM, {
                    callback: function (data) {
                        data = data.resultListObj;
                        if (data != null && data.length > 0) {
                            response($.map(data, function (item) {
                                return {
                                    label: item.orgName,
                                    value: item.orgName,
                                    name: item.orgName,
                                    id: item.organizationId
                                    //code: item.frtCode
                                };
                            }));
                        } else {
                            var xx = [];
                            response($.map(xx));
                        }
                    }
                });
            },
            minLength: 1,
            select: function (event, ui) {
                this.value = ui.item.name;
                $('input[id="${ns}copyrightCreator_organizationId"]').val(ui.item.id);
                $("#organizationId_assignShow").html(" " + ui.item.name + " ");
                return false;
            },
            open: function () {
                $(this).removeClass("ui-corner-all").addClass("ui-corner-top");
            },
            close: function () {
                $(this).removeClass("ui-corner-top").addClass("ui-corner-all");
            }
        });
        $('#copyrightM\\.budgetYear').keyup(function () {
            var dInput = this.value;
            // alert(dInput.length)
            if ($.trim(dInput).length > 0 && !${ns}validateDigitOnly(dInput)) {
                //alert('กรอก  เฉพาะตัวเลขเท่านั้น.');
                $(this).val("");
                $(this).focus();
                return false;
            }

        });
    });

    <%-- START CopyrightCreator  --%>
    function ${ns}doEditCopyrightCreatorAjax(copyrightId, creatorItemList) {

        var copyrightCreator = {
            copyrightId: copyrightId,
            creatorItemList: creatorItemList
        }
        ResearchAjax.findCopyrightCreatorById(copyrightCreator, {
            callback: function (data) {
                if (data != null) {
                    // workLoadRatio
                    ${ns}displayCorpType(data.creatorType);
                    $("#creatorType").val(data.creatorType);
                    //alert(data.creatorType)	

                    //private Integer organizationId;
                    // private OrganizationM organization;
                    $("#${ns}copyrightCreator_creatorId").val(data.creatorId);
                    var organizationId = '';
                    var orgName = ''
                    if (data.organization != null) {
                        //organizationId
                        organizationId = data.organization.organizationId;
                        orgName = data.organization.orgName;
                    }
                    $("#${ns}copyrightCreator_organizationId").val(organizationId);
                    $("#organizationId_assign_autocomplete").val(orgName);
                    $("#organizationId_assignShow").html(orgName);

                    $("#${ns}copyrightCreator_creatorType").val(data.creatorType);

                    $("#${ns}copyrightCreator_mode").val("edit");
                    $("#${ns}copyrightCreator_creatorItemList").val(creatorItemList);


                    $("#${ns}element_copyrightCreator").show("slow");
                    $("#${ns}organizationListAll").show("slow");
                }
            }
        });
    }
    function ${ns}doDeleteCopyrightCreatorAjax(copyrightId, creatorItemList) {
        //alert(copyrightId)
        var agree = confirm(" ต้องการลบข้อมูลหรือไม่? ");
        if (agree) {
            ${ns}showDownlod("${ns}copyrightCreator_item_list");
            var researchProjectCopyrightCreator = {
                copyrightId: copyrightId,
                creatorItemList: creatorItemList
            }
            ResearchAjax.deleteCopyrightCreator(researchProjectCopyrightCreator, {
                callback: function (data) {
                    ${ns}render_copyrightCreator_item(data);
                    $("#${ns}element_copyrightCreator").hide("slow");
                }
            });
            return true;
        }
        else {
            return false;
        }


    }
    function ${ns}doSubmitCopyrightCreatorAjax() {
        var mode = $("#${ns}copyrightCreator_mode").val();

        //alert(mode)
        var organizationId = $('#${ns}copyrightCreator_organizationId').val();
        var organization;

        if (organizationId.length > 0) {
            organization = {
                organizationId: organizationId
            };
        } else {
            organization = {};
        }
        //alert(organization)

        //private Integer organizationId;
        var researchProjectCopyrightCreator = {
            copyrightId: $("#copyrightId").val(),
            creatorType: $("#creatorType").val(),
            creatorId: $("#${ns}copyrightCreator_creatorId").val(),
            // researcherDept:$('#${ns}copyrightCreator_organization').val(),
            organization: organization,
            createdBy: "${user.userId}",
            updatedBy: "${user.userId}",
            creatorItemList: $("#${ns}copyrightCreator_creatorItemList").val()
        }
        // alert(researchProjectCopyrightCreator)
        ${ns}showDownlod("${ns}copyrightCreator_item_list");
        ResearchAjax.updateCopyrightCreator(researchProjectCopyrightCreator, mode, {
            callback: function (data) {
                //alert(data+"xxx")
                ${ns}render_copyrightCreator_item(data);
                $("#${ns}element_copyrightCreator").hide("slow");
            }
        });
    }
    function ${ns}render_copyrightCreator_item(obj) {

        var str = "<table class=\"table table-striped table-bordered table-condensed\" border=\"1\" style=\"font-size: 12px\"> " +
                " <thead>    " +
                "  <tr>" +
                "  		<th width=\"5%\" style=\"text-align: center\"><div class=\"th_class\">#</div></th>" +
                "		<th width=\"15%\" style=\"text-align: center\"><div class=\"th_class\">บุคคล/หน่วยงาน</div></th>" +
                "		<th width=\"30%\" style=\"text-align: center\"><div class=\"th_class\">รหัสประชาชน</div></th>  " +
                "		<th width=\"30%\" style=\"text-align: center\"><div class=\"th_class\">คณะ/สำนัก</div></th> " +
                "		<th width=\"10%\"><div class=\"th_class\"></div></th>  " +
                "	</tr> 		" +
                " </thead> 	" +
                " <tbody>  	";
        if (obj != null) {
            for (var i = 0; i < obj.length; i++) {

                str = str + " 	<tr style=\"cursor: pointer;\">" +
                        " <td onclick=\"${ns}doEditCopyrightCreatorAjax(\'" + obj[i].copyrightId + "\',\'" + obj[i].creatorItemList + "\')\" style=\"text-align: left\">" + (i + 1) + "</td>" +
                        " <td onclick=\"${ns}doEditCopyrightCreatorAjax(\'" + obj[i].copyrightId + "\',\'" + obj[i].creatorItemList + "\')\" style=\"text-align: left\">";
                if ($.trim(obj[i].creatorType) == "1") {
                    str = str + "บุคคล";
                } else {
                    str = str + "หน่วยงาน";
                }
                str = str + " </td> " +
                        " <td onclick=\"${ns}doEditCopyrightCreatorAjax(\'" + obj[i].copyrightId + "\',\'" + obj[i].creatorItemList + "\')\" style=\"text-align: left\">";

                if ($.trim(obj[i].creatorType) == "1") {
                    str = str + "" + ((obj[i].creatorId != null) ? obj[i].creatorId : "" );
                } else {

                }

                str = str + " </td> " +
                        " <td onclick=\"${ns}doEditCopyrightCreatorAjax(\'" + obj[i].copyrightId + "\',\'" + obj[i].creatorItemList + "\')\" style=\"text-align: left\">";

                if ($.trim(obj[i].creatorType) == "2") {
                    str = str + "" + ((obj[i].organization != null) ? $.trim(obj[i].organization.orgName) : "");
                } else {

                }

                str = str + " </td> " +
                        " <td style=\"text-align: center\">" +
                        "  <button onclick=\"${ns}doEditCopyrightCreatorAjax(\'" + obj[i].copyrightId + "\',\'" + obj[i].creatorItemList + "\')\" class=\"btn btn-small\" type=\"button\">แก้ใข</button> " +
                        "  <button onclick=\"${ns}doDeleteCopyrightCreatorAjax(\'" + obj[i].copyrightId + "\',\'" + obj[i].creatorItemList + "\')\" class=\"btn btn-danger btn-small\" type=\"button\">ลบ</button> " +
                        " </td>  " +
                        " </tr> ";
            }
        }
        str = str + "</tbody>" +
                "</table>";
        //alert(str)
        $("#${ns}copyrightCreator_item_list").html(str);

    }
    function ${ns}displayCorpType(corpType) {
        $("#show_corp_type_1").hide();
        $("#show_corp_type_2").hide();
        $("#show_corp_type_" + corpType).show();
    }
    function ${ns}displayCopyrightCreator(mode, id) {

        $("#creatorType").val("1");
        var creatorType = $("#creatorType").val();
        $("#organizationId_assignShow").html("");
        $("#${ns}copyrightCreator_creatorId").val("");
        $("#${ns}copyrightCreator_organizationId").val("");
        $("#organizationId_assign_autocomplete").val("");
        $("#${ns}copyrightCreator_creatorItemList").val('');
        if (mode == 'edit') {

        } else {
            // alert(creatorType)
            ${ns}displayCorpType(creatorType);
            $("#${ns}copyrightCreator_mode").val('add')
            $("#${ns}element_copyrightCreator").show("slow");
            $("#${ns}organizationListAll").show("slow");
        }
    }
    <%-- END CopyrightCreator  --%>


    <%-- START DocAssign  --%>
    function ${ns}doDeleteDocAssignMappingAjax(refId, refType, userId) {
        //alert(copyrightId)
        var agree = confirm(" ต้องการลบข้อมูลหรือไม่? ");
        if (agree) {
            ${ns}showDownlod("${ns}copyrightCreator_assign_list");
            var docAssignMapping = {
                refId: refId,
                refType: refType,
                userId: userId
            }
            ResearchAjax.deleteDocAssignMapping(docAssignMapping, {
                callback: function (data) {
                    ${ns}render_docAssign_item(data);
                    //$( "#${ns}element_copyrightCreator" ).hide( "slow"); 
                }
            });
            return true;
        }
        else {
            return false;
        }


    }
    function ${ns}doAddDocAssignMappingAjax(refId, refType, userId) {
        var mode = "add";

        var docAssignMapping = {
            refId: refId,
            refType: refType,
            userId: userId,
            createdBy: "${user.userId}",
            updatedBy: "${user.userId}"
        }

        // alert(researchProjectCopyrightCreator)
        ${ns}showDownlod("${ns}copyrightCreator_assign_list");
        ResearchAjax.saveDocAssignMapping(docAssignMapping, mode, {
            callback: function (data) {
                //alert(data+"xxx")
                ${ns}render_docAssign_item(data);
                $("#docs_assignShow").hide("slow");
                $("#docs_assign_autocomplete").val('');
                $("#docsAssign").val('');
            }
        });
    }
    function ${ns}render_docAssign_item(obj) {
        var str = "<table class=\"table table-striped table-bordered table-condensed\" border=\"0\" style=\"width:30%;font-size: 12px\"> ";
        obj = obj.resultListObj;
        if (obj != null) {

            for (var i = 0; i < obj.length; i++) {
                str = str + " 	<tr style=\"cursor: pointer;\">" +
                        " <td width=\"5%\" style=\"text-align: left\">" + (i + 1) + "</td>" +
                        " <td style=\"text-align: left\">" + obj[i].userPortal.emailAddress + "</td>" +
                        " <td width=\"5%\"  style=\"text-align: center\">" +
                        "<button onclick=\'${ns}doDeleteDocAssignMappingAjax(\"" + obj[i].refId + "\",\"" + obj[i].refType + "\",\"" + obj[i].userId + "\")\' class=\"btn btn-danger btn-small\" type=\"button\">ลบ</button>" +
                        "</td>" +
                        " </tr> ";
            }
        }

        str = str + "</table>";
        $("#${ns}copyrightCreator_assign_list").html(str);

    }


    <%-- END DocAssign  --%>

    <%-- Start Document  --%>

    function ${ns}doEditDocumentAjax(copyrightId, itemList) {

        var copyrightDocument = {
            copyrightId: copyrightId,
            itemList: itemList

        };

        ResearchAjax.findCopyrightDocumentById(copyrightDocument, {
            callback: function (data) {
                if (data != null) {
                    $("#${ns}document_description").val(data.description);

                    $("#${ns}document_mode").val("edit")
                    $("#${ns}document_itemList").val(itemList);

                    $("#${ns}element_document").show("slow");
                }
            }
        });
    }

    function ${ns}doDeleteDocumentAjax(copyrightId, itemList) {

        var agree = confirm(" ต้องการลบข้อมูลหรือไม่? ");
        if (agree) {
            ${ns}showDownlod("${ns}document_item_list");
            var copyrightDocument = {
                copyrightId: copyrightId,
                itemList: itemList
            };

            ResearchAjax.deleteCopyrightDocumentM(copyrightDocument, "copyRight", {
                callback: function (data) {
                    ${ns}render_document_item(data);
                    $("#${ns}element_document").hide("slow");
                }
            });
            return true;
        }
        else {
            return false;
        }

    }
    function ${ns}doSubmitDocumentAjax() {
        var mode = $("#${ns}document_mode").val();
        //alert($("#copyrightId").val())
        //return false;
        var copyrightDocument = {
            copyrightId: $("#copyrightId").val(),
            //documentId:1,
            description: $("#${ns}document_description").val(),
            createdBy: "${user.userId}",
            updatedBy: "${user.userId}",
            itemList: $("#${ns}document_itemList").val()
        }
        ${ns}showDownlod("${ns}document_item_list");
        var file = dwr.util.getValue('${ns}uploadFile');
        //   ResearchAjax.uploadResearchProjectDocument(file,researchProjectDocument,"researchProject",mode, {
        ResearchAjax.updateCopyrightDocument(file, copyrightDocument, "copyRight", mode, {
            callback: function (data) {
                ${ns}render_document_item(data);
                $("#${ns}element_document").hide("slow");
            }
        });
    }
    function ${ns}displayDocument(mode, id) {
        $("#${ns}document_description").val('');
        $("#${ns}document_itemList").val('');
        if (mode == 'edit') {

        } else {
            $("#${ns}document_mode").val('add');
            $("#${ns}element_document").show("slow");
        }

    }

    function ${ns}render_document_item(obj) {
        var str = "<table class=\"table table-striped table-bordered table-condensed\" border=\"1\" style=\"font-size: 12px\"> " +
                " <thead>    " +
                "  <tr>" +
                "  		<th width=\"5%\" style=\"text-align: center\"><div class=\"th_class\">#</div></th>" +
                "		<th width=\"85%\" style=\"text-align: center\"><div class=\"th_class\">Document Name</div></th>" +
                "		<th width=\"10%\"><div class=\"th_class\"></div></th>  " +
                "	</tr> 		" +
                " </thead> 	" +
                " <tbody>  	";
        if (obj != null) {
            for (var i = 0; i < obj.length; i++) {
                str = str + " 	<tr style=\"cursor: pointer;\">" +
                        " <td onclick=\"${ns}doEditDocumentAjax(\'" + obj[i].copyrightId + "\',\'" + obj[i].itemList + "\')\" style=\"text-align: left\">" + (i + 1) + "</td>";
                if ($.trim(obj[i].fileName).length > 0) {
                    str = str + "<td onclick=\"${ns}doEditDocumentAjax(\'" + obj[i].copyrightId + "\',\'" + obj[i].itemList + "\')\" style=\"text-align: left\">" + $.trim(obj[i].description) + "  [ <a style=\"text-decoration: underline;\" onclick=\'${ns}downloadFile(\"" + obj[i].itemList + "\",\"" + obj[i].copyrightId + "\")\'>" + obj[i].fileName + "</a>]</td> ";
                } else {
                    str = str + "<td onclick=\"${ns}doEditDocumentAjax(\'" + obj[i].copyrightId + "\',\'" + obj[i].itemList + "\')\" style=\"text-align: left\">" + $.trim(obj[i].description) + "</td> ";
                }
                str = str + "" +
                        " <td style=\"text-align: center\">" +
                        "  <button onclick=\"${ns}doEditDocumentAjax(\'" + obj[i].copyrightId + "\',\'" + obj[i].itemList + "\')\" class=\"btn btn-small\" type=\"button\">แก้ใข</button> " +
                        "  <button onclick=\"${ns}doDeleteDocumentAjax(\'" + obj[i].copyrightId + "\',\'" + obj[i].itemList + "\')\" class=\"btn btn-danger btn-small\" type=\"button\">ลบ</button> " +
                        " </td>  " +
                        " </tr> ";
            }
        }
        str = str + "</tbody>" +
                "</table>";
        $("#${ns}document_item_list").html(str);

    }
    function ${ns}downloadFile(ref1, ref2) {
        ResearchAjax.downloadFile(ref1, ref2, "copyRight", function (data) {
            dwr.engine.openInDownload(data);
        });
    }
    <%-- END Document  --%>

    function ${ns}validateYear(sDigit) {

        var filter = /^\d{4}$/;
        if (filter.test(sDigit)) {
            return true;
        }
        else {
            return false;
        }
    }
    function ${ns}doSubmitForm(status) {

        var budgetYear = $('input[id="copyrightM.budgetYear"]').val();
        if ($.trim(budgetYear).length > 0 && !${ns}validateYear(budgetYear)) {
            //alert('กรอก  ปีงบประมาณ ให้ถูกต้อง.');
            $('input[id="copyrightM.budgetYear"]').val("");
            $('input[id="copyrightM.budgetYear"]').focus();
            return false;
        }
        $('input[id="copyrightM.docType"]').val(status);
        var form = document.forms['copyrightForm'];
        form.submit();
    }
    function ${ns}showDownlod(element_) {
        //<c:url value='/resources/images/ajax_loading.gif'/>loading.gif
        var download_str = '<table border="0" width="100%"><tr><td  align="center"><img style="width:60px;" src="<c:url value="/resources/images/loading.gif"/>"/></td></tr></table>';
        $("#" + element_).html(download_str);

    }
    function ${ns}validateDigitOnly(sDigit) {
        var filter = /^[0-9]+$/
        if (filter.test(sDigit)) {
            return true;
        }
        else {
            return false;
        }
    }

    function ${ns}showDownlod(element_) {
        //<c:url value='/resources/images/ajax_loading.gif'/>loading.gif
        var download_str = '<table border="0" width="100%"><tr><td  align="center"><img style="width:60px;" src="<c:url value="/resources/images/loading.gif"/>"/></td></tr></table>';
        $("#" + element_).html(download_str);

    }

    function ${ns}doSearchBox(f_name, pageNo) {
        if (f_name == 'researchGroup')
            ${ns}researchGroupPopup($("#${ns}keySearch_" + f_name).val(), false, pageNo)
        else if (f_name == 'organization')
            ${ns}organizationPopup($("#${ns}keySearch_" + f_name).val(), false, pageNo)
        else if (f_name == 'fundingResource')
            ${ns}fundingResourcePopup($("#${ns}keySearch_" + f_name).val(), false, pageNo)
        else if (f_name == 'docsAssign')
            ${ns}docsAssignPopup($("#${ns}keySearch_" + f_name).val(), false, pageNo)
        else if (f_name == 'researchProject')
            ${ns}researchProjectPopup($("#${ns}keySearch_" + f_name).val(), false, pageNo)
        else if (f_name == 'workType')
            ${ns}workTypePopup($("#${ns}keySearch_" + f_name).val(), false, pageNo)
    }
    function ${ns}chk(f_name, ev) {
        var key;

        ev = ev || event;
        key = ev.keyCode;
        //alert(ev+",key->"+key)
        if (key == 13) {
            // if(f_name=='researchGroup')
            ${ns}doSearchBox(f_name, 1);
            return false;
        }
        return true;
    }

    <%-- start docsAssign Popup --%>
    function ${ns}docsAssignPopup(keySearch, init, pageNo) {
        var keyBox = " <div>" +
                "<input type=\"text\"  style=\"display:inline;width:250px\" class=\"form-control\" " +
                " id=\"${ns}keySearch_docsAssign\" onkeypress=\"${ns}chk(\'docsAssign\',event)\" aria-describedby=\"inputSuccess4Status\" value=\"" + keySearch + "\"/>" +
                "<button onclick=\"${ns}doSearchBox('docsAssign',1)\" type=\"button\" class=\"btn btn-success\" style=\"margin-top:-8px;margin-left:5px;\">Search</button>" +
                "</div>";
        var str = "<table class=\"table table-hover table-striped table-bordered table-condensed\" border=\"1\" style=\"font-size: 12px\"> " +
                " <thead>    " +
                "  <tr>" +
                    //	"  		<th width=\"20%\" style=\"text-align: center\"><div class=\"th_class\">รหัสกลุ่มวิจัย</div></th>"+ 
                    //	"		<th width=\"40%\" style=\"text-align: center\"><div class=\"th_class\">ชื่อกลุ่มวิจัยภาษาไทย</div></th>  "+
                "		<th width=\"100%\" style=\"text-align: center\"><div class=\"th_class\">ชื่อ นามสกุล</div></th> " +

                "	</tr> 		" +
                " </thead><tbody>";

        var pageObj = {
            pageSize: PAGE_SIZE_POPUP,
            pageNo: pageNo
        }
        var docsAssignM = {
            keySearch: keySearch,
            paging: pageObj
        }
        ResearchAjax.getUserPortalList(docsAssignM, {
            callback: function (data) {
                var maxRow = data.maxRow;
                var lastpage = data.lastpage;
                data = data.resultListObj;
                if (data != null && data.length > 0) {
                    for (var i = 0; i < data.length; i++) {
                        str = str + "<tr  onclick=\"${ns}selectdocsAssign('" + data[i].userId + "')\" style=\"cursor: pointer;\">" +
                                "<td style=\"text-align: left\"  >" + data[i].emailAddress + " [ " + data[i].firstName + " " + data[i].lastName + "]" + "</td>"
                        "</tr>";
                    }
                }
                str = str + "</tbody></table>";
                var pageArray = calculatePageStartEnd(pageNo, lastpage);
                var pageStart = pageArray[0];
                var pageEnd = pageArray[1];
                var pagingStr = "<table border=\"0\" width=\"100%\" style=\"font-size: 13px\">" +
                        "<tbody>" +
                        "<tr>" +
                        "<td align=\"left\" width=\"70%\">" +
                        "<span class=\"pagination\">" +
                        "<ul>";
                if (pageNo != 1)
                    pagingStr = pagingStr + "<li><a style=\"cursor: pointer;\" onclick='${ns}doSearchBox(\"docsAssign\"," + (pageNo - 1) + ")'>Prev</a></li>";

                for (var j = pageStart; j <= pageEnd; j++) {
                    if (pageNo == j) {
                        pagingStr = pagingStr + "<li><a class=\"active\" style=\"background-color: #ddd\" >" + j + "</a></li>";
                    }
                    else {
                        pagingStr = pagingStr + "<li><a class=\"active\" style=\"cursor: pointer;\" onclick='${ns}doSearchBox(\"docsAssign\"," + (j) + ")'>" + j + "</a></li>";
                    }
                }
                if (pageEnd < lastpage) {
                    pagingStr = pagingStr + "<li><a class=\"active\" style=\"cursor: pointer;\" onclick='${ns}doSearchBox(\"docsAssign\"," + (pageEnd) + ")'>...</a></li>";
                    pagingStr = pagingStr + "<li><a class=\"active\" style=\"cursor: pointer;\" onclick='${ns}doSearchBox(\"docsAssign\"," + (lastpage) + ")'>" + lastpage + "</a></li>";
                }
                if (pageNo != lastpage) {
                    pagingStr = pagingStr + "<li><a style=\"cursor: pointer;\" onclick='${ns}doSearchBox(\"docsAssign\"," + ((pageNo < lastpage) ? (pageNo + 1) : (lastpage)) + ")'>Next</a></li>";
                }
                pagingStr = pagingStr + "</ul>" +


                        "</span>" +
                        "</td>" +
                        "<td align=\"right\" width=\"30%\">" +
                        "</td>" +
                        "</tr>" +
                        "</tbody>" +
                        "</table>";
                if (init) {
                    bootbox.classes("aoe_width");
                    bootbox.dialog(keyBox + "<div id=\"${ns}docsAssignPopupTable\">" + str + pagingStr + "</div>", [{
                        "label": "Close",
                        "class": "btn-danger",
                        "callback": function () {
                        }

                    }]);
                } else {
                    $("#${ns}docsAssignPopupTable").html(str + pagingStr);
                }
            }
        });
    }
    function ${ns}selectdocsAssign(objID) {
        ${ns}doAddDocAssignMappingAjax($("#copyrightId").val(), "COPYRIGHT", objID);
        bootbox.hideAll();
    }
    <%-- end docsAssign Popup --%>

    <%-- start researchProject Popup --%>
    function ${ns}researchProjectPopup(keySearch, init, pageNo) {
        var keyBox = " <div>" +
                "<input type=\"text\"  style=\"display:inline;width:250px\" class=\"form-control\" " +
                " id=\"${ns}keySearch_researchProject\" onkeypress=\"${ns}chk(\'researchProject\',event)\" aria-describedby=\"inputSuccess4Status\" value=\"" + keySearch + "\"/>" +
                "<button onclick=\"${ns}doSearchBox('researchProject',1)\" type=\"button\" class=\"btn btn-success\" style=\"margin-top:-8px;margin-left:5px;\">Search</button>" +
                "</div>";
        var str = "<table class=\"table table-hover table-striped table-bordered table-condensed\" border=\"1\" style=\"font-size: 12px\"> " +
                " <thead>    " +
                "  <tr>" +
                "		<th width=\"10%\" style=\"text-align: center\"><div class=\"th_class\">ปีงบประมาณ</div></th> " +
                "		<th width=\"45%\" style=\"text-align: center\"><div class=\"th_class\">ชื่อโครงการ(T)</div></th> " +
                "		<th width=\"45%\" style=\"text-align: center\"><div class=\"th_class\">ชื่อโครงการ(E)</div></th> " +
                "	</tr> 		" +
                " </thead><tbody>";

        var pageObj = {
            pageSize: PAGE_SIZE_POPUP,
            pageNo: pageNo
        }
        var researchProjectM = {
            keySearch: keySearch,
            paging: pageObj
        }
        ResearchAjax.getResearchProjectList(researchProjectM, {
            callback: function (data) {
                var maxRow = data.maxRow;
                var lastpage = data.lastpage;
                data = data.resultListObj;
                if (data != null && data.length > 0) {
                    for (var i = 0; i < data.length; i++) {
                        str = str + "<tr  onclick=\"${ns}selectresearchProject('" + data[i].researchProjectId + "')\" style=\"cursor: pointer;\">" +
                                "<td style=\"text-align: left\"  >" + data[i].budgetYear + "</td>" +
                                "<td style=\"text-align: left\"  >" + ((data[i].thaiName != null) ? (data[i].thaiName) : ("")) + "</td>" +
                                "<td style=\"text-align: left\"  >" + ((data[i].engName != null) ? (data[i].engName) : ("")) + "</td>" +
                                "</tr>";
                    }
                }

                str = str + "</tbody></table>";
                var pageArray = calculatePageStartEnd(pageNo, lastpage);
                var pageStart = pageArray[0];
                var pageEnd = pageArray[1];
                var pagingStr = "<table border=\"0\" width=\"100%\" style=\"font-size: 13px\">" +
                        "<tbody>" +
                        "<tr>" +
                        "<td align=\"left\" width=\"70%\">" +
                        "<span class=\"pagination\">" +
                        "<ul>";
                if (pageNo != 1)
                    pagingStr = pagingStr + "<li><a style=\"cursor: pointer;\" onclick='${ns}doSearchBox(\"researchProject\"," + (pageNo - 1) + ")'>Prev</a></li>";

                for (var j = pageStart; j <= pageEnd; j++) {
                    if (pageNo == j) {
                        pagingStr = pagingStr + "<li><a class=\"active\" style=\"background-color: #ddd\" >" + j + "</a></li>";
                    }
                    else {
                        pagingStr = pagingStr + "<li><a class=\"active\" style=\"cursor: pointer;\" onclick='${ns}doSearchBox(\"researchProject\"," + (j) + ")'>" + j + "</a></li>";
                    }
                }
                if (pageEnd < lastpage) {
                    pagingStr = pagingStr + "<li><a class=\"active\" style=\"cursor: pointer;\" onclick='${ns}doSearchBox(\"researchProject\"," + (pageEnd) + ")'>...</a></li>";
                    pagingStr = pagingStr + "<li><a class=\"active\" style=\"cursor: pointer;\" onclick='${ns}doSearchBox(\"researchProject\"," + (lastpage) + ")'>" + lastpage + "</a></li>";
                }
                if (pageNo != lastpage) {
                    pagingStr = pagingStr + "<li><a style=\"cursor: pointer;\" onclick='${ns}doSearchBox(\"researchProject\"," + ((pageNo < lastpage) ? (pageNo + 1) : (lastpage)) + ")'>Next</a></li>";
                }
                pagingStr = pagingStr + "</ul>" +


                        "</span>" +
                        "</td>" +
                        "<td align=\"right\" width=\"30%\">" +
                        "</td>" +
                        "</tr>" +
                        "</tbody>" +
                        "</table>";
                if (init) {
                    bootbox.classes("aoe_width");
                    bootbox.dialog(keyBox + "<div id=\"${ns}researchProjectPopupTable\">" + str + pagingStr + "</div>", [{
                        "label": "Close",
                        "class": "btn-danger",
                        "callback": function () {
                        }

                    }]);
                } else {
                    $("#${ns}researchProjectPopupTable").html(str + pagingStr);
                }
            }
        });
    }
    function ${ns}selectresearchProject(objID) {

        ResearchAjax.findResearchProjectById(objID, {
            callback: function (data) {
                $('input[id="journalPaperM.researchProjectId"]').val(objID)
                $("#researchProjectId_autocomplete").val(data.thaiName);
                bootbox.hideAll();
            }
        });
    }
    <%-- end researchProject Popup --%>



    <%-- start workType Popup --%>
    function ${ns}workTypePopup(keySearch, init, pageNo) {
        var keyBox = " <div>" +
                "<input type=\"text\"  style=\"display:inline;width:250px\" class=\"form-control\" " +
                " id=\"${ns}keySearch_workType\" onkeypress=\"${ns}chk(\'workType\',event)\" aria-describedby=\"inputSuccess4Status\" value=\"" + keySearch + "\"/>" +
                "<button onclick=\"${ns}doSearchBox('workType',1)\" type=\"button\" class=\"btn btn-success\" style=\"margin-top:-8px;margin-left:5px;\">Search</button>" +
                "</div>";
        var str = "<table class=\"table table-hover table-striped table-bordered table-condensed\" border=\"1\" style=\"font-size: 12px\"> " +
                " <thead>    " +
                "  <tr>" +
                "		<th width=\"10%\" style=\"text-align: center\"><div class=\"th_class\">รหัสประเภทงาน</div></th> " +
                "		<th width=\"90%\" style=\"text-align: center\"><div class=\"th_class\">ชื่อประเภทงาน</div></th> " +

                "	</tr> 		" +
                " </thead><tbody>";

        var pageObj = {
            pageSize: PAGE_SIZE_POPUP,
            pageNo: pageNo
        }
        var workTypeM = {
            keySearch: keySearch,
            paging: pageObj
        }
        ResearchAjax.getWorkTypeList(workTypeM, {
            callback: function (data) {
                var maxRow = data.maxRow;
                var lastpage = data.lastpage;
                data = data.resultListObj;
                if (data != null && data.length > 0) {
                    for (var i = 0; i < data.length; i++) {
                        str = str + "<tr  onclick=\"${ns}selectworkType('" + data[i].workTypeId + "')\" style=\"cursor: pointer;\">" +
                                "<td style=\"text-align: left\"  >" + data[i].wtCode + "</td>" +
                                "<td style=\"text-align: left\"  >" + ((data[i].wtName != null) ? (data[i].wtName) : ("")) + "</td>" +
                                "</tr>";
                    }
                }

                str = str + "</tbody></table>";
                var pageArray = calculatePageStartEnd(pageNo, lastpage);
                var pageStart = pageArray[0];
                var pageEnd = pageArray[1];
                var pagingStr = "<table border=\"0\" width=\"100%\" style=\"font-size: 13px\">" +
                        "<tbody>" +
                        "<tr>" +
                        "<td align=\"left\" width=\"70%\">" +
                        "<span class=\"pagination\">" +
                        "<ul>";
                if (pageNo != 1)
                    pagingStr = pagingStr + "<li><a style=\"cursor: pointer;\" onclick='${ns}doSearchBox(\"workType\"," + (pageNo - 1) + ")'>Prev</a></li>";

                for (var j = pageStart; j <= pageEnd; j++) {
                    if (pageNo == j) {
                        pagingStr = pagingStr + "<li><a class=\"active\" style=\"background-color: #ddd\" >" + j + "</a></li>";
                    }
                    else {
                        pagingStr = pagingStr + "<li><a class=\"active\" style=\"cursor: pointer;\" onclick='${ns}doSearchBox(\"workType\"," + (j) + ")'>" + j + "</a></li>";
                    }
                }
                if (pageEnd < lastpage) {
                    pagingStr = pagingStr + "<li><a class=\"active\" style=\"cursor: pointer;\" onclick='${ns}doSearchBox(\"workType\"," + (pageEnd) + ")'>...</a></li>";
                    pagingStr = pagingStr + "<li><a class=\"active\" style=\"cursor: pointer;\" onclick='${ns}doSearchBox(\"workType\"," + (lastpage) + ")'>" + lastpage + "</a></li>";
                }
                if (pageNo != lastpage) {
                    pagingStr = pagingStr + "<li><a style=\"cursor: pointer;\" onclick='${ns}doSearchBox(\"workType\"," + ((pageNo < lastpage) ? (pageNo + 1) : (lastpage)) + ")'>Next</a></li>";
                }
                pagingStr = pagingStr + "</ul>" +


                        "</span>" +
                        "</td>" +
                        "<td align=\"right\" width=\"30%\">" +
                        "</td>" +
                        "</tr>" +
                        "</tbody>" +
                        "</table>";
                if (init) {
                    bootbox.classes("aoe_width");
                    bootbox.dialog(keyBox + "<div id=\"${ns}workTypePopupTable\">" + str + pagingStr + "</div>", [{
                        "label": "Close",
                        "class": "btn-danger",
                        "callback": function () {
                        }

                    }]);
                } else {
                    $("#${ns}workTypePopupTable").html(str + pagingStr);
                }
            }
        });
    }
    function ${ns}selectworkType(objID) {

        ResearchAjax.findWorkTypeById(objID, {
            callback: function (data) {
                $('input[id="copyrightM.innovativeWorksType.workTypeId"]').val(objID)
                $("#workTypeId_autocomplete").val(data.wtName);
                bootbox.hideAll();

            }
        });
    }
    <%-- end researchProject Popup --%>

    <%-- start organization Popup --%>
    function ${ns}organizationPopup(keySearch, init, pageNo) {
        var keyBox = " <div>" +
                "<input type=\"text\"  style=\"display:inline;width:250px\" class=\"form-control\" " +
                " id=\"${ns}keySearch_organization\" onkeypress=\"${ns}chk(\'organization\',event)\" aria-describedby=\"inputSuccess4Status\" value=\"" + keySearch + "\"/>" +
                "<button onclick=\"${ns}doSearchBox('organization',1)\" type=\"button\" class=\"btn btn-success\" style=\"margin-top:-8px;margin-left:5px;\">Search</button>" +
                "</div>";
        var str = "<table class=\"table table-hover table-striped table-bordered table-condensed\" border=\"1\" style=\"font-size: 12px\"> " +
                " <thead>    " +
                "  <tr>" +
                    //	"  		<th width=\"20%\" style=\"text-align: center\"><div class=\"th_class\">รหัสกลุ่มวิจัย</div></th>"+ 
                    //	"		<th width=\"40%\" style=\"text-align: center\"><div class=\"th_class\">ชื่อกลุ่มวิจัยภาษาไทย</div></th>  "+
                "		<th width=\"100%\" style=\"text-align: center\"><div class=\"th_class\">ชื่อหน่วยงานมจธ. ที่อยู่นอกสทบ.</div></th> " +
                "	</tr> 		" +
                " </thead><tbody>";

        var pageObj = {
            pageSize: PAGE_SIZE_POPUP,
            pageNo: pageNo
        }
        var organizationM = {
            keySearch: keySearch,
            paging: pageObj
        }
        ResearchAjax.getOrganizationList(organizationM, {
            callback: function (data) {
                var maxRow = data.maxRow;
                var lastpage = data.lastpage;
                data = data.resultListObj;
                if (data != null && data.length > 0) {
                    for (var i = 0; i < data.length; i++) {
                        str = str + "<tr  onclick=\"${ns}selectOrganization('" + data[i].organizationId + "')\" style=\"cursor: pointer;\">" +
                                "<td style=\"text-align: left\"  >" + data[i].orgName + "</td>" +

                                "</tr>";
                    }
                }
                str = str + "</tbody></table>";
                var pageArray = calculatePageStartEnd(pageNo, lastpage);
                var pageStart = pageArray[0];
                var pageEnd = pageArray[1];
                var pagingStr = "<table border=\"0\" width=\"100%\" style=\"font-size: 13px\">" +
                        "<tbody>" +
                        "<tr>" +
                        "<td align=\"left\" width=\"70%\">" +
                        "<span class=\"pagination\">" +
                        "<ul>";
                if (pageNo != 1)
                    pagingStr = pagingStr + "<li><a style=\"cursor: pointer;\" onclick='${ns}doSearchBox(\"organization\"," + (pageNo - 1) + ")'>Prev</a></li>";

                for (var j = pageStart; j <= pageEnd; j++) {
                    if (pageNo == j) {
                        pagingStr = pagingStr + "<li><a class=\"active\" style=\"background-color: #ddd\" >" + j + "</a></li>";
                    }
                    else {
                        pagingStr = pagingStr + "<li><a class=\"active\" style=\"cursor: pointer;\" onclick='${ns}doSearchBox(\"organization\"," + (j) + ")'>" + j + "</a></li>";
                    }
                }
                if (pageEnd < lastpage) {
                    pagingStr = pagingStr + "<li><a class=\"active\" style=\"cursor: pointer;\" onclick='${ns}doSearchBox(\"organization\"," + (pageEnd) + ")'>...</a></li>";
                    pagingStr = pagingStr + "<li><a class=\"active\" style=\"cursor: pointer;\" onclick='${ns}doSearchBox(\"organization\"," + (lastpage) + ")'>" + lastpage + "</a></li>";
                }
                if (pageNo != lastpage) {
                    pagingStr = pagingStr + "<li><a style=\"cursor: pointer;\" onclick='${ns}doSearchBox(\"organization\"," + ((pageNo < lastpage) ? (pageNo + 1) : (lastpage)) + ")'>Next</a></li>";
                }
                pagingStr = pagingStr + "</ul>" +


                        "</span>" +
                        "</td>" +
                        "<td align=\"right\" width=\"30%\">" +
                        "</td>" +
                        "</tr>" +
                        "</tbody>" +
                        "</table>";
                if (init) {
                    bootbox.classes("aoe_width");
                    bootbox.dialog(keyBox + "<div id=\"${ns}organizationPopupTable\">" + str + pagingStr + "</div>", [{
                        "label": "Close",
                        "class": "btn-danger",
                        "callback": function () {
                        }

                    }]);
                } else {
                    $("#${ns}organizationPopupTable").html(str + pagingStr);
                }
            }
        });
    }
    function ${ns}selectOrganization(objID) {
        ResearchAjax.findOrganizationById(objID, {
            callback: function (data) {
                $('input[id="${ns}copyrightCreator_organizationId"]').val(objID);
                $("#organizationId_assign_autocomplete").val(data.orgName);


                bootbox.hideAll();
            }
        });
    }
    <%-- end organization Popup --%>

    function ${ns}confirmDelete(_urlDelete) {

        var agree = confirm(" ต้องการลบข้อมูลหรือไม่? ");
        //alert(_urlDelete)
        if (agree) {
            window.location.href = _urlDelete;
            return true;
        }
        else {
            return false;
        }
    }
    function ${ns}confirmUndo(_urlUndo){
        var agree=confirm(" ต้องการ Undo ข้อมูลหรือไม่?");
        if (agree){
            window.location.href = _urlUndo;
            return true ;
        }
        else{
            return false ;
        }
    }
</script>
</body>
</html>
