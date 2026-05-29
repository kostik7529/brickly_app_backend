package ru.brickly.core.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.brickly.core.dto.ListingCreateRequest;
import ru.brickly.core.dto.ListingDefaultDTO;
import ru.brickly.core.dto.ListingUpdateDTO;
import ru.brickly.core.entity.Listing;
import ru.brickly.core.entity.ListingImage;
import ru.brickly.core.entity.User;
import ru.brickly.core.exception.ListingNotFoundException;
import ru.brickly.core.exception.UserNotFoundException;
import ru.brickly.core.repository.ListingRepository;
import ru.brickly.core.repository.ListingImageRepository;
import ru.brickly.core.repository.UserRepository;
import ru.brickly.core.service.ImageStorageService;
import ru.brickly.core.service.ListingService;
import ru.brickly.core.util.ListingMapper;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class ListingServiceImpl implements ListingService {
    private final ListingRepository listingRepository;
    private final ListingImageRepository listingImageRepository;
    private final ImageStorageService imageStorageService;
    private final UserRepository userRepository;

    @Override
    public Page<ListingDefaultDTO> getListingsPaginated(Pageable pageable) {
        return listingRepository.findAll(pageable).map(ListingMapper::convertToDefaultDto);
    }

    @Override
    public ListingDefaultDTO getListingById(Long id) {
        return ListingMapper.convertToDefaultDto(listingRepository.findById(id).orElseThrow(() -> new ListingNotFoundException("Listing with id " + id + " not found!")));
    }

    @Override
    public Page<ListingDefaultDTO> getListingsByStatusPaginated(String status, Pageable pageable) {
        return listingRepository.findListingByStatus(status, pageable).map(ListingMapper::convertToDefaultDto);
    }

    @Override
    public Page<ListingDefaultDTO> getListingsByItemIdPaginated(String itemId, Pageable pageable) {
        return listingRepository.findByItemId(itemId, pageable).map(ListingMapper::convertToDefaultDto);
    }

    @Override
    public Page<ListingDefaultDTO> getListingsByDescriptionContainingPaginated(String descriptionContaining, Pageable pageable) {
        return listingRepository.findByDescriptionContainingIgnoreCase(descriptionContaining, pageable).map(ListingMapper::convertToDefaultDto);
    }

    @Override
    public Page<ListingDefaultDTO> getListingsBySellerIdPaginated(Long sellerId, Pageable pageable) {
        return listingRepository.findBySellerId(sellerId, pageable).map(ListingMapper::convertToDefaultDto);
    }

    @Override
    public Page<ListingDefaultDTO> getListingsByDescriptionContainingOrItemIdContainingPaginated(String stringContained, Pageable pageable) {
        return listingRepository.findByDescriptionContainingIgnoreCaseOrItemIdContainingIgnoreCase(stringContained, stringContained, pageable).map(ListingMapper::convertToDefaultDto);
    }

    @Override
    public ListingDefaultDTO createListing(ListingCreateRequest request) {
        User seller = userRepository.findById(request.getSellerId()).orElseThrow(() -> new UserNotFoundException("User with id " + request.getSellerId() + " not found!"));
        
        
        Listing listing = new Listing();
        listing.setStatus(request.getStatus());
        listing.setSeller(seller);
        listing.setItemType(request.getItemType());
        listing.setItemId(request.getItemId());
        listing.setQuantity(request.getQuantity());
        listing.setDescription(request.getDescription());
        listing.setCondition(request.getCondition());
        listing.setConditionRate(request.getConditionRate());
        listing.setPrice(request.getPrice());
        listing.setViewsCount(0);
        listing.setListingImages(new ArrayList<>());
        Listing savedListing = listingRepository.save(listing);

        if (request.getImages() != null && !request.getImages().isEmpty()) {
            for (int i = 0; i < request.getImages().size(); i++) {
                MultipartFile file = request.getImages().get(i);

                if (!file.isEmpty()) {
                    String imagePath = imageStorageService.saveListingImage(file);

                    ListingImage listingImage = new ListingImage();
                    listingImage.setListing(listing);
                    listingImage.setPositionId(i);
                    listingImage.setImagePath(imagePath);

                    ListingImage savedImage = listingImageRepository.save(listingImage);

                    savedListing.getListingImages().add(savedImage);
                }
            }
        }

        return ListingMapper.convertToDefaultDto(savedListing);
    }

    @Override
    public ListingDefaultDTO updateListing(Long id, ListingUpdateDTO dto) {
        Listing listing = listingRepository.findById(id).orElseThrow(() -> new ListingNotFoundException("Listing with id " + id + " not found!"));

        if (dto.getDescription() != null) {
            listing.setDescription(dto.getDescription());
        }

        if (dto.getQuantity() != null) {
            listing.setQuantity(dto.getQuantity());
        }

        if (dto.getPrice() != null) {
            listing.setPrice(dto.getPrice());
        }

        if (dto.getViewsCount() != null) {
            listing.setViewsCount(dto.getViewsCount());
        }

        if (dto.getStatus() != null) {
            listing.setStatus(dto.getStatus());
        }

        return ListingMapper.convertToDefaultDto(listingRepository.save(listing));
    }

    @Override
    public void deleteListingById(Long id) {
        Listing listing = listingRepository.findById(id).orElseThrow(() -> new ListingNotFoundException("Listing with id " + id + " not found!"));

        if (listing.getListingImages() != null) {
            for (ListingImage image : listing.getListingImages()) {
                if (image.getImagePath() != null) {
                    imageStorageService.deleteImage(image.getImagePath());
                }
            }
        }

        listingRepository.deleteById(id);
    }
}
