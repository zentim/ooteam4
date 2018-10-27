<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<div class="top_catagory_area section-padding-80 clearfix">
        <div class="container">
            <div class="row justify-content-center">

    
<form method="post" action="foreregister" id="registerForm" style="width: 100%; max-width: 420px; padding: 15px; margin: auto;">
	<div class="registerErrorMessageDiv" style="display: none;">
        <div class="alert alert-danger" role="alert">
          <button type="button" class="close" data-dismiss="alert" aria-label="Close"></button>
            <span class="errorMessage"></span>
        </div>        
    </div>

  <div class="text-center mb-4">
    <i class="fas fa-users fa-5x mb-4"></i>
    <h1 class="h3 mb-3 font-weight-normal">Register</h1>
    <span style="color: #777">Please enter your email and password</span>
  </div>

  <div class="form-label-group mb-3">
    <input type="email" id="inputEmail" name="email" class="form-control" placeholder="Email address" required autofocus>
  </div>

  <div class="form-label-group mb-3">
    <input 
    	type="password" 
    	id="inputPassword" 
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
    	id="repeatPassword" 
    	name="repeatPassword" 
    	class="form-control" 
    	placeholder="Input your password again"
    	pattern=".{8,15}"
    	title="8 to 15 characters" 
    	required>
  </div>

  
  <button class="btn btn-lg btn-primary btn-block" type="submit">Register</button>
  <a href="login.jsp">
  	<p class="mt-5 mb-3 text-muted text-center">Login?</p>
  </a>
</form>
 
 
 		</div>
 	</div>
 </div>
 
 
 
 
<script>
$(function(){
     
    <c:if test="${!empty msg}">
	    $("span.errorMessage").html("${msg}");
	    $("div.registerErrorMessageDiv").css("display","block");       
    </c:if>
     
})
</script>