package net.minheur.mhm_bitsnbobs.commands;

/**
 * The RCON answer enum. Suppose to look like an AI. Not working
 * @see RconInputHandler
 * @see net.minheur.mhm_bitsnbobs.event.RconKeywordLoader
 */
public enum RconAnswers {
    EMPTY(null, null),
    WHO("who", "I am Rcon."),
    WHY("why", "I am here to help.");

    /**
     * The key is what the command will be searching for
     */
    private final String key;
    /**
     * The answer is what he'll be outputing when the key is found.
     */
    private final String answer;

    RconAnswers(String key, String answer) {
        this.key = key;
        this.answer = answer;
    }

    /**
     * @return the {@link #key} of the property
     */
    public String getKey() {
        return key;
    }

    /**
     * @return the {@link #answer} of the property
     */
    public String getAnswer() {
        return answer;
    }

    /**
     * Used to get the property if the {@code inputKey} is correct.
     * @param inputKey the work that is being checked.
     * @return the property corresponding to the {@code inputKey}.
     */
    public static RconAnswers fromKey(String inputKey) {
        for (RconAnswers value : values()) {
            if (value.key.equalsIgnoreCase(inputKey)) {
                return value;
            }
        }
        return EMPTY;
    }
}
