<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix='c'%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix='fmt'%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix='fn'%>


<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="description" content="">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- The above 4 meta tags *must* come first in the head; any other head content must come *after* these tags -->

    <!-- Title  -->
    <title>OO Team4</title>

    <!-- Favicon  -->
    <link rel="icon" href="img/core-img/favicon.ico">

    <!-- Core Style CSS -->
    <link rel="stylesheet" href="css/core-style.css">
    <link rel="stylesheet" href="style.css">
    
    <!-- Font Awesome -->
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.4.1/css/all.css" integrity="sha384-5sAR7xN1Nv6T6+dT2mhtzEpVJvfS3NScPQTrOxhwjIuvcA67KV2R5Jz6kr4abQsz" crossorigin="anonymous">

	<!-- jQuery -->
	<script src="js/jquery/jquery-2.2.4.min.js"></script>
<!-- 
	<script
	  src="https://code.jquery.com/jquery-3.3.1.min.js"
	  integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8="
	  crossorigin="anonymous"></script>
 -->
 
 	<!-- Style -->
 	<style type="text/css">
 	.hidden {
 		display: none;
 	}
 	</style>
 
 
  
	<!-- Format -->
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
    <!-- ##### Header Area Start ##### -->
    <header class="header_area">
        <div class="classy-nav-container breakpoint-off d-flex align-items-center justify-content-between">
            <!-- Classy Menu -->
            <nav class="classy-navbar" id="essenceNav">
                <!-- Logo -->
                <a class="nav-brand" href="forehome">OO Team4</a>
                <!-- Navbar Toggler -->
                <div class="classy-navbar-toggler">
                    <span class="navbarToggler"><span></span><span></span><span></span></span>
                </div>
                <!-- Menu -->
                <div class="classy-menu">
                    <!-- close btn -->
                    <div class="classycloseIcon">
                        <div class="cross-wrap"><span class="top"></span><span class="bottom"></span></div>
                    </div>
                    <!-- Nav Start -->
                    <div class="classynav">
                        <ul>
                            <li><a href="#">Shop</a>
                                <div class="megamenu">
                                
                                
                                
                                	<c:forEach items="${ segments }" var="segment" varStatus="stSegment">
	                                    <ul class="single-mega cn-col-4">
	                                        <li class="title">${ segment.name }</li>
	                                        <c:forEach items="${ segment.categorys }" var="c" varStatus="st">
	                                        	<li><a href="forecategory?cid=${ c.id }">${ c.name }</a></li>
	                                        </c:forEach>
	                                    </ul>
									</c:forEach>
                                    
                                    
                                    
                                    
                                    <div class="single-mega cn-col-4">
                                        <img src="img/bg-img/bg-6.jpg" alt="">
                                    </div>
                                </div>
                            </li>
                            <li><a href="#">Pages</a>
                                <ul class="dropdown">
                                    <li><a href="forehome">Home</a></li>
                                    <li><a href="forecategory?cid=1">Shop</a></li>
                                    <li><a href="foreproduct?pid=1">Product Details</a></li>
                                    <li><a href="forebuy">Checkout</a></li>
                                    <li><a href="blog.html">Blog</a></li>
                                    <li><a href="single-blog.html">Single Blog</a></li>
                                    <li><a href="regular-page.html">Regular Page</a></li>
                                    <li><a href="contact.html">Contact</a></li>
                                </ul>
                            </li>
                            <li><a href="blog.html">Blog</a></li>
                            <li><a href="contact.html">Contact</a></li>
                        </ul>
                    </div>
                    <!-- Nav End -->
                </div>
            </nav>

            <!-- Header Meta Data -->
            <div class="header-meta d-flex clearfix justify-content-end">

                <!-- Search Area -->
<!-- 
                <div class="search-area">
                    <form action="#" method="post">
                        <input type="search" name="search" id="headerSearch" placeholder="Type for search">
                        <button type="submit"><i class="fa fa-search" aria-hidden="true"></i></button>
                    </form>
                </div>
 -->
 				<c:if test="${!empty user}">
	                <!-- Favourite Area -->
	                <div class="favourite-area">
	                    <a href="foresubscribe"><img src="img/core-img/heart.svg" alt=""></a>
	                </div>
	                <!-- Orders Area -->
	                <div class="favourite-area">
	                    <a href="forebought"><i class="fas fa-clipboard-list fa-lg"></i></a>
	                </div>
                </c:if>
                <!-- Cart Area -->
                <div class="cart-area">
                    <a href="forecart" id="essenceCartBtn"><i class="fas fa-shopping-cart fa-lg"></i> <span>${cartTotalItemNumber}</span></a>
                </div>
                <!-- User Login Info -->
                <c:if test="${empty user}">
	                <div class="user-login-info">
	                    <a href="login.jsp">Login</a>
	                </div>
                </c:if>
                <c:if test="${!empty user}">
                	<div class="user-login-info">
                		<a href="#" style="width: 200px;">Hi! ${ user.email }</a>
	                </div>
	                <div class="user-login-info">
	                    <a href="forelogout">Logout</a>
	                </div>
                </c:if>
            </div>

        </div>
    </header>
    <!-- ##### Header Area End ##### -->

    <!-- ##### Right Side Cart Area ##### -->
<!--     
    <div class="cart-bg-overlay"></div>

    <div class="right-side-cart-area">
-->
        <!-- Cart Button -->
<!--         
        <div class="cart-button">
            <a href="#" id="rightSideCart"><img src="img/core-img/bag.svg" alt=""> <span>3</span></a>
        </div>

        <div class="cart-content d-flex">
-->
            <!-- Cart List Area -->
<!--              
            <div class="cart-list">
-->            
                <!-- Single Cart Item -->
<!--                 
                <div class="single-cart-item">
                    <a href="#" class="product-image">
                        <img src="img/product-img/product-1.jpg" class="cart-thumb" alt="">
-->                        
                        <!-- Cart Item Desc -->
<!--                        
                        <div class="cart-item-desc">
                          <span class="product-remove"><i class="fa fa-close" aria-hidden="true"></i></span>
                            <span class="badge">Mango</span>
                            <h6>Button Through Strap Mini Dress</h6>
                            <p class="size">Size: S</p>
                            <p class="color">Color: Red</p>
                            <p class="price">$45.00</p>
                        </div>
                    </a>
                </div>
-->
                <!-- Single Cart Item -->
<!--                 
                <div class="single-cart-item">
                    <a href="#" class="product-image">
                        <img src="img/product-img/product-2.jpg" class="cart-thumb" alt="">
-->                        
                        <!-- Cart Item Desc -->
<!--                        
                        <div class="cart-item-desc">
                          <span class="product-remove"><i class="fa fa-close" aria-hidden="true"></i></span>
                            <span class="badge">Mango</span>
                            <h6>Button Through Strap Mini Dress</h6>
                            <p class="size">Size: S</p>
                            <p class="color">Color: Red</p>
                            <p class="price">$45.00</p>
                        </div>
                    </a>
                </div>
-->
                <!-- Single Cart Item -->
<!--                 
                <div class="single-cart-item">
                    <a href="#" class="product-image">
                        <img src="img/product-img/product-3.jpg" class="cart-thumb" alt="">
-->                        
                        <!-- Cart Item Desc -->
<!--                        
                        <div class="cart-item-desc">
                          <span class="product-remove"><i class="fa fa-close" aria-hidden="true"></i></span>
                            <span class="badge">Mango</span>
                            <h6>Button Through Strap Mini Dress</h6>
                            <p class="size">Size: S</p>
                            <p class="color">Color: Red</p>
                            <p class="price">$45.00</p>
                        </div>
                    </a>
                </div>
            </div>
-->
            <!-- Cart Summary -->
<!--            
            <div class="cart-amount-summary">

                <h2>Summary</h2>
                <ul class="summary-table">
                    <li><span>subtotal:</span> <span>$274.00</span></li>
                    <li><span>delivery:</span> <span>Free</span></li>
                    <li><span>discount:</span> <span>-15%</span></li>
                    <li><span>total:</span> <span>$232.00</span></li>
                </ul>
                <div class="checkout-btn mt-100">
                    <a href="checkout.html" class="btn essence-btn">check out</a>
                </div>
            </div>
        </div>
    </div>
-->    
    <!-- ##### Right Side Cart End ##### -->