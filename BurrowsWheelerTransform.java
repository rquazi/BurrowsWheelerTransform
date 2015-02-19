package burrowswheeler;

import java.util.Arrays;

/**
 *
 * @author rumana
 */
class BurrowsWheelerTransform {

    private String firstSymbols;
    private int[] index;
    private String[] CyclicShiftFirst;
    private String[] combination;
    private String[] cyclicCombination;
    private char[] tempDecodedSeq;

    public char[] getTempDecodedSeq() {
        return tempDecodedSeq;
    }

    public String[] getCyclicCombination() {
        return cyclicCombination;
    }

    public String getFirstSymbols() {
        return firstSymbols;
    }

    public int[] getIndex() {
        return index;
    }
    public String[] getCyclicFirst()
    {
        return CyclicShiftFirst;
    }

    public String[] getCombination() {
        return combination;
    }




    public void encode(String input) {
        combination = new String[input.length()];
        CyclicShiftFirst=new String[input.length()];
       

        int x, i;
        x = input.length();
        char c[] = input.toCharArray();

        int atPosition = x - 1;
        for (i = 0; i < x; i++) {
            char startChar = input.charAt(atPosition);
            String lastPart = input.substring(0, atPosition);
            combination[i] = startChar + lastPart;
            input = combination[i];
        }

        System.out.println("Output of the first step:Cyclic Shif of input");
        for (i = 0; i < combination.length; i++) {
            CyclicShiftFirst[i]=combination[i];
            System.out.println("Before Sorting: " + combination[i]);
        }


        Arrays.sort(combination);

        System.out.println("Output of the second step: Sorting");
        for (i = 0; i < combination.length; i++) {
            System.out.println("After Sorting: " + combination[i]);
        }


        /*Cyclic Shift*/

        String[] tempCombination = new String[combination.length];

        for (i = 0; i < combination.length; i++) {
            tempCombination[i] = combination[i];
        }


        cyclicCombination = new String[combination.length];
        //int current = 0;

        for (i = 0; i < x; i++) {
            int position = (x - 1);
            char atPos = tempCombination[i].charAt(position);
            String lastPart = tempCombination[i].substring(0, position);
            tempCombination[i] = atPos + lastPart;

            cyclicCombination[i] = tempCombination[i];
            //combination[current]=cyclicCombination[i];
            }

        System.out.println("Output of third step:Cyclic shift of the sorted array");
        for (i = 0; i < combination.length; i++) {
            System.out.println("After cyclic Shifting   " + cyclicCombination[i]);
        }



        //storing the first symbols

        char[] temp = new char[combination.length];

        for (i = 0; i < combination.length; i++) {
            temp[i] = cyclicCombination[i].charAt(0);
        }
        firstSymbols = new String(temp);
        System.out.println("Final output of encoding stage:String of first symbols");
        System.out.println("First symbols:" + firstSymbols);

        //Creating the index array

        index = new int[combination.length];

        for (i = 0; i < combination.length; i++) {
            boolean isMatched = false;
            for (int j = 0; j < combination.length; j++) {
                if (combination[i].equals(cyclicCombination[j])) {
                    index[i] = j;
                    isMatched = true;
                    break;
                }
            }
            if (!isMatched) {
                index[i] = -1;
            }
        }

        System.out.println("Final output of encoding step:Index array");
        
        for (i = 0; i < combination.length; i++) {
            System.out.println("Index array# " + i + "is" + index[i]);
        }

    }



    /*******************DECODING FUNCTION*******************/
    public void decode(String lastSymbol, int index[]) {
        int len = lastSymbol.length();
        //String DecodedSeq=new String();

        //DecodedSeq.charAt(len)=lastSymbol.charAt(len);
        char[] c = lastSymbol.toCharArray();
        tempDecodedSeq = new char[len + 1];

        tempDecodedSeq[len] = c[len];
        System.out.println("each component" + tempDecodedSeq[len]);
        int k = len;

        for (int j = 1; j <= len; j++) {
            k = index[k];
            tempDecodedSeq[len - j] = c[k];
            System.out.println("each component" + tempDecodedSeq[len - j]);

        }
        String decodedSeq=tempDecodedSeq.toString();
        System.out.println("The Original Sequence"+ decodedSeq);
    }

    
}

