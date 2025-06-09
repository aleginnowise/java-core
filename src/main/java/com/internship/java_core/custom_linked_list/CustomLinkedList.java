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

        if (firstNode == null && index == 0) {
            firstNode = newNode;
            lastNode = newNode;

            size++;
            return true;
        }
        if (index == 0) {
            addFirst(value);
            return true;
        }
        if (index == size) {
            addLast(value);
            return true;
        }

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

        if (firstNode.nextNode != null) {
            nodeAfterCurrent = firstNode.nextNode;
            nodeAfterCurrent.prevNode = null;
            firstNode = nodeAfterCurrent;
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

        if (index == 0) {
            return removeFirst();
        }
        if (index == size - 1) {
            return removeLast();
        }

        Node<E> currrentNode = findNodeByIndex(index);
        final E value = currrentNode.value;

        Node<E> nodeBeforeCurrent = currrentNode.prevNode;
        Node<E> nodeAfterCurrent = currrentNode.nextNode;

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
