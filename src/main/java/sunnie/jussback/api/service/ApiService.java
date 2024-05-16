package sunnie.jussback.api.service;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;
import sunnie.jussback.api.dto.request.TransferRequestDto;
import sunnie.jussback.api.dto.response.AccountListResponseDto;
import sunnie.jussback.api.dto.response.AccountResponseDto;
import sunnie.jussback.api.dto.response.CardListResponseDto;
import sunnie.jussback.api.dto.response.ToPayResponseDto;
import sunnie.jussback.api.dto.response.TokenResponseDto;
import sunnie.jussback.api.dto.response.TransactionListResponseDto;
import sunnie.jussback.api.dto.response.TransactionResponseDto;
import sunnie.jussback.api.dto.response.UsedMoneyResponseDto;

@Service
@Slf4j
public class ApiService {

    @Value("${server.url}") //application.yml 파일에서 데이터 가져옴 -> 아래 코드의 serverUrl 변수명으로 데이터를 지정해줌
    private String serverUrl;

    private static final MultiValueMap<String, String> emptyBody = new LinkedMultiValueMap<>();

    private <T, E> T requestApi(Class<T> responseType, String token, String endpoint, HttpMethod method, E body) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        if (token != null) {headers.set("Authorization", token);}

        HttpEntity<E> httpEntity = new HttpEntity<>(body, headers);
        ResponseEntity<T> response = restTemplate.exchange(serverUrl + endpoint, method, httpEntity, responseType);

        return response.getBody();
    }

    public TokenResponseDto startJuss() {
        log.debug(">>>> start service 호출");

        // 1. 유저 생성 api 호출
        TokenResponseDto token = requestApi(TokenResponseDto.class, null, "/user/", HttpMethod.POST, emptyBody);

        // 2. fake 데이터 생성 api 호출
        HashMap data = requestApi(HashMap.class, "Bearer "+token.getAccessToken(), "/data/", HttpMethod.POST, emptyBody);
        // log.debug(">>> 데이터 생성 MSG : " + token.get("msg"));
        return token;
    }

    public AccountListResponseDto getAccounts(String token, String isShow) {
        AccountListResponseDto accountListResponseDto = requestApi(AccountListResponseDto.class, token, "/account/" + (isShow != null ? "?is_show=" + isShow : ""), HttpMethod.GET, emptyBody);
        return accountListResponseDto;
    }

    public AccountResponseDto getAccountDetail(String token, String id) {
        AccountResponseDto accountResponseDto = requestApi(AccountResponseDto.class, token, "/account/"+id, HttpMethod.GET, emptyBody);
        return accountResponseDto;
    }

    public AccountListResponseDto getRecents(String token, int accountType) {
        AccountListResponseDto accountListResponseDto = requestApi(AccountListResponseDto.class, token, "/account/recent?account_type=" + accountType, HttpMethod.GET, emptyBody);
        return accountListResponseDto;
    }

    public TransactionListResponseDto getTransactions(String token, String accountId) {
        TransactionListResponseDto transactionListResponseDto = requestApi(TransactionListResponseDto.class, token, "/transaction/" + accountId, HttpMethod.GET, emptyBody);
        return transactionListResponseDto;
    }

    public UsedMoneyResponseDto getUsed(String token) {
        UsedMoneyResponseDto usedMoneyResponseDto = requestApi(UsedMoneyResponseDto.class, token, "/transaction/used", HttpMethod.GET, emptyBody);
        return usedMoneyResponseDto;
    }

    public ToPayResponseDto getToPay(String token) {
        ToPayResponseDto toPayResponseDto = requestApi(ToPayResponseDto.class, token, "/transaction/topay", HttpMethod.GET, emptyBody);
        return toPayResponseDto;
    }

    public CardListResponseDto getCards(String token, String ym) {
        CardListResponseDto cardListResponseDto = requestApi(CardListResponseDto.class, token, "/card" + (ym != null ? "?ym="+ym:""), HttpMethod.GET, emptyBody);
        return cardListResponseDto;
    }

    public TransactionResponseDto transferMoney(String token, TransferRequestDto body) {
        TransactionResponseDto transactionResponseDto = requestApi(TransactionResponseDto.class, token, "/transfer", HttpMethod.POST, body);
        return transactionResponseDto;
    }

    public Boolean toggleFavorite(String token, String id) {
        Boolean res = requestApi(Boolean.class, token, "/account/favorite/"+id, HttpMethod.PUT, emptyBody);
        return res;
    }

}
