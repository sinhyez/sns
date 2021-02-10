//좋아요 ajax
function like(postid, userid) {

    let page = '/likes/like/' + postid;
    let param = {
        "postid" : postid, "userid" : userid
    }

    $.ajax({
        url : page,
        method : "POST",
        data : param,
        async : false,
        success : function (data) {
            if (data === 1) {
                location.reload();
            }
        },
        error: function (request, status, error) {
            console.log("code:" + request.status + "\n" + "message:" + request.responseText + "\n" + "error:" + error);
        }

    });

}

//좋아요 취소 ajax
function unlike(postid, userid) {

    let page = '/likes/unlike/' + postid;
    let param = {
        "postid" : postid, "userid" : userid
    }

    $.ajax({
        url: page,
        method: "DELETE",
        data: param,
        async : false,
        success : function (data) {
            if (data === -1){
                location.reload();
            }

        },
        error: function (request, status, error) {
            console.log("code:" + request.status + "\n" + "message:" + request.responseText + "\n" + "error:" + error);
        }
    });
}
