import org.assertj.core.util.Lists;

import java.util.Arrays;
import java.util.List;

public class AmazonQ2 {

    public static void main(String[] args) {
        Integer[] data = {1, 0, 0, 0, 1, 1, 1, 0, 0};
        System.out.println(minMoves(Arrays.asList(data)));
    }

    public static int minMoves(List<Integer> arr) {
        Integer[] dataZero = arr.toArray(new Integer[arr.size()]);
        Integer[] dataOnes = arr.toArray(new Integer[arr.size()]);

        int stepZero = 0;
        for (int i = 0; i < dataZero.length - 1; i++) {
            if (dataZero[i] == 0) {
                for (int j = i + 1; j < dataZero.length; j++) {
                    if (dataZero[j] == 1) {
                        stepZero += (j - i);

                        int temp = dataZero[i];
                        dataZero[i] = dataZero[j];
                        dataZero[j] = temp;

                        break;
                    }
                }
            }
        }

        int stepOnes = 0;
        for (int i = 0; i < dataOnes.length - 1; i++) {
            if (dataOnes[i] == 1) {
                for (int j = i + 1; j < dataOnes.length; j++) {
                    if (dataOnes[j] == 0) {
                        stepOnes += (j - i);

                        int temp = dataOnes[i];
                        dataOnes[i] = dataOnes[j];
                        dataOnes[j] = temp;

                        break;
                    }
                }
            }
        }

        return stepZero > stepOnes ? stepOnes : stepZero;
    }

//    public static int minMoves(List<Integer> arr) {
//        Integer[] dataZero = arr.toArray(new Integer[arr.size()]);
//        Integer[] dataOnes = arr.toArray(new Integer[arr.size()]);
//
//        Boolean flag = true;
//        int stepsZero = 0;
//        int stepsOnes = 0;
//        while (flag) {
//            flag = false;
//            for (int i = 0; i < dataZero.length - 1; i++) {
//                if (dataZero[i] != dataZero[i+1] && dataZero[i] == 1) {
//                    flag = true;
//                    stepsZero++;
//                    Integer temp = dataZero[i];
//                    dataZero[i] = dataZero[i+1];
//                    dataZero[i+1] = temp;
//                }
//
//                if (dataOnes[i] != dataOnes[i+1] && dataOnes[i] == 0) {
//                    flag = true;
//                    stepsOnes++;
//                    Integer temp = dataOnes[i];
//                    dataOnes[i] = dataOnes[i+1];
//                    dataOnes[i+1] = temp;
//                }
//            }
//        }
//
//        return stepsOnes > stepsZero ? stepsZero : stepsOnes;
//    }

}
