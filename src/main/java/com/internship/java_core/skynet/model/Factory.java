package com.internship.java_core.skynet.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Phaser;

public class Factory implements Runnable {
    @Getter
    @Setter
    private int DAYS = 100;

    @Getter
    private BlockingQueue<RobotParts> partsQueue;
    private Phaser phaser;

    private static final RobotParts[] partsArray = RobotParts.values();
    private static final Random rand = new Random();


    public Factory(BlockingQueue<RobotParts> partsQueue, Phaser phaser) {
        this.partsQueue = partsQueue;
        this.phaser = phaser;
    }

    @Override
    public void run() {
        try {
            for (int day = 1; day <= DAYS; day++) {
                for (int part = 1; part <= 10; part++) {
                    partsQueue.put(produceRandomPart());
                }
                phaser.arriveAndAwaitAdvance();

                phaser.arriveAndAwaitAdvance();
            }
            phaser.arriveAndDeregister();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public RobotParts produceRandomPart() {
        return partsArray[rand.nextInt(partsArray.length)];
    }
}