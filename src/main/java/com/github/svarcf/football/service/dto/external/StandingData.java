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

    public class TableData {
        private int position;
        private TeamData team;
        private int won;
        private int draw;
        private int lost;
        private int points;


        public int getPosition() {
            return position;
        }

        public void setPosition(int position) {
            this.position = position;
        }

        public TeamData getTeam() {
            return team;
        }

        public void setTeam(TeamData team) {
            this.team = team;
        }

        public int getWon() {
            return won;
        }

        public void setWon(int won) {
            this.won = won;
        }

        public int getDraw() {
            return draw;
        }

        public void setDraw(int draw) {
            this.draw = draw;
        }

        public int getLost() {
            return lost;
        }

        public void setLost(int lost) {
            this.lost = lost;
        }

        public int getPoints() {
            return points;
        }

        public void setPoints(int points) {
            this.points = points;
        }
    }
}
