package com.example.examplemod.commands;

import java.util.ArrayList;
import java.util.List;

import com.example.examplemod.MinecraftListener;

import ibxm.Player;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;

@MinecraftListener(registerInEventBus = false)
public class FlamingPigs implements ICommand {

	private List<String> aliases = new ArrayList<String>();
	private int numberOfPigs = 0;
	
	public FlamingPigs() {
		aliases.add("flamingpigs");
		aliases.add("fp");
	}
	
	@Override
	public String getName() {
		return null;
	}
	
	@Override
	public int compareTo(ICommand o) {
		return 0;
	}

	@Override
	public String getUsage(ICommandSender sender) {
		return "/flamingpigs <number of pigs>";
	}

	@Override
	public List<String> getAliases() {
		return aliases;
	}

	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
		if (args.length != 1) {
			sendErrorMessage(sender, "Invalid number of arguments!");
			return;
		}
		
		try {
			numberOfPigs = Integer.parseInt(args[0]);
		}
		catch (NumberFormatException e) {
			sendErrorMessage(sender, "The argument \"" + args[0] + "\" is not a valid number!");
			return;
		}
		
		EntityPlayer player = (EntityPlayer) sender;
		
		for (int i = 0; i < numberOfPigs; i++) {
			EntityPig pig = new EntityPig(player.world);
			pig.setLocationAndAngles(player.posX, player.posY, player.posZ, 0, 0);
			pig.setFire(10000);
			player.world.spawnEntity(pig);
		}
	}
	
	private void sendErrorMessage(ICommandSender sender, String message) {
		Style red = new Style().setColor(TextFormatting.DARK_RED);
		sender.sendMessage(new TextComponentString(message).setStyle(red));
	}

	@Override
	public boolean checkPermission(MinecraftServer server, ICommandSender sender) {
		return sender instanceof Player;
	}

	@Override
	public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, BlockPos targetPos) {
		return null;
	}

	@Override
	public boolean isUsernameIndex(String[] args, int index) {
		return false;
	}

}
