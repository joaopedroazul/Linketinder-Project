import type { Pagina } from "../router/router";
import { router } from "../router/router";


export class Home implements Pagina {
    
    render(): string {
        return `<h2>Bem-vindo à página Home!</h2>
        <p>Esta é a página inicial do nosso aplicativo.</p>
        <button id="go-page"> Ir para outra pagina</button>`;
    }

    onNavigate(): void {
        console.log("Navegou para a página Home");
        this.configurarEventos();
    }

    
    onLeave(): void {
        console.log('Saindo da HomePage');
    }

    private configurarEventos(): void {
        setTimeout(() => {
            const  botao = document.getElementById('go-page');

            if(botao){
                botao.addEventListener('click', () => {
                    // Navegar para outra página
                    console.log('Clicou no botao');
                    router.navegação('/Test'); // Exemplo de navegação para a página "Sobre"
                });
            }
        });
    }

}