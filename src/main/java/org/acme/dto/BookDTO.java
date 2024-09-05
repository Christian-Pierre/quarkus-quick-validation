package org.acme.dto;

import jakarta.validation.constraints.NotBlank;

public class BookDTO {
    @NotBlank(message="Title may not be blank")
    public String title;

    @NotBlank(message="Author may not be blank")
    public String author;
}
