$(function () {
    console.log(1)
    $("#btLogin").click(function () {
        console.log('click')
        var params = {
            "loginName": $("#loginName").val(),
            "password": $("#password").val()
        }

        console.log(params)

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