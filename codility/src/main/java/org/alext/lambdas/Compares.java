package org.alext.lambdas;

import org.alext.lambdas.domain.Person;

import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 * Created by tsumaraa on 05/09/2016.
 */
public class Compares {


    public static void main(int[] args){


        Compares cps=new Compares();
        cps.runCompares();


    }

    public void runCompares() {
        Person p1=new Person("Alex",35);
        Person p2=new Person("Vera",40);
        Person p3=new Person("Dima",28);

        final Function<Person,String> byName= person -> person.getName();
        final Function<Person,Integer> byAge= person -> person.getAge();


        System.out.println("By name:");
        Arrays.asList(p1,p2,p3).stream().sorted(Comparator.comparing(byName)).forEach(System.out::println);
        System.out.println("By age:");
        Arrays.asList(p1,p2,p3).stream().sorted(Comparator.comparing(byAge)).forEach(System.out::println);
    }

}
