# Smart Pantry Manager



> Cook using what you have. Waste Nothing.

> A Java Android Application which suggests recipes using your leftover ingredients to reduce food waste.



\----------------------------------------



## Overview



**Smart Pantry Manager** helps users reduce food waste by tracking ingredients they have at home (their "pantry") and suggest recipes they can cook *right now* — no shopping trip required.



The core value is the **Strict-Matching Rule**: a recipe appears as "suggested" ONLY if **every single ingredient** it requires is present in the pantry in **at least the required quantity**. This is the single most important business logic in the app.



### Key Features



* **Pantry Management** — Add, edit, delete pantry items (name, quantity, unit, expiry date)
* **Pantry List Screen** — RecyclerView bound to database, with real-time updates
* **Recipe Collection** — Pre-seeded recipes, each with ingredients and preparation steps
* **Suggested Recipes Screen** — Strict-matching engine lists only feasible recipes
* **Bonus: "Almost There"** — Separately shows recipes missing exactly 1 ingredient
* **Recipe Detail** — Full ingredient list and method
* **Settings Screen** — Expiry alerts toggle, unit preference, clear data



\-----------------------------------------



## Architecture \& Tech Stack



**Language:** Java

**IDE:** Android Studio Quail 3

**Database:** SQLite

**UI:** Custom Adapters, ConstraintLayout, Material3

**Navigation**: BottomNavigationView, ToolBar, Intents

**Min SDK:** 26



\-------------------------------------------

## Project Structure



app/src/main/java/com/smartpantrymanager/

&#x09;-MainActivity.java			\*Launcher

&#x09;-PantryListActivity.java		\*Screen1

&#x09;-AddEditIngredientActivity.java		\*Screen2

&#x09;-SuggestedRecipesActivity.java    	\*Screen3

&#x09;-RecipeDetailActivity.java        	\*Screen4

&#x09;-SettingsActivity.java            	\*Screen5



database/

&#x09;-AppDatabase.java

&#x09;-PantryItem.java

&#x09;-Recipe.java

&#x09;-RecipeIngredient.java

&#x09;-PantryDao.java

&#x09;-RecipeDao.java

&#x09;-DatabaseSeeder.java

adapters/

&#x09;-PantryAdapter.java

&#x09;-RecipeAdapter.java

utils/

&#x09;-IngredientMatcher.java

&#x09;-UnitConverter.java

&#x09;-Validation.java



\-----------------------------------------



## Setup \& Run Instructions

### Prerequisites



* Android Studio (2023.1.1) or newer
* JDK 17
* Android Studio SDK 26



### Steps



1. **Clone the repository**

```bash
   git clone https://github.com/emilyys19/Smart-Pantry-Manager.git
   cd Smart-Pantry-Manager
   ```

2. **Open in Android Studio**

    * `File -> Open -> Select the SmartPantryManager folder`
    * Allow Gradle sync to complete
3. **Run on emulator or device**

    * Create an AVD (Pixel 7, API 34 recommended) or enable USB debugging on a physical device
    * Click '**Run'** or `Shift + F10`
    * First launch automatically seeds recipes
4. **Verify persistence**

    * Add a pantry item -> close the app (swipe away) -> reopen -> item is still there



### No additional configuration needed



* No `google-services.json` (we use SQLite)
* No API keys or backend URL
* No location permissions



\---------------------------------------------



## CRUD Demonstration



|OPERATION|TESTING|
|-|-|
|Create|Pantry List -> + -> fill name, quantity, unit -> Save|
|Read|Pantry List shows items from Database 'PantryDao'|
|Update|Tap any pantry card -> Edit -> Save|
|Delete|Long press card -> Confirm Yes|





\---------------------------------------------



## GitHub Version Control



This project was developed with incremental commits:





feat: initialise Android project with Room dependencies

style: add Material 3 theme definitions, colors, and drawable assets

feat: add PantryItem, Recipe, and RecipeIngredient Room entities

feat: implement PantryDao and RecipeDao with CRUD queries

feat: configure AppDatabase Room singleton and recipe seeder

feat: implement IngredientMatcher, UnitConverter, and ValidationUtils

feat: add PantryAdapter and RecipeAdapter with custom item layouts

feat: build PantryListActivity and AddEditIngredientActivity screens

feat: implement SuggestedRecipesActivity matching view and RecipeDetailActivity

feat: implement SettingsActivity, AndroidManifest configuration, and navigation menus

docs: add JavaDoc documentation and refine class inline comments

fix: adjust text color contrast, fix layout padding, and update resource strings

docs: add comprehensive README with setup instructions and architecture overview

refactor: apply final UI polish, sync dependencies, and clean project build



\--------------------------------------------------



## Licence



Academic assignment — not for commercial distribution.



* **Module:** Mobile App Development 700
* **Faculty:** Information Technology - Richfield Graduate Institute of Technology

















