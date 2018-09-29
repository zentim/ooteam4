<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>

<div class="modal " id="loginModal" tabindex="-1" role="dialog" >
    <div class="modal-dialog loginDivInProductPageModalDiv">
            <div class="modal-content">

				<div class="container" style="width: 100%">
					
					<div class="login-form">
						<div class="main-div" style="width: 400px; margin: 0 auto; text-align: center;">
						    <div class="panel">
						   		<h2>User Login</h2>   
						   		<p>Please enter your account and password</p>
						   	</div>
						   
						    <div>
						
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
						       
						       <button class="btn btn-primary btn-lg loginSubmitButton" style="width: 100px;">Login</button>
						       <p></p>
							   <a href="./signUp.jsp">SignUp</a>
							   <p></p>
						    </div>
					    </div>
					</div>
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
     
    $("form.loginForm").submit(function(){
        if(0 == $("#name").val().length || 0 == $("#password").val().length){
            $("span.errorMessage").html("請輸入帳號密碼");
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