package pl.coderslab;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MockBookService {
    private static Long nextId = 4L;

    @PostMapping("")
    public void addBook(@RequestBody Book book) {
        bookService.add(book);
    }



    private List<Book> list;

    public MockBookService() {
        list = new ArrayList<>();
        list.add(new Book(1L, "9788324631766", "Thinking in Java", "Bruce Eckel", "Helion", "programming"));
        list.add(new Book(2L, "9788324627738", "Rusz głową Java.", "Sierra Kathy, Bates Bert", "Helion", "programming"));
        list.add(new Book(3L, "9780130819338", "Java 2. Podstawy", "Cay Horstmann, Gary Cornell", "Helion", "programming"));
    }

    @Override
    public void update(Book book) {
        if (this.get(book.getId()).isPresent()) {
            int indexOf = list.indexOf(this.get(book.getId()).get());
            list.set(indexOf, book);
        }
    }

    @Override
    public void delete(Long id) {
        if (get(id).isPresent()) {
            list.remove(this.get(id).get());
        }
    }

    @Override
    public Optional<Book> get(Long id) {
        return list.stream().filter(item -> item.getId().equals(id)).findFirst();
    }

    @Override
    public void add(Book book) {
        book.setId(nextId++);
        list.add(book);
    }
}




