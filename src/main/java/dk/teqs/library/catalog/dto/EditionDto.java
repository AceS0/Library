package dk.teqs.library.catalog.dto;

public record EditionDto(Long id, String editionNumber, int year, String fromat, PublisherDto publisher) {
}
