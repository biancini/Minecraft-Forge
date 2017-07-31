package com.example.examplemod.entities;

import java.util.Random;

import com.example.examplemod.MinecraftListener;

import net.minecraft.entity.passive.EntityPig;
import net.minecraft.init.Items;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@MinecraftListener
public class PigsDroppingDiamonds {

	@SubscribeEvent
	public void dopDiamonds(LivingDeathEvent event) {
		if (!(event.getEntity() instanceof EntityPig)) {
			return;
		}
		
		Random random = new Random();
		
		if (!event.getEntity().getEntityWorld().isRemote) {
			event.getEntity().dropItem(Items.DIAMOND, random.nextInt(3));
		}
	}
}
