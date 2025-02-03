package cqrs.bank.account.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class BaseResponse {
    private String message;
    public BaseResponse(String message){
        this.message = message;
    }
    public BaseResponse(){
        this.message = "";
    }
}
