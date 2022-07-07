package org.task.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.task.dto.RobotDto;
import org.task.service.RobotService;

import java.util.Arrays;
import java.util.List;

@Service
public class RobotServiceImpl implements RobotService {

    @Value("${robot.url}")
    private String robotUrl;

    @Override
    public List<RobotDto> getRobotData() {

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<String>(headers);

        ResponseEntity<Object> robotResponse = restTemplate.exchange(robotUrl, HttpMethod.GET, entity, Object.class);
        List<RobotDto> robotList = (List<RobotDto>) robotResponse.getBody();
        if (robotList != null) {
            return robotList;
        } else {
            return null;
        }

    }
}
