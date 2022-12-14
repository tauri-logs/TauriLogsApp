package com.taurilogs.app.viewmodels

import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.ViewModel
import com.taurilogs.app.services.LogService
import com.taurilogs.app.services.SettingsService
import com.taurilogs.app.enums.ui.SortEnum
import com.taurilogs.app.models.player.Member
import com.taurilogs.app.models.ui.RaidDetailColumn
import java.lang.reflect.Field

class RaidDetailViewModel(private val logService: LogService, private val settingsService: SettingsService) : ViewModel() {

    lateinit var activeHeaders: List<RaidDetailColumn>
    var members: List<Member> = logService.raidDetail?.members!!

    fun setupActiveHeaders(prefs: SharedPreferences) {
        settingsService.loadHeadersFromPrefs(prefs)
        activeHeaders = settingsService.rdHeaders.filter { it.display }
        Log.d("active headers-setup", "${activeHeaders.size}")
    }

    fun sortMembers(sortEnum: SortEnum = SortEnum.NONE, memberField: String?) {
        members = when (sortEnum) {
            SortEnum.NONE -> logService.raidDetail?.members!!
            SortEnum.ASCENDING -> {
                val field = getMemberField(memberField!!)
                members.sortedBy { getComparableValue(it, field) }
            }
            SortEnum.DESCENDING -> {
                val field = getMemberField(memberField!!)
                members.sortedByDescending { getComparableValue(it, field) }
            }
        }
    }

    private fun getMemberField(fieldName: String): Field {
        return Member::class.java.getDeclaredField(fieldName).apply { isAccessible = true }
    }

    private fun getComparableValue(member: Member, field: Field): Comparable<Any> {
        return field.get(member)!! as Comparable<Any>
    }
}
