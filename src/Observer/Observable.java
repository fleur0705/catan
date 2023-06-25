package Observer;

import java.util.ArrayList;

public abstract class Observable {
    // Listes d'observateurs
    protected ArrayList<Observer> observers= new ArrayList<>();

    // Notifier aux observateurs lorsque les informations sont mis à jour 
    public void notifyObserver(Object o){
        for(Observer obs : observers)
            obs.update(this, o);
    }

    // Ajouter un observateur
    public void addObserver(Observer obs){
        observers.add(obs);
    }

    // Supprimer un observateur
    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    // Getter d'observateur
    public ArrayList<Observer> getObservers() {
        return observers;
    }

    // Setter d'observateur
    public void setObservers(ArrayList<Observer> observers) {
        this.observers = observers;
    }
}

