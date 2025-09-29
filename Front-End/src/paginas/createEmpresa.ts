import { Empresa } from "../Classes/Empresa";
import type { Pagina } from "../router/router";
import { router } from "../router/router";

export class createEmpresa implements Pagina {
    
    render(): string {
        return `
        <h2>Bem-vindo à página Cadastro Empresa!</h2>
        <p>Esta é a página teste do nosso aplicativo.</p>

        <form id="Empresa-form">  
            <label for="Nome">Nome:</label>
            <input type="text" id="Nome" name="Empresa" required>
            </br></br>

            <label for="Email">Email:</label>
            <input type="text" id="Email" name="Empresa" required>
            </br></br>  

            <label for="pais">País:</label>
            <input type="text" id="Pais" name="Empresa" required>
            </br></br>  

            <label for="cnpj">CNPJ:</label>
            <input type="text" id="Cnpj" name="Empresa" required>
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
            <input type="text" id="Cep" name="Empresa" required>
            </br></br>   

            <label for="descricao">descricao:</label>
            <input type="text" id="descricao" name="Empresa" required>
            </br></br>   

            <button type="button" id="createEmpresa">Add Empresa</button>
        </form>

        <button id="go-page"> Ir para outra pagina</button>
        `;
    }

    onNavigate(): void {
        console.log("Navegou para a página createEmpresa");
        this.configurarEventosCreateEmpresa();
    }

    onLeave(): void {
        console.log('Saindo da createEmpresa');
    }

    private configurarEventosCreateEmpresa(): void {
        setTimeout(() => {
            const botao = document.getElementById('createEmpresa');
            const botao2 = document.getElementById('go-page');
            let operation = 1;
            if(botao && operation == 1){
                botao.onclick = () => {
                    console.log('Clicou no botao');
                    let c = this.coletarDadosEmpresa();
                    if(!c) return;
                    
                    console.log(c);
                    let Empresas = JSON.parse(localStorage.getItem('Empresas') || '[]');
                    Empresas.push(c);
                    localStorage.setItem('Empresas', JSON.stringify(Empresas));
                    console.log('Empresa adicionado com sucesso!');
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

    private coletarDadosEmpresa(): Empresa | null {
        console.log('Coletando dados do formulário...');
        
        // Obter elementos - USANDO OS MESMOS IDs DO HTML
        const nomeElement = document.getElementById('Nome') as HTMLInputElement;
        const emailElement = document.getElementById('Email') as HTMLInputElement;
        const paisElement = document.getElementById('Pais') as HTMLInputElement;
        const cnpjElement = document.getElementById('Cnpj') as HTMLInputElement;
        const estadoElement = document.getElementById('task-options') as HTMLSelectElement;
        const cepElement = document.getElementById('Cep') as HTMLInputElement;
        const descricaoElement = document.getElementById('descricao') as HTMLInputElement;

        // Debug: verificar quais elementos foram encontrados
        console.log('Elementos encontrados:', {
            nome: nomeElement,
            email: emailElement,
            pais: paisElement,
            cnpj: cnpjElement,
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
        if (!paisElement) {
            console.error('Elemento pais não encontrado!');
            alert('Erro: Campo pais não encontrado.');
            return null;
        }
        if (!cnpjElement) {
            console.error('Elemento cnpj não encontrado!');
            alert('Erro: Campo cnpj não encontrado.');
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
        const paisInput = paisElement.value;
        const cnpjInput = cnpjElement.value;
        const estadoSelect = estadoElement.value;
        const cepInput = cepElement.value;
        const descricaoInput = descricaoElement.value;

        console.log('Valores coletados:', {
            nome: nomeInput,
            email: emailInput,
            pais: paisInput,
            cnpj: cnpjInput,
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


        if (!cnpjInput.trim() || cnpjInput.length !== 11) {
            alert('cnpj deve conter 11 dígitos');
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
            return new Empresa(
                nomeInput.trim(),
                emailInput.trim(),
                paisInput.trim(),
                cnpjInput,
                estadoSelect,
                cepInput,
                descricaoInput.trim()
            );
        } catch (error) {
            console.error('Erro ao criar Empresa:', error);
            alert('Erro ao criar Empresa. Verifique os dados.');
            return null;
        }
    }
}