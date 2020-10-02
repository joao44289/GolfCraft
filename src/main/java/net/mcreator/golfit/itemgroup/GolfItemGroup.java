
package net.mcreator.golfit.itemgroup;

import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemGroup;

import net.mcreator.golfit.item.GolfClubRangedItem;
import net.mcreator.golfit.GolfItModElements;

@GolfItModElements.ModElement.Tag
public class GolfItemGroup extends GolfItModElements.ModElement {
	public GolfItemGroup(GolfItModElements instance) {
		super(instance, 2);
	}

	@Override
	public void initElements() {
		tab = new ItemGroup("tabgolf") {
			@OnlyIn(Dist.CLIENT)
			@Override
			public ItemStack createIcon() {
				return new ItemStack(GolfClubRangedItem.block, (int) (1));
			}

			@OnlyIn(Dist.CLIENT)
			public boolean hasSearchBar() {
				return false;
			}
		};
	}
	public static ItemGroup tab;
}
