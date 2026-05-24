package ru.brickly.core.util;

import lombok.experimental.UtilityClass;
import ru.brickly.core.dto.ListingImageDefaultDTO;
import ru.brickly.core.entity.ListingImage;

@UtilityClass
public class ListingImageMapper {
    public ListingImageDefaultDTO convertToDefaultDto(ListingImage listingImage) {
        ListingImageDefaultDTO listingImageDefaultDTO = new ListingImageDefaultDTO();
        listingImageDefaultDTO.setId(listingImage.getId());
        listingImageDefaultDTO.setImagePath(listingImage.getImagePath());
        listingImageDefaultDTO.setPositionId(listingImage.getPositionId());
        return listingImageDefaultDTO;
    }
}
