package com.gildedrose;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GildedRoseTest {

    @Test
    void shouldKeepTheItemName() {
        Item[] items = new Item[] { new Item("foo", 0, 0) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("foo", app.items[0].name);
    }
    @Test
    void shouldWorkWithLotsOfItemsInTheArray() {
        Item[] items = new Item[] {
            new Item("+5 Dexterity Vest", 10, 20),
            new Item("Aged Brie", 2, 0),
            new Item("Elixir of the Mongoose", 5, 7),
            new Item("Sulfuras, Hand of Ragnaros", 0, 80),
            new Item("Sulfuras, Hand of Ragnaros", -1, 80),
            new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20),
            new Item("Backstage passes to a TAFKAL80ETC concert", 10, 49),
            new Item("Backstage passes to a TAFKAL80ETC concert", 5, 49),
            new Item("Conjured Mana Cake", 3, 6),
        };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("Elixir of the Mongoose", app.items[2].name);
        assertEquals(4, app.items[2].sellIn);
        assertEquals(6, app.items[2].quality);
    }

    @Test
    void shouldLowerSellIn() {
        Item[] items = new Item[] { new Item("foo", 0, 0) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(-1, app.items[0].sellIn);
        assertEquals(0, app.items[0].quality);
    }

    @Test
    void ShouldLowerSellInTwoTimes() {
        Item[] items = new Item[] { new Item("foo", 0, 0) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        app.updateQuality();
        assertEquals(-2, app.items[0].sellIn);
        assertEquals(0, app.items[0].quality);
    }

    @Test
    void ShouldLowerSellAndQualityIfQualityIsBiggerThanZero() {
        Item[] items = new Item[] { new Item("foo", 1, 2) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(0, app.items[0].sellIn);
        assertEquals(1, app.items[0].quality);
    }

    @Test
    void ShouldLowerQualityTwiceAsFastOnceTheSellDataHasPassed() {
        Item[] items = new Item[] { new Item("foo", 0, 10) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(-1, app.items[0].sellIn);
        assertEquals(8, app.items[0].quality);
        app.updateQuality();
        assertEquals(-2, app.items[0].sellIn);
        assertEquals(6, app.items[0].quality);
    }

    @Test
    void QualityShouldNeverBeNegative() {
        Item[] items = new Item[] { new Item("foo", 0, 10) };
        GildedRose app = new GildedRose(items);

        for(int i = 0; i < 100; i++){
            app.updateQuality();
            assertEquals(-1 - i, app.items[0].sellIn);
            assertTrue(app.items[0].quality >= 0);
        }
    }

    @Test
    void AgedBrieQualityShouldIncrease() {
        Item[] items = new Item[] { new Item("Aged Brie", 1, 10) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals( 11, app.items[0].quality);
    }

    @Test
    void QualityOfAgedBrieShouldIncreaseTwiceAsFastOnceTheSellDataHasPassed() {
        Item[] items = new Item[] { new Item("Aged Brie", 0, 10) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals( 12, app.items[0].quality);
    }

    @Test
    void QualityOfAgedBrieShouldNeverBeMoreThanFifty() {
        Item[] items = new Item[] { new Item("Aged Brie", 0, 10) };
        GildedRose app = new GildedRose(items);

        for(int i = 0; i < 100; i++){
            app.updateQuality();
            assertEquals(-1 - i, app.items[0].sellIn);
            assertTrue(app.items[0].quality <= 50);
        }
    }

    @Test
    void QualityOfBackstagePassesShouldIncrease() {
        Item[] items = new Item[] { new Item("Aged Brie", 1, 10) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals( 11, app.items[0].quality);
    }
    @Test
    void QualityOfBackstagePassesShouldNeverBeMoreThanFifty() {
        Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 10, 49) };
        GildedRose app = new GildedRose(items);

        for(int i = 0; i < 100; i++){
            app.updateQuality();
            assertTrue(app.items[0].quality <= 50);
        }
    }

    @Test
    void QualityOfBackstagePassesShouldIncreasesByTwoWhenThereAreTenDaysOrLess() {
        Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 10, 0) };
        GildedRose app = new GildedRose(items);

        app.updateQuality();
        assertEquals(2,app.items[0].quality);
    }

    @Test
    void QualityOfBackstagePassesShouldIncreasesByThreeWhenThereAreFiveDaysOrLess() {
        Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 5, 0) };
        GildedRose app = new GildedRose(items);

        app.updateQuality();
        assertEquals(3,app.items[0].quality);
    }

    @Test
    void QualityOfBackstagePassesShouldDropsToZeroAfterTheConcert() {
        Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 0, 10) };
        GildedRose app = new GildedRose(items);

        app.updateQuality();
        assertEquals(0,app.items[0].quality);
    }

    @Test
    void conjuredItemsShouldDegradeTwiceAsFastAsNormalItems() {
        Item[] items = new Item[] {
            new Item("Conjured Phantom Blade", 5, 10),
            new Item("Conjured Mana Cake", 3, 1),
            new Item("Conjured Eclipsed Sunstone", 1, 15)
        };
        GildedRose app = new GildedRose(items);

        app.updateQuality();
        assertEquals(8,app.items[0].quality);
        assertEquals(0,app.items[1].quality);
        assertEquals(13,app.items[2].quality);

        app.updateQuality();
        assertEquals(6,app.items[0].quality);
        assertEquals(0,app.items[1].quality);
        assertEquals(9,app.items[2].quality);

        app.updateQuality();
        assertEquals(4,app.items[0].quality);
        assertEquals(0,app.items[1].quality);
        assertEquals(5,app.items[2].quality);
    }
}
