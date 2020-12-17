package me.zsergio.sumo.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import me.zsergio.sumo.Main;
import me.zsergio.sumo.SumoManager;

public class Mechanics implements Listener {
	
	private Main plugin = Main.getInstance();
	
	private SumoManager sumoManager = plugin.getSumoManager();
	
	@EventHandler
	public void onMove(PlayerMoveEvent event) {
		Player player = event.getPlayer();
		
		if(sumoManager.getPlayer1() == player) {
			if(sumoManager.getInGame() == true) {
				if(sumoManager.getStarted() == false) {
					event.setCancelled(true);
					player.teleport(sumoManager.getLoc1());
				}
			}
			
		} if(sumoManager.getPlayer2() == player) {
			if(sumoManager.getInGame() == true) {
				if(sumoManager.getStarted() == false) {
					event.setCancelled(true);
					player.teleport(sumoManager.getLoc2());
				}
			}
			
		}
	}
	
	@EventHandler
	public void onHit(EntityDamageEvent event) {
		if(event.getEntity() == sumoManager.getPlayer1() || event.getEntity() == sumoManager.getPlayer2()) {
			if(sumoManager.getStarted() == false) {
				event.setCancelled(true);
			} else 
				sumoManager.getPlayer1().setHealth(20);
				sumoManager.getPlayer1().setFoodLevel(20);
				sumoManager.getPlayer2().setHealth(20);
				sumoManager.getPlayer2().setFoodLevel(20);
		}
	}
	
	@EventHandler
	public void onWalk(EntityDamageEvent event) {
		
		if(event.getEntity() instanceof Player) {
			if(event.getEntity() == sumoManager.getPlayer1() || event.getEntity() == sumoManager.getPlayer2()) {
				Player player = (Player) event.getEntity();
				if(event.getCause() == DamageCause.FIRE || event.getCause() == DamageCause.FIRE) {
					sumoManager.setLoser(player);
					sumoManager.removePlayer(player);
				}
			}
		}
	}
	
	@EventHandler
	public void onBreak(BlockBreakEvent event) {
		if(sumoManager.getPlayer1() == event.getPlayer() || sumoManager.getPlayer2() == event.getPlayer()) {
			if(sumoManager.getInGame() == false) {
				event.setCancelled(true);
				if(sumoManager.getStarted() == true)
					event.setCancelled(true);
				
			}
			
		}
	} 
	
	@EventHandler
	public void onPlace(BlockPlaceEvent event) {
		if(sumoManager.getPlayer1() == event.getPlayer() || sumoManager.getPlayer2() == event.getPlayer()) {
			if(sumoManager.getInGame() == false) {
				event.setCancelled(true);
				if(sumoManager.getStarted() == true)
					event.setCancelled(true);
				
			}
			
		}
	}
	
	@EventHandler
	public void onQuit(PlayerQuitEvent event) {
		
		Player player = event.getPlayer();
		
		if(sumoManager.getPlayer1() == player || sumoManager.getPlayer2() == player) {
			sumoManager.removePlayer(event.getPlayer());
			Bukkit.getScheduler().cancelTasks(plugin);
		}
		
	}
	
	@EventHandler
	public void onCommand(PlayerCommandPreprocessEvent event) {
		Player player = event.getPlayer();
		if(player == sumoManager.getPlayer1() || player == sumoManager.getPlayer2()) {
			if(!event.getMessage().contentEquals("/leave")) {
				player.sendMessage("§cNo puedes hacer esto en juego.");
				event.setCancelled(true);
			}
				} if(event.getMessage().contentEquals("/leave")) {
					sumoManager.leavePlayer(player);
					sumoManager.removePlayer(player);
				}
					
	
			}
	
		
}


