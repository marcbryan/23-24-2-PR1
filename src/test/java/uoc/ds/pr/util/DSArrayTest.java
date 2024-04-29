package uoc.ds.pr.util;

import edu.uoc.ds.traversal.Iterator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DSArrayTest {
    private DSArray<String, Integer> dsArray;

    @Before
    public void setUp() {
        dsArray = new DSArray<>(5);

        Assert.assertTrue(dsArray.isEmpty());

        dsArray.put("abc", 1);
        dsArray.put("def", 2);
        dsArray.put("ghi", 3);
        dsArray.put("jkl", 4);
        dsArray.put("mno", 5);
    }

    @Test
    public void deleteTest() {
        dsArray.delete("abc");
        Assert.assertEquals(4, dsArray.size());

        dsArray.delete("def");
        Assert.assertEquals(3, dsArray.size());

        dsArray.delete("ghi");
        Assert.assertEquals(2, dsArray.size());
    }

    @Test
    public void containsKeyTest() {
        Assert.assertTrue(dsArray.containsKey("abc"));
        Assert.assertTrue(dsArray.containsKey("def"));
        Assert.assertFalse(dsArray.containsKey("bbb"));
        Assert.assertFalse(dsArray.containsKey("dca"));
    }

    @Test
    public void keysValuesTest() {
        String[] keys = {"abc","def","ghi","jkl","mno"};
        Integer[] values = {1,2,3,4,5};
        int i = 0;

        // Comprobación claves
        Iterator<String> iterator = dsArray.keys();
        while (iterator.hasNext()) {
            Assert.assertEquals(keys[i], iterator.next());
            i++;
        }

        i=0;
        // Comprobación valores
        Iterator<Integer> iterator2 = dsArray.values();
        while (iterator.hasNext()) {
            Assert.assertEquals(values[i], iterator2.next());
            i++;
        }
    }
}
