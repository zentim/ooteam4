<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="../include/admin/adminHeader.jsp"%>
<%@include file="../include/admin/adminNavigator.jsp"%>


<h2>Edit Promotion</h2>

<!-- Add New Discount -->
<div class="card" style="width: 23rem;">
  <div class="card-body">
    		<h4>Promotion Name</h4>

  			<div class="input-group mb-3">
			  <div class="input-group-prepend">
			    <span class="input-group-text" id="inputGroup-sizing-default">DiscountName</span>
			  </div>
			  <input 
			  	id="discount_name"
			  	name="discount_name"
			  	type="text" 
			  	class="form-control" 
			  	aria-label="Sizing example input" 
			  	aria-describedby="inputGroup-sizing-default"
			  	value="${ promotion.name }">
			</div>



			
			<h4>Promotion Time</h4>
  			
  			<div class="container">
			    <div class="row">
			        <div>
			        	From: 
			            <div class="form-group">
			                <div class='input-group date' id='datetimepicker1'>
			                    <input type='text' class="form-control" name="dateFrom" id="date_from" value="${ dateFrom }"/>
			                    <span class="input-group-addon">
			                        <span class="glyphicon glyphicon-calendar"></span>
			                    </span>
			                </div>
			            </div>
			        </div>
			        <script type="text/javascript">
			            $(function () {
			                $('#datetimepicker1').datetimepicker({
			                	autoclose: true,
			                	todayBtn: true
			                });
			            });
			        </script>
			    </div>
			    <div class="row">
			        <div>
			        	To: 
			            <div class="form-group">
			                <div class='input-group date' id='datetimepicker2'>
			                    <input type='text' class="form-control" name="date_to" id="date_to" value="${ dateTo }"/>
			                    <span class="input-group-addon">
			                        <span class="glyphicon glyphicon-calendar"></span>
			                    </span>
			                </div>
			            </div>
			        </div>
			        <script type="text/javascript">
			            $(function () {
			                $('#datetimepicker2').datetimepicker({
			                	autoclose: true,
			                	todayBtn: true
			                });
			            });
			        </script>
			    </div>
			</div>
  			
  			
  			
  			
  			
  			
  			<h4>Discount Rule</h4>
  			
  			<div class="form-group">
            <div class="input-group mb-3">
			  <div class="input-group-prepend">
			    <label class="input-group-text" for="inputGroupSelect01">DiscountType</label>
			  </div>
			  <select class="custom-select" id="discount_type">
			  	<option value="0" selected>Choose...</option>
			  	<c:if test="${ promotion.discountType == 1 }">
			  		<option value="1" selected>productSet</option>
			  	</c:if>
			  	<c:if test="${ promotion.discountType != 1 }">
			  		<option value="1">productSet</option>
			  	</c:if>
			  	<c:if test="${ promotion.discountType == 2 }">
			  		<option value="2" selected>eachGroupOfN</option>
			  	</c:if>
			  	<c:if test="${ promotion.discountType != 2 }">
			  		<option value="2">eachGroupOfN</option>
			  	</c:if>
			  	<c:if test="${ promotion.discountType == 3 }">
			  		<option value="3" selected>spendMoreThanInLastYear</option>
			  	</c:if>
			  	<c:if test="${ promotion.discountType != 3 }">
			  		<option value="3">spendMoreThanInLastYear</option>
			  	</c:if>
			  	<c:if test="${ promotion.discountType == 4 }">
			  		<option value="4" selected>buyXGetYFree</option>
			  	</c:if>
			  	<c:if test="${ promotion.discountType != 4 }">
			  		<option value="4">buyXGetYFree</option>
			  	</c:if>
			  </select>
			</div>
		   </div>
  			
<!--   			
  			<div class="mb-3">
  				<select id="promotion_item" multiple="multiple">
  					<c:forEach items="${products}" var="product">
				    <option value="${ product.id }">${ product.name }</option>
				    </c:forEach>
				</select>
				<script type="text/javascript">
				    $(document).ready(function() {
				        $('#promotion_item').multiselect();
				    });
				</script>		
	  			
  			</div>
 -->			
  			
  			<div class="input-group mb-3">
			  <div class="input-group-prepend">
			    <label class="input-group-text" for="inputGroupSelect01">State</label>
			  </div>
			  <select class="custom-select" id="state">
			  	<c:if test="${ promotion.state == 0 }">
			  		<option value="0" selected>inactive</option>
			  	</c:if>
			  	<c:if test="${ promotion.state != 0 }">
			  		<option value="0">inactive</option>
			  	</c:if>
			  	<c:if test="${ promotion.state == 1 }">
			  		<option value="1" selected>active</option>
			  	</c:if>
			  	<c:if test="${ promotion.state != 1 }">
			  		<option value="1">active</option>
			  	</c:if>
			  </select>
			</div>
  			
  				
			<button class="btn btn-lg btn-primary btn-block" id="create_promotion_btn">Submit</button>
  			
  		
  	

  </div>
</div>


<script>
$(document).ready(function(){
    $("#create_promotion_btn").click(function(){
        var discountType = $("#discount_type").val();
        var discountName = $("#discount_name").val();
        var dateFrom = $("#date_from").val();
        var dateTo = $("#date_to").val();
        var state = $("#state").val();
        
        /*
        var selectedProducts = new Array();
        $("#promotion_item option:selected").each(function() {
        		
        	selectedProducts.push($(this).val().toString());
        	
        })
        */
        
		
        if( discountType !== "Choose..." && discountType !== "" && discountName != "" ){
	        	  $.ajax({
	                  url: 'admin_promotion_update',
	                  type: 'post',
	                  async: false,
	                  data: {
	                	  discountType: discountType, 
	                	  discountName: discountName,
	                	  dateFrom: dateFrom,
	                	  dateTo: dateTo,
	                	  state: state,
	                	  promotionId: "${ promotion.id }"
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
	                              
	                    	  location.href = './admin_promotion_list';
	                         
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
