/*
 * Apache HTTPD & NGINX Access log parsing made easy
 * Copyright (C) 2011-2021 Niels Basjes
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package nl.basjes.pig.input.apachehttpdlog;

import nl.basjes.parse.httpdlog.HttpdLogFormatDissector;
import org.apache.pig.ExecType;
import org.apache.pig.PigServer;
import org.apache.pig.builtin.mock.Storage;
import org.apache.pig.data.Tuple;
import org.junit.Test;

import java.util.List;

import static org.apache.pig.builtin.mock.Storage.resetData;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class TestOutputTypes {

    private static final String LOGFORMAT = "%h %l %u %t \"%r\" %>s %b \"%{Referer}i\" \"%{User-Agent}i\" \"%{Cookie}i\"";
    private final String logfile = getClass().getResource("/omniture-access.log").toString();

    @Test
    public void allOutputTypesTest() throws Exception {
        PigServer pigServer = new PigServer(ExecType.LOCAL);
        Storage.Data data = resetData(pigServer);

        pigServer.registerQuery(
            "Clicks = " +
                "    LOAD '" + logfile + "' " +
                "    USING nl.basjes.pig.input.apachehttpdlog.Loader(" +
                "            '" + LOGFORMAT + "'," +
                "    '-load:nl.basjes.parse.core.test.NormalValuesDissector:"+ HttpdLogFormatDissector.INPUT_TYPE + "'," +
                "            'ANY:any'," +
                "            'ANY:any'," +
                "            'ANY:any'," +
                "            'STRING:string'," +
                "            'STRING:string'," +
                "            'STRING:string'," +
                "            'INT:int'," +
                "            'INT:int'," +
                "            'INT:int'," +
                "            'LONG:long'," +
                "            'LONG:long'," +
                "            'LONG:long'," +
                "            'FLOAT:float'," +
                "            'FLOAT:float'," +
                "            'FLOAT:float'," +
                "            'DOUBLE:double'," +
                "            'DOUBLE:double'," +
                "            'DOUBLE:double'" +
                "            )" +
                "         AS (" +
                "            any_string:chararray," +
                "            any_long:long," +
                "            any_double:double," +
                "            string_string:chararray," +
                "            string_long:long," +
                "            string_double:double," +
                "            int_string:chararray," +
                "            int_long:long," +
                "            int_double:double," +
                "            long_string:chararray," +
                "            long_long:long," +
                "            long_double:double," +
                "            float_string:chararray," +
                "            float_long:long," +
                "            float_double:double," +
                "            double_string:chararray," +
                "            double_long:long," +
                "            double_double:double" +
                "            );"
        );
        pigServer.registerQuery("STORE Clicks INTO 'Clicks' USING mock.Storage();");

        List<Tuple> out = data.get("Clicks");

        assertEquals(1, out.size());
        Tuple resultTuple = out.get(0);

        int index=-1;

        // Pig is apparently capable of doing more casts then what we have normally
        assertEquals("any_string    :", "42",       getString(resultTuple.get(++index)));           // any_string
        assertEquals("any_long      :", 42L,        getLong(resultTuple.get(++index)).longValue()); // any_long
        assertEquals("any_double    :", 42D,        getDouble(resultTuple.get(++index)), 0.1D);     // any_double
        assertEquals("string_string :", "FortyTwo", getString(resultTuple.get(++index)));           // string_string
        assertEquals("string_long   :", null,       getLong(resultTuple.get(++index)));             // string_long
        assertEquals("string_double :", null,       getDouble(resultTuple.get(++index)));           // string_double
        assertEquals("int_string    :", "42",       getString(resultTuple.get(++index)));           // int_string
        assertEquals("int_long      :", 42L,        getLong(resultTuple.get(++index)).longValue()); // int_long
        assertEquals("int_double    :", 42D,        getDouble(resultTuple.get(++index)), 0.1D);     // int_double
        assertEquals("long_string   :", "42",       getString(resultTuple.get(++index)));           // long_string
        assertEquals("long_long     :", 42L,        getLong(resultTuple.get(++index)).longValue()); // long_long
        assertEquals("long_double   :", 42D,        getDouble(resultTuple.get(++index)), 0.1D);     // long_double
        assertEquals("float_string  :", "42.0",     getString(resultTuple.get(++index)));           // float_string
        assertEquals("float_long    :", 42L,        getLong(resultTuple.get(++index)).longValue()); // float_long
        assertEquals("float_double  :", 42D,        getDouble(resultTuple.get(++index)), 0.1D);     // float_double
        assertEquals("double_string :", "42.0",     getString(resultTuple.get(++index)));           // double_string
        assertEquals("double_long   :", 42L,        getLong(resultTuple.get(++index)).longValue()); // double_long
        assertEquals("double_double :", 42D,        getDouble(resultTuple.get(++index)), 0.1D);     // double_double
    }

    private String getString(Object object) {
        if (object instanceof String || object == null) {
            return (String)object;
        }
        fail("This should have been a String");
        return "";
    }

    private Long getLong(Object object) {
        if (object instanceof Long || object == null) {
            return (Long) object;
        }
        fail("This should have been a Long");
        return 0L;
    }

    private Double getDouble(Object object) {
        if (object instanceof Double || object == null) {
            return (Double) object;
        }
        fail("This should have been a Double");
        return 0D;
    }

}
