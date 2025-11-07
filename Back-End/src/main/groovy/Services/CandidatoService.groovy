package Services

import DAO.CandidatoDAO
import DAO.Competencia_CandidatoDAO
import DAO.VagaDAO
import Models.Candidato
import Models.Competencia
import Models.Vaga
import Views.CandidatoView
import Views.CompetenciaView
import Views.MenuView

import java.sql.Date

class CandidatoService {

    static  Scanner scanner = new Scanner(System.in)

    static Candidato criarCandidato(){
        Candidato novoCandidato = new Candidato()

        novoCandidato = CandidatoView.viewCreateCanditado();
        CandidatoDAO.criarCandidato(novoCandidato);
        List<Competencia> novasCompetencias = CompetenciaView.viewCreateCompetencias();
        novoCandidato = CandidatoDAO.listarUltimoCandidato()
        novasCompetencias.each { competencia -> Competencia_CandidatoDAO.createCompetencia_Candidato(competencia.id,novoCandidato.id)}

    }




    static Candidato verificarCandidato(String email, String senha){
        try{
            Candidato candidatoCadastrado = CandidatoDAO.Login(email,senha)

            if(candidatoCadastrado.getId() > 0)
                return candidatoCadastrado

        }catch(NullPointerException exception){
            return new Candidato(
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    Date.valueOf("2025-10-09"),
                    "",
                    0
            );
        }
    }

    static void loginCandidato(String email, String senha ){
        Candidato candidatoLogado =  verificarCandidato(email,senha)

        if(candidatoLogado.getId() > 0){
            int condicaoDeEntrada = 1
            while(condicaoDeEntrada == 1) {
                MenuView.MensagensDeMenu("Menu Candidato")
                int opcaoInterna = scanner.nextLine().toInteger()
                switch (opcaoInterna) {
                    case 1:
                        listarVagasDisponiveis()
                    case 2:
                        break
                    default:
                        continue

                }
            }
        }
        else{
            println("Candidato não cadastrado, crie sua conta")

        }
    }

 static void listarVagasDisponiveis(){
     println("Veja a lista de empresa buscando novos trabalhadores")
     VagaDAO.listarVaga().eachWithIndex { Vaga vaga, int indexVaga ->
         println("__________________________________________")
         println("\n Vaga " + (indexVaga + 1).toString())
         println(vaga.nome)
         println(vaga.descricao)
         println("\n\n")
     }
 }

}
