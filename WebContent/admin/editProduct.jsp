<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="../include/admin/adminHeader.jsp"%>
<%@include file="../include/admin/adminNavigator.jsp"%>

<nav aria-label="breadcrumb">
  <ol class="breadcrumb">
    <li class="breadcrumb-item"><a href="admin_segment_list">All Segment</a></li>
    <li class="breadcrumb-item"><a href="admin_category_list?cid=${ s.id }">${ s.name }</a></li>
    <li class="breadcrumb-item"><a href="admin_brand_list?cid=${ c.id }">${ c.name }</a></li>
    <li class="breadcrumb-item"><a href="admin_product_list?cid=${p.brand.id}">${p.brand.name}</a></li>
    <li class="breadcrumb-item active" aria-current="page">${ p.name }</li>
    <li class="breadcrumb-item active" aria-current="page">Edit Product</li>
    </li>
  </ol>
</nav>

<!-- Edit Product -->
<div class="card" style="width: 23rem;">
  <div class="card-body">
    <h4>Edit Product</h4>

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
			  	aria-describedby="inputGroup-sizing-default"
			  	value="${ p.name }">
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
			  	aria-describedby="inputGroup-sizing-default"
			  	value="${ p.price }">
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
			  	aria-describedby="inputGroup-sizing-default"
			  	value="${ p.inventory }">
			</div>
  		
  		
			<input type="hidden" name="id" value="${ p.id }">
			<input type="hidden" name="cid" value="${ p.brand.id }">
			<button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button>
				

  	</form>

  </div>
</div>

<script>
$(function() {
	$("#editForm").submit(function() {
		if (!checkEmpty("name", "Product Name")) {
			return false;
		}
		if (!checkNumber("price", "Price")) {
			return false;
		}
		if (!checkInt("inventory", "Inventory")) {
			return false;
		}
		return true;
	})
})
</script>

<%@include file="../include/admin/adminFooter.jsp"%>
