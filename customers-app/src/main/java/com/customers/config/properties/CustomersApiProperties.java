package com.customers.config.properties;

import java.time.ZoneId;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@ConfigurationProperties("customers")
@Data
public class CustomersApiProperties {

  private String timeZone;
  private String customersCache;

  public ZoneId getDefaultZoneId() {
    return ZoneId.of(timeZone);
  }

}