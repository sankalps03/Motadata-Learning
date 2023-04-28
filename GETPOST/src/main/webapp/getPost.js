$(function () {
    $("form").submit(function (event) {
        var formData = {
            name: $("#name").val(),
            email: $("#email").val(),
            Motadataally: $("#MotadataAlly").val(),
        };

        $.ajax({
            type: "POST",
            url: "http://localhost:8080/submit",
            data: formData
        }).done(function (data) {
            console.log(data);
        });

        event.preventDefault();
    });
});