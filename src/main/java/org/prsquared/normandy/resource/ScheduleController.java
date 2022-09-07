package org.prsquared.normandy.resource;

import org.prsquared.normandy.model.Schedule;
import org.prsquared.normandy.model.Team;
import org.prsquared.normandy.service.SimulationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("schedule")
public class ScheduleController {

    @Autowired
    SimulationService simulationService;
    @GetMapping
    public Schedule createSchedule(){

        List<Team> teams = simulationService.populateTeams();
        return simulationService.createSchedule(teams);
    }
}
