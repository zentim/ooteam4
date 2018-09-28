<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
 
<div class="container">
  <h1 class="form-heading" style="color:white">login Form</h1>
  <div class="login-form">
    <div class="main-div" style="width: 400px; margin: 0 auto;">
        <div class="panel">
          <h2>User Register</h2>   
          <p>Please enter your account and password</p>
        </div>
       
        <form method="post" action="foreregister" class="registerForm">
    
            
            <div class="registerErrorMessageDiv" style="display: none;">
                <div class="alert alert-danger" role="alert">
                  <button type="button" class="close" data-dismiss="alert" aria-label="Close"></button>
                    <span class="errorMessage"></span>
                </div>        
            </div>

            <div class="form-group">
                <input type="text" name="name" class="form-control" id="name" placeholder="Account">
            </div>

            <div class="form-group">

                <input type="password"  name="password" class="form-control" id="password" placeholder="Password" style="margin: 5px 0px;">
                <input type="password"  name="repeatpassword" class="form-control" id="repeatpassword" placeholder="Input your password again">
            </div>

            <button type="submit" class="btn btn-primary btn-lg" style="width: 100px;">Sign Up</button>
        </form>
      </div>
  </div>
</div>


<script>
$(function(){
     
    <c:if test="${!empty msg}">
	    $("span.errorMessage").html("${msg}");
	    $("div.registerErrorMessageDiv").css("display","none");       
    </c:if>
     
    $(".registerForm").submit(function(){
        if(0 == $("#name").val().length){
            $("span.errorMessage").html("請輸入用戶名");
            $("div.registerErrorMessageDiv").css("display", "block");           
            return false;
        }       
        if(0 == $("#password").val().length){
            $("span.errorMessage").html("請輸入密碼");
            $("div.registerErrorMessageDiv").css("display", "block");           
            return false;
        }       
        if(0 == $("#repeatpassword").val().length){
            $("span.errorMessage").html("請輸入重複密碼");
            $("div.registerErrorMessageDiv").css("display", "block");           
            return false;
        }       
        if($("#password").val() != $("#repeatpassword").val()){
            $("span.errorMessage").html("重複密碼不一致");
            $("div.registerErrorMessageDiv").css("display", "block");           
            return false;
        }       
 
        return true;
    });
})
</script>