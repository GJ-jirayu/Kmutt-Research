<%@ taglib prefix="portlet" uri="http://java.sun.com/portlet" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@tag pageEncoding="UTF-8"%>
<c:set var="ns"><portlet:namespace/></c:set>
<%@ attribute name="commonForm" required="true" type="th.ac.kmutt.research.form.CommonForm" %>
<ul id="${ns}myTab" class="nav nav-tabs" role="tablist">
    <li role="presentation" class='${commonForm.tab=="all"?"active":""}' style="cursor: pointer;"><a
            onclick="${ns}doSubmitTab('all')" id="home-tab" role="tab" data-toggle="tab"
            aria-controls="home" aria-expanded="true">All</a></li>
    <li role="presentation" class='${commonForm.tab=="myData"?"active":""}' style="cursor: pointer;"><a
            onclick="${ns}doSubmitTab('myData')" role="tab" id="profile-tab" data-toggle="tab"
            aria-controls="profile" aria-expanded="false">My Data</a></li>
</ul>