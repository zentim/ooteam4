<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>


<div class="col-sm-2 sidenav" style="padding:10px 0px 10px 0px;">
    
    <!-- show segment -->
    <div class="accordion" id="accordionExample">
	  <div class="card" id="cardOne">
	    <div class="card-header" id="headingOne">
	      <h5 class="mb-0">
	        <button 
	        	class="btn btn-link" 
	        	type="button" 
	        	data-toggle="collapse" 
	        	data-target="#collapseOne" 
	        	aria-expanded="true" 
	        	aria-controls="collapseOne"
	        	onclick="toggleCategory(this)">
	          Collapsible Group Item #1
	        </button>
	      </h5>
	    </div>
	
	    <div id="collapseOne" class="collapse" aria-labelledby="headingOne" data-parent="#accordionExample">
	      <div class="card-body">
	        
	        <!-- show category -->
	        <ul class="nav navbar-nav">
		    	<c:forEach items="${ cs }" var="c" varStatus="stc">
					<li><a href="#${ c.id }">${ c.name } <span class="badge">${ c.products.size() }</span></a></li>
		    	</c:forEach>
		    </ul>
	        
	      </div>
	    </div>
	  </div>
	  <div class="card">
	    <div class="card-header" id="headingTwo">
	      <h5 class="mb-0">
	        <button class="btn btn-link collapsed" type="button" data-toggle="collapse" data-target="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
	          Collapsible Group Item #2
	        </button>
	      </h5>
	    </div>
	    <div id="collapseTwo" class="collapse" aria-labelledby="headingTwo" data-parent="#accordionExample">
	      <div class="card-body">
	        Anim pariatur cliche reprehenderit, enim eiusmod high life accusamus terry richardson ad squid. 3 wolf moon officia aute, non cupidatat skateboard dolor brunch. Food truck quinoa nesciunt laborum eiusmod. Brunch 3 wolf moon tempor, sunt aliqua put a bird on it squid single-origin coffee nulla assumenda shoreditch et. Nihil anim keffiyeh helvetica, craft beer labore wes anderson cred nesciunt sapiente ea proident. Ad vegan excepteur butcher vice lomo. Leggings occaecat craft beer farm-to-table, raw denim aesthetic synth nesciunt you probably haven't heard of them accusamus labore sustainable VHS.
	      </div>
	    </div>
	  </div>
	  <div class="card">
	    <div class="card-header" id="headingThree">
	      <h5 class="mb-0">
	        <button class="btn btn-link collapsed" type="button" data-toggle="collapse" data-target="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
	          Collapsible Group Item #3
	        </button>
	      </h5>
	    </div>
	    <div id="collapseThree" class="collapse" aria-labelledby="headingThree" data-parent="#accordionExample">
	      <div class="card-body">
	        Anim pariatur cliche reprehenderit, enim eiusmod high life accusamus terry richardson ad squid. 3 wolf moon officia aute, non cupidatat skateboard dolor brunch. Food truck quinoa nesciunt laborum eiusmod. Brunch 3 wolf moon tempor, sunt aliqua put a bird on it squid single-origin coffee nulla assumenda shoreditch et. Nihil anim keffiyeh helvetica, craft beer labore wes anderson cred nesciunt sapiente ea proident. Ad vegan excepteur butcher vice lomo. Leggings occaecat craft beer farm-to-table, raw denim aesthetic synth nesciunt you probably haven't heard of them accusamus labore sustainable VHS.
	      </div>
	    </div>
	  </div>
	</div>
</div>


<script type="text/javascript">
function toggleCategory(item) {
    // alert($(item).parent().parent().parent().find("ul").attr('id'));
	var display = $(item).parent().parent().parent().find("ul").parent().parent().css("display");
	var isDisplay = $(item).parent().parent().parent().find("ul").parent().parent().hasClass("in");
	if (isDisplay) {
		$(item).parent().parent().parent().find("ul").css("display", "none");
	} else {
		$(item).parent().parent().parent().find("ul").css("display", "block");
	}
}

$(function(){

});
</script>





