package com.novo.personalproject.config;

import com.novo.personalproject.PersonalProjectApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.envers.repository.config.EnableEnversRepositories;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@Configuration
@EnableEnversRepositories(basePackageClasses = PersonalProjectApplication.class)
public class AuditConfiguration {
}
