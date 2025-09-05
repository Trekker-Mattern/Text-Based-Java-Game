package com.textbasedgame.util;

import com.textbasedgame.playerFiles.GameProgress;

public class GameProgressWrapper {
    public static GameProgress gameProgress;

    public static void setGameProgress(GameProgress gameProgress) {
        GameProgressWrapper.gameProgress = gameProgress;
    }
}
