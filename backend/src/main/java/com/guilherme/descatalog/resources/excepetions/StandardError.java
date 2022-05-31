package com.guilherme.descatalog.resources.excepetions;

import lombok.*;

import java.io.Serializable;
import java.time.Instant;

@Data
public class StandardError implements Serializable {
    private static final long serialVersionUID = 1L;

    private Instant timestamp;
    private String status;
    private String error;
    private String message;
    private String path;


}
