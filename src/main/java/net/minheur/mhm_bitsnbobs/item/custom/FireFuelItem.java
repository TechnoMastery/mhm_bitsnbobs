package net.minheur.mhm_bitsnbobs.item.custom;

public class FireFuelItem extends FuelItem {
    /**
     * Fire fuel power of the item
     */
    private final int fireFuelPower;

    public FireFuelItem(Properties pProperties, int burnTime, int maxFirePower) {
        super(pProperties, burnTime);
        this.fireFuelPower = maxFirePower;
    }

    /**
     * Getter for {@link #fireFuelPower}
     * @return {@link #fireFuelPower}
     */
    public int getFireFuelPower() {
        return fireFuelPower;
    }
}
