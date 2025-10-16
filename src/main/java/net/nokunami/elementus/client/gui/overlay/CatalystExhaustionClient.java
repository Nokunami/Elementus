package net.nokunami.elementus.client.gui.overlay;

public class CatalystExhaustionClient {
    private static int exhaustion;
    private static int exhaustionChunk;
    private static int exhaustionChunkSmall;

    public static void setExhaustion(int exhaustion) {
//        CatalystExhaustionClient.exhaustion = exhaustion;
        for (int i = 0; i < exhaustion; i++) {
            CatalystExhaustionClient.exhaustionChunk += 1;
        }
        for (int i = 0; i < exhaustion; i++) {
            CatalystExhaustionClient.exhaustionChunkSmall += i;
            if (CatalystExhaustionClient.exhaustionChunkSmall > 5)
                CatalystExhaustionClient.exhaustionChunkSmall = 0;
        }
    }

    public static int getExhaustion() {
        return exhaustion;
    }

    public static int getExhaustionChunk() {
        return exhaustionChunk;
    }

    public static int getExhaustionSmallChunk() {
        return exhaustionChunkSmall;
    }
}
