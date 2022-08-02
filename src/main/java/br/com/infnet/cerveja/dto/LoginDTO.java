package br.com.infnet.cerveja.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@AllArgsConstructor
@Getter
@Setter
public class LoginDTO {
    private String user;
    private String pass;
}
