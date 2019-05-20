$(function () {
    $("#btLogin").click(function () {
        console.log(1)
        console.log($("#loginName").val())
        console.log($("#password").val())
        var params = {
            "loginName": $("#loginName").val(),
            "password": $("#password").val()
        }
        $.ajax({
            type: "POST",
            async: false,
            url: "/api/user/login",
            data: JSON.stringify(params),
            contentType: 'application/json',
            success: function (response) {
                console.log(response.code)
                console.log(response.data.user)
            }
        })
    })
})