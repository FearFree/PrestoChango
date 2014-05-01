package co.domaincontrol.prestochango;

import static org.bukkit.Bukkit.getLogger;
import static org.bukkit.Material.getMaterial;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class CommandPC implements CommandExecutor {
    
    private final PrestoChango plugin;
     
    public CommandPC(PrestoChango plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender cs, Command command, String string, String[] args) {
	if (command.getName().equalsIgnoreCase("pc")) {
	    if (args.length == 0 && cs.hasPermission("prestochango.use")) {
		if (!(cs instanceof Player)) {
		    getLogger().warning("This command can't be issued by the console!");
		    return false;
		}
		String tool = plugin.tool;
		Player player = (Player) cs;
		ItemStack is = new ItemStack(getMaterial(tool));
		ItemMeta itemMeta = is.getItemMeta();
		itemMeta.setDisplayName("PrestoChango");
		is.setItemMeta(itemMeta);
		player.getInventory().addItem(is);
		player.sendMessage(plugin.broadcastTag + "PrestoChango tool given!");
		return true;

	    } else if (args[0].equalsIgnoreCase("reload") && cs.hasPermission("prestochango.reload")) {
		plugin.reloadConfig();
		plugin.load();
		cs.sendMessage(plugin.broadcastTag + "Configuration reloaded");
		return true;
	    }

	}
	return false;
    }

}
