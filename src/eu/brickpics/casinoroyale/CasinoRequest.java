package eu.brickpics.casinoroyale;

import eu.brickpics.casinoroyale.manager.GameManager;
import eu.brickpics.casinoroyale.storage.Data;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;


public class CasinoRequest {

    public Player requestOrigin;
    public Player requestDestination;



    public GameManager.GameType type;


    public CasinoRequest(Player requestOrigin, Player requestDestination, GameManager.GameType type) {
        this.requestOrigin = requestOrigin;
        this.requestDestination = requestDestination;
        this.type = type;
        requestOrigin.sendMessage(Data.PREFIX + ChatColor.WHITE + "You have requested " + ChatColor.GOLD +  requestDestination.getDisplayName() + ChatColor.WHITE + " to a Game of " + ChatColor.GOLD + type.toString().toLowerCase());
        this.send();
    }

    public void accept() { // Aka start
        GameManager.startGame(type, requestOrigin, requestDestination);
    }

    public void send() {

        TextComponent tc = new TextComponent();
        tc.setText("[Click here to accept]");
        tc.setColor(ChatColor.GOLD);
        tc.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("Click here to accept").create()));
        tc.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND,"/casino accept " + requestOrigin.getDisplayName()));
        requestDestination.sendMessage(Data.PREFIX + ChatColor.GOLD + requestOrigin.getDisplayName() + ChatColor.WHITE + " has requested to play " + ChatColor.GOLD + type.toString().toLowerCase() + ChatColor.WHITE + " with you.");
        requestDestination.spigot().sendMessage(tc);
    }
}
