package com.gmail.michalc.jt.itemrename;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class SetLoreCommandHandler implements CommandExecutor {
	Main main = Main.Instance;

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (sender instanceof Player) {
			if (args.length != 1) {
				sender.sendMessage(main.tag + main.wrongLoreArgumentsMessage);
				return true;
			}
			Player p = (Player) sender;
			ItemStack item = p.getInventory().getItemInMainHand();
			if (item.getType() != Material.AIR) {
				ItemMeta meta = item.getItemMeta();

				ArrayList<String> lore = new ArrayList<String>();
				String[] lines = Main.replaceColors(args[0].replace("_", " ")).split(main.newLineChar);
				for (String line : lines) {
					lore.add(line);
				}
				meta.setLore(lore);
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
