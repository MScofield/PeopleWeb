package IronYard;
import spark.ModelAndView;
import spark.Spark;
import spark.template.mustache.MustacheTemplateEngine;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

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

                String offsetString;
                int offsetInt;
                offsetString = request.queryParams("offset");
                if (offsetString == null){
                    offsetString = "0";

                }

//                if (fromIndex >= listLength || toIndex <= 0 || fromIndex >= toIndex){
//
//                }
                offsetInt = Integer.parseInt(offsetString);
                List<Person> offsetList;
//
//                fromIndex = Math.max(0, fromIndex);
//                toIndex = Math.min(listLength, toIndex);

                offsetList = people.subList(offsetInt,(offsetInt + 20));


                HashMap peopleList = new HashMap();
                peopleList.put("peopleArray", offsetList);

                peopleList.put("nextOffset", (offsetInt + 20));
                peopleList.put("previousOffset", (offsetInt +- 20));
                return new ModelAndView(peopleList, "peopleweb.html");
            }),//request response
            new MustacheTemplateEngine()
            );//spark get

        //new route to follow:
//    Spark.get(
//            "/person",
//            ((request, response) -> {
//  String idString = request.queryParams("id");
//        int id = Integer.parseInt(idString);
//                Person person = null;
//                        for (Person p : people) {
//    if Person p =
//        }
//    )
    }//main method
}//class main
