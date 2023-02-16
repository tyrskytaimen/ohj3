package fi.tuni.prog3.junitorder;

import java.util.List;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class junitorderTest {

    @Test
    public void testOrder() {
        try {
            Order o = new Order();
            assertTrue(true);
        } catch (Exception e) {
            assertTrue(false);
        }

    }

    @Test
    public void testEmptyOrderException() {
        try {
            Order o = new Order();
            o.removeItems("Kana", 420);
            //Pitäs tulla error;
            assertTrue(false);
        } catch (NoSuchElementException e) {
            assertTrue(true);
        }
    }

    @Test
    public void testIllegalPriceException1() {
        try {
            Order o = new Order();
            o.addItems(new Order.Item("Kana", -1), 1);
            //Pitäs tulla error;
            assertTrue(false);
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        }
    }

    @Test
    public void testIllegalPriceException2() {
        try {
            Order o = new Order();
            o.addItems(new Order.Item("Kana", 1.03222), 1);
            o.addItems(new Order.Item("Kana", 1.03223), 1);
            //Pitäs tulla error;
            assertTrue(false);
        } catch (IllegalStateException e) {
            assertTrue(true);
        }
    }

    @Test
    public void testIllegalPriceException6() {
        try {
            Order o = new Order();
            Order.Item i = new Order.Item("xd", -1);
            //Pitäs tulla error;
            assertTrue(false);
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        }
    }

    @Test
    public void testIllegalArgumentException1() {
        try {
            Order o = new Order();
            Order.Item i = new Order.Item("xd", 1);
            Order.Entry e = new Order.Entry(i, -1);
            //Pitäs tulla error;
            assertTrue(false);
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        }
    }

    @Test
    public void testIllegalArgumentException2() {
        try {
            Order o = new Order();
            o.addItems(new Order.Item("Kana", 1.03222), 1);
            o.addItems("Kana", -1);
            //Pitäs tulla error;
            assertTrue(false);
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        }
    }

    @Test
    public void testIllegalArgumentException3() {
        try {
            Order o = new Order();
            o.addItems(new Order.Item("Kana", 1.03222), -1);
            //Pitäs tulla error;
            assertTrue(false);
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        }
    }

    @Test
    public void testIllegalArgumentException4() {
        try {
            Order o = new Order();
            o.addItems(new Order.Item("Kana", 1.03222), 1);
            o.addItems("Kana", 5);
            o.removeItems("Kana", 999);
            //Pitäs tulla error;
            assertTrue(false);
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        }
    }

    @Test
    public void testIllegalArgumentException5() {
        try {
            Order o = new Order();
            o.addItems(new Order.Item("Kana", 1.03222), 1);
            o.addItems("Kana", 5);
            o.removeItems("Kana", -1);
            //Pitäs tulla error;
            assertTrue(false);
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        }
    }

    @Test
    public void testIllegalArgumentException6() {
        try {
            Order o = new Order();
            Order.Item i = new Order.Item(null, 1);
            //Pitäs tulla error;
            assertTrue(false);
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        }
    }

    @Test
    public void testItemNotFoundException1() {
        try {
            Order o = new Order();
            o.addItems(new Order.Item("Kana", 1.03222), 1);
            o.addItems("KKana", 2);
            assertTrue(false);
        } catch (NoSuchElementException e) {
            assertTrue(true);
        }
    }

    @Test
    public void testItemNotFoundException2() {
        try {
            Order o = new Order();
            o.addItems(new Order.Item("Kana", 1.03222), 1);
            o.removeItems("Kkana", 1);
            assertTrue(false);
        } catch (NoSuchElementException e) {
            assertTrue(true);
        }
    }

    @Test
    public void testAddNewItem() {
        try {
            Order o = new Order();
            o.addItems(new Order.Item("Kana", 1.002), 2);
            List<Order.Entry> e = o.getEntries();
            if (e.get(0).getItemName() == "Kana"
                    && e.get(0).getUnitPrice() == 1.002
                    && e.get(0).getCount() == 2) {
                assertTrue(true);
            } else {
                assertTrue(false);
            }

        } catch (Exception e) {
            assertTrue(false);
        }
    }

    @Test
    public void testAddItems() {
        try {
            Order o = new Order();
            o.addItems(new Order.Item("Kana", 1.002), 1);
            o.addItems("Kana", 5);
            List<Order.Entry> e = o.getEntries();
            for (Order.Entry entry : e) {
                if (entry.getItemName() == "Kana"
                        && entry.getUnitPrice() == 1.002
                        && entry.getCount() == 6) {
                } else {
                    assertTrue(false);
                }
            }
            assertTrue(true);

        } catch (Exception e) {
            assertTrue(false);
        }
    }

    @Test
    public void TestEntryCount() {
        Order o = new Order();

        o.addItems(new Order.Item("1", 1.002), 1);
        o.addItems(new Order.Item("1", 1.002), 1);

        o.addItems(new Order.Item("2", 1.002), 1);
        o.addItems(new Order.Item("3", 1.002), 1);
        o.addItems(new Order.Item("4", 1.002), 1);
        o.addItems(new Order.Item("5", 1.002), 1);
        o.addItems(new Order.Item("6", 1.002), 1);

        if (o.getEntryCount() == 6) {
            assertTrue(true);
        } else {
            assertTrue(false);
        }
    }

    @Test
    public void TestItemCount() {
        Order o = new Order();

        o.addItems(new Order.Item("1", 1.002), 1);
        o.addItems(new Order.Item("1", 1.002), 1);

        o.addItems(new Order.Item("2", 1.002), 1);
        o.addItems(new Order.Item("3", 1.002), 1);
        o.addItems(new Order.Item("4", 1.002), 1);
        o.addItems(new Order.Item("5", 1.002), 1);
        o.addItems(new Order.Item("6", 1.002), 1);

        if (o.getItemCount() == 7) {
            assertTrue(true);
        } else {
            assertTrue(false);
        }
    }

    @Test
    public void TestTotalPrice() {
        Order o = new Order();

        o.addItems(new Order.Item("1", 1.002), 1);
        //o.addItems(new Order.Item("1", 1.002), 1);

        o.addItems(new Order.Item("2", 1.002), 1);
        o.addItems(new Order.Item("3", 1.002), 1);
        o.addItems(new Order.Item("4", 1.002), 1);
        o.addItems(new Order.Item("5", 1.002), 1);
        o.addItems(new Order.Item("6", 1.002), 1);

        if (o.getTotalPrice() * 1.00 == 6.012) {
            o.removeItems("1", 1);
            if (o.getTotalPrice() * 1.00 == 5.01) {
                assertTrue(true);
            } else {
                assertTrue(false);
            }
        } else {
            assertTrue(false);
        }
        /*if (o.getTotalPrice()*1.0 == 7.014) {
            o.removeItems("1", 1);
            if (o.getTotalPrice() == 6.012) {
                assertTrue(true);
            } else {
                assertTrue(false);
            }

        } else {
            assertTrue(false);
        }*/
    }

    @Test
    public void TestEmpty1() {
        Order o = new Order();
        o.addItems(new Order.Item("1", 1.002), 1);
        o.addItems(new Order.Item("1", 1.002), 1);

        if (o.isEmpty() == false) {
            assertTrue(true);
        } else {
            assertTrue(false);
        }
    }

    @Test
    public void TestEmpty2() {
        Order o = new Order();

        if (o.isEmpty() == true) {
            assertTrue(true);
        } else {
            assertTrue(false);
        }
    }

    @Test
    public void testRemove() {
        Order o = new Order();

        o.addItems(new Order.Item("1", 1.002), 1);
        o.addItems(new Order.Item("1", 1.002), 1);

        o.addItems(new Order.Item("2", 1.002), 1);
        o.addItems(new Order.Item("3", 1.002), 1);
        o.addItems(new Order.Item("4", 1.002), 1);
        o.addItems(new Order.Item("5", 1.002), 1);
        o.addItems(new Order.Item("6", 1.002), 1);

        o.removeItems("1", 1);
        if (o.getItemCount() == 6) {
            o.addItems(new Order.Item("3", 1.002), 1);
            o.removeItems("3", 2);
            o.removeItems("2", 1);

            if (o.getItemCount() == 4) {
                assertTrue(true);
            } else {
                assertTrue(false);
            }

        } else {
            assertTrue(false);
        }
    }

    @Test
    public void testItemClass() {
        Order o = new Order();

        Order.Item i = new Order.Item("Kana", 1.337);
        Order.Item i2 = new Order.Item("Kkana", 1.35);
        Order.Item i3 = new Order.Item("Kana", 1.337);

        if (i.getName().equals("Kana")) {
            if (i.getPrice() == 1.337) {
                if (i.toString().equals("Item(Kana, 1.34)")) {
                    if (i.equals(i3) == true) { //i.assertEquals(i3) 
                        if (i.equals(i2) == false) { //i.assertEquals(i2)
                            assertTrue(true);
                        } else {
                            assertTrue(false);
                        }
                    } else {
                        assertTrue(false);
                    }
                } else {
                    assertTrue(false);
                }
            } else {
                assertTrue(false);
            }
        } else {
            assertTrue(false);
        }

    }

    @Test
    public void testEntryClass() {
        Order o = new Order();
        Order.Item i = new Order.Item("Kana", 1.337);

        Order.Entry e = new Order.Entry(i, 420);

        if (e.getCount() == 420) {
            if (e.getItem().getName().equals("Kana")) {
                if (e.getItem().getPrice() == 1.337) {
                    if (e.getItemName().equals("Kana")) {
                        if (e.getUnitPrice() == 1.337) {
                            if (e.getCount() == 420) {
                                if (e.toString().equals("420 units of Item(Kana, 1.34)")) {
                                    assertTrue(true);
                                } else {
                                    assertTrue(false);
                                }
                            } else {
                                assertTrue(false);
                            }
                        } else {
                            assertTrue(false);
                        }
                    } else {
                        assertTrue(false);
                    }
                } else {
                    assertTrue(false);
                }
            } else {
                assertTrue(false);
            }
        } else {
            assertTrue(false);
        }
    }

}
