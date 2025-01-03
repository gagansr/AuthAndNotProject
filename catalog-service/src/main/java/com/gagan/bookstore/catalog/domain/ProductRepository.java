package com.gagan.bookstore.catalog.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

interface ProductRepository extends JpaRepository<ProductEntity, Integer> {

    Optional<ProductEntity> findByCode(String code);
}
