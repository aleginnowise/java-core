package com.internship.java_core.skynet.model;

import lombok.Getter;
import lombok.Setter;

import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Phaser;

public class WednesdayFaction implements Runnable {
    private final EnumSet<RobotParts> KIT = EnumSet.allOf(RobotParts.class);

    @Getter
    private int robot;

    @Getter
    @Setter
    private int DAYS = 100;

    @Getter
    private List<RobotParts> parts = new ArrayList<>();

    private BlockingQueue<RobotParts> partsFromFactory;
    private Phaser phaser;

    public WednesdayFaction(BlockingQueue<RobotParts> partsFromFactory, Phaser phaser) {
        this.partsFromFactory = partsFromFactory;
        this.phaser = phaser;
    }

    @Override
    public void run() {
        try {
            for (int day = 1; day <= DAYS; day++) {
                phaser.arriveAndAwaitAdvance();

                for (int j = 0; j < 5 && !partsFromFactory.isEmpty(); j++) {
                    parts.add(partsFromFactory.take());
                }
                buildRobot();
                phaser.arriveAndAwaitAdvance();
            }
            phaser.arriveAndDeregister();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void buildRobot() {
        if (new HashSet<>(parts).containsAll(KIT)) {
            for (RobotParts part : KIT) {
                parts.remove(part);
            }
            robot++;
        }
    }
}