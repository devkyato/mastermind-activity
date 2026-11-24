# MasterMind

A small code-breaking game built with Java Swing.

## How to play

Guess the four-color sequence in up to 10 attempts. After each guess, the
clues show how many colors are correct and in the right position (black) or
correct but in the wrong position (white). The available colors are blue,
green, red, yellow, pink, and orange.

The answer is currently fixed in the source code to blue, yellow, orange,
pink. Repeated colors can be selected.

## Requirements

- Java Development Kit (JDK) 8 or later

## Build and run

Run these commands from the repository root:

```sh
javac -d MasterMind/bin MasterMind/src/AnswerPanel.java MasterMind/src/ButtonPanel.java MasterMind/src/CluePanel.java MasterMind/src/ComboPanel.java MasterMind/src/MasterMind.java MasterMind/src/ResultPanel.java
java -cp MasterMind/bin MasterMind
```

The game opens in a desktop window, so it needs a graphical desktop session.

## Contributing

Bug reports and improvements are welcome. Open an issue to describe a bug or
proposed change, or submit a pull request with a concise explanation of what
you changed.

## License

This project is licensed under the MIT License. See [LICENSE](LICENSE).
