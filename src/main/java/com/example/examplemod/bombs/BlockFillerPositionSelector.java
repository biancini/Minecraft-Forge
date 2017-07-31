package com.example.examplemod.bombs;

import java.util.ArrayList;
import java.util.List;

import com.example.examplemod.MinecraftListener;

import net.minecraft.init.Items;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.LeftClickBlock;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.RightClickBlock;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@MinecraftListener
public class BlockFillerPositionSelector {
	
	public static List<Integer> pos1 = new ArrayList<Integer>();
	public static List<Integer> pos2 = new ArrayList<Integer>();
	
	@SubscribeEvent
	public void choosePositions(PlayerInteractEvent event) {
		if (event.getEntityPlayer().getHeldItemMainhand() == null ||
				!event.getEntityPlayer().getHeldItemMainhand().getItem().equals(Items.WOODEN_PICKAXE) ||
				!event.getEntityPlayer().capabilities.isCreativeMode) {
			return;
		}
	
		if (event instanceof RightClickBlock) {
			event.getEntityPlayer().sendMessage(new TextComponentString("right"));
			pos1.clear();
			pos1.add(event.getPos().getX());
			pos1.add(event.getPos().getY());
			pos1.add(event.getPos().getZ());
			event.getEntityPlayer().sendMessage(new TextComponentString("Position 1 set to " + 
					event.getPos().getX() + ", " + 
					event.getPos().getY() + ", " + 
					event.getPos().getZ() + "."));
			event.setCanceled(true);
		}
		else if (event instanceof LeftClickBlock) {
			event.getEntityPlayer().sendMessage(new TextComponentString("left"));
			pos2.clear();
			pos2.add(event.getPos().getX());
			pos2.add(event.getPos().getY());
			pos2.add(event.getPos().getZ());
			event.getEntityPlayer().sendMessage(new TextComponentString("Position 2 set to " + 
					event.getPos().getX() + ", " + 
					event.getPos().getY() + ", " + 
					event.getPos().getZ() + "."));
			event.setCanceled(true);
		}
		
	}
}
