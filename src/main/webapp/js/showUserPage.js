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

function ban(a, b) {
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
                $('#isBannedResponse').text("true").delay();
            }
    });
}

function unban(a, b) {
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
                $('#isBannedResponse').text("false").delay();
            }
    });
}