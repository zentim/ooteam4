<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
    
<c:if test="${empty param.brandcount}">
    <c:set var="brandcount" scope="page" value="100"/>
</c:if>
 
<c:if test="${!empty param.brandcount}">
    <c:set var="brandcount" scope="page" value="${param.brandcount}"/>
</c:if>




    <!-- ##### Breadcumb Area Start ##### -->
    <div class="breadcumb_area bg-img" style="background-image: url(img/bg-img/breadcumb.jpg);">
        <div class="container h-100">
            <div class="row h-100 align-items-center">
                <div class="col-12">
                    <div class="page-title text-center">
                        <h2>${ category.name }</h2>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- ##### Breadcumb Area End ##### -->

    <!-- ##### Shop Grid Area Start ##### -->
    <section class="shop_grid_area section-padding-80">
        <div class="container">
            <div class="row">
                <div class="col-12 col-md-4 col-lg-3">
                    <div class="shop_sidebar_area">

                        <!-- ##### Single Widget ##### -->
<!--                         
                        <div class="widget catagory mb-50">
-->                        
                            <!-- Widget Title -->
<!--                            
                            <h6 class="widget-title mb-30">Catagories</h6>
--> 
                            <!--  Catagories  -->
<!--                            
                            <div class="catagories-menu">
                                <ul id="menu-content2" class="menu-content collapse show">
-->                                 
                                    <!-- Single Item -->
<!--                                    
                                    <li data-toggle="collapse" data-target="#clothing">
                                        <a href="#">clothing</a>
                                        <ul class="sub-menu collapse show" id="clothing">
                                            <li><a href="#">All</a></li>
                                            <li><a href="#">Bodysuits</a></li>
                                            <li><a href="#">Dresses</a></li>
                                            <li><a href="#">Hoodies &amp; Sweats</a></li>
                                            <li><a href="#">Jackets &amp; Coats</a></li>
                                            <li><a href="#">Jeans</a></li>
                                            <li><a href="#">Pants &amp; Leggings</a></li>
                                            <li><a href="#">Rompers &amp; Jumpsuits</a></li>
                                            <li><a href="#">Shirts &amp; Blouses</a></li>
                                            <li><a href="#">Shirts</a></li>
                                            <li><a href="#">Sweaters &amp; Knits</a></li>
                                        </ul>
                                    </li>
-->                                     
                                    <!-- Single Item -->
<!--                                    
                                    <li data-toggle="collapse" data-target="#shoes" class="collapsed">
                                        <a href="#">shoes</a>
                                        <ul class="sub-menu collapse" id="shoes">
                                            <li><a href="#">All</a></li>
                                            <li><a href="#">Bodysuits</a></li>
                                            <li><a href="#">Dresses</a></li>
                                            <li><a href="#">Hoodies &amp; Sweats</a></li>
                                            <li><a href="#">Jackets &amp; Coats</a></li>
                                            <li><a href="#">Jeans</a></li>
                                            <li><a href="#">Pants &amp; Leggings</a></li>
                                            <li><a href="#">Rompers &amp; Jumpsuits</a></li>
                                            <li><a href="#">Shirts &amp; Blouses</a></li>
                                            <li><a href="#">Shirts</a></li>
                                            <li><a href="#">Sweaters &amp; Knits</a></li>
                                        </ul>
                                    </li>
-->                                     
                                    <!-- Single Item -->
<!--                                       
                                    <li data-toggle="collapse" data-target="#accessories" class="collapsed">
                                        <a href="#">accessories</a>
                                        <ul class="sub-menu collapse" id="accessories">
                                            <li><a href="#">All</a></li>
                                            <li><a href="#">Bodysuits</a></li>
                                            <li><a href="#">Dresses</a></li>
                                            <li><a href="#">Hoodies &amp; Sweats</a></li>
                                            <li><a href="#">Jackets &amp; Coats</a></li>
                                            <li><a href="#">Jeans</a></li>
                                            <li><a href="#">Pants &amp; Leggings</a></li>
                                            <li><a href="#">Rompers &amp; Jumpsuits</a></li>
                                            <li><a href="#">Shirts &amp; Blouses</a></li>
                                            <li><a href="#">Shirts</a></li>
                                            <li><a href="#">Sweaters &amp; Knits</a></li>
                                        </ul>
                                    </li>
                                </ul>
                            </div>
                        </div>
-->                            

                        <!-- ##### Single Widget ##### -->
<!-- 
                        <div class="widget price mb-50">
-->
                            <!-- Widget Title -->
<!--                             
                            <h6 class="widget-title mb-30">Filter by</h6>
-->                            
                            <!-- Widget Title 2 -->
<!--                            
                            <p class="widget-title2 mb-30">Price</p>
-->
<!-- 
                            <div class="widget-desc">
                                <div class="slider-range">
                                    <div data-min="49" data-max="360" data-unit="$" class="slider-range-price ui-slider ui-slider-horizontal ui-widget ui-widget-content ui-corner-all" data-value-min="49" data-value-max="360" data-label-result="Range:">
                                        <div class="ui-slider-range ui-widget-header ui-corner-all"></div>
                                        <span class="ui-slider-handle ui-state-default ui-corner-all" tabindex="0"></span>
                                        <span class="ui-slider-handle ui-state-default ui-corner-all" tabindex="0"></span>
                                    </div>
                                    <div class="range-price">Range: $49.00 - $360.00</div>
                                </div>
                            </div>
                        </div>
-->                        

                        <!-- ##### Single Widget ##### -->
<!--                         
                        <div class="widget color mb-50">
-->                        
                            <!-- Widget Title 2 -->
                            
<!--                              
                            <p class="widget-title2 mb-30">Color</p>
                            <div class="widget-desc">
                                <ul class="d-flex">
                                    <li><a href="#" class="color1"></a></li>
                                    <li><a href="#" class="color2"></a></li>
                                    <li><a href="#" class="color3"></a></li>
                                    <li><a href="#" class="color4"></a></li>
                                    <li><a href="#" class="color5"></a></li>
                                    <li><a href="#" class="color6"></a></li>
                                    <li><a href="#" class="color7"></a></li>
                                    <li><a href="#" class="color8"></a></li>
                                    <li><a href="#" class="color9"></a></li>
                                    <li><a href="#" class="color10"></a></li>
                                </ul>
                            </div>
                        </div>
-->                        

                        <!-- ##### Single Widget ##### -->
                        <div class="widget brands mb-50">
                            <!-- Widget Title 2 -->
                            <p class="widget-title2 mb-30">Brands</p>
                            <div class="widget-desc">
                                <ul>
                                	<c:forEach items="${ brands }" var="brand" varStatus="stc">
										<c:if test="${ stc.count <= brandcount }">
                                    		<li><a href="forebrand?cid=${ brand.id }">${ brand.name }</a></li>
                                    	</c:if>
                                    </c:forEach>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="col-12 col-md-8 col-lg-9">
                    <div class="shop_grid_product_area">
<!--                     
                        <div class="row">
                            <div class="col-12">
                                <div class="product-topbar d-flex align-items-center justify-content-between">
-->                                
                                    <!-- Total Products -->
<!--                                    
                                    <div class="total-products">
                                        <p><span>186</span> products found</p>
                                    </div>
-->                                    
                                    <!-- Sorting -->
<!--                                    
                                    <div class="product-sorting d-flex">
                                        <p>Sort by:</p>
                                        <form action="#" method="get">
                                            <select name="select" id="sortByselect">
                                                <option value="value">Highest Rated</option>
                                                <option value="value">Newest</option>
                                                <option value="value">Price: $$ - $</option>
                                                <option value="value">Price: $ - $$</option>
                                            </select>
                                            <input type="submit" class="d-none" value="">
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
--> 


						<c:forEach items="${ brands }" var="brand" varStatus="stc">
							<c:if test="${ stc.count <= brandcount }">
								<a href="forebrand?cid=${ brand.id }" >
									<h2>${ brand.name }</h2>
								</a>
								
		                        <div class="row">
		
									<c:forEach items="${ brand.components }" var="product" varStatus="st">
										<c:if test="${ st.count <= 9 }">		
				                            <!-- Single Product -->
				                            <div class="col-12 col-sm-6 col-lg-4">
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
							</c:if>
						</c:forEach>
                        
                        
                        
                        
                        
                        
                    </div>
                    <!-- Pagination -->
<!--                     
                    <nav aria-label="navigation">
                        <ul class="pagination mt-50 mb-70">
                            <li class="page-item"><a class="page-link" href="#"><i class="fa fa-angle-left"></i></a></li>
                            <li class="page-item"><a class="page-link" href="#">1</a></li>
                            <li class="page-item"><a class="page-link" href="#">2</a></li>
                            <li class="page-item"><a class="page-link" href="#">3</a></li>
                            <li class="page-item"><a class="page-link" href="#">...</a></li>
                            <li class="page-item"><a class="page-link" href="#">21</a></li>
                            <li class="page-item"><a class="page-link" href="#"><i class="fa fa-angle-right"></i></a></li>
                        </ul>
                    </nav>
-->                    
                </div>
            </div>
        </div>
    </section>
    <!-- ##### Shop Grid Area End ##### -->
            