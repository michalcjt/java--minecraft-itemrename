package com.gmail.michalc.jt.itemrename;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import net.md_5.bungee.api.ChatColor;

public class Main extends JavaPlugin {
	public String tag, commandOnlyForPlayersMessage, wrongNameArgumentsMessage, wrongLoreArgumentsMessage, successMessage, itemIsNullMessage,
			permissionMessage, newLineChar;
	public static Main Instance;


	@Override
	public void onEnable() {
		Instance = this;

		saveDefaultConfig();
		FileConfiguration config = getConfig();

		tag = replaceColors(config.getString("tag"));
		commandOnlyForPlayersMessage = replaceColors(config.getString("commandOnlyForPlayersMessage"));
		wrongNameArgumentsMessage = replaceColors(config.getString("wrongNameArgumentsMessage"));
		wrongLoreArgumentsMessage = replaceColors(config.getString("wrongLoreArgumentsMessage"));
		successMessage = replaceColors(config.getString("successMessage"));
		itemIsNullMessage = replaceColors(config.getString("itemIsNullMessage"));
		permissionMessage = replaceColors(config.getString("permissionMessage"));
		newLineChar = config.getString("newLineChar");
		
		PluginCommand[] commands = { this.getCommand("rename"), this.getCommand("setlore"),
				this.getCommand("irename") };

		commands[0].setExecutor(new RenameCommandHandler());
		commands[1].setExecutor(new SetLoreCommandHandler());
		commands[2].setExecutor(this);

		for (PluginCommand c : commands) {
			c.setPermissionMessage(permissionMessage);
		}
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(args.length == 0 || args[0].toLowerCase().equals("help"))
		{
			sender.sendMessage(ChatColor.GREEN + "[ ItemRename v" + getDescription().getVersion() + " ]");
			sender.sendMessage("/rename [text] - rename item in hand (" + this.getCommand("rename").getPermission() + ")");
			sender.sendMessage("/setlore [text] - set lore of item in hand (" + this.getCommand("setlore").getPermission() + ")");
			sender.sendMessage("/irename reload - reload config (" + this.getCommand("irename").getPermission() + ")");
		}
		else if (args[0].toLowerCase().equals("reload")) {
			reloadConfig();
			FileConfiguration config = getConfig();

			tag = replaceColors(config.getString("tag"));
			commandOnlyForPlayersMessage = replaceColors(config.getString("commandOnlyForPlayersMessage"));
			wrongNameArgumentsMessage = replaceColors(config.getString("wrongNameArgumentsMessage"));
			wrongLoreArgumentsMessage = replaceColors(config.getString("wrongLoreArgumentsMessage"));
			successMessage = replaceColors(config.getString("successMessage"));
			itemIsNullMessage = replaceColors(config.getString("itemIsNullMessage"));
			permissionMessage = replaceColors(config.getString("permissionMessage"));
			newLineChar = config.getString("newLineChar");

			sender.sendMessage(ChatColor.GREEN + "[ ItemRename v" + getDescription().getVersion()
					+ " ] config reloaded successfully");
		}
		return true;
	}

	public static String replaceColors(String source) {
		return ChatColor.translateAlternateColorCodes('&', source);
	}

}
