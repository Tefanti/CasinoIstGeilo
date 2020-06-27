package eu.brickpics.casinoroyale.listener;

import eu.brickpics.casinoroyale.manager.GameManager;
import eu.brickpics.casinoroyale.manager.InventoryManager;
import eu.brickpics.casinoroyale.manager.QueueManager;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class invClickListener implements Listener {

    @EventHandler
    public void onInvClick(InventoryClickEvent e) {

        Player p = (Player) e.getWhoClicked();
        if (e.getInventory().getTitle().equalsIgnoreCase(ChatColor.BLUE + "§lCasino")) {

            if (e.getCurrentItem().hasItemMeta()) {
                if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.AQUA + "Enter Queue")) {


                    e.setCancelled(true);

                    InventoryManager.managegameInv(p);
                    p.openInventory(InventoryManager.gameinv);
                    p.playSound(p.getLocation(), Sound.CLICK, 1.0f, 1.0f);


                }
            }


            if (e.getCurrentItem().hasItemMeta()) {
                if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.RED + "Settings")) {


                    e.setCancelled(true);
                    p.playSound(p.getLocation(), Sound.CLICK, 1.0f, 1.0f);

                }
            }
        }


        if (e.getInventory().getTitle().equalsIgnoreCase(ChatColor.BLUE + "§lGames")) {

            if (e.getCurrentItem().hasItemMeta()) {

                if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.AQUA + "RPS")) {


                    e.setCancelled(true);

                    if (QueueManager.RPSQueue.contains(p)) {
                        p.sendMessage(ChatColor.WHITE + "You left the Queue for RPS");
                        QueueManager.removePlayer(GameManager.GameType.ROCKPAPERSCISSORS,p);


                    } else {
                        p.sendMessage(ChatColor.WHITE + "You entered the Queue for RPS");
                        QueueManager.addPlayer(GameManager.GameType.ROCKPAPERSCISSORS,p);

                    }
                    p.closeInventory();
                    p.playSound(p.getLocation(), Sound.CLICK, 1.0f, 1.0f);

                }

                if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.AQUA + "TTT")) {


                    e.setCancelled(true);

                    if (QueueManager.TTTQueue.contains(p)) {
                        p.sendMessage(ChatColor.WHITE + "You left the Queue for TTT");
                        QueueManager.removePlayer(GameManager.GameType.TICTACTOE,p);


                    } else {
                        p.sendMessage(ChatColor.WHITE + "You entered the Queue for TTT");
                        QueueManager.addPlayer(GameManager.GameType.TICTACTOE,p);

                    }
                    p.closeInventory();
                    p.playSound(p.getLocation(), Sound.CLICK, 1.0f, 1.0f);

                }
            }
        }

    }
}
