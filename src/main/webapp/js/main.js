document.addEventListener("DOMContentLoaded", function () {

    document.querySelectorAll('.favoritar').forEach(botao => {
        botao.addEventListener('click', function () {
            const quartoId = this.getAttribute('data-quarto-id');
            lidarComAcao('/hotelweb/quarto/favoritar', quartoId);
        });
    });

    document.querySelectorAll('.desfavoritar').forEach(botao => {
        botao.addEventListener('click', function () {
            const quartoId = this.getAttribute('data-quarto-id');
            lidarComAcao('/hotelweb/quarto/desfavoritar', quartoId);
        });
    });

    document.querySelectorAll('.alugar').forEach(botao => {
        botao.addEventListener('click', function () {
            const quartoId = this.getAttribute('data-quarto-id');
            lidarComAcao('/hotelweb/aluguel/alugar', quartoId);
        });
    });

    document.querySelectorAll('.cancelar').forEach(botao => {
        botao.addEventListener('click', function () {
            const quartoId = this.getAttribute('data-quarto-id');
            lidarComAcao('/hotelweb/aluguel/cancelar', quartoId);
        });
    });

    function lidarComAcao(url, quartoId) {
        fetch(`${url}?quarto=${quartoId}`, {
            method: 'POST'
        })
        .then(resposta => {
            if (!resposta.ok) {
                throw new Error('Resposta da rede não foi ok.');
            }
            return resposta.text(); 
        })
        .then(mensagem => {
            mostrarMensagem(mensagem); 
        })
        .catch(erro => {
            mostrarMensagem('Erro ao processar a requisição.');
            console.error('Erro:', erro);
        });
    }

    function mostrarMensagem(mensagem) {
        const elementoMensagem = document.getElementById('mensagem');
        elementoMensagem.textContent = mensagem;
        elementoMensagem.style.display = 'block';
        setTimeout(() => {
            elementoMensagem.style.display = 'none';
        }, 3000);
    }
});
