package eu.brickpics.casinoroyale;

import eu.brickpics.casinoroyale.manager.GameManager;
import eu.brickpics.casinoroyale.manager.ItemManager;
import eu.brickpics.casinoroyale.manager.PlayerManager;
import eu.brickpics.casinoroyale.storage.Data;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import static org.bukkit.Material.*;
import static org.bukkit.Material.BLAZE_ROD;

public class CasinoRPSGame implements Listener {

    Player p1;
    Player p2;
    int rps0choice1 = 0;
    int rps0choice2 = 0;
    boolean isrunning;
    GameManager.GameType type;


    CasinoRoyale plugin;


    public CasinoRPSGame(GameManager.GameType type, Player p1, Player p2, CasinoRoyale plugin) {
        this.p1 = p1;
        this.p2 = p2;
        this.type = type;
        this.plugin = plugin;
        this.isrunning = false;
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

        this.isrunning = false;
        GameManager.endGame(GameManager.GameType.ROCKPAPERSCISSORS,this);

        Bukkit.getLogger().info("RPS Game aborted");
    }

    public void beginGame(){
        PlayerManager.addToList(p1, GameManager.GameType.ROCKPAPERSCISSORS);
        PlayerManager.addToList(p2, GameManager.GameType.ROCKPAPERSCISSORS);

        Inventory rps0inv1 = Bukkit.getServer().createInventory(null, 1 * 9, ChatColor.BLUE + "§lRPS-CHOOSE");
        rps0inv1.setItem(3, new ItemManager(COBBLESTONE).setDisplayName(ChatColor.AQUA + "ROCK").build());
        rps0inv1.setItem(4, new ItemManager(PAPER).setDisplayName(ChatColor.AQUA + "PAPER").build());
        rps0inv1.setItem(5, new ItemManager(SHEARS).setDisplayName(ChatColor.AQUA + "SCISSORS").build());

        this.p1.openInventory(rps0inv1);
        this.p2.openInventory(rps0inv1);
        this.isrunning = true;

        Bukkit.getLogger().info("RPS Game started");

        Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {

            @Override
            public void run() {

                if(isrunning) {
                    p1.sendMessage(Data.PREFIX + ChatColor.WHITE +"Game timed out");
                    p2.sendMessage(Data.PREFIX + ChatColor.WHITE +"Game timed out");
                    abortGame();


                }


            }

        }, 15 * 20L);





    }

    @EventHandler
    public void onInvClick(InventoryClickEvent e) {

        Player p = (Player) e.getWhoClicked();


        if (e.getInventory().getTitle().equalsIgnoreCase(ChatColor.BLUE + "§lRPS") || e.getInventory().getTitle().equalsIgnoreCase(ChatColor.BLUE + "§lRPS-END")) {

            if (e.getCurrentItem() != null) {
                e.setCancelled(true);

            }
        }



        if (e.getInventory().getTitle().equalsIgnoreCase(ChatColor.BLUE + "§lRPS-CHOOSE")) {


            if (e.getCurrentItem().hasItemMeta()) {

                e.setCancelled(true);
                if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.AQUA + "ROCK")) {

                    if (p == this.p1) {
                        rps0choice1 = 1;
                        p.playSound(p.getLocation(), Sound.CLICK, 1.0f, 1.0f);
                        p.closeInventory();
                    }
                    if (p == this.p2) {
                        rps0choice2 = 1;
                        p.playSound(p.getLocation(), Sound.CLICK, 1.0f, 1.0f);
                        p.closeInventory();
                    }

                }
                if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.AQUA + "PAPER")) {

                    if (p == this.p1) {
                        rps0choice1 = 2;
                        p.playSound(p.getLocation(), Sound.CLICK, 1.0f, 1.0f);
                        p.closeInventory();
                    }
                    if (p == this.p2) {
                        rps0choice2 = 2;
                        p.playSound(p.getLocation(), Sound.CLICK, 1.0f, 1.0f);
                        p.closeInventory();
                    }


                }
                if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.AQUA + "SCISSORS")) {

                    if (p == this.p1) {
                        rps0choice1 = 3;
                        p.playSound(p.getLocation(), Sound.CLICK, 1.0f, 1.0f);
                        p.closeInventory();
                    }
                    if (p == this.p2) {
                        rps0choice2 = 3;
                        p.playSound(p.getLocation(), Sound.CLICK, 1.0f, 1.0f);
                        p.closeInventory();
                    }

                }
            }

            if (rps0choice1 != 0 && rps0choice2 != 0) {
                this.isrunning = false;

                Inventory rps0vsinv = Bukkit.getServer().createInventory(null, 3 * 9, ChatColor.BLUE + "§lRPS0");
                rps0vsinv.setItem(4, new ItemManager(BLAZE_ROD).setDisplayName(ChatColor.GOLD + "VS.").build());
                rps0vsinv.setItem(13, new ItemManager(BLAZE_ROD).setDisplayName(ChatColor.GOLD + "VS.").build());
                rps0vsinv.setItem(22, new ItemManager(BLAZE_ROD).setDisplayName(ChatColor.GOLD + "VS.").build());
                rps0vsinv.setItem(11, new ItemManager(Material.SKULL_ITEM).setSkullOwner(this.p1.getDisplayName()).setData((short) SkullType.PLAYER.ordinal()).setDisplayName(this.p1.getDisplayName()).build());
                rps0vsinv.setItem(15, new ItemManager(Material.SKULL_ITEM).setSkullOwner(this.p2.getDisplayName()).setData((short) SkullType.PLAYER.ordinal()).setDisplayName(this.p2.getDisplayName()).build());


                this.p1.openInventory(rps0vsinv);
                this.p2.openInventory(rps0vsinv);

                Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
                    @Override
                    public void run() {
                        p1.playSound(p1.getLocation(), Sound.NOTE_BASS, 1.0f, 1.0f);
                        p2.playSound(p2.getLocation(), Sound.NOTE_BASS, 1.0f, 1.0f);
                    }
                },20L);
                Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
                    @Override
                    public void run() {
                        p1.playSound(p1.getLocation(), Sound.NOTE_BASS, 1.0f, 1.0f);
                        p2.playSound(p2.getLocation(), Sound.NOTE_BASS, 1.0f, 1.0f);
                    }
                },2*20L);
                Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
                    @Override
                    public void run() {
                        p1.playSound(p1.getLocation(), Sound.NOTE_BASS, 1.0f, 1.0f);
                        p2.playSound(p2.getLocation(), Sound.NOTE_BASS, 1.0f, 1.0f);
                    }
                },3*20L);

                Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
                    @Override
                    public void run() {
                        p1.playSound(p1.getLocation(), Sound.NOTE_PLING, 1.0f, 1.0f);
                        p2.playSound(p2.getLocation(), Sound.NOTE_PLING, 1.0f, 1.0f);





                        Inventory rps0endinv = Bukkit.getServer().createInventory(null, 3 * 9, ChatColor.BLUE + "§lRPS-END");



                        if(rps0choice1 == 1){
                            rps0endinv.setItem(11, new ItemManager(COBBLESTONE).setDisplayName(ChatColor.AQUA + "ROCK").build());
                        }
                        if(rps0choice1 == 2){
                            rps0endinv.setItem(11, new ItemManager(PAPER).setDisplayName(ChatColor.AQUA + "PAPER").build());
                        }
                        if(rps0choice1 == 3){
                            rps0endinv.setItem(11, new ItemManager(SHEARS).setDisplayName(ChatColor.AQUA + "SCISSORS").build());
                        }


                        if(rps0choice2 == 1){
                            rps0endinv.setItem(15, new ItemManager(COBBLESTONE).setDisplayName(ChatColor.AQUA + "ROCK").build());
                        }
                        if(rps0choice2 == 2){
                            rps0endinv.setItem(15, new ItemManager(PAPER).setDisplayName(ChatColor.AQUA + "PAPER").build());
                        }
                        if(rps0choice2 == 3){
                            rps0endinv.setItem(15, new ItemManager(SHEARS).setDisplayName(ChatColor.AQUA + "SCISSORS").build());
                        }



                        if (rps0choice1 == rps0choice2) {
                            rps0endinv.setItem(9, new ItemManager(Material.SKULL_ITEM).setSkullOwner(p1.getDisplayName()).setData((short) SkullType.PLAYER.ordinal()).setDisplayName(ChatColor.LIGHT_PURPLE +"Draft").build());
                            rps0endinv.setItem(17, new ItemManager(Material.SKULL_ITEM).setSkullOwner(p2.getDisplayName()).setData((short) SkullType.PLAYER.ordinal()).setDisplayName(ChatColor.LIGHT_PURPLE +"Draft").build());

                            rps0endinv.setItem(0, new ItemManager(STAINED_GLASS_PANE).setData((short)10).build());
                            rps0endinv.setItem(1, new ItemManager(STAINED_GLASS_PANE).setData((short)10).build());
                            rps0endinv.setItem(2, new ItemManager(STAINED_GLASS_PANE).setData((short)10).build());
                            rps0endinv.setItem(3, new ItemManager(STAINED_GLASS_PANE).setData((short)10).build());
                            rps0endinv.setItem(18, new ItemManager(STAINED_GLASS_PANE).setData((short)10).build());
                            rps0endinv.setItem(19, new ItemManager(STAINED_GLASS_PANE).setData((short)10).build());
                            rps0endinv.setItem(20, new ItemManager(STAINED_GLASS_PANE).setData((short)10).build());
                            rps0endinv.setItem(21, new ItemManager(STAINED_GLASS_PANE).setData((short)10).build());

                            rps0endinv.setItem(5, new ItemManager(STAINED_GLASS_PANE).setData((short)10).build());
                            rps0endinv.setItem(6, new ItemManager(STAINED_GLASS_PANE).setData((short)10).build());
                            rps0endinv.setItem(7, new ItemManager(STAINED_GLASS_PANE).setData((short)10).build());
                            rps0endinv.setItem(8, new ItemManager(STAINED_GLASS_PANE).setData((short)10).build());
                            rps0endinv.setItem(23, new ItemManager(STAINED_GLASS_PANE).setData((short)10).build());
                            rps0endinv.setItem(24, new ItemManager(STAINED_GLASS_PANE).setData((short)10).build());
                            rps0endinv.setItem(25, new ItemManager(STAINED_GLASS_PANE).setData((short)10).build());
                            rps0endinv.setItem(26, new ItemManager(STAINED_GLASS_PANE).setData((short)10).build());
                        }



                        if (rps0choice1 == 1 && rps0choice2 == 3 || rps0choice1 == 2 && rps0choice2 == 1 || rps0choice1 == 3 && rps0choice2 == 2) {
                            rps0endinv.setItem(9, new ItemManager(Material.SKULL_ITEM).setSkullOwner(p1.getDisplayName()).setData((short) SkullType.PLAYER.ordinal()).setDisplayName(ChatColor.GREEN +"WINNER").build());
                            rps0endinv.setItem(17, new ItemManager(Material.SKULL_ITEM).setSkullOwner(p2.getDisplayName()).setData((short) SkullType.PLAYER.ordinal()).setDisplayName(ChatColor.RED +"LOSER").build());

                            rps0endinv.setItem(0, new ItemManager(STAINED_GLASS_PANE).setData((short)13).build());
                            rps0endinv.setItem(1, new ItemManager(STAINED_GLASS_PANE).setData((short)13).build());
                            rps0endinv.setItem(2, new ItemManager(STAINED_GLASS_PANE).setData((short)13).build());
                            rps0endinv.setItem(3, new ItemManager(STAINED_GLASS_PANE).setData((short)13).build());
                            rps0endinv.setItem(18, new ItemManager(STAINED_GLASS_PANE).setData((short)13).build());
                            rps0endinv.setItem(19, new ItemManager(STAINED_GLASS_PANE).setData((short)13).build());
                            rps0endinv.setItem(20, new ItemManager(STAINED_GLASS_PANE).setData((short)13).build());
                            rps0endinv.setItem(21, new ItemManager(STAINED_GLASS_PANE).setData((short)13).build());

                            rps0endinv.setItem(5, new ItemManager(STAINED_GLASS_PANE).setData((short)14).build());
                            rps0endinv.setItem(6, new ItemManager(STAINED_GLASS_PANE).setData((short)14).build());
                            rps0endinv.setItem(7, new ItemManager(STAINED_GLASS_PANE).setData((short)14).build());
                            rps0endinv.setItem(8, new ItemManager(STAINED_GLASS_PANE).setData((short)14).build());
                            rps0endinv.setItem(23, new ItemManager(STAINED_GLASS_PANE).setData((short)14).build());
                            rps0endinv.setItem(24, new ItemManager(STAINED_GLASS_PANE).setData((short)14).build());
                            rps0endinv.setItem(25, new ItemManager(STAINED_GLASS_PANE).setData((short)14).build());
                            rps0endinv.setItem(26, new ItemManager(STAINED_GLASS_PANE).setData((short)14).build());
                        }


                        if (rps0choice2 == 1 && rps0choice1 == 3 || rps0choice2 == 2 && rps0choice1 == 1 || rps0choice2 == 3 && rps0choice1 == 2) {
                            rps0endinv.setItem(9, new ItemManager(Material.SKULL_ITEM).setSkullOwner(p1.getDisplayName()).setData((short) SkullType.PLAYER.ordinal()).setDisplayName(ChatColor.RED +"LOSER").build());
                            rps0endinv.setItem(17, new ItemManager(Material.SKULL_ITEM).setSkullOwner(p2.getDisplayName()).setData((short) SkullType.PLAYER.ordinal()).setDisplayName(ChatColor.GREEN +"WINNER").build());

                            rps0endinv.setItem(0, new ItemManager(STAINED_GLASS_PANE).setData((short)14).build());
                            rps0endinv.setItem(1, new ItemManager(STAINED_GLASS_PANE).setData((short)14).build());
                            rps0endinv.setItem(2, new ItemManager(STAINED_GLASS_PANE).setData((short)14).build());
                            rps0endinv.setItem(3, new ItemManager(STAINED_GLASS_PANE).setData((short)14).build());
                            rps0endinv.setItem(18, new ItemManager(STAINED_GLASS_PANE).setData((short)14).build());
                            rps0endinv.setItem(19, new ItemManager(STAINED_GLASS_PANE).setData((short)14).build());
                            rps0endinv.setItem(20, new ItemManager(STAINED_GLASS_PANE).setData((short)14).build());
                            rps0endinv.setItem(21, new ItemManager(STAINED_GLASS_PANE).setData((short)14).build());

                            rps0endinv.setItem(5, new ItemManager(STAINED_GLASS_PANE).setData((short)13).build());
                            rps0endinv.setItem(6, new ItemManager(STAINED_GLASS_PANE).setData((short)13).build());
                            rps0endinv.setItem(7, new ItemManager(STAINED_GLASS_PANE).setData((short)13).build());
                            rps0endinv.setItem(8, new ItemManager(STAINED_GLASS_PANE).setData((short)13).build());
                            rps0endinv.setItem(23, new ItemManager(STAINED_GLASS_PANE).setData((short)13).build());
                            rps0endinv.setItem(24, new ItemManager(STAINED_GLASS_PANE).setData((short)13).build());
                            rps0endinv.setItem(25, new ItemManager(STAINED_GLASS_PANE).setData((short)13).build());
                            rps0endinv.setItem(26, new ItemManager(STAINED_GLASS_PANE).setData((short)13).build());
                        }




                        rps0endinv.setItem(4, new ItemManager(BLAZE_ROD).setDisplayName(ChatColor.GOLD + "VS.").build());
                        rps0endinv.setItem(13, new ItemManager(BLAZE_ROD).setDisplayName(ChatColor.GOLD + "VS.").build());
                        rps0endinv.setItem(22, new ItemManager(BLAZE_ROD).setDisplayName(ChatColor.GOLD + "VS.").build());


                        p1.openInventory(rps0endinv);
                        p2.openInventory(rps0endinv);
                        e.setCancelled(true);










                        for(Player all : Bukkit.getOnlinePlayers()) {
                            if (rps0choice1 == rps0choice2) {
                                all.sendMessage(Data.PREFIX + ChatColor.GOLD + p1.getDisplayName() + ChatColor.WHITE + " played draft against " + ChatColor.GOLD + p2.getDisplayName() + ChatColor.WHITE + " in RPS");
                            }

                            if (rps0choice1 == 1 && rps0choice2 == 3 || rps0choice1 == 2 && rps0choice2 == 1 || rps0choice1 == 3 && rps0choice2 == 2) {
                                all.sendMessage(Data.PREFIX + ChatColor.GOLD + p1.getDisplayName() + ChatColor.WHITE + " won against " + ChatColor.GOLD + p2.getDisplayName() + ChatColor.WHITE + " in RPS");
                            }


                            if (rps0choice2 == 1 && rps0choice1 == 3 || rps0choice2 == 2 && rps0choice1 == 1 || rps0choice2 == 3 && rps0choice1 == 2) {
                                all.sendMessage(Data.PREFIX + ChatColor.GOLD + p2.getDisplayName() + ChatColor.WHITE + " won against " + ChatColor.GOLD + p1.getDisplayName() + ChatColor.WHITE + " in RPS");
                            }

                        }

                        abortGame();

                    }
                },4*20L);
            }

        }
    }


}
