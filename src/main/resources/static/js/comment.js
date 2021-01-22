//메인페이지 댓글달기
function commentSend(postid) {

    let userid = $("#userid"+postid).val();
    let content = $("#content"+postid).val();

    let param = {
        "postid" : postid, "userid" : userid, "content" : content
    };

    $.ajax({
        url: '/comment/insert',
        type: "POST",
        data: param,
        dataType:'text',
        success: function (data) {
            if (data === 1) {
                $(".content").val("");
                console.log(data);
            }
        },
        error: function (request, status, error) {
            console.log("code:" + request.status + "\n" + "message:" + request.responseText + "\n" + "error:" + error);
        }
    });
}

function commentList(){
    $.ajax({
        url: '/comment/list',
        type: 'GET',
        data: {"id" : id},

    })
}