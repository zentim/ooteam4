<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>



		</main>
      </div>
    </div>

	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
	
	<!-- Icons -->
    <script src="https://unpkg.com/feather-icons/dist/feather.min.js"></script>
    <script>
      feather.replace()
    </script>
    
    
    <!-- Check Functions -->
    <script>
	function checkEmpty(id, name){
		var value = $("#"+id).val();
		if(value.length==0){
			alert(name+ "Can not be empty!");
			$("#"+id)[0].focus();
			return false;
		}
		return true;
	}
	
	function checkNumber(id, name){
		var value = $("#"+id).val();
		if(value.length==0){
			alert(name+ "Can not be empty!");
			$("#"+id)[0].focus();
			return false;
		}
		if(isNaN(value)){
			alert(name+ "Must be a number!");
			$("#"+id)[0].focus();
			return false;
		}
		
		return true;
	}
	
	function checkInt(id, name){
		var value = $("#"+id).val();
		if(value.length==0){
			alert(name+ "Can not be empty!");
			$("#"+id)[0].focus();
			return false;
		}
		if(parseInt(value)!=value){
			alert(name+ "Must be an integer!");
			$("#"+id)[0].focus();
			return false;
		}
		
		return true;
	}
	
	
	$(function(){
		$("a").click(function(){
			var deleteLink = $(this).attr("deleteLink");
			console.log(deleteLink);
			if(deleteLink == "true"){
				var confirmDelete = confirm("Confirm to delete?");
				if(confirmDelete){
					return true;
				}
				return false;
			}
		});
	})
	</script>
	
	<script type="text/javascript">
	$(function (){
	
	    $('.nav-link').each(function(){
	        var path = window.location.href;
	        var current = path.substring(path.lastIndexOf('/')+1);
	        var url = $(this).attr('href');
	
	        if(url == current){
	            $(this).addClass('active');
	        };
	    });         
	});
	
	</script>	
	<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.22.2/moment.min.js"></script>
	<script type="text/javascript" src="./js/bootstrap-datetimepicker.min.js"></script>
	<script type="text/javascript" src="./js/bootstrap-multiselect.js"></script>
</body>
</html>