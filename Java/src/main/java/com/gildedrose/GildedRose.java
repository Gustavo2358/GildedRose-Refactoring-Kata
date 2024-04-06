package com.gildedrose;

class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
            calculateQualityChange(item);

            if (!item.name.equals("Sulfuras, Hand of Ragnaros")) {
                item.sellIn = item.sellIn - 1;
            }

            if (item.sellIn < 0) {
                switch (item.name) {
                    case "Aged Brie" -> item.quality = Math.min(item.quality + 1, 50);
                    case "Backstage passes to a TAFKAL80ETC concert" -> item.quality = 0;
                    case "Sulfuras, Hand of Ragnaros" -> {}
                    default -> item.quality = Math.max(item.quality - 1, 0);
                }
            }
        }
    }

    private static void calculateQualityChange(Item item) {
        if (item.quality >= 50)
            return;

        switch (item.name){
            case "Aged Brie" -> item.quality++;
            case "Backstage passes to a TAFKAL80ETC concert" -> item.quality = calculateBackstagePassesQualityIncrease(item);
            case "Sulfuras, Hand of Ragnaros" -> {}
            default -> item.quality = Math.max(item.quality - 1, 0);
        }
    }

    private static int calculateBackstagePassesQualityIncrease(Item item) {
        if (item.sellIn <= 5) {
            return Math.min(item.quality + 3, 50);
        }

        if (item.sellIn <= 10) {
            return Math.min(item.quality + 2, 50);
        }

        return Math.min(item.quality + 1, 50);
    }
}
