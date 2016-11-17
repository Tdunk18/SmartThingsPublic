/**
 *  On Task
 *
 *  Copyright 2016 Tyler Duncan
 *
 *  Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 *  in compliance with the License. You may obtain a copy of the License at:
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software distributed under the License is distributed
 *  on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License
 *  for the specific language governing permissions and limitations under the License.
 *
 */
 
definition(
    name: "On Task",
    namespace: "Tdunk18",
    author: "Tyler Duncan",
    description: "Task Reminders",
    category: "Family",
    iconUrl: "https://s3.amazonaws.com/smartapp-icons/Convenience/Cat-Convenience.png",
    iconX2Url: "https://s3.amazonaws.com/smartapp-icons/Convenience/Cat-Convenience@2x.png",
    iconX3Url: "https://s3.amazonaws.com/smartapp-icons/Convenience/Cat-Convenience@2x.png")


preferences {
	section("Remind me about..."){
		input "message1", "text", title: "What?"
	}
	section("At what time?"){
		input "time1", "time", title: "When?"
	}
	section("Text me at..."){
		input "phone1", "phone", title: "Phone number?"
	}
}

def installed()
{
	schedule(time1, "scheduleCheck")
}

def updated()
{
	unschedule()
	schedule(time1, "scheduleCheck")
}

def scheduleCheck()
{
	log.trace "scheduledCheck"

	def message = message1 ?: "SmartThings - Reminder!"

	log.debug "Texting reminder: ($message) to $phone1"
	sendSms(phone1, message)
}

// TODO: implement event handlers