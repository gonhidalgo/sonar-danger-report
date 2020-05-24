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

package fr.cnes.sonar.report.exporters.docx;

import fr.cnes.sonar.report.model.*;
import fr.cnes.sonar.report.utils.StringManager;

import java.util.*;


/**
 * Format resources in different structure to have an easier use
 */
public final class DataAdapter {

    /**
     * Regex to delete html tags
     */
    private static final String DELETE_HTML_TAGS_REGEX = "<[^>]*>";
    /**
     * just a question mark
     */
    private static final String QUESTION_MARK = "?";
    /**
     * Placeholder for author
     */
    private static final String AUTHOR_PLACEHOLDER = "XX-AUTHOR-XX";
    /**
     * Placeholder for date
     */
    private static final String DATE_PLACEHOLDER = "XX-DATE-XX";
    /**
     * Placeholder for project's name
     */
    private static final String PROJECTNAME_PLACEHOLDER = "XX-PROJECTNAME-XX";
    /**
     * Placeholder for the quality gate's anme
     */
    private static final String QUALITYGATENAME_PLACEHOLDER = "XX-QUALITYGATENAME-XX";
    /**
     * Placeholder for quality gate's filename
     */
    private static final String QUALITYGATEFILE_PLACEHOLDER = "XX-QUALITYGATEFILE-XX";
    /**
     * Placeholder for quality profile's name
     */
    private static final String QUALITYPROFILENAME_PLACEHOLDER = "XX-QUALITYPROFILENAME-XX";
    /**
     * Placeholder for quality profile's filenames
     */
    private static final String QUALITYPROFILEFILE_PLACEHOLDER = "XX-QUALITYPROFILEFILE-XX";

    /**
     * Placeholders for complexity metrics
     */
    private static final String MINCOMPLEXITY_PLACEHOLDER = "XX-MINCOMPLEXITY-XX";
    private static final String MAXCOMPLEXITY_PLACEHOLDER = "XX-MAXCOMPLEXITY-XX";
    /**
    * Key to get complexity in metricstats
    */
    private static final String MINCOMPLEXITY_STATKEY = "mincomplexity";
    private static final String MAXCOMPLEXITY_STATKEY = "maxcomplexity";
    /**
     * Placeholders for NCLOC metrics (number of line of codes)
     */
    private static final String MINNCLOC_PLACEHOLDER = "XX-MINNCLOC-XX";
    private static final String MAXNCLOC_PLACEHOLDER = "XX-MAXNCLOC-XX";
    /**
     * Key to get number of line of codes in metricstats
     */
    private static final String MINNCLOC_STATKEY = "minncloc";
    private static final String MAXNCLOC_STATKEY = "maxncloc";
    /**
     * Placeholders for comment density metrics
     */
    private static final String MINCOMMENTDENSITY_PLACEHOLDER = "XX-MINCOMMENTDENSITY-XX";
    private static final String MAXCOMMENTDENSITY_PLACEHOLDER = "XX-MAXCOMMENTDENSITY-XX";
    /**
     * Key to get comment_lines_density in metricstats
     */
    private static final String MINCOMMENTDENSITY_STATKEY = "mincomment_lines_density";
    private static final String MAXCOMMENTDENSITY_STATKEY = "maxcomment_lines_density";
    /**
     * Placeholders for duplications metrics
     */
    private static final String MINDUPLICATION_PLACEHOLDER = "XX-MINDUPLICATION-XX";
    private static final String MAXDUPLICATION_PLACEHOLDER = "XX-MAXDUPLICATION-XX";
    /**
     * Key to get duplications in metricstats
     */
    private static final String MINDUPLICATION_STATKEY = "minduplicated_lines_density";
    private static final String MAXDUPLICATION_STATKEY = "maxduplicated_lines_density";
    /**
     * Placeholders for cognitive complexity metrics
     */
    private static final String MINCOGNITIVECOMPLEXITY_PLACEHOLDER = "XX-MINCOGNITIVECOMPLEXITY-XX";
    private static final String MAXCOGNITIVECOMPLEXITY_PLACEHOLDER = "XX-MAXCOGNITIVECOMPLEXITY-XX";
    /**
     * Key to get cognitive complexity in metricstats
     */
    private static final String MINCOGNITIVECOMPLEXITY_STATKEY = "mincognitive_complexity";
    private static final String MAXCOGNITIVECOMPLEXITY_STATKEY = "maxcognitive_complexity";
    /**
     * Placeholders for coverage metrics
     */
    private static final String MINCOVERAGE_PLACEHOLDER = "XX-MINCOVERAGE-XX";
    private static final String MAXCOVERAGE_PLACEHOLDER = "XX-MAXCOVERAGE-XX";
    /**
     * Key to get coverage in metricstats
     */
    private static final String MINCOVERAGE_STATKEY = "mincoverage";
    private static final String MAXCOVERAGE_STATKEY = "maxcoverage";
    /**
     * extension for xml file
     */
    private static final String XML_EXTENSION = ".xml";
    /**
     * Field name of a measure rating
     */
    private static final String RATING = "rating";
    /**
     * String value of numerical mark given by SonarQube
     */
    private static final String MARK_1_NUMBER = "1.0";
    /**
     * String value of numerical mark given by SonarQube
     */
    private static final String MARK_2_NUMBER = "2.0";
    /**
     * String value of numerical mark given by SonarQube
     */
    private static final String MARK_3_NUMBER = "3.0";
    /**
     * String value of numerical mark given by SonarQube
     */
    private static final String MARK_4_NUMBER = "4.0";
    /**
     * String value of numerical mark given by SonarQube
     */
    private static final String MARK_5_NUMBER = "5.0";
    /**
     * field name containing the mark for a measure
     */
    private static final String VALUE = "value";
    /**
     * String value of letter mark given by SonarQube
     */
    private static final String MARK_1_LETTER = "A";
    /**
     * String value of letter mark given by SonarQube
     */
    private static final String MARK_2_LETTER = "B";
    /**
     * String value of letter mark given by SonarQube
     */
    private static final String MARK_3_LETTER = "C";
    /**
     * String value of letter mark given by SonarQube
     */
    private static final String MARK_4_LETTER = "D";
    /**
     * String value of letter mark given by SonarQube
     */
    private static final String MARK_5_LETTER = "E";
    /**
     * Placeholder for reliability mark
     */
    private static final String RELIABILITY_PLACEHOLDER = "XX-RELIABILITY-XX";
    /**
     * Placeholder for duplication rate
     */
    private static final String DUPLICATION_PLACEHOLDER = "XX-DUPLICATION-XX";
    /**
     * Placeholder for maintainability mark
     */
    private static final String MAINTAINABILITY_PLACEHOLDER = "XX-MAINTAINABILITY-XX";
    /**
     * Placeholder for coverage rate
     */
    private static final String COVERAGE_PLACEHOLDER = "XX-COVERAGE-XX";
    /**
     * Placeholder for complexity mark
     */
    private static final String LINES_PLACEHOLDER = "XX-LINES-XX";
    /**
     * Placeholder for quality gate's status
     */
    private static final String QUALITYGATE_PLACEHOLDER = "XX-QUALITYGATE-XX";
    /**
     * Placeholder for security mark
     */
    private static final String SECURITY_PLACEHOLDER = "XX-SECURITY-XX";
    /**
     * Placeholder for the project's version
     */
    private static final String VERSION_PLACEHOLDER = "XX-VERSION-XX";
    /**
     * Placeholder for the project's description
     */
    private static final String DESCRIPTION_PLACEHOLDER = "XX-DESCRIPTION-XX";
    /**
     * Default placeholder
     */
    private static final String DEFAULT_PLACEHOLDER = "XX-XXXXXXXXXXXXXXX-XX";
    /**
     * Field in json response for reliability mark
     */
    private static final String RELIABILITY_RATING = "reliability_rating";
    /**
     * Field in json response for duplications
     */
    private static final String DUPLICATED_LINES_DENSITY = "duplicated_lines_density";
    /**
     * Field in json response for maintainability mark
     */
    private static final String SQALE_RATING = "sqale_rating";
    /**
     * Field in json response for coverage
     */
    private static final String COVERAGE = "coverage";
    /**
     * Field in json response for number of code lines
     */
    private static final String NCLOC = "ncloc";
    /**
     * Field in json response for quality gate's status
     */
    private static final String ALERT_STATUS = "alert_status";
    /**
     * Field in json response for security mark
     */
    private static final String SECURITY_RATING = "security_rating";
    /**
     * List of possible issue types
     */
    private static final String[] ISSUE_TYPES = {"VULNERABILITY", "BUG", "CODE_SMELL", "SECURITY_HOTSPOT"};
    /**
     * List of possible issue severities
     */
    private static final String[] ISSUE_SEVERITIES = {
        "BLOCKER", "CRITICAL", "MAJOR", "MINOR", "INFO"
    };

    private static final String[] OWASP = {"owasp-a1", "owasp-a2", "owasp-a3", "owasp-a4","owasp-a5","owasp-a6","owasp-a7","owasp-a8","owasp-a9","owasp-a10"};
    

    /**
     * Field in json response for number of code lines per language
     */
    private static final String NCLOC_PER_LANGUAGE = "ncloc_language_distribution";
    /**
     * Just an equals sign
     */
    private static final String EQUALS = "=";
    /**
     * Just a semicolon
     */
    private static final String SEMICOLON = ";";
    /**
     * Label for the total line
     */
    private static final String TOTAL = "Total";
    /**
     * Just an empty string
     */
    private static final String EMPTY = "";

    /**
     * Private constructor to forbid instantiation of this class
     */
    private DataAdapter(){}

    /**
     * Prepare list of resources to be print in a table
     * Data are lines containing the number of issues by severity and type
     * @param report report from which to extract resources
     * @return list of lists of strings
     */
    public static List<List<String>> getTypes(Report report) {
        // result to return
        final List<List<String>> results = new ArrayList<>();

        final String[] types = ISSUE_TYPES;
        final String[] severities = ISSUE_SEVERITIES;

        for(String type : types) {
            for (String severity : severities) {
                // accumulator for the number of occurrences
                long nb = 0;
                // we sum all issues with a type and a severity
                for(Issue issue : report.getIssues()) {
                    if(issue.getType().equals(type) && issue.getSeverity().equals(severity)) {
                        nb++;
                    }
                }
                // we add it to the list
                final List<String> item = new ArrayList<>();
                item.add(type);
                item.add(severity);
                item.add(String.valueOf(nb));
                // add the whole line to the results
                results.add(item);
            }
        }

        return results;
    }

    public static List<List<String>> getTagsDoc(Report report) {

                // result to return
        final List<List<String>> results = new ArrayList<>();
        //final Rule rule = report.getRule();

        final String[] tags = OWASP;
        //final String[] types = ISSUE_TYPES;
        //final String[] severities = ISSUE_SEVERITIES;

        long nb1 = 0;
        String tagName = "Esta vacio";
        // final List<String> listTags = new ArrayList<>();
        String issueName = "ghjk";
        

       
        //for(String tag1 : tags) {
            //for(Tag tag : report.getTags()){
            for(Issue issue : report.getIssues()){
                //issueName = rule.getName();
                for (String tag : issue.getTags()){
                    final List<String> item = new ArrayList<>();
                    switch(tag){
                        case "owasp-a1":
                            item.add(issue.getComponent());
                            //item.add(tag1);
                            item.add(tag);
                            item.add("Injection flaws are very prevalent, particularly in legacy code. Injection vulnerabilities are often found in SQL, LDAP, XPath, or NoSQL queries, OS commands, XML parsers, SMTP headers, expression languages, and ORM queries. Injection flaws are easy to discover when examining code. Scanners and fuzzers can help attackers find injection flaws.");
                            item.add("Preventing injection requires keeping data separate from commands and queries. * The preferred option is to use a safe API, which avoids the use of the interpreter entirely or provides a parameterized interface, or migrate to use Object Relational Mapping Tools (ORMs). Note: Even when parameterized, stored procedures can still introduce SQL injection if PL/SQL or T-SQL concatenates queries and data, or executes hostile data with EXECUTE IMMEDIATE or exec(). * Use positive or “whitelist” server-side input validation. This is not a complete defense as many applications require special characters, such as text areas or APIs for mobile applications. * For any residual dynamic queries, escape special characters using the specific escape syntax for that interpreter. Note: SQL structure such as table names, column names, and so on cannot be escaped, and thus user-supplied structure names are dangerous. This is a common issue in report-writing software. * Use LIMIT and other SQL controls within queries to prevent mass disclosure of records in case of SQL injection.");
                            results.add(item);
                            break;
                        case "owasp-a2":
                            item.add(issue.getComponent());
                            //item.add(tag1);
                            item.add(tag);
                            item.add("Aqui iria la explicación owasp1");
                            item.add("Aqui iria la mitigacion owasp2");
                            results.add(item);
                            break;

                        case "owasp-a3":
                            item.add(issue.getComponent());
                            //item.add(tag1);
                            item.add(tag);
                            item.add("Aqui iria la explicación owasp1");
                            item.add("Aqui iria la mitigacion owasp3");
                            results.add(item);
                            break;
                        case "owasp-a4":
                            item.add(issue.getComponent());
                            //item.add(tag1);
                            item.add(tag);
                            item.add("Aqui iria la explicación owasp1");
                            item.add("Aqui iria la mitigacion owasp4 ");
                            results.add(item);
                            break;
                        case "owasp-a5":
                            item.add(issue.getComponent());
                            //item.add(tag1);
                            item.add(tag);
                            item.add("Aqui iria la explicación owasp1");
                            item.add("Aqui iria la mitigacion owasp 5");
                            results.add(item);
                            break;
                        case "owasp-a6":
                            item.add(issue.getComponent());
                            //item.add(tag1);
                            item.add(tag);
                            item.add("Aqui iria la explicación owasp1");
                            item.add("Aqui iria la mitigacion owasp6");
                            results.add(item);
                            break;
                        case "owasp-a7":
                            item.add(issue.getComponent());
                            //item.add(tag1);
                            item.add(tag);
                            item.add("Aqui iria la explicación owasp7");
                            item.add("Aqui iria la mitigacion owasp7");
                            results.add(item);
                            break;
                        case "owasp-a8":
                            item.add(issue.getComponent());
                            //item.add(tag1);
                            item.add(tag);
                            item.add("Aqui iria la explicación owasp1");
                            item.add("Aqui iria la mitigacion owasp8");
                            results.add(item);
                            break;
                        case "owasp-a9":
                            item.add(issue.getComponent());
                            //item.add(tag1);
                            item.add(tag);
                            item.add("Aqui iria la explicación owasp1");
                            item.add("Aqui iria la mitigacion owasp9");
                            results.add(item);
                            break;
                        case "owasp-a10":
                            item.add(issue.getComponent());
                            //item.add(tag1);
                            item.add(tag);
                            item.add("Aqui iria la explicación owasp1");
                            item.add("Aqui iria la mitigacion owasp10");
                            results.add(item);
                            break;
                        default:
                            break;
                    }
                    
                    
/*                    default:
                        String error = "No existe";
                        item.add(issueName);
                        item.add(tagName);
                        item.add(error);
                        results.add(item);
                        break;
                    
                    }*/

                    
                    /*item.add(issueName);
                    item.add(tagName);
                    item.add("estonovale");
                    results.add(item);*/
                }

            }
        //}
                
          

        //item.add(String.valueOf(nb));
          


        return results;

    }


    public static List<List<String>> getImprovements(Report report) {
        // result to return
        final List<List<String>> results = new ArrayList<>();

        final String[] types = ISSUE_TYPES;
        final String[] severities = ISSUE_SEVERITIES;

        for(String type : types) {
            for (String severity : severities) {
                final List<String> item = new ArrayList<>();
                long nb = 0;
                // we sum all issues with a type and a severity
                for(Issue issue : report.getIssues()) {
                    if(issue.getType().equals(type) && issue.getSeverity().equals(severity)) {
                        nb++;
                    }
                }    
                if(nb != 0){
                          
                    
                    switch(type){
                        case "VULNERABILITY":
                            switch(severity){
                                case "MINOR":
                                    String minor = "CRLF Injection or similar; remove terminations of lines such as ASCII /r or ASCII /n. Attackers could modify application data, compromising integrity and enabling the exploitation of other vulnerabilities such as XSS or Website defacement";
                                    item.add(type);
                                    item.add(severity);
                                    item.add(minor);
                                    item.add("0.5-1.9");
                                    break; 
                                case "MAJOR":
                                    String major = "When '='is used, could be interpreted as a formula, delivering in possible injections vulnerabilitys. Recommended to modify using methods. For example, if used as a comparation, use  either'.equals' or 'compareTo";
                                    item.add(type);
                                    item.add(severity);
                                    item.add(major);
                                    item.add("1.9-2.7");
                                    break;
                                case "CRITICAL":
                                    String critical = "Never accept a filename and its extension directly without having a whitelist filter, prevent from overwriting a file in case of having the same hash for both. Personal information could be in danger; consider saving the files in a database rather than on the filesystem. ";
                                    item.add(type);
                                    item.add(severity);
                                    item.add(critical);
                                    item.add("2.7-4.8");
                                    break;
                                case "BLOCKER":
                                    String blocker = "High probability to impact the behavior of the application in production. Try ";
                                    item.add(type);
                                    item.add(severity);
                                    item.add(blocker);
                                    item.add("4.8-5.8");
                                    break;    
                                case "INFO":
                                    String info = "Low profile vulnerability. Shouldn't cause any trouble.";
                                    item.add(type);
                                    item.add(severity);
                                    item.add(info);
                                    item.add("0-0.5");
                                    break;   
                                default:
                                    break;                         
                            }
                            break;

                        case "BUG":
                            switch(severity){
                                case "MINOR":
                                    String minor = "THIS IS BUG MINOR";
                                    item.add(type);
                                    item.add(severity);
                                    item.add(minor);
                                    item.add("0.5-3.9");
                                    break; 
                                case "MAJOR":
                                    String major = "The software contains dead code, which can never be executed. To solve it, remove dead code before deploying the application.";
                                    item.add(type);
                                    item.add(severity);
                                    item.add(major);
                                    item.add("3.9- 5.8");
                                    break;
                                case "CRITICAL":
                                    String critical = "THIS IS BUG CRITICAL";
                                    item.add(type);
                                    item.add(severity);
                                    item.add(critical);
                                    item.add("5.8-7.3");
                                    break;
                                case "BLOCKER":
                                    String blocker = "THIS IS BUG BLOCKER";
                                    item.add(type);
                                    item.add(severity);
                                    item.add(blocker);
                                    item.add("7.3-10");
                                    break;    
                                case "INFO":
                                    String info = "THIS IS BUG INFO";
                                    item.add(type);
                                    item.add(severity);
                                    item.add(info);
                                    item.add("0-0.5");
                                    break;   
                                default:
                                    break;                         
                            }
                            break;
                        case "CODE_SMELL":
                            switch(severity){
                                case "MINOR":
                                    String minor = "Unused variables. Make sure every declared variable is used or delete the unsed ones.";
                                    item.add(type);
                                    item.add(severity);
                                    item.add(minor);
                                    item.add("0.5-3.9");
                                    break; 
                                case "MAJOR":
                                    String major = "There are several solutions. Make sure there's neither commented code nor unused classes or functions, as it could be a potential weakness.";
                                    item.add(type);
                                    item.add(severity);
                                    item.add(major);
                                    item.add("3.9- 5.8");
                                    break;
                                case "CRITICAL":
                                    String critical = "No CWE associated found. For further information, check Issues/Code Smell to find the issue.";
                                    item.add(type);
                                    item.add(severity);
                                    item.add(critical);
                                    item.add("5.8-7.3");
                                    break;
                                case "BLOCKER":
                                    String blocker = "THIS IS CODE_SMELL BLOCKER";
                                    item.add(type);
                                    item.add(severity);
                                    item.add(blocker);
                                    item.add("7.3-10");
                                    break;    
                                case "INFO":
                                    String info = "THIS IS CODE_SMELL INFO";
                                    item.add(type);
                                    item.add(severity);
                                    item.add(info);
                                    item.add("0-0.5");
                                    break;   
                                default:
                                    break;                         
                            }
                            break;
                        case "SECURITY_HOTSPOT":
                            switch(severity){
                                case "MINOR":
                                    String minor = "THIS IS SECURITY_HOTSPOT MINOR";
                                    item.add(type);
                                    item.add(severity);
                                    item.add(minor);
                                    item.add("0.5-1.5");
                                    break; 
                                case "MAJOR":
                                    String major = "THIS IS SECURITY_HOTSPOT MAJOR";
                                    item.add(type);
                                    item.add(severity);
                                    item.add(major);
                                    item.add("1.5-2.4");
                                    break;
                                case "CRITICAL":
                                    String critical = "Multiple choices: either refactor your code to avoid using reflection. Assume all input is malicious. Use an 'accept known good' input validation strategy, which consists in a list of acceptable inputs that strictly conform to specifications. Reject any input that does not strictly conform to specifications, or transform it into something that does. When performing input validation, consider all potentially relevant properties, including length, type of input, the full range of acceptable values, missing or extra inputs, syntax, consistency across related fields, and conformance to business rules.";
                                    item.add(type);
                                    item.add(severity);
                                    item.add(critical);
                                    item.add("2.4-4.3");
                                    break;
                                case "BLOCKER":
                                    String blocker = "THIS IS SECURITY_HOTSPOT BLOCKER";
                                    item.add(type);
                                    item.add(severity);
                                    item.add(blocker);
                                    item.add("4.3-5.6");
                                    break;    
                                case "INFO":
                                    String info = "THIS IS SECURITY_HOTSPOT INFO";
                                    item.add(type);
                                    item.add(severity);
                                    item.add(info);
                                    item.add("0-0.5");
                                    break;   
                                default:
                                    break;                         
                            }
                            break;             
                        default:
                            String prueba3 = "NO EXISTE";
                            item.add(type);
                            item.add(severity);
                            item.add(prueba3);
                            item.add("NO EXISTE");
                            break;

                    }

                results.add(item);
                }





                
        //for(String type : types) {
          //  for (String severity : severities) {
                // accumulator for the number of occurrences
                // long nb = 0;
                // // we sum all issues with a type and a severity
                // for(Issue issue : report.getIssues()) {
                //     if(issue.getType().equals(type) && issue.getSeverity().equals(severity)) {
                //         nb++;
                //     }
                // }
                // // we add it to the list
                // final List<String> item = new ArrayList<>();

                // //item.add(prueba);
                // if(nb == 0){
                //     item.add(type);
                //     item.add(severity);
                //     item.add("-");
                //     item.add("-");
                // }else {
                //     item.add(type);
                //     item.add(severity);
                //     item.add(prueba2);
                //     item.add(prueba);
                //     //item.add(String.valueOf(nb));
                // }

                // add the whole line to the results
                //results.add(item);
            }
        }

        return results;
    }


    /**
     * Get formatted metrics to be printed
     * @param report Report from which to extract resources
     * @return list of list of string (metric,measure)
     */
    public static List<List<String>> getMetrics(Report report) {
        // result to return
        final List<List<String>> metrics = new ArrayList<>();

        // construct each metric
        for (Measure m : report.getMeasures()) {
            metrics.add(Arrays.asList(m.getMetric(),m.getValue()));
        }

        return metrics;
    }

    /**
     * Get formatted issues summary
     * @param report report from which to export resources
     * @return issues list
     */
    public static List<List<String>> getIssues(Report report) {
        final List<List<String>> issues = new ArrayList<>();  // result to return

        // Get the issues' id
        final Map<String, Long> items = report.getIssuesFacets();

        Map<String,Long> sortedItems =  new TreeMap<>(new RuleComparator(report));
        sortedItems.putAll(items);

        for (Map.Entry<String, Long> v : sortedItems.entrySet()) { // construct each issues
            final List<String> issue = new ArrayList<>();
            final Rule rule = report.getRule(v.getKey());
            if(rule!=null) { // if the rule is found, fill information
                // add name
                issue.add(rule.getName());
                // add description
                issue.add(rule.getHtmlDesc()
                        .replaceAll(DELETE_HTML_TAGS_REGEX, StringManager.EMPTY));
                // add type
                issue.add(rule.getType());
                // add severity
                issue.add(rule.getSeverity());
                // add number
                issue.add(Long.toString(v.getValue()));
            } else { // else set just known information
                // add name
                issue.add(v.getKey());
                // add description
                issue.add(QUESTION_MARK);
                // add type
                issue.add(QUESTION_MARK);
                // add severity
                issue.add(QUESTION_MARK);
                // add number
                issue.add(Long.toString(v.getValue()));
            }

            issues.add(issue);
        }

        return issues;
    }

    /**
     * Return values of a given facet
     * @param facets list of facets from which to extract values
     * @param facetName name of th facet to get
     * @return a list (can be empty)
     */
    public static List<Value> getFacetValues(List<Facet> facets, String facetName) {

        // iterate on facets' list
        final Iterator<Facet> iterator = facets.iterator();
        // list of results
        List<Value> items = new ArrayList<>();
        Facet facet;
        while(iterator.hasNext() && items.isEmpty()) {
            // get current facet
            facet = iterator.next();
            // check if current facet is the wanted one
            if(facet.getProperty().equals(facetName)) {
                items = facet.getValues();
            }
        }

        return items;
    }

    /**
     * Load in a map all the placeholder (key) with the corresponding replacement value (value)
     * @param report Report from which resources are extracted
     * @return the placeholders map
     */
    public static Map<String, String> loadPlaceholdersMap(Report report) {
        // final map to return
        final Map<String, String> replacementValues = new HashMap<>();
        // Replacement of placeholder
        // report meta resources placeholders
        replacementValues.put(
                AUTHOR_PLACEHOLDER,
                report.getProjectAuthor());
        replacementValues.put(
                VERSION_PLACEHOLDER,
                report.getProjectVersion());
        replacementValues.put(
                DESCRIPTION_PLACEHOLDER,
                report.getProjectDescription());
        replacementValues.put(
                DATE_PLACEHOLDER,
                report.getProjectDate());
        replacementValues.put(
                PROJECTNAME_PLACEHOLDER,
                report.getProjectName());
        // configuration placeholders
        replacementValues.put(
                QUALITYGATENAME_PLACEHOLDER,
                report.getQualityGate().getName());
        replacementValues.put(
                QUALITYGATEFILE_PLACEHOLDER,
                report.getQualityGate().getName() + XML_EXTENSION);
        replacementValues.put(
                QUALITYPROFILENAME_PLACEHOLDER,
                report.getQualityProfilesName());
        replacementValues.put(
                QUALITYPROFILEFILE_PLACEHOLDER,
                report.getQualityProfilesFilename());
        try {
            // complexity metrics
            replacementValues.put(
                    MINCOMPLEXITY_PLACEHOLDER,
                    report.getMetricsStats().get(MINCOMPLEXITY_STATKEY).toString()
            );
            replacementValues.put(
                    MAXCOMPLEXITY_PLACEHOLDER,
                    report.getMetricsStats().get(MAXCOMPLEXITY_STATKEY).toString()
            );

            // number of line of codes metrics
            replacementValues.put(
                    MINNCLOC_PLACEHOLDER,
                    report.getMetricsStats().get(MINNCLOC_STATKEY).toString()
            );
            replacementValues.put(
                    MAXNCLOC_PLACEHOLDER,
                    report.getMetricsStats().get(MAXNCLOC_STATKEY).toString()
            );

            //comment density
            replacementValues.put(
                    MINCOMMENTDENSITY_PLACEHOLDER,
                    report.getMetricsStats().get(MINCOMMENTDENSITY_STATKEY).toString()
            );
            replacementValues.put(
                    MAXCOMMENTDENSITY_PLACEHOLDER,
                    report.getMetricsStats().get(MAXCOMMENTDENSITY_STATKEY).toString()
            );

            // duplications
            replacementValues.put(
                    MINDUPLICATION_PLACEHOLDER,
                    report.getMetricsStats().get(MINDUPLICATION_STATKEY).toString()
            );
            replacementValues.put(
                    MAXDUPLICATION_PLACEHOLDER,
                    report.getMetricsStats().get(MAXDUPLICATION_STATKEY).toString()
            );

            // cognitive complexity
            replacementValues.put(
                    MINCOGNITIVECOMPLEXITY_PLACEHOLDER,
                    report.getMetricsStats().get(MINCOGNITIVECOMPLEXITY_STATKEY).toString()
            );
            replacementValues.put(
                    MAXCOGNITIVECOMPLEXITY_PLACEHOLDER,
                    report.getMetricsStats().get(MAXCOGNITIVECOMPLEXITY_STATKEY).toString()
            );

            // coverage
            replacementValues.put(
                    MINCOVERAGE_PLACEHOLDER,
                    report.getMetricsStats().get(MINCOVERAGE_STATKEY).toString()
            );
            replacementValues.put(
                    MAXCOVERAGE_PLACEHOLDER,
                    report.getMetricsStats().get(MAXCOVERAGE_STATKEY).toString()
            );
        }
        catch (NullPointerException e){
            ArrayList<String> placeholders = new ArrayList<>();
            placeholders.add(MINCOMMENTDENSITY_PLACEHOLDER);
            placeholders.add(MAXCOMMENTDENSITY_PLACEHOLDER);
            placeholders.add(MINCOMPLEXITY_PLACEHOLDER);
            placeholders.add(MAXCOMPLEXITY_PLACEHOLDER);
            placeholders.add(MINNCLOC_PLACEHOLDER);
            placeholders.add(MAXNCLOC_PLACEHOLDER);
            placeholders.add(MINCOGNITIVECOMPLEXITY_PLACEHOLDER);
            placeholders.add(MAXCOGNITIVECOMPLEXITY_PLACEHOLDER);
            placeholders.add(MINCOGNITIVECOMPLEXITY_PLACEHOLDER);
            placeholders.add(MAXCOGNITIVECOMPLEXITY_PLACEHOLDER);
            for(String placeholder: placeholders){
                replacementValues.put(
                        placeholder,
                        "unknown"
                );
            }
        }


        // Synthesis placeholders
        for (Measure m : report.getMeasures()) {
            final String placeholder = getPlaceHolderName(m.getMetric());
            String value = m.getValue();

            // convert numerical mark to letter if necessary
            if(m.getMetric().contains(RATING)) {
                value = numberToLetter(m.getValue());
            }

            replacementValues.put(
                    placeholder,
                    value);

            // Sometime, project did not have coverage
            if (replacementValues.get(COVERAGE_PLACEHOLDER) == null){
                replacementValues.put(COVERAGE_PLACEHOLDER, QUESTION_MARK);
            }
        }
        return replacementValues;
    }

    /**
     * Convert the numeric note to a letter
     * @param value numeric note
     * @return a letter
     */
    private static String numberToLetter(String value) {
        final String res;
        // make the link between numbers and letters
        switch (value) {
            case MARK_1_NUMBER:
                res = MARK_1_LETTER;
                break;
            case MARK_2_NUMBER:
                res = MARK_2_LETTER;
                break;
            case MARK_3_NUMBER:
                res = MARK_3_LETTER;
                break;
            case MARK_4_NUMBER:
                res = MARK_4_LETTER;
                break;
            case MARK_5_NUMBER:
                res = MARK_5_LETTER;
                break;
            default:
                res = VALUE;
                break;
        }
        return res;
    }

    /**
     * Give the corresponding placeholder
     * @param metric value whose it have to find the placeholder
     * @return value of the place holder
     */
    private static String getPlaceHolderName(String metric) {
        final String res;
        switch (metric) {
            case RELIABILITY_RATING:
                res = RELIABILITY_PLACEHOLDER;
                break;
            case DUPLICATED_LINES_DENSITY:
                res = DUPLICATION_PLACEHOLDER;
                break;
            case SQALE_RATING:
                res = MAINTAINABILITY_PLACEHOLDER;
                break;
            case COVERAGE:
                res = COVERAGE_PLACEHOLDER;
                break;
            case NCLOC:
                res = LINES_PLACEHOLDER;
                break;
            case ALERT_STATUS:
                res = QUALITYGATE_PLACEHOLDER;
                break;
            case SECURITY_RATING:
                res = SECURITY_PLACEHOLDER;
                break;
            default:
                res = DEFAULT_PLACEHOLDER;
                break;
        }
        return res;
    }


    /**
     * Load in a list all the volume metrics with the corresponding value (value)
     * @param report Report from which resources are extracted
     * @return the volumes list
     */
    public static List<List<String>> getVolumes(Report report) {
        // result to return
        final List<List<String>> volumes = new ArrayList<>();

        // find metrics per language and for all
        final String perLanguage = findMeasure(report.getMeasures(), NCLOC_PER_LANGUAGE);
        final String total = findMeasure(report.getMeasures(), NCLOC);

        // split raw string data into list of list of string
        // to get a relevant table
        final List<String> firstSplit = Arrays.asList(perLanguage.split(SEMICOLON));
        firstSplit.forEach(x -> volumes.add(Arrays.asList(x.split(EQUALS))));

        // replace language's key by language's name
        Language language;
        for(List<String> l : volumes) {
            language = report.getProject().getLanguage(l.get(0));
            if(null!=language) {
                l.set(0, language.getName());
            }
        }

        // add the total lines
        volumes.add(Arrays.asList(TOTAL, total));

        return volumes;
    }

    /**
     * Return the value of a given metrics
     * @param measures List of measures to browse
     * @param metric metric to search
     * @return a String containing the measure
     */
    private static String findMeasure(List<Measure> measures, String metric) {
        // result that can be null
        String result = EMPTY;
        // retrieve all measures to browse
        final Iterator<Measure> iterator = measures.iterator();
        Measure current;
        // search by name the measure corresponding to metric
        while (iterator.hasNext() && result.equals(EMPTY)) {
            current = iterator.next();
            if(current.getMetric().equals(metric)) {
                result = current.getValue();
            }
        }
        return result;
    }

}

/**
 * RuleComparator is used to compare 2 issues to sort them by type & severity
 */
class RuleComparator implements Comparator<String>{
    Report report;

    RuleComparator(Report report){
        this.report = report;
    }

    public int compare(String o1, String o2) {
        int compare = 0;

        if(o1.isEmpty() || o2.isEmpty())compare = 1;

        //If rule is removed in quality gate, the issue is send to the end of list
        if(report.getRule(o1) == null){compare = 1;}
        else if(report.getRule(o2) == null){compare = -1;}

        if (compare == 0){
            compare = report
                    .getRule(o1)
                    .getType()
                    .compareTo(
                            report.getRule(o2).getType()
                    );
        }

        if (compare == 0) {
            compare = report.getRule(o1).getSeverity().compareTo(
                    report.getRule(o2).getSeverity()
            );
        }

        if (compare == 0){
            compare = report.getRule(o1).getKey().compareTo(
                    report.getRule(o2).getKey()
            );
        }

        return compare;
    }
}