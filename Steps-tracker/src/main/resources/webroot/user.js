
$(function (){

  if(!localStorage.hasOwnProperty('username')){

  var username = window.prompt("Enter username")

  }else {

    username = localStorage.getItem("username");
  }

  $("#logout").click(function (){

    localStorage.removeItem("username");
  });



  let ajaxDataUser = {

  url: "http://localhost:8080/user/"+username,
  type: "GET",
  callback: updateprofile
}

ajaxCall(ajaxDataUser);



});

function updateDaily(result){

  let data = JSON.stringify(result);

  console.log(data.toString());

  let step = $.parseJSON(data);

  $("#totalSteps").text(step.totalSteps);

  $("#dailySteps").text(step.dailySteps);


}

function updateMonthly(result){
  let data = JSON.stringify(result);

  console.log(data.toString());

  let step = JSON.parse(data);

  $("#monthlysteps").text(step.monthlySteps);




}

function updateYearly(result){

  let data = JSON.stringify(result);

  console.log(data)

  let step = JSON.parse(data);

  $("#yearlySteps").text(step.yearlySteps);


}

function updateLifetime(result){

  let data = JSON.stringify(result);

  console.log(data)

  let step = JSON.parse(data);

  $("#totalSteps").text(step.totalSteps);


}

function updateprofile(result){

  let data = JSON.stringify(result);

  console.log(data)

  let step = JSON.parse(data);

  const date = new Date();
  let day = date.getDate();
  let month = date.getMonth();
  let year = date.getFullYear();



  $("#usName").text(step.username);
  $("#email").text(step.email);
  $("#city").text(step.city);
  $("#deviceId").text(step.deviceId);

  localStorage.setItem("username",step.username);

  let username = step.username;
  let deciceId = step.deviceId;


  let ajaxDataDaily = {

    url: "http://localhost:8080/user/"+deciceId+"/"+year+"/05/"+day,
    type: "GET",
    callback: updateDaily
  }

  ajaxCall(ajaxDataDaily);


  let ajaxDataMonthly = {

    url: "http://localhost:8080/user/"+deciceId+"/"+year+"/05",
    type: "GET",
    callback: updateMonthly

  }

  ajaxCall(ajaxDataMonthly);


  let ajaxDataYearly = {

    url: "http://localhost:8080/user/"+deciceId+"/"+year,
    type: "GET",
    callback:updateYearly

  }
  ajaxCall(ajaxDataYearly);


  let ajaxDataTotal = {

    url: "http://localhost:8080/user/"+deciceId+"/total",
    type: "GET",
    callback:updateLifetime
  }

  ajaxCall(ajaxDataTotal);


}


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


