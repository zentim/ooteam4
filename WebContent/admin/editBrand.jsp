<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="../include/admin/adminHeader.jsp"%>
<%@include file="../include/admin/adminNavigator.jsp"%>

<nav aria-label="breadcrumb">
  <ol class="breadcrumb">
    <li class="breadcrumb-item"><a href="admin_brand_list">All Brand</a></li>
    <li class="breadcrumb-item active" aria-current="page">Edit Brand</li>
  </ol>
</nav>

<!-- Edit Category -->
<div class="card" style="width: 23rem;">
  <div class="card-body">
    <h4>Edit Category</h4>

    <form
      method="post"
      id="addForm"
      action="admin_brand_update">

  		<table class="addTable">
  			<tr>
  				<td>Brand Name : </td>
  				<td>
            <input
              id="name"
              name="name"
              value="${ c.name }"
              type="text"
              class="form-control">
          </td>
  			</tr>
  			<tr>
  				<td colspan="2" align="center">
  					<input
              type="hidden"
              name="id"
              value="${ c.id }">
  					<button
              class="btn btn-lg btn-primary btn-block"
              type="submit">Submit</button>
  				</td>
  			</tr>
  		</table>

  	</form>

  </div>
</div>

<%@include file="../include/admin/adminFooter.jsp"%>
