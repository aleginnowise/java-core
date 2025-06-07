package com.internship.java_core.custom_linked_list;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class CustomLinkedListTest {
    // Method size() checked with one node
    @Test
    public void givenEmptyList_whenAddSingleNode_thenSizeIs1(){
        CustomLinkedList<String> list = new CustomLinkedList<>();
        list.addFirst("MUSIC");

        assertEquals(1, list.size());
    }

    // Method size() checked with 4 nodes
    @Test
    public void givenEmptyList_whenAddFourNodes_thenSizeIs4(){
        CustomLinkedList<String> list = new CustomLinkedList<>();

        list.add(0,"I AM");
        list.add(1,"MUSIC");
        list.add(2,"i2");
        list.add(3,"i3");

        assertEquals(4, list.size());
    }

    // Check addFirst()
    @Test
    public void givenEmptyList_whenAddFirstNode_thenReturnFirstNode(){
        CustomLinkedList<String> list = new CustomLinkedList<>();
        list.addFirst("MUSIC");

        assertEquals("MUSIC", list.getFirst());
    }

    // Check addLast()
    @Test
    public void givenEmptyList_whenAddLastNode_thenReturnLastNode(){
        CustomLinkedList<String> list = new CustomLinkedList<>();
        list.addLast("MUSIC");

        assertEquals("MUSIC", list.getLast());
    }

    // Check add() adding 1 node in the list
    @Test
    public void givenEmptyList_whenAddNodeAtIndex1_thenReturnNodeAtIndex1(){
        CustomLinkedList<String> list = new CustomLinkedList<>();
        list.add(0,"MUSIC");

        assertEquals("MUSIC", list.get(0));
    }

    // Check add() adding 2 nodes in the list
    @Test
    public void givenEmptyList_whenAddTwoNodes_thenReturnTwoNodes(){
        CustomLinkedList<String> list = new CustomLinkedList<>();

        list.add(0,"I AM");
        list.add(1,"MUSIC");

        assertEquals("I AM", list.get(0));
        assertEquals("MUSIC", list.get(1));
    }

    // Check add() adding 4 nodes in the list
    @Test
    public void givenEmptyList_whenAddFourNodes_thenReturnFourNodes(){
        CustomLinkedList<String> list = new CustomLinkedList<>();

        list.add(0,"I AM");
        list.add(1,"MUSIC");
        list.add(2,"i2");
        list.add(3,"i3");

        assertEquals("I AM", list.get(0));
        assertEquals("MUSIC", list.get(1));
        assertEquals("i2", list.get(2));
        assertEquals("i3", list.get(3));
    }

    // Check add() adding new fourth node with index 3. Size should be changed from 4 to 5
    @Test
    public void givenListWithFourNodes_whenAddNodeAtIndex3_thenSizeIs5AndReturnNodeAtIndex3(){
        CustomLinkedList<String> list = new CustomLinkedList<>();

        list.add(0,"I AM");
        list.add(1,"MUSIC");
        list.add(2,"i2");
        list.add(3,"i3");

        list.add(3, "new i3");
        assertEquals(5, list.size());
        assertEquals("new i3", list.get(3));
    }

    // getFirst() checked with 4 nodes in the list
    @Test
    public void givenListWithFourNodes_whenGetFirst_thenReturnFirstNode(){
        CustomLinkedList<String> list = new CustomLinkedList<>();

        list.add(0,"I AM");
        list.add(1,"MUSIC");
        list.add(2,"i2");
        list.add(3,"i3");

        assertEquals("I AM", list.getFirst());
    }

    // getLast() checked with 4 nodes in the list
    @Test
    public void givenListWithFourNodes_whenGetLast_thenReturnLastNode(){
        CustomLinkedList<String> list = new CustomLinkedList<>();

        list.add(0,"I AM");
        list.add(1,"MUSIC");
        list.add(2,"i2");
        list.add(3,"i3");

        assertEquals("i3", list.getLast());
    }

    // get(index) checked with 4 nodes in the list
    @Test
    public void givenListWithFourNodes_whenGetFourNodes_thenReturnFourNodes(){
        CustomLinkedList<String> list = new CustomLinkedList<>();

        list.add(0,"I AM");
        list.add(1,"MUSIC");
        list.add(2,"i2");
        list.add(3,"i3");

        assertEquals("I AM", list.get(0));
        assertEquals("MUSIC", list.get(1));
        assertEquals("i2", list.get(2));
        assertEquals("i3", list.get(3));
    }

    // removeFirst() checked with 4 nodes in the list. Size should change from 4 to 3.
    @Test
    public void givenListWithFourNodes_whenRemoveFirstNode_thenSizeIs3AndNodesShifted(){
        CustomLinkedList<String> list = new CustomLinkedList<>();

        list.add(0,"I AM");
        list.add(1,"MUSIC");
        list.add(2,"i2");
        list.add(3,"i3");

        list.removeFirst();

        assertEquals(3, list.size());
        assertEquals("MUSIC", list.get(0));
        assertEquals("i2", list.get(1));
        assertEquals("i3", list.get(2));
    }

    // removeLast() checked with 4 nodes in the list. Size should change from 4 to 3.
    @Test
    public void givenListWithFourNodes_whenRemoveLastNode_thenSizeIs3(){
        CustomLinkedList<String> list = new CustomLinkedList<>();

        list.add(0,"I AM");
        list.add(1,"MUSIC");
        list.add(2,"i2");
        list.add(3,"i3");

        list.removeLast();

        assertEquals(3, list.size());
        assertEquals("I AM", list.get(0));
        assertEquals("MUSIC", list.get(1));
        assertEquals("i2", list.get(2));
    }

    // remove(index) checked with 4 nodes in the list. Size should change from 4 to 3.
    @Test
    public void givenListWithFourNodes_whenRemoveNodeAtIndex2_thenSizeIs3AndNodesShifted(){
        CustomLinkedList<String> list = new CustomLinkedList<>();

        list.add(0,"I AM");
        list.add(1,"MUSIC");
        list.add(2,"i2");
        list.add(3,"i3");

        list.remove(2);

        assertEquals(3, list.size());
        assertEquals("I AM", list.get(0));
        assertEquals("MUSIC", list.get(1));
        assertEquals("i3", list.get(2));
    }
}