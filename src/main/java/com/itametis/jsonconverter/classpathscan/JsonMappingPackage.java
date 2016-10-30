/* This file is part of JsonLegacyKiller.
 *
 * JsonLegacyKiller is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * JsonLegacyKiller is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with JsonLegacyKiller.  If not, see <http://www.gnu.org/licenses/gpl.txt >.
 *
 * If you need to develop a closed-source software, please contact us
 * at 'social@itametis.com' to get a commercial version of JsonLegacyKiller,
 * with a proprietary license instead.
 */
package com.itametis.jsonconverter.classpathscan;

import com.google.common.collect.ImmutableSet;
import com.google.common.reflect.ClassPath;
import com.google.common.reflect.ClassPath.ClassInfo;
import com.itametis.jsonconverter.annotation.JsonField;
import com.itametis.jsonconverter.annotation.JsonPath;
import com.itametis.jsonconverter.annotation.Jsonnable;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Singleton class containing the information about the Jsonnable content.
 *
 * @author <a href="mailto:chloe.mahalin@itametis.com">Chloé MAHALIN - ITAMETIS</a>
 */
public class JsonMappingPackage {

    private final static Logger LOGGER = LoggerFactory.getLogger(JsonMappingPackage.class.getSimpleName());

    private final static JsonMappingPackage INSTANCE = new JsonMappingPackage();

    private String packageToScan;

    /**
     * Map containing the name of the jsonnable class and the class.
     */
    private final HashMap<Type, ScannedClass> jsonnableClasses = new HashMap<>();


    /**
     * Constructor.
     */
    private JsonMappingPackage() {
        super();
    }


    /**
     * Call to unique instance.
     *
     * @return the singleton.
     */
    public static JsonMappingPackage getInstance() {
        return JsonMappingPackage.INSTANCE;
    }


    /**
     * Scan the current classpath looking for Json annotations.
     *
     * @param packageName the Package Name.
     */
    public void setPackageToScan(String packageName) {
        if (packageName != null && !packageName.isEmpty()) {
            packageName = packageName.replaceAll("/", ".");
            this.packageToScan = packageName;
        }
    }


    /**
     * Return the Jsonnable content in the application (defined using the constructor of JsonConverter).
     * The key of the map is the class definition.
     * The value, the detail about the jsonnable content.
     *
     * @return a collection containing all details about jsonnable content.
     */
    public Map<Type, ScannedClass> getJsonnableContent() {
        return this.jsonnableClasses;
    }


    /**
     * Scan the classes in the declared package (full class path otherwise) to find classes with Json annotations.
     * Get them in memory.
     *
     * @throws IOException
     */
    public final void scanClassesInPackage() throws IOException {
        this.jsonnableClasses.clear();

        //Get current class loader.
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

        //Get Classpath.
        ClassPath classpath = ClassPath.from(classLoader);

        //Get Classes.
        ImmutableSet<ClassInfo> classes = this.getClassesFromClassPath(classpath);

        this.getJsonnableInformation(classes);
    }


    /**
     * Get all classes from class path depending on the declaration of a specific classpath.
     *
     * @param classpath the class path to investigate.
     *
     * @return the classes.
     */
    private ImmutableSet<ClassInfo> getClassesFromClassPath(ClassPath classpath) {
        if (this.packageToScan == null || this.packageToScan.isEmpty()) {
            return classpath.getAllClasses();
        }
        else {
            return classpath.getTopLevelClassesRecursive(this.packageToScan);
        }
    }


    /**
     * Get the Jsonnable Class info once and use them all along application life style.
     *
     * @param classes the classes found in the specified package.
     */
    private void getJsonnableInformation(ImmutableSet<ClassInfo> classes) {
        for (ClassInfo info : classes) {

            //Get all Jsonnable Classes.
            if (info.load().isAnnotationPresent(Jsonnable.class)) {
                Jsonnable annotation = info.load().getAnnotation(Jsonnable.class);

                ScannedClass jsonnable = new ScannedClass(annotation.value().isEmpty() ? info.getSimpleName() : annotation.value(), info.load());

                this.addJsonnableClassFieldInInformation(info, jsonnable);

                this.jsonnableClasses.put(info.load(), jsonnable);
            }
        }
    }


    /**
     * Get the Jsonnable fields info.
     *
     * @param info      the info of the class.
     * @param jsonnable the Jsonnable class.
     */
    private void addJsonnableClassFieldInInformation(ClassInfo info, ScannedClass jsonnable) {
        //Get all Jsonnable fields in this jsonnable class
        for (Field field : info.load().getDeclaredFields()) {
            field.setAccessible(true);

            if (field.isAnnotationPresent(JsonField.class)) {
                JsonField fieldAnnotation = field.getAnnotation(JsonField.class);

                ScannedField jsonnableField = new ScannedField(
                    fieldAnnotation.name().isEmpty() ? field.getName() : fieldAnnotation.name(),
                    fieldAnnotation.nameInJson().isEmpty() ? field.getName() : fieldAnnotation.nameInJson(),
                    field);

                //In case Field is a collection.
                if (Collection.class.isAssignableFrom(field.getType())
                    || Map.class.isAssignableFrom(field.getType())) {
                    jsonnableField.setIsCollectionOrMap(true);
                    Type type = field.getGenericType();
                    //TODO : find something else.
                    if (type instanceof ParameterizedType) {
                        ParameterizedType pType = (ParameterizedType) type;
                        for (Type actualType : pType.getActualTypeArguments()) {
                            jsonnableField.addParameter(actualType);
                        }
                    }
                }

                if (field.isAnnotationPresent(JsonPath.class)) {
                    jsonnableField.setPathInCode(field.getAnnotation(JsonPath.class).pathInCode());
                    jsonnableField.setPathInJson(field.getAnnotation(JsonPath.class).pathInJson());
                }

                jsonnable.addField(jsonnableField);
            }

            field.setAccessible(false);
        }
    }

}
