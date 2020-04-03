package spliterators;

import domain.Person;

import java.util.Spliterator;
import java.util.function.Consumer;

public class PersonSpliterator implements Spliterator<Person> {

    private final Spliterator<String> linesSpliterators;

    private String name;
    private int age;
    private String city;

    public PersonSpliterator(Spliterator<String> linesSpliterators) {
        this.linesSpliterators = linesSpliterators;
    }

    @Override
    public boolean tryAdvance(Consumer<? super Person> consumer) {
        if (this.linesSpliterators.tryAdvance(line -> this.name = line)
                && this.linesSpliterators.tryAdvance(line -> this.age = Integer.parseInt(line))
                && this.linesSpliterators.tryAdvance(line -> this.city = line)) {
            Person person = new Person(this.name, this.age, this.city);
            consumer.accept(person);
            return true;

        }
        return false;
    }

    @Override
    public Spliterator<Person> trySplit() {
        return null;
    }

    @Override
    public long estimateSize() {
        return this.linesSpliterators.estimateSize() / 3;
    }

    @Override
    public int characteristics() {
        return this.linesSpliterators.characteristics();
    }
}
