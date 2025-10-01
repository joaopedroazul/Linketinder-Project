export interface Pagina{
    render: () => string;
    onNavigate?: () => void;
    onLeave?: () => void;
}

export class Router {
    private static instance: Router;
    private rotas: Map<string, ()=> Pagina> = new Map();
    private paginaAtual: Pagina | null = null;

    private constructor() {
        this.configurandoEventosNavegacao();
    }

    static getInstance(): Router {
        if (!Router.instance) {
            Router.instance = new Router();
        }
        return Router.instance;
    }

    adicionarRota(path: string, pagina: () => Pagina): void {
        this.rotas.set(path, pagina);
    }

    navegação(path: string): void {
        if (this.paginaAtual && this.paginaAtual.onLeave) {
            this.paginaAtual.onLeave();
        }

        const FabricaDePaginas = this.rotas.get(path);
        if(!FabricaDePaginas){
            console.error(`Rota para o path "${path}" não encontrada.`);
            return;
        }
        window.history.pushState({path}, "", path);
        this.paginaAtual = FabricaDePaginas();
        if (this.paginaAtual.onNavigate) {
            this.paginaAtual.onNavigate();
        }
        this.renderizarPagina();
    }

    private renderizarPagina(): void {
        const appDiv = document.getElementById("app");
        if (appDiv && this.paginaAtual) {
            appDiv.innerHTML = this.paginaAtual.render();
        }
    }  

    private configurandoEventosNavegacao(): void{
        window.addEventListener("popstate", (event) => {
            if(event.state && event.state.path){
                this.navegação(event.state.path);
            }
        });

        document.addEventListener("click", (event) => {
            const target = event.target as HTMLElement;
            const link = target.closest("a[data-navigation]") as HTMLAnchorElement;

            if(link){
                event.preventDefault();
                const path = link.getAttribute("href");
                if(path){
                    this.navegação(path);
                }
            }
        });
    }

    start(): void {
        const caminhoInicial = window.location.pathname;
        this.navegação(caminhoInicial);
    }

}

export const router = Router.getInstance();

