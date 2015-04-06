function savePicture() {
    var url = DYNAMIC_PATH + '/picture/savePicture';

    $('#fm').form('submit', {
        url: url,
        onSubmit: function (data) {

            if (data == '0') {
                $.messager.show({
                    // show error message
                    title: 'Error',
                    msg: 'Failed of insert'
                });
            } else if (data == '2') {
                $.messager.show({
                    // show error message
                    title: 'Error',
                    msg: 'Failed of upload file'
                });
            }
        },
        success: function (data) {
            $('#dlg').dialog('close');
            $('#dg').datagrid('reload');
        }
    });

}

function updatePicture() {
    var id = $("input[name='id']").val();
    var remark = $("#remark").val();
    var orderNum = $("#orderNum").val();
    $.ajax({
        type: "GET",
        url: DYNAMIC_PATH + '/picture/updatePicture',
        cache: false,
        data: {
            id: id,
            remark: remark,
            orderNum:orderNum

        },
        dataType: "json",
        success: function (retObj) {

            if (retObj > 0) {
             
                $('#dlg').dialog('close');
                $('#dg').datagrid('reload');
            } else {
                $.messager.show({ // show error message
                    title: 'Error',
                    msg: 'Failed of update'
                });
            }

        },
        error: function (XMLHttpRequest) {

            $.messager.show({ // show error message
                title: 'Error',
                msg: 'Failed of update'
            });
        }

    });

}

function showPicture(path) {
    $('#show-pic-dlg').dialog('open').dialog('setTitle', '查看图片');
    var imgUrl = ROOT_URL + path;
    $("#preview_pic").attr("src", imgUrl);
}

function closePictureWin() {
    $('#show-pic-dlg').dialog('close');

}

function createTable() {

    var columnsStr = [[
        {
            field: 'ck',
            checkbox: true
        }, {
            field: 'id',
            title: 'ID',
            width: '80'
 }, {
            field: 'productId',
            title: '产品ID',
            width: '80'
 }, {
            field: 'name',
            title: '产品名称',
            width: '80'
 }, {
            field: 'remark',
            title: '图片描述',
            width: '200'
 }, {
            field: 'path',
            title: '图片路径',
            width: '150',
            formatter: function (value, row, index) {
                return '<a href="javascript:void(0)" onmouseOut="closePictureWin()" onmouseOver=showPicture("' + row.path + '")>' + row.path + '</a>';
            }
 }, {
            field: 'status',
            title: '是否封面',
            width: '150',
            formatter: function (value, row, index) {
                if (row.status == 1) {
                    return '<font color="red"> 是</font>';
                } else {
                    return '';
                }
            }
 },{
     field: 'orderNum',
     title: '图片顺序',
     width: '150',
   
     
}]];

    var url = DYNAMIC_PATH + "/picture/selectPictureListByPage";
    $('#dg').datagrid({
        method: 'get',
        url: url,
        singleSelect: true,
        fitColumns: false,
        columns: columnsStr,
        loadMsg: '数据正在加载中,请稍后。。。',
        pagination: true,
        rownumbers: true,
        singleSelect: true,
        selectOnCheck: false,
        checkOnSelect: true

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

    // $('#dlg').resizeDataGrid(20, 20, 20, 20);

}

function newPicture() {
    $('#dlg').dialog('open').dialog('setTitle', '新加图片');
    $('#fm').form('clear');
    $('#save-picture').show();
    $('#update-picture').hide();

}


function editPicture() {
    var row = $('#dg').datagrid('getSelected');
    $('#dlg').dialog('open').dialog('setTitle', '编辑图片');
    $('#fm').form('clear');
    $('#save-picture').hide();
    $('#update-picture').show();
    $("[name='id']").val(row.id);
    $("[name='orderNum']").val(row.orderNum);
    $("[name='remark']").val(row.remark);


}

function removePicture() {

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
                url: DYNAMIC_PATH + '/picture/removePictures/',
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
/**
 * 设置为封面
 */
function setAsCover() {
    var row = $('#dg').datagrid('getSelected');
    var ids = "";
    if (row == null) {
        $.messager.show({ // show error message
            title: 'Error',
            msg: 'Please choose the host to delete! '
        });
    }

    $.ajax({
        type: "GET",
        url: DYNAMIC_PATH + '/picture/setAsCover',
        cache: false,
        data: "ids=" + row.id,
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
                msg: 'Failed of set cover'
            });
        }

    });

}

function previewImage(file) {
    var MAXWIDTH = 200;
    var MAXHEIGHT = 200;
    var div = document.getElementById('preview');
    if (file.files && file.files[0]) {
        div.innerHTML = '<img id=imghead>';
        var img = document.getElementById('imghead');
        img.onload = function () {
            var rect = clacImgZoomParam(MAXWIDTH, MAXHEIGHT, img.offsetWidth,
                img.offsetHeight);
            img.width = rect.width;
            img.height = rect.height;
            img.style.marginLeft = rect.left + 'px';
            img.style.marginTop = rect.top + 'px';
        }
        var reader = new FileReader();
        reader.onload = function (evt) {
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
        div.innerHTML = "<div id=divhead style='width:" + rect.width + "px;height:" + rect.height + "px;margin-top:" + rect.top + "px;margin-left:" + rect.left + "px;" + sFilter + src + "\"'></div>";
    }
}

function clacImgZoomParam(maxWidth, maxHeight, width, height) {
    var param = {
        top: 0,
        left: 0,
        width: width,
        height: height
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