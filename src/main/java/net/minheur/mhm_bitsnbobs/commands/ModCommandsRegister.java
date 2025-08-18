package net.minheur.mhm_bitsnbobs.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minheur.mhm_bitsnbobs.config.ModServerConfig;

/**
 * The class for commands creation.
 */
public class ModCommandsRegister {
    public static void registerCommands(CommandDispatcher<CommandSourceStack> dispatcher) {

        dispatcher.register(
                Commands.literal("spawn")
                        .then(Commands.literal("set")
                                .requires(source -> source.hasPermission(2))
                                .executes(ctx -> {
                                    ServerPlayer player = ctx.getSource().getPlayerOrException();
                                    BlockPos pos = player.blockPosition();

                                    ModServerConfig.setSpawnPos(pos);
                                    ctx.getSource().sendSuccess(() -> Component.literal("Spawn set to " + pos + " in the overworld"), true);
                                    return 1;
                                }))
                        .then(Commands.literal("enable")
                                .requires(commandSourceStack -> commandSourceStack.hasPermission(3))
                                .executes(commandContext -> {
                                    ServerPlayer player = commandContext.getSource().getPlayerOrException();

                                    ModServerConfig.setSpawnEnabled(true);
                                    commandContext.getSource().sendSuccess(() -> Component.literal("Spawn enabled"), true);
                                    return 1;
                                }))
                        .then(Commands.literal("disable")
                                .requires(commandSourceStack -> commandSourceStack.hasPermission(3))
                                .executes(commandContext -> {
                                    ServerPlayer player = commandContext.getSource().getPlayerOrException();

                                    ModServerConfig.setSpawnEnabled(false);
                                    commandContext.getSource().sendSuccess(() -> Component.literal("Spawn disabled"), true);
                                    return 1;
                                }))
                        .executes(commandContext -> {
                            ServerPlayer player = commandContext.getSource().getPlayerOrException();
                            BlockPos pos = ModServerConfig.getSpawnPos();

                            if (!ModServerConfig.getSpawnEnabled()) {
                                commandContext.getSource().sendFailure(Component.literal("Spawn disabled !"));
                                return 0;
                            }
                            CommandSourceStack commandSource = player.createCommandSourceStack()
                                    .withPermission(Commands.LEVEL_GAMEMASTERS)
                                    .withSuppressedOutput();
                            double xPos = pos.getX() + 0.5;
                            double yPos = pos.getY() + 0.5;
                            double zPos = pos.getZ() + 0.5;
                            String command = "execute in execute in minecraft:overworld run tp @s " + xPos + " " + yPos + " " + zPos;
                            commandContext.getSource().sendSuccess(() -> Component.literal("Teleported to spawn"), true);
                            return 1;
                        })
        );

        // → duplicate this to create new commands.
        // command /bitsnbobs
        dispatcher.register(
                // command → /bitsnbobs
                Commands.literal("bitsnbobs")
                        // .then → here adds a secondary command (/mod info, /mod reload...)
                        // command → /bitsnbobs version
                        .then(Commands.literal("version")
                                .executes(commandContext -> {
                                    commandContext.getSource().sendSuccess(() -> Component.literal("[Bits'n'Bobs] Mod version : 2.0.3"), false);
                                    return 1;
                                })
                        )
                        // command → /bitsnbobs say <text>
                        .then(Commands.literal("say")
                                // .then → add arguments to the command
                                .then(Commands.argument("text", StringArgumentType.greedyString())
                                        .executes(commandContext -> {
                                            String input = StringArgumentType.getString(commandContext, "text").toLowerCase();
                                            CommandSourceStack source = commandContext.getSource();

                                            String answer = RconInputHandler.analyzeText(input);
                                            if (answer == null) {
                                                return 1;
                                            }

                                            source.sendSuccess(() -> Component.literal("[Bits'n'Bobs: Rcon] " + answer), false);
                                            return 1;
                                        })
                                )
                        )
        );
        // alias → /bitsnbobs say <text>
        dispatcher.register(
                Commands.literal("trcon")
                        .then(Commands.argument("text", StringArgumentType.greedyString())
                                .executes(commandContext -> {
                                    String input = StringArgumentType.getString(commandContext, "text").toLowerCase();
                                    CommandSourceStack source = commandContext.getSource();

                                    String answer = RconInputHandler.analyzeText(input);
                                    if (answer == null) {
                                        return 1;
                                    }

                                    source.sendSuccess(() -> Component.literal("[Bits'n'Bobs: Rcon] " + answer), false);
                                    return 1;
                                }))
        );
    }
}
