<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="portlet" uri="http://java.sun.com/portlet" %>
<%@tag pageEncoding="UTF-8"%>
<c:set var="ns"><portlet:namespace/></c:set>
<%@ attribute name="progresList" required="true"
              type="java.util.List<th.ac.kmutt.research.model.ResearchProjectProgressM>" %>
<%@ attribute name="havePermissionOnDocs" required="true" type="java.lang.Boolean" %>

<table border="0" width="100%" style="font-size: 13px">
    <tbody>
    <tr>
        <td align="center" width="100%" colspan="2">
            <div id="${ns}element_progress"
                 style="display: none" class="kmutt_block">
                <table border="0" width="100%"
                      class="kmutt_table_detail_block">
                    <tr>
                        <td align="right" width="30%">
                        		<span>
                        			<label>Due Date:<span class="kmutt_require_field"> </span>
                                    </label>
                        		</span>
                        </td>
                        <td width="70%" colspan="3">
                            <div>
                                <input type="hidden" id="${ns}progress_mode"/>
                                <input type="hidden" id="${ns}progress_itemList"/>
                                <input type="hidden" id="${ns}progress_progressItemId"/>

                                <input type="text" id="${ns}progress_dueDate"
                                       readonly="readonly" style="width:75px"
                                       class="form-control">
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td align="right" width="30%">
                        		<span>
                        			<label>Submit Date:<span class="kmutt_require_field"> </span>
                                    </label>
                        		</span>
                        </td>
                        <td width="70%" colspan="3">
                            <div>
                                <input type="text" id="${ns}progress_submitDate"
                                       readonly="readonly" style="width:75px"
                                       class="form-control">
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td align="right" width="30%">
                        		<span>
                        			<label>Progress(%):<span class="kmutt_require_field"> </span>
                                    </label>
                        		</span>
                        </td>
                        <td width="70%" colspan="3">
                            <div>
                                <input type="text" id="${ns}progress_progressPercentage"
                                       style="width:75px" class="form-control">
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td align="right" width="30%">
                        		<span>
                        			<label>Remark:<span class="kmutt_require_field"> </span>
                                    </label>
                        		</span>
                        </td>
                        <td width="70%" colspan="3">
                            <div>
                                                            <textarea rows="2" cols="2" class="500px"
                                                                      id="${ns}progress_remark"></textarea>

                            </div>
                        </td>
                    </tr>
                    <c:if test="${havePermissionOnDocs}">
                        <tr>
                            <td align="center" colspan="4" width="20%">
                                <button type="button" onclick="${ns}doSubmitProgressAjax()"
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
               <c:if test="${havePermissionOnDocs}">
                   <button onclick="${ns}displayProgress('add','0')" type="button" class="btn btn-primary">Add</button>
               </c:if>
               </span>
        </td>
    </tr>
    </tbody>
</table>
                 <span id="${ns}progress_item_list">
        <table class="table table-hover table-striped table-bordered table-condensed" border="1"
               style="font-size: 12px">
            <thead>
            <tr> <!-- 50 42+8 -->
                <th width="5%" style="text-align: center">
                    <div class="th_class">#</div>
                </th>
                <th width="8%" style="text-align: center">
                    <div class="th_class">Due Date</div>
                </th>
                <th width="8%" style="text-align: center">
                    <div class="th_class">Submit Date</div>
                </th>
                <th width="5%" style="text-align: center">
                    <div class="th_class">Progress(%)</div>
                </th>
                <th width="38%" style="text-align: center">
                    <div class="th_class">Remark</div>
                </th>
                <c:if test="${havePermissionOnDocs}">
                    <th width="8%">
                        <div class="th_class"></div>
                    </th>
                </c:if>
            </tr>
            </thead>
            <tbody>
            <c:if test="${not empty progresList}">
                <c:forEach items="${progresList}" var="progress" varStatus="loop">
                    <tr style="cursor: pointer;">
                        <td onclick='${ns}doEditProgressAjax("${progress.researchProjectId}","${progress.itemList}")'
                            style="text-align: left">${loop.index+1}</td>
                        <td onclick='${ns}doEditProgressAjax("${progress.researchProjectId}","${progress.itemList}")'
                            style="text-align: center">
                                ${progress.dueDateShow}
                        </td>
                        <td onclick='${ns}doEditProgressAjax("${progress.researchProjectId}","${progress.itemList}")'
                            style="text-align: center">
                                ${progress.submitDateShow}
                        </td>
                        <td onclick='${ns}doEditProgressAjax("${progress.researchProjectId}","${progress.itemList}")'
                            style="text-align: center">${progress.progressPercentage}</td>
                        <td onclick='${ns}doEditProgressAjax("${progress.researchProjectId}","${progress.itemList}")'
                            style="text-align: left">${progress.remark}</td>
                        <c:if test="${havePermissionOnDocs}">
                            <td style="text-align: center">
                                <button onclick='${ns}doEditProgressAjax("${progress.researchProjectId}","${progress.itemList}")'
                                        class="btn btn-small" type="button">แก้ใข
                                </button>
                                <button onclick='${ns}doDeleteProgressAjax("${progress.researchProjectId}","${progress.itemList}")'
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