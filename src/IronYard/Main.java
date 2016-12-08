package IronYard;
import spark.ModelAndView;
import spark.Spark;
import spark.template.mustache.MustacheTemplateEngine;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Main {

    static ArrayList<Person> people = new ArrayList<>();

    public static void main(String[] args) throws FileNotFoundException {
        File eF = new File("people.csv");
        Scanner csvRead = new Scanner(eF);
        while (csvRead.hasNext()){
            String individual = csvRead.nextLine();
            String[] attribute = individual.split("\\,");
            Person personObject = new Person(attribute[0],attribute[1],attribute[2],attribute[3],attribute[4],attribute[5]);
            people.add(personObject);
        }//while

    Spark.init();

    Spark.get(
            "/",
            ((request, response) -> {
//                String previousIndex = request.queryParams("previousIndex");
//                String nextIndex = request.queryParams("nextIndex");
//                int previousIndexNum = Integer.parseInt(previousIndex+-20);
//                int nextIndexNumb = Integer.parseInt(nextIndex+20);
                List<Person> twentyPer = new ArrayList<>();
                twentyPer = people.subList(0,20);

                HashMap peopleList = new HashMap();
                peopleList.put("peopleArray", twentyPer);
                return new ModelAndView(peopleList, "peopleweb.html");
            }),//request response
            new MustacheTemplateEngine()
            );//spark get
//    Spark.get(
//            "/person",
//
//    )
    }//main method
}//class main
