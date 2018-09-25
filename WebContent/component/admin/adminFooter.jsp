<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>



		</main>
      </div>
    </div>

	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
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
			alert(name+ "不能為空");
			$("#"+id)[0].focus();
			return false;
		}
		return true;
	}
	
	function checkNumber(id, name){
		var value = $("#"+id).val();
		if(value.length==0){
			alert(name+ "不能為空");
			$("#"+id)[0].focus();
			return false;
		}
		if(isNaN(value)){
			alert(name+ "必須是數字");
			$("#"+id)[0].focus();
			return false;
		}
		
		return true;
	}
	
	function checkInt(id, name){
		var value = $("#"+id).val();
		if(value.length==0){
			alert(name+ "不能為空");
			$("#"+id)[0].focus();
			return false;
		}
		if(parseInt(value)!=value){
			alert(name+ "必須是整數");
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
				var confirmDelete = confirm("確認要刪除");
				if(confirmDelete){
					return true;
				}
				return false;
			}
		});
	})
	</script>	
</body>
</html>