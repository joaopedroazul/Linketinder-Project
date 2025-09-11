package test.groovy
import Classes.Empresa
import spock.lang.Specification


class EmpresaTest extends Specification{

//    Crie testes unitários para a etapa de cadastro de novo usuário (candidato e empresa).
//    É o teste para a inserção de novo elemento nos arrays.

    def "Criando uma empresa no sistema" (){
        given:
        Empresa e = new Empresa()
        ArrayList<Empresa> listEmpresas = new ArrayList<>()
        when:
        listEmpresas = Empresa.viewCreateEmpresaWithoutTerminal(e,listEmpresas)

        then:
        listEmpresas.size() == old(listEmpresas.size()+1)
    }
}
