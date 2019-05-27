$(function () {
    $("#btLogin").click(function () {
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
                $.session.set('token', response.data.user.token)
                $.session.set('loginName', response.data.user.loginName)
                console.log($.session.get("loginName"))
                console.log($.session.get('token'))
                window.location.href = "/admin/dashboard";
            }
        })
    })
})