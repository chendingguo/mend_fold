var DYNAMIC_PATH = "/mybaits-spring-ext/resources";
var ROOT_URL = "http://192.168.95.1/";




function GetRequest() {
    var url = location.search;
    var theRequest = new Object();
    if (url.indexOf("?") != -1) {
        var str = url.substr(1);
        strs = str.split("&");
        for (var i = 0; i < strs.length; i++) {
            theRequest[strs[i].split("=")[0]] = unescape(strs[i].split("=")[1]);
        }
    }
    return theRequest;
}



$.fn.extend({
    /** 
     * 修改DataGrid对象的默认大小，以适应页面宽度。
     *
     * @param heightMargin
     *            高度对页内边距的距离。
     * @param widthMargin
     *            宽度对页内边距的距离。
     * @param minHeight
     *            最小高度。
     * @param minWidth
     *            最小宽度。
     *
     */
    resizeDataGrid: function (heightMargin, widthMargin, minHeight, minWidth) {
        var height = $(document.body).height() - heightMargin;
        var width = $(document.body).width() - widthMargin;

        height = height < minHeight ? minHeight : height;
        width = width < minWidth ? minWidth : width;

        $(this).datagrid('resize', {
            height: height,
            width: width
        });
    }
});