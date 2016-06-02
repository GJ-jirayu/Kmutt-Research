<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="portlet" uri="http://java.sun.com/portlet" %>
<%@tag pageEncoding="UTF-8"%>
<c:set var="ns"><portlet:namespace/></c:set>
<%@ attribute name="researchers" required="true" type="java.util.List<th.ac.kmutt.research.model.ResearchProjectResearcherM>" %>
<%@ attribute name="havePermissionOnDocs" required="true" type="java.lang.Boolean" %>
<table border="0" width="100%" style="font-size: 13px">
    <tbody>
    <tr>
        <td align="center" width="100%" colspan="2">
            <div id="${ns}element_researcher"
                 style="display: none" class="kmutt_block">
                <table border="0" width="100%"
                       class="kmutt_table_detail_block">
                    <tr>
                        <td align="right" width="30%">
                        		<span>
                        			<label>นักวิจัย:<span> </span>
                                    </label>
                        		</span>
                        </td>
                        <td width="70%" colspan="3">
                            <div>

                                <input type="hidden" id="${ns}researcher_mode"/>
                                <input type="hidden" id="${ns}researcher_itemList"/>

                                <input type="hidden" id="${ns}researcher_researcherId"/>
                                <input type="hidden" id="${ns}researcher_researcherName"/>
                                <input type="hidden" id="${ns}researcher_positionId"/>
                                <input type="hidden" id="${ns}researcher_organizationId"/>
                                <input type="text" class="form-control kmutt_lookup_field"
                                       id="researcherId_assign_autocomplete"
                                       placeholder=""/>
                                <button id="${ns}researcherListAll"
                                        style="margin-top: -8px;display: none"
                                        onclick="${ns}researcherPopup('',true,1)"
                                        type="button" class="btn btn-warning btn-small">...
                                </button>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td align="right" width="30%">
                        		<span>
                        			<label>ตำแหน่ง:<span> </span>
                                    </label>
                        		</span>
                        </td>
                        <td width="70%" colspan="3">
                            <div>
                                <input type="text" id="${ns}researcher_position"
                                       readonly="readonly" class="form-control">

                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td align="right" width="30%">
                        		<span>
                        			<label>บริษัท/องค์กร:<span class="kmutt_require_field"> </span>
                                    </label>
                        		</span>
                        </td>
                        <td width="70%" colspan="3">
                            <div>
                                <input type="text" id="${ns}researcher_organization"
                                       readonly="readonly" class="form-control">
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td align="right" width="30%">
                        		<span>
                        			<label>เป็นหัวหน้าโครงการ?:<span class="kmutt_lookup_field"> </span>
                                    </label>
                        		</span>
                        </td>
                        <td width="70%" colspan="3">
                            <div>
                                <c:set var="isChecked" value="false"/>
                                <c:set var="isLeader" value="false"/>
                                <c:set var="canPublish" value="false"/>
                                <c:set var="workLoad_all" value="0"/>
                                <c:if test="${not empty researchers}">
                                    <c:forEach items="${researchers}" var="researcher"
                                        varStatus="loop">
                                        <c:set var="workLoad_all" value="${researcher.workLoadRatio+workLoad_all}"/>
                                        <c:if test="${researcher.isprojectLeader=='T' && !isChecked}">
                                            <c:set var="isLeader" value="true"/>
                                            <c:set var="isChecked" value="true"/>
                                        </c:if>
                                    </c:forEach>
                                </c:if>
                                <c:if test="${isLeader}">
                                    <input type="checkbox" disabled="disabled" id="${ns}researcher_isprojectLeader"
                                           class="form-control"/>
                                </c:if>
                                <c:if test="${!isLeader}">
                                     <input type="checkbox" id="${ns}researcher_isprojectLeader"
                                           class="form-control"/>

                                </c:if>
                                <input type="hidden" id="${ns}_isLeader_init" value="${isLeader}">
                                <input type="hidden" id="${ns}workLoad_all" value="${workLoad_all}">
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td align="right" width="30%">
                        		<span>
                        			<label>ภาระการทำงาน:<span class="kmutt_lookup_field"> </span>
                                    </label>
                        		</span>
                        </td>
                        <td width="70%" colspan="3">
                            <div>
                                <input type="text" id="${ns}researcher_workLoadRatio"
                                       style="width:75px" class="form-control">
                            </div>
                        </td>
                    </tr>
                    <c:if test="${havePermissionOnDocs}">
                        <tr>
                            <td align="center" colspan="4" width="20%">
                                <button type="button"
                                        onclick="${ns}doSubmitResearcherAjax()"
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
        <td align="left" width="30%">

        </td>
        <td align="right" width="70%">
                <span sytle="padding-left:20px">
               <c:if test="${havePermissionOnDocs}">
                   <button type="button" onclick="${ns}exportProofFile('participation')" class="btn">Export
                       Participation
                   </button>
                   <button type="button" onclick="${ns}exportProofFile('academic')" class="btn">Export Academic</button>
                   <button onclick="${ns}displayResearcher('add','0')" type="button" class="btn btn-primary">Add
                   </button>
               </c:if>
               </span>
        </td>
    </tr>
    </tbody>
</table>
<span id="${ns}researcher_item_list">
    <table class="table table-hover table-striped table-bordered table-condensed" border="1"
           style="font-size: 12px">
        <thead>
        <tr>
            <th width="5%" style="text-align: center">
                <div class="th_class">#</div>
            </th>
            <th width="25%" style="text-align: center">
                <div class="th_class">ชื่อ - สกุล</div>
            </th>
            <th width="27%" style="text-align: center">
                <div class="th_class">ภาควิชา/หน่วยงาน/บริษัท/องค์กร</div>
            </th>
            <th width="14%" style="text-align: center">
                <div class="th_class">เป็นหัวหน้าโครงการ</div>
            </th>
            <th width="14%" style="text-align: center">
                <div class="th_class">ภาระการทำงาน</div>
            </th>
            <c:if test="${havePermissionOnDocs}">
                <th width="10%">
                    <div class="th_class"></div>
                </th>
            </c:if>
        </tr>
        </thead>
        <tbody>
        <c:if test="${not empty researchers}">
            <c:forEach items="${researchers}" var="researcher"
                       varStatus="loop">

                <tr style="cursor: pointer;">
                    <td onclick='${ns}doEditResearcherAjax("${researcher.researchProjectId}","${researcher.itemList}")'
                        style="text-align: left">${loop.index+1}</td>
                    <td onclick='${ns}doEditResearcherAjax("${researcher.researchProjectId}","${researcher.itemList}")'
                        style="text-align: left">${researcher.position.positionName} ${researcher.researcherName}</td>
                    <td onclick='${ns}doEditResearcherAjax("${researcher.researchProjectId}","${researcher.itemList}")'
                        style="text-align: left">${researcher.organization.orgName}</td>
                    <td onclick='${ns}doEditResearcherAjax("${researcher.researchProjectId}","${researcher.itemList}")'
                        style="text-align: center">
                        <c:if test="${researcher.isprojectLeader=='T'}">
                            เป็น
                        </c:if>
                        <c:if test="${researcher.isprojectLeader!='T'}">
                            ไม่เป็น
                        </c:if>
                    </td>
                    <td onclick='${ns}doEditResearcherAjax("${researcher.researchProjectId}","${researcher.itemList}")'
                        style="text-align: center">${researcher.workLoadRatio}</td>
                    <c:if test="${havePermissionOnDocs}">
                        <td style="text-align: center">
                            <button onclick='${ns}doEditResearcherAjax("${researcher.researchProjectId}","${researcher.itemList}")'
                                    class="btn btn-small" type="button">แก้ใข
                            </button>
                            <button onclick='${ns}doDeleteResearcherAjax("${researcher.researchProjectId}","${researcher.itemList}")'
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