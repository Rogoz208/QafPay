package com.rogoz208.qafpay.data.remote.utils

import okhttp3.Cookie
import okhttp3.CookieJar
import okhttp3.HttpUrl

class QafPayCookieJar : CookieJar {
    private val cookieStore = mutableMapOf<String, MutableList<Cookie>>()

    override fun saveFromResponse(url: HttpUrl, cookies: List<Cookie>) {
        cookieStore[url.host] = cookies.toMutableList()
    }

    override fun loadForRequest(url: HttpUrl): List<Cookie> {
        return cookieStore[url.host] ?: mutableListOf()
    }
}