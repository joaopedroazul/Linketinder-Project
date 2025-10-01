import { Candidato } from "../Classes/Candidato";
import { Competencias } from "../Classes/Competencias";
import type { Pagina } from "../router/router";
import { router } from "../router/router";


export class menuCandidato implements Pagina {
    
    render(): string {
        return `<h2>Bem-vindo à página Menu Candidato!</h2>
        <button id="go-page"> Ver Vagas</button>
        <button id="go-page2"> Excluir essa conta</button>
        <button id="go-page3"> Logout</button>`;
    }

    onNavigate(): void {
        this.configurarEventos();
    }

    
    onLeave(): void {
        console.log('Saindo da HomePage');
    }

    private configurarEventos(): void {
        setTimeout(() => {
            const  botao = document.getElementById('go-page');
            const  botao2 = document.getElementById('go-page2');
            const  botao3 = document.getElementById('go-page3');
            
            if(botao){
                botao.addEventListener('click', () => {
                    router.navegação('/showVagas');
                });
            }

            if(botao2){
                botao2.addEventListener('click', () => {
                    let candidatos = JSON.parse(localStorage.getItem('candidatos') || '[]');
                    let index : number = -1 ;
                    for(let c in candidatos){
                        let candidato = candidatos[c]
                        if(candidato.nome+candidato.email+candidato.cpf == (localStorage.getItem("Id_candidato") || null)){
                            index = parseInt(c);
                            break;
                        }
                    }
                    const listarCandidatos = candidatos.filter((item :Candidato) => item.nome+item.email+item.cpf != (localStorage.getItem("Id_candidato") || null)  )
                    localStorage.setItem("candidatos",JSON.stringify(listarCandidatos))
                    Competencias.deleteCompetenciasById((localStorage.getItem("Id_candidato") || "" ))
                    localStorage.removeItem("Id_candidato");
                    router.navegação('/');
                });
            }

            if(botao3){
                botao3.addEventListener('click', () => {
                    localStorage.removeItem("Id_candidato");
                    router.navegação('/');
                });
            }
        });
    }

}