<%@ taglib prefix="portlet" uri="http://java.sun.com/portlet" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@tag pageEncoding="UTF-8"%>
<c:set var="ns"><portlet:namespace/></c:set>
<%@ attribute name="commonForm" required="true" type="th.ac.kmutt.research.form.CommonForm" %>
<input type="hidden" name="command" id="${ns}common" value="${commonForm.command}"/>
<input type="hidden" name="mode" id="${ns}mode" value="${commonForm.mode}"/>
<input type="hidden" name="tab" id="${ns}tab" value="${commonForm.tab}"/>
<input type="hidden" name="pageNo" id="${ns}pageNo" value="${commonForm.pageNo}"/>
<input type="hidden" id="${ns}pageCount" value="${commonForm.pageCount}"/>