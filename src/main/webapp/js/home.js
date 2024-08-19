"use strict";

function handleSubmit(event) {
    // Previne o envio do formulário
    event.preventDefault();

    // Obtém os valores dos campos
    const entrada = document.getElementById('entrada').value;
    const saida = document.getElementById('saida').value;
    const capacidade = document.querySelector('input[name="capacidade"]:checked');

    // Verifica se a capacidade foi selecionada
    const capacidadeValue = capacidade ? capacidade.value : 'Nenhuma';

    // Exibe os valores em um alerta (ou faça outra coisa com eles)
    alert(`Entrada: ${entrada}\nSaída: ${saida}\nCapacidade: ${capacidadeValue}`);

}