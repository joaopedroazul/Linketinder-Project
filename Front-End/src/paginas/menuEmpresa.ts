import type { Empresa } from "../Classes/Empresa";
import type { Pagina } from "../router/router";
import { router } from "../router/router";


export class menuEmpresa implements Pagina {
    
    render(): string {
        return `<h2>Bem-vindo à página Home!</h2>
        <p>Esta é a página inicial do nosso aplicativo.</p>
        <button id="go-page"> Criar uma Vaga</button>
        <button id="go-page2"> Ver Candidatos</button>
        <button id="go-page3"> Excluir essa conta</button>
        <button id="go-page4">Logout</button>`;
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
            const  botao4 = document.getElementById('go-page4');
            if(botao){
                botao.addEventListener('click', () => {
                    localStorage.setItem("Id_empresa",'1')
                    router.navegação('/createVagas'); 
                });
            }

            if(botao2){
                botao2.addEventListener('click', () => {
                    router.navegação('/showCandidatos');
                });
            }

            if(botao3){
                botao3.addEventListener('click', () => {
                    let empresas = JSON.parse(localStorage.getItem('Empresas') || '[]');
                    let index : number = -1 ;
                    for(let e in empresas){
                        let empresa = empresas[e]
                        if(empresa.nome+empresa.email+empresa.cnpj == (localStorage.getItem("Id_empresa") || null)){
                            index = parseInt(e);
                            break;
                        }
                    }
                    const listaEmpresas = empresas.filter((item :Empresa) => item.nome+item.email+item.cnpj != (localStorage.getItem("Id_empresa") || null)  )
                    localStorage.setItem("Empresas",JSON.stringify(listaEmpresas))
                    localStorage.removeItem("Id_empresa");
                    console.log(listaEmpresas)
                    router.navegação('/');
                });
            }

            if(botao4){
                botao4.addEventListener('click', () => {
                    localStorage.removeItem("Id_empresa");
                    router.navegação('/');
                });
            }
        });
    }

}