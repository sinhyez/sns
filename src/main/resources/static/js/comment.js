
let pageurl = '/comment';


function commnetInsert(param) {

    $.ajax({
        url : pageurl,
        type : 'post',
        data : param,
        dataType : 'json',
        async : false,
        cache : false,
        success : function (data) {
            if (data === 1) {
                $('#content').val("");
                console.log(data);
            }
        }
        ,error:function(request,status,error){
            console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
        }
    });
}

$("#cInsertbtn").on("click",function () {

    const postid = $("#postid").val();
    const userid = $("#userid").val();
    const content = $("#content").val();



    const param = {"postid": postid, "userid": userid, "content": content};

    // let param = $(".add_comment_area").serialize();
    console.log(param);
    commnetInsert(param);
})


// $(function () {
//     $("#cInsertbtn").on("click", function () {
//
//         let postid = $("#postid").val();
//         let userid = $("#userid").val();
//         let content = $("#content").val();
//
//         let param = {"postid" : postid, "userid" : userid, "content" : content};
//
//         console.log(postid, userid, content);
//
//         if (content === "") {
//
//         } else {
//             $.ajax({
//                 url : '/comment/cInsert',
//                 type : 'post',
//                 dataType : 'text',
//                 data : param,
//                 success : function (data) {
//                     if (data === 1){
//                         console.log("result : " + data);
//                     }
//                 }
//             });
//         }
//     });
// });