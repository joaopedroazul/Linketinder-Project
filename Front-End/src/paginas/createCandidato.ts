import { Candidato } from "../Classes/Candidato";
import type { Pagina } from "../router/router";
import { router } from "../router/router";

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
                    console.log('Candidato adicionado com sucesso!');
                    operation--;
                    router.navegação('/'); 
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
        
        // Obter elementos - USANDO OS MESMOS IDs DO HTML
        const nomeElement = document.getElementById('Nome') as HTMLInputElement;
        const emailElement = document.getElementById('Email') as HTMLInputElement;
        const idadeElement = document.getElementById('Idade') as HTMLInputElement;
        const cpfElement = document.getElementById('CPF') as HTMLInputElement;
        const estadoElement = document.getElementById('task-options') as HTMLSelectElement;
        const cepElement = document.getElementById('Cep') as HTMLInputElement;
        const descricaoElement = document.getElementById('descricao') as HTMLInputElement;

        // Debug: verificar quais elementos foram encontrados
        console.log('Elementos encontrados:', {
            nome: nomeElement,
            email: emailElement,
            idade: idadeElement,
            cpf: cpfElement,
            estado: estadoElement,
            cep: cepElement,
            descricao: descricaoElement
        });

        // Validar se elementos existem ANTES de acessar .value
        if (!nomeElement) {
            console.error('Elemento Nome não encontrado!');
            alert('Erro: Campo Nome não encontrado.');
            return null;
        }
        if (!emailElement) {
            console.error('Elemento Email não encontrado!');
            alert('Erro: Campo Email não encontrado.');
            return null;
        }
        if (!idadeElement) {
            console.error('Elemento Idade não encontrado!');
            alert('Erro: Campo Idade não encontrado.');
            return null;
        }
        if (!cpfElement) {
            console.error('Elemento CPF não encontrado!');
            alert('Erro: Campo CPF não encontrado.');
            return null;
        }
        if (!estadoElement) {
            console.error('Elemento Estado não encontrado!');
            alert('Erro: Campo Estado não encontrado.');
            return null;
        }
        if (!cepElement) {
            console.error('Elemento Cep não encontrado!');
            alert('Erro: Campo Cep não encontrado.');
            return null;
        }
        if (!descricaoElement) {
            console.error('Elemento descricao não encontrado!');
            alert('Erro: Campo descricao não encontrado.');
            return null;
        }

        // Agora sim podemos acessar .value
        const nomeInput = nomeElement.value;
        const emailInput = emailElement.value;
        const idadeInput = idadeElement.value;
        const cpfInput = cpfElement.value;
        const estadoSelect = estadoElement.value;
        const cepInput = cepElement.value;
        const descricaoInput = descricaoElement.value;

        console.log('Valores coletados:', {
            nome: nomeInput,
            email: emailInput,
            idade: idadeInput,
            cpf: cpfInput,
            estado: estadoSelect,
            cep: cepInput,
            descricao: descricaoInput
        });

        // Validar dados
        if (!nomeInput.trim()) {
            alert('Nome é obrigatório');
            return null;
        }
        if (!emailInput.trim()) {
            alert('Email é obrigatório');
            return null;
        }

        const idade = parseInt(idadeInput);
        if (isNaN(idade) || idade < 16 || idade > 100) {
            alert('Idade deve ser entre 16 e 100 anos');
            return null;
        }

        if (!cpfInput.trim() || cpfInput.length !== 11) {
            alert('CPF deve conter 11 dígitos');
            return null;
        }

        if (!estadoSelect) {
            alert('Estado é obrigatório');
            return null;
        }

        if (!cepInput.trim() || cepInput.length !== 8) {
            alert('CEP deve conter 8 dígitos');
            return null;
        }

        if (!descricaoInput.trim()) {
            alert('Descrição é obrigatória');
            return null;
        }

        try {
            return new Candidato(
                nomeInput.trim(),
                emailInput.trim(),
                idade,
                cpfInput,
                estadoSelect,
                cepInput,
                descricaoInput.trim()
            );
        } catch (error) {
            console.error('Erro ao criar candidato:', error);
            alert('Erro ao criar candidato. Verifique os dados.');
            return null;
        }
    }
}