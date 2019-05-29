$(function() {
	speciaWork();
	$('.DHBox>li').click(function(){
		$(this).addClass('active').siblings('').removeClass('active');
	})
})
function speciaWork() {
	$.ajax({
		url: "mock.json", //json文件位置
		type: "GET", //请求方式为get
		dataType: "json", //返回数据格式为json
		timeout:1000, 
        error:function(){ 
            alert("ajax error"); 
        }, 
		success: function(data) { //请求成功完成后要执行的方法
			//each循环 使用$.each方法遍历返回的数据date
			var FocusNewsHtml = '';
			$.each(data.FocusNews, function(i, item) {
				FocusNewsHtml += '<li><a href="newsDetail.html?newsId=' + item.id + '" class="newsTit">' + item.newsTitle +
					'</a>';
				FocusNewsHtml += '<span class="newsDate">' + item.publicTime + '</span>';
				FocusNewsHtml += '</li>';
			})
			$('.speciaWork').html(FocusNewsHtml);
		}
	})
}
