<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>

<c:if test="${empty param.brandcount}">
    <c:set var="brandcount" scope="page" value="100"/>
</c:if>
 
<c:if test="${!empty param.brandcount}">
    <c:set var="brandcount" scope="page" value="${param.brandcount}"/>
</c:if>



    <!-- ##### Welcome Area Start ##### -->
    <section class="welcome_area bg-img background-overlay" style="background-image: url(img/bg-img/bg-1.jpg);">
        <div class="container h-100">
            <div class="row h-100 align-items-center">
                <div class="col-12">
                    <div class="hero-content">
<!--                     
                        <h6>asoss</h6>
-->                        
                        <h2>New Collection</h2>
                        <a href="forecategory?cid=1" class="btn essence-btn">view collection</a>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- ##### Welcome Area End ##### -->

    <!-- ##### Top Catagory Area Start ##### -->
    <div class="top_catagory_area section-padding-80 clearfix">
        <div class="container">
            <div class="row justify-content-center">
                <!-- Single Catagory -->
                <div class="col-12 col-sm-6 col-md-4">
                    <div class="single_catagory_area d-flex align-items-center justify-content-center bg-img" style="background-image: url(img/bg-img/bg-2.jpg);">
                        <div class="catagory-content">
                            <a href="forecategory?cid=1">Category</a>
                        </div>
                    </div>
                </div>
                <!-- Single Catagory -->
                <div class="col-12 col-sm-6 col-md-4">
                    <div class="single_catagory_area d-flex align-items-center justify-content-center bg-img" style="background-image: url(img/bg-img/bg-3.jpg);">
                        <div class="catagory-content">
                            <a href="forecategory?cid=1">Category</a>
                        </div>
                    </div>
                </div>
                <!-- Single Catagory -->
                <div class="col-12 col-sm-6 col-md-4">
                    <div class="single_catagory_area d-flex align-items-center justify-content-center bg-img" style="background-image: url(img/bg-img/bg-4.jpg);">
                        <div class="catagory-content">
                            <a href="forecategory?cid=1">Category</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- ##### Top Catagory Area End ##### -->

    <!-- ##### CTA Area Start ##### -->
    <div class="cta-area">
        <div class="container">
            <div class="row">
                <div class="col-12">
                    <div class="cta-content bg-img background-overlay" style="background-image: url(img/bg-img/bg-5.jpg);">
                        <div class="h-100 d-flex align-items-center justify-content-end">
                            <div class="cta--text">
                                <h6>-15%</h6>
                                <h2>Buy 100 units</h2>
                                <a href="foreproduct?pid=4" class="btn essence-btn">Buy Now</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- ##### CTA Area End ##### -->

    <!-- ##### New Arrivals Area Start ##### -->
    <section class="new_arrivals_area section-padding-80 clearfix">
        <div class="container">
            <div class="row">
                <div class="col-12">
                    <div class="section-heading text-center">
                        <h2>Popular Products</h2>
                    </div>
                </div>
            </div>
        </div>

        <div class="container">
            <div class="row">
                <div class="col-12">
                    <div class="popular-products-slides owl-carousel">

						<c:forEach items="${ brands }" var="brand" varStatus="stc">
							<c:if test="${ stc.count <= brandcount }">
							
								<c:forEach items="${ brand.components }" var="product" varStatus="st">
									<c:if test="${ st.count <= brandcount }">	
										
				                        <!-- Single Product -->
				                        <div class="single-product-wrapper">
				                        
					                        <a href="foreproduct?pid=${ product.value.id }">
					                            <!-- Product Image -->
					                            <div class="product-img" style="height: 350px; display: flex; justify-content: center; align-items: center;">
					                                <img src="img/productSingle/${product.value.firstProductImage.id}.jpg" alt="productId ${product.value.firstProductImage.id}">
					                                <!-- Hover Thumb -->		                                
	<!-- 		                                
					                                <img class="hover-img" src="img/product-img/product-2.jpg" alt="">
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
<!-- 		                                
				                                <div class="hover-content">
-->		                                
				                                    <!-- Add to Cart -->
<!--		                                    
				                                    <div class="add-to-cart-btn">
				                                        <a href="#" class="btn essence-btn">Add to Cart</a>
				                                    </div>
                          
				                                </div>
-->		          				                                
				                            </div>
				                        </div>
									</c:if>
								</c:forEach>
						
						
                      		</c:if>
						</c:forEach>

                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- ##### New Arrivals Area End ##### -->

    <!-- ##### Brands Area Start ##### -->
    <div class="brands-area d-flex align-items-center justify-content-between">
        <!-- Brand Logo -->
        <div class="single-brands-logo">
            <img src="img/core-img/brand1.png" alt="">
        </div>
        <!-- Brand Logo -->
        <div class="single-brands-logo">
            <img src="img/core-img/brand2.png" alt="">
        </div>
        <!-- Brand Logo -->
        <div class="single-brands-logo">
            <img src="img/core-img/brand3.png" alt="">
        </div>
        <!-- Brand Logo -->
        <div class="single-brands-logo">
            <img src="img/core-img/brand4.png" alt="">
        </div>
        <!-- Brand Logo -->
        <div class="single-brands-logo">
            <img src="img/core-img/brand5.png" alt="">
        </div>
        <!-- Brand Logo -->
        <div class="single-brands-logo">
            <img src="img/core-img/brand6.png" alt="">
        </div>
    </div>
    <!-- ##### Brands Area End ##### -->
            