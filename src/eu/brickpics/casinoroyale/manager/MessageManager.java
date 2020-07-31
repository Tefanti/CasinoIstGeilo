package eu.brickpics.casinoroyale.manager;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.Locale;
import java.util.ResourceBundle;


public class MessageManager {

    ResourceBundle meldunge_de = ResourceBundle.getBundle("messages", Locale.GERMAN);
    ResourceBundle meldunge_en = ResourceBundle.getBundle("messages", Locale.ENGLISH);

    public static Locale intToLocale(int i) {
        Locale result = null;

        switch (i) {
            case 0:
                result = new Locale("en");
                break;
            case 1:
                result = new Locale("de");
                break;
            default:
                Bukkit.getLogger().warning("public static Locale intToLocale(int i) wurde mit i = " + i + " aufgerufen. i existiert nicht");
        }

        return result;
    }




}
