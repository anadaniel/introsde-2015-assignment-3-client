package client;

import java.util.List;

import introsde.assignment.soap.ws.PeopleService;
import introsde.assignment.soap.ws.People;
import introsde.assignment.soap.ws.Person;
import introsde.assignment.soap.ws.Measure;

public class PeopleClient{
  public static void main(String[] args) throws Exception {

    PeopleService service = new PeopleService();
    People people = service.getPeopleImplPort();

    // Method #1
    System.out.println(">>>>> Method #1: readPersonList() <<<<<");
    List<Person> pList = people.getPeopleList();
    for(Person person : pList){
      printPersonDetails(person);
    }
  }

  public static void printPersonDetails(Person person) {
    System.out.println("Person with id: " + person.getPersonId());
    System.out.println("> Firstname: " + person.getFirstname());
    System.out.println("> Lastname: " + person.getLastname());
    System.out.println("> Birthdate: " + person.getBirthdate());
    System.out.println("> HealthProfile: ");
    printHealthProfile(person.getHealthProfile());
    System.out.println();
  }

  public static void printHealthProfile(Person.HealthProfile healthProfile){
    for(Measure measure : healthProfile.getMeasure()){
      System.out.println(">> mid: " + measure.getMid() + " | " + measure.getMeasureName() + " = " + measure.getValue() );
    }
  }
}