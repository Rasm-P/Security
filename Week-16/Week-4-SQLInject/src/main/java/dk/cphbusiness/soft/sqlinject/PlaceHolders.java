package dk.cphbusiness.soft.sqlinject;

public class PlaceHolders {

    public static String field(String name, String... whitelist) {
        if (name == null) {
            name = whitelist[0];
        }
        for (String option : whitelist) {
            if (option.equals(name)) {
                return name;
            }
        }
        throw new IllegalArgumentException("Bad input bro...");
    }

    public static String string(String value) {
        return "'" + value.replace("'", "\\'") + "'";
    }

    public static String stringList(String... values) {
        String result = "";
        for (String value : values) {
            if (result.isEmpty()) {
                result = string(value);
            } else {
                result += ", " + string(value);
            }
        }
        return "(" + result + ")";
    }

    public static String integer(String value) {
        if (value.replace("'", "").matches("-?\\d+")) {
            return value;
        } else {
            throw new IllegalArgumentException("Bad input bro...");
        }
    }

    public static String integerList(String... values) {
        String result = "";
        for (String value : values) {
            if (result.isEmpty()) {
                result = integer(value);
            } else {
                result += ", " + integer(value);
            }
        }
        return "(" + result + ")";
    }

}
