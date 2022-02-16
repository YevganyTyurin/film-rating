function toggle_visibility(id) {
    var e = document.getElementById(id);
    if(e.style.display == 'block')
        e.style.display = 'none';
    else
        e.style.display = 'block';
}

$(document).ready(function (){
    $(this).click(function (){
        var fullname = $('#fullname').val();
        var fullname2 = $('#fullname2').val();
        $.ajax({
            type: 'POST',
            data: {fullname, fullname2},
            url: 'Controller?command=updateReview',
            success: function () {
            }
        })
    })
})