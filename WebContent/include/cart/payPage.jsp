<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
    
<div class="container">
	<div class="row justify-content-center">
	
	
<div class="card" style="width: 18rem; text-align: center; margin: 100px;">
  <span style="display: block; color: #FF6600; font-weight: bold; font-size: 34px; margin: 10px;">
	$ <fmt:formatNumber type="number" value="${ param.total }" minFractionDigits="2"/>
	</span><br>
  <div class="card-body">
    <a href="forepaied?oid=${ param.oid }&total=${ param.total }">
		<button class="btn btn-primary btn-lg">Pay By ${ param.paymentOption }</button>
	</a>
  </div>
</div>
	
	




	</div>
</div>