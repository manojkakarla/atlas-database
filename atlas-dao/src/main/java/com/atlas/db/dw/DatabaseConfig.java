package com.atlas.db.dw;

import com.atlas.core.dw.AppConfig;
import io.dropwizard.db.DataSourceFactory;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.Valid;

@Data
@EqualsAndHashCode(callSuper = false)
public class DatabaseConfig extends AppConfig {
  @Valid
  private DataSourceFactory database = new DataSourceFactory();
}
