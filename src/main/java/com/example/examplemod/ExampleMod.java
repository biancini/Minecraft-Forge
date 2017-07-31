package com.example.examplemod;

import java.util.Arrays;
import java.util.HashMap;

import org.reflections.Reflections;

import com.example.examplemod.recipes.IMinecraftRecipe;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.command.ICommand;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod(modid = ExampleMod.MODID, version = ExampleMod.VERSION, name = ExampleMod.NAME)
public class ExampleMod {
	public static final String MODID = "examplemod";
	public static final String VERSION = "1.0";
	public static final String NAME = "Mod di esempio creato per capire come funziona Forge";

	public static final HashMap<String, Block> MYBLOCKS = new HashMap<String, Block>();
	public static final HashMap<String, Item> MYITEMS = new HashMap<String, Item>();

	@EventHandler
	public void init(FMLInitializationEvent event) throws InstantiationException, IllegalAccessException {
		registerBlocks();
		registerItems();
		registerEventHandler();
		registerRecipes();
	}

	@EventHandler
	public void registerCommands(FMLServerStartingEvent event)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		Reflections ref = new Reflections(this.getClass().getPackage().getName());
		for (Class<?> cl : ref.getTypesAnnotatedWith(MinecraftListener.class)) {
			if (Arrays.asList(cl.getInterfaces()).contains(ICommand.class)) {
				event.registerServerCommand((ICommand) cl.newInstance());
			}
		}
	}

	private void registerRecipes() throws InstantiationException, IllegalAccessException {
		Reflections ref = new Reflections(this.getClass().getPackage().getName());

		for (Class<?> cl : ref.getSubTypesOf(IMinecraftRecipe.class)) {
			IMinecraftRecipe recipe = (IMinecraftRecipe) cl.newInstance();
			recipe.addRecipe();
		}
	}

	private void registerEventHandler() throws InstantiationException, IllegalAccessException {
		Reflections ref = new Reflections(this.getClass().getPackage().getName());

		for (Class<?> cl : ref.getTypesAnnotatedWith(MinecraftListener.class)) {
			MinecraftListener ann = (MinecraftListener) cl.getAnnotation(MinecraftListener.class);
			if (ann == null)
				continue;

			if (ann.registerInEventBus() || ann.registerGameEvent()) {
				MinecraftForge.EVENT_BUS.register(cl.newInstance());
			}
		}
	}

	private void registerBlocks() throws InstantiationException, IllegalAccessException {
		Reflections ref = new Reflections(this.getClass().getPackage().getName());

		for (Class<?> cl : ref.getTypesAnnotatedWith(MinecraftBlock.class)) {
			MinecraftBlock ann = (MinecraftBlock) cl.getAnnotation(MinecraftBlock.class);
			if (ann == null) continue;

			Block curBlock = (Block) cl.newInstance();
			MYBLOCKS.put(ann.blockName(), curBlock);

			curBlock.setRegistryName(ann.blockName());
			GameRegistry.register(curBlock);

			ItemBlock curItemBlock = new ItemBlock(curBlock);
			MYITEMS.put(ann.blockName(), curItemBlock);
			curItemBlock.setRegistryName(ann.blockName());
			GameRegistry.register(curItemBlock);

			Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(curItemBlock, new ItemMeshDefinition() {
				@Override
				public ModelResourceLocation getModelLocation(ItemStack stack) {
					return new ModelResourceLocation(MODID + ":" + ann.blockName(), "inventory");
				}
			});
		}
	}

	private void registerItems() throws InstantiationException, IllegalAccessException {
		Reflections ref = new Reflections(this.getClass().getPackage().getName());

		for (Class<?> cl : ref.getTypesAnnotatedWith(MinecraftItem.class)) {
			MinecraftItem ann = (MinecraftItem) cl.getAnnotation(MinecraftItem.class);
			if (ann == null) continue;

			Item curItem = (Item) cl.newInstance();
			MYITEMS.put(ann.itemName(), curItem);
			curItem.setRegistryName(ann.itemName());
			GameRegistry.register(curItem);

			Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(curItem, new ItemMeshDefinition() {
				@Override
				public ModelResourceLocation getModelLocation(ItemStack stack) {
					return new ModelResourceLocation(MODID + ":" + ann.itemName(), "inventory");
				}
			});
		}
	}
}
