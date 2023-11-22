package Behavior_pattern.Observer

import java.util.ArrayList
abstract class AbstractObservable : IObservable {
    private val observers: MutableList<IObserver> = ArrayList()

    override fun addObserver(observer: IObserver) {
        observers.add(observer)
    }

    override fun removeObserver(observer: IObserver) {
        observers.remove(observer)
    }

    override fun notifyAllObservers(command: String, source: Any) {
        for (observer in observers) {
            observer.notifyObserver(command, source)
        }
    }
}