package co.domaincontrol.prestochango;

import java.io.File;
import java.util.logging.Level;
import org.bukkit.ChatColor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class PrestoChango extends JavaPlugin implements Listener {

    private File config;
    private final CommandPC cPC = new CommandPC(this);
    public String tool;
    public String broadcastTag = ChatColor.GREEN + "[PrestoChango] " + ChatColor.GRAY;
    public String noPerms = broadcastTag + ChatColor.RED + "You do not have permission to do that!";

    @Override
    public void onEnable() {
	config = new File(getDataFolder(), "config.yml");
	if (!(config.exists())) {
	    getLogger().log(Level.INFO, "Configuration not found! Creating default config.yml");
	    this.saveDefaultConfig();
	}
	load();
	//update.check(); //Future use
	getCommand("pc").setExecutor(new CommandPC(this));
	new ChangeListener(this);
	getLogger().log(Level.INFO, "PrestoChango Loaded!");
    }

    @Override
    public void onDisable() {
	getLogger().log(Level.INFO, "PrestoChango Shut Down.");
    }

    public void load() {
	tool = getConfig().getString("tool").toUpperCase();
    }
}
