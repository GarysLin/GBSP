<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta http-equiv="X-UA-Compatible" content="ie=edge">
	<title>登入</title>
</head>
<body>
	<div class="container-fluid">
		<form class="form-signin" name="form1" id="form1"> 
			<h1 class="h3 mb-3 font-weight-normal text-light">Please sign in</h1>
			<label for="loginId" class="sr-only text-light">帳號</label>
			<input type="text" id="loginId" class="form-control" placeholder="帳號" name="loginId" autofocus value="gary">
			<br>
			<label for="password" class="sr-only text-light">密碼</label>
			<input type="password" id="password" class="form-control" placeholder="密碼" name="password" value="Aa111111">
			<br>
			<button class="btn btn-lg btn-primary btn-block" type="submit" id="signin">Sign in</button>
			</form>
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
		$("#form1").validate({
			rules: {
				loginId: {
					required: true
				},
				password: {
					required: true
				}
			},
			messages: {
				loginId: {
					required: "請輸入帳號"
				},
				password: {
					required: "請輸入密碼"
				}
			},
			submitHandler: sendlogin
		});

		function sendlogin() {
			console.log('sendlogin');
			$.ajax({
				url: "/login",
				data:{
					loginId : $('#loginId').val(), 
					password : $('#password').val() 
				},
				method: "POST",
				success: function (data) {
					if (data.isLogin) {
						window.location.href = data.redirectUrl;
					} else {
						alert(data.msg);
					}
				},
				error: function (jqXHR, textStatus, errorThrown) {
					alert('系統異常，請洽系統管理員');
				}
			});
		}
	</script>
</body>
</html>