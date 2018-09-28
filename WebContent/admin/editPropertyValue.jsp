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
    <li class="breadcrumb-item active" aria-current="page">編輯產品屬性值</li>
  </ol>
</nav>

<!-- Edit PropertyValue -->
<div class="card" style="width: 23rem;">
  <div class="card-body">
    <h4>Edit Property</h4>

    <div class="editPVDiv">
        <c:forEach items="${pvs}" var="pv">
            <div class="eachPV">
                <span class="pvName" >${pv.property.name}</span>
                <span class="pvValue"><input class="pvValue" pvid="${pv.id}" type="text" value="${pv.value}"></span>
            </div>
        </c:forEach>
        <div style="clear:both"></div>  
    </div>

  </div>
</div>

<script>
$(function() {
    $("input.pvValue").keyup(function(){
    	console.log("input keyup!!!");
        var value = $(this).val();
        var page = "admin_product_updatePropertyValue";
        var pvid = $(this).attr("pvid");
        var parentSpan = $(this).parent("span");
        parentSpan.css("border","1px solid yellow");
        $.post(
                page,
                {"value":value,"pvid":pvid},
                function(result){
                    if("success"==result)
                        parentSpan.css("border","1px solid green");
                    else
                        parentSpan.css("border","1px solid red");
                }
            );      
    });
});
</script>

<%@include file="../include/admin/adminFooter.jsp"%>