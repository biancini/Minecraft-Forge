package com.example.examplemod.items;

import com.example.examplemod.MinecraftItem;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemFood;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

@MinecraftItem(itemName="enderIngot")
public class EnderIngot extends ItemFood {
	public EnderIngot() {
		super(5,1.0F, true);
		MinecraftItem ann = (MinecraftItem) this.getClass().getAnnotation(MinecraftItem.class);
		this.setPotionEffect(new PotionEffect(Potion.getPotionById(1), 60, 1), 0.5F);
		this.setAlwaysEdible();
		this.setUnlocalizedName(ann.itemName());
		this.setCreativeTab(CreativeTabs.MATERIALS);
	}
}
