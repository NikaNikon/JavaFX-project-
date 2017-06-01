package db.sample.factoryPattern;

import db.sample.IDAO.IUserDAO;
import db.sample.DAO.mongoDbDAO.MongoDbUserDAO;

public class CreatorMongoDBUserDAO extends CreatorUserDAO {
    public IUserDAO factoryUserDAO(){
        return MongoDbUserDAO.getInstance();
    }
}
