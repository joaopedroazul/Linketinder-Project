package Controllers

import Models.Candidato
import Services.CandidatoService

class CandidatoController {
    static void createCandidato(){
        Candidato possivelCandidatoCriado = CandidatoService.criarCandidato()
        try{
            if(possivelCandidatoCriado.getId() > 0)
                println("Candidato Cadastrado com sucesso")
        }catch (Exception excecao)
        println("Ocorreu um erro durante o cadastro")
    }

    static void loginCandidato(String email,String senha){
        CandidatoService.loginCandidato(email, senha)
    }


}
