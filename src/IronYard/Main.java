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

//                String previousIndexNum = request.queryParams("previousIndex");
//                String nextIndexNum = request.queryParams("nextIndex");
//                Arrays.asList(new Person[20]);

                // = new ArrayList<>();
                //= new ArrayList<>();
//                int listLength = people.size();
//                int upIndex;
//                int downIndex;
                String offsetString;
                int offsetInt;

//                String nextIndexString;
//                String previousIndexString;

                offsetString = request.queryParams("offset");
                if (offsetString == null){
                    offsetString = "0";

                }
//                nextIndexString = request.queryParams("offset");
//                previousIndexString = request.queryParams("offset");
//                if (fromIndex >= listLength || toIndex <= 0 || fromIndex >= toIndex){
//
//                }
                offsetInt = Integer.parseInt(offsetString);
//                upIndex = Integer.parseInt(nextIndexString);
//                downIndex = Integer.parseInt(previousIndexString);
                List<Person> offsetList;
//
//                fromIndex = Math.max(0, fromIndex);
//                toIndex = Math.min(listLength, toIndex);

                offsetList = people.subList(offsetInt,(offsetInt + 20));


                //offsetList = ArrayList<Person>(people.subList(downIndex, upIndex));
                //shortList = Arrays.asList(new Person[20]);


                HashMap peopleList = new HashMap();
                peopleList.put("peopleArray", offsetList);

                peopleList.put("nextOffset", (offsetInt + 20));
                peopleList.put("previousOffset", (offsetInt +- 20));
                return new ModelAndView(peopleList, "peopleweb.html");
            }),//request response
            new MustacheTemplateEngine()
            );//spark get
//    Spark.get(
//            "/person",
//  String idString=request
//        int Id parse
//                Person person = null
//                        for Person p : people) {
//    if person p =
//        }
//    )
    }//main method
}//class main
