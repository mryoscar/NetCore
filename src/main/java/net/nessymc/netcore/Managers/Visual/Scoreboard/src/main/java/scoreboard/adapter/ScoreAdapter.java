package net.nessymc.netcore.Managers.Visual.Scoreboard.src.main.java.scoreboard.adapter;

import net.nessymc.netcore.Managers.Visual.Scoreboard.src.main.java.scoreboard.PlayerScoreboard;

public interface ScoreAdapter {

    String getTitle();

    void updateLines(PlayerScoreboard playerScoreboard);

}
