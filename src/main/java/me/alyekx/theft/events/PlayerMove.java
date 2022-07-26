package me.alyekx.theft.events;

import me.alyekx.theft.Theft;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerMove implements Listener {

    private final Theft plugin;

    public PlayerMove(Theft plugin) {
        this.plugin = plugin;

        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e) {
        if(!Theft.right_clicks.containsKey(e.getPlayer())) {
            return;
        }

        Player moved = e.getPlayer();
        Player moved_from = Theft.right_clicks.get(e.getPlayer());

        if(moved_from.getLocation().distance(moved.getLocation()) >= plugin.getConfig().getInt("Theft.max_distance")) {
            moved_from.closeInventory();
        }
    }
}
