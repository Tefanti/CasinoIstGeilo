package eu.brickpics.casinoroyale.commands;

import eu.brickpics.casinoroyale.manager.GameManager;
import eu.brickpics.casinoroyale.manager.InventoryManager;
import eu.brickpics.casinoroyale.manager.RequestManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.Objects;

public class casinoCMD implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] strings) {


        if (!(sender instanceof Player))
            return false;
        Player p = (Player) sender;

        if (strings.length == 0) {
            InventoryManager.managecasinoInv(p);
            p.openInventory(InventoryManager.casinoinv);
        } else if (strings.length == 1) {
            if (Bukkit.getPlayer(strings[0]) != null) {
                InventoryManager.managerequestInv(p, Bukkit.getPlayer(strings[0]));
            }
        } else if (strings.length == 2) {
            if (strings[0].equals("accept")) {
                RequestManager.acceptRequest(Objects.requireNonNull(RequestManager.getRequestUsingPlayers(Bukkit.getPlayer(strings[1]), p)));
            }

        }


        return false;
    }
}
