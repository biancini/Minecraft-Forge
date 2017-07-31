package com.example.examplemod.recipes;

import com.example.examplemod.ExampleMod;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class EnderingotRecipe implements IMinecraftRecipe {

	@Override
	public void addRecipe() {
		GameRegistry.addShapelessRecipe(
				new ItemStack(ExampleMod.MYITEMS.get("enderIngot"), 9),
				new ItemStack(ExampleMod.MYBLOCKS.get("enderBlock")));
		
		GameRegistry.addShapelessRecipe(
				new ItemStack(ExampleMod.MYITEMS.get("enderIngot"), 12),
				new ItemStack(ExampleMod.MYBLOCKS.get("enderBlock")),
				new ItemStack(Items.IRON_INGOT),
				new ItemStack(Items.IRON_INGOT));
	}
	
}
