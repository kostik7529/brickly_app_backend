package ru.brickly.core.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.brickly.core.dto.ListingCreateRequest;
import ru.brickly.core.dto.ListingDefaultDTO;
import ru.brickly.core.dto.ListingUpdateDTO;
import ru.brickly.core.service.ListingService;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/app/listings")
public class ListingController {
    private final ListingService listingService;

    @GetMapping("/by_id/{id}")
    public ResponseEntity<ListingDefaultDTO> getListingById(@PathVariable long id) {
        return ResponseEntity.ok(listingService.getListingById(id));
    }

    @GetMapping("/paginated")
    public ResponseEntity<Page<ListingDefaultDTO>> getListingsPaginated(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(listingService.getListingsPaginated(pageable));
    }

    @GetMapping("/by_status/{status}")
    public ResponseEntity<Page<ListingDefaultDTO>> getListingsByStatus(@PathVariable String status, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(listingService.getListingsByStatusPaginated(status, pageable));
    }

    @GetMapping("/by_item_id/{itemId}")
    public ResponseEntity<Page<ListingDefaultDTO>> getListingsByItemIdPaginated(@PathVariable String itemId, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(listingService.getListingsByItemIdPaginated(itemId, pageable));
    }

    @GetMapping("/by_description_containing/{descriptionContaining}")
    public ResponseEntity<Page<ListingDefaultDTO>> getListingsByDescriptionContainingPaginated(@PathVariable String descriptionContaining, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(listingService.getListingsByDescriptionContainingPaginated(descriptionContaining, pageable));
    }

    @GetMapping("/by_seller_id/{sellerId}")
    public ResponseEntity<Page<ListingDefaultDTO>> getListingsBySellerIdPaginated(@PathVariable long sellerId, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(listingService.getListingsBySellerIdPaginated(sellerId, pageable));
    }

    @GetMapping("/by_description_or_itemId_containing/{stringContained}")
    public ResponseEntity<Page<ListingDefaultDTO>> getListingsByDescriptionContainingOrItemIdContainingPaginated(@PathVariable String stringContained, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "30") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(listingService.getListingsByDescriptionContainingOrItemIdContainingPaginated(stringContained, pageable));
    }

    @PostMapping(path = "/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ListingDefaultDTO> createListing(@ModelAttribute ListingCreateRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(listingService.createListing(request));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ListingDefaultDTO> updateListing(@PathVariable long id, @RequestBody ListingUpdateDTO dto) {
        return ResponseEntity.ok(listingService.updateListing(id, dto));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteListingById(@PathVariable long id) {
        listingService.deleteListingById(id);
        return ResponseEntity.noContent().build();
    }
}
