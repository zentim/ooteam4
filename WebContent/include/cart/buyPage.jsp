<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>

<div class="container">
	<h1 class="form-heading" style="color:white">login Form</h1>
	<div class="login-form">
		<div class="main-div">
			<form action="forecreateOrder" method="post">
				<div style="width: 600px; margin: 0 auto;">
					<div class="panel">
				   		<h4>輸入收貨地址</h4>   
				   	</div>
				   
				    
				
				        <div class="form-group">
				            <input type="text" name="address" class="form-control" placeholder="Address">
				        </div>
				
				        <div class="form-group">
				            <input type="text"  name="receiver" class="form-control" placeholder="Receiver">
				        </div>
				        
				        <div class="form-group">
				            <input type="text"  name="phone" class="form-control" placeholder="Phone">
				        </div>
					   
				
				</div>
			    
				<div>
					<div class="panel">
				   		<h4>確認訂單信息</h4>   
				   	</div>
				
					<table class="table table-striped table-bordered table-sm">
	
					    <thead>
					      <tr align="center">
					
					        <th></th>
					        <th>商品</th>
					        <th>單價</th>
					        <th>數量</th>
					        <th>小計</th>
					
					      </tr>
					    </thead>
					
					    <tbody>
					      <c:forEach items="${ois }" var="oi">
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
					              		value="${oi.product.promotePrice}" 
					              		minFractionDigits="2"/>
					              </span>
					            </td>
					
					            <td align="center">
					            	${oi.number}
					            </td>
					
								<td>
									<span class="orderItemUnitSum">
		                        		$ <fmt:formatNumber 
		                        			type="number" 
		                        			value="${oi.number * oi.product.promotePrice}" 
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
			    		<span>普通配送 快遞 免運費</span><br>
						<span>
							合計 (含運費): $
							<fmt:formatNumber type="number" value="${total}" minFractionDigits="2"/>
						</span>
			    	</p>
			    	
			    	<p>
			    		<span>實付款 :</span>
		            	<span style="color: #C40000; font-size: 22px; font-weight: bold; border-bottom: 1px dotted #F2F6FF;">
		            		$<fmt:formatNumber type="number" value="${total}" minFractionDigits="2"/>
		            	</span>
			    	</p>
			    	
			    	<p>
			    		<button type="submit" class="btn btn-lg" style="width: 120px; font-weight: bold; background-color: #C40000; color: white;">提交訂單</button>
			    	</p>
			    </div>
		    </form>
	    </div>
	</div>
</div>
