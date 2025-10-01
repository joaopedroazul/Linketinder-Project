import { Competencias } from "../../Classes/Competencias";
import type { Pagina } from "../../router/router";
import { router } from "../../router/router";

export class createCompetencia implements Pagina {
    
    render(): string {
        let arrayComp: string | null;
        if(!localStorage.getItem('CompetenciasOptions')){
            const defaultComp = [["Java",0],["Python",0],["JavaScript",0],["C#",0],["Ruby",0],["PHP",0],["C++",0],["Swift",0],["Go",0],["Kotlin",0]];
            localStorage.setItem('CompetenciasOptions', JSON.stringify(defaultComp));
            arrayComp = JSON.stringify(defaultComp);
        } else {
            arrayComp = localStorage.getItem('CompetenciasOptions');
        }
            

        return `
        <h2>Bem-vindo à página Cadastro Empresa!</h2>
        <p>Esta é a página teste do nosso aplicativo.</p>

        <form id="Empresa-form">  
            
            <label for="Estado">Estado:</label>
            <select id="task-options">
                ${JSON.parse(arrayComp || '[]')
                    .filter((item: [string, number]) => item[1] === 0)
                    .map((item: [string, number]) => `<option value="${item[0]}">${item[0]}</option>`)
                    .join('')}
            </select>

            
            <button type="button" id="createEmpresa">Add Competencia</button>
        </form>

        <button id="go-page">Finalizar cadastro</button>
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
                    this.coletarDadosEmpresa();
                    console.log('Competencia adicionado com sucesso!');
                    operation--;
                    router.navegação('/createCompetencia');
                     
                };
            }

            if(botao2 && operation == 1){
                botao2.onclick = () => {
                    localStorage.getItem("competencias");
                    let id_user = "";
                    if (localStorage.getItem("tipoCadastro") === "Candidato") {
                        let candidatos = JSON.parse(localStorage.getItem('candidatos') || '[]');
                        let c = candidatos.pop();
                        id_user = c.nome+c.email+c.cpf;
                    } else {
                        let empresas = JSON.parse(localStorage.getItem('Empresas') || '[]');
                        console.log(empresas);
                        let e = empresas.pop();
                        id_user = e.nome+e.email+e.cnpj;
                    }
                    let listaCompetencias = JSON.parse(localStorage.getItem('ListaCompetenciasParcial') || '[]');
                    let comp = new Competencias(id_user, listaCompetencias);
                    Competencias.saveCompetencias(comp);
                    localStorage.removeItem('ListaCompetenciasParcial');
                    localStorage.removeItem('tipoCadastro');
                    localStorage.removeItem('CompetenciasOptions');
                    console.log(comp);
                    console.log('Clicou no botao para ir para home');
                    operation--;
                    router.navegação('/');
                };
            }
        });
    }

    private coletarDadosEmpresa(): void {
        console.log('Coletando dados do formulário...');
        
        const competenciaElement = (document.getElementById('task-options') as HTMLSelectElement).value;
    
        if(!localStorage.getItem('ListaCompetenciasParcial')){ 
            localStorage.setItem('ListaCompetenciasParcial', JSON.stringify([competenciaElement]));
        }else {
            const currentList = JSON.parse(localStorage.getItem('ListaCompetenciasParcial') || '[]');
            if(!currentList.includes(competenciaElement)){
                currentList.push(competenciaElement);
                localStorage.setItem('ListaCompetenciasParcial', JSON.stringify(currentList));
            }
        } 

        const competenciasOptions = JSON.parse(localStorage.getItem('CompetenciasOptions') || '[]');
        
        const index = competenciasOptions.findIndex((item: [string, number]) => item[0] === competenciaElement);
        if (index !== -1) {
            competenciasOptions[index][1] = 1; 
            localStorage.setItem('CompetenciasOptions', JSON.stringify(competenciasOptions));
        }   
    
    }
}