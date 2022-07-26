package me.alyekx.theft.events;

import me.alyekx.theft.Theft;
import me.alyekx.theft.utils.Utils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.EquipmentSlot;

public class PlayerRightClick implements Listener {

    private final Theft plugin;

    public PlayerRightClick(Theft plugin) {
        this.plugin = plugin;

        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onPlayerRightClick(PlayerInteractEntityEvent e) {
        if(!e.getHand().equals(EquipmentSlot.HAND)) {
            return;
        }

        if(!(e.getRightClicked() instanceof Player)) {
            return;
        }

        Player clicker = e.getPlayer();
        Player target = (Player) e.getRightClicked();

        Theft.right_clicks.put(target, clicker);

        clicker.openInventory(
                target.getInventory()
        );

        target.sendMessage(Utils.chat(
                plugin.getConfig().getString("danger_message")
        ));
    }
}
