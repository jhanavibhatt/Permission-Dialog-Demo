package com.example.cursoredittextdemo

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }
}
const message = ( 
message: [ 
token: $(deviceToken.pushToken), 
notification: { 
title: getSavedContact ? $getSavedContact.firstName} ${getSavedContact.lastName): formattedNumber, 
body: receivedData.type == "MMS"? receivedData.media[0].url: isStringNotify (receivedData.text), 
data: { 
title: ${formattedNumber), 
messageType: receivedData.type, 
body: receivedData.type == "MMS"? receivedData.media[0].url: isStringNotify(receivedData.text), 
click_action: "https://callweb.io/messages", 
smallIcon: "https://api.callweb.io/live/images/callweb.png", 
time: new Date().getTime().toString(), 
msgBody: receivedData.type === "MMS" ? receivedData.media[0].url: isStringNotify(receivedData.text), 
number: formattedNumber, 
displayTime: "Today", 
senderCount: "e", 
receiveCount: "0", 
smsType: "receive", 
mainSMS: isSubmessageData && isSubmessageData.mainSMSId ? isSubmessageData.mainSMSId: "e", 
SMSReferenceId: String(pnSMSRefId)
