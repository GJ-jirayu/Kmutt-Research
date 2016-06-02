<%@ include file="/WEB-INF/jsp/include.jsp" %>
<%@ page contentType="text/html; charset=utf-8" %>
<c:set var="ns"><portlet:namespace/></c:set>
<c:url var="url" value="/" />
<script>
    /**
     * Created by imake on 29/08/2015.
     */
    $(document).ready(function () {
        <%-- alert('${user.userId}') --%>
        $("#endDate").datepicker({
            showOn: "button",
            buttonImage: '<c:url value="/resources/images/calendar.gif"/>',
            buttonImageOnly: true,
            dateFormat: "dd/mm/yy"
        });
        $("#startDate").datepicker({
            showOn: "button",
            buttonImage: '<c:url value="/resources/images/calendar.gif"/>',
            buttonImageOnly: true,
            dateFormat: "dd/mm/yy"
        });
        $("#reportDuedate").datepicker({
            showOn: "button",
            buttonImage: '<c:url value="/resources/images/calendar.gif"/>',
            buttonImageOnly: true,
            dateFormat: "dd/mm/yy"
        });
        $("#reportSubmitDate").datepicker({
            showOn: "button",
            buttonImage: '<c:url value="/resources/images/calendar.gif"/>',
            buttonImageOnly: true,
            dateFormat: "dd/mm/yy"
        });
        $("#${ns}withdraw_date").datepicker({
            showOn: "button",
            buttonImage: '<c:url value="/resources/images/calendar.gif"/>',
            buttonImageOnly: true,
            dateFormat: "dd/mm/yy"
        });
        $("#${ns}payment_receivedDate").datepicker({
            showOn: "button",
            buttonImage: '<c:url value="/resources/images/calendar.gif"/>',
            buttonImageOnly: true,
            dateFormat: "dd/mm/yy"
        });
        $("#${ns}progress_dueDate").datepicker({
            showOn: "button",
            buttonImage: '<c:url value="/resources/images/calendar.gif"/>',
            buttonImageOnly: true,
            dateFormat: "dd/mm/yy"
        });
        $("#${ns}progress_submitDate").datepicker({
            showOn: "button",
            buttonImage: '<c:url value="/resources/images/calendar.gif"/>',
            buttonImageOnly: true,
            dateFormat: "dd/mm/yy"
        });


        $('#researchGroupId_assign_autocomplete').autocomplete({
            source: function (request, response) {
                var researchGroupM = {
                    keySearch: request.term
                }
                ResearchAjax.getResearchGroupList(researchGroupM, {
                    callback: function (data) {
                        data = data.resultListObj;
                        if (data != null && data.length > 0) {
                            response($.map(data, function (item) {
                                return {
                                    label: item.groupTh,
                                    value: item.groupTh,
                                    name: item.groupTh,
                                    id: item.researchGroupId,
                                    code: item.groupCode
                                };
                            }));
                        } else {
                            var xx = [];
                            response($.map(xx));
                        }
                    }
                });
            },
            minLength: 1,
            select: function (event, ui) {
                this.value = ui.item.name;
                $('input[id="researchProjectM.researchGroupId"]').val(ui.item.id);
                $("#researchGroupId_assignShow").html(" " + ui.item.name + " ");
                return false;
            },
            open: function () {
                $(this).removeClass("ui-corner-all").addClass("ui-corner-top");
            },
            close: function () {
                $(this).removeClass("ui-corner-top").addClass("ui-corner-all");
            }
        });

        $('#fundingResourceId_assign_autocomplete').autocomplete({
            source: function (request, response) {
                var modelM = {
                    keySearch: request.term
                }
                ResearchAjax.getFundingResourceList(modelM, {
                    callback: function (data) {
                        data = data.resultListObj;
                        if (data != null && data.length > 0) {
                            response($.map(data, function (item) {
                                return {
                                    label: item.frNameThai,
                                    value: item.frNameThai,
                                    name: item.frNameThai,
                                    id: item.fundingResourceId
                                };
                            }));
                        } else {
                            var xx = [];
                            response($.map(xx));
                        }
                    }
                });
            },
            minLength: 1,
            select: function (event, ui) {
                this.value = ui.item.name;
                $('input[id="researchProjectM.fundingResourceId"]').val(ui.item.id);
                $("#fundingResourceId_assignShow").html(" " + ui.item.name + " ");
                return false;
            },
            open: function () {
                $(this).removeClass("ui-corner-all").addClass("ui-corner-top");
            },
            close: function () {
                $(this).removeClass("ui-corner-top").addClass("ui-corner-all");
            }
        });

        $('#organizationId_assign_autocomplete').autocomplete({
            source: function (request, response) {
                var modelM = {
                    keySearch: request.term
                }
                ResearchAjax.getOrganizationList(modelM, {
                    callback: function (data) {
                        data = data.resultListObj;
                        if (data != null && data.length > 0) {
                            response($.map(data, function (item) {
                                return {
                                    label: item.orgName,
                                    value: item.orgName,
                                    name: item.orgName,
                                    id: item.organizationId
                                };
                            }));
                        } else {
                            var xx = [];
                            response($.map(xx));
                        }
                    }
                });
            },
            minLength: 1,
            select: function (event, ui) {
                this.value = ui.item.name;
                $('input[id="researchProjectM.organizationId"]').val(ui.item.id);
                $("#organizationId_assignShow").html(" " + ui.item.name + " ");
                return false;
            },
            open: function () {
                $(this).removeClass("ui-corner-all").addClass("ui-corner-top");
            },
            close: function () {
                $(this).removeClass("ui-corner-top").addClass("ui-corner-all");
            }
        });

        $('#researcherId_assign_autocomplete').autocomplete({
            source: function (request, response) {
                var modelM = {
                    keySearch: request.term
                }
                ResearchAjax.getResearcherList(modelM, {
                    callback: function (data) {
                        data = data.resultListObj;
                        if (data != null && data.length > 0) {
                            response($.map(data, function (item) {
                                return {
                                    label: item.nameThai + " " + item.surnameThai,
                                    value: item.nameThai + " " + item.surnameThai,
                                    name: item.nameThai + " " + item.surnameThai,
                                    id: item.researcherId,
                                    position: (item.position != null && item.position.positionName) ? item.position.positionName : "",
                                    positionId: (item.position != null && item.position.positionId) ? item.position.positionId : "",
                                    organization: (item.organization != null && item.organization.orgName) ? item.organization.orgName : "",
                                    organizationId: (item.organization != null && item.organization.organizationId) ? item.organization.organizationId : ""
                                };
                            }));
                        } else {
                            var xx = [];
                            response($.map(xx));
                        }
                    }
                });
            },
            minLength: 1,
            select: function (event, ui) {
                this.value = ui.item.name;
                $('#${ns}researcher_researcherId').val(ui.item.id);
                $('#${ns}researcher_researcherName').val(ui.item.name);
                $('#${ns}researcher_positionId').val(ui.item.positionId);
                $('#${ns}researcher_organizationId').val(ui.item.organizationId);
                $("#researcherId_assignShow").html(" " + ui.item.name + " ");
                $('#${ns}researcher_position').val(ui.item.position);
                $('#${ns}researcher_organization').val(ui.item.organization);
                return false;
            },
            open: function () {
                $(this).removeClass("ui-corner-all").addClass("ui-corner-top");
            },
            close: function () {
                $(this).removeClass("ui-corner-top").addClass("ui-corner-all");
            }
        });

        $('#docs_assign_autocomplete').autocomplete({
            source: function (request, response) {
                var modelM = {
                    keySearch: request.term
                }
                ResearchAjax.getUserPortalList(modelM, {
                    callback: function (data) {
                        data = data.resultListObj;
                        if (data != null && data.length > 0) {
                            response($.map(data, function (item) {
                                return {
                                    label: item.emailAddress + " [ " + item.firstName + " " + item.lastName + "]",
                                    value: item.userId,
                                    name: item.emailAddress + " [ " + item.firstName + " " + item.lastName + "]",
                                    id: item.userId
                                    //code: item.frtCode
                                };
                            }));
                        } else {
                            var xx = [];
                            response($.map(xx));
                        }
                    }
                });
            },
            minLength: 1,
            select: function (event, ui) {
                this.value = ui.item.name;
                $('#docsAssign').val(ui.item.id);
                var buttonShow = '<button style=\"margin-top: -8px"\" onclick=\'${ns}doAddDocAssignMappingAjax("' + $("#researchProjectId").val() + '","RESEARCH","' + ui.item.id + '")\' class="btn btn-primary btn-small" type="button">Add</button>'
                $("#docs_assignShow").html(buttonShow);
                $("#docs_assignShow").show("slow");
                return false;
            },
            open: function () {
                $(this).removeClass("ui-corner-all").addClass("ui-corner-top");
            },
            close: function () {
                $(this).removeClass("ui-corner-top").addClass("ui-corner-all");
            }
        });

        var ele_numbers = ["${ns}researcher_workLoadRatio", "${ns}progress_progressPercentage"]
        for (var i = 0; i < ele_numbers.length; i++) {
            $('#' + ele_numbers[i]).keyup(function () {
                var dInput = this.value;
                if ($.trim(dInput).length > 0 && !${ns}validateDigitOnly(dInput)) {
                    $(this).val("");
                    $(this).focus();
                    return false;
                }
                if (parseInt(dInput) > 100 || parseInt(dInput) < 1) {
                    $(this).val("");
                    $(this).focus();
                    return false;
                }
            });
        }

        var budget_numbers = ["researchProjectM.purposeBudget", "researchProjectM.submitBudget"];
        for (var i = 0; i < budget_numbers.length; i++) {
            $('input[id=\"' + budget_numbers[i] + '\"]').keyup(function () {
                var dInput = this.value;
                if ($.trim(dInput).length > 0 && !${ns}validateDigit(dInput)) {
                    $(this).val("");
                    $(this).focus();
                    return false;
                }
            });
        }

        var ids = ["${ns}withdraw_balance", "${ns}payment_amountReceived", "${ns}withdraw_amount"];
        for (var i = 0; i < ids.length; i++) {
            $('#' + ids[i]).keyup(function () {
                var dInput = this.value;
                if ($.trim(dInput).length > 0 && !${ns}validateDigit(dInput)) {
                    $(this).val("");
                    $(this).focus();
                    return false;
                }
            });
        }
        $('#researchProjectM\\.budgetYear').keyup(function () {
            var dInput = this.value;
            if ($.trim(dInput).length > 0 && !${ns}validateDigitOnly(dInput)) {
                $(this).val("");
                $(this).focus();
                return false;
            }
        });

        if($("#${ns}workLoad_all").val()=='100' && $("#${ns}_isLeader_init").val()=='true'){
            $("#${ns}publish_element").show("slow");
        }else{
            $("#${ns}publish_element").hide("slow");
        }
    });

    <%-- START Researcher  --%>
    function ${ns}doEditResearcherAjax(researchProjectId, itemList) {
        var researchProjectResearcher = {
            itemList: itemList,
            researchProjectId: researchProjectId,
            researcherItemId: 1
        }
        ResearchAjax.findResearchProjectResearcherById(researchProjectResearcher, {
            callback: function (data) {
                if (data != null) {
                    $("#researcherId_assign_autocomplete").prop("readonly", "readonly")
                    $("#researcherId_assign_autocomplete").css("background-color", "");

                    $("#${ns}researcher_researcherId").val(data.researcherId);
                    $("#${ns}researcher_researcherName").val(data.researcherName);
                    var positionId = '';
                    var positionName = '';
                    if (data.position != null) {
                        positionId = data.position.positionId;
                        positionName = data.position.positionName;
                    }
                    var organizationId = '';
                    var orgName = '';
                    if (data.organization != null) {
                        organizationId = data.organization.organizationId;
                        orgName = data.organization.orgName;
                    }
                    $("#${ns}researcher_positionId").val(positionId);
                    $("#${ns}researcher_organizationId").val(organizationId);
                    $("#researcherId_assign_autocomplete").val(data.researcherName);
                    $("#researcherId_assignShow").html(data.researcherName);
                    $("#${ns}researcher_position").val(positionName);
                    $("#${ns}researcher_organization").val(orgName);
                    var isprojectleader = false;

                    if($("#${ns}_isLeader_init").val()=='true'){
                        $("#${ns}researcher_isprojectLeader").prop("disabled","disabled")
                    }else{
                        $("#${ns}researcher_isprojectLeader").prop("disabled","")
                    }

                    if (data.isprojectLeader == 'T' ) {
                        isprojectleader = true;
                        $("#${ns}researcher_isprojectLeader").prop("disabled","")
                    }
                    $("#${ns}researcher_isprojectLeader").prop("checked", isprojectleader);
                    $("#${ns}researcher_workLoadRatio").val(data.workLoadRatio);
                    $("#${ns}researcher_mode").val("edit");
                    $("#${ns}researcher_itemList").val(itemList);
                    $("#${ns}researcher_researcherItemId").val("1");
                    $("#${ns}element_researcher").show("slow");
                    $("#${ns}researcherListAll").hide("slow");
                }
            }
        });
    }
    function ${ns}doDeleteResearcherAjax(researchProjectId, itemList) {
        var agree = confirm(" ต้องการลบข้อมูลหรือไม่? ");
        if (agree) {
            ${ns}showDownlod("${ns}researcher_item_list");
            var researchProjectResearcher = {
                researchProjectId: researchProjectId,
                itemList: itemList
            }
            ResearchAjax.deleteResearchProjectResearcher(researchProjectResearcher, {
                callback: function (data) {
                    ${ns}render_researcher_item(data);
                    $("#${ns}element_researcher").hide("slow");
                }
            });
            return true;
        }
        else {
            return false;
        }
    }
    function ${ns}doSubmitResearcherAjax() {
        if($.trim($('#researcherId_assign_autocomplete').val())==0){
            $('#researcherId_assign_autocomplete').css("border","1px solid red")
            return false;
        }
        var workLoadRatio=$.trim($('#${ns}researcher_workLoadRatio').val());
        if(workLoadRatio.length==0){
            $('#${ns}researcher_workLoadRatio').css("border"," 1px solid red")
            return false;
        }

        var mode = $("#${ns}researcher_mode").val();
        var positionId = $('#${ns}researcher_positionId').val();
        var position;
        if (positionId.length > 0) {
            position = {
                positionId: positionId
            };
        } else {
            position = {};
        }
        var organizationId = $('#${ns}researcher_organizationId').val();
        var organization;
        if (organizationId.length > 0) {
            organization = {
                organizationId: organizationId
            };
        } else {
            organization = {};
        }
        var isprojectLeader = "F";
        if ($('#${ns}researcher_isprojectLeader').prop("checked"))
            isprojectLeader = "T"

        var researchProjectResearcher = {
            researchProjectId: $("#researchProjectId").val(),
            researcherItemId: 1,
            isprojectLeader: isprojectLeader,
            researcherDept: $('#${ns}researcher_organization').val(),
            workLoadRatio: workLoadRatio,
            organization: organization,
            position: position,
            researcherId: $('#${ns}researcher_researcherId').val(),
            researcherName: $('#${ns}researcher_researcherName').val(),
            createdBy: "${user.userId}",
            updatedBy: "${user.userId}",
            itemList: $("#${ns}researcher_itemList").val()
        }
        ResearchAjax.countResearchProjectResearcher(researchProjectResearcher, {
            callback: function (data) {
                var max_percent = data + parseInt(researchProjectResearcher.workLoadRatio);

                if (max_percent > 100) {
                    $('#${ns}researcher_workLoadRatio').val("")
                    $('#${ns}researcher_workLoadRatio').css("border","1px solid red")
                    $('#${ns}researcher_workLoadRatio').focus()
                    return false;
                } else {
                    ${ns}showDownlod("${ns}researcher_item_list");
                    ResearchAjax.updateResearchProjectResearcher(researchProjectResearcher, mode, {
                        callback: function (data) {
                            ${ns}render_researcher_item(data);
                            $("#${ns}element_researcher").hide("slow");
                        }
                    });
                }
            }
        });
    }
    <%-- END Researcher  --%>

    <%-- START Progress  --%>
    function ${ns}doEditProgressAjax(researchProjectId, itemList) {
        var researchProjectProgress = {
            itemList: itemList,
            researchProjectId: researchProjectId,
            progressItemId: 1
        }
        ResearchAjax.findResearchProjectProgressById(researchProjectProgress, {
            callback: function (data) {
                if (data != null) {
                    $("#${ns}progress_dueDate").val(data.dueDateShow);
                    $("#${ns}progress_remark").val(data.remark);
                    $("#${ns}progress_progressPercentage").val(data.progressPercentage);
                    $("#${ns}progress_submitDate").val(data.submitDateShow);
                    $("#${ns}progress_mode").val("edit");
                    $("#${ns}progress_itemList").val(itemList);
                    $("#${ns}progress_progressItemId").val("1");
                    $("#${ns}element_progress").show("slow");
                }
            }
        });
    }
    function ${ns}doDeleteProgressAjax(researchProjectId, itemList) {
        var agree = confirm(" ต้องการลบข้อมูลหรือไม่? ");
        if (agree) {
            ${ns}showDownlod("${ns}progress_item_list");
            var researchProjectProgress = {
                researchProjectId: researchProjectId,
                itemList: itemList
            }
            ResearchAjax.deleteResearchProjectProgress(researchProjectProgress, {
                callback: function (data) {
                    ${ns}render_progress_item(data);
                    $("#${ns}element_progress").hide("slow");
                }
            });
            return true;
        }
        else {
            return false;
        }
    }

    function ${ns}doSubmitProgressAjax() {
        var mode = $("#${ns}progress_mode").val();
        ;
        var researchProjectProgress = {
            researchProjectId: $("#researchProjectId").val(),
            progressItemId: 1,
            progressPercentage: $("#${ns}progress_progressPercentage").val(),
            remark: $("#${ns}progress_remark").val(),
            dueDateShow: $("#${ns}progress_dueDate").val(),//"31/03/2015",
            submitDateShow: $("#${ns}progress_submitDate").val(),
            createdBy: "${user.userId}",
            updatedBy: "${user.userId}",
            itemList: $("#${ns}progress_itemList").val()
        }
        ${ns}showDownlod("${ns}progress_item_list");
        ResearchAjax.updateResearchProjectProgress(researchProjectProgress, mode, {
            callback: function (data) {
                ${ns}render_progress_item(data);
                $("#${ns}element_progress").hide("slow");
            }
        });
    }
    <%-- END Progress  --%>

    <%-- START Payment  --%>
    function ${ns}doEditPaymentAjax(researchProjectId, itemList) {

        var researchProjectPayment = {
            itemList: itemList,
            researchProjectId: researchProjectId,
            paymentItemId: 1
        }

        ResearchAjax.findResearchProjectPaymentById(researchProjectPayment, {
            callback: function (data) {
                if (data != null) {
                    $("#${ns}payment_receivedDate").val(data.receivedDateShow);
                    $("#${ns}payment_receiptNo").val(data.receiptNo);
                    $("#${ns}payment_amountReceived").val(data.amountReceived);
                    $("#${ns}payment_mode").val("edit");
                    $("#${ns}payment_itemList").val(itemList);
                    $("#${ns}payment_paymentItemId").val("1");
                    $("#${ns}element_payment").show("slow");
                }
            }
        });
    }

    function ${ns}doDeletePaymentAjax(researchProjectId, itemList) {
        var agree = confirm(" ต้องการลบข้อมูลหรือไม่? ");
        if (agree) {
            ${ns}showDownlod("${ns}payment_item_list");
            var researchProjectPaymentM = {
                researchProjectId: researchProjectId,
                itemList: itemList
            }
            ResearchAjax.deleteResearchProjectPayment(researchProjectPaymentM, {
                callback: function (data) {
                    ${ns}render_payment_item(data);
                    $("#${ns}element_payment").hide("slow");
                }
            });
            return true;
        }
        else {
            return false;
        }
    }

    function ${ns}doSubmitPaymentAjax() {
        var mode = $("#${ns}payment_mode").val();
        var researchProjectPayment = {
            researchProjectId: $("#researchProjectId").val(),
            paymentItemId: 1,
            amountReceived: $("#${ns}payment_amountReceived").val(),
            receiptNo: $("#${ns}payment_receiptNo").val(),
            receivedDateShow: $("#${ns}payment_receivedDate").val(),
            createdBy: "${user.userId}",
            updatedBy: "${user.userId}",
            itemList: $("#${ns}payment_itemList").val()
        }
        ${ns}showDownlod("${ns}payment_item_list");
        ResearchAjax.updateResearchProjectPayment(researchProjectPayment, mode, {
            callback: function (data) {
                ${ns}render_payment_item(data);
                $("#${ns}element_payment").hide("slow");
            }
        });
    }
    <%-- END Payment  --%>

    <%-- START Withdraw  --%>
    function ${ns}doEditWithdrawAjax(researchProjectId, itemList) {
        var researchProjectWithdraw = {
            itemList: itemList,
            researchProjectId: researchProjectId,
            withdrawItemId: 1
        }
        ResearchAjax.findResearchProjectWithdrawById(researchProjectWithdraw, {
            callback: function (data) {
                if (data != null) {
                    $("#${ns}withdraw_date").val(data.withdrawDateShow);
                    $("#${ns}withdraw_amount").val(data.amountWithdraw);
                    $("#${ns}withdraw_balance").val(data.balance);
                    $("#${ns}withdraw_remark").val(data.remark);
                    $("#${ns}withdraw_mode").val("edit")
                    $("#${ns}withdraw_itemList").val(itemList);
                    $("#${ns}withdraw_withdrawItemId").val("1");
                    $("#${ns}element_withdraw").show("slow");
                }
            }
        });
    }

    function ${ns}doDeleteWithdrawAjax(researchProjectId, itemList) {
        var agree = confirm(" ต้องการลบข้อมูลหรือไม่? ");
        if (agree) {
            ${ns}showDownlod("${ns}withdraw_item_list");
            var researchProjectWithdraw = {
                researchProjectId: researchProjectId,
                itemList: itemList
            }
            ResearchAjax.deleteResearchProjectWithdraw(researchProjectWithdraw, {
                callback: function (data) {
                    ${ns}render_withdraw_item(data);
                    $("#${ns}element_withdraw").hide("slow");
                }
            });
            return true;
        }
        else {
            return false;
        }
    }
    function ${ns}doSubmitWithdrawAjax() {
        var mode = $("#${ns}withdraw_mode").val();
        var withdraw_date = $("#${ns}withdraw_date").val();
        var withdraw_amount = $("#${ns}withdraw_amount").val();
        var withdraw_balance = $("#${ns}withdraw_balance").val();
        var withdraw_remark = $("#${ns}withdraw_remark").val();
        var researchProjectWithdraw = {
            researchProjectId: $("#researchProjectId").val(),
            withdrawItemId: 1,
            amountWithdraw: withdraw_amount,
            balance: withdraw_balance,
            remark: withdraw_remark,
            withdrawDateShow: withdraw_date,//"31/03/2015",
            createdBy: "${user.userId}",
            updatedBy: "${user.userId}",
            itemList: $("#${ns}withdraw_itemList").val()
        }
        ${ns}showDownlod("${ns}withdraw_item_list");
        ResearchAjax.updateResearchProjectWithdraw(researchProjectWithdraw, mode, {
            callback: function (data) {
                ${ns}render_withdraw_item(data);
                $("#${ns}element_withdraw").hide("slow");
            }
        });
    }
    <%-- END Withdraw  --%>

    <%-- Start Document  --%>
    function ${ns}doEditDocumentAjax(researchProjectId, itemList) {
        var researchProjectDocument = {
            itemList: itemList,
            researchProjectId: researchProjectId,
            documentId: 1
        }

        ResearchAjax.findResearchProjectDocumentById(researchProjectDocument, {
            callback: function (data) {
                if (data != null) {
                    $("#${ns}document_description").val(data.description);
                    $("#${ns}document_mode").val("edit")
                    $("#${ns}document_itemList").val(itemList);
                    $("#${ns}element_document").show("slow");
                }
            }
        });
    }
    function ${ns}doDeleteDocumentAjax(researchProjectId, itemList) {
        var agree = confirm(" ต้องการลบข้อมูลหรือไม่? ");
        if (agree) {
            ${ns}showDownlod("${ns}document_item_list");
            var researchProjectDocument = {
                researchProjectId: researchProjectId,
                itemList: itemList
            };

            ResearchAjax.deleteResearchProjectDocument(researchProjectDocument, 'researchProject', {
                callback: function (data) {
                    ${ns}render_document_item(data);
                    $("#${ns}element_document").hide("slow");
                }
            });
            return true;
        }
        else {
            return false;
        }
    }
    function ${ns}upload() {
        var mode = "add";
        var researchProjectDocument = {
            researchProjectId: $("#researchProjectId").val(),
            documentId: 1,
            description: $("#${ns}document_description").val(),
            createdBy: "${user.userId}",
            updatedBy: "${user.userId}"
        }
        ${ns}showDownlod("${ns}document_item_list");
    }

    function ${ns}doSubmitDocumentAjax() {
        var mode = $("#${ns}document_mode").val();
        var researchProjectDocument = {
            researchProjectId: $("#researchProjectId").val(),
            documentId: 1,
            description: $("#${ns}document_description").val(),
            createdBy: "${user.userId}",
            updatedBy: "${user.userId}",
            itemList: $("#${ns}document_itemList").val()
        }
        ${ns}showDownlod("${ns}document_item_list");
        var file = dwr.util.getValue('${ns}uploadFile');
        ResearchAjax.uploadResearchProjectDocument(file, researchProjectDocument, "researchProject", mode, {
            callback: function (data) {
                ${ns}render_document_item(data);
                $("#${ns}element_document").hide("slow");
            }
        });
    }
    <%-- END Document  --%>
    function ${ns}render_researcher_item(obj) {
        var str = "<table class=\"table table-hover table-striped table-bordered table-condensed\" border=\"1\" style=\"font-size: 12px\"> " +
                " <thead>    " +
                "  <tr>" +
                "  		<th width=\"5%\" style=\"text-align: center\"><div class=\"th_class\">#</div></th>" +
                    //	"		<th width=\"15%\" style=\"text-align: center\"><div class=\"th_class\">ตำแหน่ง</div></th>"+
                "		<th width=\"25%\" style=\"text-align: center\"><div class=\"th_class\">ชื่อ - สกุล</div></th>  " +
                "		<th width=\"27%\" style=\"text-align: center\"><div class=\"th_class\">ภาควิชา/หน่วยงาน/บริษัท/องค์กร</div></th> " +
                "		<th width=\"14%\" style=\"text-align: center\"><div class=\"th_class\">เป็นหัวหน้าโครงการ</div></th> " +
                "		<th width=\"14%\" style=\"text-align: center\"><div class=\"th_class\">ภาระการทำงาน</div></th> " +
                "		<th width=\"10%\"><div class=\"th_class\"></div></th>  " +
                "	</tr> 		" +
                " </thead> 	" +
                " <tbody>  	";
        var isLeader=false;
        var sum_workload=0;
        if (obj != null) {
            for (var i = 0; i < obj.length; i++) {
                str = str + " 	<tr style=\"cursor: pointer;\">" +
                        " <td onclick=\"${ns}doEditResearcherAjax(\'" + obj[i].researchProjectId + "\',\'" + obj[i].itemList + "\')\" style=\"text-align: left\">" + (i + 1) + "</td>" +
                            //" <td style=\"text-align: left\">"+((obj[i].position!=null)?$.trim(obj[i].position.positionName):"")+"</td> "+
                        " <td onclick=\"${ns}doEditResearcherAjax(\'" + obj[i].researchProjectId + "\',\'" + obj[i].itemList + "\')\" style=\"text-align: left\">" + ((obj[i].position != null) ? $.trim(obj[i].position.positionName) : "") + " " + $.trim(obj[i].researcherName) + "</td> " +
                        " <td onclick=\"${ns}doEditResearcherAjax(\'" + obj[i].researchProjectId + "\',\'" + obj[i].itemList + "\')\" style=\"text-align: left\">" + ((obj[i].organization != null) ? $.trim(obj[i].organization.orgName) : "") + "</td> " +
                        " <td onclick=\"${ns}doEditResearcherAjax(\'" + obj[i].researchProjectId + "\',\'" + obj[i].itemList + "\')\" style=\"text-align: center\">";
                if ($.trim(obj[i].isprojectLeader) == "T") {
                    str = str + "เป็น";
                    isLeader=true;
                } else {
                    str = str + "ไม่เป็น";
                }
                str = str + " </td> " +
                        " <td onclick=\"${ns}doEditResearcherAjax(\'" + obj[i].researchProjectId + "\',\'" + obj[i].itemList + "\')\" style=\"text-align: center\">" + $.trim(obj[i].workLoadRatio) + "</td> " +
                        " <td style=\"text-align: center\">" +
                        "  <button onclick=\"${ns}doEditResearcherAjax(\'" + obj[i].researchProjectId + "\',\'" + obj[i].itemList + "\')\" class=\"btn btn-small\" type=\"button\">แก้ใข</button> " +
                        "  <button onclick=\"${ns}doDeleteResearcherAjax(\'" + obj[i].researchProjectId + "\',\'" + obj[i].itemList + "\')\" class=\"btn btn-danger btn-small\" type=\"button\">ลบ</button> " +
                        " </td>  " +
                        " </tr> ";
                if($.trim(obj[i].workLoadRatio)>0){
                    sum_workload=sum_workload+obj[i].workLoadRatio;
                }
            }
        }

        str = str + "</tbody>" +
                "</table>";
        $("#${ns}researcher_item_list").html(str);

        $("#${ns}_isLeader_init").val(isLeader);
        if(sum_workload==100 && $("#${ns}_isLeader_init").val()=='true'){
            $("#${ns}publish_element").show("slow");
        }else{
            $("#${ns}publish_element").hide("slow");
        }

        if(isLeader){
            $("#${ns}researcher_isprojectLeader").prop("disabled", "disabled");
        }else{
            $("#${ns}researcher_isprojectLeader").prop("disabled", "");
        }
        $('#${ns}researcher_workLoadRatio').css("border","1px solid #DDD")
        $('#researcherId_assign_autocomplete').css("border","1px solid #DDD")
    }

    function ${ns}render_progress_item(obj) {
        var str = "<table class=\"table table-hover table-striped table-bordered table-condensed\" border=\"1\" style=\"font-size: 12px\"> " +
                " <thead>    " +
                "  <tr>" +
                "  		<th width=\"5%\" style=\"text-align: center\"><div class=\"th_class\">#</div></th>" +
                "		<th width=\"8%\" style=\"text-align: center\"><div class=\"th_class\">Due Date</div></th>" +
                "		<th width=\"8%\" style=\"text-align: center\"><div class=\"th_class\">Submit Date</div></th>  " +
                "		<th width=\"5%\" style=\"text-align: center\"><div class=\"th_class\">Progress(%)</div></th> " +
                "		<th width=\"36%\" style=\"text-align: center\"><div class=\"th_class\">Remark</div></th> " +
                "		<th width=\"8%\"><div class=\"th_class\"></div></th>  " +
                "	</tr> 		" +
                " </thead> 	" +
                " <tbody>  	";
        if (obj != null) {
            for (var i = 0; i < obj.length; i++) {
                str = str + " 	<tr style=\"cursor: pointer;\">" +
                        " <td onclick=\"${ns}doEditProgressAjax(\'" + obj[i].researchProjectId + "\',\'" + obj[i].itemList + "\')\" style=\"text-align: left\">" + (i + 1) + "</td>" +
                        " <td onclick=\"${ns}doEditProgressAjax(\'" + obj[i].researchProjectId + "\',\'" + obj[i].itemList + "\')\" style=\"text-align: center\">" + $.trim(obj[i].dueDateShow) + "</td> " +
                        " <td onclick=\"${ns}doEditProgressAjax(\'" + obj[i].researchProjectId + "\',\'" + obj[i].itemList + "\')\" style=\"text-align: center\">" + $.trim(obj[i].submitDateShow) + "</td> " +
                        " <td onclick=\"${ns}doEditProgressAjax(\'" + obj[i].researchProjectId + "\',\'" + obj[i].itemList + "\')\" style=\"text-align: center\">" + $.trim(obj[i].progressPercentage) + "</td> " +
                        " <td onclick=\"${ns}doEditProgressAjax(\'" + obj[i].researchProjectId + "\',\'" + obj[i].itemList + "\')\" style=\"text-align: left\">" + $.trim(obj[i].remark) + "</td> " +
                        " <td style=\"text-align: center\">" +
                        "  <button onclick=\"${ns}doEditProgressAjax(\'" + obj[i].researchProjectId + "\',\'" + obj[i].itemList + "\')\" class=\"btn btn-small\" type=\"button\">แก้ใข</button> " +
                        "  <button onclick=\"${ns}doDeleteProgressAjax(\'" + obj[i].researchProjectId + "\',\'" + obj[i].itemList + "\')\" class=\"btn btn-danger btn-small\" type=\"button\">ลบ</button> " +
                        " </td>  " +
                        " </tr> ";
            }
        }
        str = str + "</tbody>" +
                "</table>";
        $("#${ns}progress_item_list").html(str);
    }

    function ${ns}render_payment_item(obj) {
        var str = "<table class=\"table table-hover table-striped table-bordered table-condensed\" border=\"1\" style=\"font-size: 12px\"> " +
                " <thead>    " +
                "  <tr>" +
                "  		<th width=\"5%\" style=\"text-align: center\"><div class=\"th_class\">#</div></th>" +
                "		<th width=\"8%\" style=\"text-align: center\"><div class=\"th_class\">Received Date</div></th>" +
                "		<th width=\"21%\" style=\"text-align: center\"><div class=\"th_class\">Received NO</div></th>  " +
                "		<th width=\"20%\" style=\"text-align: center\"><div class=\"th_class\">Amount Received</div></th> " +
                "		<th width=\"8%\"><div class=\"th_class\"></div></th>  " +
                "	</tr> 		" +
                " </thead> 	" +
                " <tbody>  	";
        if (obj != null) {
            for (var i = 0; i < obj.length; i++) {
                str = str + " 	<tr style=\"cursor: pointer;\">" +
                        " <td onclick=\"${ns}doEditPaymentAjax(\'" + obj[i].researchProjectId + "\',\'" + obj[i].itemList + "\')\" style=\"text-align: left\">" + (i + 1) + "</td>" +
                        " <td onclick=\"${ns}doEditPaymentAjax(\'" + obj[i].researchProjectId + "\',\'" + obj[i].itemList + "\')\" style=\"text-align: center\">" + $.trim(obj[i].receivedDateShow) + "</td> " +
                        " <td onclick=\"${ns}doEditPaymentAjax(\'" + obj[i].researchProjectId + "\',\'" + obj[i].itemList + "\')\" style=\"text-align: left\">" + $.trim(obj[i].receiptNo) + "</td> " +
                        " <td onclick=\"${ns}doEditPaymentAjax(\'" + obj[i].researchProjectId + "\',\'" + obj[i].itemList + "\')\" style=\"text-align: right\">" + $.trim(obj[i].amountReceivedShow) + "</td> " +
                        " <td style=\"text-align: center\">" +
                        "  <button onclick=\"${ns}doEditPaymentAjax(\'" + obj[i].researchProjectId + "\',\'" + obj[i].itemList + "\')\" class=\"btn btn-small\" type=\"button\">แก้ใข</button> " +
                        "  <button onclick=\"${ns}doDeletePaymentAjax(\'" + obj[i].researchProjectId + "\',\'" + obj[i].itemList + "\')\" class=\"btn btn-danger btn-small\" type=\"button\">ลบ</button> " +
                        " </td>  " +
                        " </tr> ";
            }
        }
        str = str + "</tbody>" +
                "</table>";
        $("#${ns}payment_item_list").html(str);
    }

    function ${ns}render_withdraw_item(obj) {
        var str = "<table class=\"table table-hover table-striped table-bordered table-condensed\" border=\"1\" style=\"font-size: 12px\"> " +
                " <thead>    " +
                "  <tr>" +
                "  		<th width=\"5%\" style=\"text-align: center\"><div class=\"th_class\">#</div></th>" +
                "		<th width=\"10%\" style=\"text-align: center\"><div class=\"th_class\">Withdraw Date</div></th>" +
                "		<th width=\"12%\" style=\"text-align: center\"><div class=\"th_class\">Amount Withdraw</div></th>  " +
                "		<th width=\"10%\" style=\"text-align: center\"><div class=\"th_class\">Balance</div></th> " +
                "		<th width=\"34%\" style=\"text-align: center\"><div class=\"th_class\">Remark</div></th> " +
                "		<th width=\"8%\"><div class=\"th_class\"></div></th>  " +
                "	</tr> 		" +
                " </thead> 	" +
                " <tbody>  	";

        if (obj != null) {
            for (var i = 0; i < obj.length; i++) {
                str = str + " 	<tr style=\"cursor: pointer;\">" +
                        " <td onclick=\"${ns}doEditWithdrawAjax(\'" + obj[i].researchProjectId + "\',\'" + obj[i].itemList + "\')\" style=\"text-align: left\">" + (i + 1) + "</td>" +
                        " <td onclick=\"${ns}doEditWithdrawAjax(\'" + obj[i].researchProjectId + "\',\'" + obj[i].itemList + "\')\" style=\"text-align: center\">" + $.trim(obj[i].withdrawDateShow) + "</td> " +
                        " <td onclick=\"${ns}doEditWithdrawAjax(\'" + obj[i].researchProjectId + "\',\'" + obj[i].itemList + "\')\" style=\"text-align: right\">" + $.trim(obj[i].amountWithdrawShow) + "</td> " +
                        " <td onclick=\"${ns}doEditWithdrawAjax(\'" + obj[i].researchProjectId + "\',\'" + obj[i].itemList + "\')\" style=\"text-align: right\">" + $.trim(obj[i].balanceShow) + "</td> " +
                        " <td onclick=\"${ns}doEditWithdrawAjax(\'" + obj[i].researchProjectId + "\',\'" + obj[i].itemList + "\')\" style=\"text-align: left\">" + $.trim(obj[i].remark) + "</td> " +
                        " <td style=\"text-align: center\">" +
                        "  <button onclick=\"${ns}doEditWithdrawAjax(\'" + obj[i].researchProjectId + "\',\'" + obj[i].itemList + "\')\" class=\"btn btn-small\" type=\"button\">แก้ใข</button> " +
                        "  <button onclick=\"${ns}doDeleteWithdrawAjax(\'" + obj[i].researchProjectId + "\',\'" + obj[i].itemList + "\')\" class=\"btn btn-danger btn-small\" type=\"button\">ลบ</button> " +
                        " </td>  " +
                        " </tr> ";
            }
        }
        str = str + "</tbody>" +
                "</table>";
        $("#${ns}withdraw_item_list").html(str);

    }

    function ${ns}render_document_item(obj) {
        var str = "<table class=\"table table-hover table-striped table-bordered table-condensed\" border=\"1\" style=\"font-size: 12px\"> " +
                " <thead>    " +
                "  <tr>" +
                "  		<th width=\"5%\" style=\"text-align: center\"><div class=\"th_class\">#</div></th>" +
                "		<th width=\"85%\" style=\"text-align: center\"><div class=\"th_class\">Document Name</div></th>" +
                "		<th width=\"10%\"><div class=\"th_class\"></div></th>  " +
                "	</tr> 		" +
                " </thead> 	" +
                " <tbody>  	";
        if (obj != null) {
            for (var i = 0; i < obj.length; i++) {
                str = str + " 	<tr style=\"cursor: pointer;\">" +
                        " <td onclick=\"${ns}doEditDocumentAjax(\'" + obj[i].researchProjectId + "\',\'" + obj[i].itemList + "\')\" style=\"text-align: left\">" + (i + 1) + "</td>";
                if ($.trim(obj[i].fileName).length > 0) {
                    str = str + "<td onclick=\"${ns}doEditDocumentAjax(\'" + obj[i].researchProjectId + "\',\'" + obj[i].itemList + "\')\" style=\"text-align: left\">" + $.trim(obj[i].description) + "  [ <a style=\"text-decoration: underline;\" onclick=\'${ns}downloadFile(\"" + obj[i].itemList + "\",\"" + obj[i].researchProjectId + "\")\'>" + obj[i].fileName + "</a>]</td> ";
                } else {
                    str = str + "<td onclick=\"${ns}doEditDocumentAjax(\'" + obj[i].researchProjectId + "\',\'" + obj[i].itemList + "\')\" style=\"text-align: left\">" + $.trim(obj[i].description) + "</td> ";
                }
                str = str + "" +
                        " <td style=\"text-align: center\">" +
                        "  <button onclick=\"${ns}doEditDocumentAjax(\'" + obj[i].researchProjectId + "\',\'" + obj[i].itemList + "\')\" class=\"btn btn-small\" type=\"button\">แก้ใข</button> " +
                        "  <button onclick=\"${ns}doDeleteDocumentAjax(\'" + obj[i].researchProjectId + "\',\'" + obj[i].itemList + "\')\" class=\"btn btn-danger btn-small\" type=\"button\">ลบ</button> " +
                        " </td>  " +
                        " </tr> ";
            }
        }
        str = str + "</tbody>" +
                "</table>";
        $("#${ns}document_item_list").html(str);
    }

    function ${ns}displayResearcher(mode, id) {
        $("#${ns}researcher_researcherId").val("");
        $("#${ns}researcher_researcherName").val("");
        $("#${ns}researcher_positionId").val("");
        $("#${ns}researcher_organizationId").val("");
        $("#researcherId_assign_autocomplete").prop("readonly", false)
        $("#researcherId_assign_autocomplete").val("");
        $("#researcherId_assign_autocomplete").css("background-color", "rgb(250, 250, 198)");
        $("#researcherId_assignShow").html("");
        $("#${ns}researcher_position").val("");
        $("#${ns}researcher_organization").val("");
        $("#${ns}researcher_isprojectLeader").prop("checked", false);
        if($("#${ns}_isLeader_init").val()=='true'){
            $("#${ns}researcher_isprojectLeader").prop("disabled","disabled")
        }else{
            $("#${ns}researcher_isprojectLeader").prop("disabled","")
        }
        $("#${ns}researcher_workLoadRatio").val("");
        if (mode == 'edit') {

        } else {
            $("#${ns}researcher_mode").val("add");
            $("#${ns}element_researcher").show("slow");
            $("#${ns}researcherListAll").show("slow");
            $("#${ns}researcher_itemList").val("");
        }
    }
    function ${ns}displayProgress(mode, id) {
        $("#${ns}progress_dueDate").val('');
        $("#${ns}progress_remark").val('');
        $("#${ns}progress_progressPercentage").val('');
        $("#${ns}progress_submitDate").val('');
        $("#${ns}progress_itemList").val('');
        $("#${ns}progress_progressItemId").val('1');
        if (mode == 'edit') {

        } else {
            $("#${ns}progress_mode").val("add");
            $("#${ns}element_progress").show("slow");
        }
    }
    function ${ns}displayPayment(mode, id) {
        $("#${ns}payment_receivedDate").val('');
        $("#${ns}payment_receiptNo").val('');
        $("#${ns}payment_amountReceived").val('');
        $("#${ns}payment_itemList").val('');
        $("#${ns}payment_progressItemId").val('1');
        if (mode == 'edit') {

        } else {
            $("#${ns}payment_mode").val("add");
            $("#${ns}element_payment").show("slow");
        }
    }
    function ${ns}displayWithdraw(mode, id) {
        $("#${ns}withdraw_date").val('');
        $("#${ns}withdraw_amount").val('');
        $("#${ns}withdraw_balance").val('');
        $("#${ns}withdraw_remark").val('');

        $("#${ns}withdraw_itemList").val("");
        $("#${ns}withdraw_withdrawItemId").val("1");
        if (mode == 'edit') {

        } else {
            $("#${ns}withdraw_mode").val("add")
            $("#${ns}element_withdraw").show("slow");
        }
    }

    function ${ns}displayDocument(mode, id) {
        $("#${ns}document_description").val('');
        $("#${ns}document_itemList").val('');
        if (mode == 'edit') {

        } else {
            $("#${ns}document_mode").val('add');
            $("#${ns}element_document").show("slow");
        }
    }

    function ${ns}exportProofFile(type) {
        $.ajax({
            url: '${research_group_resource_get_byid}',
            success: function (data) {
                var src;
                if (type == 'participation') {
                    src = data.proofParticipationResource
                } else
                    src = data.proofAcademicResource
                var div = document.createElement("div");
                document.body.appendChild(div);
                div.innerHTML = "<iframe width='0' height='0' scrolling='no' frameborder='0' src='" + src + "'></iframe>";
            }
        });
    }

    <%-- START DocAssign  --%>
    function ${ns}doDeleteDocAssignMappingAjax(refId, refType, userId) {
        var agree = confirm(" ต้องการลบข้อมูลหรือไม่? ");
        if (agree) {
            ${ns}showDownlod("${ns}doctype_assign_list");
            var docAssignMapping = {
                refId: refId,
                refType: refType,
                userId: userId
            }
            ResearchAjax.deleteDocAssignMapping(docAssignMapping, {
                callback: function (data) {
                    ${ns}render_docAssign_item(data);
                }
            });
            return true;
        }
        else {
            return false;
        }
    }
    function ${ns}doAddDocAssignMappingAjax(refId, refType, userId) {
        var mode = "add";
        var docAssignMapping = {
            refId: refId,
            refType: refType,
            userId: userId,
            createdBy: "${user.userId}",
            updatedBy: "${user.userId}"
        }
        ${ns}showDownlod("${ns}doctype_assign_list");
        ResearchAjax.saveDocAssignMapping(docAssignMapping, mode, {
            callback: function (data) {
                ${ns}render_docAssign_item(data);
                $("#docs_assignShow").hide("slow");
                $("#docs_assign_autocomplete").val('');
                $("#docsAssign").val('');
            }
        });
    }
    function ${ns}render_docAssign_item(obj) {
        var str = "<table class=\"table table-hover table-striped table-bordered table-condensed\" border=\"0\" style=\"width:30%;font-size: 12px\"> ";
        obj = obj.resultListObj;
        if (obj != null) {
            for (var i = 0; i < obj.length; i++) {
                str = str + " 	<tr style=\"cursor: pointer;\">" +
                        " <td width=\"5%\" style=\"text-align: left\">" + (i + 1) + "</td>" +
                        " <td style=\"text-align: left\">" + obj[i].userPortal.emailAddress + "</td>" +
                        " <td width=\"5%\"  style=\"text-align: center\">" +
                        "<button onclick=\'${ns}doDeleteDocAssignMappingAjax(\"" + obj[i].refId + "\",\"" + obj[i].refType + "\",\"" + obj[i].userId + "\")\' class=\"btn btn-danger btn-small\" type=\"button\">ลบ</button>" +
                        "</td>" +
                        " </tr> ";
            }
        }

        str = str + "</table>";
        $("#${ns}doctype_assign_list").html(str);
    }

    function ${ns}downloadFile(ref1, ref2) {
        ResearchAjax.downloadFile(ref1, ref2, "researchProject", function (data) {
            dwr.engine.openInDownload(data);
        });
    }
    <%-- END DocAssign  --%>
    function ${ns}count(s1, letter) {
        var match = s1.match(new RegExp(letter, 'g'));
        if (match != null)
            return match.length
        else
            return 0;
    }
    function ${ns}validateDigitTwoDigit(sDigit) {
        return (sDigit.indexOf('\.') != sDigit.length - 2);
    }
    function ${ns}validateDigit(sDigit) {
        var count = ${ns}count(sDigit, '\\.');
        var filter = /^[0-9.]+$/
        if (filter.test(sDigit)) {
            if (count > 1 || sDigit.indexOf('\.') == 0 || sDigit.indexOf('\.') == sDigit.length)
                return false;
            else
                return true;
        }
        else {
            return false;
        }
    }
    function ${ns}validateDigitOnly(sDigit) {
        var filter = /^[0-9]+$/
        if (filter.test(sDigit)) {
            return true;
        }
        else {
            return false;
        }
    }
    function ${ns}validateDigit2(sDigit) {
        var filter = /^\d*[0-9](|.\d*[0-9]|,\d*[0-9])?$/;
        if (filter.test(sDigit)) {
            return true;
        }
        else {
            return false;
        }
    }
    function ${ns}validateYear(sDigit) {
        var filter = /^\d{4}$/;
        if (filter.test(sDigit)) {
            return true;
        }
        else {
            return false;
        }
    }
    function ${ns}doSubmitForm(status) {
        var budgetYear = $('input[id="researchProjectM.budgetYear"]').val();
        if ($.trim(budgetYear).length > 0 && !${ns}validateYear(budgetYear)) {
            $('input[id="researchProjectM.budgetYear"]').val("");
            $('input[id="researchProjectM.budgetYear"]').focus();
            return false;
        }
        $('input[id="researchProjectM.docType"]').val(status);
        var form = document.forms['researchProjectForm'];
        form.submit();
    }
    function ${ns}showDownlod(element_) {
        var download_str = '<table border="0" width="100%"><tr><td  align="center"><img style="width:60px;" src="<c:url value="/resources/images/loading.gif"/>"/></td></tr></table>';
        $("#" + element_).html(download_str);
    }

    function IsNumeric(e) {
        var keyCode = e.which ? e.which : e.keyCode
        var ret = ((keyCode >= 48 && keyCode <= 57));
        if (!ret) {
            $("#error").html("Number Only.");
            $("#error").show();
        }
        else {
            $("#error").html("");
            $("#error").hide();
        }
        return ret;
    }

    function ${ns}showDownlod(element_) {
        var download_str = '<table border="0" width="100%"><tr><td  align="center"><img style="width:60px;" src="<c:url value="/resources/images/loading.gif"/>"/></td></tr></table>';
        $("#" + element_).html(download_str);
    }

    function ${ns}doSearchBox(f_name, pageNo) {
        if (f_name == 'researchGroup')
            ${ns}researchGroupPopup($("#${ns}keySearch_" + f_name).val(), false, pageNo)
        else if (f_name == 'organization')
            ${ns}organizationPopup($("#${ns}keySearch_" + f_name).val(), false, pageNo)
        else if (f_name == 'fundingResource')
            ${ns}fundingResourcePopup($("#${ns}keySearch_" + f_name).val(), false, pageNo)
        else if (f_name == 'docsAssign')
            ${ns}docsAssignPopup($("#${ns}keySearch_" + f_name).val(), false, pageNo)
        else if (f_name == 'researcher')
            ${ns}researcherPopup($("#${ns}keySearch_" + f_name).val(), false, pageNo)
    }
    function ${ns}chk(f_name, ev) {
        var key;
        ev = ev || event;
        key = ev.keyCode;
        if (key == 13) {
            ${ns}doSearchBox(f_name, 1);
            return false;
        }
        return true;
    }

    <%-- start ResearchGroup Popup --%>
    function ${ns}researchGroupPopup(keySearch, init, pageNo) {
        var keyBox = " <div>" +
                "<input type=\"text\"  style=\"display:inline;width:250px\" class=\"form-control\" " +
                " id=\"${ns}keySearch_researchGroup\" onkeypress=\"${ns}chk(\'researchGroup\',event)\" aria-describedby=\"inputSuccess4Status\" value=\"" + keySearch + "\"/>" +
                "<button onclick=\"${ns}doSearchBox('researchGroup',1)\" type=\"button\" class=\"btn btn-success\" style=\"margin-top:-8px;margin-left:5px;\">Search</button>" +
                "</div>";
        var str = "<table class=\"table table-hover table-striped table-bordered table-condensed\" border=\"1\" style=\"font-size: 12px\"> " +
                " <thead>    " +
                "  <tr>" +
                "  		<th width=\"20%\" style=\"text-align: center\"><div class=\"th_class\">รหัสกลุ่มวิจัย</div></th>" +
                "		<th width=\"40%\" style=\"text-align: center\"><div class=\"th_class\">ชื่อกลุ่มวิจัยภาษาไทย</div></th>  " +
                "		<th width=\"40%\" style=\"text-align: center\"><div class=\"th_class\">ช่ือกลุ่มวิจัยภาษาอังกฤษ</div></th> " +
                "	</tr> 		" +
                " </thead><tbody>";
        var pageObj = {
            pageSize: PAGE_SIZE_POPUP,
            pageNo: pageNo
        }
        var researchGroupM = {
            keySearch: keySearch,
            paging: pageObj
        }
        ResearchAjax.getResearchGroupList(researchGroupM, {
            callback: function (data) {
                var maxRow = data.maxRow;
                var lastpage = data.lastpage;
                data = data.resultListObj;
                if (data != null && data.length > 0) {
                    for (var i = 0; i < data.length; i++) {
                        str = str + "<tr  onclick=\"${ns}selectResearchGroup('" + data[i].researchGroupId + "')\" style=\"cursor: pointer;\">" +
                                "<td style=\"text-align: left\"  >" + data[i].groupCode + "</td>" +
                                "<td style=\"text-align: left\"  >" + data[i].groupTh + "</td>" +
                                "<td style=\"text-align: left\"  >" + data[i].groupEng + "</td>" +
                                "</tr>";
                    }
                }
                str = str + "</tbody></table>";
                var pageArray = calculatePageStartEnd(pageNo, lastpage);
                var pageStart = pageArray[0];
                var pageEnd = pageArray[1];
                var pagingStr = "<table border=\"0\" width=\"100%\" style=\"font-size: 13px\">" +
                        "<tbody>" +
                        "<tr>" +
                        "<td align=\"left\" width=\"70%\">" +
                        "<span class=\"pagination\">" +
                        "<ul>";
                if (pageNo != 1)
                    pagingStr = pagingStr + "<li><a style=\"cursor: pointer;\" onclick='${ns}doSearchBox(\"researchGroup\"," + (pageNo - 1) + ")'>Prev</a></li>";

                for (var j = pageStart; j <= pageEnd; j++) {
                    if (pageNo == j) {
                        pagingStr = pagingStr + "<li><a class=\"active\" style=\"background-color: #ddd\" >" + j + "</a></li>";
                    }
                    else {
                        pagingStr = pagingStr + "<li><a class=\"active\" style=\"cursor: pointer;\" onclick='${ns}doSearchBox(\"researchGroup\"," + (j) + ")'>" + j + "</a></li>";
                    }
                }
                if (pageEnd < lastpage) {
                    pagingStr = pagingStr + "<li><a class=\"active\" style=\"cursor: pointer;\" onclick='${ns}doSearchBox(\"researchGroup\"," + (pageEnd) + ")'>...</a></li>";
                    pagingStr = pagingStr + "<li><a class=\"active\" style=\"cursor: pointer;\" onclick='${ns}doSearchBox(\"researchGroup\"," + (lastpage) + ")'>" + lastpage + "</a></li>";
                }
                if (pageNo != lastpage) {
                    pagingStr = pagingStr + "<li><a style=\"cursor: pointer;\" onclick='${ns}doSearchBox(\"researchGroup\"," + ((pageNo < lastpage) ? (pageNo + 1) : (lastpage)) + ")'>Next</a></li>";
                }
                pagingStr = pagingStr + "</ul>" +
                        "</span>" +
                        "</td>" +
                        "<td align=\"right\" width=\"30%\">" +
                        "</td>" +
                        "</tr>" +
                        "</tbody>" +
                        "</table>";
                if (init) {
                    bootbox.classes("aoe_width");
                    bootbox.dialog(keyBox + "<div id=\"${ns}researchGroupPopupTable\">" + str + pagingStr + "</div>", [{
                        "label": "Close",
                        "class": "btn-danger",
                        "callback": function () {
                        }
                    }]);
                } else {
                    $("#${ns}researchGroupPopupTable").html(str + pagingStr);
                }
            }
        });
    }
    function ${ns}selectResearchGroup(objID) {
        ResearchAjax.findResearchGroupById(objID, {
            callback: function (data) {
                $('input[id="researchProjectM.researchGroupId"]').val(objID);
                $("#researchGroupId_assignShow").html(" " + data.groupTh + " ");
                $("#researchGroupId_assign_autocomplete").val(data.groupTh)
                bootbox.hideAll();
            }
        });
    }
    <%-- end ResearchGroup Popup --%>

    <%-- start organization Popup --%>
    function ${ns}organizationPopup(keySearch, init, pageNo) {
        var keyBox = " <div>" +
                "<input type=\"text\"  style=\"display:inline;width:250px\" class=\"form-control\" " +
                " id=\"${ns}keySearch_organization\" onkeypress=\"${ns}chk(\'organization\',event)\" aria-describedby=\"inputSuccess4Status\" value=\"" + keySearch + "\"/>" +
                "<button onclick=\"${ns}doSearchBox('organization',1)\" type=\"button\" class=\"btn btn-success\" style=\"margin-top:-8px;margin-left:5px;\">Search</button>" +
                "</div>";
        var str = "<table class=\"table table-hover table-striped table-bordered table-condensed\" border=\"1\" style=\"font-size: 12px\"> " +
                " <thead>    " +
                "  <tr>" +
                    //	"  		<th width=\"20%\" style=\"text-align: center\"><div class=\"th_class\">รหัสกลุ่มวิจัย</div></th>"+
                    //	"		<th width=\"40%\" style=\"text-align: center\"><div class=\"th_class\">ชื่อกลุ่มวิจัยภาษาไทย</div></th>  "+
                "		<th width=\"100%\" style=\"text-align: center\"><div class=\"th_class\">ชื่อหน่วยงาน</div></th> " +
                "	</tr> 		" +
                " </thead><tbody>";

        var pageObj = {
            pageSize: PAGE_SIZE_POPUP,
            pageNo: pageNo
        }
        var organizationM = {
            keySearch: keySearch,
            paging: pageObj
        }
        ResearchAjax.getOrganizationList(organizationM, {
            callback: function (data) {
                var maxRow = data.maxRow;
                var lastpage = data.lastpage;
                data = data.resultListObj;
                if (data != null && data.length > 0) {
                    for (var i = 0; i < data.length; i++) {
                        str = str + "<tr  onclick=\"${ns}selectOrganization('" + data[i].organizationId + "')\" style=\"cursor: pointer;\">" +
                                "<td style=\"text-align: left\"  >" + data[i].orgName + "</td>" +

                                "</tr>";
                    }
                }
                str = str + "</tbody></table>";
                var pageArray = calculatePageStartEnd(pageNo, lastpage);
                var pageStart = pageArray[0];
                var pageEnd = pageArray[1];
                var pagingStr = "<table border=\"0\" width=\"100%\" style=\"font-size: 13px\">" +
                        "<tbody>" +
                        "<tr>" +
                        "<td align=\"left\" width=\"70%\">" +
                        "<span class=\"pagination\">" +
                        "<ul>";
                if (pageNo != 1)
                    pagingStr = pagingStr + "<li><a style=\"cursor: pointer;\" onclick='${ns}doSearchBox(\"organization\"," + (pageNo - 1) + ")'>Prev</a></li>";

                for (var j = pageStart; j <= pageEnd; j++) {
                    if (pageNo == j) {
                        pagingStr = pagingStr + "<li><a class=\"active\" style=\"background-color: #ddd\" >" + j + "</a></li>";
                    }
                    else {
                        pagingStr = pagingStr + "<li><a class=\"active\" style=\"cursor: pointer;\" onclick='${ns}doSearchBox(\"organization\"," + (j) + ")'>" + j + "</a></li>";
                    }
                }
                if (pageEnd < lastpage) {
                    pagingStr = pagingStr + "<li><a class=\"active\" style=\"cursor: pointer;\" onclick='${ns}doSearchBox(\"organization\"," + (pageEnd) + ")'>...</a></li>";
                    pagingStr = pagingStr + "<li><a class=\"active\" style=\"cursor: pointer;\" onclick='${ns}doSearchBox(\"organization\"," + (lastpage) + ")'>" + lastpage + "</a></li>";
                }
                if (pageNo != lastpage) {
                    pagingStr = pagingStr + "<li><a style=\"cursor: pointer;\" onclick='${ns}doSearchBox(\"organization\"," + ((pageNo < lastpage) ? (pageNo + 1) : (lastpage)) + ")'>Next</a></li>";
                }
                pagingStr = pagingStr + "</ul>" +
                        "</span>" +
                        "</td>" +
                        "<td align=\"right\" width=\"30%\">" +
                        "</td>" +
                        "</tr>" +
                        "</tbody>" +
                        "</table>";
                if (init) {
                    bootbox.classes("aoe_width");
                    bootbox.dialog(keyBox + "<div id=\"${ns}organizationPopupTable\">" + str + pagingStr + "</div>", [{
                        "label": "Close",
                        "class": "btn-danger",
                        "callback": function () {
                        }

                    }]);
                } else {
                    $("#${ns}organizationPopupTable").html(str + pagingStr);
                }
            }
        });
    }
    function ${ns}selectOrganization(objID) {
        ResearchAjax.findOrganizationById(objID, {
            callback: function (data) {
                $('input[id="researchProjectM.organizationId"]').val(objID);
                $("#organizationId_assign_autocomplete").val(data.orgName);
                bootbox.hideAll();
            }
        });
    }
    <%-- end organization Popup --%>

    <%-- start organization Popup --%>
    function ${ns}fundingResourcePopup(keySearch, init, pageNo) {
        var keyBox = " <div>" +
                "<input type=\"text\"  style=\"display:inline;width:250px\" class=\"form-control\" " +
                " id=\"${ns}keySearch_fundingResource\" onkeypress=\"${ns}chk(\'fundingResource\',event)\" aria-describedby=\"inputSuccess4Status\" value=\"" + keySearch + "\"/>" +
                "<button onclick=\"${ns}doSearchBox('fundingResource',1)\" type=\"button\" class=\"btn btn-success\" style=\"margin-top:-8px;margin-left:5px;\">Search</button>" +
                "</div>";
        var str = "<table class=\"table table-hover table-striped table-bordered table-condensed\" border=\"1\" style=\"font-size: 12px\"> " +
                " <thead>    " +
                "  <tr>" +
                    //	"  		<th width=\"20%\" style=\"text-align: center\"><div class=\"th_class\">รหัสกลุ่มวิจัย</div></th>"+
                    //	"		<th width=\"40%\" style=\"text-align: center\"><div class=\"th_class\">ชื่อกลุ่มวิจัยภาษาไทย</div></th>  "+
                "		<th width=\"10%\" style=\"text-align: center\"><div class=\"th_class\">รหัสแหล่งทุน</div></th> " +
                "		<th width=\"10%\" style=\"text-align: center\"><div class=\"th_class\">รหัสประเภทแหล่งทุน</div></th> " +
                "		<th width=\"30%\" style=\"text-align: center\"><div class=\"th_class\">ชื่อแหล่งทุน(T)</div></th> " +
                "		<th width=\"30%\" style=\"text-align: center\"><div class=\"th_class\">ชื่อแหล่งทุน(E)</div></th> " +
                "		<th width=\"10%\" style=\"text-align: center\"><div class=\"th_class\">ชื่อย่อแหล่งทุน</div></th> " +
                "	</tr> 		" +
                " </thead><tbody>";

        var pageObj = {
            pageSize: PAGE_SIZE_POPUP,
            pageNo: pageNo
        }
        var fundingResourceM = {
            keySearch: keySearch,
            paging: pageObj
        }
        ResearchAjax.getFundingResourceList(fundingResourceM, {
            callback: function (data) {
                var maxRow = data.maxRow;
                var lastpage = data.lastpage;
                data = data.resultListObj;
                if (data != null && data.length > 0) {
                    for (var i = 0; i < data.length; i++) {
                        str = str + "<tr  onclick=\"${ns}selectfundingResource('" + data[i].fundingResourceId + "')\" style=\"cursor: pointer;\">" +
                                "<td style=\"text-align: left\"  >" + data[i].fundingResourceCode + "</td>" +
                                "<td style=\"text-align: left\"  >" + data[i].fundingResourceType.frtCode + " / +" + data[i].fundingResourceType.frtName + "</td>" +
                                "<td style=\"text-align: left\"  >" + ((data[i].frNameThai != null) ? (data[i].frNameThai) : ("")) + "</td>" +
                                "<td style=\"text-align: left\"  >" + ((data[i].frNameEng != null) ? (data[i].frNameEng) : ("")) + "</td>" +
                                "<td style=\"text-align: left\"  >" + ((data[i].frShortName != null) ? (data[i].frShortName) : ("")) + "</td>" +
                                "</tr>";
                    }
                }
                str = str + "</tbody></table>";
                var pageArray = calculatePageStartEnd(pageNo, lastpage);
                var pageStart = pageArray[0];
                var pageEnd = pageArray[1];
                var pagingStr = "<table border=\"0\" width=\"100%\" style=\"font-size: 13px\">" +
                        "<tbody>" +
                        "<tr>" +
                        "<td align=\"left\" width=\"70%\">" +
                        "<span class=\"pagination\">" +
                        "<ul>";
                if (pageNo != 1)
                    pagingStr = pagingStr + "<li><a style=\"cursor: pointer;\" onclick='${ns}doSearchBox(\"fundingResource\"," + (pageNo - 1) + ")'>Prev</a></li>";

                for (var j = pageStart; j <= pageEnd; j++) {
                    if (pageNo == j) {
                        pagingStr = pagingStr + "<li><a class=\"active\" style=\"background-color: #ddd\" >" + j + "</a></li>";
                    }
                    else {
                        pagingStr = pagingStr + "<li><a class=\"active\" style=\"cursor: pointer;\" onclick='${ns}doSearchBox(\"fundingResource\"," + (j) + ")'>" + j + "</a></li>";
                    }
                }
                if (pageEnd < lastpage) {
                    pagingStr = pagingStr + "<li><a class=\"active\" style=\"cursor: pointer;\" onclick='${ns}doSearchBox(\"fundingResource\"," + (pageEnd) + ")'>...</a></li>";
                    pagingStr = pagingStr + "<li><a class=\"active\" style=\"cursor: pointer;\" onclick='${ns}doSearchBox(\"fundingResource\"," + (lastpage) + ")'>" + lastpage + "</a></li>";
                }
                if (pageNo != lastpage) {
                    pagingStr = pagingStr + "<li><a style=\"cursor: pointer;\" onclick='${ns}doSearchBox(\"fundingResource\"," + ((pageNo < lastpage) ? (pageNo + 1) : (lastpage)) + ")'>Next</a></li>";
                }
                pagingStr = pagingStr + "</ul>" +


                        "</span>" +
                        "</td>" +
                        "<td align=\"right\" width=\"30%\">" +
                        "</td>" +
                        "</tr>" +
                        "</tbody>" +
                        "</table>";
                if (init) {
                    bootbox.classes("aoe_width");
                    bootbox.dialog(keyBox + "<div id=\"${ns}fundingResourcePopupTable\">" + str + pagingStr + "</div>", [{
                        "label": "Close",
                        "class": "btn-danger",
                        "callback": function () {
                        }

                    }]);
                } else {
                    $("#${ns}fundingResourcePopupTable").html(str + pagingStr);
                }
            }
        });
    }
    function ${ns}selectfundingResource(objID) {
        ResearchAjax.findFundingResourceById(objID, {
            callback: function (data) {
                $('input[id="researchProjectM.fundingResourceId"]').val(objID);
                $("#fundingResourceId_assign_autocomplete").val(data.frNameThai);
                bootbox.hideAll();
            }
        });
    }
    <%-- end researcher Popup --%>

    <%-- start docsAssign Popup --%>
    function ${ns}docsAssignPopup(keySearch, init, pageNo) {
        var keyBox = " <div>" +
                "<input type=\"text\"  style=\"display:inline;width:250px\" class=\"form-control\" " +
                " id=\"${ns}keySearch_docsAssign\" onkeypress=\"${ns}chk(\'docsAssign\',event)\" aria-describedby=\"inputSuccess4Status\" value=\"" + keySearch + "\"/>" +
                "<button onclick=\"${ns}doSearchBox('docsAssign',1)\" type=\"button\" class=\"btn btn-success\" style=\"margin-top:-8px;margin-left:5px;\">Search</button>" +
                "</div>";
        var str = "<table class=\"table table-hover table-striped table-bordered table-condensed\" border=\"1\" style=\"font-size: 12px\"> " +
                " <thead>    " +
                "  <tr>" +
                    //	"  		<th width=\"20%\" style=\"text-align: center\"><div class=\"th_class\">รหัสกลุ่มวิจัย</div></th>"+
                    //	"		<th width=\"40%\" style=\"text-align: center\"><div class=\"th_class\">ชื่อกลุ่มวิจัยภาษาไทย</div></th>  "+
                "		<th width=\"100%\" style=\"text-align: center\"><div class=\"th_class\">ชื่อ นามสกุล</div></th> " +

                "	</tr> 		" +
                " </thead><tbody>";

        var pageObj = {
            pageSize: PAGE_SIZE_POPUP,
            pageNo: pageNo
        }
        var docsAssignM = {
            keySearch: keySearch,
            paging: pageObj
        }
        ResearchAjax.getUserPortalList(docsAssignM, {
            callback: function (data) {
                var maxRow = data.maxRow;
                var lastpage = data.lastpage;
                data = data.resultListObj;
                if (data != null && data.length > 0) {
                    for (var i = 0; i < data.length; i++) {
                        str = str + "<tr  onclick=\"${ns}selectdocsAssign('" + data[i].userId + "')\" style=\"cursor: pointer;\">" +
                                "<td style=\"text-align: left\"  >" + data[i].emailAddress + " [ " + data[i].firstName + " " + data[i].lastName + "]" + "</td>"
                        "</tr>";
                    }
                }
                str = str + "</tbody></table>";
                var pageArray = calculatePageStartEnd(pageNo, lastpage);
                var pageStart = pageArray[0];
                var pageEnd = pageArray[1];
                var pagingStr = "<table border=\"0\" width=\"100%\" style=\"font-size: 13px\">" +
                        "<tbody>" +
                        "<tr>" +
                        "<td align=\"left\" width=\"70%\">" +
                        "<span class=\"pagination\">" +
                        "<ul>";
                if (pageNo != 1)
                    pagingStr = pagingStr + "<li><a style=\"cursor: pointer;\" onclick='${ns}doSearchBox(\"docsAssign\"," + (pageNo - 1) + ")'>Prev</a></li>";

                for (var j = pageStart; j <= pageEnd; j++) {
                    if (pageNo == j) {
                        pagingStr = pagingStr + "<li><a class=\"active\" style=\"background-color: #ddd\" >" + j + "</a></li>";
                    }
                    else {
                        pagingStr = pagingStr + "<li><a class=\"active\" style=\"cursor: pointer;\" onclick='${ns}doSearchBox(\"docsAssign\"," + (j) + ")'>" + j + "</a></li>";
                    }
                }
                if (pageEnd < lastpage) {
                    pagingStr = pagingStr + "<li><a class=\"active\" style=\"cursor: pointer;\" onclick='${ns}doSearchBox(\"docsAssign\"," + (pageEnd) + ")'>...</a></li>";
                    pagingStr = pagingStr + "<li><a class=\"active\" style=\"cursor: pointer;\" onclick='${ns}doSearchBox(\"docsAssign\"," + (lastpage) + ")'>" + lastpage + "</a></li>";
                }
                if (pageNo != lastpage) {
                    pagingStr = pagingStr + "<li><a style=\"cursor: pointer;\" onclick='${ns}doSearchBox(\"docsAssign\"," + ((pageNo < lastpage) ? (pageNo + 1) : (lastpage)) + ")'>Next</a></li>";
                }
                pagingStr = pagingStr + "</ul>" +
                        "</span>" +
                        "</td>" +
                        "<td align=\"right\" width=\"30%\">" +
                        "</td>" +
                        "</tr>" +
                        "</tbody>" +
                        "</table>";
                if (init) {
                    bootbox.classes("aoe_width");
                    bootbox.dialog(keyBox + "<div id=\"${ns}docsAssignPopupTable\">" + str + pagingStr + "</div>", [{
                        "label": "Close",
                        "class": "btn-danger",
                        "callback": function () {
                        }
                    }]);
                } else {
                    $("#${ns}docsAssignPopupTable").html(str + pagingStr);
                }
            }
        });
    }
    function ${ns}selectdocsAssign(objID) {
        ${ns}doAddDocAssignMappingAjax($("#researchProjectId").val(), "RESEARCH", objID);
        bootbox.hideAll();
    }
    <%-- end docsAssign Popup --%>

    <%-- start researcher Popup --%>
    function ${ns}researcherPopup(keySearch, init, pageNo) {
        var keyBox = " <div>" +
                "<input type=\"text\"  style=\"display:inline;width:250px\" class=\"form-control\" " +
                " id=\"${ns}keySearch_researcher\" onkeypress=\"${ns}chk(\'researcher\',event)\" aria-describedby=\"inputSuccess4Status\" value=\"" + keySearch + "\"/>" +
                "<button onclick=\"${ns}doSearchBox('researcher',1)\" type=\"button\" class=\"btn btn-success\" style=\"margin-top:-8px;margin-left:5px;\">Search</button>" +
                "</div>";
        var str = "<table class=\"table table-hover table-striped table-bordered table-condensed\" border=\"1\" style=\"font-size: 12px\"> " +
                " <thead>    " +
                "  <tr>" +
                "		<th width=\"50%\" style=\"text-align: center\"><div class=\"th_class\">ชื่อนักวิจัย/นามสกุล(T)</div></th> " +
                "		<th width=\"50%\" style=\"text-align: center\"><div class=\"th_class\">ชื่อนักวิจัย/นามสกุล(E)</div></th> " +
                "	</tr> 		" +
                " </thead><tbody>";

        var pageObj = {
            pageSize: PAGE_SIZE_POPUP,
            pageNo: pageNo
        }
        var researcherM = {
            keySearch: keySearch,
            paging: pageObj
        }
        ResearchAjax.getResearcherList(researcherM, {
            callback: function (data) {
                var maxRow = data.maxRow;
                var lastpage = data.lastpage;
                data = data.resultListObj;
                if (data != null && data.length > 0) {
                    for (var i = 0; i < data.length; i++) {
                        str = str + "<tr  onclick=\"${ns}selectresearcher('" + data[i].researcherId + "')\" style=\"cursor: pointer;\">" +
                                "<td style=\"text-align: left\"  >" + data[i].nameThai + " " + data[i].surnameThai + "</td>" +
                                "<td style=\"text-align: left\"  >" + data[i].nameEng + " " + data[i].surnameEng + "</td>" +
                                "</tr>";
                    }
                }
                str = str + "</tbody></table>";
                var pageArray = calculatePageStartEnd(pageNo, lastpage);
                var pageStart = pageArray[0];
                var pageEnd = pageArray[1];
                var pagingStr = "<table border=\"0\" width=\"100%\" style=\"font-size: 13px\">" +
                        "<tbody>" +
                        "<tr>" +
                        "<td align=\"left\" width=\"70%\">" +
                        "<span class=\"pagination\">" +
                        "<ul>";
                if (pageNo != 1)
                    pagingStr = pagingStr + "<li><a style=\"cursor: pointer;\" onclick='${ns}doSearchBox(\"researcher\"," + (pageNo - 1) + ")'>Prev</a></li>";

                for (var j = pageStart; j <= pageEnd; j++) {
                    if (pageNo == j) {
                        pagingStr = pagingStr + "<li><a class=\"active\" style=\"background-color: #ddd\" >" + j + "</a></li>";
                    }
                    else {
                        pagingStr = pagingStr + "<li><a class=\"active\" style=\"cursor: pointer;\" onclick='${ns}doSearchBox(\"researcher\"," + (j) + ")'>" + j + "</a></li>";
                    }
                }
                if (pageEnd < lastpage) {
                    pagingStr = pagingStr + "<li><a class=\"active\" style=\"cursor: pointer;\" onclick='${ns}doSearchBox(\"researcher\"," + (pageEnd) + ")'>...</a></li>";
                    pagingStr = pagingStr + "<li><a class=\"active\" style=\"cursor: pointer;\" onclick='${ns}doSearchBox(\"researcher\"," + (lastpage) + ")'>" + lastpage + "</a></li>";
                }
                if (pageNo != lastpage) {
                    pagingStr = pagingStr + "<li><a style=\"cursor: pointer;\" onclick='${ns}doSearchBox(\"researcher\"," + ((pageNo < lastpage) ? (pageNo + 1) : (lastpage)) + ")'>Next</a></li>";
                }
                pagingStr = pagingStr + "</ul>" +
                        "</span>" +
                        "</td>" +
                        "<td align=\"right\" width=\"30%\">" +
                        "</td>" +
                        "</tr>" +
                        "</tbody>" +
                        "</table>";
                if (init) {
                    bootbox.classes("aoe_width");
                    bootbox.dialog(keyBox + "<div id=\"${ns}researcherPopupTable\">" + str + pagingStr + "</div>", [{
                        "label": "Close",
                        "class": "btn-danger",
                        "callback": function () {
                        }

                    }]);
                } else {
                    $("#${ns}researcherPopupTable").html(str + pagingStr);
                }
            }
        });
    }
    function ${ns}selectresearcher(objID) {
        ResearchAjax.findResearcherById(objID, {
            callback: function (data) {
                $('input[id="researchProjectM.researcherId"]').val(objID);
                $("#researcherId_assign_autocomplete").val(data.nameThai + " " + data.surnameThai);
                $('#${ns}researcher_researcherId').val(objID);
                $('#${ns}researcher_researcherName').val(data.nameThai + " " + data.surnameThai);
                $('#${ns}researcher_positionId').val((data.position != null && data.position.positionId) ? data.position.positionId : "");
                $('#${ns}researcher_organizationId').val((data.organization != null && data.organization.organizationId) ? data.organization.organizationId : "");
                $('#${ns}researcher_position').val((data.position != null && data.position.positionName) ? data.position.positionName : "");
                $('#${ns}researcher_organization').val((data.organization != null && data.organization.orgName) ? data.organization.orgName : "");
                bootbox.hideAll();
            }
        });
    }
    <%-- end researcher Popup --%>

    function ${ns}confirmDelete(_urlDelete) {
        var agree = confirm(" ต้องการลบข้อมูลหรือไม่? ");
        if (agree) {
            window.location.href = _urlDelete;
            return true;
        }
        else {
            return false;
        }
    }
    function ${ns}confirmUndo(_urlUndo){
        var agree=confirm(" ต้องการ Undo ข้อมูลหรือไม่?");
        if (agree){
            window.location.href = _urlUndo;
            return true ;
        }
        else{
            return false ;
        }
    }
</script>