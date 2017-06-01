package db.sample.IDAO;

import db.sample.groups.Group;

import java.sql.DriverManager;
import java.sql.SQLException;

public interface IGroupDAO {

    Group[] displayGroups();
    Group [] findGroups(String searchOption, String searchOptionValue);
    boolean addGroup(String style, Double level, String coachName, String coachLastName, String schedule) throws ClassNotFoundException;
    boolean updateSchedule(Group g) throws ClassNotFoundException, SQLException;
    int getGroupId(Group g);
    boolean deleteGroup (Group g) throws SQLException;
    String getStringId(Group g);
    Group[] sortedGroups(String sort);

}
