package com.example.examplemod.movement;

import com.example.examplemod.MinecraftListener;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;

@MinecraftListener(registerGameEvent = true)
public class Parachute {
	
	@SubscribeEvent
	public void deployParachute(PlayerTickEvent event) {
		EntityPlayer player = event.player;
		if (!player.isAirBorne || ! player.isSneaking()) {
			return;
		}
		
		player.motionY = -0.05;
	}
	
	@SubscribeEvent
	public void negateFallDamage(LivingFallEvent event) {
		if (!(event.getEntity() instanceof EntityPlayer)) {
			return;
		}
		
		EntityPlayer player = (EntityPlayer) event.getEntity();
		
		if (!player.isSneaking()) {
			return;
		}
		
		event.setCanceled(true);
	}
}
