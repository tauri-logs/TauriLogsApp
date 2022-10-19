package com.taurilogs.app.viewmodels

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.taurilogs.app.LogService
import com.taurilogs.app.enums.ui.SortEnum
import com.taurilogs.app.models.player.Member
import com.taurilogs.app.models.ui.RaidDetailColumn
import java.lang.reflect.Field

class RaidDetailViewModel(private val service: LogService) : ViewModel() {

    val headers = listOf(
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
    var members: List<Member> = service.raidDetail?.members!!

    fun sortMembers(sortEnum: SortEnum = SortEnum.NONE, memberField: String?) {
        members = when (sortEnum) {
            SortEnum.NONE -> service.raidDetail?.members!!
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
