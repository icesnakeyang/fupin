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
                console.log(data)
                var FocusNewsHtml = '';
                $.each(data.FocusNews, function(i, item) {
                    FocusNewsHtml +='<li><a href="newsDetail.html?newsId='+item.id+'" class="newsTit">'+item.newsTitle+'</a>';
                    FocusNewsHtml += '<span class="newsDate">' + item.publicTime + '</span>';
                    FocusNewsHtml +='</li>';
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
                    FocusNewsHtml +='<li><a href="newsDetail.html?newsId='+item.id+'" class="newsTit">'+item.newsTitle+'</a>';
                    FocusNewsHtml += '<span class="newsDate">' + item.publicTime + '</span>';
                    FocusNewsHtml +='</li>';
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
                    FocusNewsHtml +='<li><a href="newsDetail.html?newsId='+item.id+'" class="newsTit">'+item.newsTitle+'</a>';
                    FocusNewsHtml += '<span class="newsDate">' + item.publicTime + '</span>';
                    FocusNewsHtml +='</li>';
                })
                $('#news').append(FocusNewsHtml)
            }
        })

})