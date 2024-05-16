package sunnie.jussback.api.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class CardResponseDto {
    private int id;
    private String cardName;

    @JsonProperty("is_credit")
    private Boolean isCredit;

    private int amount;
    private int minUsage;
}
