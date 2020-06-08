<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta http-equiv="X-UA-Compatible" content="ie=edge">
	<title>廠商管理</title>
</head>
<body>
	<div class="container-fluid">
		<form class="container">
			<div class="form-row">
				<div class="col-md-4 mb-3">
					<label for="vendorNm" class="text-light">訂單編號：</label>
					<input class="form-control" type="text" id="vendorNm" />
				</div>
				<div class="col-md-4 mb-3">
					<label for="vendorNm" class="text-light">買家帳號：</label>
					<input class="form-control" type="text" id="vendorNm" />
				</div>
			</div>
			<div class="form-row">
				<div class="col-md-3 mb-3">
					<label for="comm" class="text-light">已/未產生寄件編號：</label>
					<select class="form-control" id="comm" name="comm">
						<option value="">全部</option>
						<option value="">已產生</option>
						<option value="">未產生</option>
					</select>
				</div>
				<div class="col-md-3 mb-3">
					<label for="status" class="text-light">已/未列印寄件單：</label>
					<select class="form-control" id="status" name="status">
						<option value="">全部</option>
						<option value="">已列印</option>
						<option value="">未列印</option>
					</select>
				</div>
				<div class="col-md-3 mb-3">
					<label for="status" class="text-light">寄送方式：</label>
					<select class="form-control" id="status" name="status">
						<option value="">全部</option>
						<option value="">7-11</option>
						<option value="">全家</option>
						<option value="">萊爾富</option>
						<option value="">OK Mart</option>
						<option value="">黑貓</option>
						<option value="">中華郵政</option>
						<option value="">賣家宅配</option>
					</select>
				</div>
			</div>
			<div class="form-row">
				<div class="col-md-4 mb-3">
					<input type="button" class="btn btn-primary" id="btn1" value="查詢">
				</div>
			</div>
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
	loadTable();
	function loadTable() {
		$("#userList").datagrid({
			url:'',
			title:'訂單列表',
			iconCls: "fas fa-box",
			rownumbers: true, 
			pagination: true,
			singleSelect:true,
			height: 440,
			width: '100%',
			queryParams: {
				comm: $('#comm').val(),
				vendor: $('#vendor').val(),
				status: $('#status').val(),
				dateS: $('#dateS').val(),
				dateE: $('#dateE').val()
			},
			sortName: 'PUR_ID',
			sortOrder: 'ASC',
			frozenColumns:[[
				{field:'PUR', title:'訂單編號', width:'20%',sortable:true}
			]],
			columns:[[
				{field:'INIT_DT', title:'訂單日期', width:'10%',sortable:true},
				{field:'CASH', title:'買家帳號', width:'10%'},
				{field:'CASH', title:'商品', width:'10%'},
				{field:'CASH', title:'數量', width:'10%'},
				{field:'CASH', title:'買家備註', width:'10%'},
				{field:'CASH', title:'賣家備註', width:'10%'},
				{field:'CASH', title:'寄送方式', width:'10%'},
				{field:'CASH', title:'寄件編號', width:'10%'},
				{field:'CASH', title:'收件人', width:'10%'},
				{field:'CASH', title:'手機末三碼', width:'10%'},
				{field:'ST_NM', title:'狀態', width:'10%', styler:(val, row, index) => { if (row.STATUS == '02') { return 'color: red;';} }}
			]],
			onDblClickRow: (rowIndex, rowData) => {
				$('#ftype').val('SELECT'); 
				$('#fPurId').val($("#userList").datagrid('getSelected').PUR_ID);
				$('#fInit').val($("#userList").datagrid('getSelected').INIT_DT);
				$('#form2').submit();
			},
			toolbar: [
			{
				iconCls: 'icon-print',
				text: '列印寄件單 A4',
				handler: function(){
					if ($("#userList").datagrid('getSelections').length < 1) {
						alert('請選擇要查詢的資料');
						return;
					}
					$('#ftype').val('SELECT'); 
					$('#fPurId').val($("#userList").datagrid('getSelected').PUR_ID);
					$('#fInit').val($("#userList").datagrid('getSelected').INIT_DT);
					$('#form2').submit();
				}
			},{
				iconCls: 'icon-print',
				text: '列印寄件單 (15*10)',
				handler: function(){
					$('#ftype').val('INSERT'); 
					$('#form2').submit();
				}
			},{
				iconCls: 'icon-print',
				text: '列印寄件單+裝箱單 (15*10)',
				handler: function(){
					if ($("#userList").datagrid('getSelections').length < 1) {
						alert('請選擇要停用的資料');
						return;
					}
					let id = $("#userList").datagrid('getSelected').PUR_ID;
					checkDelPur('02', id, '是否停用此筆資料');
				}
			},{
				iconCls: 'icon-print',
				text: '列印裝箱單 A4不分頁 列印裝箱單 (15*10)',
				handler: function(){
					if ($("#userList").datagrid('getSelections').length < 1) {
					alert('請選擇要啟用的資料');
						return;
					}
					let id = $("#userList").datagrid('getSelected').PUR_ID;
					changeType('01', id, '是否啟用此筆資料');
				}
			}]
		});
	}
	</script>
</body>
</html>