package core.domain.model;

import core.domain.enums.DisciplineEnum;
import core.domain.enums.PlayingModeEnum;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by xavier on 1/24/15.
 */
public class DisciplineSettings {
    public static HashMap<DisciplineEnum, ArrayList<PlayingModeEnum>> settings;

    static {
        settings = new HashMap<>();
        ArrayList<PlayingModeEnum> modes;

        //FOOTBALL
        modes = new ArrayList<>();
        modes.add(PlayingModeEnum.SINGLE_ELIMINATION);
        modes.add(PlayingModeEnum.ROUND_ROBIN);
        modes.add(PlayingModeEnum.GROUPS);
        settings.put(DisciplineEnum.FOOTBALL, modes);
        //CHESS
        modes = new ArrayList<>();
        modes.add(PlayingModeEnum.ELO);
        settings.put(DisciplineEnum.CHESS, modes);
    }


}
