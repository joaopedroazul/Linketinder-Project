package test.groovy
import Classes.Candidato
import spock.lang.Specification

import java.nio.file.Path

class CandidatoTest extends  Specification{


//    Crie testes unitários para a etapa de cadastro de novo usuário (candidato e empresa).
//    É o teste para a inserção de novo elemento nos arrays.

    def "Criando Candidato no sistema" (){
        given:
        Candidato c = new Candidato()
        ArrayList<Candidato> listCandidatos = new ArrayList<>()
        when:
        listCandidatos = Candidato.viewCreateCanditadoWithoutTerminal(c,listCandidatos)

        then:
        listCandidatos.size() == old(listCandidatos.size()+1)
    }
}
