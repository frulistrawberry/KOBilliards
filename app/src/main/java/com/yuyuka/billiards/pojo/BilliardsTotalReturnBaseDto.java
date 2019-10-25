package com.yuyuka.billiards.pojo;

import java.io.Serializable;

public class BilliardsTotalReturnBaseDto implements Serializable {

    private Data data;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public static class  Data implements Serializable{
        private double avgVrt;
        private double avgGradeRate;
        private int avgAggre;
        private int avgDefend;
        private int avgInningLong;
        private int avgSceneLong;
        private int totalScenes;
        private String winRate;
        private int fuckPers;
        private int fuckedPers;
        private int playWithPers;
        private int totalInnings;
        private long totalScenesLong;

        public int getFuckedPers() {
            return fuckedPers;
        }

        public void setFuckedPers(int fuckedPers) {
            this.fuckedPers = fuckedPers;
        }

        public double getAvgVrt() {
            return avgVrt;
        }

        public void setAvgVrt(double avgVrt) {
            this.avgVrt = avgVrt;
        }

        public double getAvgGradeRate() {
            return avgGradeRate;
        }

        public void setAvgGradeRate(double avgGradeRate) {
            this.avgGradeRate = avgGradeRate;
        }

        public int getAvgAggre() {
            return avgAggre;
        }

        public void setAvgAggre(int avgAggre) {
            this.avgAggre = avgAggre;
        }

        public int getAvgDefend() {
            return avgDefend;
        }

        public void setAvgDefend(int avgDefend) {
            this.avgDefend = avgDefend;
        }

        public int getAvgInningLong() {
            return avgInningLong;
        }

        public void setAvgInningLong(int avgInningLong) {
            this.avgInningLong = avgInningLong;
        }

        public int getAvgSceneLong() {
            return avgSceneLong;
        }

        public void setAvgSceneLong(int avgSceneLong) {
            this.avgSceneLong = avgSceneLong;
        }

        public int getTotalScenes() {
            return totalScenes;
        }

        public void setTotalScenes(int totalScenes) {
            this.totalScenes = totalScenes;
        }

        public String getWinRate() {
            return winRate;
        }

        public void setWinRate(String winRate) {
            this.winRate = winRate;
        }

        public int getFuckPers() {
            return fuckPers;
        }

        public void setFuckPers(int fuckPers) {
            this.fuckPers = fuckPers;
        }

        public int getPlayWithPers() {
            return playWithPers;
        }

        public void setPlayWithPers(int playWithPers) {
            this.playWithPers = playWithPers;
        }

        public int getTotalInnings() {
            return totalInnings;
        }

        public void setTotalInnings(int totalInnings) {
            this.totalInnings = totalInnings;
        }

        public long getTotalScenesLong() {
            return totalScenesLong;
        }

        public void setTotalScenesLong(long totalScenesLong) {
            this.totalScenesLong = totalScenesLong;
        }
    }
}
