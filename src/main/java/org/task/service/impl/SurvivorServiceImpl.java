package org.task.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.task.dto.RobotDto;
import org.task.dto.SurvivorDataDto;
import org.task.entity.LastLocation;
import org.task.entity.Survivor;
import org.task.repository.LastLocationRepo;
import org.task.repository.ResourceRepo;
import org.task.repository.SurvivorRepo;
import org.task.service.RobotService;
import org.task.service.SurvivorService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SurvivorServiceImpl implements SurvivorService {

    @Autowired
    private RobotService robotService;

    @Autowired
    private SurvivorRepo survivorRepo;

    @Autowired
    private ResourceRepo resourceRepo;

    @Autowired
    private LastLocationRepo lastLocationRepo;

    @Override
    public List<RobotDto> getRobotData() {
        return robotService.getRobotData();
    }

    @Override
    public Survivor addSurvivor(Survivor survivor) {
        if (survivor.getResourceList() != null) {
            survivor.setResourceList(resourceRepo.saveAll(survivor.getResourceList()));
        }
        if (survivor.getLastLocation() != null) {
            survivor.setLastLocation(lastLocationRepo.save(survivor.getLastLocation()));
        }
        return survivorRepo.save(survivor);
    }

    @Override
    public Survivor updateLocation(Survivor survivor) {
        Optional<Survivor> survivorRecord = survivorRepo.findById(survivor.getSurvivorId());
        LastLocation lastLocation = survivor.getLastLocation();

        if (survivorRecord.isPresent()) {
            Survivor survivorFromDb = survivorRecord.get();
            lastLocation.setId(survivorFromDb.getLastLocation().getId());
            lastLocation = lastLocationRepo.save(lastLocation);
            survivorFromDb.setLastLocation(lastLocation);
            return survivorRepo.save(survivor);
        }
        return new Survivor();
    }

    @Override
    public boolean markContamination(int id) {
        Optional<Survivor> survivorRecord = survivorRepo.findById(id);
        if (survivorRecord.isPresent()) {
            Survivor survivor = survivorRecord.get();
            survivor.setInfectionCount(survivor.getInfectionCount() + 1);

            return survivorRepo.save(survivor).isInfected();
        }
        return false;
    }

    @Override
    public SurvivorDataDto getSurvivorsData() {

        SurvivorDataDto survivorData = new SurvivorDataDto();
        List<Survivor> survivors = survivorRepo.findAll();
        List<Survivor> infectedSurvivors = new ArrayList<>();
        List<Survivor> nonInfectedSurvivors = new ArrayList<>();

        survivors.forEach(survivor -> {
            if (survivor.isInfected()) {
                infectedSurvivors.add(survivor);
            } else {
                nonInfectedSurvivors.add(survivor);
            }
        });

        double infectedCount = infectedSurvivors.size();
        double nonInfectedCount = infectedSurvivors.size();

        survivorData.setInfectedSurvivors(infectedSurvivors);
        survivorData.setNonInfectedSurvivors(nonInfectedSurvivors);
        survivorData.setInfectedSurvivorsPercentage((infectedCount * 100) / (infectedCount + nonInfectedCount));
        survivorData.setNonInfectedSurvivorsPercentage((nonInfectedCount * 100) / (infectedCount + nonInfectedCount));
        survivorData.setRobotDtoList(getRobotData());

        return survivorData;
    }

    @Override
    public Survivor getSurvivorById(int id) {
        return survivorRepo.findById(id).get();
    }
}
