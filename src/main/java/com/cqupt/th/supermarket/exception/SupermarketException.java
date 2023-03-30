package com.cqupt.th.supermarket.exception;


import lombok.Data;


/**
 * 超市例外
 *
 * @author TianHong
 * @date 2023/03/18
 */
@Data
public class SupermarketException extends RuntimeException {
    private Integer code;

    /**
     * 接受状态码和错误消息
     * @param code
     * @param message
     */
    public SupermarketException(Integer code, String message) {
        super(message);
        this.code = code;
    }



    @Override
    public String toString() {
        return "SupermarketException{" +
                "code=" + code +
                ", message=" + this.getMessage() +
                '}';
    }
}
