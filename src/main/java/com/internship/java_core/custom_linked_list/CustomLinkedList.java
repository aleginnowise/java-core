package com.internship.java_core.custom_linked_list;

import java.util.NoSuchElementException;

public class CustomLinkedList<E> {
    private int size;
    private Node<E> firstNode;
    private Node<E> lastNode;

    public int size() {
        return size;
    }

    public void addFirst(E value) {
        Node<E> currentFirstNode = firstNode;
        Node<E> newNode = new Node<>(null, value, currentFirstNode);
        firstNode = newNode;

        // If there is no first node, the new node becomes the only node in the list.
        if (currentFirstNode == null)
            lastNode = newNode;
        else
            currentFirstNode.prevNode = newNode;

        size++;
    }

    public void addLast(E value) {
        Node<E> currentLastNode = lastNode;
        Node<E> newNode = new Node<>(currentLastNode, value, null);
        lastNode = newNode;

        // If there is no last node, the new node becomes the only node in the list.
        if (currentLastNode == null)
            firstNode = newNode;
        else
            currentLastNode.nextNode = newNode;

        size++;
    }

    public boolean add(int index, E value) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index is greater size. Index: " + index + ", Size: " + size);
        }

        Node<E> newNode = new Node<>(null, value, null);

        // If there is no first node, the new node becomes the only node in the list.
        if (firstNode == null && index == 0) {
            firstNode = newNode;
            lastNode = newNode;

            size++;
            return true;
        }
        // If index is 0, the new node will be first node
        if (index == 0) {
            addFirst(value);
            return true;
        }
        // If index equals size, the new node will be last node
        if (index == size) {
            addLast(value);
            return true;
        }

        // Find node by index and change linking
        Node<E> currentNode = findNodeByIndex(index);
        Node<E> previousNode = currentNode.prevNode;

        previousNode.nextNode = newNode;
        currentNode.prevNode = newNode;

        newNode.prevNode = previousNode;
        newNode.nextNode = currentNode;

        size++;
        return true;
    }

    public E getFirst() {
        if (firstNode == null) {
            throw new NoSuchElementException();
        }
        return firstNode.value;
    }

    public E getLast() {
        if (lastNode == null) {
            throw new NoSuchElementException();
        }
        return lastNode.value;
    }

    public E get(int index) {
        return findNodeByIndex(index).value;
    }

    public E removeFirst() {
        Node<E> currentNode = new Node<>(null, getFirst(), null);
        Node<E> nodeAfterCurrent;

        // If the second node exists, linking will be edited
        if (firstNode.nextNode != null) {
            nodeAfterCurrent = firstNode.nextNode;
            nodeAfterCurrent.prevNode = null;
            firstNode = nodeAfterCurrent;
        // If the first is the only one, just delete it
        } else {
            firstNode = null;
        }

        size--;
        return currentNode.value;
    }

    public E removeLast() {
        Node<E> currentNode = new Node<>(null, getLast(), null);
        Node<E> nodeBeforeCurrent = lastNode.prevNode;
        nodeBeforeCurrent.nextNode = null;
        lastNode = nodeBeforeCurrent;

        size--;
        return currentNode.value;
    }

    public E remove(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index is greater size. Index: " + index + ", Size: " + size);
        }

        // If index is 0, return and remove first node
        if (index == 0) {
            return removeFirst();
        }
        // If index equals size - 1, return and remove last node
        if (index == size - 1) {
            return removeLast();
        }

        Node<E> currrentNode = findNodeByIndex(index);
        final E value = currrentNode.value;

        Node<E> nodeBeforeCurrent = currrentNode.prevNode;
        Node<E> nodeAfterCurrent = currrentNode.nextNode;

        // Unlink current node by connecting its previous and next nodes
        nodeBeforeCurrent.nextNode = nodeAfterCurrent;
        nodeAfterCurrent.prevNode = nodeBeforeCurrent;

        currrentNode.prevNode = null;
        currrentNode.value = null;
        currrentNode.nextNode = null;

        size--;
        return value;
    }

    private Node<E> findNodeByIndex(int index) {
        Node<E> currentNode = firstNode;

        if (currentNode == null && index == 0) {
            throw new NoSuchElementException();
        }
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index is greater or equal size. Index: " + index + ", Size: " + size);
        }
        if (index == 0) {
            return currentNode;
        }
        if (index == size - 1) {
            return lastNode;
        }

        int mid = size / 2;
        if (index <= mid) {
            for (int i = 0; i < index; i++) {
                assert currentNode != null;
                currentNode = currentNode.nextNode;
            }
        } else {
            currentNode = lastNode;
            for (int i = size - 1; i > index; i--) {
                currentNode = currentNode.prevNode;
            }
        }
        return currentNode;
    }

    private static class Node<E> {
        Node<E> prevNode;
        E value;
        Node<E> nextNode;

        public Node(Node<E> prevNode, E value, Node<E> nextNode) {
            this.prevNode = prevNode;
            this.value = value;
            this.nextNode = nextNode;
        }
    }
}
