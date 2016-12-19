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
            if (!"id".equals(attribute[0])) {
                Person personObject = new Person(attribute[0], attribute[1], attribute[2], attribute[3], attribute[4], attribute[5]);
                people.add(personObject);
            }
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

                offsetInt = Integer.parseInt(offsetString);
                List<Person> offsetList;

                offsetList = people.subList(offsetInt,(offsetInt + 20));


                HashMap peopleList = new HashMap();
                peopleList.put("peopleArray", offsetList);

                if(offsetInt+21 < people.size()) {
                    peopleList.put("nextOffset", offsetInt + 20);
                }

                if(offsetInt+-20 >= 0) {
                    peopleList.put("previousOffset", (offsetInt + -20));
                }

                return new ModelAndView(peopleList, "peopleweb.html");
            }),//request response
            new MustacheTemplateEngine()
    );//spark get

    Spark.get(
           "/person",
            ((request, response) -> {
                HashMap personHash = new HashMap<>();
                String idString = request.queryParams("id");
                int id = Integer.parseInt(idString);
                Person person = null;
                for (Person p : people) {
                    if(p.getId().equals(idString)){
                        person = p;
                    }
                }
                personHash.put("person", person);
                return new ModelAndView(personHash, "person.html");

            }),//request response
        new MustacheTemplateEngine()

    ); //Spark.get
  }//main method
}//class main
