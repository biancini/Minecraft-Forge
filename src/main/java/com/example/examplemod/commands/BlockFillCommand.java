package com.example.examplemod.commands;

import java.util.ArrayList;
import java.util.List;

import com.example.examplemod.MinecraftListener;
import com.example.examplemod.bombs.BlockFillerPositionSelector;

import net.minecraft.block.Block;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;

@MinecraftListener(registerInEventBus = false)
public class BlockFillCommand implements ICommand {

	private List<String> aliases = new ArrayList<String>();
	private Block block;
	
	public BlockFillCommand() {
		aliases.add("fillblocks");
		aliases.add("fb");
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
		return "/fillblocks <blockID>";
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
			block = Block.getBlockById(Integer.parseInt(args[0]));
			
			if (Blocks.AIR.equals(block) && Integer.parseInt(args[0]) != 0) {
				sendErrorMessage(sender, "The argument\"" + args[0] + "\" is not a valid block ID!");
				return;
			}
		}
		catch (NumberFormatException e) {
			if (Block.getBlockFromName(args[0]) == null) {
				sendErrorMessage(sender, "The argument\"" + args[0] + "\" is not a valid block name!");
				return;
			}
			else {
				block = Block.getBlockFromName(args[0]);
			}
		}
		
		if (BlockFillerPositionSelector.pos1.isEmpty() || BlockFillerPositionSelector.pos2.isEmpty()) {
			sendErrorMessage(sender, "Make a region selection first!");
			return;
		}
		
		if (BlockFillerPositionSelector.pos1.get(0) > BlockFillerPositionSelector.pos2.get(0)) {
			swapPositions(0);
		}
		
		if (BlockFillerPositionSelector.pos1.get(1) > BlockFillerPositionSelector.pos2.get(1)) {
			swapPositions(1);
		}
		
		if (BlockFillerPositionSelector.pos1.get(2) > BlockFillerPositionSelector.pos2.get(2)) {
			swapPositions(2);
		}
		
		for (int x = BlockFillerPositionSelector.pos1.get(0);
				x <= BlockFillerPositionSelector.pos2.get(0);
				++x) {
			for (int y = BlockFillerPositionSelector.pos1.get(1);
					y <= BlockFillerPositionSelector.pos2.get(1);
					++y) {
				for (int z = BlockFillerPositionSelector.pos1.get(2);
						z <= BlockFillerPositionSelector.pos2.get(2);
						++z) {
					
					((EntityPlayer) sender).getEntityWorld().setBlockState(new BlockPos(x, y, z), block.getBlockState().getBaseState());
				}
			}
		}
	}
	
	private void swapPositions(int index) {
		int temp = BlockFillerPositionSelector.pos1.get(index);
		BlockFillerPositionSelector.pos1.set(index, BlockFillerPositionSelector.pos2.get(index));
		BlockFillerPositionSelector.pos2.set(index, temp);
	}
	
	private void sendErrorMessage(ICommandSender sender, String message) {
		Style red = new Style().setColor(TextFormatting.DARK_RED);
		sender.sendMessage(new TextComponentString(message).setStyle(red));
	}

	@Override
	public boolean checkPermission(MinecraftServer server, ICommandSender sender) {
		return true;
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
