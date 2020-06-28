package eu.brickpics.casinoroyale;

import eu.brickpics.casinoroyale.manager.GameManager;
import eu.brickpics.casinoroyale.storage.Data;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class CasinoRequest {

    public Player requestOrigin;
    public Player requestDestination;



    public GameManager.GameType type;


    public CasinoRequest(Player requestOrigin, Player requestDestination, GameManager.GameType type) {
        this.requestOrigin = requestOrigin;
        this.requestDestination = requestDestination;
        this.type = type;
        requestOrigin.sendMessage(Data.PREFIX + "You have requested " + requestDestination.getDisplayName() + " to a Game of " + type.toString().toLowerCase());
        this.send();
    }

    public void accept() { // Aka start
        GameManager.startGame(type, requestOrigin, requestDestination);
    }

    public void send() {
        requestDestination.sendMessage(requestOrigin.getDisplayName() + " has requested to play " + type.toString().toLowerCase() + " with you.");
    }
}
