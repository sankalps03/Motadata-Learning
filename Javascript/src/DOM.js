function myFunction() {
    let x = document.getElementById("numb").value;
    let text;
    if (isNaN(x) || x < 1 || x > 10) {
        text = "Input not valid";
    } else {
        text = "Input OK";
    }
    document.getElementById("demo").innerHTML = text;
}

function myMove() {
    let id = null;
    const elem = document.getElementById("animate");
    let pos = 0;
    clearInterval(id);
    id = setInterval(frame, 5);

    function frame() {
        if (pos == 350) {
            clearInterval(id);
        } else {
            pos++;
            elem.style.top = pos + "px";
            elem.style.left = pos + "px";
        }
    }
}

function displayDate() {
    document.getElementById("demo").innerHTML = Date();
}

function checkCookies() {
    var text = "";
    if (navigator.cookieEnabled == true) {
        text = "Cookies are enabled.";
    } else {
        text = "Cookies are not enabled.";
    }
    document.getElementById("load").innerHTML = text;
}

function upperCase() {
    const x = document.getElementById("fName");
    x.value = x.value.toUpperCase();
}

function mOver(obj) {
    obj.style.backgroundColor = "#1ec5e5"
    obj.innerHTML = "Thank You"
}

function mOut(obj) {
    obj.style.backgroundColor="#ff0000";
    obj.innerHTML = "Mouse Over Me"
}

function mDown(obj) {
    obj.style.backgroundColor = "#1ec5e5";
    obj.innerHTML = "Release Me";
}

function mUp(obj) {
    obj.style.backgroundColor="#ff0000";
    obj.innerHTML="Thank You";
}

window.addEventListener("resize", function(){
    document.getElementById("resize").innerHTML = Math.random();
});
function insertNew() {
    const para = document.createElement("p");

    const node = document.createTextNode("This is new.");

    para.appendChild(node);

    const element = document.getElementById("div1");

    element.appendChild(para);
}

function insertBefore() {

    const para = document.createElement("p");


    const node = document.createTextNode("This is new.");

    para.appendChild(node);

    const element = document.getElementById("div1");

    const child = document.getElementById("before");

    element.insertBefore(para, child);
}