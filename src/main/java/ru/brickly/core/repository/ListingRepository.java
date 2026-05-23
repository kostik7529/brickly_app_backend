package ru.brickly.core.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.brickly.core.entity.Listing;

@Repository
public interface ListingRepository extends JpaRepository<Listing, Long> {
    Page<Listing> findListingByStatus(String status, Pageable pageable);

    Page<Listing> findByItemId(String itemId, Pageable pageable);

    Page<Listing> findByDescriptionContainingIgnoreCase(String descriptionContaining, Pageable pageable);
}
