function makeUser(a, b) {
    $.ajax({
        url : 'Controller?command=updateReview',
        data : {
            id : a,
            review: b
        },
        async: false,
        type: 'POST',
        success :
            function() {
                $('#roleResponse').text("USER").delay();
            }
    });
}

function makeAdmin(a, b) {
    $.ajax({
        url : 'Controller?command=updateReview',
        data : {
            id : a,
            review: b
        },
        async: false,
        type: 'POST',
        success :
            function() {
                $('#roleResponse').text("ADMINISTRATOR").delay();
            }
    });
}

function ban(a) {
    $.ajax({
        url : 'Controller?command=changeUserIsBanned',
        data : {
            id : a
        },
        async: false,
        type: 'POST',
        success :
            function() {
                if(document.getElementById("banUnban").textContent.trim() === "ban") {
                    document.getElementById("banUnban").textContent = "unban";
                    $('#isBannedResponse').text("true").delay();
                } else {
                    document.getElementById("banUnban").textContent = "ban";
                    $('#isBannedResponse').text("false").delay();
                }
            }
    });
}

// function unban(a) {
//     $.ajax({
//         url : 'Controller?command=banUser',
//         data : {
//             id : a
//         },
//         async: false,
//         type: 'POST',
//         success :
//             function() {
//                 $('#isBannedResponse').text("false").delay();
//                 alert(222);
//             }
//     });
// }

// function ban(a, b) {
//     $.ajax({
//         url : 'Controller?command=banUser',
//         data : {
//             id : a,
//             isBanned: b
//         },
//         async: false,
//         type: 'POST',
//         success :
//             function() {
//                 $('#isBannedResponse').text("true").delay();
//                 alert(111);
//             }
//     });
// }
//
// function unban(a, b) {
//     $.ajax({
//         url : 'Controller?command=banUser',
//         data : {
//             id : a,
//             isBanned: b
//         },
//         async: false,
//         type: 'POST',
//         success :
//             function() {
//                 $('#isBannedResponse').text("false").delay();
//                 alert(222);
//             }
//     });
// }

function her() {
    alert(5555);
    $.ajax({
        url : 'Controller?command=banUser',
        data : {
            id : 333
        },
        async: false,
        type: 'POST',
        success :
            function() {
                alert(333);
            }
    });
}