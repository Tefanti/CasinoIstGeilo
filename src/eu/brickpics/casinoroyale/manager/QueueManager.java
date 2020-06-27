package eu.brickpics.casinoroyale.manager;

import eu.brickpics.casinoroyale.CasinoRPSGame;
import eu.brickpics.casinoroyale.CasinoRoyale;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class QueueManager {
    public static ArrayList<Player> RPSQueue = new ArrayList<>();
    public static ArrayList<Player> TTTQueue = new ArrayList<>();

    public static void addPlayer(GameManager.GameType type, Player p){

        switch (type){
            case ROCKPAPERSCISSORS:
                if(!RPSQueue.contains(p)) {
                    RPSQueue.add(p);
                }
                break;
            case TICTACTOE:
                if(!TTTQueue.contains(p)) {
                    TTTQueue.add(p);
                }
                break;
        }
    }
    public static void removePlayer(GameManager.GameType type, Player p){

        switch (type){
            case ROCKPAPERSCISSORS:
                if(RPSQueue.contains(p)){
                    RPSQueue.remove(p);
                }
                break;
            case TICTACTOE:
                if(TTTQueue.contains(p)){
                    TTTQueue.remove(p);
                }
                break;
        }
    }
    public static int queuerefreshtaskid = Bukkit.getScheduler().scheduleSyncRepeatingTask(CasinoRoyale.getInstance(), new Runnable() {
        @Override
        public void run() {
            if(RPSQueue.size() > 1){

                GameManager.startGame(GameManager.GameType.ROCKPAPERSCISSORS,RPSQueue.get(0),RPSQueue.get(1));
                removePlayer(GameManager.GameType.ROCKPAPERSCISSORS,RPSQueue.get(0));
                removePlayer(GameManager.GameType.ROCKPAPERSCISSORS,RPSQueue.get(0));
            }
            if(TTTQueue.size() > 1){

                GameManager.startGame(GameManager.GameType.TICTACTOE,TTTQueue.get(0),TTTQueue.get(1));
                removePlayer(GameManager.GameType.TICTACTOE,TTTQueue.get(0));
                removePlayer(GameManager.GameType.TICTACTOE,TTTQueue.get(0));
            }
        }
    },0L,2L);



}
