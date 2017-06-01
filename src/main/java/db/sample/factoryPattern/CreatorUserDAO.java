package db.sample.factoryPattern;

import db.sample.IDAO.IUserDAO;

public abstract class CreatorUserDAO {
    public abstract IUserDAO factoryUserDAO();
}
