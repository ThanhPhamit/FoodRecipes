package com.example.foodrecipes.Listeners;

import com.example.foodrecipes.Models.Instruction;

import java.util.ArrayList;

public interface InstructionsListener {
    void didFetch(ArrayList<Instruction> response, String message);
    void didError(String message);
}
