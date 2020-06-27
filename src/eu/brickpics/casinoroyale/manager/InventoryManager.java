package eu.brickpics.casinoroyale.manager;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;

import static org.bukkit.Material.*;

public class InventoryManager {

    public static Inventory casinoinv;
    public static Inventory gameinv;

    public static void managecasinoInv(Player p){

        casinoinv = Bukkit.getServer().createInventory(null,6*9, ChatColor.BLUE + "§lCasino");

        casinoinv.setItem(45, new ItemManager(SIGN).setDisplayName(ChatColor.AQUA + "Enter Queue").build());
        casinoinv.setItem(46, new ItemManager(REDSTONE_COMPARATOR).setDisplayName(ChatColor.RED + "Settings").build());



        ArrayList<Player> PLAYER = new ArrayList<>();



        for(Player all: Bukkit.getOnlinePlayers()){

            if(PLAYER.size() < 36) {
                PLAYER.add(all);
            }
        }




        for(int i = 0; i < PLAYER.size(); i++){

            casinoinv.setItem(i, new ItemManager(Material.SKULL_ITEM).setSkullOwner(PLAYER.get(i).getDisplayName()).setData((short) SkullType.PLAYER.ordinal()).setDisplayName(PLAYER.get(i).getDisplayName()).build());

        }

    }


    public static void managegameInv(Player p){

        gameinv = Bukkit.getServer().createInventory(null,1*9, ChatColor.BLUE + "§lGames");
        gameinv.setItem(4, new ItemManager(SHEARS).setDisplayName(ChatColor.AQUA + "RPS").build());
        gameinv.setItem(5, new ItemManager(BARRIER).setDisplayName(ChatColor.AQUA + "TTT").build());


    }
}
