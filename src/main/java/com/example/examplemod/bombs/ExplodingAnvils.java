package com.example.examplemod.bombs;

import com.example.examplemod.MinecraftListener;

import net.minecraft.entity.Entity;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@MinecraftListener
public class ExplodingAnvils {

	@SubscribeEvent
	public void explode(LivingHurtEvent event) {
		if (event.getSource() != DamageSource.ANVIL) {
			return;
		}
		
		Entity entity = event.getEntity();
		entity.world.createExplosion(entity, entity.posX, entity.posY, entity.posZ, 2, false);
	}
}
