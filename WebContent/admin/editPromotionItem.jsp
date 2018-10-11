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
    <li class="breadcrumb-item active" aria-current="page">Edit PromotionItem</li>
    </li>
  </ol>
</nav>


<!-- Edit PromotionItem -->
<div class="card" style="width: 23rem;">
  <div class="card-body">
    <h4>Edit PromotionItem</h4>

    

  			<div class="form-group">
            <div class="input-group mb-3">
			  <div class="input-group-prepend">
			    <label class="input-group-text" for="inputGroupSelect01">Promotion</label>
			  </div>
			  <select class="custom-select" id="promotion">
			  	<option value="-1" selected>Choose...</option>
			    <c:forEach items="${ps}" var="promotion">	
			    	<c:if test="${ pi.promotion.id != promotion.id }">
			    		<option value="${ promotion.id }">${ promotion.name } - (${ promotion.discountType })</option>
			    	</c:if>
			    	<c:if test="${ pi.promotion.id == promotion.id }">
			    		<option selected value="${ promotion.id }">${ promotion.name } - (${ promotion.discountType })</option>
			    	</c:if>
			    </c:forEach>
			  </select>
			</div>
		   </div>
  		
  			<div class="input-group mb-3">
			  <div class="input-group-prepend">
			    <span class="input-group-text" id="inputGroup-sizing-default">minQuantity</span>
			  </div>
			  <input 
			  	id="minQuantity"
              	name="minQuantity"
			  	type="text" 
			  	class="form-control" 
			  	aria-label="Sizing example input" 
			  	aria-describedby="inputGroup-sizing-default"
			  	value="${ pi.minQuantity }">
			</div>
  			
  			<div class="input-group mb-3">
			  <div class="input-group-prepend">
			    <span class="input-group-text" id="inputGroup-sizing-default">discountOf(%)</span>
			  </div>
			  <input 
			  	id="discountOf"
              	name="discountOf"
			  	type="text" 
			  	class="form-control" 
			  	aria-label="Sizing example input" 
			  	aria-describedby="inputGroup-sizing-default"
			  	value="${ pi.discountOf }">
			</div>
  		
  		
			<input type="hidden" name="productId" id="productId" value="${ p.id }">
			<input type="hidden" name="categoryId" id="categoryId" value="${ p.category.id }">
			<input type="hidden" name="promotionItemId" id="promotionItemId" value="${ pi.id }">
			<button class="btn btn-lg btn-primary btn-block" id="create_promotionItem_btn">Submit</button>

  </div>
</div>

<script>
$(document).ready(function(){
    $("#create_promotionItem_btn").click(function(){
    	var categoryId = $("#categoryId").val();
        var productId = $("#productId").val();
        var promotionItemId = $("#promotionItemId").val();
    	var promotionId = $("#promotion").val();
        var minQuantity = $("#minQuantity").val();
        var discountOf = $("#discountOf").val();
        
        if (!checkNumber("minQuantity", "minQuantity")) {
			return false;
		}
        
        if (!checkInt("discountOf", "discountOf")) {
			return false;
		}
		
        if( true ){
	        	  $.ajax({
	                  url: 'admin_promotionItem_update',
	                  type: 'post',
	                  async: false,
	                  data: {
	                	  categoryId: categoryId,
	                	  productId: productId,
	                	  promotionItemId: promotionItemId,
	                	  promotionId: promotionId, 
	                	  minQuantity: minQuantity,
	                	  discountOf: discountOf
	                  },
	                  success: function(result){
	                     /* if(res == 'General customer'||res == 'VIP customer'){
	                      	alert("Login successful!");
	                      	location.reload();
	                      }else if(res == 'admin'||res == 'manager'){
	                      	location.href = './BackEndIndex.jsp';
	                      
	                      */
	                      console.log("success!!!")
	                      if("success" == result){
	                              
	                    	  location.href = './admin_product_list?cid=' + categoryId;
	                         
	                      }else{                    	
	                      	$("#login_password").val('');
	                      	$('#login_notice').html('Invalid email or password!');
	                      	$("#login_notice").css("color", "#ed2553");
	                      }
	                   }
	              })
	        } 
	       	return false;
	    });
});

</script>

<%@include file="../include/admin/adminFooter.jsp"%>
