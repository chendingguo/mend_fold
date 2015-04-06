function saveProduct() {
	var url = DYNAMIC_PATH + '/product/saveProduct';

	$.ajax({
		type : "GET",
		url : url,
		cache : false,
		data : $("form").serialize(),
		dataType : "json",
		success : function(retObj) {

			$('#dlg').dialog('close');
			$('#dg').datagrid('reload');

		},
		error : function(XMLHttpRequest) {

			$.messager.show({ // show error message
				title : 'Error',
				msg : 'Failed of update'
			});
		}

	});

}

function editProduct() {
	var row = $('#dg').datagrid('getSelected');
	$('#dlg').dialog('open').dialog('setTitle', '编辑产品');
	$('#fm').form('clear');
	$('#save-product').hide();
	$('#update-product').show();
	$("[name='name']").val(row.name);
	$("[name='price']").val(row.price);
	$("[name='remark']").val(row.remark);
	$("[name='categoryId']").val(row.categoryId);
	$("[name='introduce']").val(row.introduce);

}

function updateProduct() {
	var row = $('#dg').datagrid('getSelected');
	var id = row.id;
	var categoryId = $("[name='categoryId']").val();
	var name = $("[name='name']").val();
	var remark = $("[name='remark']").val();
	var introduce = $("#introduce").val();
	var price = $("[name='price']").val();
	

	$.ajax({
		type : "GET",
		url : DYNAMIC_PATH + '/product/updateProduct',
		cache : false,
		data : {
			id : id,
			categoryId : categoryId,
			name : name,
			price : price,
			remark : remark,
			introduce : introduce

		},
		dataType : "json",
		success : function(retObj) {

			if (retObj > 0) {

				$('#dlg').dialog('close');
				$('#dg').datagrid('reload');
			} else {
				$.messager.show({ // show error message
					title : 'Error',
					msg : 'Failed of update'
				});
			}

		},
		error : function(XMLHttpRequest) {

			$.messager.show({ // show error message
				title : 'Error',
				msg : 'Failed of update'
			});
		}

	});

}

function createTable() {

	var columnsStr = [ [ {
		field : 'id',
		title : 'ID',
		width : '100'
	}, {
		field : 'name',
		title : '产品名称',
		width : '100'
	}, {
		field : 'price',
		title : '产品价格',
		width : '100'
	}, {
		field : 'remark',
		title : '价格区间',
		width : '100'
	}, {
		field : 'categoryName',
		title : '产品分类',
		width : '100'
	}, {
		field : 'introduce',
		title : '产品介绍',
		width : '200'
	} ] ];

	var url = DYNAMIC_PATH + "/product/selectProductList";
	$('#dg').datagrid({
		method : 'get',
		url : url,
		singleSelect : true,

		columns : columnsStr,
		loadMsg : '数据正在加载中,请稍后。。。'

	});
	// $('#dlg').resizeDataGrid(20, 20, 20, 20);

}

function newProduct() {
	$('#dlg').dialog('open').dialog('setTitle', '新加产品');
	$('#fm').form('clear');
	$('#save-product').show();
	$('#update-product').hide();

}

function removeProduct() {
    $.messager.confirm("删除", "确认删除吗?", function (r) {
        if (r) {
	var row = $('#dg').datagrid('getSelected');
	var id = row.id;
	if (row == null) {
		$.messager.show({ // show error message
			title : 'Error',
			msg : 'Please choose the host to delete! '
		});
	}
	$.ajax({
		type : "GET",
		url : DYNAMIC_PATH + '/product/removeProduct/' + id,
		cache : false,
		data : "id=" + id,
		dataType : "json",
		success : function(retObj) {

			if (retObj > 0) {
				alert("删除成功 !");
				$('#dlg').dialog('close');
				$('#dg').datagrid('reload');
			} else {

			}

		},
		error : function(XMLHttpRequest) {

			$.messager.show({ // show error message
				title : 'Error',
				msg : 'Failed of delete'
			});
		}

	});
}});
}

function uploadPicture() {

	var row = $('#dg').datagrid('getSelected');
	if (row !== null) {
		var picAddPath = ROOT_URL + "manage/admin/picture_add.html?productId="
				+ row.id;

		window
				.open(picAddPath, 'addpicture',
						'toolbar=no,menubar=no,scrollbars=yes, resizable=yes,location=no, status=no');
	} else {
		$.messager.show({ // show error message
			title : 'Error',
			msg : '请单击需要增加图片的产品! '
		});
	}

}

function previewImage(file) {
	var MAXWIDTH = 200;
	var MAXHEIGHT = 200;
	var div = document.getElementById('preview');
	if (file.files && file.files[0]) {
		div.innerHTML = '<img id=imghead>';
		var img = document.getElementById('imghead');
		img.onload = function() {
			var rect = clacImgZoomParam(MAXWIDTH, MAXHEIGHT, img.offsetWidth,
					img.offsetHeight);
			img.width = rect.width;
			img.height = rect.height;
			img.style.marginLeft = rect.left + 'px';
			img.style.marginTop = rect.top + 'px';
		}
		var reader = new FileReader();
		reader.onload = function(evt) {
			img.src = evt.target.result;
		}
		reader.readAsDataURL(file.files[0]);
	} else {
		var sFilter = 'filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src="';
		file.select();
		var src = document.selection.createRange().text;
		div.innerHTML = '<img id=imghead>';
		var img = document.getElementById('imghead');
		img.filters.item('DXImageTransform.Microsoft.AlphaImageLoader').src = src;
		var rect = clacImgZoomParam(MAXWIDTH, MAXHEIGHT, img.offsetWidth,
				img.offsetHeight);
		status = ('rect:' + rect.top + ',' + rect.left + ',' + rect.width + ',' + rect.height);
		div.innerHTML = "<div id=divhead style='width:" + rect.width
				+ "px;height:" + rect.height + "px;margin-top:" + rect.top
				+ "px;margin-left:" + rect.left + "px;" + sFilter + src
				+ "\"'></div>";
	}
}

function clacImgZoomParam(maxWidth, maxHeight, width, height) {
	var param = {
		top : 0,
		left : 0,
		width : width,
		height : height
	};
	if (width > maxWidth || height > maxHeight) {
		rateWidth = width / maxWidth;
		rateHeight = height / maxHeight;

		if (rateWidth > rateHeight) {
			param.width = maxWidth;
			param.height = Math.round(height / rateWidth);
		} else {
			param.width = Math.round(width / rateHeight);
			param.height = maxHeight;
		}
	}

	param.left = Math.round((maxWidth - param.width) / 2);
	param.top = Math.round((maxHeight - param.height) / 2);
	return param;
}