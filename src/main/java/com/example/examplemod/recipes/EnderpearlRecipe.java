package com.example.examplemod.recipes;

import com.example.examplemod.ExampleMod;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class EnderpearlRecipe implements IMinecraftRecipe {

	@Override
	public void addRecipe() {
		GameRegistry.addSmelting(
				Items.ENDER_PEARL,
				new ItemStack(ExampleMod.MYITEMS.get("enderIngot")),
				1.0F);
	}
	
}
