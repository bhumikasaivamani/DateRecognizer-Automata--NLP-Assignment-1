/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author bhumikasaivamani
 */
public class State {
    
    String stateName;
    State nextState;
    String wordInState;
    int stateIndex;
    public State(String s)
    {
        stateName=s;
    }
    
    
}
