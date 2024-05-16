package sunnie.jussback.api.dto.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class) //api 서버에서 snake_case로 요청해야하므로, snake_case로 바꿔줄 수 있도록 JsonNaming 사용함
public class TokenResponseDto {
    private String accessToken;
    private String refreshToken;
    private String tokenType;
}
