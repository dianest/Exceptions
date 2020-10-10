package ru.netology.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Book;
import ru.netology.domain.Product;
import ru.netology.exceptions.NotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class ProductRepositoryTest {
  Book first = new Book(1, "Война и мир", 100, "Толстой", 2000, 1850);
  Book second = new Book(2, "Код Да Винчи", 200, "Ден Браун", 500, 2000);

  @Test
  public void shouldSaveOneItem() {
    ProductRepository repository = new ProductRepository();
    repository.save(first);

    Product[] expected = new Product[]{first};
    Product[] actual = repository.findAll();
    assertArrayEquals(expected, actual);
  }

  @Test
  void removeByIdSuccessful() {
    ProductRepository repository = new ProductRepository();
    repository.save(first);
    repository.save(second);

    Product[] expected = new Product[]{first};
    repository.removeById(2);

    assertArrayEquals(expected, repository.findAll());
  }

  @Test
  void removeByIdFailure() {
    ProductRepository repository = new ProductRepository();
    repository.save(first);
    repository.save(second);

    Assertions.assertThrows(NotFoundException.class, () -> {
      repository.removeById(3);
    });
  }
}
