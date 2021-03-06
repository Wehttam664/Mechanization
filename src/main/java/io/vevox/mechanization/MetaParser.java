package io.vevox.mechanization;

import com.google.gson.JsonObject;
import org.bukkit.ChatColor;

import java.io.Serializable;

/**
 * @author Matthew Struble
 */
public class MetaParser implements Serializable {

    public final String name, description;

    public MetaParser(JsonObject meta) throws NullPointerException, ClassCastException, IllegalStateException {
        name = meta.get("name").getAsString();
        description = meta.get("description").getAsString();
    }

    public String strippedName() {
        return ChatColor.stripColor(coloredName());
    }

    public String coloredName(){
        return ChatColor.translateAlternateColorCodes('&', name);
    }

}
