package com.itametis.jsonconverter.classpathscan;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author <a href="mailto:chloe.mahalin@itametis.com">Chloé MAHALIN - ITAMETIS</a>
 */
public class ScannedClass {

    private Class classDefinition;

    private String jSonName;

    private Set<ScannedField> jSonFields;


    public ScannedClass(String jsonName, Class detail) {
        this.jSonName = jsonName;
        this.classDefinition = detail;
        this.jSonFields = new HashSet<>();
    }


    public void addField(ScannedField jsonnableField) {
        this.jSonFields.add(jsonnableField);
    }


    public Class getClassDefinition() {
        return classDefinition;
    }


    public String getjSonName() {
        return jSonName;
    }


    public Set<ScannedField> getjSonFields() {
        return jSonFields;
    }


    @Override
    public String toString() {
        return "ScannedClass{" + "jSonName=" + jSonName +", classDefinition=" + classDefinition.getSimpleName() + '}';
    }



}
