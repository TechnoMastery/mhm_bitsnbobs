package net.minheur.mhm_bitsnbobs.procedures;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.world.entity.Entity;

public class WindStickOnUse {
    public static void execute(Entity pPlayer) {
        if(pPlayer == null || pPlayer.getServer() == null) return;
        CommandSourceStack source = pPlayer.createCommandSourceStack()
                .withPermission(Commands.LEVEL_GAMEMASTERS)
                .withSuppressedOutput();
        String command = "effect give @a[name=!" + pPlayer.getName() + "] levitation 1 10 true";
        pPlayer.getServer().getCommands().performPrefixedCommand(source, command);
    }
}
