# Week 1 - Kingdoms & Quests - Test Table

## CharacterTest (tested through `Warrior("Aldric", 100, 20, 5)` because `Character` is abstract, enemy `Warrior("Goblin", 50, 8, 3)`)

| # | Method | Input | Expected Output |
|---|--------|-------|-----------------|
| 1 | `attack(Character)` | `null` | Throws `IllegalArgumentException` |
| 2 | `attack(Character)` | enemy with 50 HP and defence 3 | enemy has `33` HP (50 - (20 - 3)) |
| 3 | `defend(int)` | `5` (equal to defence) | healthPoints stays `100` |
| 4 | `defend(int)` | `500` (more than remaining HP) | healthPoints is `0`, not negative |
| 5 | `isAlive()` | 0 HP | `false` |

## WarriorTest (`Warrior("Aldric", 100, 20, 5)`, enemy `Mage("Shade", 60, 12, 2)`)

| # | Method | Input | Expected Output |
|---|--------|-------|-----------------|
| 6 | `useSpecialAbility()` | called once | attackPower is `30` |
| 7 | `useSpecialAbility()` | called twice | attackPower stays `30`, boost does not stack |
| 8 | `attack(Character)` | after `useSpecialAbility()` | enemy has `32` HP (60 - (30 - 2)) |
| 9 | `resetSpecialAbility()` | after `useSpecialAbility()` | attackPower is back to `20` |
| 10 | `resetSpecialAbility()` | without prior use | attackPower stays `20` |

## MageTest (`Mage("Shade", 60, 12, 2)`)

| # | Method | Input | Expected Output |
|---|--------|-------|-----------------|
| 11 | `useSpecialAbility()` | called once | defencePower is `12` |
| 12 | `useSpecialAbility()` | called twice | defencePower stays `12`, boost does not stack |
| 13 | `defend(int)` | `20` after `useSpecialAbility()` | healthPoints is `52` (60 - (20 - 12)) |
| 14 | `resetSpecialAbility()` | after `useSpecialAbility()` | defencePower is back to `2` |
| 15 | `resetSpecialAbility()` | without prior use | defencePower stays `2` |

## ArcherTest (`Archer("Lyra", 80, 15, 3)`, enemy `Warrior("Goblin", 50, 8, 3)`)

| # | Method | Input | Expected Output |
|---|--------|-------|-----------------|
| 16 | `countDamage()` | special ability inactive | returns `15` |
| 17 | `countDamage()` | after `useSpecialAbility()` | returns `30` |
| 18 | `useSpecialAbility()` | called once | attackPower stays `15`, only the damage is doubled |
| 19 | `attack(Character)` | after `useSpecialAbility()` | enemy has `23` HP (50 - (30 - 3)) |
| 20 | `resetSpecialAbility()` | after `useSpecialAbility()` | `countDamage()` returns `15` |

## ItemTest (`Item("Silver Sword")`)

| # | Method | Input | Expected Output |
|---|--------|-------|-----------------|
| 21 | `Item(String)` | `null` | Throws `IllegalArgumentException` |
| 22 | `Item(String)` | `"   "` | Throws `IllegalArgumentException` |
| 23 | `Item(String)` | `"Silver Sword"` | title is `"Silver Sword"` |

## QuestTest (`Quest("Clear the cellar", 3, 50, Warrior("Goblin", 50, 8, 3))`)

| # | Method | Input | Expected Output |
|---|--------|-------|-----------------|
| 24 | `setDifficulty(int)` | `0` (below minimum) | Throws `IllegalArgumentException` |
| 25 | `setDifficulty(int)` | `11` (above maximum) | Throws `IllegalArgumentException` |
| 26 | `setDifficulty(int)` | `10` (maximum) | difficulty is `10` |
| 27 | `Quest(String, int, int, Character)` | enemy `null` | Throws `IllegalArgumentException` |
| 28 | `requiredExperiencePoints()` | difficulty 3 | returns `20` ((3 - 1) * 10) |

## SpecialQuestTest (`SpecialQuest("Raid the crypt", 5, 80, Mage("Ghoul", 40, 10, 2))`)

| # | Method | Input | Expected Output |
|---|--------|-------|-----------------|
| 29 | `SpecialQuest(String, int, int, Character)` | valid values | items list is empty |
| 30 | `addItem(Item)` | `null` | Throws `IllegalArgumentException` |
| 31 | `addItem(Item)` | `Item("Silver Sword")` | items contains the item |
| 32 | `requiredExperiencePoints()` | difficulty 5 | returns `40` ((5 - 1) * 10) |

## PlayerTest (`Player("Justinas", Warrior("Aldric", 100, 20, 5))`)

Quests: easy `Quest("Clear the cellar", 1, 50, Warrior("Goblin", 50, 8, 3))`, hard `Quest("Slay the dragon", 3, 100, Warrior("Dragon", 300, 40, 20))`, special `SpecialQuest("Raid the crypt", 1, 50, Mage("Ghoul", 40, 10, 2))` holding `Item("Silver Sword")`.

| # | Method | Input | Expected Output |
|---|--------|-------|-----------------|
| 33 | `canPlayQuest(Quest)` | hard quest (needs 20 XP, player has 0) | `false` |
| 34 | `playQuest(Quest)` | hard quest (locked) | Throws `IllegalArgumentException` |
| 35 | `playQuest(Quest)` | easy quest, character wins | experiencePoints is `50` |
| 36 | `playQuest(Quest)` | special quest, character wins | player items contains `Item("Silver Sword")` |
| 37 | `levelUp()` | with 200 XP | level is `2` |

## GameTest (`Game()` with the same player, easy and hard quest as PlayerTest)

| # | Method | Input | Expected Output |
|---|--------|-------|-----------------|
| 38 | `addQuest(Quest)` | `null` | Throws `IllegalArgumentException` |
| 39 | `addPlayer(Player)` | `null` | Throws `IllegalArgumentException` |
| 40 | `availableQuestsFor(Player)` | `null` | Throws `IllegalArgumentException` |
| 41 | `availableQuestsFor(Player)` | no quests added | empty list |
| 42 | `availableQuestsFor(Player)` | easy and hard quest added, new player | list with only the easy quest |
