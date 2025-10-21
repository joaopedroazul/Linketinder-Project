package Classes

import DAO.CompetenciaDAO
import Interfaces.IPessoa

class Pessoa implements IPessoa{

    String nome, cep, descricao;
    ArrayList<String> competencias;

    @Override
    ArrayList<Pessoa>  listarCandidato() {
        return null
    }

    @Override
    ArrayList<Pessoa>   listarEmpresa() {
        return null
    }

    static ArrayList<Competencia> viewCreateCompetencias(){
        Scanner scanner = new Scanner(System.in)
        ArrayList<Competencia> competenciasDisponiveis = new ArrayList<>()
        ArrayList<Competencia> competenciasAtribuidas = new ArrayList<>()

        CompetenciaDAO.listarCompetencia().each {Competencia novaCompetencia -> competenciasDisponiveis.add(novaCompetencia)}

        println("\n\nIremos selecionar Algumas Competencias para o seu perfil\n")
        println("Para isso selecione a competencia disponivel na lista a seguir: ")
        println("\n\n")
        competenciasDisponiveis.each {competencia ->print((competencia.getId()).toString()+": "+competencia.getNome()+"\n")}
        Integer condicaoDeSaida = 0
        while(condicaoDeSaida < 1){

            println("\nSelecione o numero da competencia ou digite 0 para sair ")
            int option = scanner.nextLine().toInteger()
            if (option != 0) {
                competenciasAtribuidas.add(competenciasDisponiveis.get(option-1))
                competenciasDisponiveis.remove(option-1)
            }else{
                break
            }
            print("\033[H\033[2J")
            System.out.flush()
            competenciasDisponiveis.eachWithIndex {competencia,indexCompetencia ->print((indexCompetencia+1).toString()+": "+competencia.getNome()+"\n")}
        }

        return competenciasAtribuidas
    }

}
