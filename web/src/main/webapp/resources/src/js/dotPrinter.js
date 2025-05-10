var pt;

document.addEventListener('DOMContentLoaded', function(){
    pt =document.getElementById('svg').createSVGPoint();
});

function getPoint(event) {
    var x = event.clientX;
    var y = event.clientY;
    var cursorpt =  pt.matrixTransform(document.getElementById('svg').getScreenCTM().inverse());
    var r =document.getElementById('form:R').value;
    document.getElementById('form:x').value = (x+cursorpt.x - 150) * r /100;
    document.getElementById('form:y').value = -(y+cursorpt.y - 150) * r / 100;
    document.getElementById('form:submit').click();
}