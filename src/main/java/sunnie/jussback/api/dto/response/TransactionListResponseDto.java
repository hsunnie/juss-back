package sunnie.jussback.api.dto.response;

import java.util.ArrayList;

import lombok.Data;

@Data
public class TransactionListResponseDto {
    private ArrayList<TransactionResponseDto> transactions; // ArrayList<자료형> : 자료형이 같은 것들로만 이뤄진 리스트 생성됨 (자료형이 다르면 올 수 없음)
}
