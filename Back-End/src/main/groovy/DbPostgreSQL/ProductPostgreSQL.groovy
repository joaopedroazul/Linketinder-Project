package DbPostgreSQL

import Interfaces.DataBase

class ProductPostgreSQL extends FactoryDB{
    @Override
    protected DataBase criandoModuloDB() {
        return PostgreSQL.getDB()
    }
}
