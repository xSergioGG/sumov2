package me.zsergio.sumo.commands;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.zsergio.sumo.Main;
import me.zsergio.sumo.SumoManager;

public class sumoCMD implements CommandExecutor {

	private static Main plugin = Main.getInstance();
	private SumoManager sumoManager = plugin.getSumoManager();
	
	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender.hasPermission("vairex.sumo")) {
			Player player = (Player) sender;
			if(args[0].equalsIgnoreCase("setpos1")) {
				Location loc = new Location(player.getWorld(), player.getLocation().getX(), 
						player.getLocation().getY(), player.getLocation().getZ());
				sumoManager.setLoc1(loc, player.getEyeLocation().getPitch(), player.getEyeLocation().getYaw());
				player.sendMessage("§aPosición 1 establecida.");
				
			}
			if(args[0].equalsIgnoreCase("setpos2")) {
				Location loc = new Location(player.getWorld(), player.getLocation().getX(), 
						player.getLocation().getY(), player.getLocation().getZ());
				sumoManager.setLoc2(loc, player.getEyeLocation().getPitch(), player.getEyeLocation().getYaw());
				player.sendMessage("§aPosición 2 establecida.");
				
			}
			if(args[0].equalsIgnoreCase("join")) {
				sumoManager.addPlayer(player);
					
					if(player == sumoManager.getPlayer2()) {
						
						 
						Bukkit.getScheduler().scheduleAsyncRepeatingTask(plugin, new Runnable() {
							
							@Override
							public void run() {
								if(sumoManager.getCountdown() >= 1) {
									sumoManager.getPlayer1().sendMessage("§7El Sumo iniciara en §e"+sumoManager.getCountdown()+" Segundos...");
									sumoManager.getPlayer1().playSound(sumoManager.getPlayer1().getLocation(), Sound.LEVEL_UP, 100, 100);
									sumoManager.getPlayer2().sendMessage("§7El Sumo iniciara en §e"+sumoManager.getCountdown()+" Segundos...");
									sumoManager.getPlayer2().playSound(sumoManager.getPlayer2().getLocation(), Sound.LEVEL_UP, 100, 100);
									sumoManager.setCountdown(sumoManager.getCountdown()-1);
									sumoManager.setIngame(true);
									sumoManager.setStarted(false);
								if(sumoManager.getTLoc1() == false || sumoManager.getTLoc2() == false) {
									sumoManager.removePlayer(player);
									Bukkit.getScheduler().cancelTasks(plugin);
									
								} if(sumoManager.getCountdown() == 0) {
									
									sumoManager.setCountdown(sumoManager.getCountdown()-1);
									

									} if(sumoManager.getCountdown() == -1) {
										sumoManager.getPlayer1().sendMessage("§7El sumo ha comenzado.");
										sumoManager.getPlayer1().playSound(sumoManager.getPlayer1().getLocation(), Sound.EXPLODE, 100, 100);
										sumoManager.getPlayer2().sendMessage("§7El sumo ha comenzado.");
										sumoManager.getPlayer2().playSound(sumoManager.getPlayer2().getLocation(), Sound.EXPLODE, 100, 100);
										sumoManager.getPlayer1().teleport(sumoManager.getLoc1());
										sumoManager.getPlayer2().teleport(sumoManager.getLoc2());
										sumoManager.setIngame(false);
										sumoManager.setStarted(true);
										Bukkit.getScheduler().cancelTasks(plugin);

										}
								}
							}
						}, 0L, 20L);
					}
				}

			}
		return true;
	}


}
