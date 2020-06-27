package eu.brickpics.casinoroyale;

import eu.brickpics.casinoroyale.commands.casinoCMD;
import eu.brickpics.casinoroyale.listener.invClickListener;
import eu.brickpics.casinoroyale.listener.leaveListener;
import eu.brickpics.casinoroyale.manager.QueueManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class CasinoRoyale extends JavaPlugin {

    private static CasinoRoyale instance;
    public static CasinoRoyale getInstance() {
        return instance;
    }

    @Override
    public void onEnable(){
        instance = this;

        this.getServer().getPluginManager().registerEvents(new invClickListener(),this);
        this.getServer().getPluginManager().registerEvents(new leaveListener(),this);
        getCommand("casino").setExecutor(new casinoCMD());

    }

    @Override
    public void onDisable(){

        Bukkit.getScheduler().cancelTask(QueueManager.queuerefreshtaskid);
    }
}
