package eu.brickpics.casinoroyale.commands;

import eu.brickpics.casinoroyale.manager.InventoryManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class casinoCMD implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] strings) {


        if (!(sender instanceof Player))
            return false;
        Player p = (Player) sender;

        InventoryManager.managecasinoInv(p);
        p.openInventory(InventoryManager.casinoinv);





        return false;
    }
}
