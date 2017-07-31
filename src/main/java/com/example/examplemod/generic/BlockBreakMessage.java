package com.example.examplemod.generic;

import com.example.examplemod.MinecraftListener;

import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.event.world.BlockEvent.BreakEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@MinecraftListener
public class BlockBreakMessage {
	
	@SubscribeEvent
	public void sendMessage(BreakEvent event) {
		event.getPlayer().sendMessage(new TextComponentString("You broke a bock!"));
	}
}
