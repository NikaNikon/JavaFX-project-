package db.sample.factoryPattern;

import db.sample.DAO.mySQLHibernateDAO.MySQLHibernateUserDAO;
import db.sample.IDAO.IUserDAO;

public class CreatorMySQLHibernateUserDAO extends CreatorUserDAO {

    @Override
    public IUserDAO factoryUserDAO() {
        return MySQLHibernateUserDAO.getInstance();
    }
}
