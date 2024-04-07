package com.gildedrose;

class GildedRose {
    public static final String AGED_BRIE = "Aged Brie";
    public static final String SULFURAS = "Sulfuras, Hand of Ragnaros";
    public static final String BACKSTAGE_PASSES = "Backstage passes to a TAFKAL80ETC concert";
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
            switch (item.name) {
                case AGED_BRIE -> new AgedBrie(item).updateAgedBrie();
                case BACKSTAGE_PASSES -> new BackstagePasses(item).updatedBackstagePasses();
                case SULFURAS -> new Sulfuras(item).updateSulfuras();
                default -> new DefaultItem(item).updateDefaultItem();
            }
        }
    }
}
