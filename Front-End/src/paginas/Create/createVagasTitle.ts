import { Vagas } from "../../Classes/Vagas";
import type { Pagina } from "../../router/router";
import { router } from "../../router/router";

export class createVagas implements Pagina {
    
    render(): string {
        return `<h2>Bem-vindo à página Cadastro Empresa!</h2>
        <p>Esta é a página teste do nosso aplicativo.</p>

        <form id="vaga-form">  

            <label for="Titulo">Titulo:</label>
            <input type="text" id="Titulo" name="Titulo" required>
            </br></br>  
            
            
            <button type="button" id="createVaga">Add Competencia</button>
        </form>

         <button id="go-page">Voltar ao menu</button>
        `;
    }

    onNavigate(): void {
        console.log("Navegou para a página createVaga");
        this.configurarEventosCreateVaga();
    }

    onLeave(): void {
        console.log('Saindo da createVaga');
    }

    private configurarEventosCreateVaga(): void {
        setTimeout(() => {
            const botao = document.getElementById('createVaga');
            const botao2 = document.getElementById('go-page');
            let operation = 1;
            if(botao && operation == 1){
                botao.onclick = () => {
                    console.log('Clicou no botao');
                    this.coletarDadosVaga();
                    console.log('Competencia adicionado com sucesso!');
                    operation--;
                    router.navegação('/createVagasAttributes');
                     
                };
            }

           

            if(botao2 && operation == 1){
                botao2.onclick = () => {
                    operation--;
                    router.navegação('/menuEmpresa');
                };
            }
        });
    }

    private coletarDadosVaga(): void {
        console.log('Coletando dados do formulário...');
        
        const tituloElement = document.getElementById('Titulo') as HTMLInputElement;

        if (!tituloElement) {
            console.error('Elemento Nome não encontrado!');
        }

        localStorage.setItem("TituloVaga",tituloElement.value);
    }
    
  

    
}