package ar.edu.unq.desaapp.grupo.a.backenddesaappapi.model.UserRegister;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.function.IntPredicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class UserRegister {

    private UserRegisterChecker checker;
    private User user = new User();

    public void registerUserName(String name){
        user.setName(name);
    }

    public void registerUserSurname(String surname){
        user.setSurname(surname);
    }

    public void registerUserEmail(String email){
        user.setEmail(email);
    }

    public void registerUserAdress(String address){
        user.setAddress(address);
    }

    public void registerUserPassword(String password){
        user.setPassword(password);
    }

    public void registerUserCVU(String cvu){
        user.setCvu(cvu);
    }

    public void registerUserCryptoAssetAddress(String cryptoAssetAdress){
        user.setCryptoAssetAddress(cryptoAssetAdress);
    }


    public boolean userHasAllDataFilled(){
        return user.hasAllDataFilled();
    }

    public void registerUser(){
        //todo persistance stuff

    }
///////////////////

    public class UserRegisterChecker {

        public boolean hasMinMaxCharacterNumber (int minCharAmount, int maxCharAmount, String strToCheck){
            return strToCheck.length() >= minCharAmount && strToCheck.length() <= maxCharAmount;
        }

        public boolean hasNDigits(int digits, String strToCheck){
            return strToCheck.length() >= digits;
        }

        public boolean hasEmailFormat(String emailToCheck){
            if (!emailToCheck.contains("@")){
                return false;
            }

            int indexOfFirsAtSign = emailToCheck.indexOf("@");

            return  emailToCheck.contains("@") &&
                    hasAcceptablePrefix(emailToCheck.substring(0, indexOfFirsAtSign)) &&
                    hasAcceptableDomain(emailToCheck.substring(indexOfFirsAtSign + 1));
        }

        private boolean hasAcceptablePrefix(String prefixToCheck) {
            return !prefixToCheck.isEmpty() &&
                    prefixOnlyHasAllowedCharacters(prefixToCheck);

        }

        private boolean prefixOnlyHasAllowedCharacters(String prefixToCheck) {
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


        private boolean isValidPrefixCharacter(CharSequence c){

            List<Integer> allowedPrefixCharacters = new ArrayList<>();
            List<Integer> numbers = IntStream.range(0,9).boxed().collect(Collectors.toList());
            String alphabet = "abcdefghijklmnopqrstuvwxyz";
            String printableChars = "!#$%&'*+-/=?^_`{|}~";

            List alphabetList = string2List(alphabet);
            List alphabetUppercaseList = string2List(alphabet.toUpperCase());
            List printableCharsList = string2List(printableChars);


            allowedPrefixCharacters.addAll(numbers);
            allowedPrefixCharacters.addAll(printableCharsList);
            allowedPrefixCharacters.addAll(alphabetList);
            allowedPrefixCharacters.addAll(alphabetUppercaseList);


            return allowedPrefixCharacters.contains(c);
            /*Allowed characters: letters (a-z), numbers, underscores, periods, and dashes.
            An underscore, period, or dash must be followed by one or more letter or number.*/

        }

        private List string2List(String s){

            ArrayList<Character> list = new ArrayList<Character>();
            char[] characterArray = s.toCharArray();
            for(char c : characterArray)//iterating through the character array
                list.add(c);
            return list;
        }

        private boolean hasAcceptableDomain(String domainToCheck) {
            return !domainToCheck.isEmpty() &&
                    domainOnlyHasAllowedCharacters(domainToCheck);

        }

        private boolean domainOnlyHasAllowedCharacters(String domainToCheck) {
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

        private boolean isValidDomainCharacter(CharSequence c) {

            List<Integer> allowedPrefixCharacters = new ArrayList<>();
            List<Integer> numbers = IntStream.range(0,9).boxed().collect(Collectors.toList());
            String alphabet = "abcdefghijklmnopqrstuvwxyz";
            String hyphenAndPeriod = "-.";

            List alphabetList = string2List(alphabet);
            List alphabetUppercaseList = string2List(alphabet.toUpperCase());
            List printableCharsList = string2List(hyphenAndPeriod);


            allowedPrefixCharacters.addAll(numbers);
            allowedPrefixCharacters.addAll(printableCharsList);
            allowedPrefixCharacters.addAll(alphabetList);
            allowedPrefixCharacters.addAll(alphabetUppercaseList);


            return allowedPrefixCharacters.contains(c);

        /*Allowed characters: letters, numbers, dashes, period and two
        The last portion of the domain must be at least two characters, for example: .com, .org, .cc*/
        }


        public boolean isValidPassword(String passToCheck){
                    /*
            * al menos 1 minuscula, 1 mayuscula, 1 carac especial y min 6
            20:20 (password)*/

            return  containsSpecialCharcter(passToCheck) &&
                    containsLowercaseLetters(passToCheck) &&
                    containsUppercaseLetters( passToCheck) &&
                    passToCheck.length() >= 6;
        }

        private boolean containsUppercaseLetters(String passToCheck) {
           return contains(passToCheck, i -> Character.isLetter(i) && Character.isLowerCase(i));
        }

        private boolean containsLowercaseLetters(String passToCheck) {
            return contains(passToCheck, i -> Character.isLetter(i) && Character.isLowerCase(i));
        }

        private boolean contains(String value, IntPredicate predicate) {
            return value.chars().anyMatch(predicate);
        }

        private boolean containsSpecialCharcter(String passToCheck) {
            String specialCharacters = "#$%&'()*+,-./:;<=>?@[]^_`{|}~\\";
            boolean result = false;

            char[] chars = specialCharacters.toCharArray();

            for (char ch: chars) {
                if (passToCheck.contains(String.valueOf(ch))){
                    return true;
                }
            }

            return result;

        }
    }
}
