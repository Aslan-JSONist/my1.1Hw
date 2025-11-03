package com.mipt.aslanfarajov.CollectionsClasses;

import java.util.*;

public class CollectionPerformanceTester {

    public static void main(String[] args) {
        int elementCount = 10000;

        System.out.println("Сравнение производительности ArrayList vs LinkedList");
        System.out.println("Количество элементов: " + elementCount);
        System.out.println("==================================================");
        System.out.printf("%-20s %-15s %-15s%n", "Операция", "ArrayList(ms)", "LinkedList(ms)");
        System.out.println("--------------------------------------------------");

        testAddToEnd(elementCount);
        testAddToBeginning(elementCount);
        testInsertInMiddle(elementCount);
        testRandomAccess(elementCount);
        testRemoveFromBeginning(elementCount);
        testRemoveFromEnd(elementCount);
    }

    private static void testAddToEnd(int count) {
        List<Integer> arrayList = new ArrayList<>();
        List<Integer> linkedList = new LinkedList<>();

        long startTime = System.currentTimeMillis();
        for (int i = 0; i < count; i++) {
            arrayList.add(i);
        }
        long arrayListTime = System.currentTimeMillis() - startTime;

        startTime = System.currentTimeMillis();
        for (int i = 0; i < count; i++) {
            linkedList.add(i);
        }
        long linkedListTime = System.currentTimeMillis() - startTime;

        System.out.printf("%-20s %-15d %-15d%n", "Добавление в конец", arrayListTime, linkedListTime);
    }

    private static void testAddToBeginning(int count) {
        List<Integer> arrayList = new ArrayList<>();
        List<Integer> linkedList = new LinkedList<>();

        long startTime = System.currentTimeMillis();
        for (int i = 0; i < count; i++) {
            arrayList.add(0, i);
        }
        long arrayListTime = System.currentTimeMillis() - startTime;

        startTime = System.currentTimeMillis();
        for (int i = 0; i < count; i++) {
            linkedList.add(0, i);
        }
        long linkedListTime = System.currentTimeMillis() - startTime;

        System.out.printf("%-20s %-15d %-15d%n", "Добавление в начало", arrayListTime, linkedListTime);
    }

    private static void testInsertInMiddle(int count) {
        List<Integer> arrayList = new ArrayList<>();
        List<Integer> linkedList = new LinkedList<>();

        for (int i = 0; i < count; i++) {
            arrayList.add(i);
            linkedList.add(i);
        }

        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            arrayList.add(arrayList.size() / 2, i);
        }
        long arrayListTime = System.currentTimeMillis() - startTime;

        startTime = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            linkedList.add(linkedList.size() / 2, i);
        }
        long linkedListTime = System.currentTimeMillis() - startTime;

        System.out.printf("%-20s %-15d %-15d%n", "Вставка в середину", arrayListTime, linkedListTime);
    }

    private static void testRandomAccess(int count) {
        List<Integer> arrayList = new ArrayList<>();
        List<Integer> linkedList = new LinkedList<>();

        for (int i = 0; i < count; i++) {
            arrayList.add(i);
            linkedList.add(i);
        }

        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            arrayList.get(i % count);
        }
        long arrayListTime = System.currentTimeMillis() - startTime;

        startTime = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            linkedList.get(i % count);
        }
        long linkedListTime = System.currentTimeMillis() - startTime;

        System.out.printf("%-20s %-15d %-15d%n", "Доступ по индексу", arrayListTime, linkedListTime);
    }

    private static void testRemoveFromBeginning(int count) {
        List<Integer> arrayList = new ArrayList<>();
        List<Integer> linkedList = new LinkedList<>();

        for (int i = 0; i < count; i++) {
            arrayList.add(i);
            linkedList.add(i);
        }

        long startTime = System.currentTimeMillis();
        while (!arrayList.isEmpty()) {
            arrayList.remove(0);
        }
        long arrayListTime = System.currentTimeMillis() - startTime;

        startTime = System.currentTimeMillis();
        while (!linkedList.isEmpty()) {
            linkedList.remove(0);
        }
        long linkedListTime = System.currentTimeMillis() - startTime;

        System.out.printf("%-20s %-15d %-15d%n", "Удаление из начала", arrayListTime, linkedListTime);
    }

    private static void testRemoveFromEnd(int count) {
        List<Integer> arrayList = new ArrayList<>();
        List<Integer> linkedList = new LinkedList<>();

        for (int i = 0; i < count; i++) {
            arrayList.add(i);
            linkedList.add(i);
        }

        long startTime = System.currentTimeMillis();
        while (!arrayList.isEmpty()) {
            arrayList.remove(arrayList.size() - 1);
        }
        long arrayListTime = System.currentTimeMillis() - startTime;

        startTime = System.currentTimeMillis();
        while (!linkedList.isEmpty()) {
            linkedList.remove(linkedList.size() - 1);
        }
        long linkedListTime = System.currentTimeMillis() - startTime;

        System.out.printf("%-20s %-15d %-15d%n", "Удаление из конца", arrayListTime, linkedListTime);
    }
}
