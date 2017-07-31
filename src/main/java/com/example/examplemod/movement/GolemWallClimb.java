package com.example.examplemod.movement;

import com.example.examplemod.MinecraftListener;

import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.monster.EntitySnowman;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@MinecraftListener
public class GolemWallClimb {

	@SubscribeEvent
	public void climbWall(LivingUpdateEvent event) {
		if (!(event.getEntity() instanceof EntitySnowman) && !(event.getEntity() instanceof EntityIronGolem)) {
			return;
		}
	
		if (!event.getEntity().isCollidedHorizontally) {
			return;
		}
		
		event.getEntity().motionY = 0.5;
	}
}
