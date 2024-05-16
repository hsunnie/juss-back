package sunnie.jussback.api.dto.response;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class TransactionResponseDto {
    private int id;
    private int senderId;
    private int receiverId;
    
    @JsonProperty("is_fill")
    private Boolean isFill;
    private String memo;
    private int cardId;

    private int amount;

    private LocalDateTime createdAt;
}
