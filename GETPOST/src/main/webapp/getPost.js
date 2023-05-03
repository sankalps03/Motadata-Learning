$(document).ready(getData())
$(function () {
    $("form").submit(function (event) {
        var formData = {
            name: $("#name").val(),
            email: $("#email").val(),
            Motadataally: $("#MotadataAlly").val(),
        };

        let ajaxData = {

            url: "http://localhost:8080/submit",
            type: "POST",
            data: formData,
            dataType: 'json',
            callback: getData

        }

            ajaxCall(ajaxData);

        event.preventDefault();
    });

    $("#example").on("click","tr",function (){

        $('#tButton').remove();

        $(this).after(
            "<button id='tButton'>Delete</button>");



    });

});

function ajaxCall (result){

    $.ajax({
        type:result.type,
        url:result.url,
        data: result.data,
        dataType: result.dataType,
        success: function (data){
            console.log(data)
            if(result.hasOwnProperty('callback')){
                console.log(result.callback)
            result.callback(data);
            }
        }
        });

}

function updateDatatable(result){

    let data = $.parseJSON(result);

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

function alert(data){
    alert(data);
}

function getData(){

    let ajaxData = {

        url: "http://localhost:8080/select",
        type: "GET",
        dataType: 'json',
        callback: updateDatatable
    }

    ajaxCall(ajaxData);


}





