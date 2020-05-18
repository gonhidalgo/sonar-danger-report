/*
 * This file is part of cnesreport.
 *
 * cnesreport is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * cnesreport is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with cnesreport.  If not, see <http://www.gnu.org/licenses/>.
 */

package fr.cnes.sonar.report.model;
import java.util.*;

/**
 * An analyzed Tags of sonarqube
 */
public class Tag {
    
    private String key;
    private String rule;
    private String component;
    private String project;
    private String line;
    private String hash;
    private List<Integer> textRange;
    private String flows;
    private String status;
    private String message;
    private String effort;
    private String debt;
    private String tagName;
    private String creationDate;
    private String updateDate;
    private String type;
    private String organization;
    private String fromHotspot;

    /**
     * Default constructor
     */
    public Tag() {
        this.key = "";
        this.rule = "";
        this.component = "";
        this.project = "";
        this.line = "";
        this.hash = "";
        this.textRange = new ArrayList<>();
        this.flows = "";
        this.status = "";
        this.message = "";
        this.effort = "";
        this.debt = "";
        this.tagName = "";
        this.creationDate = "";
        this.updateDate = "";
        this.type = "";
        this.organization = "";
        this.fromHotspot = "";
    }


    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getRule() {
        return rule;
    }

    public void setRule(String rule) {
        this.rule = rule;
    }

    public String getComponent() {
        return component;
    }
    
    public void setComponent(String component) {
        this.component = component;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public List<Integer> getTextRange() {
        return textRange;
    }

    public void setTextRange(List<Integer> textRange) {
        this.textRange = textRange;
    }

    public String getFlows() {
        return flows;
    }

    public void setFlows(String flows) {
        this.flows = flows;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getEffort() {
        return effort;
    }

    public void setEffort(String effort) {
        this.effort = effort;
    }

    public String getDebt() {
        return debt;
    }

    public void setDebt(String debt) {
        this.debt = debt;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getFromHotspot() {
        return fromHotspot;
    }

    public void setFromHotspot(String fromHotspot) {
        this.fromHotspot = fromHotspot;
    }
}
