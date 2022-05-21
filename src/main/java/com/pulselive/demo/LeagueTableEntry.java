package com.pulselive.demo;

import static com.pulselive.demo.Constants.ONE;

public class LeagueTableEntry {
    private String teamName;
    private int played;
    private int won;
    private int drawn;
    private int lost;
    private int goalsFor;
    private int goalsAgainst;
    private int points;


    public LeagueTableEntry(final String teamName) {
        this.teamName = teamName;
    }

    public LeagueTableEntry(final String teamName, final int played, final int won, final int drawn, final int lost,
                            final int goalsFor, final int goalsAgainst, final int points) {
        this.teamName = teamName;
        this.played = played;
        this.won = won;
        this.drawn = drawn;
        this.lost = lost;
        this.goalsFor = goalsFor;
        this.goalsAgainst = goalsAgainst;
        this.points = points;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public int getPlayed() {
        return played;
    }

    public void setPlayed(int played) {
        this.played = played;
    }

    public int getWon() {
        return won;
    }

    public void setWon(int won) {
        this.won = won;
    }

    public int getDrawn() {
        return drawn;
    }

    public void setDrawn(int drawn) {
        this.drawn = drawn;
    }

    public int getLost() {
        return lost;
    }

    public void setLost(int lost) {
        this.lost = lost;
    }

    public int getGoalsFor() {
        return goalsFor;
    }

    public void setGoalsFor(int goalsFor) {
        this.goalsFor = goalsFor;
    }

    public int getGoalsAgainst() {
        return goalsAgainst;
    }

    public void setGoalsAgainst(int goalsAgainst) {
        this.goalsAgainst = goalsAgainst;
    }

    public int getGoalDifference() {
        return getGoalsFor() - getGoalsAgainst();
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public void addGoalsFor(int newScore) {
        this.setGoalsFor(this.getGoalsFor() + newScore);
    }

    public void addGoalsAgainst(int newScore) {
        this.setGoalsAgainst(this.getGoalsAgainst() + newScore);
    }

    public void addPoints(int newPoints) {
        this.setPoints(this.getPoints() + newPoints);
    }

    public void addDrawn(int newDrawn) {
        this.setDrawn(this.getDrawn() + newDrawn);
    }

    public void addLost(int newLost) {
        this.setLost(this.getLost() + newLost);
    }

    public void addWin(int newWin) {
        this.setWon(this.getWon() + newWin);
    }
}
