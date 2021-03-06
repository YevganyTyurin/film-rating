function deleteReview(element) {
    $.ajax({
        url : 'Controller?command=deleteReview',
        data : {
            reviewId : element.value
        },
        async: false,
        type: 'POST',
        success :
            function() {
                $('#deleteResponse').text('Обзор удален').delay();
            }
    });
}

function changeReview(a, b) {
    $.ajax({
        url : 'Controller?command=updateReview',
        data : {
            reviewId : a,
            review: b
        },
        async: false,
        type: 'POST',
        success :
            function() {
                $('#changeResponse').text(b).delay();
            }
    });
}