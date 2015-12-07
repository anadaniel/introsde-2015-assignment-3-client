package client;

import java.util.List;
import java.io.PrintWriter;
import java.util.GregorianCalendar;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import introsde.assignment.soap.ws.PeopleService;
import introsde.assignment.soap.ws.People;
import introsde.assignment.soap.ws.Person;
import introsde.assignment.soap.ws.Measure;

public class PeopleClient{
  private static PrintWriter logWriter;

  public static void main(String[] args) throws Exception {

    PeopleService service = new PeopleService();
    People people = service.getPeopleImplPort();
    logWriter = new PrintWriter("client.log", "UTF-8");

    logWriter.println("====================== SERVER WSDL URL ======================");
    logWriter.println("https://anadaniel-introsde-a03.herokuapp.com/ws/people?wsdl");
    logWriter.println();

    // Method #1
    logWriter.println(">>>>> Method #1: readPersonList() <<<<<");
    List<Person> pList = people.getPeopleList();
    for(Person person : pList){
      printPersonDetails(person);
    }

    // Method #2
    logWriter.println(">>>>> Method #2: readPerson(1) <<<<<");
    Person person = people.readPerson(1);
    printPersonDetails(person);

    // Method #3
    logWriter.println(">>>>> Method #3: updatePerson(1, person) <<<<<");
    Person editedPerson = new Person();
    editedPerson.setFirstname("Ana Soap");
    Person updatedPerson = people.updatePerson(1, editedPerson);
    printPersonDetails(updatedPerson);

    // Method #4
    logWriter.println(">>>>> Method #4: createPerson(person) <<<<<");

    // Create new Person object
    Person newPerson = new Person();
    newPerson.setFirstname("Newie");
    newPerson.setLastname("Peep");

    GregorianCalendar c = new GregorianCalendar(1990,01,01);
    XMLGregorianCalendar gregorianBirthdate = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
    newPerson.setBirthdate(gregorianBirthdate);

    // Create measures
    Measure m1 = new Measure();
    m1.setMeasureName("height");
    m1.setValue("180");
    Measure m2 = new Measure();
    m2.setMeasureName("weight");
    m2.setValue("80");

    // Set measures to healthprofile
    Person.HealthProfile hp = new Person.HealthProfile();
    hp.getMeasure().add(m1);
    hp.getMeasure().add(m2);
    newPerson.setHealthProfile(hp);

    Person createdPerson = people.createPerson(newPerson);
    printPersonDetails(createdPerson);

    // Method #5
    logWriter.println(">>>>> Method #5: deletePerson(" + createdPerson.getPersonId()  + ") <<<<<");
    people.deletePerson(createdPerson.getPersonId());
    logWriter.println();

    // Method #6
    logWriter.println(">>>>> Method #6: getPersonHistory(1, 'height') <<<<<");
    List<Measure> personMeasures = people.getPersonHistory(1, "height");
    for( Measure m : personMeasures ){
      printMeasure(m);
      logWriter.println();
    }

    // Save a measure Id
    int measureId = personMeasures.get(0).getMid();

    // Method #7
    logWriter.println(">>>>> Method #7: getMeasureTypes() <<<<<");
    List<String> measureTypes = people.getMeasureTypes();
    for( String measureType : measureTypes ){
      logWriter.println(">> " + measureType);
    }
    logWriter.println();

    // Method #8
    logWriter.println(">>>>> Method #8: getMeasure(" + measureId + ") <<<<<");
    Measure measure = people.getMeasure(measureId);
    printMeasure(measure);
    logWriter.println();

    // Method #9
    logWriter.println(">>>>> Method #9: createMeasure(measure, 1, weight) <<<<<");
    Measure newMeasure = new Measure();
    newMeasure.setValue("58");
    Measure createdMeasure = people.createMeasure(newMeasure, 1, "weight");
    printMeasure(createdMeasure);
    logWriter.println();

    // Method #10
    logWriter.println(">>>>> Method #10: updateMeasure(" + createdMeasure.getMid() + ", measure) <<<<<");
    createdMeasure.setValue("59");
    Measure updatedMeasure = people.updateMeasure(createdMeasure.getMid(), createdMeasure);
    printMeasure(updatedMeasure);
    logWriter.println();

    // Method #6
    logWriter.println(">>>>> Method #6: getPersonHistory(1, 'weight') <<<<<");
    personMeasures = people.getPersonHistory(1, "weight");
    for( Measure m : personMeasures ){
      printMeasure(m);
      logWriter.println();
    }

    logWriter.close();
  }

  public static void printPersonDetails(Person person) {
    logWriter.println("Person with id: " + person.getPersonId());
    logWriter.println("> Firstname: " + person.getFirstname());
    logWriter.println("> Lastname: " + person.getLastname());
    logWriter.println("> Birthdate: " + person.getBirthdate());
    logWriter.println("> HealthProfile: ");
    printHealthProfile(person.getHealthProfile());
    logWriter.println();
  }

  public static void printHealthProfile(Person.HealthProfile healthProfile) {
    for(Measure measure : healthProfile.getMeasure()){
      printMeasure(measure);
    }
  }

  public static void printMeasure(Measure measure) {
    logWriter.println(">> mid: " + measure.getMid() + " | " + measure.getMeasureName() + " = " + measure.getValue() );
  }
}