package me.alyekx.theft.events;

import me.alyekx.theft.Theft;
import me.alyekx.theft.utils.CountDown;
import me.alyekx.theft.utils.Utils;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.EquipmentSlot;

import java.util.Map;

public class PlayerRightClick implements Listener {

    private final Theft plugin;

    public PlayerRightClick(Theft plugin) {
        this.plugin = plugin;

        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onPlayerRightClick(PlayerInteractEntityEvent e) {
        // Prevent double call
        if(!e.getHand().equals(EquipmentSlot.HAND)) {
            return;
        }

        if(!(e.getRightClicked() instanceof Player)) {
            return;
        }

        Player clicker = e.getPlayer();
        Player target = (Player) e.getRightClicked();

        target.playSound(target.getLocation(), Sound.EVENT_RAID_HORN,1.0f, 2.0f);

        if(Theft.right_clicks.containsKey(target) || Theft.right_clicks.get(target).equals(clicker)) {
            Theft.countdowns.get(
                    Map.of(target, clicker)
            ).cancel();
        } else {
            Theft.right_clicks.put(target, clicker);
        }

        CountDown countDown = new CountDown(plugin, clicker, target);

        Theft.countdowns.put(
            Map.of(target, clicker),
            countDown
        );

        countDown.runTaskTimer(plugin, 0L, 20L);


        clicker.openInventory(
                target.getInventory()
        );

        target.sendMessage(Utils.chat(
                plugin.getConfig().getString("danger_message")
        ));

    }
}
