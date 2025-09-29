import { Router } from "./router/router";
import { Home } from "./paginas/home";
import { createCandidato } from "./paginas/createCandidato";
import { createEmpresa } from "./paginas/createEmpresa";

// Configurando as rotas
const router = Router.getInstance();
router.adicionarRota("/", () => new Home());
router.adicionarRota("/createCandidato", () => new createCandidato());
router.adicionarRota("/createEmpresa", () => new createEmpresa());
if(!localStorage.getItem('Empresas')) 
    localStorage.setItem('Empresas', JSON.stringify([]));
if(!localStorage.getItem('candidatos'))
    localStorage.setItem('candidatos', JSON.stringify([]));

document.addEventListener("DOMContentLoaded", () => {
    router.start();
});
declare global {
    interface Window {
        router: typeof router;
    }
    
}
window.router = router;