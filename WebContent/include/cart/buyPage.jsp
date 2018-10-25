<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>

<div class="container">
	<h1 class="form-heading" style="color:white">login Form</h1>
	<div class="login-form">
		<div>
			<form action="forecreateOrder" method="post" style="width: 600px; margin: 0 auto;">
				<div style="width: 600px; margin: 0 auto;">
					<div class="panel" style="margin-top: 50px">
				   		<h4>Enter the shipping information</h4>   
				   	</div>
				   
			        <div class="form-group">
			            <input type="text" name="address" class="form-control" placeholder="Address">
			        </div>
			        
			        <div class="form-group">
			            <input type="text"  name="phone" class="form-control" placeholder="Phone">
			        </div>
			        
			        
			        <div class="panel" style="margin-top: 50px">
				   		<h4>Payment options</h4>   
				   	</div>
				   
			        <div class="form-check">
					  <input class="form-check-input" type="radio" name="paymentOption" id="exampleRadios1" value="CreditCard" checked>
					  <label class="form-check-label" for="exampleRadios1">
					    CreditCard
					  </label>
					</div>
					
					<div class="form-check">
					  <input class="form-check-input" type="radio" name="paymentOption" id="exampleRadios2" value="LinePay">
					  <label class="form-check-label" for="exampleRadios2">
					    LinePay
					  </label>
					</div>
					
					<div class="form-check">
					  <input class="form-check-input" type="radio" name="paymentOption" id="exampleRadios3" value="WeChatPay">
					  <label class="form-check-label" for="exampleRadios3">
					    WeChatPay
					  </label>
					</div>
					
					
					<div class="panel" style="margin-top: 50px">
				   		<h4>Do you need a tax ID number on your receipt?</h4>   
				   	</div>
				   	
				   	<p>
					  <button class="btn btn-primary" type="button" data-toggle="collapse" data-target="#collapseExample" aria-expanded="false" aria-controls="collapseExample" style="margin: 0;">
					    Click here to enter your tax ID number (Optional)
					  </button>
					</p>
					<div class="collapse" id="collapseExample">
					  <div class="card card-body">
					    <div class="form-group">
				            <input type="text" name="taxIdNumber" class="form-control" placeholder="Tax ID Number">
				        </div>
					  </div>
					</div>
					   
				
				</div>
			    
				<div>
					<div class="panel" style="margin-top: 50px">
				   		<h4>Confirm Order Information</h4>   
				   	</div>
				
					<table class="table table-striped table-bordered table-sm">
	
					    <thead>
					      <tr align="center">
					
					        <th></th>
					        <th>Product</th>
					        <th>Price</th>
					        <th>Quantity</th>
					        <th>Subtotal</th>
					
					      </tr>
					    </thead>
					
					    <tbody>
					      <c:forEach items="${ ois }" var="oi">
					        <tr oiid="${oi.id}" class="cartProductItemTR">
					
					
					            <td align="center">
					              <div style="position:relative; width: 80px; height: 80px;">
					                <img 
					                  class="cartProductImg"  
					                  src="img/productSingle_middle/${oi.product.firstProductImage.id}.jpg" 
					                  style="position:absolute; max-width: 100%; max-height: 100%; top:0; bottom: 0; left: 0; right: 0; margin: auto; ">
					              </div>
					            </td>
					
					            <td align="center">
					              <div class="cartProductLinkOutDiv">
					                <a 
					                  href="foreproduct?pid=${oi.product.id}" 
					                  class="cartProductLink">${oi.product.name}</a>
					              </div>
					            </td>
					
					            <td align="right">
					              <span class="orderItemProductPrice">
					              	$ <fmt:formatNumber 
					              		type="number" 
					              		value="${oi.product.price}" 
					              		minFractionDigits="2"/>
					              </span>
					            </td>
					
					            <td align="center">
					            	${oi.quantity}
					            </td>
					
								<td>
									<span class="orderItemUnitSum">
		                        		$ <fmt:formatNumber 
		                        			type="number" 
		                        			value="${oi.quantity * oi.product.price}" 
		                        			minFractionDigits="2"/>
		                        	</span>
								</td>
					
					        </tr>
					      </c:forEach>
					    </tbody>
					
					  </table>
				</div>		
			    
			    <div style="float: right;">
			    	<p>
						<span>
							 Sum: $
							<fmt:formatNumber type="number" value="${ totalWithoutDiscount }" minFractionDigits="2"/>
						</span>
			    	</p>
			    	
			    	<p>
			    		<span style="color: red;" id="discountMessage">${ discountMsg }</span><br>
			    	</p>
			    	
			    	<p>
			    		<span>Payment :</span>
		            	<span style="color: #C40000; font-size: 22px; font-weight: bold; border-bottom: 1px dotted #F2F6FF;">
		            		$<fmt:formatNumber type="number" value="${ total }" minFractionDigits="2"/>
		            	</span>
			    	</p>
			    	
			    	<p>
			    		<button type="submit" class="btn btn-lg" style="font-weight: bold; background-color: #C40000; color: white;">Submit Order</button>
			    	</p>
			    </div>
		    </form>
		    
		    <form action="foreclearOrder" method="post">

		    	<button type="submit" class="btn btn-lg">Clear Order</button>
		    </form>
	    </div>
	</div>
</div>


<script>
$(function() {
	
	// display the discount message
	$("#discountMessage").html(function(){
		var s = "${ discountMsg }";
		var arrStr = s.split(/[()]/);
		
		var discountResult = ""
		for (var i = 0; i < arrStr.length; i++) {
			discountResult += arrStr[i] + "<br>";
		}
		return discountResult;
	})
})
</script>


