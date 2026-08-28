package org.example.CollectionHW.Tasks.Task1;

import java.util.*;

class Passenger {
    long arrivalTime;
    public Passenger(long arrivalTime) { this.arrivalTime = arrivalTime; }
}

public class BoatPierSimulation {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        System.out.print("Введіть середній інтервал приходу пасажирів (хв): ");
        double avgPassengerInterval = scanner.nextDouble();

        System.out.print("Введіть середній інтервал приходу катерів (хв): ");
        double avgBoatInterval = scanner.nextDouble();

        System.out.print("Це кінцева зупинка? (true/false): ");
        boolean isTerminal = scanner.nextBoolean();

        Queue<Passenger> pierQueue = new ArrayDeque<>();
        List<Long> waitTimes = new ArrayList<>();
        int simulationTime = 720; // 12 годин у хвилинах

        int nextPassengerTime = (int) (avgPassengerInterval + random.nextGaussian());
        int nextBoatTime = (int) (avgBoatInterval + random.nextGaussian());

        for (int minute = 1; minute <= simulationTime; minute++) {
            if (minute >= nextPassengerTime) {
                pierQueue.add(new Passenger(minute));
                nextPassengerTime = minute + Math.max(1, (int) (avgPassengerInterval + random.nextInt(3) - 1));
            }

            if (minute >= nextBoatTime) {
                int freeSeats = isTerminal ? random.nextInt(20) + 10 : random.nextInt(10);
                while (freeSeats > 0 && !pierQueue.isEmpty()) {
                    Passenger p = pierQueue.poll();
                    waitTimes.add((long) (minute - p.arrivalTime));
                    freeSeats--;
                }
                nextBoatTime = minute + Math.max(1, (int) (avgBoatInterval + random.nextInt(5) - 2));
            }
        }

        double avgWaitTime = waitTimes.stream().mapToLong(Long::longValue).average().orElse(0.0);
        System.out.printf("Середній час перебування людини на зупинці: %.2f хв\n", avgWaitTime);
        System.out.println("Людей у черзі наприкінці симуляції: " + pierQueue.size());
    }
}