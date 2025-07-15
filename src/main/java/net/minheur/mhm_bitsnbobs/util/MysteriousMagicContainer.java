package net.minheur.mhm_bitsnbobs.util;

import net.minecraft.world.SimpleContainer;

public class MysteriousMagicContainer extends SimpleContainer {
    private final int fuelAmount;

    public MysteriousMagicContainer(int fuelAmount, int size) {
        super(size);
        this.fuelAmount = fuelAmount;
    }

    public int getFuelAmount() {
        return fuelAmount;
    }
}
