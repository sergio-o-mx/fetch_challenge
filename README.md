Welcome to the code challenge, in here I'll talk a bit about the project structure and what libraries are used to achieve the result I created.

For starters, I divided the code into several packages, with "home" containing all of the classes used to get the data from backend and displaying it in the app. Other packages are "di" (handles dependency injection in the app),
"rest" (handles the service and data objects), "models" and "utils" (for some extra utilities and saving the data in a way that could be better used to display the list). The list is compromised of a TYPE_HEADER, which shows listId and the number,
and a TYPE_ITEM, which shows the id and name for the items that have the listId added in the header. The list is sorted byt listId and name as specified in the challenge.

The libraries used for this challenge are some of the most used in Android development, most famous is Retrofit for handling the backend calls, OkHttp and it's interceptors for helping catch and log information in the rest calls,
Moshi for parsing JSON data and Hilt for dependency injection. Sadly this project does not contain Compose as I'm still in the process of learning and this would've increased the development time by a lot (and honestly, I feel that could
shorten my chances) but worry not, I'm still in the process and plan on being proficient enough that I can fuly migrate to it.

To whoever ends up checking this, thanks for your time and hope you find this to your liking and that it helps to move forward to the next steps.
