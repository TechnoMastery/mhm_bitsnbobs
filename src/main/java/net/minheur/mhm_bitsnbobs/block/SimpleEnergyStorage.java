package net.minheur.mhm_bitsnbobs.block;

import net.minecraftforge.energy.EnergyStorage;

public class SimpleEnergyStorage extends EnergyStorage {
    private final Runnable onChange;

    public SimpleEnergyStorage(int capacity, int maxReceive, int maxExtract, Runnable onChange) {
        super(capacity, maxReceive, maxExtract);
        this.onChange = onChange;
    }
    public SimpleEnergyStorage(int capacity, Runnable onChange) {
        this(capacity, capacity, capacity, onChange);
    }

    @Override
    public int receiveEnergy(int maxReceive, boolean simulate) {
        int received = super.receiveEnergy(maxReceive, simulate);
        if (!simulate && received > 0 && onChange != null) onChange.run();
        return received;
    }

    @Override
    public int extractEnergy(int maxExtract, boolean simulate) {
        int extracted = super.extractEnergy(maxExtract, simulate);
        if (!simulate && extracted > 0 && onChange != null) onChange.run();
        return extracted;
    }

    public void setEnergy(int amount) {
        if (amount < 0) amount = 0;
        if (amount > capacity) amount = capacity;
        this.energy = amount;
        if (onChange != null) onChange.run();
    }
}
