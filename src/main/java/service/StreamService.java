package service;

import model.Student;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
        Map<String, Double> avgAgeByGender = STUDENTS.stream().collect(Collectors.groupingBy(Student::getGender, Collectors.averagingInt(Student::getAge)));
        //        System.out.println(avgAgeByGender);
        Map.Entry<String, Long> biggestDepartment = STUDENTS.stream().collect(Collectors.groupingBy(Student::getDepartmantName, Collectors.counting()))
                .entrySet().stream().max(Map.Entry.comparingByValue()).get();
        System.out.println(biggestDepartment);
    }
}
