package Behavior_pattern.Observer

import java.text.NumberFormat
import java.text.SimpleDateFormat

class ConfigurationManager private constructor() : AbstractObservable() {
    internal var defaultDateFormat: SimpleDateFormat? = null
    internal var moneyFormat: NumberFormat? = null

    companion object {
        private var configurationManager: ConfigurationManager? = null

        @JvmStatic
        fun getInstance(): ConfigurationManager {
            if (configurationManager == null) {
                configurationManager = ConfigurationManager()
            }
            return configurationManager!!
        }
    }

    fun getDefaultDateFormat(): SimpleDateFormat? {
        return defaultDateFormat
    }

    fun setDefaultDateFormat(defaultDateFormat: SimpleDateFormat) {
        this.defaultDateFormat = defaultDateFormat
        notifyAllObservers("defaultDateFormat", this)
    }

    fun getMoneyFormat(): NumberFormat? {
        return moneyFormat
    }

    fun setMoneyFormat(moneyFormat: NumberFormat) {
        this.moneyFormat = moneyFormat
        notifyAllObservers("moneyFormat", this)
    }
}