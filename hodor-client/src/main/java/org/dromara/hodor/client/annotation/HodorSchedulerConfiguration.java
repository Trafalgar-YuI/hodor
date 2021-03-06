package org.dromara.hodor.client.annotation;

import org.dromara.hodor.client.HodorApiClient;
import org.dromara.hodor.client.HodorClientInit;
import org.dromara.hodor.client.JobRegistrar;
import org.dromara.hodor.client.ServiceProvider;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * hodor scheduler configuration
 *
 * @author tomgs
 * @since 2020/12/30
 */
@Configuration
@EnableConfigurationProperties(HodorProperties.class)
public class HodorSchedulerConfiguration {

    private final HodorProperties properties;

    public HodorSchedulerConfiguration(final HodorProperties properties, final ApplicationContext applicationContext) {
        this.properties = properties;
        ServiceProvider.getInstance().setApplicationContext(applicationContext);
    }

    @Bean
    public HodorSchedulerAnnotationBeanPostProcessor scheduledAnnotationProcessor() {
        return new HodorSchedulerAnnotationBeanPostProcessor(jobRegistrar());
    }

    @Bean
    public HodorApiClient hodorApiClient() {
        return new HodorApiClient(properties);
    }

    @Bean
    public JobRegistrar jobRegistrar() {
        return new JobRegistrar();
    }

    @Bean
    public HodorClientInit hodorClientInit() {
        return new HodorClientInit();
    }

}
