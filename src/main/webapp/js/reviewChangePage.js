function deleteReview(element) {
    $.ajax({
        url : 'Controller?command=deleteReview',
        data : {
            id : element.value
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
            id : a,
            review: b
            // review: elementReview
        },
        async: false,
        type: 'POST',
        success :
            function() {
                $('#changeResponse').text(b).delay();
            }
    });
}