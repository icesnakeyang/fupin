/**
 * Created by Administrator on 2019/5/19.
 */
$(function(){
    header();
	loadBanner();
	footer();
	friendshipLink();
	//navBar
	$('.navBar li').click(function() {
	    $(this).addClass('active').siblings().removeClass('active')
	})
})
//友情链接
function friendshipLink(){
	var str ='<div class="comTit"><span class="titName">友情链接</span></div>';
	str += '<hr class="lineBr" />';
	str += '<ul class="friendshipLink cl">';
	str += '<li class="fl"><a href="javascript:;">甘孜藏族自治州人民政府</a></li>';
	str += '<li class="fl"><a href="javascript:;">甘孜县人民政府</a></li>';
	str += '<li class="fl"><a href="javascript:;">甘孜人事考试网</a></li>';
	str += '<li class="fl"><a href="javascript:;">四川扶贫网</a></li>';
	str += '<li class="fl"><a href="javascript:;">中国扶贫网</a></li>';
	str += '</ul>';
	$('.friendshipLinkBox').append(str);
}
function header(){
    var headerHtml='';
    headerHtml +='<div class="logoWrap"><div class="logoSearchWrap">';
    headerHtml +='<div class="logoBox fl"><a href="index.html"><img src="/web/common/images/logo.png"></a></div>';
    headerHtml +='<div class="searchBox fr"><input type="text" placeholder="请输入关键字查询"><img src="/web/common/images/searchBtn.png">';
    headerHtml +='</div></div></div>';
    headerHtml +='<div class="navBox"><ul class="navBar cl">';
    headerHtml +='<li class="fl index"><a href="index.html">首页</a></li>';
    headerHtml +='<li class="fl newsCenter"><a href="newsCenter.html">新闻中心</a></li>';
    headerHtml +='<li class="fl helpPoor"><a href="ganziCounty.html">甘孜扶贫</a></li>';
    headerHtml +='<li class="fl specialWork"><a href="specialWork.html">专项工作</a></li>';
    headerHtml +='<li class="fl townsActive"><a href="poverty.html">乡镇动态</a></li>';
    headerHtml +='<li class="fl noticePublicity"><a href="javascript:;">公示公告</a></li>';
    headerHtml +='</ul></div>';
    headerHtml +='<div class="bannerBox">';
    headerHtml +='<div class="swiper-container swiper-container1 swiper-no-swiping">';
    headerHtml +='<div class="swiper-wrapper bannerPicBox"></div>';
    headerHtml +='<div class="swiper-pagination swiper-pagination1"></div>';
    headerHtml +='<div class="swiper-button-next"></div>';
    headerHtml +='<div class="swiper-button-prev"></div>';
    headerHtml +='</div></div>';
    $('.header').append(headerHtml)
}
//footer
function footer(){
    var footerHtml = '';
    footerHtml += '<div style="padding-top: 20px"><a href="javascript:;">版权所有：甘孜藏族自治州脱贫攻坚&copy;2002-2019</a></div>';
    footerHtml += '<div><a href="javascript:;">川公网安备51079002110064</a></div>';
    footerHtml += '<div><a href="javascript:;">蜀ICP备06016397号</a></div>';
    $('.footer').append(footerHtml)
}
//banner
function loadBanner(){
    $.ajax({
        url: "mock.json",//json文件位置
        type: "GET",//请求方式为get
        dataType: "json", //返回数据格式为json
        success: function(data) {//请求成功完成后要执行的方法
            //each循环 使用$.each方法遍历返回的数据date
            var bannerPicHtml ="";
            $.each(data.bannerPic, function(i, item) {
                bannerPicHtml += '<div class="swiper-slide"><img src="' + item.pic + '"></div>';
                $('.bannerPicBox').html(bannerPicHtml)
            })
            var swiper = new Swiper('.swiper-container1', {
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
                    el: '.swiper-pagination1',
                    clickable: true,
                },
                navigation: {
                    nextEl: '.swiper-button-next',
                    prevEl: '.swiper-button-prev',
                },
            })
        }
    })
}