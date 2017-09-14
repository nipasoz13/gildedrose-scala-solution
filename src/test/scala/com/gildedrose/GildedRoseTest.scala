package com.gildedrose

import org.specs2.mutable._

class GildedRoseTest  extends Specification {
      "foo" should {
        "have name fixme" in {
          val items = Array[Item](new Item("foo", 0, 0))
          val app = new GildedRose(items)
          app.updateQuality()
          (app.items(0).name) must beEqualTo("fixme")
        }
      }
}