document.addEventListener("DOMContentLoaded", function () {

  var x = transacao.map(x => x[1])
  var y = transacao.map(x => x[0])

  var coresPersonalizadas = [
        "rgba(0, 56, 143, 0.56)", // Cor para a primeira categoria
        "rgba(255, 0, 0, 0.36)",   // Cor para a segunda categoria
        // Adicione mais cores aqui para cada categoria, se necessário
      ];

  var grafico = {
    labels: y,
    datasets: [
      {
        label: "Valores em Reais",
         backgroundColor: coresPersonalizadas, // Cor de preenchimento azul
         borderColor: "rgba(1, 92, 106, 1)", // Cor da borda azul
         pointBackgroundColor: "rgba(220,220,220,1)",
         pointBorderColor: "#fff",
        data: x
      }
     ]
  };

  new Chart(document.getElementById("grafico").getContext("2d"), {
      type: "bar",
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

