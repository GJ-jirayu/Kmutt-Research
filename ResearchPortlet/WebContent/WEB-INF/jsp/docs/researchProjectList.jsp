<%@ include file="/WEB-INF/jsp/include.jsp" %>
<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="kmutt" %>
<portlet:resourceURL var="research_group_resource_get_xls" id="research_group_resource_get_xls"></portlet:resourceURL>
<c:set var="ns"><portlet:namespace/></c:set>

<portlet:actionURL var="formAction">
    <portlet:param name="action" value="doSubmit"/>
</portlet:actionURL>
<portlet:renderURL var="list">
    <portlet:param name="action" value="list"/>
</portlet:renderURL>
<jsp:include page="../header.jsp"/>
<body>
<!-- for tab fillter -->
<kmutt:tabFilter commonForm="${researchProjectForm}"/>
<form:form id="researchProjectForm" modelAttribute="researchProjectForm" method="post" name="researchProjectForm"
           action="${formAction}" enctype="multipart/form-data">
    <fieldset class="kmutt_fieldset">
        <!-- for hidden field search section -->
        <kmutt:hiddenFieldSearch commonForm="${researchProjectForm}"/>
        <div class="kmutt_block">
            <table class="kmutt_section_table_filter">
                <tbody>
                <tr>
                    <td align="left" width="100%" colspan="2">
                <span style="padding-left: 20px;font-size: 13px;">
                <c:if test="${not empty errorMessage}">
                    <div class="alert alert-error">
                        <button type="button" class="close" data-dismiss="alert">&times;</button>
                        <spring:message code="${errorMessage}"/>
                    </div>
                </c:if>
                    <!-- for input filter section -->
                    <kmutt:filter/>
                </span>
                    </td>
                </tr>
                <tr>
                    <td align="left" width="30%">
                        <button onclick='${ns}rederPage("
                            <portlet:renderURL>
                                <portlet:param name="action" value="addEditView"/>
                                <portlet:param name="mode" value="add"/>
                                <portlet:param name="researchProjectId" value="0"/>
                            </portlet:renderURL>")' type="button" class="btn btn-primary">Add
                        </button>
                        <button onclick="${ns}doDeleteItems()" class="btn btn-danger" type="button">
                            <c:if test="${researchProjectForm.filter=='3'}">
                                Delete
                            </c:if>
                            <c:if test="${researchProjectForm.filter!='3'}">
                                Trash
                            </c:if>
                        </button>
                    </td>
                    <td align="right" width="70%">

                    </td>
                </tr>
                </tbody>
            </table>
            <div id="search_section_application">
                <table class="table-hover table-striped table-bordered table-condensed" border="1"
                       style="font-size: 12px">
                    <thead>
                    <tr>
                        <th width="2%">
                            <div class="th_class"><input type="checkbox"
                                                         onchange="${ns}changeSelectAll(this)"/></div>
                        </th>
                        <th width="7%">
                            <div class="th_class">ปีงบประมาณ</div>
                        </th>
                        <th width="35%">
                            <div class="th_class">ชื่อโครงการ(T)</div>
                        </th>
                        <th width="35%">
                            <div class="th_class">ชื่อโครงการ(E)</div>
                        </th>
                        <th width="5%">
                            <div class="th_class">Created</div>
                        </th>
                        <th width="5%">
                            <div class="th_class">Updated</div>
                        </th>
                        <th width="11%">
                            <div class="th_class"></div>
                        </th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:set var="userIdAsString">${user.userId}</c:set>
                    <c:if test="${not empty researchProjects}">
                        <c:forEach items="${researchProjects}" var="researchProject" varStatus="loop">
                            <tr style="cursor: pointer;">
                                <c:set var="havePermissionOnDocs" value="${( userIdAsString == researchProject.createdBy
                       				|| userIdAsString==researchProject.updatedBy || researchProject.isdocAssign ) }"></c:set>

                                <td><form:checkbox path="ids" disabled="${!havePermissionOnDocs}"
                                                   id="${ns}_x_${researchProject.researchProjectId}"
                                                   value="${researchProject.researchProjectId}"/></td>
                                <td style="text-align: left" onclick='${ns}rederPage("
                                    <portlet:renderURL>
                                        <portlet:param name="action" value="addEditView"/>
                                        <portlet:param name="mode" value="edit"/>
                                        <portlet:param name="researchProjectId">
                                            <jsp:attribute name="value">
                                                <c:out value="${researchProject.researchProjectId}"/>
                                            </jsp:attribute>
                                        </portlet:param>
                                    </portlet:renderURL>")'>${researchProject.budgetYear}</td>
                                <td style="text-align: left" onclick='${ns}rederPage("
                                    <portlet:renderURL>
                                        <portlet:param name="action" value="addEditView"/>
                                        <portlet:param name="mode" value="edit"/>
                                        <portlet:param name="researchProjectId">
                                            <jsp:attribute name="value">
                                                <c:out value="${researchProject.researchProjectId}"/>
                                            </jsp:attribute>
                                        </portlet:param>
                                    </portlet:renderURL>")'>${researchProject.thaiName}</td>
                                <td style="text-align: left" onclick='${ns}rederPage("
                                    <portlet:renderURL>
                                        <portlet:param name="action" value="addEditView"/>
                                        <portlet:param name="mode" value="edit"/>
                                        <portlet:param name="researchProjectId">
                                            <jsp:attribute name="value">
                                                <c:out value="${researchProject.researchProjectId}"/>
                                            </jsp:attribute>
                                        </portlet:param>
                                    </portlet:renderURL>")'>${researchProject.engName} </td>
                                <fmt:formatDate type="time" value="${researchProject.createdDate}" pattern="dd/MM/yyyy"
                                                var="createdDate"/>
                                <td style="text-align: left" onclick='${ns}rederPage("
                                    <portlet:renderURL>
                                        <portlet:param name="action" value="addEditView"/>
                                        <portlet:param name="mode" value="edit"/>
                                        <portlet:param name="researchProjectId">
                                            <jsp:attribute name="value">
                                                <c:out value="${researchProject.researchProjectId}"/>
                                            </jsp:attribute>
                                        </portlet:param>
                                    </portlet:renderURL>")'>${createdDate} </td>
                                <fmt:formatDate type="time" value="${researchProject.updatedDate}" pattern="dd/MM/yyyy"
                                                var="updatedDate"/>
                                <td style="text-align: left" onclick='${ns}rederPage("
                                    <portlet:renderURL>
                                        <portlet:param name="action" value="addEditView"/>
                                        <portlet:param name="mode" value="edit"/>
                                        <portlet:param name="researchProjectId">
                                            <jsp:attribute name="value">
                                                <c:out value="${researchProject.researchProjectId}"/>
                                            </jsp:attribute>
                                        </portlet:param>
                                    </portlet:renderURL>")'>${updatedDate} </td>
                                <td>
                                    <div class="btn-group">
                                        <a class="btn btn-primary dropdown-toggle" data-toggle="dropdown" href="#">
                                            <i class="icon-align-justify icon-white"></i> Action
                                            <span class="caret"></span>
                                        </a>
                                        <ul class="dropdown-menu">
                                            <c:if test="${havePermissionOnDocs}">
                                                <li><a onclick='${ns}rederPage("
                                                    <portlet:renderURL>
                                                        <portlet:param name="action" value="addEditView"/>
                                                        <portlet:param name="mode" value="edit"/>
                                                        <portlet:param name="researchProjectId">
                                                            <jsp:attribute name="value">
                                                                <c:out value="${researchProject.researchProjectId}"/>
                                                            </jsp:attribute>
                                                        </portlet:param>
                                                    </portlet:renderURL>")'><i class="icon-edit"></i> Edit</a></li>
                                            </c:if>
                                            <li><a onclick='${ns}rederPage("
                                                <portlet:renderURL>
                                                    <portlet:param name="action" value="addEditView"/>
                                                    <portlet:param name="mode" value="copy"/>
                                                    <portlet:param name="researchProjectId">
                                                        <jsp:attribute name="value">
                                                            <c:out value="${researchProject.researchProjectId}"/>
                                                        </jsp:attribute>
                                                    </portlet:param>
                                                </portlet:renderURL>")'><i class="icon-plus-sign"></i> Copy</a></li>

                                            <c:if test="${researchProjectForm.filter=='3'}">
                                                <c:if test="${havePermissionOnDocs}">
                                                    <li><a onclick='return ${ns}confirmUndo("
                                                        <portlet:actionURL>
                                                            <portlet:param name="action" value="undoItem"/>
                                                            <portlet:param name="researchProjectId">
                                                                <jsp:attribute name="value">
                                                                    <c:out value="${researchProject.researchProjectId}"/>
                                                                </jsp:attribute>
                                                            </portlet:param>
                                                        </portlet:actionURL>")'><i class="icon-repeat"></i> Undo</a>
                                                    </li>
                                                    <li><a onclick='return ${ns}confirmDelete("
                                                        <portlet:actionURL>
                                                            <portlet:param name="action" value="delete"/>
                                                            <portlet:param name="researchProjectId">
                                                                <jsp:attribute name="value">
                                                                    <c:out value="${researchProject.researchProjectId}"/>
                                                                </jsp:attribute>
                                                            </portlet:param>
                                                        </portlet:actionURL>")'><i class="icon-trash"></i> Delete</a>
                                                    </li>
                                                </c:if>
                                            </c:if>
                                            <c:if test="${researchProjectForm.filter!='3'}">
                                                <c:if test="${havePermissionOnDocs}">
                                                    <li><a onclick='return ${ns}confirmDelete("
                                                        <portlet:actionURL>
                                                            <portlet:param name="action" value="delete"/>
                                                            <portlet:param name="researchProjectId">
                                                                <jsp:attribute name="value">
                                                                    <c:out value="${researchProject.researchProjectId}"/>
                                                                </jsp:attribute>
                                                            </portlet:param>
                                                        </portlet:actionURL>")'><i class="icon-trash"></i> Trash</a>
                                                    </li>
                                                </c:if>
                                            </c:if>
                                        </ul>
                                    </div>
                                    <c:if test="${researchProject.docType=='DRAFT' && ( user.userId == researchProject.createdBy || user.userId==researchProject.updatedBy ) }">
                                    </c:if>
                                </td>
                            </tr>
                        </c:forEach>
                    </c:if>
                    </tbody>
                </table>
                <!-- for paging -->
                <kmutt:paging commonForm="${researchProjectForm}"/>
            </div>
        </div>
    </fieldset>
</form:form>

<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<jsp:include page="../footer.jsp"/>
<jsp:include page="../script/ReSearchProjectListJS.jsp"/>

</body>
</html>	
