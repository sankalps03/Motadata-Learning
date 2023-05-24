$(function (){
var username =localStorage.username;

$("#login").submit(function (event) {

  username = $("#uName").val()

});

const date = new Date();
let day = date.getDate();
let month = date.getMonth();
let year = date.getFullYear();

let ajaxDataUser = {

  url: "http://localhost:8080/user/"+username,
  type: "GET",
  callback: updateprofile()
}

ajaxCall(ajaxDataUser);

let ajaxDataDaily = {

  url: "http://localhost:8080/user/"+username+"/"+year+"/"+month+"/"+day,
  type: "GET",
  callback: updateDaily()
}

ajaxCall(ajaxDataDaily);


let ajaxDataMonthly = {

  url: "http://localhost:8080/user/"+username+"/"+year+"/"+month,
  type: "GET",
  callback: updateMonthly()

}

ajaxCall(ajaxDataMonthly);


let ajaxDataYearly = {

  url: "http://localhost:8080/user/"+username+"/"+year,
  type: "GET",
  callback:updateYearly()

}
ajaxCall(ajaxDataYearly);


let ajaxDataTotal = {

  url: "http://localhost:8080/user/"+username+"/total",
  type: "GET",
  callback:updateLifetime()
}

ajaxCall(ajaxDataTotal);

});

function updateDaily(result){

  let data = $.parseJSON(result);


  $("#dailySteps").text();

}

function updateMonthly(result){
  let data = $.parseJSON(result);


  $("#monthlysteps").text();


}

function updateYearly(result){
  let data = $.parseJSON(result);


  $("#yearlySteps").text();


}

function updateLifetime(result){
  let data = $.parseJSON(result);


  $("#totalSteps").text();


}

function updateprofile(result){

  let data = $.parseJSON(result);

  $("#usName").text();
  $("#email").text();
  $("#city").text();
  $("#city").text();
}

$(window).unload(saveUser);

function saveuser(){
  localStorage.username = $('#uName').val();
}

