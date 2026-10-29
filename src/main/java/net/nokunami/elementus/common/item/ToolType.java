package net.nokunami.elementus.common.item;

public enum ToolType {
    SWORD, SHOVEL, PICKAXE, AXE, HOE;

    public boolean isWeapon() {
        return this == SWORD || this == AXE;
    }
}
