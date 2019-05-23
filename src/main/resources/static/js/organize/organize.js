$(function () {
    console.log('organize')
    console.log($.session.get("token"))

    var params = {
        "loginName": $("#loginName").val(),
        "password": $("#password").val()
    }

    $.ajax({
        type: "POST",
        async: false,
        url: "/api/organize/listOrganize",
        data: JSON.stringify(params),
        contentType: 'application/json',
        success: function (response) {
            console.log(response.code)
            console.log(response.data.user)
            $.session.set('token', response.data.user.token)
            console.log($.session.get('token'))
            window.location.href = "/admin/dashboard";
        }
    })

})