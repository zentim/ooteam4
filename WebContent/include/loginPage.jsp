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
		   
		    <form id="Login" action="LoginController" method="get">
		
		        <div class="form-group">
		            <input type="text" name="account" class="form-control" id="account" placeholder="Account">
		        </div>
		
		        <div class="form-group">
		            <input type="password"  name="password" class="form-control" id="password" placeholder="Password">
		        </div>
		       
		       <button type="submit" class="btn btn-primary btn-lg" style="width: 100px;">Login</button>
		       <p></p>
			   <a href="./signUp.jsp">SignUp</a>
		    </form>
	    </div>
	</div>
</div>