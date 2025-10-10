package net.nokunami.elementus.client.gui.overlay;

public class CatalystExhaustionClient {
    private static int exhaustion;

    public static void set(int exhaustion) {
        CatalystExhaustionClient.exhaustion = exhaustion;
    }

    public static int get() {
        return exhaustion;
    }
}
