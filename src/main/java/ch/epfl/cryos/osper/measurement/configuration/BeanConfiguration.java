package ch.epfl.cryos.osper.measurement.configuration;

import ch.epfl.cryos.osper.measurement.model.Measurement;
import ch.epfl.cryos.osper.measurement.util.DateSerializer;
import ch.epfl.cryos.osper.measurement.util.MeasurementSerializer;
import ch.slf.pro.common.util.converter.ConverterConfiguration;
import ch.slf.pro.common.util.exception.handler.demo.ExceptionDemoConfig;
import ch.slf.pro.common.util.localization.LocalizationService;
import ch.slf.pro.common.util.localization.LocalizationServiceImpl;
import ch.slf.pro.common.util.validator.PropertyValidator;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.datatype.guava.GuavaModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.orm.jpa.vendor.HibernateJpaSessionFactoryBean;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import java.util.Date;

/**
 * Contains the spring managed beans which cannot be handled by spring autodetection.
 *
 * @author pertschy@slf.ch, Jun 11, 2015
 */
@Configuration
@Import({
        ConverterConfiguration.class,
        ExceptionDemoConfig.class
})
//@Import(ExceptionDemoConfig.class)
public class BeanConfiguration {

    private static final Logger log = LoggerFactory.getLogger(BeanConfiguration.class);

//	@Bean
//	@Scope("prototype")
//	public SecurityConfig securityConfig() {
//		SecurityConfig element = new SecurityConfig();
//		log.debug("Created element '{}'", element);
//		return element;
//	}

//    @Bean
//    @Scope("singleton")
//    public FeatureFactory featureFactory() {
//        FeatureFactory element = new FeatureFactory();
//        log.debug("Created element '{}'", element);
//        return element;
//    }

//    @Bean
//    @Scope("prototype")
//    public ExplosionUtils explosionUtils() {
//        ExplosionUtils element = new ExplosionUtils();
//        log.debug("Created element '{}'", element);
//        return element;
//    }

//    @Bean
//    @Scope("singleton")
//    public CrsSridMap crsSridMap() {
//        CrsSridMap element = OracleCrsSridMap.getInstance();
//        log.debug("Created element '{}'", element);
//        return element;
//    }

    @Bean
    @Scope("request")
    public LocalizationService localizationService(){
        LocalizationService element = new LocalizationServiceImpl("messages");
        log.debug("Created element '{}'", element);
        return element;
    }

//    @Bean
//    public LogReqIdRequestListener logReqIdRequestListener() {
//        LogReqIdRequestListener element = new LogReqIdRequestListener();
//        log.debug("Created element '{}'", element);
//        return element;
//    }
//
//    @Bean
//    @Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
//    public DataAccessExceptionInfoFactory dataAccessExceptionInfoFactory() {
//        DataAccessExceptionInfoFactory element = new DataAccessExceptionInfoFactory();
//        log.debug("Created element '{}'", element);
//        return element;
//    }

    @Bean
    @Scope("singleton")
    public MessageSource messageSource(){
        ResourceBundleMessageSource src = new ResourceBundleMessageSource();
        src.setBasenames(
                "messages",
                "ch.slf.pro.common.util.localization.messages.ValidationMessages",
                "ch.slf.pro.common.util.localization.messages.ExceptionMessages");
        return src;
    }

    @Bean
    @Scope("prototype")
    public PropertyValidator propertyValidator() {
        PropertyValidator element = new PropertyValidator();
        log.debug("Created element '{}'", element);
        return element;
    }

    @Bean
    public LocalValidatorFactoryBean localValidatorFactoryBean() {
        LocalValidatorFactoryBean element = new LocalValidatorFactoryBean();
        element.setValidationMessageSource(messageSource());
        log.debug("Created element '{}'", element);
        return element;
    }





//	@Bean
//	public FilterRegistrationBean requestLoggingFilter() {
//		CommonsRequestLoggingFilter filter = new CommonsRequestLoggingFilter();
//		filter.setIncludeClientInfo(true);
//		filter.setIncludePayload(true);
//		filter.setMaxPayloadLength(8192);
//		filter.setIncludeQueryString(true);
//		FilterRegistrationBean element = new FilterRegistrationBean(filter);
//		element.addUrlPatterns(ApplicationFields.REST_MAPPING_DATE_EXAMPLE+"/*", ApplicationFields.REST_MAPPING_EXPLOSION+"/*");
//		log.debug("Created element '{}'", element);
//		return element;
//	}

//    @Bean
//    @Scope("singleton")
//    public GeoTransformService geoTransformService() {
//        GeoTransformServiceImpl element = new GeoTransformServiceImpl();
//        log.debug("Created element '{}'", element);
//        return element;
//    }

//    @Bean
//    @Scope("singleton")
//    public FeatureUtil FeatureUtil() {
//        FeatureUtil element = new FeatureUtil();
//        log.debug("Created element '{}'", element);
//        return element;
//    }

    @Bean
    public Module guavaModule() {
        return new GuavaModule();
    }

    @Bean
    public HibernateJpaSessionFactoryBean sessionFactory() {
        return new HibernateJpaSessionFactoryBean();
    }

    @Autowired
    public void configJackson(Jackson2ObjectMapperBuilder jackson2ObjectMapperBuilder) {
        DateSerializer dateSerializer = new DateSerializer();
        jackson2ObjectMapperBuilder.serializerByType(Measurement.class, new MeasurementSerializer(dateSerializer));
        jackson2ObjectMapperBuilder.serializerByType(Date.class, dateSerializer);
    }


//    @Bean
//    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
//        return new JdbcTemplate(dataSource);
//
//    }

//    @Autowired
//    public void configJackson(Jackson2ObjectMapperBuilder jackson2ObjectMapperBuilder) {
//        jackson2ObjectMapperBuilder.serializerByType(Range.class, new TimeRangeSerializer());
//    }


}
