idade = prompt("Quantos anos você tem?");
if (idade < 18) {
  alert("Você é menor de idade. Não pode jogar.");
} else if (idade >= 18) {
  escolhaJogador = prompt("1 = Pedra, 2 = papel ou 3 = tesoura?");
  escolhaComputador = Math.floor(Math.random() * 3) + 1;

  // Jogador Pedra, Computador Pedra --> Empate!
  // Jogador Tesou, Computador Tesoura --> Empate!
  // Jogador Papel, Computador Papel --> Empate!

  if (escolhaJogador == escolhaComputador) {
    alert("Empate!");
  }

  if (escolhaJogador == 1) {
    if (escolhaComputador == 2) {
      alert(
        "Computador venceu, computador escolheu " +
          escolhaComputador +
          " - Papel"
      );
      // Jogador Pedra, Computador Papel --> Computador Vence!
    }
    if (escolhaComputador == 3) {
      alert(
        "Jogador venceu, computador escolheu " +
          escolhaComputador +
          " - Tesoura"
      );
      // Jogador Pedra, Computador Tesoura --> Jogador Vence!
    }
  }

  if (escolhaJogador == 2) {
    if (escolhaComputador == 1) {
      alert(
        "Jogador venceu, computador escolheu " + escolhaComputador + " - Pedra"
      );
      // Jogador Papel, Computador Pedra --> Computador Vence!
    }
    if (escolhaComputador) {
      alert(
        "Computador venceu, computador escolheu " +
          escolhaComputador +
          " - Tesoura"
      );
    }
  }

  if (escolhaJogador == 3) {
    if (escolhaComputador == 1) {
      alert(
        "Computador venceu, computador escolheu " +
          escolhaComputador +
          " - Pedra"
      );
      // Jogador Tesoura, Computador Pedra --> Computador vence!
    }
    if (escolhaComputador == 2) {
      alert(
        "Jogador venceu, computador escolheu " + escolhaComputador + " - Papel"
      );
      // Jogador Tesoura, Computador Papel --> Jogador vence!
    }
  }
  // Jogador Tesoura,
}

//alert("Vamos começar");
