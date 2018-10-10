<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="../include/admin/adminHeader.jsp"%>
<%@include file="../include/admin/adminNavigator.jsp"%>

<nav aria-label="breadcrumb">
  <ol class="breadcrumb">
    <li class="breadcrumb-item"><a href="admin_category_list">所有分類</a></li>
    <li class="breadcrumb-item"><a href="admin_product_list?cid=${c.id}">${c.name}</a></li>
    <li class="breadcrumb-item active" aria-current="page">產品管理</li>
  </ol>
</nav>

<div class="table-responsive table-bordered table-sm">
  <table class="table table-striped table-sm">
    <thead>
      <tr align="center">
        <th>#</th>
        <th>ProductImage</th>
        <th>ProductName</th>
        <th>Price</th>
        <th>Inventory</th>
        <th>ImageManage</th>
        <th>ProductDetailSetting</th>
        <th>DiscountSetting</th>
        <th>Edit</th>
        <th>Delete</th>
      </tr>
    </thead>

    <tbody>
      <c:forEach items="${ps}" var="p">
      <tr align="center">
        <td>${ p.id }</td>
        <td>
	        <c:if test="${ !empty p.firstProductImage }">
	        	<img width="40px" src="img/productSingle/${ p.firstProductImage.id }.jpg">
	        </c:if>
        </td>
        <td>${ p.name }</td>
        <td>${ p.price }</td>
        <td>${ p.inventory }</td>
        <td>
          <a href="admin_productImage_list?pid=${ p.id }">
            <button type="button" class="btn btn-primary btn-sm">
              <span data-feather="image"></span>
            </button>
          </a>
        </td>
        <td>
          <a href="admin_product_editProductDetail?id=${ p.id }">
            <button type="button" class="btn btn-primary btn-sm">
              <span data-feather="list"></span>
            </button>
          </a>
        </td>
        <td>
          <a href="admin_product_editPromotionItem?id=${ p.id }">
            <button type="button" class="btn btn-primary btn-sm">
              <span data-feather="edit"></span>
            </button>
          </a>
        </td>
        <td>
          <a href="admin_product_edit?id=${ p.id }">
            <button type="button" class="btn btn-primary btn-sm">
              <span data-feather="edit"></span>
            </button>
          </a>
        </td>
        <td>
          <a deleteLink="true" href="admin_product_delete?id=${ p.id }">
            <button type="button" class="btn btn-danger btn-sm">
              <span data-feather="trash-2"></span>
            </button>
          </a>
        </td>
      </tr>
      </c:forEach>
    </tbody>
  </table>
</div>




<!-- Pagination -->
<div>
	<%@include file="../include/admin/adminPage.jsp" %>
</div>



<!-- Add New Product -->

<div class="card" style="width: 23rem;">
  <div class="card-body">
    <h4>Add</h4>

    <form method="post" id="addForm" action="admin_product_add">
  		
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
  			
  			<input type="hidden" name="cid" value="${ c.id }">
  			<button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button>
  			
  		
  	</form>

  </div>
</div>


<script>
$(function(){

  $("#addForm").submit(function() {
    if(!checkEmpty("name", "ProductName")) {
    	return false;
    }
    if(!checkNumber("price", "Price")) {
    	return false;
    }
    if(!checkInt("inventory", "Inventory")) {
    	return false;
    }

    return true;
  });

});
</script>




<%@include file="../include/admin/adminFooter.jsp"%>
