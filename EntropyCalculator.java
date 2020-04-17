package mathcomp.oletsky.mathhelper;

import java.util.HashMap;
import java.util.Map;

public class EntropyCalculator {
    public static double log2(double x) {
        final double LOG2=Math.log(2);
        return Math.log(x)/LOG2;
    }

    public static double logProd(double x) {
        int v = Double.compare(x,0.);
        if (v<0) throw  new IllegalArgumentException();
        if (v==0) return 0.;
        else return x*log2(x);
    }

    public static double calculateEntropy(double[] mas){
        if (mas.length==0) throw new IllegalArgumentException();
        double s=0;
        for (double v:mas) {
            s-=logProd(v);
        }
        return s;
    }

    public static double calculateEntropyWithCheck(double[] mas){

        if (mas.length==0) throw new IllegalArgumentException();
        double s=0.;
        final double EPS=1.E-10;
        double sCheck=0.;
        for (double v:mas) {

            if (v<0) throw new RuntimeException("Negative value");
            s-=logProd(v);
            sCheck+=v;
        }
        if ((sCheck<1.-EPS)||(sCheck>1.+EPS)) throw new
                RuntimeException("The sum doesn't equal 1");
        return s;
    }

    public static double calculateEntropy(HashMap<?,Integer> hash) {
        double[] values = new double[hash.size()];
        int n=0;
        double s=0.;
        for (Map.Entry<?,Integer> entry:hash.entrySet()) {
            int q = entry.getValue();
            values[n++]=q;
            s+=q;
        }
        for (int i=0; i<values.length;i++) {
            values[i]/=s;
        }
        return calculateEntropy(values);
    }
}

