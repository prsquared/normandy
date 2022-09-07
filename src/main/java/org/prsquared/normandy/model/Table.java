package org.prsquared.normandy.model;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class Table {
    List<TableItem> tableItemList = new ArrayList<>();

    public Table(List<TableItem> tableItemList) {
        this.tableItemList = tableItemList;
    }

    public List<TableItem> getTableItemList() {
        return tableItemList;
    }

    public TableItem getTableItemByTeam(Team team) {
        for(TableItem item: tableItemList) {
            if(team.getName().equals(item.getTeam().getName())) {
                return item;
            }
        }
        return null;
    }

}
