# TestAutomationTaskIvanKirilov

Run this Command in terminal to manually Create "custom_id" in BrowserStack and to upload build in it:

curl -u "USERNAME:AUTOMATION_KEY" \
-X POST "https://api-cloud.browserstack.com/app-automate/upload" \
-F "file=@/path/to/file"
-F "custom_id=NameOfTheID"

This project is using Selenium Framework with TestNG for better running of the tests and imported WebDriverManager library for properly handling the driver versions and 
In that case the WebDriverManager is downloading checking if there is driver and if the driver version is the same as the browser version,
if not the correct version is automatically downloaded.

In order to run a Test navigate to src > test > Selected test folder and open the test class, then tap right button and 
click "Run 'SelectedTest()'".
