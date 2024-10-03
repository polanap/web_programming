<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<jsp:useBean id="history" class="table.TableHistoryBean" scope="session" />
<!DOCTYPE html>
<html>

<head>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="shprtcut icon" href="/favicon.ico" type="image/x-icon">
        <meta charset="utf-8">
        <title>Labwork #1</title>
        <link rel="stylesheet" type="text/css" href="css/style.css" />

</head>

<body>
        <table class="lay1">
                <tr>
                        <th>
                                <div class="container">
                                        <h1>Labwork#1<sub>409218</sub></h1>
                                        <h2>Выполнила студент группы Р3209 Напольская Полина Александровна</h2>
                                </div>
                        </th>
                </tr>
                <tr>
                        <td>
                                <div class="container">
                                        <table class="resultTable">
                                                <tr>
                                                        <th>X</th>
                                                        <th>Y</th>
                                                        <th>R</th>
                                                        <th>Результат</th>
                                                        <th>Текущее время</th>
                                                        <th>Время работы</th>
                                                </tr>
                                        </table>
                                        <div class="scrollbox">
                                                <table class="resultTable" id="resultTable">

                                                </table>
                                        </div>
                                </div>
                        </td>
                        <td>
                                <div class="container">
                                        <table class="lay2">
                                                <tr>
                                                        <td>

                                                                <form method="get" id="form">

                                                                        <label for="x">x:</label>
                                                                        <select type="button" id="x" name="x"
                                                                                required>
                                                                                <option>-3</option>
                                                                                <option>-2</option>
                                                                                <option>-1</option>
                                                                                <option>0</option>
                                                                                <option>1</option>
                                                                                <option>2</option>
                                                                                <option>3</option>
                                                                                <option>4</option>
                                                                                <option>5</option>
                                                                        </select>

                                                                        <label for="y">y:</label>
                                                                        <input type="text" id="y" name="y"
                                                                                required>

                                                                        <label for="R">R:</label>
                                                                        <select type="radio" id="R" name="R"
                                                                                required>
                                                                                <option>1</option>
                                                                                <option>2</option>
                                                                                <option>3</option>
                                                                                <option>4</option>
                                                                                <option>5</option>
                                                                        </select>

                                                                        <input type="submit" id="submitForm"
                                                                                value="Отправить">

                                                                </form>
                                                        </td>
                                                </tr>
                                                <tr>
                                                        <td>

                                                                <div class="values">
                                                                        <h3> -3 &le; x &le; 5; -5 < y < 3; 1
                                                                                        &le; R &le; 5</h3>
                                                                </div>

                                                        </td>
                                                </tr>
                                                <tr>
                                                        <td>
                                                                <img src="resources\areas.png" alt="Area">
                                                                <!-- <svg width="300" height="300" id="svg">

                                                                        <line x1="0" x2="300" y1="150" y2="150"></line>
                                                                        <line x1="150" x2="150" y1="0" y2="300"></line>
                                                            
                                                                        <polygon style="opacity: 0.8; fill: black" points="150,0 145,15 155,15"></polygon>
                                                                        <polygon style="opacity: 0.8; fill: black" points="300,150 285,145 285,155"></polygon>
                                                            
                                                                        <polygon style="opacity: 0.8; fill: rgb(255, 226, 187)" points="150,150 150,200 250,150"></polygon>
                                                            
                                                                        <polygon style="opacity: 0.8; fill: rgb(255, 226, 187)" points="100,150 150,150 150,250, 100,250"></polygon>
                                                                        <path style="opacity: 0.8; fill: rgb(255, 226, 187)" d="M150,150 L50,150 A90,90 0 0,1 150,50 Z"></path>
                                                            
                                                            
                                                                        <line x1="50" x2="50" y1="140" y2="160"></line>
                                                                        <line x1="100" x2="100" y1="140" y2="160"></line>
                                                                        <line x1="200" x2="200" y1="140" y2="160"></line>
                                                                        <line x1="250" x2="250" y1="140" y2="160"></line>
                                                            
                                                                        <line x1="140" x2="160" y1="50" y2="50" ></line>
                                                                        <line x1="140" x2="160" y1="100" y2="100"></line>
                                                                        <line x1="140" x2="160" y1="200" y2="200"></line>
                                                                        <line x1="140" x2="160" y1="250" y2="250"></line>
                                                            
                                                                        <text x="285" y="140">x</text>
                                                                        <text x="160" y="15">y</text>
                                                            
                                                                        <text x="40" y="145" id="-r">-R</text>
                                                                        <text x="85" y="145" id="-r2">-R/2</text>
                                                                        <text x="190" y="145" id="r2">R/2</text>
                                                                        <text x="245" y="145" id="r">R</text>
                                                            
                                                                        <text x="155" y="52.5" id="r">R</text>
                                                                        <text x="155" y="102.5" id="r2">R/2</text>
                                                                        <text x="155" y="202.5" id="-r2">-R/2</text>
                                                                        <text x="155" y="252.5" id="-r">-R</text>
                                                            
                                                                </svg> -->
                                                        </td>
                                                </tr>
                                        </table>
                                </div>

                        </td>
                </tr>
        </table>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
        <script type="text/javascript" src="js/dataSender.js"></script>
        <script type="text/javascript" src="js/tableLoader.js"></script>
</body>