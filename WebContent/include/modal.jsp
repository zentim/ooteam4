<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8" isELIgnored="false"%>

<div>
      <!-- loginFrame !-->
    <div id="myModal" class="modal">
      
      <div class="main-div">
      
      <!-- loginFrame -->
      <div class="login-div">
      
           
        <!-- Login -->
        <div class="top_catagory_area clearfix">
              <div class="container">
                
                <div class="row justify-content-end" style="margin-top: 50px;">
                    <span class="close cursor" onclick="closeModal()" style="padding: 20px 25px;">&times;</span>
                  </div>
                
                  <div class="row justify-content-center">
              <form id="ajaxLoginForm" style="width: 100%; max-width: 420px; padding: 15px; margin: auto;">
                <div class="registerErrorMessageDiv" style="display: none;">
                      <div class="alert alert-danger" role="alert">
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close"></button>
                    <span class="errorMessage"></span>
                      </div>        
                  </div>
              
                <div class="text-center mb-4">
                  <i class="fas fa-user-circle fa-5x mb-4"></i>
                  <h1 class="h3 mb-3 font-weight-normal">Login</h1>
                  <span style="color: #777">Please enter your email and password</span>
                </div>
              
                <div class="form-label-group mb-3">
                  <input type="email" id="login_email" name="email" class="form-control" placeholder="Email address" required autofocus>
                </div>
              
                <div class="form-label-group mb-3">
                  <input 
                    type="password" 
                    id="login_password" 
                    name="password" 
                    class="form-control" 
                    placeholder="Password"
                    pattern=".{8,15}"
                    title="8 to 15 characters" 
                    required>
                </div>
              
                
                <button class="btn btn-lg btn-primary btn-block" type="submit" id="login_submit">Login</button>
                <a onclick="registerShow()" style="cursor: pointer;">
                  <p class="mt-5 mb-3 text-muted text-center">Register?</p>
                </a>
              </form>
            </div>
            
          </div>
        </div>
        <!-- Login -->
           
           
      </div>
      <!-- loginFrame -->
       
       
       
      <!-- RegisterFrame !-->
        <div class="register-div">
           
           
        <!-- Register -->
        <div class="top_catagory_area clearfix">
              <div class="container">
                
                <div class="row justify-content-end" style="margin-top: 50px;">
                    <span class="close cursor" onclick="closeModal()" style="padding: 20px 25px;">&times;</span>
                  </div>
                
                  <div class="row justify-content-center">
              <form id="ajaxRegisterForm" style="width: 100%; max-width: 420px; padding: 15px; margin: auto;">
                <div class="registerErrorMessageDiv" style="display: none;">
                      <div class="alert alert-danger" role="alert">
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close"></button>
                    <span class="errorMessage"></span>
                      </div>        
                  </div>
              
                <div class="text-center mb-4">
                  <i class="fas fa-user-circle fa-5x mb-4"></i>
                  <h1 class="h3 mb-3 font-weight-normal">Login</h1>
                  <span style="color: #777">Please enter your email and password</span>
                </div>
              
                <div class="form-label-group mb-3">
                  <input type="email" id="register_email" name="email" class="form-control" placeholder="Email address" required autofocus>
                </div>
              
                <div class="form-label-group mb-3">
                  <input 
                    type="password" 
                    id="register_password" 
                    name="password" 
                    class="form-control" 
                    placeholder="Password"
                    pattern=".{8,15}"
                    title="8 to 15 characters" 
                    required>
                </div>
                
                <div class="form-label-group mb-3">
                  <input 
                    type="password" 
                    id="register_password_again" 
                    name="password" 
                    class="form-control" 
                    placeholder="Please input your password again"
                    pattern=".{8,15}"
                    title="8 to 15 characters" 
                    required>
                </div>
              
                
                <button class="btn btn-lg btn-primary btn-block" type="submit" id="register_submit">Register</button>
                <a onclick="loginShow()" style="cursor: pointer;">
                  <p class="mt-5 mb-3 text-muted text-center">Login?</p>
                </a>
              </form>
            </div>
            
          </div>
        </div>
        <!-- Register -->          
       
      </div>
      <!-- RegisterFrame !-->
      
    </div>
  </div>
</div>

<!-- For delete order -->
<div class="modal" id="deleteConfirmModal" tabindex="-1" role="dialog" style="z-index: 5000;">
    <div class="modal-dialog deleteConfirmModalDiv">
       <div class="modal-content">
          <div class="modal-header">
            <h4 class="modal-title">Confirm Delete？</h4>
          </div>
          <div class="modal-footer">
            <button class="btn btn-danger deleteConfirmButton" id="submit" type="button" style="margin-bottom: 0">confirm</button>
            <button data-dismiss="modal" class="btn btn-default" type="button">close</button>
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
      $("#ajaxLoginForm").submit(function(){
          var email = $("#login_email").val();
          var password = $("#login_password").val();
      
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
                        
                        if("success" == result){
                                
                          location.reload();
                           
                        }else{                      
                          $("#login_password").val('');
                          $('.errorMessage').html('Invalid email or password!');
                          $(".errorMessage").css("color", "#ed2553");
                          $(".registerErrorMessageDiv").css("display","block");
                        }
                     }
                })
          }else  if( email == "" && password == "" ){
            $('.errorMessage').html('Please enter your email and password!');
            $(".errorMessage").css("color", "#ed2553");
            $(".registerErrorMessageDiv").css("display","block");         
          }else if(email==""){
            $('.errorMessage').html('Please enter your email!');
            $(".errorMessage").css("color", "#ed2553");
            $(".registerErrorMessageDiv").css("display","block");     
          }else if(email==""){
            $('.errorMessage').html('Please enter your password!');
            $(".errorMessage").css("color", "#ed2553");
            $(".registerErrorMessageDiv").css("display","block");
            
          } return false;
      });
  });
  
  
  $(document).ready(function(){
      $("#ajaxRegisterForm").submit(function(){
          var email = $("#register_email").val();
          var password = $("#register_password").val();
          var password_again = $("#register_password_again").val();
          
          if (password !== password_again) {
            $('.errorMessage').html('Inconsistence Password!');
            $(".errorMessage").css("color", "#ed2553");
            $(".registerErrorMessageDiv").css("display","block");
            
            return false;
          }
      
          if( email != "" && password != "" ){
              $.ajax({
                    url:'foreregisterAjax',
                    type:'post',
                    async: false,
                    data:{email: email, password: password, repeatPassword: password_again},
                    success:function(result){
                       /* if(res == 'General customer'||res == 'VIP customer'){
                          alert("register successful!");
                          location.reload();
                        }else if(res == 'admin'||res == 'manager'){
                          location.href = './BackEndIndex.jsp';
                        
                        */
                        
                        if("success" == result){
                        	
                          location.reload();
                          
                        }else{
                          $('.errorMessage').html(result);
                          $(".errorMessage").css("color", "#ed2553");
                          $(".registerErrorMessageDiv").css("display","block");   
                        }
                     }
                })
          }else  if( email == "" && password == "" ){
            $('.errorMessage').html('Please enter your email and password!');
            $(".errorMessage").css("color", "#ed2553");
            $(".registerErrorMessageDiv").css("display","block");         
          }else if(email==""){
            $('.errorMessage').html('Please enter your email!');
            $(".errorMessage").css("color", "#ed2553");
            $(".registerErrorMessageDiv").css("display","block");     
          }else if(password==""){
            $('.errorMessage').html('Please enter your password!');
            $(".errorMessage").css("color", "#ed2553");
            $(".registerErrorMessageDiv").css("display","block");
            
          } return false;
      });
  });
  
  function openModal() {
	  $(".registerErrorMessageDiv").css("display","none");
      document.getElementById('myModal').style.display = "block";
      $('.register-div').css("display","none");
      $('.login-div').css("display","block");
  }

  function closeModal() {
      document.getElementById('myModal').style.display = "none";   
  }
  
  function registerShow(){
    $(".registerErrorMessageDiv").css("display","none");
    document.getElementById('myModal').style.display = "block";
    $('.register-div').css("display","block");
    $('.login-div').css("display","none");
  }
  
  function loginShow(){  
	$(".registerErrorMessageDiv").css("display","none");
    document.getElementById('myModal').style.display = "block";
    $('.login-div').css("display","block");
    $('.register-div').css("display","none");
  }
</script>

