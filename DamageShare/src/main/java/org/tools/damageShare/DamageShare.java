package org.tools.damageShare;

import net.md_5.bungee.api.ChatMessageType;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.plugin.java.JavaPlugin;
import net.md_5.bungee.api.chat.TextComponent;

import java.awt.*;

public final class DamageShare extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @EventHandler
    public void Damaged(EntityDamageEvent Event) {
        if (Event.getCause() != EntityDamageEvent.DamageCause.CUSTOM) {
            Entity entity = Event.getEntity();
            if (entity instanceof Player) {
                if (!((Player) entity).isBlocking()) {
                    for (Player p : Bukkit.getServer().getOnlinePlayers()) {
                        p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacy("누군가 피해를 입고 있습니다..."));
                        if (p.equals(entity)) {
                            continue;
                        }
                        p.damage(Event.getDamage());
                    }
                }
            }
        }
    }
}
