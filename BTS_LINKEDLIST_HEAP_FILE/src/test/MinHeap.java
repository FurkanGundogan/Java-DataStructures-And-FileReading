package test;

/**
 *
 * @file MinHeap.java
 * @description Kullandığım heapler için oluşturulan sınıf
 * @assignment Ödev-2
 * @date 26.05.2020
 * @author Furkan Gündoğan furkan.gundogan@stu.fsm.edu.tr
 * 
 */
public class MinHeap<T extends Comparable<T>> {

    private T[] heap;
    private int size;

    public MinHeap(int capacity) {
        this.heap = (T[]) new Comparable[capacity];
    }

    private int parent(int i) {
        return (i - 1) / 2;
    }

    private void swap(int i, int j) {
        T temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    void insert(T newData) {
        if (size < heap.length) {
            heap[size] = newData;
            int current = size++;

            while (heap[current].compareTo(heap[parent(current)]) < 0) {
                swap(current, parent(current));
                current = parent(current);
            }
        } else {
            System.out.println("heap is full !");
        }
    }

    void heapify() {
        int lastIndex = size - 1;

        for (int i = parent(lastIndex); i >= 0; i--) {
            minHeap(i);
        }
    }

    private void minHeap(int i) {
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        int min = i;

        if (left < size && heap[min].compareTo(heap[left]) > 0) {
            min = left;
        }
        if (right < size && heap[min].compareTo(heap[right]) > 0) {
            min = right;
        }

        if (min != i) {
            swap(min, i);
            minHeap(min);
        }
    }

    T deleteMin() {
        T deletedElement = heap[0];

        heap[0] = heap[size - 1];
        size--;

        // TODO: control from root to leaf on a specific path, not all tree
        heapify();

        return deletedElement;
    }

    void printArray() {
        for (T element : heap) {
            if(element!=null)System.out.println(element);
            
        }
        System.out.println();
    }
    
    T getDeger(int i){
    return heap[i];
    }

    // TODO: print parent nodes with their left child and right child
    // 0. Seviye    Parent: 4       Left Child: 8       Right Child: 12
    // 1. Seviye    Parent: 8       Left Child: 48      Right Child: 16
    // 1. Seviye    Parent: 12      Left Child: 24      Right Child: 32
    // 2. Seviye    Parent: 48      Left Child: 54      Right Child: 72
    // 2. Seviye    Parent: 16      Left Child: 20      Right Child: -
    void print() {

    }

    // only for Integer MinHeap
    void decreaseKey(int key, int amount) {
        for (int i = 0; i < size; i++) {
            if (heap[i].equals(key)) {
                heap[i] = (T) new Integer(key - amount);

                while (heap[i].compareTo(heap[parent(i)]) < 0) {
                    swap(i, parent(i));
                    i = parent(i);
                }
                break;
            }
        }
    }

    // only for Integer MinHeap
    void increaseKey(int key, int amount) {
        for (int i = 0; i < size; i++) {
            if (heap[i].equals(key)) {
                heap[i] = (T) new Integer(key + amount);

                minHeap(i);
                break;
            }
        }
    }
}