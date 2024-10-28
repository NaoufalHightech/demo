package ben.fr.demomigrationhexagonal.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Clock;
import java.time.ZoneId;

@Configuration
public class AssurerConfiguration {

    @Bean
    public Clock clock(@Value("${default.time-zone}") String defaultTimeZone) {
        return Clock.system(ZoneId.of(defaultTimeZone));
    }

}
