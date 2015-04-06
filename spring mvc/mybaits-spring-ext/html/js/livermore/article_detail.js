var url = DYNAMIC_PATH + "/article/selectArticleListById";

function refreshPage() {

	var Request = new Object();
	Request = GetRequest();
	var id = Request['id'];
	url = url.concat('?id=').concat(id);
	$.getJSON(url, function(result) {

	
		$("#name").html(result.title);
     
		if (result.htmlContent) {
			$("#htmlContent").append(result.htmlContent);
		}

	});
}