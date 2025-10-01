import { Candidato } from "../../Classes/Candidato";
import type { Pagina } from "../../router/router";
import { router } from "../../router/router";

export class createCandidato implements Pagina {
    
    render(): string {
        return `
        <h2>Bem-vindo à página Cadastro Candidato!</h2>
        <p>Esta é a página teste do nosso aplicativo.</p>

        <form id="Candidato-form">  
            <label for="Nome">Nome:</label>
            <input type="text" id="Nome" name="Candidato" required>
            </br></br>

            <label for="Email">Email:</label>
            <input type="text" id="Email" name="Candidato" required>
            </br></br>  

            <label for="Idade">Idade:</label>
            <input type="text" id="Idade" name="Candidato" required>
            </br></br>  

            <label for="CPF">CPF:</label>
            <input type="text" id="CPF" name="Candidato" required>
            </br></br>  

            <label for="Estado">Estado:</label>
            <select id="task-options">
                <option value="AC">AC</option>
                <option value="AL">AL</option>
                <option value="AP">AP</option>
                <option value="AM">AM</option>
                <option value="BA">BA</option>
                <option value="CE">CE</option>
                <option value="DF" selected>DF</option>
                <option value="ES">ES</option>
                <option value="GO">GO</option>
                <option value="MA">MA</option>
                <option value="MT">MT</option>
                <option value="MS">MS</option>
                <option value="MG">MG</option>
                <option value="PA">PA</option>
                <option value="PB">PB</option>
                <option value="PR">PR</option>
                <option value="PE">PE</option>
                <option value="PI">PI</option>
                <option value="RJ">RJ</option>
                <option value="RN">RN</option>
                <option value="RS">RS</option>
                <option value="RO">RO</option>
                <option value="RR">RR</option>
                <option value="SC">SC</option>
                <option value="SP">SP</option>
                <option value="SE">SE</option>
                <option value="TO">TO</option>
            </select>

            <label for="Cep">Cep:</label>
            <input type="text" id="Cep" name="Candidato" required>
            </br></br>   

            <label for="descricao">descricao:</label>
            <input type="text" id="descricao" name="Candidato" required>
            </br></br>   

            <button type="button" id="createCandidato">Add Candidato</button>
        </form>

        <button id="go-page"> Ir para outra pagina</button>
        `;
    }

    onNavigate(): void {
        console.log("Navegou para a página Test");
        this.configurarEventosCreateCandidato();
    }

    onLeave(): void {
        console.log('Saindo da TestPage');
    }

    private configurarEventosCreateCandidato(): void {
        setTimeout(() => {
            const botao = document.getElementById('createCandidato');
            const botao2 = document.getElementById('go-page');
            let operation = 1;
            if(botao && operation == 1){
                botao.onclick = () => {
                    console.log('Clicou no botao');
                    let c = this.coletarDadosCandidato();
                    if(!c) return;
                    
                    console.log(c);
                    let candidatos = JSON.parse(localStorage.getItem('candidatos') || '[]');
                    candidatos.push(c);
                    localStorage.setItem('candidatos', JSON.stringify(candidatos));
                    localStorage.setItem("tipoCadastro","Candidato");
                    console.log('Candidato adicionado com sucesso!');
                    operation--;
                    router.navegação('/createCompetencia'); 
                };
            }

            if(botao2 && operation == 1){
                botao2.onclick = () => {
                    console.log('Clicou no botao para ir para home');
                    operation--;
                    router.navegação('/');
                };
            }
        });
    }

    private coletarDadosCandidato(): Candidato | null {
        console.log('Coletando dados do formulário...');
        
        const nomeElement = (document.getElementById('Nome') as HTMLInputElement).value;
        const emailElement = (document.getElementById('Email') as HTMLInputElement).value;
        const idadeElement = (document.getElementById('Idade') as HTMLInputElement).value;
        const cpfElement = (document.getElementById('CPF') as HTMLInputElement).value;
        const estadoElement = (document.getElementById('task-options') as HTMLSelectElement).value;
        const cepElement = (document.getElementById('Cep') as HTMLInputElement).value;
        const descricaoElement = (document.getElementById('descricao') as HTMLInputElement).value;


        return new Candidato(
            nomeElement.trim(),
            emailElement.trim(),
            parseInt(idadeElement),
            cpfElement,
            estadoElement,
            cepElement,
            descricaoElement.trim()
        );
         
    }
}