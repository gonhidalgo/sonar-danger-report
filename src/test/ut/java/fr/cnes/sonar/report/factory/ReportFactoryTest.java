package fr.cnes.sonar.report.factory;

import fr.cnes.sonar.report.CommonTest;
import fr.cnes.sonar.report.exceptions.BadExportationDataTypeException;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.xmlbeans.XmlException;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class ReportFactoryTest extends CommonTest {

    @Test
    public void createTest() throws XmlException, BadExportationDataTypeException, OpenXML4JException, IOException {
        ReportFactory.report(conf, report);
        Assert.assertTrue(true);
    }

}
