package com.gildedrose;

class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
            if (isAgedBrie(item)) {
                item.quality = Math.min(item.quality + 1, 50);
            } else if (isBackstagePasses(item)) {
                item.quality = Math.min(updateBackstagePassesQuality(item), 50);
            } else {
                if (!isSulfuras(item)) {
                    item.quality = Math.max(item.quality - 1, 0);
                }
            }

            if (item.sellIn < 1) {
                if (isAgedBrie(item)) {
                    item.quality = Math.min(item.quality + 1, 50);
                } else {
                    if (isBackstagePasses(item)) {
                        item.quality = 0;
                    } else {
                        if (!isSulfuras(item)) {
                            item.quality = Math.max(item.quality - 1, 0);
                        }
                    }
                }
            }

            if (!isSulfuras(item)) {
                item.sellIn = item.sellIn - 1;
            }

        }
    }

    private int updateBackstagePassesQuality(Item item) {
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

    private static boolean isSulfuras(Item item) {
        return item.name.equals("Sulfuras, Hand of Ragnaros");
    }

    private static boolean isAgedBrie(Item item) {
        return item.name.equals("Aged Brie");
    }

    private static boolean isBackstagePasses(Item item) {
        return item.name.equals("Backstage passes to a TAFKAL80ETC concert");
    }
}
