import { Candidato } from "../Classes/Candidato";
import { Competencias } from "../Classes/Competencias";
import { Empresa } from "../Classes/Empresa";
import type { Pagina } from "../router/router";
import { router } from "../router/router";

export class showCandidatos implements Pagina {
    
    render(): string {
        Competencias.getDataCompetencias();
        const competencias: string[] = JSON.parse(localStorage.getItem('DataCompetencias') || '[]');
        console.log()
        return `
        ${this.cssPage()}
         <h1>Candidatos disponiveis</h1>
            <div class="candidatos-grid">
                ${this.listarCandidatos()}
            </div>
            <button id="go-page"> Voltar ao menu</button>
            <div class="bar-chart-container">
                <div class="bar-chart">
                    <div class="bar" style="height: ${competencias[0]}%;"><span class="label">Java</span></div>
                    <div class="bar" style="height: ${competencias[1]}%;"><span class="label">Python</span></div>
                    <div class="bar" style="height: ${competencias[2]}%;"><span class="label">JavaScript</span></div>
                    <div class="bar" style="height: ${competencias[3]}%;"><span class="label">C#</span></div>
                    <div class="bar" style="height: ${competencias[4]}%;"><span class="label">Ruby</span></div>
                    <div class="bar" style="height: ${competencias[5]}%;"><span class="label">PHP</span></div>
                    <div class="bar" style="height: ${competencias[6]}%;"><span class="label">C++</span></div>
                    <div class="bar" style="height: ${competencias[7]}%;"><span class="label">Swift</span></div>
                    <div class="bar" style="height: ${competencias[8]}%;"><span class="label">Go</span></div>
                    <div class="bar" style="height: ${competencias[9]}%;"><span class="label">Kotlin</span></div>
                </div>
            </div>
            
        `;
            
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

            .candidatos-grid {
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

            .bar-chart-container {
                width: 90%;
                margin: 50px auto;
                border: 1px solid #ccc;
                padding: 20px;
                box-shadow: 0 0 10px rgba(0,0,0,0.1);
            }

            .bar-chart {
                display: flex;
                align-items: flex-end; /* Align bars to the bottom */
                height: 300px; /* Set a fixed height for the chart area */
                border-bottom: 1px solid #eee;
                padding: 15px
            }

            .bar {
                flex-grow: 1; /* Distribute space equally */
                margin: 0 5px;
                background-color: #4CAF50; /* Bar color */
                position: relative;
                display: flex;
                justify-content: center;
                align-items: flex-end; /* Align label to the bottom of the bar */
                padding-bottom: 5px; /* Space for the label */
            }

            .bar .label {
                color: black;
                font-size: 0.8em;
                text-align: center;
                transform: rotate(-45deg); /* Rotate labels for better readability if needed */
                transform-origin: bottom center;
                position: absolute;
                bottom: -32px; /* Adjust position as needed */
                white-space: nowrap;
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
                    router.navegação('/menuEmpresa'); 
                };
            }
        });
    }

    private listarCandidatos(): string {
        const candidatos: Candidato[] = JSON.parse(localStorage.getItem('candidatos') || '[]');
        const competencias: Competencias[] = JSON.parse(localStorage.getItem('competencias') || '[]');
        let resultado = '';
        for(let c in candidatos){
            console.log(candidatos[c]);
            const candidato = candidatos[c];
            
            if (candidato) {
                resultado +=`<div class="card">
                    <div class="nome-candidato">Candidato `+(parseInt(c) + 1).toString()+`</div>
                    <ul class="atributos-lista">`
                const list = Competencias.getCompetenciasById(candidato.nome + candidato.email + candidato.cpf);
                if (list) {
                    list.forEach(comp => {
                        console.log(` - ${comp}`);
                        resultado += `<li>${comp}</li>`;
                    });
                }
                resultado += `</ul>
                
                </div>`;
            }
        }
        return resultado;
    }

    

    
}