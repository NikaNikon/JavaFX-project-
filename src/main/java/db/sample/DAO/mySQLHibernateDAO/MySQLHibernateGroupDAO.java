package db.sample.DAO.mySQLHibernateDAO;

import db.sample.IDAO.IGroupDAO;
import db.sample.groups.Group;

import java.sql.SQLException;

public class MySQLHibernateGroupDAO implements IGroupDAO {

    public Group[] displayGroups() {
        return new Group[0];
    }


    public Group[] findGroups(String searchOption, String searchOptionValue) {
        return new Group[0];
    }


    public boolean addGroup(String style, Double level, String coachName, String coachLastName, String schedule) throws ClassNotFoundException {
        return false;
    }


    public boolean updateSchedule(Group g) throws ClassNotFoundException, SQLException {
        return false;
    }


    public int getGroupId(Group g) {
        return 0;
    }


    public boolean deleteGroup(Group g) throws SQLException {
        return false;
    }


    public String getStringId(Group g) {
        return null;
    }


    public Group[] sortedGroups(String sort) {
        return new Group[0];
    }
}
