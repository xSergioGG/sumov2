package me.zsergio.sumo;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import me.zsergio.sumo.commands.sumoCMD;
import me.zsergio.sumo.listeners.Mechanics;

public class Main extends JavaPlugin {
	
	private static Main plugin;
	
	private static SumoManager sumoManager = new SumoManager();
	
	@SuppressWarnings("static-access")
	@Override
	public void onEnable() {
		plugin = this;
		regConfig();
		getCommand("sumo").setExecutor(new sumoCMD());
		PluginManager pm = Bukkit.getPluginManager();
		pm.registerEvents(new Mechanics(), this);
	}
	
	public static Main getInstance() {
		return plugin;
	}
	
	public SumoManager getSumoManager() {
		return sumoManager;
	}
	
	public void regConfig() {
		File config = new File(this.getDataFolder(), "config.yml");
		
		if(!config.exists()) {
			this.getConfig().options().copyDefaults(true);
		}
		
		saveConfig();
	}
	
	@Override
	public void onDisable() {
		if(!(sumoManager.getPlayer1() == null)) {
			sumoManager.removePlayer(sumoManager.getPlayer1());
		} if(!(sumoManager.getPlayer2() == null)) {
			sumoManager.removePlayer(sumoManager.getPlayer2());
		}
	}

}
