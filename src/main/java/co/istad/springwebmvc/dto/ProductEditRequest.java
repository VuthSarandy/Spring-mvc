package co.istad.springwebmvc.dto;

import jakarta.validation.constraints.*;

public record ProductEditRequest(
        @NotNull
        @NotEmpty
        @Size(max=50)
        String name,
        @Positive
        @NotNull
        Double price,
        @NotNull
        @Min(1)
        @Max(200)
        Integer qty
) {
}
