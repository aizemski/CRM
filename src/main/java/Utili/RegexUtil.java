package Utili;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexUtil {
    public static boolean loginRegex(String login) {
        return regex("^(?=.*[A-Za-z0-9]$)[A-Za-z][A-Za-z\\d.-]{4,10}$", login);
    }

    public static boolean cityRegex(String city) {
        return regex("([A-ZŻŹĆĄŚĘŁÓŃ][a-zżźćńółęąś]* *)+", city);
    }

    public static boolean passwordRegex(String password) {
        return regex("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,20}$", password);
    }

    public static boolean nameRegex(String name) {
        return regex("[A-ZŻŹĆĄŚĘŁÓŃ][a-zżźćńółęąś]*", name);
    }

    public static boolean lastnameRegex(String surname) {
        return regex("([A-ZŻŹĆĄŚĘŁÓŃ][a-zżźćńółęąś]*)(-[A-ZŻŹĆĄŚĘŁÓŃ][a-zżźćńółęąś]*)?", surname);
    }

    public static boolean emailRegex(String email) {
        return regex("^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$", email);
    }

    public static boolean tinRegex(String tin) {
        return regex("([0-9]{3})-*([0-9]{3})-*([0-9]{2})-*([0-9]{2})", tin);
    }

    public static boolean phoneRegex(String phone) {
        return regex("^(?:\\(?\\+?48)?(?:[-\\.\\(\\)\\s]*(\\d)){9}\\)?$", phone);
    }

    public static boolean priceRegex(String price) {
        return regex("^\\d+(.\\d{1,2}0?)?$", price);
    }

    public static boolean streetRegex(String street) {
        return regex("/ \\d*[a-zA-Z]?(\\/\\d+)?;$/", street);
    }

    private static boolean regex(String p, String m) {
        Pattern pattern = Pattern.compile(p);
        Matcher matcher = pattern.matcher(m);
        return matcher.matches();
    }
}
