package eu.brickpics.casinoroyale.listener;

import eu.brickpics.casinoroyale.manager.GameManager;
import eu.brickpics.casinoroyale.manager.QueueManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class leaveListener implements Listener {

    @EventHandler
    public void onLeave(PlayerQuitEvent e){

        Player p = e.getPlayer();


        QueueManager.removePlayer(GameManager.GameType.ROCKPAPERSCISSORS,p);
        QueueManager.removePlayer(GameManager.GameType.TICTACTOE,p);



    }
}
