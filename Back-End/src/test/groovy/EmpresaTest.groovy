import Classes.Empresa
import DAO.EmpresaDAO
import spock.lang.Specification

import java.sql.Date

class EmpresaTest extends Specification {
    Empresa empresaTeste = new Empresa()

    void "Teste de cadastro empresaDAO"(){
        given:
        empresaTeste.nome             =   "SKT Telecom Brasil"
        empresaTeste.email            =   "SKT_T1BR@mail.com"
        empresaTeste.cnpj             =   "12.345.678/0001-90"
        empresaTeste.pais             =   "BRASIL"
        empresaTeste.cep              =   "79432-846"
        empresaTeste.descricao        =   "Testando cadastro empresa"
        empresaTeste.senha            =   "senha123"

        boolean resultadoEsperado       =   true

        when:
        boolean resultadoObtido = EmpresaDAO.createEmpresa(empresaTeste)

        then:
        resultadoEsperado == resultadoObtido

    }



    void "Teste de listagem dos empresas "(){
        given:
        List<Empresa> EmpresasArmazenadosNoPostgreSQL = EmpresaDAO.listarEmpresa()


        when:
        Integer resultadoObtido = EmpresasArmazenadosNoPostgreSQL.size()

        then:
        resultadoObtido > 0

    }

    void "Teste de listagem do ultimo empresa criado  "(){
        given:
        Empresa EmpresaCriadoRecentemente = EmpresaDAO.listarEmpresa().last()


        when:
        Integer resultadoObtido = EmpresaCriadoRecentemente.id

        then:
        resultadoObtido > 0

    }

    void "Teste de listagem de empresa especifico  "(){

        given:
        Empresa EmpresaCriadoRecentemente = EmpresaDAO.listarEmpresa().last()
        List<Empresa> EmpresasArmazenadasNoPostgreSQL = EmpresaDAO.listarEmpresa()
        Empresa EmpresaPesquisada = EmpresaDAO.listarEmpresa( EmpresasArmazenadasNoPostgreSQL.last().id)

        when:
        Integer resultadoObtido = EmpresaPesquisada.id
        Integer resultadoEsperado = EmpresaCriadoRecentemente.id

        then:

        resultadoEsperado == resultadoObtido


    }

    void "Teste de atualizar de empresa especifico  "(){

        given:
        Empresa EmpresaCriadoRecentemente = EmpresaDAO.listarEmpresa().last()
        int id_empresa = EmpresaCriadoRecentemente.id

        when:
        EmpresaCriadoRecentemente.senha = EmpresaDAO.listarSenhaEmpresa(id_empresa)
        EmpresaCriadoRecentemente.nome = "Nome alterado"
        boolean resultadoObtido = EmpresaDAO.updateEmpresa(EmpresaCriadoRecentemente,id_empresa)
        boolean resultadoEsperado = true


        then:

        resultadoEsperado == resultadoObtido

    }

    void "Teste de excluir de empresa especifico  "(){

        given:
        Empresa EmpresaCriadoRecentemente = EmpresaDAO.listarEmpresa().last()
        int id_empresa = EmpresaCriadoRecentemente.id

        when:
        boolean resultadoObtido = EmpresaDAO.removerEmpresa(id_empresa)
        boolean resultadoEsperado = true

        then:

        resultadoEsperado == resultadoObtido

    }

}
