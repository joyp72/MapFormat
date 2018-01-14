package com.joi.mapformat.maps;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.scheduler.BukkitRunnable;

import com.joi.mapformat.Main;

import net.md_5.bungee.api.ChatColor;

public class MapListener implements Listener {

	private static MapListener instance;

	static {
		instance = new MapListener();
	}

	public static MapListener get() {
		return instance;
	}

	public void setup() {
		Bukkit.getPluginManager().registerEvents(this, Main.get());
	}

	@EventHandler
	public void onPlayerLeave(PlayerQuitEvent e) {
		Player p = e.getPlayer();
		if (MapManager.get().getMap(p) != null) {
			MapManager.get().getMap(p).kickPlayer(p);
		}
	}

	@EventHandler
	public void onPlayerPlaceBlock(BlockPlaceEvent e) {
		Player p = e.getPlayer();
		Map m = MapManager.get().getMap(p);
		if (m != null) {
			e.setCancelled(true);
		}
	}

	@EventHandler
	public void onPlayerPlaceBlock(BlockBreakEvent e) {
		Player p = e.getPlayer();
		Map m = MapManager.get().getMap(p);
		if (m != null) {
			e.setCancelled(true);
		}
	}

	@EventHandler
	public void onPlayerChat(AsyncPlayerChatEvent e) {
		Map m = MapManager.get().getMap(e.getPlayer());
		String s = e.getMessage().toLowerCase();
		if (m != null) {
			e.setMessage(ChatColor.GRAY + s);
		}
	}
}
