package io.playpen.plugin.raven;

import com.getsentry.raven.log4j2.SentryAppender;
import io.playpen.core.plugin.AbstractPlugin;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.Filter;
import org.apache.logging.log4j.core.filter.ThresholdFilter;

@Log4j2
public class RavenPlugin extends AbstractPlugin {
    @Override
    public boolean onStart() {
        log.info("Registering raven config...");
        SentryAppender sentryAppender = new SentryAppender();
        sentryAppender.setDsn(getConfig().getString("dsn"));
        sentryAppender.addFilter(ThresholdFilter.createFilter(Level.ERROR, Filter.Result.NEUTRAL, Filter.Result.DENY));
        Logger logger = LogManager.getRootLogger();
        ((org.apache.logging.log4j.core.Logger) logger).addAppender(sentryAppender);
        log.info("Done");
        return true;
    }
}
