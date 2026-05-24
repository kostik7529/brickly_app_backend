package ru.brickly.core.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.brickly.core.dto.ListingCreateRequest;
import ru.brickly.core.dto.ListingDefaultDTO;
import ru.brickly.core.dto.ListingUpdateDTO;

public interface ListingService {
    Page<ListingDefaultDTO> getListingsPaginated(Pageable pageable);

    ListingDefaultDTO getListingById(Long id);

    Page<ListingDefaultDTO> getListingsByStatusPaginated(String status, Pageable pageable);

    Page<ListingDefaultDTO> getListingsByItemIdPaginated(String itemId, Pageable pageable);

    Page<ListingDefaultDTO> getListingsByDescriptionContainingPaginated(String descriptionContaining, Pageable pageable);

    ListingDefaultDTO createListing(ListingCreateRequest request);

    ListingDefaultDTO updateListing(Long id, ListingUpdateDTO dto);

    void deleteListingById(Long id);
}
