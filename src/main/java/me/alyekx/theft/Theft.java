package me.alyekx.theft;

import me.alyekx.theft.commands.EnderChestCommand;
import me.alyekx.theft.commands.InventoryCommand;
import org.bukkit.plugin.java.JavaPlugin;

public final class Theft extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        saveDefaultConfig();

        // Commands
        new InventoryCommand(this);
        new EnderChestCommand(this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
