package net.ink.api.annotation;

import net.ink.api.config.WithMockInkUserSecurityContextFactory;
import org.springframework.security.test.context.support.WithSecurityContext;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@WithSecurityContext(factory = WithMockInkUserSecurityContextFactory.class)
public @interface WithMockInkUser {
}
