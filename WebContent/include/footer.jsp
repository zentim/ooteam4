<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
					
					
					
					
        	</div>
		</div>
    </div>
    <footer class="container-fluid text-center">
        <p>Footer Text</p>
    </footer>
    
    <%@include file="modal.jsp" %>
    
    <script>
	$(".userLoginLink").click(function(){
        var page = "forecheckLogin";
        
        $.get(
                page,
                function(result){
                    if("success" == result){
                        
                    }
                    else{
                        $("#loginModal").modal('show');                     
                    }
                }
        );      
        return false;
    });
	</script>
</body>

</html>