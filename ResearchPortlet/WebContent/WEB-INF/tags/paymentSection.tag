<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="portlet" uri="http://java.sun.com/portlet" %>
<%@tag pageEncoding="UTF-8"%>
<c:set var="ns"><portlet:namespace/></c:set>
<%@ attribute name="payments" required="true"
              type="java.util.List<th.ac.kmutt.research.model.ResearchProjectPaymentM>"
              %>
<%@ attribute name="amountReceivedShow" fragment="true" %>
<%@ variable name-given="amountReceived_name_given" %>
<%@ attribute name="havePermissionOnDocs" required="true" type="java.lang.Boolean" %>
<table border="0" width="100%" style="font-size: 13px">
    <tbody>
    <tr>
        <td align="center" width="100%" colspan="2">
            <div id="${ns}element_payment"
                 style="display: none" class="kmutt_block">
                <table border="0" width="100%"
                       class="kmutt_table_detail_block">
                    <tr>
                        <td align="right" width="30%">
                        		<span>
                        			<label>Received Date:<span class="kmutt_require_field"> </span>
                                    </label>
                        		</span>
                        </td>
                        <td width="70%" colspan="3">
                            <div>
                                <input type="text" id="${ns}payment_receivedDate"
                                       readonly="readonly" style="width:75px"
                                       class="form-control">
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td align="right" width="30%">
                        		<span>
                        			<label>Received NO:<span> </span>
                                    </label>
                        		</span>
                        </td>
                        <td width="70%" colspan="3">
                            <div>

                                <input type="hidden" id="${ns}payment_mode"/>
                                <input type="hidden" id="${ns}payment_itemList"/>
                                <input type="hidden" id="${ns}payment_progressItemId"/>
                                <input type="text" id="${ns}payment_receiptNo"
                                       style="width:75px" class="form-control">
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td align="right" width="30%">
                        		<span>
                        			<label>Amount Received:<span> </span>
                                    </label>
                        		</span>
                        </td>
                        <td width="70%" colspan="3">
                            <div>
                                <input type="text" id="${ns}payment_amountReceived"
                                       style="width:75px" class="form-control">
                            </div>
                        </td>
                    </tr>
                    <c:if test="${havePermissionOnDocs}">
                        <tr>
                            <td align="center" colspan="4" width="20%">
                                <button type="button" onclick="${ns}doSubmitPaymentAjax()"
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
                    <button onclick="${ns}displayPayment('add','0')" type="button" class="btn btn-primary">Add</button>
                </c:if>
               </span>
        </td>
    </tr>
    </tbody>
</table>
<span id="${ns}payment_item_list">
        <table class="table table-hover table-striped table-bordered table-condensed" border="1"
               style="font-size: 12px">
            <thead>
            <tr> <!-- 50 42+8 -->
                <th width="5%" style="text-align: center">
                    <div class="th_class">#</div>
                </th>
                <th width="8%" style="text-align: center">
                    <div class="th_class">Received Date</div>
                </th>
                <th width="21%" style="text-align: center">
                    <div class="th_class">Received NO</div>
                </th>
                <th width="20%" style="text-align: center">
                    <div class="th_class">Amount Received</div>
                </th>
                <c:if test="${havePermissionOnDocs}">
                    <th width="8%">
                        <div class="th_class"></div>
                    </th>
                </c:if>
            </tr>
            </thead>
            <tbody>
            <c:if test="${not empty payments}">
                <c:forEach items="${payments}" var="payment" varStatus="loop">
                    <tr style="cursor: pointer;">
                        <td onclick='${ns}doEditPaymentAjax("${payment.researchProjectId}","${payment.itemList}")'
                            style="text-align: left">${loop.index+1}</td>
                        <td onclick='${ns}doEditPaymentAjax("${payment.researchProjectId}","${payment.itemList}")'
                            style="text-align: center">
                                ${payment.receivedDateShow}
                        </td>
                        <td onclick='${ns}doEditPaymentAjax("${payment.researchProjectId}","${payment.itemList}")'
                            style="text-align: left">${payment.receiptNo}</td>
                        <td onclick='${ns}doEditPaymentAjax("${payment.researchProjectId}","${payment.itemList}")'
                            style="text-align: right;">
                            <c:set var="amountReceived_name_given" value="${payment.amountReceived}"/>
                            <jsp:invoke fragment="amountReceivedShow"  />
                        </td>
                        <c:if test="${havePermissionOnDocs}">
                            <td style="text-align: center">
                                <button onclick='${ns}doEditPaymentAjax("${payment.researchProjectId}","${payment.itemList}")'
                                        class="btn btn-small" type="button">แก้ใข
                                </button>
                                <button onclick='${ns}doDeletePaymentAjax("${payment.researchProjectId}","${payment.itemList}")'
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