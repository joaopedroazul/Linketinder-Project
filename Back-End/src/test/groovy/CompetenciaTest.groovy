import Classes.Competencia
import DAO.CompetenciaDAO
import spock.lang.Specification

class CompetenciaTest extends Specification{

    Competencia competenciaTeste = new Competencia()

    void "Teste de cadastro CompetenciaDAO"(){
        given:
        competenciaTeste.nome           =   "RUST"

        boolean resultadoEsperado       =   true

        when:
        boolean resultadoObtido = CompetenciaDAO.createCompetencia(competenciaTeste)

        then:
        resultadoEsperado == resultadoObtido

    }



    void "Teste de listagem das competencias no banco de dados "(){
        given:
        List<Competencia> CompetenciasArmazenadosNoPostgreSQL = CompetenciaDAO.listarCompetencia()


        when:
        Integer resultadoObtido = CompetenciasArmazenadosNoPostgreSQL.size()

        then:
        resultadoObtido > 0

    }

    void "Teste de listagem do ultimo competencia criada  "(){
        given:
        Competencia CompetenciaCriadoRecentemente = CompetenciaDAO.listarCompetencia().last()


        when:
        Integer resultadoObtido = CompetenciaCriadoRecentemente.id

        then:
        resultadoObtido > 0

    }

    void "Teste de listagem de competencia especifico  "(){

        given:
        Competencia CompetenciaCriadoRecentemente = CompetenciaDAO.listarCompetencia().last()
        Competencia CompetenciaPesquisada = CompetenciaDAO.listarCompetencia( CompetenciaCriadoRecentemente.id)

        when:
        String resultadoObtido = CompetenciaPesquisada.id
        String resultadoEsperado = CompetenciaCriadoRecentemente.id

        then:

        resultadoEsperado == resultadoObtido


    }

    void "Teste de atualizar de competencia especifico  "(){

        given:
        Competencia CompetenciaCriadoRecentemente = CompetenciaDAO.listarCompetencia().last()
        int id_competencia = CompetenciaCriadoRecentemente.id

        when:
        CompetenciaCriadoRecentemente.nome = "GO"
        boolean resultadoObtido = CompetenciaDAO.updateCompetencia(CompetenciaCriadoRecentemente,id_competencia)
        boolean resultadoEsperado = true


        then:

        resultadoEsperado == resultadoObtido

    }

    void "Teste de excluir de competencia especifico  "(){

        given:
        Competencia CompetenciaCriadoRecentemente = CompetenciaDAO.listarCompetencia().last()
        int id_competencia = CompetenciaCriadoRecentemente.id

        when:
        boolean resultadoObtido = CompetenciaDAO.removerCompetencia(id_competencia)
        boolean resultadoEsperado = true

        then:

        resultadoEsperado == resultadoObtido

    }
}
