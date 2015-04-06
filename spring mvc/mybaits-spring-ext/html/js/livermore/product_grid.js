var url = DYNAMIC_PATH + "/product/selectProductListByPage";
var currentPage = 1;
var Request = new Object();
Request = GetRequest();
var categoryId = Request['categoryId'];
if (categoryId != null) {
    url = url + "?categoryId=" + categoryId;
}


function loadMore() {

    var currentPage = $("#more_button").attr("num");

    var aj = $.ajax({
        url: url,
        data: {
            currentPage: currentPage
        },
        type: 'get',
        cache: false,
        dataType: 'json',
        success: function (data) {
            if (data.length == 0) {
                alert("没有更多图片了!");
                $("#dialog").show();
                return;
            }
            $.each(data, function (row, obj) {
                row = row + 1;
                var imgUrl = "";

                if (obj.pictureList != null && obj.pictureList.length > 0) {
                    imgUrl = ROOT_URL + obj.pictureList[0].path;
                }

                var productDetailUrl = "product_detail.html?id=" + obj.id;
                var gridHtml = "";
                var id = obj.id;
                if (row % 2 == 1) {
                    gridHtml = '<div class="ui-block-a">';
                    gridHtml = gridHtml.concat('<div class="ui-bar ui-bar-a" style="height:150px">');
                } else {
                    gridHtml = '<div class="ui-block-b">';
                    gridHtml = gridHtml.concat('<div class="ui-bar ui-bar-a" style="height:150px">');
                }
                gridHtml = gridHtml.concat('<a href="' + productDetailUrl + '" data-transition="pop"  rel = "external" >');
                gridHtml = gridHtml.concat('<h5>' + obj.name + '</h5><br>');
                if (imgUrl != "") {
                    gridHtml = gridHtml.concat('<img src="' + imgUrl + '" height="100%" width="100%" />');
                } else {
                    gridHtml = gridHtml.concat('封面暂未设置!');
                }

                gridHtml = gridHtml.concat('</a>');
                gridHtml = gridHtml.concat('</div>');
                gridHtml = gridHtml.concat('</div > ');
                $("#product_grid").append(gridHtml);

            });
            currentPage = parseInt(currentPage) + 1;
            $("#more_button").attr("num", currentPage);

        },
        error: function () {

            alert(" 网络异常！ ");
        }


    });
}