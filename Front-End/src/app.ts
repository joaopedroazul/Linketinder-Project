import { Router } from "./router/router";
import { Home } from "./paginas/home";
import { Test } from "./paginas/test";

// Configurando as rotas
const router = Router.getInstance();
router.adicionarRota("/", () => new Home());
router.adicionarRota("/Test", () => new Test());

document.addEventListener("DOMContentLoaded", () => {
    router.start();
});
declare global {
    interface Window {
        router: typeof router;
    }
}
window.router = router;