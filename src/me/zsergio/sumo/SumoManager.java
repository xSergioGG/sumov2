package me.zsergio.sumo;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class SumoManager {
	
	private Main plugin = Main.getInstance();
	
	private boolean ingame;
	private boolean started;
	
	private int backcountdown = 3;
	private int countdown = 3;
	
	private Location loc1 = new Location(Bukkit.getWorld("spawn"), 24.550, 21, -58.532);
	private Location loc2 = new Location(Bukkit.getWorld("spawn"), 24.518, 21, -50.588);
	
	private Location spawn = new Location(Bukkit.getWorld("spawn"), 24.463, 22, -34.653);
	
	private boolean tloc1;
	private boolean tloc2;
	
	private Player player1;
	private Player player2;
	
	private Player loser;
	
	public SumoManager() {
		loc2.setPitch((float) 0);
		loc2.setYaw((float) 179.7);
		loc1.setPitch((float) 0);
		loc1.setYaw((float) 0.4);
	}
	
	public boolean setStarted(boolean x) {
		if(x == true) {
			return started = true;
		} else {
			return started = false;
		}
	}
	
	public boolean getStarted() {
		return started;
	}
	
	public void setLoser(Player loser) {
		this.loser = loser;
	}
	
	public Player getLoser() {
		return loser;
	}
	
	public int getCountdown() {
		return countdown;
	}
	
	public void setSpawn(Location loc, float pitch, float yaw) {
		this.spawn = loc;
	}
	
	public Location getSpawn() {
		return spawn;
	}
	
	public void setCountdown(int countdown) {
		this.countdown = countdown;
	}
	
	public void setLoc1(Location loc, float pitch, float yaw) {
		this.loc1 = loc;
	}
	
	public void setLoc2(Location loc, float pitch, float yaw) {
		this.loc2 = loc;
	}
	
	public Location getLoc1() {
		return loc1;
	}
	
	public Location getLoc2() {
		return loc2;
	}
	
	public void addPlayer(Player player) {
		if(player1 == null) {
			setPlayer1(player);
			setTloc1(true);
			player.teleport(getLoc1());
			player.sendMessage("§7Esperando un Oponente...");
			player.sendMessage("§cPara salir usa /leave");
			player.setAllowFlight(false);
			player.setGameMode(GameMode.SURVIVAL);
			setIngame(true);
		} else if(player2 == null) {
			setPlayer2(player);
			setTloc2(true);
			player.teleport(getLoc2());
			player.setAllowFlight(false);
			player.setGameMode(GameMode.SURVIVAL);
		} else {
			player.sendMessage("§cSumo lleno.");
		}
	}
	
	@SuppressWarnings("deprecation")
	public void leavePlayer(Player player) {
		if(getPlayer2() == null) {
			if(getPlayer1() == player) {
				getPlayer1().teleport(spawn);
				getPlayer1().sendMessage("§7El sumo ha terminado.");
				getPlayer1().setHealth(20);
				getPlayer1().setFireTicks(0);
				setPlayer1(null);
				setTloc1(false);
				player.sendTitle("§c§l¡HAS SALIDO!", null);
			}
		}
	}
	
	@SuppressWarnings("deprecation")
	public void removePlayer(Player player) {
		if(player == player1 || player == player2) {
				
			if(player == getPlayer1()) {
				getPlayer1().sendTitle("§c§l¡HAS PERDIDO!", null);
				getPlayer2().sendTitle("§a§l¡HAS GANADO!", null);
			} else {
				getPlayer2().sendTitle("§c§l¡HAS PERDIDO!", null);
				getPlayer1().sendTitle("§a§l¡HAS GANADO!", null);
			}
			player.sendMessage("§cHas salido del Sumo.");
			if(!(getPlayer1() == null)) {
				getPlayer1().teleport(spawn);
				getPlayer1().sendMessage("§7El sumo ha terminado.");
				getPlayer1().setHealth(20);
				getPlayer1().setFireTicks(0);
				setPlayer1(null);
				setTloc1(false);
			}
			if(!(getPlayer2() == null)) {
				getPlayer2().teleport(spawn);
				getPlayer2().sendMessage("§7El sumo ha terminado.");
				getPlayer2().setHealth(20);
				getPlayer2().setFireTicks(0);
				setPlayer2(null);
				setTloc2(false);
			}

			setLoser(null);
			setIngame(false);
			setStarted(false);
			setCountdown(backcountdown);
			Bukkit.getScheduler().cancelTasks(plugin);
			
		}
	}
	
	public void setIngame(boolean ingame) {
		this.ingame = ingame;
	}
	
	public boolean getInGame() {
		if(ingame == true) {
			return true;
		} else {
			return false;
		}
	}
	public Player getPlayer1() {
		return player1;
	}
	
	public Player getPlayer2() {
		return player2;
	}
	
	public void setPlayer1(Player player1) {
		this.player1 = player1;
	}
	
	public void setPlayer2(Player player2) {
		this.player2 = player2;
	}
	
	
	public boolean getTLoc1() {
		if (tloc1 == true) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean getTLoc2() {
		if (tloc2 == true) {
			return true;
		} else {
			return false;
		}
	}
	
	public void setTloc1(boolean tloc1) {
		this.tloc1 = tloc1;
	}

	public void setTloc2(boolean tloc2) {
		this.tloc2 = tloc2;
	}
	
}
