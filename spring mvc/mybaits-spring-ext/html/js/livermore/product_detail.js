var url = DYNAMIC_PATH + "/product/selectProductById";

function refreshPage() {

    var Request = new Object();
    Request = GetRequest();
    var id = Request['id'];
    url = url.concat('/').concat(id);
    $.getJSON(url, function (result) {

        var row = 1;
        $("#name").html(result.name);
        if (result.introduce) {
            var introduceHtml = '<h3>简介: ' + result.introduce + '</h3><h3>价格: ' + result.remark + '</h3>';
            $("#introduct_div").append(introduceHtml);
        }
        if (result.pictureList != null) {
            $.each(result.pictureList, function (i, obj) {

                var imgUrl = ROOT_URL + obj.path;
                var contentHtml = obj.name;

                var imgHtml = ' <img src = "' + imgUrl + '" width = "100%" /> <br> ';

                if (obj.remark != null) {
                    $("#content").append(' <br> ' + obj.remark + "");
                }

                $("#content").append(imgHtml);


            });
        }

    });
}