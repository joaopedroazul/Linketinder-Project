import { Empresa } from "../../Classes/Empresa";
import type { Pagina } from "../../router/router";
import { router } from "../../router/router";

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
                    if(c == 1) {
                        console.log("c: ",c)
                        operation--;
                        router.navegação('/createEmpresa');
                    }
                    else{
                        console.log(c);
                        let Empresas = JSON.parse(localStorage.getItem('Empresas') || '[]');
                        Empresas.push(c);
                        localStorage.setItem('Empresas', JSON.stringify(Empresas));
                        localStorage.setItem("tipoCadastro","Empresa");
                        console.log('Empresa adicionado com sucesso!');
                        operation--;
                        router.navegação('/createCompetencia');
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

    private coletarDadosEmpresa(): Empresa | number {
        console.log('Coletando dados do formulário...');
        
        const nomeElement = (document.getElementById('Nome') as HTMLInputElement).value;
        const emailElement = (document.getElementById('Email') as HTMLInputElement).value;
        const paisElement = (document.getElementById('Pais') as HTMLInputElement).value;
        const cnpjElement = (document.getElementById('Cnpj') as HTMLInputElement).value;
        const estadoElement = (document.getElementById('task-options') as HTMLSelectElement).value;
        const cepElement = (document.getElementById('Cep') as HTMLInputElement).value;
        const descricaoElement = (document.getElementById('descricao') as HTMLInputElement).value;

        if(nomeElement){
            const regex = /[0-9]+/gi
            if(nomeElement.match(regex)){
                alert("O campo nome não aceita caracteres numericos")
                return 1
            }
        }
        if(emailElement){
            const regex = /\w+@\w+.\w+/gi
            if(!emailElement.match(regex)){
                alert("O campo Email é invalido. Faça o cadastro novamente")
                return 1
            }
        }

        if(cnpjElement){
            const regex = /\d{2}.\d{3}.\d{3}\/\d{4}-\d{2}/
            if(!cnpjElement.match(regex)){
                alert("O campo CPF é invalido. Faça o cadastro novamente")
                return 1
            }
        }
        if(cepElement){
            const regex = /\d{5}-\d{3}/
            if(!cepElement.match(regex)){
                alert("O campo cep é invalido. Faça o cadastro novamente")
                return 1
            }
        }

        return new Empresa(
            nomeElement.trim(),
            emailElement.trim(),
            paisElement.trim(),
            cnpjElement,
            estadoElement,
            cepElement,
            descricaoElement.trim()
        );
    }  
}