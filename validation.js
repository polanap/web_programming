function validation(){

    let x  = parseInt(document.querySelector("#x").value, 10);
    let y = document.querySelector("#y").value.replace(',', '.');
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
    let message = ""
    if(invalid.length == 1){
        message = "Недопустимое значение переменной "+invalid[0];
    }
    else if(invalid.length > 1){
        message = "Недопустимое значение переменных "+invalid.join(" ");
    }
    if(flag){
        alert("meoooooow")
        responder();
        
    }
    else{
        // let errWind = document.createElement('div');
        // errWind.textContent = message;
        // errWind.style.
        alert(message);

    }
}

function responder(){
    $('#form').submit(function(e) {
        e.preventDefault();
        $.ajax({
            type: "GET",
            url: 'submit_form.php',
            data: $(this).serialize(),
            success: function(response)
            {
                let obj = JSON.parse(response);
                let html = [];
                html.push('<tr>');
                for (let i in obj) {
                    html.push('<td>'+obj[i]+'</td>');
                }
                html.push('</tr>');

                $('#resultTable').innerHTML += html.join('');
           }
       });
     });
}

document.addEventListener('DOMContentLoaded', function() {
	document.getElementById('submitForm').addEventListener('click', validation);
});