package com.example.examplemod.recipes;

import com.example.examplemod.ExampleMod;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class EnderblockRecipe implements IMinecraftRecipe {

	@Override
	public void addRecipe() {
		GameRegistry.addRecipe(
				new ItemStack(ExampleMod.MYBLOCKS.get("enderBlock")),
				"iii",
				"iii",
				"iii",
				'i', ExampleMod.MYITEMS.get("enderIngot"));
		
		GameRegistry.addRecipe(
				new ItemStack(ExampleMod.MYBLOCKS.get("enderBlock")),
				"e e",
				" o ",
				"e e",
				'o', Blocks.OBSIDIAN,
				'e', Items.ENDER_EYE);
		
	}
	
}
