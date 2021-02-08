function insert(postid) {
    const ctn = $("#content"+postid);
    const btn = $("#post_btn"+postid);
    $(ctn).on("keyup", function () {
        if (ctn.val() === "") {
            btn.attr('disabled', true);
        }  else {
            btn.removeAttr('disabled', false);
        }
    });
}

//메인페이지 댓글달기
function commentSend(postid) {

    let userid = $("#userid"+postid).val();
    let content = $("#content"+postid).val();
    let page = '/comment/insert'

    let param = {
        "postid" : postid, "userid" : userid, "content" : content
    };

    $.ajax({
        url: page,
        type: "POST",
        data: param,
        dataType:'text',
        async: false,
        success: function (data) {
            if (data === 1) {
                location.reload();
                $(".content").val("");
            }
        },
        error: function (request, status, error) {
            console.log("code:" + request.status + "\n" + "message:" + request.responseText + "\n" + "error:" + error);
        }
    });
}

function commentDlete(commentid) {

    let page = "/comment/delete/" + commentid;

    $.ajax({
        url : page,
        type: "DELETE",
        data: commentid,
        success : function (data) {
            if (data === -1) {
                location.reload();
            }
        },
        error: function (request, status, error) {
            console.log("code:" + request.status + "\n" + "message:" + request.responseText + "\n" + "error:" + error);
        }
    });
}
