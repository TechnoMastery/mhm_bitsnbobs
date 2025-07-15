package net.minheur.mhm_bitsnbobs.util.cold_head;

import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;

public class ColdHeadCapability {
    public static Capability<IColdHead> INSTANCE = CapabilityManager.get(new CapabilityToken<>(){});
}
