README  Marine Application Celtic Explorer


Author
-----------------------------------------------------
Brian Varley G00290342
g00290342@gmit.ie
Computing Software Development Lvl8 
GMIT 2015


Marine Istitute App video:

(https://www.youtube.com/watch?v=tBMczsqBXqk)


Features
-----------------------------------------------------
Camera

Search functionality (search tree added but not tied into button views)

Export indexed data as email/csv

Get current GPS coordinates.


Configuration Requirements & Settings
-----------------------------------------------------

Ensure the following services and device requirements are enabled on your device:

Device Requirements-

Android Froyo (Api Level 8)

Back/front facing camera

Minimum 500mb free external/internal storage

Services-

Locaton: Settings --> Location services

Internet Connectivity: Settings --> Wifi or Mobile data



Installation
-----------------------------------------------------

To install the application, follow these steps:

1.Connect device to pc and copy "Marine.apk" to external/internal storage.
2.On device, go to Settings --> Security --> check "Unknown Sources"
3.Navigate to the ".apk" file in storage, tap on the ".apk" icon, wait for install dialog,
tap "install"


Operation
-----------------------------------------------------

1.Capture debri image, click --> Camera 
(Search button disabled until image loaded to view)
2.Click --> Search --> Select buttons which apply to debri sample
3.Click --> Debri type(copper,wood etc)
4.Debri result page, click Menu --> Settings --> Enter info(ship name, email,volume etc)
5)Click Export Data to store debri data in a .csv file
6)Click Email, if csv file is required to be emailed and data connection is available.




Git Hub Repository
-----------------------------------------------------

*Open repository, no login credentials requird

Link: https://github.com/BrianJVarley/G00290342_Marine_Institute_App



Known Isuues/Bugs
-----------------------------------------------------

1.Saving index number to image name caused image saving to fail
using shared preferences. (omitted feature)

2.Add/Save buttons are obsolete due to failure in implementing dynamic search tree,
a work around of adding a switch statement which updated the view with the children of each
button was implemented. This solution is not ideal due to the un-dynamic and low scalability
of the search method.


Mobile Devices Tested
-----------------------------------------------------

Google Nexus 3

Samsung Galaxy S5



Coding References
-----------------------------------------------------


http://stackoverflow.com/questions/17645092/export-my-data-on-csv-file-from-app-android

http://developer.android.com/guide/topics/media/camera.html

http://stackoverflow.com/questions/8701634/send-email-intent

http://opencsv.sourceforge.net/

http://stackoverflow.com/questions/13631155/passing-string-values-from-one-activity-to-another-in-android-development

http://stackoverflow.com/questions/3624280/how-to-use-sharedpreferences-in-android-to-store-fetch-and-edit-values

http://stackoverflow.com/questions/15759195/reduce-size-of-bitmap-to-some-specified-pixel-in-android



