package eu.brickpics.casinoroyale;

import eu.brickpics.casinoroyale.manager.GameManager;
import eu.brickpics.casinoroyale.manager.ItemManager;
import eu.brickpics.casinoroyale.manager.PlayerManager;
import eu.brickpics.casinoroyale.storage.Data;
import net.geknxddelt.info.api.ColorAPI;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;

import static org.bukkit.Material.*;

public class CasinoTTTGame implements Listener{

    Player p1;
    Player p2;;
    boolean isrunningttt;
    boolean p1turn;
    GameManager.GameType type;
    Inventory ttt0inv1;


    CasinoRoyale plugin;


    public CasinoTTTGame(GameManager.GameType type, Player p1, Player p2, CasinoRoyale plugin) {
        this.p1 = p1;
        this.p2 = p2;
        this.type = type;
        this.plugin = plugin;
        this.isrunningttt = false;
        this.p1turn = true;
        Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {

            @Override
            public void run() {
                beginGame();

            }
        },5*20L);

    }

    public void abortGame(){
        PlayerManager.removeFromList(p1);
        PlayerManager.removeFromList(p2);
        this.isrunningttt = false;
        GameManager.endGame(GameManager.GameType.TICTACTOE,this);

        Bukkit.getLogger().info("TTT Game aborted");

    }

    public void beginGame(){

        PlayerManager.addToList(p1, GameManager.GameType.TICTACTOE);
        PlayerManager.addToList(p2, GameManager.GameType.TICTACTOE);

        ttt0inv1 = Bukkit.getServer().createInventory(null, 3*9, ChatColor.BLUE + "§lTTT");
        ttt0inv1.setItem(2, new ItemManager(BLAZE_ROD).setNoName().build());
        ttt0inv1.setItem(3, new ItemManager(STAINED_GLASS_PANE).setNoName().build());
        ttt0inv1.setItem(4, new ItemManager(STAINED_GLASS_PANE).setNoName().build());
        ttt0inv1.setItem(5, new ItemManager(STAINED_GLASS_PANE).setNoName().build());
        ttt0inv1.setItem(6, new ItemManager(BLAZE_ROD).setNoName().build());
        ttt0inv1.setItem(9, new ItemManager(Material.SKULL_ITEM).setSkullOwner(this.p1.getDisplayName()).setData((short) SkullType.PLAYER.ordinal()).setDisplayName(this.p1.getDisplayName()).build());
        ttt0inv1.setItem(17, new ItemManager(Material.SKULL_ITEM).setSkullOwner(this.p2.getDisplayName()).setData((short) SkullType.PLAYER.ordinal()).setDisplayName(this.p2.getDisplayName()).build());
        ttt0inv1.setItem(18, new ItemManager(STAINED_GLASS_PANE).setData((short)13).build());
        ttt0inv1.setItem(26, new ItemManager(STAINED_GLASS_PANE).setData((short)14).build());
        ttt0inv1.setItem(11, new ItemManager(BLAZE_ROD).setNoName().build());
        ttt0inv1.setItem(12, new ItemManager(STAINED_GLASS_PANE).setNoName().build());
        ttt0inv1.setItem(13, new ItemManager(STAINED_GLASS_PANE).setNoName().build());
        ttt0inv1.setItem(14, new ItemManager(STAINED_GLASS_PANE).setNoName().build());
        ttt0inv1.setItem(15, new ItemManager(BLAZE_ROD).setNoName().build());
        ttt0inv1.setItem(20, new ItemManager(BLAZE_ROD).setNoName().build());
        ttt0inv1.setItem(21, new ItemManager(STAINED_GLASS_PANE).setNoName().build());
        ttt0inv1.setItem(22, new ItemManager(STAINED_GLASS_PANE).setNoName().build());
        ttt0inv1.setItem(23, new ItemManager(STAINED_GLASS_PANE).setNoName().build());
        ttt0inv1.setItem(24, new ItemManager(BLAZE_ROD).setNoName().build());


        this.p1.openInventory(ttt0inv1);
        this.p2.openInventory(ttt0inv1);

        this.isrunningttt = true;


        Bukkit.getLogger().info("TTT Game started");

        Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {

            @Override
            public void run() {

                if(isrunningttt) {
                    p1.sendMessage(Data.PREFIX +ChatColor.WHITE +"Game timed out");
                    p2.sendMessage(Data.PREFIX +ChatColor.WHITE +"Game timed out");
                    abortGame();


                }


            }

        }, 30 * 20L);

    }


    @EventHandler
    public void onClick(InventoryClickEvent e){

        Player p = (Player) e.getWhoClicked();

        if (e.getCurrentItem().hasItemMeta()) {
            e.setCancelled(true);
            if (e.getInventory().getTitle().equalsIgnoreCase(ChatColor.BLUE + "§lTTT")) {

                if(this.isrunningttt) {
                    int slot = e.getSlot();
                    if (p == this.p1) {
                        if (this.p1turn && e.getCurrentItem().getType() == STAINED_GLASS_PANE) {

                            ttt0inv1.setItem(slot, new ItemManager(SNOW_BALL).setNoName().build());
                            ttt0inv1.setItem(18, new ItemManager(STAINED_GLASS_PANE).setData((short)14).build());
                            ttt0inv1.setItem(26, new ItemManager(STAINED_GLASS_PANE).setData((short)13).build());
                            this.p1.openInventory(ttt0inv1);
                            this.p2.openInventory(ttt0inv1);
                            this.p1turn = false;
                        } else {

                            p.sendMessage("It is not your turn");
                        }
                    }
                    if (p == this.p2) {
                        if (this.p1turn == false && e.getCurrentItem().getType() == STAINED_GLASS_PANE) {

                            ttt0inv1.setItem(slot, new ItemManager(FIREBALL).setNoName().build());
                            ttt0inv1.setItem(18, new ItemManager(STAINED_GLASS_PANE).setData((short)13).build());
                            ttt0inv1.setItem(26, new ItemManager(STAINED_GLASS_PANE).setData((short)14).build());
                            this.p1.openInventory(ttt0inv1);
                            this.p2.openInventory(ttt0inv1);
                            this.p1turn = true;
                        } else {

                            p.sendMessage("It is not your turn");
                        }
                    }
                }

                //p1 wins
                //horizontal
                if(e.getClickedInventory().getItem(3).getType().equals(SNOW_BALL) && e.getClickedInventory().getItem(4).getType().equals(SNOW_BALL) && e.getClickedInventory().getItem(5).getType().equals(SNOW_BALL) || e.getClickedInventory().getItem(12).getType().equals(SNOW_BALL) && e.getClickedInventory().getItem(13).getType().equals(SNOW_BALL) && e.getClickedInventory().getItem(14).getType().equals(SNOW_BALL) || e.getClickedInventory().getItem(21).getType().equals(SNOW_BALL) && e.getClickedInventory().getItem(22).getType().equals(SNOW_BALL) && e.getClickedInventory().getItem(23).getType().equals(SNOW_BALL)){


                    Bukkit.broadcastMessage(Data.PREFIX + ChatColor.GOLD + this.p1.getDisplayName() + ChatColor.WHITE + " won against " + ChatColor.GOLD + this.p2.getDisplayName() + ChatColor.WHITE + " in TicTacToe");
                    e.setCancelled(true);
                    this.isrunningttt = false;
                    abortGame();

                }
                //vertical
                if(e.getClickedInventory().getItem(3).getType().equals(SNOW_BALL) && e.getClickedInventory().getItem(12).getType().equals(SNOW_BALL) && e.getClickedInventory().getItem(21).getType().equals(SNOW_BALL) || e.getClickedInventory().getItem(4).getType().equals(SNOW_BALL) && e.getClickedInventory().getItem(13).getType().equals(SNOW_BALL) && e.getClickedInventory().getItem(22).getType().equals(SNOW_BALL) || e.getClickedInventory().getItem(5).getType().equals(SNOW_BALL) && e.getClickedInventory().getItem(14).getType().equals(SNOW_BALL) && e.getClickedInventory().getItem(23).getType().equals(SNOW_BALL)){


                    Bukkit.broadcastMessage(Data.PREFIX + ChatColor.GOLD + this.p1.getDisplayName() + ChatColor.WHITE + " won against " + ChatColor.GOLD + this.p2.getDisplayName() + ChatColor.WHITE + " in TicTacToe");
                    e.setCancelled(true);
                    this.isrunningttt = false;
                    abortGame();

                }

                //cross
                if(e.getClickedInventory().getItem(3).getType().equals(SNOW_BALL) && e.getClickedInventory().getItem(13).getType().equals(SNOW_BALL) && e.getClickedInventory().getItem(23).getType().equals(SNOW_BALL) || e.getClickedInventory().getItem(5).getType().equals(SNOW_BALL) && e.getClickedInventory().getItem(13).getType().equals(SNOW_BALL) && e.getClickedInventory().getItem(21).getType().equals(SNOW_BALL)){


                    Bukkit.broadcastMessage(Data.PREFIX + ChatColor.GOLD + this.p1.getDisplayName() + ChatColor.WHITE + " won against " + ChatColor.GOLD + this.p2.getDisplayName() + ChatColor.WHITE + " in TicTacToe");
                    e.setCancelled(true);
                    this.isrunningttt = false;
                    abortGame();

                }


                //p2 wins
                //horizontal
                if(e.getClickedInventory().getItem(3).getType().equals(FIREBALL) && e.getClickedInventory().getItem(4).getType().equals(FIREBALL) && e.getClickedInventory().getItem(5).getType().equals(FIREBALL) || e.getClickedInventory().getItem(12).getType().equals(FIREBALL) && e.getClickedInventory().getItem(13).getType().equals(FIREBALL) && e.getClickedInventory().getItem(14).getType().equals(FIREBALL) || e.getClickedInventory().getItem(21).getType().equals(FIREBALL) && e.getClickedInventory().getItem(22).getType().equals(FIREBALL) && e.getClickedInventory().getItem(23).getType().equals(FIREBALL)){


                    Bukkit.broadcastMessage(Data.PREFIX + ChatColor.GOLD + this.p2.getDisplayName() + ChatColor.WHITE + " won against " + ChatColor.GOLD + this.p1.getDisplayName() + ChatColor.WHITE + " in TicTacToe");
                    e.setCancelled(true);
                    this.isrunningttt = false;
                    abortGame();

                }
                //vertical
                if(e.getClickedInventory().getItem(3).getType().equals(FIREBALL) && e.getClickedInventory().getItem(12).getType().equals(FIREBALL) && e.getClickedInventory().getItem(21).getType().equals(FIREBALL) || e.getClickedInventory().getItem(4).getType().equals(FIREBALL) && e.getClickedInventory().getItem(13).getType().equals(FIREBALL) && e.getClickedInventory().getItem(22).getType().equals(FIREBALL) || e.getClickedInventory().getItem(5).getType().equals(FIREBALL) && e.getClickedInventory().getItem(14).getType().equals(FIREBALL) && e.getClickedInventory().getItem(23).getType().equals(FIREBALL)){


                    Bukkit.broadcastMessage(Data.PREFIX + ChatColor.GOLD + this.p2.getDisplayName() + ChatColor.WHITE + " won against " + ChatColor.GOLD + this.p1.getDisplayName() + ChatColor.WHITE + " in TicTacToe");
                    e.setCancelled(true);
                    this.isrunningttt = false;
                    abortGame();
                    this.p1.closeInventory();
                    this.p2.closeInventory();

                }

                //cross
                if(e.getClickedInventory().getItem(3).getType().equals(FIREBALL) && e.getClickedInventory().getItem(13).getType().equals(FIREBALL) && e.getClickedInventory().getItem(23).getType().equals(FIREBALL) || e.getClickedInventory().getItem(5).getType().equals(FIREBALL) && e.getClickedInventory().getItem(13).getType().equals(FIREBALL) && e.getClickedInventory().getItem(21).getType().equals(FIREBALL)){


                    Bukkit.broadcastMessage(Data.PREFIX + ChatColor.GOLD + this.p2.getDisplayName() + ChatColor.WHITE + " won against " + ChatColor.GOLD + this.p1.getDisplayName() + ChatColor.WHITE + " in TicTacToe");
                    e.setCancelled(true);
                    this.isrunningttt = false;
                    abortGame();

                }

            }
        }
    }
}
