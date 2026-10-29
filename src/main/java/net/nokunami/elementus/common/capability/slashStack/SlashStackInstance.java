package net.nokunami.elementus.common.capability.slashStack;

public class SlashStackInstance {
    int duration;
    int stack;

    public SlashStackInstance(int time, int count) {
        duration = time;
        stack = count;
    }

    public void tick() { duration--; }

    public boolean tickOrDiscard() {
        duration--;
        return duration <= 0;
    }

    public void add(int time, int count) {
        duration = time;
        stack += count;
    }

    public int getDuration() { return duration; }
    public int getStack() { return stack; }
}
