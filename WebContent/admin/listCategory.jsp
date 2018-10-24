<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="../include/admin/adminHeader.jsp"%>
<%@include file="../include/admin/adminNavigator.jsp"%>


<nav aria-label="breadcrumb">
  <ol class="breadcrumb">
    <li class="breadcrumb-item"><a href="admin_segment_list">All Segment</a></li>
    <li class="breadcrumb-item active" aria-current="page">${c.name}</li>
    <li class="breadcrumb-item active" aria-current="page">Manage Category</li>
  </ol>
</nav>

<div class="table-responsive table-bordered table-sm">
  <table class="table table-striped table-sm">
    <thead>
      <tr align="center">
        <th>#</th>
        <th>Category Name</th>
        <th>Manage Brand</th>
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
          <a href="admin_brand_list?cid=${ c.id }">
            <button
              type="button"
              class="btn btn-primary btn-sm">
              <span data-feather="shopping-cart"></span>
            </button>
          </a>
        </td>
        <td>
          <a href="admin_category_edit?id=${ c.id }">
            <button
              type="button"
              class="btn btn-primary btn-sm">
              <span data-feather="edit"></span>
            </button>
          </a>
        </td>
        <td>
          <a deleteLink="true" href="admin_category_delete?id=${c.id}">
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



<!-- Add New Category -->

<div class="card" style="width: 23rem;">
  <div class="card-body">
    <h4>Add</h4>

    <form
      method="post"
      id="addForm"
      action="admin_category_add">
  		
  		<div class="input-group mb-3">
		  <div class="input-group-prepend">
		    <span class="input-group-text" id="inputGroup-sizing-default">CategoryName</span>
		  </div>
		  <input 
		  	id="name"
		  	name="name"
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

  $("#addForm").submit(function(){
    if(!checkEmpty("name", "Category Name"))
      return false;
    return true;
  });

});
</script>




<%@include file="../include/admin/adminFooter.jsp"%>
