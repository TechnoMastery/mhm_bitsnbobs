package net.minheur.mhm_bitsnbobs.block;

import net.minecraftforge.energy.EnergyStorage;

public class SimpleEnergyStorage extends EnergyStorage {
    public SimpleEnergyStorage(int capacity) {
        super(capacity);
    }

    public void addEnergy(int amount) {
        if (capacity < energy + amount) {
            throw new IllegalStateException("Storing more energy that capable");
        } else {
            energy += amount;
        }
    }

    public void removeEnergy(int amount) {
        if (energy < amount) {
            throw new IllegalStateException("Removing more energy than capable");
        } else {
            energy -= amount;
        }
    }

    public void forceSetEnergy(int amount) {
        if (capacity < amount) {
            throw new IllegalStateException("Storing more energy that capable");
        } else {
            energy = amount;
        }
    }
}
