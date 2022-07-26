package me.alyekx.theft.events;

import me.alyekx.theft.Theft;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class PlayerAttack implements Listener {
    public PlayerAttack(Theft plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onPlayerAttack(EntityDamageByEntityEvent e) {
        if(!(e.getDamager() instanceof Player) && !(e.getEntity() instanceof Player)) {
            return;
        }

        Player damager = (Player) e.getDamager();
        Player target = (Player) e.getEntity();

        if(Theft.right_clicks.containsKey(damager) && Theft.right_clicks.get(damager).equals(target)) {
            target.setHealth(0.0);
            Theft.right_clicks.remove(damager, target);
        }

    }
}
