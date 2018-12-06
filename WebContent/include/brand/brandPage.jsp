<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    
     
<c:if test="${empty param.brandcount}">
    <c:set var="brandcount" scope="page" value="100"/>
</c:if>
 
<c:if test="${!empty param.brandcount}">
    <c:set var="brandcount" scope="page" value="${param.brandcount}"/>
</c:if>


<div class="col-12 col-md-8 offset-md-2">
                    <div class="shop_grid_product_area">
		
		
						
						<a href="forebrand?cid=${ brand.id }" >
							<h2>${ brand.name }</h2>
						</a>
		
						
								
								
		                        <div class="row">
		
									<c:forEach items="${ brand.components }" var="product" varStatus="st">
										<c:if test="${ st.count <= brandcount }">		
				                            <!-- Single Product -->
				                            <div class="col-12 col-sm-6 col-lg-3">
				                                <div class="single-product-wrapper">
				                                
				                                	<a href="foreproduct?pid=${ product.value.id }">
					                                    <!-- Product Image -->
					                                    <div class="product-img" style="height: 350px; display: flex; justify-content: center; align-items: center;">
					                                        <img src="img/productSingle/${product.value.firstProductImage.id}.jpg" alt="productId ${product.value.firstProductImage.id}">
					                                        <!-- Hover Thumb -->
<!-- 					                                        
					                                        <img class="hover-img" src="img/product-img/product-4.jpg" alt="">
-->					                                        
					
					                                        <!-- Product Badge -->
<!-- 					                                        
					                                        <c:if test="${!empty product.value.promotionName }">
						                                        <div class="product-badge new-badge" style="top: 75%; left: 0%; height: auto;">
						                                            <span>${ product.value.promotionName } - ${ product.value.discountTypeName }</span>
						                                        </div>
					                                        </c:if>
-->				
					                                        <!-- Favourite -->
	<!-- 				                                        
					                                        <div class="product-favourite">
					                                            <a href="#" class="favme fa fa-heart"></a>
					                                        </div>
	-->				                                        
					                                    </div>
					                                </a>
				
				                                    <!-- Product Description -->
				                                    <div class="product-description">
				                                        
				                                        <span style="color: red;">${ product.value.promotionName }</span>
				                                        <a href="foreproduct?pid=${ product.value.id }">
				                                            <h6>${ product.value.name }</h6>
				                                        </a>
				                                        <p class="product-price">$${ product.value.price }</p>
				
				                                        <!-- Hover Content -->
				                                        <div class="hover-content">
				                                            <!-- Add to Cart -->
<!-- 				                                            
				                                            <div class="add-to-cart-btn">
				                                                <a href="#" class="btn essence-btn">Add to Cart</a>
				                                            </div>
 -->				                                            
				                                        </div>
				                                    </div>
				                                </div>
				                            </div>
										</c:if>
									</c:forEach>
		                            
		                        </div>
							
		
	    
	    <div style="clear:both"></div>
	</div>
	
</div>