package net.nessymc.netcore.Managers.Visual.Scoreboard.src.main.java.scoreboard;

public class ScoreboardInput {

    private String prefix;
    private String suffix;

    public ScoreboardInput(String prefix, String suffix) {
        this.prefix = prefix;
        this.suffix = suffix;
    }

    public String getPrefix() {
        return this.prefix;
    }

    public String getSuffix() {
        return this.suffix;
    }

    boolean equals(ScoreboardInput other) {
        return this.prefix.length() == other.getPrefix().length() && this.prefix.equals(other.getPrefix())
            && this.suffix.length() == other.getSuffix().length() && this.suffix.equals(other.getSuffix());
    }
}
