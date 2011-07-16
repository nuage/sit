/*
 * Project Simple Issue Tracker
 * All copyright reserved
 */

package fr.nuage.sit.gui;

import com.google.common.base.Function;
import com.google.common.base.Strings;
import com.google.common.collect.Maps;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import javax.servlet.ServletContext;

/**
 *
 * @author nuage
 */
public abstract class Command {

    private static Configuration cfg = null;

    public final void execute(ServletContext context, PrintWriter out, Map<String, String[]> params) throws TemplateException, IOException {
        Template temp = getConfiguration(context).getTemplate(view());
        temp.process(response(unify(params), 10001l), out);
        out.flush();
    }

    private Map<String, String> unify(Map<String, String[]> params) {
        return Maps.transformValues(params, new Function<String[], String>() {

            @Override
            public String apply(String[] input) {
                if (input == null || input.length == 0) {
                    return null;
                }
                return input[0];
            }

        });
    }

    protected String getS(Map<String, String> params, String name) {
        String val = params.get(name);
        return Strings.emptyToNull(val);
    }

    protected Long getL(Map<String, String> params, String name) {
        Long l = null;
        String val = params.get(name);
        if (!Strings.isNullOrEmpty(val)) {
            try {
                l = Long.parseLong(val);
            } catch (NumberFormatException e) {}
        }
        return l;
    }

    protected abstract Map<String, Object> response(Map<String, String> params, Long user);

    protected abstract String view();

    private static Configuration getConfiguration(ServletContext context) {
        if (cfg == null) {
            cfg = new Configuration();
            cfg.setServletContextForTemplateLoading(context, "views");
            cfg.setObjectWrapper(new DefaultObjectWrapper());
        }
        return cfg;
    }
}
