
package net.mcreator.golfit.item;

import net.minecraftforge.registries.ObjectHolder;

import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.block.BlockState;

import net.mcreator.golfit.itemgroup.GolfItemGroup;
import net.mcreator.golfit.GolfItModElements;

@GolfItModElements.ModElement.Tag
public class GolfBallItem extends GolfItModElements.ModElement {
	@ObjectHolder("golf_it:golf_ball")
	public static final Item block = null;
	public GolfBallItem(GolfItModElements instance) {
		super(instance, 13);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new ItemCustom());
	}
	public static class ItemCustom extends Item {
		public ItemCustom() {
			super(new Item.Properties().group(GolfItemGroup.tab).maxStackSize(64));
			setRegistryName("golf_ball");
		}

		@Override
		public int getItemEnchantability() {
			return 0;
		}

		@Override
		public int getUseDuration(ItemStack itemstack) {
			return 0;
		}

		@Override
		public float getDestroySpeed(ItemStack par1ItemStack, BlockState par2Block) {
			return 1F;
		}
	}
}
