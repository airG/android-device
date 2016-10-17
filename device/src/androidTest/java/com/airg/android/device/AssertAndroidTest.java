package com.airg.android.device;

import org.junit.Test;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

/**
 * Created by mahramf.
 */
public class AssertAndroidTest {
    @Test
    public void assertNotNull() throws Exception {
        Assert.assertNotNull("bob");

        //noinspection RedundantStringConstructorCall
        final String str = new String ();
        Assert.assertNotNull(str);

        Assert.assertNotNull(new Object());
    }

    @Test (expected = NullPointerException.class)
    public void assertNotNullWithNullLiteral() throws Exception {
        Assert.assertNotNull(null);
    }

    @Test (expected = NullPointerException.class)
    public void assertNotNullWithNullReference() throws Exception {
        final String str = null;
        Assert.assertNotNull(str);
    }

    @Test
    public void assertIsNull() throws Exception {
        Assert.assertIsNull(null);

        final String str = null;
        Assert.assertIsNull(str);
    }

    @Test (expected = IllegalStateException.class)
    public void assertIsNullWithNonNullLiteral() throws Exception {
        Assert.assertIsNull("bob");
    }

    @Test (expected = IllegalStateException.class)
    public void assertIsNullWithNonNullReference() throws Exception {
        final String str = "bob";
        Assert.assertIsNull(str);
    }

    @Test
    public void assertIsEmpty() throws Exception {
        Assert.assertIsEmpty(null);
        Assert.assertIsEmpty("");
        //noinspection RedundantStringConstructorCall
        Assert.assertIsEmpty(new String());
        Assert.assertIsEmpty(new StringBuilder());
    }

    @Test (expected = IllegalStateException.class)
    public void assertIsEmptyWithNonEmptyLiteral() throws Exception {
        Assert.assertIsEmpty("bob");
    }

    @Test (expected = IllegalStateException.class)
    public void assertIsEmptyWithNonEmptyReference() throws Exception {
        final String bob = "bob";
        Assert.assertIsEmpty(bob);
    }

    @Test
    public void assertNotEmpty() throws Exception {
        Assert.assertNotEmpty("bob");
        Assert.assertNotEmpty(new StringBuilder("bob"));
    }

    @Test (expected = IllegalStateException.class)
    public void assertNotEmptyWithNullLiteral() throws Exception {
        Assert.assertNotEmpty(null);
    }

    @Test (expected = IllegalStateException.class)
    public void assertNotEmptyWithEmptyLiteral() throws Exception {
        Assert.assertNotEmpty("");
    }

    @Test
    public void assertEqual() throws Exception {
        Assert.assertEqual(null, null);
        Assert.assertEqual(6, 6);
        Assert.assertEqual("bob", "bob");
        //noinspection RedundantStringConstructorCall
        Assert.assertEqual(new String(), "");
        final Object o = new Object();
        Assert.assertEqual(o, o);
        final String bob = "bob";
        Assert.assertEqual(bob, "bob");
    }

    @Test (expected = IllegalStateException.class)
    public void assertEqualWithLeftNull() throws Exception {
        Assert.assertEqual(null, "bob");
    }

    @Test (expected = IllegalStateException.class)
    public void assertEqualWithRightNull() throws Exception {
        Assert.assertEqual("bob", null);
    }

    @Test (expected = IllegalStateException.class)
    public void assertEqualWithUnequals() throws Exception {
        Assert.assertEqual("bob", "Sally");
    }

    @Test (expected = IllegalStateException.class)
    public void assertEqualWithUnequalTypes() throws Exception {
        Assert.assertEqual("bob", 808);
    }

    @Test
    public void assertNotEqual() throws Exception {
        Assert.assertNotEqual(0, 1);
        Assert.assertNotEqual("bob", 808);
        Assert.assertNotEqual("bob", null);
        Assert.assertNotEqual(null, "bob");
        Assert.assertNotEqual("bob", "sally");
    }

    @Test (expected = IllegalStateException.class)
    public void assertNotEqualWithReferenceEqual() throws Exception {
        final String sttr = "bob";
        Assert.assertNotEqual(sttr, sttr);
    }

    @Test (expected = IllegalStateException.class)
    public void assertNotEqualWithValueEqual() throws Exception {
        Assert.assertNotEqual("bob", "bob");
    }

    @Test (expected = IllegalStateException.class)
    public void assertNotEqualWithBothNull() throws Exception {
        Assert.assertNotEqual(null, null);
    }

    @Test (expected = IllegalStateException.class)
    public void assertNotEqualWithEqualInts() throws Exception {
        Assert.assertNotEqual(808, 808);
    }

    @Test
    public void assertReferenceEqual() throws Exception {
        Assert.assertReferenceEqual(null, null);
        final String str ="bob";
        Assert.assertReferenceEqual(str, str);
        //noinspection UnnecessaryLocalVariable
        final String other = str;
        Assert.assertReferenceEqual(str, other);
    }

    @Test (expected = IllegalStateException.class)
    public void assertReferenceEqualWithUnequalRefs() throws Exception {
        final String bob = "bob";
        //noinspection RedundantStringConstructorCall
        final String other = new String ("bob");
        Assert.assertReferenceEqual(bob, other);
    }

    @Test
    public void assertReferenceEqualWithChangedValue() throws Exception {
        final IntWrapper one = new IntWrapper(1);
        //noinspection UnnecessaryLocalVariable
        final IntWrapper other = one;
        Assert.assertReferenceEqual(one, other);
        other.anInt = 12;
        Assert.assertReferenceEqual(one, other);
    }

    @Test
    public void assertReferenceNotEqual() throws Exception {
        final IntWrapper one = new IntWrapper(1);
        final IntWrapper other = new IntWrapper(1);
        Assert.assertReferenceNotEqual(one, other);
        Assert.assertReferenceNotEqual(one, null);
        Assert.assertReferenceNotEqual(null, other);
        Assert.assertReferenceNotEqual("bob", other);
    }

    @Test (expected = IllegalStateException.class)
    public void assertReferenceNotEqualWithBothNullReferences() throws Exception {
        Assert.assertReferenceNotEqual(null, null);
    }

    @Test (expected = IllegalStateException.class)
    public void assertReferenceNotEqualWithEqualReferences() throws Exception {
        final IntWrapper one = new IntWrapper(1);
        //noinspection UnnecessaryLocalVariable
        final IntWrapper other = one;
        Assert.assertReferenceNotEqual(one, other);
    }

    @EqualsAndHashCode @AllArgsConstructor
    private static class IntWrapper {
        int anInt;
    }
}