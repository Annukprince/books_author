package org.example.dto;

import lombok.*;


public record BookDto(Long id,String title,AuthorDto author) {

}
