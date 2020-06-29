package eu.brickpics.casinoroyale.manager;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;

public class PlayerManager {

    public static HashMap<Player, GameManager.GameType> playersInGame = new HashMap<>();

    public static boolean isPlaying(Player player) { return playersInGame.containsKey(player); }

    public static void addToList(Player player, GameManager.GameType t) {
        if (!isPlaying(player))
            playersInGame.put(player, t);
    }

    public static void removeFromList(Player player) {
        if (isPlaying(player)) playersInGame.remove(player);
    }
}
