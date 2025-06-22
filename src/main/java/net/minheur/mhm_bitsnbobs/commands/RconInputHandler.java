package net.minheur.mhm_bitsnbobs.commands;

public class RconInputHandler {
    public String[] rconKeyWords;

    public static String analyzeText(String input) {
        if (input.contains("who")) {
            return "I am Rcon.";
        }
        return "hello";
    }
}
