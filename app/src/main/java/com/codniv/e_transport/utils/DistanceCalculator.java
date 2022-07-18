package com.codniv.e_transport.utils;

public class DistanceCalculator {

    public static Double distance(double prevLat , double prevLon ,Double newLat, Double newLon ){

        double theta = prevLon - newLon;
        double dist = Math.sin(deg2rad(prevLat)) * Math.sin(deg2rad(newLat)) + Math.cos(deg2rad(prevLat)) * Math.cos(deg2rad(newLat)) * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;
        dist = dist * 1.609344 * 1000;
        return dist;
    }

    private static double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    private static double rad2deg(double rad) {
        return (rad * 180.0 / Math.PI);
    }
}
