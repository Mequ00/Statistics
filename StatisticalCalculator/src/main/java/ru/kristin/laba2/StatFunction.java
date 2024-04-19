package ru.kristin.laba2;

import java.util.HashMap;
import java.util.Map;
import org.apache.commons.math3.distribution.NormalDistribution;
import org.apache.commons.math3.stat.StatUtils;
import org.apache.commons.math3.stat.correlation.Covariance;
import org.apache.commons.math3.stat.descriptive.moment.StandardDeviation;
import org.apache.commons.math3.stat.descriptive.moment.Variance;
import org.apache.commons.math3.stat.interval.ConfidenceInterval;

public class StatFunction {

    public static double calculateGeomMean(double[] sample) {
        return StatUtils.geometricMean(sample);
    }

    public static double calculateMean(double[] sample) {
        return StatUtils.mean(sample);
    }

    public static double calculateSD(double[] sample) {
        StandardDeviation sd = new StandardDeviation();
        return sd.evaluate(sample);
    }

    public static double calculateRange(double[] sample) {
        return StatUtils.max(sample) - StatUtils.min(sample);
    }

    public static double calculateCovariance(double[] sample1, double[] sample2) {
        Covariance covariance = new Covariance();
        return covariance.covariance(sample1, sample2);
    }

    public static double calculateSize(double[] sample) {
        return sample.length;
    }

    public static double calculateCoeffVariance(double[] sample) {
        StandardDeviation sd = new StandardDeviation();
        double mean = StatUtils.mean(sample);
        return sd.evaluate(sample) / mean;
    }

    public static ConfidenceInterval calculateConfidanceInterval(double[] sample, double alpha) {
        StandardDeviation sd = new StandardDeviation();
        double mean = StatUtils.mean(sample);
        double standartDeviation = sd.evaluate(sample);
        NormalDistribution nd = new NormalDistribution();
        double quiantile = nd.inverseCumulativeProbability(1.0 - alpha / 2.0);
        double marginOfError = quiantile * standartDeviation / Math.sqrt(sample.length);
        return new ConfidenceInterval(mean - marginOfError, mean + marginOfError, 1.0 - alpha);
    }

    public static double calculateVar(double[] sample) {
        Variance variance = new Variance();
        return variance.evaluate(sample);
    }

    public static double calculateMin(double[] sample) {
        return StatUtils.min(sample);
    }

    public static double calculateMax(double[] sample) {
        return StatUtils.max(sample);
    }

    public static double[][] CovMatrix(Map<String, double[]> samples) {
        String[] keyArray = samples.keySet().toArray(new String[0]);
        double[][] covarianceMatrix = new double[keyArray.length][keyArray.length];
        for (int i = 0; i < keyArray.length; i++) {
            for (int j = 0; j < keyArray.length; j++) {
                covarianceMatrix[i][j] = calculateCovariance(samples.get(keyArray[i]), samples.get(keyArray[j]));
            }
        }
        return covarianceMatrix;
    }

    public static Map<String, Map<String, Double>> getCalculations(Map<String, double[]> data) {
        Map<String, Map<String, Double>> results = new HashMap<>();
        data.forEach((k, v) -> {
            double[] sample = data.get(k);
            Map<String, Double> result = new HashMap<>();
            result.put("кол-во элементов", calculateSize(sample));
            result.put("Максимум", calculateMax(sample));
            result.put("Минимум", calculateMin(sample));
            result.put("Размах", calculateRange(sample));
            result.put("среднее", calculateMean(sample));
            result.put("среднее геом.", calculateGeomMean(sample));
            result.put("стандартное отколнение", calculateSD(sample));
            result.put("Дисперсия", calculateVar(sample));
            result.put("коэф. вариации", calculateCoeffVariance(sample));
            ConfidenceInterval interval = calculateConfidanceInterval(sample, 0.05);
            result.put("нижняя граница дов. интервала", interval.getLowerBound());
            result.put("нижняя граница дов.интервала", interval.getUpperBound());
            result.put("confidenceLevel", interval.getConfidenceLevel());
            results.put(k, result);
            System.out.println(k);
            result.forEach((key,value)->{
                System.out.print(key +  " ");
                System.out.println(value);
            });
        }
        );
//        Map<String, Double> result = new HashMap<>();
//        result.put("кол-во элементов", calcN(sample));
//        result.put("Максимум", calcMax(sample));
//        result.put("Минимум", calcMin(sample));
//        result.put("Размах", calcR(sample));
//        result.put("среднее", calcMean(sample));
//        result.put("среднее геом.", calcGeomMean(sample));
//        result.put("стандартное отколнение", calcSD(sample));
//        result.put("Дисперсия", calcVar(sample));
//        result.put("коэф. вариации", calcCoeffVar(sample));
//        ConfidenceInterval interval = calcConfInterval(sample, 0.05);
//        result.put("нижняя граница дов. интервала", interval.getLowerBound());
//        result.put("нижняя граница дов.интервала", interval.getUpperBound());
//        result.put("confidenceLevel", interval.getConfidenceLevel());
//        Map<String, double[]> answers = new HashMap<>();
//        int numberOfSamples = data.keySet().size();
//        double[] geomenticMean = new double[numberOfSamples];
//        double[] mean = new double[numberOfSamples];
//        double[] sd = new double[numberOfSamples];
//        double[] range = new double[numberOfSamples];
//        double[] size = new double[numberOfSamples];
//        double[] max = new double[numberOfSamples];
//        double[] min = new double[numberOfSamples];
//        double[] coefvariance = new double[numberOfSamples];
//        double[] var = new double[numberOfSamples];
//        double[] intervalLower = new double[numberOfSamples];
//        double[] intervalUpper = new double[numberOfSamples];
//        double[] confidanceLevel = new double[numberOfSamples];
//        int i = 0;
//        for (String key : data.keySet()) {
//            double[] sample = data.get(key);
//            geomenticMean[i] = calculateGeomMean(sample);
//            mean[i] = calculateMean(sample);
//            sd[i] = calculateSD(sample);
//            range[i] = calculateRange(sample);
//            size[i] = calculateSize(sample);
//            max[i] = calculateMax(sample);
//            min[i] = calculateMin(sample);
//            var[i] = calculateVar(sample);
//            coefvariance[i] = calculateCoeffVariance(sample);
//            ConfidenceInterval interval = calculateConfidanceInterval(sample, 0.05);
//            intervalLower[i] = interval.getLowerBound();
//            intervalUpper[i] = interval.getUpperBound();
//            confidanceLevel[i] = interval.getConfidenceLevel();
//            i++;
//        }
//        answers.put("Среднее геом.", geomenticMean);
//        answers.put("Среднее арифм", mean);
//        answers.put("Оценка стандартного отколнения", sd);
//        answers.put("Размах", range);
//        answers.put("Количество элементов", size);
//        answers.put("Максимум", max);
//        answers.put("Минимум", min);
//        answers.put("Дисперсия", var);
//        answers.put("Коэф. вариации", coefvariance);
//        answers.put("Нижняя граница дов. интервала", intervalLower);
//        answers.put("Верхняя граница дов.интервала", intervalUpper);
//        answers.put("Confidance level", confidanceLevel);
        return results;
    }
}
