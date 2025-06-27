package net.minheur.mhm_bitsnbobs.procedures;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;

public class RunesExecuteEvents {
    public static void executeMoney(Entity pPlayer) {
        if(pPlayer == null || pPlayer.getServer() == null) return;
        CommandSourceStack source = pPlayer.createCommandSourceStack()
                .withPermission(Commands.LEVEL_GAMEMASTERS)
                .withSuppressedOutput();
        String command = "give @s numismatics:spur 16";
        pPlayer.getServer().getCommands().performPrefixedCommand(source, command);
        String command1 = "clear @s mhm_bitsnbobs:money_rune 1";
        pPlayer.getServer().getCommands().performPrefixedCommand(source, command1);
    }

    public static void executeXp(Entity pPlayer) {
        if(pPlayer == null || pPlayer.getServer() == null) return;
        if(pPlayer instanceof Player _player) {
            _player.giveExperienceLevels(1);
        }
        CommandSourceStack source = pPlayer.createCommandSourceStack()
                .withPermission(Commands.LEVEL_GAMEMASTERS)
                .withSuppressedOutput();
        String command = "clear @s mhm_bitsnbobs:xp_rune 1";
        pPlayer.getServer().getCommands().performPrefixedCommand(source, command);
    }

    public static void executeOak(Entity pPlayer) {
        if(pPlayer == null || pPlayer.getServer() == null) return;
        CommandSourceStack source = pPlayer.createCommandSourceStack()
                .withPermission(Commands.LEVEL_GAMEMASTERS)
                .withSuppressedOutput();
        String command = "loot spawn ~ ~ ~ loot mhm_bitsnbobs:gameplay/oak_rune_loot";
        pPlayer.getServer().getCommands().performPrefixedCommand(source, command);
        String command1 = "clear @s mhm_bitsnbobs:oak_rune 1";
        pPlayer.getServer().getCommands().performPrefixedCommand(source, command1);
    }
    public static void executeSpruce(Entity pPlayer) {
        if(pPlayer == null || pPlayer.getServer() == null) return;
        CommandSourceStack source = pPlayer.createCommandSourceStack()
                .withPermission(Commands.LEVEL_GAMEMASTERS)
                .withSuppressedOutput();
        String command = "loot spawn ~ ~ ~ loot mhm_bitsnbobs:gameplay/spruce_rune_loot";
        pPlayer.getServer().getCommands().performPrefixedCommand(source, command);
        String command1 = "clear @s mhm_bitsnbobs:spruce_rune 1";
        pPlayer.getServer().getCommands().performPrefixedCommand(source, command1);
    }
}
