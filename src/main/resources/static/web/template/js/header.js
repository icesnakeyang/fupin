/**
 * Created by Administrator on 2019/5/19.
 */
$(function(){
    header();
})
function header(){
    var headerHtml='';
    headerHtml +='<div class="logoWrap"><div class="logoSearchWrap">';
    headerHtml +='<div class="logoBox fl"><a href="javascript:;"><img src="/web/common/images/logo.png"></a></div>';
    headerHtml +='<div class="searchBox fr"><input type="text" placeholder="请输入关键字查询"><img src="/web/common/images/searchBtn.png">';
    headerHtml +='</div></div></div>';
    headerHtml +='<div class="navBox"><ul class="navBar cl">';
    headerHtml +='<li class="fl"><a href="javascript:;">首页</a></li>';
    headerHtml +='<li class="fl"><a href="javascript:;">新闻中心</a></li>';
    headerHtml +='<li class="fl"><a href="javascript:;">甘孜扶贫</a></li>';
    headerHtml +='<li class="fl"><a href="javascript:;">专项工作</a></li>';
    headerHtml +='<li class="fl"><a href="javascript:;">乡镇动态</a></li>';
    headerHtml +='<li class="fl"><a href="javascript:;">公示公告</a></li>';
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