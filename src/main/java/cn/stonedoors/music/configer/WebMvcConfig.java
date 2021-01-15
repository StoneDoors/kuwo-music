package cn.stonedoors.music.configer;

import cn.stonedoors.music.filter.CorsFilter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.ValueFilter;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.support.FormattingConversionServiceFactoryBean;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

	/**
	 * 跨域支持
	 * 
	 * @return
	 */
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurerAdapter() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedMethods("*");
				registry.addMapping("/**").allowedOrigins("*");
			}
		};
	}
	
	@Bean
	public FilterRegistrationBean getCorsFilter() {
		CorsFilter corsFilter = new CorsFilter();
		FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		registrationBean.setFilter(corsFilter);
		List<String> urlPatterns = new ArrayList<String>();
		urlPatterns.add("/*");
		registrationBean.setUrlPatterns(urlPatterns);
		registrationBean.setOrder(1);
		return registrationBean;
	}

	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		super.configureMessageConverters(converters);
		FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
		FastJsonConfig fastJsonConfig = new FastJsonConfig();
		fastJsonConfig.setSerializerFeatures(SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullListAsEmpty, SerializerFeature.WriteNullStringAsEmpty,
				SerializerFeature.WriteNullNumberAsZero, SerializerFeature.WriteNullBooleanAsFalse, SerializerFeature.DisableCircularReferenceDetect);
		fastJsonConfig.setSerializeFilters(new LongValueFilter());
		fastConverter.setFastJsonConfig(fastJsonConfig);
		converters.add(fastConverter);
	}
	
	public class LongValueFilter implements ValueFilter {
	      public Object process(Object object, String propertyName, Object propertyValue) {
	    	  if(propertyValue instanceof Long && propertyValue != null && propertyValue.toString().length() > 15) {
	    		  return propertyValue.toString();
	    	  }
	    	  return propertyValue;
	      }
	  }
	
	@Bean
	public FormattingConversionServiceFactoryBean convers() {
		FormattingConversionServiceFactoryBean converBean = new FormattingConversionServiceFactoryBean();
		Set<Object> set = new HashSet<>();
		converBean.setConverters(set);
		return converBean;
	}

}
