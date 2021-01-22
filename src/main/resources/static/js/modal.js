function modalFollowing(userid) {

    let page = '/followinglist/' + userid;
    $('#modal-following').load(page);
    console.log($('#modal-following').load(page));

}