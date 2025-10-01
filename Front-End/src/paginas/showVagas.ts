import { Candidato } from "../Classes/Candidato";
import { Competencias } from "../Classes/Competencias";
import { Empresa } from "../Classes/Empresa";
import type { Vagas } from "../Classes/Vagas";
import type { Pagina } from "../router/router";
import { router } from "../router/router";

export class showVagas implements Pagina {
    
    render(): string {
        

        return `
        ${this.cssPage()}
         <h1>vagas disponiveis</h1>
            <div class="vagas-grid">
                ${this.listarVagas()}
            </div>
            <button id="go-page"> Voltar ao menu</button>`;
        ;
            
    }

    private cssPage(): string {
        return `
                <style>
            * {
                margin: 0;
                padding: 0;
                box-sizing: border-box;
            }

            body {
                font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
                background-color: #f5f5f5;
                padding: 20px;
            }

            .container {
                max-width: 1200px;
                margin: 0 auto;
            }

            h1 {
                text-align: center;
                color: #333;
                margin-bottom: 30px;
            }

            .vagas-grid {
                display: grid;
                grid-template-columns: repeat(auto-fit, minmax(300px, 1fr));
                gap: 20px;
                padding: 20px;
            }

            .card {
                background: white;
                border-radius: 10px;
                box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
                padding: 25px;
                transition: transform 0.3s ease, box-shadow 0.3s ease;
            }

            .card:hover {
                transform: translateY(-5px);
                box-shadow: 0 8px 15px rgba(0, 0, 0, 0.15);
            }

            .nome-candidato {
                font-size: 1.4em;
                font-weight: bold;
                color: #2c3e50;
                margin-bottom: 15px;
                padding-bottom: 10px;
                border-bottom: 2px solid #3498db;
            }

            .atributos-lista {
                list-style: none;
            }

            .atributos-lista li {
                padding: 8px 0;
                border-bottom: 1px solid #ecf0f1;
                display: flex;
                align-items: center;
            }

            .atributos-lista li:last-child {
                border-bottom: none;
            }

            .atributos-lista li::before {
                content: "•";
                color: #3498db;
                font-weight: bold;
                margin-right: 10px;
            }

            .status {
                display: inline-block;
                padding: 4px 12px;
                border-radius: 20px;
                font-size: 0.8em;
                font-weight: bold;
                margin-top: 10px;
            }

            .status-aprovado {
                background-color: #d4edda;
                color: #155724;
            }

            .status-analise {
                background-color: #fff3cd;
                color: #856404;
            }

            .status-reprovado {
                background-color: #f8d7da;
                color: #721c24;
            }

            .destaque {
                border-left: 4px solid #e74c3c;
            }
        </style>    
        `
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
            const botao = document.getElementById('go-page');
            let operation = 1;
            if(botao && operation == 1){
                botao.onclick = () => {
                    
                    operation--;
                    router.navegação('/menuCandidato'); 
                };
            }
        });
    }

    private listarVagas(): string {
        const vagas: Vagas[] = JSON.parse(localStorage.getItem('vagas') || '[]');
        let resultado = '';
        for(let v in vagas){
            console.log(vagas[v]);
            const vaga = vagas[v];
            
            if (vaga) {
                resultado +=`<div class="card">
                    <div class="nome-vaga">Vaga `+vaga.titulo+`</div>
                    </br></br><span>Requisitos:<\span></br></br>
                    <ul class="atributos-lista">`
               
                    vaga.atributos.forEach(comp => {
                        resultado += `<li>${comp}</li>`;
                    }
                );
                resultado += `</ul>
                
                </div>`;
            }
        }
        return resultado;
    }


    
}