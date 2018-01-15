/*
 * Apache HTTPD & NGINX Access log parsing made easy
 * Copyright (C) 2011-2018 Niels Basjes
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package nl.basjes.parse.core.annotation;

import nl.basjes.parse.core.Casts;
import nl.basjes.parse.core.Dissector;
import nl.basjes.parse.core.Field;
import nl.basjes.parse.core.Parsable;
import nl.basjes.parse.core.Parser;
import nl.basjes.parse.core.SimpleDissector;
import nl.basjes.parse.core.Value;
import nl.basjes.parse.core.exceptions.DissectionFailure;
import nl.basjes.parse.core.exceptions.InvalidDissectorException;
import nl.basjes.parse.core.exceptions.MissingDissectorsException;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import static nl.basjes.parse.core.Parser.SetterPolicy.ALWAYS;
import static nl.basjes.parse.core.Parser.SetterPolicy.NOT_EMPTY;
import static nl.basjes.parse.core.Parser.SetterPolicy.NOT_NULL;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class TestFieldSetters {
    private static final Logger LOG = LoggerFactory.getLogger(TestFieldSetters.class);

    public static class TestRecord {
        private Map<String, String> strings = new TreeMap<>();
        private Map<String, Long> longs = new TreeMap<>();
        private Map<String, Double> doubles = new TreeMap<>();

        private void setString(String name, String value) {
            strings.put(name, value);
        }
        private void setLong(String name, Long value) {
            longs.put(name, value);
        }
        private void setDouble(String name, Double value) {
            doubles.put(name, value);
        }

        // CHECKSTYLE.OFF: ParenPad
        // CHECKSTYLE.OFF: LineLength
        // CHECKSTYLE.OFF: LeftCurly
        @Field(value = "ANY:any"                                ) public void setAnyDefault(         String name, String value) { setString(  name, value); }
        @Field(value = "STRING:string"                          ) public void setStringDefault(      String name, String value) { setString(  name, value); }
        @Field(value = "INT:int"                                ) public void setIntDefault(         String name, String value) { setString(  name, value); }
        @Field(value = "LONG:long"                              ) public void setLongDefault(        String name, String value) { setString(  name, value); }
        @Field(value = "FLOAT:float"                            ) public void setFloatDefault(       String name, String value) { setString(  name, value); }
        @Field(value = "DOUBLE:double"                          ) public void setDoubleDefault(      String name, String value) { setString(  name, value); }
        @Field(value = "ANY:any"                                ) public void setAnyDefault(         String name, Long value)   { setLong(    name, value); }
        @Field(value = "STRING:string"                          ) public void setStringDefault(      String name, Long value)   { setLong(    name, value); }
        @Field(value = "INT:int"                                ) public void setIntDefault(         String name, Long value)   { setLong(    name, value); }
        @Field(value = "LONG:long"                              ) public void setLongDefault(        String name, Long value)   { setLong(    name, value); }
        @Field(value = "FLOAT:float"                            ) public void setFloatDefault(       String name, Long value)   { setLong(    name, value); }
        @Field(value = "DOUBLE:double"                          ) public void setDoubleDefault(      String name, Long value)   { setLong(    name, value); }
        @Field(value = "ANY:any"                                ) public void setAnyDefault(         String name, Double value) { setDouble(  name, value); }
        @Field(value = "STRING:string"                          ) public void setStringDefault(      String name, Double value) { setDouble(  name, value); }
        @Field(value = "INT:int"                                ) public void setIntDefault(         String name, Double value) { setDouble(  name, value); }
        @Field(value = "LONG:long"                              ) public void setLongDefault(        String name, Double value) { setDouble(  name, value); }
        @Field(value = "FLOAT:float"                            ) public void setFloatDefault(       String name, Double value) { setDouble(  name, value); }
        @Field(value = "DOUBLE:double"                          ) public void setDoubleDefault(      String name, Double value) { setDouble(  name, value); }

        @Field(value = "ANY:any",          setterPolicy = ALWAYS) public void setAnyAlways(         String name, String value) { setString(  "A-"+name, value); }
        @Field(value = "STRING:string",    setterPolicy = ALWAYS) public void setStringAlways(      String name, String value) { setString(  "A-"+name, value); }
        @Field(value = "INT:int",          setterPolicy = ALWAYS) public void setIntAlways(         String name, String value) { setString(  "A-"+name, value); }
        @Field(value = "LONG:long",        setterPolicy = ALWAYS) public void setLongAlways(        String name, String value) { setString(  "A-"+name, value); }
        @Field(value = "FLOAT:float",      setterPolicy = ALWAYS) public void setFloatAlways(       String name, String value) { setString(  "A-"+name, value); }
        @Field(value = "DOUBLE:double",    setterPolicy = ALWAYS) public void setDoubleAlways(      String name, String value) { setString(  "A-"+name, value); }
        @Field(value = "ANY:any",          setterPolicy = ALWAYS) public void setAnyAlways(         String name, Long value)   { setLong(    "A-"+name, value); }
        @Field(value = "STRING:string",    setterPolicy = ALWAYS) public void setStringAlways(      String name, Long value)   { setLong(    "A-"+name, value); }
        @Field(value = "INT:int",          setterPolicy = ALWAYS) public void setIntAlways(         String name, Long value)   { setLong(    "A-"+name, value); }
        @Field(value = "LONG:long",        setterPolicy = ALWAYS) public void setLongAlways(        String name, Long value)   { setLong(    "A-"+name, value); }
        @Field(value = "FLOAT:float",      setterPolicy = ALWAYS) public void setFloatAlways(       String name, Long value)   { setLong(    "A-"+name, value); }
        @Field(value = "DOUBLE:double",    setterPolicy = ALWAYS) public void setDoubleAlways(      String name, Long value)   { setLong(    "A-"+name, value); }
        @Field(value = "ANY:any",          setterPolicy = ALWAYS) public void setAnyAlways(         String name, Double value) { setDouble(  "A-"+name, value); }
        @Field(value = "STRING:string",    setterPolicy = ALWAYS) public void setStringAlways(      String name, Double value) { setDouble(  "A-"+name, value); }
        @Field(value = "INT:int",          setterPolicy = ALWAYS) public void setIntAlways(         String name, Double value) { setDouble(  "A-"+name, value); }
        @Field(value = "LONG:long",        setterPolicy = ALWAYS) public void setLongAlways(        String name, Double value) { setDouble(  "A-"+name, value); }
        @Field(value = "FLOAT:float",      setterPolicy = ALWAYS) public void setFloatAlways(       String name, Double value) { setDouble(  "A-"+name, value); }
        @Field(value = "DOUBLE:double",    setterPolicy = ALWAYS) public void setDoubleAlways(      String name, Double value) { setDouble(  "A-"+name, value); }

        @Field(value = "ANY:any",          setterPolicy = NOT_NULL) public void setAnyNotNull(      String name, String value) { setString(  "NN-"+name, value); }
        @Field(value = "STRING:string",    setterPolicy = NOT_NULL) public void setStringNotNull(   String name, String value) { setString(  "NN-"+name, value); }
        @Field(value = "INT:int",          setterPolicy = NOT_NULL) public void setIntNotNull(      String name, String value) { setString(  "NN-"+name, value); }
        @Field(value = "LONG:long",        setterPolicy = NOT_NULL) public void setLongNotNull(     String name, String value) { setString(  "NN-"+name, value); }
        @Field(value = "FLOAT:float",      setterPolicy = NOT_NULL) public void setFloatNotNull(    String name, String value) { setString(  "NN-"+name, value); }
        @Field(value = "DOUBLE:double",    setterPolicy = NOT_NULL) public void setDoubleNotNull(   String name, String value) { setString(  "NN-"+name, value); }
        @Field(value = "ANY:any",          setterPolicy = NOT_NULL) public void setAnyNotNull(      String name, Long value)   { setLong(    "NN-"+name, value); }
        @Field(value = "STRING:string",    setterPolicy = NOT_NULL) public void setStringNotNull(   String name, Long value)   { setLong(    "NN-"+name, value); }
        @Field(value = "INT:int",          setterPolicy = NOT_NULL) public void setIntNotNull(      String name, Long value)   { setLong(    "NN-"+name, value); }
        @Field(value = "LONG:long",        setterPolicy = NOT_NULL) public void setLongNotNull(     String name, Long value)   { setLong(    "NN-"+name, value); }
        @Field(value = "FLOAT:float",      setterPolicy = NOT_NULL) public void setFloatNotNull(    String name, Long value)   { setLong(    "NN-"+name, value); }
        @Field(value = "DOUBLE:double",    setterPolicy = NOT_NULL) public void setDoubleNotNull(   String name, Long value)   { setLong(    "NN-"+name, value); }
        @Field(value = "ANY:any",          setterPolicy = NOT_NULL) public void setAnyNotNull(      String name, Double value) { setDouble(  "NN-"+name, value); }
        @Field(value = "STRING:string",    setterPolicy = NOT_NULL) public void setStringNotNull(   String name, Double value) { setDouble(  "NN-"+name, value); }
        @Field(value = "INT:int",          setterPolicy = NOT_NULL) public void setIntNotNull(      String name, Double value) { setDouble(  "NN-"+name, value); }
        @Field(value = "LONG:long",        setterPolicy = NOT_NULL) public void setLongNotNull(     String name, Double value) { setDouble(  "NN-"+name, value); }
        @Field(value = "FLOAT:float",      setterPolicy = NOT_NULL) public void setFloatNotNull(    String name, Double value) { setDouble(  "NN-"+name, value); }
        @Field(value = "DOUBLE:double",    setterPolicy = NOT_NULL) public void setDoubleNotNull(   String name, Double value) { setDouble(  "NN-"+name, value); }

        @Field(value = "ANY:any",          setterPolicy = NOT_EMPTY) public void setAnyNotEmpty(     String name, String value) { setString(  "NE-"+name, value); }
        @Field(value = "STRING:string",    setterPolicy = NOT_EMPTY) public void setStringNotEmpty(  String name, String value) { setString(  "NE-"+name, value); }
        @Field(value = "INT:int",          setterPolicy = NOT_EMPTY) public void setIntNotEmpty(     String name, String value) { setString(  "NE-"+name, value); }
        @Field(value = "LONG:long",        setterPolicy = NOT_EMPTY) public void setLongNotEmpty(    String name, String value) { setString(  "NE-"+name, value); }
        @Field(value = "FLOAT:float",      setterPolicy = NOT_EMPTY) public void setFloatNotEmpty(   String name, String value) { setString(  "NE-"+name, value); }
        @Field(value = "DOUBLE:double",    setterPolicy = NOT_EMPTY) public void setDoubleNotEmpty(  String name, String value) { setString(  "NE-"+name, value); }
        @Field(value = "ANY:any",          setterPolicy = NOT_EMPTY) public void setAnyNotEmpty(     String name, Long value)   { setLong(    "NE-"+name, value); }
        @Field(value = "STRING:string",    setterPolicy = NOT_EMPTY) public void setStringNotEmpty(  String name, Long value)   { setLong(    "NE-"+name, value); }
        @Field(value = "INT:int",          setterPolicy = NOT_EMPTY) public void setIntNotEmpty(     String name, Long value)   { setLong(    "NE-"+name, value); }
        @Field(value = "LONG:long",        setterPolicy = NOT_EMPTY) public void setLongNotEmpty(    String name, Long value)   { setLong(    "NE-"+name, value); }
        @Field(value = "FLOAT:float",      setterPolicy = NOT_EMPTY) public void setFloatNotEmpty(   String name, Long value)   { setLong(    "NE-"+name, value); }
        @Field(value = "DOUBLE:double",    setterPolicy = NOT_EMPTY) public void setDoubleNotEmpty(  String name, Long value)   { setLong(    "NE-"+name, value); }
        @Field(value = "ANY:any",          setterPolicy = NOT_EMPTY) public void setAnyNotEmpty(     String name, Double value) { setDouble(  "NE-"+name, value); }
        @Field(value = "STRING:string",    setterPolicy = NOT_EMPTY) public void setStringNotEmpty(  String name, Double value) { setDouble(  "NE-"+name, value); }
        @Field(value = "INT:int",          setterPolicy = NOT_EMPTY) public void setIntNotEmpty(     String name, Double value) { setDouble(  "NE-"+name, value); }
        @Field(value = "LONG:long",        setterPolicy = NOT_EMPTY) public void setLongNotEmpty(    String name, Double value) { setDouble(  "NE-"+name, value); }
        @Field(value = "FLOAT:float",      setterPolicy = NOT_EMPTY) public void setFloatNotEmpty(   String name, Double value) { setDouble(  "NE-"+name, value); }
        @Field(value = "DOUBLE:double",    setterPolicy = NOT_EMPTY) public void setDoubleNotEmpty(  String name, Double value) { setDouble(  "NE-"+name, value); }
        // CHECKSTYLE.ON: ParenPad
        // CHECKSTYLE.ON: LineLength
        // CHECKSTYLE.ON: LeftCurly
    }

    public static class SetAllTypesDissector extends SimpleDissector {
        enum ValueClass {
            NORMAL,
            EMPTY,
            NULL
        }
        private ValueClass valueClass = null;

        private static HashMap<String, EnumSet<Casts>> dissectorConfig = new HashMap<>();
        static {
            dissectorConfig.put("ANY:any",         Casts.STRING_OR_LONG_OR_DOUBLE);
            dissectorConfig.put("STRING:string",   Casts.STRING_ONLY);
            dissectorConfig.put("INT:int",         Casts.STRING_OR_LONG);
            dissectorConfig.put("LONG:long",       Casts.STRING_OR_LONG);
            dissectorConfig.put("FLOAT:float",     Casts.STRING_OR_DOUBLE);
            dissectorConfig.put("DOUBLE:double",   Casts.STRING_OR_DOUBLE);
        }

        public SetAllTypesDissector() {
            super("INPUT", dissectorConfig);
        }

        public SetAllTypesDissector(ValueClass valueClass) {
            super("INPUT", dissectorConfig);
            this.valueClass = valueClass;
        }

        protected void initializeNewInstance(Dissector newInstance) throws InvalidDissectorException {
            super.initializeNewInstance(newInstance);
            if (newInstance instanceof SetAllTypesDissector) {
                SetAllTypesDissector dissector = (SetAllTypesDissector) newInstance;
                dissector.valueClass = valueClass;
            }
        }

        @Override
        public void dissect(Parsable<?> parsable, String inputname, Value value) throws DissectionFailure {
            switch (valueClass){
                case NORMAL:
                    LOG.info("Outputting \"NORMAL\" values");
                    parsable.addDissection(inputname, "ANY",    "any",    "42");
                    parsable.addDissection(inputname, "STRING", "string", "42");
                    parsable.addDissection(inputname, "INT",    "int",    42);
                    parsable.addDissection(inputname, "LONG",   "long",   42L);
                    parsable.addDissection(inputname, "FLOAT",  "float",  42F);
                    parsable.addDissection(inputname, "DOUBLE", "double", 42D);
                    break;
                case NULL:
                    LOG.info("Outputting \"NULL\" values");
                    parsable.addDissection(inputname, "ANY",    "any",    (String) null);
                    parsable.addDissection(inputname, "STRING", "string", (String) null);
                    parsable.addDissection(inputname, "INT",    "int",    (Long)   null);
                    parsable.addDissection(inputname, "LONG",   "long",   (Long)   null);
                    parsable.addDissection(inputname, "FLOAT",  "float",  (Double) null);
                    parsable.addDissection(inputname, "DOUBLE", "double", (Double) null);
                    break;
                case EMPTY:
                    LOG.info("Outputting \"EMPTY\" values");
                    parsable.addDissection(inputname, "ANY",    "any",    "");
                    parsable.addDissection(inputname, "STRING", "string", "");
                    parsable.addDissection(inputname, "INT",    "int",    "");
                    parsable.addDissection(inputname, "LONG",   "long",   "");
                    parsable.addDissection(inputname, "FLOAT",  "float",  "");
                    parsable.addDissection(inputname, "DOUBLE", "double", "");
                    break;
                default:
                    fail("We did not specify a valueClass");
            }
        }
    }

    private Double eDouble = (double) 42F;
    private Long eLong = 42L;

    private void isPresent(Map<String, ?> results, String field, Object value) {
        if (value == null) {
            assertTrue("The field \""+field+"\" is missing (a null value was expected).", results.containsKey(field));
            Object actualValue = results.get(field);
            if (actualValue != null) {
                assertEquals("The field \"" + field + "\" should be null but it was: " +
                    "(" + actualValue.getClass().getSimpleName() + ")\"" + actualValue + "\" ", value, actualValue);
            }
        } else {
            assertTrue("The field \""+field+"\" is missing (an entry of type "+value.getClass().getSimpleName()+" was expected).",
                results.containsKey(field));
            assertEquals("The field \"" + field + "\" should have the value (" +
                value
                + ")\"" + value.toString() + "\"is missing", value, results.get(field));
        }
    }

    private void isAbsent(Map<String, ?> results, String field) {
        Object value = results.get(field);
        if (value != null) {
            fail("The value \""+value+"\" was found for field \""+field+"\"");
        } else {
            assertFalse("A null value was found for field \"" + field + "\"", results.containsKey(field));
        }
    }

    @Test
    public void testNormal() throws InvalidDissectorException, MissingDissectorsException, DissectionFailure {
        Parser<TestRecord> parser = new Parser<>(TestRecord.class);
        parser.setRootType("INPUT");
        parser.addDissector(new SetAllTypesDissector(SetAllTypesDissector.ValueClass.NORMAL));
        TestRecord testRecord = parser.parse("Doesn't matter");

        // Default (== Always)
        isPresent(testRecord.strings, "ANY:any",        "42");
        isPresent(testRecord.strings, "STRING:string",  "42");
        isPresent(testRecord.strings, "INT:int",        "42");
        isPresent(testRecord.strings, "LONG:long",      "42");
        isPresent(testRecord.strings, "FLOAT:float",    "42.0");
        isPresent(testRecord.strings, "DOUBLE:double",  "42.0");
        isPresent(testRecord.longs,   "ANY:any",        eLong);
        isAbsent(testRecord.longs,    "STRING:string");
        isPresent(testRecord.longs,   "INT:int",        eLong);
        isPresent(testRecord.longs,   "LONG:long",      eLong);
        isAbsent(testRecord.longs,    "FLOAT:float");
        isAbsent(testRecord.longs,    "DOUBLE:double");
        isPresent(testRecord.doubles, "ANY:any",        eDouble);
        isAbsent(testRecord.doubles,  "STRING:string");
        isAbsent(testRecord.doubles,  "INT:int");
        isAbsent(testRecord.doubles,  "LONG:long");
        isPresent(testRecord.doubles, "FLOAT:float",    eDouble);
        isPresent(testRecord.doubles, "DOUBLE:double",  eDouble);


        // Always
        isPresent(testRecord.strings, "A-ANY:any",        "42");
        isPresent(testRecord.strings, "A-STRING:string",  "42");
        isPresent(testRecord.strings, "A-INT:int",        "42");
        isPresent(testRecord.strings, "A-LONG:long",      "42");
        isPresent(testRecord.strings, "A-FLOAT:float",    "42.0");
        isPresent(testRecord.strings, "A-DOUBLE:double",  "42.0");
        isPresent(testRecord.longs,   "A-ANY:any",        eLong);
        isAbsent(testRecord.longs,    "A-STRING:string");
        isPresent(testRecord.longs,   "A-INT:int",        eLong);
        isPresent(testRecord.longs,   "A-LONG:long",      eLong);
        isAbsent(testRecord.longs,    "A-FLOAT:float");
        isAbsent(testRecord.longs,    "A-DOUBLE:double");
        isPresent(testRecord.doubles, "A-ANY:any",        eDouble);
        isAbsent(testRecord.doubles,  "A-STRING:string");
        isAbsent(testRecord.doubles,  "A-INT:int");
        isAbsent(testRecord.doubles,  "A-LONG:long");
        isPresent(testRecord.doubles, "A-FLOAT:float",    eDouble);
        isPresent(testRecord.doubles, "A-DOUBLE:double",  eDouble);

        // Not Null
        isPresent(testRecord.strings, "NN-ANY:any",        "42");
        isPresent(testRecord.strings, "NN-STRING:string",  "42");
        isPresent(testRecord.strings, "NN-INT:int",        "42");
        isPresent(testRecord.strings, "NN-LONG:long",      "42");
        isPresent(testRecord.strings, "NN-FLOAT:float",    "42.0");
        isPresent(testRecord.strings, "NN-DOUBLE:double",  "42.0");
        isPresent(testRecord.longs,   "NN-ANY:any",        eLong);
        isAbsent(testRecord.longs,    "NN-STRING:string");
        isPresent(testRecord.longs,   "NN-INT:int",        eLong);
        isPresent(testRecord.longs,   "NN-LONG:long",      eLong);
        isAbsent(testRecord.longs,    "NN-FLOAT:float");
        isAbsent(testRecord.longs,    "NN-DOUBLE:double");
        isPresent(testRecord.doubles, "NN-ANY:any",        eDouble);
        isAbsent(testRecord.doubles,  "NN-STRING:string");
        isAbsent(testRecord.doubles,  "NN-INT:int");
        isAbsent(testRecord.doubles,  "NN-LONG:long");
        isPresent(testRecord.doubles, "NN-FLOAT:float",    eDouble);
        isPresent(testRecord.doubles, "NN-DOUBLE:double",  eDouble);

        // Not Empty
        isPresent(testRecord.strings, "NE-ANY:any",        "42");
        isPresent(testRecord.strings, "NE-STRING:string",  "42");
        isPresent(testRecord.strings, "NE-INT:int",        "42");
        isPresent(testRecord.strings, "NE-LONG:long",      "42");
        isPresent(testRecord.strings, "NE-FLOAT:float",    "42.0");
        isPresent(testRecord.strings, "NE-DOUBLE:double",  "42.0");
        isPresent(testRecord.longs,   "NE-ANY:any",        eLong);
        isAbsent(testRecord.longs,    "NE-STRING:string");
        isPresent(testRecord.longs,   "NE-INT:int",        eLong);
        isPresent(testRecord.longs,   "NE-LONG:long",      eLong);
        isAbsent(testRecord.longs,    "NE-FLOAT:float");
        isAbsent(testRecord.longs,    "NE-DOUBLE:double");
        isPresent(testRecord.doubles, "NE-ANY:any",        eDouble);
        isAbsent(testRecord.doubles,  "NE-STRING:string");
        isAbsent(testRecord.doubles,  "NE-INT:int");
        isAbsent(testRecord.doubles,  "NE-LONG:long");
        isPresent(testRecord.doubles, "NE-FLOAT:float",    eDouble);
        isPresent(testRecord.doubles, "NE-DOUBLE:double",  eDouble);
    }

    @Test
    public void testEmpty() throws InvalidDissectorException, MissingDissectorsException, DissectionFailure {
        Parser<TestRecord> parser = new Parser<>(TestRecord.class);
        parser.setRootType("INPUT");
        parser.addDissector(new SetAllTypesDissector(SetAllTypesDissector.ValueClass.EMPTY));
        TestRecord testRecord = parser.parse("Doesn't matter");

        // Default (== Always)
        isPresent(testRecord.strings, "ANY:any",        "");
        isPresent(testRecord.strings, "STRING:string",  "");
        isPresent(testRecord.strings, "INT:int",        "");
        isPresent(testRecord.strings, "LONG:long",      "");
        isPresent(testRecord.strings, "FLOAT:float",    "");
        isPresent(testRecord.strings, "DOUBLE:double",  "");
        isPresent(testRecord.longs,   "ANY:any",        null);
        isAbsent(testRecord.longs,    "STRING:string");
        isPresent(testRecord.longs,   "INT:int",        null);
        isPresent(testRecord.longs,   "LONG:long",      null);
        isAbsent(testRecord.longs,    "FLOAT:float");
        isAbsent(testRecord.longs,    "DOUBLE:double");
        isPresent(testRecord.doubles, "ANY:any",        null);
        isAbsent(testRecord.doubles,  "STRING:string");
        isAbsent(testRecord.doubles,  "INT:int");
        isAbsent(testRecord.doubles,  "LONG:long");
        isPresent(testRecord.doubles, "FLOAT:float",    null);
        isPresent(testRecord.doubles, "DOUBLE:double",  null);

        // Always
        isPresent(testRecord.strings, "A-ANY:any",        "");
        isPresent(testRecord.strings, "A-STRING:string",  "");
        isPresent(testRecord.strings, "A-INT:int",        "");
        isPresent(testRecord.strings, "A-LONG:long",      "");
        isPresent(testRecord.strings, "A-FLOAT:float",    "");
        isPresent(testRecord.strings, "A-DOUBLE:double",  "");
        isPresent(testRecord.longs,   "A-ANY:any",        null);
        isAbsent(testRecord.longs,    "A-STRING:string");
        isPresent(testRecord.longs,   "A-INT:int",        null);
        isPresent(testRecord.longs,   "A-LONG:long",      null);
        isAbsent(testRecord.longs,    "A-FLOAT:float");
        isAbsent(testRecord.longs,    "A-DOUBLE:double");
        isPresent(testRecord.doubles, "A-ANY:any",        null);
        isAbsent(testRecord.doubles,  "A-STRING:string");
        isAbsent(testRecord.doubles,  "A-INT:int");
        isAbsent(testRecord.doubles,  "A-LONG:long");
        isPresent(testRecord.doubles, "A-FLOAT:float",    null);
        isPresent(testRecord.doubles, "A-DOUBLE:double",  null);

        // Not Null
        isPresent(testRecord.strings, "NN-ANY:any",        "");
        isPresent(testRecord.strings, "NN-STRING:string",  "");
        isPresent(testRecord.strings, "NN-INT:int",        "");
        isPresent(testRecord.strings, "NN-LONG:long",      "");
        isPresent(testRecord.strings, "NN-FLOAT:float",    "");
        isPresent(testRecord.strings, "NN-DOUBLE:double",  "");
        isAbsent(testRecord.longs,    "NN-ANY:any");
        isAbsent(testRecord.longs,    "NN-STRING:string");
        isAbsent(testRecord.longs,    "NN-INT:int");
        isAbsent(testRecord.longs,    "NN-LONG:long");
        isAbsent(testRecord.longs,    "NN-FLOAT:float");
        isAbsent(testRecord.longs,    "NN-DOUBLE:double");
        isAbsent(testRecord.doubles,  "NN-ANY:any");
        isAbsent(testRecord.doubles,  "NN-STRING:string");
        isAbsent(testRecord.doubles,  "NN-INT:int");
        isAbsent(testRecord.doubles,  "NN-LONG:long");
        isAbsent(testRecord.doubles,  "NN-FLOAT:float");
        isAbsent(testRecord.doubles,  "NN-DOUBLE:double");

        // Not Empty
        isAbsent(testRecord.strings,  "NE-ANY:any");
        isAbsent(testRecord.strings,  "NE-STRING:string");
        isAbsent(testRecord.strings,  "NE-INT:int");
        isAbsent(testRecord.strings,  "NE-LONG:long");
        isAbsent(testRecord.strings,  "NE-FLOAT:float");
        isAbsent(testRecord.strings,  "NE-DOUBLE:double");
        isAbsent(testRecord.longs,    "NE-ANY:any");
        isAbsent(testRecord.longs,    "NE-STRING:string");
        isAbsent(testRecord.longs,    "NE-INT:int");
        isAbsent(testRecord.longs,    "NE-LONG:long");
        isAbsent(testRecord.longs,    "NE-FLOAT:float");
        isAbsent(testRecord.longs,    "NE-DOUBLE:double");
        isAbsent(testRecord.doubles,  "NE-ANY:any");
        isAbsent(testRecord.doubles,  "NE-STRING:string");
        isAbsent(testRecord.doubles,  "NE-INT:int");
        isAbsent(testRecord.doubles,  "NE-LONG:long");
        isAbsent(testRecord.doubles,  "NE-FLOAT:float");
        isAbsent(testRecord.doubles,  "NE-DOUBLE:double");

    }

    @Test
    public void testNull() throws InvalidDissectorException, MissingDissectorsException, DissectionFailure {
        Parser<TestRecord> parser = new Parser<>(TestRecord.class);
        parser.setRootType("INPUT");
        parser.addDissector(new SetAllTypesDissector(SetAllTypesDissector.ValueClass.NULL));
        TestRecord testRecord = parser.parse("Doesn't matter");

        // Default (== Always)
        isPresent(testRecord.strings, "ANY:any",        null);
        isPresent(testRecord.strings, "STRING:string",  null);
        isPresent(testRecord.strings, "INT:int",        null);
        isPresent(testRecord.strings, "LONG:long",      null);
        isPresent(testRecord.strings, "FLOAT:float",    null);
        isPresent(testRecord.strings, "DOUBLE:double",  null);
        isPresent(testRecord.longs,   "ANY:any",        null);
        isAbsent(testRecord.longs,    "STRING:string");
        isPresent(testRecord.longs,   "INT:int",        null);
        isPresent(testRecord.longs,   "LONG:long",      null);
        isAbsent(testRecord.longs,    "FLOAT:float");
        isAbsent(testRecord.longs,    "DOUBLE:double");
        isPresent(testRecord.doubles, "ANY:any",        null);
        isAbsent(testRecord.doubles,  "STRING:string");
        isAbsent(testRecord.doubles,  "INT:int");
        isAbsent(testRecord.doubles,  "LONG:long");
        isPresent(testRecord.doubles, "FLOAT:float",    null);
        isPresent(testRecord.doubles, "DOUBLE:double",  null);

        // Always
        isPresent(testRecord.strings, "A-ANY:any",        null);
        isPresent(testRecord.strings, "A-STRING:string",  null);
        isPresent(testRecord.strings, "A-INT:int",        null);
        isPresent(testRecord.strings, "A-LONG:long",      null);
        isPresent(testRecord.strings, "A-FLOAT:float",    null);
        isPresent(testRecord.strings, "A-DOUBLE:double",  null);
        isPresent(testRecord.longs,   "A-ANY:any",        null);
        isAbsent(testRecord.longs,    "A-STRING:string");
        isPresent(testRecord.longs,   "A-INT:int",        null);
        isPresent(testRecord.longs,   "A-LONG:long",      null);
        isAbsent(testRecord.longs,    "A-FLOAT:float");
        isAbsent(testRecord.longs,    "A-DOUBLE:double");
        isPresent(testRecord.doubles, "A-ANY:any",        null);
        isAbsent(testRecord.doubles,  "A-STRING:string");
        isAbsent(testRecord.doubles,  "A-INT:int");
        isAbsent(testRecord.doubles,  "A-LONG:long");
        isPresent(testRecord.doubles, "A-FLOAT:float",    null);
        isPresent(testRecord.doubles, "A-DOUBLE:double",  null);

        // Not Null
        isAbsent(testRecord.strings,  "NN-ANY:any");
        isAbsent(testRecord.strings,  "NN-STRING:string");
        isAbsent(testRecord.strings,  "NN-INT:int");
        isAbsent(testRecord.strings,  "NN-LONG:long");
        isAbsent(testRecord.strings,  "NN-FLOAT:float");
        isAbsent(testRecord.strings,  "NN-DOUBLE:double");
        isAbsent(testRecord.longs,    "NN-ANY:any");
        isAbsent(testRecord.longs,    "NN-STRING:string");
        isAbsent(testRecord.longs,    "NN-INT:int");
        isAbsent(testRecord.longs,    "NN-LONG:long");
        isAbsent(testRecord.longs,    "NN-FLOAT:float");
        isAbsent(testRecord.longs,    "NN-DOUBLE:double");
        isAbsent(testRecord.doubles,  "NN-ANY:any");
        isAbsent(testRecord.doubles,  "NN-STRING:string");
        isAbsent(testRecord.doubles,  "NN-INT:int");
        isAbsent(testRecord.doubles,  "NN-LONG:long");
        isAbsent(testRecord.doubles,  "NN-FLOAT:float");
        isAbsent(testRecord.doubles,  "NN-DOUBLE:double");

        // Not Empty
        isAbsent(testRecord.strings,  "NE-ANY:any");
        isAbsent(testRecord.strings,  "NE-STRING:string");
        isAbsent(testRecord.strings,  "NE-INT:int");
        isAbsent(testRecord.strings,  "NE-LONG:long");
        isAbsent(testRecord.strings,  "NE-FLOAT:float");
        isAbsent(testRecord.strings,  "NE-DOUBLE:double");
        isAbsent(testRecord.longs,    "NE-ANY:any");
        isAbsent(testRecord.longs,    "NE-STRING:string");
        isAbsent(testRecord.longs,    "NE-INT:int");
        isAbsent(testRecord.longs,    "NE-LONG:long");
        isAbsent(testRecord.longs,    "NE-FLOAT:float");
        isAbsent(testRecord.longs,    "NE-DOUBLE:double");
        isAbsent(testRecord.doubles,  "NE-ANY:any");
        isAbsent(testRecord.doubles,  "NE-STRING:string");
        isAbsent(testRecord.doubles,  "NE-INT:int");
        isAbsent(testRecord.doubles,  "NE-LONG:long");
        isAbsent(testRecord.doubles,  "NE-FLOAT:float");
        isAbsent(testRecord.doubles,  "NE-DOUBLE:double");
    }

}