package sunnie.jussback.api.dto.response;

import lombok.Data;

@Data
public class ToPayResponseDto {
    private ToPay topay;
}

@Data
class ToPay {
    private String date;
    private int amount;
}