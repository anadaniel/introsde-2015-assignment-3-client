package client;

import java.util.List;
import java.util.GregorianCalendar;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

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

    // Method #2
    System.out.println(">>>>> Method #2: readPerson(1) <<<<<");
    Person person = people.readPerson(1);
    printPersonDetails(person);

    // Method #3
    System.out.println(">>>>> Method #3: updatePerson(1, person) <<<<<");
    Person editedPerson = new Person();
    editedPerson.setFirstname("Ana Soap");
    Person updatedPerson = people.updatePerson(1, editedPerson);
    printPersonDetails(updatedPerson);

    // Method #4
    System.out.println(">>>>> Method #4: createPerson(person) <<<<<");

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
    System.out.println(">>>>> Method #5: deletePerson(" + createdPerson.getPersonId()  + ") <<<<<");
    people.deletePerson(createdPerson.getPersonId());
    System.out.println();

    // Method #6
    System.out.println(">>>>> Method #6: getPersonHistory(1, 'height') <<<<<");
    List<Measure> personMeasures = people.getPersonHistory(1, "height");
    for( Measure measure : personMeasures ){
      printMeasure(measure);
      System.out.println();
    }

    // Save a measure Id
    int measureId = personMeasures.get(0).getMid();

    // Method #7
    System.out.println(">>>>> Method #7: getMeasureTypes() <<<<<");
    List<String> measureTypes = people.getMeasureTypes();
    for( String measureType : measureTypes ){
      System.out.println(">> " + measureType);
    }
    System.out.println();

    // Method #8
    System.out.println(">>>>> Method #8: getMeasure(" + measureId + ") <<<<<");
    Measure measure = people.getMeasure(measureId);
    printMeasure(measure);
    System.out.println();

    // Method #9
    System.out.println(">>>>> Method #9: updateMeasure(" + measureId + ", measure) <<<<<");
    measure.setValue("123");
    Measure updatedMeasure = people.updateMeasure(measure.getMid(), measure);
    printMeasure(updatedMeasure);

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

  public static void printHealthProfile(Person.HealthProfile healthProfile) {
    for(Measure measure : healthProfile.getMeasure()){
      printMeasure(measure);
    }
  }

  public static void printMeasure(Measure measure) {
    System.out.println(">> mid: " + measure.getMid() + " | " + measure.getMeasureName() + " = " + measure.getValue() );
  }
}