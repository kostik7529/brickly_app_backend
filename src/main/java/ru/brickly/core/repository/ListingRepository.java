package ru.brickly.core.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.brickly.core.entity.Listing;

@Repository
public interface ListingRepository extends JpaRepository<Listing, Long> {
    @EntityGraph(attributePaths = {"listingImages"})
    Page<Listing> findListingByStatus(String status, Pageable pageable);

    @EntityGraph(attributePaths = {"listingImages"})
    Page<Listing> findByItemId(String itemId, Pageable pageable);

    @EntityGraph(attributePaths = {"listingImages"})
    Page<Listing> findByDescriptionContainingIgnoreCase(String descriptionContaining, Pageable pageable);
}
