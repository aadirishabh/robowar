package org.task.dto;

import lombok.Getter;
import lombok.Setter;
import org.task.entity.Survivor;

import java.util.List;

@Getter
@Setter
public class SurvivorDataDto {

    private double infectedSurvivorsPercentage;

    private double nonInfectedSurvivorsPercentage;

    private List<Survivor> infectedSurvivors;

    private List<Survivor> nonInfectedSurvivors;

    private List<RobotDto> robotDtoList;
}
