package com.example.examplemod.blocks;

import java.util.Random;

import com.example.examplemod.MinecraftBlock;
import com.example.examplemod.items.EndermanSpawner;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityEnderEye;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentBase;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

@MinecraftBlock(blockName="enderBlock")
public class EnderBlock extends Block {
	
	public EnderBlock() {
		this(Material.IRON);
	}

	public EnderBlock(Material materialIn) {
		super(materialIn);
		MinecraftBlock ann = (MinecraftBlock) this.getClass().getAnnotation(MinecraftBlock.class);
		
		this.setUnlocalizedName(ann.blockName());
		this.setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
		this.setResistance(5.0f);
		this.setHardness(10.0f);
		this.setLightLevel(1.0f);
	}
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (!worldIn.isRemote) {
			return false;
		}
			
		TextComponentBase text = new TextComponentString("You have clicked on the majestic ENDERIUM BLOCK!!!!!!!");
		Style style = new Style();
		style.setColor(TextFormatting.DARK_PURPLE);
		text.setStyle(style);
		playerIn.sendMessage(text);
		EntityEnderEye eye = new EntityEnderEye(worldIn,
				pos.getX() + 0.5,
				pos.getY() + 1.5,
				pos.getZ() + 0.5);
		eye.motionY = 0.1;
		worldIn.spawnEntity(eye);
		
		return true;
	}
	
	public Item getItemDropped(IBlockState state, Random random, int i2) {
		return new EndermanSpawner();
	}
	
	public int quantityDropped(Random random) {
		return random.nextInt(2) + 3;
	}

}
