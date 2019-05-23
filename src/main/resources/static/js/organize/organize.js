$(function () {
    console.log('organize')
    console.log($.session.get("token"))

    var params = {
        "pageIndex": 0,
        "pageSize": 200
    }

    $.ajax({
        type: "POST",
        async: false,
        url: "/api/organize/listOrganize",
        data: JSON.stringify(params),
        contentType: 'application/json',
        beforeSend: function(xhr) {
            xhr.setRequestHeader("token", $.session.get("token"));
        },
        success: function (response) {
            console.log(response.code)
            console.log(response.data)
            // $.session.set('token', response.data.user.token)
            // console.log($.session.get('token'))
            // window.location.href = "/admin/dashboard";
        }
    })

})