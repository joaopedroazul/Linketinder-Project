import { Vagas } from "../../Classes/Vagas";
import type { Pagina } from "../../router/router";
import { router } from "../../router/router";

export class createVagasAttributes implements Pagina {
    
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

            <form id="vaga-form">  

            
                <label for="Estado">Estado:</label>
                <select id="task-options">
                    ${JSON.parse(arrayComp || '[]')
                        .filter((item: [string, number]) => item[1] === 0)
                        .map((item: [string, number]) => `<option value="${item[0]}">${item[0]}</option>`)
                        .join('')}
                </select>

                
                <button type="button" id="createVaga">Add Competencia</button>
            </form>

            <button id="go-page">Finalizar cadastro</button>
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
                    this.coletarDadosVagaParte();
                    console.log('Competencia adicionado com sucesso!');
                    operation--;
                    router.navegação('/createVagasAttributes');
                     
                };
            }

            if(botao2 && operation == 1){
                botao2.onclick = () => {
                    let listaCompetencias = JSON.parse(localStorage.getItem('ListaCompetenciasParcial') || '[]');
                    let vaga = new Vagas((localStorage.getItem("TituloVaga") || ""), listaCompetencias, (localStorage.getItem("Id_empresa") || ""));
                    const listaVagas = JSON.parse(localStorage.getItem('vagas') || '[]');
                    if(!listaVagas.includes(vaga)){
                        listaVagas.push(vaga);
                        localStorage.setItem('vagas', JSON.stringify(listaVagas));
                    }
                    localStorage.removeItem('ListaCompetenciasParcial');
                    localStorage.removeItem('tipoCadastro');
                    localStorage.removeItem('CompetenciasOptions');
                    localStorage.removeItem('parte2CreateVaga');
                    localStorage.removeItem('TituloVaga');
                    console.log('terminou vag');
                    operation--;
                    router.navegação('/menuEmpresa');
                };
            }
        });
    }

 
    
    private coletarDadosVagaParte(): void {
        console.log('Coletando dados do formulário...');
        
        const competenciaElement = document.getElementById('task-options') as HTMLSelectElement;

        if (!competenciaElement) {
            console.error('Elemento Nome não encontrado!');
        }

        const competenciaInput = competenciaElement.value;


        if (!competenciaInput) {
            console.log('competencia é obrigatório');
        }

        try {
            if(!localStorage.getItem('ListaCompetenciasParcial')){ 
                localStorage.setItem('ListaCompetenciasParcial', JSON.stringify([competenciaInput]));
            }else {
                const currentList = JSON.parse(localStorage.getItem('ListaCompetenciasParcial') || '[]');
                if(!currentList.includes(competenciaInput)){
                    currentList.push(competenciaInput);
                    localStorage.setItem('ListaCompetenciasParcial', JSON.stringify(currentList));
                }
            } 
            const competenciasOptions = JSON.parse(localStorage.getItem('CompetenciasOptions') || '[]');
            const index = competenciasOptions.findIndex((item: [string, number]) => item[0] === competenciaInput);
            if (index !== -1) {
                competenciasOptions[index][1] = 1; 
                localStorage.setItem('CompetenciasOptions', JSON.stringify(competenciasOptions));
            }   
        } catch (error) {
            console.error('Erro ao criar Competencia:', error);
            alert('Erro ao criar Competencia. Verifique os dados.');
        }
    }



}