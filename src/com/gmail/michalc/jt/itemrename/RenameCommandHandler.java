package com.gmail.michalc.jt.itemrename;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class RenameCommandHandler implements CommandExecutor {
	Main main = Main.Instance;

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (sender instanceof Player) {
			if (args.length != 1) {
				sender.sendMessage(main.tag + main.wrongNameArgumentsMessage);
				return true;
			}
			Player p = (Player) sender;
			ItemStack item = p.getInventory().getItemInMainHand();
			if (item.getType() != Material.AIR) {
				ItemMeta meta = item.getItemMeta();
				meta.setDisplayName(Main.replaceColors(args[0].replace("_", " ")));
				item.setItemMeta(meta);
				sender.sendMessage(main.tag + main.successMessage);
			} else {
				sender.sendMessage(main.tag + main.itemIsNullMessage);
			}
		} else {
			sender.sendMessage(main.tag + main.commandOnlyForPlayersMessage);
		}
		return true;
	}
}
