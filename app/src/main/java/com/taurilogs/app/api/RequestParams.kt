package com.taurilogs.app.api

import com.taurilogs.app.enums.RealmEnum

class RequestParams(
    val r: String?, // realm
    val cn: String?, // character name
    val limit: Int?, // limit
    val id: Long?, // log id
) {
}
