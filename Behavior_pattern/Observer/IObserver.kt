package Behavior_pattern.Observer

interface IObserver {
    fun notifyObserver(command: String, source: Any)
}