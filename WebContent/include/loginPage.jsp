<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>

<div class="container">
	<h1 class="form-heading" style="color:white">login Form</h1>
	<div class="login-form">
		<div class="main-div" style="width: 400px; margin: 0 auto;">
		    <div class="panel">
		   		<h2>User Login</h2>   
		   		<p>Please enter your account and password</p>
		   	</div>
		   
		    <form class="loginForm" action="forelogin" method="post">
		
				<div class="loginErrorMessageDiv" style="display: none;">
	                <div class="alert alert-danger" role="alert">
	                  <button type="button" class="close" data-dismiss="alert" aria-label="Close"></button>
	                    <span class="errorMessage"></span>
	                </div>        
	            </div>
		
		        <div class="form-group">
		            <input type="text" name="name" class="form-control" id="name" placeholder="Account">
		        </div>
		
		        <div class="form-group">
		            <input type="password"  name="password" class="form-control" id="password" placeholder="Password">
		        </div>
		       
		       <button type="submit" class="btn btn-primary btn-lg" style="width: 100px;">Login</button>
		       <p></p>
			   <a href="./register.jsp">SignUp</a>
			   <p></p>
		    </form>
	    </div>
	</div>
</div>

<script>
$(function(){
     
    <c:if test="${!empty msg}">
	    $("span.errorMessage").html("${msg}");
	    $("div.loginErrorMessageDiv").show();       
    </c:if>
     
    $("form.loginForm").submit(function(){
        if(0 == $("#name").val().length || 0 == $("#password").val().length){
            $("span.errorMessage").html("Please input your account and password");
            $("div.loginErrorMessageDiv").show();           
            return false;
        }
        return true;
    });
     
    $("form.loginForm input").keyup(function(){
        $("div.loginErrorMessageDiv").hide();   
    });
     
})
</script>