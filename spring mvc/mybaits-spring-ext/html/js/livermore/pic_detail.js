var url = DYNAMIC_PATH + "/picture/selectPictureListByPage";



function refreshPage() {

    var Request = new Object();
    Request = GetRequest();
    var id = Request['id'];
    url = url.concat('?id=').concat(id);
    $.getJSON(url, function (result) {

        var row = 1;

        $.each(result, function (i, obj) {

            var imgUrl = ROOT_URL + obj.path;
            var contentHtml = obj.name;
            var imgHtml = '<img src="' + imgUrl + '" width="100%"/ > ';

            $("#name").html(obj.name);
            $("#content").append(imgHtml);
            if (obj.remark) {
                var remarkHtml = '<h3>' + obj.remark + '</h3>';
                $("#content").append(remarkHtml);
            }


        });

    });
}