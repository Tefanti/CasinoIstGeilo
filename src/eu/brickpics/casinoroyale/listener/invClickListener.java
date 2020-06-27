package eu.brickpics.casinoroyale.listener;

import eu.brickpics.casinoroyale.manager.GameManager;
import eu.brickpics.casinoroyale.manager.InventoryManager;
import eu.brickpics.casinoroyale.manager.QueueManager;
import eu.brickpics.casinoroyale.manager.RequestManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Skull;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

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

                if (e.getCurrentItem().getType() == Material.SKULL_ITEM) {
                    e.setCancelled(true);

                    InventoryManager.managerequestInv(p, Bukkit.getPlayer(e.getCurrentItem().getItemMeta().getDisplayName()));
                    p.openInventory(InventoryManager.requestinv);
                    p.playSound(p.getLocation(), Sound.CLICK, 1.0f, 1.0f);

                    //RequestManager.addRequest(p, Bukkit.getPlayer(e.getCurrentItem().getItemMeta().getDisplayName()), );
                }
            }


            if (e.getCurrentItem().hasItemMeta()) {
                if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.RED + "Settings")) {


                    e.setCancelled(true);
                    p.playSound(p.getLocation(), Sound.CLICK, 1.0f, 1.0f);

                }
            }
        }

        // "§lRequest"

        if (e.getInventory().getTitle().startsWith(ChatColor.BLUE + "§lRequest")) {
            if (e.getCurrentItem().hasItemMeta()) {
                if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.AQUA + "RPS")) {
                    e.setCancelled(true);

                    RequestManager.addRequest(p, Bukkit.getPlayer(e.getInventory().getTitle().split(" ")[1]), GameManager.GameType.ROCKPAPERSCISSORS);
                    p.closeInventory();
                    p.playSound(p.getLocation(), Sound.CLICK, 1.0f, 1.0f);

                } else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.AQUA + "TTT")) {
                    e.setCancelled(true);

                    RequestManager.addRequest(p, Bukkit.getPlayer(e.getInventory().getTitle().split(" ")[1]), GameManager.GameType.TICTACTOE);
                    p.closeInventory();
                    p.playSound(p.getLocation(), Sound.CLICK, 1.0f, 1.0f);

                }

                // TODO: TicTacToe
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
