function validation(){
    // e.preventDefault();
    let x  = parseInt(document.querySelector("#x").value, 10);
    let y = document.querySelector("#y").value;
    let R = parseInt(document.querySelector("#R").value, 10);
    let flag = true;
    let invalid = [];
    if (![-3, -2, -1, 0, 1, 2, 3, 4, 5].includes(x)){
        invalid.push("x");
        flag = false;
    }
    if (!/^-?\d+(\.\d+)?$/.test(y) || y<= -5 || y>=3){
        invalid.push("y");
        flag = false;
    }
    if (![1,2,3,4,5].includes(R)){
        invalid.push("R");
        flag = false;
    }
    let message = "success"
    if(invalid.length == 1){
        message = "Недопустимое значение переменной "+invalid[0];
    }
    else if(invalid.length > 1){
        message = "Недопустимое значение переменных "+invalid.join(" ");
    }
    return message
}

function drawTable(response){
    // let obj = response;
    let html = [];
    html.push('<tr>');
    for (let i in response) {
        console.log(i)
        html.push('<td>'+response[i]+'</td>');
    }
    html.push('</tr>');

    document.getElementById('resultTable').innerHTML += html.join('');
}

function sendData(x, y, R){
    x = x.toString();
    y = y.toString();
    R =  R.toString();
    return $.ajax({
        method:'post',
        url: 'controller',
        contentType: 'application/json; charset=utf-8',
        data: JSON.stringify({
            x: x,
            y: y,
            R: R
        }),
        success: function(response)
        {
            var pos;
            try {
                pos = response[resultData];
              } catch (e) {
              }
            drawTable(response);
            return pos;
       },
       error: function(response)
       {
        //    alert("ошибочка(((");
           return -1;
        }
        });
}


function drawPoint(x, y, pos) {
    console.log(pos);

        let canvas = $("#graph-canvas");
        let ctxAxes = canvas[0].getContext('2d');
        ctxAxes.beginPath();
        if (pos == "outer"){
            ctxAxes.fillStyle = 'red';
        }
        else if(pos == "inner"){
            ctxAxes.fillStyle = 'green';
        }else{
           ctxAxes.fillStyle = 'black'; 
        }
        ctxAxes.arc(x, y, 2, 0, 2 * Math.PI);
        ctxAxes.fill();

    console.log('point')
       
   }
   

function clearCanvas(){
    let canvas = $("#graph-canvas");
    let ctxAxes = canvas[0].getContext('2d');
    ctxAxes.clearRect(0, 0, 300, 300);   
}


let oldR;
$("#graph-canvas").on("click", function (event) {
    let R = parseInt(document.querySelector("#R").value, 10);
    if(R!=oldR){
        console.log('clear canvas')
        clearCanvas();
        oldR = R;
    }
    let offX = event.offsetX;
    let offY = event.offsetY;
    let x = ((offX - 150) * R) / 100;
    let y = -((offY - 150) * R) / 100;
    window.x = x;
    window.y = y;
    let pos = sendData(x, y, R);
    if (pos!=-1){
        drawPoint(offX, offY, pos);
    }
})


document.addEventListener('DOMContentLoaded', function() {
	document.getElementById('submitForm').addEventListener('click', function(e){
        e.preventDefault();
        let x  = parseInt(document.querySelector("#x").value, 10);
        let y = document.querySelector("#y").value;
        let R = parseInt(document.querySelector("#R").value, 10);
        let validationResult = validation(x, y, R);
        if (validationResult == 'success'){
            if(R!=oldR){
                console.log('clear canvas')
                clearCanvas();
                oldR = R;
            }
            let pos = sendData(x, y, R);
            x = parseFloat(x, 10)*100/R +150;
            y = -(parseFloat(y, 10)*100/R)+150;
            drawPoint(x, y, pos);
        }else{
            alert(validationResult);
        }
        });
});