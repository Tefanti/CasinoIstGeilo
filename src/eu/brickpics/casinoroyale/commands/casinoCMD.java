package eu.brickpics.casinoroyale.commands;

import eu.brickpics.casinoroyale.CasinoRequest;
import eu.brickpics.casinoroyale.manager.GameManager;
import eu.brickpics.casinoroyale.manager.InventoryManager;
import eu.brickpics.casinoroyale.manager.RequestManager;
import eu.brickpics.casinoroyale.storage.Data;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.Objects;

public class casinoCMD implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {


        if (!(sender instanceof Player))
            return false;
        Player p = (Player) sender;

        if (args.length == 0) {
            InventoryManager.managecasinoInv(p);
            p.openInventory(InventoryManager.casinoinv);
        } else if (args.length == 1) {
            if (Bukkit.getPlayer(args[0]) != null) {
                InventoryManager.managerequestInv(p, Bukkit.getPlayer(args[0]));
            }
        } else if (args.length == 2) {
            if (args[0].equals("accept")) {
                p.sendMessage(Data.PREFIX + ChatColor.WHITE + "You accepted the invite from " + ChatColor.GOLD + Objects.requireNonNull(RequestManager.getRequestUsingPlayers(Bukkit.getPlayer(args[1]), p)).requestOrigin.getDisplayName());
                RequestManager.acceptRequest(Objects.requireNonNull(RequestManager.getRequestUsingPlayers(Bukkit.getPlayer(args[1]), p)));

            }

        }


        return false;
    }
}
