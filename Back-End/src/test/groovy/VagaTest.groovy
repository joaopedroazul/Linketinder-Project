import Classes.Vaga
import DAO.VagaDAO
import spock.lang.Specification

class VagaTest extends Specification{
    Vaga vagaTeste = new Vaga()

    void "Teste de cadastro VagaDAO"(){
        given:
        vagaTeste.nome               =   "Front end em React, JS e TS"
        vagaTeste.empresa_id         =   1
        vagaTeste.estado_id          =   1
        vagaTeste.cidade_id          =   1
        vagaTeste.descricao          =   "Testando CRUD de Vaga"

        boolean resultadoEsperado       =   true

        when:
        boolean resultadoObtido = VagaDAO.createVaga(vagaTeste)

        then:
        resultadoEsperado == resultadoObtido

    }



    void "Teste de listagem das vagas no banco de dados "(){
        given:
        List<Vaga> VagasArmazenadosNoPostgreSQL = VagaDAO.listarVaga()


        when:
        Integer resultadoObtido = VagasArmazenadosNoPostgreSQL.size()

        then:
        resultadoObtido > 0

    }

    void "Teste de listagem do ultimo vaga criada  "(){
        given:
        Vaga VagaCriadoRecentemente = VagaDAO.listarVaga().last()


        when:
        Integer resultadoObtido = VagaCriadoRecentemente.id

        then:
        resultadoObtido > 0

    }

    void "Teste de listagem de vaga especifico  "(){

        given:
        Vaga VagaCriadoRecentemente = VagaDAO.listarVaga().last()
        Vaga VagaPesquisada = VagaDAO.listarVaga(VagaCriadoRecentemente.id)

        when:
        String resultadoObtido = VagaPesquisada.id
        String resultadoEsperado = VagaCriadoRecentemente.id

        then:

        resultadoEsperado == resultadoObtido


    }

    void "Teste de atualizar de vaga especifico  "(){

        given:
        Vaga VagaCriadoRecentemente = VagaDAO.listarVaga().last()
        int id_vaga = VagaCriadoRecentemente.id

        when:
        VagaCriadoRecentemente.nome = "Front end em Angular, JS e TS"
        boolean resultadoObtido = VagaDAO.updateVaga(VagaCriadoRecentemente,id_vaga)
        boolean resultadoEsperado = true


        then:

        resultadoEsperado == resultadoObtido

    }

    void "Teste de excluir de vaga especifico  "(){

        given:
        Vaga VagaCriadoRecentemente = VagaDAO.listarVaga().last()
        int id_vaga = VagaCriadoRecentemente.id

        when:
        boolean resultadoObtido = VagaDAO.removerVaga(id_vaga)
        boolean resultadoEsperado = true

        then:

        resultadoEsperado == resultadoObtido

    }
}
