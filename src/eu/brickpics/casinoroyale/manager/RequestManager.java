package eu.brickpics.casinoroyale.manager;

import eu.brickpics.casinoroyale.CasinoRequest;
import eu.brickpics.casinoroyale.storage.Data;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;

import static org.bukkit.Bukkit.getLogger;


public class RequestManager {

    public static ArrayList<CasinoRequest> requests = new ArrayList<>();

    public static void addRequest(Player p1, Player p2, GameManager.GameType type) {

        CasinoRequest request = new CasinoRequest(p1, p2, type);

        if (!containsRequestBy(p1)) {
            requests.add(request);
            return;
        }
        else
            removeRequestsBy(p1);
        addRequest(p1, p2, type);
    }


    public static void acceptRequest(CasinoRequest request) {
        if (!PlayerManager.isPlaying(request.requestOrigin) || !PlayerManager.isPlaying(request.requestDestination))
            request.accept();
        else
            request.requestDestination.sendMessage(Data.PREFIX + "The Request could not be accepted. One of the Players is already playing");
            request.requestOrigin.sendMessage(Data.PREFIX + "The Request could not be accepted. One of the Players is already playing");
    }


    public static CasinoRequest getRequestUsingPlayers(Player p1, Player p2) {
        for (CasinoRequest request : requests) {
            if (request.requestOrigin == p1 && request.requestDestination == p2) {
                return request;
            }
        }
        return null;
    }

    public static CasinoRequest getRequestByOrigin(Player origin) {
        for (CasinoRequest request : requests) {
            if (request.requestOrigin == origin) {
                return request;
            }
        }
        return null;
    }

    public static CasinoRequest getRequestUsingParams(Player p1, Player p2, GameManager.GameType t) {
        for (CasinoRequest request : requests) {
            if (request.requestOrigin == p1 && request.requestDestination == p2 && request.type == t) {
                return request;
            }
        }
        return null;
    }






    public static void removeRequestUsingParams(Player p1, Player p2, GameManager.GameType t) {

    }








    public static void removeAllRequests() { requests.clear(); }

    public static void removeRequestsBy(Player p) { requests.removeIf(request -> request.requestOrigin == p); }

    public static void removeRequestsTo(Player p) { requests.removeIf(request -> request.requestDestination == p); }

    public static void removeRequestsOf(GameManager.GameType t) { requests.removeIf(request -> request.type == t); }

    public static boolean containsRequestBy(Player p) {
        for (CasinoRequest request : requests)
            if (request.requestOrigin == p)
                return true;
        return false;
    }

    public static boolean containsRequestTo(Player p) {
        for (CasinoRequest request : requests)
            if (request.requestDestination == p)
                return true;
        return false;
    }

    public static boolean containsRequestOf(GameManager.GameType t) {
        for (CasinoRequest request : requests)
            if (request.type == t)
                return true;
        return false;
    }
}
