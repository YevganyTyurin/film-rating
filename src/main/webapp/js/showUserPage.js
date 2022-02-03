function changeRole(a) {
    $.ajax({
        url : 'Controller?command=changeUserRole',
        data : {
            id : a
        },
        async: false,
        type: 'POST',
        success :
            function() {
                if(document.getElementById("roleResponse").textContent.trim() === "USER") {
                    $('#roleResponse').text("ADMINISTRATOR").delay();
                } else {
                    $('#roleResponse').text("USER").delay();
                }
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