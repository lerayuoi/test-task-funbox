package com.funbox.testapplication.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class ResponseDto<T> implements Serializable {
    private ExceptionCodes status;

    public ResponseDto() {
        this.status = ExceptionCodes.OK;
    }

}
