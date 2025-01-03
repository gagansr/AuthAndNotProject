package com.gagan.bookstore.catalog.domain;

import com.gagan.bookstore.catalog.ApplicationProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ApplicationProperties applicationProperties;

    public PagedResult<Product> getAllProducts(int pageNo){
        Sort sort = Sort.by("code").ascending();
        pageNo = pageNo <= 1 ? 0 : pageNo-1;
        Pageable productPageable = PageRequest.of(pageNo, applicationProperties.pageSize(), sort);
        Page<Product> productPage = productRepository.findAll(productPageable).map(ProductMapper::toProduct);
        return new PagedResult<>(
                productPage.getContent(),
                productPage.getTotalElements(),
                productPage.getNumber()+1,
                productPage.getTotalPages(),
                productPage.isFirst(),
                productPage.isLast(),
                productPage.hasNext(),
                productPage.hasPrevious()
        );
    }
    public Optional<Product> getProductByCode(String code){
        return productRepository.findByCode(code).map(ProductMapper::toProduct);
    }
}
