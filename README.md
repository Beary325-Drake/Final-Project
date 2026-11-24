# Final-Project
Tim Dvorak, Nathan Summers

# Overview
Our final project will be a text based adventure game, where the player makes choices on where to go and how to perform combat 
# Features

# Structure
main.java # User interaction, combat loop
character.java # Abstract Superclass used in all things with stats
    player.java # The character controlled by the user. Contains player skills, level, current location, and stats
        skill_tree.java # Keeps track of what skills the player has unlocked, and what they ave yet to unlock
        location.java # Contains all information related to a single location, such as name, description, and next locations
    creature.java # The enemies that fight the player, contains enemy choices and stats
README.md
// Ideas.md (Temporary, shouldn't exist in the final product.)
# How to Run It
Run from App.java, use the terminal to give inputs that make in-game choices