document.addEventListener("DOMContentLoaded", function () {

  var x = transacao.map(x => x[1])
  var y = transacao.map(x => x[0])

  var grafico = {
    labels: y,
    datasets: [
      {
        label: "Valores em Reais",
        data: x
      }]
  };

  new Chart(document.getElementById("graficoPizza").getContext("2d"), {
    type: "pie",
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

