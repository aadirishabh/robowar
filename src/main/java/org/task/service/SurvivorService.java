package org.task.service;

import org.task.dto.RobotDto;
import org.task.dto.SurvivorDataDto;
import org.task.entity.Survivor;

import java.util.List;

public interface SurvivorService {
    List<RobotDto> getRobotData();

    Survivor addSurvivor(Survivor survivor);

    Survivor updateLocation(Survivor survivor);

    boolean markContamination(int id);

    SurvivorDataDto getSurvivorsData();

    Survivor getSurvivorById(int id);
}
