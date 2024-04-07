package com.gildedrose;

public class ItemFactory {

    private static final String AGED_BRIE = "Aged Brie";
    private static final String SULFURAS = "Sulfuras, Hand of Ragnaros";
    private static final String BACKSTAGE_PASSES = "Backstage passes to a TAFKAL80ETC concert";
    private static final String CONJURED = "Conjured";

    public static ItemCommand create(Item item) {
        return switch (item.name) {
            case String s when isAgedBrie(s) -> new AgedBrie(item);
            case String s when isBackstagePasses(s) -> new BackstagePasses(item);
            case String s when isSulfuras(s) -> new Sulfuras(item);
            case String s when isConjured(s) -> new Conjured(item);
            default -> new DefaultItem(item);
        };
    }

    private static boolean isAgedBrie(String s) {
        return s.equals(AGED_BRIE);
    }

    private static boolean isSulfuras(String s) {
        return s.equals(SULFURAS);
    }

    private static boolean isBackstagePasses(String s) {
        return s.equals(BACKSTAGE_PASSES);
    }

    private static boolean isConjured(String s) {
        return s.startsWith(CONJURED);
    }
}
