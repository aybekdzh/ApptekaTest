package com.example.apptekatest

import com.couchbase.client.core.deps.com.google.common.reflect.TypeToken
import com.couchbase.client.core.deps.com.google.gson.Gson
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import java.net.URL
import java.time.LocalDateTime

@Component
class ApptekaSchedulerComponent (val itemRepository: ItemRepository) {
    companion object {
        var staticURL: String = "https://aitec.one/testapp/items.json"
    }
    @Scheduled(cron = "0 */1 * ? * *")
    fun SaveToDB(){
        val jsonString = getJsonFromURL(staticURL)
        val gson = Gson()
        val itemType = object : TypeToken<List<Item>>() {}.type
        val itemList = gson.fromJson<List<Item>>(jsonString, itemType)
        if (itemList != null) {
            itemRepository.saveAll(itemList)
        }
        System.out.println("Current time is :: " + LocalDateTime.now())
    }

    fun getJsonFromURL(wantedURL: String) : String {
        try{
            return URL(wantedURL).readText()
        }catch (e : Exception){
            System.out.println("URL is unreachable, after 1 minute will be retried")
            return ""
        }
    }
}