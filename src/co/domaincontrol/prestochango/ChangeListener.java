package co.domaincontrol.prestochango;

import org.bukkit.Bukkit;
import static org.bukkit.Material.getMaterial;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class ChangeListener implements Listener {
    
    public PrestoChango plugin;
    
    public ChangeListener(PrestoChango instance){
	plugin = instance;
	Bukkit.getServer().getPluginManager().registerEvents(this, instance);
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
	if (event.getAction() == Action.RIGHT_CLICK_BLOCK && event.getPlayer().getItemInHand().getType() == getMaterial(plugin.tool) && event.getPlayer().getItemInHand().getItemMeta().getDisplayName().contains("PrestoChango") && event.getPlayer().hasPermission("prestochango.use")) {
	    int blockData = event.getClickedBlock().getData();
	    String blacklist = plugin.getConfig().getString("blacklist").toUpperCase();
	    String[] bl = blacklist.split(",");

	    if (event.getClickedBlock().getType().toString().equalsIgnoreCase("double_plant")) {  //double plants break the server
		return;
	    }

	    for (int i = 0; bl.length > i; i++) {
		if (event.getClickedBlock().getType().toString().equalsIgnoreCase(bl[i])) {
		    return;
		}
	    }

	    try {
		blockData = blockData + 1;
		event.getClickedBlock().setData((byte) blockData);
	    } catch (java.lang.NoClassDefFoundError e) {
		blockData = 0;
		event.getClickedBlock().setData((byte) blockData);
	    }
	}

    }
}
