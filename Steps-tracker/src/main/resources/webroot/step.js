
$(function () {
  $("form").submit(function (event) {
    var formData = {
      username: $("#UserName").val(),
      password: $("#password").val(),
      email: $("#email").val(),
      city: $("#City").val(),
    };

    let ajaxData = {

      url: "http://localhost:8080/register",
      type: "POST",
      data: formData,
      dataType: 'json',
    }

    ajaxCall(ajaxData);

    event.preventDefault();
  });





  var eventBus = new EventBus('/eventbus/');

  eventBus.onopen = function () {

    eventBus.registerHandler('updates.publicRanking', function (err, msg) {

      // jQuery.parseJSON(msg);

    console.log(JSON.stringify(msg));

    updateDatatable(JSON.stringify(msg));

    });
  }

});

function ajaxCall (result){

  $.ajax({
    type:result.type,
    url:result.url,
    data: JSON.stringify(result.data),
    dataType: result.dataType,
    success: function (data){
      console.log("hii all"+data)

      if(result.hasOwnProperty('callback')){
        console.log(result.callback)
        result.callback(data);
      }
    }
  });

}

function updateDatatable(result){

  let data = $.parseJSON(result);

  let body = data.body;

  console.log($("#example").dataTable().fnDestroy())


  let table = $('#example').DataTable({
      data: body,
      columns: [
        { data: 'userName' },
        { data: 'deviceId' },
        { data: 'steps' }
      ],
    order: [[2, 'desc']],
    });
}








