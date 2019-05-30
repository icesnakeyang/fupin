/**
 * Created by Administrator on 2019/5/18.
 */
var noMore = '<span style="display: block;text-align: center;font-size: 14px;color: #999">暂无数据</span>';
$(function(){
    loadFocusNews();
    loadProvincialNews();
    helpPoorNews();
    loadPictureTopic();
    //tabs
    $('.tabs li').mouseover(function(){
        var index = $(this).index();
        $(this).addClass('active').siblings().removeClass('active')
        $('.newsBox>ul').eq(index).show().siblings().hide()
    })
    $('.swiper-container1').mouseover(function(){
        $('.swiper-button-next,.swiper-button-prev').show()
    })
    $('.swiper-container1').mouseout(function(){
        $('.swiper-button-next,.swiper-button-prev').hide()
    })
    //跳转至列表页
    $(document).on('click','.moreBtn',function(){
        var getVal =$(this).attr("id");
        window.location.href="newsList.html?neswList="+getVal;
        //window.location.href("FocusNewsList.html?cc="+getVal);
    })
})
//图片专题
function loadPictureTopic(){
    $.ajax({
        url: "mock.json",//json文件位置
        type: "GET",//请求方式为get
        dataType: "json", //返回数据格式为json
        success: function(data) {//请求成功完成后要执行的方法
            //each循环 使用$.each方法遍历返回的数据date
            var PictureTopicHtml ="";
            $.each(data.PictureTopic, function(i, item) {
                PictureTopicHtml += '<div class="swiper-slide"><img src="' + item.src + '">' +
                '<div class="picTitle">' +
                '<div class="title">'+ item.title +'</div>' +
                '<div class="divBg"></div>' +
                '</div>' +
                '</div>';
            })
            $('.PictureTopicBox').html(PictureTopicHtml)
            var swiper = new Swiper('.swiper-container2', {
                autoplay:{
                    delay: 2500,
                    disableOnInteraction: false,
                },
                loop: true,
                preventClicks: true,
                preventClicksPropagation: true,
                paginationClickable: false,
                autoplayDisableOnInteraction: false,
                speed: 400,
                pagination: {
                    el: '.swiper-pagination2',
                    clickable: true,
                    renderBullet: function(index, className) {
                        return '<span class="' + className + '">' + (index + 1) + '</span>';
                    },
                },
            });
        }
    })
}
//焦点新闻
function loadFocusNews(){
    $.ajax({
        url: "mock.json",//json文件位置
        type: "GET",//请求方式为get
        dataType: "json", //返回数据格式为json
        success: function(data) {//请求成功完成后要执行的方法
            //each循环 使用$.each方法遍历返回的数据date
            var length = data.FocusNews.length
            var FocusNewsHtml = '';
            if(length == 0){
                FocusNewsHtml += noMore;
            }else if(length < 9 ){
                for (var i = 0; i < length; i++){
                    var item = data.FocusNews[i];
                    FocusNewsHtml +='<li ><a href="newsDetail.html?newsId='+item.id+'" class="newsTit">'+item.newsTitle+'</a>';
                    FocusNewsHtml += '<span class="newsDate">' + item.publicTime + '</span>';
                    FocusNewsHtml +='</li>';
                }
            }else{
                for (var i = 0; i < 8; i++){
                    var item = data.FocusNews[i];
                    FocusNewsHtml +='<li >';
					FocusNewsHtml +='<a href="newsDetail.html?newsId='+item.id+'" class="newsTit">'+item.newsTitle+'</a>';
                    FocusNewsHtml += '<span class="newsDate">' + item.publicTime + '</span>';
                    FocusNewsHtml +='</li>';
                }
                FocusNewsHtml +='<button id="1" class="moreBtn">更多+</button>';
            }
            $('.FocusNews').html(FocusNewsHtml)
			//$.each(data.FocusNews, function(i, item) {
			//	var str = '122121<div>' + item.newsContent + '�21212121Ա�' + item.publicTime + '</div>';
			//	document.write(str);
			//})
        }
    })
}
//省州新闻
function loadProvincialNews(){
    $.ajax({
        url: "mock.json",//json文件位置
        type: "GET",//请求方式为get
        dataType: "json", //返回数据格式为json
        success: function(data) {//请求成功完成后要执行的方法
            //each循环 使用$.each方法遍历返回的数据date
            console.log(data.ProvincialNews.length)
            var length = data.ProvincialNews.length
            var ProvincialNewsHtml = '';
            if(length == 0){
                ProvincialNewsHtml += noMore;
            }else if(length < 9 ){
                for (var i = 0; i < length; i++){
                    var item = data.ProvincialNews[i]
                    ProvincialNewsHtml +='<li><a href="newsDetail.html?newsId='+item.id+'" class="newsTit">'+item.newsTitle+'</a>';
                    ProvincialNewsHtml += '<span class="newsDate">' + item.publicTime + '</span>';
                    ProvincialNewsHtml +='</li>';
                }
            }else{
                for (var i = 0; i < 8; i++){
                    var item = data.ProvincialNews[i]
                    ProvincialNewsHtml +='<li><a href="newsDetail.html?newsId='+item.id+'"  class="newsTit">'+item.newsTitle+'</a>';
                    ProvincialNewsHtml += '<span class="newsDate">' + item.publicTime + '</span>';
                    ProvincialNewsHtml +='</li>';
                }
                ProvincialNewsHtml +='<button  id="2" class="moreBtn">更多+</button>';
            }
            $('.ProvincialNews').html(ProvincialNewsHtml)
        }
    })
}
//扶贫新闻
function helpPoorNews(){
    $.ajax({
        url: "mock.json",//json文件位置
        type: "GET",//请求方式为get
        dataType: "json", //返回数据格式为json
        success: function(data) {//请求成功完成后要执行的方法
            //each循环 使用$.each方法遍历返回的数据date
            console.log(data.helpPoorNews.length)
            var length = data.helpPoorNews.length
            var helpPoorNewsHtml = '';
            if(length == 0){
                helpPoorNewsHtml += noMore;
            }else if(length < 9 ){
                for (var i = 0; i < length; i++){
                    var item = data.helpPoorNews[i]
                    helpPoorNewsHtml +='<li><a href="newsDetail.html?newsId='+item.id+'" class="newsTit">'+item.newsTitle+'</a>';
                    helpPoorNewsHtml += '<span class="newsDate">' + item.publicTime + '</span>';
                    helpPoorNewsHtml +='</li>';
                }
            }else{
                for (var i = 0; i < 8; i++){
                    var item = data.helpPoorNews[i]
                    helpPoorNewsHtml +='<li><a href="newsDetail.html?newsId='+item.id+'" class="newsTit">'+item.newsTitle+'</a>';
                    helpPoorNewsHtml += '<span class="newsDate">' + item.publicTime + '</span>';
                    helpPoorNewsHtml +='</a></li>';
                }
                helpPoorNewsHtml +='<button id="3" class="moreBtn">更多+</button>';
            }
            $('.helpPoorNews').html(helpPoorNewsHtml)
        }
    })
}