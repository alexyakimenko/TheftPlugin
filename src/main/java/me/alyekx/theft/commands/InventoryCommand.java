package me.alyekx.theft.commands;

import me.alyekx.theft.Theft;
import me.alyekx.theft.utils.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class InventoryCommand implements CommandExecutor {
    private final Theft plugin;

    public InventoryCommand(Theft plugin) {
        this.plugin = plugin;

        Objects.requireNonNull(
                plugin.getCommand("inventory")
        ).setExecutor(this)
        ;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(!(sender instanceof Player p)) {
            sender.sendMessage(Utils.chat(
                    plugin.getConfig().getString("players_only_message")
            ));
            return true;
        }

        if(!p.hasPermission("theft.inventory")) {
            p.sendMessage(Utils.chat(
                    plugin.getConfig().getString("no_permission_message")
            ));
            return true;
        }

        if(args.length == 0) {
            p.sendMessage(Utils.chat(
                    plugin.getConfig().getString("no_arguments_message")
            ));
            return false;
        }

        if(plugin.getServer().getPlayer(args[0]) == null) {
            p.sendMessage(Utils.chat(
                    plugin.getConfig().getString("invalid_argument_message")
            ));
            return false;
        }

        Player target = plugin.getServer().getPlayer(args[0]);

        assert target != null;
        p.openInventory(target.getInventory());

        return true;
    }
}
