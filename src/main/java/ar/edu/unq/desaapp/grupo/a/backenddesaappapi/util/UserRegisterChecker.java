package ar.edu.unq.desaapp.grupo.a.backenddesaappapi.util;

import ar.edu.unq.desaapp.grupo.a.backenddesaappapi.exception.UserRegisterException;
import ar.edu.unq.desaapp.grupo.a.backenddesaappapi.model.user.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.IntPredicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class UserRegisterChecker {

    public static void userRegisterValid(User user) throws UserRegisterException{
        if(!hasMinMaxCharacterNumber(3, 30, user.getName())){throw new UserRegisterException("Name doesn't have the min(3) or max(30) size characters");}
        if(!hasMinMaxCharacterNumber(3,30, user.getSurname())){throw new UserRegisterException("Surname doesn't have the min(3) or max(30) size characters");}
        if(!hasEmailFormat(user.getEmail())){throw new UserRegisterException("Invalid Mail Format");}
        if(!hasMinMaxCharacterNumber(10, 30, user.getAddress())){throw new UserRegisterException("Address doesn't have the min(10) or max(30) size characters");}
        if(!isValidPassword(user.getPassword())){throw new UserRegisterException("Invalid Password Format");}
        if(!hasMinMaxCharacterNumber(22, 22, user.getCvu())){throw new UserRegisterException("CVU must be 22 digits");}
        if(!hasMinMaxCharacterNumber(8, 8, user.getCryptoAssetAddress())){throw new UserRegisterException("Crypto asset address must be 8 digits");}

    }
    public static boolean hasMinMaxCharacterNumber (int minCharAmount, int maxCharAmount, String strToCheck){
        return strToCheck.length() >= minCharAmount && strToCheck.length() <= maxCharAmount;
    }

    public static boolean hasNDigits(int digits, String strToCheck){
        return strToCheck.length() >= digits;
    }

    public static boolean hasEmailFormat(String emailToCheck){
        if (!emailToCheck.contains("@")){
            return false;
        }

        int indexOfFirsAtSign = emailToCheck.indexOf("@");

        return  emailToCheck.contains("@") &&
                hasAcceptablePrefix(emailToCheck.substring(0, indexOfFirsAtSign)) &&
                hasAcceptableDomain(emailToCheck.substring(indexOfFirsAtSign + 1));
    }

    private static boolean hasAcceptablePrefix(String prefixToCheck) {
        return !prefixToCheck.isEmpty() &&
                prefixOnlyHasAllowedCharacters(prefixToCheck);

    }

    private static boolean prefixOnlyHasAllowedCharacters(String prefixToCheck) {
        boolean result = true;

        for (int i = 0; i < prefixToCheck.length(); i++) {
            char c = prefixToCheck.charAt(i);
            if (!isValidPrefixCharacter(c + "")){
                result = false;
                break;
            }
        }
        return result;
    }


    private static boolean isValidPrefixCharacter(CharSequence c){

        List<Integer> numbers = IntStream.range(0,9).boxed().collect(Collectors.toList());
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        String printableChars = "!#$%&'*+-/=?^_`{|}~";

        return allowCharacters(c, numbers, alphabet, printableChars);
            /*Allowed characters: letters (a-z), numbers, underscores, periods, and dashes.
            An underscore, period, or dash must be followed by one or more letter or number.*/

    }

    private static boolean allowCharacters(CharSequence c, List<Integer> numbers, String alphabet, String printableChars) {
        List<char[]> alphabetList = Collections.singletonList(alphabet.toCharArray());
        List<char[]> printableCharsList = Collections.singletonList(printableChars.toCharArray());

        return numbers.stream().anyMatch(s -> s.toString().contentEquals(c)) ||
                printableCharsList.stream().anyMatch(s -> Arrays.toString(s).contains(c)) ||
                alphabetList.stream().anyMatch(s -> Arrays.toString(s).contains(c) || Arrays.toString(s).toUpperCase().contains(c));
    }

    private static List string2List(String s){

        ArrayList<Character> list = new ArrayList<Character>();
        char[] characterArray = s.toCharArray();
        for(char c : characterArray)//iterating through the character array
            list.add(c);
        return list;
    }

    private static boolean hasAcceptableDomain(String domainToCheck) {
        return !domainToCheck.isEmpty() &&
                domainOnlyHasAllowedCharacters(domainToCheck);
    }

    private static boolean domainOnlyHasAllowedCharacters(String domainToCheck) {
        boolean result = true;
        for (int i = 0; i < domainToCheck.length(); i++) {
            char c = domainToCheck.charAt(i);
            CharSequence cSeq = new StringBuilder(1).append(c);
            if (!isValidDomainCharacter(cSeq)){
                result = false;
                break;
            }
        }
        return result;
    }

    private static boolean isValidDomainCharacter(CharSequence c) {
        List<Integer> numbers = IntStream.range(0,9).boxed().collect(Collectors.toList());
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        String hyphenAndPeriod = "-.";

        return allowCharacters(c, numbers, alphabet, hyphenAndPeriod);

        /*Allowed characters: letters, numbers, dashes, period and two
        The last portion of the domain must be at least two characters, for example: .com, .org, .cc*/
    }


    public static boolean isValidPassword(String passToCheck){
                    /*
            * al menos 1 minuscula, 1 mayuscula, 1 carac especial y min 6
            20:20 (password)*/

        return  containsSpecialCharcter(passToCheck) &&
                containsLowercaseLetters(passToCheck) &&
                containsUppercaseLetters( passToCheck) &&
                passToCheck.length() >= 6;
    }

    private static boolean containsUppercaseLetters(String passToCheck) {
        return contains(passToCheck, i -> Character.isLetter(i) && Character.isLowerCase(i));
    }

    private static boolean containsLowercaseLetters(String passToCheck) {
        return contains(passToCheck, i -> Character.isLetter(i) && Character.isLowerCase(i));
    }

    private static boolean contains(String value, IntPredicate predicate) {
        return value.chars().anyMatch(predicate);
    }

    private static boolean containsSpecialCharcter(String passToCheck) {
        String specialCharacters = "!#$%&'()*+,-./:;<=>?@[]^_`{|}~\\";
        char[] chars = specialCharacters.toCharArray();
        for (char ch: chars) {
            if (passToCheck.contains(String.valueOf(ch))){
                return true;
            }
        }
        return false;
    }
}