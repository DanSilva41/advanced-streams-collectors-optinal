package spliterators.test;

import domain.Person;
import spliterators.PersonSpliterator;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Spliterator;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class CreatingSpliterator {

    public static void main(String[] args) {

        Path pathPeople = Paths.get("src/resources/people.txt");

        try (Stream<String> lines = Files.lines(pathPeople)) {
            Spliterator<String> linesSpliterator = lines.spliterator();
            Spliterator<Person> personSpliterator = new PersonSpliterator(linesSpliterator);

            Stream<Person> persons = StreamSupport.stream(personSpliterator, false);
            persons.forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
