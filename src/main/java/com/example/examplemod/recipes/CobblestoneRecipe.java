package com.example.examplemod.recipes;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class CobblestoneRecipe implements IMinecraftRecipe {

	@Override
	public void addRecipe() {
		GameRegistry.addRecipe(
				new ItemStack(Blocks.COBBLESTONE),
				"dd",
				"dd",
				'd', Blocks.DIRT);
	}
	
}
