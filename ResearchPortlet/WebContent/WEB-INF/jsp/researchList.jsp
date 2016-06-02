<%@ include file="/WEB-INF/jsp/include.jsp" %>
<%@ page contentType="text/html; charset=utf-8" %> 
<%@page import="javax.portlet.PortletURL"%>
 
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
                       <span><label> ปีงบประมาณ:<span style="color: red;font-size: 20;"> </span> 
                        </label>
                        <select><option>2557</option></select>
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
                    <div> <input type="text" style="display:inline;background-color: rgb(250, 250, 198);width:200px" class="form-control" id="inputEmail3" placeholder="">  <button   class="btn btn-default">...</button>
                       </div>
              </td> 
            </tr>  
             <tr  >
              <td align="right" width="20%">
                        <span><label>หน่วยงาน:<span style="color: red;font-size: 20;"> </span> 
                        </label></span> 
              </td>
              <td width="80%" colspan="3">
                    <div> <label style="padding-left:10px">วิทยาเขต:<span style="color: red;font-size: 20;padding-left:10px"> </span> <input type="text" style="display:inline;background-color: rgb(250, 250, 198);width:200px" class="form-control" id="inputEmail3" placeholder="">  <button   class="btn btn-default">...</button>
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
            <tr>
              <td align="right" width="20%">
                        <label><span>วันที่เริ่มโครงการ:<span style="color: red;font-size: 20;"></span> 
                        </span> </label>
              </td>
              <td width="30%">
                     <span>
                      <input type="datetime" style="display:inline;background-color: rgb(250, 250, 198);width:200px" class="form-control" id="inputEmail3" placeholder="">
                      <i class="glyphicon glyphicon-calendar"></i>  
                    </span>
              </td>
               <td align="left" width="20%" colspan="2">
                        <span><label>วันที่จบโครงการ:<span style="color: red;font-size: 20;"></span> </label>
                        <input type="datetime" style="display:inline;background-color: rgb(250, 250, 198);width:200px" class="form-control" id="inputEmail3" placeholder="">
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
                        <span><label>งบประมาณที่อนุมัติ:<span style="color: red;font-size: 20;"></span> </label>
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
                    <div> <input type="text" style="display:inline;background-color: rgb(250, 250, 198);width:200px" class="form-control" id="inputEmail3" placeholder="">  <button   class="btn btn-default">...</button>
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
            <tr>
              <td align="right" width="20%">
                        <label><span>รายงานฉบับสมบูรณ์:<span style="color: red;font-size: 20;"></span> 
                        </span> </label>
              </td>
              <td width="30%">
                      <span style="padding-left:5px"><label>กำหนดส่ง:<span style="color: red;font-size: 20;">
                        </span> </label><input type="datetime" style="display:inline;background-color: rgb(250, 250, 198);width:150px" class="form-control" id="inputEmail3" placeholder="">  
                      <i class="glyphicon glyphicon-calendar"></i> </span>
              </td>
               <td align="left" width="20%" colspan="2">
                        <span><label>ส่งจริง:<span style="color: red;font-size: 20;"></span> </label>
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
             <tr valign="top">
              <td align="right" colspan="4" width="100%">
          <div class="bs-example bs-example-tabs" role="tabpanel">
    <ul id="myTab" class="nav nav-tabs" role="tablist">
      <li role="presentation" class="active"><a href="#home" id="home-tab" role="tab" data-toggle="tab" aria-controls="home" aria-expanded="true">นักงิจัย</a></li>
      <li role="presentation" class=""><a href="#profile" role="tab" id="profile-tab" data-toggle="tab" aria-controls="profile" aria-expanded="false">ผลความก้าวหน้า</a></li>
      <li role="presentation" class=""><a href="#profile" role="tab" id="profile-tab" data-toggle="tab" aria-controls="profile" aria-expanded="false">การรับเงินงวด</a></li>
        <li role="presentation" class=""><a href="#profile" role="tab" id="profile-tab" data-toggle="tab" aria-controls="profile" aria-expanded="false">การเบิกเงินงวด</a></li>
          <li role="presentation" class=""><a href="#profile" role="tab" id="profile-tab" data-toggle="tab" aria-controls="profile" aria-expanded="false">เอกสาร</a></li>
        </ul>
      </li>
    </ul>
    <div id="myTabContent" class="tab-content">
      <div role="tabpanel" class="tab-pane fade active in" id="home" aria-labelledby="home-tab"> 
         <table border="0" width="100%" style="font-size: 13px">
                <tbody> 
                </tr>
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
      <div role="tabpanel" class="tab-pane fade" id="profile" aria-labelledby="profile-tab">
        <p>Food truck fixie locavore, accusamus mcsweeney's marfa nulla single-origin coffee squid. Exercitation +1 labore velit, blog sartorial PBR leggings next level wes anderson artisan four loko farm-to-table craft beer twee. Qui photo booth letterpress, commodo enim craft beer mlkshk aliquip jean shorts ullamco ad vinyl cillum PBR. Homo nostrud organic, assumenda labore aesthetic magna delectus mollit. Keytar helvetica VHS salvia yr, vero magna velit sapiente labore stumptown. Vegan fanny pack odio cillum wes anderson 8-bit, sustainable jean shorts beard ut DIY ethical culpa terry richardson biodiesel. Art party scenester stumptown, tumblr butcher vero sint qui sapiente accusamus tattooed echo park.</p>
      </div>
      <div role="tabpanel" class="tab-pane fade" id="dropdown1" aria-labelledby="dropdown1-tab">
        <p>Etsy mixtape wayfarers, ethical wes anderson tofu before they sold out mcsweeney's organic lomo retro fanny pack lo-fi farm-to-table readymade. Messenger bag gentrify pitchfork tattooed craft beer, iphone skateboard locavore carles etsy salvia banksy hoodie helvetica. DIY synth PBR banksy irony. Leggings gentrify squid 8-bit cred pitchfork. Williamsburg banh mi whatever gluten-free, carles pitchfork biodiesel fixie etsy retro mlkshk vice blog. Scenester cred you probably haven't heard of them, vinyl craft beer blog stumptown. Pitchfork sustainable tofu synth chambray yr.</p>
      </div>
      <div role="tabpanel" class="tab-pane fade" id="dropdown2" aria-labelledby="dropdown2-tab">
        <p>Trust fund seitan letterpress, keytar raw denim keffiyeh etsy art party before they sold out master cleanse gluten-free squid scenester freegan cosby sweater. Fanny pack portland seitan DIY, art party locavore wolf cliche high life echo park Austin. Cred vinyl keffiyeh DIY salvia PBR, banh mi before they sold out farm-to-table VHS viral locavore cosby sweater. Lomo wolf viral, mustache readymade thundercats keffiyeh craft beer marfa ethical. Wolf salvia freegan, sartorial keffiyeh echo park vegan.</p>
      </div>
    </div>

</div>  </td> 
            </tr>
          </table>
      
        
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
    $('#myTab a').click(function (e) {
  		e.preventDefault()
  		$(this).tab('show')
		});
    });
    </script>
  </body>
</html>
