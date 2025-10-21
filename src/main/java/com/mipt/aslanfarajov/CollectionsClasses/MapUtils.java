package com.mipt.aslanfarajov.CollectionsClasses;
import java.util.*;

public class MapUtils {

    public static List<Student> findStudentsByGradeRange(Map<Integer, Student> map,
                                                         double minGrade, double maxGrade) {
        List<Student> result = new ArrayList<>();
        for (Student student : map.values()) {
            if (student.getGrade() >= minGrade && student.getGrade() <= maxGrade) {
                result.add(student);
            }
        }
        return result;
    }

    public static List<Student> getTopNStudents(TreeMap<Integer, Student> map, int n) {
        List<Student> result = new ArrayList<>();
        int count = 0;

        for (Student student : map.descendingMap().values()) {
            if (count++ >= n) break;
            result.add(student);
        }
        return result;
    }

    public static void main(String[] args) {
        Map<Integer, Student> hashMap = new HashMap<>();
        hashMap.put(1, new Student(1, "Иван", 4.5));
        hashMap.put(2, new Student(2, "Мария", 3.8));
        hashMap.put(3, new Student(3, "Петр", 4.2));
        hashMap.put(4, new Student(4, "Анна", 4.8));

        TreeMap<Integer, Student> treeMap = new TreeMap<>(Collections.reverseOrder());
        treeMap.putAll(hashMap);

        System.out.println("Студенты с оценкой от 4.0 до 4.5:");
        List<Student> gradeRangeStudents = findStudentsByGradeRange(hashMap, 4.0, 4.5);
        gradeRangeStudents.forEach(System.out::println);

        System.out.println("\nТоп 2 студента по id:");
        List<Student> topStudents = getTopNStudents(treeMap, 2);
        topStudents.forEach(System.out::println);
    }
}