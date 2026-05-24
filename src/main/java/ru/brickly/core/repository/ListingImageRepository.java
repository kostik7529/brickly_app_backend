package ru.brickly.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.brickly.core.entity.ListingImage;

@Repository
public interface ListingImageRepository extends JpaRepository<ListingImage, Long>  {
}
