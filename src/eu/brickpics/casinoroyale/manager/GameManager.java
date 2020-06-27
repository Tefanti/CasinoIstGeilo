package eu.brickpics.casinoroyale.manager;

import eu.brickpics.casinoroyale.CasinoRPSGame;
import eu.brickpics.casinoroyale.CasinoRoyale;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

public class GameManager {


    public static ArrayList<CasinoRPSGame> RPSGames = new ArrayList<>();

    public enum GameType{

        ROCKPAPERSCISSORS,
        TICTACTOE,
    }

    public static void startGame(GameType type, Player p1, Player p2){


        switch (type){
            case ROCKPAPERSCISSORS:
                CasinoRPSGame game = new CasinoRPSGame(type, p1, p2, CasinoRoyale.getInstance());
                CasinoRoyale.getInstance().getServer().getPluginManager().registerEvents(game,CasinoRoyale.getInstance());
                RPSGames.add(game);

                break;
            case TICTACTOE:
                break;
        }
        for(CasinoRPSGame game : RPSGames) {
            Bukkit.getLogger().info(game.toString());
        }

    }
    public static void endGame(GameType type, Object o){

        switch (type){
            case ROCKPAPERSCISSORS:
                RPSGames.remove((CasinoRPSGame) o);

                break;
            case TICTACTOE:
                break;
        }
        for(CasinoRPSGame game : RPSGames){
            Bukkit.getLogger().info(game.toString());
        }
    }
}
