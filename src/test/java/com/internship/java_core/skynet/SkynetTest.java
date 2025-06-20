package com.internship.java_core.skynet;

import com.internship.java_core.skynet.model.Factory;
import com.internship.java_core.skynet.model.RobotParts;
import com.internship.java_core.skynet.model.WednesdayFaction;
import com.internship.java_core.skynet.model.WorldFaction;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.Phaser;

import static org.junit.jupiter.api.Assertions.*;

public class SkynetTest {
    private static BlockingQueue<RobotParts> partsQueue = new LinkedBlockingQueue<>();
    private static Phaser phaser = new Phaser(3);

    static Factory factory;
    static WorldFaction worldFaction;
    static WednesdayFaction wednesdayFaction;

    static Thread factoryThread;
    static Thread worldFactionThread;
    static Thread wednesdayFactionThread;

    @BeforeAll
    public static void setUp() throws InterruptedException {
        factory = new Factory(partsQueue, phaser);
        worldFaction = new WorldFaction(partsQueue, phaser);
        wednesdayFaction = new WednesdayFaction(partsQueue, phaser);

        factoryThread = new Thread(factory);
        worldFactionThread = new Thread(worldFaction);
        wednesdayFactionThread = new Thread(wednesdayFaction);

        factoryThread.start();
        worldFactionThread.start();
        wednesdayFactionThread.start();

        factoryThread.join();
        worldFactionThread.join();
        wednesdayFactionThread.join();
    }

    @Test
    public void givenFactoryAndFactions_whenTheDayEnds_thenFactoryPartsQueueIsEmpty() {
        assertTrue(factory.getPartsQueue().isEmpty());
    }

    @Test
    public void givenFactoryAndFactions_whenArmsRaceEnds_thenWorldFactionHaveParts() {
        assertFalse(worldFaction.getParts().isEmpty());
    }

    @Test
    public void givenFactoryAndFactions_whenArmsRaceEnds_thenWednesdayFactionHaveParts() {
        assertFalse(wednesdayFaction.getParts().isEmpty());
    }

    @Test
    public void givenFactoryAndFactions_whenArmsRaceEnds_thenWorldFactionHaveRobots() {
        assertTrue(worldFaction.getRobot() > 0);
    }

    @Test
    public void givenFactoryAndFactions_whenArmsRaceEnds_thenWednesdayFactionHaveRobots() {
        assertTrue(wednesdayFaction.getRobot() > 0);
    }
}