package com.example.examplemod.bombs;

import com.example.examplemod.MinecraftListener;

import net.minecraft.init.Blocks;
import net.minecraftforge.event.world.BlockEvent.BreakEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@MinecraftListener
public class DiamondOreTrap {
	
	@SubscribeEvent
	public void explode(BreakEvent event) {
		if (event.getState().getBlock() != Blocks.DIAMOND_ORE) {
			return;
		}
		
		event.getWorld().createExplosion(null, event.getPos().getX(), event.getPos().getY(), event.getPos().getZ(), 10, true);
	}
}
