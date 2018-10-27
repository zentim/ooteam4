<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
    
<div style="background-color: #ECFFDC; height: 61px; padding: 17px 0px 0px 0px; text-align: center">
  <h4>Successful Payment</h4>
</div>    

<div class="container">
	<div class="row justify-content-center">
	
	
	
	
	
<div class="card" style="width: 18rem; margin: 100px;">
  
  
  <div class="card-body">
  	<p class="card-text">
	  	Address : ${o.address}<br>
	  	Payment : $ <fmt:formatNumber type="number" value="${param.total}" minFractionDigits="2"/>
	</p>
    <a class="card-link" href="forebought" style="color: #007bff; font-size: 16px;">Go to My Orders</a>
  </div>
</div>
	
	




	</div>
</div>