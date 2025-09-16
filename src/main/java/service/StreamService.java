package service;

import model.Student;

import java.util.*;
import java.util.stream.Collectors;


import static java.util.stream.Collectors.toSet;
import static mock.data.StudentMockData.STUDENTS;

public class StreamService {

    public void demonstrate() {
        List<Student> lstStuName = STUDENTS.stream().filter(dt -> dt.getFirstName().startsWith("A"))
                .collect(Collectors.toList());
        //        System.out.println(lstStuName);
        Map<String, List<Student>> groupedByDepartment = STUDENTS.stream().collect(Collectors.groupingBy(Student::getDepartmantName));
        //        System.out.println(groupedByDepartment);
        Map<String, Long> countByDepartment = STUDENTS.stream().collect(Collectors.groupingBy(Student::getDepartmantName, Collectors.counting()));
        //        System.out.println(countByDepartment);
        Map<String, Double> avgAgeByGender = STUDENTS.stream().collect(Collectors.groupingBy(Student::getDepartmantName, Collectors.averagingInt(Student::getAge)));
        //        System.out.println(avgAgeByGender);
        Map.Entry<String, Long> biggestDepartment = STUDENTS.stream().collect(Collectors.groupingBy(Student::getDepartmantName, Collectors.counting()))
                .entrySet().stream().max(Map.Entry.comparingByValue()).get();
//        System.out.println(biggestDepartment);

        Map<String, Double> avgAgeByGenderSorted = STUDENTS.stream().collect(Collectors.groupingBy(Student::getDepartmantName, TreeMap::new, Collectors.averagingInt(Student::getAge)));
        System.out.println(avgAgeByGender);
        System.out.println(avgAgeByGenderSorted);
        //sorting of map by keys using java 8


        //odd even collection in different list using java 8
        Integer []intArr={1,2,3,4,5,6,7,8,9};
//        Map<Boolean, List<Integer>>map=Arrays.stream(intArr).collect(Collectors.partitioningBy(x->x%2==0));

        Map<Boolean, Set<Integer>>oddEvenMap=Arrays.stream(intArr).collect(Collectors.groupingBy(x->x%2==0,toSet()));
        System.out.println(oddEvenMap);



    }
}
