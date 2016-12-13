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
        Assert.notNull("bob");

        //noinspection RedundantStringConstructorCall
        final String str = new String ();
        Assert.notNull(str);

        Assert.notNull(new Object());
    }

    @Test (expected = NullPointerException.class)
    public void assertNotNullWithNullLiteral() throws Exception {
        Assert.notNull(null);
    }

    @Test (expected = NullPointerException.class)
    public void assertNotNullWithNullReference() throws Exception {
        final String str = null;
        Assert.notNull(str);
    }

    @Test
    public void assertIsNull() throws Exception {
        Assert.isNull(null);

        final String str = null;
        Assert.isNull(str);
    }

    @Test (expected = IllegalStateException.class)
    public void assertIsNullWithNonNullLiteral() throws Exception {
        Assert.isNull("bob");
    }

    @Test (expected = IllegalStateException.class)
    public void assertIsNullWithNonNullReference() throws Exception {
        final String str = "bob";
        Assert.isNull(str);
    }

    @Test
    public void assertIsEmpty() throws Exception {
        Assert.isEmpty(null);
        Assert.isEmpty("");
        //noinspection RedundantStringConstructorCall
        Assert.isEmpty(new String());
        Assert.isEmpty(new StringBuilder());
    }

    @Test (expected = IllegalStateException.class)
    public void assertIsEmptyWithNonEmptyLiteral() throws Exception {
        Assert.isEmpty("bob");
    }

    @Test (expected = IllegalStateException.class)
    public void assertIsEmptyWithNonEmptyReference() throws Exception {
        final String bob = "bob";
        Assert.isEmpty(bob);
    }

    @Test
    public void assertNotEmpty() throws Exception {
        Assert.notEmpty("bob");
        Assert.notEmpty(new StringBuilder("bob"));
    }

    @Test (expected = IllegalStateException.class)
    public void assertNotEmptyWithNullLiteral() throws Exception {
        Assert.notEmpty(null);
    }

    @Test (expected = IllegalStateException.class)
    public void assertNotEmptyWithEmptyLiteral() throws Exception {
        Assert.notEmpty("");
    }

    @Test
    public void assertEqual() throws Exception {
        Assert.equal(null, null);
        Assert.equal(6, 6);
        Assert.equal("bob", "bob");
        //noinspection RedundantStringConstructorCall
        Assert.equal(new String(), "");
        final Object o = new Object();
        Assert.equal(o, o);
        final String bob = "bob";
        Assert.equal(bob, "bob");
    }

    @Test (expected = IllegalStateException.class)
    public void assertEqualWithLeftNull() throws Exception {
        Assert.equal(null, "bob");
    }

    @Test (expected = IllegalStateException.class)
    public void assertEqualWithRightNull() throws Exception {
        Assert.equal("bob", null);
    }

    @Test (expected = IllegalStateException.class)
    public void assertEqualWithUnequals() throws Exception {
        Assert.equal("bob", "Sally");
    }

    @Test (expected = IllegalStateException.class)
    public void assertEqualWithUnequalTypes() throws Exception {
        Assert.equal("bob", 808);
    }

    @Test
    public void assertNotEqual() throws Exception {
        Assert.notEqual(0, 1);
        Assert.notEqual("bob", 808);
        Assert.notEqual("bob", null);
        Assert.notEqual(null, "bob");
        Assert.notEqual("bob", "sally");
    }

    @Test (expected = IllegalStateException.class)
    public void assertNotEqualWithReferenceEqual() throws Exception {
        final String sttr = "bob";
        Assert.notEqual(sttr, sttr);
    }

    @Test (expected = IllegalStateException.class)
    public void assertNotEqualWithValueEqual() throws Exception {
        Assert.notEqual("bob", "bob");
    }

    @Test (expected = IllegalStateException.class)
    public void assertNotEqualWithBothNull() throws Exception {
        Assert.notEqual(null, null);
    }

    @Test (expected = IllegalStateException.class)
    public void assertNotEqualWithEqualInts() throws Exception {
        Assert.notEqual(808, 808);
    }

    @Test
    public void assertReferenceEqual() throws Exception {
        Assert.referenceEqual(null, null);
        final String str ="bob";
        Assert.referenceEqual(str, str);
        //noinspection UnnecessaryLocalVariable
        final String other = str;
        Assert.referenceEqual(str, other);
    }

    @Test (expected = IllegalStateException.class)
    public void assertReferenceEqualWithUnequalRefs() throws Exception {
        final String bob = "bob";
        //noinspection RedundantStringConstructorCall
        final String other = new String ("bob");
        Assert.referenceEqual(bob, other);
    }

    @Test
    public void assertReferenceEqualWithChangedValue() throws Exception {
        final IntWrapper one = new IntWrapper(1);
        //noinspection UnnecessaryLocalVariable
        final IntWrapper other = one;
        Assert.referenceEqual(one, other);
        other.anInt = 12;
        Assert.referenceEqual(one, other);
    }

    @Test
    public void assertReferenceNotEqual() throws Exception {
        final IntWrapper one = new IntWrapper(1);
        final IntWrapper other = new IntWrapper(1);
        Assert.referenceNotEqual(one, other);
        Assert.referenceNotEqual(one, null);
        Assert.referenceNotEqual(null, other);
        Assert.referenceNotEqual("bob", other);
    }

    @Test (expected = IllegalStateException.class)
    public void assertReferenceNotEqualWithBothNullReferences() throws Exception {
        Assert.referenceNotEqual(null, null);
    }

    @Test (expected = IllegalStateException.class)
    public void assertReferenceNotEqualWithEqualReferences() throws Exception {
        final IntWrapper one = new IntWrapper(1);
        //noinspection UnnecessaryLocalVariable
        final IntWrapper other = one;
        Assert.referenceNotEqual(one, other);
    }

    @EqualsAndHashCode @AllArgsConstructor
    private static class IntWrapper {
        int anInt;
    }
}