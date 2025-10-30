import Classes.Candidato
import DAO.CandidatoDAO
import spock.lang.Specification

import java.sql.Date

class CandidatoTest extends Specification {

    Candidato candidatoTeste = new Candidato()

    void "Teste de cadastro candidatoDAO"(){
        given:
        candidatoTeste.nome             =   "Carlos"
        candidatoTeste.sobrenome        =   "Souza"
        candidatoTeste.email            =   "carlos@mail.com"
        candidatoTeste.cpf              =   "123.456.789-00"
        candidatoTeste.país             =   "BRASIL"
        candidatoTeste.dataNascimento   =   Date.valueOf("2025-10-10");
        candidatoTeste.cep              =   "78956-846"
        candidatoTeste.descricao        =   "Testando cadastro candidato"
        candidatoTeste.senha            =   "senha123"

        boolean resultadoEsperado       =   true

        when:
        boolean resultadoObtido = CandidatoDAO.criarCandidato(candidatoTeste)

        then:
        resultadoEsperado == resultadoObtido

    }

    void "Teste de listagem dos candidatos "(){
        given:
        List<Candidato> CandidatosArmazenadosNoPostgreSQL = CandidatoDAO.listarCandidato()


        when:
        Integer resultadoObtido = CandidatosArmazenadosNoPostgreSQL.size()

        then:
        resultadoObtido > 0

    }

    void "Teste de listagem do ultimo candidato criado  "(){
        given:
        Candidato CandidatoCriadoRecentemente = CandidatoDAO.listarUltimoCandidato()


        when:
        Integer resultadoObtido = CandidatoCriadoRecentemente.id

        then:
        resultadoObtido > 0

    }

    void "Teste de listagem de candidato especifico  "(){

        given:
        Candidato CandidatoCriadoRecentemente = CandidatoDAO.listarUltimoCandidato()
        List<Candidato> CandidatosArmazenadosNoPostgreSQL = CandidatoDAO.listarCandidato()
        Candidato CandidatoPesquisado = CandidatoDAO.listarCandidato( CandidatosArmazenadosNoPostgreSQL.last().id)

        when:
        Integer resultadoObtido = CandidatoPesquisado.id
        Integer resultadoEsperado = CandidatoCriadoRecentemente.id

        then:

        resultadoEsperado == resultadoObtido


    }

    void "Teste de atualizar de candidato especifico  "(){

        given:
        Candidato CandidatoCriadoRecentemente = CandidatoDAO.listarUltimoCandidato()
        int id_candidato = CandidatoCriadoRecentemente.id

        when:
        CandidatoCriadoRecentemente.senha = CandidatoDAO.listarSenhaCandidato(id_candidato)
        CandidatoCriadoRecentemente.nome = "Nome alterado"
        boolean resultadoObtido = CandidatoDAO.atualizarCandidato(CandidatoCriadoRecentemente,id_candidato)
        boolean resultadoEsperado = true


        then:

        resultadoEsperado == resultadoObtido

    }

    void "Teste de excluir de candidato especifico  "(){

        given:
        Candidato CandidatoCriadoRecentemente = CandidatoDAO.listarUltimoCandidato()
        int id_candidato = CandidatoCriadoRecentemente.id

        when:
        boolean resultadoObtido = CandidatoDAO.removerCandidato(id_candidato)
        boolean resultadoEsperado = true

        then:

        resultadoEsperado == resultadoObtido

    }




}