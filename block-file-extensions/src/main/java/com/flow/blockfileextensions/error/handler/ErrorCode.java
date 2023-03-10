package com.flow.blockfileextensions.error.handler;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCode {

    ALREADY_SAVED_EXTENSION(409, "이미 추가 된 확장자입니다."),
    INVALID_PARAMETER(400, "잘못된 요청입니다."),
    INVALID_INPUT_LENGTH(400, "1자 이상 20자 이하로 입력해주세요."),
    INVALID_EXTENSION_COUNT(400, "커스텀 확장자는 최대 200개 추가할 수 있습니다.");

    private final int status;
    private final String message;
}
