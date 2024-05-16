package sunnie.jussback.api.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class AccountResponseDto {
    private int id;
    private int accountType;
    private int userId;
    private String bankName;
    private String accountName;
    private String accountNumber;
    private Long balance;
    private int cardId;

    @JsonProperty("is_show")
    private Boolean isShow;

    @JsonProperty("is_favorite")
    private Boolean isFavorite;  
}
