<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="portlet" uri="http://java.sun.com/portlet" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@tag pageEncoding="UTF-8"%>
<c:set var="ns"><portlet:namespace/></c:set>
<%@ attribute name="documents" required="true"
              type="java.util.List<th.ac.kmutt.research.model.ResearchProjectDocumentM>" %>
<%@ attribute name="havePermissionOnDocs" required="true" type="java.lang.Boolean" %>
<table border="0" width="100%" style="font-size: 13px">
    <tbody>
    <tr>
        <td align="center" width="100%" colspan="2">
            <div id="${ns}element_document"
                 style="display: none" class="kmutt_block">
                <table border="0" width="100%"
                       class="kmutt_table_detail_block">
                    <tr>
                        <td align="right" width="30%">
                        		<span>
                        			<label>Document Name:<span > </span>
                                    </label>
                        		</span>
                        </td>
                        <td width="70%" colspan="3">
                            <div>

                                <input type="hidden" id="${ns}document_mode"/>
                                <input type="hidden" id="${ns}document_itemList"/>
                                <input type="text" id="${ns}document_description"
                                       style="width:75px" class="form-control">
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td align="right" width="30%">
                        		<span>
                        			<label><span > </span>
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
                                <button type="button" onclick="${ns}doSubmitDocumentAjax()"
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
                   <button onclick="${ns}displayDocument('add','0')" type="button" class="btn btn-primary">Add</button>
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

            <c:if test="${not empty documents}">
                <c:forEach items="${documents}" var="document" varStatus="loop">
                    <tr style="cursor: pointer;">
                        <td onclick='${ns}doEditDocumentAjax("${document.researchProjectId}","${document.itemList}")'
                            style="text-align: left">${loop.index+1}</td>
                        <c:if test="${not empty document.fileName}">
                            <td onclick='${ns}doEditDocumentAjax("${document.researchProjectId}","${document.itemList}")'
                                style="text-align: left">${document.description} [ <a
                                    style="text-decoration: underline;"
                                    onclick='${ns}downloadFile("${document.itemList}","${document.researchProjectId}")'>${document.fileName}</a>
                                ]
                            </td>
                        </c:if>
                        <c:if test="${empty document.fileName}">
                            <td onclick='${ns}doEditDocumentAjax("${document.researchProjectId}","${document.itemList}")'
                                style="text-align: left">${document.description} </td>
                        </c:if>
                        <c:if test="${havePermissionOnDocs}">
                            <td style="text-align: center">
                                <button onclick='${ns}doEditDocumentAjax("${document.researchProjectId}","${document.itemList}")'
                                        class="btn btn-small" type="button">แก้ใข
                                </button>
                                <button onclick='${ns}doDeleteDocumentAjax("${document.researchProjectId}","${document.itemList}")'
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