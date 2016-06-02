<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="portlet" uri="http://java.sun.com/portlet" %>
<%@tag pageEncoding="UTF-8"%>
<c:set var="ns"><portlet:namespace/></c:set>
<div class="form-group has-success has-feedback">
    <label class="control-label">ระบุ key word</label>

    <form:input path="keySearch" cssStyle="display:inline;width:250px" cssClass="form-control" id="keySearch"
                aria-describedby="inputSuccess4Status"/>
    <form:select path="filter" cssStyle="width:90px;" onchange="${ns}doSearch()">
        <form:option value="0">ทั้งหมด</form:option>
        <form:option value="1">อนุมัติ</form:option>
        <form:option value="2">ร่าง</form:option>
        <form:option value="3">ถังขยะ</form:option>
    </form:select>
    <button onclick="${ns}doSearch()" type="button" class="btn btn-success" style="margin-top:-8px;">
        Search
    </button>
</div>