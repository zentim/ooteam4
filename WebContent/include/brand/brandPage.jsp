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
		
									<c:forEach items="${ brand.products }" var="product" varStatus="st">
										<c:if test="${ st.count <= brandcount }">		
				                            <!-- Single Product -->
				                            <div class="col-12 col-sm-6 col-lg-3">
				                                <div class="single-product-wrapper">
				                                
				                                	<a href="foreproduct?pid=${ product.id }">
					                                    <!-- Product Image -->
					                                    <div class="product-img" style="height: 350px; display: flex; justify-content: center; align-items: center;">
					                                        <img src="img/productSingle/${product.firstProductImage.id}.jpg" alt="productId ${product.firstProductImage.id}">
					                                        <!-- Hover Thumb -->
<!-- 					                                        
					                                        <img class="hover-img" src="img/product-img/product-4.jpg" alt="">
-->					                                        
					
					                                        <!-- Product Badge -->
					                                        <c:if test="${!empty product.promotionName }">
						                                        <div class="product-badge new-badge" style="top: 75%; left: 30%; height: auto;">
						                                            <span>${ product.promotionName } - ${ product.discountTypeName }</span>
						                                        </div>
					                                        </c:if>
					
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
				                                        <span>topshop</span>
				                                        <a href="foreproduct?pid=${ product.id }">
				                                            <h6>${ product.name }</h6>
				                                        </a>
				                                        <p class="product-price">$${ product.price }</p>
				
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