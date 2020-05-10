package com.github.svarcf.football.service.dto.external;

public class StandingData {

    private String stage;
    private TableData[] table;

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public TableData[] getTable() {
        return table;
    }

    public void setTable(TableData[] table) {
        this.table = table;
    }
}
