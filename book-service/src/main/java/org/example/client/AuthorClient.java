package org.example.client;

import org.example.dto.AuthorDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "author-service")
public interface AuthorClient {

    @GetMapping("/{id}")
    AuthorDto getAuthorById(@PathVariable("id") Long id);
}
