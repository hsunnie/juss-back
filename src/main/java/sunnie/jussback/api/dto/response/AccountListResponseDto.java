package sunnie.jussback.api.dto.response;

import java.util.ArrayList;

import lombok.Data;

@Data
public class AccountListResponseDto {
    private ArrayList<AccountResponseDto> accounts;
}
