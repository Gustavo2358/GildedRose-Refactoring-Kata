package com.gildedrose;

public class BackstagePasses {

    private final Item item;

    public BackstagePasses(Item item) {
        this.item = item;
    }

    public void updatedBackstagePasses() {
        item.quality = Math.min(updateBackstagePassesQuality(), 50);
        item.sellIn = item.sellIn - 1;
    }

    private int updateBackstagePassesQuality() {
        if (item.sellIn < 1) {
            return 0;
        }

        if (item.sellIn < 6) {
            if (item.quality < 50) {
                return item.quality + 3;
            }
        }

        if (item.sellIn < 11) {
            if (item.quality < 50) {
                return item.quality + 2;
            }
        }

        return item.quality + 1;
    }

}
