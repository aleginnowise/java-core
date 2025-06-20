package com.internship.java_core.skynet;

import com.internship.java_core.skynet.model.Factory;
import com.internship.java_core.skynet.model.RobotParts;
import com.internship.java_core.skynet.model.WednesdayFaction;
import com.internship.java_core.skynet.model.WorldFaction;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.Phaser;

public class SkynetApplication {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<RobotParts> partsQueue = new LinkedBlockingQueue<>();
        Phaser phaser = new Phaser(3);

        Factory factory = new Factory(partsQueue, phaser);
        WorldFaction worldFaction = new WorldFaction(partsQueue, phaser);
        WednesdayFaction wednesdayFaction = new WednesdayFaction(partsQueue, phaser);

        Thread factoryThread = new Thread(factory);
        Thread worldFactionThread = new Thread(worldFaction);
        Thread wednesdayFactionThread = new Thread(wednesdayFaction);

        factoryThread.start();
        worldFactionThread.start();
        wednesdayFactionThread.start();

        factoryThread.join();
        worldFactionThread.join();
        wednesdayFactionThread.join();

        whoHaveTheStrongestArmy(worldFaction.getRobot(), wednesdayFaction.getRobot());
    }

    static void whoHaveTheStrongestArmy(int worldFactionRobot, int wednesdayFactionRobot) {
        if (worldFactionRobot > wednesdayFactionRobot) {
            System.out.println("\n//// Arms race ends ////\nWorld Faction has the strongest army");
        } else if (worldFactionRobot < wednesdayFactionRobot) {
            System.out.println("\nWednesday Faction has the strongest army");
        } else {
            System.out.println("\nWorld Faction and Wednesday Faction armies are equal");
        }

        System.out.println("World Faction robots: " + worldFactionRobot
                + "\nWednesday Faction robots: " + wednesdayFactionRobot);
    }
}