<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="portlet" uri="http://java.sun.com/portlet" %>
<%@tag pageEncoding="UTF-8"%>
<c:set var="ns"><portlet:namespace/></c:set>
<%@ attribute name="withdraws" required="true"
              type="java.util.List<th.ac.kmutt.research.model.ResearchProjectWithdrawM>" %>
<%@ attribute name="havePermissionOnDocs" required="true" type="java.lang.Boolean" %>

<%@ attribute name="amountWithdrawShow" fragment="true" %>
<%@ variable name-given="amountWithdraw_name_given" %>

<%@ attribute name="balanceShow" fragment="true" %>
<%@ variable name-given="balance_name_given" %>

<table border="0" width="100%" style="font-size: 13px">
    <tbody>
    <tr>
        <td align="center" width="100%" colspan="2">
            <div id="${ns}element_withdraw"
                 style="display: none" class="kmutt_block">
                <table border="0" width="100%"
                       class="kmutt_table_detail_block">
                    <tr>
                        <td align="right" width="30%">
                        		<span>
                        			<label>Withdraw Date:<span> </span>
                                    </label>
                        		</span>
                        </td>
                        <td width="70%" colspan="3">
                            <div>
                                <input type="hidden" id="${ns}withdraw_mode"/>
                                <input type="hidden" id="${ns}withdraw_itemList"/>
                                <input type="hidden" id="${ns}withdraw_withdrawItemId"/>
                                <input type="text" id="${ns}withdraw_date"
                                       readonly="readonly" style="width:75px"
                                       class="form-control">
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td align="right" width="30%">
                        		<span>
                        			<label>Amount Withdraw:<span > </span>
                                    </label>
                        		</span>
                        </td>
                        <td width="70%" colspan="3">
                            <div>
                                <input type="text" id="${ns}withdraw_amount"
                                       style="width:75px" class="form-control">
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td align="right" width="30%">
                        		<span>
                        			<label>Balance:<span > </span>
                                    </label>
                        		</span>
                        </td>
                        <td width="70%" colspan="3">
                            <div>
                                <input type="text" id="${ns}withdraw_balance"
                                       style="width:75px" class="form-control">
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td align="right" width="30%">
                        		<span>
                        			<label>Remark:<span > </span>
                                    </label>
                        		</span>
                        </td>
                        <td width="70%" colspan="3">
                            <div>
                                                            <textarea rows="2" cols="2" class="500px"
                                                                      id="${ns}withdraw_remark"></textarea>

                            </div>
                        </td>
                    </tr>
                    <c:if test="${havePermissionOnDocs}">
                        <tr>
                            <td align="center" colspan="4" width="20%">
                                <button type="button" onclick="${ns}doSubmitWithdrawAjax()"
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
                  <button onclick="${ns}displayWithdraw('add','0')" type="button" class="btn btn-primary">Add</button>
              </c:if>
               </span>
        </td>
    </tr>
    </tbody>
</table>
 <span id="${ns}withdraw_item_list">
        <table class="table table-hover table-striped table-bordered table-condensed" border="1"
               style="font-size: 12px">
            <thead>
            <tr> <!-- 50 42+8 -->
                <th width="5%" style="text-align: center">
                    <div class="th_class">#</div>
                </th>
                <th width="10%" style="text-align: center">
                    <div class="th_class">Withdraw Date</div>
                </th>
                <th width="12%" style="text-align: center">
                    <div class="th_class">Amount Withdraw</div>
                </th>
                <th width="10%" style="text-align: center">
                    <div class="th_class">Balance</div>
                </th>
                <th width="34%" style="text-align: center">
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

            <c:if test="${not empty withdraws}">
                <c:forEach items="${withdraws}" var="withdraw" varStatus="loop">
                    <tr style="cursor: pointer;">
                        <td onclick='${ns}doEditWithdrawAjax("${withdraw.researchProjectId}","${withdraw.itemList}")'
                            style="text-align: left">${loop.index+1}</td>
                        <td onclick='${ns}doEditWithdrawAjax("${withdraw.researchProjectId}","${withdraw.itemList}")'
                            style="text-align: center">
                                ${withdraw.withdrawDateShow}
                        </td>
                        <td onclick='${ns}doEditWithdrawAjax("${withdraw.researchProjectId}","${withdraw.itemList}")'
                            style="text-align: right;">
                            <c:set var="amountWithdraw_name_given" value="${withdraw.amountWithdraw}"/>
                            <jsp:invoke fragment="amountWithdrawShow"/>
                        </td>
                        <td onclick='${ns}doEditWithdrawAjax("${withdraw.researchProjectId}","${withdraw.itemList}")'
                            style="text-align: right">
                            <c:set var="balance_name_given" value="${withdraw.balance}"/>
                            <jsp:invoke fragment="balanceShow"/>
                        </td>
                        <td onclick='${ns}doEditWithdrawAjax("${withdraw.researchProjectId}","${withdraw.itemList}")'
                            style="text-align: left">${withdraw.remark}</td>
                        <c:if test="${havePermissionOnDocs}">
                            <td style="text-align: center">
                                <button onclick='${ns}doEditWithdrawAjax("${withdraw.researchProjectId}","${withdraw.itemList}")'
                                        class="btn btn-small" type="button">แก้ใข
                                </button>
                                <button onclick='${ns}doDeleteWithdrawAjax("${withdraw.researchProjectId}","${withdraw.itemList}")'
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