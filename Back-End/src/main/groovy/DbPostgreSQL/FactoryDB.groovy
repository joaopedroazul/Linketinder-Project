package DbPostgreSQL

import Interfaces.DataBase

abstract class FactoryDB {

    DataBase iniciandoProducao(){
        DataBase bancoEscolhido = criandoModuloDB()
        return bancoEscolhido
    }

    protected abstract DataBase criandoModuloDB()
}
