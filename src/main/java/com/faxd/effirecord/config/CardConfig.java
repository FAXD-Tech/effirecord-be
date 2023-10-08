package com.faxd.effirecord.config;

import com.faxd.effirecord.exception.BankNotFoundException;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "card")
public class CardConfig {

    private Map<String, String> banks = new HashMap<>();

    public String getKeyByValue(String value) {
        for (Map.Entry<String, String> bankEntry : banks.entrySet()) {
            if (bankEntry.getValue().equalsIgnoreCase(value)){
                return bankEntry.getKey();
            }
        }
        throw new BankNotFoundException(value);
    }
}
