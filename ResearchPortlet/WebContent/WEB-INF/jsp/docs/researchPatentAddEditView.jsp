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
<form:form id="patentForm" modelAttribute="patentForm" method="post" name="patentForm" action="${formAction}"
           enctype="multipart/form-data">
    <fieldset style="font-family: sans-serif;padding-top:5px">
        <input type="hidden" name="command" id="${ns}common" value="${patentForm.command}"/>
        <input type="hidden" name="mode" id="${ns}mode" value="${patentForm.mode}"/>
        <form:hidden path="patentM.inventionId" id="inventionId"/>
        <c:set var="userIdAsString">${user.userId}</c:set>
        <c:set var="havePermissionOnDocs" value="${patentForm.mode=='edit' && patentForm.patentM.docType=='DRAFT' 
    								&& ( userIdAsString == patentForm.patentM.createdBy 
                       				|| userIdAsString==patentForm.patentM.updatedBy || patentForm.patentM.isdocAssign ) }"></c:set>
        <div style="border: 1px solid #FFC299;background: #F9F9F9;padding: 10px">
            <div class="accordion" id="accordion2">
                <div class="accordion-group">
                    <div class="accordion-heading">
                        <a href="${list}" style="padding-right: 20px;">Back</a>
                        <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion2"
                           href="#collapseOne">
                            ข้อมูลสิ่งประดิษฐ์ / การออกแบบผลิตภัณฑ์
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
                                        <label><span>ปีงบประมาณ:</span>
                                        </label>
                                    </td>
                                    <td width="30%">

                                        <form:input path="patentM.budgetYear" maxlength="4" cssStyle="width:100px"
                                                    cssClass="form-control"/>
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

                                        <form:textarea path="patentM.thaiName" cssStyle="width:500px" rows="2"/>
                                    </td>
                                </tr>
                                <tr valign="top">
                                    <td align="right" width="20%">
                        <span><label>ขื่อโครงการ(E):<span style="color: red;font-size: 20;"> </span>
                        </label></span>
                                    </td>
                                    <td width="80%" colspan="3">

                                        <form:textarea path="patentM.engName" cssStyle="width:500px" rows="2"/>
                                    </td>
                                </tr>
                                <tr>
                                    <td align="right" width="20%">
                        <span><label>มาจากโครงการวิจัย<span style="color: red;font-size: 20;"> </span>
                        </label></span>
                                    </td>
                                    <td width="80%" colspan="3">
                                        <div>
                                            <form:hidden path="patentM.researchProjectId"/>
                                            <input type="text" style="background-color: 
                    rgb(250, 250, 198);width:350px" class="form-control"
                                                   id="researchProjectId_autocomplete" placeholder=""
                                                   value="${patentForm.patentM.researchProjectShow}"/>
                                            <button style="margin-top: -8px"
                                                    onclick="${ns}researchProjectPopup('',true,1)"
                                                    type="button" class="btn btn-warning btn-small">...
                                            </button>
                                                <%--
                   <span id="researchProjectId_assignShow">${patentForm.patentM.researchProjectShow} </span>
              
                      <form:input path="patentM.researchProjectId" cssStyle="display:inline;background-color: rgb(250, 250, 198);width:100px" cssClass="form-control"/>
                       --%>
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td align="right" width="20%">
                        <span><label>ประเภทคำขอ:<span style="color: red;font-size: 20;"> </span>
                        </label></span>
                                    </td>
                                    <td width="80%" colspan="3">
                                        <div>
                                            <table style="width:100%">
                                                <tr>

                                                    <td width="40%"><form:radiobutton path="patentM.rpoposeType"
                                                                                      value="1"/> สิทธิบัตร
                                                    </td>
                                                    <td width="60%"><form:radiobutton path="patentM.rpoposeType"
                                                                                      value="2"/> อนุสิทธิบัตร
                                                    </td>
                                                </tr>

                                            </table>
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td align="right" width="20%">
                        <span><label>ประเภทสิ่งประดิษฐ์:<span style="color: red;font-size: 20;"> </span>
                        </label></span>
                                    </td>
                                    <td width="80%" colspan="3">
                                        <div>
                                            <table style="width:100%">

                                                <tr>
                                                    <td width="40%"><form:radiobutton path="patentM.inventionType"
                                                                                      value="1"/> การประดิษฐ์
                                                    </td>
                                                    <td width="60%"><form:radiobutton path="patentM.inventionType"
                                                                                      value="2"/> การออกแบบผลิตภัณฑ์
                                                    </td>
                                                </tr>
                                            </table>
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td align="right" width="20%">
                        <span><label>หน่วยงาน:<span style="color: red;font-size: 20;"> </span>
                        </label></span>
                                    </td>
                                    <td width="80%" colspan="3">
                                        <div>
                                            <form:hidden path="patentM.integerernalOrganizationId"/>
                                            <input type="text" style="background-color: rgb(250, 250, 198);width:350px"
                                                   class="form-control"
                                                   id="integerernalOrganizationId_assign_autocomplete"
                                                   value="${patentForm.patentM.integerernalOrganizationShow}"
                                                   placeholder=""/>
                                            <button style="margin-top: -8px"
                                                    onclick="${ns}organizationPopup('',true,1)"
                                                    type="button" class="btn btn-warning btn-small">...
                                            </button>
                                                <%--  <span id="integerernalOrganizationIdShow">${patentForm.patentM.integerernalOrganizationShow}</span>
                 --%>
                                                <%--
                       <form:input path="patentM.IntegerernalOrganizationId" cssStyle="display:inline;background-color: rgb(250, 250, 198);width:100px" cssClass="form-control"/>
                        --%>

                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td align="right" width="20%">
                        <span><label>กลุ่มวิจัย:<span style="color: red;font-size: 20;"> </span>
                        </label></span>
                                    </td>
                                    <td width="80%" colspan="3">
                                        <div>
                                            <form:hidden path="patentM.researchGroup"/>
                                            <input type="text" style="background-color: 
                    rgb(250, 250, 198);width:350px" class="form-control"
                                                   id="researchGroup_assign_autocomplete" placeholder=""
                                                   value="${patentForm.patentM.researchGroupShow}"/>
                                            <button style="margin-top: -8px"
                                                    onclick="${ns}researchGroupPopup('',true,1)"
                                            " type="button" class="btn btn-warning btn-small">...</button>
                                                <%--
                   <span id="researchGroup_assignShow">${patentForm.patentM.researchGroupShow} </span>
                    --%>
                                                <%-- 
                      <form:input path="patentM.researchGroup" cssStyle="display:inline;background-color: rgb(250, 250, 198);width:100px" cssClass="form-control"/>
                     --%>
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td align="right" width="20%">
                        <span><label>เลขที่คำขอ:<span style="color: red;font-size: 50;"> </span>
                        </label></span>
                                    </td>
                                    <td width="80%" colspan="3">
                                        <div>

                                            <form:input path="patentM.proposeNo" cssStyle="width:100px"
                                                        cssClass="form-control"/>
                                        </div>
                                    </td>
                                </tr>

                                <tr>
                                    <td align="right" width="20%">
                                        <label><span>วันที่ยื่นคำขอ:<span style="color: red;font-size: 20;"></span> 
                        </span> </label>
                                    </td>
                                    <td width="30%">
                     <span>
                       <%-- 
                       <fmt:formatDate type="time" value="${patentForm.patentM.proposeDate}"   pattern="dd/MM/yyyy" var="proposeDate" />
                        --%>
                      <input type="text" value="${patentForm.proposeDate}" readonly="readonly" id="proposeDate"
                             name="proposeDate" style="width:75px" placeholder="dd/mm/yyyy" class="form-control"/>
                    </span>
                                    </td>
                                    <td align="left" width="20%" colspan="2">
                        <span style="font-size: 16px"> วันที่ยื่นคำขอให้ตรวจสอบ:<span
                                style="color: red;font-size: 20;"></span> 
                       <%--
                          <fmt:formatDate type="time" value="${patentForm.patentM.verifyDate}"   pattern="dd/MM/yyyy" var="verifyDate" />
                           --%>
                      <input type="text" value="${patentForm.verifyDate}" readonly="readonly" id="verifyDate"
                             name="verifyDate"  style="width:75px" placeholder="dd/mm/yyyy" class="form-control"/>
                        </span>
                                    </td>
                                </tr>
                                <tr>
                                    <td align="right" width="20%">
                                        <label><span>วันที่ประกาศโฆษณา:<span style="color: red;font-size: 20;"></span> 
                        </span> </label>
                                    </td>
                                    <td width="30%">
                     <span>
                     <%--
                        <fmt:formatDate type="time" value="${patentForm.patentM.announcementDate}"   pattern="dd/MM/yyyy" var="announcementDate" />
                         --%>
                      <input type="text" value="${patentForm.announcementDate}" readonly="readonly"
                             id="announcementDate" name="announcementDate" style="width:75px"
                             placeholder="dd/mm/yyyy" class="form-control"/>
                    </span>
                                    </td>
                                    <td align="left" width="20%" colspan="2">
                        <span style="font-size: 16px"> วันที่ชำระค่าประกาศโฆษณา:<span
                                style="color: red;font-size: 20;"></span> 
                        <%--
                         <fmt:formatDate type="time" value="${patentForm.patentM.announcementPayDate}"   pattern="dd/MM/yyyy" var="announcementPayDate" />
                          --%>
                      <input type="text" value="${patentForm.announcementPayDate}" readonly="readonly"
                             id="announcementPayDate" name="announcementPayDate" style="width:75px" placeholder="dd/mm/yyyy"
                              class="form-control"/>
                        </span>
                                    </td>
                                </tr>
                                <tr>
                                    <td align="right" width="20%">
                                        <label><span>วันที่หนังสือจากกรมทรัพย์ฯ:<span
                                                style="color: red;font-size: 20;"></span> 
                        </span> </label>
                                    </td>
                                    <td width="30%">
                     <span>
                    <%--
                       <fmt:formatDate type="time" value="${patentForm.patentM.proposeBookDate}"   pattern="dd/MM/yyyy" var="proposeBookDate" />
                        --%>
                      <input type="text" value="${patentForm.proposeBookDate}" readonly="readonly" id="proposeBookDate"
                             name="proposeBookDate" style="width:75px" placeholder="dd/mm/yyyy" class="form-control"/>
                    </span>
                                    </td>
                                    <td align="left" width="20%" colspan="2">
                        <span style="font-size: 16px"> วันที่รับหนังสือ:<span style="color: red;font-size: 20;"></span> 
                       <%--
                          <fmt:formatDate type="time" value="${patentForm.patentM.receiveDate}"   pattern="dd/MM/yyyy" var="receiveDate" />
                           --%>
                      <input type="text" value="${patentForm.receiveDate}" readonly="readonly" id="receiveDate"
                             name="receiveDate" style="width:75px" placeholder="dd/mm/yyyy" class="form-control"/>
                        </span>
                                    </td>
                                </tr>
                                <tr>
                                    <td align="right" width="20%">
                                        <label><span>ผลการตรวจสอบ:<span style="color: red;font-size: 20;"></span> 
                        </span> </label>
                                    </td>
                                    <td width="50%" colspan="3">
                                        <div>
                                            <table style="width:100%">
                                                <tr>

                                                    <td width="40%"><form:radiobutton path="patentM.result" value="1"/>
                                                        รับจด
                                                    </td>
                                                    <td width="60%"><form:radiobutton path="patentM.result" value="2"/>อยู่ระหว่างการดำเนินงาน</td>
                                                </tr>
                                                <tr>
                                                    <td><form:radiobutton path="patentM.result"
                                                                          value="3"/>ไม่รับจดเพราะ
                                                    </td>
                                                    <td><input type="text" id="result_cause" class="form-control"></td>
                                                </tr>
                                            </table>
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td align="right" width="20%">
                        <span><label>เลขที่สิทธิบัตร/อนุสิทธิบัตร:<span style="color: red;font-size: 20;"> </span>
                        </label></span>
                                    </td>
                                    <td width="80%" colspan="3">
                                        <div>

                                            <form:input path="patentM.patentNo" cssStyle="" cssClass="form-control"/>
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td align="right" width="20%">
                                        <label><span>วันที่ออกสิทธิบัตร/อนุสิทธิบัตร:<span
                                                style="color: red;font-size: 20;"></span> 
                        </span> </label>
                                    </td>
                                    <td width="30%">
                     <span>
                       <%--
                        <fmt:formatDate type="time" value="${patentForm.patentM.patentDate}"   pattern="dd/MM/yyyy" var="patentDate" />
                         --%>
                      <input type="text" value="${patentForm.patentDate}" readonly="readonly" id="patentDate"
                             name="patentDate" style="width:75px" placeholder="dd/mm/yyyy" class="form-control"/>
                    </span>
                                    </td>
                                    <td align="left" width="20%" colspan="2">
                        <span style="font-size: 16px"> วันที่ชำระค่าธรรมเนียม:<span
                                style="color: red;font-size: 20;"></span> 
                       <%--
                         <fmt:formatDate type="time" value="${patentForm.patentM.payDate}"   pattern="dd/MM/yyyy" var="payDate" />
                          --%>
                      <input type="text" value="${patentForm.payDate}" readonly="readonly" id="payDate" name="payDate"
                             style="width:75px" placeholder="dd/mm/yyyy" class="form-control"/>
                        </span>
                                    </td>
                                </tr>

                                <tr valign="top">
                                    <td align="right" width="20%">
                        <span><label>หมายเหตุ:<span style="color: red;font-size: 20;"> </span>
                        </label></span>
                                    </td>
                                    <td width="80%" colspan="3">

                                        <form:textarea path="patentM.remark" cssStyle="width:500px" rows="2"/>
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
              <span id="${ns}doctype_assign_list">
               <c:if test="${not empty patentForm.patentM.docAssignMappings}">
                   <table class="table table-hover table-striped table-bordered table-condensed" border="0"
                          style="width:30%;font-size: 12px">
                       <c:forEach items="${patentForm.patentM.docAssignMappings}" var="docAssignMapping"
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
                                        <form:hidden path="patentM.docType"/>
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
                                                <c:if test="${patentForm.patentM.flag=='0'}">
                                                    <button type="button" class="btn" onclick='return ${ns}confirmUndo("
                                                    <portlet:actionURL>
                                                        <portlet:param name="action" value="undoItem"/>
                                                    <portlet:param name="inventionId">
                                                    <jsp:attribute name="value">
                                                        <c:out value="${patentForm.patentM.inventionId}"/>
                                                    </jsp:attribute>
                                                    </portlet:param>
                                                    </portlet:actionURL>")'> Undo</button>
                                                </c:if>
                                                <button class="btn btn-danger" type="button"
                                                        onclick='return ${ns}confirmDelete("
                                                            <portlet:actionURL>
                                                                <portlet:param name="action" value="delete"/>
                                                                <portlet:param name="inventionId">
                                                                    <jsp:attribute name="value">
                                                                        <c:out value="${patentForm.patentM.inventionId}"/>
                                                                    </jsp:attribute>
                                                                </portlet:param>
                                                            </portlet:actionURL>")'>
                                                    <c:if test="${patentForm.patentM.flag=='1'}">
                                                        ลบ
                                                    </c:if>
                                                    <c:if test="${patentForm.patentM.flag=='0'}">
                                                        ลบ ถาวร
                                                    </c:if>
                                                </button>
                                            </c:if>
                                        </c:if>
                                        <c:if test="${mode=='add' || mode =='copy'}">
                                            <button type="button" onclick="${ns}doSubmitForm('DRAFT')"
                                                    class="btn btn-primary">Save as Draft
                                            </button>
                                            <%--	<button type="button" onclick="${ns}doSubmitForm('PUBLISH')" class="btn btn-primary">Submit</button> --%>
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
                                        <div id="${ns}element_patentCreator"
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
                                                                   id="${ns}patentCreator_mode"/>
                                                            <input type="hidden"
                                                                   id="${ns}patentCreator_creatorItemList"/>
                                                            <input type="text"
                                                                   id="${ns}patentCreator_creatorId"
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
                                                                   id="${ns}patentCreator_organizationId"/>
                                                            <input type="text" style="background-color: 
                    rgb(250, 250, 198);" class="form-control"
                                                                   id="patentCreator_organizationId_assign_autocomplete"
                                                                   placeholder=""/>
                                                            <button id="${ns}patentCreatorOrganizationListAll"
                                                                    style="margin-top: -8px;display: none" onclick="
                                                                ${ns}patentCreatorOrganizationPopup('',true,1)"
                                                                    type="button" class="btn btn-warning btn-small">...
                                                            </button>
                                                                <%--
                   <span id="patentCreator_organizationId_assignShow"></span>
                    --%>
                                                        </div>
                                                    </td>
                                                </tr>
                                                <c:if test="${havePermissionOnDocs}">
                                                    <tr>
                                                        <td align="center" colspan="4" width="20%">
                                                            <button type="button" onclick="
                                                                ${ns}doSubmitPatentCreatorAjax()"
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
                 <c:if test="${patentForm.mode=='edit'}">
                     <c:if test="${havePermissionOnDocs}">
                         <button onclick="${ns}displayPatentCreator('add','0')" type="button"
                                 class="btn btn-primary">Add
                         </button>
                     </c:if>
                 </c:if>
               </span>
                                    </td>
                                </tr>
                                </tbody>
                            </table>  
                  <span id="${ns}patentCreator_item_list">
            <table class="table table-hover table-striped table-bordered table-condensed" border="1"
                   style="font-size: 12px">
                <thead>
                <tr> <!-- 50 42+8 20+71+9 -->
                    <th width="5%" style="text-align: center">
                        <div class="th_class">#</div>
                    </th>
                    <th width="15%" style="text-align: center">
                        <div class="th_class">บุคคล/หน่วยงาน</div>
                    </th>
                    <th width="37%" style="text-align: center">
                        <div class="th_class">รหัสประชาชน</div>
                    </th>
                    <th width="33%" style="text-align: center">
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
                <c:if test="${not empty patentForm.patentM.patentCreators}">
                    <c:forEach items="${patentForm.patentM.patentCreators}" var="patentCreator" varStatus="loop">

                        <tr style="cursor: pointer;">
                            <td onclick='
                                ${ns}doEditPatentCreatorAjax("${patentCreator.inventionId}","${patentCreator.creatorItemList}")'
                                style="text-align: left">${loop.index+1}</td>
                            <td onclick='
                                ${ns}doEditPatentCreatorAjax("${patentCreator.inventionId}","${patentCreator.creatorItemList}")'
                                style="text-align: left">
                                <c:if test="${patentCreator.creatorType=='1'}">
                                    บุคลากร
                                </c:if>
                                <c:if test="${patentCreator.creatorType=='2'}">
                                    หน่วยงาน
                                </c:if>
                            </td>
                            <td onclick='
                                ${ns}doEditPatentCreatorAjax("${patentCreator.inventionId}","${patentCreator.creatorItemList}")'
                                style="text-align: left">
                                <c:if test="${patentCreator.creatorType=='1'}">
                                    ${patentCreator.creatorId}
                                </c:if>
                            </td>
                            <td onclick='
                                ${ns}doEditPatentCreatorAjax("${patentCreator.inventionId}","${patentCreator.creatorItemList}")'
                                style="text-align: left">
                                <c:if test="${patentCreator.creatorType=='2'}">

                                    ${patentCreator.organization.orgName}
                                </c:if>
                            </td>
                            <c:if test="${havePermissionOnDocs}">
                                <td style="text-align: center">
                                    <button onclick='
                                        ${ns}doEditPatentCreatorAjax("${patentCreator.inventionId}","${patentCreator.creatorItemList}")'
                                            class="btn btn-small" type="button">แก้ใข
                                    </button>
                                    <button onclick='
                                        ${ns}doDeletePatentCreatorAjax("${patentCreator.inventionId}","${patentCreator.creatorItemList}")'
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
                           href="#collapseThree">
                            วันที่ยื่นแก้ใขคำขอ
                        </a>
                    </div>
                    <div id="collapseThree" class="accordion-body collapse" style="height: 0px;">
                        <div class="accordion-inner">
                            <table border="0" width="100%" style="font-size: 13px">
                                <tbody>
                                <tr>
                                    <td align="center" width="100%" colspan="2">
                                        <div id="${ns}element_patentEditDate"
                                             style="border: 1px solid #FFC299;background: #F9F9F9;padding: 10px;display: none">
                                            <table border="0" width="100%"
                                                   style="border: 0px solid #FFC299;font-size:13px">
                                                <tr>
                                                    <td align="right" width="30%">
                        		<span>
                        			<label>Edit Date:<span style="color: red;font-size: 50;"> </span>
                                    </label>
                        		</span>
                                                    </td>
                                                    <td width="70%" colspan="3">
                                                        <div>
                                                            <input type="hidden"
                                                                   id="${ns}patentEditDate_mode"/>
                                                            <input type="hidden"
                                                                   id="${ns}patentEditDate_editItemList"/>

                                                            <input type="text"
                                                                   id="${ns}patentEditDate_editDate"
                                                                   style="width:75px" readonly="readonly"
                                                                   placeholder="dd/mm/yyyy" class="form-control">

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
                                                            <textarea id="${ns}patentEditDate_remark"
                                                                      rows="2" style="width:500px"></textarea>
                                                        </div>
                                                    </td>
                                                </tr>
                                                <c:if test="${havePermissionOnDocs}">
                                                    <tr>
                                                        <td align="center" colspan="4" width="20%">
                                                            <button type="button" onclick="
                                                                ${ns}doSubmitPatentEditDateAjax()"
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
                   <c:if test="${patentForm.mode=='edit'}">
                       <c:if test="${havePermissionOnDocs}">
                           <button onclick="${ns}displayPatentEditDate('add','0')" type="button"
                                   class="btn btn-primary">Add
                           </button>
                       </c:if>
                   </c:if>
               </span>
                                    </td>
                                </tr>

                                </tbody>
                            </table>  
                   <span id="${ns}patentEditDate_item_list">
           <table class="table table-hover table-striped table-bordered table-condensed" border="1"
                  style="font-size: 12px">
               <thead>
               <tr> <!-- 21+79 -->
                   <th width="5%" style="text-align: center">
                       <div class="th_class">#</div>
                   </th>
                   <th width="8%" style="text-align: center">
                       <div class="th_class">Edit Date</div>
                   </th>
                   <th width="77%" style="text-align: center">
                       <div class="th_class">Remark</div>
                   </th>
                   <c:if test="${havePermissionOnDocs}">
                       <th width="10%">
                           <div class="th_class"></div>
                       </th>
                   </c:if>
               </tr>
               </thead>
               <tbody>
               <c:if test="${not empty patentForm.patentM.patentEditDates}">
                   <c:forEach items="${patentForm.patentM.patentEditDates}" var="patentEditDate" varStatus="loop">
                       <tr style="cursor: pointer;">
                           <td onclick='
                               ${ns}doEditPatentEditDateAjax("${patentEditDate.inventionId}","${patentEditDate.editItemList}")'
                               style="text-align: left">${loop.index+1}</td>
                           <td onclick='
                               ${ns}doEditPatentEditDateAjax("${patentEditDate.inventionId}","${patentEditDate.editItemList}")'
                               style="text-align: left">${patentEditDate.editDateShow}</td>
                           <td onclick='
                               ${ns}doEditPatentEditDateAjax("${patentEditDate.inventionId}","${patentEditDate.editItemList}")'
                               style="text-align: left">${patentEditDate.remark}</td>
                           <c:if test="${havePermissionOnDocs}">
                               <td>
                                   <button onclick='
                                       ${ns}doEditPatentEditDateAjax("${patentEditDate.inventionId}","${patentEditDate.editItemList}")'
                                           class="btn btn-small" type="button">แก้ใข
                                   </button>
                                   <button onclick='
                                       ${ns}doDeletePatentEditDateAjax("${patentEditDate.inventionId}","${patentEditDate.editItemList}")'
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
                           href="#collapseFour">
                            ชำระค่าธรรมเนียม
                        </a>
                    </div>
                    <div id="collapseFour" class="accordion-body collapse" style="height: 0px;">
                        <div class="accordion-inner">
                            <table border="0" width="100%" style="font-size: 13px">
                                <tbody>
                                <tr>
                                    <td align="center" width="100%" colspan="2">
                                        <div id="${ns}element_patentFeePayment"
                                             style="border: 1px solid #FFC299;background: #F9F9F9;padding: 10px;display: none">
                                            <table border="0" width="100%"
                                                   style="border: 0px solid #FFC299;font-size:13px">
                                                <tr>
                                                    <td align="right" width="30%">
                        		<span>
                        			<label>Years:<span style="color: red;font-size: 50;"> </span>
                                    </label>
                        		</span>
                                                    </td>
                                                    <td width="70%" colspan="3">
                                                        <div>
                                                            <input type="hidden"
                                                                   id="${ns}patentFeePayment_mode"/>
                                                            <input type="hidden"
                                                                   id="${ns}patentFeePayment_itemList"/>

                                                            <input type="text"
                                                                   id="${ns}patentFeePayment_years"
                                                                   style="width:75px" class="form-control"/>

                                                        </div>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td align="right" width="30%">
                        		<span>
                        			<label>Date:<span style="color: red;font-size: 50;"> </span>
                                    </label>
                        		</span>
                                                    </td>
                                                    <td width="70%" colspan="3">
                                                        <div>
                                                            <input type="text"
                                                                   id="${ns}patentFeePayment_date"
                                                                   style="width:75px" readonly="readonly"
                                                                   placeholder="dd/mm/yyyy" class="form-control">

                                                        </div>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td align="right" width="30%">
                        		<span>
                        			<label>Amount:<span style="color: red;font-size: 50;"> </span>
                                    </label>
                        		</span>
                                                    </td>
                                                    <td width="70%" colspan="3">
                                                        <div>
                                                            <input type="text"
                                                                   id="${ns}patentFeePayment_amount"
                                                                   style="width:75px" class="form-control">
                                                        </div>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td align="right" width="30%">
                        		<span>
                        			<label>is Pay?:<span style="color: red;font-size: 50;"> </span>
                                    </label>
                        		</span>
                                                    </td>
                                                    <td width="70%" colspan="3">
                                                        <div>
                                                            <input type="checkbox"
                                                                   id="${ns}patentFeePayment_isPay"
                                                                   class="form-control">
                                                        </div>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td align="right" width="30%">
                        		<span>
                        			<label>remark:<span style="color: red;font-size: 50;"> </span>
                                    </label>
                        		</span>
                                                    </td>
                                                    <td width="70%" colspan="3">
                                                        <div>
                                                            <textarea id="${ns}patentFeePayment_remark"
                                                                      rows="2" style="width:500px"></textarea>
                                                        </div>
                                                    </td>
                                                </tr>
                                                <c:if test="${havePermissionOnDocs}">
                                                    <tr>
                                                        <td align="center" colspan="4" width="20%">
                                                            <button type="button" onclick="
                                                                ${ns}doSubmitPatentFeePaymentAjax()"
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
                  <c:if test="${patentForm.mode=='edit'}">
                      <c:if test="${havePermissionOnDocs}">
                          <button onclick="${ns}displayPatentFeePayment('add','0')" type="button"
                                  class="btn btn-primary">Add
                          </button>
                      </c:if>
                  </c:if>
               </span>
                                    </td>
                                </tr>
                                </tbody>
                            </table> 
                 <span id="${ns}patentFeePayment_item_list">
                  <table class="table table-hover table-striped table-bordered table-condensed" border="1"
                         style="font-size: 12px">

                      <thead>
                      <tr> <!-- 57+39+38 -->
                          <th width="5%" style="text-align: center">
                              <div class="th_class">#</div>
                          </th>
                          <th width="5%" style="text-align: center">
                              <div class="th_class">Years</div>
                          </th>
                          <th width="5%" style="text-align: center">
                              <div class="th_class">Date</div>
                          </th>
                          <th width="10%" style="text-align: center;">
                              <div class="th_class">Amount</div>
                          </th>
                          <th width="10%" style="text-align: center">
                              <div class="th_class">is Pay?</div>
                          </th>
                          <th width="50%" style="text-align: center">
                              <div class="th_class">remark</div>
                          </th>
                          <c:if test="${havePermissionOnDocs}">
                              <th width="10%">
                                  <div class="th_class"></div>
                              </th>
                          </c:if>
                      </tr>
                      </thead>
                      <tbody>
                      <c:if test="${not empty patentForm.patentM.patentFeePayments}">
                          <c:forEach items="${patentForm.patentM.patentFeePayments}" var="patentFeePayment"
                                     varStatus="loop">
                              <tr style="cursor: pointer;">
                                  <td onclick='
                                      ${ns}doEditPatentFeePaymentAjax("${patentFeePayment.inventionId}","${patentFeePayment.itemList}","${patentFeePayment.years}")'
                                      style="text-align: left">${loop.index+1}</td>
                                  <td onclick='
                                      ${ns}doEditPatentFeePaymentAjax("${patentFeePayment.inventionId}","${patentFeePayment.itemList}","${patentFeePayment.years}")'
                                      style="text-align: center">${patentFeePayment.years}</td>
                                  <td onclick='
                                      ${ns}doEditPatentFeePaymentAjax("${patentFeePayment.inventionId}","${patentFeePayment.itemList}","${patentFeePayment.years}")'
                                      style="text-align: center">
                                          <%--
                <fmt:formatDate type="time" value="${patentFeePayment.date}"   pattern="dd/MM/yyyy" var="dateShow" />
                 --%>
                                          ${patentFeePayment.dateShow}
                                  </td>
                                  <td onclick='
                                      ${ns}doEditPatentFeePaymentAjax("${patentFeePayment.inventionId}","${patentFeePayment.itemList}","${patentFeePayment.years}")'
                                      style="text-align: right">
                                      <fmt:formatNumber type="number" pattern="###,###,###.00"
                                                        value="${patentFeePayment.amount}" var="amount"/>
                                          ${amount}
                                  </td>
                                  <td onclick='
                                      ${ns}doEditPatentFeePaymentAjax("${patentFeePayment.inventionId}","${patentFeePayment.itemList}","${patentFeePayment.years}")'
                                      style="text-align: center">
                                      <c:if test="${patentFeePayment.ispay=='1'}">
                                          ชำระแล้ว
                                      </c:if>
                                      <c:if test="${patentFeePayment.ispay!='1'}">
                                          ยังไม่ชำระ
                                      </c:if>
                                  </td>
                                  <td onclick='
                                      ${ns}doEditPatentFeePaymentAjax("${patentFeePayment.inventionId}","${patentFeePayment.itemList}","${patentFeePayment.years}")'
                                      style="text-align: left">${patentFeePayment.remark}</td>
                                  <c:if test="${havePermissionOnDocs}">
                                      <td style="text-align: left">
                                          <button onclick='
                                              ${ns}doEditPatentFeePaymentAjax("${patentFeePayment.inventionId}","${patentFeePayment.itemList}","${patentFeePayment.years}")'
                                                  class="btn btn-small" type="button">แก้ใข
                                          </button>
                                          <button onclick='
                                              ${ns}doDeletePatentFeePaymentAjax("${patentFeePayment.inventionId}","${patentFeePayment.itemList}","${patentFeePayment.years}")'
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
                           href="#collapseFive">
                            ผู้ทรงสิทธิร่วม
                        </a>
                    </div>
                    <div id="collapseFive" class="accordion-body collapse" style="height: 0px;">
                        <div class="accordion-inner">
                            <table border="0" width="100%" style="font-size: 13px">
                                <tbody>
                                <tr>
                                    <td align="center" width="100%" colspan="2">
                                        <div id="${ns}element_patentRightholder"
                                             style="border: 1px solid #FFC299;background: #F9F9F9;padding: 10px;display: none">
                                            <table border="0" width="100%"
                                                   style="border: 0px solid #FFC299;font-size:13px">
                                                <tr>
                                                    <td align="right" width="30%">
                        		<span>
                        			<label>หน่วยงาน:<span style="color: red;font-size: 50;"> </span>
                                    </label>
                        		</span>
                                                    </td>
                                                    <td width="70%" colspan="3">
                                                        <div>

                                                            <input type="hidden"
                                                                   id="${ns}patentRightholder_organizationId"/>
                                                            <input type="text" style="background-color: 
                    rgb(250, 250, 198);" class="form-control"
                                                                   id="patentRightholder_organizationId_assign_autocomplete"
                                                                   placeholder=""/>
                                                            <button id="${ns}patentRightholderOrganizationListAll"
                                                                    style="margin-top: -8px;" onclick="
                                                                ${ns}patentRightholderOrganizationPopup('',true,1)"
                                                                    type="button" class="btn btn-warning btn-small">...
                                                            </button>
                                                                <%--
                   <span id="patentRightholder_organizationId_assignShow"></span>
                    --%>
                                                        </div>
                                                    </td>
                                                </tr>
                                                <c:if test="${havePermissionOnDocs}">
                                                    <tr>
                                                        <td align="center" colspan="4" width="20%">
                                                            <button type="button" onclick="
                                                                ${ns}doSubmitPatentRightholderAjax()"
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
                 <c:if test="${patentForm.mode=='edit'}">
                     <c:if test="${havePermissionOnDocs}">
                         <button onclick="${ns}displayPatentRightholder('add','0')" type="button"
                                 class="btn btn-primary">Add
                         </button>
                     </c:if>
                 </c:if>
               </span>
                                    </td>
                                </tr>
                                </tbody>
                            </table>  
                <span id="${ns}patentRightholder_item_list"> 
           <table class="table  table-hover table-striped table-bordered table-condensed" border="1"
                  style="font-size: 12px">
               <thead>
               <tr> <!-- 50 42+8 -->
                   <th width="5%" style="text-align: center">
                       <div class="th_class">#</div>
                   </th>
                   <th width="90%" style="text-align: center">
                       <div class="th_class">หน่วยงาน</div>
                   </th>
                   <c:if test="${havePermissionOnDocs}">
                       <th width="5%">
                           <div class="th_class"></div>
                       </th>
                   </c:if>
               </tr>
               </thead>
               <tbody>
               <c:if test="${not empty patentForm.patentM.patentRightholders}">
                   <c:forEach items="${patentForm.patentM.patentRightholders}" var="patentRightholder" varStatus="loop">
                       <tr style="cursor: pointer;">
                           <td style="text-align: left">${loop.index+1}</td>
                           <td style="text-align: left">${patentRightholder.organization.orgName}</td>
                           <c:if test="${havePermissionOnDocs}">
                               <td style="text-align: center">
                                       <%--
                <button onclick='${ns}doEditPatentRightholderAjax("${patentRightholder.inventionId}","${patentRightholder.rightholderItemList}")' class="btn btn-small" type="button">แก้ใข</button>
                 --%>
                                   <button onclick='
                                       ${ns}doDeletePatentRightholderAjax("${patentRightholder.inventionId}","${patentRightholder.rightholderItemList}")'
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
                           href="#collapseSix">
                            ผู้ได้รับถ่ายทอดเทคโนโลยี
                        </a>
                    </div>
                    <div id="collapseSix" class="accordion-body collapse" style="height: 0px;">
                        <div class="accordion-inner">
                            <table border="0" width="100%" style="font-size: 13px">
                                <tbody>
                                <tr>
                                    <td align="center" width="100%" colspan="2">
                                        <div id="${ns}element_patentInherited"
                                             style="border: 1px solid #FFC299;background: #F9F9F9;padding: 10px;display: none">
                                            <table border="0" width="100%"
                                                   style="border: 0px solid #FFC299;font-size:13px">
                                                <tr>
                                                    <td align="right" width="30%">
                        		<span>
                        			<label>หน่วยงาน:<span style="color: red;font-size: 50;"> </span>
                                    </label>
                        		</span>
                                                    </td>
                                                    <td width="70%" colspan="3">
                                                        <div>

                                                            <input type="hidden"
                                                                   id="${ns}patentInherited_organizationId"/>
                                                            <input type="text" style="background-color: 
                    rgb(250, 250, 198);" class="form-control"
                                                                   id="patentInherited_organizationId_assign_autocomplete"
                                                                   placeholder=""/>
                                                            <button id="${ns}patentInheritedOrganizationListAll"
                                                                    style="margin-top: -8px;" onclick="
                                                                ${ns}patentInheritedOrganizationPopup('',true,1)"
                                                                    type="button" class="btn btn-warning btn-small">...
                                                            </button>
                                                                <%--
                   <span id="patentInherited_organizationId_assignShow"></span>
                    --%>
                                                        </div>
                                                    </td>
                                                </tr>
                                                <c:if test="${havePermissionOnDocs}">
                                                    <tr>
                                                        <td align="center" colspan="4" width="20%">
                                                            <button type="button" onclick="
                                                                ${ns}doSubmitPatentInheritedAjax()"
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
                    <c:if test="${patentForm.mode=='edit'}">
                        <c:if test="${havePermissionOnDocs}">
                            <button onclick="${ns}displayPatentInherited('add','0')" type="button"
                                    class="btn btn-primary">Add
                            </button>
                        </c:if>
                    </c:if>
               </span>
                                    </td>
                                </tr>
                                </tbody>
                            </table> 
                   <span id="${ns}patentInherited_item_list"> 
           <table class="table table-hover table-striped table-bordered table-condensed" border="1"
                  style="font-size: 12px">
               <thead>
               <tr> <!-- 50 42+8 -->
                   <th width="5%" style="text-align: center">
                       <div class="th_class">#</div>
                   </th>
                   <th width="90%" style="text-align: center">
                       <div class="th_class">หน่วยงาน</div>
                   </th>
                   <c:if test="${havePermissionOnDocs}">
                       <th width="5%">
                           <div class="th_class"></div>
                       </th>
                   </c:if>
               </tr>
               </thead>
               <tbody>
               <c:if test="${not empty patentForm.patentM.patentInheriteds}">
                   <c:forEach items="${patentForm.patentM.patentInheriteds}" var="patentInherited" varStatus="loop">
                       <tr style="cursor: pointer;">
                           <td style="text-align: left">${loop.index+1}</td>
                           <td style="text-align: left">${patentInherited.organization.orgName}</td>
                           <c:if test="${havePermissionOnDocs}">
                               <td style="text-align: center">
                                       <%--
                	<button onclick='${ns}doEditPatentInheritedAjax("${patentInherited.inventionId}","${patentInherited.inheritedItemList}")' class="btn btn-small" type="button">แก้ใข</button>
                	 --%>
                                   <button onclick='
                                       ${ns}doDeletePatentInheritedAjax("${patentInherited.inventionId}","${patentInherited.inheritedItemList}")'
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
                  <c:if test="${patentForm.mode=='edit'}">
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
            <c:if test="${not empty patentForm.patentM.patentDocuments}">
                <c:forEach items="${patentForm.patentM.patentDocuments}" var="patentDocument" varStatus="loop">
                    <tr style="cursor: pointer;">
                        <td onclick='
                            ${ns}doEditDocumentAjax("${patentDocument.inventionId}","${patentDocument.itemList}")'
                            style="text-align: left">${loop.index+1}</td>
                        <c:if test="${not empty patentDocument.fileName}">
                            <td onclick='
                                ${ns}doEditDocumentAjax("${patentDocument.inventionId}","${patentDocument.itemList}")'
                                style="text-align: left">${patentDocument.description} [ <a
                                    style="text-decoration: underline;" onclick='
                                ${ns}downloadFile("${patentDocument.itemList}","${patentDocument.inventionId}")'>${patentDocument.fileName}</a>
                                ]
                            </td>
                        </c:if>
                        <c:if test="${empty patentDocument.fileName}">
                            <td onclick='
                                ${ns}doEditDocumentAjax("${patentDocument.inventionId}","${patentDocument.itemList}")'
                                style="text-align: left">${patentDocument.description} </td>
                        </c:if>
                        <c:if test="${havePermissionOnDocs}">
                            <td style="text-align: center">
                                <button onclick='
                                    ${ns}doEditDocumentAjax("${patentDocument.inventionId}","${patentDocument.itemList}")'
                                        class="btn btn-small" type="button">แก้ใข
                                </button>
                                <button onclick='
                                    ${ns}doDeleteDocumentAjax("${patentDocument.inventionId}","${patentDocument.itemList}")'
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
        $("#proposeDate").datepicker({
            showOn: "button",
            buttonImage: '<c:url value="/resources/images/calendar.gif"/>',
            buttonImageOnly: true,
            dateFormat: "dd/mm/yy"
        });
        $("#verifyDate").datepicker({
            showOn: "button",
            buttonImage: '<c:url value="/resources/images/calendar.gif"/>',
            buttonImageOnly: true,
            dateFormat: "dd/mm/yy"
        });
        $("#announcementDate").datepicker({
            showOn: "button",
            buttonImage: '<c:url value="/resources/images/calendar.gif"/>',
            buttonImageOnly: true,
            dateFormat: "dd/mm/yy"
        });
        $("#announcementPayDate").datepicker({
            showOn: "button",
            buttonImage: '<c:url value="/resources/images/calendar.gif"/>',
            buttonImageOnly: true,
            dateFormat: "dd/mm/yy"
        });
        $("#proposeBookDate").datepicker({
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
        $("#patentDate").datepicker({
            showOn: "button",
            buttonImage: '<c:url value="/resources/images/calendar.gif"/>',
            buttonImageOnly: true,
            dateFormat: "dd/mm/yy"
        });
        $("#payDate").datepicker({
            showOn: "button",
            buttonImage: '<c:url value="/resources/images/calendar.gif"/>',
            buttonImageOnly: true,
            dateFormat: "dd/mm/yy"
        });

        $("#${ns}patentFeePayment_date").datepicker({
            showOn: "button",
            buttonImage: '<c:url value="/resources/images/calendar.gif"/>',
            buttonImageOnly: true,
            dateFormat: "dd/mm/yy"
        });

        $("#${ns}patentEditDate_editDate").datepicker({
            showOn: "button",
            buttonImage: '<c:url value="/resources/images/calendar.gif"/>',
            buttonImageOnly: true,
            dateFormat: "dd/mm/yy"
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
                $('input[id="patentM.researchProjectId"]').val(ui.item.id);
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

        $('#integerernalOrganizationId_assign_autocomplete').autocomplete({
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
                $('input[id="patentM.integerernalOrganizationId"]').val(ui.item.id);
                $("#integerernalOrganizationIdShow").html(" " + ui.item.name + " ");
                return false;
            },
            open: function () {
                $(this).removeClass("ui-corner-all").addClass("ui-corner-top");
            },
            close: function () {
                $(this).removeClass("ui-corner-top").addClass("ui-corner-all");
            }
        });

        $('#researchGroup_assign_autocomplete').autocomplete({
            source: function (request, response) {
                var researchGroupM = {
                    keySearch: request.term
                }
                ResearchAjax.getResearchGroupList(researchGroupM, {
                    callback: function (data) {
                        data = data.resultListObj;
                        if (data != null && data.length > 0) {
                            response($.map(data, function (item) {
                                return {
                                    label: item.groupTh,
                                    value: item.groupTh,
                                    name: item.groupTh,
                                    id: item.researchGroupId
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
                $('input[id="patentM.researchGroup"]').val(ui.item.id);
                $("#researchGroup_assignShow").html(" " + ui.item.name + " ");

                return false;
            },
            open: function () {
                $(this).removeClass("ui-corner-all").addClass("ui-corner-top");
            },
            close: function () {
                $(this).removeClass("ui-corner-top").addClass("ui-corner-all");
            }
        });

        $('#patentInherited_organizationId_assign_autocomplete').autocomplete({
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
                $('input[id="${ns}patentInherited_organizationId"]').val(ui.item.id);
                $("#patentInherited_organizationId_assignShow").html(" " + ui.item.name + " ");
                return false;
            },
            open: function () {
                $(this).removeClass("ui-corner-all").addClass("ui-corner-top");
            },
            close: function () {
                $(this).removeClass("ui-corner-top").addClass("ui-corner-all");
            }
        });

        $('#patentRightholder_organizationId_assign_autocomplete').autocomplete({
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
                $('input[id="${ns}patentRightholder_organizationId"]').val(ui.item.id);
                $("#patentRightholder_organizationId_assignShow").html(" " + ui.item.name + " ");
                return false;
            },
            open: function () {
                $(this).removeClass("ui-corner-all").addClass("ui-corner-top");
            },
            close: function () {
                $(this).removeClass("ui-corner-top").addClass("ui-corner-all");
            }
        });

        $('#patentCreator_organizationId_assign_autocomplete').autocomplete({
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
                $('input[id="${ns}patentCreator_organizationId"]').val(ui.item.id);
                $("#patentCreator_organizationId_assignShow").html(" " + ui.item.name + " ");
                return false;
            },
            open: function () {
                $(this).removeClass("ui-corner-all").addClass("ui-corner-top");
            },
            close: function () {
                $(this).removeClass("ui-corner-top").addClass("ui-corner-all");
            }
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
                var buttonShow = '<button style=\"margin-top: -8px"\" onclick=\'${ns}doAddDocAssignMappingAjax("' + $("#inventionId").val() + '","PATENT","' + ui.item.id + '")\' class="btn btn-primary btn-small" type="button">Add</button>'

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

        var ids = ["${ns}patentFeePayment_amount"];
        for (var i = 0; i < ids.length; i++) {
            $('#' + ids[i]).keyup(function () {
                var dInput = this.value;
                // alert(dInput.length)
                if ($.trim(dInput).length > 0 && !${ns}validateDigit(dInput)) {
                    //alert('กรอก  เฉพาะตัวเลขเท่านั้น.');
                    $(this).val("");
                    $(this).focus();
                    return false;
                }

            });
        }
        $('#patentM\\.budgetYear').keyup(function () {
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

    <%-- START PatentFeePayment  --%>
    function ${ns}doEditPatentFeePaymentAjax(inventionId, itemList, years) {

        var patentFeePayment = {
            inventionId: inventionId,
            itemList: itemList,
            years: years
        }

        ResearchAjax.findPatentFeePaymentById(patentFeePayment, {
            callback: function (data) {
                if (data != null) {

                    if (data.ispay == '1')
                        $('#${ns}patentFeePayment_isPay').prop("checked", true)
                    else
                        $('#${ns}patentFeePayment_isPay').prop("checked", false)


                    $("#${ns}patentFeePayment_date").val(data.dateShow);
                    $("#${ns}patentFeePayment_amount").val(data.amount);
                    $("#${ns}patentFeePayment_isPay").val(data.ispay);
                    $("#${ns}patentFeePayment_remark").val(data.remark);

                    $("#${ns}patentFeePayment_years").val(data.years);
                    $("#${ns}patentFeePayment_years").prop("readonly", "readonly")
                    $("#${ns}patentFeePayment_mode").val("edit");
                    $("#${ns}patentFeePayment_itemList").val(itemList);

                    $("#${ns}element_patentFeePayment").show("slow");
                }
            }
        });
    }
    function ${ns}doDeletePatentFeePaymentAjax(inventionId, itemList, years) {
        var agree = confirm(" ต้องการลบข้อมูลหรือไม่? ");
        if (agree) {
            ${ns}showDownlod("${ns}patentFeePayment_item_list");
            var researchProjectPatentFeePayment = {
                inventionId: inventionId,
                itemList: itemList,
                years: years
            }
            ResearchAjax.deletePatentFeePaymentM(researchProjectPatentFeePayment, {
                callback: function (data) {
                    ${ns}render_patentFeePayment_item(data);
                    $("#${ns}element_patentFeePayment").hide("slow");
                }
            });
            return true;
        }
        else {
            return false;
        }


    }
    function ${ns}doSubmitPatentFeePaymentAjax() {

        var budgetYear = $('#${ns}patentFeePayment_years').val();
        //   var dInput = this.value;
        // alert(dInput.length)
        if ($.trim(budgetYear).length > 0 && !${ns}validateYear(budgetYear)) {
            //alert('กรอก  ปีงบประมาณให้ถูกต้อง.');
            $('#${ns}patentFeePayment_years').val("");
            $('#${ns}patentFeePayment_years').focus();
            return false;
        }
        var mode = $("#${ns}patentFeePayment_mode").val();
        var patentFeePayment_years = $("#${ns}patentFeePayment_years").val();
        var patentFeePayment_date = $("#${ns}patentFeePayment_date").val();
        var patentFeePayment_amount = $("#${ns}patentFeePayment_amount").val();
        var patentFeePayment_remark = $("#${ns}patentFeePayment_remark").val();


        var ispay = "0";
        if ($('#${ns}patentFeePayment_isPay').prop("checked"))
            ispay = "1"
        //researcherId  researcherName
        var researchProjectPatentFeePayment = {
            inventionId: $("#inventionId").val(),
            years: patentFeePayment_years,
            ispay: ispay,
            remark: patentFeePayment_remark,
            dateShow: patentFeePayment_date,
            amount: patentFeePayment_amount,

            createdBy: "${user.userId}",
            updatedBy: "${user.userId}",
            itemList: $("#${ns}patentFeePayment_itemList").val()
        }
        ${ns}showDownlod("${ns}patentFeePayment_item_list");
        ResearchAjax.updatePatentFeePayment(researchProjectPatentFeePayment, mode, {
            callback: function (data) {
                ${ns}render_patentFeePayment_item(data);
                $("#${ns}element_patentFeePayment").hide("slow");
            }
        });
    }
    function ${ns}render_patentFeePayment_item(obj) {

        var str = "<table class=\"table table-hover table-striped table-bordered table-condensed\" border=\"1\" style=\"font-size: 12px\"> " +
                " <thead>    " +
                "  <tr>" +
                "  		<th width=\"5%\" style=\"text-align: center\"><div class=\"th_class\">#</div></th>" +
                "		<th width=\"5%\" style=\"text-align: center\"><div class=\"th_class\">Years</div></th>" +
                "		<th width=\"5%\" style=\"text-align: center\"><div class=\"th_class\">Date</div></th>  " +
                "		<th width=\"10%\" style=\"text-align: center\"><div class=\"th_class\">Amount</div></th> " +
                "		<th width=\"10%\" style=\"text-align: center\"><div class=\"th_class\">is Pay?</div></th> " +
                "		<th width=\"50%\" style=\"text-align: center\"><div class=\"th_class\">remark</div></th> " +
                "		<th width=\"10%\"><div class=\"th_class\"></div></th>  " +
                "	</tr> 		" +
                " </thead> 	" +
                " <tbody>  	";

        if (obj != null) {
            for (var i = 0; i < obj.length; i++) {
                str = str + " 	<tr style=\"cursor: pointer;\">" +
                        " <td onclick=\"${ns}doEditPatentFeePaymentAjax(\'" + obj[i].inventionId + "\',\'" + obj[i].itemList + "\',\'" + obj[i].years + "\')\" style=\"text-align: left\">" + (i + 1) + "</td>" +
                        " <td onclick=\"${ns}doEditPatentFeePaymentAjax(\'" + obj[i].inventionId + "\',\'" + obj[i].itemList + "\',\'" + obj[i].years + "\')\" style=\"text-align: center\">" + ((obj[i].years != null) ? $.trim(obj[i].years) : "") + "</td> " +
                        " <td onclick=\"${ns}doEditPatentFeePaymentAjax(\'" + obj[i].inventionId + "\',\'" + obj[i].itemList + "\',\'" + obj[i].years + "\')\" style=\"text-align: center\">" + ((obj[i].dateShow != null) ? $.trim(obj[i].dateShow) : "") + "</td> " +
                        " <td onclick=\"${ns}doEditPatentFeePaymentAjax(\'" + obj[i].inventionId + "\',\'" + obj[i].itemList + "\',\'" + obj[i].years + "\')\" style=\"text-align: right\">" + ((obj[i].amountShow != null) ? $.trim(obj[i].amountShow) : "") + "</td> " +
                        " <td onclick=\"${ns}doEditPatentFeePaymentAjax(\'" + obj[i].inventionId + "\',\'" + obj[i].itemList + "\',\'" + obj[i].years + "\')\" style=\"text-align: center\">";
                if ($.trim(obj[i].ispay) == "1") {
                    str = str + "ชำระแล้ว";
                } else {
                    str = str + "ยังไม่ได้ชำระ";
                }
                str = str + " </td> " +
                        " <td onclick=\"${ns}doEditPatentFeePaymentAjax(\'" + obj[i].inventionId + "\',\'" + obj[i].itemList + "\',\'" + obj[i].years + "\')\" style=\"text-align: left\">" + $.trim(obj[i].remark) + "</td> " +
                        " <td style=\"text-align: center\">" +
                        "  <button onclick=\"${ns}doEditPatentFeePaymentAjax(\'" + obj[i].inventionId + "\',\'" + obj[i].itemList + "\',\'" + obj[i].years + "\')\" class=\"btn btn-small\" type=\"button\">แก้ใข</button> " +
                        "  <button onclick=\"${ns}doDeletePatentFeePaymentAjax(\'" + obj[i].inventionId + "\',\'" + obj[i].itemList + "\',\'" + obj[i].years + "\')\" class=\"btn btn-danger btn-small\" type=\"button\">ลบ</button> " +
                        " </td>  " +
                        " </tr> ";
            }
        }
        str = str + "</tbody>" +
                "</table>";
        $("#${ns}patentFeePayment_item_list").html(str);

    }
    function ${ns}displayPatentFeePayment(mode, id) {


        $("#${ns}patentFeePayment_years").val("");
        $("#${ns}patentFeePayment_years").prop("readonly", false)

        $("#${ns}patentFeePayment_date").val("");
        $("#${ns}patentFeePayment_amount").val("");
        $("#${ns}patentFeePayment_isPay").prop("checked", false);
        $("#${ns}patentFeePayment_itemList").val('');
        $("#${ns}patentFeePayment_remark").val("");
        if (mode == 'edit') {

        } else {
            $("#${ns}patentFeePayment_mode").val("add");
            $("#${ns}element_patentFeePayment").show("slow");
        }
    }
    <%-- END PatentFeePayment  --%>


    <%-- START PatentEditDate  --%>
    function ${ns}doEditPatentEditDateAjax(inventionId, editItemList) {

        var patentEditDate = {
            inventionId: inventionId,
            editItemList: editItemList
        }

        ResearchAjax.findPatentEditDateById(patentEditDate, {
            callback: function (data) {
                if (data != null) {

                    $("#${ns}patentEditDate_editDate").val(data.editDateShow);
                    $("#${ns}patentEditDate_remark").val(data.remark);

                    $("#${ns}patentEditDate_mode").val("edit");
                    $("#${ns}patentEditDate_editItemList").val(editItemList);

                    $("#${ns}element_patentEditDate").show("slow");
                }
            }
        });
    }

    function ${ns}doDeletePatentEditDateAjax(inventionId, editItemList) {
        var agree = confirm(" ต้องการลบข้อมูลหรือไม่? ");
        if (agree) {
            ${ns}showDownlod("${ns}patentEditDate_item_list");
            var researchProjectPatentEditDate = {
                inventionId: inventionId,
                editItemList: editItemList
            }
            ResearchAjax.deletePatentEditDateM(researchProjectPatentEditDate, {
                callback: function (data) {
                    ${ns}render_patentEditDate_item(data);
                    $("#${ns}element_patentEditDate").hide("slow");
                }
            });
            return true;
        }
        else {
            return false;
        }


    }
    function ${ns}doSubmitPatentEditDateAjax() {
        var mode = $("#${ns}patentEditDate_mode").val();
        var patentEditDate_remark = $("#${ns}patentEditDate_remark").val();
        var patentEditDate_editDate = $("#${ns}patentEditDate_editDate").val();

        //researcherId  researcherName
        var researchProjectPatentEditDate = {
            inventionId: $("#inventionId").val(),
            remark: patentEditDate_remark,
            editDateShow: patentEditDate_editDate,

            createdBy: "${user.userId}",
            updatedBy: "${user.userId}",
            editItemList: $("#${ns}patentEditDate_editItemList").val()
        }
        ${ns}showDownlod("${ns}patentEditDate_item_list");
        ResearchAjax.updatePatentEditDate(researchProjectPatentEditDate, mode, {
            callback: function (data) {
                ${ns}render_patentEditDate_item(data);
                $("#${ns}element_patentEditDate").hide("slow");
            }
        });
    }
    function ${ns}render_patentEditDate_item(obj) {

        var str = "<table class=\"table table-hover table-striped table-bordered table-condensed\" border=\"1\" style=\"font-size: 12px\"> " +
                " <thead>    " +
                "  <tr>" +
                "  		<th width=\"5%\" style=\"text-align: center\"><div class=\"th_class\">#</div></th>" +
                "		<th width=\"8%\" style=\"text-align: center\"><div class=\"th_class\">Edit Date</div></th>  " +
                "		<th width=\"77%\" style=\"text-align: center\"><div class=\"th_class\">Remark</div></th> " +
                "		<th width=\"10%\"><div class=\"th_class\"></div></th>  " +
                "	</tr> 		" +
                " </thead> 	" +
                " <tbody>  	";

        if (obj != null) {
            for (var i = 0; i < obj.length; i++) {
                str = str + " 	<tr style=\"cursor: pointer;\">" +
                        " <td onclick=\"${ns}doEditPatentEditDateAjax(\'" + obj[i].inventionId + "\',\'" + obj[i].editItemList + "\')\" style=\"text-align: left\">" + (i + 1) + "</td>" +
                        " <td onclick=\"${ns}doEditPatentEditDateAjax(\'" + obj[i].inventionId + "\',\'" + obj[i].editItemList + "\')\" style=\"text-align: left\">" + ((obj[i].editDateShow != null) ? $.trim(obj[i].editDateShow) : "") + "</td> " +
                        " <td onclick=\"${ns}doEditPatentEditDateAjax(\'" + obj[i].inventionId + "\',\'" + obj[i].editItemList + "\')\" style=\"text-align: center\">" + $.trim(obj[i].remark) + "</td> " +
                        " <td style=\"text-align: center\">" +
                        "  <button onclick=\"${ns}doEditPatentEditDateAjax(\'" + obj[i].inventionId + "\',\'" + obj[i].editItemList + "\')\" class=\"btn btn-small\" type=\"button\">แก้ใข</button> " +
                        "  <button onclick=\"${ns}doDeletePatentEditDateAjax(\'" + obj[i].inventionId + "\',\'" + obj[i].editItemList + "\')\" class=\"btn btn-danger btn-small\" type=\"button\">ลบ</button> " +
                        " </td>  " +
                        " </tr> ";
            }
        }
        str = str + "</tbody>" +
                "</table>";
        $("#${ns}patentEditDate_item_list").html(str);

    }
    function ${ns}displayPatentEditDate(mode, id) {
        $("#${ns}patentEditDate_editDate").val("");
        $("#${ns}patentEditDate_remark").val("");


        $("#${ns}patentEditDate_remark").val("");
        if (mode == 'edit') {

        } else {
            $("#${ns}patentEditDate_mode").val('add');
            $("#${ns}element_patentEditDate").show("slow");
        }
    }
    <%-- END PatentEditDate  --%>


    <%-- START PatentCreator  --%>
    function ${ns}doEditPatentCreatorAjax(inventionId, creatorItemList) {

        var patentCreator = {
            inventionId: inventionId,
            creatorItemList: creatorItemList
        }
        ResearchAjax.findPatentCreatorById(patentCreator, {
            callback: function (data) {
                if (data != null) {
                    // workLoadRatio
                    ${ns}displayCorpType(data.creatorType);
                    $("#creatorType").val(data.creatorType);
                    //alert(data.creatorType)	

                    //private Integer organizationId;
                    // private OrganizationM organization;
                    $("#${ns}patentCreator_creatorId").val(data.creatorId);
                    var organizationId = '';
                    var orgName = ''
                    if (data.organization != null) {
                        //organizationId
                        organizationId = data.organization.organizationId;
                        orgName = data.organization.orgName;
                    }

                    $("#${ns}patentCreator_organizationId").val(organizationId);
                    $("#patentCreator_organizationId_assign_autocomplete").val(orgName);
                    $("#patentCreator_organizationId_assignShow").html(orgName);

                    $("#${ns}patentCreator_creatorType").val(data.creatorType);

                    $("#${ns}patentCreator_mode").val("edit");
                    $("#${ns}patentCreator_creatorItemList").val(creatorItemList);


                    $("#${ns}element_patentCreator").show("slow");
                    $("#${ns}patentCreatorOrganizationListAll").show("slow");
                }
            }
        });
    }

    function ${ns}doDeletePatentCreatorAjax(inventionId, creatorItemList) {
        //alert(inventionId)
        var agree = confirm(" ต้องการลบข้อมูลหรือไม่? ");
        if (agree) {
            ${ns}showDownlod("${ns}patentCreator_item_list");
            var researchProjectPatentCreator = {
                inventionId: inventionId,
                creatorItemList: creatorItemList
            }
            ResearchAjax.deletePatentCreatorM(researchProjectPatentCreator, {
                callback: function (data) {
                    ${ns}render_patentCreator_item(data);
                    $("#${ns}element_patentCreator").hide("slow");
                }
            });
            return true;
        }
        else {
            return false;
        }


    }
    function ${ns}doSubmitPatentCreatorAjax() {
        var mode = $("#${ns}patentCreator_mode").val();


        var organizationId = $('#${ns}patentCreator_organizationId').val();
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
        var researchProjectPatentCreator = {
            inventionId: $("#inventionId").val(),
            creatorType: $("#creatorType").val(),
            creatorId: $("#${ns}patentCreator_creatorId").val(),
            // researcherDept:$('#${ns}patentCreator_organization').val(),
            organization: organization,
            createdBy: "${user.userId}",
            updatedBy: "${user.userId}",
            creatorItemList: $("#${ns}patentCreator_creatorItemList").val()
        }
        // alert(researchProjectPatentCreator)
        ${ns}showDownlod("${ns}patentCreator_item_list");
        ResearchAjax.updatePatentCreator(researchProjectPatentCreator, mode, {
            callback: function (data) {
                //alert(data+"xxx")
                ${ns}render_patentCreator_item(data);
                $("#${ns}element_patentCreator").hide("slow");
            }
        });
    }
    function ${ns}render_patentCreator_item(obj) {

        var str = "<table class=\"table table-hover table-striped table-bordered table-condensed\" border=\"1\" style=\"font-size: 12px\"> " +
                " <thead>    " +
                "  <tr>" +
                "  		<th width=\"5%\" style=\"text-align: center\"><div class=\"th_class\">#</div></th>" +
                "		<th width=\"15%\" style=\"text-align: center\"><div class=\"th_class\">บุคคล/หน่วยงาน</div></th>" +
                "		<th width=\"37%\" style=\"text-align: center\"><div class=\"th_class\">รหัสประชาชน</div></th>  " +
                "		<th width=\"33%\" style=\"text-align: center\"><div class=\"th_class\">คณะ/สำนัก</div></th> " +
                "		<th width=\"10%\"><div class=\"th_class\"></div></th>  " +
                "	</tr> 		" +
                " </thead> 	" +
                " <tbody>  	";
        if (obj != null) {
            for (var i = 0; i < obj.length; i++) {

                str = str + " 	<tr style=\"cursor: pointer;\">" +
                        " <td onclick=\"${ns}doEditPatentCreatorAjax(\'" + obj[i].inventionId + "\',\'" + obj[i].creatorItemList + "\')\" style=\"text-align: left\">" + (i + 1) + "</td>" +
                        " <td onclick=\"${ns}doEditPatentCreatorAjax(\'" + obj[i].inventionId + "\',\'" + obj[i].creatorItemList + "\')\" style=\"text-align: left\">";
                if ($.trim(obj[i].creatorType) == "1") {
                    str = str + "บุคคล";
                } else {
                    str = str + "หน่วยงาน";
                }
                str = str + " </td> " +
                        " <td onclick=\"${ns}doEditPatentCreatorAjax(\'" + obj[i].inventionId + "\',\'" + obj[i].creatorItemList + "\')\" style=\"text-align: left\">";

                if ($.trim(obj[i].creatorType) == "1") {
                    str = str + "" + ((obj[i].creatorId != null) ? obj[i].creatorId : "" );
                } else {

                }

                str = str + " </td> " +
                        " <td onclick=\"${ns}doEditPatentCreatorAjax(\'" + obj[i].inventionId + "\',\'" + obj[i].creatorItemList + "\')\" style=\"text-align: left\">";

                if ($.trim(obj[i].creatorType) == "2") {
                    str = str + "" + ((obj[i].organization != null) ? $.trim(obj[i].organization.orgName) : "");
                } else {

                }

                str = str + " </td> " +
                        " <td style=\"text-align: center\">" +
                        "  <button onclick=\"${ns}doEditPatentCreatorAjax(\'" + obj[i].inventionId + "\',\'" + obj[i].creatorItemList + "\')\" class=\"btn btn-small\" type=\"button\">แก้ใข</button> " +
                        "  <button onclick=\"${ns}doDeletePatentCreatorAjax(\'" + obj[i].inventionId + "\',\'" + obj[i].creatorItemList + "\')\" class=\"btn btn-danger btn-small\" type=\"button\">ลบ</button> " +
                        " </td>  " +
                        " </tr> ";
            }
        }
        str = str + "</tbody>" +
                "</table>";
        //alert(str)
        $("#${ns}patentCreator_item_list").html(str);

    }
    function ${ns}displayCorpType(corpType) {
        $("#show_corp_type_1").hide();
        $("#show_corp_type_2").hide();
        $("#show_corp_type_" + corpType).show();
    }
    function ${ns}displayPatentCreator(mode, id) {

        $("#creatorType").val("1");
        var creatorType = $("#creatorType").val();
        $("#patentCreator_organizationId_assignShow").html("");
        $("#${ns}patentCreator_creatorId").val("");
        $("#${ns}patentCreator_organizationId").val("");
        $("#patentCreator_organizationId_assign_autocomplete").val("");
        $("#${ns}patentCreator_creatorItemList").val('');
        if (mode == 'edit') {

        } else {
            // alert(creatorType)
            ${ns}displayCorpType(creatorType);
            $("#${ns}patentCreator_mode").val('add')
            $("#${ns}element_patentCreator").show("slow");
            $("#${ns}patentCreatorOrganizationListAll").show("slow");
        }
    }
    <%-- END PatentCreator  --%>

    <%-- START PatentRightholder  --%>
    function ${ns}doDeletePatentRightholderAjax(inventionId, rightholderItemList) {
        //alert(inventionId)
        var agree = confirm(" ต้องการลบข้อมูลหรือไม่? ");
        if (agree) {
            ${ns}showDownlod("${ns}patentRightholder_item_list");
            var researchProjectPatentRightholder = {
                inventionId: inventionId,
                rightholderItemList: rightholderItemList
            }
            ResearchAjax.deletePatentRightholderM(researchProjectPatentRightholder, {
                callback: function (data) {
                    ${ns}render_patentRightholder_item(data);
                    $("#${ns}element_patentRightholder").hide("slow");
                }
            });
            return true;
        }
        else {
            return false;
        }


    }
    function ${ns}doSubmitPatentRightholderAjax() {
        var mode = "add";


        var organizationId = $('#${ns}patentRightholder_organizationId').val();
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
        var researchProjectPatentRightholder = {
            inventionId: $("#inventionId").val(),
            organization: organization,
            createdBy: "${user.userId}",
            updatedBy: "${user.userId}"
        }
        // alert(researchProjectPatentRightholder)
        ${ns}showDownlod("${ns}patentRightholder_item_list");
        ResearchAjax.updatePatentRightholder(researchProjectPatentRightholder, mode, {
            callback: function (data) {
                //alert(data+"xxx")
                ${ns}render_patentRightholder_item(data);
                $("#${ns}element_patentRightholder").hide("slow");
            }
        });
    }
    function ${ns}render_patentRightholder_item(obj) {
        var str = "<table class=\"table table-hover table-striped table-bordered table-condensed\" border=\"1\" style=\"font-size: 12px\"> " +
                " <thead>    " +
                "  <tr>" +
                "  		<th width=\"5%\" style=\"text-align: center\"><div class=\"th_class\">#</div></th>" +
                "		<th width=\"90%\" style=\"text-align: center\"><div class=\"th_class\">หน่วยงาน/หน่วยงาน</div></th>" +
                "		<th width=\"5%\"><div class=\"th_class\"></div></th>  " +
                "	</tr> 		" +
                " </thead> 	" +
                " <tbody>  	";
        if (obj != null) {
            for (var i = 0; i < obj.length; i++) {

                str = str + " 	<tr style=\"cursor: pointer;\">" +
                        " <td style=\"text-align: left\">" + (i + 1) + "</td>" +
                        " <td style=\"text-align: left\">";
                str = str + "" + ((obj[i].organization != null) ? $.trim(obj[i].organization.orgName) : "");
                str = str + " </td> " +
                        " <td style=\"text-align: center\">" +
                            //"  <button onclick=\"${ns}doEditPatentRightholderAjax(\'"+obj[i].inventionId+"\',\'"+obj[i].rightholderItemList+"\')\" class=\"btn btn-small\" type=\"button\">แก้ใข</button> "+
                        "  <button onclick=\"${ns}doDeletePatentRightholderAjax(\'" + obj[i].inventionId + "\',\'" + obj[i].rightholderItemList + "\')\" class=\"btn btn-danger btn-small\" type=\"button\">ลบ</button> " +
                        " </td>  " +
                        " </tr> ";
            }
        }
        str = str + "</tbody>" +
                "</table>";
        //alert(str)
        $("#${ns}patentRightholder_item_list").html(str);

    }
    function ${ns}displayPatentRightholder(mode, id) {

        $("#${ns}patentRightholder_organizationId").val("");
        $("#patentRightholder_organizationId_assign_autocomplete").val("");
        $("#patentRightholder_organizationId_assignShow").html("")
        if (mode == 'edit') {

        } else {
            // alert(creatorType)
            $("#${ns}element_patentRightholder").show("slow");
        }
    }
    <%-- END PatentRightholder  --%>

    <%-- START PatentInherited  --%>
    function ${ns}doDeletePatentInheritedAjax(inventionId, inheritedItemList) {
        //alert(inventionId)
        var agree = confirm(" ต้องการลบข้อมูลหรือไม่? ");
        if (agree) {
            ${ns}showDownlod("${ns}patentInherited_item_list");
            var researchProjectPatentInherited = {
                inventionId: inventionId,
                inheritedItemList: inheritedItemList
            }
            ResearchAjax.deletePatentInheritedM(researchProjectPatentInherited, {
                callback: function (data) {
                    ${ns}render_patentInherited_item(data);
                    $("#${ns}element_patentInherited").hide("slow");
                }
            });
            return true;
        }
        else {
            return false;
        }


    }
    function ${ns}doSubmitPatentInheritedAjax() {
        var mode = "add";


        var organizationId = $('#${ns}patentInherited_organizationId').val();
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
        var researchProjectPatentInherited = {
            inventionId: $("#inventionId").val(),
            organization: organization,
            createdBy: "${user.userId}",
            updatedBy: "${user.userId}"
        }
        // alert(researchProjectPatentInherited)
        ${ns}showDownlod("${ns}patentInherited_item_list");
        ResearchAjax.updatePatentInherited(researchProjectPatentInherited, mode, {
            callback: function (data) {
                //alert(data+"xxx")
                ${ns}render_patentInherited_item(data);
                $("#${ns}element_patentInherited").hide("slow");
            }
        });
    }
    function ${ns}render_patentInherited_item(obj) {
        var str = "<table class=\"table table-hover table-striped table-bordered table-condensed\" border=\"1\" style=\"font-size: 12px\"> " +
                " <thead>    " +
                "  <tr>" +
                "  		<th width=\"5%\" style=\"text-align: center\"><div class=\"th_class\">#</div></th>" +
                "		<th width=\"90%\" style=\"text-align: center\"><div class=\"th_class\">หน่วยงาน/หน่วยงาน</div></th>" +
                "		<th width=\"5%\"><div class=\"th_class\"></div></th>  " +
                "	</tr> 		" +
                " </thead> 	" +
                " <tbody>  	";
        if (obj != null) {
            for (var i = 0; i < obj.length; i++) {

                str = str + " 	<tr style=\"cursor: pointer;\">" +
                        " <td style=\"text-align: left\">" + (i + 1) + "</td>" +
                        " <td style=\"text-align: left\">";
                str = str + "" + ((obj[i].organization != null) ? $.trim(obj[i].organization.orgName) : "");
                str = str + " </td> " +
                        " <td style=\"text-align: center\">" +
                            //	"  <button onclick=\"${ns}doEditPatentInheritedAjax(\'"+obj[i].inventionId+"\',\'"+obj[i].inheritedItemList+"\')\" class=\"btn btn-small\" type=\"button\">แก้ใข</button> "+
                        "  <button onclick=\"${ns}doDeletePatentInheritedAjax(\'" + obj[i].inventionId + "\',\'" + obj[i].inheritedItemList + "\')\" class=\"btn btn-danger btn-small\" type=\"button\">ลบ</button> " +
                        " </td>  " +
                        " </tr> ";
            }
        }
        str = str + "</tbody>" +
                "</table>";
        //alert(str)
        $("#${ns}patentInherited_item_list").html(str);

    }
    function ${ns}displayPatentInherited(mode, id) {

        $("#${ns}patentInherited_organizationId").val("");
        $("#patentInherited_organizationId_assign_autocomplete").val("");
        $("#patentInherited_organizationId_assignShow").html("")
        if (mode == 'edit') {

        } else {
            // alert(creatorType)
            $("#${ns}element_patentInherited").show("slow");
        }
    }
    <%-- END PatentInherited  --%>

    <%-- Start Document  --%>

    function ${ns}doEditDocumentAjax(inventionId, itemList) {

        var researchProjectDocument = {
            inventionId: inventionId,
            itemList: itemList,
            documentId: 1
        };

        ResearchAjax.findPatentDocumentById(researchProjectDocument, {
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

    function ${ns}doDeleteDocumentAjax(inventionId, itemList) {

        var agree = confirm(" ต้องการลบข้อมูลหรือไม่? ");
        if (agree) {
            ${ns}showDownlod("${ns}document_item_list");
            var researchProjectDocument = {
                inventionId: inventionId,
                itemList: itemList
            };

            ResearchAjax.deletePatentDocumentM(researchProjectDocument, "patent", {
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
        //alert($("#inventionId").val())
        //return false;
        var researchProjectDocument = {
            inventionId: $("#inventionId").val(),
            documentId: 1,
            description: $("#${ns}document_description").val(),
            createdBy: "${user.userId}",
            updatedBy: "${user.userId}",
            itemList: $("#${ns}document_itemList").val()
        }
        ${ns}showDownlod("${ns}document_item_list");
        var file = dwr.util.getValue('${ns}uploadFile');
        //   ResearchAjax.uploadResearchProjectDocument(file,researchProjectDocument,"researchProject",mode, {
        ResearchAjax.updatePatentDocument(file, researchProjectDocument, "patent", mode, {
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
        var str = "<table class=\"table table-hover table-striped table-bordered table-condensed\" border=\"1\" style=\"font-size: 12px\"> " +
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
                        " <td style=\"text-align: left\">" + (i + 1) + "</td>";
                if ($.trim(obj[i].fileName).length > 0) {
                    str = str + "<td style=\"text-align: left\">" + $.trim(obj[i].description) + "  [ <a style=\"text-decoration: underline;\" onclick=\'${ns}downloadFile(\"" + obj[i].itemList + "\",\"" + obj[i].inventionId + "\")\'>" + obj[i].fileName + "</a>]</td> ";
                } else {
                    str = str + "<td style=\"text-align: left\">" + $.trim(obj[i].description) + "</td> ";
                }
                str = str + "" +
                        " <td style=\"text-align: center\">" +
                        "  <button onclick=\"${ns}doEditDocumentAjax(\'" + obj[i].inventionId + "\',\'" + obj[i].itemList + "\')\" class=\"btn btn-small\" type=\"button\">แก้ใข</button> " +
                        "  <button onclick=\"${ns}doDeleteDocumentAjax(\'" + obj[i].inventionId + "\',\'" + obj[i].itemList + "\')\" class=\"btn btn-danger btn-small\" type=\"button\">ลบ</button> " +
                        " </td>  " +
                        " </tr> ";
            }
        }
        str = str + "</tbody>" +
                "</table>";
        $("#${ns}document_item_list").html(str);

    }
    <%-- END Document  --%>

    <%-- START DocAssign  --%>
    function ${ns}doDeleteDocAssignMappingAjax(refId, refType, userId) {
        //alert(copyrightId)
        var agree = confirm(" ต้องการลบข้อมูลหรือไม่? ");
        if (agree) {
            ${ns}showDownlod("${ns}doctype_assign_list");
            var docAssignMapping = {
                refId: refId,
                refType: refType,
                userId: userId
            }
            ResearchAjax.deleteDocAssignMapping(docAssignMapping, {
                callback: function (data) {
                    ${ns}render_docAssign_item(data);
                    //$( "#${ns}element_doctype" ).hide( "slow"); 
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
        ${ns}showDownlod("${ns}doctype_assign_list");
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
        var str = "<table class=\"table table-hover table-striped table-bordered table-condensed\" border=\"0\" style=\"width:30%;font-size: 12px\"> ";
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
        $("#${ns}doctype_assign_list").html(str);

    }
    function ${ns}validateYear(sDigit) {

        var filter = /^\d{4}$/;
        if (filter.test(sDigit)) {
            return true;
        }
        else {
            return false;
        }
    }
    function ${ns}downloadFile(ref1, ref2) {
        ResearchAjax.downloadFile(ref1, ref2, "patent", function (data) {
            dwr.engine.openInDownload(data);
        });
    }
    <%-- END DocAssign  --%>
    function ${ns}count(s1, letter) {
        var match = s1.match(new RegExp(letter, 'g'));
        if (match != null)
            return match.length
        else
            return 0;
        //return s1.match( new RegExp(letter,'g') ).length;
    }
    function ${ns}validateDigit(sDigit) {
        //var filter = /^(\d+(?:[\.]\d{2})?)$/;
        var count = ${ns}count(sDigit, '\\.');
        //alert(sDigit.indexOf('\.'))
        var filter = /^[0-9.]+$/
        //	var filter=/^\d+(\.\d\d)?$/;
        // var filter=/^\d$/;
        if (filter.test(sDigit)) {
            if (count > 1 || sDigit.indexOf('\.') == 0 || sDigit.indexOf('\.') == sDigit.length)
                return false;
            else
                return true;
        }
        else {
            return false;
        }
    }
    function ${ns}doSubmitForm(status) {
        var budgetYear = $('input[id="patentM.budgetYear"]').val();
        //   var dInput = this.value;
        // alert(dInput.length)
        if ($.trim(budgetYear).length > 0 && !${ns}validateYear(budgetYear)) {
            //alert('กรอก  ปีงบประมาณให้ถูกต้อง.');
            $('input[id="patentM.budgetYear"]').val("");
            $('input[id="patentM.budgetYear"]').focus();
            return false;
        }

        $('input[id="patentM.docType"]').val(status);
        var form = document.forms['patentForm'];
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
        else if (f_name == 'patentInheritedOrganization')
            ${ns}patentInheritedOrganizationPopup($("#${ns}keySearch_" + f_name).val(), false, pageNo)
        else if (f_name == 'patentRightholderOrganization')
            ${ns}patentRightholderOrganizationPopup($("#${ns}keySearch_" + f_name).val(), false, pageNo)
        else if (f_name == 'patentCreatorOrganization')
            ${ns}patentCreatorOrganizationPopup($("#${ns}keySearch_" + f_name).val(), false, pageNo)

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

    <%-- start ResearchGroup Popup --%>
    function ${ns}researchGroupPopup(keySearch, init, pageNo) {
        var keyBox = " <div>" +
                "<input type=\"text\"  style=\"display:inline;width:250px\" class=\"form-control\" " +
                " id=\"${ns}keySearch\" onkeypress=\"${ns}chk(\'researchGroup\',event)\" aria-describedby=\"inputSuccess4Status\" value=\"" + keySearch + "\"/>" +
                "<button onclick=\"${ns}doSearchBox('researchGroup',1)\" type=\"button\" class=\"btn btn-success\" style=\"margin-top:-8px;margin-left:5px;\">Search</button>" +
                "</div>";
        var str = "<table class=\"table table-hover table-striped table-bordered table-condensed\" border=\"1\" style=\"font-size: 12px\"> " +
                " <thead>    " +
                "  <tr>" +
                "  		<th width=\"20%\" style=\"text-align: center\"><div class=\"th_class\">รหัสกลุ่มวิจัย</div></th>" +
                "		<th width=\"40%\" style=\"text-align: center\"><div class=\"th_class\">ชื่อกลุ่มวิจัยภาษาไทย</div></th>  " +
                "		<th width=\"40%\" style=\"text-align: center\"><div class=\"th_class\">ช่ือกลุ่มวิจัยภาษาอังกฤษ</div></th> " +
                "	</tr> 		" +
                " </thead><tbody>";
        var pageObj = {
            pageSize: PAGE_SIZE_POPUP,
            pageNo: pageNo
        }
        var researchGroupM = {
            keySearch: keySearch,
            paging: pageObj
        }
        ResearchAjax.getResearchGroupList(researchGroupM, {
            callback: function (data) {
                var maxRow = data.maxRow;
                var lastpage = data.lastpage;
                data = data.resultListObj;
                if (data != null && data.length > 0) {
                    for (var i = 0; i < data.length; i++) {
                        str = str + "<tr  onclick=\"${ns}selectResearchGroup('" + data[i].researchGroupId + "')\" style=\"cursor: pointer;\">" +
                                "<td style=\"text-align: left\"  >" + data[i].groupCode + "</td>" +
                                "<td style=\"text-align: left\"  >" + data[i].groupTh + "</td>" +
                                "<td style=\"text-align: left\"  >" + data[i].groupEng + "</td>" +
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
                    pagingStr = pagingStr + "<li><a style=\"cursor: pointer;\" onclick='${ns}doSearchBox(\"researchGroup\"," + (pageNo - 1) + ")'>Prev</a></li>";

                for (var j = pageStart; j <= lastpage; j++) {
                    if (pageNo == j) {
                        pagingStr = pagingStr + "<li><a class=\"active\" style=\"background-color: #ddd\" >" + j + "</a></li>";
                    }
                    else {
                        pagingStr = pagingStr + "<li><a class=\"active\" style=\"cursor: pointer;\" onclick='${ns}doSearchBox(\"researchGroup\"," + (j) + ")'>" + j + "</a></li>";
                    }
                }
                if (pageEnd < lastpage) {
                    pagingStr = pagingStr + "<li><a class=\"active\" style=\"cursor: pointer;\" onclick='${ns}doSearchBox(\"researchGroup\"", +(lastpage) + ")'>" + lastpage + "</a></li>";
                }
                if (pageNo != lastpage) {
                    pagingStr = pagingStr + "<li><a style=\"cursor: pointer;\" onclick='${ns}doSearchBox(\"researchGroup\"," + ((pageNo < lastpage) ? (pageNo + 1) : (lastpage)) + ")'>Next</a></li>";
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
                    bootbox.dialog(keyBox + "<div id=\"${ns}researchGroupPopupTable\">" + str + pagingStr + "</div>", [{
                        "label": "Close",
                        "class": "btn-danger",
                        "callback": function () {
                        }

                    }]);
                } else {
                    $("#${ns}researchGroupPopupTable").html(str + pagingStr);
                }
            }
        });
    }
    function ${ns}selectResearchGroup(objID) {
        ResearchAjax.findResearchGroupById(objID, {
            callback: function (data) {
                $('input[id="patentM.researchGroup"]').val(objID);
                $("#researchGroup_assignShow").html(" " + data.groupTh + " ");
                $("#researchGroup_assign_autocomplete").val(data.groupTh)

                bootbox.hideAll();
            }
        });
    }
    <%-- end ResearchGroup Popup --%>

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
                $('input[id="patentM.integerernalOrganizationId"]').val(objID);
                $("#integerernalOrganizationId_assign_autocomplete").val(data.orgName);


                bootbox.hideAll();
            }
        });
    }
    <%-- end organization Popup --%>

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
        ${ns}doAddDocAssignMappingAjax($("#inventionId").val(), "PATENT", objID);
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

    <%-- start patentCreatorOrganization Popup --%>
    function ${ns}patentCreatorOrganizationPopup(keySearch, init, pageNo) {
        var keyBox = " <div>" +
                "<input type=\"text\"  style=\"display:inline;width:250px\" class=\"form-control\" " +
                " id=\"${ns}keySearch_patentCreatorOrganization\" onkeypress=\"${ns}chk(\'patentCreatorOrganization\',event)\" aria-describedby=\"inputSuccess4Status\" value=\"" + keySearch + "\"/>" +
                "<button onclick=\"${ns}doSearchBox('patentCreatorOrganization',1)\" type=\"button\" class=\"btn btn-success\" style=\"margin-top:-8px;margin-left:5px;\">Search</button>" +
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
        var patentCreatorOrganizationM = {
            keySearch: keySearch,
            paging: pageObj
        }
        ResearchAjax.getOrganizationList(patentCreatorOrganizationM, {
            callback: function (data) {
                var maxRow = data.maxRow;
                var lastpage = data.lastpage;
                data = data.resultListObj;
                if (data != null && data.length > 0) {
                    for (var i = 0; i < data.length; i++) {
                        str = str + "<tr  onclick=\"${ns}selectPatentCreatorOrganization('" + data[i].organizationId + "')\" style=\"cursor: pointer;\">" +
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
                    pagingStr = pagingStr + "<li><a style=\"cursor: pointer;\" onclick='${ns}doSearchBox(\"patentCreatorOrganization\"," + (pageNo - 1) + ")'>Prev</a></li>";

                for (var j = pageStart; j <= pageEnd; j++) {
                    if (pageNo == j) {
                        pagingStr = pagingStr + "<li><a class=\"active\" style=\"background-color: #ddd\" >" + j + "</a></li>";
                    }
                    else {
                        pagingStr = pagingStr + "<li><a class=\"active\" style=\"cursor: pointer;\" onclick='${ns}doSearchBox(\"patentCreatorOrganization\"," + (j) + ")'>" + j + "</a></li>";
                    }
                }
                if (pageEnd < lastpage) {
                    pagingStr = pagingStr + "<li><a class=\"active\" style=\"cursor: pointer;\" onclick='${ns}doSearchBox(\"patentCreatorOrganization\"," + (pageEnd) + ")'>...</a></li>";
                    pagingStr = pagingStr + "<li><a class=\"active\" style=\"cursor: pointer;\" onclick='${ns}doSearchBox(\"patentCreatorOrganization\"," + (lastpage) + ")'>" + lastpage + "</a></li>";
                }
                if (pageNo != lastpage) {
                    pagingStr = pagingStr + "<li><a style=\"cursor: pointer;\" onclick='${ns}doSearchBox(\"patentCreatorOrganization\"," + ((pageNo < lastpage) ? (pageNo + 1) : (lastpage)) + ")'>Next</a></li>";
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
                    bootbox.dialog(keyBox + "<div id=\"${ns}patentCreatorOrganizationPopupTable\">" + str + pagingStr + "</div>", [{
                        "label": "Close",
                        "class": "btn-danger",
                        "callback": function () {
                        }

                    }]);
                } else {
                    $("#${ns}patentCreatorOrganizationPopupTable").html(str + pagingStr);
                }
            }
        });
    }
    function ${ns}selectPatentCreatorOrganization(objID) {
        ResearchAjax.findOrganizationById(objID, {
            callback: function (data) {
                $('input[id="${ns}patentCreator_organizationId"]').val(objID);
                $("#patentCreator_organizationId_assign_autocomplete").val(data.orgName);

                bootbox.hideAll();
            }
        });
    }
    <%-- end patentCreatorOrganization Popup --%>

    <%-- start patentRightholderOrganization Popup --%>
    function ${ns}patentRightholderOrganizationPopup(keySearch, init, pageNo) {
        var keyBox = " <div>" +
                "<input type=\"text\"  style=\"display:inline;width:250px\" class=\"form-control\" " +
                " id=\"${ns}keySearch_patentRightholderOrganization\" onkeypress=\"${ns}chk(\'patentRightholderOrganization\',event)\" aria-describedby=\"inputSuccess4Status\" value=\"" + keySearch + "\"/>" +
                "<button onclick=\"${ns}doSearchBox('patentRightholderOrganization',1)\" type=\"button\" class=\"btn btn-success\" style=\"margin-top:-8px;margin-left:5px;\">Search</button>" +
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
        var patentRightholderOrganizationM = {
            keySearch: keySearch,
            paging: pageObj
        }
        ResearchAjax.getOrganizationList(patentRightholderOrganizationM, {
            callback: function (data) {
                var maxRow = data.maxRow;
                var lastpage = data.lastpage;
                data = data.resultListObj;
                if (data != null && data.length > 0) {
                    for (var i = 0; i < data.length; i++) {
                        str = str + "<tr  onclick=\"${ns}selectPatentRightholderOrganization('" + data[i].organizationId + "')\" style=\"cursor: pointer;\">" +
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
                    pagingStr = pagingStr + "<li><a style=\"cursor: pointer;\" onclick='${ns}doSearchBox(\"patentRightholderOrganization\"," + (pageNo - 1) + ")'>Prev</a></li>";

                for (var j = pageStart; j <= pageEnd; j++) {
                    if (pageNo == j) {
                        pagingStr = pagingStr + "<li><a class=\"active\" style=\"background-color: #ddd\" >" + j + "</a></li>";
                    }
                    else {
                        pagingStr = pagingStr + "<li><a class=\"active\" style=\"cursor: pointer;\" onclick='${ns}doSearchBox(\"patentRightholderOrganization\"," + (j) + ")'>" + j + "</a></li>";
                    }
                }
                if (pageEnd < lastpage) {
                    pagingStr = pagingStr + "<li><a class=\"active\" style=\"cursor: pointer;\" onclick='${ns}doSearchBox(\"patentRightholderOrganization\"," + (pageEnd) + ")'>...</a></li>";
                    pagingStr = pagingStr + "<li><a class=\"active\" style=\"cursor: pointer;\" onclick='${ns}doSearchBox(\"patentRightholderOrganization\"," + (lastpage) + ")'>" + lastpage + "</a></li>";
                }
                if (pageNo != lastpage) {
                    pagingStr = pagingStr + "<li><a style=\"cursor: pointer;\" onclick='${ns}doSearchBox(\"patentRightholderOrganization\"," + ((pageNo < lastpage) ? (pageNo + 1) : (lastpage)) + ")'>Next</a></li>";
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
                    bootbox.dialog(keyBox + "<div id=\"${ns}patentRightholderOrganizationPopupTable\">" + str + pagingStr + "</div>", [{
                        "label": "Close",
                        "class": "btn-danger",
                        "callback": function () {
                        }

                    }]);
                } else {
                    $("#${ns}patentRightholderOrganizationPopupTable").html(str + pagingStr);
                }
            }
        });
    }
    function ${ns}selectPatentRightholderOrganization(objID) {
        ResearchAjax.findOrganizationById(objID, {
            callback: function (data) {
                $('input[id="${ns}patentRightholder_organizationId"]').val(objID);
                $("#patentRightholder_organizationId_assign_autocomplete").val(data.orgName);


                bootbox.hideAll();
            }
        });
    }
    <%-- end patentRightholderOrganization Popup --%>

    <%-- start patentInheritedOrganization Popup --%>
    function ${ns}patentInheritedOrganizationPopup(keySearch, init, pageNo) {
        var keyBox = " <div>" +
                "<input type=\"text\"  style=\"display:inline;width:250px\" class=\"form-control\" " +
                " id=\"${ns}keySearch_patentInheritedOrganization\" onkeypress=\"${ns}chk(\'patentInheritedOrganization\',event)\" aria-describedby=\"inputSuccess4Status\" value=\"" + keySearch + "\"/>" +
                "<button onclick=\"${ns}doSearchBox('patentInheritedOrganization',1)\" type=\"button\" class=\"btn btn-success\" style=\"margin-top:-8px;margin-left:5px;\">Search</button>" +
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
        var patentInheritedOrganizationM = {
            keySearch: keySearch,
            paging: pageObj
        }
        ResearchAjax.getOrganizationList(patentInheritedOrganizationM, {
            callback: function (data) {
                var maxRow = data.maxRow;
                var lastpage = data.lastpage;
                data = data.resultListObj;
                if (data != null && data.length > 0) {
                    for (var i = 0; i < data.length; i++) {
                        str = str + "<tr  onclick=\"${ns}selectPatentInheritedOrganization('" + data[i].organizationId + "')\" style=\"cursor: pointer;\">" +
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
                    pagingStr = pagingStr + "<li><a style=\"cursor: pointer;\" onclick='${ns}doSearchBox(\"patentInheritedOrganization\"," + (pageNo - 1) + ")'>Prev</a></li>";

                for (var j = pageStart; j <= pageEnd; j++) {
                    if (pageNo == j) {
                        pagingStr = pagingStr + "<li><a class=\"active\" style=\"background-color: #ddd\" >" + j + "</a></li>";
                    }
                    else {
                        pagingStr = pagingStr + "<li><a class=\"active\" style=\"cursor: pointer;\" onclick='${ns}doSearchBox(\"patentInheritedOrganization\"," + (j) + ")'>" + j + "</a></li>";
                    }
                }
                if (pageEnd < lastpage) {
                    pagingStr = pagingStr + "<li><a class=\"active\" style=\"cursor: pointer;\" onclick='${ns}doSearchBox(\"patentInheritedOrganization\"," + (pageEnd) + ")'>...</a></li>";
                    pagingStr = pagingStr + "<li><a class=\"active\" style=\"cursor: pointer;\" onclick='${ns}doSearchBox(\"patentInheritedOrganization\"," + (lastpage) + ")'>" + lastpage + "</a></li>";
                }
                if (pageNo != lastpage) {
                    pagingStr = pagingStr + "<li><a style=\"cursor: pointer;\" onclick='${ns}doSearchBox(\"patentInheritedOrganization\"," + ((pageNo < lastpage) ? (pageNo + 1) : (lastpage)) + ")'>Next</a></li>";
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
                    bootbox.dialog(keyBox + "<div id=\"${ns}patentInheritedOrganizationPopupTable\">" + str + pagingStr + "</div>", [{
                        "label": "Close",
                        "class": "btn-danger",
                        "callback": function () {
                        }

                    }]);
                } else {
                    $("#${ns}patentInheritedOrganizationPopupTable").html(str + pagingStr);
                }
            }
        });
    }
    function ${ns}selectPatentInheritedOrganization(objID) {
        ResearchAjax.findOrganizationById(objID, {
            callback: function (data) {
                $('input[id="${ns}patentInherited_organizationId"]').val(objID);
                $("#patentInherited_organizationId_assign_autocomplete").val(data.orgName);


                bootbox.hideAll();
            }
        });
    }
    <%-- end patentInheritedOrganization Popup --%>

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
