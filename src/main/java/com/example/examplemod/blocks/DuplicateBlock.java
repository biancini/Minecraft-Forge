package com.example.examplemod.blocks;

import com.example.examplemod.MinecraftBlock;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

@MinecraftBlock(blockName="duplicateBlock")
public class DuplicateBlock extends Block {
	public DuplicateBlock() {
		this(Material.IRON);
	}

	public DuplicateBlock(Material materialIn) {
		super(materialIn);
		MinecraftBlock ann = (MinecraftBlock) this.getClass().getAnnotation(MinecraftBlock.class);
		
		this.setUnlocalizedName(ann.blockName());
		this.setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
		this.setResistance(5.0f);
		this.setHardness(10.0f);
		this.setLightLevel(1.0f);
	}
	
	@Override
	public void onBlockAdded(World world, BlockPos pos, IBlockState state) {
		IBlockState block = world.getBlockState(pos.add(0, -1, 0));
		world.setBlockState(pos,  block);
		EntityLightningBolt lightning = new EntityLightningBolt(world, pos.getX(), pos.getY(), pos.getZ(), true);
		world.addWeatherEffect(lightning);
	}

}
