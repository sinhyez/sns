$(".modal-close").click(function () {
    $("#modal-box").hide();
    location.reload();
})

async function modalFollowing(userid) {

    let page = '/followinglist/' + userid;
    $('#modal-following_list').load(page, function () {
        $("#modal-following_list").show();
    })
}

async function modalfollower(userid) {

    let page = '/followerlist/' + userid;
    $('#modal-follower_list').load(page, function () {
        $("#modal-follower_list").show();
    })

}
function likelist(postid) {

    const page = "/likes/likelist/" + postid;
    $("#modal-like_list").load(page, function () {
        $("#modal-like_list").show();
    })

}

async function posting() {

    let page = '/ipro/post';
    $("#modal-post").load(page, function () {
        $("#modal-post").show();
    })
}

