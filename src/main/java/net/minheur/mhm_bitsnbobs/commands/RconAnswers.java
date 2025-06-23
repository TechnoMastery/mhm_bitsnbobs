package net.minheur.mhm_bitsnbobs.commands;

public enum RconAnswers {
    EMPTY(null, null),
    WHO("who", "I am Rcon."),
    WHY("why", "I am here to help.");

    private final String key;
    private final String answer;

    RconAnswers(String key, String answer) {
        this.key = key;
        this.answer = answer;
    }

    public String getKey() {
        return key;
    }

    public String getAnswer() {
        return answer;
    }

    // Méthode utilitaire pour retrouver l'enum à partir du key
    public static RconAnswers fromKey(String inputKey) {
        for (RconAnswers value : values()) {
            if (value.key.equalsIgnoreCase(inputKey)) {
                return value;
            }
        }
        return EMPTY;
    }
}
