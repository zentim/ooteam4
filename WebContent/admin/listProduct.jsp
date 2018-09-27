<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="../component/admin/adminHeader.jsp"%>
<%@include file="../component/admin/adminNavigator.jsp"%>	

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
      <tr>
        <th>#</th>
        <th>圖片</th>
        <th>產品名稱</th>
        <th>原價</th>
        <th>優惠價</th>
        <th>庫存數量</th>
        <th>圖片管理</th>
        <th>設置屬性</th>
        <th>編輯</th>
        <th>刪除</th>
      </tr>
    </thead>
    
    <tbody>
      <c:forEach items="${ps}" var="p">
      <tr>
        <td>${ p.id }</td>
        <td>
	        <c:if test="${ !empty p.firstProductImage }">
	        	<img width="40px" src="img/productSingle/${ p.firstProductImage.id }.jpg">
	        </c:if>
        </td>
        <td>${ p.name }</td>
        <td>${ p.originalPrice }</td>
        <td>${ p.promotePrice }</td>
        <td>${ p.stock }</td>
        <td>
          <a href="admin_productImage_list?pid=${ p.id }">
            <button type="button" class="btn btn-primary btn-sm">
              <span data-feather="image"></span>
            </button>
          </a>
        </td>
        <td>
          <a href="admin_product_editPropertyValue?id=${ p.id }">
            <button type="button" class="btn btn-primary btn-sm">
              <span data-feather="list"></span>
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
	<%@include file="../component/admin/adminPage.jsp" %>
</div>

        
	
<!-- Add New Product -->

<div class="card" style="width: 23rem;">
  <div class="card-body">
    <h4>Add New Product</h4>

    <form method="post" id="addForm" action="admin_product_add">
  		<table>
  			<tr>
  				<td>產品名稱 : </td>
  				<td><input id="name" name="name" type="text"></td>
  			</tr>
  			<tr>
  				<td>原價 : </td>
  				<td><input id="originalPrice" name="originalPrice" type="text"></td>
  			</tr>
  			<tr>
  				<td>優惠價 : </td>
  				<td><input id="promotePrice" name="promotePrice" type="text"></td>
  			</tr>
  			<tr>
  				<td>庫存 : </td>
  				<td><input id="stock" name="stock" type="text"></td>
  			</tr>
  			<tr>
  				<td colspan="2" align="center">
  					<input type="hidden" name="cid" value="${ c.id }">
  					<button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button>
  				</td>
  			</tr>
  		</table>
  	</form>

  </div>
</div>

  
<script>
$(function(){

  $("#addForm").submit(function(){
    if(!checkEmpty("name", "產品名稱")) {
    	return false;
    }
    if(!checkNumber("originalPrice", "原價")) {
    	return false;
    }
    if(!checkNumber("promotePrice", "優惠價")) {
    	return false;
    }
    if(!checkInt("stock", "庫存")) {
    	return false;
    }
    return true;
  });
  
});
</script>
	



<%@include file="../component/admin/adminFooter.jsp"%>