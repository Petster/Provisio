"use strict";

let validInputs = [
    false,
    false,
    false,
    true,
    false
];

let form = document.getElementById('emailForm');
let formInputs = form.querySelectorAll('input');
let textArea = form.querySelector('textarea');

form.addEventListener('keyup', function(e) {
    e.preventDefault();
    for(let i = 0; i < formInputs.length; i++) {
        if(formInputs[i].value === '') {
            validInputs[i] = false;
        } else {
            validInputs[i] = true;
            formInputs[i].style.border = 'none';
        }
    }
    if(textArea.value === '') {
        textArea.style.border = '1px red solid';
    } else {
        textArea.style.border = 'none';
    }
})

$('#submitEmail').click(function (e) {
    e.preventDefault();
    if(validInputs.includes(false)) {
        for(let i = 0; i < validInputs.length; i++) {
            if(validInputs[i] === false) {
                formInputs[i].style.border = '1px red solid';
                let fieldName = ""
                switch(formInputs[i].name) {
                    case "name": fieldName = "Name"; break;
                    case "email": fieldName = "Email"; break;
                    case "phone": fieldName = "Phone"; break;
                    case "resnum": fieldName = "Reservation Number (Enter 0 for none)"; break;
                    case "subject": fieldName = "Subject"; break;
                }
                swal({
                    title: "Error",
                    text: `The ${fieldName} field is empty`,
                    icon: "error",
                });
            } else {
                formInputs[i].style.border = 'none';
            }
        }
    } else {
        if(textArea.value === '') {
            textArea.style.border = '1px red solid';
            swal({
                title: "Error",
                text: `The message field is empty`,
                icon: "error",
            });
        } else {
            $.ajax({
                type: 'post',
                url: 'ContactUs',
                data: $('#emailForm').serialize()
            }).then((result) => {
                if (result.success) {
                    swal({
                        title: "Success",
                        text: result.msg,
                        icon: "success",
                    }).then(() => {
                        $('#emailForm').trigger("reset");
                    });
                } else {
                    swal({
                        title: "Error",
                        text: result.msg,
                        icon: "error",
                    });
                }
            })
        }
    }
});