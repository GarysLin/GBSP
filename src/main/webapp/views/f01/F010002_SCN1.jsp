<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta http-equiv="X-UA-Compatible" content="ie=edge">
	<title>全家PDF轉換</title>
</head>
<body>
	<div class="container-fluid">
		<!--<form  class="container"> -->
		<form:form class="container" id="form1" method="post" action="F010002_SCN1/convertData" 
			modelAttribute="uploadForm" enctype="multipart/form-data">
			<div class="form-row">
				<div class="col-md-4 mb-3">
					<label for="vendorNm" class="text-light">上傳檔案：</label>
					<div class="custom-file">
						<input class="custom-file-input" id="file1" name="file" type="file" />
						<label class="custom-file-label" for="inputGroupFile01">Choose file</label>
					</div>
				</div>
			</div>
			
			<div class="form-row">
				<div class="col-md-4 mb-3">
					<input type="button" class="btn btn-primary" id="btn1" value="轉換">
				</div>
			</div>
		</form:form>
	</div>

	<div class="container-fluid">
		<form class="container">
			<div class="form-row">
				<table id="userList"></table>
				<div id="userListPager"></div>
			</div>
		</form>
	</div>
	
	
	<script>
		$("#btn1").click(function() {
			var file = $("#file1").val();
			var FileExt = file.replace(/.+\./,"").toLowerCase();
			if(FileExt != 'pdf'){
				alert('請上傳PDF檔');
				return;
			}
			
			$("#form1").submit();
		});
		
		$(".custom-file-input").change(function () {
			$(this).next(".custom-file-label").html($(this).val().split("\\").pop());
		});
	</script>
</body>
</html>