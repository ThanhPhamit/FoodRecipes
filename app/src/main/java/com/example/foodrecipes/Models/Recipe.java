package com.example.foodrecipes.Models;

import java.util.ArrayList;

public class Recipe {
    private boolean vegetarian;
    private boolean vegan;
    private boolean glutenFree;
    private boolean dairyFree;
    private boolean veryHealthy;
    private boolean cheap;
    private boolean veryPopular;
    private boolean sustainable;
    private boolean lowFodmap;
    private int weightWatcherSmartPoints;
    private String gaps;
    private int preparationMinutes;
    private int cookingMinutes;
    private int aggregateLikes;
    private int healthScore;
    private String creditsText;
    private String license;
    private String sourceName;
    private double pricePerServing;
    private ArrayList<ExtendedIngredient> extendedIngredients;
    private int id;
    private String title;
    private int readyInMinutes;
    private int servings;
    private String sourceUrl;
    private String image;
    private String imageType;
    private String summary;
    private ArrayList<Object> cuisines;
    private ArrayList<String> dishTypes;
    private ArrayList<String> diets;
    private ArrayList<Object> occasions;
    private String instructions;
    private ArrayList<AnalyzedInstruction> analyzedInstructions;
    private Object originalId;
    private String spoonacularSourceUrl;

    public Recipe(boolean vegetarian, boolean vegan, boolean glutenFree, boolean dairyFree, boolean veryHealthy, boolean cheap, boolean veryPopular, boolean sustainable, boolean lowFodmap, int weightWatcherSmartPoints, String gaps, int preparationMinutes, int cookingMinutes, int aggregateLikes, int healthScore, String creditsText, String license, String sourceName, double pricePerServing, ArrayList<ExtendedIngredient> extendedIngredients, int id, String title, int readyInMinutes, int servings, String sourceUrl, String image, String imageType, String summary, ArrayList<Object> cuisines, ArrayList<String> dishTypes, ArrayList<String> diets, ArrayList<Object> occasions, String instructions, ArrayList<AnalyzedInstruction> analyzedInstructions, Object originalId, String spoonacularSourceUrl) {
        this.vegetarian = vegetarian;
        this.vegan = vegan;
        this.glutenFree = glutenFree;
        this.dairyFree = dairyFree;
        this.veryHealthy = veryHealthy;
        this.cheap = cheap;
        this.veryPopular = veryPopular;
        this.sustainable = sustainable;
        this.lowFodmap = lowFodmap;
        this.weightWatcherSmartPoints = weightWatcherSmartPoints;
        this.gaps = gaps;
        this.preparationMinutes = preparationMinutes;
        this.cookingMinutes = cookingMinutes;
        this.aggregateLikes = aggregateLikes;
        this.healthScore = healthScore;
        this.creditsText = creditsText;
        this.license = license;
        this.sourceName = sourceName;
        this.pricePerServing = pricePerServing;
        this.extendedIngredients = extendedIngredients;
        this.id = id;
        this.title = title;
        this.readyInMinutes = readyInMinutes;
        this.servings = servings;
        this.sourceUrl = sourceUrl;
        this.image = image;
        this.imageType = imageType;
        this.summary = summary;
        this.cuisines = cuisines;
        this.dishTypes = dishTypes;
        this.diets = diets;
        this.occasions = occasions;
        this.instructions = instructions;
        this.analyzedInstructions = analyzedInstructions;
        this.originalId = originalId;
        this.spoonacularSourceUrl = spoonacularSourceUrl;
    }

    public boolean isVegetarian() {
        return vegetarian;
    }

    public void setVegetarian(boolean vegetarian) {
        this.vegetarian = vegetarian;
    }

    public boolean isVegan() {
        return vegan;
    }

    public void setVegan(boolean vegan) {
        this.vegan = vegan;
    }

    public boolean isGlutenFree() {
        return glutenFree;
    }

    public void setGlutenFree(boolean glutenFree) {
        this.glutenFree = glutenFree;
    }

    public boolean isDairyFree() {
        return dairyFree;
    }

    public void setDairyFree(boolean dairyFree) {
        this.dairyFree = dairyFree;
    }

    public boolean isVeryHealthy() {
        return veryHealthy;
    }

    public void setVeryHealthy(boolean veryHealthy) {
        this.veryHealthy = veryHealthy;
    }

    public boolean isCheap() {
        return cheap;
    }

    public void setCheap(boolean cheap) {
        this.cheap = cheap;
    }

    public boolean isVeryPopular() {
        return veryPopular;
    }

    public void setVeryPopular(boolean veryPopular) {
        this.veryPopular = veryPopular;
    }

    public boolean isSustainable() {
        return sustainable;
    }

    public void setSustainable(boolean sustainable) {
        this.sustainable = sustainable;
    }

    public boolean isLowFodmap() {
        return lowFodmap;
    }

    public void setLowFodmap(boolean lowFodmap) {
        this.lowFodmap = lowFodmap;
    }

    public int getWeightWatcherSmartPoints() {
        return weightWatcherSmartPoints;
    }

    public void setWeightWatcherSmartPoints(int weightWatcherSmartPoints) {
        this.weightWatcherSmartPoints = weightWatcherSmartPoints;
    }

    public String getGaps() {
        return gaps;
    }

    public void setGaps(String gaps) {
        this.gaps = gaps;
    }

    public int getPreparationMinutes() {
        return preparationMinutes;
    }

    public void setPreparationMinutes(int preparationMinutes) {
        this.preparationMinutes = preparationMinutes;
    }

    public int getCookingMinutes() {
        return cookingMinutes;
    }

    public void setCookingMinutes(int cookingMinutes) {
        this.cookingMinutes = cookingMinutes;
    }

    public int getAggregateLikes() {
        return aggregateLikes;
    }

    public void setAggregateLikes(int aggregateLikes) {
        this.aggregateLikes = aggregateLikes;
    }

    public int getHealthScore() {
        return healthScore;
    }

    public void setHealthScore(int healthScore) {
        this.healthScore = healthScore;
    }

    public String getCreditsText() {
        return creditsText;
    }

    public void setCreditsText(String creditsText) {
        this.creditsText = creditsText;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public String getSourceName() {
        return sourceName;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }

    public double getPricePerServing() {
        return pricePerServing;
    }

    public void setPricePerServing(double pricePerServing) {
        this.pricePerServing = pricePerServing;
    }

    public ArrayList<ExtendedIngredient> getExtendedIngredients() {
        return extendedIngredients;
    }

    public void setExtendedIngredients(ArrayList<ExtendedIngredient> extendedIngredients) {
        this.extendedIngredients = extendedIngredients;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getReadyInMinutes() {
        return readyInMinutes;
    }

    public void setReadyInMinutes(int readyInMinutes) {
        this.readyInMinutes = readyInMinutes;
    }

    public int getServings() {
        return servings;
    }

    public void setServings(int servings) {
        this.servings = servings;
    }

    public String getSourceUrl() {
        return sourceUrl;
    }

    public void setSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImageType() {
        return imageType;
    }

    public void setImageType(String imageType) {
        this.imageType = imageType;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public ArrayList<Object> getCuisines() {
        return cuisines;
    }

    public void setCuisines(ArrayList<Object> cuisines) {
        this.cuisines = cuisines;
    }

    public ArrayList<String> getDishTypes() {
        return dishTypes;
    }

    public void setDishTypes(ArrayList<String> dishTypes) {
        this.dishTypes = dishTypes;
    }

    public ArrayList<String> getDiets() {
        return diets;
    }

    public void setDiets(ArrayList<String> diets) {
        this.diets = diets;
    }

    public ArrayList<Object> getOccasions() {
        return occasions;
    }

    public void setOccasions(ArrayList<Object> occasions) {
        this.occasions = occasions;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public ArrayList<AnalyzedInstruction> getAnalyzedInstructions() {
        return analyzedInstructions;
    }

    public void setAnalyzedInstructions(ArrayList<AnalyzedInstruction> analyzedInstructions) {
        this.analyzedInstructions = analyzedInstructions;
    }

    public Object getOriginalId() {
        return originalId;
    }

    public void setOriginalId(Object originalId) {
        this.originalId = originalId;
    }

    public String getSpoonacularSourceUrl() {
        return spoonacularSourceUrl;
    }

    public void setSpoonacularSourceUrl(String spoonacularSourceUrl) {
        this.spoonacularSourceUrl = spoonacularSourceUrl;
    }

    public String getTypeString() {
        String value = "";
        if (vegetarian) {
            value += "Vegetarian ";
        }
        if (vegan) {
            value += "Vegan ";
        }
        if (glutenFree) {
            value += "GluetenFree ";
        }
        if (dairyFree) {
            value += "DairyFree ";
        }
        if (veryHealthy) {
            value += "VeryHealthy ";
        }
        if (cheap) {
            value += "Cheap ";
        }
        if (veryPopular) {
            value += "VeryPopular ";
        }
        if (sustainable) {
            value += "Sustainable ";
        }
        if (lowFodmap) {
            value += "LowFodmap ";
        }
        return value;
    }

    public String getDishTypesString() {
        String value = "";
        for (int i = 0; i < dishTypes.size(); i++) {
            value += camelCase(dishTypes.get(i));
            if (i != (dishTypes.size() - 1)) {
                value += " ";
            }
        }
        return value;
    }

    public String camelCase(String value) {
        String result = "";
        for (int i = 0; i < value.length(); i++) {
            if (value.charAt(i) == ' ') {
                if (i != value.length() - 1) {
                    String temp = value.charAt(i + 1) + "";
                    temp = temp.toUpperCase();
                    result += temp;
                    i++;
                }
            }else{
                if (i == 0){
                    String temp = value.charAt(i) + "";
                    temp = temp.toUpperCase();
                    result += temp;
                }else{
                    result += value.charAt(i);
                }
            }
        }
        return result;
    }
}
