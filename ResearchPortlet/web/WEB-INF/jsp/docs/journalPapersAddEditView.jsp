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

        /*  .bootbox { width: 1000px !important;} 
 .modal{margin-left:-500px}
 .modal-body{max-height:1000px}
 .modal.fade.in{top:1%}
 */
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
        .ispublished_true{
            display: none;
        }
        .ispublished_false{
            display: none;
        }
    </style>

</head>

<body>
<form:form id="journalPapersForm" modelAttribute="journalPapersForm" method="post" name="journalPapersForm"
           action="${formAction}" enctype="multipart/form-data">
    <fieldset style="font-family: sans-serif;padding-top:5px">
        <input type="hidden" name="command" id="${ns}common" value="${journalPapersForm.command}"/>
        <input type="hidden" name="mode" id="${ns}mode" value="${journalPapersForm.mode}"/>
        <form:hidden path="journalPaperM.journalPapersId" id="journalPapersId"/>
        <input type="hidden" name="journalPaperM.type" id="journalPaperM.type" value="1"/>

        <input type="hidden" id="${ns}_export_proof_participation"
               value="${journalPapersForm.journalPaperM.proofParticipationResource}"/>
        <input type="hidden" id="${ns}_export_proof_academic"
               value="${journalPapersForm.journalPaperM.proofAcademicResource}"/>
        <c:set var="userIdAsString">${user.userId}</c:set>
        <c:set var="havePermissionOnDocs" value="${journalPapersForm.mode=='edit' && journalPapersForm.journalPaperM.docType=='DRAFT' 
    								&& ( userIdAsString == journalPapersForm.journalPaperM.createdBy 
                       				|| userIdAsString == journalPapersForm.journalPaperM.updatedBy || journalPapersForm.journalPaperM.isdocAssign ) }"></c:set>
        <div style="border: 1px solid #FFC299;background: #F9F9F9;padding: 10px">
            <div class="accordion" id="accordion2">
                <div class="accordion-group">
                    <div class="accordion-heading">
                        <a href="${list}" style="padding-right: 20px;">Back</a>
                        <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion2"
                           href="#collapseOne">
                            ข้อมูลบทความวิจัย ( วารสาร )
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
                                        <label><span>รหัสอ้างอิง:</span>
                                        </label>
                                    </td>
                                    <td width="30%">
                                        <input type="text" class="form-control"
                                               value="${journalPapersForm.journalPaperM.journalPapersId}" disabled/>
                                    <td width="50%"></td>
                                </tr>
                                <tr valign="top">
                                    <td align="right" width="20%">
                        <span><label>ขื่อบทความ:<span style="color: red;font-size:20;"> </span>
                        </label></span>
                                    </td>
                                    <td width="80%" colspan="3">

                                        <form:textarea path="journalPaperM.thaiName" cssStyle="width:500px" rows="2"/>
                                    </td>
                                </tr>
                                <tr valign="top">
                                    <td align="right" width="20%">
                        <span><label>ขื่อบทความ(แปลภาษา):<span style="color: red;font-size: 20;"> </span>
                        </label></span>
                                    </td>
                                    <td width="80%" colspan="3">

                                        <form:textarea path="journalPaperM.engName" cssStyle="width:500px" rows="2"/>
                                    </td>
                                </tr>
                                <tr>
                                    <td align="right" width="20%">
                        <span><label>ประเภทบทความ:<span style="color: red;font-size: 20;"> </span>
                        </label></span>
                                    </td>
                                    <td width="80%" colspan="3">
                                        <div>
                                            <form:hidden path="journalPaperM.journalPapersType"/>
                                            <input type="text" style="background-color:
                    rgb(250, 250, 198);width:350px" class="form-control"
                                                   id="journalPapersType_autocomplete" placeholder=""
                                                   value="${journalPapersForm.journalPaperM.journalPapersTypeShow}"/>
                                            <button style="margin-top: -8px"
                                                    onclick="${ns}journalPapersTypePopup('',true,1)"
                                                    type="button" class="btn btn-warning btn-small">...
                                            </button>
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td align="right" width="20%">
                                        <label><span>ชื่อวารสาร:</span>
                                        </label>
                                    </td>
                                    <td width="30%">
                                            <form:input path="journalPaperM.journalPapersJournal.journalName"
                                                        cssClass="form-control"/>
                                    <td width="50%"></td>
                                </tr>
                                <tr>
                                    <td align="right" width="20%">
                                        <label><span>ระดับการตีพิมพ์:</span>
                                        </label>
                                    </td>
                                    <td width="30%">
                                        <div>
                                            <form:hidden path="journalPaperM.journalPapersJournal.level"/>
                                            <input type="text" style="background-color:
                    rgb(250, 250, 198);width:350px" class="form-control"
                                                   id="publishLevel_autocomplete" placeholder=""
                                                   value="${journalPapersForm.journalPaperM.journalPapersLevelShow}"/>
                                            <button style="margin-top: -8px"
                                                    onclick="${ns}publishLevelPopup('',true,1)"
                                                    type="button" class="btn btn-warning btn-small">...
                                            </button>
                                        </div>
                                    </td>
                                    <td width="50%">

                                    </td>
                                </tr>

                                <tr>
                                    <td align="right" width="20%">
                                        <label><span>
                                            <form:hidden path="journalPaperM.journalPapersJournal.ispublished"/>
                                            <c:if test="${mode=='add'}">
                                                <input type="checkbox" id="ispublishedShow" checked="true"
                                                       name="ispublishedShow" value="1" onchange="${ns}changePublished(this)"/>
                                            </c:if>
                                            <c:if test="${mode=='edit'}">
                                            <c:if test="${journalPapersForm.journalPaperM.journalPapersJournal.ispublished=='1'}">
                                                <input type="checkbox" id="ispublishedShow" checked="true"
                                                       name="ispublishedShow" value="1" onchange="${ns}changePublished(this)"/>
                                                <%--
                                                <form:checkbox path="journalPaperM.journalPapersJournal.ispublished" value="1"
                                                               onchange="${ns}changePublished(this)"/>
                                                               --%>
                                            </c:if>
                                            <c:if test="${journalPapersForm.journalPaperM.journalPapersJournal.ispublished!='1'}">
                                                <input type="checkbox" id="ispublishedShow"
                                                       name="ispublishedShow" value="0" onchange="${ns}changePublished(this)"/>
                                            </c:if>
                                            </c:if>
                                            :</span>
                                        </label>
                                    </td>
                                    <td width="30%">

                                       ตีพิมพ์แล้ว

                                    </td>
                                    <td width="50%">

                                    </td>
                                </tr>
                                      <tr class="ispublished_false">
                                          <td align="right" width="20%">
                                              <label><span>วันที่ตอบรับ:</span>
                                              </label>
                                          </td>
                                          <td width="30%">

                                              <input type="text"
                                                     value="${journalPapersForm.journalPaperM.journalPapersJournal.acceptedDateShow}"
                                                     id="journalPaperM.journalPapersJournal.acceptedDateShow"
                                                     name="journalPaperM.journalPapersJournal.acceptedDateShow"
                                                     style="width:75px" class="form-control" placeholder="dd/mm/yyyy" readonly="readonly" />

                                          </td>
                                          <td width="50%">

                                          </td>
                                      </tr>

                                <tr class="ispublished_true">
                                    <td align="right" width="20%">
                                        <label><span>เล่มที่:</span>
                                        </label>
                                    </td>
                                    <td width="30%">

                                        <form:input path="journalPaperM.journalPapersJournal.vol" cssStyle="width:100px"
                                                    cssClass="form-control"/>

                                    </td>
                                    <td width="50%">

                                    </td>
                                </tr>
                                <tr class="ispublished_true">
                                    <td align="right" width="20%">
                                        <label><span>ฉบับที่:</span>
                                        </label>
                                    </td>
                                    <td width="30%">

                                        <form:input path="journalPaperM.journalPapersJournal.issue"
                                                    cssStyle="width:100px" cssClass="form-control"/>

                                    </td>
                                    <td width="50%">

                                    </td>
                                </tr>
                                <tr class="ispublished_true">
                                    <td align="right" width="20%">
                                        <label><span>หน้าที่:</span>
                                        </label>
                                    </td>
                                    <td width="30%">

                                        <form:input path="journalPaperM.journalPapersJournal.page"
                                                    cssStyle="width:100px" cssClass="form-control"/>

                                    </td>
                                    <td width="50%">

                                    </td>
                                </tr>
                                <tr class="ispublished_true">
                                    <td align="right" width="20%">
                                        <label><span>เดือน:</span>
                                        </label>
                                    </td>
                                    <td width="30%">

                                        <form:input path="journalPaperM.journalPapersJournal.month"
                                                    cssStyle="width:100px" cssClass="form-control"/>

                                    </td>
                                    <td width="50%">

                                    </td>
                                </tr>
                                <tr class="ispublished_true">
                                    <td align="right" width="20%">
                                        <label><span>ปี (พ.ศ.):</span>
                                        </label>
                                    </td>
                                    <td width="30%">

                                        <form:input path="journalPaperM.journalPapersJournal.year" maxlength="4"
                                                    cssStyle="width:100px" cssClass="form-control"/>

                                    </td>
                                    <td width="50%">

                                    </td>
                                </tr>
                                    <%--
           <tr>
              <td align="right" width="20%">
                        <label><span>ปีงบประมาณ:</span> 
                         </label>
              </td>
              <td width="30%">
              
                       <form:input path="journalPaperM.budgetYear" maxlength="4" cssStyle="width:50px" cssClass="form-control"/>
                      
              </td>
              <td width="50%">
                     
              </td> 
            </tr>
            --%>


                                <tr>
                                    <td align="right" width="20%">
                        <span><label>หน่วยงาน:<span style="color: red;font-size: 20;"> </span>
                        </label></span>
                                    </td>
                                    <td width="80%" colspan="3">
                                        <div>

                                            <form:hidden path="journalPaperM.organizationId"/>
                                            <input type="text" style="background-color: rgb(250, 250, 198);width:350px"
                                                   class="form-control" id="organizationId_assign_autocomplete"
                                                   value="${journalPapersForm.journalPaperM.organizationShow}"
                                                   placeholder=""/>
                                            <button style="margin-top: -8px"
                                                    onclick="${ns}organizationPopup('',true,1)"
                                                    type="button" class="btn btn-warning btn-small">...
                                            </button>

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

                                            <form:hidden path="journalPaperM.researchGroupId"/>
                                            <input type="text" style="background-color: 
                    rgb(250, 250, 198);width:350px" class="form-control"
                                                   id="researchGroupId_assign_autocomplete" placeholder=""
                                                   value="${journalPapersForm.journalPaperM.researchGroupShow}"/>

                                            <button style="margin-top: -8px"
                                                    onclick="${ns}researchGroupPopup('',true,1)"
                                                    type="button" class="btn btn-warning btn-small">...
                                            </button>
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td align="right" width="20%">
                        <span><label>มาจากโครงการวิจัย:<span style="color: red;font-size: 20;"> </span>
                        </label></span>
                                    </td>
                                    <td width="80%" colspan="3">
                                        <div>

                                            <form:hidden path="journalPaperM.researchProjectId"/>
                                            <input type="text" style="background-color: 
                    rgb(250, 250, 198);width:350px" class="form-control"
                                                   id="researchProjectId_autocomplete" placeholder=""
                                                   value="${journalPapersForm.journalPaperM.researchProjectShow}"/>
                                            <button style="margin-top: -8px"
                                                    onclick="${ns}researchProjectPopup('',true,1)"
                                                    type="button" class="btn btn-warning btn-small">...
                                            </button>
                                        </div>
                                    </td>
                                </tr>

                                <tr valign="top">
                                    <td align="right" width="20%">
                        <span><label>หมายเหตุ:<span style="color: red;font-size: 20;"> </span>
                        </label></span>
                                    </td>
                                    <td width="80%" colspan="3">
                                        <form:textarea path="journalPaperM.remark" cssStyle="width:500px" rows="2"/>
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
               <c:if test="${not empty journalPapersForm.journalPaperM.docAssignMappings}">
                   <table class="table table-hover table-striped table-bordered table-condensed" border="0"
                          style="width:30%;font-size: 12px">
                       <c:forEach items="${journalPapersForm.journalPaperM.docAssignMappings}" var="docAssignMapping"
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
                                        <form:hidden path="journalPaperM.docType"/>
                                        <c:if test="${mode=='edit'}">
                                            <c:if test="${havePermissionOnDocs}">
                                                <button type="button"
                                                        onclick="${ns}doSubmitForm('DRAFT')"
                                                        class="btn btn-primary">Save as Draft
                                                </button>
                                                <button id="${ns}publish_element" type="button"
                                                        onclick="${ns}doSubmitForm('PUBLISH')"
                                                        class="btn btn-primary">Submit
                                                </button>
                                                <c:if test="${journalPapersForm.journalPaperM.flag=='0'}">
                                                    <button type="button" class="btn" onclick='return ${ns}confirmUndo("
                                                    <portlet:actionURL>
                                                        <portlet:param name="action" value="undoItem"/>
                                                    <portlet:param name="journalPapersId">
                                                    <jsp:attribute name="value">
                                                        <c:out value="${journalPapersForm.journalPaperM.journalPapersId}"/>
                                                    </jsp:attribute>
                                                    </portlet:param>
                                                    </portlet:actionURL>")'> Undo</button>
                                                </c:if>
                                                <button class="btn btn-danger" type="button"
                                                        onclick='return ${ns}confirmDelete("
                                                            <portlet:actionURL>
                                                                <portlet:param name="pageInit"
                                                                               value="journalPapersList"/>
                                                                <portlet:param name="action" value="delete"/>
                                                                <portlet:param name="journalPapersId">
                                                                    <jsp:attribute name="value">
                                                                        <c:out value="${journalPapersForm.journalPaperM.journalPapersId}"/>
                                                                    </jsp:attribute>
                                                                </portlet:param>
                                                            </portlet:actionURL>")'>
                                                    <c:if test="${journalPapersForm.journalPaperM.flag=='1'}">
                                                        ลบ
                                                    </c:if>
                                                    <c:if test="${journalPapersForm.journalPaperM.flag=='0'}">
                                                        ลบ ถาวร
                                                    </c:if>
                                                </button>
                                            </c:if>
                                        </c:if>
                                        <c:if test="${mode=='add' || mode =='copy'}">
                                            <button type="button" onclick="${ns}doSubmitForm('DRAFT')"
                                                    class="btn btn-primary">Save as Draft
                                            </button>
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
                            นักวิจัย
                        </a>
                    </div>
                    <div id="collapseTwo" class="accordion-body collapse" style="height: 0px;">
                        <div class="accordion-inner">
                            <table border="0" width="100%" style="font-size: 13px">
                                <tbody>
                                <tr>
                                    <td align="center" width="100%" colspan="2">
                                        <div id="${ns}element_journalPapersWriter"
                                             style="border: 1px solid #FFC299;background: #F9F9F9;padding: 10px;display: none">
                                            <table border="0" width="100%"
                                                   style="border: 0px solid #FFC299;font-size:13px">
                                                <tr>
                                                    <td align="right" width="30%">
                        		<span>
                        			<label>นักวิจัย:<span style="color: red;font-size: 50;"> </span>
                                    </label>
                        		</span>
                                                    </td>
                                                    <td width="70%" colspan="3">
                                                        <div>
                                                            <input type="hidden"
                                                                   id="${ns}journalPapersWriter_mode"/>
                                                            <input type="hidden"
                                                                   id="${ns}journalPapersWriter_itemList"/>

                                                            <input type="hidden"
                                                                   id="${ns}journalPapersWriter_researcherId"/>
                                                            <input type="hidden"
                                                                   id="${ns}journalPapersWriter_researcherName"/>
                                                            <input type="hidden"
                                                                   id="${ns}journalPapersWriter_positionId"/>
                                                            <input type="hidden"
                                                                   id="${ns}journalPapersWriter_organizationId"/>
                                                            <input type="text" style="background-color: 
                    rgb(250, 250, 198);" class="form-control"
                                                                   id="journalPapersWriterId_assign_autocomplete"
                                                                   placeholder=""/>
                                                            <button id="${ns}researcherListAll"
                                                                    style="margin-top: -8px;display: none" onclick="
                                                                ${ns}researcherPopup('',true,1)"
                                                                    type="button" class="btn btn-warning btn-small">...
                                                            </button>
                                                        </div>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td align="right" width="30%">
                        		<span>
                        			<label>ตำแหน่ง:<span > </span>
                                    </label>
                        		</span>
                                                    </td>
                                                    <td width="70%" colspan="3">
                                                        <div>
                                                            <input type="text"
                                                                   id="${ns}journalPapersWriter_position"
                                                                   readonly="readonly" class="form-control">

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
                                                            <input type="text"
                                                                   id="${ns}journalPapersWriter_organization"
                                                                   readonly="readonly" class="form-control">
                                                        </div>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td align="right" width="30%">
                        		<span>
                        			<label>เป็นผู้เขียนหลัก?:<span style="color: red;font-size: 50;"> </span>
                                    </label>
                        		</span>
                                                    </td>
                                                    <td width="70%" colspan="3">
                                                        <div>
                                                            <c:set var="isChecked" value="false"/>
                                                            <c:set var="isLeader" value="false"/>
                                                            <c:set var="workLoad_all" value="0"/>
                                                            <c:if test="${not empty journalPapersForm.journalPaperM.journalPapersWriters}">
                                                                <c:forEach items="${journalPapersForm.journalPaperM.journalPapersWriters}" var="journalPapersWriter"
                                                                           varStatus="loop">
                                                                    <c:set var="workLoad_all" value="${journalPapersWriter.workLoadRatio+workLoad_all}"/>
                                                                    <c:if test="${journalPapersWriter.isMain=='T' && !isChecked}">
                                                                        <c:set var="isLeader" value="true"/>
                                                                        <c:set var="isChecked" value="true"/>
                                                                    </c:if>
                                                                </c:forEach>
                                                            </c:if>
                                                            <c:if test="${isLeader}">
                                                                <input type="checkbox" disabled="disabled"
                                                                       id="${ns}journalPapersWriter_isMain"
                                                                       class="form-control">
                                                            </c:if>
                                                            <c:if test="${!isLeader}">
                                                                <input type="checkbox"
                                                                       id="${ns}journalPapersWriter_isMain"
                                                                       class="form-control">
                                                            </c:if>
                                                            <input type="hidden" id="${ns}_isLeader_init" value="${isLeader}">
                                                            <input type="hidden" id="${ns}workLoad_all" value="${workLoad_all}">
                                                        </div>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td align="right" width="30%">
                        		<span>
                        			<label>สัดส่วนการทำงาน:<span style="color: red;font-size: 50;"> </span>
                                    </label>
                        		</span>
                                                    </td>
                                                    <td width="70%" colspan="3">
                                                        <div>
                                                            <input type="text"
                                                                   id="${ns}journalPapersWriter_workLoadRatio"
                                                                   style="width:75px" class="form-control">
                                                        </div>
                                                    </td>
                                                </tr>
                                                <c:if test="${havePermissionOnDocs}">
                                                    <tr>
                                                        <td align="center" colspan="4" width="20%">
                                                            <button type="button" onclick="
                                                                ${ns}doSubmitResearcherAjax()"
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
                 <c:if test="${journalPapersForm.mode=='edit'}">
                     <c:if test="${havePermissionOnDocs}">
                         <button onclick="${ns}displayResearcher('add','0')" type="button"
                                 class="btn btn-primary">Add
                         </button>
                     </c:if>
                 </c:if>
               </span>
                                    </td>
                                </tr>
                                </tbody>
                            </table>  
                 <span id="${ns}journalPapersWriter_item_list">
        <table class="table table-striped table-bordered table-condensed" border="1" style="font-size: 12px">
            <thead>
            <tr>

                <th width="5%" style="text-align: center">
                    <div class="th_class">#</div>
                </th>
                    <%--
                   <th width="15%" style="text-align: center"><div class="th_class">ตำแหน่ง</div></th>
                    --%>
                <th width="25%" style="text-align: center">
                    <div class="th_class">ชื่อ - สกุล</div>
                </th>
                <th width="27%" style="text-align: center">
                    <div class="th_class">ภาควิชา/หน่วยงาน/บริษัท/องค์กร</div>
                </th>

                <th width="14%" style="text-align: center">
                    <div class="th_class">เป็นผู้เขียนหลัก</div>
                </th>
                <th width="14%" style="text-align: center">
                    <div class="th_class">สัดส่วนการทำงาน</div>
                </th>
                <c:if test="${havePermissionOnDocs}">
                    <th width="10%">
                        <div class="th_class"></div>
                    </th>
                </c:if>
            </tr>
            </thead>
            <tbody>
            <c:if test="${not empty journalPapersForm.journalPaperM.journalPapersWriters}">
                <c:forEach items="${journalPapersForm.journalPaperM.journalPapersWriters}" var="journalPapersWriter"
                           varStatus="loop">
                    <tr style="cursor: pointer;">
                        <td onclick='
                            ${ns}doEditResearcherAjax("${journalPapersWriter.journalPapersId}","${journalPapersWriter.itemList}")'
                            style="text-align: left">${loop.index+1}</td>
                            <%-- <td style="text-align: left">${journalPapersWriter.position.positionName}</td>
                	 --%>
                        <td onclick='
                            ${ns}doEditResearcherAjax("${journalPapersWriter.journalPapersId}","${journalPapersWriter.itemList}")'
                            style="text-align: left">
                            <c:if test="${not empty journalPapersWriter.researcher.academicTitle && journalPapersWriter.researcher.academicTitle.academicTitleCode!='000'}">

                                <c:set var="academicTitleName"
                                       value="${journalPapersWriter.researcher.academicTitle.academicTitleName}"/>
                            </c:if>
                            <c:if test="${not empty journalPapersWriter.researcher.title && journalPapersWriter.researcher.title.academicTitleCode!='000'}">
                                <c:set var="academicTitleName"
                                       value="${journalPapersWriter.researcher.title.academicTitleName}"/>
                            </c:if>
                                ${academicTitleName} ${journalPapersWriter.researcher.nameThai} ${journalPapersWriter.researcher.surnameThai}
                        </td>
                        <td onclick='
                            ${ns}doEditResearcherAjax("${journalPapersWriter.journalPapersId}","${journalPapersWriter.itemList}")'
                            style="text-align: left">${journalPapersWriter.organization.orgName}</td>
                        <td onclick='
                            ${ns}doEditResearcherAjax("${journalPapersWriter.journalPapersId}","${journalPapersWriter.itemList}")'
                            style="text-align: center">
                            <c:if test="${journalPapersWriter.isMain=='T'}">
                                เป็น
                            </c:if>
                            <c:if test="${journalPapersWriter.isMain!='T'}">
                                ไม่เป็น
                            </c:if>
                        </td>
                        <td onclick='
                            ${ns}doEditResearcherAjax("${journalPapersWriter.journalPapersId}","${journalPapersWriter.itemList}")'
                            style="text-align: center">${journalPapersWriter.workLoadRatio}</td>
                        <c:if test="${havePermissionOnDocs}">
                            <td style="text-align: center">
                                <button onclick='
                                    ${ns}doEditResearcherAjax("${journalPapersWriter.journalPapersId}","${journalPapersWriter.itemList}")'
                                        class="btn btn-small" type="button">แก้ใข
                                </button>
                                <button onclick='
                                    ${ns}doDeleteResearcherAjax("${journalPapersWriter.journalPapersId}","${journalPapersWriter.itemList}")'
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
                            เอกสาร
                        </a>
                    </div>
                    <div id="collapseSix" class="accordion-body collapse" style="height: 0px;">
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
                 <c:if test="${journalPapersForm.mode=='edit'}">
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
        <table class="table table-striped table-bordered table-condensed" border="1" style="font-size: 12px">
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
            <c:if test="${not empty journalPapersForm.journalPaperM.journalPapersDocuments}">
                <c:forEach items="${journalPapersForm.journalPaperM.journalPapersDocuments}" var="journalPapersDocument"
                           varStatus="loop">
                    <tr style="cursor: pointer;">
                        <td onclick='
                            ${ns}doEditDocumentAjax("${journalPapersDocument.journalPapersId}","${journalPapersDocument.itemList}")'
                            style="text-align: left">${loop.index+1}</td>
                        <c:if test="${not empty journalPapersDocument.fileName}">
                            <td onclick='
                                ${ns}doEditDocumentAjax("${journalPapersDocument.journalPapersId}","${journalPapersDocument.itemList}")'
                                style="text-align: left">${journalPapersDocument.description} [ <a
                                    style="text-decoration: underline;" onclick='
                                ${ns}downloadFile("${journalPapersDocument.itemList}","${journalPapersDocument.journalPapersId}")'>${journalPapersDocument.fileName}</a>
                                ]
                            </td>
                        </c:if>
                        <c:if test="${empty journalPapersDocument.fileName}">
                            <td onclick='
                                ${ns}doEditDocumentAjax("${journalPapersDocument.journalPapersId}","${journalPapersDocument.itemList}")'
                                style="text-align: left">${journalPapersDocument.description} </td>
                        </c:if>


                        <c:if test="${havePermissionOnDocs}">
                            <td style="text-align: center">
                                <button onclick='
                                    ${ns}doEditDocumentAjax("${journalPapersDocument.journalPapersId}","${journalPapersDocument.itemList}")'
                                        class="btn btn-small" type="button">แก้ใข
                                </button>
                                <button onclick='
                                    ${ns}doDeleteDocumentAjax("${journalPapersDocument.journalPapersId}","${journalPapersDocument.itemList}")'
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
        $('input[id="journalPaperM.journalPapersJournal.acceptedDateShow"]').datepicker({
            showOn: "button",
            buttonImage: '<c:url value="/resources/images/calendar.gif"/>',
            buttonImageOnly: true,
            dateFormat: "dd/mm/yy"
        });

        var checked_ispublish_value=$('input[name="ispublishedShow"]').prop("checked");
       // alert(checked_ispublish_value)
        ////$(".ispublished_"+checked_ispublish_value).length
        $(".ispublished_"+checked_ispublish_value).each(function(  ) {
                    $( this ).show('slow')
                });
        //alert(checked_ispublish_value)

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
                //  this.value = ui.item.id;
                this.value = ui.item.name;
                $('input[id="journalPaperM.researchProjectId"]').val(ui.item.id);
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

        $('#researchGroupId_assign_autocomplete').autocomplete({
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
                $('input[id="journalPaperM.researchGroupId"]').val(ui.item.id);
                $("#researchGroupId_assignShow").html(" " + ui.item.name + " ");
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
                $('input[id="journalPaperM.organizationId"]').val(ui.item.id);
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

        $('#journalPapersType_autocomplete').autocomplete({
            source: function (request, response) {
                var journalPapersTypeM = {
                    keySearch: request.term
                }
                ResearchAjax.getJournalPapersTypeList(journalPapersTypeM, {
                    callback: function (data) {
                        data = data.resultListObj;
                        if (data != null && data.length > 0) {
                            response($.map(data, function (item) {
                                return {
                                    label: item.jptName,
                                    value: item.jptName,
                                    name: item.jptName,
                                    id: item.journalPapersTypeId
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
                $('input[id="journalPaperM.journalPapersType"]').val(ui.item.id);
                $("#journalPapersType_assignShow").html(" " + ui.item.name + " ");
                return false;
            },
            open: function () {
                $(this).removeClass("ui-corner-all").addClass("ui-corner-top");
            },
            close: function () {
                $(this).removeClass("ui-corner-top").addClass("ui-corner-all");
            }
        });

        $('#journalPapersWriterId_assign_autocomplete').autocomplete({
            source: function (request, response) {
                var researcherM = {
                    keySearch: request.term
                }
                ResearchAjax.getResearcherList(researcherM, {
                    callback: function (data) {
                        data = data.resultListObj;
                        if (data != null && data.length > 0) {
                            response($.map(data, function (item) {
                                return {
                                    label: item.nameThai + " " + item.surnameThai,
                                    value: item.nameThai + " " + item.surnameThai,
                                    name: item.nameThai + " " + item.surnameThai,
                                    id: item.researcherId
                                    ,

                                    position: (item.position != null && item.position.positionName) ? item.position.positionName : "",
                                    positionId: (item.position != null && item.position.positionId) ? item.position.positionId : "",
                                    organization: (item.organization != null && item.organization.orgName) ? item.organization.orgName : "",
                                    organizationId: (item.organization != null && item.organization.organizationId) ? item.organization.organizationId : "",
                                    titleId: (item.title != null && item.title.titleId) ? item.title.titleId : "",
                                    titleName: (item.title != null && item.title.titleName) ? item.title.titleName : ""
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

                $('#${ns}journalPapersWriter_researcherId').val(ui.item.id);
                $('#${ns}journalPapersWriter_researcherName').val(ui.item.name);
                $('#${ns}journalPapersWriter_positionId').val(ui.item.positionId);
                $('#${ns}journalPapersWriter_organizationId').val(ui.item.organizationId);
                // titleId:(item.title!=null && item.title.titleId)?item.title.titleId:"",
                //         	  titleName:(item.title!=null && item.title.titleName)?item.title.titleName:""
                $("#journalPapersWriterId_assignShow").html(" " + ui.item.name + " ");
                $('#${ns}journalPapersWriter_position').val(ui.item.position);
                $('#${ns}journalPapersWriter_organization').val(ui.item.organization);

                return false;
            },
            open: function () {
                $(this).removeClass("ui-corner-all").addClass("ui-corner-top");
            },
            close: function () {
                $(this).removeClass("ui-corner-top").addClass("ui-corner-all");
            }
        });

        $('#publishLevel_autocomplete').autocomplete({
            source: function (request, response) {

                var researchProjectM = {
                    keySearch: request.term
                }
                ResearchAjax.getPublishLevelList(researchProjectM, {
                    callback: function (data) {
                        data = data.resultListObj;
                        if (data != null && data.length > 0) {
                            response($.map(data, function (item) {
                                return {
                                    label: item.plName,
                                    value: item.plName,
                                    name: item.plName,
                                    id: item.publishLevelId
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
                //  this.value = ui.item.id;
                this.value = ui.item.name;
                $('input[id="journalPaperM.journalPapersJournal.level"]').val(ui.item.id);
                // $("#publishLevel_autocomplete").html(" "+ui.item.name+" ");
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
                var buttonShow = '<button style=\"margin-top: -8px"\"  onclick=\'${ns}doAddDocAssignMappingAjax("' + $("#journalPapersId").val() + '","JOURNAL","' + ui.item.id + '")\' class="btn btn-primary btn-small" type="button">Add</button>'

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

        var ele_numbers = ["${ns}journalPapersWriter_workLoadRatio"]
        for (var i = 0; i < ele_numbers.length; i++) {
            $('#' + ele_numbers[i]).keyup(function () {
                var dInput = this.value;
                // alert(dInput.length)
                if ($.trim(dInput).length > 0 && !${ns}validateDigitOnly(dInput)) {
                    //alert('กรอก  เฉพาะตัวเลขเท่านั้น.');
                    $(this).val("");
                    $(this).focus();
                    return false;
                }
                if (parseInt(dInput) > 100 || parseInt(dInput) < 1) {
                    //alert('กรอก ค่ามากเกิน 100.');
                    $(this).val("");
                    $(this).focus();
                    return false;
                }
            });
        }

        $('#journalPaperM\\.journalPapersJournal\\.year').keyup(function () {
            var dInput = this.value;
            // alert(dInput.length)
            if ($.trim(dInput).length > 0 && !${ns}validateDigitOnly(dInput)) {
                //alert('กรอก  เฉพาะตัวเลขเท่านั้น.');
                $(this).val("");
                $(this).focus();
                return false;
            }

        });
        if($("#${ns}workLoad_all").val()=='100' && $("#${ns}_isLeader_init").val()=='true'){
            $("#${ns}publish_element").show("slow");
        }else{
            $("#${ns}publish_element").hide("slow");
        }
    });

    <%-- Start Document  --%>
    function ${ns}doEditDocumentAjax(journalPapersId, itemList) {

        var researchProjectDocument = {
            journalPapersId: journalPapersId,
            itemList: itemList,
            documentId: 1
        };

        ResearchAjax.findJournalPapersDocumentById(researchProjectDocument, {
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
    function ${ns}doDeleteDocumentAjax(journalPapersId, itemList) {

        var agree = confirm(" ต้องการลบข้อมูลหรือไม่? ");
        if (agree) {
            ${ns}showDownlod("${ns}document_item_list");
            var researchProjectDocument = {
                journalPapersId: journalPapersId,
                itemList: itemList
            };

            ResearchAjax.deleteJournalPapersDocument(researchProjectDocument, "journalPapers", {
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
        //alert($("#journalPapersId").val())
        //return false;
        var researchProjectDocument = {
            journalPapersId: $("#journalPapersId").val(),
            documentId: 1,
            description: $("#${ns}document_description").val(),
            createdBy: "${user.userId}",
            updatedBy: "${user.userId}",
            itemList: $("#${ns}document_itemList").val()
        }
        ${ns}showDownlod("${ns}document_item_list");
        var file = dwr.util.getValue('${ns}uploadFile');
        ResearchAjax.updateJournalPapersDocument(file, researchProjectDocument, "journalPapers", mode, {
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
            $("#${ns}document_mode").val('add')
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
                        " <td onclick=\"${ns}doEditDocumentAjax(\'" + obj[i].journalPapersId + "\',\'" + obj[i].itemList + "\')\"  style=\"text-align: left\">" + (i + 1) + "</td>";
                if ($.trim(obj[i].fileName).length > 0) {
                    str = str + "<td onclick=\"${ns}doEditDocumentAjax(\'" + obj[i].journalPapersId + "\',\'" + obj[i].itemList + "\')\" style=\"text-align: left\">" + $.trim(obj[i].description) + "  [ <a style=\"text-decoration: underline;\" onclick=\'${ns}downloadFile(\"" + obj[i].itemList + "\",\"" + obj[i].journalPapersId + "\")\'>" + obj[i].fileName + "</a>]</td> ";
                } else {
                    str = str + "<td onclick=\"${ns}doEditDocumentAjax(\'" + obj[i].journalPapersId + "\',\'" + obj[i].itemList + "\')\" style=\"text-align: left\">" + $.trim(obj[i].description) + "</td> ";
                }
                str = str + "" +

                        " <td style=\"text-align: center\">" +
                        "  <button onclick=\"${ns}doEditDocumentAjax(\'" + obj[i].journalPapersId + "\',\'" + obj[i].itemList + "\')\" class=\"btn btn-small\" type=\"button\">แก้ใข</button> " +
                        "  <button onclick=\"${ns}doDeleteDocumentAjax(\'" + obj[i].journalPapersId + "\',\'" + obj[i].itemList + "\')\" class=\"btn btn-danger btn-small\" type=\"button\">ลบ</button> " +
                        " </td>  " +
                        " </tr> ";
            }
        }
        str = str + "</tbody>" +
                "</table>";
        $("#${ns}document_item_list").html(str);

    }
    <%-- END Document  --%>

    <%-- START Researcher  --%>
    function ${ns}doEditResearcherAjax(journalPapersId, itemList) {
        //alert(researchProjectId)

        var journalPapersWriter = {
            itemList: itemList,
            journalPapersId: journalPapersId,
            writerItemId: 1
        }
        ResearchAjax.findJournalPapersWriterById(journalPapersWriter, {
            callback: function (data) {
                if (data != null) {
                    $("#journalPapersWriterId_assign_autocomplete").prop("readonly", "readonly")
                    $("#journalPapersWriterId_assign_autocomplete").css("background-color", "");

                    $("#${ns}journalPapersWriter_researcherId").val(data.researcher.researcherId);
                    $("#${ns}journalPapersWriter_researcherName").val(data.researcher.nameThai + " " + data.researcher.surnameThai);
                    var positionId = '';
                    var positionName = '';
                    if (data.position != null) {
                        positionId = data.position.positionId;
                        positionName = data.position.positionName;
                    }
                    var organizationId = '';
                    var orgName = '';
                    if (data.organization != null) {
                        organizationId = data.organization.organizationId;
                        orgName = data.organization.orgName;
                    }
                    //alert(data.researcher.researcherId) 

                    $("#${ns}journalPapersWriter_positionId").val(positionId);
                    $("#${ns}journalPapersWriter_organizationId").val(organizationId);
                    $("#journalPapersWriterId_assign_autocomplete").val(data.researcher.nameThai + " " + data.researcher.surnameThai);
                    $("#journalPapersWriterId_assignShow").html(data.researcher.nameThai + " " + data.researcher.surnameThai);
                    $("#${ns}journalPapersWriter_position").val(positionName);
                    $("#${ns}journalPapersWriter_organization").val(orgName);
                    if($("#${ns}_isLeader_init").val()=='true'){
                        $("#${ns}journalPapersWriter_isMain").prop("disabled","disabled")

                    }else{
                        $("#${ns}journalPapersWriter_isMain").prop("disabled","")
                    }
                    var isMain = false;
                    if (data.isMain == 'T') {
                        isMain = true;
                        $("#${ns}journalPapersWriter_isMain").prop("disabled", "");
                    }
                    $("#${ns}journalPapersWriter_isMain").prop("checked", isMain);
                    $("#${ns}journalPapersWriter_workLoadRatio").val(data.workLoadRatio);

                    $("#${ns}journalPapersWriter_mode").val("edit");

                    $("#${ns}journalPapersWriter_itemList").val(itemList);
                    $("#${ns}journalPapersWriter_researcherItemId").val("1");


                    $("#${ns}element_journalPapersWriter").show("slow");
                    $("#${ns}researcherListAll").hide("slow");
                }
            }
        });
    }

    function ${ns}doDeleteResearcherAjax(journalPapersId, itemList) {
        var agree = confirm(" ต้องการลบข้อมูลหรือไม่? ");
        if (agree) {
            ${ns}showDownlod("${ns}journalPapersWriter_item_list");
            var researchProjectResearcher = {
                journalPapersId: journalPapersId,
                itemList: itemList
            }
            ResearchAjax.deleteJournalPapersWriter(researchProjectResearcher, {
                callback: function (data) {
                    ${ns}render_journalPapersWriter_item(data);
                    $("#${ns}element_journalPapersWriter").hide("slow");
                }
            });
            return true;
        }
        else {
            return false;
        }


    }
    function ${ns}doSubmitResearcherAjax() {
        if($.trim($('#journalPapersWriterId_assign_autocomplete').val())==0){
            $('#journalPapersWriterId_assign_autocomplete').css("border","1px solid red")
            return false;
        }
        var workLoadRatio=$.trim($('#${ns}journalPapersWriter_workLoadRatio').val());
        if(workLoadRatio.length==0){
            $('#${ns}journalPapersWriter_workLoadRatio').css("border"," 1px solid red")
            return false;
        }
        var mode = $("#${ns}journalPapersWriter_mode").val();
        var positionId = $('#${ns}journalPapersWriter_positionId').val();
        var position;
        if (positionId.length > 0) {
            position = {
                positionId: positionId
            };
        } else {
            position = {};
        }

        var organizationId = $('#${ns}journalPapersWriter_organizationId').val();
        var organization;
        if (organizationId.length > 0) {
            organization = {
                organizationId: organizationId
            };
        } else {
            organization = {};
        }


        var researcherId = $('#${ns}journalPapersWriter_researcherId').val();
        var researcher;
        if (researcherId.length > 0) {
            researcher = {
                researcherId: researcherId
            };
        } else {
            researcher = {};
        }

        var isMain = "F";
        if ($('#${ns}journalPapersWriter_isMain').prop("checked"))
            isMain = "T"
        //researcherId  researcherName
        var researchProjectResearcher = {
            journalPapersId: $("#journalPapersId").val(),
            writerItemId: 1,
            isMain: isMain,
            // researcherDept:$('#${ns}journalPapersWriter_organization').val(),
            workLoadRatio: workLoadRatio,
            organization: organization,
            position: position,
            researcher: researcher,

            //researcherId:$('#${ns}journalPapersWriter_researcherId').val(),
            //researcherName:$('#${ns}journalPapersWriter_researcherName').val(),
            createdBy: "${user.userId}",
            updatedBy: "${user.userId}",
            itemList: $("#${ns}journalPapersWriter_itemList").val()
        }

        ResearchAjax.countJournalPapersWriter(researchProjectResearcher, {
            callback: function (data) {
                var max_percent = data + parseInt(researchProjectResearcher.workLoadRatio);
                if (max_percent > 100) {
                    //alert(" สัดส่วนการทำงาน เกิน 100 ")
                    $('#${ns}journalPapersWriter_workLoadRatio').css("border"," 1px solid red")
                    $('#${ns}journalPapersWriter_workLoadRatio').val("")
                    $('#${ns}journalPapersWriter_workLoadRatio').focus();
                    return false;
                } else {
                    ${ns}showDownlod("${ns}journalPapersWriter_item_list");
                    ResearchAjax.updateJournalPapersWriter(researchProjectResearcher, mode, {
                        callback: function (data) {
                            ${ns}render_journalPapersWriter_item(data);
                            $("#${ns}element_journalPapersWriter").hide("slow");
                        }
                    });
                }

            }
        });

    }
    function ${ns}render_journalPapersWriter_item(obj) {


        var str = "<table class=\"table table-striped table-bordered table-condensed\" border=\"1\" style=\"font-size: 12px\"> " +
                " <thead>    " +
                "  <tr>" +
                "  		<th width=\"5%\" style=\"text-align: center\"><div class=\"th_class\">#</div></th>" +
                    //"		<th width=\"15%\" style=\"text-align: center\"><div class=\"th_class\">ตำแหน่ง</div></th>"+  
                "		<th width=\"25%\" style=\"text-align: center\"><div class=\"th_class\">ชื่อ - สกุล</div></th>  " +
                "		<th width=\"27%\" style=\"text-align: center\"><div class=\"th_class\">ภาควิชา/หน่วยงาน/บริษัท/องค์กร</div></th> " +
                "		<th width=\"14%\" style=\"text-align: center\"><div class=\"th_class\">เป็นผู้เขียนหลัก</div></th> " +
                "		<th width=\"14%\" style=\"text-align: center\"><div class=\"th_class\">สัดส่วนการทำงาน</div></th> " +
                "		<th width=\"10%\"><div class=\"th_class\"></div></th>  " +
                "	</tr> 		" +
                " </thead> 	" +
                " <tbody>  	";
        var isLeader=false;
        var sum_workload=0;
        if (obj != null) {
            for (var i = 0; i < obj.length; i++) {
                var titleShow = "";
                /*
                 var title2=(obj[i].researcher.academicTitle!=null  && obj[i].researcher.academicTitle.titleCode!='000')?obj[i].researcher.academicTitle.titleName:"";
                 var title1=(obj[i].researcher.title!=null && obj[i].researcher.title.academicTitleCode!='000')?obj[i].researcher.title.academicTitleName:"";
                 */
                var title2 = (obj[i].researcher.academicTitle != null && obj[i].researcher.academicTitle.academicTitleCode != '000') ? obj[i].researcher.academicTitle.academicTitleName : "";
                var title1 = (obj[i].researcher.title != null && obj[i].researcher.title.academicTitleCode != '000') ? obj[i].researcher.title.academicTitleName : "";
                //	alert(title1+" "+title2)
                //		alert(obj[i].researcher.title.titleName)
                if (title1 != '')
                    titleShow = title1;
                else if (title2 != '')
                    titleShow = title2;
                str = str + " 	<tr style=\"cursor: pointer;\">" +
                        " <td onclick=\"${ns}doEditResearcherAjax(\'" + obj[i].journalPapersId + "\',\'" + obj[i].itemList + "\')\" style=\"text-align: left\">" + (i + 1) + "</td>" +
                            // " <td style=\"text-align: left\">"+((obj[i].position!=null)?$.trim(obj[i].position.positionName):"")+"</td> "+


                        " <td onclick=\"${ns}doEditResearcherAjax(\'" + obj[i].journalPapersId + "\',\'" + obj[i].itemList + "\')\" style=\"text-align: left\">" + titleShow + " " + ((obj[i].researcher != null) ? $.trim(obj[i].researcher.nameThai) : "") + " " + ((obj[i].researcher != null) ? $.trim(obj[i].researcher.surnameThai) : "") + "</td> " +
                        " <td onclick=\"${ns}doEditResearcherAjax(\'" + obj[i].journalPapersId + "\',\'" + obj[i].itemList + "\')\" style=\"text-align: left\">" + ((obj[i].organization != null) ? $.trim(obj[i].organization.orgName) : "") + "</td> " +
                        " <td onclick=\"${ns}doEditResearcherAjax(\'" + obj[i].journalPapersId + "\',\'" + obj[i].itemList + "\')\" style=\"text-align: center\">";
                if ($.trim(obj[i].isMain) == "T") {
                    str = str + "เป็น";
                    isLeader=true;
                } else {
                    str = str + "ไม่เป็น";
                }
                str = str + " </td> " +
                        " <td onclick=\"${ns}doEditResearcherAjax(\'" + obj[i].journalPapersId + "\',\'" + obj[i].itemList + "\')\" style=\"text-align: center\">" + $.trim(obj[i].workLoadRatio) + "</td> " +
                        " <td style=\"text-align: center\">" +
                        "  <button onclick=\"${ns}doEditResearcherAjax(\'" + obj[i].journalPapersId + "\',\'" + obj[i].itemList + "\')\" class=\"btn btn-small\" type=\"button\">แก้ใข</button> " +
                        "  <button onclick=\"${ns}doDeleteResearcherAjax(\'" + obj[i].journalPapersId + "\',\'" + obj[i].itemList + "\')\" class=\"btn btn-danger btn-small\" type=\"button\">ลบ</button> " +
                        " </td>  " +
                        " </tr> ";
                if($.trim(obj[i].workLoadRatio)>0){
                    sum_workload=sum_workload+obj[i].workLoadRatio;
                }
            }
        }
        str = str + "</tbody>" +
                "</table>";
        $("#${ns}journalPapersWriter_item_list").html(str);

        $("#${ns}_isLeader_init").val(isLeader);
        if(sum_workload==100 && $("#${ns}_isLeader_init").val()=='true'){
            $("#${ns}publish_element").show("slow");
        }else{
            $("#${ns}publish_element").hide("slow");
        }

        if(isLeader){
            $("#${ns}journalPapersWriter_isMain").prop("disabled", "disabled");
        }else
            $("#${ns}journalPapersWriter_isMain").prop("disabled", "");

        $('#${ns}journalPapersWriter_workLoadRatio').css("border"," 1px solid #DDD")
        $('#journalPapersWriterId_assign_autocomplete').css("border","1px solid #DDD")

    }
    function ${ns}displayResearcher(mode, id) {
        $("#${ns}journalPapersWriter_researcherId").val("");
        $("#${ns}journalPapersWriter_researcherName").val("");
        $("#${ns}journalPapersWriter_positionId").val("");
        $("#${ns}journalPapersWriter_organizationId").val("");
        $("#journalPapersWriterId_assign_autocomplete").prop("readonly", false)
        $("#journalPapersWriterId_assign_autocomplete").val("");
        $("#journalPapersWriterId_assign_autocomplete").css("background-color", "rgb(250, 250, 198)");

        $("#journalPapersWriterId_assignShow").html("");
        $("#${ns}journalPapersWriter_position").val("");
        $("#${ns}journalPapersWriter_organization").val("");
        $("#${ns}journalPapersWriter_isMain").prop("checked", false);
        if($("#${ns}_isLeader_init").val()=='true'){
            $("#${ns}journalPapersWriter_isMain").prop("disabled","disabled")
        }else{
            $("#${ns}journalPapersWriter_isMain").prop("disabled","")
        }
        $("#${ns}journalPapersWriter_workLoadRatio").val("");

        $("#${ns}journalPapersWriter_itemList").val("");
        if (mode == 'edit') {

        } else {
            $("#${ns}journalPapersWriter_mode").val("add")
            $("#${ns}element_journalPapersWriter").show("slow");
            $("#${ns}researcherListAll").show("slow");
            $("#${ns}journalPapersWriter_itemList").val("")
        }
    }
    <%-- END Researcher  --%>

    <%-- START DocAssign  --%>
    function ${ns}doDeleteDocAssignMappingAjax(refId, refType, userId) {
        //alert(journalPapersId)
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
        $("#${ns}doctype_assign_list").html(str);

    }


    <%-- END DocAssign  --%>
    function ${ns}exportProofFile(type) {
        return false;
        //alert('${research_group_resource_get_byid}')
        $.ajax({
            url: '${research_group_resource_get_byid}',
            // type: 'POST',
            // datatype:'json',
            // data: { researchGroupId: id }, 
            success: function (data) {
                var src;
                //alert(data)
                if (type == 'participation') {
                    src = data.proofParticipationResource
                } else
                    src = data.proofAcademicResource
                //alert(type)

                //alert(src)
                var div = document.createElement("div");
                document.body.appendChild(div);
                div.innerHTML = "<iframe width='0' height='0' scrolling='no' frameborder='0' src='" + src + "'></iframe>";

            }
        });


    }


    function ${ns}downloadFile(ref1, ref2) {
        ResearchAjax.downloadFile(ref1, ref2, "journalPapers", function (data) {
            dwr.engine.openInDownload(data);
        });
    }
    function ${ns}doSubmitForm(status) {
        var year = $('input[id="journalPaperM.journalPapersJournal.year"]').val();
        if ($.trim(year).length > 0 && !${ns}validateYear(year)) {
            //alert('กรอก  ปี (พ.ศ.) ให้ถูกต้อง.');
            $('input[id="journalPaperM.journalPapersJournal.year"]').val("");
            $('input[id="journalPaperM.journalPapersJournal.year"]').focus();
            return false;
        }
        /*
         var budgetYear=$('input[id="journalPaperM.budgetYear"]').val();
         if ($.trim(budgetYear).length > 0 && !
        ${ns}validateYear(budgetYear)) {
         //alert('กรอก  ปีงบประมาณ ให้ถูกต้อง.');
         $('input[id="journalPaperM.budgetYear"]').val("");
         $('input[id="journalPaperM.budgetYear"]').focus();
         return false;
         }
         */
        var checked_ispublish_value=$('input[name="ispublishedShow"]').prop("checked");
        //alert($(".ispublished_"+checked_ispublish_value).length)
        ////$(".ispublished_"+checked_ispublish_value).length
        var checked_value="0";
        if(checked_ispublish_value){
            checked_value="1";
        }
     //  alert(checked_value)
        $('input[name="journalPaperM.journalPapersJournal.ispublished"]').val(checked_value)


        $('input[id="journalPaperM.docType"]').val(status);
        var form = document.forms['journalPapersForm'];
        form.submit();
    }
    function ${ns}validateDigitOnly(sDigit) {

        //alert(sDigit.indexOf('\.'))
        var filter = /^[0-9]+$/
        //	var filter=/^\d+(\.\d\d)?$/;
        // var filter=/^\d$/;
        if (filter.test(sDigit)) {
            return true;
        }
        else {
            return false;
        }
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
    function ${ns}showDownlod(element_) {
        //<c:url value='/resources/images/ajax_loading.gif'/>loading.gif
        var download_str = '<table border="0" width="100%"><tr><td  align="center"><img style="width:60px;" src="<c:url value="/resources/images/loading.gif"/>"/></td></tr></table>';
        $("#" + element_).html(download_str);

    }
    function ${ns}researchGroupPopup() {
        //ImakeResultMessage
        var str = "<table class=\"table table-hover table-striped table-bordered table-condensed\" border=\"1\" style=\"font-size: 12px\"> " +
                " <thead>    " +
                "  <tr>" +
                "  		<th width=\"20%\" style=\"text-align: center\"><div class=\"th_class\">รหัสกลุ่มวิจัย</div></th>" +
                "		<th width=\"40%\" style=\"text-align: center\"><div class=\"th_class\">ชื่อกลุ่มวิจัยภาษาไทย</div></th>  " +
                "		<th width=\"40%\" style=\"text-align: center\"><div class=\"th_class\">ช่ือกลุ่มวิจัยภาษาอังกฤษ</div></th> " +
                "	</tr> 		" +
                " </thead><tbody>";
        ResearchAjax.getResearchGroupList("", {
            callback: function (data) {
                var maxRow = data.maxRow;
                var lastpage = data.lastpage;
                data = data.resultListObj;
                //alert(str)
                if (data != null && data.length > 0) {
                    for (var i = 0; i < data.length; i++) {
                        str = str + "<tr style=\"cursor: pointer;\">" +
                                "<td style=\"text-align: left\"  >" + data[i].groupCode + "</td>" +
                                "<td style=\"text-align: left\"  >" + data[i].groupTh + "</td>" +
                                "<td style=\"text-align: left\"  >" + data[i].groupEng + "</td>" +
                                "</tr>";
                    }
                    // alert(str)
                }
                str = str + "</tbody></table>";
                bootbox.classes("aoe_width");
                bootbox.dialog(str, [{
                    "label": "Close",
                    "class": "btn-danger"

                }]);
            }
        });

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
        else if (f_name == 'journalPapersType')
            ${ns}journalPapersTypePopup($("#${ns}keySearch_" + f_name).val(), false, pageNo)
        else if (f_name == 'researchProject')
            ${ns}researchProjectPopup($("#${ns}keySearch_" + f_name).val(), false, pageNo)
        else if (f_name == 'researcher')
            ${ns}researcherPopup($("#${ns}keySearch_" + f_name).val(), false, pageNo)
        else if (f_name == 'publishLevel')
            ${ns}publishLevelPopup($("#${ns}keySearch_" + f_name).val(), false, pageNo)
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
                $('input[id="journalPaperM.researchGroupId"]').val(objID);
                $("#researchGroupId_assignShow").html(" " + data.groupTh + " ");
                $("#researchGroupId_assign_autocomplete").val(data.groupTh)
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
                $('input[id="journalPaperM.organizationId"]').val(objID);
                $("#organizationId_assign_autocomplete").val(data.orgName);


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

        ${ns}doAddDocAssignMappingAjax($("#journalPapersId").val(), "JOURNAL", objID);
        bootbox.hideAll();
    }
    <%-- end docsAssign Popup --%>

    <%-- start journalPapersType Popup --%>
    function ${ns}journalPapersTypePopup(keySearch, init, pageNo) {
        var keyBox = " <div>" +
                "<input type=\"text\"  style=\"display:inline;width:250px\" class=\"form-control\" " +
                " id=\"${ns}keySearch_journalPapersType\" onkeypress=\"${ns}chk(\'journalPapersType\',event)\" aria-describedby=\"inputSuccess4Status\" value=\"" + keySearch + "\"/>" +
                "<button onclick=\"${ns}doSearchBox('journalPapersType',1)\" type=\"button\" class=\"btn btn-success\" style=\"margin-top:-8px;margin-left:5px;\">Search</button>" +
                "</div>";
        var str = "<table class=\"table table-hover table-striped table-bordered table-condensed\" border=\"1\" style=\"font-size: 12px\"> " +
                " <thead>    " +
                "  <tr>" +
                "		<th width=\"10%\" style=\"text-align: center\"><div class=\"th_class\">รหัสประเภทแหล่งทุน</div></th> " +
                "		<th width=\"90%\" style=\"text-align: center\"><div class=\"th_class\">ชื่อประเภทแหล่งทุน</div></th> " +
                "	</tr> 		" +
                " </thead><tbody>";

        var pageObj = {
            pageSize: PAGE_SIZE_POPUP,
            pageNo: pageNo
        }
        var journalPapersTypeM = {
            keySearch: keySearch,
            paging: pageObj
        }
        ResearchAjax.getJournalPapersTypeList(journalPapersTypeM, {
            callback: function (data) {
                var maxRow = data.maxRow;
                var lastpage = data.lastpage;
                data = data.resultListObj;
                if (data != null && data.length > 0) {
                    for (var i = 0; i < data.length; i++) {
                        str = str + "<tr  onclick=\"${ns}selectjournalPapersType('" + data[i].journalPapersTypeId + "')\" style=\"cursor: pointer;\">" +
                                "<td style=\"text-align: left\"  >" + data[i].jptCode + "</td>" +
                                "<td style=\"text-align: left\"  >" + data[i].jptName + "</td>" +
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
                    pagingStr = pagingStr + "<li><a style=\"cursor: pointer;\" onclick='${ns}doSearchBox(\"journalPapersType\"," + (pageNo - 1) + ")'>Prev</a></li>";

                for (var j = pageStart; j <= pageEnd; j++) {
                    if (pageNo == j) {
                        pagingStr = pagingStr + "<li><a class=\"active\" style=\"background-color: #ddd\" >" + j + "</a></li>";
                    }
                    else {
                        pagingStr = pagingStr + "<li><a class=\"active\" style=\"cursor: pointer;\" onclick='${ns}doSearchBox(\"journalPapersType\"," + (j) + ")'>" + j + "</a></li>";
                    }
                }
                if (pageEnd < lastpage) {
                    pagingStr = pagingStr + "<li><a class=\"active\" style=\"cursor: pointer;\" onclick='${ns}doSearchBox(\"journalPapersType\"," + (pageEnd) + ")'>...</a></li>";
                    pagingStr = pagingStr + "<li><a class=\"active\" style=\"cursor: pointer;\" onclick='${ns}doSearchBox(\"journalPapersType\"," + (lastpage) + ")'>" + lastpage + "</a></li>";
                }
                if (pageNo != lastpage) {
                    pagingStr = pagingStr + "<li><a style=\"cursor: pointer;\" onclick='${ns}doSearchBox(\"journalPapersType\"," + ((pageNo < lastpage) ? (pageNo + 1) : (lastpage)) + ")'>Next</a></li>";
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
                    bootbox.dialog(keyBox + "<div id=\"${ns}journalPapersTypePopupTable\">" + str + pagingStr + "</div>", [{
                        "label": "Close",
                        "class": "btn-danger",
                        "callback": function () {
                        }

                    }]);
                } else {
                    $("#${ns}journalPapersTypePopupTable").html(str + pagingStr);
                }
            }
        });
    }
    function ${ns}selectjournalPapersType(objID) {

        ResearchAjax.findJournalPapersTypeById(objID, {
            callback: function (data) {
                $('input[id="journalPaperM.journalPapersType"]').val(objID)
                $("#journalPapersType_autocomplete").val(data.jptName);

                bootbox.hideAll();
            }
        });
    }
    <%-- end journalPapersType Popup --%>



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

    <%-- start researcher Popup --%>
    function ${ns}researcherPopup(keySearch, init, pageNo) {
        var keyBox = " <div>" +
                "<input type=\"text\"  style=\"display:inline;width:250px\" class=\"form-control\" " +
                " id=\"${ns}keySearch_researcher\" onkeypress=\"${ns}chk(\'researcher\',event)\" aria-describedby=\"inputSuccess4Status\" value=\"" + keySearch + "\"/>" +
                "<button onclick=\"${ns}doSearchBox('researcher',1)\" type=\"button\" class=\"btn btn-success\" style=\"margin-top:-8px;margin-left:5px;\">Search</button>" +
                "</div>";
        var str = "<table class=\"table table-hover table-striped table-bordered table-condensed\" border=\"1\" style=\"font-size: 12px\"> " +
                " <thead>    " +
                "  <tr>" +
                "		<th width=\"50%\" style=\"text-align: center\"><div class=\"th_class\">ชื่อนักวิจัย/นามสกุล(T)</div></th> " +
                "		<th width=\"50%\" style=\"text-align: center\"><div class=\"th_class\">ชื่อนักวิจัย/นามสกุล(E)</div></th> " +
                "	</tr> 		" +
                " </thead><tbody>";

        var pageObj = {
            pageSize: PAGE_SIZE_POPUP,
            pageNo: pageNo
        }
        var researcherM = {
            keySearch: keySearch,
            paging: pageObj
        }
        ResearchAjax.getResearcherList(researcherM, {
            callback: function (data) {
                var maxRow = data.maxRow;
                var lastpage = data.lastpage;
                data = data.resultListObj;
                if (data != null && data.length > 0) {
                    for (var i = 0; i < data.length; i++) {
                        str = str + "<tr  onclick=\"${ns}selectresearcher('" + data[i].researcherId + "')\" style=\"cursor: pointer;\">" +
                                "<td style=\"text-align: left\"  >" + data[i].nameThai + " " + data[i].surnameThai + "</td>" +
                                "<td style=\"text-align: left\"  >" + data[i].nameEng + " " + data[i].surnameEng + "</td>" +
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
                    pagingStr = pagingStr + "<li><a style=\"cursor: pointer;\" onclick='${ns}doSearchBox(\"researcher\"," + (pageNo - 1) + ")'>Prev</a></li>";

                for (var j = pageStart; j <= pageEnd; j++) {
                    if (pageNo == j) {
                        pagingStr = pagingStr + "<li><a class=\"active\" style=\"background-color: #ddd\" >" + j + "</a></li>";
                    }
                    else {
                        pagingStr = pagingStr + "<li><a class=\"active\" style=\"cursor: pointer;\" onclick='${ns}doSearchBox(\"researcher\"," + (j) + ")'>" + j + "</a></li>";
                    }
                }
                if (pageEnd < lastpage) {
                    pagingStr = pagingStr + "<li><a class=\"active\" style=\"cursor: pointer;\" onclick='${ns}doSearchBox(\"researcher\"," + (pageEnd) + ")'>...</a></li>";
                    pagingStr = pagingStr + "<li><a class=\"active\" style=\"cursor: pointer;\" onclick='${ns}doSearchBox(\"researcher\"," + (lastpage) + ")'>" + lastpage + "</a></li>";
                }
                if (pageNo != lastpage) {
                    pagingStr = pagingStr + "<li><a style=\"cursor: pointer;\" onclick='${ns}doSearchBox(\"researcher\"," + ((pageNo < lastpage) ? (pageNo + 1) : (lastpage)) + ")'>Next</a></li>";
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
                    bootbox.dialog(keyBox + "<div id=\"${ns}researcherPopupTable\">" + str + pagingStr + "</div>", [{
                        "label": "Close",
                        "class": "btn-danger",
                        "callback": function () {
                        }

                    }]);
                } else {
                    $("#${ns}researcherPopupTable").html(str + pagingStr);
                }
            }
        });
    }
    function ${ns}selectresearcher(objID) {
        ResearchAjax.findResearcherById(objID, {
            callback: function (data) {
                //	$('input[id="researchProjectM.researcherId"]').val(objID);
                $("#journalPapersWriterId_assign_autocomplete").val(data.nameThai + " " + data.surnameThai);

                $('#${ns}journalPapersWriter_researcherId').val(objID);
                $('#${ns}journalPapersWriter_researcherName').val(data.nameThai + " " + data.surnameThai);
                $('#${ns}journalPapersWriter_positionId').val((data.position != null && data.position.positionId) ? data.position.positionId : "");
                $('#${ns}journalPapersWriter_organizationId').val((data.organization != null && data.organization.organizationId) ? data.organization.organizationId : "");

                $('#${ns}journalPapersWriter_position').val((data.position != null && data.position.positionName) ? data.position.positionName : "");
                $('#${ns}journalPapersWriter_organization').val((data.organization != null && data.organization.orgName) ? data.organization.orgName : "");

                bootbox.hideAll();
            }
        });
    }
    <%-- end researcher Popup --%>

    <%-- start publishLevel Popup --%>
    function ${ns}publishLevelPopup(keySearch, init, pageNo) {
        var keyBox = " <div>" +
                "<input type=\"text\"  style=\"display:inline;width:250px\" class=\"form-control\" " +
                " id=\"${ns}keySearch_publishLevel\" onkeypress=\"${ns}chk(\'publishLevel\',event)\" aria-describedby=\"inputSuccess4Status\" value=\"" + keySearch + "\"/>" +
                "<button onclick=\"${ns}doSearchBox('publishLevel',1)\" type=\"button\" class=\"btn btn-success\" style=\"margin-top:-8px;margin-left:5px;\">Search</button>" +
                "</div>";
        var str = "<table class=\"table table-hover table-striped table-bordered table-condensed\" border=\"1\" style=\"font-size: 12px\"> " +
                " <thead>    " +
                "  <tr>" +
                "		<th width=\"10%\" style=\"text-align: center\"><div class=\"th_class\">รหัสระดับการตีพิมพ์</div></th> " +
                "		<th width=\"90%\" style=\"text-align: center\"><div class=\"th_class\">ชื่อระดับการตีพิมพ์</div></th> " +
                "	</tr> 		" +
                " </thead><tbody>";

        var pageObj = {
            pageSize: PAGE_SIZE_POPUP,
            pageNo: pageNo
        }
        var publishLevelM = {
            keySearch: keySearch,
            paging: pageObj
        }
        ResearchAjax.getPublishLevelList(publishLevelM, {
            callback: function (data) {
                var maxRow = data.maxRow;
                var lastpage = data.lastpage;
                data = data.resultListObj;
                if (data != null && data.length > 0) {
                    for (var i = 0; i < data.length; i++) {
                        str = str + "<tr  onclick=\"${ns}selectpublishLevel('" + data[i].publishLevelId + "')\" style=\"cursor: pointer;\">" +
                                "<td style=\"text-align: left\"  >" + data[i].plCode + "</td>" +
                                "<td style=\"text-align: left\"  >" + data[i].plName + "</td>" +
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
                    pagingStr = pagingStr + "<li><a style=\"cursor: pointer;\" onclick='${ns}doSearchBox(\"publishLevel\"," + (pageNo - 1) + ")'>Prev</a></li>";

                for (var j = pageStart; j <= pageEnd; j++) {
                    if (pageNo == j) {
                        pagingStr = pagingStr + "<li><a class=\"active\" style=\"background-color: #ddd\" >" + j + "</a></li>";
                    }
                    else {
                        pagingStr = pagingStr + "<li><a class=\"active\" style=\"cursor: pointer;\" onclick='${ns}doSearchBox(\"publishLevel\"," + (j) + ")'>" + j + "</a></li>";
                    }
                }
                if (pageEnd < lastpage) {
                    pagingStr = pagingStr + "<li><a class=\"active\" style=\"cursor: pointer;\" onclick='${ns}doSearchBox(\"publishLevel\"," + (pageEnd) + ")'>...</a></li>";
                    pagingStr = pagingStr + "<li><a class=\"active\" style=\"cursor: pointer;\" onclick='${ns}doSearchBox(\"publishLevel\"," + (lastpage) + ")'>" + lastpage + "</a></li>";
                }
                if (pageNo != lastpage) {
                    pagingStr = pagingStr + "<li><a style=\"cursor: pointer;\" onclick='${ns}doSearchBox(\"publishLevel\"," + ((pageNo < lastpage) ? (pageNo + 1) : (lastpage)) + ")'>Next</a></li>";
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
                    bootbox.dialog(keyBox + "<div id=\"${ns}publishLevelPopupTable\">" + str + pagingStr + "</div>", [{
                        "label": "Close",
                        "class": "btn-danger",
                        "callback": function () {
                        }

                    }]);
                } else {
                    $("#${ns}publishLevelPopupTable").html(str + pagingStr);
                }
            }
        });
    }
    function ${ns}selectpublishLevel(objID) {

        ResearchAjax.findPublishLevelById(objID, {
            callback: function (data) {
                $('input[id="journalPaperM.journalPapersJournal.level"]').val(objID)
                $("#publishLevel_autocomplete").val(data.plName);

                bootbox.hideAll();
            }
        });
    }
    <%-- end publishLevel Popup --%>


    function ${ns}confirmDelete(_urlDelete) {

        var agree = confirm(" ต้องการลบข้อมูลหรือไม่? ");
        //alert(_urlDeleteG)
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
    function ${ns}changePublished(objectPublished){
        var ischecked=objectPublished.checked;
        var uncheck="true";
        if(ischecked){
            uncheck="false";
        }
       // alert(ischecked)
       // var checked_ispublish_value=$('input[name="journalPaperM.journalPapersJournal.ispublished"]').prop("checked");
        //alert($(".ispublished_"+checked_ispublish_value).length)
        ////$(".ispublished_"+checked_ispublish_value).length
        $(".ispublished_"+uncheck).each(function(  ) {
            $( this ).hide('slow')
        });
        $(".ispublished_"+ischecked).each(function(  ) {
            $( this ).show('slow')
        });
    }

</script>
</body>
</html>
