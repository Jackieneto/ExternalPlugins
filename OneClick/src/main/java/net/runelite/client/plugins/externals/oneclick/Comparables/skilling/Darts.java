package net.runelite.client.plugins.externals.oneclick.comparables.skilling;

import com.google.common.collect.ImmutableSet;
import java.util.Set;
import net.runelite.api.ItemID;
import net.runelite.api.MenuEntry;
import net.runelite.api.MenuOpcode;
import net.runelite.client.plugins.externals.oneclick.comparables.ClickCompare;

public class Potions extends ClickCompare
{
	private static final Set<Integer> COMPOST_POTION = ImmutableSet.of(
		ItemID.COMPOST_POTION4, ItemID.COMPOST_POTION3, ItemID.COMPOST_POTION2,
		ItemID.COMPOST_POTION1
	);

	@Override
	public boolean isEntryValid(MenuEntry event)
	{
		return event.getOpcode() == MenuOpcode.ITEM_USE.getId() &&
			(COMPOST_POTION.contains(event.getIdentifier()));
	}

	@Override
	public void modifyEntry(MenuEntry event)
	{
		if (findItem(ItemID.COMPOST).getLeft() == -1)
		{
			return;
		}

		MenuEntry e = event.clone();
		e.setTarget("<col=ff9040>Compost<col=ffffff> -> " + getTargetMap().get(event.getIdentifier()));
		e.setForceLeftClick(true);
		insert(e);
	}

	@Override
	public boolean isClickValid(MenuEntry event)
	{
		return event.getOpcode() == MenuOpcode.ITEM_USE.getId() &&
			event.getTarget().contains("<col=ff9040>Compost<col=ffffff> -> ");
	}

	@Override
	public void modifyClick(MenuEntry event)
	{
		if (updateSelectedItem(ItemID.COMPOST))
		{
			event.setOpcode(MenuOpcode.ITEM_USE_ON_WIDGET_ITEM.getId());
		}
	}
}
