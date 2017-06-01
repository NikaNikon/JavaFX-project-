package db.sample.factoryPattern;

import db.sample.IDAO.IUserDAO;
import db.sample.DAO.mySQLDAO.MySQLUserDAO;

public class CreatorMySQLUserDAO extends CreatorUserDAO {
    public IUserDAO factoryUserDAO(){
        return MySQLUserDAO.getInstance();
    }
}
