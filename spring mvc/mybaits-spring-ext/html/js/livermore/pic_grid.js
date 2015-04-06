var url = DYNAMIC_PATH + "/picture/selectPictureListByPage";
var currentPage = 1;


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
                var imgUrl = ROOT_URL + obj.path;
                var picDetailUrl = "pic_detail.html?id=" + obj.id;
                var gridHtml = "";
                var id = obj.id;
                if (row % 2 == 1) {
                    gridHtml = '<div class="ui-block-a">';
                    gridHtml = gridHtml.concat('<div class="ui-bar ui-bar-a" style="height:150px">');
                } else {
                    gridHtml = '<div class="ui-block-b">';
                    gridHtml = gridHtml.concat('<div class="ui-bar ui-bar-a" style="height:150px">');
                }
                gridHtml = gridHtml.concat('<a href="' + picDetailUrl + '" data-transition="pop"  rel = "external" >');
                gridHtml = gridHtml.concat('<img src="' + imgUrl + '" height="140"/>');
                gridHtml = gridHtml.concat('</a>');
                gridHtml = gridHtml.concat('</div>');
                gridHtml = gridHtml.concat('</div > ');
                $("#pic_grid").append(gridHtml);

            });
            currentPage = parseInt(currentPage) + 1;
            $("#more_button").attr("num", currentPage);

        },
        error: function () {

            alert(" 异常！ ");
        }


    });
}