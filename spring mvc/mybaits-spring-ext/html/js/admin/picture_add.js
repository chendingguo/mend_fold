/*
 * jQuery File Upload Plugin JS Example 8.9.1
 * https://github.com/blueimp/jQuery-File-Upload
 *
 * Copyright 2010, Sebastian Tschan
 * https://blueimp.net
 *
 * Licensed under the MIT license:
 * http://www.opensource.org/licenses/MIT
 */

/* global $, window */

$(function () {

    // get product id
    var Request = new Object();
    Request = GetRequest();
    var productId = Request['productId'];

    var url = "/mybaits-spring-ext/resources/picture/savePicture";
    var remark = window["remark"];
    // Initialize the jQuery File Upload widget:
    $('#fileupload').fileupload({

        // Uncomment the following to send cross-domain cookies:
        // xhrFields: {withCredentials: true},
        url: url,
        maxNumberOfFiles: 5,
        maxFileSize: 2097152,
        formData: {

            productId: productId

        },
        done: function (e, data) {
            // 保存 简介

            savePictureRemark(data);
        },
        fail: function (e, data) {
            // 错误提示

            alert('Fail!' + e);
        }

    });

    var savePictureRemark = function (data) {
        var url = "/mybaits-spring-ext/resources/picture/savePictureRemark";
        var pictureId = data.result.pictureId;
        var remark = $("#remark").val();
        var aj = $.ajax({
            url: url,
            data: {
                id: pictureId,
                remark: remark
            },
            type: 'get',
            cache: false,
            dataType: 'json',
            success: function (data) {

                alert("图片保存成功");
                $("#remark").remove();

            },
            error: function () {

                alert(" 异常！ ");
            }
        })
    };

});