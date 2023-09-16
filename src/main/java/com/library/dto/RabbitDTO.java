package com.library.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RabbitDTO {
    Object result;
    String process;
}
