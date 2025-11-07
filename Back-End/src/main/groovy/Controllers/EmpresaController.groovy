package Controllers

import Models.Empresa
import Services.CandidatoService
import Services.EmpresaService

class EmpresaController {

    static void createEmpresa(){
        Empresa possivelEmpresaCriada = EmpresaService.criarEmpresa()
        try{
            if(possivelEmpresaCriada.getId() > 0)
                println("Empresa Cadastrada com sucesso")
        }catch (Exception excecao)
        println("Ocorreu um erro durante o cadastro")
    }

    static void loginEmpresa(String email,String senha){
        EmpresaService.loginEmpresa(email, senha)
    }


}
