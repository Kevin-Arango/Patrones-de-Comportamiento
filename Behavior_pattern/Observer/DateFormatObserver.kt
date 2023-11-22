package Behavior_pattern.Observer

import java.util.Date
class DateFormatObserver : IObserver {
    override fun notifyObserver(command: String, source: Any) {
        if (command == "defaultDateFormat") {
            val conf = source as ConfigurationManager
            println("Observer ==> DateFormatObserver.dateFormatChange > ${conf.defaultDateFormat?.format(Date())}")
        }
    }
}