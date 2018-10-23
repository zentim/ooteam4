<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="../include/admin/adminHeader.jsp"%>
<%@include file="../include/admin/adminNavigator.jsp"%>


<h2>Brand</h2>
<div class="table-responsive table-bordered table-sm">
  <table class="table table-striped table-sm">
    <thead>
      <tr align="center">
        <th>#</th>
        <th>Brand Name</th>
        <th>Manage Product</th>
        <th>Edit</th>
        <th>Delete</th>
      </tr>
    </thead>

    <tbody>
      <c:forEach items="${thecs}" var="c">

      <tr align="center">
        <td>${ c.id }</td>
        <td>${ c.name }</td>
        <td>
          <a href="admin_product_list?cid=${ c.id }">
            <button
              type="button"
              class="btn btn-primary btn-sm">
              <span data-feather="shopping-cart"></span>
            </button>
          </a>
        </td>
        <td>
          <a href="admin_brand_edit?id=${ c.id }">
            <button
              type="button"
              class="btn btn-primary btn-sm">
              <span data-feather="edit"></span>
            </button>
          </a>
        </td>
        <td>
          <a deleteLink="true" href="admin_brand_delete?id=${c.id}">
            <button
              type="button"
              class="btn btn-danger btn-sm">
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



<!-- Add New Brand -->

<div class="card" style="width: 23rem;">
  <div class="card-body">
    <h4>Add</h4>

    <form
      method="post"
      id="addForm"
      action="admin_brand_add">
  		
  		<div class="input-group mb-3">
		  <div class="input-group-prepend">
		    <span class="input-group-text" id="inputGroup-sizing-default">BrandName</span>
		  </div>
		  <input 
		  	id="name"
		  	name="name"
		  	type="text" 
		  	class="form-control" 
		  	aria-label="Sizing example input" 
		  	aria-describedby="inputGroup-sizing-default">
		</div>
  		
  		<button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button>
  	</form>

  </div>
</div>


<script>
$(function(){

  $("#addForm").submit(function(){
    if(!checkEmpty("name", "分類名稱"))
      return false;
    return true;
  });

});
</script>




<%@include file="../include/admin/adminFooter.jsp"%>
