function getTableHistory(e){
    $.ajax({
        method:'get',
        url: 'controller',
        dataType:'json',
        success: function(response)
        {
            let table = response;
            let html = [];
            for (let row in table){
                html.push('<tr>');
                for (let i in row) {
                    html.push('<td>'+obj[i]+'</td>');
                }
                html.push('</tr>');
            }
            document.getElementById('resultTable').innerHTML += html.join('');
       },
       error: function(response)
       {
           alert("ошибка при загрузке таблицы");
      }
        });
}
document.addEventListener('DOMContentLoaded', getTableHistory())