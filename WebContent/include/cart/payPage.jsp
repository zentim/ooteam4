<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
    
<div>
	<span style="display: block; color: #FF6600; font-weight: bold; font-size: 20px; margin: 10px;">
		$ <fmt:formatNumber type="number" value="${ param.total }" minFractionDigits="2"/>
	</span><br>
	<a href="forepaied?oid=${ param.oid }&total=${ param.total }">
		<button class="btn btn-primary btn-lg" style="width: 120px;">確認支付</button>
	</a>
</div>

