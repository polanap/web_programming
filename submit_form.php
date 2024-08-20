<?php

function check($x, $y, $R) {
    if ($x < 0 && $y > 0 && $x*$x+$y*$y <= $R*$R){
        return true;
    }
    else if (-$R/2<=$x && $x<= 0 && -$R<=$y && $y<=0){
        return true;   
    }
    else if ($x>=0 && $y<=0 && $y>=-$R/2+$x/2){
        return true;
    }
    else{
        return false;
    }

}

$start = microtime(true);
$x = $_GET['x'];
$y = $_GET['y'];
$R = $_GET['R'];
$result = check($x, $y, $R) ? 'внутри' : 'снаружи';
$currentTime = date("d-m-Y H:i:s"); 
$executionTime = number_format(microtime(true) - $start, 10, '.', '');

$output = ['x' => $x, 'y' => $y, 'R' => $R, 'result' => $result, 'currentTime' => $currentTime, 'executionTime'=> $executionTime,];
exit(json_encode($output));

?>