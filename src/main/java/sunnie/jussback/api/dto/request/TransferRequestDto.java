package sunnie.jussback.api.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class TransferRequestDto {
    @JsonProperty("sender_id")
    private int senderId;

    @JsonProperty("receiver_id")
    private int receiverId;

    private Long amount;
    private String memo;
}
