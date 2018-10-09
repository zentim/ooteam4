<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>

<div>
	    <!-- loginFrame !-->
		<div id="myModal" class="modal">
		  <span class="close cursor" onclick="closeModal()">&times;</span>
		  <!-- loginFrame -->
		  <div class="main-div">
		  <div class="login-div">
		    <div class="panel">
		     <div class="card">
		   <h2 class="title"> Login</h2> 
		   </div>
		   
		   <p id="login_notice">Please enter your email and password</p>
		   </div>
		        <div class="form-group">
		        <input type="text" class="form-control" id="login_email" placeholder="Account">
		        </div>
		        <div class="form-group">
		            <input type="password" class="form-control" id="login_password" placeholder="Password">
		       </div>  
		       <div class="form-group">
		       <button id="login_submit" class="btn ">Login</button>
		       <button id="register_show" class="btn  " onclick="registerShow()">register</button>
		       </div>
			</div>
		   <!-- loginFrame -->
		   
		   
		   
		   <!-- RegisterFrame !-->
		    <div class="register-div">
		     <div class="card">
    		<h2 class="title" style="clear:both">Register</h2>
    		</div>
		    <div class="panel">
		    
    		<p id="register_notice">Please fill in the form</p>
		   </div>
		        <div class="form-group">
		            <input type="text" name="email" class="form-control" id="register_email" placeholder="email">
		       </div>
		        <div class="form-group">
		            <input type="password" name="password" class="form-control" id="register_password" placeholder="Password">
		       </div> 
		       
		        <div class="form-group col-md-6 mb-3" style="padding:0px">
		            <input type="text" name="firstName" class="form-control" id="register_firstName" placeholder="firstName">
		       </div>
		        <div class="form-group col-md-6 mb-3" style="padding-right:0px">
		            <input type="text" name="lastName" class="form-control" id="register_lastName" placeholder="lastName">
		       </div>
		       	       
		       <div class="form-group "  >
		            <input type="text" name="phone" class="form-control" id="register_phone_last" placeholder="phone number" maxlength="10">
		       </div>
		       
		        <div class="form-group">
		            <input type="text" name="address" class="form-control" id="register_address" placeholder="address">
		       </div>
		       <div class="form-group">
			   
			    <select class="form-control" id="exampleFormControlSelect1">
			      <option value="100" disabled selected>gender</option>
			      <option value="0" >woman</option>
			      <option value="1">man</option>
			      
			    </select>
			  </div>
		       <button id="register_submit" class="btn btn-primary">Register</button>
		         
		       <br><br>
				
		   
		    </div>
		     <!-- RegisterFrame !-->
		</div>
	</div>
</div>

<!-- For delete order -->
<div class="modal" id="deleteConfirmModal" tabindex="-1" role="dialog" >
    <div class="modal-dialog deleteConfirmModalDiv">
       <div class="modal-content">
          <div class="modal-header">
            <button data-dismiss="modal" class="close" type="button"><span aria-hidden="true">×</span><span class="sr-only">Close</span></button>
            <h4 class="modal-title">確認刪除？</h4>
          </div>
          <div class="modal-footer">
            <button data-dismiss="modal" class="btn btn-default" type="button">close</button>
            <button class="btn btn-primary deleteConfirmButton" id="submit" type="button">confirm</button>
          </div>
      </div>
    </div>
</div>


<script>
$(function(){
     
    <c:if test="${!empty msg}">
	    $("span.errorMessage").html("${msg}");
	    $("div.loginErrorMessageDiv").show();       
    </c:if>
     
})



$(document).ready(function(){
	    $("#login_submit").click(function(){
	        var email = $("#login_email").val();
	        var password = $("#login_password").val();
			console.log(email + password);
	        if( email != "" && password != "" ){
	        	  $.ajax({
	                  url:'foreloginAjax',
	                  type:'post',
	                  async: false,
	                  data:{email: email, password: password},
	                  success:function(result){
	                     /* if(res == 'General customer'||res == 'VIP customer'){
	                      	alert("Login successful!");
	                      	location.reload();
	                      }else if(res == 'admin'||res == 'manager'){
	                      	location.href = './BackEndIndex.jsp';
	                      
	                      */
	                      console.log("login success!!!")
	                      if("success" == result){
	                              
	                    	  location.reload();
	                         
	                      }else{                    	
	                      	$("#login_password").val('');
	                      	$('#login_notice').html('Invalid email or password!');
	                      	$("#login_notice").css("color", "#ed2553");
	                      }
	                   }
	              })
	        }else  if( email == "" && password == "" ){
	        	$('#login_notice').html('Please enter your email and password!');
	        	$("#login_notice").css("color", "#ed2553");        	
	        }else if(email==""){
	        	$('#login_notice').html('Please enter your email!');
	        	$("#login_notice").css("color", "#ed2553");    	
	        }else if(email==""){
	        	$('#login_notice').html('Please enter your password!');
	        	$("#login_notice").css("color", "#ed2553");
	        	
	        } return false;
	    });
	});
	
	
	$(document).ready(function(){
	    $("#register_submit").click(function(){
	        var email = $("#register_email").val();
	        var password = $("#register_password").val();
			console.log(email+password);
	        if( email != "" && password != "" ){
	        	  $.ajax({
	                  url:'foreregister',
	                  type:'get',
	                  async: false,
	                  data:{email:email,password:password},
	                  error: function (jqXHR, exception) {
	                      console.log("register error!!!")
	                	  var msg = '';
	                      if (jqXHR.status === 0) {
	                          msg = 'Not connect.\n Verify Network.';
	                      } else if (jqXHR.status == 404) {
	                          msg = 'Requested page not found. [404]';
	                      } else if (jqXHR.status == 500) {
	                          msg = 'Internal Server Error [500].';
	                      } else if (exception === 'parsererror') {
	                          msg = 'Requested JSON parse failed.';
	                      } else if (exception === 'timeout') {
	                          msg = 'Time out error.';
	                      } else if (exception === 'abort') {
	                          msg = 'Ajax request aborted.';
	                      } else {
	                          msg = 'Uncaught Error.\n' + jqXHR.responseText;
	                      }
	                      $('#post').html(msg);
	                  },
	                  success:function(result){
	                     /* if(res == 'General customer'||res == 'VIP customer'){
	                      	alert("register successful!");
	                      	location.reload();
	                      }else if(res == 'admin'||res == 'manager'){
	                      	location.href = './BackEndIndex.jsp';
	                      
	                      */
	                   	console.log("register success: " + result)
	                      if("success" == result){
	                              
	                    	  location.reload();
	                       
	                      }else{
	                    		$('#register_notice').html(result);
	                    		$("#register_notice").css("color", "red");
	                      }
	                   }
	              })
	        }else  if( email == "" && password == "" ){
	        	$('#register_notice').html('Please enter your email and password!');
	        	$("#register_notice").css("color", "#ed2553");        	
	        }else if(email==""){
	        	$('#register_notice').html('Please enter your email!');
	        	$("#register_notice").css("color", "#ed2553");    	
	        }else if(password==""){
	        	$('#register_notice').html('Please enter your password!');
	        	$("#register_notice").css("color", "#ed2553");
	        	
	        } return false;
	    });
	});
	
	function openModal() {
		  document.getElementById('myModal').style.display = "block";
		  $('.register-div').css("display","none");
		  $('.login-div').css("display","block");
	}

	function closeModal() {
		  document.getElementById('myModal').style.display = "none";	 
	}
	function registerShow(){
		
		document.getElementById('myModal').style.display = "block";
		 $('.register-div').css("display","block");
		  $('.login-div').css("display","none");
	
	}
</script>

