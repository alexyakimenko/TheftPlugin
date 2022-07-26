package me.alyekx.theft;

import me.alyekx.theft.commands.EnderChestCommand;
import me.alyekx.theft.commands.InventoryCommand;
import me.alyekx.theft.events.PlayerMove;
import me.alyekx.theft.events.PlayerRightClick;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;

public final class Theft extends JavaPlugin {
    public static Map<Player, Player> right_clicks = new HashMap<>();

    @Override
    public void onEnable() {
        // Plugin startup logic
        saveDefaultConfig();

        // Listeners
        if(getConfig().getBoolean("Theft.allow")) {
            new PlayerRightClick(this);
            new PlayerMove(this);
        }

        // Commands
        new InventoryCommand(this);
        new EnderChestCommand(this);
    }
}
