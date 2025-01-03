package com.gagan.bookstore.catalog.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.List;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

@DataJpaTest(
        properties = {
            "spring.test.database.replace=none",
            "spring.datasource.url=jdbc:tc:postgresql:16-alpine:///db",
        })
// @Import(TestcontainersConfiguration.class)
@Sql("/test-data.sql")
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void shouldGetAllProducts() {
        List<ProductEntity> products = productRepository.findAll();
        assertThat(products).hasSize(15);
    }

    @Test
    public void shouldGetProductByCode() {
        ProductEntity product = productRepository.findByCode("P001").orElseThrow();
        assertThat(product.getCode()).isEqualTo("P100");
        assertThat(product.getDescription()).isEqualTo("Winning will make you famous. Losing means certain death...");
        assertThat(product.getName()).isEqualTo("The Hunger Games");
        assertThat(product.getImageUrl()).isEqualTo("https://images.gr-assets.com/books/1447303603l/2767052.jpg");
        assertThat(product.getPrice()).isEqualTo(new BigDecimal("34.0"));
    }

    @Test
    public void shouldReturnEmptyWhenProductCodeNotExists() {
        assertThat(productRepository.findByCode("invalid_code")).isEmpty();
    }
}
