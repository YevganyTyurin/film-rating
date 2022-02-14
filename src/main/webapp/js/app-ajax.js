$(document).ready(function() {
    $('#submit_review').click(function() {
        var mark = $('#mark').val();
        if( mark != '' ) {
            $.ajax({
                url : 'Controller?command=leaveReview',
                data : {
                    filmMark : $('#mark').val(),
                    reviewText : $('#review_text').val()
                },
                async: false,
                type: 'POST',
                success :
                    function() {
                    alert(1111);
                        document.getElementById('review').style.display = 'none';
                        $('#ajaxGetUserServletResponse').text('Отзыв оставлен').delay(2500).hide(0);
                    }
            });
        } else {
            alert ('друг, заполни плиз все поля');
        }
    });
});

window.onload = function () {
    document.getElementById('show_review_form').onclick = function () {
        if(document.getElementById('review_form').style.display === 'none') {
            document.getElementById('review_form').style.display = 'block'
            if (this.value==="Оставить отзыв") this.value = "Не оставлять отзыв";
            else this.value = "Оставить отзыв";
        } else {
            document.getElementById('review_form').style.display = 'none'
            if (this.value==="Оставить отзыв") this.value = "Не оставлять отзыв";
            else this.value = "Оставить отзыв";
        }
    };
};

function updateLike(element) {
    var element_parent = element.parentNode;
    $.ajax({
        url : 'Controller?command=updateLike',
        data : {
            reviewId : element.value
        },
        async: false,
        type: 'POST',
        success :
            function(responseText) {
                var list = document.getElementsByClassName("c")[element_parent.id];
                list.getElementsByClassName("n")[0].innerHTML = responseText;
                if (list.getElementsByClassName("n")[1].style.backgroundColor === 'green') {
                    list.getElementsByClassName("n")[1].style.backgroundColor = 'transparent';
                } else {
                    list.getElementsByClassName("n")[1].style.backgroundColor = 'green';
                }
                if (list.getElementsByClassName("n")[2].style.backgroundColor == 'red' &&
                    list.getElementsByClassName("n")[1].style.backgroundColor == 'green') {
                    updateDislike(element);
                }
            }
    });
}


function updateDislike(element) {
    var element_child = element;
    var element_parent = element_child.parentNode;
    $.ajax({
        url : 'Controller?command=updateDislike',
        data : {
            reviewId : element.value
        },
        async: false,
        type: 'POST',
        success :
            function(responseText) {
                var list = document.getElementsByClassName("c")[element_parent.id];

                if (list.getElementsByClassName("n")[2].style.backgroundColor == 'red') {
                    list.getElementsByClassName("n")[2].style.backgroundColor = 'transparent';
                } else {
                    list.getElementsByClassName("n")[2].style.backgroundColor = 'red';
                }

                list.getElementsByClassName("n")[3].innerHTML = responseText;

                if (list.getElementsByClassName("n")[2].style.backgroundColor == 'red' &&
                    list.getElementsByClassName("n")[1].style.backgroundColor == 'green') {
                    updateLike(element);
                }
            }
    });
};