$(function () {
    $("#btLogin").click(function () {
        console.log(1)
        $.ajax({
            type:"POST",
            async:false,
            url:"/api/user/login",
            // data:$("#from_login").serialize(),
            data:JSON.stringify("sdlkfj"),
            dataType:'application/json',
            success:function (response) {
                console.log(response)
            }
        })
    })
})