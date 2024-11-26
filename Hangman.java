package project1;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.InputMismatchException;
abstract class Hangman {
String wordToGuess; 
int count=0;
abstract String getRandomWord(String theme, int difficulty);
abstract String displayHangman(int attemptsLeft);
public void play() {
Scanner scanner = new Scanner(System.in);
char guess;
boolean gameWon = false;
// Theme selection
String selectedTheme = selectTheme(scanner);
// Difficulty selection
int difficulty = selectDifficulty(scanner);
// Select a random word from the chosen theme with selected difficulty
wordToGuess = getRandomWord(selectedTheme, 
difficulty);
// Initialize the guessed word with dashes
StringBuilder guessedWord = new
StringBuilder(wordToGuess.length());
for (int i = 0; i < wordToGuess.length(); i++) {
guessedWord.append('-');
}
// Track guessed letters
StringBuilder guessedLetters = new StringBuilder();
// Track attempts
int attemptsLeft = 0;
int points = 100; // Initial points
// Game loop
while (attemptsLeft < getMaxTries() && !gameWon) 
{
// Display hangman
System.out.println(displayHangman(attemptsLeft));
// Display the current state of the guessed word
System.out.println("Word: " + guessedWord);
System.out.println("Guessed letters: " + 
guessedLetters);
System.out.println("Tries left: " + (getMaxTries() -
attemptsLeft));
System.out.println("Points: " + points); // Display points
System.out.print("Enter a letter (or type 'hint' for a hint): ");
String input = scanner.nextLine().toLowerCase();
// If the input is "hint", provide a definition hint
if (input.equals("hint")) {
Hint(wordToGuess);
continue; // Skip the rest of the loop iteration
}
// Handle normal guess
guess = input.charAt(0);
// Check if the guessed letter is in the word
boolean letterGuessed = false;
for (int i = 0; i < wordToGuess.length(); i++) {
if (wordToGuess.charAt(i) == guess) {
guessedWord.setCharAt(i, guess);
letterGuessed = true;
}
}
// If the letter is not guessed before, add it to guessed letters
if
(!guessedLetters.toString().contains(String.valueOf(guess))) {
guessedLetters.append(guess);
}
// If the guessed letter is incorrect, increment tries and deduct points
if (!letterGuessed) {
attemptsLeft++;
points -= 10; // Deduct 10 points for each wrong guess
}
// Check if the word is fully guessed
if ((guessedWord.toString()).equals(wordToGuess)) {
gameWon = true;
count++;
}
}
// Game over message with points
if (gameWon) {
System.out.println("Congratulations! You've guessed the word: " + wordToGuess);
System.out.println("Total Points: " + points);
} else {
System.out.println("Sorry, you've run out of tries. The word was: " + wordToGuess);
// Display hangman for max tries reached
System.out.println(displayHangman(getMaxTries()));
System.out.println("Total Points: " + points);
}
}
int getcount() 
{
return count;
}
String getwordToGuess()
{
return wordToGuess;
}
String selectTheme(Scanner scanner) {
System.out.println("Choose a theme:");
System.out.println("1. Mythical Creatures");
System.out.println("2. Villains");
System.out.println("3. Technical");
System.out.println("4. Space and Astronomy");
System.out.println("5. Mythology");
System.out.print("Enter theme number: ");
try {
int choice = scanner.nextInt();
scanner.nextLine(); // Consume newline
switch (choice) {
case 1:
return "Mythical Creatures";
case 2:
return "Villains";
case 3:
return "Technical";
case 4:
return "Space and Astronomy";
case 5:
return "Mythology";
default:
System.out.println("Invalid choice. Using default theme.");
return "Default";
}
} catch (InputMismatchException e) {
System.out.println("Invalid input.InputMismatchException. Please enter a valid theme number.");
scanner.nextLine(); // Consume the invalid input
return selectTheme(scanner); // Recursively call selectTheme to retry input
}
}
int selectDifficulty(Scanner scanner) {
System.out.println("Choose a difficulty level:");
System.out.println("1. Easy");
System.out.println("2. Medium");
System.out.println("3. Hard");
System.out.print("Enter difficulty level: ");
try {
int choice = scanner.nextInt();
scanner.nextLine(); // Consume newline
switch (choice) {
case 1:
return 1; // Easy
case 2:
return 2; // Medium
case 3:
return 3; // Hard
default:
System.out.println("Invalid choice. Using medium difficulty.");
return 2; // Medium
}
} 
catch (InputMismatchException e) 
{
System.out.println("Invalid input.InputMismatchException. Please enter a valid difficulty level.");
scanner.nextLine(); // Consume the invalid input
return selectDifficulty(scanner); // Recursively call selectDifficulty to retry input
}
}
int getMaxTries() {
return 6;
}
// Method to provide a definition hint to the player
void Hint(String wordToGuess) {
int i;
int thints = 1;
List<String> hints = new ArrayList<>();
// Theme: Mythical Creatures
// Easy
hints.add("dragon: a mythical creature known for its fiery breath and scales");
hints.add("unicorn: a legendary animal often depicted with a horn on its forehead");
hints.add("fairy: Small, magical humanoid creature, associated with nature and enchantments.");
hints.add("elf: Graceful, long-lived humanoid creature with pointed ears, known for affinity for nature and magic.");
hints.add("goblin: Small, mischievous creature, often depicted as greedy and cunning.");
// Medium
hints.add("centaur: Creature with upper body of a human and lower body of a horse, known for strength and wisdom in Greek mythology.");
hints.add("griffin: Legendary creature with body of lion and head/wings of eagle, symbolizing strength and majesty.");
hints.add("satyr: Half-human, half-goat creature, loves music, wine, and revelry in Greek mythology.");
hints.add("phoenix: Mythical bird cyclically regenerates from its own ashes, symbolizing renewal and immortality.");
hints.add("nymph: A beautiful and immortal female spirit of nature, often associated with specific natural features like rivers, mountains, or forests.");
// Hard
hints.add("chimera: Monstrous creature with body of lion, goat head, and tail with snake's head, symbolizing fire-breathing and ferocity.");
hints.add("kraken: Legendary sea monster, giant size, cephalopod-like appearance, attacks ships.");
hints.add("hydra: Multi-headed serpent-like creature, capable of regenerating heads, nearly immortal.");
hints.add("minotaur: Creature with human body, bull head, imprisoned in King Minos' labyrinth, representing strength and savagery.");
hints.add("basilisk: Legendary serpent or lizard, can kill with gaze or venom, associated with dark magic and curses.");
// Theme: Villains
// Easy
hints.add("joker: Clown Prince of Crime in Gotham");
hints.add("loki: Trickster god in Norse mythology");
hints.add("sauron: Dark Lord in Middle-earth");
hints.add("grendel: Beast in Beowulf");
hints.add("hades: Ruler of the Underworld in Greek mythology");
// Medium
hints.add("moriarty: Arch-nemesis of Sherlock Holmes");
hints.add("thanos: Marvel supervillain obsessed with balance");
hints.add("ursula: Sea witch in 'The Little Mermaid'");
hints.add("maleficent: Evil fairy in 'Sleeping Beauty'");
hints.add("voldemort: Dark wizard in 'Harry Potter'");
// Hard
hints.add("medusa: Gorgon with snakes for hair");
hints.add("saruman: Corrupted wizard in Middleearth");
hints.add("jafar: Royal vizier in 'Aladdin'");
hints.add("magneto: Mutant with control over magnetism in X-Men");
hints.add("scar: Usurping lion in 'The Lion King'");
// Theme: Technical (Keywords)
// Easy
hints.add("java: Versatile programming language for building cross-platform applications.");
hints.add("html: Markup language for creating web pages.");
hints.add("css: Styling language for web page design.");
hints.add("cache: Quick-access storage for frequently used data.");
hints.add("byte: Basic unit of digital information.");
// Medium
hints.add("algorithm: A set of rules for solving a problem.");
hints.add("programming: Writing instructions for computers to perform tasks.");
hints.add("debugging: Identifying and fixing errors in code.");
hints.add("compiler: Converts human-readable code into machine-executable instructions.");
hints.add("database: Organized collection of data for efficient storage and retrieval.");
// Hard
hints.add("encryption: Securing data by converting it into unreadable form.");
hints.add("polymorphism: The ability of objects to take on multiple forms depending on their context.");
hints.add("cybersecurity: Safeguarding digital systems from harm or unauthorized access.");
hints.add("virtualization: Simulating multiple instances of resources for efficient use and management.");
hints.add("encapsulation: Wrapping data and methods within a class to control access and maintain code modularity.");
// Theme: Space and Astronomy
// Easy
hints.add("moon: Controls the tides, pulls on hearts, and inspires poets. Earth's only natural satellite, with a face that's always watching");
hints.add("star: A twinkling light in the night sky, powered by nuclear fusion.");
hints.add("earth: The blue marble, where life thrives in the vastness of space.");
hints.add("comet: A cosmic snowball with a glowing tail, heralding change.");
hints.add("rocket: Humanity's ticket to the stars, propelling us beyond Earth's bounds.");
// Medium
hints.add("galaxy: Contains billions of stars, planets, and mysteries waiting to be discovered.");
hints.add("nebula: Cosmic clouds of gas and dust, where stars are born and die");
hints.add("cosmology: The study of the universe's origin, structure, and evolution.");
hints.add("satellite: Artificial moons orbiting Earth, helping us communicate, navigate, and observe.");
hints.add("interstellar: Journeying between the stars, beyond the reach of our solar system.");
// Hard
hints.add("blackhole: A cosmic vacuum cleaner, swallowing everything that gets too close.");
hints.add("supernova: The explosive death of a massive star, outshining entire galaxies for weeks");
hints.add("quasar: Cosmic lighthouses powered by supermassive black holes, visible across the universe");
hints.add("parallax: A subtle dance of the stars, revealing cosmic distances and the Earth's orbit around the sun.");
hints.add("heliopause: The boundary where the sun's influence ends and interstellar space begins.");
// Theme: Mythology
// Easy
hints.add("zeus: Ruler of the gods in Greek mythology and was famous for his thunderbolt.");
hints.add("thor: The Norse god of thunder, carried the mighty hammer Mjolnir.");
hints.add("odin: Father of the Norse gods, was known for his wisdom and poetry.");
hints.add("hera: Queen of the Greek gods and was notorious for her jealousy.");
hints.add("loki: Norse god of mischief, was known for creating chaos.");
// Medium
hints.add("athena: The goddess of wisdom, warfare, and crafts, often shown with an owl by her side.");
hints.add("apollo: The god of the sun, music, poetry, and healing, known for his association with the lyre.");
hints.add("artemis: The goddess of the hunt, wilderness, and childbirth, typically depicted with a bow and arrows.");
hints.add("demeter: The goddess of agriculture, fertility, and the harvest, closely linked to grains.");
hints.add("dionysus: The god of wine, ecstasy, and fertility, famous for his wild revelry and madness.");
// Hard
hints.add("persephone: A Greek queen of the underworld who was abducted by Hades and is connected with spring and vegetation.");
hints.add("anubis: An Egyptian god, is known for mummification and the afterlife, and is often represented with the head of a jackal.");
hints.add("hades: A Greek god, rules the underworld  and the dead and is typically portrayed wearing a helm of invisibility.");
hints.add("freyja: A Norse goddess, is associated with love, beauty, fertility, and war, as well as with cats and falcon feathers.");
hints.add("marduk: A Babylonian god, is known for wisdom, justice, and storms, and is the slayer of the chaos dragon Tiamat.");
for (i = 0; (i < hints.size()) && (thints==1); i++) {
String definition = hints.get(i);
if (definition.startsWith(wordToGuess + ":")) {
System.out.println("Hint: " + 
definition.substring(wordToGuess.length() + 2));
thints--; // Decrement remaining hints
return;
}
}
if (thints == 1) {
System.out.println("No definition available for this word.");
}
}
}
class HangmanGame extends Hangman {
private static final
ArrayList<ArrayList<ArrayList<String>>> wordThemes = 
new ArrayList<>();
static {
// Theme: Mythical Creatures
ArrayList<ArrayList<String>> mythicalCreatures = new
ArrayList<>();
ArrayList<String> easyMythicalWords = new
ArrayList<>(List.of("dragon", "unicorn", "fairy", 
"elf", "goblin"));
ArrayList<String> mediumMythicalWords = new
ArrayList<>(List.of("centaur", "griffin", "satyr", 
"phoenix", "nymph"));
ArrayList<String> hardMythicalWords = new
ArrayList<>(List.of("chimera", "kraken", "hydra", 
"minotaur", "basilisk"));
mythicalCreatures.add(easyMythicalWords);
mythicalCreatures.add(mediumMythicalWords);
mythicalCreatures.add(hardMythicalWords);
wordThemes.add(mythicalCreatures);
// Theme: Villains
ArrayList<ArrayList<String>> villains = new
ArrayList<>();
ArrayList<String> easyVillainWords = new
ArrayList<>(List.of("joker", "loki", "sauron", 
"grendel", "hades"));
ArrayList<String> mediumVillainWords = new
ArrayList<>(List.of("moriarty", "thanos", "ursula", 
"maleficent", "voldemort"));
ArrayList<String> hardVillainWords = new
ArrayList<>(List.of("medusa", "saruman", "jafar", 
"magneto", "scar"));
villains.add(easyVillainWords);
villains.add(mediumVillainWords);
villains.add(hardVillainWords);
wordThemes.add(villains);
// Theme: Technical
ArrayList<ArrayList<String>> technical = new
ArrayList<>();
ArrayList<String> easyTechnicalWords = new
ArrayList<>(List.of("java", "html", "css", "cache", 
"byte"));
ArrayList<String> mediumTechnicalWords = new
ArrayList<>(List.of("algorithm", "programming", 
"debugging", "compiler", "database"));
ArrayList<String> hardTechnicalWords = new
ArrayList<>(List.of("encryption", "polymorphism", 
"cybersecurity", "virtualization", "encapsulation"));
technical.add(easyTechnicalWords);
technical.add(mediumTechnicalWords);
technical.add(hardTechnicalWords);
wordThemes.add(technical);
// Theme: Space and Astronomy
ArrayList<ArrayList<String>> spaceAndAstronomy = new
ArrayList<>();
ArrayList<String> easySpaceWords = new
ArrayList<>(List.of("moon", "star", 
"earth","comet","rocket"));
ArrayList<String> mediumSpaceWords = new
ArrayList<>(List.of("galaxy", "nebula", 
"cosmology","satellite","interstellar"));
ArrayList<String> hardSpaceWords = new
ArrayList<>(List.of("blackhole", "supernova", 
"quasar","parallax","heliopause"));
spaceAndAstronomy.add(easySpaceWords);
spaceAndAstronomy.add(mediumSpaceWords);
spaceAndAstronomy.add(hardSpaceWords);
wordThemes.add(spaceAndAstronomy);
// Theme: Mythology
ArrayList<ArrayList<String>> mythology = new
ArrayList<>();
ArrayList<String> easyMythologyWords = new
ArrayList<>(List.of("zeus", "thor", "odin", "hera", 
"loki"));
ArrayList<String> mediumMythologyWords = new
ArrayList<>(List.of("athena", "apollo", "artemis", 
"demeter", "dionysus"));
ArrayList<String> hardMythologyWords = new
ArrayList<>(List.of("persephone", "anubis", "hades", 
"freyja", "marduk"));
mythology.add(easyMythologyWords);
mythology.add(mediumMythologyWords);
mythology.add(hardMythologyWords);
wordThemes.add(mythology);
}
String getRandomWord(String theme, int difficulty) {
Random rand = new Random();
int themeIndex = -1;
switch (theme) {
case "Mythical Creatures":
themeIndex = 0;
break;
case "Villains":
themeIndex = 1;
break;
case "Technical":
themeIndex = 2;
break;
case "Space and Astronomy":
themeIndex = 3;
break;
case "Mythology":
themeIndex = 4;
break;
default:
break;
}
if (themeIndex != -1 && difficulty >= 1 && difficulty
<= 3) {
ArrayList<String> words = 
wordThemes.get(themeIndex).get(difficulty - 1);
return words.get(rand.nextInt(words.size()));
} else {
// Return a default word if theme or difficulty is invalid
return "hangman";
}
}
String displayHangman(int attemptsLeft) {
StringBuilder hangman = new StringBuilder();
switch (attemptsLeft) {
case 1:
hangman.append(" ______\n");
hangman.append(" | |\n");
hangman.append(" | O\n");
hangman.append(" |\n");
hangman.append(" |\n");
hangman.append(" |\n");
hangman.append("|\n");
break;
case 2:
hangman.append(" ______\n");
hangman.append(" | |\n");
hangman.append(" | O\n");
hangman.append(" | |\n");
hangman.append(" |\n");
hangman.append(" |\n");
hangman.append("|\n");
break;
case 3:
hangman.append(" ______\n");
hangman.append(" | |\n");
hangman.append(" | O\n");
hangman.append(" | /|\n");
hangman.append(" |\n");
hangman.append(" |\n");
hangman.append("|\n");
break;
case 4:
hangman.append(" ______\n");
hangman.append(" | |\n");
hangman.append(" | O\n");
hangman.append(" | /|\\\n");
hangman.append(" |\n");
hangman.append(" |\n");
hangman.append("|\n");
break;
case 5:
hangman.append(" ______\n");
hangman.append(" | |\n");
hangman.append(" | O\n");
hangman.append(" | /|\\\n");
hangman.append(" | /\n");
hangman.append(" |\n");
hangman.append("|\n");
break;
case 6:
hangman.append(" ______\n");
hangman.append(" | |\n");
hangman.append(" | O\n");
hangman.append(" | /|\\\n");
hangman.append(" | / \\\n");
hangman.append(" |\n");
hangman.append("|\n");
break;
default:
hangman.append(" ______\n");
hangman.append(" | |\n");
hangman.append(" |\n");
hangman.append(" |\n");
hangman.append(" |\n");
hangman.append(" |\n");
hangman.append("|\n");
}
return hangman.toString();
}
}
 class Main 
{
static String intialw;
public static void main(String[] args) 
{
int fcount;
Hangman hangmanGame = new HangmanGame();
Scanner scanner = new Scanner(System.in);
boolean playAgain = true;
// Creating a TimerTask to manage time limit
TimerTask task = new TimerTask() {
public void run() 
{
intialw=hangmanGame.getwordToGuess();
System.out.println("the word to be guessed is"+intialw);
System.out.println("Time's up! You've run out of time.");
System.exit(0); // Exiting the program when time's up
}
};
// Creating a Timer object
Timer timer = new Timer();
// Start the timer before the loop
timer.schedule(task, 120 * 1000); // 120 seconds
while (playAgain) 
{
// Start the game
hangmanGame.play();
fcount=hangmanGame.getcount();
System.out.println("The number of correct guesses are: "+fcount);
// Ask if the user wants to play again
System.out.print("Do you want to play again? (yes/no): ");
String input = scanner.nextLine().toLowerCase();
playAgain = input.equals("yes");
}
// Cancel the timer after the loop ends
timer.cancel();
// Close the scanner
scanner.close();
}
}
