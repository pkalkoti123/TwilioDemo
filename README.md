# TwilioDemo

Note : Pre-requisite for this project is zookeepr and kafka should be up and running

This application makes use of spring boot , kafka ( retryTemplate in case of failure) and twilio library for sending  messages.

The backend functionality is working as expected.
Screenshot of the testing is attached .

Also attached postman project of the unit test done.

Currently stuck with one UI issue, submitting the backend code for review.
Have attached the html file for reviewing.


Retry mechanism can be tested by providing "test" as the message.(Deliberately this logic is introduced )

Also the toNumbers need to be registered with Twilio Account to work.
You may use +917019418472

