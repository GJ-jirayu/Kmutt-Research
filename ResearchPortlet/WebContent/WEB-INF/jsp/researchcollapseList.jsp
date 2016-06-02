<%@ include file="/WEB-INF/jsp/include.jsp" %>
<%@ page contentType="text/html; charset=utf-8" %> 
<%@page import="javax.portlet.PortletURL"%>
 <c:url var="url" value="/" />
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content=""> 
    <title>Research</title> 
    <!-- Bootstrap core CSS -->
     <link rel="stylesheet" href="<c:url value="/resources/bootstrap/css/bootstrap.min.css"/>" type="text/css"/> 
    <style type="text/css">
  		#breadcrumbs { display:none; }
    </style>
   
  </head>

  <body> 
        <fieldset style="font-family: sans-serif;padding-top:5px">
    <div style="border: 1px solid #FFC299;background: #F9F9F9;padding: 10px">
    <div class="accordion" id="accordion2">
                <div class="accordion-group">
                  <div class="accordion-heading">
                    <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion2" href="#collapseOne">
                      ข้อมูลโครงการ
                    </a>
                  </div>
                  <div id="collapseOne" class="accordion-body in collapse" style="height: auto;">
                    <div class="accordion-inner">
                       <table border="1" width="100%" style="border: 1px solid #FFC299;font-size:13px">
            <tr>
              <td width="100%" colspan="4"></td>
            </tr>
            <tr>
              <td align="right" width="20%">
                        <label><span>รหัสโครงการ:<span style="color: red;font-size: 20;"><strong>*</strong></span> 
                        </span> </label>
              </td>
              <td width="30%">
                      <input readonly="readonly" type="text" style="background-color: rgb(250, 250, 198);width:200px" class="form-control" id="inputEmail3" placeholder="">  
              </td>
              <td width="50%">
                       <span style="font-size: 16px">  ปีงบประมาณ:<span style="color: red;font-size: 20;"> </span> 
                        
                        <select><option>2557</option><option>2558</option></select>
                          
                        </span>  
              </td> 
            </tr>
            <tr valign="top">
              <td align="right" width="20%">
                        <span><label>ขื่อโครงการ(T):<span style="color: red;font-size: 20;"> </span> 
                        </label></span> 
              </td>
              <td width="80%" colspan="3">
                   <textarea class="form-control" rows="2"></textarea>  
              </td> 
            </tr> 
            <tr valign="top">
              <td align="right" width="20%">
                        <span><label>ขื่อโครงการ(E):<span style="color: red;font-size: 20;"> </span> 
                        </label></span> 
              </td>
              <td width="80%" colspan="3">
                   <textarea class="form-control" rows="2"></textarea>  
              </td> 
            </tr>   
            <tr  >
              <td align="right" width="20%">
                        <span><label>ค้นหาโครงการจาก Axapla:<span style="color: red;font-size: 20;"> </span> 
                        </label></span> 
              </td>
              <td width="80%" colspan="3">
                    <div> <input readonly="readonly" type="text" style="display:inline;background-color: rgb(250, 250, 198);width:200px" class="form-control" id="inputEmail3" placeholder="">  <button   class="btn btn-default">...</button>
                       </div>
              </td> 
            </tr>  
             <tr  >
              <td align="right" width="20%"> 
              </td>
              <td width="80%" colspan="3">
                   <textarea style="background-color: rgb(250, 250, 198)" class="form-control" rows="2"></textarea>  
              </td> 
            </tr>  
            <tr  >
              <td align="right" width="20%">
                        <span><label>ระยะที่:<span style="color: red;font-size: 50;"> </span> 
                        </label></span> 
              </td>
              <td width="80%" colspan="3">
                    <div> <input type="text" style="display:inline;width:50px" class="form-control" id="inputEmail3" placeholder="">   
                       </div>
              </td> 
            </tr> 
            <tr  >
              <td align="right" width="20%">
                        <span><label>กลุ่มวิจัย:<span style="color: red;font-size: 20;"> </span> 
                        </label></span> 
              </td>
              <td width="80%" colspan="3">
                    <div> <input type="text" style="display:inline;background-color: rgb(250, 250, 198);width:200px"
                     class="form-control" id="inputEmail3" placeholder=""
                      data-provide="typeahead" data-items="4" data-source="[&quot;กลุ่มงานวิจัย 1&quot;,&quot;กลุ่มงานวิจัย 2&quot;,
                      &quot;กลุ่มงานวิจัย 3&quot;,
                     &quot;กลุ่มงานวิจัย 4&quot;]">  <button   class="btn btn-default">...</button>
                       </div>
              </td> 
            </tr>  
             <tr  >
              <td align="right" width="20%">
                        <span><label>หน่วยงาน:<span style="color: red;font-size: 20;"> </span> 
                        </label></span> 
              </td>
              <td width="80%" colspan="3">
                    <div>
                     <input type="text" style="margin: 0 auto;display:inline;background-color: rgb(250, 250, 198);width:200px" 
                     class="form-control" id="inputEmail3" placeholder=""
                     data-provide="typeahead" data-items="4" data-source="[&quot;คณะ A / ฝ่าย A&quot;,&quot;คณะ B -> ฝ่าย B&quot;,
                     &quot;คณะ C -> ฝ่าย C&quot;
                     ]" >  <button   class="btn btn-default">...</button>
                      
                       </div>
              </td> 
            </tr>  
             <%--
             <tr  >
              <td align="right" width="20%">
                        <span><label>หน่วยงาน:<span style="color: red;font-size: 20;"> </span> 
                        </label></span> 
              </td>
              <td width="80%" colspan="3">
                    <div> <label style="padding-left:10px">วิทยาเขต:<span style="color: red;font-size: 20;padding-left:10px"> </span>
                     <input type="text" style="display:inline;background-color: rgb(250, 250, 198);width:200px" class="form-control" id="inputEmail3" placeholder="">  <button   class="btn btn-default">...</button>
                     <input type="text" class="span3" style="margin: 0 auto;" data-provide="typeahead" data-items="4" data-source="[&quot;Alabama&quot;,&quot;Alaska&quot;,&quot;Arizona&quot;,&quot;Arkansas&quot;,&quot;California&quot;,&quot;Colorado&quot;,&quot;Connecticut&quot;,&quot;Delaware&quot;,&quot;Florida&quot;,&quot;Georgia&quot;,&quot;Hawaii&quot;,&quot;Idaho&quot;,&quot;Illinois&quot;,&quot;Indiana&quot;,&quot;Iowa&quot;,&quot;Kansas&quot;,&quot;Kentucky&quot;,&quot;Louisiana&quot;,&quot;Maine&quot;,&quot;Maryland&quot;,&quot;Massachusetts&quot;,&quot;Michigan&quot;,&quot;Minnesota&quot;,&quot;Mississippi&quot;,&quot;Missouri&quot;,&quot;Montana&quot;,&quot;Nebraska&quot;,&quot;Nevada&quot;,&quot;New Hampshire&quot;,&quot;New Jersey&quot;,&quot;New Mexico&quot;,&quot;New York&quot;,&quot;North Dakota&quot;,&quot;North Carolina&quot;,&quot;Ohio&quot;,&quot;Oklahoma&quot;,&quot;Oregon&quot;,&quot;Pennsylvania&quot;,&quot;Rhode Island&quot;,&quot;South Carolina&quot;,&quot;South Dakota&quot;,&quot;Tennessee&quot;,&quot;Texas&quot;,&quot;Utah&quot;,&quot;Vermont&quot;,&quot;Virginia&quot;,&quot;Washington&quot;,&quot;West Virginia&quot;,&quot;Wisconsin&quot;,&quot;Wyoming&quot;]">
                       </div>
              </td> 
            </tr>  
            
            <tr  >
              <td align="right" width="20%"> 
              </td>
              <td width="80%" colspan="3">
                    <div> <label style="padding-left:10px">คณะ/สำนัก:<span style="color: red;font-size: 20;padding-left:10px"> </span> <input type="text" style="display:inline;background-color: rgb(250, 250, 198);width:200px" class="form-control" id="inputEmail3" placeholder="">  
                       </div>
              </td> 
            </tr>  
             <tr  >
              <td align="right" width="20%"> 
              </td>
              <td width="80%" colspan="3">
                    <div> <label style="padding-left:10px">ภาควิชา/ฝ่าย/กอง:<span style="color: red;font-size: 20;padding-left:10px"> </span> <input type="text" style="display:inline;background-color: rgb(250, 250, 198);width:200px" class="form-control" id="inputEmail3" placeholder="">   
                       </div>
              </td> 
            </tr>  
             <tr  >
              <td align="right" width="20%"> 
              </td>
              <td width="80%" colspan="3">
                    <div> <label style="padding-left:10px">งาน:<span style="color: red;font-size: 20;padding-left:10px"> </span> <input type="text" style="display:inline;background-color: rgb(250, 250, 198);width:200px" class="form-control" id="inputEmail3" placeholder="">  
                       </div>
              </td> 
            </tr>  
             --%>
            <tr>
              <td align="right" width="20%">
                        <label><span>วันที่เริ่มโครงการ:<span style="color: red;font-size: 20;"></span> 
                        </span> </label>
              </td>
              <td width="30%">
                     <span>
                      <input type="datetime" style="display:inline;background-color: rgb(250, 250, 198);width:200px" class="datepicker" id="startDate" placeholder="">
                         
                    </span>
              </td>
               <td align="left" width="20%" colspan="2">
                        <span style="font-size: 16px"> วันที่จบโครงการ:<span style="color: red;font-size: 20;"></span> 
                        <input type="datetime" style="display:inline;background-color: rgb(250, 250, 198);width:200px" class="form-control" id="endDate" placeholder="">
                        <i class="glyphicon glyphicon-calendar"></i> 
                        </span> 
              </td> 
            </tr>
             <tr>
              <td align="right" width="20%">
                        <label><span>งบประมาณที่เสนอขอ:<span style="color: red;font-size: 20;"></span> 
                        </span> </label>
              </td>
              <td width="30%">
                      <input type="datetime" style="width:200px" class="form-control" id="inputEmail3" placeholder="">  
              </td>
               <td align="left" width="20%" colspan="2">
                        <span style="font-size:16px"> งบประมาณที่อนุมัติ:<span style="color: red;font-size: 20;"></span>  
                        <input type="datetime" style="display:inline;width:200px" class="form-control" id="inputEmail3" placeholder="">
                        </span> 
              </td> 
            </tr>
             <tr  >
              <td align="right" width="20%">
                        <span><label>ประเภทแหล่งทุน:<span style="color: red;font-size: 20;"> </span> 
                        </label></span> 
              </td>
              <td width="80%" colspan="3">
                    <div> <input type="text" style="display:inline;background-color: rgb(250, 250, 198);width:200px" 
                    class="form-control" id="inputEmail3" placeholder=""
                     data-provide="typeahead" data-items="4" data-source="[&quot;ประเภทแหล่งทุน 1 -> แหล่งทุน 1 -> ไทย&quot;,&quot;ประเภทแหล่งทุน 2-> แหล่งทุน 2 -> ไทย&quot;,
                      &quot;ประเภทแหล่งทุน 3 -> แหล่งทุน 3 -> ไทย&quot;,
                     &quot;ประเภทแหล่งทุน 4 -> แหล่งทุน 4 -> ไทย&quot;]">  <button   class="btn btn-default">...</button>
                       </div>
              </td> 
            </tr>  
            <%-- 
             <tr  >
              <td align="right" width="20%">
                        <span><label>ประเภทแหล่งทุน:<span style="color: red;font-size: 20;"> </span> 
                        </label></span> 
              </td>
              <td width="80%" colspan="3">
                    <div> <input type="text" style="display:inline;background-color: rgb(250, 250, 198);width:200px" 
                    class="form-control" id="inputEmail3" placeholder=""
                     data-provide="typeahead" data-items="4" data-source="[&quot;ประเภทแหล่งทุน 1&quot;,&quot;ประเภทแหล่งทุน 2&quot;,
                      &quot;ประเภทแหล่งทุน 3&quot;,
                     &quot;ประเภทแหล่งทุน 4&quot;]">  <button   class="btn btn-default">...</button>
                       </div>
              </td> 
            </tr>  
             <tr  >
              <td align="right" width="20%">
                        <span><label>แหล่งทุน:<span style="color: red;font-size: 20;"> </span> 
                        </label></span> 
              </td>
              <td width="80%" colspan="3">
                    <div> <input readonly="readonly" type="text" style="display:inline;width:200px;background-color: rgb(250, 250, 198);" class="form-control" id="inputEmail3" placeholder=""> 
                       </div>
              </td> 
            </tr>  
             <tr  >
              <td align="right" width="20%">
                        <span><label>ประเทศ:<span style="color: red;font-size: 20;"> </span> 
                        </label></span> 
              </td>
              <td width="80%" colspan="3">
                    <div> <input readonly="readonly" type="text" style="display:inline;width:200px;background-color: rgb(250, 250, 198);" class="form-control" id="inputEmail3" placeholder="">   
                       </div>
              </td> 
            </tr>  
             --%>
            <tr>
              <td align="right" width="20%">
                        <label><span>รายงานฉบับสมบูรณ์:<span style="color: red;font-size: 20;"></span> 
                        </span> </label>
              </td>
              <td width="30%">
                      <span style="padding-left:5px;font-size: 16px"> กำหนดส่ง:<span style="color: red;font-size: 20;">
                        </span> <input type="datetime" style="display:inline;background-color: rgb(250, 250, 198);width:150px" class="form-control" id="inputEmail3" placeholder="">  
                      <i class="glyphicon glyphicon-calendar"></i> </span>
              </td>
               <td align="left" width="20%" colspan="2">
                        <span style="font-size: 16px"> ส่งจริง:<span style="color: red;font-size: 20;"></span>  
                        <input type="datetime" style="display:inline;background-color: rgb(250, 250, 198);width:150px" class="form-control" id="inputEmail3" placeholder="">
                        <i class="glyphicon glyphicon-calendar"></i> </span> 
              </td> 
            </tr>
             <tr valign="top">
              <td align="right" width="20%">
                        <span><label>หมายเหตุ:<span style="color: red;font-size: 20;"> </span> 
                        </label></span> 
              </td>
              <td width="80%" colspan="3">
                   <textarea class="form-control" rows="2"></textarea>  
              </td> 
            </tr>  
              
          </table>
                    </div>
                  </div>
                </div>
                <div class="accordion-group">
                  <div class="accordion-heading">
                    <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion2" href="#collapseTwo">
                      นักวิจัย
                    </a>
                  </div>
                  <div id="collapseTwo" class="accordion-body collapse" style="height: 0px;">
                    <div class="accordion-inner"> 
         <table border="0" width="100%" style="font-size: 13px">
                <tbody>  
                <tr>
                <td align="left" width="70%">   

                </td><td align="right" width="30%"> 
                <span sytle="padding-left:20px">
                 <button type="button" class="btn btn-primary">Add</button> 
               </span >
                </td>
                </tr>
                </tbody></table>  
        <table class="table table-striped table-bordered table-condensed" border="1" style="font-size: 12px"> 
               <thead>    
                   <tr> <!-- 50 42+8 -->
                   <th width="5%" style="text-align: center"><div class="th_class">#</div></th> 
                 <th width="8%" style="text-align: center"><div class="th_class">รหัสนักวิจัย</div></th>  
                   <th width="24%" style="text-align: center"><div class="th_class">ชื่อ - สกุล</div></th>  
                    <th width="20%" style="text-align: center"><div class="th_class">ภาควิชา/หน่วยงาน/บริษัท/องค์กร</div></th>   
                    <th width="10%" style="text-align: center"><div class="th_class">ตำแหน่ง</div></th>  
                    <th width="14%" style="text-align: center"><div class="th_class">เป็นหัวหน้าโครงการ</div></th>  
                    <th width="14%" style="text-align: center"><div class="th_class">ภาระการทำงาน</div></th>  
                <th width="5%"><div class="th_class"></div></th>  
                   </tr> 
                </thead> 
                <tbody>    
                <tr style="cursor: pointer;">
                <td style="text-align: left">1</td> 
                <td style="text-align: left">2541001</td> 
                <td style="text-align: left">ผศ ดร. xxxx xxxx</td> 
                <td style="text-align: left">คณะ xxxx xxxx</td> 
                 <td style="text-align: left">อาจารย์</td> 
                   <td style="text-align: left">ไม่เป็น</td> 
                     <td style="text-align: left"></td>
                <td style="text-align: center"><i class="glyphicon glyphicon-edit"></i>
                <i class="glyphicon glyphicon-remove"></i></td> 
              </tr>
                
            </tbody>
          </table> 
                    </div>
                  </div>
                </div>
                <div class="accordion-group">
                  <div class="accordion-heading">
                    <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion2" href="#collapseThree">
                       ผลความก้าวหน้า
                    </a>
                  </div>
                  <div id="collapseThree" class="accordion-body collapse" style="height: 0px;">
                    <div class="accordion-inner">
                      Anim pariatur cliche reprehenderit, enim eiusmod high life accusamus terry richardson ad squid. 3 wolf moon officia aute, non cupidatat skateboard dolor brunch. Food truck quinoa nesciunt laborum eiusmod. Brunch 3 wolf moon tempor, sunt aliqua put a bird on it squid single-origin coffee nulla assumenda shoreditch et. Nihil anim keffiyeh helvetica, craft beer labore wes anderson cred nesciunt sapiente ea proident. Ad vegan excepteur butcher vice lomo. Leggings occaecat craft beer farm-to-table, raw denim aesthetic synth nesciunt you probably haven't heard of them accusamus labore sustainable VHS.
                    </div>
                  </div>
                </div>
                <div class="accordion-group">
                  <div class="accordion-heading">
                    <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion2" href="#collapseFour">
             การรับเงินงวด
                    </a>
                  </div>
                  <div id="collapseFour" class="accordion-body collapse" style="height: 0px;">
                    <div class="accordion-inner">
                      Anim pariatur cliche reprehenderit, enim eiusmod high life accusamus terry richardson ad squid. 3 wolf moon officia aute, non cupidatat skateboard dolor brunch. Food truck quinoa nesciunt laborum eiusmod. Brunch 3 wolf moon tempor, sunt aliqua put a bird on it squid single-origin coffee nulla assumenda shoreditch et. Nihil anim keffiyeh helvetica, craft beer labore wes anderson cred nesciunt sapiente ea proident. Ad vegan excepteur butcher vice lomo. Leggings occaecat craft beer farm-to-table, raw denim aesthetic synth nesciunt you probably haven't heard of them accusamus labore sustainable VHS.
                    </div>
                  </div>
                </div>
                <div class="accordion-group">
                  <div class="accordion-heading">
                    <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion2" href="#collapseFive">
                 การเบิกเงินงวด
                    </a>
                  </div>
                  <div id="collapseFive" class="accordion-body collapse" style="height: 0px;">
                    <div class="accordion-inner">
                      Anim pariatur cliche reprehenderit, enim eiusmod high life accusamus terry richardson ad squid. 3 wolf moon officia aute, non cupidatat skateboard dolor brunch. Food truck quinoa nesciunt laborum eiusmod. Brunch 3 wolf moon tempor, sunt aliqua put a bird on it squid single-origin coffee nulla assumenda shoreditch et. Nihil anim keffiyeh helvetica, craft beer labore wes anderson cred nesciunt sapiente ea proident. Ad vegan excepteur butcher vice lomo. Leggings occaecat craft beer farm-to-table, raw denim aesthetic synth nesciunt you probably haven't heard of them accusamus labore sustainable VHS.
                    </div>
                  </div>
                </div>
                <div class="accordion-group">
                  <div class="accordion-heading">
                    <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion2" href="#collapseSix">
                     เอกสาร
                    </a>
                  </div>
                  <div id="collapseSix" class="accordion-body collapse" style="height: 0px;">
                    <div class="accordion-inner">
                      Anim pariatur cliche reprehenderit, enim eiusmod high life accusamus terry richardson ad squid. 3 wolf moon officia aute, non cupidatat skateboard dolor brunch. Food truck quinoa nesciunt laborum eiusmod. Brunch 3 wolf moon tempor, sunt aliqua put a bird on it squid single-origin coffee nulla assumenda shoreditch et. Nihil anim keffiyeh helvetica, craft beer labore wes anderson cred nesciunt sapiente ea proident. Ad vegan excepteur butcher vice lomo. Leggings occaecat craft beer farm-to-table, raw denim aesthetic synth nesciunt you probably haven't heard of them accusamus labore sustainable VHS.
                    </div>
                  </div>
                </div>
              </div> 
        
</div>
</fieldset>
     
    <div class="container">
      <!-- Example row of columns -->
      <div class="row">
         
      </div>

      <hr>

      <footer>
        <%-- <p>&copy; Company 2014</p> --%>
      </footer>
    </div> <!-- /container -->


    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster --> 
     <script src="<c:url value="/resources/js/jquery-1.11.2.min.js"/>"></script> 
    <script src="<c:url value="/resources/bootstrap/js/bootstrap.min.js"/>"></script> 
    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <%-- 
    <script src="../assets/js/ie10-viewport-bug-workaround.js"></script>
     --%>
      <script>
    $(document).ready(function() {  
    	var _path="${url}";
    	//alert(_path)
    	//$('.datepicker').datepicker()
    	/*
    	 $("#startDate" ).datepicker({ 
    			dateFormat:"dd/mm/yy" ,
    			changeMonth: true,
    			changeYear: true
    		});
    	$("#endDate" ).datepicker({ 
			dateFormat:"dd/mm/yy" ,
			changeMonth: true,
			changeYear: true
		});
    	*/
    });
    </script>
  </body>
</html>
