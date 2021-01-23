function modalFollowing(userid) {

    let page = '/followinglist/' + userid;
    $('#modal-following').show(page);
    console.log($('#modal-following').load(page));

}