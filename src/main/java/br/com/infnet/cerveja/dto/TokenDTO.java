package br.com.infnet.cerveja.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@AllArgsConstructor
@Data
public class TokenDTO {
    private String type;
    private String token;
}
