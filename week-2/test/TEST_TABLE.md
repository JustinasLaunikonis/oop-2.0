# Week 2 - User Validation - Test Table

Default user in every setup: `User("Justinas", "Secret1!", "justinas@example.com", LocalDate.of(2000, 1, 15))` unless stated otherwise.

## UserTest

| # | Method | Input | Expected Output |
|---|--------|-------|-----------------|
| 1 | `setName(String)` | `null` | Throws `IllegalArgumentException` |
| 2 | `setName(String)` | `"   "` (blank) | Throws `IllegalArgumentException` |
| 3 | `setDateOfBirth(LocalDate)` | tomorrow | Throws `IllegalArgumentException` |
| 4 | `getAgeInYears(LocalDate)` | exactly 20 years ago | `20` |
| 5 | `getAgeInYears(LocalDate)` | 20 years ago plus one day | `19` |

## InMemoryUserStorageTest

| # | Method | Input | Expected Output |
|---|--------|-------|-----------------|
| 6 | `save(User)` | `null` | Throws `IllegalArgumentException` |
| 7 | `save(User)` | new user | `getUsers()` has size `1` and contains the user |
| 8 | `save(User)` | second user with name `"Justinas"` | Throws `IllegalArgumentException` |
| 9 | `containsName(String)` | `"Justinas"` after saving the user | `true` |
| 10 | `findByName(String)` | `"Nobody"` on empty storage | `null` |

## UserValidatorTest (rules: `EmailValidationRule`, `MinimumAgeValidationRule(18)`; adult born 20 years ago, child born 10 years ago)

| # | Method | Input | Expected Output |
|---|--------|-------|-----------------|
| 11 | `UserValidator(UserStorage)` | `null` | Throws `IllegalArgumentException` |
| 12 | `addRule(ValidationRule)` | `null` | Throws `IllegalArgumentException` |
| 13 | `failedRules(User)` | child | list of size `1` holding the `MinimumAgeValidationRule` |
| 14 | `register(User)` | adult | `true`, storage contains `"Justinas"` |
| 15 | `register(User)` | child | `false`, storage does not contain `"Timmy"` |

## PasswordValidationRuleTest (strict rule: no spaces, special, number, lowercase and uppercase required; lenient rule: everything allowed, nothing required)

| # | Method | Input | Expected Output |
|---|--------|-------|-----------------|
| 16 | `isValid(User)` | `null` | Throws `IllegalArgumentException` |
| 17 | `isValid(User)` | strict rule, password `"Secret1!"` | `true` |
| 18 | `isValid(User)` | strict rule, password `"Secret 1!"` | `false` (space) |
| 19 | `isValid(User)` | strict rule, password `"Secret1"` | `false` (no special character) |
| 20 | `isValid(User)` | lenient rule, password `"just words"` | `true` |

## MinimumAgeValidationRuleTest (`MinimumAgeValidationRule(18)`, user born exactly 18 years ago)

| # | Method | Input | Expected Output |
|---|--------|-------|-----------------|
| 21 | `setMinimumAgeInYears(int)` | `-1` | Throws `IllegalArgumentException` |
| 22 | `setMinimumAgeInYears(int)` | `0` (minimum) | minimumAgeInYears is `0` |
| 23 | `isValid(User)` | `null` | Throws `IllegalArgumentException` |
| 24 | `isValid(User)` | user turned 18 today | `true` |
| 25 | `isValid(User)` | user turns 18 tomorrow | `false` |

## EmailValidationRuleTest

| # | Method | Input | Expected Output |
|---|--------|-------|-----------------|
| 26 | `isValid(User)` | `null` | Throws `IllegalArgumentException` |
| 27 | `isValid(User)` | `"justinas.launikonis@student.nhlstenden.com"` | `true` |
| 28 | `isValid(User)` | `"justinas.example.com"` (no `@`) | `false` |
| 29 | `isValid(User)` | `"justinas@example"` (no top-level domain) | `false` |
| 30 | `isValid(User)` | `"@example.com"` (empty local part) | `false` |

## UniqueUsernameValidationRuleTest

| # | Method | Input | Expected Output |
|---|--------|-------|-----------------|
| 31 | `UniqueUsernameValidationRule(UserStorage)` | `null` | Throws `IllegalArgumentException` |
| 32 | `isValid(User)` | `null` | Throws `IllegalArgumentException` |
| 33 | `isValid(User)` | empty storage | `true` |
| 34 | `isValid(User)` | storage already holds `"Justinas"` | `false` |
| 35 | `isValid(User)` | storage holds `"justinas"` (different case) | `true` |
