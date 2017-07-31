package com.example.examplemod.items;

import com.example.examplemod.MinecraftItem;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

@MinecraftItem(itemName="endermanSpawner")
public class EndermanSpawner extends Item {
	public EndermanSpawner() {
		MinecraftItem ann = (MinecraftItem) this.getClass().getAnnotation(MinecraftItem.class);
		
		this.setUnlocalizedName(ann.itemName());
		this.setCreativeTab(CreativeTabs.MATERIALS);
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		EntityEnderman enderman = new EntityEnderman(worldIn);
		enderman.setLocationAndAngles(playerIn.posX, playerIn.posY, playerIn.chasingPosZ, 0, 0);
		worldIn.spawnEntity(enderman);
		
		if (!playerIn.capabilities.isCreativeMode) {
			playerIn.getActiveItemStack().shrink(1);
		}
		
		return new ActionResult<ItemStack>(EnumActionResult.PASS, playerIn.getHeldItem(handIn));
	}
}
