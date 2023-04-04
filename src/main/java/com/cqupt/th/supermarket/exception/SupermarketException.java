package com.cqupt.th.supermarket.exception;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * 超市例外
 *
 * @author TianHong
 * @date 2023/03/18
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SupermarketException extends RuntimeException {
    private Integer code;
    private String message;


    @Override
    public String toString() {
        return "SupermarketException{" +
                "code=" + code +
                ", message=" + this.getMessage() +
                '}';
    }
}
