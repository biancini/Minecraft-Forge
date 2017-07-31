package com.example.examplemod.bombs;

import com.example.examplemod.MinecraftListener;

import net.minecraft.entity.item.EntityMinecart;
import net.minecraftforge.event.entity.minecart.MinecartCollisionEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@MinecraftListener
public class MinecartExplosion {
	
	@SubscribeEvent
	public void explode(MinecartCollisionEvent event) {
		EntityMinecart minecart = event.getMinecart();
		minecart.world.createExplosion(minecart, minecart.posX, minecart.posY, minecart.posZ, 2, false);
	}
}
