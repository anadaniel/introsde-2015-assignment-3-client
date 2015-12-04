package client;

import java.util.List;

import introsde.assignment.soap.ws.PeopleService;
import introsde.assignment.soap.ws.People;
import introsde.assignment.soap.ws.Person;

public class PeopleClient{
  public static void main(String[] args) throws Exception {
    PeopleService service = new PeopleService();
    People people = service.getPeopleImplPort();
    Person person = people.readPerson(1);
    System.out.println("Result ==> " + person.getFirstname());
  }
}