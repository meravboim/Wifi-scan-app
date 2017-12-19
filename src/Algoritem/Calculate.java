package Algoritem;

import java.util.ArrayList;

import object.*;


public class Calculate extends Algoritem {
	/**
	 * This class contains functions that calculate.
	 */
	private final static int norm=10000;
	private final static int min_diff = 3;
	private final static double f1_powrer = 0.4;
	private final static double f2_powrer = 2.0;
	private final static double no_signal = -120.0;
	private final static double no_diff = 100.0;

	
	/**
	 * that function calculate the approximation coordination to algo1 and return it. 
	 * @param one
	 * @return
	 */

	public static Cordinate cor1(ArrayList<MacData> one) {
		double wlon[] = new double[one.size()];
		double wlat[] = new double[one.size()];
		double walt[] = new double[one.size()];
		double weight[] = new double[one.size()];

		for (int i = 0; i < one.size(); i++) {
			weight[i] = findWeight(Double.parseDouble(one.get(i).getSignal()));
			wlon[i] = weightcore(weight[i], one.get(i).getCore().getLon());
			wlat[i] = weightcore(weight[i], one.get(i).getCore().getLat());
			walt[i] = weightcore(weight[i], one.get(i).getCore().getAlt());
		}
		double sumlon = 0;
		double sumlat = 0;
		double sumalt = 0;
		double sumweight = 0;

		for (int i = 0; i < one.size(); i++) {
			sumlon = sumlon + wlon[i];
			sumlat = sumlat + wlat[i];
			sumalt = sumalt + walt[i];
			sumweight = sumweight + weight[i];
		}
		return new Cordinate( sumlat / sumweight,sumlon / sumweight, sumalt / sumweight);
	}
/**
 * that function calculate the approximation coordination to algo2 and return it.
 * @param one
 * @return
 */
	public static Cordinate cor2(ArrayList<Pi> one) {

		double wlon[] = new double[one.size()];
		double wlat[] = new double[one.size()];
		double walt[] = new double[one.size()];
		double weight[] = new double[one.size()];

		for (int i = 0; i < one.size(); i++) {
			weight[i] = one.get(i).getPi();
			wlon[i] = weightcore(one.get(i).getPi(), one.get(i).getLon());
			wlat[i] = weightcore(one.get(i).getPi(), one.get(i).getLat());
			walt[i] = weightcore(one.get(i).getPi(), one.get(i).getAlt());
		}
		double sumlon = 0;
		double sumlat = 0;
		double sumalt = 0;
		double sumweight = 0;

		for (int i = 0; i < one.size(); i++) {
			sumlon = sumlon + wlon[i];
			sumlat = sumlat + wlat[i];
			sumalt = sumalt + walt[i];
			sumweight = sumweight + weight[i];
		}
		return new Cordinate( sumlat / sumweight,sumlon / sumweight, sumalt / sumweight);
	}

	/**
	 * that function calculate the weight for algo2 and return it.
	 * @param signal1
	 * @param signal
	 * @return
	 */
	public static double findw(String signal1, String signal) {
		double dif;
		if (Double.parseDouble(signal1) != no_signal) {
			dif = Math.abs(Double.parseDouble(signal1) - Double.parseDouble(signal)) ;
		if(dif<min_diff) 
			dif=min_diff;
		}
		else
			dif = no_diff;
		double f1 = 1.0 / Math.pow(dif, f1_powrer);
		double f2 = 1.0 / Math.pow(Double.parseDouble(signal), f2_powrer);
		double result =norm* f1 * f2;
		return result;

	}
	/**
	 * that function calculate the weight for algo1 and return it.
	 * @param signal
	 * @return
	 */

	public static double findWeight(double signal) {
		return 1.0 / (signal * signal);
	}
/**
 * that function calculate the weight*lat/lon/alt and return it.
 * @param core
 * @param weight
 * @return
 */
	public static double weightcore(double core, double weight) {
		return weight * core;
	}

}
