import type { Pagina } from "../router/router";
import { router } from "../router/router";


export class Home implements Pagina {
    
    render(): string {
        return `<h2>Bem-vindo à página Home!</h2>
        <p>Esta é a página inicial do nosso aplicativo.</p>
        <button id="go-page"> Fazer Login</button>;
        <button id="go-page2"> Criar sua conta</button>`;
    }

    onNavigate(): void {
        console.log("Navegou para a página Home");
        var candidatos = JSON.parse(localStorage.getItem('candidatos') || '[]');
        console.log(candidatos); 
        this.configurarEventos();
    }

    
    onLeave(): void {
        console.log('Saindo da HomePage');
    }

    private configurarEventos(): void {
        setTimeout(() => {
            const  botao = document.getElementById('go-page');
            const  botao2 = document.getElementById('go-page2');
            if(botao){
                botao.addEventListener('click', () => {
                    console.log('Clicou no botao');
                    router.navegação('/login'); 
                });
            }

            if(botao2){
                botao2.addEventListener('click', () => {
                    console.log('Clicou no botao');
                    router.navegação('/menuCadastro'); 
                });
            }
        });
    }

}