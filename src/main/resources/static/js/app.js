var api = (function () {

    function calculateStatistics (){
        var url='http://localhost:36000/calcular';
        //var url='https://arepwebserver.herokuapp.com/calcular';
        var data=document.getElementById("data").value;
        putData(data);
        axios.post(url,data)
            .then(res => {
                let json = JSON.parse(res.data);
                $("#media").text("Media : "+json.media);
                $("#std").text("Desviación estándar : "+json.std);

            }
        )
    }

    function putData(data){
        data=data.split(",")
        $("#TData > tbody").empty();
        $("#media").empty();
        $("#std").empty();
        for(i=0;i<data.length;i++){
            $("#TData > tbody").append(
                "<tr>" +
                " <td>" + (i+1) + "</td>" +
                "<td>" + data[i] + "</td> " +
                "</tr>"
            );
        }
    }




    return {
        calculateStatistics : calculateStatistics
    };
})();