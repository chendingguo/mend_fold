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
            var gridHtml = '<ul data-role="listview" data-inset="true"  class="ui-listview ui-listview-inset ui-corner-all ui-shadow">';
            $.each(data, function (row, obj) {

                row = row + 1;
                var imgUrl = "";

                if (obj.pictureList != null && obj.pictureList.length > 0) {
                    imgUrl = ROOT_URL + obj.pictureList[0].path;
                }


                var productDetailUrl = "product_detail.html?id=" + obj.id;

                var id = obj.id;

                gridHtml = gridHtml.concat('<li class="ui-li-has-thumb">');
                gridHtml = gridHtml.concat('<a href="' + productDetailUrl + '" class="ui-btn ui-btn-icon-right ui-icon-carat-r" rel = "external">');
                gridHtml = gridHtml.concat('<img  src="' + imgUrl + '" height="80" />');
                gridHtml = gridHtml.concat('<h2>' + obj.name + '</h2>');
                gridHtml = gridHtml.concat('<p>' + obj.introduce + '</p>');


                gridHtml = gridHtml.concat('</a> </li>');



            });

            gridHtml.concat('</ul>');
            $("#product_list").append(gridHtml);
            currentPage = parseInt(currentPage) + 1;
            $("#more_button").attr("num", currentPage);

        },
        error: function () {

            alert(" 网络异常！ ");
        }


    });
}