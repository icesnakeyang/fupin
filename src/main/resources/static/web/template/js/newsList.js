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
            url: "mock.json",//json�ļ�λ��
            type: "GET",//����ʽΪget
            dataType: "json", //�������ݸ�ʽΪjson
            success: function(data) {//����ɹ���ɺ�Ҫִ�еķ���
                //eachѭ�� ʹ��$.each�����������ص�����date
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
            url: "mock.json",//json�ļ�λ��
            type: "GET",//����ʽΪget
            dataType: "json", //�������ݸ�ʽΪjson
            success: function(data) {//����ɹ���ɺ�Ҫִ�еķ���
                //eachѭ�� ʹ��$.each�����������ص�����date
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