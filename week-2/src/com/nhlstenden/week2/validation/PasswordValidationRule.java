package com.nhlstenden.week2.validation;

import com.nhlstenden.week2.user.User;

public class PasswordValidationRule implements ValidationRule
{
    public static final String SPECIAL_CHARACTERS = "!@#$%^&*()-_=+?.,:;";
    private boolean spacesAllowed;
    private boolean specialCharacterRequired;
    private boolean numberRequired;
    private boolean lowercaseRequired;
    private boolean uppercaseRequired;

    public PasswordValidationRule(boolean spacesAllowed, boolean specialCharacterRequired, boolean numberRequired, boolean lowercaseRequired, boolean uppercaseRequired)
    {
        this.setSpacesAllowed(spacesAllowed);
        this.setSpecialCharacterRequired(specialCharacterRequired);
        this.setNumberRequired(numberRequired);
        this.setLowercaseRequired(lowercaseRequired);
        this.setUppercaseRequired(uppercaseRequired);
    }

    public boolean isSpacesAllowed()
    {
        return this.spacesAllowed;
    }

    public void setSpacesAllowed(boolean spacesAllowed)
    {
        this.spacesAllowed = spacesAllowed;
    }

    public boolean isSpecialCharacterRequired()
    {
        return this.specialCharacterRequired;
    }

    public void setSpecialCharacterRequired(boolean specialCharacterRequired)
    {
        this.specialCharacterRequired = specialCharacterRequired;
    }

    public boolean isNumberRequired()
    {
        return this.numberRequired;
    }

    public void setNumberRequired(boolean numberRequired)
    {
        this.numberRequired = numberRequired;
    }

    public boolean isLowercaseRequired()
    {
        return this.lowercaseRequired;
    }

    public void setLowercaseRequired(boolean lowercaseRequired)
    {
        this.lowercaseRequired = lowercaseRequired;
    }

    public boolean isUppercaseRequired()
    {
        return this.uppercaseRequired;
    }

    public void setUppercaseRequired(boolean uppercaseRequired)
    {
        this.uppercaseRequired = uppercaseRequired;
    }

    @Override
    public boolean isValid(User user)
    {
        if (user == null)
        {
            throw new IllegalArgumentException("user cannot be null");
        }

        String password = user.getPassword();

        if (!this.spacesAllowed && password.contains(" "))
        {
            return false;
        }

        if (this.specialCharacterRequired && !this.containsSpecialCharacter(password))
        {
            return false;
        }

        if (this.numberRequired && !this.containsCharacterMatching(password, "[0-9]"))
        {
            return false;
        }

        if (this.lowercaseRequired && !this.containsCharacterMatching(password, "[a-z]"))
        {
            return false;
        }

        if (this.uppercaseRequired && !this.containsCharacterMatching(password, "[A-Z]"))
        {
            return false;
        }

        return true;
    }

    @Override
    public String getDescription()
    {
        StringBuilder description = new StringBuilder("Password");

        if (!this.spacesAllowed)
        {
            description.append(" may not contain spaces,");
        }

        if (this.specialCharacterRequired)
        {
            description.append(" must contain a special character (").append(SPECIAL_CHARACTERS).append("),");
        }

        if (this.numberRequired)
        {
            description.append(" must contain a number,");
        }

        if (this.lowercaseRequired)
        {
            description.append(" must contain a lowercase letter,");
        }

        if (this.uppercaseRequired)
        {
            description.append(" must contain an uppercase letter,");
        }

        if (description.charAt(description.length() - 1) == ',')
        {
            description.setLength(description.length() - 1);
        }
        else
        {
            description.append(" has no requirements");
        }

        return description.toString();
    }

    private boolean containsSpecialCharacter(String password)
    {
        for (char character : password.toCharArray())
        {
            if (SPECIAL_CHARACTERS.indexOf(character) >= 0)
            {
                return true;
            }
        }

        return false;
    }

    private boolean containsCharacterMatching(String password, String characterPattern)
    {
        for (char character : password.toCharArray())
        {
            if (String.valueOf(character).matches(characterPattern))
            {
                return true;
            }
        }

        return false;
    }
}
