package com.gildedrose

import org.specs2.mutable._

class GildedRoseTest extends Specification {
  "An item SellIn" should {
    "decreases at the end of tne day" in {
      val items = Array[Item](new Item("Item", 1, 50))
      val app = new GildedRose(items)
      app.updateQuality()
      (app.items(0).sellIn) must beEqualTo(0)
    }
  }

  "An item quality" should {
    "degrades at the end of the day" in {
      val items = Array[Item](new Item("Item", 1, 50))
      val app = new GildedRose(items)
      app.updateQuality()
      (app.items(0).quality) must beEqualTo(49)
    }

    "degrades twice as fast when the date has past" in {
      val items = Array[Item](new Item("Item", 0, 50))
      val app = new GildedRose(items)
      app.updateQuality()
      (app.items(0).quality) must beEqualTo(48)
    }

    "never be negative" in {
      val items = Array[Item](new Item("Item", 0, 0))
      val app = new GildedRose(items)
      app.updateQuality()
      (app.items(0).quality) must beEqualTo(0)
    }

    "never more be than 50" in {
      val items = Array[Item](new Item("Aged Brie", 1, 50))
      val app = new GildedRose(items)
      app.updateQuality()
      (app.items(0).quality) must beEqualTo(50)
    }
  }

  "Aged Brie" should {
    "increase in quality when it gets older" in {
      val items = Array[Item](new Item("Aged Brie", 1, 20))
      val app = new GildedRose(items)
      app.updateQuality()
      (app.items(0).quality) must beEqualTo(21)
    }
  }

  "Sulfuras" should {
    val items = Array[Item](new Item("Sulfuras, Hand of Ragnaros", 0, 40))
    val app = new GildedRose(items)

    "Never be sold" in {
      app.updateQuality()
      (app.items(0).sellIn) must beEqualTo(0)
    }

    "Never decrease in quality" in {
      app.updateQuality()
      (app.items(0).quality) must beEqualTo(40)
    }
  }

  "Backstage passes" should {
    "increase in Quality by 2, 10 to 6 days before the concert" in {
      val items = Array[Item](new Item("Backstage passes to a TAFKAL80ETC concert", 9, 40))
      val app = new GildedRose(items)
      app.updateQuality()
      (app.items(0).quality) must beEqualTo(42)
    }

    "increase in Quality by 3, 5 days or less before the concert" in {
      val items = Array[Item](new Item("Backstage passes to a TAFKAL80ETC concert", 5, 40))
      val app = new GildedRose(items)
      app.updateQuality()
      (app.items(0).quality) must beEqualTo(43)
    }

    "quality drops to 0 after the concert" in {
      val items = Array[Item](new Item("Backstage passes to a TAFKAL80ETC concert", 0, 40))
      val app = new GildedRose(items)
      app.updateQuality()
      (app.items(0).quality) must beEqualTo(0)
    }
  }

}