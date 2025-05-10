function digitalClock() {
    var date = new Date();
    var hours = date.getHours();
    var minutes = date.getMinutes();
    var seconds = date.getSeconds();

    if (hours < 10) hours = "0" + hours;
    if (minutes < 10) minutes = "0" + minutes;
    if (seconds < 10) seconds = "0" + seconds;
    document.getElementById("clock").innerHTML = hours + ":" + minutes + ":" + seconds;
    setTimeout("digitalClock()", 1000);
}

function clockPainting() {
    var now = new Date();
    var sec = now.getSeconds();
    var min = now.getMinutes();
    var hr = now.getHours();

    var ctx = document.getElementById("clock").getContext("2d");
    ctx.save();// помещаем текущий контекст в стэк

    ctx.clearRect(0,0,150,150);
    ctx.translate(75, 75);
    ctx.scale(0.4,0.4);
    ctx.rotate(-Math.PI/2);

    ctx.strokeStyle = "black";
    ctx.fillStyle = "black";
    ctx.lineWidth = 8;
    ctx.lineCap = "round";

    ctx.save();
    ctx.beginPath();

    for (var i = 0; i < 12; i++) {
    ctx.rotate(Math.PI/6);
    ctx.moveTo(100,0);
    ctx.lineTo(120,0);
}

    ctx.stroke();// нарисовали то, что ранее описали
    ctx.restore();// достаем последний сохраненный контекст из стэка

    ctx.save();
    // рисуем часовую стрелку, вращая холст
    ctx.rotate((Math.PI/6)*hr +
    (Math.PI/360)*min +
    (Math.PI/21600)*sec);
    ctx.lineWidth = 14;

    ctx.beginPath();
    ctx.moveTo(-20,0);

    // линия почти до часовых меток
    ctx.lineTo(80,0);
    ctx.stroke();
    ctx.restore();

    ctx.save();

    // минутная стрелка
    ctx.rotate((Math.PI/30*min) +
    (Math.PI/1800)*sec);
    ctx.lineWidth = 10;

    ctx.beginPath();
    ctx.moveTo(-28,0);
    ctx.lineTo(112,0);
    ctx.stroke();
    ctx.restore();

    ctx.save();

    // секундная стрелка
    ctx.rotate(sec * Math.PI/30);
    ctx.strokeStyle = "#153f4c";// цвет контура
    ctx.fillStyle = "#153f4c";
    ctx.lineWidth = 6;

    ctx.beginPath();
    ctx.moveTo(-30,0);
    ctx.lineTo(83,0);
    ctx.stroke();
    ctx.restore();

    ctx.restore();
}
    window.onload = function () {
    setInterval(clockPainting, 1000);// функция, перерисовывающая часы
    //через равные промежутки времени
}
document.addEventListener('DOMContentLoaded', clockPainting)