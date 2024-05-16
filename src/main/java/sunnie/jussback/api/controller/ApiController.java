package sunnie.jussback.api.controller;

import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
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
import sunnie.jussback.api.service.ApiService;

import java.util.HashMap;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;





@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor //lombok에 의해 필요한 construct를 beans 가져올 수 있게됨
@Slf4j
public class ApiController { // input
    private final ApiService apiService; // = new ApiService();

    @PostMapping("/start")
    public TokenResponseDto startJuss() {
        log.debug(">>>> start api 호출");
        
        //output
        return apiService.startJuss();
    }

    @GetMapping("/accounts")
    public AccountListResponseDto getAccounts(@RequestHeader("Authorization") String token,
                                              @RequestParam(name="isShow", required=false) String isShow) {
        log.debug(">>>> 전체 계좌 조회 api 호출");
        log.debug(">>>> 토큰 : " + token);
        return apiService.getAccounts(token, isShow);
    }
    
    @GetMapping("/accounts/{id}")
    public AccountResponseDto getAccountDetail(@PathVariable(name="id") String id,
                                   @RequestHeader(name = "Authorization") String token) { // 자료형은 String OR Int OR Long 중 상황에 맞게 사용
        log.debug(">>>> 계좌 상세 조회 api 호출");
        log.debug(">>>> id: "+ id);
        return apiService.getAccountDetail(token, id);
    }
    
    @GetMapping("/recent")
    public AccountListResponseDto getRecents(@RequestParam(name="accountType") int accountType,
                                @RequestHeader(name = "Authorization") String token) {
        log.debug(">>> 최근계좌 조회 api 호출");
        log.debug(">>> Account Type : " + accountType);
        return apiService.getRecents(token, accountType);
    }

    @GetMapping("/transaction/{accountId}")
    public TransactionListResponseDto getTransactions(@PathVariable(name="accountId") String accountId,
                                                      @RequestHeader(name="Authorization") String token) {
        log.debug(">>>> 거래내역 조회 api 호출");
        log.debug(">>>> Account ID : "+ accountId);
        return apiService.getTransactions(token, accountId);
    }
    
    @GetMapping("/used")
    public UsedMoneyResponseDto getUsed(@RequestHeader(name = "Authorization") String token) {
        log.debug(">>>> 이번 달 사용내역 조회 api 호출");
        return apiService.getUsed(token);
    }
    
    @GetMapping("/topay")
    public ToPayResponseDto getToPay(@RequestHeader(name = "Authorization") String token) {
        log.debug(">>>> 내야 할 카드값 조회 api 호출");
        return apiService.getToPay(token);
    }
    
    @GetMapping("/card")
    public CardListResponseDto getCards(@RequestHeader(name = "Authorization") String token,
                                        @RequestParam(name = "ym", required=false) String ym) {
        log.debug(">>>> 카드목록 조회 api 호출");
        return apiService.getCards(token, ym);
    }
    
    @PostMapping("/transfer")
    public TransactionResponseDto transferMoney(@RequestHeader(name = "Authorization") String token,
                                                @RequestBody TransferRequestDto body) {
        log.debug(">>>> 송금 요청 api 호출");
        log.debug(">>>> body :" + body.getSenderId() + " " + body.getReceiverId() + " " + body.getAmount());
        log.debug(String.format(">>>> body: %s %s %s", body.getReceiverId(), body.getSenderId(), body.getAmount()));
        return apiService.transferMoney(token, body);
    }
    
    @PutMapping("/accounts/{id}/favorite")
    public Boolean toggleFavorite(@PathVariable(name="id") String id,
                                  @RequestHeader(name = "Authorization") String token) {
        log.debug(">>>> 즐겨찾기 api 호출");
        return apiService.toggleFavorite(token, id);
    }

}
