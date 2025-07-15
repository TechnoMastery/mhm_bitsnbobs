package net.minheur.mhm_bitsnbobs.util.cold_head;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.minheur.mhm_bitsnbobs.MhmBitsnbobs;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ColdHeadProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {
    public static final ResourceLocation IDENTIFIER =
            new ResourceLocation(MhmBitsnbobs.MOD_ID, "cold_head");

    private final ColdHead instance = new ColdHead();

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        return cap == ColdHeadCapability.INSTANCE ? LazyOptional.of(() -> instance).cast() : LazyOptional.empty();
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag tag = new CompoundTag();
        tag.putFloat("cold", instance.getColdLevel());
        return tag;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        instance.setColdLevel(nbt.getFloat("cold"));
    }
}
