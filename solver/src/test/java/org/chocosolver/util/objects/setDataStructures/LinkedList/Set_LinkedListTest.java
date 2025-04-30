package org.chocosolver.util.objects.setDataStructures.LinkedList;

import org.chocosolver.util.objects.setDataStructures.SetType;
import org.chocosolver.util.objects.setDataStructures.linkedlist.Set_LinkedList;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Set_LinkedListTest {
    @Test(groups = "1s")
    public void testLinkedListMin(){
        Set_LinkedList list = new Set_LinkedList();
        Assert.expectThrows(IllegalStateException.class, () -> list.min());
    }

    @Test(groups = "1s")
    public void testLinkedListMax(){
        Set_LinkedList list = new Set_LinkedList();
        Assert.expectThrows(IllegalStateException.class, () -> list.max());
    }

    @Test(groups = "1s")
    public void testLinkedListType(){
        Set_LinkedList list = new Set_LinkedList();
        Assert.assertEquals(SetType.LINKED_LIST, list.getSetType());
    }
}
