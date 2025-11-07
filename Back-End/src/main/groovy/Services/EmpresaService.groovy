package Services

import DAO.CandidatoDAO
import DAO.EmpresaDAO
import DAO.VagaDAO
import Models.Candidato
import Models.Empresa
import Models.Vaga
import Views.EmpresaView
import Views.MenuView
import Views.VagaView

class EmpresaService {

    static  Scanner scanner = new Scanner(System.in)

    static void criarEmpresa(){
        Empresa novaEmpresa = new Empresa();
        EmpresaView.viewCreateEmpresa(novaEmpresa);
        EmpresaDAO.createEmpresa(novaEmpresa);

    }


    static Empresa verificarEmpresa(String email, String senha){
        try{
            Empresa empressCadastrado =  EmpresaDAO.Login(email,senha)

            if(empressCadastrado.getId() > 0)
                return empressCadastrado

        }catch(NullPointerException exception){
            return new Empresa(
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    0,
            );
        }
    }

    static void loginEmpresa(String email, String senha){
        Empresa empresaLogada = verificarEmpresa(email,senha)
            if(empresaLogada.getId() > 0){
                int condicaoDeEntrada = 1
                while(condicaoDeEntrada >= 1 || condicaoDeEntrada <= 2) {
                    MenuView.MensagensDeMenu("Menu Empresa")
                    int opcaoInterna = scanner.nextLine().toInteger()
                    if (opcaoInterna == 1) {
                        CandidatoDAO.listarCandidato().each { Candidato c ->
                            println("________________________________________")
                            println(" Candidato " + (c.id.toString()))
                            println(c.descricao)
                            println("")
                        }
                    } else if (opcaoInterna == 2) {
                        Vaga novaVaga = new Vaga()
                        novaVaga = VagaView.viewCreateVaga(empresaLogada.getId())
                        VagaDAO.createVaga(novaVaga)
                    }
                    else{break}
                    condicaoDeEntrada = opcaoInterna
                }
            }else{
                println("Empresa não cadastrada, crie sua conta")
            }
    }


}
