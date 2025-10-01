import { Candidato } from "../Classes/Candidato";
import type { Pagina } from "../router/router";
import { router } from "../router/router";

export class login implements Pagina {
    
    render(): string {
        return `
        <h2>Bem-vindo à página Login !</h2>
        <p>Esta é a página teste do nosso aplicativo.</p>

        <form id="Login-form">  
            <label for="CPF">CPF/CNPJ:</label>
            <input type="text" id="CPF" name="Login" required>
            </br></br>

            <label for="Email">Email:</label>
            <input type="text" id="Email" name="Login" required>
            </br></br>  

            <button type="button" id="Login">Entrar</button>
        </form>

        <button id="go-page">Voltar para pagina inicial</button>
        `;
    }

    onNavigate(): void {
        console.log("Navegou para a página Test");
        this.configurarEventosLogin();
    }

    onLeave(): void {
        console.log('Saindo da TestPage');
    }

    private configurarEventosLogin(): void {
        setTimeout(() => {
            const botao = document.getElementById('Login');
            const botao2 = document.getElementById('go-page');
            let operation = 1;
            if(botao && operation == 1){
                botao.onclick = () => {
                    console.log('Clicou no botao');
                    let dados = this.coletarDadosLogin();
                   
                    let candidatos = JSON.parse(localStorage.getItem('candidatos') || '[]');
                    let empresas = JSON.parse(localStorage.getItem('Empresas') || '[]');
                    let logado = false;
                    let option = '';
                    let id = '';
                    for (let c in candidatos){
                        let candidato = candidatos[c];
                        if(candidato.email == dados![0] && candidato.cpf == dados![1]){
                            logado = true;
                            id = candidato.nome+candidato.email+candidato.cpf
                            option = 'Candidato';
                            break
                        }
                    }

                    for (let e in empresas){
                        let empresa = empresas[e];
                        if(empresa.email == dados![0] && empresa.cnpj == dados![1]){
                            logado = true;
                            id = empresa.nome+empresa.email+empresa.cnpj
                            option = 'Empresa';
                            break
                        }
                    }
                    console.log('Candidato adicionado com sucesso!');
                    operation--;
                    if (logado && option == 'Candidato'){
                        localStorage.setItem("Id_candidato",id)
                        router.navegação('/menuCandidato'); 
                    }else if (logado && option == 'Empresa'){
                        localStorage.setItem("Id_empresa",id)
                        router.navegação('/menuEmpresa'); 
                    }else{
                        alert("Usuário não encontrado, por favor verifique os dados ou cadastre-se");
                        router.navegação('/Login'); 
                    }
                    
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

    private coletarDadosLogin(): [String, String] | null {
        console.log('Coletando dados do formulário...');
        
        const cpfElement = document.getElementById('CPF') as HTMLInputElement;
        const emailElement = document.getElementById('Email') as HTMLInputElement;
        
        const emailInput = emailElement.value;
        const cpfInput = cpfElement.value;

        console.log('Valores coletados:', {
            email: emailInput,
            cpf: cpfInput
        });

        return [emailInput.trim(),cpfInput];
        
    }
}