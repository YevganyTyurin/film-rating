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
                alert("111");
            }
        })
    })
})
// function updateRev(element) {
//     var element_parent = element.parentNode;
//     var lll = element_parent().firstChild;
//     $.ajax({
//         url : 'Controller?command=updateLike',
//         data : {
//             id : element.value()
//         },
//         async: false,
//         type: 'POST',
//         success :
//             function() {
//                alert(lll)
//             }
//     });
// }