var uploadUrl = DYNAMIC_PATH + "/picture/savePicture";
$(function() {
	/* activate the plugin */
	$('#fileupload').fileupload({
	    url:uploadUrl,//文件上传地址，当然也可以直接写在input的data-url属性内
	    formData:{param1:"test1",param2:"test2"},//如果需要额外添加参数可以在这里添加
	    add: function (e, data) {
            data.context = $('<button/>').text('Upload')
                .appendTo(document.body)
                .click(function () {
                    $(this).replaceWith($('<p/>').text('Uploading...'));
                    data.submit();
                });
        },
	    done:function(e,data){
	    	$.each(data.result.files, function (index, file) {
	    		alert(file.name);
                $('<p/>').text(file.name).appendTo(document.body);
            });
	    },
	    progressall: function (e, data) {
	        var progress = parseInt(data.loaded / data.total * 100, 10);
	        $('#progress .bar').css(
	            'width',
	            progress + '%'
	        );
	    }
	    
	    
	})

});