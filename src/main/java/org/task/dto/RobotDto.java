package org.task.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class RobotDto {

    private String model;

    private String serialNumber;

    private Date manufacturedDate;

    private String category;
}
