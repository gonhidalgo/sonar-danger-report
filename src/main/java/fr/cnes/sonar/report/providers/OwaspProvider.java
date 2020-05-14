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

package fr.cnes.sonar.report.providers;

import com.google.gson.JsonObject;
import fr.cnes.sonar.report.exceptions.BadSonarQubeRequestException;
import fr.cnes.sonar.report.exceptions.SonarQubeException;
import fr.cnes.sonar.report.model.Owasp;
import fr.cnes.sonar.report.model.SonarQubeServer;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.Map;

/**
 * Provides languages
 */
public class OwaspProvider extends AbstractDataProvider {

    /**
     * Json's field containing the language's array
     */
    private static final String TAGS_FIELD = "tags";
    //private static final String LANGUAGES_FIELD = "languages";

    /**
     * Map containing temporary memory of the data
     */
    private Map<String, Owasp> owasps;
    //private ArrayList<Owasp> owasps;

    /**
     * Complete constructor.
     * @param pServer SonarQube server.
     * @param pToken String representing the user token.
     * @param pProject The id of the project to report.
     */
    public OwaspProvider(final SonarQubeServer pServer, final String pToken, final String pProject) {
        super(pServer, pToken, pProject);
        //owasps = new ArrayList<>();
        owasps = new HashMap<>();
    }

    /**
     * Get the language corresponding to the given key
     * @param languageKey the key of the language
     * @return Language's name
     * @throws BadSonarQubeRequestException when the server does not understand the request
     * @throws SonarQubeException When SonarQube server is not callable.
     */
    public String getOwasp(final String owaspKey)
            throws BadSonarQubeRequestException, SonarQubeException {
        // if(owasps.isEmpty()){
        //     this.getOwasps();
        // }
        

        // String owaspName ="?";
        // if(this.owasps.getName() != null) {
        //     owaspName = this.owasps.getName();
        // }
        // return owaspName;

        if(owasps.isEmpty()){
            this.getOwasps();
        }
        String owaspName = "?";
        if(this.owasps.get(owaspKey) != null) {
            owaspName = this.owasps.get(owaspKey).getName();
        }
        return owaspName;
    }

    /**
     * Get all the languages of SonarQube
     * @return a map with all the languages
     * @throws BadSonarQubeRequestException when the server does not understand the request
     * @throws SonarQubeException When SonarQube server is not callable.
     */
    //public ArrayList<Owasp> getOwasps() throws BadSonarQubeRequestException, SonarQubeException {
    public Map<String, Owasp> getOwasps() throws BadSonarQubeRequestException, SonarQubeException {    
        // send a request to sonarqube server and return th response as a json object
        // if there is an error on server side this method throws an exception
        final JsonObject jo = request(String.format(getRequest(GET_TAGS), getServer().getUrl()));
        final Owasp[] owaspsList = getGson().fromJson(jo.get(TAGS_FIELD),
                Owasp[].class);

        // put data in a map to access it faster
        //this.owasps = new Owasp();
        for(Owasp owasp : owaspsList){
            this.owasps.put(owasp.getKey(), owasp);
            //this.owasps.add(owasp);
        }

        return this.owasps;
    }


}
