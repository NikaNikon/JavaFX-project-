package db.sample.hibernateEntities;

import javax.persistence.*;

@Entity
@Table(name = "groups", schema = "ua21db")
@IdClass(GroupsEntityPK.class)
public class GroupsEntity {
    private int groupId;
    private int level;
    private int coachId;
    private String style;
    private String schedule;

    @Id
    @Column(name = "group_id")
    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    @Basic
    @Column(name = "level")
    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    @Id
    @Column(name = "coach_id")
    public int getCoachId() {
        return coachId;
    }

    public void setCoachId(int coachId) {
        this.coachId = coachId;
    }

    @Basic
    @Column(name = "style")
    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    @Basic
    @Column(name = "schedule")
    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GroupsEntity that = (GroupsEntity) o;

        if (groupId != that.groupId) return false;
        if (level != that.level) return false;
        if (coachId != that.coachId) return false;
        if (style != null ? !style.equals(that.style) : that.style != null) return false;
        if (schedule != null ? !schedule.equals(that.schedule) : that.schedule != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = groupId;
        result = 31 * result + level;
        result = 31 * result + coachId;
        result = 31 * result + (style != null ? style.hashCode() : 0);
        result = 31 * result + (schedule != null ? schedule.hashCode() : 0);
        return result;
    }
}
