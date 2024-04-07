package com.gildedrose;

class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
            if (isAgedBrie(item)) {
                new AgedBrie(item).updateAgedBrie();
            } else if (isBackstagePasses(item)) {
                new BackstagePasses(item).updatedBackstagePasses();
            } else if (isSulfuras(item)) {
                new Sulfuras(item).updateSulfuras();
            }
            else {
                new DefaultItem(item).updateDefaultItem();
            }
        }
    }

    private boolean isSulfuras(Item item) {
        return item.name.equals("Sulfuras, Hand of Ragnaros");
    }

    private boolean isAgedBrie(Item item) {
        return item.name.equals("Aged Brie");
    }

    private boolean isBackstagePasses(Item item) {
        return item.name.equals("Backstage passes to a TAFKAL80ETC concert");
    }
}
