package org.prsquared.normandy.model;

import java.util.ArrayList;
import java.util.List;

public class Table {
    List<TableItem> tableItemList = new ArrayList<>();

    public Table(List<TableItem> tableItemList) {
        this.tableItemList = tableItemList;
    }

}
