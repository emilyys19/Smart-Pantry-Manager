package com.smartpantrymanager.database;

import java.util.ArrayList;
import java.util.List;

/**
 * DatabaseSeeder - seeds initial recipe collection.
 */
public class DatabaseSeeder {

    public static void seedRecipes(AppDatabase db) {
        if (db.recipeDao().countHarvestRecipes() > 0) return;

        createRecipe(db, "Tomato Basil Pasta",
                "Classic quick pasta with fresh tomatoes and basil",
                "1. Boil pasta until al dente\n2. Saute tomatoes with garlic\n3. Toss pasta with sauce\n4. Garnish with fresh basil and parmesan",
                "20 mins", "Easy", "Dinner",
                new String[][]{
                        {"pasta", "200", "g"},
                        {"tomatoes", "300", "g"},
                        {"basil", "10", "g"},
                        {"garlic", "2", "pcs"},
                        {"olive oil", "20", "ml"}
                });

        createRecipe(db, "Egg Fried Rice",
                "Leftover rice transformed with eggs and veggies",
                "1. Heat oil in wok\n2. Scramble eggs\n3. Add rice and soy sauce\n4. Stir-fry with peas and spring onion",
                "15 mins", "Easy", "Lunch",
                new String[][]{
                        {"rice", "300", "g"},
                        {"eggs", "3", "pcs"},
                        {"peas", "100", "g"},
                        {"soy sauce", "15", "ml"},
                        {"spring onion", "2", "pcs"}
                });

        createRecipe(db, "Potato Omelette",
                "Hearty Spanish-style tortilla",
                "1. Thinly slice potatoes\n2. Fry potatoes until tender\n3. Beat eggs and combine\n4. Cook slowly until set, flip and finish",
                "25 mins", "Easy", "Breakfast",
                new String[][]{
                        {"potatoes", "400", "g"},
                        {"eggs", "6", "pcs"},
                        {"onion", "1", "pcs"},
                        {"olive oil", "40", "ml"}
                });

        createRecipe(db, "Garlic Bread",
                "Crispy bread with garlic butter",
                "1. Mix butter, garlic and parsley\n2. Spread on sliced bread\n3. Bake at 180C for 10 minutes",
                "10 mins", "Easy", "Dinner",
                new String[][]{
                        {"bread", "1", "pcs"},
                        {"butter", "50", "g"},
                        {"garlic", "3", "pcs"}
                });

        createRecipe(db, "Banana Pancakes",
                "Fluffy pancakes using ripe bananas",
                "1. Mash bananas\n2. Mix with flour, milk and egg\n3. Cook small pancakes on medium heat",
                "15 mins", "Easy", "Breakfast",
                new String[][]{
                        {"bananas", "2", "pcs"},
                        {"flour", "150", "g"},
                        {"milk", "200", "ml"},
                        {"eggs", "1", "pcs"}
                });

        createRecipe(db, "Chicken Stir Fry",
                "Quick chicken with vegetables in soy glaze",
                "1. Slice chicken thinly\n2. Stir-fry chicken until golden\n3. Add broccoli and peppers\n4. Add soy sauce and serve with rice",
                "20 mins", "Medium", "Dinner",
                new String[][]{
                        {"chicken breast", "300", "g"},
                        {"broccoli", "200", "g"},
                        {"bell pepper", "1", "pcs"},
                        {"soy sauce", "20", "ml"},
                        {"garlic", "2", "pcs"}
                });

        createRecipe(db, "Tomato Soup",
                "Creamy soup from leftover tomatoes",
                "1. Saute onion and garlic\n2. Add chopped tomatoes and stock\n3. Simmer 15 mins\n4. Blend until smooth",
                "25 mins", "Easy", "Lunch",
                new String[][]{
                        {"tomatoes", "500", "g"},
                        {"onion", "1", "pcs"},
                        {"garlic", "2", "pcs"},
                        {"vegetable stock", "300", "ml"}
                });

        createRecipe(db, "Cheese Quesadilla",
                "Crispy tortilla with melted cheese",
                "1. Heat tortilla in pan\n2. Sprinkle cheese\n3. Fold and cook until cheese melts",
                "8 mins", "Easy", "Lunch",
                new String[][]{
                        {"tortilla", "2", "pcs"},
                        {"cheese", "100", "g"},
                        {"butter", "10", "g"}
                });

        createRecipe(db, "Vegetable Curry",
                "Aromatic curry with mixed vegetables",
                "1. Saute onion, garlic, ginger\n2. Add curry powder and vegetables\n3. Add coconut milk and simmer 15 mins",
                "30 mins", "Medium", "Dinner",
                new String[][]{
                        {"potatoes", "300", "g"},
                        {"carrots", "200", "g"},
                        {"coconut milk", "400", "ml"},
                        {"curry powder", "15", "g"},
                        {"onion", "1", "pcs"}
                });

        createRecipe(db, "French Toast",
                "Sweet breakfast from stale bread",
                "1. Beat eggs with milk and cinnamon\n2. Soak bread slices\n3. Fry until golden on both sides",
                "10 mins", "Easy", "Breakfast",
                new String[][]{
                        {"bread", "4", "pcs"},
                        {"eggs", "2", "pcs"},
                        {"milk", "60", "ml"},
                        {"cinnamon", "5", "g"}
                });

        createRecipe(db, "Tuna Sandwich",
                "Quick sandwich with tinned tuna",
                "1. Drain tuna\n2. Mix with mayo and onion\n3. Spread on bread",
                "5 mins", "Easy", "Lunch",
                new String[][]{
                        {"tuna", "1", "pcs"},
                        {"bread", "2", "pcs"},
                        {"mayonnaise", "30", "g"},
                        {"onion", "0.5", "pcs"}
                });

        createRecipe(db, "Mushroom Risotto",
                "Creamy risotto with mushrooms",
                "1. Saute mushrooms and onion\n2. Add rice, stir\n3. Gradually add stock, stirring\n4. Finish with parmesan",
                "35 mins", "Medium", "Dinner",
                new String[][]{
                        {"rice", "300", "g"},
                        {"mushrooms", "250", "g"},
                        {"onion", "1", "pcs"},
                        {"vegetable stock", "800", "ml"},
                        {"parmesan", "50", "g"}
                });

        createRecipe(db, "Fruit Salad",
                "Fresh mixed fruit medley",
                "1. Chop all fruits into bite-size pieces\n2. Mix with honey and lemon juice\n3. Chill before serving",
                "10 mins", "Easy", "Breakfast",
                new String[][]{
                        {"apples", "2", "pcs"},
                        {"bananas", "2", "pcs"},
                        {"oranges", "2", "pcs"},
                        {"honey", "20", "ml"}
                });

        createRecipe(db, "Lemon Garlic Chicken",
                "Pan-fried chicken with lemon sauce",
                "1. Season chicken\n2. Pan-fry until cooked through\n3. Make sauce with lemon, garlic and butter",
                "25 mins", "Medium", "Dinner",
                new String[][]{
                        {"chicken breast", "400", "g"},
                        {"lemon", "1", "pcs"},
                        {"garlic", "3", "pcs"},
                        {"butter", "30", "g"}
                });

        createRecipe(db, "Spaghetti Aglio e Olio",
                "Simple garlic and chilli pasta",
                "1. Cook spaghetti\n2. Saute garlic and chilli in olive oil\n3. Toss pasta through, add parsley",
                "15 mins", "Easy", "Dinner",
                new String[][]{
                        {"spaghetti", "250", "g"},
                        {"garlic", "4", "pcs"},
                        {"chilli flakes", "5", "g"},
                        {"olive oil", "30", "ml"}
                });

        createRecipe(db, "Avocado Toast",
                "Creamy avocado on toasted bread",
                "1. Toast bread\n2. Mash avocado with lemon\n3. Spread and top with chilli flakes",
                "5 mins", "Easy", "Breakfast",
                new String[][]{
                        {"bread", "2", "pcs"},
                        {"avocado", "1", "pcs"},
                        {"lemon", "0.5", "pcs"}
                });

        createRecipe(db, "Beef Tacos",
                "Seasoned mince in tortillas",
                "1. Brown mince with taco seasoning\n2. Warm tortillas\n3. Assemble with lettuce and cheese",
                "20 mins", "Medium", "Dinner",
                new String[][]{
                        {"mince", "300", "g"},
                        {"tortilla", "4", "pcs"},
                        {"lettuce", "100", "g"},
                        {"cheese", "80", "g"},
                        {"taco seasoning", "15", "g"}
                });

        createRecipe(db, "Greek Salad",
                "Fresh Mediterranean salad",
                "1. Chop tomatoes, cucumber, onion\n2. Add olives and feta\n3. Dress with olive oil and lemon",
                "10 mins", "Easy", "Lunch",
                new String[][]{
                        {"tomatoes", "300", "g"},
                        {"cucumber", "1", "pcs"},
                        {"feta cheese", "100", "g"},
                        {"olives", "50", "g"},
                        {"olive oil", "20", "ml"}
                });

        createRecipe(db, "Chocolate Mug Cake",
                "Single-serve cake in a mug",
                "1. Mix flour, sugar, cocoa, milk, oil in mug\n2. Microwave 90 seconds\n3. Let cool slightly",
                "5 mins", "Easy", "Breakfast",
                new String[][]{
                        {"flour", "60", "g"},
                        {"sugar", "40", "g"},
                        {"cocoa powder", "15", "g"},
                        {"milk", "45", "ml"},
                        {"vegetable oil", "20", "ml"}
                });

        createRecipe(db, "Lentil Soup",
                "Hearty lentil and vegetable soup",
                "1. Saute onion, carrot, celery\n2. Add lentils and stock\n3. Simmer 25 mins until tender",
                "35 mins", "Easy", "Lunch",
                new String[][]{
                        {"lentils", "200", "g"},
                        {"carrots", "150", "g"},
                        {"onion", "1", "pcs"},
                        {"vegetable stock", "600", "ml"},
                        {"garlic", "2", "pcs"}
                });
    }

    private static void createRecipe(AppDatabase db, String title, String summary,
                                      String steps, String duration, String difficulty,
                                      String category, String[][] ingredientMatrix) {
        Recipe recipe = new Recipe(title, summary, steps, duration, difficulty, category);
        long generatedId = db.recipeDao().insertHarvestRecipe(recipe);
        List<RecipeIngredient> ingredientsToInsert = new ArrayList<>();
        for (String[] singleIngredient : ingredientMatrix) {
            ingredientsToInsert.add(new RecipeIngredient((int) generatedId, singleIngredient[0],
                    Double.parseDouble(singleIngredient[1]), singleIngredient[2]));
        }
        db.recipeDao().insertHarvestIngredients(ingredientsToInsert);
    }
}
