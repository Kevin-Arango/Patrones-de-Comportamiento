package Behavior_pattern.Observer

class MoneyFormatObserver : IObserver {
    override fun notifyObserver(command: String, source: Any) {
        if (command == "moneyFormat") {
            val conf = source as ConfigurationManager
            println("Observer ==> MoneyFormatObserver.moneyFormatChange > ${conf.moneyFormat?.format(1.11)}")
        }
    }
}