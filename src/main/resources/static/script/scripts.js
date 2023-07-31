document.addEventListener("DOMContentLoaded", function () {

  var x = transacao.map(x => x[1])
  var y = transacao.map(x => x[0])

  var grafico = {
    labels: y,
    datasets: [
      {
        label: "Valores em Reais",
         backgroundColor: "rgba(1, 92, 106, 1)", // Cor de preenchimento azul
         borderColor: "rgba(1, 92, 106, 1)", // Cor da borda azul
         pointBackgroundColor: "rgba(220,220,220,1)",
         pointBorderColor: "#fff",
        data: x
      }]
  };

  new Chart(document.getElementById("grafico").getContext("2d"), {
    type: "line",
    data: grafico,
    options: {
      plugins: {
        title: {
          display: true,
          text: "Gastos x Categorias", // Texto do título do gráfico
        }
      }
    }
    });
});

