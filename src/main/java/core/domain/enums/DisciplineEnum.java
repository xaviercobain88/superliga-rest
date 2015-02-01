package core.domain.enums;

/**
 * Created by xavier on 1/24/15.
 */
public enum DisciplineEnum {
    FOOTBALL(true), CHESS(false);
    boolean isForTeam;


    DisciplineEnum(boolean isForTeam) {
        this.isForTeam = isForTeam;
    }

    public boolean isForTeam() {
        return isForTeam;
    }
}
