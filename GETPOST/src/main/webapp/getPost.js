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

            addData(data);

            console.log(data);
        });

        event.preventDefault();
    });

    $("button#select").click(function (){

        $.ajax({
            type: "GET",
            url: "http://localhost:8080/select",
            dataType: 'json',
            success: function (datas){

                console.log(datas);


                let data = $.parseJSON(datas);

                $("#example").dataTable().fnDestroy(),

                 $('#example').DataTable({
                    data: data,
                    columns: [
                        { data: 'name' },
                        { data: 'email' },
                        { data: 'ally' }
                    ]
                });

                }
        });

    });
});




