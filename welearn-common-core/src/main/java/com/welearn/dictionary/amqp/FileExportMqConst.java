package com.welearn.dictionary.amqp;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Description :
 * Created by Setsuna Jin on 2019/4/9.
 */
@AllArgsConstructor
public enum  FileExportMqConst {
    EXCHANGE("file-export.exchange"),
    QUEUE("file-export.queue"),
    ROUTING_KEY("file-export.binding"),

    ;
    @Getter
    private String name;
}
