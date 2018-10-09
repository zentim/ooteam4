<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>

<div style="background-color: #ECFFDC; height: 61px; padding: 17px 0px 0px 25px;">
	<h4>您已成功付款</h4>
</div>

<div>
	<ul>
         <li>收貨地址: ${o.address} </li>
         <li>實付款: <span class="payedInfoPrice">
       		$ <fmt:formatNumber type="number" value="${param.total}" minFractionDigits="2"/>
         </li>
         <li>預計 24 小時內送達</li>
     </ul>
              
     <div class="paedCheckLinkDiv">
     	您可以
		<a class="payedCheckLink" href="forebought">查看已買到的商品</a>
		<a class="payedCheckLink" href="forebought">查看交易詳情</a>
     </div>
</div>