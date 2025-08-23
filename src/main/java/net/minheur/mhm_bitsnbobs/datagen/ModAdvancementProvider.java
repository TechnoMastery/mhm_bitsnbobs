package net.minheur.mhm_bitsnbobs.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.advancements.AdvancementProvider;
import net.minheur.mhm_bitsnbobs.datagen.advancements.ModStoryAdvancementSubProvider;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Providing advancement generation data.
 */
public class ModAdvancementProvider extends AdvancementProvider {
    /**
     * Constructor of the provider. Used to add sub providers in. List all advancement sub providers here.
     * @param pOutput the place where the files are saved
     * @param registries the registry thing
     */
    public ModAdvancementProvider(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> registries) {
        super(pOutput, registries, List.of(
                // advancements generators Here.
                new ModStoryAdvancementSubProvider()
        ));
    }
}
