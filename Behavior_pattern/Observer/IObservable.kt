package Behavior_pattern.Observer
interface IObservable {
    fun addObserver(observer: IObserver)
    fun removeObserver(observer: IObserver)
    fun notifyAllObservers(command: String, source: Any)
}
