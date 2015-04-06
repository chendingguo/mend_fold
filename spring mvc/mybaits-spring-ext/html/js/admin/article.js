$(function() {
	CKEDITOR.replace('htmlContent');

});

//====================================================save product 

function newArticle() {
	$('#dlg').dialog('open').dialog('setTitle', '新加文章');
	$('#fm').form('clear');
	$('#save-article').show();
	$('#update-article').hide();
}




function saveArticle() {
	var title = $('#title').val();
	var categoryId = $("#categoryId").val();
	var htmlContent = CKEDITOR.instances.htmlContent.getData();
	var url = DYNAMIC_PATH + '/article/saveArticle';


	$.ajax({
		type : "POST",
		url : url,
		cache : false,
		data : {
			title:title,
			categoryId:categoryId,
			htmlContent:htmlContent
		},
		dataType : "json",
		success : function(retObj) {

			$('#dlg').dialog('close');
			$('#dg').datagrid('reload');

		},
		error : function(XMLHttpRequest) {

			$.messager.show({ // show error message
				title : 'Error',
				msg : "错误:"+XMLHttpRequest.statusText
			});
		}

	});

}

//====================================================================edit article
function editArticle() {
    var row = $('#dg').datagrid('getSelected');
    $('#dlg').dialog('open').dialog('setTitle', '编辑图片');
    $('#fm').form('clear');
    
    $('#save-article').hide();
    $('#update-article').show();
    $("[name='id']").val(row.id);
    $("[name='categoryId']").val(row.categoryId);
    $("[name='title']").val(row.title);
    $(window.frames[0].document).find("p").html(row.htmlContent);


}



function updateArticle() {
	var id = $("input[name='id']").val();
	var title = $('#title').val();
	var categoryId = $("#categoryId").val();
	var htmlContent = CKEDITOR.instances.htmlContent.getData();
	var url = DYNAMIC_PATH + '/article/updateArticle';
	
	$.ajax({
		type : "POST",
		url : url,
		cache : false,
		data : {
			id:id,
			title:title,
			categoryId:categoryId,
			htmlContent:htmlContent
		},
		dataType : "json",
		success : function(retObj) {

			$('#dlg').dialog('close');
			$('#dg').datagrid('reload');

		},
		error : function(XMLHttpRequest) {

			$.messager.show({ // show error message
				title : 'Error',
				msg : 'Failed of save '+XMLHttpRequest.statusText
			});
		}

	});

}
//==========================================================================remove article
function removeArticle() {

    var checkedItems = $('#dg').datagrid('getChecked');

    var ids = "";

    $.each(checkedItems, function (index, item) {
        ids += item.id + ",";
    });

    if (ids.length = 0) {
        $.messager.show({ // show error message
            title: 'Error',
            msg: 'Please choose the picture to delete! '
        });
        return;
    } else {
        ids = ids.substring(0, ids.length - 1);
    }

    $.messager.confirm("删除", "确认删除吗?", function (r) {
        if (r) {
            $.ajax({
                type: "GET",
                url: DYNAMIC_PATH + '/article/removeArticles/',
                cache: false,
                data: "ids=" + ids,
                dataType: "json",
                success: function (retObj) {

                    if (retObj > 0) {
                      
                        $('#dlg').dialog('close');
                        $('#dg').datagrid('reload');
                    } else {

                    }

                },
                error: function (XMLHttpRequest) {

                    $.messager.show({ // show error message
                        title: 'Error',
                        msg: 'Failed of delete'
                    });
                }

            });

        } else {
            return;
        }
    });





}

// 初始加载
function createTable() {

	var columnsStr = [ [ {
		field : 'ck',
		checkbox : true
	}, {
		field : 'id',
		title : 'ID',
		width : '100'
	}, {
		field : 'title',
		title : '文章标题',
		width : '100'
	}, {
		field : 'categoryId',
		title : '文章类别',
		width : '100'
	}, {
		field : 'createTime',
		title : '创建时间',
		width : '100'
	} ] ];

	var url = DYNAMIC_PATH + "/article/selectArticleListByPage";
	$('#dg').datagrid({
		method : 'get',
		url : url,
		singleSelect : true,
		columns : columnsStr,
		loadMsg : '数据正在加载中,请稍后。。。',
		pagination: true,
		rownumbers : true,
		singleSelect : true,
		selectOnCheck : false,
		checkOnSelect : true

	});

	 //设置分页控件 
    var p = $('#dg').datagrid('getPager');
    $(p).pagination({

        pageSize: 10, //每页显示的记录条数，默认为10 
        pageList: [10], //可以设置每页记录条数的列表 
        beforePageText: '第', //页数文本框前显示的汉字 
        afterPageText: '页    共 {pages} 页',
        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录',
        /*onBeforeRefresh:function(){
            $(this).pagination('loading');
            alert('before refresh');
            $(this).pagination('loaded');
        }*/
    });

}