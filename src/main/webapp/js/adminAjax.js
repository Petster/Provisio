$('#locationCreate').click(function(e) {
    e.preventDefault();
    $.ajax({
        type: 'post',
        url: 'Admin?cLocation',
        data: $('#createLocation').serialize()
    }).then((result) => {
        if(result.success) {
            swal({
                title: "Success",
                text: result.msg,
                icon: "success",
            });
        } else {
            swal({
                title: "Error",
                text: result.msg,
                icon: "error",
            });
        }
    })
});

$('#newsCreate').click(function(e) {
    e.preventDefault();
    $.ajax({
        type: 'post',
        url: 'Admin?cNews',
        data: $('#createNews').serialize()
    }).then((result) => {
        if(result.success) {
            swal({
                title: "Success",
                text: result.msg,
                icon: "success",
            });
        } else {
            swal({
                title: "Error",
                text: result.msg,
                icon: "error",
            });
        }
    })
});

$('#roomCreate').click(function(e) {
    e.preventDefault();
    $.ajax({
        type: 'post',
        url: 'Admin?cRoom',
        data: $('#createRoom').serialize()
    }).then((result) => {
        if(result.success) {
            swal({
                title: "Success",
                text: result.msg,
                icon: "success",
            });
        } else {
            swal({
                title: "Error",
                text: result.msg,
                icon: "error",
            });
        }
    })
});
$('#resetDatabase').click(function(e) {
    e.preventDefault();
    $.ajax({
        type: 'post',
        url: 'ResetDatabase',
        success: function(response) {
            swal({
                title: "Success",
                text: "Database Reset",
                icon: "success",
            });
        },
        error: function(response) {
            swal({
                title: "Error",
                text: "Failed to Reset Database",
                icon: "error",
            });
        }
    })
});

$('#dummyData').click(function(e) {
    e.preventDefault();
    $.ajax({
        type: 'post',
        url: 'DummyData',
        success: function(response) {
            swal({
                title: "Success",
                text: "Dummy Data Created",
                icon: "success",
            });
        },
        error: function(response) {
            swal({
                title: "Error",
                text: `Failed to Create Dummy Data ${response}`,
                icon: "error",
            });
        }
    })
});

let deleteRes = document.querySelectorAll('.deleteRes');

deleteRes.forEach((el) => {
    el.addEventListener('click', function() {
        let delID = el.getAttribute('name').replace(/\D/g,'');
        let finalData = "delid="+delID;
        $.ajax({
            type: 'post',
            url: 'Admin?dRes',
            data: finalData
        }).then((result) => {
            console.log(result);
            if(result.success) {
                swal({
                    title: "Success",
                    text: result.msg,
                    icon: "success",
                }).then(() => {
                    window.location.reload();
                });
            } else {
                swal({
                    title: "Error",
                    text: result.msg,
                    icon: "error",
                });
            }
        })
    })
})
