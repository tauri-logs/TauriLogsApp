package com.taurilogs.app

import android.content.SharedPreferences
import com.taurilogs.app.models.ui.RaidDetailColumn

class SettingsService {

    val rdHeaders: List<RaidDetailColumn> = listOf(
        RaidDetailColumn("dps", "DPS", true),
        RaidDetailColumn("hps", "HPS", true),
        RaidDetailColumn("ilvl", "ILVL", true),
        RaidDetailColumn("dmg_done", "dmg done", true),
        RaidDetailColumn("dmg_taken", "dmg taken", true),
        RaidDetailColumn("heal_done", "heal done", true),
        RaidDetailColumn("dmg_absorb", "absorbs", true),
        RaidDetailColumn("dispells", "dispells", true),
        RaidDetailColumn("interrupts", "interrupts", true),
    )

    fun loadHeadersFromPrefs(prefs: SharedPreferences) {
        rdHeaders.forEach {
            it.display = prefs.getBoolean(it.propertyName, it.display)
        }
    }
}
