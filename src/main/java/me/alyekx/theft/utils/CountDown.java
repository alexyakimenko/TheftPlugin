package me.alyekx.theft.utils;

import me.alyekx.theft.Theft;
import net.kyori.adventure.text.Component;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class CountDown extends BukkitRunnable {
    private final Theft plugin;
    private final Player clicker;
    private final Player target;
    private int time_to_kill;

    public CountDown(Theft plugin, Player clicker, Player target) {
        this.plugin = plugin;
        this.clicker = clicker;
        this.target = target;
        this.time_to_kill = plugin.getConfig().getInt("Theft.time_to_catch");
    }

    @Override
    public void run() {
        // Timer above inventory
        target.sendActionBar(
            Component.text(
                    Utils.chat("&c" + time_to_kill)
            )
        );
        target.playSound(target.getLocation(),Sound.ENTITY_PLAYER_LEVELUP, 0.1f, 1.0f);

        if(Theft.right_clicks.containsKey(target)) {
            if(!Theft.right_clicks.get(target).equals(clicker)) {
                catchSuccessEvent(clicker, target);
                cancel();
            }
        } else {
            catchSuccessEvent(clicker, target);
            cancel();
        }

        if(time_to_kill <= 0) {
            escapeSuccessEvent(clicker, target);
            Theft.right_clicks.remove(target, clicker);
            cancel();
        }

        time_to_kill--;
    }

    public void catchSuccessEvent(Player clicker, Player target) {
        target.sendMessage(Utils.chat(
            plugin.getConfig().getString("Catch.success_message")
        ));
        target.playSound(target.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 1.0f, 1.0f);
        clicker.sendMessage(Utils.chat(
            plugin.getConfig().getString("Escape.fail_message")
        ));
        clicker.playSound(target.getLocation(), Sound.BLOCK_ANVIL_LAND, 1.0f, 1.0f);
    }

    public void escapeSuccessEvent(Player clicker, Player target) {
        target.sendMessage(Utils.chat(
            plugin.getConfig().getString("Catch.fail_message")
        ));
        target.playSound(target.getLocation(), Sound.BLOCK_ANVIL_LAND, 1.0f, 1.0f);
        clicker.sendMessage(Utils.chat(
            plugin.getConfig().getString("Escape.success_message")
        ));
        clicker.playSound(target.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 1.0f, 1.0f);
    }
}
