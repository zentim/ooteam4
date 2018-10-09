<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix='c'%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix='fmt'%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix='fn'%>


<!DOCTYPE html>
<html>

<head>
    <title>ooteam4</title>
    <meta name="viewport" content="width=device-width, initial-scale=1" charset="UTF-8">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="./css/fore/index.css">
    <link rel="stylesheet" href="./css/fore/lightBox.css">
    <link rel="stylesheet" href="./css/fore/login.css">
    
    <script>
	function formatMoney(num){
	    num = num.toString().replace(/\$|\,/g,'');  
	    if(isNaN(num))  
	        num = "0";  
	    sign = (num == (num = Math.abs(num)));  
	    num = Math.floor(num*100+0.50000000001);  
	    cents = num%100;  
	    num = Math.floor(num/100).toString();  
	    if(cents<10)  
	    cents = "0" + cents;  
	    for (var i = 0; i < Math.floor((num.length-(1+i))/3); i++)  
	    num = num.substring(0,num.length-(4*i+3))+','+  
	    num.substring(num.length-(4*i+3));  
	    return (((sign)?'':'-') + num + '.' + cents);  
	}
	function checkEmpty(id, name){
	    var value = $("#"+id).val();
	    if(value.length==0){
	         
	        $("#"+id)[0].focus();
	        return false;
	    }
	    return true;
	}
	 
	$(function(){
	 
	    $("a.productDetailTopReviewLink").click(function(){
	        $("div.productReviewDiv").show();
	        $("div.productDetailDiv").hide();
	    });
	    $("a.productReviewTopPartSelectedLink").click(function(){
	        $("div.productReviewDiv").hide();
	        $("div.productDetailDiv").show();       
	    });
	     
	    $("span.leaveMessageTextareaSpan").hide();
	    $("img.leaveMessageImg").click(function(){
	         
	        $(this).hide();
	        $("span.leaveMessageTextareaSpan").show();
	        $("div.orderItemSumDiv").css("height","100px");
	    });
	     
	    $("a.wangwanglink").click(function(){
	        alert("display only");
	    });
	    $("a.notImplementLink").click(function(){
	        alert("Not Implement Function!!!");
	    });
	     
	});
	 
	</script> 
	
</head>

<body>
 <!-- left Button !-->   
 <a class="fa fa-heart round" style="top: 60%;color:white;font-size:24px;text-decoration:none;" href="subscribe.jsp"></a>
	 <a class="fa fa-shopping-cart round" style="top: 70%;color:white;font-size:24px;text-decoration:none;" href="shopCart.jsp"></a>
	<!--<a class="fa fa-cart-plus round" style="top: 70%;color:white;font-size:24px;text-decoration:none;" href="#"></a>!-->
	 <a class="fa fa-chevron-circle-up round" style="top: 80%;color:white;font-size:24px;text-decoration:none;" href="#top_anchor" ></a>
  <!-- left Button !-->   
    <nav class="navbar navbar-inverse">
	    <a name="top_anchor"></a>
	    <div class="container-fluid">
	    
	        <div class="navbar-header">
	            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
	                <span class="icon-bar"></span>
	                <span class="icon-bar"></span>
	                <span class="icon-bar"></span>
	            </button>
	            <a class="navbar-brand" href="forehome">OO Team4</a>
	        </div>
	        
	        <div class="collapse navbar-collapse" id="myNavbar">
	            <ul class="nav navbar-nav">
	                <li class="active"><a href="forehome">Home</a></li>
	                <li><a href="#">About</a></li>
	                <li><a href="#">Projects</a></li>
	                <li><a href="#">Contact</a></li>
	            </ul>
	            <ul class="nav navbar-nav navbar-right">
	            	<c:if test="${!empty user}">
			            <li><a href="forebought">My Orders</a></li>
		            	<li>
				            <a href="forecart">
				            	<span style="margin:0px" class=" glyphicon glyphicon-shopping-cart"></span>
				            	Shopping Cart <strong>(${cartTotalItemNumber})</strong>
				            </a>
				        </li>  
			        </c:if>
			        
			        <c:if test="${empty user}">
			            <li><a onclick="openModal()" class="userLoginLink">My Orders</a></li>
		            	<li>
				            <a onclick="openModal()"  class="userLoginLink">
				            	<span style="margin:0px" class=" glyphicon glyphicon-shopping-cart"></span>
				            	Shopping Cart <strong>(${cartTotalItemNumber})</strong>
				            </a>
				        </li>  
			        </c:if>
	              
	            	<c:if test="${!empty user}">
			            <li><a href="forepersonalDetail">${user.email}</a></li>
			            <li><a href="forelogout">Logout</a></li>
			        </c:if>
			         
			        <c:if test="${empty user}">
			        	<li>
			        		<a onclick="openModal()" >Login</a>
			        	</li>
			            <li><a onclick="registerShow()">Register</a></li>     
			        </c:if>
	                
	            </ul>
	        </div>
	    </div>
	</nav>
    
    <div class="container-fluid text-center"> 
    	
		
    	