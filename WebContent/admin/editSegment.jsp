<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="../include/admin/adminHeader.jsp"%>
<%@include file="../include/admin/adminNavigator.jsp"%>

<nav aria-label="breadcrumb">
  <ol class="breadcrumb">
    <li class="breadcrumb-item"><a href="admin_segment_list">All Segment</a></li>
    <li class="breadcrumb-item active" aria-current="page">${ s.name }</li>
    <li class="breadcrumb-item active" aria-current="page">Edit Segment</li>
  </ol>
</nav>

<!-- Edit Segment -->
<div class="card" style="width: 23rem;">
  <div class="card-body">
    <h4>Edit Segment</h4>

    <form
      method="post"
      id="addForm"
      action="admin_segment_update">

  		<table class="addTable">
  			<tr>
  				<td>Segment Name : </td>
  				<td>
            <input
              id="name"
              name="name"
              value="${ s.name }"
              type="text"
              class="form-control">
          </td>
  			</tr>
  			<tr>
  				<td colspan="2" align="center">
  					<input
              type="hidden"
              name="id"
              value="${ s.id }">
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
