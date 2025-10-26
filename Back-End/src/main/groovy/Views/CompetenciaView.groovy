package Views

import Classes.Competencia
import DAO.CompetenciaDAO

class CompetenciaView {

    static ArrayList<Competencia> viewCreateCompetencias(){
        Scanner scanner = new Scanner(System.in)
        ArrayList<Competencia> competenciasDisponiveis = new ArrayList<>()
        ArrayList<Competencia> competenciasAtribuidas = new ArrayList<>()

        CompetenciaDAO.listarCompetencia().each { Competencia novaCompetencia -> competenciasDisponiveis.add(novaCompetencia)}

        println("\n\nIremos selecionar Algumas Competencias para o seu perfil\n")
        println("Para isso selecione a competencia disponivel na lista a seguir: ")
        println("\n\n")
        competenciasDisponiveis.each {competencia ->print((competencia.getId()).toString()+": "+competencia.getNome()+"\n")}
        Integer condicaoDeSaida = 0
        while(condicaoDeSaida < 1){

            println("\nSelecione o numero da competencia ou digite 0 para sair ")
            int option = getOption()
            while(option == -1){
                option = getOption()
            }
            if (option != 0) {
                competenciasAtribuidas.add(competenciasDisponiveis.get(option-1))
                competenciasDisponiveis.remove(option-1)
            }
            else if(option == 0){
                break
            }
            else{
                continue
            }
            print("\033[H\033[2J")
            System.out.flush()
            competenciasDisponiveis.eachWithIndex {competencia,indexCompetencia ->print((indexCompetencia+1).toString()+": "+competencia.getNome()+"\n")}
        }

        return competenciasAtribuidas
    }

    static int getOption(){
        return entradaDeDadosProtegida()
    }

    static int entradaDeDadosProtegida(){
        Scanner scanner = new Scanner(System.in)
        try{
            Integer opcaoRecebida= scanner.nextLine().toInteger()
            return opcaoRecebida
        }
        catch(NumberFormatException ex) {
            println("Erro: Por favor, digite um número válido.")

            return -1
        }
    }
}
