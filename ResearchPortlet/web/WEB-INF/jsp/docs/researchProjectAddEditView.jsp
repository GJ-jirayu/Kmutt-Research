<%@ include file="/WEB-INF/jsp/include.jsp" %>
<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="kmutt" %>

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
<portlet:resourceURL var="research_group_resource_get_byid" id="research_group_resource_get_byid"/>
<jsp:include page="../header.jsp"/>
<body>
<form:form id="researchProjectForm" modelAttribute="researchProjectForm" method="post" name="researchProjectForm"
           action="${formAction}" enctype="multipart/form-data">
    <fieldset class="kmutt_fieldset">
        <input type="hidden" name="command" id="${ns}common" value="${researchProjectForm.command}"/>
        <input type="hidden" name="mode" id="${ns}mode" value="${researchProjectForm.mode}"/>
        <form:hidden path="researchProjectM.researchProjectId" id="researchProjectId"/>
        <input type="hidden" id="${ns}_export_proof_participation"
               value="${researchProjectForm.researchProjectM.proofParticipationResource}"/>
        <input type="hidden" id="${ns}_export_proof_academic"
               value="${researchProjectForm.researchProjectM.proofAcademicResource}"/>

        <c:set var="userIdAsString">${user.userId}</c:set>
        <c:set var="havePermissionOnDocs" value="${researchProjectForm.mode=='edit' && researchProjectForm.researchProjectM.docType=='DRAFT'
    								&& ( userIdAsString == researchProjectForm.researchProjectM.createdBy 
                       				|| userIdAsString==researchProjectForm.researchProjectM.updatedBy || researchProjectForm.researchProjectM.isdocAssign ) }"></c:set>
        <div class="kmutt_block">
            <div class="accordion" id="accordion2">
                <div class="accordion-group">
                    <div class="accordion-heading">
                        <a href="${list}" style="padding-right: 20px;">Back</a>
                        <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion2"
                           href="#collapseOne">
                            ข้อมูลโครงการ
                        </a>
                    </div>
                    <div id="collapseOne" class="accordion-body in collapse" style="height: auto;">
                        <div class="accordion-inner">
                            <table border="0" width="100%" class="kmutt_table_detail_block">
                                <tr>
                                    <td width="100%" colspan="4"></td>
                                </tr>
                                <tr>
                                    <td align="right" width="20%">
                                        <label><span>ปีงบประมาณ:
                        </span> </label>
                                    </td>
                                    <td width="30%">
                                        <form:input path="researchProjectM.budgetYear" maxlength="4"
                                                    cssStyle="width:50px" cssClass="form-control"/>
                                        <span id="error" style="color: Red; display: none"></span>
                                    </td>
                                    <td width="50%">

                                    </td>
                                </tr>
                                <tr valign="top">
                                    <td align="right" width="20%">
                        <span><label>ขื่อโครงการ(T):<span class="kmutt_require_field"> </span>
                        </label></span>
                                    </td>
                                    <td width="80%" colspan="3">

                                        <form:textarea path="researchProjectM.thaiName" cssClass="kmutt_texarea" 
                                                       rows="2"/>
                                    </td>
                                </tr>
                                <tr valign="top">
                                    <td align="right" width="20%">
                        <span><label>ขื่อโครงการ(E):<span class="kmutt_require_field"> </span>
                        </label></span>
                                    </td>
                                    <td width="80%" colspan="3">

                                        <form:textarea path="researchProjectM.engName" cssClass="kmutt_texarea" rows="2"/>
                                    </td>
                                </tr>
                                <tr valign="top">
                                    <td align="right" width="20%">
                        <span><label>รายละเอียดโครงการ:<span class="kmutt_require_field"> </span>
                        </label></span>
                                    </td>
                                    <td width="80%" colspan="3">

                                        <form:textarea path="researchProjectM.detail" cssClass="kmutt_texarea" rows="2"/>
                                    </td>
                                </tr>
                                <tr valign="top">
                                    <td align="right" width="20%">
                        <span><label>รหัสประเภทโครงการ:<span class="kmutt_require_field"> </span>
                        </label></span>
                                    </td>
                                    <td width="80%" colspan="3">

                                        <form:select path="researchProjectM.projectType">
                                            <form:option value="1">การวิจัยพื้นฐาน</form:option>
                                            <form:option value="2">การวิจัยประยุกต์</form:option>
                                            <form:option value="3">การวิจัยพัฒนา</form:option>
                                        </form:select>
                                    </td>
                                </tr>
                                <tr valign="top">
                                    <td align="right" width="20%">
                        <span><label>รหัสหมวด NRCT:<span class="kmutt_require_field"> </span>
                        </label></span>
                                    </td>
                                    <td width="80%" colspan="3">

                                        <form:input path="researchProjectM.nrctCatelogry" cssClass="form-control"/>
                                    </td>
                                </tr>
                                <tr valign="top">
                                    <td align="right" width="20%">
                        <span><label>วัตถุประสงค์:<span class="kmutt_require_field"> </span>
                        </label></span>
                                    </td>
                                    <td width="80%" colspan="3">

                                        <form:textarea path="researchProjectM.objectiveTitle" cssClass="kmutt_texarea"
                                                       rows="2"/>
                                    </td>
                                </tr>
                                <tr valign="top">
                                    <td align="right" width="20%">
                        <span><label>บทคัดย่อ:<span class="kmutt_require_field"> </span>
                        </label></span>
                                    </td>
                                    <td width="80%" colspan="3">

                                        <form:textarea path="researchProjectM.abstractTitle" cssClass="kmutt_texarea"
                                                       rows="2"/>
                                    </td>
                                </tr>
                                <tr valign="top">
                                    <td align="right" width="20%">
                        <span><label>คำค้น:<span class="kmutt_require_field"> </span>
                        </label></span>
                                    </td>
                                    <td width="80%" colspan="3">

                                        <form:input path="researchProjectM.keyworkTitle" cssClass="form-control"/>
                                    </td>
                                </tr>
                                <tr valign="top">
                                    <td align="right" width="20%">
                        <span><label>อ้างอิง:<span class="kmutt_require_field"> </span>
                        </label></span>
                                    </td>
                                    <td width="80%" colspan="3">

                                        <form:input path="researchProjectM.reference" cssClass="form-control"/>
                                    </td>
                                </tr>
                                <tr>
                                    <td align="right" width="20%">
                        <span><label>ระยะที่:<span class="kmutt_require_field"> </span>
                        </label></span>
                                    </td>
                                    <td width="80%" colspan="3">
                                        <div>

                                            <form:input path="researchProjectM.phase" cssStyle="width:50px;"
                                                        cssClass="form-control"/>
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td align="right" width="20%">
                        <span><label>กลุ่มวิจัย:<span class="kmutt_require_field"> </span>
                        </label></span>
                                    </td>
                                    <td width="80%" colspan="3">
                                        <div>
                                            <form:hidden path="researchProjectM.researchGroupId"/>
                                            <input type="text"  class="form-control kmutt_lookup_field"
                                                   id="researchGroupId_assign_autocomplete" placeholder=""
                                                   value="${researchProjectForm.researchProjectM.researchGroupShow}"/>
                                            <button style="margin-top: -8px"
                                                    onclick="${ns}researchGroupPopup('',true,1)" type="button"
                                                    class="btn btn-warning btn-small">...
                                            </button>
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td align="right" width="20%">
                        <span><label>หน่วยงาน:<span class="kmutt_require_field"> </span>
                        </label></span>
                                    </td>
                                    <td width="80%" colspan="3">
                                        <div>
                                            <form:hidden path="researchProjectM.organizationId"/>
                                            <input type="text" 
                                                   class="form-control kmutt_lookup_field" id="organizationId_assign_autocomplete"
                                                   value="${researchProjectForm.researchProjectM.organizationShow}"
                                                   placeholder=""/>
                                            <button style="margin-top: -8px" onclick="${ns}organizationPopup('',true,1)"
                                                    type="button" class="btn btn-warning btn-small">...
                                            </button>
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td align="right" width="20%">
                                        <label><span>วันที่เริ่มโครงการ:<span class="kmutt_require_field"></span>
                        </span> </label>
                                    </td>
                                    <td width="30%">
                     <span>
                      <input type="text" value="${researchProjectForm.startDate}" placeholder="dd/mm/yyyy" readonly="readonly" id="startDate"
                             name="startDate" style="width:75px" class="form-control"/>
                    </span>
                                    </td>
                                    <td align="left" width="20%" colspan="2">
                        <span style="font-size: 16px"> วันที่จบโครงการ:<span class="kmutt_require_field"></span>
                      <input type="text" value="${researchProjectForm.endDate}" placeholder="dd/mm/yyyy" readonly="readonly" id="endDate"
                             name="endDate" style="width:75px" class="form-control"/>
                        </span>
                                    </td>
                                </tr>
                                <tr>
                                    <td align="right" width="20%">
                                        <label><span>งบประมาณที่เสนอขอ:<span class="kmutt_require_field"></span>
                        </span> </label>
                                    </td>
                                    <td width="30%">

                                        <form:input path="researchProjectM.purposeBudget" cssStyle="width:75px"
                                                    cssClass="form-control"/>

                                    </td>
                                    <td align="left" width="20%" colspan="2">
                        <span style="font-size:16px"> งบประมาณที่อนุมัติ:<span class="kmutt_require_field"></span>
                        
                         <form:input path="researchProjectM.submitBudget" cssStyle="width:75px"
                                     cssClass="form-control"/>
                        </span>
                                    </td>
                                </tr>
                                <tr>
                                    <td align="right" width="20%">
                        <span><label>แหล่งทุน:<span class="kmutt_require_field"> </span>
                        </label></span>
                                    </td>
                                    <td width="80%" colspan="3">
                                        <div>
                                            <form:hidden path="researchProjectM.fundingResourceId"/>
                                            <input type="text" 
                                                   class="form-control kmutt_lookup_field" id="fundingResourceId_assign_autocomplete"
                                                   value="${researchProjectForm.researchProjectM.fundingResourceShow}"
                                                   placeholder=""/>
                                            <button style="margin-top: -8px"
                                                    onclick="${ns}fundingResourcePopup('',true,1)" type="button"
                                                    class="btn btn-warning btn-small">...
                                            </button>
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td align="right" width="20%">
                                        <label><span>รายงานฉบับสมบูรณ์:<span class="kmutt_require_field"></span>
                        </span> </label>
                                    </td>
                                    <td width="30%">
                      <span style="padding-left:5px;font-size: 16px"> กำหนดส่ง:<span class="kmutt_require_field">
                        </span> 
                      <input type="text" value="${researchProjectForm.reportDuedate}" placeholder="dd/mm/yyyy"  readonly="readonly"
                             id="reportDuedate" name="reportDuedate" style="width:75px" class="form-control"/>
                      </span>
                                    </td>
                                    <td align="left" width="20%" colspan="2">
                        <span style="font-size: 16px"> ส่งจริง:<span ></span>
                      <input type="text" value="${researchProjectForm.reportSubmitDate}" placeholder="dd/mm/yyyy" readonly="readonly"
                             id="reportSubmitDate" name="reportSubmitDate" style="width:75px" class="form-control"/>
                         </span>
                                    </td>
                                </tr>
                                <tr valign="top">
                                    <td align="right" width="20%">
                        <span><label>หมายเหตุ:<span > </span>
                        </label></span>
                                    </td>
                                    <td width="80%" colspan="3">
                                        <form:textarea path="researchProjectM.remark" cssClass="kmutt_texarea" rows="2"/>
                                    </td>
                                </tr>
                                <c:if test="${mode=='edit'}">
                                    <tr valign="top">
                                        <td align="right" width="20%">
                        <span><label>Assign to:<span class="kmutt_require_field"> </span>
                        </label></span>
                                        </td>
                                        <td width="80%" colspan="3">
                                            <input type="hidden" id="docsAssign" name="docsAssign"/>
                                            <input type="text" style="width:250px"
                                                   class="form-control kmutt_lookup_field" id="docs_assign_autocomplete" placeholder=""/>
                                            <c:if test="${havePermissionOnDocs}">
                                                <span id="docs_assignShow"> </span>
                                                <button style="margin-top: -8px"
                                                        onclick="${ns}docsAssignPopup('',true,1)" type="button"
                                                        class="btn btn-warning btn-small">...
                                                </button>
                                            </c:if>
                                        </td>
                                    </tr>
                                </c:if>
                                <tr valign="top">
                                    <td align="right" width="20%">
                        <span><label><span class="kmutt_require_field"> </span>
                        </label></span>
                                    </td>
                                    <td width="80%" colspan="3">
              <span id="${ns}doctype_assign_list">
               <c:if test="${not empty researchProjectForm.researchProjectM.docAssignMappings}">
                   <table class="table table-hover table-hover table-striped table-bordered table-condensed" border="0"
                          style="width:30%;font-size: 12px">
                       <c:forEach items="${researchProjectForm.researchProjectM.docAssignMappings}"
                                  var="docAssignMapping" varStatus="loop">
                           <tr style="cursor: pointer;">
                               <td width="5%" style="text-align: left">${loop.index+1}</td>
                               <td width="95%" style="text-align: left">
                                       ${docAssignMapping.userPortal.emailAddress}
                               </td>
                               <c:if test="${havePermissionOnDocs}">
                                   <td width="5%" style="text-align: center">
                                       <button onclick='${ns}doDeleteDocAssignMappingAjax("${docAssignMapping.refId}","${docAssignMapping.refType}","${docAssignMapping.userId}")'
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
                                        <form:hidden path="researchProjectM.docType"/>
                                        <c:if test="${mode=='edit'}">
                                            <c:if test="${havePermissionOnDocs}">

                                                <button type="button" onclick="${ns}doSubmitForm('DRAFT')"
                                                        class="btn btn-primary">Save as Draft
                                                </button>
                                                <button id="${ns}publish_element" type="button" onclick="${ns}doSubmitForm('PUBLISH')"
                                                        class="btn btn-primary">Submit
                                                </button>
                                                <c:if test="${researchProjectForm.researchProjectM.flag=='0'}">
                                                <button type="button" class="btn" onclick='return ${ns}confirmUndo("
                                                <portlet:actionURL>
                                                    <portlet:param name="action" value="undoItem"/>
                                                <portlet:param name="researchProjectId">
                                                <jsp:attribute name="value">
                                                    <c:out value="${researchProjectForm.researchProjectM.researchProjectId}"/>
                                                </jsp:attribute>
                                                </portlet:param>
                                                </portlet:actionURL>")'> Undo</button>
                                                </c:if>
                                                <button class="btn btn-danger" type="button"
                                                        onclick='return ${ns}confirmDelete("
                                                            <portlet:actionURL>
                                                                <portlet:param name="action" value="delete"/>
                                                                <portlet:param name="researchProjectId">
                                                                    <jsp:attribute name="value">
                                                                        <c:out value="${researchProjectForm.researchProjectM.researchProjectId}"/>
                                                                    </jsp:attribute>
                                                                </portlet:param>
                                                            </portlet:actionURL>")'>
                                                    <c:if test="${researchProjectForm.researchProjectM.flag=='1'}">
                                                        ลบ
                                                    </c:if>
                                                    <c:if test="${researchProjectForm.researchProjectM.flag=='0'}">
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
                           <kmutt:researcherSection researchers="${researchProjectForm.researchProjectM.researchers}"
                                                    havePermissionOnDocs="${havePermissionOnDocs}" />
                        </div>
                    </div>
                </div>
                <div class="accordion-group">
                    <div class="accordion-heading">
                        <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion2"
                           href="#collapseThree">
                            ผลความก้าวหน้า
                        </a>
                    </div>
                    <div id="collapseThree" class="accordion-body collapse" style="height: 0px;">
                        <div class="accordion-inner">
                           <kmutt:progressSection progresList="${researchProjectForm.researchProjectM.progresList}"
                           havePermissionOnDocs="${havePermissionOnDocs}" />
                        </div>
                    </div>

                </div>
                <div class="accordion-group">
                    <div class="accordion-heading">
                        <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion2"
                           href="#collapseFour">
                            การรับเงินงวด
                        </a>
                    </div>
                    <div id="collapseFour" class="accordion-body collapse" style="height: 0px;">
                        <div class="accordion-inner">
                            <kmutt:paymentSection payments="${researchProjectForm.researchProjectM.payments}"
                                                  havePermissionOnDocs="${havePermissionOnDocs}">
                                <jsp:attribute name="amountReceivedShow">
                                    <fmt:formatNumber type="number" pattern="###,###,###.00"
                                                      value="${amountReceived_name_given}"
                                                   var="amountReceived"/>
                                        ${amountReceived}
                                </jsp:attribute>
                            </kmutt:paymentSection>
                        </div>
                    </div>
                </div>
                <div class="accordion-group">
                    <div class="accordion-heading">
                        <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion2"
                           href="#collapseFive">
                            การเบิกเงินงวด
                        </a>
                    </div>
                    <div id="collapseFive" class="accordion-body collapse" style="height: 0px;">
                        <div class="accordion-inner">
                            <kmutt:withDrawSection withdraws="${researchProjectForm.researchProjectM.withdraws}"
                                                   havePermissionOnDocs="${havePermissionOnDocs}">
                                <jsp:attribute name="amountWithdrawShow">
                                    <fmt:formatNumber type="number" pattern="###,###,###.00"
                                                      value="${amountWithdraw_name_given}"
                                                      var="amountWithdraw"/>
                                    ${amountWithdraw}
                                </jsp:attribute>
                                <jsp:attribute name="balanceShow">
                                    <fmt:formatNumber type="number" pattern="###,###,###.00"
                                                      value="${balance_name_given}"
                                                      var="balance"/>
                                    ${balance}
                                </jsp:attribute>
                            </kmutt:withDrawSection>
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
                            <kmutt:documentSection documents="${researchProjectForm.researchProjectM.documents}"
                                                   havePermissionOnDocs="${havePermissionOnDocs}"/>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </fieldset>
</form:form>
<!--  Bootstrap  core JavaScript
================================================== -->
<!--  Placed at the end of the document so the pages load faster -->
<jsp:include page="../footer.jsp"/>
<jsp:include page="../script/ResearchProjectAddEditViewJS.jsp"/>

</body>
</html>
