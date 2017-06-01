package db.sample.groups;

import db.sample.DAO.mongoDbDAO.MongoDbGroupDAO;
import db.sample.IDAO.IGroupDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

public class GroupsController {
    @FXML
    TextField txtFieldStyle;

    private Group tmp;

    @FXML
    Pane groups;
    @FXML
    Pane newGroup;
    @FXML
    Pane editGroupPane;
    @FXML
    Pane deletePane;

    @FXML
    TextField setStyle;
    @FXML
    TextField setLevel;
    @FXML
    TextField setCoachName;
    @FXML
    TextField setCoachLastName;
    @FXML
    TextField setSchedule;


    @FXML
    TextField styleField;
    @FXML
    TextField coachNameField;
    @FXML
    TextField scheduleField;
    @FXML
    TextField coachLastNameField;
    @FXML
    TextField levelField;
    @FXML
    static TextArea msgField;

    @FXML
    ListView<String> groupsList = new ListView<String>();

    @FXML
    MenuItem optionEdit;
    @FXML
    MenuItem optionDelete;

    @FXML
    ChoiceBox<String> searchOptions;
    @FXML
    ChoiceBox<String> sortOptions;

    ObservableList<Group> currentGroupsData = FXCollections.observableArrayList();

    public void fillList(String sortValue) {
        ObservableList<String> groupsDataString = FXCollections.observableArrayList();
        ObservableList<Group> groupsData = FXCollections.observableArrayList();
        /*IGroupDAO dao = MySQLGroupDAO.getInstance();
    Group groups[] = dao.displayGroups();*/
        IGroupDAO mongoDao = MongoDbGroupDAO.getInstance();
        if (sortValue == null) {
            Group groups[] = mongoDao.displayGroups();
            for (Group g : groups) {
                groupsData.add(g);
                groupsDataString.add(g.style + "       " + g.level + " level" + "       " + g.coachName + " " + g.coachLastName
                        + "       " + g.schedule);
            }
            currentGroupsData.clear();
            currentGroupsData.addAll(groupsData);
            groupsList.setItems(groupsDataString);
        } else {
            Group groups[] = mongoDao.sortedGroups(sortValue);
            for (Group g : groups) {
                groupsData.add(g);
                groupsDataString.add(g.style + "       " + g.level + " level" + "       " + g.coachName + " " + g.coachLastName
                        + "       " + g.schedule);
            }
            currentGroupsData.clear();
            currentGroupsData.addAll(groupsData);
            groupsList.setItems(groupsDataString);
        }
    }

    public void setCheckbox()
    {
        ObservableList<String> searchOptionsList = FXCollections.observableArrayList();
        searchOptionsList.addAll("по стилю", "по уровню", "по фамилии тренера");
        searchOptions.setItems(searchOptionsList);
        searchOptions.getSelectionModel().selectFirst();

        ObservableList<String> sortOptionsList = FXCollections.observableArrayList();
        sortOptionsList.addAll("по уровню", "по стилю", "по фамилии тренера");
        sortOptions.setItems(sortOptionsList);
        sortOptions.getSelectionModel().selectFirst();
    }

    public static void setMsgLabel(String text){
        //msgField.setText(text);
        System.out.println(text);
        /*try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(text);
        msgField.setVisible(false);*/
    }

    public void onSearchBtnClicked() {
        String searchValue = txtFieldStyle.getText();
        if (searchValue.length() == 0){
            fillList(null);
            return;
        }
        ObservableList<Group> searchGroupsData = FXCollections.observableArrayList();
        ObservableList<String> searchGroupsDataString = FXCollections.observableArrayList();
        String searchOption = "";
        switch(searchOptions.getSelectionModel().getSelectedIndex())
        {
            case 0: {
                searchOption = "style";
                break;}
            case 1: {
                searchOption = "level";
                break;}
            case 2: {
                searchOption = "last_name";
                break;}
        }
        try {
            IGroupDAO dao1 = MongoDbGroupDAO.getInstance();
            Group groups[] = dao1.findGroups(searchOption, searchValue); //пофиксить поиск по фамилии и стилю
            /*IGroupDAO dao = MySQLGroupDAO.getInstance();
            Group groups[] = dao.findGroups(searchOption, searchValue);*/
            for(Group g : groups)
            {
                searchGroupsData.add(g);
                searchGroupsDataString.add(g.style + "       " + g.level+" level" + "       " + g.coachName + " " + g.coachLastName
                        + "       " + g.schedule);
            }
            currentGroupsData.clear();
            currentGroupsData.addAll(searchGroupsData);
            groupsList.setItems(searchGroupsDataString);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onAddGroupBtnClicked() {
        groups.setVisible(false);
        newGroup.setVisible(true);
    }

    public void onOKClicked() {
        if (setStyle.getText().length() >= 1 && setLevel.getText().length() >= 1 && setCoachName.getText().length() >= 1
                && setCoachLastName.getText().length() >= 1 && setSchedule.getText().length() >= 1) {
            try {
//                IGroupDAO dao = MySQLGroupDAO.getInstance();
                IGroupDAO dao = MongoDbGroupDAO.getInstance();
                Group temp = new Group(setStyle.getText(), Double.parseDouble(setLevel.getText()), setCoachName.getText(),
                        setCoachLastName.getText(), setSchedule.getText());
                dao.addGroup(setStyle.getText(), Double.parseDouble(setLevel.getText()), setCoachName.getText(),
                        setCoachLastName.getText(), setSchedule.getText());
//                temp.setId(dao.getGroupId(temp));
                String id = dao.getStringId(temp);
                if(id != null)
                temp.setStringId(dao.getStringId(temp));
                currentGroupsData.add(new Group(temp));
                newGroup.setVisible(false);
                fillList(null);
                groups.setVisible(true);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

    }

    public void onEditClicked() {
        int index = groupsList.getSelectionModel().getSelectedIndex();
        Group temp = currentGroupsData.get(index);
        styleField.setText(temp.getStyle());
        levelField.setText(temp.getLevel().toString());
        coachNameField.setText(temp.getCoachName());
        coachLastNameField.setText(temp.getCoachLastName());
        scheduleField.setText(temp.getSchedule());
        groups.setVisible(false);
        tmp = temp;
        editGroupPane.setVisible(true); // здесь открывать копию newGroup
        //по нажатию ОК менять поля в tmp, потом вызывать апдейт
    }

    public void onOKEditClicked()
    {
        if(scheduleField.getText().length() >= 4)
        {
            int index = groupsList.getSelectionModel().getSelectedIndex();
            currentGroupsData.get(index).setSchedule(scheduleField.getText());
            tmp.setSchedule(scheduleField.getText());
            try {
//                IGroupDAO dao = MySQLGroupDAO.getInstance();
                IGroupDAO dao = MongoDbGroupDAO.getInstance();
                dao.updateSchedule(tmp);
                editGroupPane.setVisible(false);
                fillList(null);
                groups.setVisible(true);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void onDeleteClicked(){
        deletePane.setBackground(new Background(new BackgroundFill(Color.web("#8ED2A4"), CornerRadii.EMPTY, Insets.EMPTY)));
        deletePane.setVisible(true);
    }
    public void onDeleteBtnClicked(){
        deletePane.setVisible(false);
        //System.out.println("shouldn't be visible");
        int index = groupsList.getSelectionModel().getSelectedIndex();
//        IGroupDAO dao = MySQLGroupDAO.getInstance();
        IGroupDAO dao = MongoDbGroupDAO.getInstance();
        try {
            dao.deleteGroup(currentGroupsData.get(index));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        currentGroupsData.remove(index);
        fillList(null);

    }
    public void onCancelBtnClicked(){
        deletePane.setVisible(false);
    }

    public void sort() {
        switch(sortOptions.getSelectionModel().getSelectedIndex()){
            case 0: fillList("level");
                break;
            case 1: fillList("style");
                break;
            case 2: fillList("coach_last_name");
                break;
        }
    }
}
