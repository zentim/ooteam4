<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="../include/admin/adminHeader.jsp"%>
<%@include file="../include/admin/adminNavigator.jsp"%>

<nav aria-label="breadcrumb">
  <ol class="breadcrumb">
    <li class="breadcrumb-item"><a href="admin_category_list">所有分類</a></li>
    <li class="breadcrumb-item"><a href="admin_product_list?cid=${p.category.id}">${p.category.name}</a></li>
    <li class="breadcrumb-item active" aria-current="page">${ p.name }</li>
    <li class="breadcrumb-item active" aria-current="page">編輯產品</li>
    </li>
  </ol>
</nav>

<!-- Edit Product -->
<div class="card" style="width: 23rem;">
  <div class="card-body">
    <h4>Edit Property</h4>

    <form method="post" id="editForm" action="admin_product_update">

  		<div class="input-group mb-3">
			  <div class="input-group-prepend">
			    <span class="input-group-text" id="inputGroup-sizing-default">ProductName</span>
			  </div>
			  <input 
			  	id="name"
			  	name="name"
			  	type="text" 
			  	class="form-control" 
			  	aria-label="Sizing example input" 
			  	aria-describedby="inputGroup-sizing-default">
			</div>
  		
  			<div class="input-group mb-3">
			  <div class="input-group-prepend">
			    <span class="input-group-text" id="inputGroup-sizing-default">Price</span>
			  </div>
			  <input 
			  	id="price"
              	name="price"
			  	type="text" 
			  	class="form-control" 
			  	aria-label="Sizing example input" 
			  	aria-describedby="inputGroup-sizing-default">
			</div>
  			
  			<div class="input-group mb-3">
			  <div class="input-group-prepend">
			    <span class="input-group-text" id="inputGroup-sizing-default">Inventory</span>
			  </div>
			  <input 
			  	id="inventory"
              	name="inventory"
			  	type="text" 
			  	class="form-control" 
			  	aria-label="Sizing example input" 
			  	aria-describedby="inputGroup-sizing-default">
			</div>
  		
  		
			<input type="hidden" name="id" value="${ p.id }">
			<input type="hidden" name="cid" value="${ p.category.id }">
			<button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button>
				

  	</form>

  </div>
</div>

<script>
$(function() {
	$("#editForm").submit(function() {
		if (!checkEmpty("name", "產品名稱")) {
			return false;
		}
		if (!checkNumber("price", "原價")) {
			return false;
		}
		if (!checkInt("inventory", "庫存")) {
			return false;
		}
		return true;
	})
})
</script>

<%@include file="../include/admin/adminFooter.jsp"%>
