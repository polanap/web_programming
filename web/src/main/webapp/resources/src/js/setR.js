R = document.getElementById("form:R")
hiddenR = document.getElementById("formR:hiddenR")
R.addEventListener('input', function() {
    hiddenR.value = R.value;
    document.getElementById("formR:submitR").click();
})