import type { Pagina } from "../router/router";
import { router } from "../router/router";


export class Test implements Pagina {
    
    render(): string {
        return `<h2>Bem-vindo à página Test!</h2>
        <p>Esta é a página teste do nosso aplicativo.</p>
        <button id="go-page"> Ir para outra pagina</button>`;
    }

    onNavigate(): void {
        console.log("Navegou para a página Test");
        this.configurarEventos();
    }

    
    onLeave(): void {
        console.log('Saindo da TestPage');
    }

    private configurarEventos(): void {
        setTimeout(() => {
            const  botao = document.getElementById('go-page');

            if(botao){
                botao.addEventListener('click', () => {
                    // Navegar para outra página
                    router.navegação('/'); // Exemplo de navegação para a página "Sobre"
                });
            }
        });
    }

}