package com.ck.tinnydouban.modules.message.dto;

import lombok.Data;
import lombok.ToString;

import java.util.Date;


@Data
@ToString
public class MessageVO {

    private Long id;

    private Integer type;

    private Long typeId;

    private Long fromUserId;

    private Long sendUserId;

    private Boolean hasRead;

    private String sendTime;

    private String message;
}
