package ru.brickly.core.util;

import lombok.experimental.UtilityClass;
import ru.brickly.core.dto.ListingDefaultDTO;
import ru.brickly.core.entity.Listing;

@UtilityClass
public class ListingMapper {
    public ListingDefaultDTO convertToDefaultDto(Listing listing) {
        ListingDefaultDTO listingDefaultDTO = new ListingDefaultDTO();
        listingDefaultDTO.setId(listing.getId());
        listingDefaultDTO.setStatus(listing.getStatus());
        listingDefaultDTO.setItemType(listing.getItemType());
        listingDefaultDTO.setItemId(listing.getItemId());
        listingDefaultDTO.setQuantity(listing.getQuantity());
        listingDefaultDTO.setDescription(listing.getDescription());
        listingDefaultDTO.setCondition(listing.getCondition());
        listingDefaultDTO.setConditionRate(listing.getConditionRate());
        listingDefaultDTO.setPrice(listing.getPrice());
        listingDefaultDTO.setViewsCount(listing.getViewsCount());
        return listingDefaultDTO;
    }
}
