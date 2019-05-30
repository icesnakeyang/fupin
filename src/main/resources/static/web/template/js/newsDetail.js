/**
 * Created by Administrator on 2019/5/19.
 */
var thisURL = document.URL;
var postVal =thisURL.split('?')[1];
var getVal= postVal.split("=")[1];
$(function () {
    $.ajax({
        url: "mock.json",//json文件位置
        type: "GET",//请求方式为get
        dataType: "json", //返回数据格式为json
        success: function(data) {//请求成功完成后要执行的方法
            //each循环 使用$.each方法遍历返回的数据date
            var FocusNewsHtml = '';
            $.each(data.FocusNews, function(i, item) {
                if(getVal == item.id){
                    FocusNewsHtml +='<h4 class="title">'+item.newsTitle+'</div>';
                    FocusNewsHtml +='<div class="publicTime">'+item.publicTime+'</div>';
                    FocusNewsHtml +='<div class="con">'+item.newsCon+'</div>';
                }
            })
            $('#news').append(FocusNewsHtml)
        }
    })
    $.ajax({
        url: "mock.json",//json文件位置
        type: "GET",//请求方式为get
        dataType: "json", //返回数据格式为json
        success: function(data) {//请求成功完成后要执行的方法
            //each循环 使用$.each方法遍历返回的数据date
            var FocusNewsHtml = '';
            $.each(data.ProvincialNews, function(i, item) {
                if(getVal == item.id){
                    FocusNewsHtml +='<h4 class="title">'+item.newsTitle+'</div>';
                    FocusNewsHtml +='<div class="publicTime">'+item.publicTime+'</div>';
                    FocusNewsHtml +='<div class="con">'+item.newsCon+'</div>';
                }
            })
            $('#news').append(FocusNewsHtml)
        }
    })
    $.ajax({
        url: "mock.json",//json文件位置
        type: "GET",//请求方式为get
        dataType: "json", //返回数据格式为json
        success: function(data) {//请求成功完成后要执行的方法
            //each循环 使用$.each方法遍历返回的数据date
            var FocusNewsHtml = '';
            $.each(data.helpPoorNews, function(i, item) {
                if(getVal == item.id){
                    FocusNewsHtml +='<h4 class="title">'+item.newsTitle+'</div>';
                    FocusNewsHtml +='<div class="publicTime">'+item.publicTime+'</div>';
                    FocusNewsHtml +='<div class="con">'+item.newsCon+'</div>';
                }
            })
            $('#news').append(FocusNewsHtml)
        }
    })
})