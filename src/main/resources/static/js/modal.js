
function modalFollowing(userid) {

    const page = '/followinglist/' + userid;
    $('#modal-following_list').load(page, function () {
        $("#modal-following_list").show();
        $(".modal-close").on("click", function () {
            $("#modal-following_list").hide();
        })
    })
}

function modalfollower(userid) {

    const page = '/followerlist/' + userid;
    $('#modal-follower_list').load(page, function () {
        $("#modal-follower_list").show();
        $(".modal-close").on("click", function () {
            $("#modal-follower_list").hide();
        })
    })
}

function likelist(postid) {

    const page = "/likes/likelist/" + postid;
    $("#modal-like_list").load(page, function () {
        $("#modal-like_list").show();
        $("#overlay").on("click", function () {
            $("#modal-like_list").hide();
        })
        $(".modal-close").on("click", function () {
            $("#modal-like_list").hide();
        })
    })
}

function profileImgEdit(userid, loginid) {

    if(userid === loginid) {
        const page = "/ipro/user/img_insert";
        $("#modal-img_insert").load(page, function () {
            $("#modal-img_insert").show();
        })
    }

}
function profileImgEdit2(userid) {

    const page = "/ipro/user/img_insert";
    $("#modal-img_insert").load(page, function () {
        $("#modal-img_insert").show();
    })

}

function posting() {

    const page = '/ipro/post';
    $("#modal-post").load(page, function () {
        $("#modal-post").show();
    })
}

