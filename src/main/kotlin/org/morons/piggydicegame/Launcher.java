package org.morons.piggydicegame;

public class Launcher implements Runnable {
    static String[] Args;

    public static void main(String[] args) {
        Args = args;
        new Thread(
                new Launcher(),
                "Launcher Thread"
        ).start();
    }

    @Override
    public void run() {
        System.out.println("Running Thread: " + Thread.currentThread().getName());
        PigGameApplication.main(Args);
    }
}
