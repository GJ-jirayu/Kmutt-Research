<%@ include file="/WEB-INF/jsp/include.jsp" %>
<%@ page contentType="text/html; charset=utf-8" %>
<c:set var="ns"><portlet:namespace/></c:set>
<script>
    /**
     * Created by imake on 29/08/2015.
     */
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
function ${ns}confirmDelete(_urlDelete){
    var agree=confirm(" ต้องการลบข้อมูลหรือไม่? ");
    if (agree){
        window.location.href = _urlDelete;
        return true ;
    }
    else{
        return false ;
    }
}
function ${ns}chk(ev) {
    var key;
    ev = ev || event;
    key = ev.keyCode;
    alert(key)
    if (key == 13) {
    ${ns}doAction();
        return false;
    }
    return true;
}
function ${ns}doDeleteItems(){
    var agree=confirm(" ต้องการลบข้อมูลหรือไม่? ");
    if (agree){
        var form = document.forms['researchProjectForm'];
        $("#${ns}mode").val('deleteItems');
        form.submit();
    }
    else{
        return false ;
    }
}
function ${ns}doSubmitForm(){
    var form = document.forms['researchProjectForm'];
    form.submit();
}
function ${ns}doSubmitTab(tab){
    var form = document.forms['researchProjectForm'];
    form.elements['${ns}tab'].value=tab;
    form.elements['${ns}mode'].value="search";
    form.submit();
}
function ${ns}doSubmitPaging(){
    var form = document.forms['researchProjectForm'];
    form.elements['${ns}mode'].value="search";
    form.submit();
}
function ${ns}doSearch(){
    $("#${ns}pageNo").val("1");
    ${ns}doSubmitPaging();
}
function ${ns}doChangePage(){
${ns}doSubmitPaging();
}
function ${ns}exportFile(type){
    var src = $("#${ns}_export_"+type).val();
    var div = document.createElement("div");
    document.body.appendChild(div);
    div.innerHTML = "<iframe width='0' height='0' scrolling='no' frameborder='0' src='" + src + "'></iframe>";
}
function  ${ns}importFile(type){

}
function ${ns}doAction(){
    return true;
}

<!-- footer -->
function  ${ns}rederPage(url){
    window.location.href=url;
}
function ${ns}displayElementEdit(mode,url,id){
    $("#groupCode").val('');
    $("#groupTh").val('');
    $("#${ns}mode").val(mode);
    $("#${ns}command").val("list");
    $("#${ns}export_import_element").hide();
    if(mode=='edit'){
        $("#${ns}export_import_element").show();
        $.ajax({
            url: url ,
            data: { researcherId: id },
            success: function(data){
                $("#researcherId").val(id)
                $("#groupCode").val(data.groupCode)
                $("#groupTh").val(data.groupTh);
                $("#groupEng").val(data.groupEng);
                $("#${ns}_export_csv").val(data.csvResource);
                $("#${ns}_export_xml").val(data.xmlResource);
                $( "#${ns}element_edit" ).show( "slow");
            }
        });
    }else{
        $( "#${ns}element_edit" ).show( "slow");
    }
}
function ${ns}goPrev(){
    if($("#${ns}pageNo").val()!='1'){
        var prev=parseInt($("#${ns}pageNo").val())-1;
        $("#${ns}pageNo").val(prev);
    ${ns}doSubmitPaging();
    }
}
function ${ns}goNext(){
    var next=parseInt($("#${ns}pageNo").val());
    if(next<parseInt($("#${ns}pageCount").val())){
        next=next+1;
        $("#${ns}pageNo").val(next);
    ${ns}doSubmitPaging();
    }
}
function ${ns}goToPage(pageNo){
    $("#${ns}pageNo").val(pageNo)
    ${ns}doSubmitPaging();
}
function ${ns}changeSelectAll(obj){
    $( "input[name='ids']" ).each(function() {
        if(!$( this ).prop( "disabled"))
            $( this ).prop( "checked", obj.checked );
    });
}
</script>