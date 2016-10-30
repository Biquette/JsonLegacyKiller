# JsonLegacyKiller

This piece of code based on the GSON converter allows you to go over
the structure of the received Json to be able to create the model you want.

It uses annotations to change the format of your json before converting it to an object.

!! On construction -- not over !!


# HOW TO

See JsonConverterTest class in src/test/java/com/itametis/jsonconverter

1) Annotate your model with the provided annotation, redescribing your conversion

@Jsonnable
public class ComplexClass {

    private final static Logger LOGGER = LoggerFactory.getLogger("pouet");

    @JsonPath(pathInCode = ".",pathInJson = ".")
    @JsonField
    private String titi;

    @JsonField
    private String tutu;

    @JsonField
    private SimpleClassWithIgnoredField simple;


2) Call the JsonConverter :

//Indicate the package containing your model as JsonConverter scan the package at initialization.
JsonConverter converter = new JsonConverter("com.itametis.jsonconverter.entities");

ComplexClass test = converter.convertFromJson("{\"titi\":\"Titi\",\"tutu\":\"Tutu\",\"simple\":{\"toto\":\"truc\",\"tata\":true}}", ComplexClass.class);

Assert.assertEquals("truc", test.getSimple().getToto());
Assert.assertFalse(test.getSimple().getTata());
Assert.assertEquals("Titi", test.getTiti());
