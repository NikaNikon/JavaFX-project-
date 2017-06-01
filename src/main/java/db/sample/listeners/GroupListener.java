package db.sample.listeners;

import db.sample.groups.GroupsController;

import java.util.Date;

public class GroupListener implements IGroupListener {

    @Override
    public void onAdded() {
        GroupsController.setMsgLabel("Группа добавлена " + new Date().toString());
    }

    @Override
    public void onDeleted() {
        String msg = "Группа удалена " + new Date().toString();
        GroupsController.setMsgLabel(msg);
    }

    @Override
    public void onChanged() {
        GroupsController.setMsgLabel("Расписание изменено " + new Date().toString());
    }
}
