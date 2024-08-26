<!DOCTYPE html>
<html>
<head>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="shprtcut icon" href="/favicon.ico" type="image/x-icon">
        <meta charset="utf-8">
        <title>Labwork #1</title>
        <link rel="stylesheet" type="text/css" href="style.css"/>

</head>
<body>
        <table>
                <tr>
                        <th>
                                <div class="container">
                                        <h1>Labwork#1<sub>1234</sub></h1>
                                        <h2>Выполнила студент группы Р3109<br>Напольская Полина Александровна</br>
                                </div>
                        </th>                        
                </tr>
                <tr>
                        <td>
                                <div class="container">
                                        <table id="resultTable">
                                                <tr>
                                                        <th>X</th>
                                                        <th>Y</th>
                                                        <th>R</th>
                                                        <th>Результат</th>
                                                        <th>Текущее время</th>
                                                        <th>Время работы</th>
                                                </tr>
                                        </table>
                                </div>
                        </td>
                        <td>
                                <table>
                                        <tr>
                                                <div class="container">
                                                        <form method="get" id="form">

                                                                <label for="x">x:</label>
                                                                <select type="button" id="x" name="x" required>
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
                                                                <input type="text" id="y" name="y" required>

                                                                <label for="R">R:</label>
                                                                <select type="radio" id="R" name="R" required>
                                                                                <option>1</option>
                                                                                <option>2</option>
                                                                                <option>3</option>
                                                                                <option>4</option>
                                                                                <option>5</option>
                                                                </select>

                                                                <input type="submit" id="submitForm" value="Отправить">
                                                
                                                        </form>
                                                </div>
                                        </tr>
                                        <tr>
                                                <div class="container">
                                                        <div class="values">
                                                                <h3> -3 &le; x &le; 5</h3>
                                                                <h3> -5 < y < 3</h3>
                                                                <h3> 1 &le; R &le; 5</h3>
                                                        </div>
                                                </div>

                                        </tr>
                                </table>

                        </td>
                        <td>
                                <div class="container">
                                        <img src="resources\areas.png" alt="Area">
                                </div>
                        </td>
                </tr>
        </table>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
                        <script type="text/javascript" src="validation.js"></script>
</body>