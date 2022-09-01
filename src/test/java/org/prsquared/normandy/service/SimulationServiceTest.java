package org.prsquared.normandy.service;

import org.junit.jupiter.api.Test;
import org.prsquared.normandy.model.Table;
import org.prsquared.normandy.model.TableItem;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class SimulationServiceTest {


    SimulationService simulationService;
    @Test
    void TestSimulate() throws InterruptedException {
        simulationService = new SimulationService();
        int winCount = 0;
        for(int i = 0 ; i < 100; i ++) {
            Table table = simulationService.simulate();
            Comparator<TableItem> compareByPoints = Comparator.comparing(TableItem::getPoints)
                    .thenComparingInt(TableItem::getGoalDifference)
                    .thenComparingInt(TableItem::getGoalsScored).reversed();

            List<TableItem> tableItemList =  table.getTableItemList().stream()
                    .sorted(compareByPoints)
                    .collect(Collectors.toList());
            if("Leicester City".equals(tableItemList.get(0).getTeam().getName())) {
                winCount++;
            }
        }
        System.out.println("Won "+winCount+ " times in 100 attempts");
    }
}