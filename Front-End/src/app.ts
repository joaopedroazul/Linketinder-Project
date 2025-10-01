import { Router } from "./router/router";
import { Home } from "./paginas/home";
import { createCandidato } from "./paginas/Create/createCandidato";
import { createEmpresa } from "./paginas/Create/createEmpresa";
import { createCompetencia } from "./paginas/Create/createCompetencias";
import { showCandidatos } from "./paginas/showCandidatos";
import { showVagas } from "./paginas/showVagas";
import { createVagas } from "./paginas/Create/createVagasTitle";
import { menuEmpresa } from "./paginas/menuEmpresa";
import { createVagasAttributes } from "./paginas/Create/createVagasAttributes";
import { menuCandidato } from "./paginas/menuCandidato";
import { menuCadastro } from "./paginas/menuCadastro";
import { login } from "./paginas/login";

// Configurando as rotas
const router = Router.getInstance();
router.adicionarRota("/", () => new Home());

router.adicionarRota("/createCandidato", () => new createCandidato());
router.adicionarRota("/createEmpresa", () => new createEmpresa());
router.adicionarRota("/createCompetencia", () => new createCompetencia());
router.adicionarRota("/createVagas", () => new createVagas());
router.adicionarRota("/createVagasAttributes", () => new createVagasAttributes());

router.adicionarRota("/showCandidatos", () => new showCandidatos());
router.adicionarRota("/showVagas", () => new showVagas());

router.adicionarRota("/menuEmpresa", () => new menuEmpresa());
router.adicionarRota("/menuCandidato", () => new menuCandidato());
router.adicionarRota("/menuCadastro", () => new menuCadastro());

router.adicionarRota("/login", () => new login());


if(!localStorage.getItem('Empresas')) 
    localStorage.setItem('Empresas', JSON.stringify([]));
if(!localStorage.getItem('candidatos'))
    localStorage.setItem('candidatos', JSON.stringify([]));
if(!localStorage.getItem('competencias'))
    localStorage.setItem('competencias', JSON.stringify([]));
if(!localStorage.getItem('vagas'))
    localStorage.setItem('vagas', JSON.stringify([]));

document.addEventListener("DOMContentLoaded", () => {
    router.start();
});
declare global {
    interface Window {
        router: typeof router;
    }
    
}
window.router = router;