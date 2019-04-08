package org.sid.entities;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories("org.sid.data")
@EntityScan("org.sid.entities")
@Configuration
public class DataConfiguration {

}
