/**
 * Created by Administrator on 2019/5/19.
 */
var thisURL = document.URL;
var postVal =thisURL.split('?')[1];
var getVal= postVal.split("=")[1];
$(function () {
    $.ajax({
        url: "mock.json",//json�ļ�λ��
        type: "GET",//����ʽΪget
        dataType: "json", //�������ݸ�ʽΪjson
        success: function(data) {//����ɹ���ɺ�Ҫִ�еķ���
            //eachѭ�� ʹ��$.each�����������ص�����date
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
        url: "mock.json",//json�ļ�λ��
        type: "GET",//����ʽΪget
        dataType: "json", //�������ݸ�ʽΪjson
        success: function(data) {//����ɹ���ɺ�Ҫִ�еķ���
            //eachѭ�� ʹ��$.each�����������ص�����date
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
        url: "mock.json",//json�ļ�λ��
        type: "GET",//����ʽΪget
        dataType: "json", //�������ݸ�ʽΪjson
        success: function(data) {//����ɹ���ɺ�Ҫִ�еķ���
            //eachѭ�� ʹ��$.each�����������ص�����date
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