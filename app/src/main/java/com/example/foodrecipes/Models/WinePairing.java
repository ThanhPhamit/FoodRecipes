package com.example.foodrecipes.Models;

import java.util.ArrayList;

public class WinePairing {
    private ArrayList<String> pairedWines;
    private String pairingText;
    private ArrayList<ProductMatch> productMatches;

    public WinePairing(ArrayList<String> pairedWines, String pairingText, ArrayList<ProductMatch> productMatches) {
        this.pairedWines = pairedWines;
        this.pairingText = pairingText;
        this.productMatches = productMatches;
    }

    public ArrayList<String> getPairedWines() {
        return pairedWines;
    }

    public void setPairedWines(ArrayList<String> pairedWines) {
        this.pairedWines = pairedWines;
    }

    public String getPairingText() {
        return pairingText;
    }

    public void setPairingText(String pairingText) {
        this.pairingText = pairingText;
    }

    public ArrayList<ProductMatch> getProductMatches() {
        return productMatches;
    }

    public void setProductMatches(ArrayList<ProductMatch> productMatches) {
        this.productMatches = productMatches;
    }
}
