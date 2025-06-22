package net.minheur.mhm_bitsnbobs.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;

public class ModCommandsRegister {
    public static void registerCommands(CommandDispatcher<CommandSourceStack> dispatcher) {

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
                                            String input = StringArgumentType.getString(commandContext, "text");
                                            CommandSourceStack source = commandContext.getSource();

                                            String answer = RconInputHandler.analyzeText(input);

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
                                    String input = StringArgumentType.getString(commandContext, "text");
                                    CommandSourceStack source = commandContext.getSource();

                                    String answer = RconInputHandler.analyzeText(input);

                                    source.sendSuccess(() -> Component.literal("[Bits'n'Bobs: Rcon] " + answer), false);
                                    return 1;
                                }))
        );
    }
}
