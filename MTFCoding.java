/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package burrowswheeler;

import java.util.Arrays;

/**
 *
 * @author rumana
 */
public class MTFCoding {

    private final int MAX_ALPHABET = 26;
    private String finalAlpha;
    private int[] codeArray;
    private char[] tempLastSymbol;

    public char[] getTempLastSymbol() {
        return tempLastSymbol;
    }

    public void mtfCoding(String lastSymbol, int index[]) {
        codeArray = new int[lastSymbol.length()];
        char[] alphabet = new char[MAX_ALPHABET];
        char[] tempLastSymbol_code = lastSymbol.toCharArray();

        //init alphabet with null
        for(int i=0; i<MAX_ALPHABET; i++)
            alphabet[i] = '\0';

        int pos = 0;
        for (int i = 0; i < tempLastSymbol_code.length; i++) {
            for (int j = 0; j < MAX_ALPHABET; j++) {
                if (tempLastSymbol_code[i] == alphabet[j]) {
                    break;
                } else if (alphabet[j] == '\0') {
                    alphabet[j] = tempLastSymbol_code[i];
                    pos++;
                    break;
                }
            }
        }
        Arrays.sort(alphabet, 0, pos);

        String tempAlpha = new String(alphabet);
        tempAlpha = tempAlpha.substring(0, pos);
        finalAlpha = new String(alphabet);
        finalAlpha = finalAlpha.substring(0, pos);

        System.out.println("tempalpha "+tempAlpha+" length "+tempAlpha.length());

        for (int i = 0; i < lastSymbol.length(); i++) {
            for (int j = 0; j < tempAlpha.length(); j++) {
                if (lastSymbol.charAt(i) == tempAlpha.charAt(j)) {
                    codeArray[i] = j;
                    String firstPart = tempAlpha.substring(0, j);
                    String lastPart = tempAlpha.substring(j + 1);
                    char atPosition = tempAlpha.charAt(j);

                    String updatedAlpha = atPosition + firstPart + lastPart;
                    tempAlpha = updatedAlpha;
                    break;
                }
            }
        }

        for (int i = 0; i < lastSymbol.length(); i++) {
            System.out.println("The Code Array" + codeArray[i]);
        }
    }

    public void mtfDecoding(String Alphabet, int CodeArray[]) {
        int len = CodeArray.length;
        tempLastSymbol = new char[len];
       
        for (int i = 0; i < len; i++) {
            int current = CodeArray[i];
            tempLastSymbol[i] = Alphabet.charAt(current);
            String firstPart = Alphabet.substring(0, current);
            String lastPart = Alphabet.substring(current + 1);

            String tempAlphabet = tempLastSymbol[i] + firstPart + lastPart;
            Alphabet = tempAlphabet;

        }

        for (int i = 0; i < len; i++) {
            System.out.println("The Decoded Last Symbols ar:" + tempLastSymbol[i]);
        }
    }


    public int[] getCodeArray() {
        return codeArray;
    }

    public String getFinalAlpha() {
        return finalAlpha;
    }
}
