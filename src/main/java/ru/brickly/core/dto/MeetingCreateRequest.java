package ru.brickly.core.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.time.OffsetDateTime;

@Data
public class MeetingCreateRequest {
    private OffsetDateTime date;
    private String title;
    private long creatorId;
    private OffsetDateTime announceDate;
    private Integer duration;
    private String address;
    private int typeId;
    private int ticketPrice;
    private String description;
    private Integer discountDuration;
    private Integer discountAmount;
    private Integer discountModifier;
    private MultipartFile previewImage;
}
