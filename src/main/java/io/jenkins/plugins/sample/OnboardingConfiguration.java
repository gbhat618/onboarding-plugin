package io.jenkins.plugins.sample;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.kohsuke.stapler.QueryParameter;

import hudson.Extension;
import hudson.util.FormValidation;
import jenkins.model.GlobalConfiguration;


@Extension
public class OnboardingConfiguration extends GlobalConfiguration {

    private String name;
    private String description;

    public OnboardingConfiguration() {
        load();
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setName(String name) {
        this.name = name;
        save();
    }

    public void setDescription(String description) {
        this.description = description;
        save();
    }

    public FormValidation doCheckName(@QueryParameter("value") String value) {
        if (value.isEmpty()) {
            return FormValidation.warning("Name cannot be empty!");
        } else {
            Pattern pattern = Pattern.compile("^[A-Za-z]+$");
            Matcher matcher = pattern.matcher(value);

            if (!matcher.matches()) {
                return FormValidation.error("Invalid data");
            } else {
                return FormValidation.ok("all good");
            }
        }
    }
}
