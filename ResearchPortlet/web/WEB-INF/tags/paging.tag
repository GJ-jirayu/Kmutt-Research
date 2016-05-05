<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="portlet" uri="http://java.sun.com/portlet" %>
<%@tag pageEncoding="UTF-8"%>
<c:set var="ns"><portlet:namespace/></c:set>
<%@ attribute name="commonForm" required="true" type="th.ac.kmutt.research.form.CommonForm" %>
<table border="0" width="100%" style="font-size: 13px">
    <tbody>
    <tr>
        <td align="left" width="70%">
                <span class="pagination">
 				 <ul>
                     <c:if test="${commonForm.pageNo!=1}">
                         <li><a style="cursor: pointer;" onclick='${ns}goPrev()'>Prev</a></li>
                     </c:if>
                     <c:if test="${not empty commonForm.pageCount}">
                         <c:forEach begin="${commonForm.pageStart}" end="${commonForm.pageEnd}"
                                    var="loop">
                             <c:if test="${commonForm.pageNo==(loop)}">
                                 <li><a class="active" style="background-color: #ddd">${loop}</a></li>
                             </c:if>
                             <c:if test="${commonForm.pageNo!=(loop)}">
                                 <li><a class="active" style="cursor: pointer;"
                                        onclick='${ns}goToPage("${loop}")'>${loop}</a></li>
                             </c:if>
                         </c:forEach>
                         <c:if test="${commonForm.pageEnd<commonForm.pageCount}">
                             <li><a class="active" style="cursor: pointer;"
                                    onclick='${ns}goToPage("${commonForm.pageEnd}")'>...</a>
                             </li>
                             <li><a class="active" style="cursor: pointer;" onclick='
                                 ${ns}goToPage("${commonForm.pageCount}")'>${commonForm.pageCount}</a>
                             </li>
                         </c:if>
                     </c:if>
                     <c:if test="${commonForm.pageNo!=commonForm.pageCount}">
                         <li><a style="cursor: pointer;" onclick='${ns}goNext()'>Next</a></li>
                     </c:if>
                 </ul>
			</span>
        </td>
        <td align="right" width="30%">
            <form:select path="paging.pageSize" cssStyle="width:50px" onchange='${ns}doChangePage()'>
                <form:option value="10">10</form:option>
                <form:option value="20">20</form:option>
                <form:option value="30">30</form:option>
                <form:option value="50">50</form:option>
            </form:select>
        </td>
    </tr>
    </tbody>
</table>